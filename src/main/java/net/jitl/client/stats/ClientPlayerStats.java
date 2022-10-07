package net.jitl.client.stats;

import net.minecraft.client.Minecraft;

public class ClientPlayerStats {

    public static void setHasBlizzard(boolean value) {
        Minecraft.getInstance().player.getCapability(PlayerStatsProvider.PLAYER_STATS).orElseThrow(null).setBlizzard(value);
    }

    public static boolean getHasBlizzard() {
        return Minecraft.getInstance().player.getCapability(PlayerStatsProvider.PLAYER_STATS).orElseThrow(null).hasBlizzard();
    }

}
