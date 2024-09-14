package net.jitl.core.init.internal;

import net.jitl.common.entity.base.JBoat;
import net.jitl.common.entity.misc.Sentacoin;
import net.jitl.common.entity.projectile.*;
import net.jitl.common.items.*;
import net.jitl.common.items.base.*;
import net.jitl.common.items.curios.HeartContainerItem;
import net.jitl.common.items.curios.JCurioItem;
import net.jitl.common.items.curios.amulet.CloudwalkingAmuletItem;
import net.jitl.common.items.curios.amulet.DynasterAmuletItem;
import net.jitl.common.items.curios.amulet.IceAmuletItem;
import net.jitl.common.items.curios.catalyst.EssenceCatalystItem;
import net.jitl.common.items.curios.catalyst.EssenceRegenCatalystItem;
import net.jitl.common.items.curios.ring.JRingItem;
import net.jitl.common.items.gear.IAbility;
import net.jitl.common.items.gear.LuniumAbility;
import net.jitl.common.items.gear.bloodcrust.BloodcrustArmorAbility;
import net.jitl.common.items.gear.bloodcrust.BloodcrustSwordAbility;
import net.jitl.common.items.gear.bloodcrust.BloodcrustToolAbility;
import net.jitl.common.items.gear.celestium.CelestiumArmorAbility;
import net.jitl.common.items.gear.korite.KoriteSwordAbility;
import net.jitl.common.items.gear.mekyum.MekyumSwordAbility;
import net.jitl.core.helper.JToolTiers;
import net.jitl.core.init.JITL;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.function.Supplier;

