package net.jitl.core.data.world_gen.placed_features;

import com.google.common.collect.ImmutableList;
import net.jitl.core.init.JITL;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
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
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                heightRange,
                BiomeFilter.biome());
    }

    public static List<PlacementModifier> patch(int count, PlacementModifier heightRange, boolean square) {
        return List.of(
                CountPlacement.of(count),
                heightRange,
                BiomeFilter.biome());
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
                .add(BiomeFilter.biome());
    }

    public static ImmutableList.Builder<PlacementModifier> treePlacementBaseHeight(PlacementModifier placement, PlacementModifier placement2) {
        return ImmutableList.<PlacementModifier>builder()
                .add(placement)
                .add(placement2)
                .add(InSquarePlacement.spread())
                .add(SurfaceWaterDepthFilter.forMaxDepth(0))
                .add(PlacementUtils.FULL_RANGE)
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
