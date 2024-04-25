package net.jitl.common.entity.jmerchent;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class SentacoinMerchantOffers extends ArrayList<SentacoinMerchantOffer> {

    public SentacoinMerchantOffers() {

    }

    private SentacoinMerchantOffers(int i) {
        super(i);
    }

    public SentacoinMerchantOffers(CompoundTag pCompoundTag) {
        ListTag listtag = pCompoundTag.getList("Recipes", 10);

        for (int i = 0; i < listtag.size(); ++i) {
            this.add(new SentacoinMerchantOffer(listtag.getCompound(i)));
        }

    }

    @Nullable
    public SentacoinMerchantOffer getRecipeFor(int pIndex) {
        if (pIndex > 0 && pIndex < this.size()) {
            SentacoinMerchantOffer merchantoffer1 = (SentacoinMerchantOffer) this.get(pIndex);
            return merchantoffer1.satisfiedBy() ? merchantoffer1 : null;
        } else {
            for (int i = 0; i < this.size(); ++i) {
                SentacoinMerchantOffer merchantoffer = (SentacoinMerchantOffer) this.get(i);
                if (merchantoffer.satisfiedBy()) {
                    return merchantoffer;
                }
            }

            return null;
        }
    }

    public void writeToStream(FriendlyByteBuf pBuffer) {
        pBuffer.writeCollection(this, (c, merchent) -> {
            c.writeInt(merchent.getSentacoinCost());
            c.writeItem(merchent.getResult());
        });
    }

    public static SentacoinMerchantOffers createFromStream(FriendlyByteBuf pBuffer) {
        return pBuffer.readCollection(SentacoinMerchantOffers::new, (merchent) -> {
            int coins = merchent.readInt();
            ItemStack result = merchent.readItem();
            return new SentacoinMerchantOffer(coins, result);
        });
    }

    public CompoundTag createTag() {
        CompoundTag compoundtag = new CompoundTag();
        ListTag listtag = new ListTag();

        for (int i = 0; i < this.size(); ++i) {
            SentacoinMerchantOffer merchantoffer = this.get(i);
            listtag.add(merchantoffer.createTag());
        }

        compoundtag.put("Recipes", listtag);
        return compoundtag;
    }

    public SentacoinMerchantOffers copy() {
        SentacoinMerchantOffers SentacoinMerchantOffers = new SentacoinMerchantOffers(this.size());

        for(SentacoinMerchantOffer merchantoffer : this) {
            SentacoinMerchantOffers.add(merchantoffer.copy());
        }

        return SentacoinMerchantOffers;
    }
}