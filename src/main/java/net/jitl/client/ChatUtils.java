package net.jitl.client;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class ChatUtils {

    public static void addDialogStyleChat(Player player, String chat) {
        if(!player.level().isClientSide)
            player.displayClientMessage(Component.translatable(chat), true);
    }

    public static void addChatBarChat(Player player, String chat) {
        if(!player.level().isClientSide)
            player.displayClientMessage(Component.translatable(chat), false);
    }
}
