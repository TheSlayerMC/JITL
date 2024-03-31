package net.jitl.common.entity.boss;

import net.jitl.client.gui.BossBarRenderer;
import net.jitl.common.entity.base.JFlyingBossEntity;
import net.jitl.common.entity.base.MobStats;
import net.jitl.common.entity.goal.AttackWhenDifficultGoal;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JLootTables;
import net.jitl.core.init.internal.JParticleManager;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;

import java.util.Objects;

public class Scale extends JFlyingBossEntity {

    private final ServerBossEvent BOSS_INFO = new ServerBossEvent(Objects.requireNonNull(this.getDisplayName()), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.NOTCHED_6);
    private final BossBarRenderer BOSS_BAR = new BossBarRenderer(this, JITL.rl("textures/gui/bossbars/scale.png"));
    private static final EntityDataAccessor<Boolean> DATA_IS_CHARGING = SynchedEntityData.defineId(Scale.class, EntityDataSerializers.BOOLEAN);

    public Scale(EntityType<? extends Scale> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setFlyingSpeed(0.2D);
    }

    @Override
    public void addGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(7, new Scale.ShootFireballGoal(this));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new AttackWhenDifficultGoal(this, this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, null));
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.WITHER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return SoundEvents.WITHER_HURT;
    }

    @Override
    public boolean despawnInPeaceful() {
        return false;
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        this.BOSS_INFO.removePlayer(player);
    }

    @Override
    public void startSeenByPlayer(@NotNull ServerPlayer player) {
        if(showBarWhenSpawned())
            this.BOSS_INFO.addPlayer(player);
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.SCALE_HEALTH)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.SCALE_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_BOSS_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.STANDARD_MOVEMENT_SPEED).build();
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            for (int i = 0; i < 6; i++)
                this.level().addParticle(JParticleManager.CRYSTAL_FRUIT.get(), this.position().x + (this.random.nextDouble() - 0.5D), this.position().y + this.random.nextDouble(), this.position().z + (this.random.nextDouble() - 0.5D), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
        }
    }

    @Override
    public BossBarRenderer getBossBar() {
        return BOSS_BAR;
    }

    @Override
    public ServerBossEvent getEvent() {
        return BOSS_INFO;
    }

    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.scale.idle");

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, state -> state.setAndContinue(IDLE)));
    }

    @Override
    public boolean wantsToAttack(LivingEntity target, LivingEntity living) {
        return level().getDifficulty() != Difficulty.PEACEFUL;
    }

    @Override
    protected @Nullable BossCrystal.Type getDeathCrystalType() {
        return BossCrystal.Type.DEPTHS;
    }

    @Override
    public ResourceLocation lootTable() {
        return JLootTables.SCALE_CRYSTAL;
    }

    @Override
    public boolean showBarWhenSpawned() {
        return false;
    }


    public void setCharging(boolean pCharging) {
        this.entityData.set(DATA_IS_CHARGING, pCharging);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_IS_CHARGING, false);
    }

    public static class ShootFireballGoal extends Goal {
        private final Scale scale;
        public int chargeTime;

        public ShootFireballGoal(Scale pscale) {
            this.scale = pscale;
        }

        @Override
        public boolean canUse() {
            return this.scale.getTarget() != null;
        }

        @Override
        public void start() {
            this.chargeTime = 0;
        }

        @Override
        public void stop() {
            this.scale.setCharging(false);
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            LivingEntity livingentity = this.scale.getTarget();
            if(livingentity != null) {
                if(livingentity.distanceToSqr(this.scale) < 4096.0D && this.scale.hasLineOfSight(livingentity)) {
                    Level level = this.scale.level();
                    this.chargeTime++;
                    if(this.chargeTime == 10 && !this.scale.isSilent()) {
                        level.levelEvent((Player)null, 1015, this.scale.blockPosition(), 0);
                    }

                    if(this.chargeTime == 20) {
                        Vec3 vec3 = this.scale.getViewVector(1.0F);
                        double d2 = livingentity.getX() - (this.scale.getX() + vec3.x * 4.0D);
                        double d3 = livingentity.getY(0.5D) - (0.5D + this.scale.getY(0.5D));
                        double d4 = livingentity.getZ() - (this.scale.getZ() + vec3.z * 4.0D);
                        if(!this.scale.isSilent()) {
                            level.levelEvent(null, 1016, this.scale.blockPosition(), 0);
                        }
                        SmallFireball fireball = new SmallFireball(level, this.scale, d2, d3, d4);
                        fireball.setPos(this.scale.getX() + vec3.x * 4.0D, this.scale.getY(0.5D) + 0.5D, fireball.getZ() + vec3.z * 4.0D);
                        level.addFreshEntity(fireball);
                        this.chargeTime = -40;
                    }
                } else if(this.chargeTime > 0) {
                    this.chargeTime--;
                }

                this.scale.setCharging(this.chargeTime > 10);
            }
        }
    }
}