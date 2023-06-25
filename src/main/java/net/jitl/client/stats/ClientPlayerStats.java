package net.jitl.client.stats;

import net.jitl.common.capability.stats.PlayerStatsProvider;
import net.minecraft.client.Minecraft;

public class ClientPlayerStats {

    public static void setHasBlizzard(boolean value) {
        Minecraft.getInstance().player.getCapability(PlayerStatsProvider.PLAYER_STATS).orElseThrow(null).setBlizzard(value);
    }

    public static boolean getHasBlizzard() {
        return Minecraft.getInstance().player.getCapability(PlayerStatsProvider.PLAYER_STATS).orElseThrow(null).hasBlizzard();
    }

    public static void addSentacoins(int value) {
        Minecraft.getInstance().player.getCapability(PlayerStatsProvider.PLAYER_STATS).orElseThrow(null).addSentacoins(value);
    }

    public static void useSentacoins(int value) {
        Minecraft.getInstance().player.getCapability(PlayerStatsProvider.PLAYER_STATS).orElseThrow(null).useSentacoins(value);
    }

    public static int getSentacoins() {
        return Minecraft.getInstance().player.getCapability(PlayerStatsProvider.PLAYER_STATS).orElseThrow(null).getSentacoins();
    }

    public static void setSentacoins(int value) {
        Minecraft.getInstance().player.getCapability(PlayerStatsProvider.PLAYER_STATS).orElseThrow(null).setSentacoins(value);
    }

}
