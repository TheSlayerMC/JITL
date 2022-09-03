package net.jitl.common.entity.base;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public abstract class AnimatableMonster extends Monster implements IAnimatable {

    private static final EntityDataAccessor<Boolean> ATTACK = SynchedEntityData.defineId(AnimatableMonster.class, EntityDataSerializers.BOOLEAN);
    private final AnimationFactory factory = new AnimationFactory(this);

    protected AnimatableMonster(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    protected abstract <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event);

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACK, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("attack", isAttacking());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if(compound.contains("attack")) {
            setAttacking(compound.getBoolean("attack"));
        }
    }

    public boolean isAttacking() {
        return getEntityData().get(ATTACK);
    }

    public void setAttacking(boolean value) {
        getEntityData().set(ATTACK, value);
    }

    public class AnimatedAttackGoal extends MeleeAttackGoal {
        private final AnimatableMonster entity;

        public AnimatedAttackGoal(AnimatableMonster entity, double speed, boolean useLongMemory) {
            super(entity, speed, useLongMemory);
            this.entity = entity;
        }

        @Override
        public void start() {
            super.start();
        }

        @Override
        public void stop() {
            super.stop();
            this.entity.setAggressive(false);
            setAttacking(false);
        }

        @Override
        public boolean canUse() {
            LivingEntity livingEntity = getTarget();
            return livingEntity != null && livingEntity.isAlive();
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
            double d0 = this.getAttackReachSqr(enemy);
            if (distToEnemySqr <= d0) {
                this.resetAttackCooldown();
                setAttacking(true);
                this.mob.doHurtTarget(enemy);
            }
        }
    }
}