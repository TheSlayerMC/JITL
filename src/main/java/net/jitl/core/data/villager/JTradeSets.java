package net.jitl.core.data.villager;

import net.jitl.core.init.JITL;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.VillagerTradeTags;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;

import java.util.Optional;

public class JTradeSets {

    public static final ResourceKey<TradeSet> CRYPIAN = resourceKey("euca/crypian");
    public static final ResourceKey<TradeSet> BOIL_TRADER = resourceKey("boil/boil_trader");
    public static final ResourceKey<TradeSet> CONVICT = resourceKey("boil/convict");
    public static final ResourceKey<TradeSet> STARLIGHT_BLACKSMITH = resourceKey("cloudia/starlight_blacksmith");
    public static final ResourceKey<TradeSet> STARLIGHT_VILLAGER = resourceKey("cloudia/starlight_villager");
    public static final ResourceKey<TradeSet> GREEN_TORDO = resourceKey("corba/green_tordo");
    public static final ResourceKey<TradeSet> HOODED = resourceKey("corba/hooded");
    public static final ResourceKey<TradeSet> RED_TORDO = resourceKey("corba/red_tordo");
    public static final ResourceKey<TradeSet> OVERGROWN_MERCHANT = resourceKey("corba/overgrown_merchant");
    public static final ResourceKey<TradeSet> AURON = resourceKey("depths/auron");
    public static final ResourceKey<TradeSet> STARING = resourceKey("depths/staring_guardian");
    public static final ResourceKey<TradeSet> ALLOY_MENDER = resourceKey("euca/alloy_mender");
    public static final ResourceKey<TradeSet> TERRANIAN_ENCHANTER = resourceKey("terrania/terranian_enchanter");
    public static final ResourceKey<TradeSet> TERRANIAN_TRADER = resourceKey("terrania/terranian_trader");
    public static final ResourceKey<TradeSet> ROCKITE_GOLEM = resourceKey("overworld/rockite");
    public static final ResourceKey<TradeSet> ESKIMO = resourceKey("frozen/eskimo");
    public static final ResourceKey<TradeSet> GUNSMITH = resourceKey("overworld/gunsmith");
    public static final ResourceKey<TradeSet> BLACKSMITH = resourceKey("overworld/blacksmithr");
    public static final ResourceKey<TradeSet> MAGE = resourceKey("overworld/mage");
    
    public static Holder<TradeSet> bootstrap(BootstrapContext<TradeSet> context) {
        register(context, CRYPIAN, JVillagerTradeTagsProvider.CRYPIAN);
        register(context, BOIL_TRADER, JVillagerTradeTagsProvider.BOIL_TRADER);
        register(context, CONVICT, JVillagerTradeTagsProvider.CONVICT);
        register(context, STARLIGHT_BLACKSMITH, JVillagerTradeTagsProvider.STARLIGHT_BLACKSMITH);
        register(context, STARLIGHT_VILLAGER, JVillagerTradeTagsProvider.STARLIGHT_VILLAGER);
        register(context, GREEN_TORDO, JVillagerTradeTagsProvider.GREEN_TORDO);
        register(context, HOODED, JVillagerTradeTagsProvider.HOODED);
        register(context, RED_TORDO, JVillagerTradeTagsProvider.RED_TORDO);
        register(context, OVERGROWN_MERCHANT, JVillagerTradeTagsProvider.OVERGROWN_MERCHANT);
        register(context, AURON, JVillagerTradeTagsProvider.AURON);
        register(context, STARING, JVillagerTradeTagsProvider.STARING);
        register(context, ALLOY_MENDER, JVillagerTradeTagsProvider.ALLOY_MENDER);
        register(context, TERRANIAN_ENCHANTER, JVillagerTradeTagsProvider.TERRANIAN_ENCHANTER);
        register(context, TERRANIAN_TRADER, JVillagerTradeTagsProvider.TERRANIAN_TRADER);
        register(context, ROCKITE_GOLEM, JVillagerTradeTagsProvider.ROCKITE_GOLEM);
        register(context, ESKIMO, JVillagerTradeTagsProvider.ESKIMO);
        register(context, GUNSMITH, JVillagerTradeTagsProvider.GUNSMITH);
        register(context, BLACKSMITH, JVillagerTradeTagsProvider.BLACKSMITH);
        register(context, MAGE, JVillagerTradeTagsProvider.MAGE);
        return register(context, resourceKey("weaponsmith/level_5"), VillagerTradeTags.WEAPONSMITH_LEVEL_5);
    }

    public static ResourceKey<TradeSet> resourceKey(String path) {
        return ResourceKey.create(Registries.TRADE_SET, Identifier.fromNamespaceAndPath(JITL.MOD_ID, path));
    }

    public static Holder.Reference<TradeSet> register(BootstrapContext<TradeSet> context, ResourceKey<TradeSet> resourceKey, TagKey<VillagerTrade> tradeTag) {
        return register(context, resourceKey, tradeTag, ConstantValue.exactly(2.0F));
    }

    public static Holder.Reference<TradeSet> register(
            BootstrapContext<TradeSet> context, ResourceKey<TradeSet> resourceKey, TagKey<VillagerTrade> tradeTag, NumberProvider numberProvider
    ) {
        return context.register(
                resourceKey,
                new TradeSet(
                        context.lookup(Registries.VILLAGER_TRADE).getOrThrow(tradeTag),
                        numberProvider,
                        false,
                        Optional.of(resourceKey.identifier().withPrefix("trade_set/"))
                )
        );
    }
}
