package net.jitl.common.entity.boss;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.dataticket.DataTicket;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.Objects;

public class BossCrystal extends Mob implements GeoEntity {
    
    private final NonNullList<ItemStack> storedItems = NonNullList.create();
    private ResourceKey<LootTable> loot_table;
    private static final EntityDataAccessor<String> TYPE = SynchedEntityData.defineId(BossCrystal.class, EntityDataSerializers.STRING);
    public static final DataTicket<String> TYPE_TICKET = DataTicket.create("crystal_type", String.class);

    public BossCrystal(EntityType<? extends BossCrystal> pEntityType, Level pLevel, Type t, ResourceKey<LootTable> loot) {
        this(pEntityType, pLevel);
        setType(t.getName());
        setLootTable(loot);
        this.setDeltaMovement(0, 0, 0);
        LootTable table = Objects.requireNonNull(level().getServer()).reloadableRegistries().getLootTable(loot_table);
        List<ItemStack> itemList = table.getRandomItems(new LootParams.Builder((ServerLevel)level()).withParameter(LootContextParams.THIS_ENTITY, this).withParameter(LootContextParams.ORIGIN, this.position()).create(LootContextParamSets.GIFT));
        storedItems.addAll(itemList);
    }

    public BossCrystal(EntityType<? extends BossCrystal> entityEntityType, Level level) {
        super(entityEntityType, level);
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 1)
                .add(Attributes.FOLLOW_RANGE, 0)
                .add(Attributes.MOVEMENT_SPEED, 0.0).build();
    }

    @Override
    public void aiStep() {
        for (int i = 0; i < 1; i++) {
            this.level().addParticle(ParticleTypes.SMOKE, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
        }
        super.aiStep();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(TYPE, "type");
    }

    public String getCrystalType() {
        return this.entityData.get(TYPE);
    }

    public void setType(String t) {
        this.entityData.set(TYPE, t);
    }

    public void setLootTable(ResourceKey<LootTable> table) {
        this.loot_table = table;
    }

    @Override
    protected void readAdditionalSaveData(ValueInput compound) {
        ContainerHelper.loadAllItems(compound, storedItems);
        setType(compound.getStringOr("type", "type"));
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput compound) {
        ContainerHelper.saveAllItems(compound, storedItems, true);
        compound.putString("type", getCrystalType());
    }

    @Override
    public boolean canBeCollidedWith(@Nullable Entity e) {
        return true;
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        if(!this.level().isClientSide) {

            for (ItemStack storedItem : storedItems) {
                ItemStack item = new ItemStack(storedItem.getItem());
                item.setCount(storedItem.getCount());
                ItemEntity entity = new ItemEntity(level(), this.getX(), this.getY(), this.getZ(), item);
                level().addFreshEntity(entity);
            }
            playSound(JSounds.CRYSTAL_PICKUP.get(), 1.0F, 1.0F);
            remove(RemovalReason.DISCARDED);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public boolean canCollideWith(Entity e) {
        return false;
    }

    @Override
    public boolean canBeLeashed() {
        return false;
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    public void push(Entity entity) { }

    @Override
    protected void doPush(Entity entityIn) { }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean hurtServer(ServerLevel p_376221_, DamageSource p_376460_, float p_376610_) {
        return false;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    public ResourceLocation getTexture() {
        return JITL.rl("textures/entity/crystal/" + getCrystalType() + ".png");
    }

    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.bosscrystal.idle");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("controller", 5, state -> state.setAndContinue(IDLE)));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public enum Type {
        COMMON("common"),
        NETHER("nether"),
        BOIL("boil"),
        EUCA("euca"),
        DEPTHS("depths"),
        CORBA("corba"),
        TERRANIA("terrania"),
        CLOUDIA("cloudia"),
        SENTERIAN("senterian"),
        FROZEN("frozen");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}