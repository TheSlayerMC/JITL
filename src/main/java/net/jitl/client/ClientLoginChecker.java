package net.jitl.client;

import net.jitl.core.config.JClientConfig;
import net.jitl.core.helper.InternetHandler;
import net.jitl.core.init.JITL;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.io.IOException;
import java.net.SocketException;

@EventBusSubscriber(modid = JITL.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientLoginChecker {

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if(JClientConfig.UPDATE_MESSAGE.get()) {
            if(!player.level().isClientSide) {
                ChatUtils.sendColouredMessage(player, ChatFormatting.GOLD, "[|---------------------------------------------------|]");
                ChatUtils.sendColouredMessage(player, ChatFormatting.GOLD, "[" + JITL.MOD_NAME + "]");
                ChatUtils.sendColouredTranslatedMessage(player, ChatFormatting.LIGHT_PURPLE, "jitl.message.thank_you", player.getDisplayName());
                ChatUtils.sendColouredTranslatedMessage(player, ChatFormatting.BLUE, "jitl.message.current_version", JITL.MOD_VERSION);

                try {
                    if(!InternetHandler.isOnline()) {
                        MutableComponent msg = Component.translatable("jitl.message.no_internet");
                        msg.withStyle(ChatFormatting.RED);
                        ChatUtils.addChatBarChat(player, msg);
                    }
                    try {
                        if(InternetHandler.isUpdateAvailable() && InternetHandler.isOnline()) {
                            ChatUtils.sendColouredTranslatedMessage(player, ChatFormatting.GREEN, "jitl.message.update_available", InternetHandler.getUpdateVersion());
                        }
                        if(!InternetHandler.isUpdateAvailable() && InternetHandler.isOnline()) {
                            ChatUtils.sendColouredTranslatedMessage(player, ChatFormatting.AQUA, "jitl.message.up_to_date");
                        }
                    } catch(IOException e) {
                        throw new RuntimeException(e);
                    }
                } catch(SocketException e) {
                    throw new RuntimeException(e);
                }
                ChatUtils.sendColouredMessage(player, ChatFormatting.GOLD, "[|---------------------------------------------------|]");
            }
        }
    }
}