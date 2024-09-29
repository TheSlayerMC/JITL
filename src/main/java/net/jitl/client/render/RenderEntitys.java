package net.jitl.client.render;

import net.jitl.client.JModelLayers;
import net.jitl.client.model.AnimatedMonsterModel;
import net.jitl.client.model.FrozenTrollModel;
import net.jitl.client.model.JBoatModel;
import net.jitl.client.render.block.*;
import net.jitl.client.render.entity.RenderAnimated2D;
import net.jitl.client.render.entity.euca.CrypianRenderer;
import net.jitl.client.render.entity.euca.EucaHopperRenderer;
import net.jitl.client.render.entity.euca.RoyalKingRenderer;
import net.jitl.client.render.entity.frozen.FrozenGuardianRenderer;
import net.jitl.client.render.entity.frozen.FrozenTrollRenderer;
import net.jitl.client.render.entity.misc.BossCrystalRenderer;
import net.jitl.client.render.entity.misc.SentacoinRender;
import net.jitl.client.render.entity.misc.SpiritCrystalRenderer;
import net.jitl.client.render.entity.nether.MiniGhastRenderer;
import net.jitl.client.render.entity.overworld.BoomBoomRenderer;
import net.jitl.client.render.projectile.*;
import net.jitl.client.render.vehicle.JBoatRenderer;
import net.jitl.common.entity.base.JBoat;
import net.jitl.common.entity.misc.Sentacoin;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlockEntities;
import net.jitl.core.init.internal.JDimension;
import net.jitl.core.init.internal.JEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class RenderEntitys {

    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(JEntities.CONJURING_PROJECTILE_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/conjuring.png")));
        event.registerEntityRenderer(JEntities.ESSENCIA_PROJECTILE_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/essencia.png")));
        event.registerEntityRenderer(JEntities.FIREBALL_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/fireball.png")));
        event.registerEntityRenderer(JEntities.FLORO_MUD_TYPE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(JEntities.DEMONIC_BOMB_TYPE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(JEntities.FIRE_BOMB_TYPE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(JEntities.MAGIC_BOMB_TYPE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(JEntities.ESSENCE_ARROW_TYPE.get(), EssenceArrowRenderer::new);
        event.registerEntityRenderer(JEntities.MAGIC_POT_TYPE.get(), ThrownItemRenderer::new);

        event.registerEntityRenderer(JEntities.ESSENCIA_BOLT_TYPE.get(), EssenciaBoltRenderer::new);

        event.registerEntityRenderer(JEntities.BOSS_CRYSTAL_TYPE.get(), BossCrystalRenderer::new);
        event.registerEntityRenderer(JEntities.SPIRIT_CRYSTAL_TYPE.get(), SpiritCrystalRenderer::new);

        event.registerEntityRenderer(JEntities.PIERCER_TYPE.get(), manager -> new PiercerRenderer(manager, Minecraft.getInstance().getItemRenderer()));
        event.registerEntityRenderer(JEntities.KNIFE_TYPE.get(), manager -> new KnifeRenderer(manager, Minecraft.getInstance().getItemRenderer()));

        event.registerEntityRenderer(JEntities.JBOAT_TYPE.get(), JBoatRenderer::new);
        event.registerEntityRenderer(JEntities.SENTACOIN_TYPE.get(), (context) -> new SentacoinRender(context, Sentacoin.Type.COIN));
        event.registerEntityRenderer(JEntities.SENTACOIN_BAG_TYPE.get(), (context) -> new SentacoinRender(context, Sentacoin.Type.BAG));

        event.registerEntityRenderer(JEntities.FROZEN_TROLL_TYPE.get(), FrozenTrollRenderer::new);
        event.registerEntityRenderer(JEntities.SWAMP_FLY_TYPE.get(), manager -> new RenderAnimated2D<>(manager, 10,
                "corba/swamp_fly_0",
                "corba/swamp_fly_1",
                "corba/swamp_fly_2",
                "corba/swamp_fly_3"));

        event.registerBlockEntityRenderer(JBlockEntities.ROCKITE.get(), RockiteSpawnerRenderer::new);
        event.registerBlockEntityRenderer(JBlockEntities.OBELISK.get(), con -> new ObeliskRenderer());
        event.registerBlockEntityRenderer(JBlockEntities.SENTERIAN_ALTAR.get(), con -> new SenterianAltarRenderer());
        event.registerBlockEntityRenderer(JBlockEntities.JCHEST.get(), JChestRenderer::new);
        event.registerBlockEntityRenderer(JBlockEntities.PEDESTAL.get(), PedestalRenderer::new);
        event.registerBlockEntityRenderer(JBlockEntities.GOLD_BOT_SPAWNER.get(), JSpawnerRenderer::new);
        event.registerBlockEntityRenderer(JBlockEntities.MINI_GHAST_SPAWNER.get(), JSpawnerRenderer::new);
        event.registerBlockEntityRenderer(JBlockEntities.DARK_SORCERER_SPAWNER.get(), JSpawnerRenderer::new);
        event.registerBlockEntityRenderer(JBlockEntities.SCREAMER_SPAWNER.get(), JSpawnerRenderer::new);
        event.registerBlockEntityRenderer(JBlockEntities.OBSERVER_SPAWNER.get(), JSpawnerRenderer::new);
        event.registerBlockEntityRenderer(JBlockEntities.HELLWING_SPAWNER.get(), JSpawnerRenderer::new);
        event.registerBlockEntityRenderer(JBlockEntities.HELLBOT_SPAWNER.get(), JSpawnerRenderer::new);
        event.registerBlockEntityRenderer(JBlockEntities.FROSTBITER_SPAWNER.get(), JSpawnerRenderer::new);
        event.registerBlockEntityRenderer(JBlockEntities.OVERSEER_SPAWNER.get(), JSpawnerRenderer::new);
        event.registerBlockEntityRenderer(JBlockEntities.OVERSEER_ELDER_SPAWNER.get(), JSpawnerRenderer::new);

        event.registerBlockEntityRenderer(JBlockEntities.SUMMONING_TABLE.get(), SummoningTableRenderer::new);
        event.registerBlockEntityRenderer(JBlockEntities.BITTERWOOD_CAMPFIRE.get(), BitterwoodCampfireRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        for(JBoat.Type type : JBoat.Type.values()) {
            event.registerLayerDefinition(JModelLayers.createBoatModelName(type), JBoatModel::createBodyModel);
        }

        event.registerLayerDefinition(JModelLayers.FROZEN_TROLL_MODEL_LAYER, FrozenTrollModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.FROZEN_TROLL_HELD_ITEM_LAYER, FrozenTrollModel::createBodyLayer);

        event.registerLayerDefinition(JModelLayers.JCHEST, JChestRenderer::createSingleBodyLayer);
        event.registerLayerDefinition(JModelLayers.JDOUBLE_CHEST_RIGHT, JChestRenderer::createDoubleBodyRightLayer);
        event.registerLayerDefinition(JModelLayers.JDOUBLE_CHEST_LEFT, JChestRenderer::createDoubleBodyLeftLayer);
    }

    public static void registerAnimationRenderers() {
        EntityRenderers.register(JEntities.MAGE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("mage", JDimension.OVERWORLD), 0.55F, 1.25F));
        EntityRenderers.register(JEntities.FLORO_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("floro", JDimension.OVERWORLD), 0.5F, 1.25F));
        EntityRenderers.register(JEntities.BOOM_TYPE.get(), renderer -> new BoomBoomRenderer<>(renderer, new AnimatedMonsterModel<>("boomboom", JDimension.OVERWORLD)));
        EntityRenderers.register(JEntities.BROWN_HONGO_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("brown_hongo", JDimension.OVERWORLD), 0.6F));
        EntityRenderers.register(JEntities.ILLAGER_MECH_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("illager_mech", JDimension.OVERWORLD), 1F, 1.5F));
        EntityRenderers.register(JEntities.SPYCLOPSE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("spyclopse", JDimension.OVERWORLD), 0.6F));
        EntityRenderers.register(JEntities.BLIZZARD_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("blizzard", JDimension.OVERWORLD), 0.5F));
        EntityRenderers.register(JEntities.BIG_HONGO_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("big_hongo", JDimension.OVERWORLD), 0.6F));
        EntityRenderers.register(JEntities.MEDIUM_HONGO_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("medium_hongo", JDimension.OVERWORLD), 0.6F));
        EntityRenderers.register(JEntities.SMALL_HONGO_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("small_hongo", JDimension.OVERWORLD), 0.6F));
        EntityRenderers.register(JEntities.JUNGLE_TURTLE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("jungle_turtle", JDimension.OVERWORLD), 1.0F));
        EntityRenderers.register(JEntities.JUNGLE_GOLEM_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("jungle_golem", JDimension.OVERWORLD), 0.9F));
        EntityRenderers.register(JEntities.SAND_CRAWLER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("sand_crawler", JDimension.OVERWORLD), 0.5F));
        EntityRenderers.register(JEntities.ROCKITE_GOLEM_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("rockite_golem", JDimension.OVERWORLD), 0.5F));
        EntityRenderers.register(JEntities.STONEWALKER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("stonewalker", JDimension.OVERWORLD), 0.5F));
        EntityRenderers.register(JEntities.CAVELING_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("caveling", JDimension.OVERWORLD), 0.5F));
        EntityRenderers.register(JEntities.CAVURN_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("cavurn", JDimension.OVERWORLD), 0.5F));
        EntityRenderers.register(JEntities.NEUTRAL_SENTRY_STALKER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("neutral_sentry_stalker", JDimension.OVERWORLD), 0.25F, 0.7F));

        EntityRenderers.register(JEntities.TOWER_GUARDIAN_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("tower_guardian", JDimension.BOSS), 1F, 1.15F));
        EntityRenderers.register(JEntities.ROCKITE_SMASHER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("rockite_smasher", JDimension.BOSS), 1F, 2F));
        EntityRenderers.register(JEntities.FROST_GOLEM_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("frost_golem", JDimension.BOSS), 1F, 2F));
        EntityRenderers.register(JEntities.OKOLOO_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("okoloo", JDimension.BOSS), 0.8F, 1.5F));
        EntityRenderers.register(JEntities.BLAZIER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("blazier", JDimension.BOSS), 0.6F, 2.5F));
        EntityRenderers.register(JEntities.CALCIA_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("calcia", JDimension.BOSS), 0.6F, 1.2F));
        EntityRenderers.register(JEntities.SOUL_WATCHER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("soul_watcher", JDimension.BOSS), 0.6F, 1.5F));
        EntityRenderers.register(JEntities.WITHERING_BEAST_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("withering_beast", JDimension.BOSS), 0.6F, 1.2F));
        EntityRenderers.register(JEntities.EUDOR_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("eudor", JDimension.BOSS), 0.6F, 1.7F));
        EntityRenderers.register(JEntities.CORALLATOR_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("corallator", JDimension.BOSS), 0.6F, 2.5F));
        EntityRenderers.register(JEntities.THUNDER_BIRD_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("thunder_bird", JDimension.BOSS), 0.6F, 3F));
        EntityRenderers.register(JEntities.SCALE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("scale", JDimension.BOSS), 0.6F, 1.5F));
        EntityRenderers.register(JEntities.LOGGER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("logger", JDimension.BOSS), 0.6F, 2F));
        EntityRenderers.register(JEntities.TERRANIAN_PROTECTOR_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("terranian_protector", JDimension.BOSS), 0.6F, 1F));
        EntityRenderers.register(JEntities.SENTRY_KING_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("sentry_king", JDimension.BOSS), 0.6F, 1.2F));
        EntityRenderers.register(JEntities.SKY_STALKER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("sky_stalker", JDimension.BOSS), 0.6F, 1.5F));

        EntityRenderers.register(JEntities.WITHERSPINE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("witherspine", JDimension.NETHER), 0.6F));
        EntityRenderers.register(JEntities.MINI_GHAST_TYPE.get(), renderer -> new MiniGhastRenderer<>(renderer, new AnimatedMonsterModel<>("mini_ghast", JDimension.NETHER)));
        EntityRenderers.register(JEntities.HELL_TURTLE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("hell_turtle", JDimension.NETHER), 1.0F, 1.25F));
        EntityRenderers.register(JEntities.REAPER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("reaper", JDimension.NETHER), 0.5F));
        EntityRenderers.register(JEntities.INFERNO_BLAZE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("inferno_blaze", JDimension.NETHER), 0.5F));
        EntityRenderers.register(JEntities.HELL_COW_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("hell_cow", JDimension.NETHER), 0.7F));
        EntityRenderers.register(JEntities.HELLBOT_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("hellbot", JDimension.NETHER), 0.8F));
        EntityRenderers.register(JEntities.HELL_SERPENT_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("hell_serpent", JDimension.NETHER), 0.25F));

        EntityRenderers.register(JEntities.FLAME_LOTUS_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("flame_lotus", JDimension.BOIL), 0.5F));
        EntityRenderers.register(JEntities.BURNING_LIGHT_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("burning_light", JDimension.BOIL), 0.5F));
        EntityRenderers.register(JEntities.FRIGHTENER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("frightener", JDimension.BOIL), 0.5F, 1.25F));
        EntityRenderers.register(JEntities.HELLWING_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("hellwing", JDimension.BOIL), 0.5F));
        EntityRenderers.register(JEntities.MAGMA_BLAZE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("magma_blaze", JDimension.BOIL), 0.5F, 1.25F));
        EntityRenderers.register(JEntities.OBSERVER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("observer", JDimension.BOIL), 0.5F, 1.25F));
        EntityRenderers.register(JEntities.SCREAMER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("screamer", JDimension.BOIL), 0.5F));
        EntityRenderers.register(JEntities.BOIL_TRADER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("boil_trader", JDimension.BOIL), 0.5F));
        EntityRenderers.register(JEntities.ESCAPED_CONVICT_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("escaped_convict", JDimension.BOIL), 0.5F, 1.5F));

        EntityRenderers.register(JEntities.EUCA_CHARGER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("euca_charger", JDimension.EUCA), 0.5F));
        EntityRenderers.register(JEntities.DYNASTER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("dynaster", JDimension.EUCA), 0.8F));
        EntityRenderers.register(JEntities.GOLDBOT_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("goldbot", JDimension.EUCA), 0.8F));
        EntityRenderers.register(JEntities.SHIMMERER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("shimmerer", JDimension.EUCA), 0.8F));
        EntityRenderers.register(JEntities.GOLDER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("golder", JDimension.EUCA), 0.8F, 2.0F));
        EntityRenderers.register(JEntities.ROYAL_KING_TYPE.get(), renderer -> new RoyalKingRenderer<>(renderer, new AnimatedMonsterModel<>("royal_king", JDimension.EUCA)));
        EntityRenderers.register(JEntities.CRYPIAN_TYPE.get(), renderer -> new CrypianRenderer<>(renderer, new AnimatedMonsterModel<>("crypian", JDimension.EUCA)));
        EntityRenderers.register(JEntities.ALLOY_MENDER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("alloy_mender", JDimension.EUCA)));
        EntityRenderers.register(JEntities.EUCA_HOPPER.get(), renderer -> new EucaHopperRenderer<>(renderer, new AnimatedMonsterModel<>("euca_hopper", JDimension.EUCA)));

        EntityRenderers.register(JEntities.ESKIMO_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("eskimo", JDimension.FROZEN), 0.5F));
        EntityRenderers.register(JEntities.FROZEN_GUARDIAN_TYPE.get(), renderer -> new FrozenGuardianRenderer<>(renderer, new AnimatedMonsterModel<>("frozen_guardian", JDimension.FROZEN)));
        EntityRenderers.register(JEntities.CRYSTAL_CLUSTER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("crystal_cluster", JDimension.FROZEN)));
        EntityRenderers.register(JEntities.FROZEN_FROSTBITER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("frozen_frostbiter", JDimension.FROZEN)));
        EntityRenderers.register(JEntities.ICE_GOLEM_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("ice_golem", JDimension.FROZEN), 1.5F, 2F));
        EntityRenderers.register(JEntities.PERMAFRAUST_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("permafraust", JDimension.FROZEN)));
        EntityRenderers.register(JEntities.SHATTERER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("shatterer", JDimension.FROZEN)));
        EntityRenderers.register(JEntities.SHIVERING_BUSHWALKER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("shivering_bushwalker", JDimension.FROZEN)));
        EntityRenderers.register(JEntities.SHIVERING_SHRIEKER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("shivering_shrieker", JDimension.FROZEN)));
        EntityRenderers.register(JEntities.CAPYBARA_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("capybara", JDimension.FROZEN)));

        EntityRenderers.register(JEntities.DARKENER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("darkener", JDimension.DEPTHS)));
        EntityRenderers.register(JEntities.DARKNESS_CRAWLER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("darkness_crawler", JDimension.DEPTHS)));
        EntityRenderers.register(JEntities.DARK_SORCERER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("dark_sorcerer", JDimension.DEPTHS)));
        EntityRenderers.register(JEntities.DEPTHS_BEAST_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("depths_beast", JDimension.DEPTHS), 0.5F, 2.0F));
        EntityRenderers.register(JEntities.ROC_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("roc", JDimension.DEPTHS)));
        EntityRenderers.register(JEntities.SPIKED_BEAST_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("spiked_beast", JDimension.DEPTHS)));
        EntityRenderers.register(JEntities.DEPTHS_HUNTER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("depths_hunter", JDimension.DEPTHS), 0.5F, 1.5F));
        EntityRenderers.register(JEntities.STARING_GUARDIAN_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("staring_guardian", JDimension.DEPTHS)));
        EntityRenderers.register(JEntities.AURON_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("auron", JDimension.DEPTHS), 0.35F, 1.5F));

        EntityRenderers.register(JEntities.CORBANIAN_MOLLUSK_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("corbanian_mollusk", JDimension.CORBA), 0.35F, 2.5F));
        EntityRenderers.register(JEntities.SMELLY_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("smelly", JDimension.CORBA)));
        EntityRenderers.register(JEntities.STINKY_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("stinky", JDimension.CORBA)));
        EntityRenderers.register(JEntities.RED_TORDO_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("red_tordo", JDimension.CORBA)));
        EntityRenderers.register(JEntities.WOOD_CREATURE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("wood_creature", JDimension.CORBA)));
        EntityRenderers.register(JEntities.GREEN_TORDO_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("green_tordo", JDimension.CORBA)));
        EntityRenderers.register(JEntities.TREE_GOLEM_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("tree_golem", JDimension.CORBA)));
        EntityRenderers.register(JEntities.LEAF_BLOWER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("leaf_blower", JDimension.CORBA)));
        EntityRenderers.register(JEntities.OVERSEER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("overseer", JDimension.CORBA)));
        EntityRenderers.register(JEntities.OVERSEER_ELDER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("overseer_elder", JDimension.CORBA)));
        EntityRenderers.register(JEntities.SURFACE_SEER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("surface_seer", JDimension.CORBA)));
        EntityRenderers.register(JEntities.NATURE_MAGE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("nature_mage", JDimension.CORBA)));
        EntityRenderers.register(JEntities.OVERGROWN_MERCHANT_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("overgrown_merchant", JDimension.CORBA)));
        EntityRenderers.register(JEntities.HOODED_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("hooded", JDimension.CORBA)));

        EntityRenderers.register(JEntities.CLOUD_GHOST_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("cloud_ghost", JDimension.CLOUDIA)));
        EntityRenderers.register(JEntities.SKY_EEL_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("sky_eel", JDimension.CLOUDIA)));
        EntityRenderers.register(JEntities.STARLIGHT_BLACKSMITH_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("starlight_blacksmith", JDimension.CLOUDIA)));
        EntityRenderers.register(JEntities.STARLIGHT_VILLAGER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("starlight_villager", JDimension.CLOUDIA)));
        EntityRenderers.register(JEntities.STARLIGHT_GOLEM_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("starlight_golem", JDimension.CLOUDIA)));
        EntityRenderers.register(JEntities.STARLIGHT_TRANSPORTER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("starlight_transporter", JDimension.CLOUDIA)));
        EntityRenderers.register(JEntities.STARLIGHT_WALKER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("starlight_walker", JDimension.CLOUDIA)));
        EntityRenderers.register(JEntities.AERO_LOTUS_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("aero_lotus", JDimension.CLOUDIA)));

        EntityRenderers.register(JEntities.ARANA_KING_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("arana_king", JDimension.TERRAINIA)));
        EntityRenderers.register(JEntities.PURPLIAN_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("purplian", JDimension.TERRAINIA)));
        EntityRenderers.register(JEntities.TERRAGROW_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("terragrow", JDimension.TERRAINIA)));
        EntityRenderers.register(JEntities.TERRASCATTERER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("terrascatterer", JDimension.TERRAINIA)));
        EntityRenderers.register(JEntities.TERRASHROOM_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("terrashroom", JDimension.TERRAINIA)));
        EntityRenderers.register(JEntities.TERRASLUG_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("terraslug", JDimension.TERRAINIA)));
        EntityRenderers.register(JEntities.FLUNGAS_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("flungas", JDimension.TERRAINIA), 0.5F, 2));
        EntityRenderers.register(JEntities.TERRANIAN_ENCHANTER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("terranian_enchanter", JDimension.TERRAINIA)));
        EntityRenderers.register(JEntities.TERRANIAN_TRADER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("terranian_trader", JDimension.TERRAINIA)));

        EntityRenderers.register(JEntities.SENTRY_LORD_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("sentry_lord", JDimension.SENTERIAN)));
        EntityRenderers.register(JEntities.SENTRY_STALKER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("sentry_stalker", JDimension.SENTERIAN)));
        EntityRenderers.register(JEntities.SENTRY_WALKER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("sentry_walker", JDimension.SENTERIAN)));
        EntityRenderers.register(JEntities.MINI_SENTRY_LORD_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("mini_sentry_lord", JDimension.SENTERIAN), 0.25F, 0.4F));
        EntityRenderers.register(JEntities.MINI_SENTRY_STALKER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("mini_sentry_stalker", JDimension.SENTERIAN), 0.25F, 0.4F));
        EntityRenderers.register(JEntities.MINI_SENTRY_WALKER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("mini_sentry_walker", JDimension.SENTERIAN), 0.25F, 0.4F));

    }
}