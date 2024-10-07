package net.jitl.core.data.world_gen.placed_features;

import net.jitl.core.data.world_gen.JConfiguredFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class CorbaPlacedFeatures extends JPlacedFeature {

    public static final ResourceKey<PlacedFeature>
            ORBADITE_ORE = registerKey("orbadite_ore"),
            GORBITE_ORE = registerKey("gorbite_ore"),
            CORBA_VEG = registerKey("corba_veg"),
            CORBA_TALL_GRASS = registerKey("corba_tall_grass"),
            CORBA_TALL_PLANTS = registerKey("corba_tall_plants"),
            CORBA_RUINS = registerKey("corba_ruins"),
            CORBA_LILY_PAD = registerKey("corba_lily_pad"),
            BOGSHROOMS = registerKey("bogshrooms"),
            CORBA_TREE_SMALL = registerKey("corba_tree_small"),
            CORBA_TREE_MEDIUM = registerKey("corba_tree_medium"),
            CORBA_TREE_LARGE = registerKey("corba_tree_large"),
            CORBA_SWAMP_TREE = registerKey("corba_swamp_tree");
    
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(
                context,
                ORBADITE_ORE,
                holdergetter.getOrThrow(JConfiguredFeatures.ORBADITE_ORE),
                commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(256)))
        );

        PlacementUtils.register(
                context,
                GORBITE_ORE,
                holdergetter.getOrThrow(JConfiguredFeatures.GORBITE_ORE),
                commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(256)))
        );

        PlacementUtils.register(
                context,
                CORBA_TREE_SMALL,
                holdergetter.getOrThrow(JConfiguredFeatures.CORBA_TREE_SMALL),
                treePlacement(PlacementUtils.countExtra(16, 0.1F, 1))
        );

        PlacementUtils.register(
                context,
                CORBA_TREE_MEDIUM,
                holdergetter.getOrThrow(JConfiguredFeatures.CORBA_TREE_MEDIUM),
                treePlacement(PlacementUtils.countExtra(16, 0.1F, 1))
        );

        PlacementUtils.register(
                context,
                CORBA_TREE_LARGE,
                holdergetter.getOrThrow(JConfiguredFeatures.CORBA_TREE_LARGE),
                treePlacement(PlacementUtils.countExtra(4, 0.1F, 1))
        );

        PlacementUtils.register(
                context,
                CORBA_SWAMP_TREE,
                holdergetter.getOrThrow(JConfiguredFeatures.CORBA_SWAMP_TREE),
                treePlacement(PlacementUtils.countExtra(4, 0.1F, 1))
        );

        PlacementUtils.register(
                context,
                CORBA_VEG,
                holdergetter.getOrThrow(JConfiguredFeatures.CORBA_VEG),
                patch(5, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                CORBA_TALL_GRASS,
                holdergetter.getOrThrow(JConfiguredFeatures.CORBA_TALL_GRASS),
                patch(2, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                BOGSHROOMS,
                holdergetter.getOrThrow(JConfiguredFeatures.BOGSHROOMS),
                patch(5, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                CORBA_TALL_PLANTS,
                holdergetter.getOrThrow(JConfiguredFeatures.CORBA_TALL_PLANTS),
                patch(5, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                CORBA_RUINS,
                holdergetter.getOrThrow(JConfiguredFeatures.CORBA_RUINS),
                patch(1, 30, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                CORBA_LILY_PAD,
                holdergetter.getOrThrow(JConfiguredFeatures.CORBA_LILY_PAD),
                patch(4, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );
    }
}
