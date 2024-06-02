package net.jitl.client.util;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

import java.util.function.BiConsumer;

public class RenderUtils {

    private static final Minecraft minecraft = Minecraft.getInstance();
    private static final int width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
    private static final int height = Minecraft.getInstance().getWindow().getGuiScaledHeight();
    protected static Font font = Minecraft.getInstance().font;
    protected static ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

    public static void drawCenteredStringWithCustomScale(GuiGraphics gui, Font f, FormattedText comp, int x, int y, int z, EnumHexColor colour, float size, int avaliableHeight) {
        gui.pose().pushPose();
        gui.pose().translate(x - (double)f.width(comp) / 2 * size, y + ((double)avaliableHeight / 2) + (f.lineHeight * size > 1 ? -1 * f.lineHeight * size : f.lineHeight * size) * 0.5, z);
        gui.pose().scale(size, size, size);
        gui.drawString(f, comp.getString(), 0, 0, colour.getInt(), false);
        gui.pose().popPose();
    }

    public static void rectangle(GuiGraphics poseStack, Rectangle rectangle, int argbColor) {
        fill(poseStack, rectangle.left(), rectangle.top(), rectangle.right(), rectangle.bottom(), argbColor);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void fill(GuiGraphics matrixStack, int minX, int minY, int maxX, int maxY, int colorIn) {
        innerFill(matrixStack, minX, minY, maxX, maxY, colorIn);
    }

    private static void innerFill(GuiGraphics graphics, int minX, int minY, int maxX, int maxY, int colorIn) {
        Matrix4f matrix = graphics.pose().last().pose();
        if (minX < maxX) {
            int i = minX;
            minX = maxX;
            maxX = i;
        }

        if (minY < maxY) {
            int j = minY;
            minY = maxY;
            maxY = j;
        }

        float f3 = (float) (colorIn >> 24 & 255) / 255.0F;
        float f = (float) (colorIn >> 16 & 255) / 255.0F;
        float f1 = (float) (colorIn >> 8 & 255) / 255.0F;
        float f2 = (float) (colorIn & 255) / 255.0F;
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        bufferbuilder.vertex(matrix, (float) minX, (float) maxY, 0.0F).color(f, f1, f2, f3).endVertex();
        bufferbuilder.vertex(matrix, (float) maxX, (float) maxY, 0.0F).color(f, f1, f2, f3).endVertex();
        bufferbuilder.vertex(matrix, (float) maxX, (float) minY, 0.0F).color(f, f1, f2, f3).endVertex();
        bufferbuilder.vertex(matrix, (float) minX, (float) minY, 0.0F).color(f, f1, f2, f3).endVertex();
        bufferbuilder.end();
        BufferUploader.reset();
        RenderSystem.disableBlend();
    }

    public void fillGradient(PoseStack matrixStack, int x1, int y1, int x2, int y2, int colorFrom, int colorTo, int blitOffset) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        fillGradient(matrixStack.last().pose(), bufferbuilder, x1, y1, x2, y2, blitOffset, colorFrom, colorTo);
        tessellator.end();
        RenderSystem.disableBlend();
    }

    public static void fillGradient(Matrix4f matrix, BufferBuilder builder, int x1, int y1, int x2, int y2, int z, int colorA, int colorB) {
        float f = (float) (colorA >> 24 & 255) / 255.0F;
        float f1 = (float) (colorA >> 16 & 255) / 255.0F;
        float f2 = (float) (colorA >> 8 & 255) / 255.0F;
        float f3 = (float) (colorA & 255) / 255.0F;
        float f4 = (float) (colorB >> 24 & 255) / 255.0F;
        float f5 = (float) (colorB >> 16 & 255) / 255.0F;
        float f6 = (float) (colorB >> 8 & 255) / 255.0F;
        float f7 = (float) (colorB & 255) / 255.0F;
        builder.vertex(matrix, (float) x2, (float) y1, (float) z).color(f1, f2, f3, f).endVertex();
        builder.vertex(matrix, (float) x1, (float) y1, (float) z).color(f1, f2, f3, f).endVertex();
        builder.vertex(matrix, (float) x1, (float) y2, (float) z).color(f5, f6, f7, f4).endVertex();
        builder.vertex(matrix, (float) x2, (float) y2, (float) z).color(f5, f6, f7, f4).endVertex();
    }

