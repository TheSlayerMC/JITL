package net.jitl.common.entity.jmerchant;

import net.jitl.core.init.internal.JContainers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class SentacoinMerchantMenu extends AbstractContainerMenu {

    public SentacoinMerchantMenu(int id, Inventory playerInv, FriendlyByteBuf extraData) {
        this(id);
    }

    public SentacoinMerchantMenu(int id) {
        super(JContainers.SENTACOIN_MERCHANT.get(), id);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
