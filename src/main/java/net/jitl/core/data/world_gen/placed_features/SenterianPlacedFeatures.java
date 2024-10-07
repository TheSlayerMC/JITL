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

public class SenterianPlacedFeatures extends JPlacedFeature {

    public static final ResourceKey<PlacedFeature> SENTERIAN_TERRAIN = registerKey("senterian_terrain");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(
                context,
                SENTERIAN_TERRAIN,
                holdergetter.getOrThrow(JConfiguredFeatures.SENTERIAN_TERRAIN),
                patch(1, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(0)), false)
        );
    }
}
