package net.jitl.common.world.gen;

import com.google.common.collect.ImmutableList;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.*;
import net.minecraft.data.worldgen.features.NetherFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class JPlacedFeatures {

    public  static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, JITL.MODID);

    public static final RegistryObject<PlacedFeature> IRIDIUM_ORE = PLACED_FEATURES.register("iridum_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.IRIDIUM_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(48)))));

    public static final RegistryObject<PlacedFeature> SAPPHIRE_ORE = PLACED_FEATURES.register("sapphire_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.SAPPHIRE_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(48)))));

    public static final RegistryObject<PlacedFeature> LUNIUM_ORE = PLACED_FEATURES.register("lunium_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.LUNIUM_ORE.getHolder().get(),
                    commonOrePlacement(8,
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(48)))));

    public static final RegistryObject<PlacedFeature> SHADIUM_ORE = PLACED_FEATURES.register("shadium_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.SHADIUM_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(48)))));

    public static final RegistryObject<PlacedFeature> WARPED_QUARTZ_ORE = PLACED_FEATURES.register("warped_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.WARPED_QUARTZ_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()))));

    public static final RegistryObject<PlacedFeature> CRIMSON_QUARTZ_ORE = PLACED_FEATURES.register("crimson_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.CRIMSON_QUARTZ_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()))));

    public static final RegistryObject<PlacedFeature> BLOODCRUST_ORE = PLACED_FEATURES.register("blood_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.BLOODCRUST_ORE.getHolder().get(),
                    commonOrePlacement(4,
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()))));

    public static final RegistryObject<PlacedFeature> ENDERILLIUM_ORE = PLACED_FEATURES.register("enderillium_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.ENDERILLIUM_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(100)))));

    public static final RegistryObject<PlacedFeature> STORON_ORE = PLACED_FEATURES.register("storon_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.STORON_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128)))));

    public static final RegistryObject<PlacedFeature> MEKYUM_ORE = PLACED_FEATURES.register("mekyum_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.MEKYUM_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128)))));

    public static final RegistryObject<PlacedFeature> KORITE_ORE = PLACED_FEATURES.register("korite_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.KORITE_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128)))));

    public static final RegistryObject<PlacedFeature> CELESTIUM_ORE = PLACED_FEATURES.register("celestium_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.CELESTIUM_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128)))));

    public static final RegistryObject<PlacedFeature> FLAIURM_ORE = PLACED_FEATURES.register("flairium_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.FLAIRIUM_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128)))));

    public static final RegistryObject<PlacedFeature> DES_ORE = PLACED_FEATURES.register("des_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.DES_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128)))));


    public static final RegistryObject<PlacedFeature> DEPTHS_LAMP_FLOOR = PLACED_FEATURES.register("depths_lamp_floor_placed", () ->
            new PlacedFeature(JConfiguredFeatures.DEPTHS_LAMP_FLOOR.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(64)))));

    public static final RegistryObject<PlacedFeature> DEPTHS_ROOF_LAMP = PLACED_FEATURES.register("depths_lamp_roof_placed", () ->
            new PlacedFeature(JConfiguredFeatures.DEPTHS_LAMP_ROOF.getHolder().get()
                    , patch(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(256)))));

    public static final RegistryObject<PlacedFeature> EUCA_BOULDER = PLACED_FEATURES.register("euca_boulder_placed", () ->
            new PlacedFeature(JConfiguredFeatures.EUCA_BOULDER.getHolder().get()
                            , patch(2, 5, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)));

    public static final RegistryObject<PlacedFeature> GOLD_VEG = PLACED_FEATURES.register("gold_veg_placed", () ->
                    new PlacedFeature(JConfiguredFeatures.GOLD_VEG.getHolder().get()
                            , patch(6, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)));

    public static final RegistryObject<PlacedFeature> GOLDITE_VEG = PLACED_FEATURES.register("goldite_veg_placed", () ->
            new PlacedFeature(JConfiguredFeatures.GOLDITE_VEG.getHolder().get()
                    , patch(4, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)));

    public static final RegistryObject<PlacedFeature> EUCA_GREEN_GOLD_TREE = PLACED_FEATURES.register("euca_gold_tree_placed", () ->
                    new PlacedFeature(JConfiguredFeatures.EUCA_GOLD_TREE.getHolder().get(),
                            treePlacement(PlacementUtils.countExtra(4, 0.1F, 1), JBlocks.EUCA_GREEN_SAPLING.get())));

    public static final RegistryObject<PlacedFeature> EUCA_GREEN_TREE = PLACED_FEATURES.register("euca_green_tree_placed", () ->
                    new PlacedFeature(JConfiguredFeatures.EUCA_GREEN_TREE.getHolder().get(),
            treePlacement(PlacementUtils.countExtra(4, 0.1F, 1), JBlocks.EUCA_GOLD_SAPLING.get())));

    public static final RegistryObject<PlacedFeature> DYING_BURNED_TREE = PLACED_FEATURES.register("dying_burned_tree_placed", () ->
            new PlacedFeature(JConfiguredFeatures.DYING_BURNED_TREE.getHolder().get(),
                    treePlacement(PlacementUtils.countExtra(5, 0.1F, 1), JBlocks.CHARRED_SAPLING.get())));

    public static final RegistryObject<PlacedFeature> LARGE_BURNED_TREE = PLACED_FEATURES.register("large_burned_tree_placed", () ->
            new PlacedFeature(JConfiguredFeatures.LARGE_CHARRED_TREE.getHolder().get(),
                    treePlacement(PlacementUtils.countExtra(4, 0.1F, 1), JBlocks.CHARRED_SAPLING.get())));

    public static final RegistryObject<PlacedFeature> MEDIUM_BURNED_TREE = PLACED_FEATURES.register("medium_burned_tree_placed", () ->
            new PlacedFeature(JConfiguredFeatures.MEDIUM_BURNED_TREE.getHolder().get(),
                    treePlacement(PlacementUtils.countExtra(24, 0.1F, 1), JBlocks.CHARRED_SAPLING.get())));

    public static final RegistryObject<PlacedFeature> SMALL_BURNED_TREE = PLACED_FEATURES.register("small_burned_tree_placed", () ->
            new PlacedFeature(JConfiguredFeatures.SMALL_BURNED_TREE.getHolder().get(),
                    treePlacement(PlacementUtils.countExtra(20, 0.1F, 1), JBlocks.CHARRED_SAPLING.get())));

    public static final RegistryObject<PlacedFeature> BOIL_FIRE = PLACED_FEATURES.register("boil_fire_placed", () ->
            new PlacedFeature(JConfiguredFeatures.PATCH_FIRE.getHolder().get()
                    , patch(1, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)));

    public static final RegistryObject<PlacedFeature> BOIL_PLAINS_VEG = PLACED_FEATURES.register("boil_veg_placed", () ->
            new PlacedFeature(JConfiguredFeatures.BOIL_PLAINS_VEG.getHolder().get()
                    , patch(5, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)));

    public static final RegistryObject<PlacedFeature> BOIL_SANDS_VEG = PLACED_FEATURES.register("boil_sands_veg_placed", () ->
            new PlacedFeature(JConfiguredFeatures.BOIL_SANDS_VEG.getHolder().get()
                    , patch(5, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)));

    public static final RegistryObject<PlacedFeature> CHARRED_VEG = PLACED_FEATURES.register("charred_veg_placed", () ->
            new PlacedFeature(JConfiguredFeatures.CHARRED_FIELDS_VEG.getHolder().get()
                    , patch(5, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)));

    public static final RegistryObject<PlacedFeature> SCORCHED_STALAGMITE = PLACED_FEATURES.register("scorched_stalagmite_placed", () ->
            new PlacedFeature(JConfiguredFeatures.BOIL_STALAGMITE.getHolder().get()
                    , patch(15, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)));

    public static final RegistryObject<PlacedFeature> VOLCANIC_ROCK = PLACED_FEATURES.register("volcanic_rock_placed", () ->
            new PlacedFeature(JConfiguredFeatures.VOLCANIC_ROCK.getHolder().get()
                    , patch(1, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)));

    public static final RegistryObject<PlacedFeature> SULPHUR_DEPOSIT = PLACED_FEATURES.register("sulphur_deposit_placed", () ->
            new PlacedFeature(JConfiguredFeatures.SULPHUR_DEPOSIT.getHolder().get()
                    , patch(2, 5, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)));

    public static final RegistryObject<PlacedFeature> SULPHUR_CRYSTAL = PLACED_FEATURES.register("sulphur_crystal_placed", () ->
            new PlacedFeature(JConfiguredFeatures.SULPHUR_CRYSTAL.getHolder().get()
                    , patch(15, 5, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)));

    public static final RegistryObject<PlacedFeature> SCORCHED_CACTUS = PLACED_FEATURES.register("scorched_cactus_placed", () ->
            new PlacedFeature(JConfiguredFeatures.SCORCHED_CACTUS.getHolder().get()
                    , patch(5, 5, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)));

    public static final RegistryObject<PlacedFeature> EUCA_WATER = PLACED_FEATURES.register("euca_water_placed", () ->
            new PlacedFeature(JConfiguredFeatures.EUCA_WATER.getHolder().get()
                    , patch(100, 8, PlacementUtils.FULL_RANGE)));

    public static final RegistryObject<PlacedFeature> BLAZIUM_ORE = PLACED_FEATURES.register("blazium_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.BLAZIUM_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(256)))));

    public static final RegistryObject<PlacedFeature> ASHUAL_ORE = PLACED_FEATURES.register("ashual_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.ASHUAL_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(256)))));

    public static final RegistryObject<PlacedFeature> RIMESTONE_ORE = PLACED_FEATURES.register("rimestone_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.RIMESTONE_ORE.getHolder().get(),
                    commonOrePlacement(12,
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(256)))));

    public static final RegistryObject<PlacedFeature> PERIDOT_ORE = PLACED_FEATURES.register("peridot_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.PERIDOT_ORE.getHolder().get(),
                    commonOrePlacement(12,
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(256)))));

    public static final RegistryObject<PlacedFeature> DEFAULT_OVERWORLD_RUINS = PLACED_FEATURES.register("default_overworld_ruins_placed", () ->
            new PlacedFeature(JConfiguredFeatures.DEFAULT_OVERWORLD_RUINS.getHolder().get(),
                            rarePatch(10, 7)));

    public static final RegistryObject<PlacedFeature> DESERT_OVERWORLD_RUINS = PLACED_FEATURES.register("desert_overworld_ruins_placed", () ->
            new PlacedFeature(JConfiguredFeatures.DESERT_OVERWORLD_RUINS.getHolder().get(),
                    rarePatch(10, 7)));

    public static final RegistryObject<PlacedFeature> EUCA_RUINS = PLACED_FEATURES.register("euca_ruins_placed", () ->
            new PlacedFeature(JConfiguredFeatures.EUCA_RUINS.getHolder().get(),
                    rarePatch(10, 7)));

    public static final RegistryObject<PlacedFeature> GOLD_EUCA_RUINS = PLACED_FEATURES.register("euca_goldite_ruins_placed", () ->
            new PlacedFeature(JConfiguredFeatures.EUCA_GOLDITE_RUINS.getHolder().get(),
                    rarePatch(10, 7)));

    public static final RegistryObject<PlacedFeature> LARGE_FROZEN_TREE = PLACED_FEATURES.register("large_frozen_tree_placed", () ->
            new PlacedFeature(JConfiguredFeatures.LARGE_FROZEN_TREE.getHolder().get(),
                    treePlacement(PlacementUtils.countExtra(4, 0.1F, 1), JBlocks.FROSTWOOD_SAPLING.get())));

    public static final RegistryObject<PlacedFeature> MEDIUM_FROZEN_TREE = PLACED_FEATURES.register("medium_frozen_tree_placed", () ->
            new PlacedFeature(JConfiguredFeatures.MEDIUM_FROZEN_TREE.getHolder().get(),
                    treePlacement(PlacementUtils.countExtra(16, 0.1F, 1), JBlocks.FROSTWOOD_SAPLING.get())));

    public static final RegistryObject<PlacedFeature> SMALL_FROZEN_TREE = PLACED_FEATURES.register("small_frozen_tree_placed", () ->
            new PlacedFeature(JConfiguredFeatures.SMALL_FROZEN_TREE.getHolder().get(),
                    treePlacement(PlacementUtils.countExtra(16, 0.1F, 1), JBlocks.FROSTWOOD_SAPLING.get())));

    public static final RegistryObject<PlacedFeature> LARGE_BITTERWOOD_TREE = PLACED_FEATURES.register("large_bitterwood_tree_placed", () ->
            new PlacedFeature(JConfiguredFeatures.LARGE_FROZEN_BITTERWOOD_TREE.getHolder().get(),
                    treePlacement(PlacementUtils.countExtra(4, 0.1F, 1), JBlocks.BITTERWOOD_SAPLING.get())));

    public static final RegistryObject<PlacedFeature> MEDIUM_BITTERWOOD_TREE = PLACED_FEATURES.register("medium_bitterwood_tree_placed", () ->
            new PlacedFeature(JConfiguredFeatures.MEDIUM_FROZEN_BITTERWOOD_TREE.getHolder().get(),
                    treePlacement(PlacementUtils.countExtra(16, 0.1F, 1), JBlocks.BITTERWOOD_SAPLING.get())));

    public static final RegistryObject<PlacedFeature> SMALL_BITTERWOOD_TREE = PLACED_FEATURES.register("small_bitterwood_tree_placed", () ->
            new PlacedFeature(JConfiguredFeatures.SMALL_FROZEN_BITTERWOOD_TREE.getHolder().get(),
                    treePlacement(PlacementUtils.countExtra(16, 0.1F, 1), JBlocks.BITTERWOOD_SAPLING.get())));

    public static final RegistryObject<PlacedFeature> FROZEN_VEG = PLACED_FEATURES.register("frozen_veg_placed", () ->
            new PlacedFeature(JConfiguredFeatures.FROZEN_VEG.getHolder().get()
                    , patch(12, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)));

    public static final RegistryObject<PlacedFeature> FROZEN_FLOWERS = PLACED_FEATURES.register("frozen_flowers_placed", () ->
            new PlacedFeature(JConfiguredFeatures.FROZEN_FLOWERS.getHolder().get()
                    , patch(12, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)));

    public static final RegistryObject<PlacedFeature> ICE_SPIKE = PLACED_FEATURES.register("ice_spike_placed", () ->
            new PlacedFeature(JConfiguredFeatures.ICE_SPIKE.getHolder().get()
                    , patch(1, 5, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)));

    /** -----------------------------------------------------------------------------------------------------------------------------------------------------------------  **/

    private static ImmutableList.Builder<PlacementModifier> treePlacementBase(PlacementModifier countModifier) {
        return ImmutableList.<PlacementModifier>builder().add(countModifier).add(InSquarePlacement.spread()).add(SurfaceWaterDepthFilter.forMaxDepth(0)).add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR).add(BiomeFilter.biome()).add();
    }

    public static List<PlacementModifier> treePlacement(PlacementModifier modifier, Block block_) {
        return treePlacementBase(modifier).add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(block_.defaultBlockState(), BlockPos.ZERO))).build();
    }

    private static List<PlacementModifier> patch(int count, int chance, PlacementModifier placementModifier) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                placementModifier,
                RarityFilter.onAverageOnceEvery(chance),
                BiomeFilter.biome());
    }

    private static List<PlacementModifier> patch(int count, PlacementModifier placementModifier) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                placementModifier,
                BiomeFilter.biome());
    }

    private static List<PlacementModifier> undergroundCeilingPatch(int count, Block blockPredicatte) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.matchesBlocks(blockPredicatte), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
                BiomeFilter.biome());
    }

    private static List<PlacementModifier> undergroundFloorPatch(int count, Block blockPredicatte) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.matchesBlocks(blockPredicatte), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.vertical(ConstantInt.of(1)),
                BiomeFilter.biome());
    }

    private static List<PlacementModifier> surfaceFloorPatch(int count, Block blockPredicatte) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.matchesBlocks(blockPredicatte), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.vertical(ConstantInt.of(1)),
                BiomeFilter.biome());
    }

    private static List<PlacementModifier> rareUndergroundFloorPatch(int count, int chance, Block blockPredicatte) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.matchesBlocks(blockPredicatte), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.vertical(ConstantInt.of(1)),
                RarityFilter.onAverageOnceEvery(chance),
                BiomeFilter.biome());
    }

    private static List<PlacementModifier> rarePatch(int count, int chance) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                PlacementUtils.FULL_RANGE,
                BiomeFilter.biome(),
                RarityFilter.onAverageOnceEvery(chance));
    }

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier height) {
        return orePlacement(CountPlacement.of(count), height);
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier count, PlacementModifier height) {
        return List.of(count, InSquarePlacement.spread(), height, BiomeFilter.biome());
    }

}
