package net.jitl.client.gui.screen;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.jitl.client.util.EnumHexColor;
import net.jitl.client.util.GuiHelper;
import net.jitl.common.scroll.IDescComponent;
import net.jitl.common.scroll.ScrollEntry;
import net.jitl.core.helper.internal.DrawHelper;
import net.jitl.core.init.JITL;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

public class LoreScrollEntryScreen extends Screen {

    private static final ResourceLocation BG = JITL.rl("textures/gui/scroll_base.png");
    private static final int SLIDER_LIGHT_COLOR = 0xFFe5bd85;
    private static final int SLIDER_PATH_COLOR = 0x333c2c14;
    private static final int SLIDER_DARK_COLOR = 0xFFc18a3c;
    private final int headerHeight = 30;
    private final ScrollEntry scrollEntry;
    private int guiWidth;
    private int guiHeight;
    private int guix0;
    private int guiy0;
    private int mouseX;
    private int mouseY;
    private int entryWidth;
    private int top;
    private int bottom;
    private int left;
    private float initialMouseClickY = -2.0F;
    private float scrollDistance;
    private float scrollFactor;

    public LoreScrollEntryScreen(ScrollEntry ScrollEntry) {
        super(Component.translatable("jitl.lore_scroll"));
        this.scrollEntry = ScrollEntry;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }


    private void drawScreen(int mouseX, int mouseY) { }

    private int getContentPartCount() {
        return scrollEntry.getDesc().size();
    }

    private int getContentHeight() {
        int conHeight = this.headerHeight;
        for (IDescComponent part : scrollEntry.getDesc()) {
            conHeight += part.getContentPartHeight();
        }
        return conHeight;
    }

    private int getContentPartHeight(int index) {
        return scrollEntry.getDesc().get(index).getContentPartHeight();
    }

    private void determineAllContentPartHeight(int width) {
        for (IDescComponent part : scrollEntry.getDesc()) {
            part.determineContentPartHeight(width);
        }
    }

    private void drawHeader(GuiGraphics poseStack, int maxX, int y0, Tesselator tess) {
        if(scrollEntry.hasComment()) {
            GuiHelper.drawCenteredStringWithCustomScale(poseStack, font, Component.translatable(scrollEntry.getTitleKey()), left + (maxX - left) / 2 + 1, y0, ARGB.colorFromFloat(1, 0, 0, 0), 1.5F, headerHeight - 5);
            if(scrollEntry.getCommentKey() != null)
                GuiHelper.drawCenteredStringWithCustomScale(poseStack, font, Component.translatable(scrollEntry.getCommentKey()), left + (maxX - left) / 2 + 1, y0 + (int) ((float) font.lineHeight * 0.7),-12566464, 1F, headerHeight + 5);
        } else {
            GuiHelper.drawCenteredStringWithCustomScale(poseStack, font, Component.translatable(scrollEntry.getTitleKey()), left + (maxX - left) / 2 + 1, y0, -12566464, 1.2F, headerHeight);
        }
    }

    private void drawContentPart(GuiGraphics poseStack, int partIdx, int contentRight, int partTop, int partBuffer, Tesselator tess) {
        scrollEntry.getDesc().get(partIdx).drawContentPart(poseStack, this.left + 2, partTop, contentRight);
    }

    private void applyScrollLimits() {
        int listHeight = this.getContentHeight() - (this.bottom - this.top - 4);

        if(listHeight < 0)
            listHeight /= 2;

        if(this.scrollDistance < 0.0F)
            this.scrollDistance = 0.0F;

        if(this.scrollDistance > (float) listHeight)
            this.scrollDistance = (float) listHeight;
    }

    @Override
    public void render(@NotNull GuiGraphics poseStack, int mouseX, int mouseY, float partialTicks) {
        assert minecraft != null;
        RenderSystem.setShaderTexture(0, minecraft.getTextureManager().getTexture(BG).getTextureView());

        int heightRectCount = (height - (height <= 480 ? 12 : 48)) / 32;
        int widthRectCount = height <= 480 ? 6 : 10;

        this.guiWidth = widthRectCount * 32;
        this.guiHeight = heightRectCount * 32;

        this.guix0 = width / 2 - guiWidth / 2;
        this.guiy0 = height / 2 - guiHeight / 2;

        for (int x = 0; x < widthRectCount; x++) {
            for (int y = 0; y < heightRectCount; y++) {
                int textureX = x == 0 ? 0 : (x == widthRectCount - 1 ? 64 : 32);
                int textureY = y == 0 ? 0 : (y == heightRectCount - 1 ? 64 : 32);
                poseStack.blit(RenderPipelines.GUI_TEXTURED, BG, guix0 + x * 32, guiy0 + y * 32, textureX, textureY, 32, 32, 256, 256);
            }
        }
        drawScrollingContent(poseStack, mouseX, mouseY);
    }

