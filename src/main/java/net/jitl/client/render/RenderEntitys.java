package net.jitl.client.render;

import net.jitl.client.render.overworld.FloroRenderer;
import net.jitl.client.render.overworld.MageRenderer;
import net.jitl.client.render.projectile.EssenciaBoltRenderer;
import net.jitl.client.render.projectile.RenderProjectile;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JEntities;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RenderEntitys {

    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(JEntities.CONJURING_PROJECTILE_TYPE.get(), manager -> new RenderProjectile(manager, JITL.rl("textures/entity/projectile/conjuring.png")));
        event.registerEntityRenderer(JEntities.ESSENCIA_PROJECTILE_TYPE.get(), manager -> new RenderProjectile(manager, JITL.rl("textures/entity/projectile/essencia.png")));

        event.registerEntityRenderer(JEntities.ESSENCIA_BOLT_TYPE.get(), EssenciaBoltRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {

    }

    public static void registerAnimationRenderers() {
        EntityRenderers.register(JEntities.MAGE_TYPE.get(), MageRenderer::new);
        EntityRenderers.register(JEntities.FLORO_TYPE.get(), FloroRenderer::new);

    }
}