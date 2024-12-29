package net.jitl.client;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.client.render.item.*;
import net.jitl.client.render.world.*;
import net.jitl.client.util.ClientGetter;
import net.jitl.common.world.dimension.Dimensions;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.FogParameters;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;

@EventBusSubscriber(modid = JITL.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler {

    public static void regToBus(IEventBus forgeBus) {
        forgeBus.addListener(ClientEventHandler::onFogDensityEvent);
    }

    public static void onFogDensityEvent(ViewportEvent.RenderFog event) {
        float farPlaneDistance = event.getFarPlaneDistance();
        Player player = ClientGetter.player();
        if (ClientGetter.level().dimension() == Dimensions.FROZEN_LANDS) {
                float density;
//TODO                ICuriosItemHandler curios = CuriosApi.getCuriosInventory(player).get();
//                if(player.getData(JDataAttachments.PLAYER_STATS).hasBlizzard() || curios.findFirstCurio(JItems.EYE_OF_THE_BLIZZARD.get()).isPresent()) {
//                    density = 0.55F;
//                } else {
//                    density = 0.1F;
//                }
               // RenderSystem.setShaderFog(new FogParameters(density, density * farPlaneDistance, FogShape.SPHERE, 1F, 1F, 1F, 1F));
        }

        if(ClientGetter.level().dimension() == Dimensions.CLOUDIA) {
            float density = 0.35F;
            RenderSystem.setShaderFog(new FogParameters(density, density * farPlaneDistance, FogShape.SPHERE, 1F, 1F, 1F, 1F));

        }

        if(ClientGetter.level().dimension() == Dimensions.DEPTHS) {
            float density = 0.75F;
            RenderSystem.setShaderFog(new FogParameters(density, density * farPlaneDistance, FogShape.SPHERE, 1F, 1F, 1F, 1F));
        }
    }

    @SubscribeEvent
    public static void registerSpecialModelRenderer(RegisterSpecialModelRendererEvent event) {
        event.register(JITL.rl("bloodcrust_shield"), BloodcrustShieldRenderer.Unbaked.MAP_CODEC);
        event.register(JITL.rl("celestium_shield"), CelestiumShieldRenderer.Unbaked.MAP_CODEC);
        event.register(JITL.rl("des_shield"), DesShieldRenderer.Unbaked.MAP_CODEC);
        event.register(JITL.rl("flairium_shield"), FlairiumShieldRenderer.Unbaked.MAP_CODEC);
        event.register(JITL.rl("gorbite_shield"), GorbiteShieldRenderer.Unbaked.MAP_CODEC);
        event.register(JITL.rl("korite_shield"), KoriteShieldRenderer.Unbaked.MAP_CODEC);
        event.register(JITL.rl("lunium_shield"), LuniumShieldRenderer.Unbaked.MAP_CODEC);
        event.register(JITL.rl("mekyum_shield"), MekyumShieldRenderer.Unbaked.MAP_CODEC);
        event.register(JITL.rl("orbadite_shield"), OrbaditeShieldRenderer.Unbaked.MAP_CODEC);
        event.register(JITL.rl("sapphire_shield"), SapphireShieldRenderer.Unbaked.MAP_CODEC);
        event.register(JITL.rl("shadium_shield"), ShadiumShieldRenderer.Unbaked.MAP_CODEC);
        event.register(JITL.rl("soulstone_shield"), SoulstoneShieldRenderer.Unbaked.MAP_CODEC);
        event.register(JITL.rl("storon_shield"), StoronShieldRenderer.Unbaked.MAP_CODEC);
    }

    @SubscribeEvent
    public static void registerDimensionSpecialEffects(RegisterDimensionSpecialEffectsEvent event) {
        event.register(Dimensions.EUCA_EFFECTS, new EucaRenderInfo());
        event.register(Dimensions.DEPTHS_EFFECTS, new EucaRenderInfo());
        event.register(Dimensions.BOIL_EFFECTS, new BoilRenderInfo());
        event.register(Dimensions.FROZEN_EFFECTS, new FrozenRenderInfo());
        event.register(Dimensions.CORBA_EFFECTS, new CorbaRenderInfo());
        event.register(Dimensions.TERRANIA_EFFECTS, new TerraniaRenderInfo());
        event.register(Dimensions.CLOUDIA_EFFECTS, new CloudiaRenderInfo());
    }
}