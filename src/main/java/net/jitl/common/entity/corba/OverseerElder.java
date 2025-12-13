package net.jitl.common.entity.corba;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.entity.base.JFlyingEntity;
import net.jitl.common.entity.base.MobStats;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
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
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class OverseerElder extends JFlyingEntity {

    public OverseerElder(EntityType<? extends JFlyingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setKnowledge(EnumKnowledge.CORBA, 5F);
    }

    @Override
    public void addGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new OverseerElder.ShootFireballGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (e, s) -> Math.abs(e.getY() - this.getY()) <= 4.0D));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JSounds.OVERSEER.get();
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return JSounds.OVERSEER_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JSounds.OVERSEER_DEATH.get();
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.OVERSEER_ELDER_HEALTH)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.STANDARD_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.STANDARD_MOVEMENT_SPEED).build();
    }

    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.overseer_elder.idle");

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("controller", 5, state -> state.setAndContinue(IDLE)));
    }

    @Override
    public boolean despawnInPeaceful() {
        return true;
    }

    @Override
    protected float getSoundVolume() {
        return 2.5F;
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 2;
    }

    public static boolean checkSpawn(EntityType<OverseerElder> entity, ServerLevelAccessor level, EntitySpawnReason spawnType, BlockPos pos, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL && random.nextInt(10) == 0 && checkMobSpawnRules(entity, level, spawnType, pos, random);
    }

    public static class ShootFireballGoal extends Goal {
        private final OverseerElder seer;
        public int chargeTime;

        public ShootFireballGoal(OverseerElder pseer) {
            this.seer = pseer;
        }

        @Override
        public boolean canUse() {
            return this.seer.getTarget() != null;
        }

        @Override
        public void start() {
            this.chargeTime = 0;
        }

        @Override
        public void stop() {

        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            LivingEntity livingentity = this.seer.getTarget();
            if(livingentity != null) {
                if(livingentity.distanceToSqr(this.seer) < 4096.0D && this.seer.hasLineOfSight(livingentity)) {
                    Level level = this.seer.level();
                    this.chargeTime--;
                    if(this.chargeTime == 10 && !this.seer.isSilent())
                        level.levelEvent(null, 1015, this.seer.blockPosition(), 0);

                    if(this.chargeTime == 20) {
                        Vec3 vec3 = this.seer.getViewVector(1.0F);
                        double d2 = livingentity.getX() - (this.seer.getX() + vec3.x * 4.0D);
                        double d3 = livingentity.getY(0.5D) - (0.5D + this.seer.getY(0.5D));
                        double d4 = livingentity.getZ() - (this.seer.getZ() + vec3.z * 4.0D);
                        Vec3 vec31 = new Vec3(d2, d3, d4);

                        if(!this.seer.isSilent())
                            level.levelEvent(null, 1016, this.seer.blockPosition(), 0);

                        SmallFireball fireball = new SmallFireball(level, this.seer, vec31.normalize());

                        fireball.setPos(this.seer.getX() + vec3.x * 4.0D, this.seer.getY(0.5D) + 0.5D, fireball.getZ() + vec3.z * 4.0D);
                        level.addFreshEntity(fireball);
                        this.chargeTime = -40;
                    }
                } else if(this.chargeTime > 0) {
                    this.chargeTime--;
                }
            }
        }
    }
}