package net.jitl.common.entity.jmerchent;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public class SentacoinsForItems implements SentacoinItemListing {
    private final ItemStack itemStack;
    private final int sentacoinAmount;

    public SentacoinsForItems(ItemStack pItem, int sentacoinAmount) {
        this.itemStack = pItem;
        this.sentacoinAmount = sentacoinAmount;
    }

    public SentacoinMerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
        return new SentacoinMerchantOffer(this.sentacoinAmount, this.itemStack);
    }
}