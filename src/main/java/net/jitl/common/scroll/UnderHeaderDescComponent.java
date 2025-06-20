package net.jitl.common.scroll;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.core.init.JITL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class UnderHeaderDescComponent implements IDescComponent {

    private final ResourceLocation content;
    private int contentHeight;

    public UnderHeaderDescComponent() {
        this.content = JITL.rl("textures/gui/scroll_base.png");
    }

    @Override
    public int getContentPartHeight() {
        return contentHeight;
    }

    @Override
    public void drawContentPart(GuiGraphics matrixStack, int x0, int y0, int width) {
        matrixStack.pose().pushMatrix();
        //GlStateManager._enableBlend();
        drawImage(x0, y0 - 5, 0, 151, 196,30, matrixStack);
        //todo GlStateManager._disableBlend();
        matrixStack.pose().popMatrix();
    }

    protected void drawImage(int x, int y, int textureX, int textureY, int realWidth, int realHeight, GuiGraphics matrix) {
        RenderSystem.setShaderTexture(0, Minecraft.getInstance().getTextureManager().getTexture(this.content).getTextureView());
        //RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        matrix.pose().pushMatrix();
        matrix.blit(RenderPipelines.GUI_TEXTURED, content, x, y, textureX, textureY, realWidth, realHeight, 256, 256);
        matrix.pose().popMatrix();
    }

    @Override
    public void determineContentPartHeight(int width) {
        contentHeight = 25 ;
    }
}