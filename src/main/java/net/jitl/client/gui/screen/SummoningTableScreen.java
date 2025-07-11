package net.jitl.client.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.common.block.entity.container.SummoningTableContainer;
import net.jitl.core.init.JITL;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class SummoningTableScreen extends AbstractContainerScreen<SummoningTableContainer> {

    private static final ResourceLocation GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "textures/gui/summoning_table.png");

    public SummoningTableScreen(SummoningTableContainer menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void render(@NotNull GuiGraphics matrixStack, int x, int y, float partialTicks) {
        super.render(matrixStack, x, y, partialTicks);
        renderTooltip(matrixStack, x, y);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics graphics, float tick, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, minecraft.getTextureManager().getTexture(GUI_TEXTURE).getTextureView());
        int i = this.leftPos;
        int j = this.topPos;
        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
    }
}
