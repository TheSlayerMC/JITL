package net.jitl.common.entity.base;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.EnumSet;

public abstract class JFlyingEntity extends FlyingMob implements Enemy, IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    public JFlyingEntity(EntityType<? extends JFlyingEntity> type, Level worldIn) {
        super(type, worldIn);
        this.moveControl = new JFlyingEntity.MoveHelperController(this);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    protected abstract <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event);

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new JFlyingEntity.RandomFlyGoal(this));
        this.goalSelector.addGoal(7, new JFlyingEntity.LookAroundGoal(this));
        this.addGoals();
    }

    public abstract void addGoals();

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return despawnInPeaceful();
    }

    public abstract boolean despawnInPeaceful();

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

    static class MoveHelperController extends MoveControl {
        private final JFlyingEntity entity;
        private int floatDuration;

        public MoveHelperController(JFlyingEntity entity) {
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
                if (!this.entity.level.noCollision(this.entity, a)) {
                    return false;
                }
            }

            return true;
        }
    }

    static class RandomFlyGoal extends Goal {
        private final JFlyingEntity entity;

        public RandomFlyGoal(JFlyingEntity entity) {
            this.entity = entity;
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
            double d1 = this.entity.getY() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.entity.getZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.entity.getMoveControl().setWantedPosition(d0, d1, d2, 0.3D);
        }
    }
}