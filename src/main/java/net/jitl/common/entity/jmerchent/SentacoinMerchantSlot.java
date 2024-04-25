package net.jitl.common.entity.jmerchent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class SentacoinMerchantSlot extends Slot {

    private final SentacoinMerchantContainer slots;
    private final Player player;
    private int removeCount;
    private final SentacoinMerchant merchant;

    public SentacoinMerchantSlot(Player pPlayer, SentacoinMerchant pMerchant, SentacoinMerchantContainer pSlots, int pSlot, int pXPosition, int pYPosition) {
        super(pSlots, pSlot, pXPosition, pYPosition);
        this.player = pPlayer;
        this.merchant = pMerchant;
        this.slots = pSlots;
    }

    public boolean mayPlace(ItemStack pStack) {
        return false;
    }

    public ItemStack remove(int pAmount) {
        if (this.hasItem()) {
            this.removeCount += Math.min(pAmount, this.getItem().getCount());
        }

        return super.remove(pAmount);
    }

    protected void onQuickCraft(ItemStack pStack, int pAmount) {
        this.removeCount += pAmount;
        this.checkTakeAchievements(pStack);
    }

    protected void checkTakeAchievements(ItemStack pStack) {
       // pStack.onCraftedBy(this.player.level(), this.player, this.removeCount);
        this.removeCount = 0;
    }

    public void onTake(Player pPlayer, ItemStack pStack) {
        this.checkTakeAchievements(pStack);
        SentacoinMerchantOffer merchantoffer = this.slots.getActiveOffer();
        if (merchantoffer != null) {
            if(merchantoffer.take(pPlayer)) {
                this.merchant.notifyTrade(merchantoffer);
            }
        }
    }
}