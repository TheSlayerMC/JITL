package net.jitl.common.entity.overworld;

import net.jitl.common.entity.base.AnimatableMonster;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

import java.util.EnumSet;

public class Floro extends AnimatableMonster implements RangedAttackMob {

    private static final EntityDataAccessor<Boolean> HIDDEN = SynchedEntityData.defineId(Floro.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_SHOOTING = SynchedEntityData.defineId(Floro.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_SHOWING = SynchedEntityData.defineId(Floro.class, EntityDataSerializers.BOOLEAN);

    private boolean isHiding = false;

    public Floro(EntityType<? extends Monster> type, Level world) {
        super(type, world);
    }

    @Override
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.floro.walk", true));
            return PlayState.CONTINUE;
        }

        if(isHidden()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.floro.hidden", true));
            return PlayState.CONTINUE;
        }

        if(isShooting()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.floro.shoot", true));
            return PlayState.CONTINUE;
        }

        if(isShowing()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.floro.showing", false));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.floro.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        setHidden(false);
        return super.hurt(pSource, pAmount);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HIDDEN, true);
        this.entityData.define(IS_SHOOTING, false);
        this.entityData.define(IS_SHOWING, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("hidden", isHidden());
        compound.putBoolean("shooting", isShooting());
        compound.putBoolean("showing", isShowing());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        if (compound.contains("hidden")) {
            setHidden(compound.getBoolean("hidden"));
        }

        if (compound.contains("shooting")) {
            setShooting(compound.getBoolean("shooting"));
        }
    }

    @Override
    public void onAddedToWorld() {
        if(isEffectiveAi() && isHidden()) {
            setHidden(true);
        }
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new FloroRevealingGoal());
        goalSelector.addGoal(2, new FloroHidingGoal());
        goalSelector.addGoal(3, new FloatGoal(this));//mutex 4
        goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Wolf.class, 6.0F, 1.0D, 1.2D));//mutex 1

        goalSelector.addGoal(5, new FloroAttackGoal(this, 1.0D, 20, 15.0F));

        goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));//mutex 1
        goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));//mutex 2
        goalSelector.addGoal(7, new RandomLookAroundGoal(this));//mutex 3

        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 5/*will target if rand.next(chance) == 0*/, true, false, null));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Bee.class, 5, true, false, null));
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 25)
                .add(Attributes.FOLLOW_RANGE, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.26).build();
    }

    @Override
    public void aiStep() {
        if (isEffectiveAi()) {
            if (isHidden()) {
                isHiding = false;
            }
            if (canMove()) {
                goalSelector.enableControlFlag(Goal.Flag.LOOK);
                goalSelector.enableControlFlag(Goal.Flag.JUMP);
            } else {
                goalSelector.disableControlFlag(Goal.Flag.LOOK);
                goalSelector.disableControlFlag(Goal.Flag.JUMP);
            }
        }
        super.aiStep();
    }

    @Override
    public void push(double x, double y, double z) {
        if(canMove()) {
            super.push(x, y, z);
        } else {
            super.push(0, 0, 0);
        }
    }

    @Override
    public void knockback(double strength, double ratioX, double ratioZ) {
        if (canMove()) {
            super.knockback(strength, ratioX, ratioZ);
        }
    }

    private boolean canMove() {
        return !isHidden() && !isHiding;
    }

    public boolean isHidden() {
        return getEntityData().get(HIDDEN);
    }

    public boolean isShooting() {
        return getEntityData().get(IS_SHOOTING);
    }

    public boolean isShowing() {
        return getEntityData().get(IS_SHOWING);
    }

    public void setHidden(boolean value) {
        getEntityData().set(HIDDEN, value);
    }

    public void setShooting(boolean value) {
        getEntityData().set(IS_SHOOTING, value);
    }

    public void setShowing(boolean value) {
        getEntityData().set(IS_SHOWING, value);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return sizeIn.height * 0.78F;
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {
        Snowball snowball = new Snowball(this.level, this);
        double d0 = pTarget.getEyeY() - (double)1.1F;
        double d1 = pTarget.getX() - this.getX();
        double d2 = d0 - snowball.getY();
        double d3 = pTarget.getZ() - this.getZ();
        double d4 = Math.sqrt(d1 * d1 + d3 * d3) * (double)0.2F;
        snowball.shoot(d1, d2 + d4, d3, 1.6F, 12.0F);
        this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(snowball);
    }

    private class FloroRevealingGoal extends Goal {

        public FloroRevealingGoal() {
            setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            return !canMove() && getTarget() != null;
        }

        @Override
        public void start() {
            if(isHidden()) {
                setShowing(true);
            } else {
                setHidden(false);
            }
        }

        @Override
        public boolean isInterruptable() {
            return false;
        }
    }

    private class FloroHidingGoal extends Goal {
        public FloroHidingGoal() {
            setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            return !isHidden() && getTarget() == null;
        }

        @Override
        public boolean canContinueToUse() {
            return !isHidden();
        }

        @Override
        public void start() {
            isHiding = true;
        }

        @Override
        public void tick() {
            if(!isHidden() && tickCount % (12 * 20) == 0) {
                setHidden(true);
            }
        }

        @Override
        public void stop() {
            isHiding = false;
        }
    }
}