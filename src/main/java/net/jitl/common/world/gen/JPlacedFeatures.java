package net.jitl.common.world.gen;

import com.google.common.collect.ImmutableList;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.Vec3i;
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

    public static final RegistryObject<PlacedFeature> EUCA_BOULDER = PLACED_FEATURES.register("euca_boulder_placed", () ->
            new PlacedFeature(JConfiguredFeatures.EUCA_BOULDER.getHolder().get()
                            , patch(2, 5, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)));

    public static final RegistryObject<PlacedFeature> GOLD_VEG = PLACED_FEATURES.register("gold_veg_placed", () ->
                    new PlacedFeature(JConfiguredFeatures.GOLD_VEG.getHolder().get()
                            , patch(6, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)));

    public static final RegistryObject<PlacedFeature> EUCA_GREEN_GOLD_TREE = PLACED_FEATURES.register("euca_gold_tree_placed", () ->
                    new PlacedFeature(JConfiguredFeatures.EUCA_GOLD_TREE.getHolder().get(),
                            treePlacement(PlacementUtils.countExtra(4, 0.1F, 1), JBlocks.EUCA_GREEN_SAPLING.get())));

    public static final RegistryObject<PlacedFeature> EUCA_GREEN_TREE = PLACED_FEATURES.register("euca_green_tree_placed", () ->
                    new PlacedFeature(JConfiguredFeatures.EUCA_GREEN_TREE.getHolder().get(),
            treePlacement(PlacementUtils.countExtra(4, 0.1F, 1), JBlocks.EUCA_GOLD_SAPLING.get())));

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
