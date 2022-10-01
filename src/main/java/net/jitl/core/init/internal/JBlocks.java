package net.jitl.core.init.internal;

import net.jitl.common.block.*;
import net.jitl.common.world.dimension.Dimensions;
import net.jitl.common.world.gen.tree_grower.EucaGoldTreeGrower;
import net.jitl.common.world.gen.tree_grower.EucaGreenTreeGrower;
import net.jitl.core.init.JBlockProperties;
import net.jitl.core.init.JITL;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.function.Supplier;

public class JBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, JITL.MODID);

    public static final ArrayList<String> modelBlockName = new ArrayList<>();
    public static final ArrayList<String> modelLangName = new ArrayList<>();
    public static final ArrayList<String> rotatableBlockName = new ArrayList<>();
    public static final ArrayList<String> rotatableLangName = new ArrayList<>();
    public static final ArrayList<String> furnaceBlockName = new ArrayList<>();
    public static final ArrayList<String> furnaceLangName = new ArrayList<>();
    public static final ArrayList<String> normalBlockName = new ArrayList<>();
    public static final ArrayList<String> normalLangName = new ArrayList<>();
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
    public static final ArrayList<String> crossBlockName = new ArrayList<>();
    public static final ArrayList<String> crossLangName = new ArrayList<>();
    public static final ArrayList<String> grassBlockName = new ArrayList<>();
    public static final ArrayList<String> grassLangName = new ArrayList<>();
    public static final ArrayList<String> portalBlockName = new ArrayList<>();
    public static final ArrayList<String> portalLangName = new ArrayList<>();
    public static final ArrayList<String> chestBlockName = new ArrayList<>();
    public static final ArrayList<String> chestLangName = new ArrayList<>();

    public static final RegistryObject<Block> IRIDIUM_ORE = register("iridium_ore", "Iridium Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> IRIDIUM_BLOCK = registerFuelBlock("iridium_block", "Iridium Block", () -> new Block(JBlockProperties.STONE), 16000);
    public static final RegistryObject<Block> DEEPSLATE_IRIDIUM_ORE = register("deepslate_iridium_ore", "Deepslate Iridium Ore", JBlockProperties.STONE);

    public static final RegistryObject<Block> SAPPHIRE_ORE = register("sapphire_ore", "Sapphire Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> SAPPHIRE_BLOCK = register("sapphire_block", "Sapphire Block", JBlockProperties.STONE);
    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = register("deepslate_sapphire_ore", "Deepslate Sapphire Ore", JBlockProperties.STONE);

    public static final RegistryObject<Block> SHADIUM_ORE = register("shadium_ore", "Shadium Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> SHADIUM_BLOCK = register("shadium_block", "Shadium Block", JBlockProperties.STONE);
    public static final RegistryObject<Block> DEEPSLATE_SHADIUM_ORE = register("deepslate_shadium_ore", "Deepslate Shadium Ore", JBlockProperties.STONE);

    public static final RegistryObject<Block> LUNIUM_ORE = register("lunium_ore", "Lunium Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> LUNIUM_BLOCK = register("lunium_block", "Lunium Block", JBlockProperties.STONE);
    public static final RegistryObject<Block> DEEPSLATE_LUNIUM_ORE = register("deepslate_lunium_ore", "Deepslate Lunium Ore", JBlockProperties.STONE);

    public static final RegistryObject<Block> BLOODCRUST_ORE = register("bloodcrust_ore", "Bloodcrust Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> BLOODCRUST_BLOCK = register("bloodcrust_block", "Bloodcrust Block", JBlockProperties.STONE);

    public static final RegistryObject<RotatedPillarBlock> FIRESTONE_ORE = registerPillar("firestone_ore", "Firestone Ore", false, JBlockProperties.STONE);
    public static final RegistryObject<Block> FIRESTONE_BLOCK = register("firestone_block", "Firestone Block", JBlockProperties.STONE);

    public static final RegistryObject<Block> ENDERILLIUM_ORE = register("enderillium_ore", "Enderillium Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> ENDERILLIUM_BLOCK = register("enderillium_block", "Enderillium Block", JBlockProperties.STONE);

    public static final RegistryObject<Block> ASHUAL_ORE = register("ashual_ore", "Ashual Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> ASHUAL_BLOCK = register("ashual_block", "Ashual Block", JBlockProperties.STONE);

    public static final RegistryObject<Block> BLAZIUM_ORE = register("blazium_ore", "Blazium Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> BLAZIUM_BLOCK = register("blazium_block", "Blazium Block", JBlockProperties.STONE);

    public static final RegistryObject<Block> CELESTIUM_ORE = register("celestium_ore", "Celestium Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> CELESTIUM_BLOCK = register("celestium_block", "Celestium Block", JBlockProperties.STONE);

    public static final RegistryObject<Block> MEKYUM_ORE = register("mekyum_ore", "Mekyum Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> MEKYUM_BLOCK = register("mekyum_block", "Mekyum Block", JBlockProperties.STONE);

    public static final RegistryObject<Block> STORON_ORE = register("storon_ore", "Storon Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> STORON_BLOCK = register("storon_block", "Storon Block", JBlockProperties.STONE);

    public static final RegistryObject<Block> KORITE_ORE = register("korite_ore", "Korite Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> KORITE_BLOCK = register("korite_block", "Korite Block", JBlockProperties.STONE);

    public static final RegistryObject<Block> RIMESTONE_ORE = register("rimestone_ore", "Rimestone Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> RIMESTONE_BLOCK = register("rimestone_block", "Rimestone Block", JBlockProperties.STONE);

    public static final RegistryObject<Block> PERIDOT_ORE = register("peridot_ore", "Peridot Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> PERIDOT_BLOCK = register("peridot_block", "Peridot Block", JBlockProperties.STONE);

    public static final RegistryObject<Block> RARE_GEM_BLOCK = register("rare_gem_block", "Rare Gem Block", JBlockProperties.STONE);
    public static final RegistryObject<Block> COMMON_GEM_BLOCK = register("common_gem_block", "Common Gem Block", JBlockProperties.STONE);
    public static final RegistryObject<Block> YELLOW_GEM_BLOCK = register("yellow_gem_block", "Yellow Gem Block", JBlockProperties.STONE);
    public static final RegistryObject<Block> PURPLE_GEM_BLOCK = register("purple_gem_block", "Purple Gem Block", JBlockProperties.STONE);
    public static final RegistryObject<Block> GREEN_GEM_BLOCK = register("green_gem_block", "Green Gem Block", JBlockProperties.STONE);
    public static final RegistryObject<Block> BLUE_GEM_BLOCK = register("blue_gem_block", "Blue Gem Block", JBlockProperties.STONE);

    public static final RegistryObject<Block> WARPED_QUARTZ_ORE = register("warped_quartz_ore", "Warped Quartz Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> WARPED_QUARTZ_BLOCK = register("warped_quartz_block", "Warped Quartz Block", JBlockProperties.STONE);
    public static final RegistryObject<RotatedPillarBlock> CHISELED_WARPED_QUARTZ_BLOCK = registerPillar("chiseled_warped_quartz_block", "Chiseled Warped Quartz Block", false, JBlockProperties.STONE);
    public static final RegistryObject<SlabBlock> WARPED_QUARTZ_SLAB = registerSlab("warped_quartz_slab", "Warped Quartz Slab", false, JBlockProperties.STONE);
    public static final RegistryObject<Block> WARPED_QUARTZ_BRICKS = register("warped_quartz_bricks", "Warped Quartz Bricks", JBlockProperties.STONE);
    public static final RegistryObject<StairBlock> WARPED_QUARTZ_STAIRS = registerStairs("warped_quartz_stairs", "Warped Quartz Stairs", WARPED_QUARTZ_BLOCK, false, JBlockProperties.STONE);
    public static final RegistryObject<RotatedPillarBlock> WARPED_QUARTZ_PILLAR = registerPillar("warped_quartz_pillar", "Warped Quartz Pillar", false, JBlockProperties.STONE);
    public static final RegistryObject<Block> SMOOTH_WARPED_QUARTZ = register("smooth_warped_quartz_block", "Smooth Warped Quartz Block", JBlockProperties.STONE);
    public static final RegistryObject<SlabBlock> SMOOTH_WARPED_QUARTZ_SLAB = registerSlab("smooth_warped_quartz_slab", "Smooth Warped Quartz Slab", false, JBlockProperties.STONE);
    public static final RegistryObject<StairBlock> SMOOTH_WARPED_QUARTZ_STAIRS = registerStairs("smooth_warped_quartz_stairs", "Smooth Warped Quartz Stairs", SMOOTH_WARPED_QUARTZ, false, JBlockProperties.STONE);

    public static final RegistryObject<Block> CRIMSON_QUARTZ_ORE = register("crimson_quartz_ore", "Crimson Quartz Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> CRIMSON_QUARTZ_BLOCK = register("crimson_quartz_block", "Crimson Quartz Block", JBlockProperties.STONE);
    public static final RegistryObject<RotatedPillarBlock> CHISELED_CRIMSON_QUARTZ_BLOCK = registerPillar("chiseled_crimson_quartz_block", "Chiseled Crimson Quartz Block", false, JBlockProperties.STONE);
    public static final RegistryObject<SlabBlock> CRIMSON_QUARTZ_SLAB = registerSlab("crimson_quartz_slab", "Crimson Quartz Slab", false, JBlockProperties.STONE);
    public static final RegistryObject<Block> CRIMSON_QUARTZ_BRICKS = register("crimson_quartz_bricks", "Crimson Quartz Bricks", JBlockProperties.STONE);
    public static final RegistryObject<StairBlock> CRIMSON_QUARTZ_STAIRS = registerStairs("crimson_quartz_stairs", "Crimson Quartz Stairs", CRIMSON_QUARTZ_BLOCK, false, JBlockProperties.STONE);
    public static final RegistryObject<RotatedPillarBlock> CRIMSON_QUARTZ_PILLAR = registerPillar("crimson_quartz_pillar", "Crimson Quartz Pillar", false, JBlockProperties.STONE);
    public static final RegistryObject<Block> SMOOTH_CRIMSON_QUARTZ = register("smooth_crimson_quartz_block", "Smooth Crimson Quartz Block", JBlockProperties.STONE);
    public static final RegistryObject<SlabBlock> SMOOTH_CRIMSON_QUARTZ_SLAB = registerSlab("smooth_crimson_quartz_slab", "Smooth Crimson Quartz Slab", false, JBlockProperties.STONE);
    public static final RegistryObject<StairBlock> SMOOTH_CRIMSON_QUARTZ_STAIRS = registerStairs("smooth_crimson_quartz_stairs", "Smooth Crimson Quartz Stairs", SMOOTH_CRIMSON_QUARTZ, false, JBlockProperties.STONE);

    public static final RegistryObject<Block> NETHIC_GEM_BLOCK = register("nethic_gem_block", "Nethic Gemstone Block", JBlockProperties.STONE);

    public static final RegistryObject<Block> DUNGEON_BRICKS = register("dungeon_bricks", "Dungeon Bricks", JBlockProperties.DUNGEON_BLOCK);
    public static final RegistryObject<StairBlock> DUNGEON_BRICK_STAIRS = registerStairs("dungeon_brick_stairs", "Dungeon Brick Stairs", DUNGEON_BRICKS, false, JBlockProperties.DUNGEON_BLOCK);
    public static final RegistryObject<JFenceBlock> DUNGEON_BRICK_FENCE = registerFence("dungeon_brick_fence", "Dungeon Brick Fence", false, JBlockProperties.DUNGEON_BLOCK);
    public static final RegistryObject<Block> DUNGEON_FLOOR = register("dungeon_floor", "Dungeon Floor", JBlockProperties.DUNGEON_BLOCK);
    public static final RegistryObject<Block> GILDED_DUNGEON_BRICKS = register("gilded_dungeon_bricks", "Gilded Dungeon Bricks", JBlockProperties.DUNGEON_BLOCK);
    public static final RegistryObject<StairBlock> GILDED_DUNGEON_BRICK_STAIRS = registerStairs("gilded_dungeon_brick_stairs", "Gilded Dungeon Brick Stairs", GILDED_DUNGEON_BRICKS, false, JBlockProperties.DUNGEON_BLOCK);
    public static final RegistryObject<JFenceBlock> GILDED_DUNGEON_BRICK_FENCE = registerFence("gilded_dungeon_brick_fence", "Gilded Dungeon Brick Fence", false, JBlockProperties.DUNGEON_BLOCK);
    public static final RegistryObject<Block> DUNGEON_LAMP = register("dungeon_lamp", "Dungeon Lamp", JBlockProperties.DUNGEON_LAMP);
    public static final RegistryObject<StairBlock> DUNGEON_LAMP_STAIRS = registerStairs("dungeon_lamp_stairs", "Dungeon Lamp Stairs", DUNGEON_LAMP, false, JBlockProperties.DUNGEON_LAMP);
    public static final RegistryObject<JFenceBlock> DUNGEON_LAMP_FENCE = registerFence("dungeon_lamp_fence", "Dungeon Lamp Fence", false, JBlockProperties.DUNGEON_LAMP);

    public static final RegistryObject<Block> EUCA_PORTAL_FRAME = register("euca_portal_frame", "Euca Portal Frame", JBlockProperties.STONE);
    public static final RegistryObject<JBasePortalBlock> EUCA_PORTAL = registerPortalBlock("euca_portal", "Euca Portal", () -> new JBasePortalBlock(Dimensions.EUCA, EUCA_PORTAL_FRAME));
    public static final RegistryObject<Block> GOLDITE_DIRT = register("goldite_dirt", "Goldite Soil", JBlockProperties.DIRT);
    public static final RegistryObject<Block> GOLDITE_STONE = register("goldite_stone", "Goldite Stone", JBlockProperties.STONE);
    public static final RegistryObject<Block> EUCA_BRICK = register("euca_brick", "Euca Bricks", JBlockProperties.STONE);
    public static final RegistryObject<Block> EUCA_DUNGEON_BRICKS = register("euca_dungeon_brick", "Euca Dungeon Bricks", JBlockProperties.DUNGEON_BLOCK);
    public static final RegistryObject<Block> EUCA_DUNGEON_TILE = register("euca_dungeon_tile", "Euca Dungeon Tile", JBlockProperties.DUNGEON_BLOCK);
    public static final RegistryObject<Block> EUCA_RUNIC_BRICKS = register("euca_runic_bricks", "Euca Runic Bricks", JBlockProperties.STONE);
    public static final RegistryObject<Block> EUCA_RUNIC_LAMP = register("euca_runic_lamp", "Euca Runic Lamp", JBlockProperties.BREAKABLE_DUNGEON_LAMP);
    public static final RegistryObject<Block> EUCA_SQUARE_RUNIC_BRICKS = register("euca_square_runic_bricks", "Euca Square Runic Bricks", JBlockProperties.STONE);
    public static final RegistryObject<Block> EUCA_SQUARE_BRICKS = register("euca_square_bricks", "Euca Square Bricks", JBlockProperties.STONE);
    public static final RegistryObject<Block> EUCA_TILE = register("euca_tile", "Euca Tile", JBlockProperties.STONE);
    public static final RegistryObject<Block> EUCA_TALL_GRASS = registerCrossBlock("euca_tall_grass", "Euca Tall Grass", () -> new TallGrassBlock(JBlockProperties.TALL_GRASS));
    public static final RegistryObject<Block> EUCA_TALL_FLOWERS = registerCrossBlock("euca_tall_flowers", "Euca Tall Flowers", () -> new TallGrassBlock(JBlockProperties.TALL_GRASS));
    public static final RegistryObject<Block> EUCA_SILVER_FLOWER = registerCrossBlock("euca_silver_gold_flower", "Euca Silver Flower", () -> new TallGrassBlock(JBlockProperties.TALL_GRASS));
    public static final RegistryObject<Block> EUCA_BLUE_FLOWER = registerCrossBlock("euca_blue_flower", "Euca Blue Flower", () -> new TallGrassBlock(JBlockProperties.TALL_GRASS));

    public static final RegistryObject<Block> EUCA_GOLD_GRASS = registerGrassBlock("euca_gold_grass", "Euca Gold Grass", () -> new JGrassBlock(GOLDITE_DIRT));
    public static final RegistryObject<Block> GOLDITE_GRASS = registerGrassBlock("goldite_grass", "Goldite Grass", () -> new JGrassBlock(GOLDITE_DIRT));

    public static final RegistryObject<RotatedPillarBlock> EUCA_GOLD_LOG = registerPillar("euca_gold_log", "Euca Gold Log", true, JBlockProperties.WOOD);
    public static final RegistryObject<Block> EUCA_GOLD_LEAVES = register("euca_gold_leaves", "Euca Gold Leaves", JBlockProperties.LEAVES);//JLeavesBlock::new);
    public static final RegistryObject<Block> EUCA_GOLD_SAPLING = registerCrossBlock("euca_gold_sapling", "Euca Gold Sapling", () -> new JSaplingBlock(new EucaGoldTreeGrower()));
    public static final RegistryObject<Block> EUCA_GOLD_PLANKS = registerFuelBlock("euca_gold_planks", "Euca Gold Planks", JBlockProperties.WOOD, 300);
    public static final RegistryObject<DoorBlock> EUCA_GOLD_DOOR = registerDoor("euca_gold_door", "Euca Gold Door", true, JBlockProperties.DOOR);
    public static final RegistryObject<TrapDoorBlock> EUCA_GOLD_TRAP_DOOR = registerTrapDoor("euca_gold_trap_door", "Euca Gold Trap Door", true, JBlockProperties.DOOR);
    public static final RegistryObject<StairBlock> EUCA_GOLD_STAIRS = registerStairs("euca_gold_stairs", "Euca Gold Plank Stairs", EUCA_GOLD_PLANKS, true, JBlockProperties.WOOD);
    public static final RegistryObject<SlabBlock> EUCA_GOLD_SLAB = registerSlab("euca_gold_slab", "Euca Gold Plank Slab", true, JBlockProperties.BUTTON);
    public static final RegistryObject<ButtonBlock> EUCA_GOLD_BUTTON = registerButton("euca_gold_button", "Euca Gold Button", false, true, JBlockProperties.WOOD);
    public static final RegistryObject<PressurePlateBlock> EUCA_GOLD_PRESSURE_PLATE = registerPressurePlate("euca_gold_pressure_plate", "Euca Gold Pressure Plate", PressurePlateBlock.Sensitivity.EVERYTHING, true, JBlockProperties.WOOD);
    public static final RegistryObject<FenceGateBlock> EUCA_GOLD_FENCE_GATE = registerFenceGate("euca_gold_fence_gate", "Euca Gold Fence Gate", true, JBlockProperties.WOOD);
    public static final RegistryObject<JFenceBlock> EUCA_GOLD_FENCE = registerFence("euca_gold_fence", "Euca Gold Fence", true, JBlockProperties.WOOD);

    public static final RegistryObject<RotatedPillarBlock> EUCA_BROWN_LOG = registerPillar("euca_brown_log", "Euca Brown Log", true, JBlockProperties.WOOD);
    public static final RegistryObject<Block> EUCA_GREEN_LEAVES = register("euca_green_leaves", "Euca Green Leaves", JBlockProperties.LEAVES);// JLeavesBlock::new);
    public static final RegistryObject<Block> EUCA_GREEN_SAPLING = registerCrossBlock("euca_green_sapling", "Euca Green Sapling", () -> new JSaplingBlock(new EucaGreenTreeGrower()));
    public static final RegistryObject<Block> EUCA_BROWN_PLANKS = registerFuelBlock("euca_brown_planks", "Euca Brown Planks", JBlockProperties.WOOD, 300);
    public static final RegistryObject<DoorBlock> EUCA_BROWN_DOOR = registerDoor("euca_brown_door", "Euca Brown Door", true, JBlockProperties.DOOR);
    public static final RegistryObject<TrapDoorBlock> EUCA_BROWN_TRAP_DOOR = registerTrapDoor("euca_brown_trap_door", "Euca Brown Trap Door", true, JBlockProperties.DOOR);
    public static final RegistryObject<StairBlock> EUCA_BROWN_STAIRS = registerStairs("euca_brown_stairs", "Euca Brown Plank Stairs", EUCA_BROWN_PLANKS, true, JBlockProperties.WOOD);
    public static final RegistryObject<SlabBlock> EUCA_BROWN_SLAB = registerSlab("euca_brown_slab", "Euca Brown Plank Slab", true, JBlockProperties.WOOD);
    public static final RegistryObject<ButtonBlock> EUCA_BROWN_BUTTON = registerButton("euca_brown_button", "Euca Brown Button", true, true, JBlockProperties.BUTTON);
    public static final RegistryObject<PressurePlateBlock> EUCA_BROWN_PRESSURE_PLATE = registerPressurePlate("euca_brown_pressure_plate", "Euca Brown Pressure Plate", PressurePlateBlock.Sensitivity.EVERYTHING, true, JBlockProperties.WOOD);
    public static final RegistryObject<FenceGateBlock> EUCA_BROWN_FENCE_GATE = registerFenceGate("euca_brown_fence_gate", "Euca Brown Fence Gate", true, JBlockProperties.WOOD);
    public static final RegistryObject<JFenceBlock> EUCA_BROWN_FENCE = registerFence("euca_brown_fence", "Euca Brown Fence", true, JBlockProperties.WOOD);

    public static final RegistryObject<Block> FROZEN_PORTAL_FRAME = register("frozen_portal_frame", "Frozen Portal Frame", JBlockProperties.STONE);
    public static final RegistryObject<JBasePortalBlock> FROZEN_PORTAL = registerPortalBlock("frozen_portal", "Frozen Portal", () -> new JBasePortalBlock(Dimensions.FROZEN_LANDS, FROZEN_PORTAL_FRAME));
    public static final RegistryObject<Block> FUMICE = register("fumice", "Fumice", JBlockProperties.DIRT);
    public static final RegistryObject<Block> GRASSY_PERMAFROST = registerGrassBlock("grassy_permafrost", "Grassy Permafrost", () -> new Block(JBlockProperties.GRASS));

    public static final RegistryObject<Block> PERMAFROST = register("permafrost", "Permafrost", JBlockProperties.STONE);
    public static final RegistryObject<Block> CRUMBLED_PERMAFROST = register("crumbled_permafrost", "Crumbled Permafrost", JBlockProperties.STONE);
    public static final RegistryObject<RotatedPillarBlock> FROZEN_LOG = registerPillar("frozen_log", "Frozen Log", true, JBlockProperties.WOOD);
    public static final RegistryObject<Block> FROZEN_LEAVES = register("frozen_leaves", "Frozen Leaves", JBlockProperties.LEAVES);
    public static final RegistryObject<Block> FROSTWOOD_SAPLING = registerCrossBlock("frostwood_sapling", "Frostwood Sapling", () -> new JSaplingBlock(new EucaGreenTreeGrower()));
    public static final RegistryObject<Block> FROZEN_PLANKS = registerFuelBlock("frozen_planks", "Frostwood Planks", JBlockProperties.WOOD, 300);
    public static final RegistryObject<DoorBlock> FROZEN_DOOR = registerDoor("frozen_door", "Frostwood Door", true, JBlockProperties.DOOR);
    public static final RegistryObject<TrapDoorBlock> FROZEN_TRAP_DOOR = registerTrapDoor("frozen_trap_door", "Frostwood Trap Door", true, JBlockProperties.DOOR);
    public static final RegistryObject<StairBlock> FROZEN_STAIRS = registerStairs("frozen_stairs", "Frostwood Plank Stairs", EUCA_BROWN_PLANKS, true, JBlockProperties.WOOD);
    public static final RegistryObject<SlabBlock> FROZEN_SLAB = registerSlab("frozen_slab", "Frostwood Plank Slab", true, JBlockProperties.WOOD);
    public static final RegistryObject<ButtonBlock> FROZEN_BUTTON = registerButton("frozen_button", "Frostwood Button", true, true, JBlockProperties.BUTTON);
    public static final RegistryObject<PressurePlateBlock> FROZEN_PRESSURE_PLATE = registerPressurePlate("frozen_pressure_plate", "Frostwood Pressure Plate", PressurePlateBlock.Sensitivity.EVERYTHING, true, JBlockProperties.WOOD);
    public static final RegistryObject<FenceGateBlock> FROZEN_FENCE_GATE = registerFenceGate("frozen_fence_gate", "Frostwood Fence Gate", true, JBlockProperties.WOOD);
    public static final RegistryObject<JFenceBlock> FROZEN_FENCE = registerFence("frozen_fence", "Frostwood Fence", true, JBlockProperties.WOOD);
    public static final RegistryObject<Block> FROST_CRYSTAL_LARGE = registerCrossBlock("frost_crystal_large", "Frost Crystal", () -> new TallGrassBlock(JBlockProperties.TALL_GRASS));
    public static final RegistryObject<Block> FROST_CRYSTAL_MEDIUM = registerCrossBlock("frost_crystal_medium", "Frost Crystal", () -> new TallGrassBlock(JBlockProperties.TALL_GRASS));
    public static final RegistryObject<Block> FROST_CRYSTAL_SMALL = registerCrossBlock("frost_crystal_small", "Frost Crystal", () -> new TallGrassBlock(JBlockProperties.TALL_GRASS));
    public static final RegistryObject<Block> FROST_CRYSTAL_TINY = registerCrossBlock("frost_crystal_tiny", "Frost Crystal", () -> new TallGrassBlock(JBlockProperties.TALL_GRASS));
    public static final RegistryObject<Block> FROSTBERRY_THORN = registerCrossBlock("frostberry_thorn", "Frostberry Thorn", () -> new TallGrassBlock(JBlockProperties.TALL_GRASS));
    public static final RegistryObject<Block> FROZEN_BLOOM = registerCrossBlock("frozen_bloom", "Frozen Bloom", () -> new TallGrassBlock(JBlockProperties.TALL_GRASS));
    public static final RegistryObject<Block> FROZEN_FLOWER = registerCrossBlock("frozen_flower", "Frozen Flower", () -> new TallGrassBlock(JBlockProperties.TALL_GRASS));
    public static final RegistryObject<Block> FROSTY_ICE = register("frosty_ice", "Frosty Ice", JBlockProperties.ICE);
    public static final RegistryObject<Block> FROST_GEM_BLOCK = register("frost_gem_block", "Frost Gem Block", JBlockProperties.STONE);

    public static final RegistryObject<Block> BOIL_PORTAL_FRAME = register("boil_portal_frame", "Boiling Point Portal Frame", JBlockProperties.STONE);
    public static final RegistryObject<JBasePortalBlock> BOIL_PORTAL = registerPortalBlock("boil_portal", "Boiling Point Portal", () -> new JBasePortalBlock(Dimensions.BOIL, BOIL_PORTAL_FRAME));
    public static final RegistryObject<Block> SULPHUR_CRYSTAL = registerCrossBlock("sulphur_crystal", "Sulphur Crystal", () -> new TallGrassBlock(JBlockProperties.TALL_GRASS));

    public static final RegistryObject<Block> GRINDSTONE = registerModeledBlock("grindstone", "Grindstone", JGrindstoneBlock::new);

    public static final RegistryObject<Block> JOURNEY_CHEST = registerChestBlock("journey_chest", "Journey Chest", JChestBlock::new);
    public static final RegistryObject<Block> NETHER_CHEST = registerChestBlock("nether_chest", "Nether Chest", JChestBlock::new);
    public static final RegistryObject<Block> FROZEN_CHEST = registerChestBlock("frozen_chest", "Frozen Chest", JChestBlock::new);
    public static final RegistryObject<Block> EUCA_CHEST = registerChestBlock("euca_chest", "Euca Chest", JChestBlock::new);
    public static final RegistryObject<Block> BOIL_CHEST = registerChestBlock("boil_chest", "Boiling Chest", JChestBlock::new);

    public static final RegistryObject<Block> BOIL_LOCK = registerRotatableBlock("boil_lock", "Boiling Lock", LockBlock::new);

    public static final RegistryObject<RotatedPillarBlock> STONE_PLILLAR = registerPillar("stone_pillar", "Stone Pillar", false, JBlockProperties.STONE);

    public static RegistryObject<Block> register(String name, String translatedName, BlockBehaviour.Properties props, CreativeModeTab tab) {
        return register(name, translatedName, () -> new Block(props), tab);
    }

    public static RegistryObject<Block> register(String name, String translatedName, BlockBehaviour.Properties props) {
        normalBlockName.add(name);
        return register(name, translatedName, () -> new Block(props), JTabs.BLOCKS);
    }

    public static RegistryObject<Block> register(String name, String translatedName, Supplier<Block> block) {
        normalLangName.add(translatedName);
        RegistryObject<Block> block1 = BLOCKS.register(name, block);
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<Block> register(String name, String translatedName, Supplier<Block> block, CreativeModeTab tab) {
        normalLangName.add(translatedName);
        RegistryObject<Block> block1 = BLOCKS.register(name, block);
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(tab)));
        return block1;
    }

    public static RegistryObject<Block> registerFurnaceBlock(String name, String translatedName, Supplier<Block> block) {
        furnaceBlockName.add(name);
        furnaceLangName.add(translatedName);
        RegistryObject<Block> block1 = BLOCKS.register(name, block);
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<Block> registerChestBlock(String name, String translatedName, Supplier<Block> block) {
        chestBlockName.add(name);
        chestLangName.add(translatedName);
        RegistryObject<Block> block1 = BLOCKS.register(name, block);
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<Block> registerRotatableBlock(String name, String translatedName, Supplier<Block> block) {
        rotatableBlockName.add(name);
        rotatableLangName.add(translatedName);
        RegistryObject<Block> block1 = BLOCKS.register(name, block);
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<Block> registerModeledBlock(String name, String translatedName, Supplier<Block> block) {
        modelBlockName.add(name);
        modelLangName.add(translatedName);
        RegistryObject<Block> block1 = BLOCKS.register(name, block);
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<Block> registerGrassBlock(String name, String translatedName, Supplier<Block> block) {
        grassBlockName.add(name);
        grassLangName.add(translatedName);
        RegistryObject<Block> block1 = BLOCKS.register(name, block);
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<JBasePortalBlock> registerPortalBlock(String name, String translatedName, Supplier<JBasePortalBlock> block) {
        portalBlockName.add(name);
        portalLangName.add(translatedName);
        RegistryObject<JBasePortalBlock> block1 = BLOCKS.register(name, block);
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<RotatedPillarBlock> registerPillar(String name, String translatedName, boolean wood, BlockBehaviour.Properties props) {
        logBlockName.add(name);
        logLangName.add(translatedName);
        RegistryObject<RotatedPillarBlock> block1 = BLOCKS.register(name, () -> new RotatedPillarBlock(props));
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)) {
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return wood ? 300 : -1;
            }
        });
        return block1;
    }

    public static RegistryObject<DoorBlock> registerDoor(String name, String translatedName, boolean wood, BlockBehaviour.Properties p) {
        doorBlockName.add(name);
        doorLangName.add(translatedName);
        RegistryObject<DoorBlock> block1 = BLOCKS.register(name, () -> new DoorBlock(p));
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)) {
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return wood ? 300 : -1;
            }
        });
        return block1;
    }

    public static RegistryObject<TrapDoorBlock> registerTrapDoor(String name, String translatedName, boolean wood, BlockBehaviour.Properties p) {
        trapDoorBlockName.add(name);
        trapDoorLangName.add(translatedName);
        RegistryObject<TrapDoorBlock> block1 = BLOCKS.register(name, () -> new TrapDoorBlock(p));
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)) {
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return wood ? 300 : -1;
            }
        });
        return block1;
    }

    public static RegistryObject<StairBlock> registerStairs(String name, String translatedName, RegistryObject<Block> plank, boolean wood, BlockBehaviour.Properties p) {
        stairsBlockName.add(name);
        stairsLangName.add(translatedName);
        RegistryObject<StairBlock> block1 = BLOCKS.register(name, () -> new StairBlock(() -> plank.get().defaultBlockState(), p));
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)) {
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return wood ? 300 : -1;
            }
        });
        return block1;
    }

    public static RegistryObject<SlabBlock> registerSlab(String name, String translatedName, boolean wood, BlockBehaviour.Properties p) {
        slabBlockName.add(name);
        slabLangName.add(translatedName);
        RegistryObject<SlabBlock> block1 = BLOCKS.register(name, () -> new SlabBlock(p));
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)) {
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return wood ? 300 : -1;
            }
        });
        return block1;
    }

    public static RegistryObject<ButtonBlock> registerButton(String name, String translatedName, boolean sensitive, boolean wood, BlockBehaviour.Properties p) {
        buttonBlockName.add(name);
        buttonLangName.add(translatedName);
        RegistryObject<ButtonBlock> block1 = BLOCKS.register(name, () -> new ButtonBlock(sensitive, p) {
            @Override
            protected @NotNull SoundEvent getSound(boolean pIsOn) {
                return SoundEvents.WOODEN_BUTTON_CLICK_ON;
            }
        });
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)) {
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return wood ? 300 : -1;
            }
        });
        return block1;
    }

    public static RegistryObject<PressurePlateBlock> registerPressurePlate(String name, String translatedName, PressurePlateBlock.Sensitivity s, boolean wood, BlockBehaviour.Properties p) {
        pressurePlateBlockName.add(name);
        pressurePlateLangName.add(translatedName);
        RegistryObject<PressurePlateBlock> block1 = BLOCKS.register(name, () -> new PressurePlateBlock(s, p));
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)) {
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return wood ? 300 : -1;
            }
        });
        return block1;
    }

    public static RegistryObject<FenceGateBlock> registerFenceGate(String name, String translatedName, boolean wood, BlockBehaviour.Properties p) {
        gateBlockName.add(name);
        gateLangName.add(translatedName);
        RegistryObject<FenceGateBlock> block1 = BLOCKS.register(name, () -> new FenceGateBlock(p));
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)) {
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return wood ? 300 : -1;
            }
        });
        return block1;
    }

    public static RegistryObject<JFenceBlock> registerFence(String name, String translatedName, boolean wood, BlockBehaviour.Properties p) {
        fenceBlockName.add(name);
        fenceLangName.add(translatedName);
        RegistryObject<JFenceBlock> block1 = BLOCKS.register(name, () -> new JFenceBlock(p));
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)) {
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return wood ? 300 : -1;
            }
        });
        return block1;
    }

    public static RegistryObject<Block> registerCrossBlock(String name, String translatedName, Supplier<Block> block) {
        crossBlockName.add(name);
        crossLangName.add(translatedName);
        RegistryObject<Block> block1 = BLOCKS.register(name, block);
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static <T extends Block>RegistryObject<T> registerFuelBlock(String name, String translatedName, Supplier<T> block, int burnTime) {
        normalLangName.add(translatedName);
        normalBlockName.add(name);
        RegistryObject<T> block1 = BLOCKS.register(name, block);
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)) {
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return burnTime;
            }
        });
        return block1;
    }

    public static RegistryObject<Block> registerFuelBlock(String name, String translatedName, BlockBehaviour.Properties p, int burnTime) {
        normalLangName.add(translatedName);
        normalBlockName.add(name);
        RegistryObject<Block> block1 = BLOCKS.register(name, () -> new Block(p));
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)) {
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return burnTime;
            }
        });
        return block1;
    }
}