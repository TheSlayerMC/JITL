package net.jitl.common.scroll;

import net.jitl.client.util.EnumHexColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

import java.util.List;

public class TextDescComponent implements IDescComponent {
    private final String langKey;
    private int contentHeight;
    private List<FormattedCharSequence> wrappedText;

    public TextDescComponent(String langKey) {
        this.langKey = langKey;
    }

    @Override
    public int getContentPartHeight() {
        return contentHeight + 5;
    }

    @Override
    public void drawContentPart(GuiGraphics matrixStack, int x0, int y0, int width) {
        int i = y0;
        for(FormattedCharSequence s : wrappedText) {
            matrixStack.drawString(Minecraft.getInstance().font, s, x0, i, -12566464, false);
            i += Minecraft.getInstance().font.lineHeight;
        }
    }

    @Override
    public void determineContentPartHeight(int width) {
        wrappedText = Minecraft.getInstance().font.split(Component.translatable(String.valueOf(langKey)), width);
        contentHeight = Minecraft.getInstance().font.lineHeight * wrappedText.size();
    }
}