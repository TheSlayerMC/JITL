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
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Supplier;

public class JBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(JITL.MOD_ID);

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
    public static final ArrayList<String> redstoneLampBlockName = new ArrayList<>();
    public static final ArrayList<String> redstoneLampLangName = new ArrayList<>();
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
    public static final ArrayList<String> torchBlockName = new ArrayList<>();
    public static final ArrayList<String> torchLangName = new ArrayList<>();
    public static final ArrayList<String> wallTorchBlockName = new ArrayList<>();
    public static final ArrayList<String> wallTorchLangName = new ArrayList<>();
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
    public static final DeferredBlock<Block> IRIDIUM_BLOCK = registerFuelBlock("iridium_block", "Iridium Block", Block::new, JBlockProperties.STONE, 16000);
    public static final DeferredBlock<Block> DEEPSLATE_IRIDIUM_ORE = register("deepslate_iridium_ore", "Deepslate Iridium Ore", JBlockProperties.STONE);

    public static final DeferredBlock<Block> SAPPHIRE_ORE = register("sapphire_ore", "Sapphire Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> SAPPHIRE_BLOCK = register("sapphire_block", "Sapphire Block", JBlockProperties.STONE);
    public static final DeferredBlock<Block> DEEPSLATE_SAPPHIRE_ORE = register("deepslate_sapphire_ore", "Deepslate Sapphire Ore", JBlockProperties.STONE);

    public static final DeferredBlock<Block> SHADIUM_ORE = register("shadium_ore", "Shadium Ore", JBlockProperties.STONE);
    public static final DeferredBlock<Block> SHADIUM_BLOCK = register("shadium_block", "Shadium Block", JBlockProperties.STONE);
    public static final DeferredBlock<Block> DEEPSLATE_SHADIUM_ORE = register("deepslate_shadium_ore", "Deepslate Shadium Ore", JBlockProperties.STONE);

    public static final DeferredBlock<Block> LUNIUM_ORE = register("lunium_ore", "Lunium Ore", JBlockProperties.LUNIUM_ORE_PROPS);
    public static final DeferredBlock<Block> LUNIUM_BLOCK = register("lunium_block", "Lunium Block", JBlockProperties.LUNIUM_ORE_PROPS);
    public static final DeferredBlock<Block> DEEPSLATE_LUNIUM_ORE = register("deepslate_lunium_ore", "Deepslate Lunium Ore", JBlockProperties.LUNIUM_ORE_PROPS);

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

    public static final DeferredBlock<Block> IRIDIUM_CAMPFIRE = registerCampfire("iridium_campfire", "Iridium Campfire", IridiumCampfireBlock::new, JBlockProperties.CAMPFIRE);
    public static final DeferredBlock<Block> IRIDIUM_TORCH = registerTorch("iridium_torch_block", "Iridium Torch", (p) -> new TorchBlock(JParticleManager.GREEN_FLAME.get(), p));
    public static final DeferredBlock<Block> IRIDIUM_WALL_TORCH = registerWallTorch("iridium_wall_torch", "Iridium Torch", (p) -> new WallTorchBlock(JParticleManager.GREEN_FLAME.get(), p));
    public static final DeferredBlock<Block> IRIDIUM_LAMP = registerRedstoneLamp("iridium_lamp", "Iridium Lamp", RedstoneLampBlock::new, JBlockProperties.REDSTONE_LAMP);

    public static final DeferredBlock<Block> WARPED_QUARTZ_ORE = register("warped_quartz_ore", "Warped Quartz Ore", JBlockProperties.STONE);//gravel
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

    public static final DeferredBlock<Block> TALL_GREEN_GLOWSHROOM = registerDoublePlant("tall_green_glowshroom", "Tall Green Glowshroom", TallGlowshroomBlock::new, JBlockProperties.CAVE_GLOW_PLANT);
    public static final DeferredBlock<Block> TALL_BLUE_GLOWSHROOM = registerDoublePlant("tall_blue_glowshroom", "Tall Blue Glowshroom", TallGlowshroomBlock::new, JBlockProperties.CAVE_GLOW_PLANT);
    public static final DeferredBlock<Block> TALL_RED_GLOWSHROOM = registerDoublePlant("tall_red_glowshroom", "Tall Red Glowshroom", TallGlowshroomBlock::new, JBlockProperties.CAVE_GLOW_PLANT);
    public static final DeferredBlock<Block> GREEN_GLOWSHROOM = registerCrossBlock("green_glowshroom", "Green Glowshroom", CavePlantBlock::new, JBlockProperties.CAVE_GLOW_PLANT);
    public static final DeferredBlock<Block> BLUE_GLOWSHROOM = registerCrossBlock("blue_glowshroom", "Blue Glowshroom", CavePlantBlock::new, JBlockProperties.CAVE_GLOW_PLANT);
    public static final DeferredBlock<Block> RED_GLOWSHROOM = registerCrossBlock("red_glowshroom", "Red Glowshroom", CavePlantBlock::new, JBlockProperties.CAVE_GLOW_PLANT);

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
    public static final DeferredBlock<JWallBlock> BOIL_COBBLESTONE_WALL = registerWallBlock("boil_cobblestone_wall", "Boiling Cobblestone Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> BOIL_COBBLESTONE_STAIRS = registerStairs("boil_cobblestone_stairs", "Boiling Cobblestone Stairs", BOIL_COBBLESTONE, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> BOIL_COBBLESTONE_SLAB = registerSlab("boil_cobblestone_slab", "Boiling Cobblestone Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> BOIL_COBBLESTONE_BUTTON = registerButton("boil_cobblestone_button", "Boiling Cobblestone Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> BOIL_COBBLESTONE_PRESSURE_PLATE = registerPressurePlate("boil_cobblestone_pressure_plate", "Boiling Cobblestone Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> BOIL_COBBLESTONE_FENCE_GATE = registerFenceGate("boil_cobblestone_fence_gate", "Boiling Cobblestone Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> BOIL_COBBLESTONE_FENCE = registerFence("boil_cobblestone_fence", "Boiling Cobblestone Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<RotatedPillarBlock> BOIL_PILLAR = registerPillar("boil_pillar", "Boil Pillar", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> BOIL_BRICKS = register("boil_bricks", "Boil Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> BOIL_BRICK_WALL = registerWallBlock("boil_brick_wall", "Boil Brick Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> BOIL_BRICK_STAIRS = registerStairs("boil_brick_stairs", "Boil Brick Stairs", BOIL_BRICKS, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> BOIL_BRICK_SLAB = registerSlab("boil_brick_slab", "Boil Brick Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> BOIL_BRICK_BUTTON = registerButton("boil_brick_button", "Boil Brick Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> BOIL_BRICK_PRESSURE_PLATE = registerPressurePlate("boil_brick_pressure_plate", "Boil Brick Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> BOIL_BRICK_FENCE_GATE = registerFenceGate("boil_brick_fence_gate", "Boil Brick Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> BOIL_BRICK_FENCE = registerFence("boil_brick_fence", "Boil Brick Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> BOIL_SQUARE_BRICKS = register("boil_square_bricks", "Boil Square Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> BOIL_SQUARE_BRICK_WALL = registerWallBlock("boil_square_brick_wall", "Boil Square Brick Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> BOIL_SQUARE_BRICK_STAIRS = registerStairs("boil_square_brick_stairs", "Boil Square Brick Stairs", BOIL_SQUARE_BRICKS, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> BOIL_SQUARE_BRICK_SLAB = registerSlab("boil_square_brick_slab", "Boil Square Brick Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> BOIL_SQUARE_BRICK_BUTTON = registerButton("boil_square_brick_button", "Boil Square Brick Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> BOIL_SQUARE_BRICK_PRESSURE_PLATE = registerPressurePlate("boil_square_brick_pressure_plate", "Boil Square Brick Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> BOIL_SQUARE_BRICK_FENCE_GATE = registerFenceGate("boil_square_brick_fence_gate", "Boil Square Brick Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> BOIL_SQUARE_BRICK_FENCE = registerFence("boil_square_brick_fence", "Boil Square Brick Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> BOIL_SHINGLE = register("boil_shingle", "Boil Shingle", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> BOIL_SHINGLE_WALL = registerWallBlock("boil_shingle_wall", "Boil Shingle Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> BOIL_SHINGLE_STAIRS = registerStairs("boil_shingle_stairs", "Boil Shingle Stairs", BOIL_SHINGLE, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> BOIL_SHINGLE_SLAB = registerSlab("boil_shingle_slab", "Boil Shingle Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> BOIL_SHINGLE_BUTTON = registerButton("boil_shingle_button", "Boil Shingle Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> BOIL_SHINGLE_PRESSURE_PLATE = registerPressurePlate("boil_shingle_pressure_plate", "Boil Shingle Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> BOIL_SHINGLE_FENCE_GATE = registerFenceGate("boil_shingle_fence_gate", "Boil Shingle Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> BOIL_SHINGLE_FENCE = registerFence("boil_shingle_fence", "Boil Shingle Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> BLAZIER_BRICKS = register("blazier_bricks", "Blazier Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> BLAZIER_BRICK_WALL = registerWallBlock("blazier_brick_wall", "Blazier Brick Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> BLAZIER_BRICK_STAIRS = registerStairs("blazier_brick_stairs", "Blazier Brick Stairs", BLAZIER_BRICKS, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> BLAZIER_BRICK_SLAB = registerSlab("blazier_brick_slab", "Blazier Brick Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> BLAZIER_BRICK_BUTTON = registerButton("blazier_brick_button", "Blazier Brick Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> BLAZIER_BRICK_PRESSURE_PLATE = registerPressurePlate("blazier_brick_pressure_plate", "Blazier Brick Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> BLAZIER_BRICK_FENCE_GATE = registerFenceGate("blazier_brick_fence_gate", "Blazier Brick Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> BLAZIER_BRICK_FENCE = registerFence("blazier_brick_fence", "Blazier Brick Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> BLAZIER_METAL = register("blazier_metal", "Blazier Metal", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> BLAZIER_METAL_WALL = registerWallBlock("blazier_metal_wall", "Blazier Metal Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> BLAZIER_METAL_STAIRS = registerStairs("blazier_metal_stairs", "Blazier Metal Stairs", BLAZIER_METAL, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> BLAZIER_METAL_SLAB = registerSlab("blazier_metal_slab", "Blazier Metal Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> BLAZIER_METAL_BUTTON = registerButton("blazier_metal_button", "Blazier Metal Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> BLAZIER_METAL_PRESSURE_PLATE = registerPressurePlate("blazier_metal_pressure_plate", "Blazier Metal Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> BLAZIER_METAL_FENCE_GATE = registerFenceGate("blazier_metal_fence_gate", "Blazier Metal Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> BLAZIER_METAL_FENCE = registerFence("blazier_metal_fence", "Blazier Metal Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> HELLWING_SPAWNER = register("hellwing_spawner", "Hellwing Spawner", HellwingSpawnerBlock::new, JBlockProperties.SPAWNER);
    public static final DeferredBlock<Block> OBSERVER_SPAWNER = register("observer_spawner", "Observer Spawner", ObserverSpawnerBlock::new, JBlockProperties.SPAWNER);
    public static final DeferredBlock<Block> SCREAMER_SPAWNER = register("screamer_spawner", "Screamer Spawner", ScreamerSpawnerBlock::new, JBlockProperties.SPAWNER);
    public static final DeferredBlock<Block> SMALL_BRISON_BRICKS = register("small_brison_bricks", "Small Brison Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<Block> RED_BRISON_BRICKS = register("red_brison_brick", "Red Brison Brick", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BRISON_STONE = register("brison_stone", "Brison Stone", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BOILING_LAMP = register("boiling_lamp", "Boiling Lamp", JBlockProperties.GLOW_BLOCK);
    public static final DeferredBlock<JFenceBlock> SIZZLING_POST = registerFence("sizzling_post", "Sizzling Post", false, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> SCORCHED_RUBBLE_BRICKS = register("scorched_rubble_bricks", "Scorched Rubble Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> SCORCHED_RUBBLE_BRICK_WALL = registerWallBlock("scorched_rubble_brick_wall", "Scorched Rubble Brick Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> SCORCHED_RUBBLE_BRICK_STAIRS = registerStairs("scorched_rubble_brick_stairs", "Scorched Rubble Brick Stairs", SCORCHED_RUBBLE_BRICKS, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> SCORCHED_RUBBLE_BRICK_SLAB = registerSlab("scorched_rubble_brick_slab", "Scorched Rubble Brick Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> SCORCHED_RUBBLE_BRICK_BUTTON = registerButton("scorched_rubble_brick_button", "Scorched Rubble Brick Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> SCORCHED_RUBBLE_BRICK_PRESSURE_PLATE = registerPressurePlate("scorched_rubble_brick_pressure_plate", "Scorched Rubble Brick Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> SCORCHED_RUBBLE_BRICK_FENCE_GATE = registerFenceGate("scorched_rubble_brick_fence_gate", "Scorched Rubble Brick Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> SCORCHED_RUBBLE_BRICK_FENCE = registerFence("scorched_rubble_brick_fence", "Scorched Rubble Brick Fence", false, JBlockProperties.STONE);

    public static final DeferredBlock<Block> GOLDITE_FARMLAND = registerFarmlandBlock("goldite_farmland", "Goldite Farmland", GolditeFarmland::new, JBlockProperties.FARMLAND);
    public static final DeferredBlock<Block> DEPTHS_FARMLAND = registerFarmlandBlock("depths_farmland", "Depths Farmland", DepthsFarmland::new, JBlockProperties.FARMLAND);
    public static final DeferredBlock<Block> PERMAFROST_FARMLAND = registerFarmlandBlock("permafrost_farmland", "Permafrost Farmland", PermafrostFarmland::new, JBlockProperties.FARMLAND);
    public static final DeferredBlock<Block> CORBA_FARMLAND = registerFarmlandBlock("corba_farmland", "Corba Farmland", CorbaFarmland::new, JBlockProperties.FARMLAND);
    public static final DeferredBlock<Block> CLOUDIA_FARMLAND = registerFarmlandBlock("cloudia_farmland", "Cloudia Farmland", CloudiaFarmland::new, JBlockProperties.FARMLAND);

    public static final DeferredBlock<Block> EUCA_PORTAL_FRAME = register("euca_portal_frame", "Euca Portal Frame", JBlockProperties.STONE);
    public static final DeferredBlock<JBasePortalBlock> EUCA_PORTAL = registerPortalBlock("euca_portal", "Euca Portal", Dimensions.EUCA, EUCA_PORTAL_FRAME);
    public static final DeferredBlock<Block> GOLDITE_DIRT = registerTerrainBlock("goldite_dirt", "Goldite Soil", JDirt::new, JBlockProperties.DIRT);
    public static final DeferredBlock<Block> GOLDITE_STONE = registerTerrainBlock("goldite_stone", "Goldite Stone", JBlockProperties.STONE);
    public static final DeferredBlock<Block> GOLDITE_COBBLESTONE = register("goldite_cobblestone", "Goldite Cobblestone", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> GOLDITE_COBBLESTONE_WALL = registerWallBlock("goldite_cobblestone_wall", "Goldite Cobblestone Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> GOLDITE_COBBLESTONE_STAIRS = registerStairs("goldite_cobblestone_stairs", "Goldite Cobblestone Stairs", GOLDITE_COBBLESTONE, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> GOLDITE_COBBLESTONE_SLAB = registerSlab("goldite_cobblestone_slab", "Goldite Cobblestone Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> GOLDITE_COBBLESTONE_BUTTON = registerButton("goldite_cobblestone_button", "Goldite Cobblestone Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> GOLDITE_COBBLESTONE_PRESSURE_PLATE = registerPressurePlate("goldite_cobblestone_pressure_plate", "Goldite Cobblestone Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> GOLDITE_COBBLESTONE_FENCE_GATE = registerFenceGate("goldite_cobblestone_fence_gate", "Goldite Cobblestone Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> GOLDITE_COBBLESTONE_FENCE = registerFence("goldite_cobblestone_fence", "Goldite Cobblestone Fence", false, JBlockProperties.STONE);
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
    public static final DeferredBlock<Block> EUCA_TALL_GRASS = registerCrossBlock("euca_tall_grass", "Euca Tall Grass", TallGrassBlock::new, JBlockProperties.REPLACABLE_PLANT);
    public static final DeferredBlock<Block> EUCA_TALL_FLOWERS = registerCrossBlock("euca_tall_flowers", "Euca Tall Flowers", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> EUCA_SILVER_FLOWER = registerCrossBlock("euca_silver_gold_flower", "Euca Silver Flower", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> EUCA_BLUE_FLOWER = registerCrossBlock("euca_blue_flower", "Euca Blue Flower", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> GOLDITE_FLOWER = registerCrossBlock("goldite_flower", "Goldite Flower", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> GOLDITE_STALKS = registerCrossBlock("goldite_stalks", "Goldite Stalks", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> GOLDITE_BULB = registerCrossBlock("goldite_bulb", "Goldite Bulb", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> GOLDITE_TALL_GRASS = registerDoublePlant("goldite_tall_grass", "Goldite Tall Grass", JDoublePlantBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> GOLD_BOT_SPAWNER = register("gold_bot_spawner", "Gold Bot Spawner", GoldBotSpawnerBlock::new, JBlockProperties.SPAWNER);
    public static final DeferredBlock<Block> GOLDITE_FURNACE = registerFurnaceBlock("goldite_furnace", "Goldite Furnace");
    public static final DeferredBlock<Block> EUCA_PUMPKIN = registerRotatableBlock("euca_pumpkin", "Euca Pumpkin", FaceableBlock::new, JBlockProperties.WOOD, true);
    public static final DeferredBlock<Block> GLIMMER_ROOT = registerVineBlock("glimmer_root", "Glimmer Root", JVineBlock::new ,JBlockProperties.VINE);
    public static final DeferredBlock<Block> EUCA_GOLD_GRASS = registerGrassBlock("euca_gold_grass", "Euca Gold Grass", JGrassBlock::new);
    public static final DeferredBlock<Block> GOLDITE_GRASS = registerGrassBlock("goldite_grass", "Goldite Grass", JGrassBlock::new);
    public static final DeferredBlock<RotatedPillarBlock> EUCA_GOLD_LOG = addLogBlock("euca_gold_log", "Euca Gold Log");
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_EUCA_GOLD_LOG = registerPillar("stripped_euca_gold_log", "Stripped Euca Gold Log", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> EUCA_GOLD_LEAVES = registerTerrainBlock("euca_gold_leaves", "Euca Gold Leaves", (p) -> new JLeavesBlock(p, 15138560), JBlockProperties.LEAVES);
    public static final DeferredBlock<Block> EUCA_GOLD_SAPLING = registerCrossBlock("euca_gold_sapling", "Euca Gold Sapling", (p) -> new JSaplingBlock(JTreeGrower.EUCA_GOLD_TREE_GROWER, p), JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> EUCA_GOLD_PLANKS = registerFuelBlock("euca_gold_planks", "Euca Gold Planks", JBlockProperties.WOOD, 300);
    public static final DeferredBlock<DoorBlock> EUCA_GOLD_DOOR = registerDoor("euca_gold_door", "Euca Gold Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<TrapDoorBlock> EUCA_GOLD_TRAP_DOOR = registerTrapDoor("euca_gold_trap_door", "Euca Gold Trap Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<StairBlock> EUCA_GOLD_STAIRS = registerStairs("euca_gold_stairs", "Euca Gold Plank Stairs", EUCA_GOLD_PLANKS, true, JBlockProperties.WOOD);
    public static final DeferredBlock<SlabBlock> EUCA_GOLD_SLAB = registerSlab("euca_gold_slab", "Euca Gold Plank Slab", true, JBlockProperties.WOOD);
    public static final DeferredBlock<ButtonBlock> EUCA_GOLD_BUTTON = registerButton("euca_gold_button", "Euca Gold Button", false, true, JBlockProperties.WOOD);
    public static final DeferredBlock<PressurePlateBlock> EUCA_GOLD_PRESSURE_PLATE = registerPressurePlate("euca_gold_pressure_plate", "Euca Gold Pressure Plate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<FenceGateBlock> EUCA_GOLD_FENCE_GATE = registerFenceGate("euca_gold_fence_gate", "Euca Gold Fence Gate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<JFenceBlock> EUCA_GOLD_FENCE = registerFence("euca_gold_fence", "Euca Gold Fence", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> GOLDITE_PATH = registerPathBlock("goldite_path", "Goldite Path");
    public static final DeferredBlock<RotatedPillarBlock> EUCA_BROWN_LOG = addLogBlock("euca_brown_log", "Euca Brown Log");
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_EUCA_BROWN_LOG = registerPillar("stripped_euca_brown_log", "Stripped Euca Brown Log", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> EUCA_GREEN_LEAVES = registerAltTexBlock("euca_green_leaves", "Euca Green Leaves", (p) -> new JLeavesBlock(p, 65322), JBlockProperties.LEAVES);
    public static final DeferredBlock<Block> EUCA_GREEN_SAPLING = registerCrossBlock("euca_green_sapling", "Euca Green Sapling", (p) -> new JSaplingBlock(JTreeGrower.EUCA_GREEN_TREE_GROWER, p), JBlockProperties.FLOWER);
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
    public static final DeferredBlock<JBasePortalBlock> FROZEN_PORTAL = registerPortalBlock("frozen_portal", "Frozen Portal", Dimensions.FROZEN_LANDS, FROZEN_PORTAL_FRAME);
    public static final DeferredBlock<Block> FUMICE = register("fumice", "Fumice", JBlockProperties.DIRT);
    public static final DeferredBlock<Block> GRASSY_PERMAFROST = registerGrassBlock("grassy_permafrost", "Grassy Permafrost", JGrassBlock::new);

    public static final DeferredBlock<Block> PERMAFROST = registerTerrainBlock("permafrost", "Permafrost", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> PERMAFROST_WALL = registerWallBlock("permafrost_wall", "Permafrost Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> PERMAFROST_STAIRS = registerStairs("permafrost_stairs", "Permafrost Stairs", PERMAFROST, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> PERMAFROST_SLAB = registerSlab("permafrost_slab", "Permafrost Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> PERMAFROST_BUTTON = registerButton("permafrost_button", "Permafrost Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> PERMAFROST_PRESSURE_PLATE = registerPressurePlate("permafrost_pressure_plate", "Permafrost Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> PERMAFROST_FENCE_GATE = registerFenceGate("permafrost_fence_gate", "Permafrost Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> PERMAFROST_FENCE = registerFence("permafrost_fence", "Permafrost Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> CRUMBLED_PERMAFROST = registerTerrainBlock("crumbled_permafrost", "Crumbled Permafrost", JDirt::new, JBlockProperties.DIRT);
    public static final DeferredBlock<RotatedPillarBlock> FROZEN_LOG = addLogBlock("frozen_log", "Frozen Log");
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_FROZEN_LOG = registerPillar("stripped_frozen_log", "Stripped Frozen Log", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> FROZEN_LEAVES = registerTerrainBlock("frozen_leaves", "Frozen Leaves", (p) -> new JLeavesBlock(p, 54527), JBlockProperties.LEAVES);
    public static final DeferredBlock<Block> FROSTWOOD_SAPLING = registerCrossBlock("frostwood_sapling", "Frostwood Sapling", (p) -> new JSaplingBlock(JTreeGrower.FROSTWOOD_TREE_GROWER, p), JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> FROZEN_PLANKS = registerFuelBlock("frozen_planks", "Frostwood Planks", JBlockProperties.WOOD, 300);
    public static final DeferredBlock<DoorBlock> FROZEN_DOOR = registerDoor("frozen_door", "Frostwood Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<TrapDoorBlock> FROZEN_TRAP_DOOR = registerTrapDoor("frozen_trap_door", "Frostwood Trap Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<StairBlock> FROZEN_STAIRS = registerStairs("frozen_stairs", "Frostwood Plank Stairs", EUCA_BROWN_PLANKS, true, JBlockProperties.WOOD);
    public static final DeferredBlock<SlabBlock> FROZEN_SLAB = registerSlab("frozen_slab", "Frostwood Plank Slab", true, JBlockProperties.WOOD);
    public static final DeferredBlock<ButtonBlock> FROZEN_BUTTON = registerButton("frozen_button", "Frostwood Button", true, true, JBlockProperties.BUTTON);
    public static final DeferredBlock<PressurePlateBlock> FROZEN_PRESSURE_PLATE = registerPressurePlate("frozen_pressure_plate", "Frostwood Pressure Plate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<FenceGateBlock> FROZEN_FENCE_GATE = registerFenceGate("frozen_fence_gate", "Frostwood Fence Gate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<JFenceBlock> FROZEN_FENCE = registerFence("frozen_fence", "Frostwood Fence", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> FROST_CRYSTAL_LARGE = registerAttachedCrossBlock("frost_crystal_large", "Frost Crystal", AttachedBlock::new, JBlockProperties.CRYSTAL);
    public static final DeferredBlock<Block> FROST_CRYSTAL_MEDIUM = registerAttachedCrossBlock("frost_crystal_medium", "Frost Crystal", AttachedBlock::new, JBlockProperties.CRYSTAL);
    public static final DeferredBlock<Block> FROST_CRYSTAL_SMALL = registerAttachedCrossBlock("frost_crystal_small", "Frost Crystal", AttachedBlock::new, JBlockProperties.CRYSTAL);
    public static final DeferredBlock<Block> FROST_CRYSTAL_TINY = registerAttachedCrossBlock("frost_crystal_tiny", "Frost Crystal", AttachedBlock::new, JBlockProperties.CRYSTAL);
    public static final DeferredBlock<Block> FROSTBERRY_THORN = registerCrossBlock("frostberry_thorn", "Frostberry Thorn", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> ICE_BUSH = registerCrossBlock("ice_bush", "Ice Bush", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> ICE_BUD = registerCrossBlock("ice_bud", "Ice Bud", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> CICLEBLOOM = registerModeledBlock("ciclebloom", "Cicle Bloom", SingleDoublePlant::new, JBlockProperties.LIGHT_PLANT);
    public static final DeferredBlock<Block> ICY_BRUSH = registerVineBlock("icy_brush", "Icy Brush", JVineBlock::new, JBlockProperties.VINE);
    public static final DeferredBlock<Block> CRYSTAL_FRUIT = registerModeledBlock("crystal_fruit", "Crystal Fruit", CrystalFruit::new, JBlockProperties.CRYSTAL_FRUIT);
    public static final DeferredBlock<GrowingPlantHeadBlock> ICY_IVY = registerGrowingPlantHeadBlock("icy_ivy", "Icy Ivy", IcyIvyTopBlock::new, JBlockProperties.VINE);
    public static final DeferredBlock<Block> ICY_IVY_PLANT = registerCrossBlock("icy_ivy_plant", "Icy Ivy", IcyIvyBlock::new, JBlockProperties.VINE);
    public static final DeferredBlock<Block> ICE_SHROOM_SHELF = registerModeledBlock("ice_shroom_shelf", "Ice Shroom Shelf", JBlockFungalShelf::new, JBlockProperties.MUSHROOM_SHELF);
    public static final DeferredBlock<Block> BITTERWOOD_SAPLING = registerCrossBlock("bitterwood_sapling", "Bitterwood Sapling", (p) -> new JSaplingBlock(JTreeGrower.BITTERWOOD_TREE_GROWER, p), JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> BITTERWOOD_CAMPFIRE = registerCampfire("bitterwood_campfire", "Bitterwood Campfire", BitterwoodCampfireBlock::new, JBlockProperties.CAMPFIRE);
    public static final DeferredBlock<Block> PERMAFROST_FURNACE = registerFurnaceBlock("permafrost_furnace", "Permafrost Furnace");
    public static final DeferredBlock<Block> PACKED_SNOW_BRICKS = register("packed_snow_bricks", "Packed Snow Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> PACKED_SNOW_BRICK_WALL = registerWallBlock("packed_snow_brick_wall", "Packed Snow Brick Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> PACKED_SNOW_BRICK_STAIRS = registerStairs("packed_snow_brick_stairs", "Packed Snow Brick Stairs", PACKED_SNOW_BRICKS, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> PACKED_SNOW_BRICK_SLAB = registerSlab("packed_snow_brick_slab", "Packed Snow Brick Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> PACKED_SNOW_BRICK_BUTTON = registerButton("packed_snow_brick_button", "Packed Snow Brick Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> PACKED_SNOW_BRICK_PRESSURE_PLATE = registerPressurePlate("packed_snow_brick_pressure_plate", "Packed Snow Brick Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> PACKED_SNOW_BRICK_FENCE_GATE = registerFenceGate("packed_snow_brick_fence_gate", "Packed Snow Brick Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> PACKED_SNOW_BRICK_FENCE = registerFence("packed_snow_brick_fence", "Packed Snow Brick Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> PACKED_ICE_BRICKS = register("packed_ice_bricks", "Packed Ice Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> PACKED_ICE_BRICK_WALL = registerWallBlock("packed_ice_brick_wall", "Packed Ice Brick Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> PACKED_ICE_BRICK_STAIRS = registerStairs("packed_ice_brick_stairs", "Packed Ice Brick Stairs", PACKED_ICE_BRICKS, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> PACKED_ICE_BRICK_SLAB = registerSlab("packed_ice_brick_slab", "Packed Ice Brick Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> PACKED_ICE_BRICK_BUTTON = registerButton("packed_ice_brick_button", "Packed Ice Brick Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> PACKED_ICE_BRICK_PRESSURE_PLATE = registerPressurePlate("packed_ice_brick_pressure_plate", "Packed Ice Brick Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> PACKED_ICE_BRICK_FENCE_GATE = registerFenceGate("packed_ice_brick_fence_gate", "Packed Ice Brick Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> PACKED_ICE_BRICK_FENCE = registerFence("packed_ice_brick_fence", "Packed Ice Brick Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> FROZEN_BRICKS = register("frozen_bricks", "Frozen Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> FROZEN_BRICK_WALL = registerWallBlock("frozen_brick_wall", "Frozen Brick Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> FROZEN_BRICK_STAIRS = registerStairs("frozen_brick_stairs", "Frozen Brick Stairs", FROZEN_BRICKS, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> FROZEN_BRICK_SLAB = registerSlab("frozen_brick_slab", "Frozen Brick Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> FROZEN_BRICK_BUTTON = registerButton("frozen_brick_button", "Frozen Brick Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> FROZEN_BRICK_PRESSURE_PLATE = registerPressurePlate("frozen_brick_pressure_plate", "Frozen Brick Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> FROZEN_BRICK_FENCE_GATE = registerFenceGate("frozen_brick_fence_gate", "Frozen Brick Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> FROZEN_BRICK_FENCE = registerFence("frozen_brick_fence", "Frozen Brick Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> PERMAFROST_ROAD = registerPathBlock("permafrost_road", "Permafrost Road");
    public static final DeferredBlock<Block> FROZEN_BLOOM = registerCrossBlock("frozen_bloom", "Frozen Bloom", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> FROZEN_FLOWER = registerCrossBlock("frozen_flower", "Frozen Flower", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> FROSTY_ICE = register("frosty_ice", "Frosty Ice", JBlockProperties.ICE);
    public static final DeferredBlock<Block> ICE_STONE = register("ice_stone", "Ice Stone", JBlockProperties.GLOW_BLOCK);
    public static final DeferredBlock<Block> FROST_GEM_BLOCK = register("frost_gem_block", "Frost Gem Block", JBlockProperties.STONE);
    public static final DeferredBlock<Block> FROSTBITER_SPAWNER = register("frostbiter_spawner", "Frostbiter Spawner", FrostbiterSpawnerBlock::new, JBlockProperties.SPAWNER);
    public static final DeferredBlock<Block> GLACIAL_ROCK = register("glacial_rock", "Glacial Rock", JBlockProperties.STONE);

    public static final DeferredBlock<Block> BOIL_PORTAL_FRAME = register("boil_portal_frame", "Boiling Point Portal Frame", JBlockProperties.STONE);
    public static final DeferredBlock<JBasePortalBlock> BOIL_PORTAL = registerPortalBlock("boil_portal", "Boiling Point Portal", Dimensions.BOIL, BOIL_PORTAL_FRAME);
    public static final DeferredBlock<Block> SULPHUR_CRYSTAL = registerAttachedCrossBlock("sulphur_crystal", "Sulphur Crystal", AttachedBlock::new, JBlockProperties.STONE);
    public static final DeferredBlock<Block> SULPHUR_ROCK = register("sulphur_rock", "Sulphur Rock", JBlockProperties.STONE);
    public static final DeferredBlock<Block> RUBBLE = registerTerrainBlock("rubble", "Rubble", JBlockProperties.FIRE_DIRT);
    public static final DeferredBlock<Block> ASH_BLOCK = registerTerrainBlock("ash_block", "Ash", JBlockProperties.FIRE_STONE);
    public static final DeferredBlock<Block> SCORCHED_RUBBLE = registerTerrainBlock("scorched_rubble", "Scorched Rubble", JBlockProperties.FIRE_DIRT);
    public static final DeferredBlock<Block> VOLCANIC_SAND = registerTerrainBlock("volcanic_sand", "Volcanic Sand", JBlockProperties.FIRE_SAND);
    public static final DeferredBlock<Block> VOLCANIC_SOIL = registerTerrainBlock("volcanic_soil", "Volcanic Soil", JBlockProperties.FIRE_DIRT);
    public static final DeferredBlock<Block> HOT_GROUND = registerTerrainBlock("hot_ground", "Hot Ground", JBlockProperties.FIRE_STONE);
    public static final DeferredBlock<Block> CHARRED_GRASS = registerGrassBlock("charred_grass", "Charred Grass", JGrassBlock::new);
    public static final DeferredBlock<Block> VOLCANIC_SANDSTONE = registerGrassBlock("volcanic_sandstone", "Volcanic Sandstone", Block::new, JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> VOLCANIC_SANDSTONE_WALL = registerWallBlock("volcanic_sandstone_wall", "Volcanic Sandstone Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> VOLCANIC_SANDSTONE_STAIRS = registerStairs("volcanic_sandstone_stairs", "Volcanic Sandstone Stairs", VOLCANIC_SANDSTONE, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> VOLCANIC_SANDSTONE_SLAB = registerSlab("volcanic_sandstone_slab", "Volcanic Sandstone Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> VOLCANIC_SANDSTONE_BUTTON = registerButton("volcanic_sandstone_button", "Volcanic Sandstone Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> VOLCANIC_SANDSTONE_PRESSURE_PLATE = registerPressurePlate("volcanic_sandstone_pressure_plate", "Volcanic Sandstone Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> VOLCANIC_SANDSTONE_FENCE_GATE = registerFenceGate("volcanic_sandstone_fence_gate", "Volcanic Sandstone Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> VOLCANIC_SANDSTONE_FENCE = registerFence("volcanic_sandstone_fence", "Volcanic Sandstone Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> SCORCHED_STALAGMITE_TINY = registerModeledBlock("scorched_stalagmite_tiny", "Scorched Stalagmite", JBlockStalagmite::new, JBlockProperties.STONE);
    public static final DeferredBlock<Block> SCORCHED_STALAGMITE_SMALL = registerModeledBlock("scorched_stalagmite_small", "Scorched Stalagmite", JBlockStalagmite::new, JBlockProperties.STONE);
    public static final DeferredBlock<Block> SCORCHED_STALAGMITE_MED = registerModeledBlock("scorched_stalagmite_med", "Scorched Stalagmite", JBlockStalagmite::new, JBlockProperties.STONE);
    public static final DeferredBlock<Block> SCORCHED_STALAGMITE_LARGE = registerModeledBlock("scorched_stalagmite_large", "Scorched Stalagmite", JBlockStalagmite::new, JBlockProperties.STONE);
    public static final DeferredBlock<Block> CHARRED_BRUSH = registerModeledBlock("charred_brush", "Charred Brush", VineBlock::new, JBlockProperties.VINE);
    public static final DeferredBlock<Block> CHARRED_SHORT_GRASS = registerCrossBlock("charred_short_grass", "Charred Short Grass", TallGrassBlock::new, JBlockProperties.REPLACABLE_PLANT);
    public static final DeferredBlock<Block> CHARRED_WEEDS = registerCrossBlock("charred_weeds", "Charred Weeds", TallGrassBlock::new, JBlockProperties.REPLACABLE_PLANT);
    public static final DeferredBlock<Block> CRUMBLING_PINE = registerCrossBlock("crumbling_pine", "Crumbling Pine", TallGrassBlock::new, JBlockProperties.REPLACABLE_PLANT);
    public static final DeferredBlock<Block> CRISP_GRASS = registerCrossBlock("crisp_grass", "Crisp Grass", TallGrassBlock::new, JBlockProperties.REPLACABLE_PLANT);
    public static final DeferredBlock<Block> FLAME_POD = registerCrossBlock("flame_pod", "Flame Pod", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> LAVA_BLOOM = registerCrossBlock("lava_bloom", "Lava Bloom", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> INFERNO_BUSH = registerCrossBlock("inferno_bush", "Inferno Bush", TallGrassBlock::new, JBlockProperties.REPLACABLE_PLANT);
    public static final DeferredBlock<Block> SCORCHED_CACTUS = registerModeledBlock("scorched_cactus", "Scorched Cactus", JBlockCactus::new, JBlockProperties.CACTUS);
    public static final DeferredBlock<Block> CHARRED_LEAVES = registerTerrainBlock("charred_leaves", "Charred Leaves", (p) -> new JLeavesBlock(p, 9043970), JBlockProperties.LEAVES);
    public static final DeferredBlock<RotatedPillarBlock> BURNED_BARK = addLogBlock("burned_bark", "Burned Bark");
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_BURNED_BARK = registerPillar("stripped_burned_bark", "Stripped Burned Bark", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> BURNED_SAPLING = registerCrossBlock("burned_sapling", "Burned Sapling", (p) -> new JSaplingBlock(JTreeGrower.BURNED_TREE_GROWER, p), JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> CHARRED_SAPLING = registerCrossBlock("charred_sapling", "Charred Sapling", (p) -> new JSaplingBlock(JTreeGrower.CHARRED_TREE_GROWER, p), JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> BURNED_PLANKS = registerFuelBlock("burned_bark_planks", "Burned Planks", JBlockProperties.WOOD, 300);
    public static final DeferredBlock<DoorBlock> BURNED_DOOR = registerDoor("burned_door", "Burned Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<TrapDoorBlock> BURNED_TRAP_DOOR = registerTrapDoor("burned_trap_door", "Burned Trap Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<StairBlock> BURNED_STAIRS = registerStairs("burned_stairs", "Burned Plank Stairs", BURNED_PLANKS, true, JBlockProperties.WOOD);
    public static final DeferredBlock<SlabBlock> BURNED_SLAB = registerSlab("burned_slab", "Burned Plank Slab", true, JBlockProperties.WOOD);
    public static final DeferredBlock<ButtonBlock> BURNED_BUTTON = registerButton("burned_button", "Burned Button", true, true, JBlockProperties.BUTTON);
    public static final DeferredBlock<PressurePlateBlock> BURNED_PRESSURE_PLATE = registerPressurePlate("burned_pressure_plate", "Burned Pressure Plate",true, JBlockProperties.WOOD);
    public static final DeferredBlock<FenceGateBlock> BURNED_FENCE_GATE = registerFenceGate("burned_fence_gate", "Burned Fence Gate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<JFenceBlock> BURNED_FENCE = registerFence("burned_fence", "Burned Fence", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> VOLCANIC_ROCK = registerModeledBlock("volcanic_rock", "Volcanic Rock", VolcanicRockBlock::new, JBlockProperties.VOLCANIC_BLOCK);
    public static final DeferredBlock<Block> BOIL_LOCK = registerRotatableBlock("boil_lock", "Boiling Lock", LockBlock::new, JBlockProperties.DUNGEON_BLOCK, false);
    public static final DeferredBlock<Block> TALL_MOLTEN_PLANT = registerDoublePlant("tall_molten_plant", "Tall Molten Plant", JDoublePlantBlock::new, JBlockProperties.PLANT);
    public static final DeferredBlock<Block> TALL_CRUMBLING_PINE = registerDoublePlant("tall_crumbling_pine", "Tall Crumbling Pine", JDoublePlantBlock::new, JBlockProperties.PLANT);
    public static final DeferredBlock<Block> TALL_CHARRED_GRASS = registerDoublePlant("tall_charred_grass", "Tall Charred Grass", JDoublePlantBlock::new, JBlockProperties.PLANT);
    public static final DeferredBlock<Block> TALL_SIZZLESHROOM = registerDoublePlant("tall_sizzleshroom", "Tall Sizzleshroom", TallGlowshroomBlock::new, JBlockProperties.CAVE_GLOW_PLANT);
    public static final DeferredBlock<Block> SIZZLESHROOM = registerCrossBlock("sizzleshroom", "Sizzleshroom", CavePlantBlock::new, JBlockProperties.CAVE_GLOW_PLANT);

    public static final DeferredBlock<Block> DEPTHS_PORTAL_FRAME = registerEndPortalFrameStyleBlock("depths_portal_frame", "Depths Portal Frame", DepthsPortalFrameBlock::new, JBlockProperties.STONE);
    public static final DeferredBlock<Block> DEPTHS_PORTAL = registerEndPortalStyleBlock("depths_portal", "Depths Portal", DepthsPortalBlock::new, JBlockProperties.PORTAL);
    public static final DeferredBlock<Block> DEPTHS_GRASS = registerGrassBlock("depths_grass", "Depths Grass", JGrassBlock::new);
    public static final DeferredBlock<Block> DEPTHS_PATH = registerPathBlock("depths_path", "Depths Path");
    public static final DeferredBlock<Block> DEPTHS_DIRT = registerTerrainBlock("depths_dirt", "Depths Soil", JDirt::new, JBlockProperties.DIRT);
    public static final DeferredBlock<Block> DEPTHS_STONE = registerTerrainBlock("depths_stone", "Depths Stone", JBlockProperties.STONE);
    public static final DeferredBlock<RotatedPillarBlock> DEPTHS_LOG = addLogBlock("depths_log", "Depths Log");
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_DEPTHS_LOG = registerPillar("stripped_depths_log", "Stripped Depths Log", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> DEPTHS_LEAVES = registerTerrainBlock("depths_leaves", "Depths Leaves", (p) -> new JLeavesBlock(p, 20991), JBlockProperties.LUMINESCENT_LEAVES);
    public static final DeferredBlock<Block> DEPTHS_SAPLING = registerCrossBlock("depths_sapling", "Depths Sapling", (p) -> new JSaplingBlock(JTreeGrower.DEPTHS_TREE_GROWER, p), JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> DEPTHS_PLANKS = registerFuelBlock("depths_planks", "Depths Planks", JBlockProperties.WOOD, 300);
    public static final DeferredBlock<DoorBlock> DEPTHS_DOOR = registerDoor("depths_door", "Depths Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<TrapDoorBlock> DEPTHS_TRAP_DOOR = registerTrapDoor("depths_trap_door", "Depths Trap Door", true, JBlockProperties.DOOR);
    public static final DeferredBlock<StairBlock> DEPTHS_STAIRS = registerStairs("depths_stairs", "Depths Plank Stairs", DEPTHS_PLANKS, true, JBlockProperties.WOOD);
    public static final DeferredBlock<SlabBlock> DEPTHS_SLAB = registerSlab("depths_slab", "Depths Plank Slab", true, JBlockProperties.WOOD);
    public static final DeferredBlock<ButtonBlock> DEPTHS_BUTTON = registerButton("depths_button", "Depths Button", true, true, JBlockProperties.BUTTON);
    public static final DeferredBlock<PressurePlateBlock> DEPTHS_PRESSURE_PLATE = registerPressurePlate("depths_pressure_plate", "Depths Pressure Plate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<FenceGateBlock> DEPTHS_FENCE_GATE = registerFenceGate("depths_fence_gate", "Depths Fence Gate", true, JBlockProperties.WOOD);
    public static final DeferredBlock<JFenceBlock> DEPTHS_FENCE = registerFence("depths_fence", "Depths Fence", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> DEPTHS_CRYSTAL = registerAttachedCrossBlock("depths_crystal", "Depths Crystal", AttachedBlock::new, JBlockProperties.LIGHT_CRYSTAL_BLOCK);
    public static final DeferredBlock<Block> DEPTHS_CRYSTAL_BLOCK = register("depths_crystal_block", "Depths Crystal Block", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BUDDING_DEPTHS_CRYSTAL = register("budding_depths_crystal", "Budding Depths Crystal", JBlockProperties.STONE);
    public static final DeferredBlock<Block> FLOOR_DEPTHS_CRYSTAL_BLUE = registerModeledBlock("floor_depths_crystal_blue", "Depths Crystal", FloorDepthsCrystalBlock::new, JBlockProperties.CRYSTAL);
    public static final DeferredBlock<Block> FLOOR_DEPTHS_CRYSTAL_PINK = registerModeledBlock("floor_depths_crystal_pink", "Depths Crystal", FloorDepthsCrystalBlock::new, JBlockProperties.CRYSTAL);
    public static final DeferredBlock<Block> FLOOR_DEPTHS_CRYSTAL_YELLOW = registerModeledBlock("floor_depths_crystal_yellow", "Depths Crystal", FloorDepthsCrystalBlock::new, JBlockProperties.CRYSTAL);
    public static final DeferredBlock<Block> FLOOR_DEPTHS_CRYSTAL_GREEN = registerModeledBlock("floor_depths_crystal_green", "Depths Crystal", FloorDepthsCrystalBlock::new, JBlockProperties.CRYSTAL);
    public static final DeferredBlock<Block> DARK_BRICK = register("dark_brick", "Dark Brick", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> DARK_BRICK_WALL = registerWallBlock("dark_brick_wall", "Dark Brick Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> DARK_BRICK_STAIRS = registerStairs("dark_brick_stairs", "Dark Brick Stairs", DARK_BRICK, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> DARK_BRICK_SLAB = registerSlab("dark_brick_slab", "Dark Brick Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> DARK_BRICK_BUTTON = registerButton("dark_brick_button", "Dark Brick Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> DARK_BRICK_PRESSURE_PLATE = registerPressurePlate("dark_brick_pressure_plate", "Dark Brick Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> DARK_BRICK_FENCE_GATE = registerFenceGate("dark_brick_fence_gate", "Dark Brick Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> DARK_BRICK_FENCE = registerFence("dark_brick_fence", "Dark Brick Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> DARK_FLOOR = register("dark_floor", "Dark Floor", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> DARK_FLOOR_WALL = registerWallBlock("dark_floor_wall", "Dark Floor Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> DARK_FLOOR_STAIRS = registerStairs("dark_floor_stairs", "Dark Floor Stairs", DARK_FLOOR, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> DARK_FLOOR_SLAB = registerSlab("dark_floor_slab", "Dark Floor Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> DARK_FLOOR_BUTTON = registerButton("dark_floor_button", "Dark Floor Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> DARK_FLOOR_PRESSURE_PLATE = registerPressurePlate("dark_floor_pressure_plate", "Dark Floor Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> DARK_FLOOR_FENCE_GATE = registerFenceGate("dark_floor_fence_gate", "Dark Floor Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> DARK_FLOOR_FENCE = registerFence("dark_floor_fence", "Dark Floor Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> DARK_SHINGLE = register("dark_shingle", "Dark Shingle", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> DARK_SHINGLE_WALL = registerWallBlock("dark_shingle_wall", "Dark Shingle Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> DARK_SHINGLE_STAIRS = registerStairs("dark_shingle_stairs", "Dark Shingle Stairs", DARK_SHINGLE, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> DARK_SHINGLE_SLAB = registerSlab("dark_shingle_slab", "Dark Shingle Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> DARK_SHINGLE_BUTTON = registerButton("dark_shingle_button", "Dark Shingle Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> DARK_SHINGLE_PRESSURE_PLATE = registerPressurePlate("dark_shingle_pressure_plate", "Dark Shingle Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> DARK_SHINGLE_FENCE_GATE = registerFenceGate("dark_shingle_fence_gate", "Dark Shingle Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> DARK_SHINGLE_FENCE = registerFence("dark_shingle_fence", "Dark Shingle Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> DEPTHS_BRICK = register("depths_brick", "Depths Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> DEPTHS_BRICK_WALL = registerWallBlock("depths_brick_wall", "Depths Brick Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> DEPTHS_BRICK_STAIRS = registerStairs("depths_brick_stairs", "Depths Brick Stairs", DEPTHS_BRICK, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> DEPTHS_BRICK_SLAB = registerSlab("depths_brick_slab", "Depths Brick Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> DEPTHS_BRICK_BUTTON = registerButton("depths_brick_button", "Depths Brick Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> DEPTHS_BRICK_PRESSURE_PLATE = registerPressurePlate("depths_brick_pressure_plate", "Depths Brick Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> DEPTHS_BRICK_FENCE_GATE = registerFenceGate("depths_brick_fence_gate", "Depths Brick Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> DEPTHS_BRICK_FENCE = registerFence("depths_brick_fence", "Depths Brick Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> DEPTHS_SHINGLE = register("depths_shingle", "Depths Shingle", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> DEPTHS_SHINGLE_WALL = registerWallBlock("depths_shingle_wall", "Depths Shingle Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> DEPTHS_SHINGLE_STAIRS = registerStairs("depths_shingle_stairs", "Depths Shingle Stairs", DEPTHS_SHINGLE, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> DEPTHS_SHINGLE_SLAB = registerSlab("depths_shingle_slab", "Depths Shingle Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> DEPTHS_SHINGLE_BUTTON = registerButton("depths_shingle_button", "Depths Shingle Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> DEPTHS_SHINGLE_PRESSURE_PLATE = registerPressurePlate("depths_shingle_pressure_plate", "Depths Shingle Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> DEPTHS_SHINGLE_FENCE_GATE = registerFenceGate("depths_shingle_fence_gate", "Depths Shingle Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> DEPTHS_SHINGLE_FENCE = registerFence("depths_shingle_fence", "Depths Shingle Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> DEPTHS_DARK_SHINGLE = register("depths_dark_shingle", "Depths Dark Shingle", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> DEPTHS_DARK_SHINGLE_WALL = registerWallBlock("depths_dark_shingle_wall", "Depths Dark Shingle Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> DEPTHS_DARK_SHINGLE_STAIRS = registerStairs("depths_dark_shingle_stairs", "Depths Dark Shingle Stairs", DEPTHS_DARK_SHINGLE, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> DEPTHS_DARK_SHINGLE_SLAB = registerSlab("depths_dark_shingle_slab", "Depths Dark Shingle Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> DEPTHS_DARK_SHINGLE_BUTTON = registerButton("depths_dark_shingle_button", "Depths Dark Shingle Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> DEPTHS_DARK_SHINGLE_PRESSURE_PLATE = registerPressurePlate("depths_dark_shingle_pressure_plate", "Depths Dark Shingle Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> DEPTHS_DARK_SHINGLE_FENCE_GATE = registerFenceGate("depths_dark_shingle_fence_gate", "Depths Dark Shingle Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> DEPTHS_DARK_SHINGLE_FENCE = registerFence("depths_dark_shingle_fence", "Depths Dark Shingle Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> DEPTHS_COBBLESTONE = register("depths_cobblestone", "Depths Cobblestone", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> DEPTHS_COBBLESTONE_WALL = registerWallBlock("depths_cobblestone_wall", "Depths Cobblestone Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> DEPTHS_COBBLESTONE_STAIRS = registerStairs("depths_cobblestone_stairs", "Depths Cobblestone Stairs", DEPTHS_COBBLESTONE, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> DEPTHS_COBBLESTONE_SLAB = registerSlab("depths_cobblestone_slab", "Depths Cobblestone Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> DEPTHS_COBBLESTONE_BUTTON = registerButton("depths_cobblestone_button", "Depths Cobblestone Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> DEPTHS_COBBLESTONE_PRESSURE_PLATE = registerPressurePlate("depths_cobblestone_pressure_plate", "Depths Cobblestone Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> DEPTHS_COBBLESTONE_FENCE_GATE = registerFenceGate("depths_cobblestone_fence_gate", "Depths Cobblestone Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> DEPTHS_COBBLESTONE_FENCE = registerFence("depths_cobblestone_fence", "Depths Cobblestone Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> DEPTHS_TILE = register("depths_tile", "Depths Tile", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> DEPTHS_TILE_WALL = registerWallBlock("depths_tile_wall", "Depths Tile Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> DEPTHS_TILE_STAIRS = registerStairs("depths_tile_stairs", "Depths Tile Stairs", DEPTHS_TILE, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> DEPTHS_TILE_SLAB = registerSlab("depths_tile_slab", "Depths Tile Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> DEPTHS_TILE_BUTTON = registerButton("depths_tile_button", "Depths Tile Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> DEPTHS_TILE_PRESSURE_PLATE = registerPressurePlate("depths_tile_pressure_plate", "Depths Tile Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> DEPTHS_TILE_FENCE_GATE = registerFenceGate("depths_tile_fence_gate", "Depths Tile Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> DEPTHS_TILE_FENCE = registerFence("depths_tile_fence", "Depths Tile Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> DEPTHS_GLASS = register("depths_glass", "Depths Glass", JTransparentBlock::new, JBlockProperties.GLASS);
    public static final DeferredBlock<RotatedPillarBlock> DEPTHS_PILLAR = registerPillar("depths_pillar", "Depths Pillar", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> DARKLY_LOCK = registerRotatableBlock("darkly_lock", "Darkly Lock", LockBlock::new, JBlockProperties.DUNGEON_BLOCK, false);
    public static final DeferredBlock<Block> DEPTHS_LOCK = registerRotatableBlock("depths_lock", "Depths Lock", LockBlock::new, JBlockProperties.DUNGEON_BLOCK, false);
    public static final DeferredBlock<RotatedPillarBlock> DEPTHS_BOOK_SHELF = registerPillar("depths_book_shelf", "Depths Bookshelf", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> DEPTHS_BLUE_FLOWER = registerCrossBlock("depths_blue_flower", "Depths Blue Flower", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> DEPTHS_FLOWER = registerCrossBlock("depths_flower", "Depths Flower", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> DEPTHS_FURNACE = registerFurnaceBlock("depths_furnace", "Depths Furnace");
    public static final DeferredBlock<IronBarsBlock> DEPTHS_GATE = registerPaneBlock("depths_gate", "Depths Gate", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> DARK_SORCERER_SPAWNER = register("dark_sorcerer_spawner", "Dark Sorcerer Spawner", DarkSorcererSpawnerBlock::new, JBlockProperties.SPAWNER);
    public static final DeferredBlock<Block> POINTED_CRYSTALLIZED_DRIPSTONE = registerDripstoneBlock("crystallized_pointed_dripstone", "Crystallized Dripstone", CrystallizedDripstoneBlock::new, JBlockProperties.LIGHT_POINTED_DRIPSTONE);
    public static final DeferredBlock<Block> CRYSTALLIZED_DRIPSTONE = register("crystallized_dripstone", "Crystallized Dripstone", JBlockProperties.DRIPSTONE);
    public static final DeferredBlock<Block> DEPTHS_MOSS_BLOCK = register("depths_moss_block", "Depths Moss Block", JBlockProperties.DIRT);
    public static final DeferredBlock<Block> POINTED_DEPTHS_DRIPSTONE = registerDripstoneBlock("depths_pointed_dripstone", "Depths Dripstone", DepthsDripstoneBlock::new, JBlockProperties.POINTED_DRIPSTONE);
    public static final DeferredBlock<Block> DEPTHS_DRIPSTONE = register("depths_dripstone", "Depths Dripstone", JBlockProperties.DRIPSTONE);
    public static final DeferredBlock<Block> GREEN_CRYSTAL_SHROOM_BLOCK = registerMushroomBlock("green_crystal_shroom_block", "Green Crystal Shroom", GlowMushroomBlock::new, JBlockProperties.GLOW_MUSHROOM_BLOCK);
    public static final DeferredBlock<Block> BLUE_CRYSTAL_SHROOM_BLOCK = registerMushroomBlock("blue_crystal_shroom_block", "Blue Crystal Shroom", GlowMushroomBlock::new, JBlockProperties.GLOW_MUSHROOM_BLOCK);
    public static final DeferredBlock<Block> RED_CRYSTAL_SHROOM_BLOCK = registerMushroomBlock("red_crystal_shroom_block", "Red Crystal Shroom", GlowMushroomBlock::new, JBlockProperties.GLOW_MUSHROOM_BLOCK);
    public static final DeferredBlock<Block> PURPLE_CRYSTAL_SHROOM_BLOCK = registerMushroomBlock("purple_crystal_shroom_block", "Purple Crystal Shroom", GlowMushroomBlock::new, JBlockProperties.GLOW_MUSHROOM_BLOCK);
    public static final DeferredBlock<Block> CRYSTALSHROOM_STEM = registerMushroomBlock("crystalshroom_stem", "Crystalshroom Stem", GlowMushroomBlock::new, JBlockProperties.GLOW_MUSHROOM_BLOCK);
    public static final DeferredBlock<Block> SMALL_LIGSHROOM = registerModeledBlock("small_ligshroom", "Small Ligshroom", TallGrassBlock::new, JBlockProperties.GLOW_FLOWER);
    public static final DeferredBlock<Block> TALL_LIGSHROOM = registerModeledBlock("tall_ligshroom", "Tall Ligshroom", TallGrassBlock::new, JBlockProperties.GLOW_FLOWER);

    public static final DeferredBlock<Block> CORBA_PORTAL_FRAME = registerEndPortalFrameStyleBlock("corba_portal_frame", "Corba Portal Frame", CorbaPortalFrameBlock::new, JBlockProperties.STONE);
    public static final DeferredBlock<Block> CORBA_PORTAL = registerEndPortalStyleBlock("corba_portal", "Corba Portal", CorbaPortalBlock::new, JBlockProperties.PORTAL);
    public static final DeferredBlock<Block> CORBA_DIRT = registerTerrainBlock("corba_dirt", "Corba Dirt", JDirt::new, JBlockProperties.DIRT);
    public static final DeferredBlock<Block> CORBA_GRASS = registerOverlayGrassBlock("corba_grass", "Corba Grass", JGrassBlock::new);
    public static final DeferredBlock<Block> CORBA_STONE = registerTerrainBlock("corba_stone", "Corba Stone", JBlockProperties.STONE);
    public static final DeferredBlock<Block> CORBA_PATH = registerPathBlock("corba_path", "Corba Path");
    public static final DeferredBlock<JFenceBlock> CORBA_POST = registerFence("corba_post", "Corba Post", true, JBlockProperties.WOOD);
    public static final DeferredBlock<RotatedPillarBlock> CORBA_PLILLAR = registerPillar("corba_pillar", "Corba Pillar", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> TAINTED_MUD = registerTerrainBlock("tainted_mud", "Tainted Mud", JBlockProperties.DIRT);
    public static final DeferredBlock<Block> DRIED_MUD = registerTerrainBlock("dried_mud", "Loggers Mud", JBlockProperties.DIRT);
    public static final DeferredBlock<RotatedPillarBlock> BOGWOOD_LOG = addLogBlock("bogwood_log", "Bogwood Log");
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_BOGWOOD_LOG = registerPillar("stripped_bogwood_log", "Stripped Bogwood Log", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> BOGWOOD_LEAVES = registerTintedLeavesBlock("bogwood_leaves", "Bogwood Leaves", (p) -> new JLeavesBlock(p, 7186988), JBlockProperties.LEAVES);
    public static final DeferredBlock<Block> BOGWOOD_SAPLING = registerCrossBlock("bogwood_sapling", "Bogwood Sapling", (p) -> new JSaplingBlock(JTreeGrower.BOGWOOD_TREE_GROWER, p), JBlockProperties.FLOWER);
    public static final DeferredBlock<RotatedPillarBlock> CORBA_LOG = addLogBlock("corba_log", "Corba Log");
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CORBA_LOG = registerPillar("stripped_corba_log", "Stripped Corba Log", true, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> CORBA_LEAVES = registerTintedLeavesBlock("corba_leaves", "Corba Leaves", (p) -> new JLeavesBlock(p, 7186988), JBlockProperties.LEAVES);
    public static final DeferredBlock<Block> CORBA_SAPLING = registerCrossBlock("corba_sapling", "Corba Sapling", (p) -> new JSaplingBlock(JTreeGrower.CORBA_TREE_GROWER, p), JBlockProperties.FLOWER);
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
    public static final DeferredBlock<JWallBlock> CORBA_COBBLESTONE_WALL = registerWallBlock("corba_cobblestone_wall", "Corba Cobblestone Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> CORBA_COBBLESTONE_STAIRS = registerStairs("corba_cobblestone_stairs", "Corba Cobblestone Stairs", CORBA_COBBLESTONE, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> CORBA_COBBLESTONE_SLAB = registerSlab("corba_cobblestone_slab", "Corba Cobblestone Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> CORBA_COBBLESTONE_BUTTON = registerButton("corba_cobblestone_button", "Corba Cobblestone Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> CORBA_COBBLESTONE_PRESSURE_PLATE = registerPressurePlate("corba_cobblestone_pressure_plate", "Corba Cobblestone Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> CORBA_COBBLESTONE_FENCE_GATE = registerFenceGate("corba_cobblestone_fence_gate", "Corba Cobblestone Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> CORBA_COBBLESTONE_FENCE = registerFence("corba_cobblestone_fence", "Corba Cobblestone Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> CORBA_FURNACE = registerFurnaceBlock("corba_furnace", "Corba Furnace");
    public static final DeferredBlock<Block> CORBA_BRICKS = register("corba_bricks", "Corba Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> CORBA_BRICK_WALL = registerWallBlock("corba_brick_wall", "Corba Brick Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> CORBA_BRICK_STAIRS = registerStairs("corba_brick_stairs", "Corba Brick Stairs", CORBA_BRICKS, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> CORBA_BRICK_SLAB = registerSlab("corba_brick_slab", "Corba Brick Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> CORBA_BRICK_BUTTON = registerButton("corba_brick_button", "Corba Brick Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> CORBA_BRICK_PRESSURE_PLATE = registerPressurePlate("corba_brick_pressure_plate", "Corba Brick Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> CORBA_BRICK_FENCE_GATE = registerFenceGate("corba_brick_fence_gate", "Corba Brick Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> CORBA_BRICK_FENCE = registerFence("corba_brick_fence", "Corba Brick Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> CORBA_CRACKED_BRICKS = register("corba_cracked_bricks", "Corba Cracked Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> CORBA_CRACKED_BRICK_WALL = registerWallBlock("corba_cracked_brick_wall", "Corba Cracked Brick Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> CORBA_CRACKED_BRICK_STAIRS = registerStairs("corba_cracked_brick_stairs", "Corba Cracked Brick Stairs", CORBA_CRACKED_BRICKS, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> CORBA_CRACKED_BRICK_SLAB = registerSlab("corba_cracked_brick_slab", "Corba Cracked Brick Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> CORBA_CRACKED_BRICK_BUTTON = registerButton("corba_cracked_brick_button", "Corba Cracked Brick Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> CORBA_CRACKED_BRICK_PRESSURE_PLATE = registerPressurePlate("corba_cracked_brick_pressure_plate", "Corba Cracked Brick Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> CORBA_CRACKED_BRICK_FENCE_GATE = registerFenceGate("corba_cracked_brick_fence_gate", "Corba Cracked Brick Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> CORBA_CRACKED_BRICK_FENCE = registerFence("corba_cracked_brick_fence", "Corba Cracked Brick Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> CORBA_DARK_BRICKS = register("corba_dark_bricks", "Corba Dark Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> CORBA_DARK_BRICK_WALL = registerWallBlock("corba_dark_brick_wall", "Corba Dark Brick Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> CORBA_DARK_BRICK_STAIRS = registerStairs("corba_dark_brick_stairs", "Corba Dark Brick Stairs", CORBA_DARK_BRICKS, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> CORBA_DARK_BRICK_SLAB = registerSlab("corba_dark_brick_slab", "Corba Dark Brick Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> CORBA_DARK_BRICK_BUTTON = registerButton("corba_dark_brick_button", "Corba Dark Brick Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> CORBA_DARK_BRICK_PRESSURE_PLATE = registerPressurePlate("corba_dark_brick_pressure_plate", "Corba Dark Brick Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> CORBA_DARK_BRICK_FENCE_GATE = registerFenceGate("corba_dark_brick_fence_gate", "Corba Dark Brick Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> CORBA_DARK_BRICK_FENCE = registerFence("corba_dark_brick_fence", "Corba Dark Brick Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> CORBA_LIGHT_BRICKS = register("corba_light_bricks", "Corba Light Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> CORBA_LIGHT_BRICK_WALL = registerWallBlock("corba_light_brick_wall", "Corba Light Brick Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> CORBA_LIGHT_BRICK_STAIRS = registerStairs("corba_light_brick_stairs", "Corba Light Brick Stairs", CORBA_LIGHT_BRICKS, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> CORBA_LIGHT_BRICK_SLAB = registerSlab("corba_light_brick_slab", "Corba Light Brick Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> CORBA_LIGHT_BRICK_BUTTON = registerButton("corba_light_brick_button", "Corba Light Brick Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> CORBA_LIGHT_BRICK_PRESSURE_PLATE = registerPressurePlate("corba_light_brick_pressure_plate", "Corba Light Brick Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> CORBA_LIGHT_BRICK_FENCE_GATE = registerFenceGate("corba_light_brick_fence_gate", "Corba Light Brick Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> CORBA_LIGHT_BRICK_FENCE = registerFence("corba_light_brick_fence", "Corba Light Brick Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> CORBA_SENTRY_BRICKS = register("corba_sentry_bricks", "Corba Sentry Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> CORBA_SENTRY_BRICK_WALL = registerWallBlock("corba_sentry_brick_wall", "Corba Sentry Brick Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> CORBA_SENTRY_BRICK_STAIRS = registerStairs("corba_sentry_brick_stairs", "Corba Sentry Brick Stairs", CORBA_SENTRY_BRICKS, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> CORBA_SENTRY_BRICK_SLAB = registerSlab("corba_sentry_brick_slab", "Corba Sentry Brick Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> CORBA_SENTRY_BRICK_BUTTON = registerButton("corba_sentry_brick_button", "Corba Sentry Brick Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> CORBA_SENTRY_BRICK_PRESSURE_PLATE = registerPressurePlate("corba_sentry_brick_pressure_plate", "Corba Sentry Brick Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> CORBA_SENTRY_BRICK_FENCE_GATE = registerFenceGate("corba_sentry_brick_fence_gate", "Corba Sentry Brick Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> CORBA_SENTRY_BRICK_FENCE = registerFence("corba_sentry_brick_fence", "Corba Sentry Brick Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> OVERSEER_ELDER_SPAWNER = register("overseer_elder_spawner", "Overseer Elder Spawner", OverseerElderSpawnerBlock::new, JBlockProperties.SPAWNER);
    public static final DeferredBlock<Block> OVERSEER_SPAWNER = register("overseer_spawner", "Overseer Spawner", OverseerSpawnerBlock::new, JBlockProperties.SPAWNER);
    public static final DeferredBlock<Block> ELDER_BLOCK = register("elder_block", "Elder Block", ChangableBlock::new, JBlockProperties.STONE.sound(SoundType.METAL));
    public static final DeferredBlock<Block> CORBA_LADDER = registerLadder("corba_ladder", "Corba Ladder", LadderBlock::new, JBlockProperties.LADDER);
    public static final DeferredBlock<Block> CORBA_BLUE_FLOWER = registerCrossBlock("corba_blue_flower", "Corba Blue Flower", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> CORBA_RED_FLOWER = registerCrossBlock("corba_red_flower", "Corba Red Flower", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> CORBA_SPECKLED_FLOWER = registerCrossBlock("corba_speckled_flower", "Corba Speckled Flower", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> CORBA_PURPLE_FLOWER = registerCrossBlock("corba_purple_flower", "Corba Purple Flower", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> CORBA_LIGHT_PURPLE_FLOWER = registerCrossBlock("corba_light_purple_flower", "Corba Light Purple Flower", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> CORBA_DARK_PURPLE_FLOWER = registerCrossBlock("corba_dark_purple_flower", "Corba Dark Purple Flower", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> CORBA_FLOWER = registerCrossBlock("corba_flower", "Corba Flower", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> CORBA_TALL_GRASS = registerTintedCrossBlock("corba_tall_grass", "Corba Tall Grass", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> SMALL_BOGSHROOM = registerModeledBlock("small_bogshroom", "Small Bogshroom", TallGrassBlock::new, JBlockProperties.GLOW_FLOWER);
    public static final DeferredBlock<Block> TALL_BOGSHROOM = registerModeledBlock("tall_bogshroom", "Tall Bogshroom", TallGrassBlock::new, JBlockProperties.GLOW_FLOWER);
    public static final DeferredBlock<Block> BOGWEED = registerDoublePlant("bogweed", "Bogweed", JDoublePlantBlock::new, JBlockProperties.PLANT);
    public static final DeferredBlock<Block> SWAMP_LILY = registerLilyPad("swamp_lily", "Swamp Lilly", WaterlilyBlock::new, JBlockProperties.LILY_PLANT);
    public static final DeferredBlock<Block> FUNGAL_SHELF = registerModeledBlock("fungal_shelf", "Fungal Shelf", JBlockFungalShelf::new, JBlockProperties.MUSHROOM_SHELF);
    public static final DeferredBlock<Block> SLIME = registerSlimeStyleBlock("slime", "Slime", JSlimeBlock::new, JBlockProperties.SLIME);
    public static final DeferredBlock<Block> SWAMP_LAMP = registerModeledBlock("swamp_lamp", "Swamp Lamp", JBlockSwampLamp::new, JBlockProperties.BOTTLE);

    public static final DeferredBlock<Block> TERRANIAN_PORTAL_FRAME = register("terranian_portal_frame", "Terranian Portal Frame", JBlockProperties.STONE);
    public static final DeferredBlock<JBasePortalBlock> TERRANIAN_PORTAL = registerPortalBlock("terranian_portal", "Terranian Portal", Dimensions.TERRANIA, TERRANIAN_PORTAL_FRAME);
    public static final DeferredBlock<Block> TERRANIAN_GRASS = registerGrassBlock("terranian_grass", "Terranian Grass", JGrassBlock::new);
    public static final DeferredBlock<Block> TERRANIAN_DIRT = registerTerrainBlock("terranian_dirt", "Terranian Dirt", JDirt::new, JBlockProperties.DIRT);
    public static final DeferredBlock<Block> TERRANIAN_STONE = registerTerrainBlock("terranian_stone", "Terranian Stone", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> TERRANIAN_STONE_WALL = registerWallBlock("terranian_stone_wall", "Terranian Stone Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> TERRANIAN_STONE_STAIRS = registerStairs("terranian_stone_stairs", "Terranian Stone Stairs", TERRANIAN_STONE, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> TERRANIAN_STONE_SLAB = registerSlab("terranian_stone_slab", "Terranian Stone Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> TERRANIAN_STONE_BUTTON = registerButton("terranian_stone_button", "Terranian Stone Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> TERRANIAN_STONE_PRESSURE_PLATE = registerPressurePlate("terranian_stone_pressure_plate", "Terranian Stone Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> TERRANIAN_STONE_FENCE_GATE = registerFenceGate("terranian_stone_fence_gate", "Terranian Stone Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> TERRANIAN_STONE_FENCE = registerFence("terranian_stone_fence", "Terranian Stone Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> TERRANIAN_LEAVES = registerTopBottomBlock("terranian_leaves", "Terrania Leaves", (p) -> new JLeavesBlock(p, 9437439), JBlockProperties.LEAVES);
    public static final DeferredBlock<Block> TERRANIAN_VINE = registerVineBlock("terranian_vine", "Terrania Vine", JVineBlock::new, JBlockProperties.VINE);
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
    public static final DeferredBlock<JWallBlock> TERRANIAN_DARK_PANEL_WALL = registerWallBlock("terranian_dark_panel_wall", "Terranian Dark Panel Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> TERRANIAN_DARK_PANEL_STAIRS = registerStairs("terranian_dark_panel_stairs", "Terranian Dark Panel Stairs", TERRANIAN_DARK_PANELS, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> TERRANIAN_DARK_PANEL_SLAB = registerSlab("terranian_dark_panel_slab", "Terranian Dark Panel Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> TERRANIAN_DARK_PANEL_BUTTON = registerButton("terranian_dark_panel_button", "Terranian Dark Panel Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> TERRANIAN_DARK_PANEL_PRESSURE_PLATE = registerPressurePlate("terranian_dark_panel_pressure_plate", "Terranian Dark Panel Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> TERRANIAN_DARK_PANEL_FENCE_GATE = registerFenceGate("terranian_dark_panel_fence_gate", "Terranian Dark Panel Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> TERRANIAN_DARK_PANEL_FENCE = registerFence("terranian_dark_panel_fence", "Terranian Dark Panel Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> TERRANIAN_PANELS = register("terranian_panels", "Terranian Panels", JBlockProperties.STONE);
    public static final DeferredBlock<JWallBlock> TERRANIAN_PANEL_WALL = registerWallBlock("terranian_panel_wall", "Terranian Panel Wall", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> TERRANIAN_PANEL_STAIRS = registerStairs("terranian_panel_stairs", "Terranian Panel Stairs", TERRANIAN_PANELS, false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> TERRANIAN_PANEL_SLAB = registerSlab("terranian_panel_slab", "Terranian Panel Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<ButtonBlock> TERRANIAN_PANEL_BUTTON = registerButton("terranian_panel_button", "Terranian Panel Button", false, false, JBlockProperties.STONE);
    public static final DeferredBlock<PressurePlateBlock> TERRANIAN_PANEL_PRESSURE_PLATE = registerPressurePlate("terranian_panel_pressure_plate", "Terranian Panel Plate", false, JBlockProperties.STONE);
    public static final DeferredBlock<FenceGateBlock> TERRANIAN_PANEL_FENCE_GATE = registerFenceGate("terranian_panel_fence_gate", "Terranian Panel Fence Gate", false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> TERRANIAN_PANEL_FENCE = registerFence("terranian_panel_fence", "Terranian Panel Fence", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> TERRANIAN_TALL_GRASS = registerCrossBlock("terranian_tall_grass", "Terranian Tall Grass", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> TERRAMUSHROOM = registerCrossBlock("terramushroom", "Terranian Shroom", TallGrassBlock::new, JBlockProperties.FLOWER);//makes big terrashroom
    public static final DeferredBlock<Block> TALL_TERRAMUSHROOM = registerDoublePlant("tall_terramushroom", "Tall Terranian Shroom", JDoublePlantBlock::new, JBlockProperties.GLOW_FLOWER);
    public static final DeferredBlock<Block> TERRANIAN_FLOWER = registerCrossBlock("terranian_flower", "Terranian Flower", TallGrassBlock::new, JBlockProperties.FLOWER);
    public static final DeferredBlock<Block> ENCHANTED_SHROOMS_SMALL = registerModeledBlock("enchanted_shrooms_small", "Enchanted Shrooms", TallGrassBlock::new, JBlockProperties.GLOW_FLOWER);
    public static final DeferredBlock<Block> ENCHANTED_SHROOMS_TALL = registerModeledBlock("enchanted_shroom_tall", "Tall Enchanted Shroom", JDoublePlantBlock::new, JBlockProperties.GLOW_FLOWER);
    public static final DeferredBlock<Block> TERRAMUSHROOM_BLOCK_PINK = registerMushroomBlock("terrashroom_block_pink", "Terrashroom Block", HugeMushroomBlock::new, JBlockProperties.MUSHROOM_BLOCK);
    public static final DeferredBlock<Block> TERRAMUSHROOM_BLOCK_PURPLE = registerMushroomBlock("terrashroom_block_purple", "Terrashroom Block", HugeMushroomBlock::new, JBlockProperties.MUSHROOM_BLOCK);
    public static final DeferredBlock<Block> TERRASHROOM_STEM = registerMushroomBlock("terrashroom_stem", "Terrashroom Stem", HugeMushroomBlock::new, JBlockProperties.MUSHROOM_BLOCK);
    public static final DeferredBlock<Block> TERRANIAN_SAPLING = registerCrossBlock("terranian_sapling", "Terranian Sapling", (p) -> new JSaplingBlock(JTreeGrower.TERRRANIAN_TREE_GROWER, p), JBlockProperties.FLOWER);

    public static final DeferredBlock<Block> CLOUDIA_PORTAL_FRAME = register("cloudia_portal_frame", "Cloudia Portal Frame", JBlockProperties.STONE);
    public static final DeferredBlock<JBasePortalBlock> CLOUDIA_PORTAL = registerPortalBlock("cloudia_portal", "Cloudia Portal", Dimensions.CLOUDIA, CLOUDIA_PORTAL_FRAME);
    public static final DeferredBlock<Block> CLOUDIA_DIRT = registerTerrainBlock("cloudia_dirt", "Cloudia Dirt", JDirt::new, JBlockProperties.DIRT);
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
    public static final DeferredBlock<Block> CLOUDIA_LEAVES = registerTerrainBlock("cloudia_leaves", "Cloudia Leaves", (p) -> new JLeavesBlock(p, 15597823), JBlockProperties.LUMINESCENT_LEAVES);
    public static final DeferredBlock<Block> CLOUDIA_COBBLESTONE = register("cloudia_cobblestone", "Cloudia Cobblestone", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> CLOUDIA_BRICK_STAIRS = registerStairs("cloudia_brick_stairs", "Cloudia Brick Stairs", CLOUDIA_BRICK, false, JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> CLOUDIA_TILE_STAIRS = registerStairs("cloudia_tile_stairs", "Cloudia Tile Stairs",CLOUDIA_TILE, false, JBlockProperties.STONE);
    public static final DeferredBlock<JFenceBlock> CLOUDIA_POST = registerFence("cloudia_post", "Cloudia Post", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> PINK_CLOUDIA_CLOUD = register("pink_cloudia_cloud", "Pink Cloudia Cloud", JTransparentBlock::new, JBlockProperties.CLOUD);
    public static final DeferredBlock<Block> BLUE_CLOUDIA_CLOUD = register("blue_cloudia_cloud", "Blue Cloudia Cloud", JTransparentBlock::new, JBlockProperties.CLOUD);
    public static final DeferredBlock<Block> LIGHT_BLUE_CLOUDIA_CLOUD = register("light_blue_cloudia_cloud", "Light Blue Cloudia Cloud", JTransparentBlock::new, JBlockProperties.CLOUD);
    public static final DeferredBlock<SlabBlock> CLOUDIA_TILE_SLAB = registerSlab("cloudia_tile_slab", "Cloudia Tile Slab", false, JBlockProperties.STONE);
    public static final DeferredBlock<SlabBlock> CLOUDIA_BRICK_SLAB = registerSlab("cloudia_brick_slab", "Cloudia Brick Slab", false, JBlockProperties.STONE);

    public static final DeferredBlock<Block> SENTERIAN_PORTAL_FRAME = registerEndPortalFrameStyleBlock("senterian_portal_frame", "Senterian Portal Frame", SenterianPortalFrameBlock::new, JBlockProperties.STONE);
    public static final DeferredBlock<Block> SENTERIAN_PORTAL = registerEndPortalStyleBlock("senterian_portal", "Senterian Portal", SenterianPortalBlock::new, JBlockProperties.PORTAL);
    public static final DeferredBlock<IronBarsBlock> SENTERIAN_BARS = registerPaneBlock("senterian_bars", "Senterian Bars", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> SENTERIAN_BRICKS = register("senterian_bricks", "Senterian Bricks", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<StairBlock> SENTERIAN_BRICK_STAIRS = registerStairs("senterian_brick_stairs", "Senterian Brick Stairs", SENTERIAN_BRICKS, false, JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> SENTERIAN_CARVED_ROCK = register("senterian_carved_rock", "Senterian Carved Rock", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> SENTERIAN_FLOOR = register("senterian_floor", "Senterian Floor", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> SENTERIAN_ROCK = register("senterian_rock", "Senterian Rock", JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> SENTERIAN_GLASS = register("senterian_glass", "Senterian Glass", JTransparentBlock::new, JBlockProperties.DUNGEON_GLASS);
    public static final DeferredBlock<Block> SENTERIAN_ALTAR = registerModeledBlock("senterian_altar", "Senterian Altar", SenterianAltar::new, JBlockProperties.ALTAR);
    public static final DeferredBlock<JFenceBlock> SENTERIAN_POST = registerFence("senterian_post", "Senterian Post", false, JBlockProperties.DUNGEON_BLOCK);
    public static final DeferredBlock<Block> SENTRY_LOCK = registerRotatableBlock("sentry_lock", "Sentry Lock", LockBlock::new, JBlockProperties.DUNGEON_BLOCK, false);

    public static final DeferredBlock<IronBarsBlock> BREAKABLE_SENTERIAN_BARS = registerPaneBlock("breakable_senterian_bars", "Senterian Bars", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BREAKABLE_SENTERIAN_BRICKS = register("breakable_senterian_bricks", "Senterian Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<StairBlock> BREAKABLE_SENTERIAN_BRICK_STAIRS = registerStairs("breakable_senterian_brick_stairs", "Senterian Brick Stairs", BREAKABLE_SENTERIAN_BRICKS, false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> BREAKABLE_SENTERIAN_CARVED_ROCK = register("breakable_senterian_carved_rock", "Senterian Carved Rock", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BREAKABLE_SENTERIAN_FLOOR = register("breakable_senterian_floor", "Senterian Floor", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BREAKABLE_SENTERIAN_ROCK = register("breakable_senterian_rock", "Senterian Rock", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BREAKABLE_SENTERIAN_GLASS = register("breakable_senterian_glass", "Senterian Glass", JTransparentBlock::new, JBlockProperties.GLASS);
    public static final DeferredBlock<JFenceBlock> BREAKABLE_SENTERIAN_POST = registerFence("breakable_senterian_post", "Senterian Post", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> BREAKABLE_SENTERIAN_GUARDIAN_LAMP = register("breakable_senterian_guardian_lamp", "Senterian Guardian Lamp", JBlockProperties.GLOW_BLOCK);
    public static final DeferredBlock<Block> BREAKABLE_SENTERIAN_LIGHT_LAMP = register("breakable_senterian_light_lamp", "Senterian Light Lamp", JBlockProperties.GLOW_BLOCK);
    public static final DeferredBlock<Block> BREAKABLE_SENTERIAN_MELLOW_LAMP = register("breakable_senterian_mellow_lamp", "Senterian Mellow Lamp", JBlockProperties.GLOW_BLOCK);

    public static final DeferredBlock<Block> JOURNEY_CHEST = registerChestBlock("journey_chest", "Journey Chest", JChestBlock::new, JBlockProperties.CHEST);
    public static final DeferredBlock<Block> NETHER_CHEST = registerChestBlock("nether_chest", "Nether Chest", JChestBlock::new, JBlockProperties.CHEST);
    public static final DeferredBlock<Block> FROZEN_CHEST = registerChestBlock("frozen_chest", "Frozen Chest", JChestBlock::new, JBlockProperties.CHEST);
    public static final DeferredBlock<Block> EUCA_CHEST = registerChestBlock("euca_chest", "Euca Chest", JChestBlock::new, JBlockProperties.CHEST);
    public static final DeferredBlock<Block> BOIL_CHEST = registerChestBlock("boil_chest", "Boiling Chest", JChestBlock::new, JBlockProperties.CHEST);
    public static final DeferredBlock<Block> DEPTHS_CHEST = registerChestBlock("depths_chest", "Depths Chest", JChestBlock::new, JBlockProperties.CHEST);
    public static final DeferredBlock<Block> CORBA_CHEST = registerChestBlock("corba_chest", "Corba Chest", JChestBlock::new, JBlockProperties.CHEST);
    public static final DeferredBlock<Block> TERRANIAN_CHEST = registerChestBlock("terranian_chest", "Terranian Chest", JChestBlock::new, JBlockProperties.CHEST);
    public static final DeferredBlock<Block> CLOUDIA_CHEST = registerChestBlock("cloudia_chest", "Cloudia Chest", JChestBlock::new, JBlockProperties.CHEST);
    public static final DeferredBlock<Block> SENTERIAN_CHEST = registerChestBlock("senterian_chest", "Senterian Chest", JChestBlock::new, JBlockProperties.CHEST);

    public static final DeferredBlock<Block> ROCKITE_SPAWNER = registerModeledBlock("rockite_spawner", "Rockite Spawner", RockiteBlock::new, JBlockProperties.ROCKITE_SPAWNER);

    public static final DeferredBlock<Block> FROZEN_PEDESTAL = registerModeledBlock("frozen_pedestal", "Frozen Pedestal", PedestalBlock::new, JBlockProperties.STONE);
    public static final DeferredBlock<Block> ROYAL_PEDESTAL = registerModeledBlock("royal_pedestal", "Royal Pedestal", PedestalBlock::new, JBlockProperties.STONE);
    public static final DeferredBlock<Block> OKOLOO_PEDESTAL = registerModeledBlock("okoloo_pedestal", "Okoloo Pedestal", OkolooPedestalBlock::new, JBlockProperties.DUNGEON_BLOCK);

    public static final DeferredBlock<Block> BLOOD_ROCK = register("blood_rock", "Blood Rock", JBlockProperties.STONE);
    public static final DeferredBlock<Block> BLOOD_RUNE = register("blood_rune", "Blood Rune", JBlockProperties.STONE);
    public static final DeferredBlock<RotatedPillarBlock> BLOOD_PILLAR = registerPillar("blood_pillar", "Blood Pillar", false, JBlockProperties.STONE);
    public static final DeferredBlock<Block> BLOOD_BRICKS = register("blood_bricks", "Blood Bricks", JBlockProperties.STONE);
    public static final DeferredBlock<Block> CARVED_BLOOD_ROCK = register("carved_blood_rock", "Carved Blood Rock", JBlockProperties.STONE);
    public static final DeferredBlock<Block> OBELISK = register("obelisk", "Obelisk", JBlockProperties.BREAKABLE_DUNGEON_LAMP);
    public static final DeferredBlock<Block> BLOOD_LAMP = register("blood_lamp", "Blood Lamp", JBlockProperties.GLOW_BLOCK);
    public static final DeferredBlock<Block> SUMMONING_TABLE = registerModeledBlock("summoning_table", "Summoning Table", SummoningTableBlock::new, JBlockProperties.GRINDSTONE);

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

    public static final DeferredBlock<Block> ANCIENT_OBELISK = registerModeledBlock("ancient_obelisk", "Ancient Obelisk", ObeliskBlock::new, JBlockProperties.OBELISK);
    public static final DeferredBlock<Block> ANCIENT_SOCKET = registerModeledBlock("ancient_socket", "Ancient Socket", AncientSocketBlock::new, JBlockProperties.ANCIENT_STONE);
    public static final DeferredBlock<Block> ANCIENT_CATALYST = register("ancient_catalyst", "Ancient Catalyst", AncientCatalystBlock::new, JBlockProperties.ANCIENT_STONE);
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
    public static final DeferredBlock<Block> TOMATO_CROP = registerCropBlock("tomato_crop", "Tomato", 8, TomatoCropBlock::new, JBlockProperties.CROP);
    public static final DeferredBlock<Block> FLORO_PEDAL_CROP = registerCropBlock("floro_pedal_crop", "Floro Pedal", 8, FloroCropBlock::new, JBlockProperties.CROP);
    public static final DeferredBlock<Block> REDCURRANT_BUSH = registerGrowingBushBlock("redcurrant_bush", "Redcurrant Bush", RedcurrantBushBlock::new);
    public static final DeferredBlock<Block> BRADBERRY_BUSH = registerGrowingBushBlock("bradberry_bush", "Bradberry Bush", BradberryBushBlock::new);

    //EUCA
    public static final DeferredBlock<Block> ZATPEDAL_CROP = registerCropBlock("zatpedal_crop", "Zatpedal", 8, ZatpedalCropBlock::new, JBlockProperties.CROP);
    public static final DeferredBlock<Block> SPINEBERRY_CROP = registerCropBlock("spineberry_crop", "Spineberry", 8, SpineberryCropBlock::new, JBlockProperties.CROP);

    //DEPTHS
    public static final DeferredBlock<Block> CRAKEBULB_CROP = registerCropBlock("crakebulb_crop", "Crakebulb", 4, CrakebulbCropBlock::new, JBlockProperties.CROP);
    public static final DeferredBlock<Block> CRACKENCANE_CROP = registerCropBlock("crackencane_crop", "Crackencane", 8, CrackencanesCropBlock::new, JBlockProperties.CROP);

    //CORBA
    public static final DeferredBlock<Block> CORVEGGIES_CROP = registerCropBlock("corveggies_crop", "Corveggies", 3, CorveggieCropBlock::new, JBlockProperties.CROP);
    public static final DeferredBlock<Block> GLOWA_CROP = registerCropBlock("glowa_crop", "Glowa", 4, GlowaCropBlock::new, JBlockProperties.CROP);

    //CLOUDIA
    public static final DeferredBlock<Block> AIRROOT_MELON = registerModeledBlock("airroot_melon", "Airroot Melon", Block::new, JBlockProperties.WOOD);
    public static final DeferredBlock<Block> AIRROOT_CROP = registerModeledCropBlock("airroot", "Airroot", 4, AirrootCropBlock::new, JBlockProperties.CROP);


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
        return register(name, translatedName, JBlock::new, props, false);
    }

    public static DeferredBlock<Block> register(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        checkForHoeableBlocks(props, name);
        checkForShovelableBlocks(props, name);
        checkForAxeableBlocks(props, name);
        checkForPickaxeableBlocks(props, name);
        normalBlockName.add(name);
        return register(name, translatedName, block, props, false);
    }

    public static DeferredBlock<Block> register(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props, boolean addName) {
        normalLangName.add(translatedName);
        if(addName)
            normalBlockName.add(name);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerCampfire(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        addAxeableBlocks(name);
        campfireBlockName.add(name);
        campfireLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerTorch(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block) {
        torchBlockName.add(name);
        torchLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerWallTorch(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block) {
        wallTorchBlockName.add(name);
        wallTorchLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerRedstoneLamp(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        redstoneLampBlockName.add(name);
        redstoneLampLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerDoublePlant(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        doublePlantBlockName.add(name);
        doublePlantLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerLilyPad(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        lilyPadBlockName.add(name);
        lilyPadLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerFurnaceBlock(String name, String translatedName) {
        addPickaxeableBlocks(name);
        furnaceBlockName.add(name);
        furnaceLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, JFurnaceBlock::new, JBlockProperties.FURNACE);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerChestBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        addPickaxeableBlocks(name);
        chestBlockName.add(name);
        chestLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerLadder(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        addAxeableBlocks(name);
        ladderLangName.add(translatedName);
        ladderBlockName.add(name);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerAltTexBlock(String name, String translatedName,  Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        checkForShovelableBlocks(props, name);
        checkForAxeableBlocks(props, name);
        checkForPickaxeableBlocks(props, name);
        checkForHoeableBlocks(props, name);
        randomLangName.add(translatedName);
        randomBlockName.add(name);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerAltTexBlock(String name, String translatedName, BlockBehaviour.Properties props) {
        checkForShovelableBlocks(props, name);
        checkForAxeableBlocks(props, name);
        checkForPickaxeableBlocks(props, name);
        checkForHoeableBlocks(props, name);
        randomLangName.add(translatedName);
        randomBlockName.add(name);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, Block::new, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerTintedLeavesBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        checkForShovelableBlocks(props, name);
        checkForAxeableBlocks(props, name);
        checkForPickaxeableBlocks(props, name);
        checkForHoeableBlocks(props, name);
        tintedLeavesLangName.add(translatedName);
        tintedLeavesBlockName.add(name);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerMushroomBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        addAxeableBlocks(name);
        mushroomLangName.add(translatedName);
        mushroomBlockName.add(name);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerEndPortalFrameStyleBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        addPickaxeableBlocks(name);
        basePortalFrameLangName.add(translatedName);
        basePortalFrameBlockName.add(name);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerSlimeStyleBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        addShovelableBlocks(name);
        slimeLangName.add(translatedName);
        slimeBlockName.add(name);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerEndPortalStyleBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        basePortalLangName.add(translatedName);
        basePortalBlockName.add(name);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerTerrainBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        if(!name.contains("stone"))
            addShovelableBlocks(name);
        terrainLangName.add(translatedName);
        terrainBlockName.add(name);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerTerrainBlock(String name, String translatedName, BlockBehaviour.Properties props) {
        checkForAxeableBlocks(props, name);
        checkForPickaxeableBlocks(props, name);
        checkForHoeableBlocks(props, name);
        return registerTerrainBlock(name, translatedName, Block::new, props);
    }

    public static DeferredBlock<Block> registerRotatableBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props, boolean wood) {
        if(wood) {
            addAxeableBlocks(name);
        } else {
            addPickaxeableBlocks(name);
        }
        rotatableBlockName.add(name);
        rotatableLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerVineBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        addHoeableBlocks(name);
        vineBlockName.add(name);
        vineLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerModeledBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        addPickaxeableBlocks(name);
        modelBlockName.add(name);
        modelLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerTrophyBlock(String name, String translatedName) {
        addPickaxeableBlocks(name);
        trophyBlockName.add(name);
        trophyLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, TrophyBlock::new, JBlockProperties.STONE);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerDripstoneBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        addPickaxeableBlocks(name);
        dripstoneBlockName.add(name);
        dripstoneLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerTotemBlock(String name, String translatedName) {
        addPickaxeableBlocks(name);
        totemBlockName.add(name);
        totemLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, TotemBlock::new, JBlockProperties.STONE);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerGrassBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        checkForShovelableBlocks(props, name);
        checkForAxeableBlocks(props, name);
        checkForPickaxeableBlocks(props, name);
        checkForHoeableBlocks(props, name);
        grassBlockName.add(name);
        grassLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerGrassBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block) {
        return registerGrassBlock(name, translatedName, block, JBlockProperties.GRASS);
    }

    public static DeferredBlock<Block> registerOverlayGrassBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block) {
        addShovelableBlocks(name);
        overlayGrassBlockName.add(name);
        overlayGrassLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, JBlockProperties.GRASS);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<JBasePortalBlock> registerPortalBlock(String name, String translatedName, ResourceKey<Level> dimID, Supplier<Block> frame) {
        portalBlockName.add(name);
        portalLangName.add(translatedName);
        DeferredBlock<JBasePortalBlock> block1 = BLOCKS.register(name, () -> new JBasePortalBlock(JBlockProperties.PORTAL.setId(ResourceKey.create(Registries.BLOCK, JITL.rl(name))), dimID, frame));

        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerPathBlock(String name, String translatedName) {
        addShovelableBlocks(name);
        pathBlockName.add(name);
        pathLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, () -> new JDirtPathBlock(JBlockProperties.PATH.setId(ResourceKey.create(Registries.BLOCK, JITL.rl(name)))));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerGrowingBushBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block) {
        bushBlockName.add(name);
        bushLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, JBlockProperties.GROWING_BUSH);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerFarmlandBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        addShovelableBlocks(name);
        farmlandBlockName.add(name);
        farmlandLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerCropBlock(String name, String translatedName, int maxStages, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        cropBlockName.add(name);
        cropLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        if(JITL.DEV_MODE)
            new JBlockCropGenerator().generate(name, maxStages);
        return block1;
    }

    public static DeferredBlock<Block> registerModeledCropBlock(String name, String translatedName, int maxStages, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        cropBlockName.add(name);
        cropLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        if(JITL.DEV_MODE)
            new JBlockModeledCropGenerator().generate(name, maxStages);
        return block1;
    }

    public static DeferredBlock<RotatedPillarBlock> addLogBlock(String name, String translatedName) {
        addAxeableBlocks(name);
        logBlockName.add(name);
        logLangName.add(translatedName);
        DeferredBlock<RotatedPillarBlock> block1 = BLOCKS.registerBlock(name, LogBlock::new, JBlockProperties.WOOD);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType, @NotNull FuelValues fuelValues) {
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
        DeferredBlock<RotatedPillarBlock> block1 = BLOCKS.register(name, () -> new RotatedPillarBlock(props.setId(ResourceKey.create(Registries.BLOCK, JITL.rl(name)))));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType, @NotNull FuelValues fuelValues) {
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
        DeferredBlock<DoorBlock> block1 = BLOCKS.register(name, () -> new DoorBlock(BlockSetType.OAK, p.setId(ResourceKey.create(Registries.BLOCK, JITL.rl(name)))));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType, @NotNull FuelValues fuelValues) {
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
        DeferredBlock<TrapDoorBlock> block1 = BLOCKS.register(name, () -> new TrapDoorBlock(BlockSetType.OAK, p.setId(ResourceKey.create(Registries.BLOCK, JITL.rl(name)))));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType, @NotNull FuelValues fuelValues) {
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
        DeferredBlock<StairBlock> block1 = BLOCKS.register(name, () -> new StairBlock(plank.get().defaultBlockState(), p.setId(ResourceKey.create(Registries.BLOCK, JITL.rl(name)))));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType, @NotNull FuelValues fuelValues) {
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
        DeferredBlock<SlabBlock> block1 = BLOCKS.register(name, () -> new SlabBlock(p.setId(ResourceKey.create(Registries.BLOCK, JITL.rl(name)))));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType, @NotNull FuelValues fuelValues) {
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
        DeferredBlock<ButtonBlock> block1 = BLOCKS.register(name, () -> new ButtonBlock(BlockSetType.OAK, sensitive ? 20 : 30, p.setId(ResourceKey.create(Registries.BLOCK, JITL.rl(name)))) {
            @Override
            protected @NotNull SoundEvent getSound(boolean pIsOn) {
                return SoundEvents.WOODEN_BUTTON_CLICK_ON;
            }
        });
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType, @NotNull FuelValues fuelValues) {
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
        DeferredBlock<PressurePlateBlock> block1 = BLOCKS.register(name, () -> new PressurePlateBlock(BlockSetType.OAK, p.setId(ResourceKey.create(Registries.BLOCK, JITL.rl(name)))));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType, @NotNull FuelValues fuelValues) {
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
        DeferredBlock<FenceGateBlock> block1 = BLOCKS.register(name, () -> new FenceGateBlock(WoodType.OAK, p.setId(ResourceKey.create(Registries.BLOCK, JITL.rl(name)))));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType, @NotNull FuelValues fuelValues) {
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
        DeferredBlock<JFenceBlock> block1 = BLOCKS.register(name, () -> new JFenceBlock(p.setId(ResourceKey.create(Registries.BLOCK, JITL.rl(name)))));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType, @NotNull FuelValues fuelValues) {
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
        DeferredBlock<JWallBlock> block1 = BLOCKS.register(name, () -> new JWallBlock(props.setId(ResourceKey.create(Registries.BLOCK, JITL.rl(name)))));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<IronBarsBlock> registerPaneBlock(String name, String translatedName, BlockBehaviour.Properties props) {
        checkForShovelableBlocks(props, name);
        checkForAxeableBlocks(props, name);
        checkForPickaxeableBlocks(props, name);
        checkForHoeableBlocks(props, name);
        paneBlockName.add(name);
        paneLangName.add(translatedName);
        DeferredBlock<IronBarsBlock> block1 = BLOCKS.register(name, () -> new IronBarsBlock(props.setId(ResourceKey.create(Registries.BLOCK, JITL.rl(name)))));
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerCrossBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        crossBlockName.add(name);
        crossLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<GrowingPlantHeadBlock> registerGrowingPlantHeadBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends GrowingPlantHeadBlock> block, BlockBehaviour.Properties props) {
        crossBlockName.add(name);
        crossLangName.add(translatedName);
        DeferredBlock<GrowingPlantHeadBlock> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerTopBottomBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        addHoeableBlocks(name);//only for terranian leaves
        topBottomBlockName.add(name);
        topBottomLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerTintedCrossBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        tintedCrossBlockName.add(name);
        tintedCrossLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerAttachedCrossBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        attachedCrossBlockName.add(name);
        attachedCrossLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static <T extends Block>DeferredBlock<T> registerFuelBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends T> block, BlockBehaviour.Properties props, int burnTime) {
        addPickaxeableBlocks(name);
        normalLangName.add(translatedName);
        normalBlockName.add(name);
        DeferredBlock<T> block1 = BLOCKS.registerBlock(name, block, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType, @NotNull FuelValues fuelValues) {
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
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, Block::new, props);
        JItems.registerBlockItem(name, () -> new BlockItem(block1.get(), JItems.itemProps(name).useBlockDescriptionPrefix()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType, @NotNull FuelValues fuelValues) {
                return burnTime;
            }
        });
        return block1;
    }
}