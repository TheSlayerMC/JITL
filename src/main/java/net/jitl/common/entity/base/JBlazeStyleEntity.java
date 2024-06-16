package net.jitl.common.entity.base;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;

public abstract class JBlazeStyleEntity extends JMonsterEntity {

    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(JBlazeStyleEntity.class, EntityDataSerializers.BYTE);
    private int nextHeightOffsetChangeTick;
    private float allowedHeightOffset = 0.5F;
    public boolean canSpawnSmoke = true;

    public JBlazeStyleEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new JBlazeStyleEntity.JBlazeStyleEntityAttackGoal(this));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(1, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, null));
    }

    public boolean canSpawnSmoke(boolean smoke) {
        return canSpawnSmoke = smoke;
    }

    @Override
    protected void customServerAiStep() {
        this.nextHeightOffsetChangeTick--;
        if (this.nextHeightOffsetChangeTick <= 0) {
            this.nextHeightOffsetChangeTick = 100;
            this.allowedHeightOffset = (float)this.random.triangle(0.5D, 6.891D);
        }

        LivingEntity target = this.getTarget();
        if(target != null && target.getEyeY() > this.getEyeY() + (double)this.allowedHeightOffset && this.canAttack(target)) {
            Vec3 vec3 = this.getDeltaMovement();
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, ((double)0.3F - vec3.y) * (double)0.3F, 0.0D));
            this.hasImpulse = true;
        }
        super.customServerAiStep();
    }

    @Override
    public void aiStep() {
        if (!this.onGround() && this.getDeltaMovement().y < 0.0D) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D));
        }

        if (this.level().isClientSide) {
            if(canSpawnSmoke) {
                if(this.random.nextInt(24) == 0 && !this.isSilent())
                    this.level().playLocalSound(this.getX() + 0.5D, this.getY() + 0.5D, this.getZ() + 0.5D, SoundEvents.BLAZE_BURN, this.getSoundSource(), 1.0F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F, false);

                for(int i = 0; i < 1; i++)
                    this.level().addParticle(ParticleTypes.LARGE_SMOKE, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);

            }
        }

        super.aiStep();
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.BLAZE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return SoundEvents.BLAZE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLAZE_DEATH;
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, @NotNull DamageSource pSource) {
        return false;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(DATA_FLAGS_ID, (byte)0);
    }

    void setCharged(boolean pCharged) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if(pCharged) {
            b0 = (byte)(b0 | 1);
        } else {
            b0 = (byte)(b0 & -2);
        }

        this.entityData.set(DATA_FLAGS_ID, b0);
    }

    static class JBlazeStyleEntityAttackGoal extends Goal {
        private final JBlazeStyleEntity blaze;
        private int attackStep;
        private int attackTime;
        private int lastSeen;

        public JBlazeStyleEntityAttackGoal(JBlazeStyleEntity pBlaze) {
            this.blaze = pBlaze;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity livingentity = this.blaze.getTarget();
            return livingentity != null && livingentity.isAlive() && this.blaze.canAttack(livingentity);
        }

        @Override
        public void start() {
            this.attackStep = 0;
        }

        @Override
        public void stop() {
            this.blaze.setCharged(false);
            this.lastSeen = 0;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            --this.attackTime;
            LivingEntity livingentity = this.blaze.getTarget();
            if(livingentity != null) {
                boolean flag = this.blaze.getSensing().hasLineOfSight(livingentity);
                if(flag) {
                    this.lastSeen = 0;
                } else {
                    ++this.lastSeen;
                }

                double d0 = this.blaze.distanceToSqr(livingentity);
                if(d0 < 4.0D) {
                    if(!flag) {
                        return;
                    }

                    if(this.attackTime <= 0) {
                        this.attackTime = 20;
                        this.blaze.doHurtTarget(livingentity);
                    }

                    this.blaze.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
                } else if(d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
                    double d1 = livingentity.getX() - this.blaze.getX();
                    double d2 = livingentity.getY(0.5D) - this.blaze.getY(0.5D);
                    double d3 = livingentity.getZ() - this.blaze.getZ();
                    if(this.attackTime <= 0) {
                        ++this.attackStep;
                        if(this.attackStep == 1) {
                            this.attackTime = 60;
                            this.blaze.setCharged(true);
                        } else if(this.attackStep <= 4) {
                            this.attackTime = 6;
                        } else {
                            this.attackTime = 100;
                            this.attackStep = 0;
                            this.blaze.setCharged(false);
                        }
                        if(this.attackStep > 1) {
                            double d4 = Math.sqrt(Math.sqrt(d0)) * 0.5D;
                            if(!this.blaze.isSilent()) {
                                this.blaze.level().levelEvent(null, 1018, this.blaze.blockPosition(), 0);
                            }
                            for(int i = 0; i < 1; ++i) {
                                Vec3 vec3 = new Vec3(this.blaze.getRandom().triangle(d1, 2.297 * d4), d2, this.blaze.getRandom().triangle(d3, 2.297 * d4));
                                SmallFireball smallfireball = new SmallFireball(this.blaze.level(), this.blaze, vec3.normalize());
                                smallfireball.setPos(smallfireball.getX(), this.blaze.getY(0.5D) + 0.5D, smallfireball.getZ());
                                this.blaze.level().addFreshEntity(smallfireball);
                            }
                        }
                    }
                    this.blaze.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
                } else if(this.lastSeen < 5) {
                    this.blaze.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
                }

                super.tick();
            }
        }
        private double getFollowDistance() {
            return this.blaze.getAttributeValue(Attributes.FOLLOW_RANGE);
        }
    }
}