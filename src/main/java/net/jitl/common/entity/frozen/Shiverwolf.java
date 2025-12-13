package net.jitl.common.entity.frozen;

import net.jitl.common.entity.base.JTamableEntity;
import net.jitl.common.entity.base.MobStats;
import net.jitl.common.entity.goal.ShiverwolfBegGoal;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.entity.animal.equine.Llama;
import net.minecraft.world.entity.animal.turtle.Turtle;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.manager.AnimatableManager;

import javax.annotation.Nullable;

public class Shiverwolf extends JTamableEntity {

    private static final EntityDataAccessor<Boolean> DATA_INTERESTED_ID = SynchedEntityData.defineId(Shiverwolf.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_COLLAR_COLOR = SynchedEntityData.defineId(Shiverwolf.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(Shiverwolf.class, EntityDataSerializers.INT);
    public static final TargetingConditions.Selector PREY_SELECTOR = (p_375833_, p_375834_) -> {
        EntityType<?> entitytype = p_375833_.getType();
        return entitytype == EntityType.SHEEP || entitytype == EntityType.RABBIT || entitytype == EntityType.FOX;
    };
    private float interestedAngle;
    private float interestedAngleO;
    private boolean isWet;
    private boolean isShaking;
    private float shakeAnim;
    private float shakeAnimO;
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    @Nullable
    private EntityReference<@NotNull LivingEntity> persistentAngerTarget;
    private static final DyeColor DEFAULT_COLLAR_COLOR = DyeColor.RED;

    public Shiverwolf(EntityType<? extends JTamableEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setTame(false, false);
        this.setPathfindingMalus(PathType.POWDER_SNOW, -1.0F);
        this.setPathfindingMalus(PathType.DANGER_POWDER_SNOW, -1.0F);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TamableAnimal.TamableAnimalPanicGoal(1.5, DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new Shiverwolf.WolfAvoidEntityGoal<>(this, Llama.class, 24.0F, 1.5, 1.5));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(9, new ShiverwolfBegGoal(this, 8.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, Animal.class, false, PREY_SELECTOR));
        this.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeleton.class, false));
        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.30000001192092896)
                .add(Attributes.MAX_HEALTH, MobStats.SHIVERWOLF_HEALTH)
                .add(Attributes.ATTACK_DAMAGE, MobStats.SHIVERWOLF_DAMAGE).build();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_INTERESTED_ID, false);
        builder.define(DATA_COLLAR_COLOR, DEFAULT_COLLAR_COLOR.getId());
        builder.define(DATA_REMAINING_ANGER_TIME, 0);
    }

    @Override
    protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState block) {
        this.playSound(SoundEvents.WOLF_STEP, 0.15F, 1.0F);
    }

    @Override
    public void addAdditionalSaveData(ValueOutput compound) {
        super.addAdditionalSaveData(compound);
        compound.putByte("CollarColor", (byte)this.getCollarColor().getId());
        this.addPersistentAngerSaveData(compound);
    }

    @Override
    public void readAdditionalSaveData(ValueInput compound) {
        super.readAdditionalSaveData(compound);
        this.setCollarColor(compound.read("CollarColor", DyeColor.LEGACY_ID_CODEC).orElse(DEFAULT_COLLAR_COLOR));
        this.readPersistentAngerSaveData(this.level(), compound);
    }

    public Identifier getTexture() {
        if (this.isTame()) {
            return JITL.rl("textures/entity/frozen/shiverwolf_tame.png");
        } else {
            return this.isAngry() ? JITL.rl("textures/entity/frozen/shiverwolf_angry.png") : JITL.rl("textures/entity/frozen/shiverwolf.png");
        }
    }

