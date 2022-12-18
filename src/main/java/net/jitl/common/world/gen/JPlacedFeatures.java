package net.jitl.common.world.gen;

import com.google.common.collect.ImmutableList;
import net.jitl.core.init.JITL;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;

import java.util.List;

public class JPlacedFeatures {

    public  static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registries.PLACED_FEATURE, JITL.MODID);


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
