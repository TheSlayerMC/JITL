package net.jitl.client.gui;

import com.mojang.blaze3d.platform.InputConstants;
import net.jitl.client.gui.overlay.PlayerStats;
import net.jitl.core.data.JNetworkRegistry;
import net.jitl.core.init.network.CKeyPressedPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import org.lwjgl.glfw.GLFW;

public class KeyUsedEvent {

    private static final Minecraft MINECRAFT = Minecraft.getInstance();

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        InputConstants.Key key = InputConstants.getKey(event.getKey(), event.getScanCode());
        if (MINECRAFT.screen == null) {

            assert MINECRAFT.player != null;
            int action = event.getAction();

            if (action == GLFW.GLFW_PRESS) {
                if (key == KeyBindEvents.keyStats.getKey()) {
                    assert Minecraft.getInstance().player != null;
                    displayPlayerStats(Minecraft.getInstance().player);

                } else {
                    //handleIsometricCameraKeys(key);
                    //handleBigScreenshotKeys(key);
                    handleAbilityKeys(key, action);
                }
            } else if (action == GLFW.GLFW_RELEASE) {
                handleAbilityKeys(key, action);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void displayPlayerStats(Player player) {
        Minecraft.getInstance().setScreen(new PlayerStats(player));
    }

    public static void handleAbilityKeys(InputConstants.Key input, int action) {
        boolean key = input == KeyBindEvents.keyAmulet.getKey();
        if (key || input == KeyBindEvents.keyArmor.getKey()) {
            //JNetworkRegistry.sendToPlayer(new CKeyPressedPacket(key, action == GLFW.GLFW_PRESS));
        }
    }
}
