package net.jitl.core.data.world_gen.placed_features;

import com.google.common.collect.ImmutableList;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class JPlacedFeature {

    public static List<PlacementModifier> orePlacement(PlacementModifier countPlacement, PlacementModifier heightRange) {
        return List.of(countPlacement, InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
    }

    public static List<PlacementModifier> patch(int count, int chance, PlacementModifier heightRange) {
        return List.of(CountPlacement.of(count),
                InSquarePlacement.spread(),
                heightRange,
                RarityFilter.onAverageOnceEvery(chance),
                BiomeFilter.biome());
    }

    public static List<PlacementModifier> patch(int count, PlacementModifier heightRange) {
        return List.of(
                NoiseThresholdCountPlacement.of(-0.8, 5, 10),
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                heightRange,
                BiomeFilter.biome());
    }

    public static List<PlacementModifier> cactus(int count, Block block) {
        return List.of(
                CountPlacement.of(count),
                RandomOffsetPlacement.ofTriangle(7, 3),
                BlockPredicateFilter.forPredicate(
                        BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.wouldSurvive(block.defaultBlockState(), BlockPos.ZERO))
                )
        );
    }

    public static List<PlacementModifier> flowerPatch(int count) {
        return List.of(
                NoiseThresholdCountPlacement.of(-0.8, 5, 10),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome(),
                CountPlacement.of(count),
                RandomOffsetPlacement.ofTriangle(7, 3),
                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE)
        );
    }

    public static List<PlacementModifier> pumpkinPatch() {
        return List.of(
                RarityFilter.onAverageOnceEvery(300),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome(),
                CountPlacement.of(96),
                RandomOffsetPlacement.ofTriangle(7, 3),
                BlockPredicateFilter.forPredicate(
                        BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(), JBlocks.GOLDITE_GRASS.get()))
                ),
                BlockPredicateFilter.forPredicate(
                        BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(), JBlocks.EUCA_GOLD_GRASS.get()))
                )
        );
    }

    public static List<PlacementModifier> patch(int count, int heightStep) {
        return Util.copyAndAdd(
                worldSurfaceSquaredWithCount(heightStep),
                CountPlacement.of(count),
                RandomOffsetPlacement.ofTriangle(7, 3),
                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE)
        );
    }

    public static List<PlacementModifier> patch(int count, PlacementModifier heightRange, boolean square) {
        return List.of(
                CountPlacement.of(count),
                heightRange,
                BiomeFilter.biome());
    }

    public static List<PlacementModifier> worldSurfaceSquaredWithCount(int count) {
        return List.of(CountPlacement.of(count), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier heightRange) {
        return orePlacement(CountPlacement.of(count), heightRange);
    }

    public static List<PlacementModifier> rareOrePlacement(int chance, PlacementModifier heightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), heightRange);
    }

    public static ResourceKey<PlacedFeature> registerKey(String key) {
        return ResourceKey.create(Registries.PLACED_FEATURE, JITL.rl(key));
    }

    public static ImmutableList.Builder<PlacementModifier> treePlacementBase(PlacementModifier placement) {
        return ImmutableList.<PlacementModifier>builder()
                .add(placement)
                .add(InSquarePlacement.spread())
                .add(SurfaceWaterDepthFilter.forMaxDepth(0))
                .add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
                .add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)))
                .add(BiomeFilter.biome());
    }

    public static ImmutableList.Builder<PlacementModifier> treePlacementBaseHeight(PlacementModifier placement, PlacementModifier placement2) {
        return ImmutableList.<PlacementModifier>builder()
                .add(placement)
                .add(placement2)
                .add(InSquarePlacement.spread())
                .add(SurfaceWaterDepthFilter.forMaxDepth(0))
                .add(PlacementUtils.FULL_RANGE)
                .add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)))
                .add(BiomeFilter.biome());
    }

    public static List<PlacementModifier> treePlacement(PlacementModifier placement) {
        return treePlacementBase(placement).build();
    }

    public static List<PlacementModifier> treePlacement(PlacementModifier placement, PlacementModifier placement2) {
        return treePlacementBaseHeight(placement, placement2).build();
    }

    public static List<PlacementModifier> treePlacement(PlacementModifier placement, Block saplingBlock) {
        return treePlacementBase(placement)
                .add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(saplingBlock.defaultBlockState(), BlockPos.ZERO)))
                .build();
    }

}
