package net.jitl.common.entity.overworld;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.entity.base.JMonsterEntity;
import net.jitl.common.entity.base.MobStats;
import net.jitl.common.entity.goal.BoomSwellGoal;
import net.jitl.core.config.JCommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

import javax.annotation.Nullable;
import java.util.Collection;

public class BoomBoom extends JMonsterEntity {

    private static final EntityDataAccessor<Integer> DATA_SWELL_DIR = SynchedEntityData.defineId(BoomBoom.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_IS_POWERED = SynchedEntityData.defineId(BoomBoom.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_IS_IGNITED = SynchedEntityData.defineId(BoomBoom.class, EntityDataSerializers.BOOLEAN);
    private int oldSwell;
    private int swell;
    private int maxSwell = 30;
    private int explosionRadius = 4;

    public BoomBoom(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setKnowledge(EnumKnowledge.OVERWORLD, 5F);
    }

    public static boolean checkSpawn(EntityType<BoomBoom> entity, ServerLevelAccessor level, EntitySpawnReason spawnType, BlockPos pos, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL && checkMobSpawnRules(entity, level, spawnType, pos, random) && level.canSeeSky(pos)
                && JCommonConfig.ENABLE_BOOM_SPAWN.get();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new BoomSwellGoal(this));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Ocelot.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Cat.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.BOOM_HEALTH)
                .add(Attributes.ATTACK_DAMAGE, MobStats.BIG_HONGO_DAMAGE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.BOOM_MOVEMENT_SPEED).build();
    }

    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.boomboom.walk");
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.boomboom.idle");

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("controller", 5, state -> {
            if(state.isMoving()) {
                return state.setAndContinue(MOVING);
            } else {
                return state.setAndContinue(IDLE);
            }
        }));
    }

    @Override
    public int getMaxFallDistance() {
        return this.getTarget() == null ? 3 : 3 + (int)(this.getHealth() - 1.0F);
    }

    @Override
    public boolean causeFallDamage(double pFallDistance, float pMultiplier, DamageSource pSource) {
        boolean flag = super.causeFallDamage(pFallDistance, pMultiplier, pSource);
        this.swell += (int)(pFallDistance * 1.5F);
        if(this.swell > this.maxSwell - 5)
            this.swell = this.maxSwell - 5;
        return flag;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(DATA_SWELL_DIR, -1);
        pBuilder.define(DATA_IS_POWERED, false);
        pBuilder.define(DATA_IS_IGNITED, false);
    }

    @Override
    public void addAdditionalSaveData(ValueOutput pCompound) {
        super.addAdditionalSaveData(pCompound);
        if(this.entityData.get(DATA_IS_POWERED))
            pCompound.putBoolean("powered", true);
        pCompound.putShort("Fuse", (short)this.maxSwell);
        pCompound.putByte("ExplosionRadius", (byte)this.explosionRadius);
        pCompound.putBoolean("ignited", this.isIgnited());
    }

    @Override
    public void readAdditionalSaveData(ValueInput compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(DATA_IS_POWERED, compound.getBooleanOr("powered", false));
        this.maxSwell = compound.getShortOr("Fuse", (short)30);
        this.explosionRadius = compound.getByteOr("ExplosionRadius", (byte)3);
        if(compound.getBooleanOr("ignited", false))
            this.ignite();
    }

    @Override
    public void tick() {
        if(this.isAlive()) {
            this.oldSwell = this.swell;
            if(this.isIgnited())
                this.setSwellDir(1);

            int i = this.getSwellDir();
            if(i > 0 && this.swell == 0) {
                this.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);
                this.gameEvent(GameEvent.PRIME_FUSE);
            }
            this.swell += i;
            if(this.swell < 0)
                this.swell = 0;

            if(this.swell >= this.maxSwell) {
                this.swell = this.maxSwell;
                this.explodeBoom();
            }
        }

        super.tick();
    }

    @Override
    public void setTarget(@Nullable LivingEntity pTarget) {
        if(!(pTarget instanceof Goat)) {
            super.setTarget(pTarget);
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.CREEPER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.CREEPER_DEATH;
    }

    public boolean isPowered() {
        return this.entityData.get(DATA_IS_POWERED);
    }

    public float getSwelling(float pPartialTicks) {
        return Mth.lerp(pPartialTicks, (float)this.oldSwell, (float)this.swell) / (float)(this.maxSwell - 2);
    }

    public int getSwellDir() {
        return this.entityData.get(DATA_SWELL_DIR);
    }

    public void setSwellDir(int pState) {
        this.entityData.set(DATA_SWELL_DIR, pState);
    }

    @Override
    public void thunderHit(ServerLevel pLevel, LightningBolt pLightning) {
        super.thunderHit(pLevel, pLightning);
        this.entityData.set(DATA_IS_POWERED, true);
    }

    @Override
    protected @NotNull InteractionResult mobInteract(Player pPlayer, @NotNull InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.is(Items.FLINT_AND_STEEL)) {
            this.level().playSound(pPlayer, this.getX(), this.getY(), this.getZ(), SoundEvents.FLINTANDSTEEL_USE, this.getSoundSource(), 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
            if (!this.level().isClientSide) {
                this.ignite();
                if (!itemstack.isDamageableItem()) {
                    itemstack.shrink(1);
                } else {
                    itemstack.hurtAndBreak(1, pPlayer, getSlotForHand(pHand));
                }
            }
            return InteractionResult.SUCCESS_SERVER;
        } else {
            return super.mobInteract(pPlayer, pHand);
        }
    }

    private void explodeBoom() {
        if (!this.level().isClientSide) {
            float f = this.isPowered() ? 2.0F : 1.0F;
            this.dead = true;
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * f, Level.ExplosionInteraction.MOB);
            this.discard();
            this.spawnLingeringCloud();
        }
    }

    private void spawnLingeringCloud() {
        Collection<MobEffectInstance> collection = this.getActiveEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
            areaeffectcloud.setRadius(2.5F);
            areaeffectcloud.setRadiusOnUse(-0.5F);
            areaeffectcloud.setWaitTime(10);
            areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
            areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());
            for(MobEffectInstance mobeffectinstance : collection)
                areaeffectcloud.addEffect(new MobEffectInstance(mobeffectinstance));
            this.level().addFreshEntity(areaeffectcloud);
        }

    }

    public boolean isIgnited() {
        return this.entityData.get(DATA_IS_IGNITED);
    }

    public void ignite() {
        this.entityData.set(DATA_IS_IGNITED, true);
    }
}