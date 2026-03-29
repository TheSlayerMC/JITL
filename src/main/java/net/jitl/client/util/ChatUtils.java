package net.jitl.client.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

public class ChatUtils {

    public static void addDialogStyleChat(Player player, String chat) {
        if(!player.level().isClientSide())
            player.sendOverlayMessage(Component.translatable(chat));
    }

    public static void addChatBarChat(Player player, String chat) {
        if(!player.level().isClientSide())
            player.sendSystemMessage(Component.translatable(chat));
    }

    public static void addChatBarChat(Player player, Component chat) {
        if(!player.level().isClientSide())
            player.sendSystemMessage(chat);
    }

    public static void addColouredDialogStyleChat(Player player, ChatFormatting c, String chat) {
        Component comp = Component.translatable(chat);
        comp.getStyle().withColor(c);
        if(!player.level().isClientSide()) {
            player.sendOverlayMessage(comp);
        }
    }

    public static void addColouredChatBarChat(Player player, ChatFormatting c, String chat) {
        Component comp = Component.translatable(chat);
        comp.getStyle().withColor(c);
        if(!player.level().isClientSide()) {
            player.sendSystemMessage(comp);
        }
    }

    public static void sendColouredTranslatedMessage(Player player, ChatFormatting colour, String translationKey, Object... args) {
        MutableComponent msg = Component.translatable(translationKey, args);
        msg.withStyle(colour);
        player.sendSystemMessage(msg);
    }

    public static void sendColouredMessage(Player player, ChatFormatting colour, String key) {
        MutableComponent msg = Component.literal(key);
        msg.withStyle(colour);
        player.sendSystemMessage(msg);
    }
}
