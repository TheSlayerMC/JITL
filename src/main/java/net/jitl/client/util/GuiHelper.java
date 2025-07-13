package net.jitl.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;

public class GuiHelper {

    public static void drawString(GuiGraphics graphics, String text, int x, int y, int colour, boolean shadow) {
        graphics.drawString(Minecraft.getInstance().font, text, x, y, colour, shadow);
    }

    public static void drawString(GuiGraphics graphics, Component text, int x, int y, int colour, boolean shadow) {
        graphics.drawString(Minecraft.getInstance().font, text, x, y, colour, shadow);
    }

    public static void drawString(GuiGraphics graphics, FormattedCharSequence text, int x, int y, int colour, boolean shadow) {
        graphics.drawString(Minecraft.getInstance().font, text, x, y, colour, shadow);
    }

    public static void drawTexture(GuiGraphics graphics, ResourceLocation texture, int x, int y, int u, int v, int width, int height, int texWidth, int texHeight) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, u, v, width, height, texWidth, texHeight);
    }

    public static void drawCenteredStringWithCustomScale(GuiGraphics gui, Font f, FormattedText comp, int x, int y, int colour, float size, int availableHeight) {
        gui.pose().pushMatrix();
        gui.pose().translate((float) (x - (double)f.width(comp) / 2 * size), (float) (y + ((double)availableHeight / 2) + (f.lineHeight * size > 1 ? -1 * f.lineHeight * size : f.lineHeight * size) * 0.5));
        gui.pose().scale(size, size);
        GuiHelper.drawString(gui, comp.getString(), 0, 0, colour, false);
        gui.pose().popMatrix();
    }
}
