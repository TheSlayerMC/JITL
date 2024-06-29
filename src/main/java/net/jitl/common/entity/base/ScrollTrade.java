package net.jitl.common.entity.base;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.capability.player.LoreScroll;
import net.jitl.common.scroll.ScrollEntry;
import net.jitl.core.init.internal.JDataComponents;
import net.jitl.core.init.internal.JItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

public class ScrollTrade implements VillagerTrades.ItemListing {

    public final ScrollEntry entry;
    public final EnumKnowledge knowledge;
    public final int levels;
    public final ItemLike trade;
    public final int cost;

    public ScrollTrade(ItemLike trade, int cost, ScrollEntry entry, EnumKnowledge knowledge, int level) {
        this.entry = entry;
        this.knowledge = knowledge;
        this.levels = level;
        this.trade = trade;
        this.cost = cost;
    }

    @Nullable
    @Override
    public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
        ItemStack scroll = new ItemStack(JItems.LORE_SCROLL.asItem());
        scroll.set(JDataComponents.SCROLL, new LoreScroll(this.entry.getId(), this.knowledge.getName(), this.levels, false));
        return new MerchantOffer(new ItemCost(trade.asItem(), cost), scroll, 12, 5, 0F);
    }
}
