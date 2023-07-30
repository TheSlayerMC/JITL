package net.jitl.client.gui.screen;

import net.jitl.common.block.entity.container.JFurnaceMenu;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class JFurnaceScreen extends AbstractFurnaceScreen<JFurnaceMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/furnace.png");

    public JFurnaceScreen(JFurnaceMenu menu, Inventory playerInventory, Component title) {
        super(menu, new SmeltingRecipeBookComponent(), playerInventory, title, TEXTURE);
    }
}
