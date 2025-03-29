package net.jitl.core.helper;

import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;

import java.util.List;
import java.util.function.Consumer;

public class TooltipFiller {
    private final Consumer<Component> tooltip;
    private final String key;
    private int line = 0;
    private final int startPoint;

    public TooltipFiller(Consumer<Component> text, String itemKey) {
        tooltip = text;
        key = itemKey;
        startPoint = -1;
    }

    public TooltipFiller(Consumer<Component> text, String itemKey, int start) {
        tooltip = text;
        key = itemKey;
        startPoint = start;
    }

    public void addTooltip(ChatFormatting color) {
        if (startPoint == -1) {
            tooltip.accept(Component.translatable("jitl.tooltip." + key + "." + line++).withStyle(color));
        } else {
            //tooltip.accept(startPoint + line, Component.translatable("jitl.tooltip." + key + "." + line++).withStyle(color));
        }
    }

    public void addOverview() {
        addTooltip(ChatFormatting.GOLD);
    }

    public void addDetail() {
        addTooltip(ChatFormatting.AQUA);
    }

    public void addDrawback() {
        addTooltip(ChatFormatting.RED);
    }

    public void addValue(Object... values) {
        //TODO: test
        tooltip.accept(Component.translatable("jitl.tooltip." + key + "." + line++, values).withStyle(ChatFormatting.GREEN));
    }

    public void addBreak() {
        tooltip.accept(Component.empty());
    }
}