//    @Override
//    protected SoundEvent getAmbientSound() {
//        if (this.isAngry()) {
//            return SoundEvents.WOLF_GROWL;
//        } else if (this.random.nextInt(3) != 0) {
//            return SoundEvents.WOLF_AMBIENT;
//        } else {
//            return this.isTame() && this.getHealth() < 20.0F ? SoundEvents.WOLF_WHINE : SoundEvents.WOLF_PANT;
//        }
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
//        return this.canArmorAbsorb(damageSource) ? SoundEvents.WOLF_ARMOR_DAMAGE : SoundEvents.WOLF_HURT;
//    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide() && this.isWet && !this.isShaking && !this.isPathFinding() && this.onGround()) {
            this.isShaking = true;
            this.shakeAnim = 0.0F;
            this.shakeAnimO = 0.0F;
            this.level().broadcastEntityEvent(this, (byte)8);
        }

        if (!this.level().isClientSide()) {
            this.updatePersistentAnger((ServerLevel)this.level(), true);
        }

    }

    public void tick() {
        super.tick();
        if (this.isAlive()) {
            this.interestedAngleO = this.interestedAngle;
            if (this.isInterested()) {
                this.interestedAngle += (1.0F - this.interestedAngle) * 0.4F;
            } else {
                this.interestedAngle += (0.0F - this.interestedAngle) * 0.4F;
            }

            if (this.isInWaterOrRain()) {
                this.isWet = true;
                if (this.isShaking && !this.level().isClientSide()) {
                    this.level().broadcastEntityEvent(this, (byte)56);
                    this.cancelShake();
                }
            } else if ((this.isWet || this.isShaking) && this.isShaking) {
                if (this.shakeAnim == 0.0F) {
                    this.playSound(SoundEvents.WOLF_SHAKE, this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                    this.gameEvent(GameEvent.ENTITY_ACTION);
                }

                this.shakeAnimO = this.shakeAnim;
                this.shakeAnim += 0.05F;
                if (this.shakeAnimO >= 2.0F) {
                    this.isWet = false;
                    this.isShaking = false;
                    this.shakeAnimO = 0.0F;
                    this.shakeAnim = 0.0F;
                }

                if (this.shakeAnim > 0.4F) {
                    float f = (float)this.getY();
                    int i = (int)(Mth.sin((this.shakeAnim - 0.4F) * 3.1415927F) * 7.0F);
                    Vec3 vec3 = this.getDeltaMovement();

                    for(int j = 0; j < i; ++j) {
                        float f1 = (this.random.nextFloat() * 2.0F - 1.0F) * this.getBbWidth() * 0.5F;
                        float f2 = (this.random.nextFloat() * 2.0F - 1.0F) * this.getBbWidth() * 0.5F;
                        this.level().addParticle(ParticleTypes.SPLASH, this.getX() + (double)f1, f + 0.8F, this.getZ() + (double)f2, vec3.x, vec3.y, vec3.z);
                    }
                }
            }
        }
    }

    private void cancelShake() {
        this.isShaking = false;
        this.shakeAnim = 0.0F;
        this.shakeAnimO = 0.0F;
    }

    @Override
    public void die(@NotNull DamageSource cause) {
        this.isWet = false;
        this.isShaking = false;
        this.shakeAnimO = 0.0F;
        this.shakeAnim = 0.0F;
        super.die(cause);
    }

    public boolean isWet() {
        return this.isWet;
    }

    public float getWetShade(float partialTicks) {
        return Math.min(0.75F + Mth.lerp(partialTicks, this.shakeAnimO, this.shakeAnim) / 2.0F * 0.25F, 1.0F);
    }

    public float getBodyRollAngle(float partialTicks, float offset) {
        float f = (Mth.lerp(partialTicks, this.shakeAnimO, this.shakeAnim) + offset) / 1.8F;
        if (f < 0.0F) {
            f = 0.0F;
        } else if (f > 1.0F) {
            f = 1.0F;
        }

        return Mth.sin(f * 3.1415927F) * Mth.sin(f * 3.1415927F * 11.0F) * 0.15F * 3.1415927F;
    }

    public float getHeadRollAngle(float partialTicks) {
        return Mth.lerp(partialTicks, this.interestedAngleO, this.interestedAngle) * 0.15F * 3.1415927F;
    }

    public float getShakeAnim(float p_364626_) {
        return Mth.lerp(p_364626_, this.shakeAnimO, this.shakeAnim);
    }

    @Override
    public int getMaxHeadXRot() {
        return this.isInSittingPose() ? 20 : super.getMaxHeadXRot();
    }

    @Override
    public boolean canUseSlot(@NotNull EquipmentSlot slot) {
        return true;
    }

    @Override
    protected void actuallyHurt(@NotNull ServerLevel level, @NotNull DamageSource damageSource, float damageAmount) {
        if(!this.canArmorAbsorb(damageSource)) {
            super.actuallyHurt(level, damageSource, damageAmount);
        } else {
            ItemStack itemstack = this.getBodyArmorItem();
            int i = itemstack.getDamageValue();
            int j = itemstack.getMaxDamage();
            itemstack.hurtAndBreak(Mth.ceil(damageAmount), this, EquipmentSlot.BODY);
            if(Crackiness.WOLF_ARMOR.byDamage(i, j) != Crackiness.WOLF_ARMOR.byDamage(this.getBodyArmorItem())) {
                this.playSound(SoundEvents.WOLF_ARMOR_CRACK);
                Level var7 = this.level();
                if(var7 instanceof ServerLevel serverlevel)
                    serverlevel.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, Items.ARMADILLO_SCUTE.getDefaultInstance()), this.getX(), this.getY() + 1.0, this.getZ(), 20, 0.2, 0.1, 0.2, 0.1);

            }
        }
    }

    private boolean canArmorAbsorb(DamageSource damageSource) {
        return this.hasArmor() && !damageSource.is(DamageTypeTags.BYPASSES_WOLF_ARMOR);
    }
