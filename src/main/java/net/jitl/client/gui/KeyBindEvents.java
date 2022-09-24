package net.jitl.client.gui;

import net.jitl.client.gui.overlay.PlayerStats;
import net.jitl.core.init.internal.JKeys;
import net.jitl.core.init.JITL;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class KeyBindEvents {

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
