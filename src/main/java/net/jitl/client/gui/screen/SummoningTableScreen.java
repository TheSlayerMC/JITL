package net.jitl.client.gui.screen;

import net.jitl.common.block.entity.container.SummoningTableContainer;
import net.jitl.core.init.JITL;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class SummoningTableScreen extends AbstractContainerScreen<SummoningTableContainer> {

    private static final Identifier GUI_TEXTURE = Identifier.fromNamespaceAndPath(JITL.MOD_ID, "textures/gui/summoning_table.png");

    public SummoningTableScreen(SummoningTableContainer menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void extractRenderState(@NotNull GuiGraphicsExtractor pGuiGraphicsExtractor, int pMouseX, int pMouseY, float pPartialTick) {
        super.extractRenderState(pGuiGraphicsExtractor, pMouseX, pMouseY, pPartialTick);
        extractTooltip(pGuiGraphicsExtractor, pMouseX, pMouseY);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int pMouseX, int pMouseY, float pPartialTick) {
        //RenderSystem.setShaderTexture(0, minecraft.getTextureManager().getTexture(GUI_TEXTURE).getTextureView());
        int i = this.leftPos;
        int j = this.topPos;
        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
    }
}
