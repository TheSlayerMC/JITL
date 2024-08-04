package net.jitl.core.init.internal;

import net.jitl.common.entity.base.JBoat;
import net.jitl.common.entity.base.JMonsterEntity;
import net.jitl.common.entity.boil.*;
import net.jitl.common.entity.boil.npc.BoilTrader;
import net.jitl.common.entity.boil.npc.EscapedConvict;
import net.jitl.common.entity.boss.*;
import net.jitl.common.entity.cloudia.*;
import net.jitl.common.entity.cloudia.npc.StarlightBlacksmith;
import net.jitl.common.entity.cloudia.npc.StarlightVillager;
import net.jitl.common.entity.corba.*;
import net.jitl.common.entity.corba.npc.GreenTordo;
import net.jitl.common.entity.corba.npc.Hooded;
import net.jitl.common.entity.corba.npc.OvergrownMerchant;
import net.jitl.common.entity.corba.npc.RedTordo;
import net.jitl.common.entity.depths.*;
import net.jitl.common.entity.depths.npc.Auron;
import net.jitl.common.entity.depths.npc.StaringGuardian;
import net.jitl.common.entity.euca.*;
import net.jitl.common.entity.euca.npc.AlloyMender;
import net.jitl.common.entity.euca.npc.Crypian;
import net.jitl.common.entity.euca.npc.RoyalKing;
import net.jitl.common.entity.frozen.*;
import net.jitl.common.entity.frozen.npc.Eskimo;
import net.jitl.common.entity.frozen.npc.FrozenGuardian;
import net.jitl.common.entity.misc.Sentacoin;
import net.jitl.common.entity.nether.*;
import net.jitl.common.entity.overworld.*;
import net.jitl.common.entity.overworld.npc.Mage;
import net.jitl.common.entity.overworld.npc.OverworldSentryStalker;
import net.jitl.common.entity.overworld.npc.RockiteGolem;
import net.jitl.common.entity.projectile.*;
import net.jitl.common.entity.senterian.*;
import net.jitl.common.entity.terrania.*;
import net.jitl.common.entity.terrania.npc.TerranianEnchanter;
import net.jitl.common.entity.terrania.npc.TerranianTrader;
import net.jitl.core.init.JITL;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;

