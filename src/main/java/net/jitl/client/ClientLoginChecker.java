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
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.io.IOException;
import java.net.SocketException;

@Mod.EventBusSubscriber(modid = JITL.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientLoginChecker {

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if(JClientConfig.UPDATE_MESSAGE.get()) {
            if(!player.level().isClientSide) {
                try {
                    if(!InternetHandler.isOnline()) {
                        MutableComponent msg = Component.translatable("jitl.message.no_internet");
                        msg.withStyle(ChatFormatting.RED);
                        player.sendSystemMessage(msg);
                    }
                    try {
                        if(InternetHandler.isUpdateAvailable() && InternetHandler.isOnline()) {
                            MutableComponent msg1 = Component.translatable("jitl.message.update_available1", player.getDisplayName());
                            msg1.withStyle(ChatFormatting.AQUA);
                            MutableComponent msg2 = Component.translatable("jitl.message.update_available2", JITL.MOD_VERSION);
                            msg2.withStyle(ChatFormatting.RED);
                            MutableComponent msg3 = Component.translatable("jitl.message.update_available3");
                            msg3.withStyle(ChatFormatting.YELLOW);
                            MutableComponent msg4 = Component.translatable("jitl.message.update_available4", InternetHandler.getUpdateVersion());
                            msg4.withStyle(ChatFormatting.YELLOW);

                            player.sendSystemMessage(msg1);
                            player.sendSystemMessage(msg2);
                            player.sendSystemMessage(msg3);
                            player.sendSystemMessage(msg4);
                        }
                        if(!InternetHandler.isUpdateAvailable() && InternetHandler.isOnline()) {
                            MutableComponent msg1 = Component.translatable("jitl.message.no_update1", player.getDisplayName());
                            msg1.withStyle(ChatFormatting.AQUA);
                            MutableComponent msg2 = Component.translatable("jitl.message.no_update2", JITL.MOD_VERSION);
                            msg2.withStyle(ChatFormatting.LIGHT_PURPLE);
                            MutableComponent msg3 = Component.translatable("jitl.message.no_update3");
                            msg3.withStyle(ChatFormatting.GREEN);
                            player.sendSystemMessage(msg1);
                            player.sendSystemMessage(msg2);
                            player.sendSystemMessage(msg3);
                        }
                    } catch(IOException e) {
                        throw new RuntimeException(e);
                    }
                } catch(SocketException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}