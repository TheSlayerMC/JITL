package net.jitl.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.client.render.world.EucaRenderInfo;
import net.jitl.common.capability.stats.PlayerStatsProvider;
import net.jitl.common.world.dimension.Dimensions;
import net.jitl.core.init.internal.JItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientEventHandler {

    public static void regToBus(IEventBus forgeBus, IEventBus modEventBus) {
        forgeBus.addListener(ClientEventHandler::onFogDensityEvent);
    }

    public static void onFogDensityEvent(ViewportEvent.RenderFog event) {
        float farPlaneDistance = event.getFarPlaneDistance();
        Player player = Minecraft.getInstance().player;
        assert player != null;
        if (player.level.dimension() == Dimensions.FROZEN_LANDS) {
            player.getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(stats -> {
                float density;
                if(stats.hasBlizzard() || CuriosApi.getCuriosHelper().findFirstCurio(player, JItems.EYE_OF_THE_BLIZZARD.get()).isPresent()) {
                    density = 0.55F;
                } else {
                    density = 0.1F;
                }
                RenderSystem.setShaderFogStart(density);
                RenderSystem.setShaderFogEnd(density * farPlaneDistance);
            });
        }
    }

    public static void onClientSetup() {
        DimensionSpecialEffects.EFFECTS.put(Dimensions.EUCA_EFFECTS, new EucaRenderInfo());
    }
}
