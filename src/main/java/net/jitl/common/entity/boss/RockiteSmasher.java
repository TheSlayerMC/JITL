package net.jitl.common.entity.boss;

import net.jitl.client.gui.BossBarRenderer;
import net.jitl.common.entity.IJourneyBoss;
import net.jitl.common.entity.base.IDontAttackWhenPeaceful;
import net.jitl.common.entity.base.JBossInfo;
import net.jitl.common.entity.goal.AttackWhenDifficultGoal;
import net.jitl.common.entity.goal.IdleHealGoal;
import net.jitl.core.init.JITL;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class RockiteSmasher extends Monster implements RangedAttackMob, IAnimatable, IJourneyBoss, IDontAttackWhenPeaceful {

    private final AnimationFactory factory = new AnimationFactory(this);
    private final ServerBossEvent BOSS_INFO = new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.NOTCHED_6);
    private final BossBarRenderer BOSS_BAR = new BossBarRenderer(this, JITL.rl("textures/gui/bossbars/rockite_smasher.png"));

    private static final EntityDataAccessor<Boolean> ATTACK = SynchedEntityData.defineId(RockiteSmasher.class, EntityDataSerializers.BOOLEAN);

    public RockiteSmasher(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new RockiteSmasher.SmashingGoal(this, 1.0D, false));
        this.goalSelector.addGoal(1, new IdleHealGoal(this, 1200));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new AttackWhenDifficultGoal(this, this));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, null));
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 100)
                .add(Attributes.FOLLOW_RANGE, 25)
                .add(Attributes.MOVEMENT_SPEED, 0.26).build();
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        JBossInfo.addInfo(player, BOSS_INFO, this);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        JBossInfo.removeInfo(player, BOSS_INFO, this);
    }

    @Override
    public BossBarRenderer getBossBar() {
        return BOSS_BAR;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(event.isMoving() && !isAttacking()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.rockite_smasher.walk", true));
            return PlayState.CONTINUE;
        }

        if(isAttacking()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.rockite_smasher.swing", true));
            return PlayState.CONTINUE;
        }

        if(!isAttacking())
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.rockite_smasher.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {

    }

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

    @Override
    public boolean wantsToAttack(LivingEntity target, LivingEntity living) {
        return level.getDifficulty() != Difficulty.PEACEFUL;
    }

    private class SmashingGoal extends MeleeAttackGoal {
        private final RockiteSmasher entity;

        private SmashingGoal(RockiteSmasher entity, double speed, boolean useLongMemory) {
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