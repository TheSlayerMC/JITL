package net.jitl.client.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

public class ChatUtils {

    public static void addDialogStyleChat(Player player, String chat) {
        if(!player.level().isClientSide())
            player.displayClientMessage(Component.translatable(chat), true);
    }

    public static void addChatBarChat(Player player, String chat) {
        if(!player.level().isClientSide())
            player.displayClientMessage(Component.translatable(chat), false);
    }

    public static void addColouredDialogStyleChat(Player player, ChatFormatting c, String chat) {
        Component comp = Component.translatable(chat);
        comp.getStyle().withColor(c);
        if(!player.level().isClientSide()) {
            player.displayClientMessage(comp, true);
        }
    }

    public static void addColouredChatBarChat(Player player, ChatFormatting c, String chat) {
        Component comp = Component.translatable(chat);
        comp.getStyle().withColor(c);
        if(!player.level().isClientSide()) {
            player.displayClientMessage(comp, false);
        }
    }

    public static void sendColouredTranslatedMessage(Player player, ChatFormatting colour, String translationKey, Object... args) {
        MutableComponent msg = Component.translatable(translationKey, args);
        msg.withStyle(colour);
        player.displayClientMessage(msg, false);
    }

    public static void sendColouredMessage(Player player, ChatFormatting colour, String key) {
        MutableComponent msg = Component.literal(key);
        msg.withStyle(colour);
        player.displayClientMessage(msg, false);
    }
}
