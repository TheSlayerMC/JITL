package net.jitl.core.data.world_gen.placed_features;

import com.google.common.collect.ImmutableList;
import net.jitl.core.data.world_gen.JConfiguredFeatures;
import net.jitl.core.init.JITL;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class FrozenPlacedFeatures extends JPlacedFeature {

    public static final ResourceKey<PlacedFeature> SMALL_FROZEN_TREE = registerKey("small_frozen_tree"),
            MEDIUM_FROZEN_TREE = registerKey("medium_frozen_tree"),
            LARGE_FROZEN_TREE = registerKey("large_frozen_tree"),
            RIMESTONE_ORE = registerKey("rimestone_ore"),
            PERIDOT_ORE = registerKey("peridot_ore"),
            FROZEN_VEG = registerKey("frozen_veg"),
            FROZEN_FLOWERS = registerKey("frozen_flowers"),
            LARGE_FROZEN_BITTERWOOD_TREE = registerKey("large_frozen_bitterwood_tree"),
            MEDIUM_FROZEN_BITTERWOOD_TREE = registerKey("medium_frozen_bitterwood_tree"),
            SMALL_FROZEN_BITTERWOOD_TREE = registerKey("small_frozen_bitterwood_tree"),
            ICE_SPIKE = registerKey("frozen_ice_spike"),
            GLACIAL_ROCK = registerKey("glacial_rock");
    
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(
                context,
                PERIDOT_ORE,
                holdergetter.getOrThrow(JConfiguredFeatures.PERIDOT_ORE),
                commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(256)))
        );

        PlacementUtils.register(
                context,
                RIMESTONE_ORE,
                holdergetter.getOrThrow(JConfiguredFeatures.RIMESTONE_ORE),
                commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(256)))
        );

        PlacementUtils.register(
                context,
                MEDIUM_FROZEN_TREE,
                holdergetter.getOrThrow(JConfiguredFeatures.MEDIUM_FROZEN_TREE),
                treePlacement(PlacementUtils.countExtra(16, 0.1F, 1))
        );

        PlacementUtils.register(
                context,
                SMALL_FROZEN_TREE,
                holdergetter.getOrThrow(JConfiguredFeatures.SMALL_FROZEN_TREE),
                treePlacement(PlacementUtils.countExtra(16, 0.1F, 1))
        );

        PlacementUtils.register(
                context,
                LARGE_FROZEN_TREE,
                holdergetter.getOrThrow(JConfiguredFeatures.LARGE_FROZEN_TREE),
                treePlacement(PlacementUtils.countExtra(4, 0.1F, 1))
        );

        PlacementUtils.register(
                context,
                LARGE_FROZEN_BITTERWOOD_TREE,
                holdergetter.getOrThrow(JConfiguredFeatures.LARGE_FROZEN_BITTERWOOD_TREE),
                treePlacement(PlacementUtils.countExtra(4, 0.1F, 1))
        );

        PlacementUtils.register(
                context,
                MEDIUM_FROZEN_BITTERWOOD_TREE,
                holdergetter.getOrThrow(JConfiguredFeatures.MEDIUM_FROZEN_BITTERWOOD_TREE),
                treePlacement(PlacementUtils.countExtra(16, 0.1F, 1))
        );

        PlacementUtils.register(
                context,
                SMALL_FROZEN_BITTERWOOD_TREE,
                holdergetter.getOrThrow(JConfiguredFeatures.MEDIUM_FROZEN_BITTERWOOD_TREE),
                treePlacement(PlacementUtils.countExtra(16, 0.1F, 1))
        );

        PlacementUtils.register(
                context,
                FROZEN_VEG,
                holdergetter.getOrThrow(JConfiguredFeatures.FROZEN_VEG),
                patch(8, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                FROZEN_FLOWERS,
                holdergetter.getOrThrow(JConfiguredFeatures.FROZEN_FLOWERS),
                patch(8, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                ICE_SPIKE,
                holdergetter.getOrThrow(JConfiguredFeatures.ICE_SPIKE),
                patch(3, 5, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                GLACIAL_ROCK,
                holdergetter.getOrThrow(JConfiguredFeatures.GLACIAL_ROCK),
                patch(1, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );
    }
}
