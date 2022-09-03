package net.jitl.client.render;

import net.jitl.client.model.AnimatedMonsterModel;
import net.jitl.client.render.projectile.*;
import net.jitl.core.helper.JDimension;
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
        event.registerEntityRenderer(JEntities.CONJURING_PROJECTILE_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/conjuring.png")));
        event.registerEntityRenderer(JEntities.ESSENCIA_PROJECTILE_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/essencia.png")));
        event.registerEntityRenderer(JEntities.ESSENCIA_BOLT_TYPE.get(), EssenciaBoltRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) { }

    public static void registerAnimationRenderers() {
        EntityRenderers.register(JEntities.MAGE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("mage", JDimension.OVERWORLD), 0.55F, 1.25F));
        EntityRenderers.register(JEntities.FLORO_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("floro", JDimension.OVERWORLD), 0.5F, 1.25F));
        EntityRenderers.register(JEntities.TOWER_GUARDIAN_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("tower_guardian", JDimension.OVERWORLD), 1F, 1.15F));
        EntityRenderers.register(JEntities.ROCKITE_SMASHER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("rockite_smasher", JDimension.OVERWORLD), 1F, 2F));
        EntityRenderers.register(JEntities.WITHERSPINE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("witherspine", JDimension.NETHER), 0.6F));

    }
}