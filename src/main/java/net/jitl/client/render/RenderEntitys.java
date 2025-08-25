package net.jitl.client.render;

import net.jitl.client.JModelLayers;
import net.jitl.client.model.AnimatedMonsterModel;
import net.jitl.client.model.FrozenTrollModel;
import net.jitl.client.model.JBoatModel;
import net.jitl.client.model.ShiverwolfModel;
import net.jitl.client.render.block.*;
import net.jitl.client.render.entity.frozen.ShiveringRamRenderer;
import net.jitl.client.render.entity.frozen.ShiverwolfRenderer;
import net.jitl.client.render.entity.misc.RenderAnimated2D;
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
import net.jitl.client.render.item.*;
import net.jitl.client.render.projectile.*;
import net.jitl.client.render.vehicle.JBoatRenderer;
import net.jitl.common.entity.base.JBoat;
import net.jitl.common.entity.misc.Sentacoin;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlockEntities;
import net.jitl.core.init.internal.JDimension;
import net.jitl.core.init.internal.JEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;
import org.checkerframework.checker.units.qual.A;

import java.util.function.BiConsumer;

@EventBusSubscriber(modid = JITL.MOD_ID, value = Dist.CLIENT)
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
        event.registerEntityRenderer(JEntities.ICEBALL_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/iceball.png")));
        event.registerEntityRenderer(JEntities.HELLSTONE_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/hellstone.png")));
        event.registerEntityRenderer(JEntities.WIZARDS_STAR_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/wizards_star.png")));
        event.registerEntityRenderer(JEntities.DOOMSBRINGER_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/doomsbringer.png")));
        event.registerEntityRenderer(JEntities.GREENPACE_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/greenpace.png")));
        event.registerEntityRenderer(JEntities.OVERGROWN_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/overgrown.png")));
        event.registerEntityRenderer(JEntities.BOUNCING_OVERGROWN_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/overgrown.png")));
        event.registerEntityRenderer(JEntities.DIVINITY_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/divinity.png")));
        event.registerEntityRenderer(JEntities.BOUNCING_DIVINITY_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/divinity.png")));
        event.registerEntityRenderer(JEntities.ENLIGHTENMENT_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/enlightenment.png")));
        event.registerEntityRenderer(JEntities.CHAOS_CANNON.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/bouncing.png")));
        event.registerEntityRenderer(JEntities.NETHER_PLASMA_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/nether_plasma.png")));
        event.registerEntityRenderer(JEntities.OCEAN_PLASMA_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/ocean_plasma.png")));
        event.registerEntityRenderer(JEntities.EYE_BLASTER_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/eye_blaster.png")));
        event.registerEntityRenderer(JEntities.SPELLBINDING_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/homing.png")));
        event.registerEntityRenderer(JEntities.EARTHEN_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/greenpace.png")));
        event.registerEntityRenderer(JEntities.FLAMING_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/hellstone.png")));
        event.registerEntityRenderer(JEntities.NETHIC_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/nether_plasma.png")));
        event.registerEntityRenderer(JEntities.OVERGROWN_HAMMER_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/overgrown.png")));
        event.registerEntityRenderer(JEntities.ROCKY_HAMMER_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/divinity.png")));
        event.registerEntityRenderer(JEntities.ROYAL_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/wizards_star.png")));
        event.registerEntityRenderer(JEntities.CRYSTALLIZED_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/iceball.png")));
        event.registerEntityRenderer(JEntities.WITHIC_TYPE.get(), manager -> new RenderProjectile<>(manager, JITL.rl("textures/entity/projectile/homing.png")));

        event.registerEntityRenderer(JEntities.ESSENCIA_BOLT_TYPE.get(), EssenciaBoltRenderer::new);

        event.registerEntityRenderer(JEntities.BOSS_CRYSTAL_TYPE.get(), BossCrystalRenderer::new);
        event.registerEntityRenderer(JEntities.SPIRIT_CRYSTAL_TYPE.get(), SpiritCrystalRenderer::new);

        event.registerEntityRenderer(JEntities.PIERCER_TYPE.get(), manager -> new PiercerRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
        event.registerEntityRenderer(JEntities.KNIFE_TYPE.get(), manager -> new KnifeRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));

        event.registerEntityRenderer(JEntities.BROWN_EUCA_BOAT_TYPE.get(), manager -> new JBoatRenderer(manager, JModelLayers.BROWN_EUCA_BOAT, "brown_euca"));
        event.registerEntityRenderer(JEntities.GOLD_EUCA_BOAT_TYPE.get(), manager -> new JBoatRenderer(manager, JModelLayers.GOLD_EUCA_BOAT, "gold_euca"));
        event.registerEntityRenderer(JEntities.CLOUDIA_BOAT_TYPE.get(), manager -> new JBoatRenderer(manager, JModelLayers.CLOUDIA_BOAT, "cloudia"));
        event.registerEntityRenderer(JEntities.CORBA_BOAT_TYPE.get(), manager -> new JBoatRenderer(manager, JModelLayers.CORBA_BOAT, "corba"));
        event.registerEntityRenderer(JEntities.DEPTHS_BOAT_TYPE.get(), manager -> new JBoatRenderer(manager, JModelLayers.DEPTHS_BOAT, "depths"));
        event.registerEntityRenderer(JEntities.FROZEN_BOAT_TYPE.get(), manager -> new JBoatRenderer(manager, JModelLayers.FROZEN_BOAT, "frozen"));
        event.registerEntityRenderer(JEntities.BURNED_BOAT_TYPE.get(), manager -> new JBoatRenderer(manager, JModelLayers.BURNED_BOAT, "burned"));
        event.registerEntityRenderer(JEntities.TERRANIA_BOAT_TYPE.get(), manager -> new JBoatRenderer(manager, JModelLayers.TERRANIA_BOAT, "terranian"));
        event.registerEntityRenderer(JEntities.SENTACOIN_TYPE.get(), (context) -> new SentacoinRender<>(context, Sentacoin.Type.COIN));
        event.registerEntityRenderer(JEntities.SENTACOIN_BAG_TYPE.get(), (context) -> new SentacoinRender<>(context, Sentacoin.Type.BAG));

        event.registerEntityRenderer(JEntities.FROZEN_TROLL_TYPE.get(), FrozenTrollRenderer::new);
        event.registerEntityRenderer(JEntities.SHIVERWOLF_TYPE.get(), ShiverwolfRenderer::new);

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
        
        registerAnimationRenderers(event::registerEntityRenderer);
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        for(JBoat.Type type : JBoat.Type.values()) {
            event.registerLayerDefinition(JModelLayers.createBoatModelName(type), JBoatModel::createBodyModel);
        }

        event.registerLayerDefinition(JModelLayers.FROZEN_TROLL_MODEL_LAYER, FrozenTrollModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.SHIVERWOLF_MODEL_LAYER, () -> LayerDefinition.create(ShiverwolfModel.createMeshDefinition(CubeDeformation.NONE), 64, 32));
        event.registerLayerDefinition(JModelLayers.SHIVERWOLF_BABY_MODEL_LAYER, () -> LayerDefinition.create(ShiverwolfModel.createMeshDefinition(CubeDeformation.NONE), 64, 32));
        event.registerLayerDefinition(JModelLayers.SHIVERWOLF_ARMOR_LAYER, () -> LayerDefinition.create(ShiverwolfModel.createMeshDefinition(new CubeDeformation(0.2F)), 64, 32));

        event.registerLayerDefinition(JModelLayers.JCHEST, JChestRenderer::createSingleBodyLayer);
        event.registerLayerDefinition(JModelLayers.JDOUBLE_CHEST_RIGHT, JChestRenderer::createDoubleBodyRightLayer);
        event.registerLayerDefinition(JModelLayers.JDOUBLE_CHEST_LEFT, JChestRenderer::createDoubleBodyLeftLayer);

        event.registerLayerDefinition(JModelLayers.SHIVERWOLF_MODEL_LAYER, () -> LayerDefinition.create(ShiverwolfModel.createMeshDefinition(CubeDeformation.NONE), 64, 32));

        event.registerLayerDefinition(JModelLayers.GOLD_EUCA_BOAT, BoatModel::createBoatModel);
        event.registerLayerDefinition(JModelLayers.BROWN_EUCA_BOAT, BoatModel::createBoatModel);
        event.registerLayerDefinition(JModelLayers.CLOUDIA_BOAT, BoatModel::createBoatModel);
        event.registerLayerDefinition(JModelLayers.CORBA_BOAT, BoatModel::createBoatModel);
        event.registerLayerDefinition(JModelLayers.DEPTHS_BOAT, BoatModel::createBoatModel);
        event.registerLayerDefinition(JModelLayers.FROZEN_BOAT, BoatModel::createBoatModel);
        event.registerLayerDefinition(JModelLayers.TERRANIA_BOAT, BoatModel::createBoatModel);
        event.registerLayerDefinition(JModelLayers.BURNED_BOAT, BoatModel::createBoatModel);
    }

    @SubscribeEvent
    public static void registerSpecialRenderers(RegisterSpecialModelRendererEvent event) {
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

    public static void registerAnimationRenderers(BiConsumer<EntityType<? extends Entity>, EntityRendererProvider> entityRenderers) {
        entityRenderers.accept(JEntities.MAGE_TYPE.get(), renderer -> new AnimatedVillagerRenderer<>(renderer, new AnimatedMonsterModel<>("mage", JDimension.OVERWORLD), 0.55F, 1.25F));
        entityRenderers.accept(JEntities.BLACKSMITH_TYPE.get(), renderer -> new AnimatedVillagerRenderer<>(renderer, new AnimatedMonsterModel<>("blacksmith", JDimension.OVERWORLD), 0.5F, 1F));
        entityRenderers.accept(JEntities.GUNSMITH_TYPE.get(), renderer -> new AnimatedVillagerRenderer<>(renderer, new AnimatedMonsterModel<>("gunsmith", JDimension.OVERWORLD), 0.5F, 1F));
        entityRenderers.accept(JEntities.FLORO_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("floro", JDimension.OVERWORLD), 0.5F, 1.25F));
        entityRenderers.accept(JEntities.BOOM_TYPE.get(), renderer -> new BoomBoomRenderer<>(renderer, new AnimatedMonsterModel<>("boomboom", JDimension.OVERWORLD)));
        entityRenderers.accept(JEntities.BROWN_HONGO_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("brown_hongo", JDimension.OVERWORLD), 0.6F));
        entityRenderers.accept(JEntities.ILLAGER_MECH_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("illager_mech", JDimension.OVERWORLD), 1F, 1.5F));
        entityRenderers.accept(JEntities.SPYCLOPSE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("spyclopse", JDimension.OVERWORLD), 0.6F));
        entityRenderers.accept(JEntities.BLIZZARD_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("blizzard", JDimension.OVERWORLD), 0.5F));
        entityRenderers.accept(JEntities.BIG_HONGO_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("big_hongo", JDimension.OVERWORLD), 0.6F));
        entityRenderers.accept(JEntities.MEDIUM_HONGO_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("medium_hongo", JDimension.OVERWORLD), 0.6F));
        entityRenderers.accept(JEntities.SMALL_HONGO_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("small_hongo", JDimension.OVERWORLD), 0.6F));
        entityRenderers.accept(JEntities.JUNGLE_TURTLE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("jungle_turtle", JDimension.OVERWORLD), 1.0F));
        entityRenderers.accept(JEntities.JUNGLE_GOLEM_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("jungle_golem", JDimension.OVERWORLD), 0.9F));
        entityRenderers.accept(JEntities.SAND_CRAWLER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("sand_crawler", JDimension.OVERWORLD), 0.5F));
        entityRenderers.accept(JEntities.ROCKITE_GOLEM_TYPE.get(), renderer -> new AnimatedVillagerRenderer<>(renderer, new AnimatedMonsterModel<>("rockite_golem", JDimension.OVERWORLD), 0.5F));
        entityRenderers.accept(JEntities.STONEWALKER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("stonewalker", JDimension.OVERWORLD), 0.5F));
        entityRenderers.accept(JEntities.CAVELING_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("caveling", JDimension.OVERWORLD), 0.5F));
        entityRenderers.accept(JEntities.CAVURN_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("cavurn", JDimension.OVERWORLD), 0.5F));
        entityRenderers.accept(JEntities.NEUTRAL_SENTRY_STALKER_TYPE.get(), renderer -> new AnimatedPathfinderRenderer<>(renderer, new AnimatedMonsterModel<>("neutral_sentry_stalker", JDimension.OVERWORLD), 0.25F, 0.7F));
        entityRenderers.accept(JEntities.ROBOT_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("robot", JDimension.OVERWORLD)));
        entityRenderers.accept(JEntities.PET_ROBOT_TYPE.get(), renderer -> new AnimatedTamableRenderer<>(renderer, new AnimatedMonsterModel<>("pet_robot", JDimension.OVERWORLD), 0.25F, 0.5F));
        entityRenderers.accept(JEntities.FERRET_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("ferret", JDimension.OVERWORLD), 0.25F, 1.0F));

        entityRenderers.accept(JEntities.TOWER_GUARDIAN_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("tower_guardian", JDimension.BOSS), 1F, 1.15F));
        entityRenderers.accept(JEntities.ROCKITE_SMASHER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("rockite_smasher", JDimension.BOSS), 1F, 2F));
        entityRenderers.accept(JEntities.FROST_GOLEM_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("frost_golem", JDimension.BOSS), 1F, 2F));
        entityRenderers.accept(JEntities.OKOLOO_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("okoloo", JDimension.BOSS), 0.8F, 1.5F));
        entityRenderers.accept(JEntities.BLAZIER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("blazier", JDimension.BOSS), 0.6F, 2.5F));
        entityRenderers.accept(JEntities.CALCIA_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("calcia", JDimension.BOSS), 0.6F, 1.2F));
        entityRenderers.accept(JEntities.SOUL_WATCHER_TYPE.get(), renderer -> new AnimatedFlyingRenderer<>(renderer, new AnimatedMonsterModel<>("soul_watcher", JDimension.BOSS), 0.6F, 1.5F));
        entityRenderers.accept(JEntities.WITHERING_BEAST_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("withering_beast", JDimension.BOSS), 0.6F, 1.2F));
        entityRenderers.accept(JEntities.EUDOR_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("eudor", JDimension.BOSS), 0.6F, 1.7F));
        entityRenderers.accept(JEntities.CORALLATOR_TYPE.get(), renderer -> new AnimatedFlyingRenderer<>(renderer, new AnimatedMonsterModel<>("corallator", JDimension.BOSS), 0.6F, 2.5F));
        entityRenderers.accept(JEntities.THUNDER_BIRD_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("thunder_bird", JDimension.BOSS), 0.6F, 3F));
        entityRenderers.accept(JEntities.SCALE_TYPE.get(), renderer -> new AnimatedFlyingRenderer<>(renderer, new AnimatedMonsterModel<>("scale", JDimension.BOSS), 0.6F, 1.5F));
        entityRenderers.accept(JEntities.LOGGER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("logger", JDimension.BOSS), 0.6F, 2F));
        entityRenderers.accept(JEntities.TERRANIAN_PROTECTOR_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("terranian_protector", JDimension.BOSS), 0.6F, 1F));
        entityRenderers.accept(JEntities.SENTRY_KING_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("sentry_king", JDimension.BOSS), 0.6F, 1.2F));
        entityRenderers.accept(JEntities.SKY_STALKER_TYPE.get(), renderer -> new AnimatedFlyingRenderer<>(renderer, new AnimatedMonsterModel<>("sky_stalker", JDimension.BOSS), 0.6F, 1.5F));

        entityRenderers.accept(JEntities.WITHERSPINE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("witherspine", JDimension.NETHER), 0.6F));
        entityRenderers.accept(JEntities.MINI_GHAST_TYPE.get(), renderer -> new MiniGhastRenderer<>(renderer, new AnimatedMonsterModel<>("mini_ghast", JDimension.NETHER)));
        entityRenderers.accept(JEntities.HELL_TURTLE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("hell_turtle", JDimension.NETHER), 1.0F, 1.25F));
        entityRenderers.accept(JEntities.REAPER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("reaper", JDimension.NETHER), 0.5F));
        entityRenderers.accept(JEntities.INFERNO_BLAZE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("inferno_blaze", JDimension.NETHER), 0.5F));
        entityRenderers.accept(JEntities.HELL_COW_TYPE.get(), renderer -> new AnimatedAnimalRenderer< >(renderer, new AnimatedMonsterModel<>("hell_cow", JDimension.NETHER), 0.7F));
        entityRenderers.accept(JEntities.HELLBOT_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("hellbot", JDimension.NETHER), 0.8F));
        entityRenderers.accept(JEntities.HELL_SERPENT_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("hell_serpent", JDimension.NETHER), 0.25F));

        entityRenderers.accept(JEntities.FLAME_LOTUS_TYPE.get(), renderer -> new AnimatedPathfinderRenderer<>(renderer, new AnimatedMonsterModel<>("flame_lotus", JDimension.BOIL), 0.5F));
        entityRenderers.accept(JEntities.BURNING_LIGHT_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("burning_light", JDimension.BOIL), 0.5F));
        entityRenderers.accept(JEntities.FRIGHTENER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("frightener", JDimension.BOIL), 0.5F, 1.25F));
        entityRenderers.accept(JEntities.HELLWING_TYPE.get(), renderer -> new AnimatedFlyingRenderer<>(renderer, new AnimatedMonsterModel<>("hellwing", JDimension.BOIL), 0.5F));
        entityRenderers.accept(JEntities.MAGMA_BLAZE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("magma_blaze", JDimension.BOIL), 0.5F, 1.25F));
        entityRenderers.accept(JEntities.OBSERVER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("observer", JDimension.BOIL), 0.5F, 1.25F));
        entityRenderers.accept(JEntities.SCREAMER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("screamer", JDimension.BOIL), 0.5F));
        entityRenderers.accept(JEntities.BOIL_TRADER_TYPE.get(), renderer -> new AnimatedVillagerRenderer<>(renderer, new AnimatedMonsterModel<>("boil_trader", JDimension.BOIL), 0.5F));
        entityRenderers.accept(JEntities.ESCAPED_CONVICT_TYPE.get(), renderer -> new AnimatedVillagerRenderer<>(renderer, new AnimatedMonsterModel<>("escaped_convict", JDimension.BOIL), 0.5F, 1.5F));

        entityRenderers.accept(JEntities.EUCA_CHARGER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("euca_charger", JDimension.EUCA), 0.5F));
        entityRenderers.accept(JEntities.DYNASTER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("dynaster", JDimension.EUCA), 0.8F));
        entityRenderers.accept(JEntities.GOLDBOT_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("goldbot", JDimension.EUCA), 0.8F));
        entityRenderers.accept(JEntities.SHIMMERER_TYPE.get(), renderer -> new AnimatedFlyingRenderer<>(renderer, new AnimatedMonsterModel<>("shimmerer", JDimension.EUCA), 0.8F));
        entityRenderers.accept(JEntities.GOLDER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("golder", JDimension.EUCA), 0.8F, 2.0F));
        entityRenderers.accept(JEntities.ROYAL_KING_TYPE.get(), renderer -> new RoyalKingRenderer<>(renderer, new AnimatedMonsterModel<>("royal_king", JDimension.EUCA)));
        entityRenderers.accept(JEntities.CRYPIAN_TYPE.get(), renderer -> new CrypianRenderer<>(renderer, new AnimatedMonsterModel<>("crypian", JDimension.EUCA)));
        entityRenderers.accept(JEntities.ALLOY_MENDER_TYPE.get(), renderer -> new AnimatedVillagerRenderer<>(renderer, new AnimatedMonsterModel<>("alloy_mender", JDimension.EUCA)));
        entityRenderers.accept(JEntities.EUCA_HOPPER.get(), renderer -> new EucaHopperRenderer<>(renderer, new AnimatedMonsterModel<>("euca_hopper", JDimension.EUCA)));

        entityRenderers.accept(JEntities.ESKIMO_TYPE.get(), renderer -> new AnimatedVillagerRenderer<>(renderer, new AnimatedMonsterModel<>("eskimo", JDimension.FROZEN), 0.5F));
        entityRenderers.accept(JEntities.FROZEN_GUARDIAN_TYPE.get(), renderer -> new FrozenGuardianRenderer<>(renderer, new AnimatedMonsterModel<>("frozen_guardian", JDimension.FROZEN)));
        entityRenderers.accept(JEntities.CRYSTAL_CLUSTER_TYPE.get(), renderer -> new AnimatedFlyingRenderer<>(renderer, new AnimatedMonsterModel<>("crystal_cluster", JDimension.FROZEN)));
        entityRenderers.accept(JEntities.FROZEN_FROSTBITER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("frozen_frostbiter", JDimension.FROZEN)));
        entityRenderers.accept(JEntities.ICE_GOLEM_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("ice_golem", JDimension.FROZEN), 1.5F, 2F));
        entityRenderers.accept(JEntities.PERMAFRAUST_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("permafraust", JDimension.FROZEN)));
        entityRenderers.accept(JEntities.SHATTERER_TYPE.get(), renderer -> new AnimatedFlyingRenderer<>(renderer, new AnimatedMonsterModel<>("shatterer", JDimension.FROZEN)));
        entityRenderers.accept(JEntities.SHIVERING_BUSHWALKER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("shivering_bushwalker", JDimension.FROZEN)));
        entityRenderers.accept(JEntities.SHIVERING_SHRIEKER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("shivering_shrieker", JDimension.FROZEN)));
        entityRenderers.accept(JEntities.CAPYBARA_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("capybara", JDimension.FROZEN)));
        entityRenderers.accept(JEntities.SHIVERING_RAM_TYPE.get(), renderer -> new ShiveringRamRenderer<>(renderer, new AnimatedMonsterModel<>("shivering_ram", JDimension.FROZEN)));

        entityRenderers.accept(JEntities.DARKENER_TYPE.get(), renderer -> new AnimatedFlyingRenderer<>(renderer, new AnimatedMonsterModel<>("darkener", JDimension.DEPTHS)));
        entityRenderers.accept(JEntities.DARKNESS_CRAWLER_TYPE.get(), renderer -> new AnimatedTamableRenderer<>(renderer, new AnimatedMonsterModel<>("darkness_crawler", JDimension.DEPTHS)));
        entityRenderers.accept(JEntities.DARK_SORCERER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("dark_sorcerer", JDimension.DEPTHS)));
        entityRenderers.accept(JEntities.DEPTHS_BEAST_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("depths_beast", JDimension.DEPTHS), 0.5F, 2.0F));
        entityRenderers.accept(JEntities.ROC_TYPE.get(), renderer -> new AnimatedTamableRenderer<>(renderer, new AnimatedMonsterModel<>("roc", JDimension.DEPTHS)));
        entityRenderers.accept(JEntities.SPIKED_BEAST_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("spiked_beast", JDimension.DEPTHS)));
        entityRenderers.accept(JEntities.DEPTHS_HUNTER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("depths_hunter", JDimension.DEPTHS), 0.5F, 1.5F));
        entityRenderers.accept(JEntities.STARING_GUARDIAN_TYPE.get(), renderer -> new AnimatedVillagerRenderer<>(renderer, new AnimatedMonsterModel<>("staring_guardian", JDimension.DEPTHS)));
        entityRenderers.accept(JEntities.AURON_TYPE.get(), renderer -> new AnimatedVillagerRenderer<>(renderer, new AnimatedMonsterModel<>("auron", JDimension.DEPTHS), 0.35F, 1.5F));

        entityRenderers.accept(JEntities.CORBANIAN_MOLLUSK_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("corbanian_mollusk", JDimension.CORBA), 0.35F, 2.5F));
        entityRenderers.accept(JEntities.SMELLY_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("smelly", JDimension.CORBA)));
        entityRenderers.accept(JEntities.STINKY_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("stinky", JDimension.CORBA)));
        entityRenderers.accept(JEntities.RED_TORDO_TYPE.get(), renderer -> new AnimatedVillagerRenderer<>(renderer, new AnimatedMonsterModel<>("red_tordo", JDimension.CORBA)));
        entityRenderers.accept(JEntities.WOOD_CREATURE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("wood_creature", JDimension.CORBA)));
        entityRenderers.accept(JEntities.GREEN_TORDO_TYPE.get(), renderer -> new AnimatedVillagerRenderer<>(renderer, new AnimatedMonsterModel<>("green_tordo", JDimension.CORBA)));
        entityRenderers.accept(JEntities.TREE_GOLEM_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("tree_golem", JDimension.CORBA)));
        entityRenderers.accept(JEntities.LEAF_BLOWER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("leaf_blower", JDimension.CORBA)));
        entityRenderers.accept(JEntities.OVERSEER_TYPE.get(), renderer -> new AnimatedFlyingRenderer<>(renderer, new AnimatedMonsterModel<>("overseer", JDimension.CORBA)));
        entityRenderers.accept(JEntities.OVERSEER_ELDER_TYPE.get(), renderer -> new AnimatedFlyingRenderer<>(renderer, new AnimatedMonsterModel<>("overseer_elder", JDimension.CORBA)));
        entityRenderers.accept(JEntities.SURFACE_SEER_TYPE.get(), renderer -> new AnimatedFlyingRenderer<>(renderer, new AnimatedMonsterModel<>("surface_seer", JDimension.CORBA)));
        entityRenderers.accept(JEntities.NATURE_MAGE_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("nature_mage", JDimension.CORBA)));
        entityRenderers.accept(JEntities.OVERGROWN_MERCHANT_TYPE.get(), renderer -> new AnimatedVillagerRenderer<>(renderer, new AnimatedMonsterModel<>("overgrown_merchant", JDimension.CORBA)));
        entityRenderers.accept(JEntities.HOODED_TYPE.get(), renderer -> new AnimatedVillagerRenderer<>(renderer, new AnimatedMonsterModel<>("hooded", JDimension.CORBA)));

        entityRenderers.accept(JEntities.CLOUD_GHOST_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("cloud_ghost", JDimension.CLOUDIA)));
        entityRenderers.accept(JEntities.SKY_EEL_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("sky_eel", JDimension.CLOUDIA)));
        entityRenderers.accept(JEntities.STARLIGHT_BLACKSMITH_TYPE.get(), renderer -> new AnimatedVillagerRenderer<>(renderer, new AnimatedMonsterModel<>("starlight_blacksmith", JDimension.CLOUDIA)));
        entityRenderers.accept(JEntities.STARLIGHT_VILLAGER_TYPE.get(), renderer -> new AnimatedVillagerRenderer<>(renderer, new AnimatedMonsterModel<>("starlight_villager", JDimension.CLOUDIA)));
        entityRenderers.accept(JEntities.STARLIGHT_GOLEM_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("starlight_golem", JDimension.CLOUDIA)));
        entityRenderers.accept(JEntities.STARLIGHT_TRANSPORTER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("starlight_transporter", JDimension.CLOUDIA)));
        entityRenderers.accept(JEntities.STARLIGHT_WALKER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("starlight_walker", JDimension.CLOUDIA)));
        entityRenderers.accept(JEntities.AERO_LOTUS_TYPE.get(), renderer -> new AnimatedPathfinderRenderer<>(renderer, new AnimatedMonsterModel<>("aero_lotus", JDimension.CLOUDIA)));

        entityRenderers.accept(JEntities.ARANA_KING_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("arana_king", JDimension.TERRAINIA)));
        entityRenderers.accept(JEntities.PURPLIAN_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("purplian", JDimension.TERRAINIA)));
        entityRenderers.accept(JEntities.TERRAGROW_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("terragrow", JDimension.TERRAINIA)));
        entityRenderers.accept(JEntities.TERRASCATTERER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("terrascatterer", JDimension.TERRAINIA)));
        entityRenderers.accept(JEntities.TERRASHROOM_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("terrashroom", JDimension.TERRAINIA)));
        entityRenderers.accept(JEntities.TERRASLUG_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("terraslug", JDimension.TERRAINIA)));
        entityRenderers.accept(JEntities.FLUNGAS_TYPE.get(), renderer -> new AnimatedPathfinderRenderer<>(renderer, new AnimatedMonsterModel<>("flungas", JDimension.TERRAINIA), 0.5F, 2));
        entityRenderers.accept(JEntities.TERRANIAN_ENCHANTER_TYPE.get(), renderer -> new AnimatedVillagerRenderer<>(renderer, new AnimatedMonsterModel<>("terranian_enchanter", JDimension.TERRAINIA)));
        entityRenderers.accept(JEntities.TERRANIAN_TRADER_TYPE.get(), renderer -> new AnimatedVillagerRenderer<>(renderer, new AnimatedMonsterModel<>("terranian_trader", JDimension.TERRAINIA)));

        entityRenderers.accept(JEntities.SENTRY_LORD_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("sentry_lord", JDimension.SENTERIAN)));
        entityRenderers.accept(JEntities.SENTRY_STALKER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("sentry_stalker", JDimension.SENTERIAN)));
        entityRenderers.accept(JEntities.SENTRY_WALKER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("sentry_walker", JDimension.SENTERIAN)));
        entityRenderers.accept(JEntities.MINI_SENTRY_LORD_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("mini_sentry_lord", JDimension.SENTERIAN), 0.25F, 0.4F));
        entityRenderers.accept(JEntities.MINI_SENTRY_STALKER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("mini_sentry_stalker", JDimension.SENTERIAN), 0.25F, 0.4F));
        entityRenderers.accept(JEntities.MINI_SENTRY_WALKER_TYPE.get(), renderer -> new AnimatedMonsterRenderer<>(renderer, new AnimatedMonsterModel<>("mini_sentry_walker", JDimension.SENTERIAN), 0.25F, 0.4F));

    }
}