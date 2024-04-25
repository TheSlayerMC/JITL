package net.jitl.common.entity.overworld.npc;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.jitl.client.ChatUtils;
import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.capability.stats.PlayerStats;
import net.jitl.common.entity.base.MobStats;
import net.jitl.common.entity.jmerchent.*;
import net.jitl.common.world.menu.SentacoinMerchantMenu;
import net.jitl.core.data.JNetworkRegistry;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Objects;
import java.util.Set;

public class OverworldSentryStalker extends PathfinderMob implements GeoEntity, Npc, SentacoinMerchant {

    private static final EntityDataAccessor<Boolean> DATA_IS_ACTIVATED = SynchedEntityData.defineId(OverworldSentryStalker.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_HAS_KEY = SynchedEntityData.defineId(OverworldSentryStalker.class, EntityDataSerializers.BOOLEAN);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    protected SentacoinMerchantOffers offers;
    private Player tradingPlayer;

    private static final Int2ObjectMap<SentacoinItemListing[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(1, new SentacoinItemListing[]{
            new SentacoinsForItems(new ItemStack(JBlocks.ANCIENT_RUNIC_STONE_1.get()), 16),
            new SentacoinsForItems(new ItemStack(JBlocks.ANCIENT_RUNIC_STONE_2.get()), 32),
            new SentacoinsForItems(new ItemStack(JBlocks.ANCIENT_RUNIC_STONE_3.get()), 64),
            new SentacoinsForItems(new ItemStack(JBlocks.ANCIENT_RUNIC_STONE_0.get()), 128),
            new SentacoinsForItems(new ItemStack(JBlocks.BREAKABLE_SENTERIAN_BRICKS.get()), 256)
    }));

    public OverworldSentryStalker(EntityType<? extends OverworldSentryStalker> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(1, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.neutral_sentry_stalker.idle");
    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.neutral_sentry_stalker.walk");

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, state -> {
            if(state.isMoving()) {
                return state.setAndContinue(MOVING);
            } else {
                return state.setAndContinue(IDLE);
            }
        }));
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.NPC_HEALTH)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.STANDARD_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.STANDARD_MOVEMENT_SPEED).build();
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    public void tick() {
        super.tick();
        if(isActivated()) {
            if(!level().isClientSide) {
                if(hasKey()) {
                    this.level().addFreshEntity(new ItemEntity(level(), this.position().x + 0.5F, this.position().y + 1.4F, this.position().z + 0.5F, new ItemStack(JItems.SENTRY_KEY.get(), 1)));
                    this.playSound(JSounds.COIN_PICKUP.get(), 1.5F, 1.0F);
                    setHasKey(false);
                }
            }
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_IS_ACTIVATED, false);
        this.entityData.define(DATA_HAS_KEY, true);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("activated", this.entityData.get(DATA_IS_ACTIVATED));
        compound.putBoolean("hasKey", this.entityData.get(DATA_HAS_KEY));
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setActivated(compound.getBoolean("activated"));
        setHasKey(compound.getBoolean("hasKey"));
    }

    public void setActivated(boolean activated) {
        this.entityData.set(DATA_IS_ACTIVATED, activated);
    }

    public void setHasKey(boolean activated) {
        this.entityData.set(DATA_HAS_KEY, activated);
    }

    public boolean isActivated() {
        return this.entityData.get(DATA_IS_ACTIVATED);
    }

    public boolean hasKey() {
        return this.entityData.get(DATA_HAS_KEY);
    }

    @Override
    public boolean hurt(@NotNull DamageSource pSource, float pAmount) {
        return false;
    }

    @Override
    protected @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand hand) {
        PlayerStats stats = player.getData(JDataAttachments.PLAYER_STATS);
            if(stats.getLevel(EnumKnowledge.OVERWORLD) >= 75)
                setActivated(true);

            if(hasKey())
                ChatUtils.addDialogStyleChat(player, "jitl.sen.knowledge_1");

            if(!hasKey()) {
                ChatUtils.addDialogStyleChat(player, "jitl.sen.unlocked");
                if(!level().isClientSide && tradingPlayer == null)
                    trade((ServerPlayer)player);
            }
        return super.mobInteract(player, hand);
    }

    @Override
    public void setTradingPlayer(@Nullable Player var1) {
        this.tradingPlayer = var1;
    }

    @Nullable
    @Override
    public Player getTradingPlayer() {
        return this.tradingPlayer;
    }

    @Override
    public SentacoinMerchantOffers getOffers() {
        if (offers == null) {
            offers = new SentacoinMerchantOffers();
            provideTrades();
        }
        return offers;
    }

    protected void provideTrades() {
        SentacoinItemListing[] trades = Objects.requireNonNull(TRADES).get(1);
        if (trades != null) {
            SentacoinMerchantOffers merchantOffers = getOffers();
            addTrades(merchantOffers, trades);
        }
    }

    public void trade(ServerPlayer player) {
        if (!getOffers().isEmpty()) {
            if (!level().isClientSide()) {
                setTradingPlayer(player);
                player.openMenu(new SimpleMenuProvider((p_45298_, p_45299_, p_45300_) -> new SentacoinMerchantMenu(p_45298_, p_45299_, this), Objects.requireNonNull(getDisplayName())));
                SentacoinMerchantOffers merchantoffers = this.getOffers();
                if(!merchantoffers.isEmpty()) {
                    JNetworkRegistry.sendToPlayer(player, new ClientBoundSentacoinMerchantOffersPacket(merchantoffers));
                }
            }
        }
    }

    @Override
    public void overrideOffers(SentacoinMerchantOffers var1) { }

    @Override
    public void notifyTrade(SentacoinMerchantOffer var1) {

    }

    @Override
    public void notifyTradeUpdated(ItemStack var1) {

    }

    protected void addTrades(SentacoinMerchantOffers offers, SentacoinItemListing[] trades) {
        Set<Integer> set = Sets.newHashSet();
        for (int i = 0; i < trades.length; ++i) {
            set.add(i);
        }
        for (int int1 : set) {
            SentacoinItemListing villagerTrades = trades[int1];
            SentacoinMerchantOffer merchantoffer = villagerTrades.getOffer(this, random);
            if (merchantoffer != null) {
                offers.add(merchantoffer);
            }
        }
    }

    @Override
    public SoundEvent getNotifyTradeSound() {
        return null;
    }

    @Override
    public boolean isClientSide() {
        return false;
    }
}