@EventBusSubscriber(modid = JITL.MODID, bus = EventBusSubscriber.Bus.MOD)
public class JEntities {

    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, JITL.MODID);
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
    public static final DeferredHolder<EntityType<?>, EntityType<ConjuringProjectileEntity>> CONJURING_PROJECTILE_TYPE = registerProjectile(ConjuringProjectileEntity::new, "conjuring_projectile", "Conjuring", 0.25F, 0.25F);
    public static final DeferredHolder<EntityType<?>, EntityType<EssenciaProjectileEntity>> ESSENCIA_PROJECTILE_TYPE = registerProjectile(EssenciaProjectileEntity::new, "essencia_projectile", "Essencia", 0.25F, 0.25F);
    public static final DeferredHolder<EntityType<?>, EntityType<FloroMudEntity>> FLORO_MUD_TYPE = registerProjectile(FloroMudEntity::new, "floro_mud", "Floro Mud", 0.5F, 0.5F);
    public static final DeferredHolder<EntityType<?>, EntityType<PiercerEntity>> PIERCER_TYPE = registerProjectile(PiercerEntity::new, "piercer", "Piercer", 0.5F, 0.5F);
    public static final DeferredHolder<EntityType<?>, EntityType<KnifeEntity>> KNIFE_TYPE = registerProjectile(KnifeEntity::new, "knife", "Knife", 0.5F, 0.5F);
    public static final DeferredHolder<EntityType<?>, EntityType<DemonicBombEntity>> DEMONIC_BOMB_TYPE = registerProjectile(DemonicBombEntity::new, "demonic_bomb", "Demonic Bomb", 0.5F, 0.5F);
    public static final DeferredHolder<EntityType<?>, EntityType<FireBombEntity>> FIRE_BOMB_TYPE = registerProjectile(FireBombEntity::new, "fire_bomb", "Fire Bomb", 0.5F, 0.5F);
    public static final DeferredHolder<EntityType<?>, EntityType<MagicBombEntity>> MAGIC_BOMB_TYPE = registerProjectile(MagicBombEntity::new, "magic_bomb", "Magic Bomb", 0.5F, 0.5F);
    public static final DeferredHolder<EntityType<?>, EntityType<EssenceArrowEntity>> ESSENCE_ARROW_TYPE = registerProjectile(EssenceArrowEntity::new, "essence_arrow", "Essence Arrow", 0.5F, 0.5F);
    public static final DeferredHolder<EntityType<?>, EntityType<MagicPotEntity>> MAGIC_POT_TYPE = registerProjectile(MagicPotEntity::new, "magic_pot", "Magic Pot", 0.5F, 0.5F);
    public static final DeferredHolder<EntityType<?>, EntityType<FireballEntity>> FIREBALL_TYPE = registerProjectile(FireballEntity::new, "fireball", "Fireball", 0.25F, 0.25F);

    //RAW ENTITYS
    public static final DeferredHolder<EntityType<?>, EntityType<EssenciaBoltEntity>> ESSENCIA_BOLT_TYPE = registerRawEntity(EssenciaBoltEntity::new, "essencia_bolt", "Essencia Bolt", 0.25F, 0.25F);
    public static final DeferredHolder<EntityType<?>, EntityType<JBoat>> JBOAT_TYPE = registerRawEntity(JBoat::new, "jboat", "Journey Boat", 1.375F, 0.5625F);
    public static final DeferredHolder<EntityType<?>, EntityType<Sentacoin>> SENTACOIN_TYPE = registerRawEntity(Sentacoin::new, "sentacoin", "Sentacoin", 0.1F, 0.1F);
    public static final DeferredHolder<EntityType<?>, EntityType<Sentacoin>> SENTACOIN_BAG_TYPE = registerRawEntity(Sentacoin::new, "sentacoin_bag", "Sentacoin Bag", 0.375F, 0.375F);
    public static final DeferredHolder<EntityType<?>, EntityType<BossCrystal>> BOSS_CRYSTAL_TYPE = registerEntity(BossCrystal::new, "boss_crystal", "Boss Crystal", 0.5F, 0.5F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<SpiritCrystal>> SPIRIT_CRYSTAL_TYPE = registerEntity(SpiritCrystal::new, "spirit_crystal", "Spirit Crystal", 1F, 2F, CORBA_COLOR, PASSIVE_COLOR);

    //BOSS MOBS
    public static final DeferredHolder<EntityType<?>, EntityType<TowerGuardian>> TOWER_GUARDIAN_TYPE = registerEntity(TowerGuardian::new, "tower_guardian", "Tower Guardian", 2F, 3.5F, OVERWORLD_COLOR, BOSS_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<RockiteSmasher>> ROCKITE_SMASHER_TYPE = registerEntity(RockiteSmasher::new, "rockite_smasher", "Rockite Smasher", 2F, 3.25F, OVERWORLD_COLOR, BOSS_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Okoloo>> OKOLOO_TYPE = registerEntity(Okoloo::new, "okoloo", "Okoloo", 1.2F, 2.5F, NETHER_COLOR, BOSS_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Blazier>> BLAZIER_TYPE = registerEntity(Blazier::new, "blazier", "Blazier", 1F, 1.9F, NETHER_COLOR, BOSS_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Calcia>> CALCIA_TYPE = registerEntity(Calcia::new, "calcia", "Calcia", 1.2F, 2.8F, NETHER_COLOR, BOSS_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<SoulWatcher>> SOUL_WATCHER_TYPE = registerEntity(SoulWatcher::new, "soul_watcher", "Soul Watcher", 1.5F, 1F, NETHER_COLOR, BOSS_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<WitheringBeast>> WITHERING_BEAST_TYPE = registerEntity(WitheringBeast::new, "withering_beast", "Withering Beast", 1.2F, 3F, NETHER_COLOR, BOSS_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<FrostGolem>> FROST_GOLEM_TYPE = registerEntity(FrostGolem::new, "frost_golem", "Frost Golem", 2F, 3.25F, FROZEN_COLOR, BOSS_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Eudor>> EUDOR_TYPE = registerEntity(Eudor::new, "eudor", "Eudor", 1.6F, 3.5F, EUCA_COLOR, BOSS_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Corallator>> CORALLATOR_TYPE = registerEntity(Corallator::new, "corallator", "Corallator", 1.2F, 1.8F, EUCA_COLOR, BOSS_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<ThunderBird>> THUNDER_BIRD_TYPE = registerEntity(ThunderBird::new, "thunder_bird", "Thunder Bird", 0.9F, 1.3F, DEPTHS_COLOR, BOSS_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Scale>> SCALE_TYPE = registerEntity(Scale::new, "scale", "Scale", 1.5F, 1F, DEPTHS_COLOR, BOSS_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Logger>> LOGGER_TYPE = registerEntity(Logger::new, "logger", "Logger", 1.2F, 1.8F, CORBA_COLOR, BOSS_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<SentryKing>> SENTRY_KING_TYPE = registerEntity(SentryKing::new, "sentry_king", "Sentry King", 2F, 4F, CORBA_COLOR, BOSS_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<TerranianProtector>> TERRANIAN_PROTECTOR_TYPE = registerEntity(TerranianProtector::new, "terranian_protector", "Terranian Protector", 2F, 4F, TERRANIA_COLOR, BOSS_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<SkyStalker>> SKY_STALKER_TYPE = registerEntity(SkyStalker::new, "sky_stalker", "Sky Stalker", 1.2F, 1.8F, CLOUDIA_COLOR, BOSS_COLOR);

    //OVERWORLD MOBS
    public static final DeferredHolder<EntityType<?>, EntityType<Mage>> MAGE_TYPE = registerEntity(Mage::new, "mage", "Mage", 1F, 1.75F, OVERWORLD_COLOR, TRADER_COLOR, MobCategory.CREATURE);
    public static final DeferredHolder<EntityType<?>, EntityType<Floro>> FLORO_TYPE = registerEntity(Floro::new, "floro", "Floro", 1F, 1.75F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<BoomBoom>> BOOM_TYPE = registerEntity(BoomBoom::new, "boomboom", "BoomBoom", 1F, 1.75F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<IllagerMech>> ILLAGER_MECH_TYPE = registerEntity(IllagerMech::new, "illager_mech", "Illager Mech", 2F, 3.25F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<BrownHongo>> BROWN_HONGO_TYPE = registerEntity(BrownHongo::new, "brown_hongo", "Brown Hongo", 1F, 2F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Spyclopse>> SPYCLOPSE_TYPE = registerEntity(Spyclopse::new, "spyclopse", "Spyclopse", 1F, 2F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Blizzard>> BLIZZARD_TYPE = registerEntity(Blizzard::new, "blizzard", "Blizzard", 1F, 2F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<BigHongo>> BIG_HONGO_TYPE = registerEntity(BigHongo::new, "big_hongo", "Big Hongo", 1F, 2F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<MediumHongo>> MEDIUM_HONGO_TYPE = registerEntity(MediumHongo::new, "medium_hongo", "Medium Hongo", 1F, 2F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<SmallHongo>> SMALL_HONGO_TYPE = registerEntity(SmallHongo::new, "small_hongo", "Small Hongo", 1F, 2F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<JungleTurtle>> JUNGLE_TURTLE_TYPE = registerEntity(JungleTurtle::new, "jungle_turtle", "Jungle Turtle", 2F, 2F, OVERWORLD_COLOR, PASSIVE_COLOR, MobCategory.CREATURE);
    public static final DeferredHolder<EntityType<?>, EntityType<JungleGolem>> JUNGLE_GOLEM_TYPE = registerEntity(JungleGolem::new, "jungle_golem", "Jungle Golem", 1F, 2F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<SandCrawler>> SAND_CRAWLER_TYPE = registerEntity(SandCrawler::new, "sand_crawler", "Sand Crawler", 1.3F, 1F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<RockiteGolem>> ROCKITE_GOLEM_TYPE = registerEntity(RockiteGolem::new, "rockite_golem", "Rockite Golem", 1.3F, 1.5F, OVERWORLD_COLOR, TRADER_COLOR, MobCategory.CREATURE);
    public static final DeferredHolder<EntityType<?>, EntityType<Caveling>> CAVELING_TYPE = registerEntity(Caveling::new, "caveling", "Caveling", 1F, 1.1F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Cavurn>> CAVURN_TYPE = registerEntity(Cavurn::new, "cavurn", "Cavurn", 1F, 2F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Stonewalker>> STONEWALKER_TYPE = registerEntity(Stonewalker::new, "stonewalker", "Stonewalker", 1F, 1.2F, OVERWORLD_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<OverworldSentryStalker>> NEUTRAL_SENTRY_STALKER_TYPE = registerEntity(OverworldSentryStalker::new, "neutral_sentry_stalker", "Neutral Sentry Stalker", 1F, 2.75F, OVERWORLD_COLOR, NEUTRAL_COLOR);

    //NETHER MOBS
    public static final DeferredHolder<EntityType<?>, EntityType<Witherspine>> WITHERSPINE_TYPE = registerEntity(Witherspine::new, "witherspine", "Witherspine", 1F, 3.75F, NETHER_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<MiniGhast>> MINI_GHAST_TYPE = registerEntity(MiniGhast::new, "mini_ghast", "Mini Ghast", 1F, 1F, NETHER_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<HellTurtle>> HELL_TURTLE_TYPE = registerEntity(HellTurtle::new, "hell_turtle", "Hell Turtle", 2F, 2F, NETHER_COLOR, NEUTRAL_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Reaper>> REAPER_TYPE = registerEntity(Reaper::new, "reaper", "Reaper", 1F, 2F, NETHER_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<InfernoBlaze>> INFERNO_BLAZE_TYPE = registerEntity(InfernoBlaze::new, "inferno_blaze", "Inferno Blaze", 1F, 2F, NETHER_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<HellCow>> HELL_COW_TYPE = registerEntity(HellCow::new, "hell_cow", "Hell Cow", 1.5F, 1.75F, NETHER_COLOR, PASSIVE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Hellbot>> HELLBOT_TYPE = registerEntity(Hellbot::new, "hellbot", "Hellbot", 0.5F, 0.75F, NETHER_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<HellSerpent>> HELL_SERPENT_TYPE = registerEntity(HellSerpent::new, "hell_serpent", "Hell Serpent", 0.5F, 0.25F, NETHER_COLOR, HOSTILE_COLOR);

    //BOIL MOBS
    public static final DeferredHolder<EntityType<?>, EntityType<FlameLotus>> FLAME_LOTUS_TYPE = registerEntity(FlameLotus::new, "flame_lotus", "Flame Lotus", 1F, 0.25F, BOILING_COLOR, PASSIVE_COLOR, MobCategory.CREATURE);
    public static final DeferredHolder<EntityType<?>, EntityType<BurningLight>> BURNING_LIGHT_TYPE = registerEntity(BurningLight::new, "burning_light", "Burning Light", 0.5F, 2F, BOILING_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Frightener>> FRIGHTENER_TYPE = registerEntity(Frightener::new, "frightener", "Frightener", 0.5F, 2F, BOILING_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Hellwing>> HELLWING_TYPE = registerEntity(Hellwing::new, "hellwing", "Hellwing", 1F, 1F, NETHER_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<MagmaBlaze>> MAGMA_BLAZE_TYPE = registerEntity(MagmaBlaze::new, "magma_blaze", "Magma Blaze", 0.5F, 1.75F, NETHER_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Observer>> OBSERVER_TYPE = registerEntity(Observer::new, "observer", "Observer", 0.5F, 2F, NETHER_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Screamer>> SCREAMER_TYPE = registerEntity(Screamer::new, "screamer", "Screamer", 0.5F, 2F, NETHER_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<BoilTrader>> BOIL_TRADER_TYPE = registerEntity(BoilTrader::new, "boil_trader", "Boil Trader", 0.5F, 2F, NETHER_COLOR, TRADER_COLOR, MobCategory.CREATURE);
    public static final DeferredHolder<EntityType<?>, EntityType<EscapedConvict>> ESCAPED_CONVICT_TYPE = registerEntity(EscapedConvict::new, "escaped_convict", "Escaped Convict", 1F, 1.9F, NETHER_COLOR, TRADER_COLOR, MobCategory.CREATURE);

    //EUCA MOBS
    public static final DeferredHolder<EntityType<?>, EntityType<EucaCharger>> EUCA_CHARGER_TYPE = registerEntity(EucaCharger::new, "euca_charger", "Euca Charger", 0.5F, 0.75F, EUCA_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Dynaster>> DYNASTER_TYPE = registerEntity(Dynaster::new, "dynaster", "Dynaster", 1F, 1F, EUCA_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Goldbot>> GOLDBOT_TYPE = registerEntity(Goldbot::new, "goldbot", "Goldbot", 0.5F, 0.75F, EUCA_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Crypian>> CRYPIAN_TYPE = registerEntity(Crypian::new, "crypian", "Crypian", 0.75F, 1.8F, EUCA_COLOR, TRADER_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<AlloyMender>> ALLOY_MENDER_TYPE = registerEntity(AlloyMender::new, "alloy_mender", "Alloy Mender", 0.75F, 1.8F, EUCA_COLOR, TRADER_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Shimmerer>> SHIMMERER_TYPE = registerEntity(Shimmerer::new, "shimmerer", "Shimmerer", 0.5F, 0.75F, EUCA_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Golder>> GOLDER_TYPE = registerEntity(Golder::new, "golder", "Golder", 1F, 2F, EUCA_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<RoyalKing>> ROYAL_KING_TYPE = registerEntity(RoyalKing::new, "royal_king", "Royal King", 1F, 2F, EUCA_COLOR, PASSIVE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<EucaHopper>> EUCA_HOPPER = registerEntity(EucaHopper::new, "euca_hopper", "Euca Hopper", 1F, 1F, EUCA_COLOR, NEUTRAL_COLOR);

    //FROZEN MOBS
    public static final DeferredHolder<EntityType<?>, EntityType<Eskimo>> ESKIMO_TYPE = registerEntity(Eskimo::new, "eskimo", "Eskimo", 1F, 2F, FROZEN_COLOR, TRADER_COLOR, MobCategory.CREATURE);
    public static final DeferredHolder<EntityType<?>, EntityType<FrozenTrollEntity>> FROZEN_TROLL_TYPE = registerEntity(FrozenTrollEntity::new, "frozen_troll", "Frozen Troll", 1F, 2F, FROZEN_COLOR, NEUTRAL_COLOR, MobCategory.CREATURE);
    public static final DeferredHolder<EntityType<?>, EntityType<FrozenGuardian>> FROZEN_GUARDIAN_TYPE = registerEntity(FrozenGuardian::new, "frozen_guardian", "Frozen Guardian", 0.75F, 2F, FROZEN_COLOR, PASSIVE_COLOR, MobCategory.CREATURE);
    public static final DeferredHolder<EntityType<?>, EntityType<CrystalCluster>> CRYSTAL_CLUSTER_TYPE = registerEntity(CrystalCluster::new, "crystal_cluster", "Crystal Cluster", 1F, 2F, FROZEN_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<FrozenFrostbiter>> FROZEN_FROSTBITER_TYPE = registerEntity(FrozenFrostbiter::new, "frozen_frostbiter", "Frozen Frostbiter", 1F, 2F, FROZEN_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<IceGolem>> ICE_GOLEM_TYPE = registerEntity(IceGolem::new, "ice_golem", "Ice Golem", 1F, 2F, FROZEN_COLOR, HOSTILE_COLOR, MobCategory.CREATURE);
    public static final DeferredHolder<EntityType<?>, EntityType<Permafraust>> PERMAFRAUST_TYPE = registerEntity(Permafraust::new, "permafraust", "Permafraust", 1F, 2F, FROZEN_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Shatterer>> SHATTERER_TYPE = registerEntity(Shatterer::new, "shatterer", "Shatterer", 1F, 2F, FROZEN_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<ShiveringBushwalker>> SHIVERING_BUSHWALKER_TYPE = registerEntity(ShiveringBushwalker::new, "shivering_bushwalker", "Shivering Bushwalker", 1F, 2F, FROZEN_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<ShiveringShrieker>> SHIVERING_SHRIEKER_TYPE = registerEntity(ShiveringShrieker::new, "shivering_shrieker", "Shivering Shrieker", 1F, 2F, FROZEN_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Capybara>> CAPYBARA_TYPE = registerEntity(Capybara::new, "capybara", "Capybara", 1F, 2F, FROZEN_COLOR, NEUTRAL_COLOR, MobCategory.CREATURE);

    //DEPTHS MOBS
    public static final DeferredHolder<EntityType<?>, EntityType<Darkener>> DARKENER_TYPE = registerEntity(Darkener::new, "darkener", "Darkener", 1F, 1F, DEPTHS_COLOR, NEUTRAL_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<DarknessCrawler>> DARKNESS_CRAWLER_TYPE = registerEntity(DarknessCrawler::new, "darkness_crawler", "Darkness Crawler", 1F, 1F, DEPTHS_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<DarkSorcerer>> DARK_SORCERER_TYPE = registerEntity(DarkSorcerer::new, "dark_sorcerer", "Dark Sorcerer", 0.75F, 3F, DEPTHS_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<DepthsBeast>> DEPTHS_BEAST_TYPE = registerEntity(DepthsBeast::new, "depths_beast", "Depths Beast", 1F, 2F, DEPTHS_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<StaringGuardian>> STARING_GUARDIAN_TYPE = registerEntity(StaringGuardian::new, "staring_guardian", "Staring Guardian", 1F, 2F, DEPTHS_COLOR, TRADER_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<SpikedBeast>> SPIKED_BEAST_TYPE = registerEntity(SpikedBeast::new, "spiked_beast", "Spiked Beast", 0.75F, 1.5F, DEPTHS_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<DepthsHunter>> DEPTHS_HUNTER_TYPE = registerEntity(DepthsHunter::new, "depths_hunter", "Depths Hunter", 1.25F, 2F, DEPTHS_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Roc>> ROC_TYPE = registerEntity(Roc::new, "roc", "Roc", 0.5F, 1F, DEPTHS_COLOR, NEUTRAL_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Auron>> AURON_TYPE = registerEntity(Auron::new, "auron", "Auron", 0.5F, 1.1F, DEPTHS_COLOR, TRADER_COLOR);

    //CORBA MOBS
    public static final DeferredHolder<EntityType<?>, EntityType<CorbanianMollusk>> CORBANIAN_MOLLUSK_TYPE = registerEntity(CorbanianMollusk::new, "corbanian_mollusk", "Corbanian Mollusk", 0.75F, 1F, CORBA_COLOR, NEUTRAL_COLOR, MobCategory.MONSTER);
    public static final DeferredHolder<EntityType<?>, EntityType<Smelly>> SMELLY_TYPE = registerEntity(Smelly::new, "smelly", "Smelly", 1.5F, 3F, CORBA_COLOR, HOSTILE_COLOR, MobCategory.MONSTER);
    public static final DeferredHolder<EntityType<?>, EntityType<Stinky>> STINKY_TYPE = registerEntity(Stinky::new, "stinky", "Stinky", 0.75F, 1.75F, CORBA_COLOR, HOSTILE_COLOR, MobCategory.MONSTER);
    public static final DeferredHolder<EntityType<?>, EntityType<GreenTordo>> GREEN_TORDO_TYPE = registerEntity(GreenTordo::new, "green_tordo", "Green Tordo", 0.75F, 2.5F, CORBA_COLOR, TRADER_COLOR, MobCategory.MONSTER);
    public static final DeferredHolder<EntityType<?>, EntityType<RedTordo>> RED_TORDO_TYPE = registerEntity(RedTordo::new, "red_tordo", "Red Tordo", 0.75F, 2.5F, CORBA_COLOR, TRADER_COLOR, MobCategory.MONSTER);
    public static final DeferredHolder<EntityType<?>, EntityType<WoodCreature>> WOOD_CREATURE_TYPE = registerEntity(WoodCreature::new, "wood_creature", "Wood Creature", 0.75F, 1.8F, CORBA_COLOR, HOSTILE_COLOR, MobCategory.MONSTER);
    public static final DeferredHolder<EntityType<?>, EntityType<TreeGolem>> TREE_GOLEM_TYPE = registerEntity(TreeGolem::new, "tree_golem", "Tree Golem", 0.75F, 1.8F, CORBA_COLOR, HOSTILE_COLOR, MobCategory.MONSTER);
    public static final DeferredHolder<EntityType<?>, EntityType<LeafBlower>> LEAF_BLOWER_TYPE = registerEntity(LeafBlower::new, "leaf_blower", "Leaf Blower", 0.75F, 1.8F, CORBA_COLOR, HOSTILE_COLOR, MobCategory.MONSTER);
    public static final DeferredHolder<EntityType<?>, EntityType<Overseer>> OVERSEER_TYPE = registerEntity(Overseer::new, "overseer", "Overseer", 1F, 1.25F, CORBA_COLOR, HOSTILE_COLOR, MobCategory.MONSTER);
    public static final DeferredHolder<EntityType<?>, EntityType<OverseerElder>> OVERSEER_ELDER_TYPE = registerEntity(OverseerElder::new, "overseer_elder", "Overseer Elder", 2F, 4F, CORBA_COLOR, HOSTILE_COLOR, MobCategory.MONSTER);
    public static final DeferredHolder<EntityType<?>, EntityType<SurfaceSeer>> SURFACE_SEER_TYPE = registerEntity(SurfaceSeer::new, "surface_seer", "Surface Seer", 1F, 1.5F, CORBA_COLOR, HOSTILE_COLOR, MobCategory.MONSTER);
    public static final DeferredHolder<EntityType<?>, EntityType<NatureMage>> NATURE_MAGE_TYPE = registerEntity(NatureMage::new, "nature_mage", "Nature Mage", 0.75F, 2F, CORBA_COLOR, HOSTILE_COLOR, MobCategory.MONSTER);
    public static final DeferredHolder<EntityType<?>, EntityType<OvergrownMerchant>> OVERGROWN_MERCHANT_TYPE = registerEntity(OvergrownMerchant::new, "overgrown_merchant", "Overgrown Merchant", 0.75F, 2F, CORBA_COLOR, TRADER_COLOR, MobCategory.MONSTER);
    public static final DeferredHolder<EntityType<?>, EntityType<Hooded>> HOODED_TYPE = registerEntity(Hooded::new, "hooded", "The Hooded", 0.75F, 2F, CORBA_COLOR, TRADER_COLOR, MobCategory.MONSTER);

    //TERRANIAN MOBS
    public static final DeferredHolder<EntityType<?>, EntityType<AranaKing>> ARANA_KING_TYPE = registerEntity(AranaKing::new, "arana_king", "Arana King", 1F, 1.25F, TERRANIA_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Purplian>> PURPLIAN_TYPE = registerEntity(Purplian::new, "purplian", "Purplian", 0.8F, 1.75F, TERRANIA_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Terragrow>> TERRAGROW_TYPE = registerEntity(Terragrow::new, "terragrow", "Terragrow", 0.9F, 1.1F, TERRANIA_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Terrascatterer>> TERRASCATTERER_TYPE = registerEntity(Terrascatterer::new, "terrascatterer", "Terrascatterer", 0.9F, 1.5F, TERRANIA_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Terrashroom>> TERRASHROOM_TYPE = registerEntity(Terrashroom::new, "terrashroom", "terrashroom", 1F, 2F, TERRANIA_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Terraslug>> TERRASLUG_TYPE = registerEntity(Terraslug::new, "terraslug", "Terraslug", 0.5F, 0.5F, TERRANIA_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<Flungas>> FLUNGAS_TYPE = registerEntity(Flungas::new, "flungas", "Flungas", 0.75F, 0.4F, TERRANIA_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<TerranianEnchanter>> TERRANIAN_ENCHANTER_TYPE = registerEntity(TerranianEnchanter::new, "terranian_enchanter", "Terranian Enchanter", 1.25F, 3.5F, TERRANIA_COLOR, TRADER_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<TerranianTrader>> TERRANIAN_TRADER_TYPE = registerEntity(TerranianTrader::new, "terranian_trader", "Terranian Trader", 1.25F, 3.5F, TERRANIA_COLOR, TRADER_COLOR);

    //CLOUDIA MOBS
    public static final DeferredHolder<EntityType<?>, EntityType<CloudGhost>> CLOUD_GHOST_TYPE = registerEntity(CloudGhost::new, "cloud_ghost", "Cloud Ghost", 0.75F, 1.9F, CLOUDIA_COLOR, HOSTILE_COLOR, MobCategory.CREATURE);
    public static final DeferredHolder<EntityType<?>, EntityType<SkyEel>> SKY_EEL_TYPE = registerEntity(SkyEel::new, "sky_eel", "Sky Eel", 1F, 1.8F, CLOUDIA_COLOR, HOSTILE_COLOR, MobCategory.CREATURE);
    public static final DeferredHolder<EntityType<?>, EntityType<StarlightBlacksmith>> STARLIGHT_BLACKSMITH_TYPE = registerEntity(StarlightBlacksmith::new, "starlight_blacksmith", "Starlight Blacksmith", 1.5F, 2.2F, TRADER_COLOR, HOSTILE_COLOR, MobCategory.CREATURE);
    public static final DeferredHolder<EntityType<?>, EntityType<StarlightVillager>> STARLIGHT_VILLAGER_TYPE = registerEntity(StarlightVillager::new, "starlight_villager", "Starlight Villager", 1.5F, 2.2F, TRADER_COLOR, HOSTILE_COLOR, MobCategory.CREATURE);
    public static final DeferredHolder<EntityType<?>, EntityType<StarlightGolem>> STARLIGHT_GOLEM_TYPE = registerEntity(StarlightGolem::new, "starlight_golem", "Starlight Golem", 1.5F, 3F, CLOUDIA_COLOR, HOSTILE_COLOR, MobCategory.CREATURE);
    public static final DeferredHolder<EntityType<?>, EntityType<StarlightTransporter>> STARLIGHT_TRANSPORTER_TYPE = registerEntity(StarlightTransporter::new, "starlight_transporter", "Starlight Transporter", 0.75F, 1.75F, CLOUDIA_COLOR, HOSTILE_COLOR, MobCategory.CREATURE);
    public static final DeferredHolder<EntityType<?>, EntityType<StarlightWalker>> STARLIGHT_WALKER_TYPE = registerEntity(StarlightWalker::new, "starlight_walker", "Starlight Walker", 0.75F, 1F, CLOUDIA_COLOR, HOSTILE_COLOR, MobCategory.CREATURE);
    public static final DeferredHolder<EntityType<?>, EntityType<AeroLotus>> AERO_LOTUS_TYPE = registerEntity(AeroLotus::new, "aero_lotus", "Aero Lotus", 1F, 0.25F, CLOUDIA_COLOR, HOSTILE_COLOR, MobCategory.CREATURE);

    //SENTERIAN MOBS
    public static final DeferredHolder<EntityType<?>, EntityType<SentryLord>> SENTRY_LORD_TYPE = registerEntity(SentryLord::new, "sentry_lord", "Sentry Lord", 1F, 2.9F, SENTERIAN_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<SentryStalker>> SENTRY_STALKER_TYPE = registerEntity(SentryStalker::new, "sentry_stalker", "Sentry Stalker", 1F, 3.9F, SENTERIAN_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<SentryWalker>> SENTRY_WALKER_TYPE = registerEntity(SentryWalker::new, "sentry_walker", "Sentry Walker", 1F, 3.9F, SENTERIAN_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<MiniSentryLord>> MINI_SENTRY_LORD_TYPE = registerEntity(MiniSentryLord::new, "mini_sentry_lord", "Mini Sentry Lord", 0.8F, 2.9F, SENTERIAN_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<MiniSentryStalker>> MINI_SENTRY_STALKER_TYPE = registerEntity(MiniSentryStalker::new, "mini_sentry_stalker", "Mini Sentry Stalker", 0.8F, 3.9F, SENTERIAN_COLOR, HOSTILE_COLOR);
    public static final DeferredHolder<EntityType<?>, EntityType<MiniSentryWalker>> MINI_SENTRY_WALKER_TYPE = registerEntity(MiniSentryWalker::new, "mini_sentry_walker", "Mini Sentry Walker", 0.8F, 3.9F, SENTERIAN_COLOR, HOSTILE_COLOR);

    private static <T extends Mob> DeferredHolder<EntityType<?>, EntityType<T>> registerEntity(EntityType.EntityFactory<T> factory, String name, String lang, float width, float height, int backgroundColor, int highlightColor, MobCategory category) {
        DeferredHolder<EntityType<?>, EntityType<T>> entity = REGISTRY.register(name, () -> EntityType.Builder.of(factory, category).sized(width, height).build(ResourceLocation.fromNamespaceAndPath(JITL.MODID, name).toString()));
        JItems.register(name + "_spawn_egg" , lang + " Spawn Egg", () -> new DeferredSpawnEggItem(entity, backgroundColor, highlightColor, JItems.itemProps()), JItems.ItemType.SPAWN_EGG);
        entityName.add(name);
        entityLangName.add(lang);
        return entity;
    }

    private static <T extends Mob> DeferredHolder<EntityType<?>, EntityType<T>> registerEntity(EntityType.EntityFactory<T> factory, String name, String lang, float width, float height, int backgroundColor, int highlightColor) {
        return registerEntity(factory, name, lang, width, height, backgroundColor, highlightColor, MobCategory.MONSTER);
    }

    private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> registerRawEntity(EntityType.EntityFactory<T> factory, String name, String lang, float width, float height) {
        entityName.add(name);
        entityLangName.add(lang);
        return REGISTRY.register(name, () -> EntityType.Builder.of(factory, MobCategory.MISC).sized(width, height).setShouldReceiveVelocityUpdates(true).setTrackingRange(80).build(ResourceLocation.fromNamespaceAndPath(JITL.MODID, name).toString()));
    }

    private static <T extends Projectile> DeferredHolder<EntityType<?>, EntityType<T>> registerProjectile(EntityType.EntityFactory<T> factory, String name, String lang, float width, float height) {
        entityName.add(name);
        entityLangName.add(lang);
        return REGISTRY.register(name, () -> EntityType.Builder.of(factory, MobCategory.MISC).sized(width, height).setShouldReceiveVelocityUpdates(true).setTrackingRange(120).setUpdateInterval(20).build(ResourceLocation.fromNamespaceAndPath(JITL.MODID, name).getPath()));
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
        event.put(JUNGLE_TURTLE_TYPE.get(), JungleTurtle.createAttributes());
        event.put(JUNGLE_GOLEM_TYPE.get(), JungleGolem.createAttributes());
        event.put(SAND_CRAWLER_TYPE.get(), SandCrawler.createAttributes());
        event.put(ROCKITE_GOLEM_TYPE.get(), RockiteGolem.createAttributes());
        event.put(FROST_GOLEM_TYPE.get(), FrostGolem.createAttributes());
        event.put(CAVURN_TYPE.get(), Cavurn.createAttributes());
        event.put(CAVELING_TYPE.get(), Caveling.createAttributes());
        event.put(STONEWALKER_TYPE.get(), Stonewalker.createAttributes());
        event.put(BOSS_CRYSTAL_TYPE.get(), BossCrystal.createAttributes());
        event.put(SPIRIT_CRYSTAL_TYPE.get(), SpiritCrystal.createAttributes());

        event.put(WITHERING_BEAST_TYPE.get(), WitheringBeast.createAttributes());
        event.put(CALCIA_TYPE.get(), Calcia.createAttributes());
        event.put(SOUL_WATCHER_TYPE.get(), SoulWatcher.createAttributes());
        event.put(CORALLATOR_TYPE.get(), Corallator.createAttributes());
        event.put(BLAZIER_TYPE.get(), Blazier.createAttributes());
        event.put(THUNDER_BIRD_TYPE.get(), ThunderBird.createAttributes());
        event.put(SCALE_TYPE.get(), Scale.createAttributes());
        event.put(EUDOR_TYPE.get(), Eudor.createAttributes());
        event.put(LOGGER_TYPE.get(), Logger.createAttributes());
        event.put(TERRANIAN_PROTECTOR_TYPE.get(), TerranianProtector.createAttributes());
        event.put(SENTRY_KING_TYPE.get(), SentryKing.createAttributes());
        event.put(SKY_STALKER_TYPE.get(), SkyStalker.createAttributes());

        event.put(WITHERSPINE_TYPE.get(), Witherspine.createAttributes());
        event.put(MINI_GHAST_TYPE.get(), MiniGhast.createAttributes());
        event.put(HELL_TURTLE_TYPE.get(), HellTurtle.createAttributes());
        event.put(REAPER_TYPE.get(), Reaper.createAttributes());
        event.put(INFERNO_BLAZE_TYPE.get(), InfernoBlaze.createAttributes());
        event.put(HELL_COW_TYPE.get(), HellCow.createAttributes());
        event.put(HELLBOT_TYPE.get(), Hellbot.createAttributes());
        event.put(OKOLOO_TYPE.get(), Okoloo.createAttributes());
        event.put(HELL_SERPENT_TYPE.get(), HellSerpent.createAttributes());

        event.put(FLAME_LOTUS_TYPE.get(), FlameLotus.createAttributes());
        event.put(BURNING_LIGHT_TYPE.get(), BurningLight.createAttributes());
        event.put(FRIGHTENER_TYPE.get(), Frightener.createAttributes());
        event.put(HELLWING_TYPE.get(), Hellwing.createAttributes());
        event.put(MAGMA_BLAZE_TYPE.get(), MagmaBlaze.createAttributes());
        event.put(OBSERVER_TYPE.get(), Observer.createAttributes());
        event.put(SCREAMER_TYPE.get(), Screamer.createAttributes());
        event.put(BOIL_TRADER_TYPE.get(), BoilTrader.createAttributes());
        event.put(ESCAPED_CONVICT_TYPE.get(), EscapedConvict.createAttributes());

        event.put(EUCA_CHARGER_TYPE.get(), EucaCharger.createAttributes());
        event.put(DYNASTER_TYPE.get(), Dynaster.createAttributes());
        event.put(GOLDBOT_TYPE.get(), Goldbot.createAttributes());
        event.put(SHIMMERER_TYPE.get(), Shimmerer.createAttributes());
        event.put(GOLDER_TYPE.get(), Golder.createAttributes());
        event.put(ROYAL_KING_TYPE.get(), RoyalKing.createAttributes());
        event.put(CRYPIAN_TYPE.get(), Crypian.createAttributes());
        event.put(ALLOY_MENDER_TYPE.get(), AlloyMender.createAttributes());
        event.put(EUCA_HOPPER.get(), EucaHopper.createAttributes());

        event.put(DARKENER_TYPE.get(), Darkener.createAttributes());
        event.put(DARKNESS_CRAWLER_TYPE.get(), DarknessCrawler.createAttributes());
        event.put(DARK_SORCERER_TYPE.get(), DarknessCrawler.createAttributes());
        event.put(DEPTHS_BEAST_TYPE.get(), DepthsBeast.createAttributes());
        event.put(STARING_GUARDIAN_TYPE.get(), StaringGuardian.createAttributes());
        event.put(SPIKED_BEAST_TYPE.get(), SpikedBeast.createAttributes());
        event.put(ROC_TYPE.get(), Roc.createAttributes());
        event.put(DEPTHS_HUNTER_TYPE.get(), DepthsHunter.createAttributes());
        event.put(AURON_TYPE.get(), Auron.createAttributes());

        event.put(ESKIMO_TYPE.get(), Eskimo.createAttributes());
        event.put(FROZEN_GUARDIAN_TYPE.get(), FrozenGuardian.createAttributes());
        event.put(CRYSTAL_CLUSTER_TYPE.get(), CrystalCluster.createAttributes());
        event.put(FROZEN_FROSTBITER_TYPE.get(), FrozenFrostbiter.createAttributes());
        event.put(ICE_GOLEM_TYPE.get(), IceGolem.createAttributes());
        event.put(PERMAFRAUST_TYPE.get(), Permafraust.createAttributes());
        event.put(SHATTERER_TYPE.get(), Shatterer.createAttributes());
        event.put(SHIVERING_BUSHWALKER_TYPE.get(), ShiveringBushwalker.createAttributes());
        event.put(SHIVERING_SHRIEKER_TYPE.get(), ShiveringShrieker.createAttributes());
        event.put(FROZEN_TROLL_TYPE.get(), FrozenTrollEntity.createAttributes());
        event.put(CAPYBARA_TYPE.get(), Capybara.createAttributes());

        event.put(CORBANIAN_MOLLUSK_TYPE.get(), CorbanianMollusk.createAttributes());
        event.put(SMELLY_TYPE.get(), Smelly.createAttributes());
        event.put(STINKY_TYPE.get(), Stinky.createAttributes());
        event.put(RED_TORDO_TYPE.get(), RedTordo.createAttributes());
        event.put(GREEN_TORDO_TYPE.get(), GreenTordo.createAttributes());
        event.put(WOOD_CREATURE_TYPE.get(), WoodCreature.createAttributes());
        event.put(TREE_GOLEM_TYPE.get(), TreeGolem.createAttributes());
        event.put(OVERSEER_TYPE.get(), Overseer.createAttributes());
        event.put(OVERSEER_ELDER_TYPE.get(), OverseerElder.createAttributes());
        event.put(SURFACE_SEER_TYPE.get(), SurfaceSeer.createAttributes());
        event.put(HOODED_TYPE.get(), Hooded.createAttributes());
        event.put(OVERGROWN_MERCHANT_TYPE.get(), OvergrownMerchant.createAttributes());
        event.put(LEAF_BLOWER_TYPE.get(), LeafBlower.createAttributes());
        event.put(NATURE_MAGE_TYPE.get(), NatureMage.createAttributes());

        event.put(CLOUD_GHOST_TYPE.get(), CloudGhost.createAttributes());
        event.put(SKY_EEL_TYPE.get(), SkyEel.createAttributes());
        event.put(STARLIGHT_BLACKSMITH_TYPE.get(), StarlightBlacksmith.createAttributes());
        event.put(STARLIGHT_VILLAGER_TYPE.get(), StarlightVillager.createAttributes());
        event.put(STARLIGHT_GOLEM_TYPE.get(), StarlightGolem.createAttributes());
        event.put(STARLIGHT_TRANSPORTER_TYPE.get(), StarlightTransporter.createAttributes());
        event.put(STARLIGHT_WALKER_TYPE.get(), StarlightWalker.createAttributes());
        event.put(AERO_LOTUS_TYPE.get(), AeroLotus.createAttributes());

        event.put(ARANA_KING_TYPE.get(), AranaKing.createAttributes());
        event.put(PURPLIAN_TYPE.get(), Purplian.createAttributes());
        event.put(TERRAGROW_TYPE.get(), Terragrow.createAttributes());
        event.put(TERRASCATTERER_TYPE.get(), Terrascatterer.createAttributes());
        event.put(TERRASHROOM_TYPE.get(), Terrashroom.createAttributes());
        event.put(TERRASLUG_TYPE.get(), Terraslug.createAttributes());
        event.put(FLUNGAS_TYPE.get(), Flungas.createAttributes());
        event.put(TERRANIAN_ENCHANTER_TYPE.get(), TerranianEnchanter.createAttributes());
        event.put(TERRANIAN_TRADER_TYPE.get(), TerranianTrader.createAttributes());

        event.put(SENTRY_STALKER_TYPE.get(), SentryStalker.createAttributes());
        event.put(SENTRY_LORD_TYPE.get(), SentryLord.createAttributes());
        event.put(SENTRY_WALKER_TYPE.get(), SentryWalker.createAttributes());
        event.put(MINI_SENTRY_STALKER_TYPE.get(), MiniSentryStalker.createAttributes());
        event.put(MINI_SENTRY_LORD_TYPE.get(), MiniSentryLord.createAttributes());
        event.put(MINI_SENTRY_WALKER_TYPE.get(), MiniSentryWalker.createAttributes());
        event.put(NEUTRAL_SENTRY_STALKER_TYPE.get(), OverworldSentryStalker.createAttributes());
    }

    @SubscribeEvent
    public static void registerSpawnPlacement(RegisterSpawnPlacementsEvent event) {
        setCustomSpawn(event, SHIMMERER_TYPE, Shimmerer::checkSpawn);
        setCustomSpawn(event, MINI_GHAST_TYPE, MiniGhast::checkSpawn);
        setCustomSpawn(event, DARKENER_TYPE, Darkener::checkSpawn);
        setCustomSpawn(event, ROCKITE_GOLEM_TYPE, RockiteGolem::checkSpawn);
        setCustomSpawn(event, CAVELING_TYPE, Caveling::checkSpawn);
        setCustomSpawn(event, STONEWALKER_TYPE, Stonewalker::checkSpawn);
        setCustomSpawn(event, CAVURN_TYPE, Cavurn::checkSpawn);
        setCustomSpawn(event, HELLWING_TYPE, Hellwing::checkSpawn);
        setCustomSpawn(event, SHATTERER_TYPE, Shatterer::checkSpawn);
        setCustomSpawn(event, CRYSTAL_CLUSTER_TYPE, CrystalCluster::checkSpawn);
        setCustomSpawn(event, SKY_EEL_TYPE, SkyEel::checkSpawn);
        setCustomSpawn(event, OVERSEER_TYPE, Overseer::checkSpawn);
        setCustomSpawn(event, OVERSEER_ELDER_TYPE, OverseerElder::checkSpawn);
        setCustomSpawn(event, SURFACE_SEER_TYPE, SurfaceSeer::checkSpawn);
        setCustomSpawn(event, BOOM_TYPE, BoomBoom::checkSpawn);

        setDefaultMonsterSpawn(event, FLORO_TYPE);
        setDefaultMonsterSpawn(event, BROWN_HONGO_TYPE);
        setDefaultMonsterSpawn(event, SPYCLOPSE_TYPE);
        setDefaultMonsterSpawn(event, BLIZZARD_TYPE);
        setDefaultMonsterSpawn(event, BIG_HONGO_TYPE);
        setDefaultMonsterSpawn(event, MEDIUM_HONGO_TYPE);
        setDefaultSpawn(event, SMALL_HONGO_TYPE);
        setDefaultMonsterSpawn(event, JUNGLE_GOLEM_TYPE);
        setDefaultMonsterSpawn(event, SAND_CRAWLER_TYPE);
        setDefaultSpawn(event, JUNGLE_TURTLE_TYPE);
        setDefaultSpawn(event, NEUTRAL_SENTRY_STALKER_TYPE);

        setDefaultMonsterSpawn(event, WITHERSPINE_TYPE);
        setDefaultSpawn(event, HELL_TURTLE_TYPE);
        setDefaultMonsterSpawn(event, REAPER_TYPE);
        setDefaultMonsterSpawn(event, INFERNO_BLAZE_TYPE);
        setDefaultSpawn(event, HELL_COW_TYPE);
        setDefaultMonsterSpawn(event, HELLBOT_TYPE);
        setDefaultMonsterSpawn(event, HELL_SERPENT_TYPE);
        setDefaultMonsterSpawn(event, OKOLOO_TYPE);

        setDefaultMonsterSpawn(event, FLAME_LOTUS_TYPE);
        setDefaultMonsterSpawn(event, BURNING_LIGHT_TYPE);
        setDefaultMonsterSpawn(event, MAGMA_BLAZE_TYPE);
        setDefaultMonsterSpawn(event, FRIGHTENER_TYPE);
        setDefaultSpawn(event, BOIL_TRADER_TYPE);
        setDefaultSpawn(event, ESCAPED_CONVICT_TYPE);
        setDefaultMonsterSpawn(event, OBSERVER_TYPE);
        setDefaultMonsterSpawn(event, SCREAMER_TYPE);

        setDefaultMonsterSpawn(event, EUCA_CHARGER_TYPE);
        setDefaultMonsterSpawn(event, DYNASTER_TYPE);
        setDefaultMonsterSpawn(event, GOLDER_TYPE);
        setDefaultSpawn(event, EUCA_HOPPER);

        setDefaultMonsterSpawn(event, DARKNESS_CRAWLER_TYPE);
        setDefaultMonsterSpawn(event, DEPTHS_BEAST_TYPE);
        setDefaultMonsterSpawn(event, SPIKED_BEAST_TYPE);
        setDefaultMonsterSpawn(event, ROC_TYPE);
        setDefaultMonsterSpawn(event, DEPTHS_HUNTER_TYPE);

        setDefaultSpawn(event, ESKIMO_TYPE);
        setDefaultMonsterSpawn(event, ICE_GOLEM_TYPE);
        setDefaultMonsterSpawn(event, FROZEN_TROLL_TYPE);
        setDefaultMonsterSpawn(event, FROZEN_FROSTBITER_TYPE);
        setDefaultMonsterSpawn(event, PERMAFRAUST_TYPE);
        setDefaultMonsterSpawn(event, SHIVERING_BUSHWALKER_TYPE);
        setDefaultMonsterSpawn(event, SHIVERING_SHRIEKER_TYPE);
        setDefaultMonsterSpawn(event, CAPYBARA_TYPE);

        setDefaultMonsterSpawn(event, CLOUD_GHOST_TYPE);
        setDefaultSpawn(event, STARLIGHT_BLACKSMITH_TYPE);
        setDefaultSpawn(event, STARLIGHT_VILLAGER_TYPE);
        setDefaultMonsterSpawn(event, STARLIGHT_GOLEM_TYPE);
        setDefaultMonsterSpawn(event, STARLIGHT_TRANSPORTER_TYPE);
        setDefaultMonsterSpawn(event, STARLIGHT_WALKER_TYPE);
        setDefaultMonsterSpawn(event, AERO_LOTUS_TYPE);

        setDefaultSpawn(event, GREEN_TORDO_TYPE);
        setDefaultSpawn(event, RED_TORDO_TYPE);
        setDefaultSpawn(event, HOODED_TYPE);
        setDefaultSpawn(event, OVERGROWN_MERCHANT_TYPE);
        setDefaultMonsterSpawn(event, CORBANIAN_MOLLUSK_TYPE);
        setDefaultMonsterSpawn(event, LEAF_BLOWER_TYPE);
        setDefaultMonsterSpawn(event, NATURE_MAGE_TYPE);
        setDefaultMonsterSpawn(event, SMELLY_TYPE);
        setDefaultMonsterSpawn(event, STINKY_TYPE);
        setDefaultMonsterSpawn(event, TREE_GOLEM_TYPE);
        setDefaultMonsterSpawn(event, WOOD_CREATURE_TYPE);

        setDefaultMonsterSpawn(event, BLAZIER_TYPE);
        setDefaultMonsterSpawn(event, CALCIA_TYPE);
        setDefaultSpawn(event, SOUL_WATCHER_TYPE);
        setDefaultMonsterSpawn(event, WITHERING_BEAST_TYPE);
        setDefaultMonsterSpawn(event, EUDOR_TYPE);
        setDefaultSpawn(event, CORALLATOR_TYPE);
        setDefaultMonsterSpawn(event, THUNDER_BIRD_TYPE);
        setDefaultSpawn(event, SCALE_TYPE);
        setDefaultMonsterSpawn(event, LOGGER_TYPE);
        setDefaultMonsterSpawn(event, TERRANIAN_PROTECTOR_TYPE);
        setDefaultMonsterSpawn(event, SENTRY_KING_TYPE);
        setDefaultSpawn(event, SKY_STALKER_TYPE);
        setDefaultSpawn(event, FROST_GOLEM_TYPE);

        setDefaultMonsterSpawn(event, ARANA_KING_TYPE);
        setDefaultMonsterSpawn(event, PURPLIAN_TYPE);
        setDefaultMonsterSpawn(event, TERRAGROW_TYPE);
        setDefaultMonsterSpawn(event, TERRASCATTERER_TYPE);
        setDefaultMonsterSpawn(event, TERRASHROOM_TYPE);
        setDefaultMonsterSpawn(event, TERRASLUG_TYPE);
        setDefaultSpawn(event, FLUNGAS_TYPE);
        setDefaultSpawn(event, TERRANIAN_ENCHANTER_TYPE);
        setDefaultSpawn(event, TERRANIAN_TRADER_TYPE);

        setDefaultMonsterSpawn(event, SENTRY_LORD_TYPE);
        setDefaultMonsterSpawn(event, SENTRY_STALKER_TYPE);
        setDefaultMonsterSpawn(event, SENTRY_WALKER_TYPE);
        setDefaultMonsterSpawn(event, MINI_SENTRY_LORD_TYPE);
        setDefaultMonsterSpawn(event, MINI_SENTRY_STALKER_TYPE);
        setDefaultMonsterSpawn(event, MINI_SENTRY_WALKER_TYPE);

    }

    public static <T extends Entity> void setCustomSpawn(RegisterSpawnPlacementsEvent event, DeferredHolder<EntityType<?>, EntityType<T>> entity, SpawnPlacements.SpawnPredicate<T> spawn) {
        event.register(entity.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, spawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    //For normal mob spawns (animals / NPC's)
    public static <T extends Mob> void setDefaultSpawn(RegisterSpawnPlacementsEvent event, DeferredHolder<EntityType<?>, EntityType<T>> entity) {
        event.register(entity.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    //For monsters to spawn anywhere
    public static <T extends JMonsterEntity> void setDefaultMonsterSpawn(RegisterSpawnPlacementsEvent event, DeferredHolder<EntityType<?>, EntityType<T>> entity) {
        event.register(entity.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JMonsterEntity::checkAnyLightMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    //For monsters in only dark spots
    public static <T extends Monster> void setDarkMonsterSpawn(RegisterSpawnPlacementsEvent event, DeferredHolder<EntityType<?>, EntityType<T>> entity) {
        event.register(entity.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }


}