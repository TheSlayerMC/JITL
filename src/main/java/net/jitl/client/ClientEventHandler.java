package net.jitl.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.client.render.world.EucaRenderInfo;
import net.jitl.client.stats.PlayerStatsProvider;
import net.jitl.common.world.dimension.Dimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientEventHandler {

    public static void regToBus(IEventBus forgeBus, IEventBus modEventBus) {
        forgeBus.addListener(ClientEventHandler::onFogDensityEvent);
        modEventBus.addListener(ClientEventHandler::onClientSetup);
    }

    public static void onFogDensityEvent(ViewportEvent.RenderFog event) {
        float farPlaneDistance = event.getFarPlaneDistance();
        Player player = Minecraft.getInstance().player;
        assert player != null;
        player.getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(stats -> {
            if (player.level.dimension() == Dimensions.FROZEN_LANDS) {
                float density;
                if(stats.hasBlizzard()) {
                    density = 0.6F;
                } else {
                    density = 0.1F;
                }
                RenderSystem.setShaderFogStart(density);
                RenderSystem.setShaderFogEnd(density * farPlaneDistance);
            }
        });
    }

    private static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            DimensionSpecialEffects.EFFECTS.put(Dimensions.EUCA_TYPE.location(), new EucaRenderInfo());
        });
    }
}
