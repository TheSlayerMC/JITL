package net.jitl.core.data.villager;

import net.jitl.core.init.JITL;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.VillagerTradesTagsProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.concurrent.CompletableFuture;

public class JVillagerTradeTagsProvider extends VillagerTradesTagsProvider {

    public static final TagKey<VillagerTrade> CRYPIAN = create("euca/crypian");

    public JVillagerTradeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        this.tag(CRYPIAN).add(JVillagerTrades.CRYPIAN);
    }

    private static TagKey<VillagerTrade> create(String name) {
        return TagKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(JITL.MOD_ID, name));
    }
}