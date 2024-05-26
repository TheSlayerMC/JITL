package net.jitl.common.scroll;

import net.minecraft.client.gui.GuiGraphics;

public interface IDescComponent {
    int getContentPartHeight();

    void drawContentPart(GuiGraphics matrixStack, int x0, int y0, int width);

    void determineContentPartHeight(int width);
}