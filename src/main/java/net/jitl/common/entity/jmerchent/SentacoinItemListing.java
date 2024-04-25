package net.jitl.common.entity.jmerchent;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

public interface SentacoinItemListing {

    @Nullable
    SentacoinMerchantOffer getOffer(Entity var1, RandomSource var2);
}