    public static void drawCenteredString(GuiGraphics gui, FormattedText comp, String fontIn, float x, float y, EnumHexColor colour) {
        gui.drawString(font, comp.getString(), x - font.width(fontIn) / 2F, y, colour.getInt(), true);

    }

    public static void blitOutlineBlack(int width, int height, BiConsumer<Integer, Integer> boxXYConsumer) {
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        boxXYConsumer.accept(width + 1, height);
        boxXYConsumer.accept(width - 1, height);
        boxXYConsumer.accept(width, height + 1);
        boxXYConsumer.accept(width, height - 1);
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        boxXYConsumer.accept(width, height);
    }

    public static void blit(PoseStack matrixStack, int x, int y, int blitOffset, int width, int height, TextureAtlasSprite sprite) {
        innerBlit(matrixStack.last().pose(), x, x + width, y, y + height, blitOffset, sprite.getU0(), sprite.getU1(), sprite.getV0(), sprite.getV1());
    }

    public static void blit(PoseStack matrixStack, int x, int y, int uOffset, int vOffset, int uWidth, int vHeight, int blitOffset) {
        blit(matrixStack, x, y, blitOffset, (float) uOffset, (float) vOffset, uWidth, vHeight, 256, 256);
    }

    public static void blit(PoseStack matrixStack, int x, int y, int blitOffset, float uOffset, float vOffset, int uWidth, int vHeight, int textureHeight, int textureWidth) {
        innerBlit(matrixStack, x, x + uWidth, y, y + vHeight, blitOffset, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight);
    }

    public static void blit(PoseStack matrixStack, int x, int y, int width, int height, float uOffset, float vOffset, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        innerBlit(matrixStack, x, x + width, y, y + height, 0, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight);
    }

    public static void blit(PoseStack matrixStack, int x, int y, float uOffset, float vOffset, int width, int height, int textureWidth, int textureHeight) {
        blit(matrixStack, x, y, width, height, uOffset, vOffset, width, height, textureWidth, textureHeight);
    }

    private static void innerBlit(PoseStack matrixStack, int x1, int x2, int y1, int y2, int blitOffset, int uWidth, int vHeight, float uOffset, float vOffset, int textureWidth, int textureHeight) {
        innerBlit(matrixStack.last().pose(), x1, x2, y1, y2, blitOffset, (uOffset + 0.0F) / (float) textureWidth, (uOffset + (float) uWidth) / (float) textureWidth, (vOffset + 0.0F) / (float) textureHeight, (vOffset + (float) vHeight) / (float) textureHeight);
    }

    private static void innerBlit(Matrix4f matrix, int x1, int x2, int y1, int y2, int blitOffset, float minU, float maxU, float minV, float maxV) {
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(matrix, (float) x1, (float) y2, (float) blitOffset).uv(minU, maxV).endVertex();
        bufferbuilder.vertex(matrix, (float) x2, (float) y2, (float) blitOffset).uv(maxU, maxV).endVertex();
        bufferbuilder.vertex(matrix, (float) x2, (float) y1, (float) blitOffset).uv(maxU, minV).endVertex();
        bufferbuilder.vertex(matrix, (float) x1, (float) y1, (float) blitOffset).uv(minU, minV).endVertex();
        bufferbuilder.end();
    }

    public static void renderTextureOverlay(ResourceLocation textureLocation, float alpha) {
        Minecraft minecraft = Minecraft.getInstance();
        int width = minecraft.getWindow().getGuiScaledWidth();
        int height = minecraft.getWindow().getGuiScaledHeight();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
        RenderSystem.setShaderTexture(0, textureLocation);
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(0.0D, height, -90.0D).uv(0.0F, 1.0F).endVertex();
        bufferbuilder.vertex(width, height, -90.0D).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(width, 0.0D, -90.0D).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 0.0F).endVertex();
        tesselator.end();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
