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

    public static Holder<TradeSet> bootstrap(BootstrapContext<TradeSet> context) {
        register(context, CRYPIAN, JVillagerTradeTagsProvider.CRYPIAN);

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