    private void drawScrollingContent(GuiGraphics poseStack, int mouseX, int mouseY) {
        int indent = 17;
        this.left = guix0 + indent + 4;
        this.top = guiy0 + indent;
        this.entryWidth = guiWidth - indent * 2;
        int entryHeight = guiHeight - indent * 2;
        this.bottom = top + entryHeight;

        this.mouseX = mouseX;
        this.mouseY = mouseY;

        boolean isHovering = mouseX >= this.left && mouseX <= this.left + this.entryWidth && mouseY >= this.top && mouseY <= this.bottom;
        int entryLength = this.getContentPartCount();
        int scrollButtonWidth = 3;
        int scrollButtonRightTop = this.left + this.entryWidth;
        int scrollButtonLeftTop = scrollButtonRightTop - scrollButtonWidth;
        int contentRightTop = scrollButtonLeftTop - 5;

        determineAllContentPartHeight(contentRightTop - this.left - 4);

        int viewHeight = this.bottom - this.top;
        int border = 4;

        assert minecraft != null;
        MouseHandler mouseHandler = new MouseHandler(minecraft);
        if(mouseHandler.isLeftPressed()) {
            if(this.initialMouseClickY == -1.0F) {
                if(isHovering) {
                    if(mouseX >= scrollButtonLeftTop && mouseX <= scrollButtonRightTop) {
                        this.scrollFactor = -1.0F;
                        int scrollHeight = this.getContentHeight() - viewHeight - border;
                        if(scrollHeight < 1) scrollHeight = 1;

                        int var13 = (int) ((float) (viewHeight * viewHeight) / (float) this.getContentHeight());

                        if(var13 < 32) var13 = 32;
                        if(var13 > viewHeight - border * 2)
                            var13 = viewHeight - border * 2;

                        this.scrollFactor /= (float) (viewHeight - var13) / (float) scrollHeight;

                    } else {
                        this.scrollFactor = 1.0F;
                    }

                    this.initialMouseClickY = mouseY;
                } else {
                    this.initialMouseClickY = -2.0F;
                }
            } else if(this.initialMouseClickY >= 0.0F) {
                this.scrollDistance -= ((float) mouseY - this.initialMouseClickY) * this.scrollFactor;
                this.initialMouseClickY = (float) mouseY;
            }
        } else {
            this.initialMouseClickY = -1.0F;
        }

        this.applyScrollLimits();

        Tesselator tess = Tesselator.getInstance();

        double scaleW = minecraft.getWindow().getScreenWidth() / (double) minecraft.getWindow().getGuiScaledWidth();
        double scaleH = minecraft.getWindow().getScreenHeight() / (double) minecraft.getWindow().getGuiScaledHeight();
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor((int) (left * scaleW), (int) (minecraft.getWindow().getScreenHeight() - (bottom * scaleH)), (int) (entryWidth * scaleW), (int) (viewHeight * scaleH));

        int baseY = this.top + border - (int) this.scrollDistance;
        int indentY = 0;

        //RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        for (int partIdx = 0; partIdx < entryLength; ++partIdx) {
            int partTop = baseY + this.headerHeight + indentY;
            int partBuffer = getContentPartHeight(partIdx) - border;

            if(baseY + headerHeight >= top) {
                drawHeader(poseStack, contentRightTop, baseY, tess);
            }

            if(partTop <= this.bottom && partTop + partBuffer >= this.top) {
                int min = this.left;
                this.drawContentPart(poseStack, partIdx, contentRightTop - min - 4, partTop, partBuffer, tess);
            }
            indentY += scrollEntry.getDesc().get(partIdx).getContentPartHeight();
        }

