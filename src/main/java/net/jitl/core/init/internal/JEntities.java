package net.jitl.core.init.internal;

import net.jitl.common.entity.base.JBoat;
import net.jitl.common.entity.boss.BossCrystal;
import net.jitl.common.entity.boss.RockiteSmasher;
import net.jitl.common.entity.boss.TowerGuardian;
import net.jitl.common.entity.depths.*;
import net.jitl.common.entity.euca.*;
import net.jitl.common.entity.frozen.Eskimo;
import net.jitl.common.entity.frozen.FrozenGuardian;
import net.jitl.common.entity.nether.*;
import net.jitl.common.entity.overworld.*;
import net.jitl.common.entity.overworld.npc.Mage;
import net.jitl.common.entity.projectile.*;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = JITL.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JEntities {

    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, JITL.MODID);
    public static final ArrayList<String> entityName = new ArrayList<>();
    public static final ArrayList<String> entityLangName = new ArrayList<>();

    private static final int OVERWORLD_COLOR = 0x32f53f;
    private static final int NETHER_COLOR = 0x881a2b;
    private static final int END_COLOR = 0x931aa3;
    private static final int FROZEN_COLOR = 0x3ea4ff;
    private static final int BOILING_COLOR = 0xeb8026;
    private static final int EUCA_COLOR = 0xffff0b;
    private static final int DEPTHS_COLOR = 0x0705a7;
    private static final int CORBA_COLOR = 0x106903;
    private static final int TERRANIA_COLOR = 0x91046d;
    private static final int CLOUDIA_COLOR = 0xfa45cd;
    private static final int SENTERIAN_COLOR = 0x2e2d2c;
    //mob type colors
    private static final int PASSIVE_COLOR = 0x00ff00;
    private static final int NEUTRAL_COLOR = 0x555555;
    private static final int HOSTILE_COLOR = 0xff0000;
    private static final int TRADER_COLOR = 0x7d007d;
    private static final int BOSS_COLOR = 0xffff7d;

    //PROJECTILES
    public static final RegistryObject<EntityType<ConjuringProjectileEntity>> CONJURING_PROJECTILE_TYPE = registerProjectile(ConjuringProjectileEntity::new, "conjuring_projectile", "Conjuring", 0.25F, 0.25F);
    public static final RegistryObject<EntityType<EssenciaProjectileEntity>> ESSENCIA_PROJECTILE_TYPE = registerProjectile(EssenciaProjectileEntity::new, "essencia_projectile", "Essencia", 0.25F, 0.25F);
    public static final RegistryObject<EntityType<FloroMudEntity>> FLORO_MUD_TYPE = registerProjectile(FloroMudEntity::new, "floro_mud", "Floro Mud", 0.5F, 0.5F);
    public static final RegistryObject<EntityType<PiercerEntity>> PIERCER_TYPE = registerProjectile(PiercerEntity::new, "piercer", "Piercer", 0.5F, 0.5F);
    public static final RegistryObject<EntityType<KnifeEntity>> KNIFE_TYPE = registerProjectile(KnifeEntity::new, "knife", "Knife", 0.5F, 0.5F);

    //RAW ENTITYS
    public static final RegistryObject<EntityType<EssenciaBoltEntity>> ESSENCIA_BOLT_TYPE = registerRawEntity(EssenciaBoltEntity::new, "essencia_bolt", "Essencia Bolt", 0.25F, 0.25F);
    public static final RegistryObject<EntityType<BossCrystal>> BOSS_CRYSTAL_TYPE = registerRawEntity(BossCrystal::new, "boss_crystal", "Boss Crystal", 0.5F, 0.5F);
    public static final RegistryObject<EntityType<JBoat>> JBOAT_TYPE = registerRawEntity(JBoat::new, "jboat", "Journey Boat", 1.375F, 0.5625F);

    //OVERWORLD MOBS
    public static final RegistryObject<EntityType<Mage>> MAGE_TYPE = registerEntity(Mage::new, "mage", "Mage", 1F, 1.75F, OVERWORLD_COLOR, TRADER_COLOR, MobCategory.CREATURE);
    public static final RegistryObject<EntityType<Floro>> FLORO_TYPE = registerEntity(Floro::new, "floro", "Floro", 1F, 1.75F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<BoomBoom>> BOOM_TYPE = registerEntity(BoomBoom::new, "boomboom", "BoomBoom", 1F, 1.75F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<TowerGuardian>> TOWER_GUARDIAN_TYPE = registerEntity(TowerGuardian::new, "tower_guardian", "Tower Guardian", 2F, 3.5F, OVERWORLD_COLOR, BOSS_COLOR);
    public static final RegistryObject<EntityType<RockiteSmasher>> ROCKITE_SMASHER_TYPE = registerEntity(RockiteSmasher::new, "rockite_smasher", "Rockite Smasher", 2F, 3.25F, OVERWORLD_COLOR, BOSS_COLOR);
    public static final RegistryObject<EntityType<IllagerMech>> ILLAGER_MECH_TYPE = registerEntity(IllagerMech::new, "illager_mech", "Illager Mech", 2F, 3.25F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<BrownHongo>> BROWN_HONGO_TYPE = registerEntity(BrownHongo::new, "brown_hongo", "Brown Hongo", 1F, 2F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<Spyclopse>> SPYCLOPSE_TYPE = registerEntity(Spyclopse::new, "spyclopse", "Spyclopse", 1F, 2F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<Blizzard>> BLIZZARD_TYPE = registerEntity(Blizzard::new, "blizzard", "Blizzard", 1F, 2F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<BigHongo>> BIG_HONGO_TYPE = registerEntity(BigHongo::new, "big_hongo", "Big Hongo", 1F, 2F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<MediumHongo>> MEDIUM_HONGO_TYPE = registerEntity(MediumHongo::new, "medium_hongo", "Medium Hongo", 1F, 2F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<SmallHongo>> SMALL_HONGO_TYPE = registerEntity(SmallHongo::new, "small_hongo", "Small Hongo", 1F, 2F, OVERWORLD_COLOR, HOSTILE_COLOR);

    //NETHER MOBS
    public static final RegistryObject<EntityType<Witherspine>> WITHERSPINE_TYPE = registerEntity(Witherspine::new, "witherspine", "Witherspine", 1F, 2F, NETHER_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<MiniGhast>> MINI_GHAST_TYPE = registerEntity(MiniGhast::new, "mini_ghast", "Mini Ghast", 1F, 1F, NETHER_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<HellTurtle>> HELL_TURTLE_TYPE = registerEntity(HellTurtle::new, "hell_turtle", "Hell Turtle", 2F, 2F, NETHER_COLOR, NEUTRAL_COLOR, MobCategory.CREATURE);
    public static final RegistryObject<EntityType<Reaper>> REAPER_TYPE = registerEntity(Reaper::new, "reaper", "Reaper", 1F, 2F, NETHER_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<InfernoBlaze>> INFERNO_BLAZE_TYPE = registerEntity(InfernoBlaze::new, "inferno_blaze", "Inferno Blaze", 1F, 2F, NETHER_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<HellCow>> HELL_COW_TYPE = registerEntity(HellCow::new, "hell_cow", "Hell Cow", 1.5F, 1.75F, NETHER_COLOR, PASSIVE_COLOR, MobCategory.CREATURE);

    //EUCA MOBS
    public static final RegistryObject<EntityType<EucaCharger>> EUCA_CHARGER_TYPE = registerEntity(EucaCharger::new, "euca_charger", "Euca Charger", 0.5F, 0.75F, EUCA_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<Dynaster>> DYNASTER_TYPE = registerEntity(Dynaster::new, "dynaster", "Dynaster", 1F, 1F, EUCA_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<Goldbot>> GOLDBOT_TYPE = registerEntity(Goldbot::new, "goldbot", "Goldbot", 0.5F, 0.75F, EUCA_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<Crypian>> CRYPIAN_TYPE = registerEntity(Crypian::new, "crypian", "Crypian", 0.75F, 1.8F, EUCA_COLOR, TRADER_COLOR, MobCategory.CREATURE);
    public static final RegistryObject<EntityType<AlloyMender>> ALLOY_MENDER_TYPE = registerEntity(AlloyMender::new, "alloy_mender", "Alloy Mender", 0.75F, 1.8F, EUCA_COLOR, TRADER_COLOR, MobCategory.CREATURE);
    public static final RegistryObject<EntityType<Shimmerer>> SHIMMERER_TYPE = registerEntity(Shimmerer::new, "shimmerer", "Shimmerer", 0.5F, 0.75F, EUCA_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<Golder>> GOLDER_TYPE = registerEntity(Golder::new, "golder", "Golder", 1F, 2F, EUCA_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<RoyalKing>> ROYAL_KING_TYPE = registerEntity(RoyalKing::new, "royal_king", "Royal King", 1F, 2F, EUCA_COLOR, PASSIVE_COLOR, MobCategory.CREATURE);

    //FROZEN MOBS
    public static final RegistryObject<EntityType<Eskimo>> ESKIMO_TYPE = registerEntity(Eskimo::new, "eskimo", "Eskimo", 1F, 2F, FROZEN_COLOR, TRADER_COLOR, MobCategory.CREATURE);
    public static final RegistryObject<EntityType<FrozenGuardian>> FROZEN_GUARDIAN_TYPE = registerEntity(FrozenGuardian::new, "frozen_guardian", "Frozen Guardian", 0.75F, 2F, FROZEN_COLOR, PASSIVE_COLOR, MobCategory.CREATURE);

    //DEPTHS MOBS
    public static final RegistryObject<EntityType<Darkener>> DARKENER_TYPE = registerEntity(Darkener::new, "darkener", "Darkener", 1F, 1F, DEPTHS_COLOR, NEUTRAL_COLOR);
    public static final RegistryObject<EntityType<DarknessCrawler>> DARKNESS_CRAWLER_TYPE = registerEntity(DarknessCrawler::new, "darkness_crawler", "Darkness Crawler", 1F, 1F, DEPTHS_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<DarkSorcerer>> DARK_SORCERER_TYPE = registerEntity(DarkSorcerer::new, "dark_sorcerer", "Dark Sorcerer", 0.75F, 3F, DEPTHS_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<DepthsBeast>> DEPTHS_BEAST_TYPE = registerEntity(DepthsBeast::new, "depths_beast", "Depths Beast", 1F, 2F, DEPTHS_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<StaringGuardian>> STARING_GUARDIAN_TYPE = registerEntity(StaringGuardian::new, "staring_guardian", "Staring Guardian", 1F, 2F, DEPTHS_COLOR, TRADER_COLOR);
    public static final RegistryObject<EntityType<SpikedBeast>> SPIKED_BEAST_TYPE = registerEntity(SpikedBeast::new, "spiked_beast", "Spiked Beast", 0.75F, 1.5F, DEPTHS_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<DepthsHunter>> DEPTHS_HUNTER_TYPE = registerEntity(DepthsHunter::new, "depths_hunter", "Depths Hunter", 1.25F, 2F, DEPTHS_COLOR, HOSTILE_COLOR);
    public static final RegistryObject<EntityType<Roc>> ROC_TYPE = registerEntity(Roc::new, "roc", "Roc", 0.5F, 1F, DEPTHS_COLOR, NEUTRAL_COLOR);

    private static <T extends Mob> RegistryObject<EntityType<T>> registerEntity(EntityType.EntityFactory<T> factory, String name, String lang, float width, float height, int backgroundColor, int highlightColor, MobCategory category) {
        RegistryObject<EntityType<T>> entity = REGISTRY.register(name, () -> EntityType.Builder.of(factory, category).sized(width, height).build(new ResourceLocation(JITL.MODID, name).toString()));
        JItems.register(name + "_spawn_egg" , lang + " Spawn Egg", () -> new ForgeSpawnEggItem(entity, backgroundColor, highlightColor, JItems.itemProps()), JItems.ItemType.SPAWN_EGG);
        entityName.add(name);
        entityLangName.add(lang);
        return entity;
    }

    private static <T extends Mob> RegistryObject<EntityType<T>> registerEntity(EntityType.EntityFactory<T> factory, String name, String lang, float width, float height, int backgroundColor, int highlightColor) {
        return registerEntity(factory, name, lang, width, height, backgroundColor, highlightColor, MobCategory.MONSTER);
    }

    private static <T extends Entity> RegistryObject<EntityType<T>> registerRawEntity(EntityType.EntityFactory<T> factory, String name, String lang, float width, float height) {
        entityName.add(name);
        entityLangName.add(lang);
        return REGISTRY.register(name, () -> EntityType.Builder.of(factory, MobCategory.MISC).sized(width, height).setShouldReceiveVelocityUpdates(true).setTrackingRange(80).build(new ResourceLocation(JITL.MODID, name).toString()));
    }

    private static <T extends Projectile> RegistryObject<EntityType<T>> registerProjectile(EntityType.EntityFactory<T> factory, String name, String lang, float width, float height) {
        entityName.add(name);
        entityLangName.add(lang);
        return REGISTRY.register(name, () -> EntityType.Builder.of(factory, MobCategory.MISC).sized(width, height).setShouldReceiveVelocityUpdates(true).setTrackingRange(120).setUpdateInterval(20).build(new ResourceLocation(JITL.MODID, name).getPath()));
    }

    @SubscribeEvent
    public static void registerAttributes(final EntityAttributeCreationEvent event) {
        event.put(MAGE_TYPE.get(), Mage.createAttributes());
        event.put(FLORO_TYPE.get(), Floro.createAttributes());
        event.put(BOOM_TYPE.get(), BoomBoom.createAttributes());
        event.put(TOWER_GUARDIAN_TYPE.get(), TowerGuardian.createAttributes());
        event.put(ROCKITE_SMASHER_TYPE.get(), RockiteSmasher.createAttributes());
        event.put(BROWN_HONGO_TYPE.get(), BrownHongo.createAttributes());
        event.put(ILLAGER_MECH_TYPE.get(), IllagerMech.createAttributes());
        event.put(SPYCLOPSE_TYPE.get(), Spyclopse.createAttributes());
        event.put(BLIZZARD_TYPE.get(), Blizzard.createAttributes());
        event.put(BIG_HONGO_TYPE.get(), BigHongo.createAttributes());
        event.put(MEDIUM_HONGO_TYPE.get(), MediumHongo.createAttributes());
        event.put(SMALL_HONGO_TYPE.get(), SmallHongo.createAttributes());

        event.put(WITHERSPINE_TYPE.get(), Witherspine.createAttributes());
        event.put(MINI_GHAST_TYPE.get(), MiniGhast.createAttributes());
        event.put(HELL_TURTLE_TYPE.get(), HellTurtle.createAttributes());
        event.put(REAPER_TYPE.get(), Reaper.createAttributes());
        event.put(INFERNO_BLAZE_TYPE.get(), InfernoBlaze.createAttributes());
        event.put(HELL_COW_TYPE.get(), HellCow.createAttributes());

        event.put(EUCA_CHARGER_TYPE.get(), EucaCharger.createAttributes());
        event.put(DYNASTER_TYPE.get(), Dynaster.createAttributes());
        event.put(GOLDBOT_TYPE.get(), Goldbot.createAttributes());
        event.put(SHIMMERER_TYPE.get(), Shimmerer.createAttributes());
        event.put(GOLDER_TYPE.get(), Golder.createAttributes());
        event.put(ROYAL_KING_TYPE.get(), RoyalKing.createAttributes());
        event.put(CRYPIAN_TYPE.get(), Crypian.createAttributes());
        event.put(ALLOY_MENDER_TYPE.get(), AlloyMender.createAttributes());

        event.put(DARKENER_TYPE.get(), Darkener.createAttributes());
        event.put(DARKNESS_CRAWLER_TYPE.get(), DarknessCrawler.createAttributes());
        event.put(DARK_SORCERER_TYPE.get(), DarknessCrawler.createAttributes());
        event.put(DEPTHS_BEAST_TYPE.get(), DepthsBeast.createAttributes());
        event.put(STARING_GUARDIAN_TYPE.get(), StaringGuardian.createAttributes());
        event.put(SPIKED_BEAST_TYPE.get(), SpikedBeast.createAttributes());
        event.put(ROC_TYPE.get(), Roc.createAttributes());
        event.put(DEPTHS_HUNTER_TYPE.get(), DepthsHunter.createAttributes());

        event.put(ESKIMO_TYPE.get(), Eskimo.createAttributes());
        event.put(FROZEN_GUARDIAN_TYPE.get(), FrozenGuardian.createAttributes());
    }


    @SubscribeEvent
    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event) {
        setCustomSpawn(event, SHIMMERER_TYPE.get(), Shimmerer::checkSpawn);
        setCustomSpawn(event, MINI_GHAST_TYPE.get(), MiniGhast::checkSpawn);
        setCustomSpawn(event, DARKENER_TYPE.get(), Darkener::checkSpawn);

        setDefaultMonsterSpawn(event, FLORO_TYPE);
        setDefaultMonsterSpawn(event, BOOM_TYPE);
        setDefaultMonsterSpawn(event, BROWN_HONGO_TYPE);
        setDefaultMonsterSpawn(event, SPYCLOPSE_TYPE);
        setDefaultMonsterSpawn(event, BLIZZARD_TYPE);
        setDefaultMonsterSpawn(event, BIG_HONGO_TYPE);
        setDefaultMonsterSpawn(event, MEDIUM_HONGO_TYPE);
        setDefaultMonsterSpawn(event, SMALL_HONGO_TYPE);

        setDefaultMonsterSpawn(event, WITHERSPINE_TYPE);
        setDefaultMonsterSpawn(event, HELL_TURTLE_TYPE);
        setDefaultMonsterSpawn(event, REAPER_TYPE);
        setDefaultMonsterSpawn(event, INFERNO_BLAZE_TYPE);
        setDefaultMonsterSpawn(event, HELL_COW_TYPE);

        setDefaultMonsterSpawn(event, EUCA_CHARGER_TYPE);
        setDefaultMonsterSpawn(event, DYNASTER_TYPE);
        //MAKE STRUCTURE
        setDefaultMonsterSpawn(event, GOLDBOT_TYPE);
        setDefaultMonsterSpawn(event, GOLDER_TYPE);

        setDefaultMonsterSpawn(event, DARKNESS_CRAWLER_TYPE);
        setDefaultMonsterSpawn(event, DARK_SORCERER_TYPE);
        setDefaultMonsterSpawn(event, DEPTHS_BEAST_TYPE);
        setDefaultMonsterSpawn(event, SPIKED_BEAST_TYPE);
        setDefaultMonsterSpawn(event, ROC_TYPE);
        setDefaultMonsterSpawn(event, DEPTHS_HUNTER_TYPE);

        setDefaultSpawn(event, ESKIMO_TYPE);
    }

    public static <T extends Entity> void setCustomSpawn(SpawnPlacementRegisterEvent event, EntityType<T> entity, SpawnPlacements.SpawnPredicate<T> spawn) {
        event.register(entity, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, spawn, SpawnPlacementRegisterEvent.Operation.AND);
    }

    //For normal mob spawns (animals / NPC's)
    public static <T extends Mob> void setDefaultSpawn(SpawnPlacementRegisterEvent event, RegistryObject<EntityType<T>> entity) {
        event.register(entity.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
    }

    //For monsters to spawn anywhere
    public static <T extends Monster> void setDefaultMonsterSpawn(SpawnPlacementRegisterEvent event, RegistryObject<EntityType<T>> entity) {
        event.register(entity.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
    }

    //For monsters in only dark spots
    public static  <T extends Monster> void setDarkMonsterSpawn(SpawnPlacementRegisterEvent event, RegistryObject<EntityType<T>> entity) {
        event.register(entity.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
    }
}