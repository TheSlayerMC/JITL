package net.jitl.core.helper.internal;

import net.jitl.core.helper.JContainers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.IContainerFactory;

public class EmptyContainer extends AbstractContainerMenu implements IContainerFactory<EmptyContainer> {

    public EmptyContainer() {
        super(JContainers.EMPTY_CONTAINER.get(), 200);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return false;
    }

    public static EmptyContainer createContainerClientSide(int windowID, Inventory playerInventory, FriendlyByteBuf extraData) {
        try {
            return new EmptyContainer();
        } catch (IllegalArgumentException iae) {

        }
        return null;
    }

    @Override
    public EmptyContainer create(int windowId, Inventory inv, FriendlyByteBuf data) {
        return new EmptyContainer();
    }
}
