package net.jitl.common.entity.jmerchent;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public interface SentacoinMerchant {

    void setTradingPlayer(@Nullable Player var1);

    @Nullable
    Player getTradingPlayer();

    SentacoinMerchantOffers getOffers();

    void overrideOffers(SentacoinMerchantOffers var1);

    void notifyTrade(SentacoinMerchantOffer var1);

    void notifyTradeUpdated(ItemStack var1);

    SoundEvent getNotifyTradeSound();

    boolean isClientSide();
}