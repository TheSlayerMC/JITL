package net.jitl.common.entity.corba;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.entity.base.JFlyingEntity;
import net.jitl.common.entity.base.MobStats;
import net.jitl.common.entity.euca.Shimmerer;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animation.AnimatableManager;

import java.util.EnumSet;

public class SwampFly extends JFlyingEntity {

    public SwampFly(EntityType<? extends JFlyingEntity> type, Level worldIn) {
        super(type, worldIn);
        setKnowledge(EnumKnowledge.CORBA, 2F);
        this.moveControl = new SwampFly.FlyMoveHelperController(this);
    }

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) { }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.SWAMP_FLY_HEALTH)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.STANDARD_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.STANDARD_MOVEMENT_SPEED).build();
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 10;
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(itemstack.is(Items.GLASS_BOTTLE)) {
            player.playSound(JSounds.BOTTLE_PLUG.get(), 1.0F, 1.0F);
            ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, JBlocks.SWAMP_LAMP.get().asItem().getDefaultInstance());
            player.setItemInHand(hand, itemstack1);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(player, hand);
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new SwampFly.FlyRandomFlyGoal(this, 0.2D));
        this.goalSelector.addGoal(7, new SwampFly.LookAroundGoal(this));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
    }

    @Override
    public void addGoals() {

    }

    @Override
    public boolean despawnInPeaceful() {
        return false;
    }

    static class LookAroundGoal extends Goal {
        private final JFlyingEntity entity;

        public LookAroundGoal(JFlyingEntity entity) {
            this.entity = entity;
            this.setFlags(EnumSet.of(Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return true;
        }

        @Override
        public void tick() {
            Vec3 vector3d = this.entity.getDeltaMovement();
            this.entity.setYRot(-((float) Mth.atan2(vector3d.x, vector3d.z)) * (180F / (float) Math.PI));
            this.entity.yBodyRot = this.entity.getYRot();
        }
    }

    static class FlyMoveHelperController extends MoveControl {
        private final JFlyingEntity entity;
        private int floatDuration;

        public FlyMoveHelperController(JFlyingEntity entity) {
            super(entity);
            this.entity = entity;
        }

        @Override
        public void tick() {
            if(this.operation == Operation.MOVE_TO) {
                if(this.floatDuration-- <= 0) {
                    this.floatDuration += this.entity.getRandom().nextInt(3) + 1;
                    Vec3 vector3d = new Vec3(this.wantedX - this.entity.getX(), this.wantedY - this.entity.getY(), this.wantedZ - this.entity.getZ());
                    double d0 = vector3d.length();
                    vector3d = vector3d.normalize();
                    if(this.canReach(vector3d, Mth.ceil(d0))) {
                        this.entity.setDeltaMovement(this.entity.getDeltaMovement().add(vector3d.scale(0.1D)));
                    } else {
                        this.operation = Operation.WAIT;
                    }
                }

            }
        }

        private boolean canReach(Vec3 vector3d_, int int_) {
            AABB a = this.entity.getBoundingBox();

            for(int i = 1; i < int_; ++i) {
                a = a.move(vector3d_);
                if (!this.entity.level().noCollision(this.entity, a)) {
                    return false;
                }
            }

            return true;
        }
    }

    static class FlyRandomFlyGoal extends Goal {
        private final JFlyingEntity entity;
        public final double speed;

        public FlyRandomFlyGoal(JFlyingEntity entity, double speed) {
            this.entity = entity;
            this.speed = speed;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            MoveControl m = this.entity.getMoveControl();
            if (!m.hasWanted()) {
                return true;
            } else {
                double d0 = m.getWantedX() - this.entity.getX();
                double d1 = m.getWantedY() - this.entity.getY();
                double d2 = m.getWantedZ() - this.entity.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        @Override
        public boolean canContinueToUse() {
            return false;
        }

        @Override
        public void start() {
            RandomSource random = this.entity.getRandom();
            double d0 = this.entity.getX() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.entity.getY() + (double)((random.nextFloat() * 2.0F - 1.0F) * 8.0F);
            double d2 = this.entity.getZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.entity.getMoveControl().setWantedPosition(d0, d1, d2, this.speed);
        }
    }
}
