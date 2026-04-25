package net.jitl.common.entity.base;

import com.geckolib.animatable.GeoEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.util.GeckoLibUtil;
import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.core.Holder;
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

    protected JVillagerMob(EntityType<? extends AbstractVillager> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setVillagerData(this.getVillagerData().withProfession(getVillagerProfession()));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(DATA_VILLAGER_DATA, new VillagerData(BuiltInRegistries.VILLAGER_TYPE.getOrThrow(VillagerType.PLAINS), getVillagerProfession(), 1));
    }

    public abstract Holder<VillagerProfession> getVillagerProfession();

    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {

        this.setVillagerData(this.getVillagerData().withProfession(getVillagerProfession()));
        VillagerData data = this.getVillagerData();
        VillagerProfession profession = data.profession().value();
        ResourceKey<TradeSet> trades = profession.getTrades(data.level());
        this.addOffersFromTradeSet(level.getLevel(), this.getOffers(), trades);
        

        return super.finalizeSpawn(level, difficulty, spawnReason, groupData);
    }

    @Override
    protected void updateTrades(ServerLevel level) {
        VillagerData data = this.getVillagerData();
        VillagerProfession profession = data.profession().value();
        ResourceKey<TradeSet> trades = profession.getTrades(data.level());
            this.addOffersFromTradeSet(level, this.getOffers(), trades);

    }

    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.store("VillagerData", VillagerData.CODEC, this.getVillagerData());
    }

    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        Optional<VillagerData> villagerDataOptional = input.read("VillagerData", VillagerData.CODEC);
        if(villagerDataOptional.isPresent()) {
            VillagerData villagerData = (VillagerData)villagerDataOptional.orElseGet(() -> this.getVillagerData().withType(level().registryAccess().getOrThrow(VillagerType.PLAINS)).withProfession(getVillagerProfession()));
            this.entityData.set(DATA_VILLAGER_DATA, villagerData);
        }
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (this.isAlive() && !this.isTrading()) {
                if (!this.level().isClientSide()) {
                    if (hand == InteractionHand.MAIN_HAND) {
                        player.awardStat(Stats.TALKED_TO_VILLAGER);
                    }
                    System.out.println(getOffers());
                    this.startTrading(player);
                }
                return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(player, hand);
        }
    }

    public void startTrading(Player player) {
        this.setTradingPlayer(player);
        VillagerData data = this.getVillagerData();
        VillagerProfession profession = data.profession().value();
        ResourceKey<TradeSet> trades = profession.getTrades(data.level());
        if(level() instanceof ServerLevel sLevel)
            this.addOffersFromTradeSet(sLevel, this.getOffers(), trades);
        this.openTradingScreen(player, this.getDisplayName(), 1);
    }

    public void setVillagerData(VillagerData data) {
        this.entityData.set(DATA_VILLAGER_DATA, data);
    }

    public VillagerData getVillagerData() {
        return this.entityData.get(DATA_VILLAGER_DATA);
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
