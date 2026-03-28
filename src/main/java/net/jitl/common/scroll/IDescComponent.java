package net.jitl.common.scroll;

import net.minecraft.client.gui.GuiGraphicsExtractor;

public interface IDescComponent {
    int getContentPartHeight();

    void drawContentPart(GuiGraphicsExtractor matrixStack, int x0, int y0, int width);

    void determineContentPartHeight(int width);
}