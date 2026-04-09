package net.jitl.common.entity.base;

import com.geckolib.animatable.GeoEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.util.GeckoLibUtil;
import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.SharedConstants;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.npc.villager.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public abstract class JVillagerMob extends AbstractVillager implements GeoEntity {

    protected EnumKnowledge knowledge;
    protected float knowledgeAmount = 0.0F;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static final EntityDataAccessor<VillagerData> DATA_VILLAGER_DATA = SynchedEntityData.defineId(JVillagerMob .class, EntityDataSerializers.VILLAGER_DATA);
    private static final EntityDataAccessor<Boolean> DATA_VILLAGER_DATA_FINALIZED = SynchedEntityData.defineId(JVillagerMob.class, EntityDataSerializers.BOOLEAN);;

    protected JVillagerMob(EntityType<? extends AbstractVillager> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setVillagerData(this.getVillagerData().withType(pLevel.registryAccess().getOrThrow(VillagerType.PLAINS)).withProfession(getVillagerProfession()));
    }

    public abstract Holder<VillagerProfession> getVillagerProfession();

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(DATA_VILLAGER_DATA, createDefaultVillagerData());
        entityData.define(DATA_VILLAGER_DATA_FINALIZED, false);
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.store("VillagerData", VillagerData.CODEC, this.getVillagerData());
        output.putBoolean("VillagerDataFinalized", (Boolean)this.entityData.get(DATA_VILLAGER_DATA_FINALIZED));
        MerchantOffers offers = this.getOffers();
        if (!offers.isEmpty()) {
            output.store("Offers", MerchantOffers.CODEC, offers);
        }
    }

    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        Optional<VillagerData> villagerDataOptional = input.read("VillagerData", VillagerData.CODEC);
        if (input.getBooleanOr("VillagerDataFinalized", false) || villagerDataOptional.isPresent()) {
            this.entityData.set(DATA_VILLAGER_DATA_FINALIZED, true);
            VillagerData villagerData = villagerDataOptional.orElseGet(Villager::createDefaultVillagerData);
            this.entityData.set(DATA_VILLAGER_DATA, villagerData);
        }
        this.offers = (MerchantOffers)input.read("Offers", MerchantOffers.CODEC).orElse(null);
        this.readInventoryFromTag(input);
    }

    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
        if (!(Boolean)this.entityData.get(DATA_VILLAGER_DATA_FINALIZED)) {
            this.setVillagerData(this.getVillagerData().withType(level.registryAccess(), VillagerType.byBiome(level.getBiome(this.blockPosition()))));
            this.entityData.set(DATA_VILLAGER_DATA_FINALIZED, true);
        }

        this.offers = getOffers();

        return super.finalizeSpawn(level, difficulty, spawnReason, groupData);
    }

    public static VillagerData createDefaultVillagerData() {
        return new VillagerData(BuiltInRegistries.VILLAGER_TYPE.getOrThrow(VillagerType.PLAINS), BuiltInRegistries.VILLAGER_PROFESSION.getOrThrow(VillagerProfession.NONE), 1);
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (this.isAlive() && !this.isTrading()) {
                if (!this.level().isClientSide()) {
                    if (hand == InteractionHand.MAIN_HAND) {
                        player.awardStat(Stats.TALKED_TO_VILLAGER);
                    }
                    this.startTrading(player);
                }
                return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(player, hand);
        }
    }

    public void startTrading(Player player) {
        this.setTradingPlayer(player);
        this.openTradingScreen(player, this.getDisplayName(), 1);
    }

    public void setVillagerData(VillagerData data) {
        VillagerData currentData = this.getVillagerData();
        if (!currentData.profession().equals(data.profession())) {
            this.offers = null;
        }
        this.entityData.set(DATA_VILLAGER_DATA, data);
    }

    public VillagerData getVillagerData() {
        return this.entityData.get(DATA_VILLAGER_DATA);
    }

    protected void updateTrades(ServerLevel level) {
        VillagerData data = this.getVillagerData();
        VillagerProfession profession = (VillagerProfession)data.profession().value();
        ResourceKey<TradeSet> trades = profession.getTrades(data.level());
        if (trades != null) {
            this.addOffersFromTradeSet(level, this.getOffers(), trades);
            if (SharedConstants.DEBUG_UNLOCK_ALL_TRADES && data.level() < 5) {
                this.increaseMerchantCareer(level);
            }
        }

    }

    private void increaseMerchantCareer(ServerLevel level) {
        this.setVillagerData(this.getVillagerData().withLevel(this.getVillagerData().level() + 1));
        this.updateTrades(level);
    }

    protected <T> boolean applyImplicitComponent(DataComponentType<T> type, T value) {
        if (type == DataComponents.VILLAGER_VARIANT) {
            Holder<VillagerType> variant = (Holder)castComponentValue(DataComponents.VILLAGER_VARIANT, value);
            this.setVillagerData(this.getVillagerData().withType(variant));
            return true;
        } else {
            return super.applyImplicitComponent(type, value);
        }
    }

    public MerchantOffers getOffers() {
        Level var2 = this.level();
        if (var2 instanceof ServerLevel serverLevel) {
            if (this.offers == null) {
                this.offers = new MerchantOffers();
                this.updateTrades(serverLevel);
            }

            return this.offers;
        } else {
            throw new IllegalStateException("Cannot load Villager offers on the client");
        }
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, EntitySpawnReason type) {
        return !(level.getBiome(blockPosition()).is(Tags.Biomes.IS_MUSHROOM) || level.getBiome(blockPosition()).is(Biomes.DEEP_DARK));
    }

    protected abstract void controller(AnimatableManager.ControllerRegistrar controllers);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controller(controllers);
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
}
