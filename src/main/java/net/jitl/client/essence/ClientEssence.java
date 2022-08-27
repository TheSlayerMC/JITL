package net.jitl.client.essence;

import net.minecraft.client.Minecraft;

public class ClientEssence {

    public static void setClientEssence(int value) {
        Minecraft.getInstance().player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).orElseThrow(null).setEssence(value);
    }

    public static int getClientEssence() {
        return Minecraft.getInstance().player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).orElseThrow(null).getEssence();
    }

}
