package net.jitl.client.essence;

import net.jitl.common.capability.essence.PlayerEssenceProvider;
import net.minecraft.client.Minecraft;

public class ClientEssence {

    public static void setClientEssence(float value) {
        Minecraft.getInstance().player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).orElseThrow(null).setEssence(value);
    }

    public static void setClientBurnout(float value) {
        Minecraft.getInstance().player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).orElseThrow(null).setBurnout(value);
    }

    public static float getCurrentClientEssence() {
        return Minecraft.getInstance().player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).orElseThrow(null).getCurrentEssence();
    }

    public static float getMaxClientEssence() {
        return Minecraft.getInstance().player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).orElseThrow(null).getMaxEssence(Minecraft.getInstance().player);
    }

    public static float getClientEssenceBurnout() {
        return Minecraft.getInstance().player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).orElseThrow(null).getBurnout();
    }
}
