package net.jitl.common.entity.base;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public abstract class JMonsterEntity extends Monster implements GeoEntity {

    protected EnumKnowledge knowledge;
    protected float knowledgeAmount = 0.0F;

    private static final EntityDataAccessor<Boolean> ATTACK = SynchedEntityData.defineId(JMonsterEntity.class, EntityDataSerializers.BOOLEAN);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    protected JMonsterEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected abstract void controller(AnimatableManager.ControllerRegistrar controllers);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controller(controllers);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, @NotNull EntitySpawnReason type) {
        return !(level.getBiome(blockPosition()).is(Tags.Biomes.IS_MUSHROOM) || level.getBiome(blockPosition()).is(Biomes.DEEP_DARK));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public void setKnowledge(EnumKnowledge knowledge, float amount) {
        this.knowledge = knowledge;
        this.knowledgeAmount = amount;
    }

    @Override
    public void die(@NotNull DamageSource cause) {
        super.die(cause);
        if(cause.getEntity() instanceof Player player && this.knowledge != null) {
            player.getData(JDataAttachments.PLAYER_STATS).addXP(this.knowledge, this.knowledgeAmount, player);
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(ATTACK, false);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput compound) {
        setAttacking(compound.getBooleanOr("attack", true));
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput compound) {
        compound.putBoolean("attack", isAttacking());
    }

    public boolean isAttacking() {
        return getEntityData().get(ATTACK);
    }

    public void setAttacking(boolean value) {
        getEntityData().set(ATTACK, value);
    }

    public class AnimatedAttackGoal extends MeleeAttackGoal {
        private final JMonsterEntity entity;

        public AnimatedAttackGoal(JMonsterEntity entity, double speed, boolean useLongMemory) {
            super(entity, speed, useLongMemory);
            this.entity = entity;
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
        protected void checkAndPerformAttack(LivingEntity enemy) {
            if (this.canPerformAttack(enemy)) {
                this.resetAttackCooldown();
                setAttacking(true);
                this.mob.swing(InteractionHand.MAIN_HAND);
                this.mob.doHurtTarget(getServerLevel(this.mob), enemy);
            }
        }
    }
}