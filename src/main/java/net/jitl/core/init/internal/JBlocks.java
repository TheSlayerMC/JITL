package net.jitl.core.init.internal;

import net.jitl.common.block.*;
import net.jitl.common.block.base.*;
import net.jitl.common.block.crop.*;
import net.jitl.common.block.crop.bushs.BradberryBushBlock;
import net.jitl.common.block.crop.bushs.RedcurrantBushBlock;
import net.jitl.common.block.portal.*;
import net.jitl.common.block.spawners.*;
import net.jitl.common.world.dimension.Dimensions;
import net.jitl.common.world.gen.tree_grower.JTreeGrower;
import net.jitl.core.data.block_generation.JBlockCropGenerator;
import net.jitl.core.data.block_generation.JBlockModeledCropGenerator;
import net.jitl.core.init.JITL;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.function.Supplier;

public class JBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(JITL.MODID);

    public static final ArrayList<String> modelBlockName = new ArrayList<>();
    public static final ArrayList<String> modelLangName = new ArrayList<>();
    public static final ArrayList<String> rotatableBlockName = new ArrayList<>();
    public static final ArrayList<String> rotatableLangName = new ArrayList<>();
    public static final ArrayList<String> furnaceBlockName = new ArrayList<>();
    public static final ArrayList<String> furnaceLangName = new ArrayList<>();
    public static final ArrayList<String> normalBlockName = new ArrayList<>();
    public static final ArrayList<String> normalLangName = new ArrayList<>();
    public static final ArrayList<String> tintedLeavesBlockName = new ArrayList<>();
    public static final ArrayList<String> tintedLeavesLangName = new ArrayList<>();
    public static final ArrayList<String> logBlockName = new ArrayList<>();
    public static final ArrayList<String> logLangName = new ArrayList<>();
    public static final ArrayList<String> doorBlockName = new ArrayList<>();
    public static final ArrayList<String> doorLangName = new ArrayList<>();
    public static final ArrayList<String> trapDoorBlockName = new ArrayList<>();
    public static final ArrayList<String> trapDoorLangName = new ArrayList<>();
    public static final ArrayList<String> stairsBlockName = new ArrayList<>();
    public static final ArrayList<String> stairsLangName = new ArrayList<>();
    public static final ArrayList<String> slabBlockName = new ArrayList<>();
    public static final ArrayList<String> slabLangName = new ArrayList<>();
    public static final ArrayList<String> buttonBlockName = new ArrayList<>();
    public static final ArrayList<String> buttonLangName = new ArrayList<>();
    public static final ArrayList<String> pressurePlateBlockName = new ArrayList<>();
    public static final ArrayList<String> pressurePlateLangName = new ArrayList<>();
    public static final ArrayList<String> gateBlockName = new ArrayList<>();
    public static final ArrayList<String> gateLangName = new ArrayList<>();
    public static final ArrayList<String> fenceBlockName = new ArrayList<>();
    public static final ArrayList<String> fenceLangName = new ArrayList<>();
    public static final ArrayList<String> wallBlockName = new ArrayList<>();
    public static final ArrayList<String> wallLangName = new ArrayList<>();
    public static final ArrayList<String> paneBlockName = new ArrayList<>();
    public static final ArrayList<String> paneLangName = new ArrayList<>();
    public static final ArrayList<String> crossBlockName = new ArrayList<>();
    public static final ArrayList<String> crossLangName = new ArrayList<>();
    public static final ArrayList<String> tintedCrossBlockName = new ArrayList<>();
    public static final ArrayList<String> tintedCrossLangName = new ArrayList<>();
    public static final ArrayList<String> vineBlockName = new ArrayList<>();
    public static final ArrayList<String> vineLangName = new ArrayList<>();
    public static final ArrayList<String> attachedCrossBlockName = new ArrayList<>();
    public static final ArrayList<String> attachedCrossLangName = new ArrayList<>();
    public static final ArrayList<String> doublePlantBlockName = new ArrayList<>();
    public static final ArrayList<String> doublePlantLangName = new ArrayList<>();
    public static final ArrayList<String> lilyPadBlockName = new ArrayList<>();
    public static final ArrayList<String> lilyPadLangName = new ArrayList<>();
    public static final ArrayList<String> grassBlockName = new ArrayList<>();
    public static final ArrayList<String> grassLangName = new ArrayList<>();
    public static final ArrayList<String> terrainBlockName = new ArrayList<>();
    public static final ArrayList<String> terrainLangName = new ArrayList<>();
    public static final ArrayList<String> randomBlockName = new ArrayList<>();
    public static final ArrayList<String> randomLangName = new ArrayList<>();
    public static final ArrayList<String> ladderBlockName = new ArrayList<>();
    public static final ArrayList<String> ladderLangName = new ArrayList<>();
    public static final ArrayList<String> overlayGrassBlockName = new ArrayList<>();
    public static final ArrayList<String> overlayGrassLangName = new ArrayList<>();
    public static final ArrayList<String> portalBlockName = new ArrayList<>();
    public static final ArrayList<String> portalLangName = new ArrayList<>();
    public static final ArrayList<String> chestBlockName = new ArrayList<>();
    public static final ArrayList<String> chestLangName = new ArrayList<>();
    public static final ArrayList<String> campfireBlockName = new ArrayList<>();
    public static final ArrayList<String> campfireLangName = new ArrayList<>();
    public static final ArrayList<String> pathBlockName = new ArrayList<>();
    public static final ArrayList<String> pathLangName = new ArrayList<>();
    public static final ArrayList<String> cropBlockName = new ArrayList<>();
    public static final ArrayList<String> cropLangName = new ArrayList<>();
    public static final ArrayList<String> bushBlockName = new ArrayList<>();
    public static final ArrayList<String> bushLangName = new ArrayList<>();
    public static final ArrayList<String> farmlandBlockName = new ArrayList<>();
    public static final ArrayList<String> farmlandLangName = new ArrayList<>();
    public static final ArrayList<String> basePortalBlockName = new ArrayList<>();
    public static final ArrayList<String> basePortalLangName = new ArrayList<>();
    public static final ArrayList<String> basePortalFrameBlockName = new ArrayList<>();
    public static final ArrayList<String> basePortalFrameLangName = new ArrayList<>();
    public static final ArrayList<String> mushroomBlockName = new ArrayList<>();
    public static final ArrayList<String> mushroomLangName = new ArrayList<>();
    public static final ArrayList<String> topBottomBlockName = new ArrayList<>();
    public static final ArrayList<String> topBottomLangName = new ArrayList<>();
    public static final ArrayList<String> slimeBlockName = new ArrayList<>();
    public static final ArrayList<String> slimeLangName = new ArrayList<>();
    public static final ArrayList<String> trophyBlockName = new ArrayList<>();
    public static final ArrayList<String> trophyLangName = new ArrayList<>();
    public static final ArrayList<String> totemBlockName = new ArrayList<>();
    public static final ArrayList<String> totemLangName = new ArrayList<>();
    public static final ArrayList<String> dripstoneBlockName = new ArrayList<>();
    public static final ArrayList<String> dripstoneLangName = new ArrayList<>();

    public static final ArrayList<String> PICKAXE_BLOCKS = new ArrayList<>();
    public static final ArrayList<String> AXE_BLOCKS = new ArrayList<>();
    public static final ArrayList<String> SHOVEL_BLOCKS = new ArrayList<>();
    public static final ArrayList<String> HOE_BLOCKS = new ArrayList<>();

    public static final DeferredBlock<Block> IRIDIUM_ORE = register("iridium_ore", "Iridium Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> IRIDIUM_BLOCK = registerFuelBlock("iridium_block", "Iridium Block", () -> new Block(JBlockProperties.STONE), 16000);
    public static final DeferredBlock<Block> DEEPSLATE_IRIDIUM_ORE = register("deepslate_iridium_ore", "Deepslate Iridium Ore", JBlockProperties.STONE);

    public static final DeferredBlock<Block> SAPPHIRE_ORE = register("sapphire_ore", "Sapphire Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> SAPPHIRE_BLOCK = register("sapphire_block", "Sapphire Block", JBlockProperties.STONE);
    public static final DeferredBlock<Block> DEEPSLATE_SAPPHIRE_ORE = register("deepslate_sapphire_ore", "Deepslate Sapphire Ore", JBlockProperties.STONE);

    public static final DeferredBlock<Block> SHADIUM_ORE = register("shadium_ore", "Shadium Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> SHADIUM_BLOCK = register("shadium_block", "Shadium Block", JBlockProperties.STONE);
    public static final DeferredBlock<Block> DEEPSLATE_SHADIUM_ORE = register("deepslate_shadium_ore", "Deepslate Shadium Ore", JBlockProperties.STONE);

    public static final DeferredBlock<Block> LUNIUM_ORE = register("lunium_ore", "Lunium Ore", JBlockProperties.LUNIUM_ORE_PROPS);
    public static final DeferredBlock<Block> LUNIUM_BLOCK = register("lunium_block", "Lunium Block", JBlockProperties.LUNIUM_ORE_PROPS);
    public static final DeferredBlock<Block> DEEPSLATE_LUNIUM_ORE = register("deepslate_lunium_ore", "Deepslate Lunium Ore", JBlockProperties.LUNIUM_ORE_PROPS);//TODO DEEPSLATE SOUNDS

    public static final DeferredBlock<Block> BLOODCRUST_ORE = register("bloodcrust_ore", "Bloodcrust Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BLOODCRUST_BLOCK = register("bloodcrust_block", "Bloodcrust Block", JBlockProperties.STONE);

    public static final DeferredBlock<RotatedPillarBlock> FIRESTONE_ORE = registerPillar("firestone_ore", "Firestone Ore", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> FIRESTONE_BLOCK = register("firestone_block", "Firestone Block", JBlockProperties.STONE);

    public static final DeferredBlock<Block> ENDERILLIUM_ORE = register("enderillium_ore", "Enderillium Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> ENDERILLIUM_BLOCK = register("enderillium_block", "Enderillium Block", JBlockProperties.STONE);

    public static final DeferredBlock<Block> ASHUAL_ORE = register("ashual_ore", "Ashual Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> ASHUAL_BLOCK = register("ashual_block", "Ashual Block", JBlockProperties.STONE);

    public static final DeferredBlock<Block> BLAZIUM_ORE = register("blazium_ore", "Blazium Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BLAZIUM_BLOCK = register("blazium_block", "Blazium Block", JBlockProperties.STONE);

    public static final DeferredBlock<Block> CELESTIUM_ORE = register("celestium_ore", "Celestium Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> CELESTIUM_BLOCK = register("celestium_block", "Celestium Block", JBlockProperties.STONE);

    public static final DeferredBlock<Block> MEKYUM_ORE = register("mekyum_ore", "Mekyum Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> MEKYUM_BLOCK = register("mekyum_block", "Mekyum Block", JBlockProperties.STONE);

    public static final DeferredBlock<Block> STORON_ORE = register("storon_ore", "Storon Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> STORON_BLOCK = register("storon_block", "Storon Block", JBlockProperties.STONE);

    public static final DeferredBlock<Block> KORITE_ORE = register("korite_ore", "Korite Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> KORITE_BLOCK = register("korite_block", "Korite Block", JBlockProperties.STONE);

    public static final DeferredBlock<Block> RIMESTONE_ORE = register("rimestone_ore", "Rimestone Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> RIMESTONE_BLOCK = register("rimestone_block", "Rimestone Block", JBlockProperties.STONE);

    public static final DeferredBlock<Block> PERIDOT_ORE = register("peridot_ore", "Peridot Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> PERIDOT_BLOCK = register("peridot_block", "Peridot Block", JBlockProperties.STONE);

    public static final DeferredBlock<Block> DES_ORE = register("des_ore", "Des Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> DES_BLOCK = register("des_block", "Des Block", JBlockProperties.STONE);

    public static final DeferredBlock<Block> FLAIRIUM_ORE = register("flairium_ore", "Flairium Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> FLAIRIUM_BLOCK = register("flairium_block", "Flairium Block", JBlockProperties.STONE);

//    public static final DeferredBlock<Block> VERDITE_ORE = register("verdite_ore", "Verdite Ore", JBlockProperties.STONE);
//    public static final DeferredBlock<Block> DEEPSLATE_VERDITE_ORE = register("deepslate_verdite_ore", "Deepslate Verdite Ore", JBlockProperties.STONE);
//    public static final DeferredBlock<Block> VERDITE_BLOCK = register("verdite_block", "Verdite Block", JBlockProperties.STONE);

    public static final DeferredBlock<Block> ORBADITE_ORE = register("orbadite_ore", "Orbadite Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> ORBADITE_BLOCK = register("orbadite_block", "Orbadite Block", JBlockProperties.STONE);

    public static final DeferredBlock<Block> GORBITE_ORE = register("gorbite_ore", "Gorbite Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> GORBITE_BLOCK = register("gorbite_block", "Gorbite Block", JBlockProperties.STONE);

    public static final DeferredBlock<Block> LUNITE_ORE = register("lunite_ore", "Lunite Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> LUNITE_BLOCK = register("lunite_block", "Lunite Block", JBlockProperties.STONE);

    public static final DeferredBlock<Block> RARE_GEM_BLOCK = register("rare_gem_block", "Rare Gem Block", JBlockProperties.STONE);
    public static final DeferredBlock<Block> COMMON_GEM_BLOCK = register("common_gem_block", "Common Gem Block", JBlockProperties.STONE);
    public static final DeferredBlock<Block> YELLOW_GEM_BLOCK = register("yellow_gem_block", "Yellow Gem Block", JBlockProperties.STONE);
    public static final DeferredBlock<Block> PURPLE_GEM_BLOCK = register("purple_gem_block", "Purple Gem Block", JBlockProperties.STONE);
    public static final DeferredBlock<Block> GREEN_GEM_BLOCK = register("green_gem_block", "Green Gem Block", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BLUE_GEM_BLOCK = register("blue_gem_block", "Blue Gem Block", JBlockProperties.STONE);

    public static final DeferredBlock<Block> WARPED_QUARTZ_ORE = register("warped_quartz_ore", "Warped Quartz Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> WARPED_QUARTZ_BLOCK = register("warped_quartz_block", "Warped Quartz Block", JBlockProperties.STONE);
    public static final DeferredBlock<RotatedPillarBlock> CHISELED_WARPED_QUARTZ_BLOCK = registerPillar("chiseled_warped_quartz_block", "Chiseled Warped Quartz Block", false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> WARPED_QUARTZ_SLAB = registerSlab("warped_quartz_slab", "Warped Quartz Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> WARPED_QUARTZ_BRICKS = register("warped_quartz_bricks", "Warped Quartz Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> WARPED_QUARTZ_STAIRS = registerStairs("warped_quartz_stairs", "Warped Quartz Stairs", WARPED_QUARTZ_BLOCK, false, JBlockProperties.STONE);
    public static final DeferredBlock<RotatedPillarBlock> WARPED_QUARTZ_PILLAR = registerPillar("warped_quartz_pillar", "Warped Quartz Pillar", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> SMOOTH_WARPED_QUARTZ = register("smooth_warped_quartz_block", "Smooth Warped Quartz Block", JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> SMOOTH_WARPED_QUARTZ_SLAB = registerSlab("smooth_warped_quartz_slab", "Smooth Warped Quartz Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> SMOOTH_WARPED_QUARTZ_STAIRS = registerStairs("smooth_warped_quartz_stairs", "Smooth Warped Quartz Stairs", SMOOTH_WARPED_QUARTZ, false, JBlockProperties.STONE);

    public static final DeferredBlock<Block> CRIMSON_QUARTZ_ORE = register("crimson_quartz_ore", "Crimson Quartz Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> CRIMSON_QUARTZ_BLOCK = register("crimson_quartz_block", "Crimson Quartz Block", JBlockProperties.STONE);
    public static final DeferredBlock<RotatedPillarBlock> CHISELED_CRIMSON_QUARTZ_BLOCK = registerPillar("chiseled_crimson_quartz_block", "Chiseled Crimson Quartz Block", false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> CRIMSON_QUARTZ_SLAB = registerSlab("crimson_quartz_slab", "Crimson Quartz Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> CRIMSON_QUARTZ_BRICKS = register("crimson_quartz_bricks", "Crimson Quartz Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> CRIMSON_QUARTZ_STAIRS = registerStairs("crimson_quartz_stairs", "Crimson Quartz Stairs", CRIMSON_QUARTZ_BLOCK, false, JBlockProperties.STONE);
    public static final DeferredBlock<RotatedPillarBlock> CRIMSON_QUARTZ_PILLAR = registerPillar("crimson_quartz_pillar", "Crimson Quartz Pillar", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> SMOOTH_CRIMSON_QUARTZ = register("smooth_crimson_quartz_block", "Smooth Crimson Quartz Block", JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> SMOOTH_CRIMSON_QUARTZ_SLAB = registerSlab("smooth_crimson_quartz_slab", "Smooth Crimson Quartz Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> SMOOTH_CRIMSON_QUARTZ_STAIRS = registerStairs("smooth_crimson_quartz_stairs", "Smooth Crimson Quartz Stairs", SMOOTH_CRIMSON_QUARTZ, false, JBlockProperties.STONE);

    public static final DeferredBlock<Block> BLEEDSTONE_BLOCK = register("bleedstone_block", "Bleedstone Block", JBlockProperties.GLOW_BLOCK);
    public static final DeferredBlock<Block> SMITHSTONE_BLOCK = register("smithstone_block", "Smithstone Block", JBlockProperties.GLOW_BLOCK);
    public static final DeferredBlock<Block> SOULSTONE_BLOCK = register("soulstone_block", "Soulstone Block", JBlockProperties.GLOW_BLOCK);
    public static final DeferredBlock<Block> DEPTHS_LAMP = register("depths_lamp", "Depths Lamp", JBlockProperties.GLOW_BLOCK);
    public static final DeferredBlock<Block> DEPTHS_LIGHT = register("depths_light", "Depths Light", JBlockProperties.GLOW_BLOCK);
    public static final DeferredBlock<Block> TERRANIAN_LAMP = register("terrania_lamp", "Terrania Lamp", JBlockProperties.GLOW_BLOCK);
    public static final DeferredBlock<Block> CORBA_LAMP = register("corba_lamp", "Corba Lamp", JBlockProperties.GLOW_BLOCK);
    public static final DeferredBlock<Block> CLOUDIA_LAMP = register("cloudia_lamp", "Cloudia Lamp", JBlockProperties.GLOW_BLOCK);
    public static final DeferredBlock<Block> SENTERIAN_GUARDIAN_LAMP = register("senterian_guardian_lamp", "Senterian Guardian Lamp", JBlockProperties.GLOW_DUNGEON_BLOCK);
    public static final DeferredBlock<Block> SENTERIAN_LIGHT_LAMP = register("senterian_light_lamp", "Senterian Light Lamp", JBlockProperties.GLOW_DUNGEON_BLOCK);
    public static final DeferredBlock<Block> SENTERIAN_MELLOW_LAMP = register("senterian_mellow_lamp", "Senterian Mellow Lamp", JBlockProperties.GLOW_DUNGEON_BLOCK);

    public static final DeferredBlock<Block> TALL_GREEN_GLOWSHROOM = registerDoublePlant("tall_green_glowshroom", "Tall Green Glowshroom", () -> new TallGlowshroomBlock(JBlockProperties.CAVE_GLOW_PLANT));
    public static final DeferredBlock<Block> TALL_BLUE_GLOWSHROOM = registerDoublePlant("tall_blue_glowshroom", "Tall Blue Glowshroom", () -> new TallGlowshroomBlock(JBlockProperties.CAVE_GLOW_PLANT));
    public static final DeferredBlock<Block> TALL_RED_GLOWSHROOM = registerDoublePlant("tall_red_glowshroom", "Tall Red Glowshroom", () -> new TallGlowshroomBlock(JBlockProperties.CAVE_GLOW_PLANT));
    public static final DeferredBlock<Block> GREEN_GLOWSHROOM = registerCrossBlock("green_glowshroom", "Green Glowshroom", () -> new CavePlantBlock(JBlockProperties.CAVE_GLOW_PLANT));
    public static final DeferredBlock<Block> BLUE_GLOWSHROOM = registerCrossBlock("blue_glowshroom", "Blue Glowshroom", () -> new CavePlantBlock(JBlockProperties.CAVE_GLOW_PLANT));
    public static final DeferredBlock<Block> RED_GLOWSHROOM = registerCrossBlock("red_glowshroom", "Red Glowshroom", () -> new CavePlantBlock(JBlockProperties.CAVE_GLOW_PLANT));

    public static final DeferredBlock<Block> DUNGEON_BRICKS = register("dungeon_bricks", "Dungeon Bricks", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<StairBlock> DUNGEON_BRICK_STAIRS = registerStairs("dungeon_brick_stairs", "Dungeon Brick Stairs", DUNGEON_BRICKS, false, JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<JFenceBlock> DUNGEON_BRICK_FENCE = registerFence("dungeon_brick_fence", "Dungeon Brick Fence", false, JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> CARVED_DUNGEON_BRICKS = register("carved_dungeon_bricks", "Carved Dungeon Bricks", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> CRACKED_DUNGEON_BRICKS = register("cracked_dungeon_bricks", "Cracked Dungeon Bricks", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<StairBlock> CARVED_DUNGEON_BRICK_STAIRS = registerStairs("carved_dungeon_brick_stairs", "Carved Dungeon Brick Stairs", DUNGEON_BRICKS, false, JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<JFenceBlock> CARVED_DUNGEON_BRICK_FENCE = registerFence("carved_dungeon_brick_fence", "Carved Dungeon Brick Fence", false, JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> CHISELED_DUNGEON_BRICKS = register("chiseled_dungeon_bricks", "Chiseled Dungeon Bricks", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<StairBlock> CHISELED_DUNGEON_BRICK_STAIRS = registerStairs("chiseled_dungeon_brick_stairs", "Chiseled Dungeon Brick Stairs", DUNGEON_BRICKS, false, JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<JFenceBlock> CHISELED_DUNGEON_BRICK_FENCE = registerFence("chiseled_dungeon_brick_fence", "Chiseled Dungeon Brick Fence", false, JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> DUNGEON_FLOOR = register("dungeon_floor", "Dungeon Floor", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> GILDED_DUNGEON_BRICKS = register("gilded_dungeon_bricks", "Gilded Dungeon Bricks", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<StairBlock> GILDED_DUNGEON_BRICK_STAIRS = registerStairs("gilded_dungeon_brick_stairs", "Gilded Dungeon Brick Stairs", GILDED_DUNGEON_BRICKS, false, JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<JFenceBlock> GILDED_DUNGEON_BRICK_FENCE = registerFence("gilded_dungeon_brick_fence", "Gilded Dungeon Brick Fence", false, JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> DUNGEON_LAMP = register("dungeon_lamp", "Dungeon Lamp", JBlockProperties.DUNGEON_LAMP);
    public static final DeferredBlock<StairBlock> DUNGEON_LAMP_STAIRS = registerStairs("dungeon_lamp_stairs", "Dungeon Lamp Stairs", DUNGEON_LAMP, false, JBlockProperties.DUNGEON_LAMP);
    public static final DeferredBlock<JFenceBlock> DUNGEON_LAMP_FENCE = registerFence("dungeon_lamp_fence", "Dungeon Lamp Fence", false, JBlockProperties.DUNGEON_LAMP);;

    public static final DeferredBlock<Block> NETHER_DUNGEON_BRICKS = register("nether_dungeon_bricks", "Nether Dungeon Bricks", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> LARGE_NETHER_BRICKS = register("large_nether_bricks", "Large Nether Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<Block> NETHIC_LAMP = register("nethic_lamp", "Nethic Lamp", JBlockProperties.GLOW_BLOCK);
    public static final DeferredBlock<StairBlock> NETHER_DUNGEON_BRICK_STAIRS = registerStairs("nether_dungeon_brick_stairs", "Nether Dungeon Brick Stairs", NETHER_DUNGEON_BRICKS, false, JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<JFenceBlock> NETHER_DUNGEON_BRICK_FENCE = registerFence("nether_dungeon_brick_fence", "Nether Dungeon Brick Fence", false, JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> MINI_GHAST_SPAWNER = register("mini_ghast_spawner", "Mini Ghast Spawner", MiniGhastSpawnerBlock::new, JBlockProperties.SPAWNER);
    public static final DeferredBlock<Block> HELLBOT_SPAWNER = register("hellbot_spawner", "Hellbot Spawner", HellbotSpawnerBlock::new, JBlockProperties.SPAWNER);

    public static final DeferredBlock<IronBarsBlock> BOIL_CHAIN = registerPaneBlock("boil_chain", "Boil Chain", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<IronBarsBlock> BRISON_BARS = registerPaneBlock("brison_bars", "Brison Bars", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BOIL_COBBLESTONE = register("boil_cobblestone", "Boiling Cobblestone", JBlockProperties.STONE);
    public static final DeferredBlock<RotatedPillarBlock> BOIL_PILLAR = registerPillar("boil_pillar", "Boil Pillar", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> BOIL_BRICKS = register("boil_bricks", "Boil Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BOIL_SQUARE_BRICKS = register("boil_square_bricks", "Boil Square Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BOIL_SHINGLE = register("boil_shingle", "Boil Shingle", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BLAZIER_BRICKS = register("blazier_bricks", "Blazier Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BLAZIER_METAL = register("blazier_metal", "Blazier Metal", JBlockProperties.STONE);
    public static final DeferredBlock<Block> HELLWING_SPAWNER = register("hellwing_spawner", "Hellwing Spawner", HellwingSpawnerBlock::new, JBlockProperties.SPAWNER);
    public static final DeferredBlock<Block> OBSERVER_SPAWNER = register("observer_spawner", "Observer Spawner", ObserverSpawnerBlock::new, JBlockProperties.SPAWNER);
    public static final DeferredBlock<Block> SCREAMER_SPAWNER = register("screamer_spawner", "Screamer Spawner", ScreamerSpawnerBlock::new, JBlockProperties.SPAWNER);
    public static final DeferredBlock<Block> SMALL_BRISON_BRICKS = register("small_brison_bricks", "Small Brison Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<Block> RED_BRISON_BRICKS = register("red_brison_brick", "Red Brison Brick", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BRISON_STONE = register("brison_stone", "Brison Stone", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BOILING_LAMP = register("boiling_lamp", "Boiling Lamp", JBlockProperties.GLOW_BLOCK);
    public static final DeferredBlock<JFenceBlock> SIZZLING_POST = registerFence("sizzling_post", "Sizzling Post", false, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> SCORCHED_RUBBLE_BRICKS = register("scorched_rubble_bricks", "Scorched Rubble Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> SCORCHED_RUBBLE_BRICK_STAIRS = registerStairs("scorched_rubble_brick_stairs", "Scorched Rubble Brick Stairs", SCORCHED_RUBBLE_BRICKS, false, JBlockProperties.STONE);

    public static final DeferredBlock<Block> GOLDITE_FARMLAND = registerFarmlandBlock("goldite_farmland", "Goldite Farmland", GolditeFarmland::new);
    public static final DeferredBlock<Block> DEPTHS_FARMLAND = registerFarmlandBlock("depths_farmland", "Depths Farmland", DepthsFarmland::new);
    public static final DeferredBlock<Block> PERMAFROST_FARMLAND = registerFarmlandBlock("permafrost_farmland", "Permafrost Farmland", PermafrostFarmland::new);
    public static final DeferredBlock<Block> CORBA_FARMLAND = registerFarmlandBlock("corba_farmland", "Corba Farmland", CorbaFarmland::new);
    public static final DeferredBlock<Block> CLOUDIA_FARMLAND = registerFarmlandBlock("cloudia_farmland", "Cloudia Farmland", CloudiaFarmland::new);

    public static final DeferredBlock<Block> EUCA_PORTAL_FRAME = register("euca_portal_frame", "Euca Portal Frame", JBlockProperties.STONE);
    public static final DeferredBlock<JBasePortalBlock> EUCA_PORTAL = registerPortalBlock("euca_portal", "Euca Portal", () -> new JBasePortalBlock(Dimensions.EUCA, EUCA_PORTAL_FRAME));
    public static final DeferredBlock<Block> GOLDITE_DIRT = registerTerrainBlock("goldite_dirt", "Goldite Soil", JDirt::new);
    public static final DeferredBlock<Block> GOLDITE_STONE = registerTerrainBlock("goldite_stone", "Goldite Stone", JBlockProperties.STONE);
    public static final DeferredBlock<Block> GOLDITE_COBBLESTONE = register("goldite_cobblestone", "Goldite Cobblestone", JBlockProperties.STONE);
    public static final DeferredBlock<Block> EUCA_BRICK = register("euca_brick", "Euca Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<Block> EUCA_DUNGEON_BRICKS = register("euca_dungeon_brick", "Euca Dungeon Bricks", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> ROYAL_BRICKS = register("royal_bricks", "Royal Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> ROYAL_STAIRS = registerStairs("royal_stairs", "Royal Stairs", ROYAL_BRICKS, false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> ROYAL_FENCE = registerFence("royal_fence", "Royal Fence", false, JBlockProperties.STONE);

    public static final DeferredBlock<Block> ROYAL_STONE = register("royal_stone", "Royal Stone", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> ROYAL_STONE_STAIRS = registerStairs("royal_stone_stairs", "Royal Stone Stairs", ROYAL_STONE, false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> ROYAL_STONE_FENCE = registerFence("royal_stone_fence", "Royal Stone Fence",false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> ROYAL_PAVER = register("royal_paver", "Royal Paver", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> ROYAL_PAVER_STAIRS = registerStairs("royal_paver_stairs", "Royal Paver Stairs", ROYAL_PAVER, false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> ROYAL_PAVER_FENCE = registerFence("royal_paver_fence", "Royal Paver Fence",false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> ROYAL_SHINGLE = register("royal_shingle", "Royal Shingle", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> ROYAL_SHINGLE_STAIRS = registerStairs("royal_shingle_stairs", "Royal Shingle Stairs", ROYAL_SHINGLE, false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> ROYAL_SHINGLE_FENCE = registerFence("royal_shingle_fence", "Royal Shingle Fence",false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> POLISHED_ROYAL_STONE = register("polished_royal_stone", "Polished Royal Stone", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> POLISHED_ROYAL_STAIRS = registerStairs("polished_royal_stairs", "Polished Royal Stairs", POLISHED_ROYAL_STONE, false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> POLISHED_ROYAL_FENCE = registerFence("polished_royal_shingle_fence", "Polished Royal Fence",false, JBlockProperties.STONE);
    public static final DeferredBlock<RotatedPillarBlock> ROYAL_PLILLAR = registerPillar("royal_pillar", "Royal Pillar", false, JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> EUCA_DUNGEON_STAIRS = registerStairs("euca_dungeon_stairs", "Euca Dungeon Stairs", EUCA_DUNGEON_BRICKS, false, JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> EUCA_DUNGEON_TILE = register("euca_dungeon_tile", "Euca Dungeon Tile", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> EUCA_RUNIC_BRICKS = register("euca_runic_bricks", "Euca Runic Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<Block> EUCA_RUNIC_LAMP = register("euca_runic_lamp", "Euca Runic Lamp", JBlockProperties.BREAKABLE_DUNGEON_LAMP);
    public static final DeferredBlock<Block> EUCA_SQUARE_RUNIC_BRICKS = register("euca_square_runic_bricks", "Euca Square Runic Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<Block> EUCA_SQUARE_BRICKS = register("euca_square_bricks", "Euca Square Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<Block> EUCA_TILE = register("euca_tile", "Euca Tile", JBlockProperties.STONE);
    public static final DeferredBlock<Block> EUCA_TALL_GRASS = registerCrossBlock("euca_tall_grass", "Euca Tall Grass", () -> new TallGrassBlock(JBlockProperties.REPLACABLE_PLANT));
    public static final DeferredBlock<Block> EUCA_TALL_FLOWERS = registerCrossBlock("euca_tall_flowers", "Euca Tall Flowers", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> EUCA_SILVER_FLOWER = registerCrossBlock("euca_silver_gold_flower", "Euca Silver Flower", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> EUCA_BLUE_FLOWER = registerCrossBlock("euca_blue_flower", "Euca Blue Flower", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> GOLDITE_FLOWER = registerCrossBlock("goldite_flower", "Goldite Flower", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> GOLDITE_STALKS = registerCrossBlock("goldite_stalks", "Goldite Stalks", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> GOLDITE_BULB = registerCrossBlock("goldite_bulb", "Goldite Bulb", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> GOLDITE_TALL_GRASS = registerDoublePlant("goldite_tall_grass", "Goldite Tall Grass", () -> new JDoublePlantBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> GOLD_BOT_SPAWNER = register("gold_bot_spawner", "Gold Bot Spawner", GoldBotSpawnerBlock::new, JBlockProperties.SPAWNER);
    public static final DeferredBlock<Block> GOLDITE_FURNACE = registerFurnaceBlock("goldite_furnace", "Goldite Furnace");
    public static final DeferredBlock<Block> EUCA_PUMPKIN = registerRotatableBlock("euca_pumpkin", "Euca Pumpkin", () -> new FaceableBlock(JBlockProperties.WOOD), true);
    public static final DeferredBlock<Block> GLIMMER_ROOT = registerVineBlock("glimmer_root", "Glimmer Root", () -> new JVineBlock(JBlockProperties.VINE));
    public static final DeferredBlock<Block> EUCA_GOLD_GRASS = registerGrassBlock("euca_gold_grass", "Euca Gold Grass", JGrassBlock::new);
    public static final DeferredBlock<Block> GOLDITE_GRASS = registerGrassBlock("goldite_grass", "Goldite Grass", JGrassBlock::new);
    public static final DeferredBlock<RotatedPillarBlock> EUCA_GOLD_LOG = addLogBlock("euca_gold_log", "Euca Gold Log");
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_EUCA_GOLD_LOG = registerPillar("stripped_euca_gold_log", "Stripped Euca Gold Log", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> EUCA_GOLD_LEAVES = registerTerrainBlock("euca_gold_leaves", "Euca Gold Leaves", JBlockProperties.LEAVES);//JLeavesBlock::new);
    public static final DeferredBlock<Block> EUCA_GOLD_SAPLING = registerCrossBlock("euca_gold_sapling", "Euca Gold Sapling", () -> new JSaplingBlock(JTreeGrower.EUCA_GOLD_TREE_GROWER, JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> EUCA_GOLD_PLANKS = registerFuelBlock("euca_gold_planks", "Euca Gold Planks", JBlockProperties.WOOD, 300);
    public static final DeferredBlock<DoorBlock> EUCA_GOLD_DOOR = registerDoor("euca_gold_door", "Euca Gold Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<TrapDoorBlock> EUCA_GOLD_TRAP_DOOR = registerTrapDoor("euca_gold_trap_door", "Euca Gold Trap Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<StairBlock> EUCA_GOLD_STAIRS = registerStairs("euca_gold_stairs", "Euca Gold Plank Stairs", EUCA_GOLD_PLANKS, true, JBlockProperties.WOOD);
    public static final DeferredBlock<SlabBlock> EUCA_GOLD_SLAB = registerSlab("euca_gold_slab", "Euca Gold Plank Slab", true, JBlockProperties.WOOD);
    public static final DeferredBlock<ButtonBlock> EUCA_GOLD_BUTTON = registerButton("euca_gold_button", "Euca Gold Button", false, true, JBlockProperties.WOOD);
    public static final DeferredBlock<PressurePlateBlock> EUCA_GOLD_PRESSURE_PLATE = registerPressurePlate("euca_gold_pressure_plate", "Euca Gold Pressure Plate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<FenceGateBlock> EUCA_GOLD_FENCE_GATE = registerFenceGate("euca_gold_fence_gate", "Euca Gold Fence Gate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<JFenceBlock> EUCA_GOLD_FENCE = registerFence("euca_gold_fence", "Euca Gold Fence", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> GOLDITE_PATH = registerPathBlock("goldite_path", "Goldite Path", () -> new JDirtPathBlock(JBlockProperties.PATH));
    public static final DeferredBlock<RotatedPillarBlock> EUCA_BROWN_LOG = addLogBlock("euca_brown_log", "Euca Brown Log");
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_EUCA_BROWN_LOG = registerPillar("stripped_euca_brown_log", "Stripped Euca Brown Log", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> EUCA_GREEN_LEAVES = registerAltTexBlock("euca_green_leaves", "Euca Green Leaves", JBlockProperties.LEAVES);// JLeavesBlock::new);
    public static final DeferredBlock<Block> EUCA_GREEN_SAPLING = registerCrossBlock("euca_green_sapling", "Euca Green Sapling", () -> new JSaplingBlock(JTreeGrower.EUCA_GREEN_TREE_GROWER, JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> EUCA_BROWN_PLANKS = registerFuelBlock("euca_brown_planks", "Euca Brown Planks", JBlockProperties.WOOD, 300);
    public static final DeferredBlock<DoorBlock> EUCA_BROWN_DOOR = registerDoor("euca_brown_door", "Euca Brown Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<TrapDoorBlock> EUCA_BROWN_TRAP_DOOR = registerTrapDoor("euca_brown_trap_door", "Euca Brown Trap Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<StairBlock> EUCA_BROWN_STAIRS = registerStairs("euca_brown_stairs", "Euca Brown Plank Stairs", EUCA_BROWN_PLANKS, true, JBlockProperties.WOOD);
    public static final DeferredBlock<SlabBlock> EUCA_BROWN_SLAB = registerSlab("euca_brown_slab", "Euca Brown Plank Slab", true, JBlockProperties.WOOD);
    public static final DeferredBlock<ButtonBlock> EUCA_BROWN_BUTTON = registerButton("euca_brown_button", "Euca Brown Button", true, true, JBlockProperties.BUTTON);
    public static final DeferredBlock<PressurePlateBlock> EUCA_BROWN_PRESSURE_PLATE = registerPressurePlate("euca_brown_pressure_plate", "Euca Brown Pressure Plate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<FenceGateBlock> EUCA_BROWN_FENCE_GATE = registerFenceGate("euca_brown_fence_gate", "Euca Brown Fence Gate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<JFenceBlock> EUCA_BROWN_FENCE = registerFence("euca_brown_fence", "Euca Brown Fence", true, JBlockProperties.WOOD);

    public static final DeferredBlock<Block> FROZEN_PORTAL_FRAME = register("frozen_portal_frame", "Frozen Portal Frame", JBlockProperties.STONE);
    public static final DeferredBlock<JBasePortalBlock> FROZEN_PORTAL = registerPortalBlock("frozen_portal", "Frozen Portal", () -> new JBasePortalBlock(Dimensions.FROZEN_LANDS, FROZEN_PORTAL_FRAME));
    public static final DeferredBlock<Block> FUMICE = register("fumice", "Fumice", JBlockProperties.DIRT);
    public static final DeferredBlock<Block> GRASSY_PERMAFROST = registerGrassBlock("grassy_permafrost", "Grassy Permafrost", JGrassBlock::new);
    public static final DeferredBlock<Block> PERMAFROST = registerTerrainBlock("permafrost", "Permafrost", JBlockProperties.STONE);
    public static final DeferredBlock<Block> CRUMBLED_PERMAFROST = registerTerrainBlock("crumbled_permafrost", "Crumbled Permafrost", JDirt::new);
    public static final DeferredBlock<RotatedPillarBlock> FROZEN_LOG = addLogBlock("frozen_log", "Frozen Log");
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_FROZEN_LOG = registerPillar("stripped_frozen_log", "Stripped Frozen Log", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> FROZEN_LEAVES = registerTerrainBlock("frozen_leaves", "Frozen Leaves", JBlockProperties.LEAVES);
    public static final DeferredBlock<Block> FROSTWOOD_SAPLING = registerCrossBlock("frostwood_sapling", "Frostwood Sapling", () -> new JSaplingBlock(JTreeGrower.EUCA_GREEN_TREE_GROWER, JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> FROZEN_PLANKS = registerFuelBlock("frozen_planks", "Frostwood Planks", JBlockProperties.WOOD, 300);
    public static final DeferredBlock<DoorBlock> FROZEN_DOOR = registerDoor("frozen_door", "Frostwood Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<TrapDoorBlock> FROZEN_TRAP_DOOR = registerTrapDoor("frozen_trap_door", "Frostwood Trap Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<StairBlock> FROZEN_STAIRS = registerStairs("frozen_stairs", "Frostwood Plank Stairs", EUCA_BROWN_PLANKS, true, JBlockProperties.WOOD);
    public static final DeferredBlock<SlabBlock> FROZEN_SLAB = registerSlab("frozen_slab", "Frostwood Plank Slab", true, JBlockProperties.WOOD);
    public static final DeferredBlock<ButtonBlock> FROZEN_BUTTON = registerButton("frozen_button", "Frostwood Button", true, true, JBlockProperties.BUTTON);
    public static final DeferredBlock<PressurePlateBlock> FROZEN_PRESSURE_PLATE = registerPressurePlate("frozen_pressure_plate", "Frostwood Pressure Plate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<FenceGateBlock> FROZEN_FENCE_GATE = registerFenceGate("frozen_fence_gate", "Frostwood Fence Gate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<JFenceBlock> FROZEN_FENCE = registerFence("frozen_fence", "Frostwood Fence", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> FROST_CRYSTAL_LARGE = registerAttachedCrossBlock("frost_crystal_large", "Frost Crystal", () -> new AttachedBlock(JBlockProperties.CRYSTAL));
    public static final DeferredBlock<Block> FROST_CRYSTAL_MEDIUM = registerAttachedCrossBlock("frost_crystal_medium", "Frost Crystal", () -> new AttachedBlock(JBlockProperties.CRYSTAL));
    public static final DeferredBlock<Block> FROST_CRYSTAL_SMALL = registerAttachedCrossBlock("frost_crystal_small", "Frost Crystal", () -> new AttachedBlock(JBlockProperties.CRYSTAL));
    public static final DeferredBlock<Block> FROST_CRYSTAL_TINY = registerAttachedCrossBlock("frost_crystal_tiny", "Frost Crystal", () -> new AttachedBlock(JBlockProperties.CRYSTAL));
    public static final DeferredBlock<Block> FROSTBERRY_THORN = registerCrossBlock("frostberry_thorn", "Frostberry Thorn", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> ICE_BUSH = registerCrossBlock("ice_bush", "Ice Bush", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> ICE_BUD = registerCrossBlock("ice_bud", "Ice Bud", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> CICLEBLOOM = registerModeledBlock("ciclebloom", "Cicle Bloom", () -> new SingleDoublePlant(JBlockProperties.PLANT.lightLevel((light) -> 4)));
    public static final DeferredBlock<Block> ICY_BRUSH = registerVineBlock("icy_brush", "Icy Brush", () -> new JVineBlock(JBlockProperties.VINE));
    public static final DeferredBlock<Block> CRYSTAL_FRUIT = registerModeledBlock("crystal_fruit", "Crystal Fruit", () -> new CrystalFruit(JBlockProperties.CRYSTAL_FRUIT));
    public static final DeferredBlock<GrowingPlantHeadBlock> ICY_IVY = registerGrowingPlantHeadBlock("icy_ivy", "Icy Ivy", () -> new IcyIvyTopBlock(JBlockProperties.VINE));
    public static final DeferredBlock<Block> ICY_IVY_PLANT = registerCrossBlock("icy_ivy_plant", "Icy Ivy", () -> new IcyIvyBlock(JBlockProperties.VINE));
    public static final DeferredBlock<Block> ICE_SHROOM_SHELF = registerModeledBlock("ice_shroom_shelf", "Ice Shroom Shelf", JBlockFungalShelf::new);
    public static final DeferredBlock<Block> BITTERWOOD_SAPLING = registerCrossBlock("bitterwood_sapling", "Bitterwood Sapling", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> BITTERWOOD_CAMPFIRE = registerCampfire("bitterwood_campfire", "Bitterwood Campfire", BitterwoodCampfireBlock::new);
    public static final DeferredBlock<Block> PERMAFROST_FURNACE = registerFurnaceBlock("permafrost_furnace", "Permafrost Furnace");
    public static final DeferredBlock<Block> PACKED_SNOW_BRICKS = register("packed_snow_bricks", "Packed Snow Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<Block> PACKED_ICE_BRICKS = register("packed_ice_bricks", "Packed Ice Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> PACKED_SNOW_FENCE = registerFence("packed_snow_fence", "Packed Snow Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> PACKED_ICE_FENCE = registerFence("packed_ice_fence", "Packed Ice Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> FROZEN_BRICKS = register("frozen_bricks", "Frozen Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> PACKED_SNOW_BRICKS_STAIRS = registerStairs("packed_snow_brick_stairs", "Packed Snow Brick Stairs", PACKED_SNOW_BRICKS, false, JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> PACKED_ICE_BRICKS_STAIRS = registerStairs("packed_ice_brick_stairs", "Packed ice Brick Stairs", PACKED_ICE_BRICKS, false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> PERMAFROST_ROAD = registerPathBlock("permafrost_road", "Permafrost Road", () -> new JDirtPathBlock(JBlockProperties.PATH));
    public static final DeferredBlock<Block> FROZEN_BLOOM = registerCrossBlock("frozen_bloom", "Frozen Bloom", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> FROZEN_FLOWER = registerCrossBlock("frozen_flower", "Frozen Flower", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> FROSTY_ICE = register("frosty_ice", "Frosty Ice", JBlockProperties.ICE);
    public static final DeferredBlock<Block> ICE_STONE = register("ice_stone", "Ice Stone", JBlockProperties.GLOW_BLOCK);
    public static final DeferredBlock<Block> FROST_GEM_BLOCK = register("frost_gem_block", "Frost Gem Block", JBlockProperties.STONE);
    public static final DeferredBlock<Block> FROSTBITER_SPAWNER = register("frostbiter_spawner", "Frostbiter Spawner", FrostbiterSpawnerBlock::new, JBlockProperties.SPAWNER);
    public static final DeferredBlock<Block> GLACIAL_ROCK = register("glacial_rock", "Glacial Rock", JBlockProperties.STONE);

    public static final DeferredBlock<Block> BOIL_PORTAL_FRAME = register("boil_portal_frame", "Boiling Point Portal Frame", JBlockProperties.STONE);
    public static final DeferredBlock<JBasePortalBlock> BOIL_PORTAL = registerPortalBlock("boil_portal", "Boiling Point Portal", () -> new JBasePortalBlock(Dimensions.BOIL, BOIL_PORTAL_FRAME));
    public static final DeferredBlock<Block> SULPHUR_CRYSTAL = registerAttachedCrossBlock("sulphur_crystal", "Sulphur Crystal", () -> new AttachedBlock(JBlockProperties.STONE));
    public static final DeferredBlock<Block> SULPHUR_ROCK = register("sulphur_rock", "Sulphur Rock", JBlockProperties.STONE);
    public static final DeferredBlock<Block> RUBBLE = registerTerrainBlock("rubble", "Rubble", JBlockProperties.FIRE_DIRT);
    public static final DeferredBlock<Block> ASH_BLOCK = registerTerrainBlock("ash_block", "Ash", JBlockProperties.FIRE_STONE);
    public static final DeferredBlock<Block> SCORCHED_RUBBLE = registerTerrainBlock("scorched_rubble", "Scorched Rubble", JBlockProperties.FIRE_DIRT);
    public static final DeferredBlock<Block> VOLCANIC_SAND = registerTerrainBlock("volcanic_sand", "Volcanic Sand", JBlockProperties.FIRE_SAND);
    public static final DeferredBlock<Block> VOLCANIC_SOIL = registerTerrainBlock("volcanic_soil", "Volcanic Soil", JBlockProperties.FIRE_DIRT);
    public static final DeferredBlock<Block> HOT_GROUND = registerTerrainBlock("hot_ground", "Hot Ground", JBlockProperties.FIRE_STONE);
    public static final DeferredBlock<Block> CHARRED_GRASS = registerGrassBlock("charred_grass", "Charred Grass", JGrassBlock::new);
    public static final DeferredBlock<Block> VOLCANIC_SANDSTONE = registerGrassBlock("volcanic_sandstone", "Volcanic Sandstone", () -> new Block(JBlockProperties.STONE), JBlockProperties.STONE);
    public static final DeferredBlock<Block> SCORCHED_STALAGMITE_TINY = registerModeledBlock("scorched_stalagmite_tiny", "Scorched Stalagmite", JBlockStalagmite::new);
    public static final DeferredBlock<Block> SCORCHED_STALAGMITE_SMALL = registerModeledBlock("scorched_stalagmite_small", "Scorched Stalagmite", JBlockStalagmite::new);
    public static final DeferredBlock<Block> SCORCHED_STALAGMITE_MED = registerModeledBlock("scorched_stalagmite_med", "Scorched Stalagmite", JBlockStalagmite::new);
    public static final DeferredBlock<Block> SCORCHED_STALAGMITE_LARGE = registerModeledBlock("scorched_stalagmite_large", "Scorched Stalagmite", JBlockStalagmite::new);
    public static final DeferredBlock<Block> CHARRED_BRUSH = registerModeledBlock("charred_brush", "Charred Brush", () -> new VineBlock(JBlockProperties.VINE));
    public static final DeferredBlock<Block> CHARRED_SHORT_GRASS = registerCrossBlock("charred_short_grass", "Charred Short Grass", () -> new TallGrassBlock(JBlockProperties.REPLACABLE_PLANT));
    public static final DeferredBlock<Block> CHARRED_WEEDS = registerCrossBlock("charred_weeds", "Charred Weeds", () -> new TallGrassBlock(JBlockProperties.REPLACABLE_PLANT));
    public static final DeferredBlock<Block> CRUMBLING_PINE = registerCrossBlock("crumbling_pine", "Crumbling Pine", () -> new TallGrassBlock(JBlockProperties.REPLACABLE_PLANT));
    public static final DeferredBlock<Block> CRISP_GRASS = registerCrossBlock("crisp_grass", "Crisp Grass", () -> new TallGrassBlock(JBlockProperties.REPLACABLE_PLANT));
    public static final DeferredBlock<Block> FLAME_POD = registerCrossBlock("flame_pod", "Flame Pod", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> LAVA_BLOOM = registerCrossBlock("lava_bloom", "Lava Bloom", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> INFERNO_BUSH = registerCrossBlock("inferno_bush", "Inferno Bush", () -> new TallGrassBlock(JBlockProperties.REPLACABLE_PLANT));
    public static final DeferredBlock<Block> SCORCHED_CACTUS = registerModeledBlock("scorched_cactus", "Scorched Cactus", JBlockCactus::new);
    public static final DeferredBlock<Block> CHARRED_LEAVES = registerTerrainBlock("charred_leaves", "Charred Leaves", JBlockProperties.LEAVES);
    public static final DeferredBlock<RotatedPillarBlock> BURNED_BARK = addLogBlock("burned_bark", "Burned Bark");
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_BURNED_BARK = registerPillar("stripped_burned_bark", "Stripped Burned Bark", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> BURNED_SAPLING = registerCrossBlock("burned_sapling", "Burned Sapling", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> CHARRED_SAPLING = registerCrossBlock("charred_sapling", "Charred Sapling", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> BURNED_PLANKS = registerFuelBlock("burned_bark_planks", "Burned Planks", JBlockProperties.WOOD, 300);
    public static final DeferredBlock<DoorBlock> BURNED_DOOR = registerDoor("burned_door", "Burned Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<TrapDoorBlock> BURNED_TRAP_DOOR = registerTrapDoor("burned_trap_door", "Burned Trap Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<StairBlock> BURNED_STAIRS = registerStairs("burned_stairs", "Burned Plank Stairs", BURNED_PLANKS, true, JBlockProperties.WOOD);
    public static final DeferredBlock<SlabBlock> BURNED_SLAB = registerSlab("burned_slab", "Burned Plank Slab", true, JBlockProperties.WOOD);
    public static final DeferredBlock<ButtonBlock> BURNED_BUTTON = registerButton("burned_button", "Burned Button", true, true, JBlockProperties.BUTTON);
    public static final DeferredBlock<PressurePlateBlock> BURNED_PRESSURE_PLATE = registerPressurePlate("burned_pressure_plate", "Burned Pressure Plate",true, JBlockProperties.WOOD);
    public static final DeferredBlock<FenceGateBlock> BURNED_FENCE_GATE = registerFenceGate("burned_fence_gate", "Burned Fence Gate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<JFenceBlock> BURNED_FENCE = registerFence("burned_fence", "Burned Fence", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> VOLCANIC_ROCK = registerModeledBlock("volcanic_rock", "Volcanic Rock", () -> new VolcanicRockBlock(JBlockProperties.VOLCANIC_BLOCK));
    public static final DeferredBlock<Block> BOIL_LOCK = registerRotatableBlock("boil_lock", "Boiling Lock", LockBlock::new, false);
    public static final DeferredBlock<Block> TALL_MOLTEN_PLANT = registerDoublePlant("tall_molten_plant", "Tall Molten Plant", () -> new JDoublePlantBlock(JBlockProperties.PLANT));
    public static final DeferredBlock<Block> TALL_CRUMBLING_PINE = registerDoublePlant("tall_crumbling_pine", "Tall Crumbling Pine", () -> new JDoublePlantBlock(JBlockProperties.PLANT));
    public static final DeferredBlock<Block> TALL_CHARRED_GRASS = registerDoublePlant("tall_charred_grass", "Tall Charred Grass", () -> new JDoublePlantBlock(JBlockProperties.PLANT));
    public static final DeferredBlock<Block> TALL_SIZZLESHROOM = registerDoublePlant("tall_sizzleshroom", "Tall Sizzleshroom", () -> new TallGlowshroomBlock(JBlockProperties.CAVE_GLOW_PLANT));
    public static final DeferredBlock<Block> SIZZLESHROOM = registerCrossBlock("sizzleshroom", "Sizzleshroom", () -> new CavePlantBlock(JBlockProperties.CAVE_GLOW_PLANT));

    public static final DeferredBlock<Block> DEPTHS_PORTAL_FRAME = registerEndPortalFrameStyleBlock("depths_portal_frame", "Depths Portal Frame", DepthsPortalFrameBlock::new);
    public static final DeferredBlock<Block> DEPTHS_PORTAL = registerEndPortalStyleBlock("depths_portal", "Depths Portal", DepthsPortalBlock::new);
    public static final DeferredBlock<Block> DEPTHS_GRASS = registerGrassBlock("depths_grass", "Depths Grass", JGrassBlock::new);
    public static final DeferredBlock<Block> DEPTHS_PATH = registerPathBlock("depths_path", "Depths Path", () -> new JDirtPathBlock(JBlockProperties.PATH));
    public static final DeferredBlock<Block> DEPTHS_DIRT = registerTerrainBlock("depths_dirt", "Depths Soil", JDirt::new);
    public static final DeferredBlock<Block> DEPTHS_STONE = registerTerrainBlock("depths_stone", "Depths Stone", JBlockProperties.STONE);
    public static final DeferredBlock<RotatedPillarBlock> DEPTHS_LOG = addLogBlock("depths_log", "Depths Log");
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_DEPTHS_LOG = registerPillar("stripped_depths_log", "Stripped Depths Log", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> DEPTHS_LEAVES = registerTerrainBlock("depths_leaves", "Depths Leaves", JBlockProperties.LUMINESCENT_LEAVES);
    public static final DeferredBlock<Block> DEPTHS_SAPLING = registerCrossBlock("depths_sapling", "Depths Sapling", () -> new JSaplingBlock(JTreeGrower.DEPTHS_TREE_GROWER, JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> DEPTHS_PLANKS = registerFuelBlock("depths_planks", "Depths Planks", JBlockProperties.WOOD, 300);
    public static final DeferredBlock<DoorBlock> DEPTHS_DOOR = registerDoor("depths_door", "Depths Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<TrapDoorBlock> DEPTHS_TRAP_DOOR = registerTrapDoor("depths_trap_door", "Depths Trap Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<StairBlock> DEPTHS_STAIRS = registerStairs("depths_stairs", "Depths Plank Stairs", DEPTHS_PLANKS, true, JBlockProperties.WOOD);
    public static final DeferredBlock<SlabBlock> DEPTHS_SLAB = registerSlab("depths_slab", "Depths Plank Slab", true, JBlockProperties.WOOD);
    public static final DeferredBlock<ButtonBlock> DEPTHS_BUTTON = registerButton("depths_button", "Depths Button", true, true, JBlockProperties.BUTTON);
    public static final DeferredBlock<PressurePlateBlock> DEPTHS_PRESSURE_PLATE = registerPressurePlate("depths_pressure_plate", "Depths Pressure Plate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<FenceGateBlock> DEPTHS_FENCE_GATE = registerFenceGate("depths_fence_gate", "Depths Fence Gate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<JFenceBlock> DEPTHS_FENCE = registerFence("depths_fence", "Depths Fence", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> DEPTHS_CRYSTAL = registerAttachedCrossBlock("depths_crystal", "Depths Crystal", () -> new AttachedBlock(JBlockProperties.GLOW_BLOCK.noCollission().noOcclusion()));
    public static final DeferredBlock<Block> DEPTHS_CRYSTAL_BLOCK = register("depths_crystal_block", "Depths Crystal Block", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BUDDING_DEPTHS_CRYSTAL = register("budding_depths_crystal", "Budding Depths Crystal", JBlockProperties.STONE);
    public static final DeferredBlock<Block> FLOOR_DEPTHS_CRYSTAL_BLUE = registerModeledBlock("floor_depths_crystal_blue", "Depths Crystal", FloorDepthsCrystalBlock::new);
    public static final DeferredBlock<Block> FLOOR_DEPTHS_CRYSTAL_PINK = registerModeledBlock("floor_depths_crystal_pink", "Depths Crystal", FloorDepthsCrystalBlock::new);
    public static final DeferredBlock<Block> FLOOR_DEPTHS_CRYSTAL_YELLOW = registerModeledBlock("floor_depths_crystal_yellow", "Depths Crystal", FloorDepthsCrystalBlock::new);
    public static final DeferredBlock<Block> FLOOR_DEPTHS_CRYSTAL_GREEN = registerModeledBlock("floor_depths_crystal_green", "Depths Crystal", FloorDepthsCrystalBlock::new);
    public static final DeferredBlock<Block> DARK_BRICK = register("dark_brick", "Dark Brick", JBlockProperties.STONE);
    public static final DeferredBlock<Block> DARK_FLOOR = register("dark_floor", "Dark Floor", JBlockProperties.STONE);
    public static final DeferredBlock<Block> DARK_SHINGLE = register("dark_shingle", "Dark Shingle", JBlockProperties.STONE);
    public static final DeferredBlock<Block> DEPTHS_BRICK = register("depths_brick", "Depths Brick", JBlockProperties.STONE);
    public static final DeferredBlock<Block> DEPTHS_SHINGLE = register("depths_shingle", "Depths Shingle", JBlockProperties.STONE);
    public static final DeferredBlock<Block> DEPTHS_DARK_SHINGLE = register("depths_dark_shingle", "Depths Dark Shingle", JBlockProperties.STONE);
    public static final DeferredBlock<Block> DEPTHS_COBBLESTONE = register("depths_cobblestone", "Depths Cobblestone", JBlockProperties.STONE);
    public static final DeferredBlock<Block> DEPTHS_TILE = register("depths_tile", "Depths Tile", JBlockProperties.STONE);
    public static final DeferredBlock<Block> DEPTHS_GLASS = register("depths_glass", "Depths Glass", () -> new JTransparentBlock(JBlockProperties.GLASS), JBlockProperties.GLASS);
    public static final DeferredBlock<RotatedPillarBlock> DEPTHS_PILLAR = registerPillar("depths_pillar", "Depths Pillar", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> DARKLY_LOCK = registerRotatableBlock("darkly_lock", "Darkly Lock", LockBlock::new, false);
    public static final DeferredBlock<Block> DEPTHS_LOCK = registerRotatableBlock("depths_lock", "Depths Lock", LockBlock::new, false);
    public static final DeferredBlock<RotatedPillarBlock> DEPTHS_BOOK_SHELF = registerPillar("depths_book_shelf", "Depths Bookshelf", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> DEPTHS_BLUE_FLOWER = registerCrossBlock("depths_blue_flower", "Depths Blue Flower", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> DEPTHS_FLOWER = registerCrossBlock("depths_flower", "Depths Flower", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> DEPTHS_FURNACE = registerFurnaceBlock("depths_furnace", "Depths Furnace");
    public static final DeferredBlock<IronBarsBlock> DEPTHS_GATE = registerPaneBlock("depths_gate", "Depths Gate", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> DARK_SORCERER_SPAWNER = register("dark_sorcerer_spawner", "Dark Sorcerer Spawner", DarkSorcererSpawnerBlock::new, JBlockProperties.SPAWNER);
    public static final DeferredBlock<Block> POINTED_CRYSTALLIZED_DRIPSTONE = registerDripstoneBlock("crystallized_pointed_dripstone", "Crystallized Dripstone", () -> new CrystallizedDripstoneBlock(JBlockProperties.POINTED_DRIPSTONE));
    public static final DeferredBlock<Block> CRYSTALLIZED_DRIPSTONE = register("crystallized_dripstone", "Crystallized Dripstone", JBlockProperties.DRIPSTONE);
    public static final DeferredBlock<Block> DEPTHS_MOSS_BLOCK = register("depths_moss_block", "Depths Moss Block", JBlockProperties.DIRT);

    public static final DeferredBlock<Block> CORBA_PORTAL_FRAME = registerEndPortalFrameStyleBlock("corba_portal_frame", "Corba Portal Frame", CorbaPortalFrameBlock::new);
    public static final DeferredBlock<Block> CORBA_PORTAL = registerEndPortalStyleBlock("corba_portal", "Corba Portal", CorbaPortalBlock::new);
    public static final DeferredBlock<Block> CORBA_DIRT = registerTerrainBlock("corba_dirt", "Corba Dirt", JDirt::new);
    public static final DeferredBlock<Block> CORBA_GRASS = registerOverlayGrassBlock("corba_grass", "Corba Grass", JGrassBlock::new);
    public static final DeferredBlock<Block> CORBA_STONE = registerTerrainBlock("corba_stone", "Corba Stone", JBlockProperties.STONE);
    public static final DeferredBlock<Block> CORBA_PATH = registerPathBlock("corba_path", "Corba Path", () -> new JDirtPathBlock(JBlockProperties.PATH));
    public static final DeferredBlock<JFenceBlock> CORBA_POST = registerFence("corba_post", "Corba Post", true, JBlockProperties.WOOD);
    public static final DeferredBlock<JWallBlock> CORBA_COBBLESTONE_WALL = registerWallBlock("corba_cobblestone_wall", "Corba Cobblestone Wall", JBlockProperties.STONE);
    public static final DeferredBlock<RotatedPillarBlock> CORBA_PLILLAR = registerPillar("corba_pillar", "Corba Pillar", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> TAINTED_MUD = registerTerrainBlock("tainted_mud", "Tainted Mud", JBlockProperties.DIRT);
    public static final DeferredBlock<Block> DRIED_MUD = registerTerrainBlock("dried_mud", "Dried Mud", JBlockProperties.DIRT);
    public static final DeferredBlock<RotatedPillarBlock> BOGWOOD_LOG = addLogBlock("bogwood_log", "Bogwood Log");
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_BOGWOOD_LOG = registerPillar("stripped_bogwood_log", "Stripped Bogwood Log", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> BOGWOOD_LEAVES = registerTintedLeavesBlock("bogwood_leaves", "Bogwood Leaves", JBlockProperties.LEAVES);
    public static final DeferredBlock<Block> BOGWOOD_SAPLING = registerCrossBlock("bogwood_sapling", "Bogwood Sapling", () -> new JSaplingBlock(JTreeGrower.BOGWOOD_TREE_GROWER, JBlockProperties.FLOWER));
    public static final DeferredBlock<RotatedPillarBlock> CORBA_LOG = addLogBlock("corba_log", "Corba Log");
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CORBA_LOG = registerPillar("stripped_corba_log", "Stripped Corba Log", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> CORBA_LEAVES = registerTintedLeavesBlock("corba_leaves", "Corba Leaves", JBlockProperties.LEAVES);
    public static final DeferredBlock<Block> CORBA_SAPLING = registerCrossBlock("corba_sapling", "Corba Sapling", () -> new JSaplingBlock(JTreeGrower.CORBA_TREE_GROWER, JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> CORBA_PLANKS = registerFuelBlock("corba_planks", "Corba Planks", JBlockProperties.WOOD, 300);
    public static final DeferredBlock<DoorBlock> CORBA_DOOR = registerDoor("corba_door", "Corba Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<TrapDoorBlock> CORBA_TRAP_DOOR = registerTrapDoor("corba_trap_door", "Corba Trap Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<StairBlock> CORBA_STAIRS = registerStairs("corba_stairs", "Corba Plank Stairs", CORBA_PLANKS, true, JBlockProperties.WOOD);
    public static final DeferredBlock<SlabBlock> CORBA_SLAB = registerSlab("corba_slab", "Corba Plank Slab", true, JBlockProperties.WOOD);
    public static final DeferredBlock<ButtonBlock> CORBA_BUTTON = registerButton("corba_button", "Corba Button", true, true, JBlockProperties.BUTTON);
    public static final DeferredBlock<PressurePlateBlock> CORBA_PRESSURE_PLATE = registerPressurePlate("corba_pressure_plate", "Corba Pressure Plate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<FenceGateBlock> CORBA_FENCE_GATE = registerFenceGate("corba_fence_gate", "Corba Fence Gate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<JFenceBlock> CORBA_FENCE = registerFence("corba_fence", "Corba Fence", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> CORBA_COBBLESTONE = register("corba_cobblestone", "Corba Cobblestone", JBlockProperties.STONE);
    public static final DeferredBlock<Block> CORBA_FURNACE = registerFurnaceBlock("corba_furnace", "Corba Furnace");
    public static final DeferredBlock<Block> CORBA_BRICKS = register("corba_bricks", "Corba Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> CORBA_BRICK_STAIRS = registerStairs("corba_brick_stairs", "Corba Brick Stairs", CORBA_BRICKS, false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> CORBA_CRACKED_BRICKS = register("corba_cracked_bricks", "Corba Cracked Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<Block> CORBA_DARK_BRICKS = register("corba_dark_bricks", "Corba Dark Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<Block> CORBA_LIGHT_BRICKS = register("corba_light_bricks", "Corba Light Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<Block> OVERSEER_ELDER_SPAWNER = register("overseer_elder_spawner", "Overseer Elder Spawner", OverseerElderSpawnerBlock::new, JBlockProperties.SPAWNER);
    public static final DeferredBlock<Block> OVERSEER_SPAWNER = register("overseer_spawner", "Overseer Spawner", OverseerSpawnerBlock::new, JBlockProperties.SPAWNER);
    public static final DeferredBlock<Block> ELDER_BLOCK = register("elder_block", "Elder Block", ChangableBlock::new);
    public static final DeferredBlock<Block> CORBA_SENTRY_BRICKS = register("corba_sentry_bricks", "Corba Sentry Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<Block> CORBA_LADDER = registerLadder("corba_ladder", "Corba Ladder", () -> new LadderBlock(JBlockProperties.LADDER));
    public static final DeferredBlock<Block> CORBA_BLUE_FLOWER = registerCrossBlock("corba_blue_flower", "Corba Blue Flower", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> CORBA_RED_FLOWER = registerCrossBlock("corba_red_flower", "Corba Red Flower", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> CORBA_SPECKLED_FLOWER = registerCrossBlock("corba_speckled_flower", "Corba Speckled Flower", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> CORBA_PURPLE_FLOWER = registerCrossBlock("corba_purple_flower", "Corba Purple Flower", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> CORBA_LIGHT_PURPLE_FLOWER = registerCrossBlock("corba_light_purple_flower", "Corba Light Purple Flower", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> CORBA_DARK_PURPLE_FLOWER = registerCrossBlock("corba_dark_purple_flower", "Corba Dark Purple Flower", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> CORBA_FLOWER = registerCrossBlock("corba_flower", "Corba Flower", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> CORBA_TALL_GRASS = registerTintedCrossBlock("corba_tall_grass", "Corba Tall Grass", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> SMALL_BOGSHROOM = registerModeledBlock("small_bogshroom", "Small Bogshroom", () -> new TallGrassBlock(JBlockProperties.GLOW_FLOWER));
    public static final DeferredBlock<Block> TALL_BOGSHROOM = registerModeledBlock("tall_bogshroom", "Tall Bogshroom", () -> new TallGrassBlock(JBlockProperties.GLOW_FLOWER));
    public static final DeferredBlock<Block> BOGWEED = registerDoublePlant("bogweed", "Bogweed", () -> new JDoublePlantBlock(JBlockProperties.PLANT));
    public static final DeferredBlock<Block> SWAMP_LILY = registerLilyPad("swamp_lily", "Swamp Lilly", () -> new WaterlilyBlock(JBlockProperties.LILY_PLANT));
    public static final DeferredBlock<Block> FUNGAL_SHELF = registerModeledBlock("fungal_shelf", "Fungal Shelf", JBlockFungalShelf::new);
    public static final DeferredBlock<Block> SLIME = registerSlimeStyleBlock("slime", "Slime", JSlimeBlock::new);
    public static final DeferredBlock<Block> SWAMP_LAMP = registerModeledBlock("swamp_lamp", "Swamp Lamp", JBlockSwampLamp::new);

    public static final DeferredBlock<Block> TERRANIAN_PORTAL_FRAME = register("terranian_portal_frame", "Terranian Portal Frame", JBlockProperties.STONE);
    public static final DeferredBlock<JBasePortalBlock> TERRANIAN_PORTAL = registerPortalBlock("terranian_portal", "Terranian Portal", () -> new JBasePortalBlock(Dimensions.TERRANIA, TERRANIAN_PORTAL_FRAME));
    public static final DeferredBlock<Block> TERRANIAN_GRASS = registerGrassBlock("terranian_grass", "Terranian Grass", JGrassBlock::new);
    public static final DeferredBlock<Block> TERRANIAN_DIRT = registerTerrainBlock("terranian_dirt", "Terranian Dirt", JDirt::new);
    public static final DeferredBlock<Block> TERRANIAN_STONE = register("terranian_stone", "Terranian Stone", JBlockProperties.STONE);
    public static final DeferredBlock<Block> TERRANIAN_LEAVES = registerTopBottomBlock("terranian_leaves", "Terrania Leaves", () -> new Block(JBlockProperties.LEAVES));
    public static final DeferredBlock<Block> TERRANIAN_VINE = registerVineBlock("terranian_vine", "Terrania Vine", () -> new JVineBlock(JBlockProperties.VINE));
    public static final DeferredBlock<RotatedPillarBlock> TERRANIAN_LOG = addLogBlock("terranian_log", "Terranian Log");
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_TERRANIAN_LOG = registerPillar("stripped_terranian_log", "Stripped Terranian Log", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> TERRANIAN_PLANKS = register("terranian_planks", "Terranian Planks", JBlockProperties.WOOD);
    public static final DeferredBlock<DoorBlock> TERRANIAN_DOOR = registerDoor("terranian_door", "Terranian Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<TrapDoorBlock> TERRANIAN_TRAP_DOOR = registerTrapDoor("terranian_trap_door", "Terranian Trap Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<StairBlock> TERRANIAN_STAIRS = registerStairs("terranian_stairs", "Terranian Plank Stairs", CORBA_PLANKS, true, JBlockProperties.WOOD);
    public static final DeferredBlock<SlabBlock> TERRANIAN_SLAB = registerSlab("terranian_slab", "Terranian Plank Slab", true, JBlockProperties.WOOD);
    public static final DeferredBlock<ButtonBlock> TERRANIAN_BUTTON = registerButton("terranian_button", "Terranian Button", true, true, JBlockProperties.BUTTON);
    public static final DeferredBlock<PressurePlateBlock> TERRANIAN_PRESSURE_PLATE = registerPressurePlate("terranian_pressure_plate", "Terranian Pressure Plate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<JFenceBlock> TERRANIAN_POST = registerFence("terranian_post", "Terranian Post", true, JBlockProperties.WOOD);
    public static final DeferredBlock<FenceGateBlock> TERRANIAN_FENCE_GATE = registerFenceGate("terranian_fence_gate", "Terranian Fence Gate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<IronBarsBlock> TERRANIAN_BARS = registerPaneBlock("terranian_bars", "Terranian Bars", JBlockProperties.STONE);
    public static final DeferredBlock<Block> TERRANIAN_DARK_PANELS = register("terranian_dark_panels", "Terranian Dark Panels", JBlockProperties.STONE);
    public static final DeferredBlock<Block> TERRANIAN_PANELS = register("terranian_panels", "Terranian Panels", JBlockProperties.STONE);
    public static final DeferredBlock<Block> TERRANIAN_TALL_GRASS = registerCrossBlock("terranian_tall_grass", "Terranian Tall Grass", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> TERRAMUSHROOM = registerCrossBlock("terramushroom", "Terranian Shroom", () -> new TallGrassBlock(JBlockProperties.FLOWER));//makes big terrashroom
    public static final DeferredBlock<Block> TALL_TERRAMUSHROOM = registerDoublePlant("tall_terramushroom", "Tall Terranian Shroom", () -> new JDoublePlantBlock(JBlockProperties.GLOW_FLOWER));
    public static final DeferredBlock<Block> TERRANIAN_FLOWER = registerCrossBlock("terranian_flower", "Terranian Flower", () -> new TallGrassBlock(JBlockProperties.FLOWER));
    public static final DeferredBlock<Block> ENCHANTED_SHROOMS_SMALL = registerModeledBlock("enchanted_shrooms_small", "Enchanted Shrooms", () -> new TallGrassBlock(JBlockProperties.GLOW_FLOWER));
    public static final DeferredBlock<Block> ENCHANTED_SHROOMS_TALL = registerModeledBlock("enchanted_shroom_tall", "Tall Enchanted Shroom",  () -> new JDoublePlantBlock(JBlockProperties.GLOW_FLOWER));
    public static final DeferredBlock<Block> TERRAMUSHROOM_BLOCK_PINK = registerMushroomBlock("terrashroom_block_pink", "Terrashroom Block", () -> new HugeMushroomBlock(JBlockProperties.MUSHROOM_BLOCK));
    public static final DeferredBlock<Block> TERRAMUSHROOM_BLOCK_PURPLE = registerMushroomBlock("terrashroom_block_purple", "Terrashroom Block", () -> new HugeMushroomBlock(JBlockProperties.MUSHROOM_BLOCK));
    public static final DeferredBlock<Block> TERRASHROOM_STEM = registerMushroomBlock("terrashroom_stem", "Terrashroom Stem", () -> new HugeMushroomBlock(JBlockProperties.MUSHROOM_BLOCK));
    public static final DeferredBlock<Block> TERRANIAN_SAPLING = registerCrossBlock("terranian_sapling", "Terranian Sapling", () -> new JSaplingBlock(JTreeGrower.TERRRANIAN_TREE_GROWER, JBlockProperties.FLOWER));

    public static final DeferredBlock<Block> CLOUDIA_PORTAL_FRAME = register("cloudia_portal_frame", "Cloudia Portal Frame", JBlockProperties.STONE);
    public static final DeferredBlock<JBasePortalBlock> CLOUDIA_PORTAL = registerPortalBlock("cloudia_portal", "Cloudia Portal", () -> new JBasePortalBlock(Dimensions.CLOUDIA, CLOUDIA_PORTAL_FRAME));
    public static final DeferredBlock<Block> CLOUDIA_DIRT = registerTerrainBlock("cloudia_dirt", "Cloudia Dirt", JDirt::new);
    public static final DeferredBlock<Block> CLOUDIA_GRASS = registerGrassBlock("cloudia_grass", "Cloudia Grass", JGrassBlock::new);
    public static final DeferredBlock<RotatedPillarBlock> CLOUDIA_LOG = addLogBlock("cloudia_log", "Cloudia Log");
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CLOUDIA_LOG = registerPillar("stripped_cloudia_log", "Stripped Cloudia Log", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> CLOUDIA_ROCK = register("cloudia_rock", "Cloudia Rock", JBlockProperties.STONE);
    public static final DeferredBlock<RotatedPillarBlock> CLOUDIA_PILLAR = registerPillar("cloudia_pillar", "Cloudia Pillar", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> CLOUDIA_PLANKS = register("cloudia_planks", "Cloudia Planks", JBlockProperties.WOOD);
    public static final DeferredBlock<DoorBlock> CLOUDIA_DOOR = registerDoor("cloudia_door", "Cloudia Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<TrapDoorBlock> CLOUDIA_TRAP_DOOR = registerTrapDoor("cloudia_trap_door", "Cloudia Trap Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<StairBlock> CLOUDIA_STAIRS = registerStairs("cloudia_stairs", "Cloudia Plank Stairs", CORBA_PLANKS, true, JBlockProperties.WOOD);
    public static final DeferredBlock<SlabBlock> CLOUDIA_SLAB = registerSlab("cloudia_slab", "Cloudia Plank Slab", true, JBlockProperties.WOOD);
    public static final DeferredBlock<ButtonBlock> CLOUDIA_BUTTON = registerButton("cloudia_button", "Cloudia Button", true, true, JBlockProperties.BUTTON);
    public static final DeferredBlock<PressurePlateBlock> CLOUDIA_PRESSURE_PLATE = registerPressurePlate("cloudia_pressure_plate", "Cloudia Pressure Plate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<FenceGateBlock> CLOUDIA_FENCE_GATE = registerFenceGate("cloudia_fence_gate", "Cloudia Fence Gate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<JFenceBlock> CLOUDIA_FENCE = registerFence("cloudia_fence", "Cloudia Fence", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> CLOUDIA_BRICK = register("cloudia_brick", "Cloudia Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> CLOUDIA_WALL = registerWallBlock("cloudia_wall", "Cloudia Wall", JBlockProperties.STONE);
    public static final DeferredBlock<Block> CLOUDIA_TILE = register("cloudia_tile", "Cloudia Tile", JBlockProperties.STONE);
    public static final DeferredBlock<Block> CLOUDIA_LEAVES = register("cloudia_leaves", "Cloudia Leaves", JBlockProperties.LUMINESCENT_LEAVES);
    public static final DeferredBlock<Block> CLOUDIA_COBBLESTONE = register("cloudia_cobblestone", "Cloudia Cobblestone", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> CLOUDIA_BRICK_STAIRS = registerStairs("cloudia_brick_stairs", "Cloudia Brick Stairs", CLOUDIA_BRICK, false, JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> CLOUDIA_TILE_STAIRS = registerStairs("cloudia_tile_stairs", "Cloudia Tile Stairs",CLOUDIA_TILE, false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> CLOUDIA_POST = registerFence("cloudia_post", "Cloudia Post", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> PINK_CLOUDIA_CLOUD = register("pink_cloudia_cloud", "Pink Cloudia Cloud", () -> new JTransparentBlock(JBlockProperties.CLOUD));
    public static final DeferredBlock<Block> BLUE_CLOUDIA_CLOUD = register("blue_cloudia_cloud", "Blue Cloudia Cloud", () -> new JTransparentBlock(JBlockProperties.CLOUD));
    public static final DeferredBlock<Block> LIGHT_BLUE_CLOUDIA_CLOUD = register("light_blue_cloudia_cloud", "Light Blue Cloudia Cloud", () -> new JTransparentBlock(JBlockProperties.CLOUD));
    public static final DeferredBlock<SlabBlock> CLOUDIA_TILE_SLAB = registerSlab("cloudia_tile_slab", "Cloudia Tile Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> CLOUDIA_BRICK_SLAB = registerSlab("cloudia_brick_slab", "Cloudia Brick Slab", false, JBlockProperties.STONE);

    public static final DeferredBlock<Block> SENTERIAN_PORTAL_FRAME = registerEndPortalFrameStyleBlock("senterian_portal_frame", "Senterian Portal Frame", SenterianPortalFrameBlock::new);
    public static final DeferredBlock<Block> SENTERIAN_PORTAL = registerEndPortalStyleBlock("senterian_portal", "Senterian Portal", SenterianPortalBlock::new);
    public static final DeferredBlock<IronBarsBlock> SENTERIAN_BARS = registerPaneBlock("senterian_bars", "Senterian Bars", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> SENTERIAN_BRICKS = register("senterian_bricks", "Senterian Bricks", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<StairBlock> SENTERIAN_BRICK_STAIRS = registerStairs("senterian_brick_stairs", "Senterian Brick Stairs", SENTERIAN_BRICKS, false, JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> SENTERIAN_CARVED_ROCK = register("senterian_carved_rock", "Senterian Carved Rock", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> SENTERIAN_FLOOR = register("senterian_floor", "Senterian Floor", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> SENTERIAN_ROCK = register("senterian_rock", "Senterian Rock", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> SENTERIAN_GLASS = register("senterian_glass", "Senterian Glass", () -> new JTransparentBlock(JBlockProperties.GLASS), JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> SENTERIAN_ALTAR = registerModeledBlock("senterian_altar", "Senterian Altar", () -> new SenterianAltar(JBlockProperties.STONE.lightLevel((l) -> 2).noOcclusion()));
    public static final DeferredBlock<JFenceBlock> SENTERIAN_POST = registerFence("senterian_post", "Senterian Post", false, JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> SENTRY_LOCK = registerRotatableBlock("sentry_lock", "Sentry Lock", LockBlock::new, false);

    public static final DeferredBlock<IronBarsBlock> BREAKABLE_SENTERIAN_BARS = registerPaneBlock("breakable_senterian_bars", "Senterian Bars", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BREAKABLE_SENTERIAN_BRICKS = register("breakable_senterian_bricks", "Senterian Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> BREAKABLE_SENTERIAN_BRICK_STAIRS = registerStairs("breakable_senterian_brick_stairs", "Senterian Brick Stairs", BREAKABLE_SENTERIAN_BRICKS, false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> BREAKABLE_SENTERIAN_CARVED_ROCK = register("breakable_senterian_carved_rock", "Senterian Carved Rock", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BREAKABLE_SENTERIAN_FLOOR = register("breakable_senterian_floor", "Senterian Floor", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BREAKABLE_SENTERIAN_ROCK = register("breakable_senterian_rock", "Senterian Rock", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BREAKABLE_SENTERIAN_GLASS = register("breakable_senterian_glass", "Senterian Glass", () -> new JTransparentBlock(JBlockProperties.GLASS), JBlockProperties.GLASS);
    public static final DeferredBlock<JFenceBlock> BREAKABLE_SENTERIAN_POST = registerFence("breakable_senterian_post", "Senterian Post", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> BREAKABLE_SENTERIAN_GUARDIAN_LAMP = register("breakable_senterian_guardian_lamp", "Senterian Guardian Lamp", JBlockProperties.GLOW_BLOCK);
    public static final DeferredBlock<Block> BREAKABLE_SENTERIAN_LIGHT_LAMP = register("breakable_senterian_light_lamp", "Senterian Light Lamp", JBlockProperties.GLOW_BLOCK);
    public static final DeferredBlock<Block> BREAKABLE_SENTERIAN_MELLOW_LAMP = register("breakable_senterian_mellow_lamp", "Senterian Mellow Lamp", JBlockProperties.GLOW_BLOCK);

    public static final DeferredBlock<Block> JOURNEY_CHEST = registerChestBlock("journey_chest", "Journey Chest", JChestBlock::new);
    public static final DeferredBlock<Block> NETHER_CHEST = registerChestBlock("nether_chest", "Nether Chest", JChestBlock::new);
    public static final DeferredBlock<Block> FROZEN_CHEST = registerChestBlock("frozen_chest", "Frozen Chest", JChestBlock::new);
    public static final DeferredBlock<Block> EUCA_CHEST = registerChestBlock("euca_chest", "Euca Chest", JChestBlock::new);
    public static final DeferredBlock<Block> BOIL_CHEST = registerChestBlock("boil_chest", "Boiling Chest", JChestBlock::new);
    public static final DeferredBlock<Block> DEPTHS_CHEST = registerChestBlock("depths_chest", "Depths Chest", JChestBlock::new);
    public static final DeferredBlock<Block> CORBA_CHEST = registerChestBlock("corba_chest", "Corba Chest", JChestBlock::new);
    public static final DeferredBlock<Block> TERRANIAN_CHEST = registerChestBlock("terranian_chest", "Terranian Chest", JChestBlock::new);
    public static final DeferredBlock<Block> CLOUDIA_CHEST = registerChestBlock("cloudia_chest", "Cloudia Chest", JChestBlock::new);
    public static final DeferredBlock<Block> SENTERIAN_CHEST = registerChestBlock("senterian_chest", "Senterian Chest", JChestBlock::new);

    public static final DeferredBlock<Block> ROCKITE_SPAWNER = registerModeledBlock("rockite_spawner", "Rockite Spawner", () -> new RockiteBlock(JBlockProperties.ROCKITE_SPAWNER));

    public static final DeferredBlock<Block> FROZEN_PEDESTAL = registerModeledBlock("frozen_pedestal", "Frozen Pedestal", PedestalBlock::new);
    public static final DeferredBlock<Block> ROYAL_PEDESTAL = registerModeledBlock("royal_pedestal", "Royal Pedestal", PedestalBlock::new);
    public static final DeferredBlock<Block> OKOLOO_PEDESTAL = registerModeledBlock("okoloo_pedestal", "Okoloo Pedestal", OkolooPedestalBlock::new);

    public static final DeferredBlock<Block> BLOOD_ROCK = register("blood_rock", "Blood Rock", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BLOOD_RUNE = register("blood_rune", "Blood Rune", JBlockProperties.STONE);
    public static final DeferredBlock<RotatedPillarBlock> BLOOD_PILLAR = registerPillar("blood_pillar", "Blood Pillar", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> BLOOD_BRICKS = register("blood_bricks", "Blood Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<Block> CARVED_BLOOD_ROCK = register("carved_blood_rock", "Carved Blood Rock", JBlockProperties.STONE);
    public static final DeferredBlock<Block> OBELISK = register("obelisk", "Obelisk", JBlockProperties.BREAKABLE_DUNGEON_LAMP);
    public static final DeferredBlock<Block> BLOOD_LAMP = register("blood_lamp", "Blood Lamp", JBlockProperties.GLOW_BLOCK);
    public static final DeferredBlock<Block> SUMMONING_TABLE = registerModeledBlock("summoning_table", "Summoning Table", () -> new SummoningTableBlock(JBlockProperties.GRINDSTONE));

    public static final DeferredBlock<Block> OKOLOO_TROPHY = registerTrophyBlock("okoloo_trophy", "Okoloo Trophy");
    public static final DeferredBlock<Block> WITHERING_BEAST_TROPHY = registerTrophyBlock("wither_beast_trophy", "Withering Beast Trophy");
    public static final DeferredBlock<Block> CALCIA_TROPHY = registerTrophyBlock("calcia_trophy", "Calcia Trophy");
    public static final DeferredBlock<Block> SOUL_WATCHER_TROPHY = registerTrophyBlock("soul_watcher_trophy", "Soul Watcher Trophy");
    public static final DeferredBlock<Block> EUDOR_TROPHY = registerTrophyBlock("eudor_trophy", "Eudor Trophy");
    public static final DeferredBlock<Block> CORALLATOR_TROPHY = registerTrophyBlock("corallator_trophy", "Corallator Trophy");
    public static final DeferredBlock<Block> BLAZIER_TROPHY = registerTrophyBlock("blazier_trophy", "Blazier Trophy");
    public static final DeferredBlock<Block> THUNDER_BIRD_TROPHY = registerTrophyBlock("thunder_bird_trophy", "Thunderbird Trophy");
    public static final DeferredBlock<Block> SCALE_TROPHY = registerTrophyBlock("scale_trophy", "Scale Trophy");
    public static final DeferredBlock<Block> LOGGER_TROPHY = registerTrophyBlock("logger_trophy", "Logger Trophy");
    public static final DeferredBlock<Block> SENTRY_KING_TROPHY = registerTrophyBlock("sentry_king_trophy", "Sentry King Trophy");
    public static final DeferredBlock<Block> TERRANIAN_PROTECTOR_TROPHY = registerTrophyBlock("terranian_protector_trophy", "Terranian Protector Trophy");
    public static final DeferredBlock<Block> SKY_STALKER_TROPHY = registerTrophyBlock("sky_stalker_trophy", "Sky Stalker Trophy");

    public static final DeferredBlock<RotatedPillarBlock> STONE_PLILLAR = registerPillar("stone_pillar", "Stone Pillar", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> SMALL_STONE_BRICKS = register("small_stone_bricks", "Small Stone Bricks", JBlockProperties.STONE);

    public static final DeferredBlock<Block> ANCIENT_OBELISK = registerModeledBlock("ancient_obelisk", "Ancient Obelisk", () -> new ObeliskBlock(JBlockProperties.ANCIENT_STONE.lightLevel((l) -> 2).noOcclusion()));
    public static final DeferredBlock<Block> ANCIENT_SOCKET = registerModeledBlock("ancient_socket", "Ancient Socket", AncientSocketBlock::new);
    public static final DeferredBlock<Block> ANCIENT_CATALYST = register("ancient_catalyst", "Ancient Catalyst", AncientCatalystBlock::new);
    public static final DeferredBlock<RotatedPillarBlock> ANCIENT_STONE = registerPillar("ancient_stone", "Ancient Stone", false, JBlockProperties.ANCIENT_STONE);
    public static final DeferredBlock<RotatedPillarBlock> ANCIENT_RUNIC_STONE_0 = registerPillar("ancient_stone_runic_0", "Ancient Runic Stone", false, JBlockProperties.ANCIENT_STONE);
    public static final DeferredBlock<RotatedPillarBlock> ANCIENT_RUNIC_STONE_1 = registerPillar("ancient_stone_runic_1", "Ancient Runic Stone", false, JBlockProperties.ANCIENT_STONE);
    public static final DeferredBlock<RotatedPillarBlock> ANCIENT_RUNIC_STONE_2 = registerPillar("ancient_stone_runic_2", "Ancient Runic Stone", false, JBlockProperties.ANCIENT_STONE);
    public static final DeferredBlock<RotatedPillarBlock> ANCIENT_RUNIC_STONE_3 = registerPillar("ancient_stone_runic_3", "Ancient Runic Stone", false, JBlockProperties.ANCIENT_STONE);

    public static final DeferredBlock<Block> TOTEM_BASE = registerTotemBlock("totem_base", "Totem");
    public static final DeferredBlock<Block> TOTEM_ANGRY = registerTotemBlock("totem_angry", "Angry Totem");
    public static final DeferredBlock<Block> TOTEM_HAPPY = registerTotemBlock("totem_happy", "Happy Totem");
    public static final DeferredBlock<Block> TOTEM_SAD = registerTotemBlock("totem_sad", "Sad Totem");
    public static final DeferredBlock<Block> TOTEM_SCARED = registerTotemBlock("totem_scared", "Scared Totem");

    //OVERWORLD
    public static final DeferredBlock<BushBlock> TOMATO_CROP = registerCropBlock("tomato_crop", "Tomato", 8, TomatoCropBlock::new);
    public static final DeferredBlock<BushBlock> FLORO_PEDAL_CROP = registerCropBlock("floro_pedal_crop", "Floro Pedal", 8, FloroCropBlock::new);
    public static final DeferredBlock<BushBlock> REDCURRANT_BUSH = registerGrowingBushBlock("redcurrant_bush", "Redcurrant Bush", () -> new RedcurrantBushBlock(JBlockProperties.GROWING_BUSH));
    public static final DeferredBlock<BushBlock> BRADBERRY_BUSH = registerGrowingBushBlock("bradberry_bush", "Bradberry Bush", () -> new BradberryBushBlock(JBlockProperties.GROWING_BUSH));

    //EUCA
    public static final DeferredBlock<BushBlock> ZATPEDAL_CROP = registerCropBlock("zatpedal_crop", "Zatpedal", 8, ZatpedalCropBlock::new);
    public static final DeferredBlock<BushBlock> SPINEBERRY_CROP = registerCropBlock("spineberry_crop", "Spineberry", 8, SpineberryCropBlock::new);

    //DEPTHS
    public static final DeferredBlock<BushBlock> CRAKEBULB_CROP = registerCropBlock("crakebulb_crop", "Crakebulb", 4, CrakebulbCropBlock::new);
    public static final DeferredBlock<BushBlock> CRACKENCANE_CROP = registerCropBlock("crackencane_crop", "Crackencane", 8, CrackencanesCropBlock::new);

    //CORBA
    public static final DeferredBlock<BushBlock> CORVEGGIES_CROP = registerCropBlock("corveggies_crop", "Corveggies", 3, CorveggieCropBlock::new);
    public static final DeferredBlock<BushBlock> GLOWA_CROP = registerCropBlock("glowa_crop", "Glowa", 4, GlowaCropBlock::new);

    //CLOUDIA
    public static final DeferredBlock<Block> AIRROOT_MELON = registerModeledBlock("airroot_melon", "Airroot Melon", () -> new Block(JBlockProperties.WOOD));
    public static final DeferredBlock<Block> AIRROOT_CROP = registerModeledCropBlock("airroot", "Airroot", 4, AirrootCropBlock::new);


    private static void checkForPickaxeableBlocks(BlockBehaviour.Properties props, String name) {
        if(props == JBlockProperties.STONE || props == JBlockProperties.GLOW_BLOCK || props == JBlockProperties.BREAKABLE_DUNGEON_LAMP || props == JBlockProperties.CRYSTAL
                || props == JBlockProperties.FIRE_STONE || props == JBlockProperties.FURNACE || props == JBlockProperties.ICE
                || props == JBlockProperties.VOLCANIC_BLOCK || props == JBlockProperties.SPAWNER || props == JBlockProperties.ROCKITE_SPAWNER || props == JBlockProperties.GRINDSTONE
                || props == JBlockProperties.GLASS || props == JBlockProperties.DUNGEON_BLOCK || props == JBlockProperties.LUNIUM_ORE_PROPS || props == JBlockProperties.CHEST
                || props == JBlockProperties.DUNGEON_LAMP || props == JBlockProperties.CLOUD) {
            addPickaxeableBlocks(name);
        }
    }

    private static void checkForAxeableBlocks(BlockBehaviour.Properties props, String name) {
        if(props == JBlockProperties.WOOD || props == JBlockProperties.CLOUD || props == JBlockProperties.VINE || props == JBlockProperties.CACTUS || props == JBlockProperties.LADDER || props == JBlockProperties.CAMPFIRE || props == JBlockProperties.BUTTON || props == JBlockProperties.DOOR || props == JBlockProperties.MUSHROOM_BLOCK) {
            addAxeableBlocks(name);
        }
    }

    private static void checkForShovelableBlocks(BlockBehaviour.Properties props, String name) {
        if(props == JBlockProperties.SAND || props == JBlockProperties.FIRE_SAND || props == JBlockProperties.PATH || props == JBlockProperties.DIRT || props == JBlockProperties.GRASS || props == JBlockProperties.FARMLAND || props == JBlockProperties.FIRE_DIRT) {
            addShovelableBlocks(name);
        }
    }

    private static void checkForHoeableBlocks(BlockBehaviour.Properties props, String name) {
        if(props == JBlockProperties.LEAVES || props == JBlockProperties.LUMINESCENT_LEAVES) {
            addHoeableBlocks(name);
        }
    }

    private static void addAxeableBlocks(String name) {
        AXE_BLOCKS.add(name);
    }

    private static void addPickaxeableBlocks(String name) {
        PICKAXE_BLOCKS.add(name);
    }

    private static void addHoeableBlocks(String name) {
        HOE_BLOCKS.add(name);
    }

    private static void addShovelableBlocks(String name) {
        SHOVEL_BLOCKS.add(name);
    }

    public static DeferredBlock<Block> register(String name, String translatedName, BlockBehaviour.Properties props) {
        checkForHoeableBlocks(props, name);
        checkForShovelableBlocks(props, name);
        checkForAxeableBlocks(props, name);
        checkForPickaxeableBlocks(props, name);
        normalBlockName.add(name);
        return register(name, translatedName, () -> new JBlock(props), false);
    }

    public static DeferredBlock<Block> register(String name, String translatedName, Supplier<Block> block, BlockBehaviour.Properties props) {
        checkForHoeableBlocks(props, name);
        checkForShovelableBlocks(props, name);
        checkForAxeableBlocks(props, name);
        checkForPickaxeableBlocks(props, name);
        normalBlockName.add(name);
        return register(name, translatedName, block, false);
    }

    public static DeferredBlock<Block> register(String name, String translatedName, Supplier<Block> block) {
        normalBlockName.add(name);
        return register(name, translatedName, block, false);
    }

    public static DeferredBlock<Block> register(String name, String translatedName, Supplier<Block> block, boolean addName) {
        normalLangName.add(translatedName);
        if(addName)
            normalBlockName.add(name);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }
    public static DeferredBlock<Block> registerCampfire(String name, String translatedName, Supplier<Block> block) {
        addAxeableBlocks(name);
        campfireBlockName.add(name);
        campfireLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerDoublePlant(String name, String translatedName, Supplier<Block> block) {
        doublePlantBlockName.add(name);
        doublePlantLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerLilyPad(String name, String translatedName, Supplier<Block> block) {
        lilyPadBlockName.add(name);
        lilyPadLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerFurnaceBlock(String name, String translatedName) {
        addPickaxeableBlocks(name);
        furnaceBlockName.add(name);
        furnaceLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, () -> new JFurnaceBlock(JBlockProperties.FURNACE));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerChestBlock(String name, String translatedName, Supplier<Block> block) {
        addPickaxeableBlocks(name);
        chestBlockName.add(name);
        chestLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerLadder(String name, String translatedName, Supplier<Block> block) {
        addAxeableBlocks(name);
        ladderLangName.add(translatedName);
        ladderBlockName.add(name);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerAltTexBlock(String name, String translatedName, BlockBehaviour.Properties props) {
        checkForShovelableBlocks(props, name);
        checkForAxeableBlocks(props, name);
        checkForPickaxeableBlocks(props, name);
        checkForHoeableBlocks(props, name);
        randomLangName.add(translatedName);
        randomBlockName.add(name);
        DeferredBlock<Block> block1 = BLOCKS.register(name, () -> new Block(props));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerTintedLeavesBlock(String name, String translatedName, BlockBehaviour.Properties props) {
        checkForShovelableBlocks(props, name);
        checkForAxeableBlocks(props, name);
        checkForPickaxeableBlocks(props, name);
        checkForHoeableBlocks(props, name);
        tintedLeavesLangName.add(translatedName);
        tintedLeavesBlockName.add(name);
        DeferredBlock<Block> block1 = BLOCKS.register(name, () -> new Block(props));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerMushroomBlock(String name, String translatedName, Supplier<Block> block) {
        addAxeableBlocks(name);
        mushroomLangName.add(translatedName);
        mushroomBlockName.add(name);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerEndPortalFrameStyleBlock(String name, String translatedName, Supplier<Block> block) {
        addPickaxeableBlocks(name);
        basePortalFrameLangName.add(translatedName);
        basePortalFrameBlockName.add(name);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerSlimeStyleBlock(String name, String translatedName, Supplier<Block> block) {
        addShovelableBlocks(name);
        slimeLangName.add(translatedName);
        slimeBlockName.add(name);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerEndPortalStyleBlock(String name, String translatedName, Supplier<Block> block) {
        basePortalLangName.add(translatedName);
        basePortalBlockName.add(name);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerTerrainBlock(String name, String translatedName, Supplier<Block> block) {
        if(!name.contains("stone"))
            addShovelableBlocks(name);
        terrainLangName.add(translatedName);
        terrainBlockName.add(name);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerTerrainBlock(String name, String translatedName, BlockBehaviour.Properties props) {
        checkForAxeableBlocks(props, name);
        checkForPickaxeableBlocks(props, name);
        checkForHoeableBlocks(props, name);
        return registerTerrainBlock(name, translatedName, () -> new Block(props));
    }

    public static DeferredBlock<Block> registerRotatableBlock(String name, String translatedName, Supplier<Block> block, boolean wood) {
        if(wood) {
            addAxeableBlocks(name);
        } else {
            addPickaxeableBlocks(name);
        }
        rotatableBlockName.add(name);
        rotatableLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerVineBlock(String name, String translatedName, Supplier<JVineBlock> block) {
        addHoeableBlocks(name);
        vineBlockName.add(name);
        vineLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerModeledBlock(String name, String translatedName, Supplier<Block> block) {
        addPickaxeableBlocks(name);
        modelBlockName.add(name);
        modelLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerTrophyBlock(String name, String translatedName) {
        addPickaxeableBlocks(name);
        trophyBlockName.add(name);
        trophyLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, TrophyBlock::new);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerDripstoneBlock(String name, String translatedName, Supplier<Block> block) {
        addPickaxeableBlocks(name);
        dripstoneBlockName.add(name);
        dripstoneLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerTotemBlock(String name, String translatedName) {
        addPickaxeableBlocks(name);
        totemBlockName.add(name);
        totemLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, TotemBlock::new);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerGrassBlock(String name, String translatedName, Supplier<Block> block, BlockBehaviour.Properties props) {
        checkForShovelableBlocks(props, name);
        checkForAxeableBlocks(props, name);
        checkForPickaxeableBlocks(props, name);
        checkForHoeableBlocks(props, name);
        grassBlockName.add(name);
        grassLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerGrassBlock(String name, String translatedName, Supplier<Block> block) {
        return registerGrassBlock(name, translatedName, block, JBlockProperties.GRASS);
    }

    public static DeferredBlock<Block> registerOverlayGrassBlock(String name, String translatedName, Supplier<Block> block) {
        addShovelableBlocks(name);
        overlayGrassBlockName.add(name);
        overlayGrassLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<JBasePortalBlock> registerPortalBlock(String name, String translatedName, Supplier<JBasePortalBlock> block) {
        portalBlockName.add(name);
        portalLangName.add(translatedName);
        DeferredBlock<JBasePortalBlock> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerPathBlock(String name, String translatedName, Supplier<Block> block) {
        addShovelableBlocks(name);
        pathBlockName.add(name);
        pathLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<BushBlock> registerGrowingBushBlock(String name, String translatedName, Supplier<BushBlock> block) {
        bushBlockName.add(name);
        bushLangName.add(translatedName);
        DeferredBlock<BushBlock> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerFarmlandBlock(String name, String translatedName, Supplier<Block> block) {
        addShovelableBlocks(name);
        farmlandBlockName.add(name);
        farmlandLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<BushBlock> registerCropBlock(String name, String translatedName, int maxStages, Supplier<BushBlock> block) {
        cropBlockName.add(name);
        cropLangName.add(translatedName);
        DeferredBlock<BushBlock> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        if(JITL.DEV_MODE)
            new JBlockCropGenerator().generate(name, maxStages);
        return block1;
    }

    public static DeferredBlock<Block> registerModeledCropBlock(String name, String translatedName, int maxStages, Supplier<Block> block) {
        cropBlockName.add(name);
        cropLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        if(JITL.DEV_MODE)
            new JBlockModeledCropGenerator().generate(name, maxStages);
        return block1;
    }

    public static DeferredBlock<RotatedPillarBlock> addLogBlock(String name, String translatedName) {
        addAxeableBlocks(name);
        logBlockName.add(name);
        logLangName.add(translatedName);
        DeferredBlock<RotatedPillarBlock> block1 = BLOCKS.register(name, LogBlock::new);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return 300;
            }
        });
        return block1;
    }

    public static DeferredBlock<RotatedPillarBlock> registerPillar(String name, String translatedName, boolean wood, BlockBehaviour.Properties props) {
        if(wood) {
            addAxeableBlocks(name);
        } else {
          addPickaxeableBlocks(name);
        }
        logBlockName.add(name);
        logLangName.add(translatedName);
        DeferredBlock<RotatedPillarBlock> block1 = BLOCKS.register(name, () -> new RotatedPillarBlock(props));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return wood ? 300 : 0;
            }
        });
        return block1;
    }

    public static DeferredBlock<DoorBlock> registerDoor(String name, String translatedName, boolean wood, BlockBehaviour.Properties p) {
        if(wood) {
            addAxeableBlocks(name);
        } else {
            addPickaxeableBlocks(name);
        }
        doorBlockName.add(name);
        doorLangName.add(translatedName);
        DeferredBlock<DoorBlock> block1 = BLOCKS.register(name, () -> new DoorBlock(BlockSetType.OAK, p));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return wood ? 300 : 0;
            }
        });
        return block1;
    }

    public static DeferredBlock<TrapDoorBlock> registerTrapDoor(String name, String translatedName, boolean wood, BlockBehaviour.Properties p) {
        if(wood) {
            addAxeableBlocks(name);
        } else {
            addPickaxeableBlocks(name);
        }
        trapDoorBlockName.add(name);
        trapDoorLangName.add(translatedName);
        DeferredBlock<TrapDoorBlock> block1 = BLOCKS.register(name, () -> new TrapDoorBlock(BlockSetType.OAK, p));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return wood ? 300 : 0;
            }
        });
        return block1;
    }

    public static DeferredBlock<StairBlock> registerStairs(String name, String translatedName, DeferredBlock<Block> plank, boolean wood, BlockBehaviour.Properties p) {
        if(wood) {
            addAxeableBlocks(name);
        } else {
            addPickaxeableBlocks(name);
        }
        stairsBlockName.add(name);
        stairsLangName.add(translatedName);
        DeferredBlock<StairBlock> block1 = BLOCKS.register(name, () -> new StairBlock(plank.get().defaultBlockState(), p));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return wood ? 300 : 0;
            }
        });
        return block1;
    }

    public static DeferredBlock<SlabBlock> registerSlab(String name, String translatedName, boolean wood, BlockBehaviour.Properties p) {
        if(wood) {
            addAxeableBlocks(name);
        } else {
            addPickaxeableBlocks(name);
        }
        slabBlockName.add(name);
        slabLangName.add(translatedName);
        DeferredBlock<SlabBlock> block1 = BLOCKS.register(name, () -> new SlabBlock(p));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return wood ? 300 : 0;
            }
        });
        return block1;
    }

    public static DeferredBlock<ButtonBlock> registerButton(String name, String translatedName, boolean sensitive, boolean wood, BlockBehaviour.Properties p) {
        if(wood) {
            addAxeableBlocks(name);
        } else {
            addPickaxeableBlocks(name);
        }
        buttonBlockName.add(name);
        buttonLangName.add(translatedName);
        DeferredBlock<ButtonBlock> block1 = BLOCKS.register(name, () -> new ButtonBlock(BlockSetType.OAK, sensitive ? 20 : 30, p) {
            @Override
            protected @NotNull SoundEvent getSound(boolean pIsOn) {
                return SoundEvents.WOODEN_BUTTON_CLICK_ON;
            }
        });
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return wood ? 300 : 0;
            }
        });
        return block1;
    }

    public static DeferredBlock<PressurePlateBlock> registerPressurePlate(String name, String translatedName, boolean wood, BlockBehaviour.Properties p) {
        if(wood) {
            addAxeableBlocks(name);
        } else {
            addPickaxeableBlocks(name);
        }
        pressurePlateBlockName.add(name);
        pressurePlateLangName.add(translatedName);
        DeferredBlock<PressurePlateBlock> block1 = BLOCKS.register(name, () -> new PressurePlateBlock(BlockSetType.OAK, p));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return wood ? 300 : 0;
            }
        });
        return block1;
    }

    public static DeferredBlock<FenceGateBlock> registerFenceGate(String name, String translatedName, boolean wood, BlockBehaviour.Properties p) {
        if(wood) {
            addAxeableBlocks(name);
        } else {
            addPickaxeableBlocks(name);
        }
        gateBlockName.add(name);
        gateLangName.add(translatedName);
        DeferredBlock<FenceGateBlock> block1 = BLOCKS.register(name, () -> new FenceGateBlock(WoodType.OAK, p));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return wood ? 300 : 0;
            }
        });
        return block1;
    }

    public static DeferredBlock<JFenceBlock> registerFence(String name, String translatedName, boolean wood, BlockBehaviour.Properties p) {
        if(wood) {
            addAxeableBlocks(name);
        } else {
            addPickaxeableBlocks(name);
        }
        fenceBlockName.add(name);
        fenceLangName.add(translatedName);
        DeferredBlock<JFenceBlock> block1 = BLOCKS.register(name, () -> new JFenceBlock(p));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return wood ? 300 : 0;
            }
        });
        return block1;
    }

    public static DeferredBlock<JWallBlock> registerWallBlock(String name, String translatedName, BlockBehaviour.Properties props) {
        checkForShovelableBlocks(props, name);
        checkForAxeableBlocks(props, name);
        checkForPickaxeableBlocks(props, name);
        checkForHoeableBlocks(props, name);
        wallBlockName.add(name);
        wallLangName.add(translatedName);
        DeferredBlock<JWallBlock> block1 = BLOCKS.register(name, () -> new JWallBlock(props));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<IronBarsBlock> registerPaneBlock(String name, String translatedName, BlockBehaviour.Properties props) {
        checkForShovelableBlocks(props, name);
        checkForAxeableBlocks(props, name);
        checkForPickaxeableBlocks(props, name);
        checkForHoeableBlocks(props, name);
        paneBlockName.add(name);
        paneLangName.add(translatedName);
        DeferredBlock<IronBarsBlock> block1 = BLOCKS.register(name, () -> new IronBarsBlock(props));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerCrossBlock(String name, String translatedName, Supplier<Block> block) {
        crossBlockName.add(name);
        crossLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<GrowingPlantHeadBlock> registerGrowingPlantHeadBlock(String name, String translatedName, Supplier<GrowingPlantHeadBlock> block) {
        crossBlockName.add(name);
        crossLangName.add(translatedName);
        DeferredBlock<GrowingPlantHeadBlock> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerTopBottomBlock(String name, String translatedName, Supplier<Block> block) {
        addHoeableBlocks(name);//only for terranian leaves
        topBottomBlockName.add(name);
        topBottomLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerTintedCrossBlock(String name, String translatedName, Supplier<Block> block) {
        tintedCrossBlockName.add(name);
        tintedCrossLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static DeferredBlock<Block> registerAttachedCrossBlock(String name, String translatedName, Supplier<Block> block) {
        attachedCrossBlockName.add(name);
        attachedCrossLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }

    public static <T extends Block>DeferredBlock<T> registerFuelBlock(String name, String translatedName, Supplier<T> block, int burnTime) {
        addPickaxeableBlocks(name);
        normalLangName.add(translatedName);
        normalBlockName.add(name);
        DeferredBlock<T> block1 = BLOCKS.register(name, block);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return burnTime;
            }
        });
        return block1;
    }

    public static DeferredBlock<Block> registerFuelBlock(String name, String translatedName, BlockBehaviour.Properties props, int burnTime) {
        checkForShovelableBlocks(props, name);
        checkForAxeableBlocks(props, name);
        checkForPickaxeableBlocks(props, name);
        checkForHoeableBlocks(props, name);
        normalLangName.add(translatedName);
        normalBlockName.add(name);
        DeferredBlock<Block> block1 = BLOCKS.register(name, () -> new Block(props));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), new Item.Properties()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return burnTime;
            }
        });
        return block1;
    }
}