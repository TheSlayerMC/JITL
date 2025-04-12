package net.jitl.common.entity.frozen;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.entity.base.JNeutralMonster;
import net.jitl.common.entity.base.MobStats;
import net.jitl.core.helper.MathHelper;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.manager.AnimatableManager;

public class FrozenTrollEntity extends JNeutralMonster {

    private static final EntityDataAccessor<Boolean> IS_ANGRY_ID = SynchedEntityData.defineId(FrozenTrollEntity.class, EntityDataSerializers.BOOLEAN);

    public FrozenTrollEntity(EntityType<? extends FrozenTrollEntity> entityType, Level world) {
        super(entityType, world);
        this.setCanPickUpLoot(true);
        setKnowledge(EnumKnowledge.FROZEN, 5F);
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.FROZEN_TROLL_HEALTH)
                .add(Attributes.ATTACK_DAMAGE, MobStats.FROZEN_TROLL_DAMAGE)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.FROZEN_TROLL_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.STANDARD_MOVEMENT_SPEED).build();
    }

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {

    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("angry", this.entityData.get(IS_ANGRY_ID));
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setAngry(compound.getBooleanOr("angry", false));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(IS_ANGRY_ID, false);
    }

    public boolean isAngry() {
        return this.entityData.get(IS_ANGRY_ID);
    }

    public void setAngry(boolean angry) {
        this.entityData.set(IS_ANGRY_ID, angry);
    }

    @Override
    protected void customServerAiStep(ServerLevel level) {
        boolean isPresent = getTarget() != null;
        this.entityData.set(IS_ANGRY_ID, isPresent);
        super.customServerAiStep(level);
    }

    public void playSound(SoundEvent soundEvent_) {
        this.playSound(soundEvent_, this.getSoundVolume(), this.getVoicePitch());
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity entityIn) {
        if (super.doHurtTarget(level, entityIn)) {
            if(entityIn instanceof LivingEntity) {
                entityIn.setDeltaMovement(entityIn.getDeltaMovement().add(-MathHelper.sin((float) (this.interpolation.yRot() * Math.PI / 180.0F)) * 2, 0.1D, MathHelper.cos((float) (this.interpolation.yRot() * Math.PI / 180.0F)) * 2));
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void playAmbientSound() {
        if (this.isAngry()) {
            this.playSound(getAngryAmbientSound(), this.getSoundVolume(), this.getVoicePitch());
        } else {
            this.playSound(getCuteAmbientSound(), this.getSoundVolume(), this.getVoicePitch() + 1.0F);
        }
    }

    protected SoundEvent getCuteAmbientSound() {
        return JSounds.FROZEN_TROLL_INTRIGUED.get();
    }

    protected SoundEvent getAngryAmbientSound() {
        return JSounds.FROZEN_TROLL_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(@NotNull DamageSource damageSourceIn) {
        return JSounds.FROZEN_TROLL_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return JSounds.FROZEN_TROLL_DEATH.get();
    }

    @Override
    protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
        this.playSound(SoundEvents.WOLF_STEP, 0.15F, 1.0F);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }
}
