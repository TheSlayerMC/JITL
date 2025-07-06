package net.jitl.client.gui;

import net.jitl.client.gui.overlay.PlayerStats;
import net.jitl.core.data.JNetworkRegistry;
import net.jitl.core.init.network.PacketKeyPressed;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;

public class KeyUsedEvent {

    private static final Minecraft MINECRAFT = Minecraft.getInstance();

    @SubscribeEvent
    public static void onKeyInput(ClientTickEvent.Post event) {
        if (MINECRAFT.screen == null) {

            assert MINECRAFT.player != null;
            while(KeyBindEvents.keyStats.consumeClick()) {
                assert Minecraft.getInstance().player != null;
                displayPlayerStats(Minecraft.getInstance().player);
            }
            if(KeyBindEvents.keyAmulet.isDown() || KeyBindEvents.keyArmor.isDown()) {
                handleAbilityKeys(KeyBindEvents.keyAmulet.isDown(), KeyBindEvents.keyArmor.isDown());
            } else {
                handleAbilityKeys(false, false);
            }
        }
    }

    public static void handleAbilityKeys(boolean amulet, boolean gear) {
        JNetworkRegistry.sendToServer(new PacketKeyPressed(amulet, gear));
    }

    public static void displayPlayerStats(Player player) {
        Minecraft.getInstance().setScreen(new PlayerStats(player));
    }
}
