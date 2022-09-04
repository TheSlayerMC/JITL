package net.jitl.client.gui;

import net.jitl.client.essence.PlayerEssence;
import net.jitl.client.gui.overlay.EssenceBar;
import net.jitl.client.gui.overlay.PlayerStats;
import net.jitl.client.knowledge.PlayerKnowledge;
import net.jitl.core.helper.JKeys;
import net.jitl.core.init.JITL;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OverlayRegistry {

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("essence_bar", EssenceBar::render);
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerEssence.class);
        event.register(PlayerKnowledge.class);
    }

    @Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(JKeys.KEY_STATS);
        }

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(JKeys.KEY_STATS.consumeClick()) {
                assert Minecraft.getInstance().player != null;
                Minecraft.getInstance().setScreen(new PlayerStats(Minecraft.getInstance().player.getInventory()));
            }
        }
    }

}
