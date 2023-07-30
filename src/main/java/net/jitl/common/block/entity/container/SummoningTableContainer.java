package net.jitl.common.block.entity.container;

import net.jitl.core.init.internal.JContainers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SummoningTableContainer extends AbstractContainerMenu {

    private final Container container;

    public SummoningTableContainer(int id, Inventory playerInv, FriendlyByteBuf extraData) {
        this(id, playerInv, new SimpleContainer(7));
    }

    public SummoningTableContainer(int id, Inventory playerInv, Container c) {
        super(JContainers.SUMMONING_TABLE.get(), id);
        container = c;
        checkContainerSize(container, 1);

        this.addSlot(new Slot(container, 0, 44, 17));
        this.addSlot(new Slot(container, 1, 44, 35));
        this.addSlot(new Slot(container, 2, 44, 53));
        this.addSlot(new Slot(container, 3, 80, 35));
        this.addSlot(new Slot(container, 4, 117, 17));
        this.addSlot(new Slot(container, 5, 117, 35));
        this.addSlot(new Slot(container, 6, 117, 53));

        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                this.addSlot(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
        for (int i = 0; i < 9; ++i) this.addSlot(new Slot(playerInv, i, 8 + i * 18, 142));
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return container.stillValid(player);
    }
}