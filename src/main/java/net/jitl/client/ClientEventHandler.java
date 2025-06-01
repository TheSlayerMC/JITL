package net.jitl.client;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.client.render.world.*;
import net.jitl.client.render.world.clouds.JCloudRenderer;
import net.jitl.client.util.ClientGetter;
import net.jitl.common.world.dimension.Dimensions;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JItems;
import net.minecraft.client.renderer.FogParameters;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.AddClientReloadListenersEvent;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

@EventBusSubscriber(modid = JITL.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler {

    public static void regToBus(IEventBus forgeBus) {
        forgeBus.addListener(ClientEventHandler::onFogDensityEvent);
    }

    @SubscribeEvent
    public static void addReload(AddClientReloadListenersEvent event) {
        event.addListener(JITL.rl("clouds"), new JCloudRenderer());
    }

    public static void onFogDensityEvent(ViewportEvent.RenderFog event) {
        event.setCanceled(true);
        float farPlaneDistance = event.getFarPlaneDistance();
        Player player = ClientGetter.player();
        if (ClientGetter.level().dimension() == Dimensions.FROZEN_LANDS) {
            float density;
            ICuriosItemHandler curios = CuriosApi.getCuriosInventory(player).get();
            if(player.getData(JDataAttachments.PLAYER_STATS).hasBlizzard() || curios.findFirstCurio(JItems.EYE_OF_THE_BLIZZARD.get()).isPresent()) {
                density = 0.55F;
            } else {
                density = 0.1F;
            }
            event.setNearPlaneDistance(density);
            event.setFarPlaneDistance(density * farPlaneDistance);
        }

        if(ClientGetter.level().dimension() == Dimensions.CLOUDIA) {
            float density = 0.35F;
            event.setNearPlaneDistance(density);
            event.setFarPlaneDistance(density * farPlaneDistance);
        }

        if(ClientGetter.level().dimension() == Dimensions.DEPTHS) {
            float density = 0.85F;
            event.setNearPlaneDistance(density);
            event.setFarPlaneDistance(density * farPlaneDistance);
        }

//        if(ClientGetter.level().dimension() == Dimensions.EUCA) {
//            float density = 2.5F;
//            event.setNearPlaneDistance(density);
//            event.setFarPlaneDistance(density * farPlaneDistance);
//        }
    }

    @SubscribeEvent
    public static void registerDimensionSpecialEffects(RegisterDimensionSpecialEffectsEvent event) {
        event.register(Dimensions.EUCA_EFFECTS, new EucaRenderInfo());
//        event.register(Dimensions.DEPTHS_EFFECTS, new EucaRenderInfo());
//        event.register(Dimensions.BOIL_EFFECTS, new BoilRenderInfo());
//        event.register(Dimensions.FROZEN_EFFECTS, new FrozenRenderInfo());todo
//        event.register(Dimensions.CORBA_EFFECTS, new CorbaRenderInfo());
       // event.register(Dimensions.TERRANIA_EFFECTS, new TerraniaRenderInfo());
//        event.register(Dimensions.CLOUDIA_EFFECTS, new CloudiaRenderInfo());
    }
}