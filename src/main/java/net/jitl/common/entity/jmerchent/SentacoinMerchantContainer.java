package net.jitl.common.entity.jmerchent;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.Iterator;

public class SentacoinMerchantContainer implements Container {

    private final SentacoinMerchant merchant;
    private final NonNullList<ItemStack> itemStacks;
    @Nullable
    private SentacoinMerchantOffer activeOffer;
    private int selectionHint;

    public SentacoinMerchantContainer(SentacoinMerchant pMerchant) {
        this.itemStacks = NonNullList.withSize(1, ItemStack.EMPTY);
        this.merchant = pMerchant;
    }

    public int getContainerSize() {
        return this.itemStacks.size();
    }

    public boolean isEmpty() {
        Iterator var1 = this.itemStacks.iterator();

        ItemStack itemstack;
        do {
            if (!var1.hasNext()) {
                return true;
            }

            itemstack = (ItemStack)var1.next();
        } while(itemstack.isEmpty());

        return false;
    }

    public ItemStack getItem(int pIndex) {
        return this.itemStacks.get(pIndex);
    }

    public ItemStack removeItem(int pIndex, int pCount) {
        ItemStack itemstack = this.itemStacks.get(pIndex);
        if (pIndex == 2 && !itemstack.isEmpty()) {
            return ContainerHelper.removeItem(this.itemStacks, pIndex, itemstack.getCount());
        } else {
            ItemStack itemstack1 = ContainerHelper.removeItem(this.itemStacks, pIndex, pCount);
            if (!itemstack1.isEmpty() && this.isPaymentSlot(pIndex)) {
                this.updateSellItem();
            }

            return itemstack1;
        }
    }

    private boolean isPaymentSlot(int pSlot) {
        return false;
    }

    public ItemStack removeItemNoUpdate(int pIndex) {
        return ContainerHelper.takeItem(this.itemStacks, pIndex);
    }

    public void setItem(int pIndex, ItemStack pStack) {
        this.itemStacks.set(pIndex, pStack);
        if(!pStack.isEmpty() && pStack.getCount() > this.getMaxStackSize()) {
            pStack.setCount(this.getMaxStackSize());
        }

        if(this.isPaymentSlot(pIndex)) {
            this.updateSellItem();
        }
    }

    public boolean stillValid(Player pPlayer) {
        return this.merchant.getTradingPlayer() == pPlayer;
    }

    public void setChanged() {
        this.updateSellItem();
    }

    public void updateSellItem() {
        this.activeOffer = null;
            SentacoinMerchantOffers merchantoffers = this.merchant.getOffers();
            if (!merchantoffers.isEmpty()) {
                SentacoinMerchantOffer merchantoffer = merchantoffers.getRecipeFor(this.selectionHint);
                if (merchantoffer == null) {
                    this.activeOffer = merchantoffer;
                    merchantoffer = merchantoffers.getRecipeFor(this.selectionHint);
                }

                if (merchantoffer != null) {
                    this.activeOffer = merchantoffer;
                    this.setItem(0, merchantoffer.assemble());
                }
            }
            this.merchant.notifyTradeUpdated(this.getItem(0));
    }

    @Nullable
    public SentacoinMerchantOffer getActiveOffer() {
        return this.activeOffer;
    }

    public void setSelectionHint(int pCurrentRecipeIndex) {
        this.selectionHint = pCurrentRecipeIndex;
        this.updateSellItem();
    }

    public void clearContent() {
        this.itemStacks.clear();
    }

}