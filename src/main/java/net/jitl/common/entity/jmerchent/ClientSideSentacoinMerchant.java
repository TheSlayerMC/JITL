package net.jitl.common.entity.jmerchent;

import net.jitl.core.init.internal.JSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class ClientSideSentacoinMerchant implements SentacoinMerchant {

    private final Player source;
    private SentacoinMerchantOffers offers = new SentacoinMerchantOffers();

    public ClientSideSentacoinMerchant(Player pSource) {
        this.source = pSource;
    }

    public Player getTradingPlayer() {
        return this.source;
    }

    public void setTradingPlayer(@Nullable Player pPlayer) {
    }

    public SentacoinMerchantOffers getOffers() {
        return this.offers;
    }

    @Override
    public void overrideOffers(SentacoinMerchantOffers var1) {
        this.offers = var1;
    }

    public void notifyTrade(SentacoinMerchantOffer pOffer) { }

    public void notifyTradeUpdated(ItemStack pStack) { }

    public boolean isClientSide() {
        return this.source.level().isClientSide;
    }

    public SoundEvent getNotifyTradeSound() {
        return JSounds.COIN_PICKUP.get();
    }
}
