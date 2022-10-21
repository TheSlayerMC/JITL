package net.jitl.common.event;

import net.jitl.client.stats.PlayerStatsProvider;
import net.jitl.common.world.dimension.Dimensions;
import net.jitl.core.init.JITL;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class DimensionWeatherHandler {

    @SubscribeEvent
    public static void handleDimensionWeather(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Level level = event.player.level;
        player.getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(stats -> {
            if (level.dimension() == Dimensions.FROZEN_LANDS) {
                if(stats.hasBlizzard()) {
                    level.setRainLevel(0.0F);
                } else {
                    level.setRainLevel(5.0F);

                }
            }
        });
    }
}