//
//    @Override
//    protected SoundEvent getDeathSound() {
//        return SoundEvents.WOLF_DEATH;
//    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) { }

    @Override
    public int setBaseHealth() {
        return MobStats.SHIVERWOLF_HEALTH;
    }

    @Override
    public int setTameHealth() {
        return MobStats.TAMED_SHIVERWOLF_HEALTH;
    }

    @Override
    public Item getTameItem() {
        return JItems.FROZEN_ICE_BALL.get();
    }

    @Override
    protected void hurtArmor(@NotNull DamageSource damageSource, float damageAmount) {
        this.doHurtEquipment(damageSource, damageAmount, EquipmentSlot.BODY);
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();
        if (this.isTame()) {
            if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                FoodProperties foodproperties = itemstack.get(DataComponents.FOOD);
                float f = foodproperties != null ? (float)foodproperties.nutrition() : 1.0F;
                this.heal(2.0F * f);
                this.usePlayerItem(player, hand, itemstack);
                this.gameEvent(GameEvent.EAT); // Neo: add EAT game event
                return InteractionResult.SUCCESS;
            } else {
                if (item instanceof DyeItem dyeitem && this.isOwnedBy(player)) {
                    DyeColor dyecolor = dyeitem.getDyeColor();
                    if (dyecolor != this.getCollarColor()) {
                        this.setCollarColor(dyecolor);
                        itemstack.consume(1, player);
                        return InteractionResult.SUCCESS;
                    }

                    return super.mobInteract(player, hand);
                }

                if (this.isEquippableInSlot(itemstack, EquipmentSlot.BODY) && !this.isWearingBodyArmor() && this.isOwnedBy(player) && !this.isBaby()) {
                    this.setBodyArmorItem(itemstack.copyWithCount(1));
                    itemstack.consume(1, player);
                    return InteractionResult.SUCCESS;
                } else if (itemstack.canPerformAction(net.neoforged.neoforge.common.ItemAbilities.SHEARS_REMOVE_ARMOR)
                        && this.isOwnedBy(player)
                        && this.isWearingBodyArmor()
                        && (!EnchantmentHelper.has(this.getBodyArmorItem(), EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE) || player.isCreative())) {
                    itemstack.shrink(1);
                    this.playSound(SoundEvents.ARMOR_UNEQUIP_WOLF);
                    ItemStack itemstack1 = this.getBodyArmorItem();
                    this.setBodyArmorItem(ItemStack.EMPTY);
                    if (this.level() instanceof ServerLevel serverlevel) {
                        this.spawnAtLocation(serverlevel, itemstack1);
                    }

                    return InteractionResult.SUCCESS;
                } else if (this.isInSittingPose()
                        && this.isWearingBodyArmor()
                        && this.isOwnedBy(player)
                        && this.getBodyArmorItem().isDamaged()
                        && this.getBodyArmorItem().isValidRepairItem(itemstack)) {
                    itemstack.shrink(1);
                    this.playSound(SoundEvents.WOLF_ARMOR_REPAIR);
                    ItemStack itemstack2 = this.getBodyArmorItem();
                    int i = (int) ((float) itemstack2.getMaxDamage() * 0.125F);
                    itemstack2.setDamageValue(Math.max(0, itemstack2.getDamageValue() - i));
                    return InteractionResult.SUCCESS;
                } else {
                    InteractionResult interactionresult = super.mobInteract(player, hand);
                    if (!interactionresult.consumesAction() && this.isOwnedBy(player)) {
                        this.setOrderedToSit(!this.isOrderedToSit());
                        this.jumping = false;
                        this.navigation.stop();
                        this.setTarget(null);
                        return InteractionResult.SUCCESS.withoutItem();
                    } else {
                        return interactionresult;
                    }
                }
            }
        } else if (!this.level().isClientSide() && itemstack.is(Items.BONE) && !this.isAngry()) {
            itemstack.consume(1, player);
            this.tryToTame(player);
            return InteractionResult.SUCCESS_SERVER;
        } else {
            return super.mobInteract(player, hand);
        }
    }

    @Override
    public void handleEntityEvent(byte id) {
        if(id == 8) {
            this.isShaking = true;
            this.shakeAnim = 0.0F;
            this.shakeAnimO = 0.0F;
        } else if (id == 56) {
            this.cancelShake();
        } else {
            super.handleEntityEvent(id);
        }
    }

    public float getTailAngle() {
        if (this.isAngry()) {
            return 1.5393804F;
        } else if (this.isTame()) {
            float f = this.getMaxHealth();
            float f1 = (f - this.getHealth()) / f;
            return (0.55F - f1 * 0.4F) * 3.1415927F;
        } else {
            return 0.62831855F;
        }
    }
    
    @Override
    public long getPersistentAngerEndTime() {
        return (Long)this.entityData.get(DATA_ANGER_END_TIME);
    }

    @Override
    public void setPersistentAngerEndTime(long p_455794_) {
        this.entityData.set(DATA_ANGER_END_TIME, p_455794_);
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setTimeToRemainAngry(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    @Override
    public @org.jetbrains.annotations.Nullable EntityReference<LivingEntity> getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public void setPersistentAngerTarget(@org.jetbrains.annotations.Nullable EntityReference<LivingEntity> target) {
        this.persistentAngerTarget = target;
    }

    public DyeColor getCollarColor() {
        return DyeColor.byId((Integer)this.entityData.get(DATA_COLLAR_COLOR));
    }

    public boolean hasArmor() {
        return this.getBodyArmorItem().is(Items.WOLF_ARMOR);
    }

    private void setCollarColor(DyeColor collarColor) {
        this.entityData.set(DATA_COLLAR_COLOR, collarColor.getId());
    }

    @Nullable
    @Override
    public Shiverwolf getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob otherParent) {
        Shiverwolf wolf = JEntities.SHIVERWOLF_TYPE.get().create(level, EntitySpawnReason.BREEDING);
        if (wolf != null && otherParent instanceof Shiverwolf wolf1) {
            if (this.isTame()) {
                wolf.setOwnerReference(this.getOwnerReference());
                wolf.setTame(true, true);
                if (this.random.nextBoolean()) {
                    wolf.setCollarColor(this.getCollarColor());
                } else {
                    wolf.setCollarColor(wolf1.getCollarColor());
                }
            }
        }
        return wolf;
    }

    public void setIsInterested(boolean isInterested) {
        this.entityData.set(DATA_INTERESTED_ID, isInterested);
    }

    @Override
    public boolean canMate(@NotNull Animal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        } else if (!this.isTame()) {
            return false;
        } else if (otherAnimal instanceof Shiverwolf wolf) {
            if (!wolf.isTame()) {
                return false;
            } else {
                return !wolf.isInSittingPose() && this.isInLove() && wolf.isInLove();
            }
        } else {
            return false;
        }
    }

    public boolean isInterested() {
        return this.entityData.get(DATA_INTERESTED_ID);
    }

    @Override
    public boolean wantsToAttack(@NotNull LivingEntity target, @NotNull LivingEntity owner) {
        if(!(target instanceof Creeper) && !(target instanceof Ghast) && !(target instanceof ArmorStand)) {
            if(!(target instanceof Shiverwolf wolf)) {
                if(target instanceof Player player) {
                    if(owner instanceof Player player1) {
                        if(!player1.canHarmPlayer(player)) {
                            return false;
                        }
                    }
                }

                if (target instanceof AbstractHorse abstracthorse) {
                    if (abstracthorse.isTamed()) {
                        return false;
                    }
                }

                if (target instanceof TamableAnimal tamableanimal) {
                    return !tamableanimal.isTame();
                }

                return true;
            } else {
                return !wolf.isTame() || wolf.getOwner() != owner;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canBeLeashed() {
        return !this.isAngry();
    }

    @Override
    public @NotNull Vec3 getLeashOffset() {
        return new Vec3(0.0, (double)(0.6F * this.getEyeHeight()), (double)(this.getBbWidth() * 0.4F));
    }

    class WolfAvoidEntityGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
        private final Shiverwolf wolf;

        public WolfAvoidEntityGoal(Shiverwolf wolf, Class<T> entityClassToAvoid, float maxDist, double walkSpeedModifier, double sprintSpeedModifier) {
            super(wolf, entityClassToAvoid, maxDist, walkSpeedModifier, sprintSpeedModifier);
            this.wolf = wolf;
        }

        public boolean canUse() {
            return super.canUse() && this.toAvoid instanceof Llama && !this.wolf.isTame() && this.avoidLlama((Llama) this.toAvoid);
        }

        private boolean avoidLlama(Llama llama) {
            return llama.getStrength() >= Shiverwolf.this.random.nextInt(5);
        }

        public void start() {
            Shiverwolf.this.setTarget(null);
            super.start();
        }

        public void tick() {
            Shiverwolf.this.setTarget(null);
            super.tick();
        }
    }
}
