package net.jitl.client.render;

import net.jitl.client.JModelLayers;
import net.jitl.client.model.AnimatedMonsterModel;
import net.jitl.client.model.JBoatModel;
import net.jitl.client.render.block.GrindstoneRenderer;
import net.jitl.client.render.block.JChestRenderer;
import net.jitl.client.render.block.PedestalRenderer;
import net.jitl.client.render.block.RockiteSpawnerRenderer;
import net.jitl.client.render.entity.euca.RoyalKingRenderer;
import net.jitl.client.render.entity.frozen.FrozenGuardianRenderer;
import net.jitl.client.render.entity.overworld.BoomBoomRenderer;
import net.jitl.client.render.projectile.*;
import net.jitl.client.render.vehicle.JBoatRenderer;
import net.jitl.common.entity.base.JBoat;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlockEntities;
import net.jitl.core.init.internal.JDimension;
import net.jitl.core.init.internal.JEntities;
import net.minecraft.client.Minecraft;
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
        event.registerEntityRenderer(JEntities.FLORO_MUD_TYPE.get(), ThrownItemRenderer::new);

        event.registerEntityRenderer(JEntities.ESSENCIA_BOLT_TYPE.get(), EssenciaBoltRenderer::new);
        event.registerEntityRenderer(JEntities.PIERCER_TYPE.get(), manager -> new PiercerRenderer(manager, Minecraft.getInstance().getItemRenderer()));
        event.registerEntityRenderer(JEntities.KNIFE_TYPE.get(), manager -> new KnifeRenderer(manager, Minecraft.getInstance().getItemRenderer()));

        event.registerEntityRenderer(JEntities.JBOAT_TYPE.get(), JBoatRenderer::new);

        event.registerBlockEntityRenderer(JBlockEntities.GRINDSTONE.get(), GrindstoneRenderer::new);
        event.registerBlockEntityRenderer(JBlockEntities.ROCKITE.get(), RockiteSpawnerRenderer::new);
        event.registerBlockEntityRenderer(JBlockEntities.JCHEST.get(), JChestRenderer::new);
        event.registerBlockEntityRenderer(JBlockEntities.PEDESTAL.get(), PedestalRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        for(JBoat.Type type : JBoat.Type.values()) {
            event.registerLayerDefinition(JModelLayers.createBoatModelName(type), JBoatModel::createBodyModel);
        }

        event.registerLayerDefinition(JModelLayers.JCHEST, JChestRenderer::createSingleBodyLayer);
        event.registerLayerDefinition(JModelLayers.JDOUBLE_CHEST_RIGHT, JChestRenderer::createDoubleBodyRightLayer);
        event.registerLayerDefinition(JModelLayers.JDOUBLE_CHEST_LEFT, JChestRenderer::createDoubleBodyLeftLayer);
    }

    public static void registerAnimationRenderers() {
        EntityRenderers.register(JEntities.MAGE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("mage", JDimension.OVERWORLD), 0.55F, 1.25F));
        EntityRenderers.register(JEntities.FLORO_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("floro", JDimension.OVERWORLD), 0.5F, 1.25F));
        EntityRenderers.register(JEntities.BOOM_TYPE.get(), renderer -> new BoomBoomRenderer<>(renderer, new AnimatedMonsterModel<>("boomboom", JDimension.OVERWORLD)));
        EntityRenderers.register(JEntities.TOWER_GUARDIAN_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("tower_guardian", JDimension.OVERWORLD), 1F, 1.15F));
        EntityRenderers.register(JEntities.ROCKITE_SMASHER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("rockite_smasher", JDimension.OVERWORLD), 1F, 2F));
        EntityRenderers.register(JEntities.WITHERSPINE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("witherspine", JDimension.NETHER), 0.6F));
        EntityRenderers.register(JEntities.BROWN_HONGO_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("brown_hongo", JDimension.OVERWORLD), 0.6F));
        EntityRenderers.register(JEntities.ILLAGER_MECH_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("illager_mech", JDimension.OVERWORLD), 1F, 1.5F));

        EntityRenderers.register(JEntities.EUCA_CHARGER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("euca_charger", JDimension.EUCA), 0.5F));
        EntityRenderers.register(JEntities.DYNASTER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("dynaster", JDimension.EUCA), 0.8F));
        EntityRenderers.register(JEntities.GOLDBOT_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("goldbot", JDimension.EUCA), 0.8F));
        EntityRenderers.register(JEntities.SHIMMERER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("shimmerer", JDimension.EUCA), 0.8F));
        EntityRenderers.register(JEntities.GOLDER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("golder", JDimension.EUCA), 0.8F, 2.0F));
        EntityRenderers.register(JEntities.ROYAL_KING.get(), renderer -> new RoyalKingRenderer<>(renderer, new AnimatedMonsterModel<>("royal_king", JDimension.EUCA)));

        EntityRenderers.register(JEntities.ESKIMO_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("eskimo", JDimension.FROZEN), 0.5F));
        EntityRenderers.register(JEntities.FROZEN_GUARDIAN_TYPE.get(), renderer -> new FrozenGuardianRenderer<>(renderer, new AnimatedMonsterModel<>("frozen_guardian", JDimension.FROZEN)));
    }
}