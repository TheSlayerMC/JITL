package net.jitl.core.data.world_gen.placed_features;

import net.jitl.core.data.world_gen.JConfiguredFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class CloudiaPlacedFeatures extends JPlacedFeature {

    public static final ResourceKey<PlacedFeature> CLOUDIA_TERRAIN = registerKey("cloudia_terrain"),
            CLOUDIA_CLOUD_BLUE = registerKey("blue_cloudia_clouds"),
            CLOUDIA_CLOUD_LIGHT_BLUE = registerKey("light_blue_cloudia_clouds"),
            CLOUDIA_CLOUD_PINK = registerKey("pink_cloudia_clouds"),
            CLOUDIA_ISLAND = registerKey("cloudia_island");
    
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(
                context,
                CLOUDIA_TERRAIN,
                holdergetter.getOrThrow(JConfiguredFeatures.CLOUDIA_TERRAIN),
                patch(1, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(0)), false)
        );

        PlacementUtils.register(
                context,
                CLOUDIA_CLOUD_BLUE,
                holdergetter.getOrThrow(JConfiguredFeatures.CLOUDIA_CLOUD_BLUE),
                commonOrePlacement(7, HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(180)))
        );

        PlacementUtils.register(
                context,
                CLOUDIA_CLOUD_LIGHT_BLUE,
                holdergetter.getOrThrow(JConfiguredFeatures.CLOUDIA_CLOUD_LIGHT_BLUE),
                commonOrePlacement(7, HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(180)))
        );

        PlacementUtils.register(
                context,
                CLOUDIA_CLOUD_PINK,
                holdergetter.getOrThrow(JConfiguredFeatures.CLOUDIA_CLOUD_PINK),
                commonOrePlacement(7, HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(180)))
        );

        PlacementUtils.register(
                context,
                CLOUDIA_ISLAND,
                holdergetter.getOrThrow(JConfiguredFeatures.CLOUDIA_ISLAND),
                patch(1, 20, HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(180)))
        );
    }
}
