package net.jitl.common.block.entity.container;

import net.jitl.core.init.internal.JContainers;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeType;

public class JFurnaceMenu extends AbstractFurnaceMenu {

    public JFurnaceMenu(int sync, Inventory inventory) {
        super(JContainers.JFURNACE.get(), RecipeType.SMELTING, RecipeBookType.FURNACE, sync, inventory);
    }

    public JFurnaceMenu(int sync, Inventory inventory, Container container, ContainerData data) {
        super(JContainers.JFURNACE.get(), RecipeType.SMELTING, RecipeBookType.FURNACE, sync, inventory, container, data);
    }

}