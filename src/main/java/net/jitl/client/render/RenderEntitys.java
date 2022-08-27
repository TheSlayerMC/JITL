package net.jitl.client.render;

import net.jitl.client.render.projectile.RenderEntity2D;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RenderEntitys {

    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(JEntities.CONJURING_PROJECTILE_TYPE, manager -> new RenderEntity2D<>(manager, JITL.rl("textures/entity/projectile/conjuring.png"))
                .fullbright(true)
                .projectile(true)
                .scale(0.5F));
        event.registerEntityRenderer(JEntities.ESSENCIA_PROJECTILE_TYPE, manager -> new RenderEntity2D<>(manager, JITL.rl("textures/entity/projectile/essencia.png"))
                .fullbright(true)
                .projectile(true)
                .scale(0.5F));
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {

    }
}