        int extraHeight = (this.getContentHeight() + border) - viewHeight;
        if(extraHeight > 0) {
            int height = (viewHeight * viewHeight) / this.getContentHeight();
            if(height < 32) {
                height = 32;
            }
            if(height > viewHeight - border * 2) {
                height = viewHeight - border * 2;
            }
            int barTop = (int) this.scrollDistance * (viewHeight - height) / extraHeight + this.top;
            if(barTop < this.top) {
                barTop = this.top;
            }

            int alpha, red, green, blue;
            alpha = DrawHelper.getAlpha(SLIDER_PATH_COLOR);
            red = DrawHelper.getRed(SLIDER_PATH_COLOR);
            green = DrawHelper.getGreen(SLIDER_PATH_COLOR);
            blue = DrawHelper.getBlue(SLIDER_PATH_COLOR);
            BufferBuilder worldr = tess.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
            worldr.addVertex(scrollButtonLeftTop, this.bottom, 0.0F).setUv(0.0F, 1.0F).setColor(red, green, blue, alpha);
            worldr.addVertex(scrollButtonRightTop, this.bottom, 0.0F).setUv(1.0F, 1.0F).setColor(red, green, blue, alpha);
            worldr.addVertex(scrollButtonRightTop, this.top, 0.0F).setUv(1.0F, 0.0F).setColor(red, green, blue, alpha);
            worldr.addVertex(scrollButtonLeftTop, this.top, 0.0F).setUv(0.0F, 0.0F).setColor(red, green, blue, alpha);
            //worldr.build();

            // Dark slider part
            alpha = DrawHelper.getAlpha(SLIDER_DARK_COLOR);
            red = DrawHelper.getRed(SLIDER_DARK_COLOR);
            green = DrawHelper.getGreen(SLIDER_DARK_COLOR);
            blue = DrawHelper.getBlue(SLIDER_DARK_COLOR);
            worldr = tess.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);

            worldr.addVertex(scrollButtonLeftTop, barTop + height, 0.0F).setUv(0.0F, 1.0F).setColor(red, green, blue, alpha);
            worldr.addVertex(scrollButtonRightTop, barTop + height, 0.0F).setUv(1.0F, 1.0F).setColor(red, green, blue, alpha);
            worldr.addVertex(scrollButtonRightTop, barTop, 0.0F).setUv(1.0F, 0.0F).setColor(red, green, blue, alpha);
            worldr.addVertex(scrollButtonLeftTop, barTop, 0.0F).setUv(0.0F, 0.0F).setColor(red, green, blue, alpha);
            //BufferUploader.drawWithShader(worldr.buildOrThrow());

            // Light slider part
            alpha = DrawHelper.getAlpha(SLIDER_LIGHT_COLOR);
            red = DrawHelper.getRed(SLIDER_LIGHT_COLOR);
            green = DrawHelper.getGreen(SLIDER_LIGHT_COLOR);
            blue = DrawHelper.getBlue(SLIDER_LIGHT_COLOR);
            worldr = tess.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);

            worldr.addVertex(scrollButtonLeftTop, barTop + height - 1, 0.0F).setUv(0.0F, 1.0F).setColor(red, green, blue, alpha);
            worldr.addVertex(scrollButtonRightTop - 1, barTop + height - 1, 0.0F).setUv(1.0F, 1.0F).setColor(red, green, blue, alpha);
            worldr.addVertex(scrollButtonRightTop - 1, barTop, 0.0F).setUv(1.0F, 0.0F).setColor(red, green, blue, alpha);
            worldr.addVertex(scrollButtonLeftTop, barTop, 0.0F).setUv(0.0F, 0.0F).setColor(red, green, blue, alpha);
//            BufferUploader.drawWithShader(worldr.buildOrThrow());
//
        }

        this.drawScreen(mouseX, mouseY);
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pScrollX, double pScrollY) {
        boolean isHovering = mouseX >= this.left && mouseX <= this.left + this.entryWidth &&
                mouseY >= this.top && mouseY <= this.bottom;
        if(!isHovering)
            return false;

        this.scrollDistance += (float)((-1 * pScrollY) * 2);
        return super.mouseScrolled(pMouseX, pMouseY, pScrollX, pScrollY);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers_) {
        if(keyCode == 256) {
            assert minecraft != null;
            minecraft.setScreen(null);
        }
        return true;
    }
}