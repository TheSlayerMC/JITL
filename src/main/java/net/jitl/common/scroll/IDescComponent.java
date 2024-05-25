package net.jitl.common.scroll;

import net.minecraft.client.gui.GuiGraphics;

/*
 * Code by TimeConqueror
 */
public interface IDescComponent {
    int getContentPartHeight();

    void drawContentPart(GuiGraphics matrixStack, int x0, int y0, int width);

    /**
     * Must be called before drawingContentPart and doing any mechanics with
     */
    void determineContentPartHeight(int width);
}