public class JItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(JITL.MODID);
    public static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(JITL.MODID);

    public static final IAbility BASIC = new IAbility() {};
    private static final IAbility LUNIUM_ABILITY = new LuniumAbility();
    private static final IAbility MEKYUM_SWORD_ABILITY = new MekyumSwordAbility();
    private static final IAbility KORITE_SWORD_ABILITY = new KoriteSwordAbility();
    private static final IAbility CELESTIUM_ARMOR_ABILITY = new CelestiumArmorAbility();
    private static final IAbility BLOODCRUST_SWORD_ABILITY = new BloodcrustSwordAbility();
    private static final IAbility BLOODCRUST_ARMOR_ABILITY = new BloodcrustArmorAbility();
    private static final IAbility BLOODCRUST_TOOL_ABILITY = new BloodcrustToolAbility();

    public static final ArrayList<String> itemName = new ArrayList<>();
    public static final ArrayList<String> toolName = new ArrayList<>();
    public static final ArrayList<String> modelName = new ArrayList<>();
    public static final ArrayList<String> recordName = new ArrayList<>();
    public static final ArrayList<String> recordDescName = new ArrayList<>();
    public static final ArrayList<String> spawnName = new ArrayList<>();
    public static final ArrayList<String> spawnLangName = new ArrayList<>();
    public static final ArrayList<String> bowName = new ArrayList<>();
    public static final ArrayList<String> bowLangName = new ArrayList<>();
    public static final ArrayList<String> shieldName = new ArrayList<>();
    public static final ArrayList<String> shieldLangName = new ArrayList<>();
    public static final ArrayList<String> langName = new ArrayList<>();
    public static final ArrayList<String> toolLangName = new ArrayList<>();
    public static final ArrayList<String> modelLangName = new ArrayList<>();
    public static final ArrayList<String> recordLangName = new ArrayList<>();
    public static final ArrayList<String> recordDescLangName = new ArrayList<>();
    public static final ArrayList<String> swordName = new ArrayList<>();
    public static final ArrayList<String> pickaxeName = new ArrayList<>();
    public static final ArrayList<String> axeName = new ArrayList<>();
    public static final ArrayList<String> shovelName = new ArrayList<>();
    public static final ArrayList<String> hoeName = new ArrayList<>();
    public static final ArrayList<String> piercerName = new ArrayList<>();

    public static final ArrayList<String> helmetName = new ArrayList<>();
    public static final ArrayList<String> chestplateName = new ArrayList<>();
    public static final ArrayList<String> leggingsName = new ArrayList<>();
    public static final ArrayList<String> bootsName = new ArrayList<>();


    public static final DeferredItem<Item> TEST_BUG = registerNormalItem("test_bug", "Test Bug", TestBugItem::new);
    public static final DeferredItem<Item> LORE_SCROLL = registerNormalItem("lore_scroll", "Lore Scroll", LoreScrollItem::new);

    public static final DeferredItem<Item> IRIDIUM_NUGGET = registerFuelItem("iridium_nugget", "Iridium Nugget", 1600);
    public static final DeferredItem<Item> ENDERILLIUM_SHARD = registerNormalItem("enderillium_shard", "Enderillium Shard");
    public static final DeferredItem<Item> WARPED_QUARTZ = registerNormalItem("warped_quartz", "Warped Quartz");
    public static final DeferredItem<Item> CRIMSON_QUARTZ = registerNormalItem("crimson_quartz", "Crimson Quartz");
    public static final DeferredItem<Item> FIRESTONE_CLUMP = registerFuelItem("firestone_clump", "Firestone Clump", 2000);
    public static final DeferredItem<Item> ASH = registerNormalItem("ash", "Ash");
    public static final DeferredItem<Item> RIMESTONE = registerNormalItem("rimestone", "Rimestone");
    public static final DeferredItem<Item> PERIDOT_GEMSTONE = registerNormalItem("peridot_gemstone", "Peridot Gemstone");
    public static final DeferredItem<Item> RAW_BLAZIUM = registerNormalItem("raw_blazium", "Raw Blazium");
    public static final DeferredItem<Item> BLAZIUM_INGOT = registerNormalItem("blazium_ingot", "Blazium Ingot");
    public static final DeferredItem<Item> REINFORCED_CRYSTAL_INGOT = registerNormalItem("reinforced_crystal_ingot", "Reinforced Crystal Ingot");
    public static final DeferredItem<Item> REINFORCED_STONE_INGOT = registerNormalItem("reinforced_stone_ingot", "Reinforced Stone Ingot");
    public static final DeferredItem<Item> VERDITE_INGOT = registerNormalItem("verdite_ingot", "Verdite Ingot");

    public static final DeferredItem<Item> YELLOW_GEM = registerNormalItem("yellow_gem", "Yellow Gem");
    public static final DeferredItem<Item> PURPLE_GEM = registerNormalItem("purple_gem", "Purple Gem");
    public static final DeferredItem<Item> GREEN_GEM = registerNormalItem("green_gem", "Green Gem");
    public static final DeferredItem<Item> BLUE_GEM = registerNormalItem("blue_gem", "Blue Gem");
    public static final DeferredItem<Item> AQUA_STONE = registerNormalItem("aqua_stone", "Aqua Stone");
    public static final DeferredItem<Item> HEART_STONE = registerNormalItem("heart_stone", "Heart Stone");
    public static final DeferredItem<Item> HELL_TURTLE_SHELL = registerNormalItem("hell_turtle_shell", "Hell Turtle Shell");
    public static final DeferredItem<Item> OVER_SEEING_EYE = registerNormalItem("over_seeing_eye", "Over Seeing Eye");
    public static final DeferredItem<Item> OVER_SEEING_TABLET = registerNormalItem("over_seeing_tablet", "Over Seeing tablet");
    public static final DeferredItem<Item> POTTERY_SHARD = registerNormalItem("pottery_shard", "Pottery Shard");
    public static final DeferredItem<Item> ROCK_SHARD = registerNormalItem("rock_shard", "Rock Shard");
    public static final DeferredItem<Item> SPYCLOPSE_EYE = registerNormalItem("spyclopse_eye", "Spyclopse Eye");
    public static final DeferredItem<Item> STONE_CLUMP = registerNormalItem("stone_clump", "Stone Clump");
    public static final DeferredItem<Item> CAVE_CRYSTAL = registerNormalItem("cave_crystal", "Cave Crystal");
    public static final DeferredItem<Item> CAVE_DUST = registerNormalItem("cave_dust", "Cave Dust");
    public static final DeferredItem<Item> COLLECTOR_ROCK = registerNormalItem("collector_rock", "Collector Rock");
    public static final DeferredItem<Item> CRYSTAL_EYE = registerNormalItem("crystal_eye", "Crystal Eye");
    public static final DeferredItem<Item> DARK_MATTER_GEMSTONE = registerNormalItem("dark_matter_gemstone", "Dark Matter Gemstone");
    public static final DeferredItem<Item> FLORO_PEDAL = registerFoodItem("floro_pedal", "Floro Pedal", JFood.FLORO_PEDAL);
    public static final DeferredItem<Item> FLORO_SEEDS = registerNormalItem("floro_seeds", "Floro Seeds", () -> new ItemNameBlockItem(JBlocks.FLORO_PEDAL_CROP.get(), JItems.itemProps()));
    public static final DeferredItem<Item> CORVEGGIES = registerNormalItem("corveggies", "Corveggies", () -> new ItemNameBlockItem(JBlocks.CORVEGGIES_CROP.get(), JItems.itemProps().food(Foods.CARROT)));
    public static final DeferredItem<Item> CRACKENCANE_SEEDS = registerNormalItem("crackencane_seeds", "Crackencane Seeds", () -> new ItemNameBlockItem(JBlocks.CRACKENCANE_CROP.get(), JItems.itemProps()));
    public static final DeferredItem<Item> CRACKENCANE = registerNormalItem("crackencane", "Crackencane");
    public static final DeferredItem<Item> CRAKEBULB_SEEDS = registerNormalItem("crakebulb_seeds", "Crakebulb Seeds", () -> new ItemNameBlockItem(JBlocks.CRAKEBULB_CROP.get(), JItems.itemProps()));
    public static final DeferredItem<Item> CRAKEBULB = registerNormalItem("crakebulb", "Crakebulb");
    public static final DeferredItem<Item> GLOWA_SEEDS = registerNormalItem("glowa_seeds", "Glowa Seeds", () -> new ItemNameBlockItem(JBlocks.GLOWA_CROP.get(), JItems.itemProps()));
    public static final DeferredItem<Item> GLOWA = registerNormalItem("glowa", "Glowa");
    public static final DeferredItem<Item> SPINEBERRY_SEEDS = registerNormalItem("spineberry_seeds", "Spineberry Seeds", () -> new ItemNameBlockItem(JBlocks.SPINEBERRY_CROP.get(), JItems.itemProps()));
    public static final DeferredItem<Item> SPINEBERRIES = registerNormalItem("spineberries", "Spineberries");
    public static final DeferredItem<Item> ZATPEDAL_SEEDS = registerNormalItem("zatpedal_seeds", "Zatpedal Seeds", () -> new ItemNameBlockItem(JBlocks.ZATPEDAL_CROP.get(), JItems.itemProps()));
    public static final DeferredItem<Item> ZATPEDAL = registerNormalItem("zatpedal", "Zatpedal");
    public static final DeferredItem<Item> TOMATO_SEEDS = registerNormalItem("tomato_seeds", "Tomato Seeds", () -> new ItemNameBlockItem(JBlocks.TOMATO_CROP.get(), JItems.itemProps()));
    public static final DeferredItem<Item> TOMATO = registerFoodItem("tomato", "Tomato", JFood.TOMATO);
    public static final DeferredItem<Item> HONGOSHROOM = registerFoodItem("hongoshroom", "Hongoshroom", JFood.HONGOSROOM);
    public static final DeferredItem<Item> HONGLOWSHROOM = registerFoodItem("honglowshroom", "Honglowshroom", JFood.HONGLOWSROOM);
    public static final DeferredItem<Item> CRYSTAL_APPLE = registerFoodItem("crystal_apple", "Crystal Apple", true, JFood.CRYSTAL_APPLE);
    public static final DeferredItem<Item> FRIED_GHAST_TENTACLE = registerFoodItem("fried_ghast_tentacle", "Fried Ghast Tentacle", JFood.FRIED_GHAST_TENTACLE);
    public static final DeferredItem<Item> FLAMING_GHAST_TENTACLE = registerFoodItem("flaming_ghast_tentacle", "Flaming Ghast Tentacle", JFood.FLAMING_GHAST_TENTACLE);
    public static final DeferredItem<Item> FRIED_FLAMING_GHAST_TENTACLE = registerFoodItem("fried_flaming_ghast_tentacle", "Fried Flaming Ghast Tentacle", JFood.FRIED_FLAMING_GHAST_TENTACLE);
    public static final DeferredItem<Item> GHAST_TENTACLE = registerFoodItem("ghast_tentacle", "Ghast Tentacle", JFood.GHAST_TENTACLE);
    public static final DeferredItem<Item> FRIED_EGG = registerFoodItem("fried_egg", "Fried Egg", JFood.FRIED_EGG);
    public static final DeferredItem<Item> MINT_CANDY_CANE = registerFoodItem("mint_candy_cane", "Mint Candy Cane", JFood.MINT_CANDY_CANE);
    public static final DeferredItem<Item> FRUITY_CANDY_CANE = registerFoodItem("fruity_candy_cane", "Fruity Candy Cane", JFood.FRUITY_CANDY_CANE);
    public static final DeferredItem<Item> CHERRY_CANDY_CANE = registerFoodItem("cherry_candy_cane", "Cherry Candy Cane", JFood.CHERRY_CANDY_CANE);
    public static final DeferredItem<Item> PEPPERMINT = registerFoodItem("peppermint", "Peppermint", JFood.PEPPERMINT);
    public static final DeferredItem<Item> JELLYBEANS = registerFoodItem("jellybeans", "Jellybeans", JFood.JELLYBEANS);
    public static final DeferredItem<Item> CHOCOLATE = registerFoodItem("chocolate", "Chocolate", JFood.CHOCOLATE);
    public static final DeferredItem<Item> VANILLA_WAFER = registerFoodItem("vanilla_wafer", "Vanilla Wafer", JFood.VANILLA_WAFER);
    public static final DeferredItem<Item> REDCURRANT_BERRY = registerNormalItem("redcurrant_berry", "Redcurrant Berry", () -> new ItemNameBlockItem(JBlocks.REDCURRANT_BUSH.get(), JItems.itemProps().food(JFood.REDCURRANT)));
    public static final DeferredItem<Item> BRADBERRY = registerNormalItem("bradberry", "bradberry", () -> new ItemNameBlockItem(JBlocks.BRADBERRY_BUSH.get(), JItems.itemProps().food(JFood.BRADBERRY)));
    public static final DeferredItem<Item> AIRMELON = registerFoodItem("airmelon", "Airmelon", JFood.AIRMELON);
    public static final DeferredItem<Item> AIRROOT_SEEDS = registerNormalItem("airroot_seeds", "Airroot Seeds", () -> new ItemNameBlockItem(JBlocks.AIRROOT_CROP.get(), JItems.itemProps()));
    public static final DeferredItem<Item> SNAKE_FLESH = registerFoodItem("snake_flesh", "Snake Flesh", JFood.SNAKE_FLESH);
    public static final DeferredItem<Item> SLIMY_FLESH = registerFoodItem("slimy_flesh", "Slimy Flesh", JFood.SLIMY_FLESH);
    public static final DeferredItem<Item> BREATHING_FUNGUS = registerFoodItem("breathing_fungus", "Freathing Fungus", JFood.BREATHING_FUNGUS);

    public static final DeferredItem<Item> OBSIDIAN_STICK = registerNormalItem("obsidian_stick", "Obsidian Rod");
    public static final DeferredItem<Item> STONE_STICK = registerNormalItem("stone_stick", "Stone Stick");

    public static final DeferredItem<Item> EUCA_PORTAL_PIECE_1 = registerNormalItem("euca_portal_piece_1", "Euca Portal Piece");
    public static final DeferredItem<Item> EUCA_PORTAL_PIECE_2 = registerNormalItem("euca_portal_piece_2", "Euca Portal Piece");
    public static final DeferredItem<Item> EUCA_PORTAL_PIECE_3 = registerNormalItem("euca_portal_piece_3", "Euca Portal Piece");
    public static final DeferredItem<Item> EUCA_PORTAL_GEM = registerNormalItem("euca_portal_gem", "Euca Portal Gem");
    public static final DeferredItem<Item> DEPTHS_PORTAL_GEM = registerNormalItem("depths_portal_gem", "Depths Portal Gem");
    public static final DeferredItem<Item> CORBA_PORTAL_GEM = registerNormalItem("corba_portal_gem", "Corba Portal Gem");
    public static final DeferredItem<Item> TERRANIA_PORTAL_GEM = registerNormalItem("terrania_portal_gem", "Terrania Portal Gem");
    public static final DeferredItem<Item> CLOUDIA_PORTAL_GEM = registerNormalItem("cloudia_portal_gem", "Cloudia Portal Gem");

    public static final DeferredItem<Item> EUCA_TABLET = registerNormalItem("euca_tablet", "Euca Tablet");
    public static final DeferredItem<Item> EUCA_MEAT = registerFoodItem("euca_meat", "Euca Meat", JFood.EUCA_MEAT);
    public static final DeferredItem<Item> GOLDER_DUST = registerNormalItem("golder_dust", "Golder Dust");
    public static final DeferredItem<Item> SHIMMERER_DUST = registerNormalItem("shimmerer_dust", "Shimmerer Dust");
    public static final DeferredItem<Item> METAL_DISK = registerNormalItem("metal_disk", "Metal Disk");
    public static final DeferredItem<Item> ROYAL_DISK = registerNormalItem("royal_disk", "Royal Disk");
    public static final DeferredItem<Item> GATE_KEYS = registerNormalItem("gate_keys", "Gate Keys");
    public static final DeferredItem<Item> GOLD_CLUMP = registerNormalItem("gold_clump", "Gold Clump");

    public static final DeferredItem<Item> DARK_GEM = registerNormalItem("dark_gem", "Dark Gem", DarkGemItem::new);
    public static final DeferredItem<Item> DEMONIC_EYE = registerNormalItem("demonic_eye", "Demonic Eye", DemonicEyeItem::new);
    public static final DeferredItem<Item> DARK_CRYSTAL = registerNormalItem("dark_crystal", "Dark Crystal");
    public static final DeferredItem<Item> DARK_ORB = registerNormalItem("dark_orb", "Dark Orb");
    public static final DeferredItem<Item> GLOSSY_EYE = registerNormalItem("glossy_eye", "Glossy Eye");

    public static final DeferredItem<Item> FROST_GEM = registerNormalItem("frost_gem", "Frost Gem");
    public static final DeferredItem<Item> ROC_FEATHER = registerNormalItem("roc_feather", "Roc Feather");
    public static final DeferredItem<Item> SCALE = registerNormalItem("scale", "Scale");
    public static final DeferredItem<Item> NATURE_TABLET = registerNormalItem("nature_tablet", "Nature Tablet");
    public static final DeferredItem<Item> ENCHANTED_LEAF = registerNormalItem("enchanted_leaf", "Enchanted Leaf");
    public static final DeferredItem<Item> OVERGROWN_NATURE_BALL = registerNormalItem("overgrown_nature_ball", "Overgrown Nature Ball");
    public static final DeferredItem<Item> EARTHEN_CRYSTAL = registerNormalItem("earthen_crystal", "Earthen Crystal");
    public static final DeferredItem<Item> PURPLE_POWDER = registerNormalItem("purple_powder", "Purple Powder");
    public static final DeferredItem<Item> FROST_FLAKE = registerNormalItem("frost_flake", "Frost Flake");
    public static final DeferredItem<Item> FROZEN_ICE_BALL = registerNormalItem("frozen_ice_ball", "Frozen Ice Ball");
    public static final DeferredItem<Item> ICE_FLAKE = registerNormalItem("ice_flake", "Ice Flake");
    public static final DeferredItem<Item> DEPTHS_FLAKE = registerNormalItem("depths_flake", "Depths Flake");
    public static final DeferredItem<Item> COLD_CLUMP = registerNormalItem("cold_clump", "Cold Clump");
    public static final DeferredItem<Item> CRYSTAL_BALL = registerNormalItem("crystal_ball", "Crystal Ball");
    public static final DeferredItem<Item> MAGIC_DUST = registerNormalItem("magic_dust", "Magic Dust");
    public static final DeferredItem<Item> DEMONIC_DUST = registerNormalItem("demonic_dust", "Demonic Dust");
    public static final DeferredItem<Item> DEMONIC_BONE = registerNormalItem("demonic_bone", "Demonic Bone");
    public static final DeferredItem<Item> SMITHSTONE = registerNormalItem("smithstone", "Smithstone");
    public static final DeferredItem<Item> SMITHSTONE_DUST = registerNormalItem("smithstone_dust", "Smithstone Dust");
    public static final DeferredItem<Item> BLEEDSTONE = registerNormalItem("bleedstone", "Bleedstone");
    public static final DeferredItem<Item> BLEEDSTONE_DUST = registerNormalItem("bleedstone_dust", "Bleedstone Dust");
    public static final DeferredItem<Item> CRYSTAL_FLAKE = registerNormalItem("crystal_flake", "Crystal Flake");
    public static final DeferredItem<Item> FREEZING_TABLET = registerNormalItem("freezing_tablet", "Freezing Tablet");
    public static final DeferredItem<Item> SLUG_SLIME = registerNormalItem("slug_slime", "Slug Slime");
    public static final DeferredItem<Item> LIGHT_TERRANIAN_SOIL = registerNormalItem("light_terranian_soil", "Light Terranian Soil");
    public static final DeferredItem<Item> DARK_TERRANIAN_SOIL = registerNormalItem("dark_terranian_soil", "Dark Terranian Soil");
    public static final DeferredItem<Item> BILE_VIAL = registerNormalItem("bile_vial", "Bile Vial");

    public static final DeferredItem<Item> SIZZLING_EYE = registerNormalItem("sizzling_eye", "Sizzling Eye");
    public static final DeferredItem<Item> SNAKE_SKIN = registerNormalItem("snake_skin", "Snake Skin");
    public static final DeferredItem<Item> WITHIC_DUST = registerNormalItem("withic_dust", "Withic Dust");
    public static final DeferredItem<Item> WITHIC_SOUL = registerNormalItem("withic_soul", "Withic Soul");
    public static final DeferredItem<Item> LOST_SOUL = registerNormalItem("lost_soul", "Lost Soul");
    public static final DeferredItem<Item> BLOOD = registerNormalItem("blood", "Blood");
    public static final DeferredItem<Item> CONCENTRATED_BLOOD = registerNormalItem("concentrated_blood", "Concentrated Blood");
    public static final DeferredItem<Item> BALMY_TEARDROP = registerNormalItem("balmy_teardrop", "Balmy Teardrop");
    public static final DeferredItem<Item> BEASTLY_STOMACH = registerNormalItem("beastly_stomach", "Beastly Stomach");
    public static final DeferredItem<Item> BLAZING_FIREBALL = registerNormalItem("blazing_fireball", "Blazing Fireball");
    public static final DeferredItem<Item> DEMONIC_SKULL = registerNormalItem("demonic_skull", "Demonic Skull");
    public static final DeferredItem<Item> BOILING_SKULL = registerNormalItem("boiling_skull", "Boiling Skull");
    public static final DeferredItem<Item> FLAMING_SPRING = registerNormalItem("flaming_spring", "Flaming Spring");
    public static final DeferredItem<Item> FLAMING_SPROCKET = registerNormalItem("flaming_sprocket", "Flaming Sprocket");
    public static final DeferredItem<Item> FLAMING_HIDE = registerNormalItem("flaming_hide", "Flaming Hide");
    public static final DeferredItem<Item> HELL_SHARDS = registerNormalItem("hell_shards", "Hell Shards");
    public static final DeferredItem<Item> SPAWNER_CLUMP = registerNormalItem("spawner_clump", "Spawner Clump");
    public static final DeferredItem<Item> SPAWNER_BAR = registerNormalItem("spawner_bar", "Spawner Bar");
    public static final DeferredItem<Item> LUNITE_CHUNK = registerNormalItem("lunite_chunk", "Lunite Chunk");
    public static final DeferredItem<Item> SENTRY_EYE = registerNormalItem("sentry_eye", "Sentry Eye", SentryEyeItem::new);
    public static final DeferredItem<Item> ANCIENT_PIECE = registerNormalItem("ancient_piece", "Ancient Piece", AncientPieceItem::new);
    public static final DeferredItem<Item> ANCIENT_SHARD = registerNormalItem("ancient_shard", "Ancient Shard", AncientPieceItem::new);
    public static final DeferredItem<Item> ANCIENT_CHUNK = registerNormalItem("ancient_chunk", "Ancient Chunk", AncientPieceItem::new);
    public static final DeferredItem<Item> ANCIENT_FRAGMENT = registerNormalItem("ancient_fragment", "Ancient Fragment", AncientPieceItem::new);
    public static final DeferredItem<Item> SENTRY_STONE = registerNormalItem("sentry_stone", "Sentry Stone");
    public static final DeferredItem<Item> ANCIENT_EYE_OF_OPENING = registerNormalItem("ancient_eye_of_opening", "Ancient Eye Of Opening");
    public static final DeferredItem<Item> ELDER_KEY = registerNormalItem("elder_key", "Elder Key");

    public static final DeferredItem<Item> TERRASTAR = registerNormalItem("terrastar", "Terrastar");
    public static final DeferredItem<Item> TERRASHROOM = registerNormalItem("terrashroom", "Terrashroom");

    public static final DeferredItem<Item> BOTTLE_OF_ESSENCIA = registerNormalItem("bottle_of_essencia", "Bottle 'o' Essencia", () -> new BottleEssenciaItem(false));
    public static final DeferredItem<Item> STRONG_BOTTLE_OF_ESSENCIA = registerNormalItem("strong_bottle_of_essencia", "Bottle 'o' Essencia", () -> new BottleEssenciaItem(true));
    public static final DeferredItem<Item> FLAME_COIN = registerNormalItem("flame_coin", "Flame Coin", FlameCoinItem::new);

    public static final DeferredItem<Item> BOIL_LOCK_KEY = registerNormalItem("boil_lock_key", "Boiling Lock Key");
    public static final DeferredItem<Item> DEPTHS_KEY = registerNormalItem("depths_lock_key", "Depths Lock Key");
    public static final DeferredItem<Item> DARK_KEY = registerNormalItem("dark_lock_key", "Darkly Lock Key");
    public static final DeferredItem<Item> SENTRY_KEY = registerNormalItem("sentry_key", "Sentry Key");

    public static final DeferredItem<Item> PADLOCK = registerNormalItem("padlock", "Padlock", ChestInteractionItem::new);
    public static final DeferredItem<Item> CHEST_KEY = registerNormalItem("chest_key", "Master Chest Key", ChestInteractionItem::new);
    public static final DeferredItem<Item> JOURNEY_KEY = registerNormalItem("journey_key", "Journey Chest Key", ChestInteractionItem::new);
    public static final DeferredItem<Item> NETHER_KEY = registerNormalItem("nether_key", "Nether Chest Key", ChestInteractionItem::new);
    public static final DeferredItem<Item> FROZEN_KEY = registerNormalItem("frozen_key", "frozen Chest Key", ChestInteractionItem::new);
    public static final DeferredItem<Item> EUCA_KEY = registerNormalItem("euca_key", "Euca Chest Key", ChestInteractionItem::new);
    public static final DeferredItem<Item> BOILING_KEY = registerNormalItem("boiling_key", "Boiling Chest Key", ChestInteractionItem::new);
    public static final DeferredItem<Item> DEPTHS_CHEST_KEY = registerNormalItem("depths_key", "Depths Chest Key", ChestInteractionItem::new);
    public static final DeferredItem<Item> CORBA_KEY = registerNormalItem("corba_key", "Corba Chest Key", ChestInteractionItem::new);
    public static final DeferredItem<Item> TERRANIAN_KEY = registerNormalItem("terranian_key", "Terranian Chest Key", ChestInteractionItem::new);
    public static final DeferredItem<Item> CLOUDIA_KEY = registerNormalItem("cloudia_key", "Cloudia Chest Key", ChestInteractionItem::new);
    public static final DeferredItem<Item> SENTERIAN_KEY = registerNormalItem("senterian_key", "Senterian Chest Key", ChestInteractionItem::new);

    public static final DeferredItem<Item> SAPPHIRE = registerNormalItem("sapphire", "Sapphire");
    public static final DeferredItem<Item> SAPPHIRE_SHIELD = registerShieldItem("sapphire_shield", "Sapphire Shield", () -> new JShieldItem("sapphire", 1200, SAPPHIRE.get()));
    public static final DeferredItem<Item> SAPPHIRE_SHOVEL = registerToolItem("sapphire_shovel", "Sapphire Shovel", () -> new JShovelItem(JToolTiers.SAPPHIRE_SHOVEL, BASIC), ItemType.SHOVEL);
    public static final DeferredItem<Item> SAPPHIRE_PICKAXE = registerToolItem("sapphire_pickaxe", "Sapphire Pickaxe", () -> new JPickaxeItem(JToolTiers.SAPPHIRE_PICKAXE, BASIC), ItemType.PICKAXE);
    public static final DeferredItem<Item> SAPPHIRE_AXE = registerToolItem("sapphire_axe", "Sapphire Axe", () -> new JAxeItem(JToolTiers.SAPPHIRE_AXE, BASIC), ItemType.AXE);
    public static final DeferredItem<Item> SAPPHIRE_HOE = registerToolItem("sapphire_hoe", "Sapphire Hoe", () -> new JHoeItem(JToolTiers.SAPPHIRE_HOE, -1, BASIC), ItemType.HOE);
    public static final DeferredItem<Item> SAPPHIRE_MULTITOOL = registerToolItem("sapphire_multitool", "Sapphire Multitool", () -> new MultitoolItem(JToolTiers.SAPPHIRE_SWORD, BASIC), ItemType.MULTITOOL);
    public static final DeferredItem<Item> SAPPHIRE_SWORD = registerToolItem("sapphire_sword", "Sapphire Sword", () -> new JSwordItem(JToolTiers.SAPPHIRE_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> SAPPHIRE_HELMET = registerNormalItem("sapphire_helmet", "Sapphire Helmet", () -> new JArmorItem(JToolTiers.JArmorTier.SAPPHIRE, ArmorItem.Type.HELMET, null), ItemType.HELMET);
    public static final DeferredItem<Item> SAPPHIRE_CHEST = registerNormalItem("sapphire_chestplate", "Sapphire Chestplate", () -> new JArmorItem(JToolTiers.JArmorTier.SAPPHIRE, ArmorItem.Type.CHESTPLATE, null), ItemType.CHESTPLATE);
    public static final DeferredItem<Item> SAPPHIRE_LEGS = registerNormalItem("sapphire_leggings", "Sapphire Leggings", () -> new JArmorItem(JToolTiers.JArmorTier.SAPPHIRE, ArmorItem.Type.LEGGINGS, null), ItemType.LEGGINGS);
    public static final DeferredItem<Item> SAPPHIRE_BOOTS = registerNormalItem("sapphire_boots", "Sapphire Boots", () -> new JArmorItem(JToolTiers.JArmorTier.SAPPHIRE, ArmorItem.Type.BOOTS, null), ItemType.BOOTS);

    public static final DeferredItem<Item> LUNIUM_POWDER = registerNormalItem("lunium_powder", "Lunium Powder");
    public static final DeferredItem<Item> LUNIUM_SHIELD = registerShieldItem("lunium_shield", "Lunium Shield", () -> new JShieldItem("lunium", 1200, LUNIUM_POWDER.get()));
    public static final DeferredItem<Item> LUNIUM_INGOT = registerNormalItem("lunium_ingot", "Lunium Ingot");
    public static final DeferredItem<Item> LUNIUM_CLUMP = registerNormalItem("lunium_clump", "Lunium Clump");
    public static final DeferredItem<Item> LUNIUM_SHOVEL = registerToolItem("lunium_shovel", "Lunium Shovel", () -> new JShovelItem(JToolTiers.LUNIUM_SHOVEL, BASIC), ItemType.SHOVEL);
    public static final DeferredItem<Item> LUNIUM_PICKAXE = registerToolItem("lunium_pickaxe", "Lunium Pickaxe", () -> new JPickaxeItem(JToolTiers.LUNIUM_PICKAXE, BASIC), ItemType.PICKAXE);
    public static final DeferredItem<Item> LUNIUM_AXE = registerToolItem("lunium_axe", "Lunium Axe", () -> new JAxeItem(JToolTiers.LUNIUM_AXE, BASIC), ItemType.AXE);
    public static final DeferredItem<Item> LUNIUM_HOE = registerToolItem("lunium_hoe", "Lunium Hoe", () -> new JHoeItem(JToolTiers.LUNIUM_HOE, -2, BASIC), ItemType.HOE);
    public static final DeferredItem<Item> LUNIUM_MULTITOOL = registerToolItem("lunium_multitool", "Lunium Multitool", () -> new MultitoolItem(JToolTiers.LUNIUM_SWORD, BASIC), ItemType.MULTITOOL);
    public static final DeferredItem<Item> LUNIUM_SWORD = registerToolItem("lunium_sword", "Lunium Sword", () -> new JSwordItem(JToolTiers.LUNIUM_SWORD, LUNIUM_ABILITY), ItemType.SWORD);
    public static final DeferredItem<Item> LUNIUM_HELMET = registerNormalItem("lunium_helmet", "Lunium Helmet", () -> new JArmorItem(JToolTiers.JArmorTier.LUNIUM, ArmorItem.Type.HELMET, LUNIUM_ABILITY), ItemType.HELMET);
    public static final DeferredItem<Item> LUNIUM_CHEST = registerNormalItem("lunium_chestplate", "Lunium Chestplate", () -> new JArmorItem(JToolTiers.JArmorTier.LUNIUM, ArmorItem.Type.CHESTPLATE, LUNIUM_ABILITY), ItemType.CHESTPLATE);
    public static final DeferredItem<Item> LUNIUM_LEGS = registerNormalItem("lunium_leggings", "Lunium Leggings", () -> new JArmorItem(JToolTiers.JArmorTier.LUNIUM, ArmorItem.Type.LEGGINGS, LUNIUM_ABILITY), ItemType.LEGGINGS);
    public static final DeferredItem<Item> LUNIUM_BOOTS = registerNormalItem("lunium_boots", "Lunium Boots", () -> new JArmorItem(JToolTiers.JArmorTier.LUNIUM, ArmorItem.Type.BOOTS, LUNIUM_ABILITY), ItemType.BOOTS);

    public static final DeferredItem<Item> RAW_SHADIUM = registerNormalItem("raw_shadium", "Raw Shadium");
    public static final DeferredItem<Item> SHADIUM_INGOT = registerNormalItem("shadium_ingot", "Shadium Ingot");
    public static final DeferredItem<Item> SHADIUM_CLUMP = registerNormalItem("shadium_clump", "Shadium Clump");
    public static final DeferredItem<Item> SHADIUM_SHIELD = registerShieldItem("shadium_shield", "Shadium Shield", () -> new JShieldItem("shadium", 1200, SHADIUM_INGOT.get()));
    public static final DeferredItem<Item> SHADIUM_SHOVEL = registerToolItem("shadium_shovel", "Shadium Shovel", () -> new JShovelItem(JToolTiers.SHADIUM_SHOVEL, BASIC), ItemType.SHOVEL);
    public static final DeferredItem<Item> SHADIUM_PICKAXE = registerToolItem("shadium_pickaxe", "Shadium Pickaxe", () -> new JPickaxeItem(JToolTiers.SHADIUM_PICKAXE, BASIC), ItemType.PICKAXE);
    public static final DeferredItem<Item> SHADIUM_AXE = registerToolItem("shadium_axe", "Shadium Axe", () -> new JAxeItem(JToolTiers.SHADIUM_AXE, BASIC), ItemType.AXE);
    public static final DeferredItem<Item> SHADIUM_HOE = registerToolItem("shadium_hoe", "Shadium Hoe", () -> new JHoeItem(JToolTiers.SHADIUM_HOE, -2, BASIC), ItemType.HOE);
    public static final DeferredItem<Item> SHADIUM_MULTITOOL = registerToolItem("shadium_multitool", "Shadium Multitool", () -> new MultitoolItem(JToolTiers.SHADIUM_SWORD, BASIC), ItemType.MULTITOOL);
    public static final DeferredItem<Item> SHADIUM_SWORD = registerToolItem("shadium_sword", "Shadium Sword", () -> new JSwordItem(JToolTiers.SHADIUM_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> SHADIUM_HELMET = registerNormalItem("shadium_helmet", "Shadium Helmet", () -> new JArmorItem(JToolTiers.JArmorTier.SHADIUM, ArmorItem.Type.HELMET, null), ItemType.HELMET);
    public static final DeferredItem<Item> SHADIUM_CHEST = registerNormalItem("shadium_chestplate", "Shadium Chestplate", () -> new JArmorItem(JToolTiers.JArmorTier.SHADIUM, ArmorItem.Type.CHESTPLATE, null), ItemType.CHESTPLATE);
    public static final DeferredItem<Item> SHADIUM_LEGS = registerNormalItem("shadium_leggings", "Shadium Leggings", () -> new JArmorItem(JToolTiers.JArmorTier.SHADIUM, ArmorItem.Type.LEGGINGS, null), ItemType.LEGGINGS);
    public static final DeferredItem<Item> SHADIUM_BOOTS = registerNormalItem("shadium_boots", "Shadium Boots", () -> new JArmorItem(JToolTiers.JArmorTier.SHADIUM, ArmorItem.Type.BOOTS, null), ItemType.BOOTS);

    public static final DeferredItem<Item> RAW_BLOODCRUST = registerNormalItem("raw_bloodcrust", "Raw Bloodcrust");
    public static final DeferredItem<Item> BLOODCRUST_INGOT = registerNormalItem("bloodcrust_ingot", "Bloodcrust Ingot");
    public static final DeferredItem<Item> BLOODCRUST_CLUMP = registerNormalItem("bloodcrust_clump", "Bloodcrust Clump");
    public static final DeferredItem<Item> BLOODCRUST_SHIELD = registerShieldItem("bloodcrust_shield", "Bloodcrust Shield", () -> new JShieldItem("bloodcrust", 1200, BLOODCRUST_INGOT.get()));
    public static final DeferredItem<Item> BLOODCRUST_SHOVEL = registerToolItem("bloodcrust_shovel", "Bloodcrust Shovel", () -> new JShovelItem(JToolTiers.BLOODCRUST_SHOVEL, BLOODCRUST_TOOL_ABILITY), ItemType.SHOVEL);
    public static final DeferredItem<Item> BLOODCRUST_PICKAXE = registerToolItem("bloodcrust_pickaxe", "Bloodcrust Pickaxe", () -> new JPickaxeItem(JToolTiers.BLOODCRUST_PICKAXE, BLOODCRUST_TOOL_ABILITY), ItemType.PICKAXE);
    public static final DeferredItem<Item> BLOODCRUST_AXE = registerToolItem("bloodcrust_axe", "Bloodcrust Axe", () -> new JAxeItem(JToolTiers.BLOODCRUST_AXE, BLOODCRUST_TOOL_ABILITY), ItemType.AXE);
    public static final DeferredItem<Item> BLOODCRUST_MULTITOOL = registerToolItem("bloodcrust_multitool", "Bloodcrust Multitool", () -> new MultitoolItem(JToolTiers.BLOODCRUST_SWORD, BLOODCRUST_TOOL_ABILITY), ItemType.MULTITOOL);
    public static final DeferredItem<Item> BLOODCRUST_HOE = registerToolItem("bloodcrust_hoe", "Bloodcrust Hoe", () -> new JHoeItem(JToolTiers.BLOODCRUST_HOE, -2, BLOODCRUST_TOOL_ABILITY), ItemType.HOE);
    public static final DeferredItem<Item> BLOODCRUST_SWORD = registerToolItem("bloodcrust_sword", "Bloodcrust Sword", () -> new JSwordItem(JToolTiers.BLOODCRUST_SWORD, BLOODCRUST_SWORD_ABILITY), ItemType.SWORD);
    public static final DeferredItem<Item> BLOODCRUST_HELMET = registerNormalItem("bloodcrust_helmet", "Bloodcrust Helmet", () -> new JArmorItem(JToolTiers.JArmorTier.BLOODCRUST, ArmorItem.Type.HELMET, BLOODCRUST_ARMOR_ABILITY), ItemType.HELMET);
    public static final DeferredItem<Item> BLOODCRUST_CHEST = registerNormalItem("bloodcrust_chestplate", "Bloodcrust Chestplate", () -> new JArmorItem(JToolTiers.JArmorTier.BLOODCRUST, ArmorItem.Type.CHESTPLATE, BLOODCRUST_ARMOR_ABILITY), ItemType.CHESTPLATE);
    public static final DeferredItem<Item> BLOODCRUST_LEGS = registerNormalItem("bloodcrust_leggings", "Bloodcrust Leggings", () -> new JArmorItem(JToolTiers.JArmorTier.BLOODCRUST, ArmorItem.Type.LEGGINGS, BLOODCRUST_ARMOR_ABILITY), ItemType.LEGGINGS);
    public static final DeferredItem<Item> BLOODCRUST_BOOTS = registerNormalItem("bloodcrust_boots", "Bloodcrust Boots", () -> new JArmorItem(JToolTiers.JArmorTier.BLOODCRUST, ArmorItem.Type.BOOTS, BLOODCRUST_ARMOR_ABILITY), ItemType.BOOTS);

    public static final DeferredItem<Item> CELESTIUM_INGOT = registerNormalItem("celestium_ingot", "Celestium Ingot");
    public static final DeferredItem<Item> CELESTIUM_GEMSTONE = registerNormalItem("celestium_gemstone", "Celestium Gemstone");
    public static final DeferredItem<Item> CELESTIUM_SHIELD = registerShieldItem("celestium_shield", "Celestium Shield", () -> new JShieldItem("celestium", 1200, CELESTIUM_INGOT.get()));
    public static final DeferredItem<Item> CELESTIUM_SHOVEL = registerToolItem("celestium_shovel", "Celestium Shovel", () -> new JShovelItem(JToolTiers.CELESTIUM_SHOVEL, BASIC), ItemType.SHOVEL);
    public static final DeferredItem<Item> CELESTIUM_PICKAXE = registerToolItem("celestium_pickaxe", "Celestium Pickaxe", () -> new JPickaxeItem(JToolTiers.CELESTIUM_PICKAXE, BASIC), ItemType.PICKAXE);
    public static final DeferredItem<Item> CELESTIUM_AXE = registerToolItem("celestium_axe", "Celestium Axe", () -> new JAxeItem(JToolTiers.CELESTIUM_AXE, BASIC), ItemType.AXE);
    public static final DeferredItem<Item> CELESTIUM_HOE = registerToolItem("celestium_hoe", "Celestium Hoe", () -> new JHoeItem(JToolTiers.CELESTIUM_HOE, -5, BASIC), ItemType.HOE);
    public static final DeferredItem<Item> CELESTIUM_MULTITOOL = registerToolItem("celestium_multitool", "Celestium Multitool", () -> new MultitoolItem(JToolTiers.CELESTIUM_SWORD, BASIC), ItemType.MULTITOOL);
    public static final DeferredItem<Item> CELESTIUM_SWORD = registerToolItem("celestium_sword", "Celestium Sword", () -> new JSwordItem(JToolTiers.CELESTIUM_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> CELESTIUM_HELMET = registerNormalItem("celestium_helmet", "Celestium Helmet", () -> new JArmorItem(JToolTiers.JArmorTier.CELESTIUM, ArmorItem.Type.HELMET, CELESTIUM_ARMOR_ABILITY), ItemType.HELMET);
    public static final DeferredItem<Item> CELESTIUM_CHEST = registerNormalItem("celestium_chestplate", "Celestium Chestplate", () -> new JArmorItem(JToolTiers.JArmorTier.CELESTIUM, ArmorItem.Type.CHESTPLATE, CELESTIUM_ARMOR_ABILITY), ItemType.CHESTPLATE);
    public static final DeferredItem<Item> CELESTIUM_LEGS = registerNormalItem("celestium_leggings", "Celestium Leggings", () -> new JArmorItem(JToolTiers.JArmorTier.CELESTIUM, ArmorItem.Type.LEGGINGS, CELESTIUM_ARMOR_ABILITY), ItemType.LEGGINGS);
    public static final DeferredItem<Item> CELESTIUM_BOOTS = registerNormalItem("celestium_boots", "Celestium Boots", () -> new JArmorItem(JToolTiers.JArmorTier.CELESTIUM, ArmorItem.Type.BOOTS, CELESTIUM_ARMOR_ABILITY), ItemType.BOOTS);

    public static final DeferredItem<Item> MEKYUM_INGOT = registerNormalItem("mekyum_ingot", "Mekyum Ingot");
    public static final DeferredItem<Item> MEKYUM_GEMSTONE = registerNormalItem("mekyum_gemstone", "Mekyum Gemstone");
    public static final DeferredItem<Item> MEKYUM_SHIELD = registerShieldItem("mekyum_shield", "Mekyum Shield", () -> new JShieldItem("mekyum", 1200, MEKYUM_INGOT.get()));
    public static final DeferredItem<Item> MEKYUM_SHOVEL = registerToolItem("mekyum_shovel", "Mekyum Shovel", () -> new JShovelItem(JToolTiers.MEKYUM_SHOVEL, BASIC), ItemType.SHOVEL);
    public static final DeferredItem<Item> MEKYUM_PICKAXE = registerToolItem("mekyum_pickaxe", "Mekyum Pickaxe", () -> new JPickaxeItem(JToolTiers.MEKYUM_PICKAXE, BASIC), ItemType.PICKAXE);
    public static final DeferredItem<Item> MEKYUM_AXE = registerToolItem("mekyum_axe", "Mekyum Axe", () -> new JAxeItem(JToolTiers.MEKYUM_AXE, BASIC), ItemType.AXE);
    public static final DeferredItem<Item> MEKYUM_HOE = registerToolItem("mekyum_hoe", "Mekyum Hoe", () -> new JHoeItem(JToolTiers.MEKYUM_HOE, -5, BASIC), ItemType.HOE);
    public static final DeferredItem<Item> MEKYUM_MULTITOOL = registerToolItem("mekyum_multitool", "Mekyum Multitool", () -> new MultitoolItem(JToolTiers.MEKYUM_SWORD, BASIC), ItemType.MULTITOOL);
    public static final DeferredItem<Item> MEKYUM_SWORD = registerToolItem("mekyum_sword", "Mekyum Sword", () -> new JSwordItem(JToolTiers.MEKYUM_SWORD, MEKYUM_SWORD_ABILITY), ItemType.SWORD);

    public static final DeferredItem<Item> STORON_INGOT = registerNormalItem("storon_ingot", "Storon Ingot");
    public static final DeferredItem<Item> STORON_GEMSTONE = registerNormalItem("storon_gemstone", "Storon Gemstone");
    public static final DeferredItem<Item> STORON_SHIELD = registerShieldItem("storon_shield", "Storon Shield", () -> new JShieldItem("storon", 1200, STORON_INGOT.get()));
    public static final DeferredItem<Item> STORON_SHOVEL = registerToolItem("storon_shovel", "Storon Shovel", () -> new JShovelItem(JToolTiers.STORON_SHOVEL, BASIC), ItemType.SHOVEL);
    public static final DeferredItem<Item> STORON_PICKAXE = registerToolItem("storon_pickaxe", "Storon Pickaxe", () -> new JPickaxeItem(JToolTiers.STORON_PICKAXE, BASIC), ItemType.PICKAXE);
    public static final DeferredItem<Item> STORON_AXE = registerToolItem("storon_axe", "Storon Axe", () -> new JAxeItem(JToolTiers.STORON_AXE, BASIC), ItemType.AXE);
    public static final DeferredItem<Item> STORON_HOE = registerToolItem("storon_hoe", "Storon Hoe", () -> new JHoeItem(JToolTiers.STORON_HOE, -5, BASIC), ItemType.HOE);
    public static final DeferredItem<Item> STORON_MULTITOOL = registerToolItem("storon_multitool", "Storon Multitool", () -> new MultitoolItem(JToolTiers.STORON_SWORD, BASIC), ItemType.MULTITOOL);
    public static final DeferredItem<Item> STORON_SWORD = registerToolItem("storon_sword", "Storon Sword", () -> new JSwordItem(JToolTiers.STORON_SWORD, BASIC), ItemType.SWORD);

    public static final DeferredItem<Item> KORITE_INGOT = registerNormalItem("korite_ingot", "Korite Ingot");
    public static final DeferredItem<Item> KORITE_GEMSTONE = registerNormalItem("korite_gemstone", "Korite Gemstone");
    public static final DeferredItem<Item> KORITE_SHIELD = registerShieldItem("korite_shield", "Korite Shield", () -> new JShieldItem("korite", 1200, KORITE_INGOT.get()));
    public static final DeferredItem<Item> KORITE_SHOVEL = registerToolItem("korite_shovel", "Korite Shovel", () -> new JShovelItem(JToolTiers.KORITE_SHOVEL, BASIC), ItemType.SHOVEL);
    public static final DeferredItem<Item> KORITE_PICKAXE = registerToolItem("korite_pickaxe", "Korite Pickaxe", () -> new JPickaxeItem(JToolTiers.KORITE_PICKAXE, BASIC), ItemType.PICKAXE);
    public static final DeferredItem<Item> KORITE_AXE = registerToolItem("korite_axe", "korite Axe", () -> new JAxeItem(JToolTiers.KORITE_AXE, BASIC), ItemType.AXE);
    public static final DeferredItem<Item> KORITE_HOE = registerToolItem("korite_hoe", "Korite Hoe", () -> new JHoeItem(JToolTiers.KORITE_HOE, -5, BASIC), ItemType.HOE);
    public static final DeferredItem<Item> KORITE_MULTITOOL = registerToolItem("korite_multitool", "Korite Multitool", () -> new MultitoolItem(JToolTiers.KORITE_SWORD, BASIC), ItemType.MULTITOOL);
    public static final DeferredItem<Item> KORITE_SWORD = registerToolItem("korite_sword", "korite Sword", () -> new JSwordItem(JToolTiers.KORITE_SWORD, KORITE_SWORD_ABILITY), ItemType.SWORD);

    public static final DeferredItem<Item> FLAIRIUM_INGOT = registerNormalItem("flairium_ingot", "Flairium Ingot");
    public static final DeferredItem<Item> FLAIRIUM_SHIELD = registerShieldItem("flairium_shield", "Flairium Shield", () -> new JShieldItem("flairium", 1200, FLAIRIUM_INGOT.get()));
    public static final DeferredItem<Item> FLAIRIUM_SHOVEL = registerToolItem("flairium_shovel", "Flairium Shovel", () -> new JShovelItem(JToolTiers.FLAIRIUM_SHOVEL, BASIC), ItemType.SHOVEL);
    public static final DeferredItem<Item> FLAIRIUM_PICKAXE = registerToolItem("flairium_pickaxe", "Flairium Pickaxe", () -> new JPickaxeItem(JToolTiers.FLAIRIUM_PICKAXE, BASIC), ItemType.PICKAXE);
    public static final DeferredItem<Item> FLAIRIUM_AXE = registerToolItem("flairium_axe", "Flairium Axe", () -> new JAxeItem(JToolTiers.FLAIRIUM_AXE, BASIC), ItemType.AXE);
    public static final DeferredItem<Item> FLAIRIUM_HOE = registerToolItem("flairium_hoe", "Flairium Hoe", () -> new JHoeItem(JToolTiers.FLAIRIUM_HOE, -7, BASIC), ItemType.HOE);
    public static final DeferredItem<Item> FLAIRIUM_MULTITOOL = registerToolItem("flairium_multitool", "Flairium Multitool", () -> new MultitoolItem(JToolTiers.FLAIRIUM_SWORD, BASIC), ItemType.MULTITOOL);
    public static final DeferredItem<Item> FLAIRIUM_SWORD = registerToolItem("flairium_sword", "Flairium Sword", () -> new JSwordItem(JToolTiers.FLAIRIUM_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> FLAIRIUM_HELMET = registerNormalItem("flairium_helmet", "Flairium Helmet", () -> new JArmorItem(JToolTiers.JArmorTier.FLAIRIUM, ArmorItem.Type.HELMET, null), ItemType.HELMET);
    public static final DeferredItem<Item> FLAIRIUM_CHEST = registerNormalItem("flairium_chestplate", "Flairium Chestplate", () -> new JArmorItem(JToolTiers.JArmorTier.FLAIRIUM, ArmorItem.Type.CHESTPLATE, null), ItemType.CHESTPLATE);
    public static final DeferredItem<Item> FLAIRIUM_LEGS = registerNormalItem("flairium_leggings", "Flairium Leggings", () -> new JArmorItem(JToolTiers.JArmorTier.FLAIRIUM, ArmorItem.Type.LEGGINGS, null), ItemType.LEGGINGS);
    public static final DeferredItem<Item> FLAIRIUM_BOOTS = registerNormalItem("flairium_boots", "Flairium Boots", () -> new JArmorItem(JToolTiers.JArmorTier.FLAIRIUM, ArmorItem.Type.BOOTS, null), ItemType.BOOTS);

    public static final DeferredItem<Item> DES_INGOT = registerNormalItem("des_ingot", "Des Ingot");
    public static final DeferredItem<Item> DES_SHIELD = registerShieldItem("des_shield", "Des Shield", () -> new JShieldItem("des", 1200, DES_INGOT.get()));
    public static final DeferredItem<Item> DES_SHOVEL = registerToolItem("des_shovel", "Des Shovel", () -> new JShovelItem(JToolTiers.DES_SHOVEL, BASIC), ItemType.SHOVEL);
    public static final DeferredItem<Item> DES_PICKAXE = registerToolItem("des_pickaxe", "Des Pickaxe", () -> new JPickaxeItem(JToolTiers.DES_PICKAXE, BASIC), ItemType.PICKAXE);
    public static final DeferredItem<Item> DES_AXE = registerToolItem("des_axe", "Des Axe", () -> new JAxeItem(JToolTiers.DES_AXE, BASIC), ItemType.AXE);
    public static final DeferredItem<Item> DES_HOE = registerToolItem("des_hoe", "Des Hoe", () -> new JHoeItem(JToolTiers.DES_HOE, -7, BASIC), ItemType.HOE);
    public static final DeferredItem<Item> DES_MULTITOOL = registerToolItem("des_multitool", "Des Multitool", () -> new MultitoolItem(JToolTiers.DES_SWORD, BASIC), ItemType.MULTITOOL);
    public static final DeferredItem<Item> DES_SWORD = registerToolItem("des_sword", "Des Sword", () -> new JSwordItem(JToolTiers.DES_SWORD, BASIC), ItemType.SWORD);

    public static final DeferredItem<Item> GORBITE_GEM = registerNormalItem("gorbite_gem", "Gorbite Gem");
    public static final DeferredItem<Item> GORBITE_SHIELD = registerShieldItem("gorbite_shield", "Gorbite Shield", () -> new JShieldItem("gorbite", 1200, GORBITE_GEM.get()));
    public static final DeferredItem<Item> GORBITE_SHOVEL = registerToolItem("gorbite_shovel", "Gorbite Shovel", () -> new JShovelItem(JToolTiers.GORBITE_SHOVEL, BASIC), ItemType.SHOVEL);
    public static final DeferredItem<Item> GORBITE_PICKAXE = registerToolItem("gorbite_pickaxe", "Gorbite Pickaxe", () -> new JPickaxeItem(JToolTiers.GORBITE_PICKAXE, BASIC), ItemType.PICKAXE);
    public static final DeferredItem<Item> GORBITE_AXE = registerToolItem("gorbite_axe", "Gorbite Axe", () -> new JAxeItem(JToolTiers.GORBITE_AXE, BASIC), ItemType.AXE);
    public static final DeferredItem<Item> GORBITE_HOE = registerToolItem("gorbite_hoe", "Gorbite Hoe", () -> new JHoeItem(JToolTiers.GORBITE_HOE, -8, BASIC), ItemType.HOE);
    public static final DeferredItem<Item> GORBITE_MULTITOOL = registerToolItem("gorbite_multitool", "Gorbite Multitool", () -> new MultitoolItem(JToolTiers.GORBITE_SWORD, BASIC), ItemType.MULTITOOL);
    public static final DeferredItem<Item> GORBITE_SWORD = registerToolItem("gorbite_sword", "Gorbite Sword", () -> new JSwordItem(JToolTiers.GORBITE_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> GORBITE_HELMET = registerNormalItem("gorbite_helmet", "Gorbite Helmet", () -> new JArmorItem(JToolTiers.JArmorTier.GORBITE, ArmorItem.Type.HELMET, null), ItemType.HELMET);
    public static final DeferredItem<Item> GORBITE_CHEST = registerNormalItem("gorbite_chestplate", "Gorbite Chestplate", () -> new JArmorItem(JToolTiers.JArmorTier.GORBITE, ArmorItem.Type.CHESTPLATE, null), ItemType.CHESTPLATE);
    public static final DeferredItem<Item> GORBITE_LEGS = registerNormalItem("gorbite_leggings", "Gorbite Leggings", () -> new JArmorItem(JToolTiers.JArmorTier.GORBITE, ArmorItem.Type.LEGGINGS, null), ItemType.LEGGINGS);
    public static final DeferredItem<Item> GORBITE_BOOTS = registerNormalItem("gorbite_boots", "Gorbite Boots", () -> new JArmorItem(JToolTiers.JArmorTier.GORBITE, ArmorItem.Type.BOOTS, null), ItemType.BOOTS);

    public static final DeferredItem<Item> RAW_ORBADITE = registerNormalItem("raw_orbadite", "Raw Orbadite");
    public static final DeferredItem<Item> ORBADITE_INGOT = registerNormalItem("orbadite_ingot", "Orbadite Ingot");
    public static final DeferredItem<Item> ORBADITE_SHIELD = registerShieldItem("orbadite_shield", "Orbadite Shield", () -> new JShieldItem("orbadite", 1200, ORBADITE_INGOT.get()));
    public static final DeferredItem<Item> ORBADITE_SHOVEL = registerToolItem("orbadite_shovel", "Orbadite Shovel", () -> new JShovelItem(JToolTiers.ORBADITE_SHOVEL, BASIC), ItemType.SHOVEL);
    public static final DeferredItem<Item> ORBADITE_PICKAXE = registerToolItem("orbadite_pickaxe", "Orbadite Pickaxe", () -> new JPickaxeItem(JToolTiers.ORBADITE_PICKAXE, BASIC), ItemType.PICKAXE);
    public static final DeferredItem<Item> ORBADITE_AXE = registerToolItem("orbadite_axe", "Orbadite Axe", () -> new JAxeItem(JToolTiers.ORBADITE_AXE, BASIC), ItemType.AXE);
    public static final DeferredItem<Item> ORBADITE_HOE = registerToolItem("orbadite_hoe", "Orbadite Hoe", () -> new JHoeItem(JToolTiers.ORBADITE_HOE, -8, BASIC), ItemType.HOE);
    public static final DeferredItem<Item> ORBADITE_MULTITOOL = registerToolItem("orbadite_multitool", "Orbadite Multitool", () -> new MultitoolItem(JToolTiers.ORBADITE_SWORD, BASIC), ItemType.MULTITOOL);
    public static final DeferredItem<Item> ORBADITE_SWORD = registerToolItem("orbadite_sword", "Orbadite Sword", () -> new JSwordItem(JToolTiers.ORBADITE_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> ORBADITE_HELMET = registerNormalItem("orbadite_helmet", "Orbadite Helmet", () -> new JArmorItem(JToolTiers.JArmorTier.ORBADITE, ArmorItem.Type.HELMET, null), ItemType.HELMET);
    public static final DeferredItem<Item> ORBADITE_CHEST = registerNormalItem("orbadite_chestplate", "Orbadite Chestplate", () -> new JArmorItem(JToolTiers.JArmorTier.ORBADITE, ArmorItem.Type.CHESTPLATE, null), ItemType.CHESTPLATE);
    public static final DeferredItem<Item> ORBADITE_LEGS = registerNormalItem("orbadite_leggings", "Orbadite Leggings", () -> new JArmorItem(JToolTiers.JArmorTier.ORBADITE, ArmorItem.Type.LEGGINGS, null), ItemType.LEGGINGS);
    public static final DeferredItem<Item> ORBADITE_BOOTS = registerNormalItem("orbadite_boots", "Orbadite Boots", () -> new JArmorItem(JToolTiers.JArmorTier.ORBADITE, ArmorItem.Type.BOOTS, null), ItemType.BOOTS);

    public static final DeferredItem<Item> SOULSTONE = registerNormalItem("soulstone", "Soulstone");
    public static final DeferredItem<Item> SOULSTONE_SHIELD = registerShieldItem("soulstone_shield", "Soulstone Shield", () -> new JShieldItem("soulstone", 1200, SOULSTONE.get()));
    public static final DeferredItem<Item> SOULSTONE_SHOVEL = registerToolItem("soulstone_shovel", "Soulstone Shovel", () -> new JShovelItem(JToolTiers.SOULSTONE_SHOVEL, BASIC), ItemType.SHOVEL);
    public static final DeferredItem<Item> SOULSTONE_PICKAXE = registerToolItem("soulstone_pickaxe", "Soulstone Pickaxe", () -> new JPickaxeItem(JToolTiers.SOULSTONE_PICKAXE, BASIC), ItemType.PICKAXE);
    public static final DeferredItem<Item> SOULSTONE_AXE = registerToolItem("soulstone_axe", "Soulstone Axe", () -> new JAxeItem(JToolTiers.SOULSTONE_AXE, BASIC), ItemType.AXE);
    public static final DeferredItem<Item> SOULSTONE_HOE = registerToolItem("soulstone_hoe", "Soulstone Hoe", () -> new JHoeItem(JToolTiers.SOULSTONE_HOE, -5, BASIC), ItemType.HOE);
    public static final DeferredItem<Item> SOULSTONE_MULTITOOL = registerToolItem("soulstone_multitool", "Soulstone Multitool", () -> new MultitoolItem(JToolTiers.SOULSTONE_SWORD, BASIC), ItemType.MULTITOOL);
    public static final DeferredItem<Item> SOULSTONE_SWORD = registerToolItem("soulstone_sword", "Soulstone Sword", () -> new JSwordItem(JToolTiers.SOULSTONE_SWORD, BASIC), ItemType.SWORD);

    public static final DeferredItem<Item> TWILIGHT_HELMET = registerNormalItem("twilight_helmet", "Twilight Helmet", () -> new JArmorItem(JToolTiers.JArmorTier.TWILIGHT, ArmorItem.Type.HELMET, null), ItemType.HELMET);
    public static final DeferredItem<Item> TWILIGHT_CHEST = registerNormalItem("twilight_chestplate", "Twilight Chestplate", () -> new JArmorItem(JToolTiers.JArmorTier.TWILIGHT, ArmorItem.Type.CHESTPLATE, null), ItemType.CHESTPLATE);
    public static final DeferredItem<Item> TWILIGHT_LEGS = registerNormalItem("twilight_leggings", "Twilight Leggings", () -> new JArmorItem(JToolTiers.JArmorTier.TWILIGHT, ArmorItem.Type.LEGGINGS, null), ItemType.LEGGINGS);
    public static final DeferredItem<Item> TWILIGHT_BOOTS = registerNormalItem("twilight_boots", "Twilight Boots", () -> new JArmorItem(JToolTiers.JArmorTier.TWILIGHT, ArmorItem.Type.BOOTS, null), ItemType.BOOTS);

    public static final DeferredItem<Item> FLAME_HELMET = registerNormalItem("flame_helmet", "Flame Helmet", () -> new JArmorItem(JToolTiers.JArmorTier.FLAME, ArmorItem.Type.HELMET, null), ItemType.HELMET);
    public static final DeferredItem<Item> FLAME_CHEST = registerNormalItem("flame_chestplate", "Flame Chestplate", () -> new JArmorItem(JToolTiers.JArmorTier.FLAME, ArmorItem.Type.CHESTPLATE, null), ItemType.CHESTPLATE);
    public static final DeferredItem<Item> FLAME_LEGS = registerNormalItem("flame_leggings", "Flame Leggings", () -> new JArmorItem(JToolTiers.JArmorTier.FLAME, ArmorItem.Type.LEGGINGS, null), ItemType.LEGGINGS);
    public static final DeferredItem<Item> FLAME_BOOTS = registerNormalItem("flame_boots", "Flame Boots", () -> new JArmorItem(JToolTiers.JArmorTier.FLAME, ArmorItem.Type.BOOTS, null), ItemType.BOOTS);

    public static final DeferredItem<Item> HOLLOW_HELMET = registerNormalItem("hollow_helmet", "Hollow Helmet", () -> new JArmorItem(JToolTiers.JArmorTier.HOLLOW, ArmorItem.Type.HELMET, null), ItemType.HELMET);
    public static final DeferredItem<Item> HOLLOW_CHEST = registerNormalItem("hollow_chestplate", "Hollow Chestplate", () -> new JArmorItem(JToolTiers.JArmorTier.HOLLOW, ArmorItem.Type.CHESTPLATE, null), ItemType.CHESTPLATE);
    public static final DeferredItem<Item> HOLLOW_LEGS = registerNormalItem("hollow_leggings", "Hollow Leggings", () -> new JArmorItem(JToolTiers.JArmorTier.HOLLOW, ArmorItem.Type.LEGGINGS, null), ItemType.LEGGINGS);
    public static final DeferredItem<Item> HOLLOW_BOOTS = registerNormalItem("hollow_boots", "Hollow Boots", () -> new JArmorItem(JToolTiers.JArmorTier.HOLLOW, ArmorItem.Type.BOOTS, null), ItemType.BOOTS);

    public static final DeferredItem<Item> CRYSTAL_FLAKE_HELMET = registerNormalItem("crystal_flake_helmet", "Crystal Flake Helmet", () -> new JArmorItem(JToolTiers.JArmorTier.CRYSTAL_FLAKE, ArmorItem.Type.HELMET, null), ItemType.HELMET);
    public static final DeferredItem<Item> CRYSTAL_FLAKE_CHEST = registerNormalItem("crystal_flake_chestplate", "Crystal Flake Chestplate", () -> new JArmorItem(JToolTiers.JArmorTier.CRYSTAL_FLAKE, ArmorItem.Type.CHESTPLATE, null), ItemType.CHESTPLATE);
    public static final DeferredItem<Item> CRYSTAL_FLAKE_LEGS = registerNormalItem("crystal_flake_leggings", "Crystal Flake Leggings", () -> new JArmorItem(JToolTiers.JArmorTier.CRYSTAL_FLAKE, ArmorItem.Type.LEGGINGS, null), ItemType.LEGGINGS);
    public static final DeferredItem<Item> CRYSTAL_FLAKE_BOOTS = registerNormalItem("crystal_flake_boots", "Crystal Flake Boots", () -> new JArmorItem(JToolTiers.JArmorTier.CRYSTAL_FLAKE, ArmorItem.Type.BOOTS, null), ItemType.BOOTS);

    public static final DeferredItem<Item> FROSTBITTEN_HELMET = registerNormalItem("frostbitten_helmet", "Frostbitten Helmet", () -> new JArmorItem(JToolTiers.JArmorTier.FROSTBITTEN, ArmorItem.Type.HELMET, null), ItemType.HELMET);
    public static final DeferredItem<Item> FROSTBITTEN_CHEST = registerNormalItem("frostbitten_chestplate", "Frostbitten Chestplate", () -> new JArmorItem(JToolTiers.JArmorTier.FROSTBITTEN, ArmorItem.Type.CHESTPLATE, null), ItemType.CHESTPLATE);
    public static final DeferredItem<Item> FROSTBITTEN_LEGS = registerNormalItem("frostbitten_leggings", "Frostbitten Leggings", () -> new JArmorItem(JToolTiers.JArmorTier.FROSTBITTEN, ArmorItem.Type.LEGGINGS, null), ItemType.LEGGINGS);
    public static final DeferredItem<Item> FROSTBITTEN_BOOTS = registerNormalItem("frostbitten_boots", "Frostbitten Boots", () -> new JArmorItem(JToolTiers.JArmorTier.FROSTBITTEN, ArmorItem.Type.BOOTS, null), ItemType.BOOTS);

    public static final DeferredItem<Item> HELLMETAL_HELMET = registerNormalItem("hellmetal_helmet", "Mask of Hellmetal", () -> new JArmorItem(JToolTiers.JArmorTier.HELLMETAL, ArmorItem.Type.HELMET, null), ItemType.HELMET);

    public static final DeferredItem<Item> STAFF_OF_CONJURING = registerToolItem("staff_of_conjuring", "Staff of Conjuring", () -> new StaffItem(1, ConjuringProjectileEntity::new));
    public static final DeferredItem<Item> STAFF_OF_ESSENCIA = registerToolItem("staff_of_essencia", "Staff of Essencia", () -> new StaffItem(4, EssenciaProjectileEntity::new));

    public static final DeferredItem<Item> GOLDEN_EUCA_BOAT = registerNormalItem("golden_euca_boat", "Gold Euca Boat", () -> new JBoatItem(JBoat.Type.GOLD_EUCA));
    public static final DeferredItem<Item> BROWN_EUCA_BOAT = registerNormalItem("brown_euca_boat", "Brown Euca Boat", () -> new JBoatItem(JBoat.Type.BROWN_EUCA));
    public static final DeferredItem<Item> FROZEN_BOAT = registerNormalItem("frozen_boat", "Frostwood Boat", () -> new JBoatItem(JBoat.Type.FROZEN));
    public static final DeferredItem<Item> DEPTHS_BOAT = registerNormalItem("depths_boat", "Depths Boat", () -> new JBoatItem(JBoat.Type.DEPTHS));
    public static final DeferredItem<Item> BURNED_BOAT = registerNormalItem("burned_boat", "Burned Boat", () -> new JBoatItem(JBoat.Type.BURNED));
    public static final DeferredItem<Item> CORBA_BOAT = registerNormalItem("corba_boat", "Corba Boat", () -> new JBoatItem(JBoat.Type.CORBA));
    public static final DeferredItem<Item> TERRANIAN_BOAT = registerNormalItem("terranian_boat", "Terranian Boat", () -> new JBoatItem(JBoat.Type.TERRANIA));
    public static final DeferredItem<Item> CLOUDIA_BOAT = registerNormalItem("cloudia_boat", "Cloudia Boat", () -> new JBoatItem(JBoat.Type.CLOUDIA));

    public static final DeferredItem<Item> EUCA_PIERCER = registerToolItem("euca_piercer", "Euca Piercer", () ->
            new PiercerItem(3, 10.0F), ItemType.PIERCER);

    public static final DeferredItem<Item> SUNSET_PIERCER = registerToolItem("sunset_piercer", "Sunset Piercer", () ->
            new PiercerItem( 3, 10.0F), ItemType.PIERCER);

    public static final DeferredItem<Item> NETHIC_PIERCER = registerToolItem("nethic_piercer", "Nethic Piercer", () ->
            new PiercerItem( 3, 10.0F), ItemType.PIERCER);

    public static final DeferredItem<Item> FROSTBITTEN_PIERCER = registerToolItem("frostbitten_piercer", "Frostbitten Piercer", () ->
            new PiercerItem( 3, 10.0F), ItemType.PIERCER);

    public static final DeferredItem<Item> FROZEN_PIERCER = registerToolItem("frozen_piercer", "Frozen Piercer", () ->
            new PiercerItem( 3, 10.0F), ItemType.PIERCER);

    public static final DeferredItem<Item> FROSTY_PIERCER = registerToolItem("frosty_piercer", "Frosty Piercer", () ->
            new PiercerItem(3, 10.0F), ItemType.PIERCER);

    public static final DeferredItem<Item> BOILING_PIERCER = registerToolItem("boiling_piercer", "Boiling Piercer", () ->
            new PiercerItem( 3, 10.0F), ItemType.PIERCER);

    public static final DeferredItem<Item> DEPTHS_PIERCER = registerToolItem("depths_piercer", "Depths Piercer", () ->
            new PiercerItem( 3, 10.0F), ItemType.PIERCER);

    public static final DeferredItem<Item> CORBA_PIERCER = registerToolItem("corba_piercer", "Corba Piercer", () ->
            new PiercerItem( 3, 10.0F), ItemType.PIERCER);

    public static final DeferredItem<Item> SKY_PIERCER = registerToolItem("sky_piercer", "Sky Piercer", () ->
            new PiercerItem( 3, 10.0F), ItemType.PIERCER);

    public static final DeferredItem<Item> CLOUD_PIERCER = registerToolItem("cloud_piercer", "Cloud Piercer", () ->
            new PiercerItem( 3, 10.0F), ItemType.PIERCER);

    public static final DeferredItem<Item> PIERCER = registerToolItem("piercer", "Piercer", () ->
            new PiercerItem(3, 3.0F), ItemType.PIERCER);

    public static final DeferredItem<Item> MOLTEN_KNIFE = registerToolItem("molten_knife", "Molten Knife", () ->
            new KnifeItem(itemProps(),  4.0F), ItemType.SWORD);

    public static final DeferredItem<Item> SIZZLING_KNIFE = registerToolItem("sizzling_knife", "Sizzling Knife", () ->
            new KnifeItem(itemProps(), 4.0F), ItemType.SWORD);

    public static final DeferredItem<Item> IRON_THROWING_KNIFE = registerToolItem("iron_throwing_knife", "Iron Throwing Knife", () ->
            new KnifeItem(itemProps(),  3.0F), ItemType.SWORD);

    public static final DeferredItem<Item> ROYAL_KNIFE = registerToolItem("royal_knife", "Royal Knife", () ->
            new KnifeItem(itemProps(),  3.0F), ItemType.SWORD);

    public static final DeferredItem<Item> AQUATIC_KNIFE = registerToolItem("aquatic_knife", "Aquatic Knife", () ->
            new KnifeItem(itemProps(),  3.0F), ItemType.SWORD);

    public static final DeferredItem<Item> CHARRED_KNIFE = registerToolItem("charred_knife", "Charred Knife", () ->
            new KnifeItem(itemProps(),  3.0F), ItemType.SWORD);

    public static final DeferredItem<Item> BLOOD_KNIFE = registerToolItem("blood_knife", "Blood Knife", () ->
            new KnifeItem(itemProps(),  3.0F), ItemType.SWORD);

    public static final DeferredItem<Item> MUD_BALL = registerNormalItem("mud_ball", "Mud Ball", () -> new ThrowableItem(itemProps(),
            (world, thrower) -> new FloroMudEntity(JEntities.FLORO_MUD_TYPE.get(), world, thrower, 0.0F)).setSound(() -> SoundEvents.SNOWBALL_THROW));

    public static final DeferredItem<Item> DEMONIC_BOMB = registerNormalItem("demonic_bomb", "Demonic Bomb", () -> new ThrowableItem(itemProps(),
            (w, thrower) -> new DemonicBombEntity(JEntities.DEMONIC_BOMB_TYPE.get(), w, thrower, 4F)).setSound(() -> SoundEvents.SNOWBALL_THROW));

    public static final DeferredItem<Item> FIRE_BOMB = registerNormalItem("fire_bomb", "Fire Bomb", () -> new ThrowableItem(itemProps(),
            (w, thrower) -> new FireBombEntity(JEntities.FIRE_BOMB_TYPE.get(), w, thrower, 6F)).setSound(() -> SoundEvents.SNOWBALL_THROW));

    public static final DeferredItem<Item> MAGIC_BOMB = registerNormalItem("magic_bomb", "Magic Bomb", () -> new MagicBombItem(itemProps()).setSound(() -> SoundEvents.SNOWBALL_THROW));

    public static final DeferredItem<Item> MAGIC_POT_OF_DESTRUCTION = registerNormalItem("magic_pot_of_destruction", "Magic Pot of Destruction", () -> new MagicPotItem(itemProps().durability(128)));

    public static final DeferredItem<Item> ESSENCE_ARROW = registerNormalItem("essence_arrow", "Essence Arrow", EssenceArrowItem::new);

    //OVERWORLD BOWS
    public static final DeferredItem<Item> FLAME_BOW = registerBowItem("flame_bow", "Flame Bow", () -> new JBowItem(2, 368, EnumSet.of(EssenceArrowEntity.BowEffects.FLAME), JBowItem.DEFAULT_DURATION));
    public static final DeferredItem<Item> POISON_BOW = registerBowItem("poison_bow", "Poison Bow", () -> new JBowItem(2, 368, EnumSet.of(EssenceArrowEntity.BowEffects.POISON), JBowItem.DEFAULT_DURATION));
    public static final DeferredItem<Item> DARKNESS_BOW = registerBowItem("darkness_bow", "Darkness Bow", () -> new JBowItem(2, 368, EnumSet.of(EssenceArrowEntity.BowEffects.WITHER), JBowItem.DEFAULT_DURATION));
    public static final DeferredItem<Item> FROZEN_BOW = registerBowItem("frozen_bow", "Frozen Bow", () -> new JBowItem(2, 368, EnumSet.of(EssenceArrowEntity.BowEffects.SLOWNESS), JBowItem.DEFAULT_DURATION));

    //FROZEN BOWS
    public static final DeferredItem<Item> FROSTBITTEN_BOW = registerBowItem("frostbitten_bow", "Frostbitten Bow", () -> new JBowItem(2.5F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.SLOWNESS), 64000));
    public static final DeferredItem<Item> FROSTY_BOW = registerBowItem("frosty_bow", "Frosty Bow", () -> new JBowItem(2.5F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.SLOWNESS), 64000));

    //NETHER BOWS
    public static final DeferredItem<Item> STARING_BOW = registerBowItem("staring_bow", "Staring Bow", () -> new JBowItem(2.5F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.FLAME), 56000));

    //BOIL BOWS
    public static final DeferredItem<Item> CHARRED_BOW = registerBowItem("charred_bow", "Charred Bow", () -> new JBowItem(2.75F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.WITHER), 64000));
    public static final DeferredItem<Item> FLAMING_BOW = registerBowItem("flaming_bow", "Flaming Bow", () -> new JBowItem(2.75F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.FLAME), 64000));
    public static final DeferredItem<Item> BLAZING_BOW = registerBowItem("blazing_bow", "Blazing Bow", () -> new JBowItem(2.75F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.FLAME), 56000));

    //EUCA BOWS
    public static final DeferredItem<Item> MANTLE_BOW = registerBowItem("mantle_bow", "Mantle Bow", () -> new JBowItem(3.5F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.FLAME), 48000));
    public static final DeferredItem<Item> CORE_EXPANDER = registerBowItem("core_expander", "Core Expander", () -> new JBowItem(2.5F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.CONSUMES_ESSENCE, EssenceArrowEntity.BowEffects.FLAME), JBowItem.DEFAULT_DURATION).setEssenceUse(3));
    public static final DeferredItem<Item> ROYAL_BOW = registerBowItem("royal_bow", "Royal Bow", () -> new JBowItem(3F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.WITHER), 64000));

    //DEPTHS BOWS
    public static final DeferredItem<Item> DARK_ENFORCER = registerBowItem("dark_enforcer", "Dark Enforcer", () -> new JBowItem(5F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.CONSUMES_ESSENCE), 64000).setEssenceUse(3));
    public static final DeferredItem<Item> DEPTHS_BOW = registerBowItem("depths_bow", "Depths Bow", () -> new JBowItem(4F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.CONSUMES_ESSENCE), 32000).setEssenceUse(3));
    public static final DeferredItem<Item> ROCS_WING = registerBowItem("rocs_wing", "Roc's Wing", () -> new JBowItem(4F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.WITHER), 54000));
    public static final DeferredItem<Item> SCALE_BOW = registerBowItem("scale_bow", "Scale Bow", () -> new JBowItem(4F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.SLOWNESS), 54000));

    //CORBA BOWS
    public static final DeferredItem<Item> LOGGERS_BOW = registerBowItem("loggers_bow", "Logger's Bow", () -> new JBowItem(4.5F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.POISON), 48000));
    public static final DeferredItem<Item> OVERGROWN_BOW = registerBowItem("overgrown_bow", "Overgrown Bow", () -> new JBowItem(4.5F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.POISON), 54000));
    public static final DeferredItem<Item> OVERSEER_BOW = registerBowItem("overseer_bow", "Overseer Bow", () -> new JBowItem(4.5F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.WITHER), 38000));
    public static final DeferredItem<Item> WOODLAND_BOW = registerBowItem("woodland_bow", "Woodland Bow", () -> new JBowItem(4.5F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.POISON), 54000));

    //TERRANIA BOWS
    public static final DeferredItem<Item> DARK_TERRA_BOW = registerBowItem("dark_terra_bow", "Dark Terra Bow", () -> new JBowItem(5F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.WITHER), 48000));
    public static final DeferredItem<Item> LAVENDER_BOW = registerBowItem("lavender_bow", "Lavender Bow", () -> new JBowItem(5F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.SLOWNESS), 36000));
    public static final DeferredItem<Item> TERRALIGHT_BOW = registerBowItem("terralight_bow", "Terralight Bow", () -> new JBowItem(5F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.CONSUMES_ESSENCE), 32000).setEssenceUse(2));
    public static final DeferredItem<Item> TERRIAN_BOW = registerBowItem("terrian_bow", "Terrian Bow", () -> new JBowItem(5F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.POISON), 40000));

    //CLOUDIA BOWS
    public static final DeferredItem<Item> STARLIGHT_BOW = registerBowItem("starlight_bow", "Starlight Bow", () -> new JBowItem(5.5F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.POISON), 24000));
    public static final DeferredItem<Item> FLUFFY_BOW = registerBowItem("fluffy_bow", "Fluffy Bow", () -> new JBowItem(5.5F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.SLOWNESS), 18000));
    public static final DeferredItem<Item> GOLEM_BOW = registerBowItem("golem_bow", "Golem Bow", () -> new JBowItem(5.5F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.WITHER), 36000));

    //MISC BOWS
    public static final DeferredItem<Item> DEATH_PIERCER_BOW = registerBowItem("death_piercer_bow", "Death Piercer Bow", () -> new JBowItem(3F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.WITHER), JBowItem.DEFAULT_DURATION));
    public static final DeferredItem<Item> FUSION_BOW = registerBowItem("fusion_bow", "Fusion Bow", () -> new JBowItem(3F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.WITHER), JBowItem.DEFAULT_DURATION));
    public static final DeferredItem<Item> SPRING_BOW = registerBowItem("spring_bow", "Spring Bow", () -> new JBowItem(5F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.FLAME), JBowItem.DEFAULT_DURATION));
    public static final DeferredItem<Item> WASTEFUL_BOW = registerBowItem("wasteful_bow", "Wasteful Bow", () -> new JBowItem(3F, 384, EnumSet.of(EssenceArrowEntity.BowEffects.DOUBLE_ARROW), JBowItem.DEFAULT_DURATION));

    public static final DeferredItem<Item> POISON_SWORD = registerToolItem("poison_sword", "Poison Sword", () -> new JSwordItem(JToolTiers.POISON_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> CLOUD_SLICER = registerToolItem("cloud_slicer", "Cloud Slicer", () -> new JSwordItem(JToolTiers.CLOUD_SLICER, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> DRAGONS_TOOTH = registerToolItem("dragons_tooth", "Dragons Tooth", () -> new JSwordItem(JToolTiers.DRAGONS_TOOTH, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> WITHERING_BEAST_SWORD = registerToolItem("withering_beast_sword", "Witheringbeast Sword", () -> new JSwordItem(JToolTiers.WITHERING_BEAST_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> CALCIA_SWORD = registerToolItem("calcia_sword", "Calcia Sword", () -> new JSwordItem(JToolTiers.CALCIA_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> CHAMPIONS_SWORD = registerToolItem("champions_sword", "Champions Sword", () -> new JSwordItem(JToolTiers.CHAMPIONS_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> THE_WRAITH = registerToolItem("the_wraith", "The Wraith", () -> new JSwordItem(JToolTiers.THE_WRAITH, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> BUBBLE_SWORD = registerToolItem("bubble_sword", "Bubble Sword", () -> new JSwordItem(JToolTiers.BUBBLE_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> BOILING_BLADE = registerToolItem("boiling_blade", "Boiling Blade", () -> new JSwordItem(JToolTiers.BOILING_BLADE, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> LOGGERS_SWORD = registerToolItem("loggers_sword", "Loggers Sword", () -> new JSwordItem(JToolTiers.LOGGERS_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> NATURES_BLADE = registerToolItem("natures_blade", "Natures Blade", () -> new JSwordItem(JToolTiers.NATURES_BLADE, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> DEPTHS_DARKSWORD = registerToolItem("depths_dark_sword", "Depths Darksword", () -> new JSwordItem(JToolTiers.DEPTHS_DARKSWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> DEPTHS_SLAYER = registerToolItem("depths_slayer", "Depths Slayer", () -> new JSwordItem(JToolTiers.DEPTHS_SLAYER, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> SNOW_SHOVELER = registerToolItem("snow_shoveler", "Snow Shoveler", () -> new JSwordItem(JToolTiers.SNOW_SHOVELER, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> FROSTY_SWORD = registerToolItem("frosty_sword", "Frosty Sword", () -> new JSwordItem(JToolTiers.FROSTY_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> FROSTBITTEN_SWORD = registerToolItem("frostbitten_sword", "Frostbitten Sword", () -> new JSwordItem(JToolTiers.FROSTBITTEN_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> TREE_HUGGER = registerToolItem("tree_hugger", "Tree Hugger", () -> new JSwordItem(JToolTiers.TREE_HUGGER, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> CORE_MENDER = registerToolItem("core_mender", "Core Mender", () -> new JSwordItem(JToolTiers.CORE_MENDER, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> ROYAL_BLADE = registerToolItem("royal_blade", "Royal Blade", () -> new JSwordItem(JToolTiers.ROYAL_BLADE, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> ROYAL_STABBER = registerToolItem("royal_stabber", "Royal Stabber", () -> new JSwordItem(JToolTiers.ROYAL_STABBER, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> ROC_SWORD = registerToolItem("roc_sword", "Roc Sword", () -> new JSwordItem(JToolTiers.ROC_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> SWORD_THUNDERBIRD = registerToolItem("sword_of_the_thunderbird", "Sword of the Thunderbird", () -> new JSwordItem(JToolTiers.SWORD_THUNDERBIRD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> BLOODWIELD_SWORD = registerToolItem("blood_wield_sword", "Blood Wielder", () -> new JSwordItem(JToolTiers.BLOODWIELD_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> CHARRED_BLADE = registerToolItem("charred_blade", "Charred Blade", () -> new JSwordItem(JToolTiers.CHARRED_BLADE, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> SIZZLER_SWORD = registerToolItem("sizzler_sword", "Sizzler Sword", () -> new JSwordItem(JToolTiers.SIZZLER_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> FLUFFY_BLADE = registerToolItem("fluffy_blade", "Fluffy Blade", () -> new JSwordItem(JToolTiers.FLUFFY_BLADE, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> GOLEM_SWORD = registerToolItem("golem_sword", "Golem Sword", () -> new JSwordItem(JToolTiers.GOLEM_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> THUNDERBLADE = registerToolItem("thunder_blade", "Thunder Blade", () -> new JSwordItem(JToolTiers.THUNDERBLADE, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> SENTRY_SWORD = registerToolItem("sentry_sword", "Sentry Sword", () -> new JSwordItem(JToolTiers.SENTRY_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> CRYSTAL_BLADE = registerToolItem("crystal_blade", "Crystal Blade", () -> new JSwordItem(JToolTiers.CRYSTAL_BLADE, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> STARLIGHT_BLADE = registerToolItem("starlight_blade", "Starlight Blade", () -> new JSwordItem(JToolTiers.STARLIGHT_BLADE, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> PEDAL_SWORD = registerToolItem("pedal_sword", "Pedal Sword", () -> new JSwordItem(JToolTiers.PEDAL_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> WITHIC_BLADE = registerToolItem("withic_blade", "Withic Blade", () -> new JSwordItem(JToolTiers.WITHIC_BLADE, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> REINFORCED_CRYSTAL_SWORD = registerToolItem("reinforced_crystal_sword", "Reinforced Crystal Sword", () -> new JSwordItem(JToolTiers.RE_CRYSTAL_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> REINFORCED_STONE_SWORD = registerToolItem("reinforced_stone_sword", "Reinforced Stone Sword", () -> new JSwordItem(JToolTiers.RE_STONE_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> TERRALIGHT_BLADE = registerToolItem("terralight_blade", "Terralight Blade", () -> new JSwordItem(JToolTiers.TERRALIGHT_BLADE, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> TERRANA_SWORD = registerToolItem("terrana_sword", "Terrana Sword", () -> new JSwordItem(JToolTiers.TERRANA_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> TERROLICA_SWORD = registerToolItem("terrolica_sword", "Terrolica Sword", () -> new JSwordItem(JToolTiers.TERROLICA_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> VOLITE_SWORD = registerToolItem("volite_sword", "Volite Sword", () -> new JSwordItem(JToolTiers.VOLITE_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> KINGS_SWORD = registerToolItem("kings_sword", "Kings Sword", () -> new JSwordItem(JToolTiers.KINGS_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> DEMONIC_SWORD = registerToolItem("demonic_sword", "Wither Sword", () -> new JSwordItem(JToolTiers.DEMONIC_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> VINESTRAND_BLADE = registerToolItem("vinestrand_blade", "Vinestrand Blade", () -> new JSwordItem(JToolTiers.VINESTRAND_BLADE, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> DARK_PINE_SWORD = registerToolItem("dark_pine_sword", "Dark Pine Sword", () -> new JSwordItem(JToolTiers.DARK_PINE_SWORD, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> HEALERS_BLADE = registerToolItem("healers_blade", "Healers Blade", () -> new JSwordItem(JToolTiers.HEALERS_BLADE, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> TERRONIC_BLADE = registerToolItem("terronic_blade", "Terronic Blade", () -> new JSwordItem(JToolTiers.TERRONIC_BLADE, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> DARK_KEEPERS_BLADE = registerToolItem("blade_of_dark_keeper", "Dark Keepers Blade", () -> new JSwordItem(JToolTiers.DARK_KEEPER, BASIC), ItemType.SWORD);
    public static final DeferredItem<Item> DEVELOPER_SWORD = registerToolItem("developer_sword", "Creative Sword", () -> new JSwordItem(JToolTiers.DEVELOPER_SWORD, BASIC), ItemType.SWORD);

    public static final DeferredItem<Item> EYE_OF_THE_BLIZZARD = registerNormalItem("eye_of_the_blizzard", "Eye Of The Blizzard", () -> new JCurioItem(itemProps().stacksTo(1)).overview(true));
    public static final DeferredItem<Item> FROSTBORN_SOUL = registerNormalItem("frostborn_soul", "Frostborn Soul");
    public static final DeferredItem<Item> WITHICSPINE = registerNormalItem("withicspine", "Withic Spine");
    public static final DeferredItem<Item> BOIL_POWDER = registerNormalItem("boil_powder", "Boil Powder");
    public static final DeferredItem<Item> GOLEM_CHUNK = registerNormalItem("golem_chunk", "Golem Chunk");
    public static final DeferredItem<Item> FLUFFY_FEATHER = registerNormalItem("fluffy_feather", "Fluffy Feather");
    public static final DeferredItem<Item> CLOUDIA_ORB = registerNormalItem("cloudia_orb", "Cloudia Orb");

    public static final DeferredItem<Item> HEART_CONTAINER_SMALL = registerNormalItem("heart_container_small", "Heart Container", () -> new HeartContainerItem(itemProps().stacksTo(1)).health(1));
    public static final DeferredItem<Item> HEART_CONTAINER_MEDIUM = registerNormalItem("heart_container_medium", "Heart Container", () -> new HeartContainerItem(itemProps().stacksTo(1)).health(4));
    public static final DeferredItem<Item> HEART_CONTAINER_LARGE = registerNormalItem("heart_container_large", "Heart Container", () -> new HeartContainerItem(itemProps().stacksTo(1)).health(8));
    public static final DeferredItem<Item> HEART_CONTAINER_ULTIMATE = registerNormalItem("heart_container_ultimate", "Heart Container", () -> new HeartContainerItem(itemProps().stacksTo(1)).health(16));

    public static final DeferredItem<Item> RING_OF_POISON = registerNormalItem("ring_of_poison", "Ring of Poison", () -> new JRingItem(itemProps().stacksTo(1)).effect(MobEffects.POISON));
    public static final DeferredItem<Item> RING_OF_BLINDNESS = registerNormalItem("ring_of_blindness", "Ring of Blindness", () -> new JRingItem(itemProps().stacksTo(1)).effect(MobEffects.BLINDNESS));
    public static final DeferredItem<Item> RING_OF_HARMING = registerNormalItem("ring_of_harming", "Ring of Harming", () -> new JRingItem(itemProps().stacksTo(1)).effect(MobEffects.HARM));
    public static final DeferredItem<Item> RING_OF_MINING_FATIGUE = registerNormalItem("ring_of_mining_fatigue", "Ring of Mining Fatigue", () -> new JRingItem(itemProps().stacksTo(1)).effect(MobEffects.DIG_SLOWDOWN));
    public static final DeferredItem<Item> RING_OF_NAUSEA = registerNormalItem("ring_of_nausea", "Ring of Nausea", () -> new JRingItem(itemProps().stacksTo(1)).effect(MobEffects.CONFUSION));
    public static final DeferredItem<Item> RING_OF_SLOWNESS = registerNormalItem("ring_of_slowness", "Ring of Slowness", () -> new JRingItem(itemProps().stacksTo(1)).effect(MobEffects.MOVEMENT_SLOWDOWN));
    public static final DeferredItem<Item> RING_OF_WITHERING = registerNormalItem("ring_of_withering", "Ring of Withering", () -> new JRingItem(itemProps().stacksTo(1)).effect(MobEffects.WITHER));
    public static final DeferredItem<Item> SKULL_OF_DECAY = registerNormalItem("skull_of_decay", "Skull Of Decay", () -> new JItem(JItems.itemProps().stacksTo(1).durability(100)));

    public static final DeferredItem<Item> LOOT_POUCH = registerNormalItem("loot_pouch", "Loot Pouch", () -> new LootItem(LootItem.LootTier.NORMAL, Rarity.COMMON));
    public static final DeferredItem<Item> GOLD_LOOT_POUCH = registerNormalItem("gold_loot_pouch", "Loot Pouch", () -> new LootItem(LootItem.LootTier.GOLD, Rarity.UNCOMMON));
    public static final DeferredItem<Item> DIAMOND_LOOT_POUCH = registerNormalItem("diamond_loot_pouch", "Loot Pouch", () -> new LootItem(LootItem.LootTier.DIAMOND, Rarity.RARE));
    public static final DeferredItem<Item> FROSTY_GIFT = registerNormalItem("frosty_gift", "Frosty Gift", () -> new LootItem(LootItem.LootTier.FROSTY_GIFT, Rarity.COMMON));

    public static final DeferredItem<Item> CLOUDWALKER_AMULET = registerNormalItem("cloudwalker_amulet", "Cloudwalker Amulet", () -> new CloudwalkingAmuletItem(itemProps()));
    public static final DeferredItem<Item> DYNASTER_AMULET = registerNormalItem("dynaster_amulet", "Amulet of the Dynaster", () -> new DynasterAmuletItem(itemProps().stacksTo(1)));
    public static final DeferredItem<Item> ICE_AMULET = registerNormalItem("ice_amulet", "Amulet of Glacial Bloodlust", () -> new IceAmuletItem(itemProps().stacksTo(1)));

    public static final DeferredItem<Item> VERY_WEAK_ESSENCE_CRYSTAL = registerNormalItem("very_weak_essence_crystal", "Very Weak Essence Crystal", () -> new EssenceCatalystItem(itemProps().stacksTo(1)).essence(1F));
    public static final DeferredItem<Item> WEAK_ESSENCE_CRYSTAL = registerNormalItem("weak_essence_crystal", "Weak Essence Crystal", () -> new EssenceCatalystItem(itemProps().stacksTo(1)).essence(2.5F));
    public static final DeferredItem<Item> STRONG_ESSENCE_CRYSTAL = registerNormalItem("strong_essence_crystal", "Strong Essence Crystal", () -> new EssenceCatalystItem(itemProps().stacksTo(1)).essence(5F));
    public static final DeferredItem<Item> VERY_STRONG_ESSENCE_CRYSTAL = registerNormalItem("very_strong_essence_crystal", "Very Strong Essence Crystal", () -> new EssenceCatalystItem(itemProps().stacksTo(1)).essence(10F));

    public static final DeferredItem<Item> BREATHING_STONE = registerNormalItem("breathing_stone", "Breathing Stone", () -> new EssenceRegenCatalystItem(itemProps().stacksTo(1)).speed(0.0112F));

    public static final DeferredItem<Item> BROKEN_OKOLOO_CLUB = register("broken_okoloo_club", "Broken Okoloo Club", BossSpawnerItem::new, ItemType.MODEL);
    public static final DeferredItem<Item> WITHERING_BEAST_ORB = registerNormalItem("withering_beast_orb", "Withering Beast Orb", BossSpawnerItem::new);
    public static final DeferredItem<Item> CALCIA_ORB = registerNormalItem("calcia_orb", "Calcia Orb", BossSpawnerItem::new);
    public static final DeferredItem<Item> SOUL_WATCHER_ORB = registerNormalItem("soul_watcher_orb", "Soul Watcher Orb", BossSpawnerItem::new);
    public static final DeferredItem<Item> EUDOR_CROWN = registerNormalItem("eudor_crown", "Eudor's Crown", BossSpawnerItem::new);
    public static final DeferredItem<Item> CORALLATOR_ORB = registerNormalItem("corallator_orb", "Corallator Orb", BossSpawnerItem::new);
    public static final DeferredItem<Item> BLAZIER_ORB = registerNormalItem("blazier_orb", "Blazier Orb", BossSpawnerItem::new);
    public static final DeferredItem<Item> THUNDER_BIRD_ORB = registerNormalItem("thunder_bird_orb", "Thunderbird Orb", BossSpawnerItem::new);
    public static final DeferredItem<Item> SCALE_ORB = registerNormalItem("scale_orb", "Scale Orb", BossSpawnerItem::new);
    public static final DeferredItem<Item> LOGGER_ORB = registerNormalItem("logger_orb", "Logger Orb", BossSpawnerItem::new);
    public static final DeferredItem<Item> SENTRY_KING_ORB = registerNormalItem("sentry_king_orb", "Sentry King Orb", BossSpawnerItem::new);
    public static final DeferredItem<Item> MYSTERIOUS_DISK = registerNormalItem("mysterious_disk", "Mysterious Disk", BossSpawnerItem::new);
    public static final DeferredItem<Item> ENCHANTED_TERRASTAR = registerNormalItem("enchanted_terrastar", "Enchanted Terrastar", BossSpawnerItem::new);

    public static final DeferredItem<Item> SENTACOIN = registerNormalItem("sentacoin", "Sentacoin", () -> new SentacoinItem(Sentacoin.Type.COIN));
    public static final DeferredItem<Item> SENTACOIN_BAG = register("sentacoin_bag", "Sentacoin Bag", () -> new SentacoinItem(Sentacoin.Type.BAG), ItemType.MODEL);
    public static final DeferredItem<Item> SENTRY_OBSERVER = registerNormalItem("sentry_observer", "Sentry Observer");

    public static final DeferredItem<Item> RECIPE_BOOK = registerNormalItem("recipe_book", "Recipe Book", JRecipeBook::new);

    public static final DeferredItem<Item> UNDERWATER_WORLD_RECORD = registerRecord("underwater_world_record", "Blue Water", JSounds.UNDERWATER_WORLD_JUKEBOX);
    public static final DeferredItem<Item> GOLD_PLAINS_RECORD = registerRecord("gold_plains_record", "Gold Plains", JSounds.GOLD_PLAINS_JUKEBOX);
    public static final DeferredItem<Item> EUCA_RECORD_1 = registerRecord("euca_record_1", "Euca", JSounds.EUCA_DISC_1_JUKEBOX);
    public static final DeferredItem<Item> EUCA_RECORD_2 = registerRecord("euca_record_2", "Euca", JSounds.EUCA_DISC_2_JUKEBOX);
    public static final DeferredItem<Item> EUCA_RECORD_3 = registerRecord("euca_record_3", "Euca", JSounds.EUCA_DISC_3_JUKEBOX);
    public static final DeferredItem<Item> FROZEN_RECORD_1 = registerRecord("frozen_record_1", "Frozen", JSounds.FROZEN_DISC_1_JUKEBOX);
    public static final DeferredItem<Item> BOIL_RECORD_1 = registerRecord("boil_record_1", "Sizzle Record", JSounds.BOIL_DISC_1_JUKEBOX);

    private static DeferredItem<Item> registerNormalItem(String name, String translatedName) {
        return register(name, translatedName, () -> new JItem(new Item.Properties()), ItemType.ITEM);
    }

    private static DeferredItem<Item> registerFoodItem(String name, String translatedName, boolean foil, FoodProperties props) {
        return register(name, translatedName, foil ? () -> new FoilItem(JItems.foodProps(props)) : () -> new JItem(JItems.foodProps(props)), ItemType.ITEM);
    }

    private static DeferredItem<Item> registerFoodItem(String name, String translatedName, FoodProperties props) {
        return registerFoodItem(name, translatedName, false, props);
    }

    private static DeferredItem<Item> registerFuelItem(String name, String translatedName, int burnTime /* 200 ticks per item */) {
        return register(name, translatedName, () -> new JItem(new Item.Properties()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return burnTime;
            }
        }, ItemType.ITEM);
    }

    private static DeferredItem<Item> registerNormalItem(String name, String translatedName, Supplier<Item> item, ItemType type) {
        switch(type) {
            case HELMET -> helmetName.add(name);
            case CHESTPLATE -> chestplateName.add(name);
            case LEGGINGS -> leggingsName.add(name);
            case BOOTS -> bootsName.add(name);
        }
        return registerNormalItem(name, translatedName, item);
    }

    private static DeferredItem<Item> registerNormalItem(String name, String translatedName, Supplier<Item> item) {
        return register(name, translatedName, item, ItemType.ITEM);
    }

    private static DeferredItem<Item> registerToolItem(String name, String translatedName, Supplier<Item> tool, ItemType type) {
        switch(type) {
            case SWORD -> swordName.add(name);
            case PICKAXE -> pickaxeName.add(name);
            case AXE -> axeName.add(name);
            case SHOVEL -> shovelName.add(name);
            case HOE -> hoeName.add(name);
            case MULTITOOL -> {
                pickaxeName.add(name);
                axeName.add(name);
                shovelName.add(name);
                hoeName.add(name);
            }
            case PIERCER -> piercerName.add(name);
        }
        return registerToolItem(name, translatedName, tool);
    }

    private static DeferredItem<Item> registerToolItem(String name, String translatedName, Supplier<Item> tool) {
        return register(name, translatedName, tool, ItemType.TOOL);
    }

    private static DeferredItem<Item> registerBowItem(String name, String translatedName, Supplier<Item> bow) {
        return register(name, translatedName, bow, ItemType.BOW);
    }

    private static DeferredItem<Item> registerShieldItem(String name, String translatedName, Supplier<Item> item) {
        return register(name, translatedName, item, ItemType.SHIELD);
    }

    private static DeferredItem<Item> register(String name, String translatedName) {
        return register(name, translatedName, () -> new Item(new Item.Properties()), ItemType.ITEM);
    }

    private static DeferredItem<Item> registerRecord(String name, String descTranslated, DeferredHolder<JukeboxSong, JukeboxSong> sound) {
        recordDescName.add("item.jitl." + name + ".desc");
        recordDescLangName.add(descTranslated);
        return register(name, "Journey Record", () -> new Item(itemProps().stacksTo(1).jukeboxPlayable(sound.getKey())), ItemType.RECORD);
    }

    public static DeferredItem<Item> register(String name, String translatedName, Supplier<Item> item, ItemType type) {
        if(type == ItemType.TOOL) {
            toolName.add(name);
            toolLangName.add(translatedName);
        }
        if(type == ItemType.ITEM) {
            itemName.add(name);
            langName.add(translatedName);
        }
        if(type == ItemType.RECORD) {
            recordName.add(name);
            recordLangName.add(translatedName);
        }
        if(type == ItemType.MODEL) {
            modelName.add(name);
            modelLangName.add(translatedName);
        }
        if(type == ItemType.SPAWN_EGG) {
            spawnName.add(name);
            spawnLangName.add(translatedName);
        }
        if(type == ItemType.BOW) {
            bowName.add(name);
            bowLangName.add(translatedName);
        }
        if(type == ItemType.SHIELD) {
            shieldName.add(name);
            shieldLangName.add(translatedName);
        }
        return register(name, item);
    }

    private static DeferredItem<Item> register(String name, Supplier<Item> item) {
        return ITEMS.register(name, item);
    }

    public static void registerBlockItem(String name, Supplier<Item> item) {
        BLOCK_ITEMS.register(name, item);
    }

    public static Item.Properties itemProps() {
        return new Item.Properties();
    }

    private static Item.Properties foodProps(FoodProperties props) {
        return new Item.Properties().food(props);
    }

    public enum ItemType {
        ITEM,
        RECORD,
        SPAWN_EGG,
        MODEL,
        TOOL,
        BOW,
        SHIELD,
        PIERCER,

        SWORD,
        PICKAXE,
        AXE,
        SHOVEL,
        HOE,
        MULTITOOL,

        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS
    }
}