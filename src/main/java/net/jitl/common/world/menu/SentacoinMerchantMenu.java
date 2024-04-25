package net.jitl.common.world.menu;

import net.jitl.common.entity.jmerchent.*;
import net.jitl.core.init.internal.JContainers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class SentacoinMerchantMenu extends AbstractContainerMenu {

    private final SentacoinMerchant trader;
    private final SentacoinMerchantContainer tradeContainer;

    public SentacoinMerchantMenu(int pContainerId, Inventory pPlayerInventory, FriendlyByteBuf extraData) {
        this(pContainerId, pPlayerInventory, new ClientSideSentacoinMerchant(pPlayerInventory.player));
    }

    public SentacoinMerchantMenu(int pContainerId, Inventory pPlayerInventory, SentacoinMerchant pTrader) {
        super(JContainers.SENTACOIN_MERCHANT.get(), pContainerId);
        this.trader = pTrader;
        this.tradeContainer = new SentacoinMerchantContainer(pTrader);
        this.addSlot(new SentacoinMerchantSlot(pPlayerInventory.player, pTrader, this.tradeContainer, 0, 220, 37));

        int k;
        for(k = 0; k < 3; ++k) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(pPlayerInventory, j + k * 9 + 9, 108 + j * 18, 84 + k * 18));
            }
        }

        for(k = 0; k < 9; ++k) {
            this.addSlot(new Slot(pPlayerInventory, k, 108 + k * 18, 142));
        }

    }

    public void slotsChanged(Container pInventory) {
        this.tradeContainer.updateSellItem();
        super.slotsChanged(pInventory);
    }

    public void setSelectionHint(int pCurrentRecipeIndex) {
        this.tradeContainer.setSelectionHint(pCurrentRecipeIndex);
    }

    public boolean stillValid(Player pPlayer) {
        return this.trader.getTradingPlayer() == pPlayer;
    }

    public boolean canTakeItemForPickAll(ItemStack pStack, Slot pSlot) {
        return false;
    }

    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(pIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (pIndex == 0) {
                if (!this.moveItemStackTo(itemstack1, 1, 7, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
                this.playTradeSound();
            } else if (!this.moveItemStackTo(itemstack1, 1, 7, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(pPlayer, itemstack1);
        }

        return itemstack;
    }

    private void playTradeSound() {
        if (!this.trader.isClientSide()) {
            Entity entity = (Entity)this.trader;
            entity.level().playLocalSound(entity.getX(), entity.getY(), entity.getZ(), this.trader.getNotifyTradeSound(), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
        }

    }

    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        this.trader.setTradingPlayer((Player)null);
        if (!this.trader.isClientSide()) {
            if (!pPlayer.isAlive() || pPlayer instanceof ServerPlayer && ((ServerPlayer)pPlayer).hasDisconnected()) {
                ItemStack itemstack = this.tradeContainer.removeItemNoUpdate(0);
                if (!itemstack.isEmpty()) {
                    pPlayer.drop(itemstack, false);
                }

                itemstack = this.tradeContainer.removeItemNoUpdate(1);
                if (!itemstack.isEmpty()) {
                    pPlayer.drop(itemstack, false);
                }
            }
        }

    }

    public void tryMoveItems(int pSelectedMerchantRecipe) {
        if (pSelectedMerchantRecipe >= 0 && this.getOffers().size() > pSelectedMerchantRecipe) {
            ItemStack itemstack = this.tradeContainer.getItem(0);
            if (!itemstack.isEmpty()) {
                if (!this.moveItemStackTo(itemstack, 1, 37, true)) {
                    return;
                }

            }
        }
    }

    private void moveFromInventoryToPaymentSlot(int pPaymentSlotIndex, ItemStack pPaymentSlot) {
        if (!pPaymentSlot.isEmpty()) {
            for(int i = 3; i < 39; ++i) {
                ItemStack itemstack = ((Slot)this.slots.get(i)).getItem();
                if (!itemstack.isEmpty() && ItemStack.isSameItemSameTags(pPaymentSlot, itemstack)) {
                    ItemStack itemstack1 = this.tradeContainer.getItem(pPaymentSlotIndex);
                    int j = itemstack1.isEmpty() ? 0 : itemstack1.getCount();
                    int k = Math.min(pPaymentSlot.getMaxStackSize() - j, itemstack.getCount());
                    ItemStack itemstack2 = itemstack.copy();
                    int l = j + k;
                    itemstack.shrink(k);
                    itemstack2.setCount(l);
                    if (l >= pPaymentSlot.getMaxStackSize()) {
                        break;
                    }
                }
            }
        }

    }

    public void setOffers(SentacoinMerchantOffers pOffers) {
        this.trader.overrideOffers(pOffers);
    }

    public SentacoinMerchantOffers getOffers() {
        return this.trader.getOffers();
    }
}
