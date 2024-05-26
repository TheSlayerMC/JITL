package net.jitl.common.scroll;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.core.init.JITL;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
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
        matrixStack.pose().pushPose();
        GlStateManager._enableBlend();
        drawImage(x0, y0 - 5, 0, 151, 196,30, matrixStack);
        GlStateManager._disableBlend();
        matrixStack.pose().popPose();
    }

    protected void drawImage(int x, int y, int textureX, int textureY, int realWidth, int realHeight, GuiGraphics matrix) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, this.content);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        matrix.pose().pushPose();
        matrix.blit(content, x, y, textureX, textureY, realWidth, realHeight);
        matrix.pose().popPose();
    }

    @Override
    public void determineContentPartHeight(int width) {
        contentHeight = 25 ;
    }
}