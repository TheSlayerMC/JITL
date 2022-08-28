package net.jitl.common.entity.base;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

public class CurrencyForItemsTrade implements VillagerTrades.ItemListing {

    private final Item item;
    private final Item currency;
    private final int cost;
    private final int count;
    private final int maxUses;
    private final int villagerXp;
    private final float priceMultiplier;

    public CurrencyForItemsTrade(ItemLike currency, int cost, ItemLike trade, int count, int maxUses, int villagerXp) {
        this.item = trade.asItem();
        this.currency = currency.asItem();
        this.cost = cost;
        this.count = count;
        this.maxUses = maxUses;
        this.villagerXp = villagerXp;
        this.priceMultiplier = 0.05F;
    }

    @Nullable
    @Override
    public MerchantOffer getOffer(Entity entity, RandomSource random) {
        ItemStack currencyStack = new ItemStack(this.currency, this.cost);
        ItemStack tradeStack = new ItemStack(this.item, this.count);
        return new MerchantOffer(currencyStack, tradeStack, this.maxUses, this.villagerXp, this.priceMultiplier);
    }
}