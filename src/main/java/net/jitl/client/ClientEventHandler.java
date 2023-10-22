package net.jitl.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.client.render.world.*;
import net.jitl.client.util.ClientGetter;
import net.jitl.common.capability.stats.PlayerStatsProvider;
import net.jitl.common.world.dimension.Dimensions;
import net.jitl.core.init.JITL;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler {

    public static void regToBus(IEventBus forgeBus) {
        forgeBus.addListener(ClientEventHandler::onFogDensityEvent);
    }

    public static void onFogDensityEvent(ViewportEvent.RenderFog event) {
        float farPlaneDistance = event.getFarPlaneDistance();
        Player player = ClientGetter.player();
        if (ClientGetter.level().dimension() == Dimensions.FROZEN_LANDS) {
            player.getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(stats -> {
                float density;
                if(stats.hasBlizzard() ) {//|| CuriosApi.getCuriosHelper().findFirstCurio(player, JItems.EYE_OF_THE_BLIZZARD.get()).isPresent()) {
                    density = 0.55F;
                } else {
                    density = 0.1F;
                }
                RenderSystem.setShaderFogStart(density);
                RenderSystem.setShaderFogEnd(density * farPlaneDistance);
            });
        }

        if(ClientGetter.level().dimension() == Dimensions.CLOUDIA) {
            float density = 0.35F;
            RenderSystem.setShaderFogStart(density);
            RenderSystem.setShaderFogEnd(density * farPlaneDistance);
        }

        if(ClientGetter.level().dimension() == Dimensions.DEPTHS) {
            float density = 0.5F;
            RenderSystem.setShaderFogStart(density);
            RenderSystem.setShaderFogEnd(density * farPlaneDistance);
        }
    }

    @SubscribeEvent
    public static void registerDimensionSpecialEffects(RegisterDimensionSpecialEffectsEvent event) {
        event.register(Dimensions.EUCA_EFFECTS, new EucaRenderInfo());
        event.register(Dimensions.BOIL_EFFECTS, new BoilRenderInfo());
        event.register(Dimensions.FROZEN_EFFECTS, new FrozenRenderInfo());
        event.register(Dimensions.TERRANIA_EFFECTS, new TerraniaRenderInfo());
        event.register(Dimensions.CLOUDIA_EFFECTS, new CloudiaRenderInfo());
    }
}
