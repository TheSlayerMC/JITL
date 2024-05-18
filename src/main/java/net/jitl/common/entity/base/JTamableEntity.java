package net.jitl.common.entity.base;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Objects;
import java.util.UUID;

public abstract class JTamableEntity extends TamableAnimal implements NeutralMob, GeoEntity {

    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(JTamableEntity.class, EntityDataSerializers.INT);
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    @Nullable
    private UUID persistentAngerTarget;

    protected EnumKnowledge knowledge;
    protected float knowledgeAmount = 0.0F;

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    protected JTamableEntity(EntityType<? extends JTamableEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setTame(false, false);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType type) {
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
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeleton.class, false));
        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(DATA_REMAINING_ANGER_TIME, 0);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.readPersistentAngerSaveData(this.level(), pCompound);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if(!this.level().isClientSide)
            this.updatePersistentAnger((ServerLevel)this.level(), true);
    }

    @Override
    public boolean hurt(@NotNull DamageSource pSource, float pAmount) {
        if (this.isInvulnerableTo(pSource)) {
            return false;
        } else {
            if(!this.level().isClientSide)
                this.setOrderedToSit(false);

            return super.hurt(pSource, pAmount);
        }
    }

    @Override
    public void die(@NotNull DamageSource cause) {
        super.die(cause);
        if(cause.getEntity() instanceof Player player && this.knowledge != null) {
            player.getData(JDataAttachments.PLAYER_STATS).addXP(this.knowledge, this.knowledgeAmount, player);
        }
    }


    @Override
    protected void applyTamingSideEffects() {
        if(this.isTame()) {
            Objects.requireNonNull(this.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(setTameHealth());
            this.setHealth(setTameHealth());
        } else {
            Objects.requireNonNull(this.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(setBaseHealth());
        }
    }

    protected abstract void controller(AnimatableManager.ControllerRegistrar controllers);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controller(controllers);
    }

    public abstract int setBaseHealth();
    public abstract int setTameHealth();

    @Override
    public @NotNull InteractionResult mobInteract(Player pPlayer, @NotNull InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (!this.level().isClientSide || this.isBaby() && this.isFood(itemstack)) {
            if (this.isTame()) {
                if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                    FoodProperties foodproperties = itemstack.getFoodProperties(this);
                    float f = foodproperties != null ? (float)foodproperties.nutrition() : 1.0F;
                    this.heal(2.0F * f);
                    itemstack.consume(1, pPlayer);
                    this.gameEvent(GameEvent.EAT);
                    return InteractionResult.sidedSuccess(this.level().isClientSide());
                } else {
                    InteractionResult interactionresult = super.mobInteract(pPlayer, pHand);
                    if (!interactionresult.consumesAction() && this.isOwnedBy(pPlayer)) {
                        this.setOrderedToSit(!this.isOrderedToSit());
                        this.jumping = false;
                        this.navigation.stop();
                        this.setTarget(null);
                        return InteractionResult.SUCCESS_NO_ITEM_USED;
                    } else {
                        return interactionresult;
                    }
                }
            } else if (itemstack.is(getTameItem()) && !this.isAngry()) {
                itemstack.consume(1, pPlayer);
                this.tryToTame(pPlayer);
                return InteractionResult.SUCCESS;
            } else {
                return super.mobInteract(pPlayer, pHand);
            }
        } else {
            boolean flag = this.isOwnedBy(pPlayer) || this.isTame() || itemstack.is(Items.BONE) && !this.isTame() && !this.isAngry();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        }
    }

    public abstract Item getTameItem();

    private void tryToTame(Player pPlayer) {
        if (this.random.nextInt(3) == 0 && !EventHooks.onAnimalTame(this, pPlayer)) {
            this.tame(pPlayer);
            this.navigation.stop();
            this.setTarget(null);
            this.setOrderedToSit(true);
            this.level().broadcastEntityEvent(this, (byte) 7);
        } else {
            this.level().broadcastEntityEvent(this, (byte) 6);
        }
    }

    @Override
    public boolean isFood(ItemStack s) {
        return s.is(getTameItem());
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 8;
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    @Override
    public void setRemainingPersistentAngerTime(int pTime) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, pTime);
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    @javax.annotation.Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public void setPersistentAngerTarget(@javax.annotation.Nullable UUID pTarget) {
        this.persistentAngerTarget = pTarget;
    }

    @Override
    public boolean wantsToAttack(@NotNull LivingEntity pTarget, @NotNull LivingEntity pOwner) {
        if (pTarget instanceof Creeper || pTarget instanceof Ghast || pTarget instanceof ArmorStand) {
            return false;
        } else if (pTarget instanceof JTamableEntity e) {
            return !e.isTame() || e.getOwner() != pOwner;
        } else {
            if (pTarget instanceof Player player && pOwner instanceof Player player1 && !player1.canHarmPlayer(player)) {
                return false;
            }
            return !(pTarget instanceof TamableAnimal tamableanimal) || !tamableanimal.isTame();
        }
    }

    @Override
    public boolean canBeLeashed(@NotNull Player pPlayer) {
        return !this.isAngry() && super.canBeLeashed(pPlayer);
    }

    @Override
    public @NotNull Vec3 getLeashOffset() {
        return new Vec3(0.0, 0.6F * this.getEyeHeight(), this.getBbWidth() * 0.4F);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel pLevel, @NotNull AgeableMob pOtherParent) {
        return null;
    }
}
