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

public class DepthsPlacedFeatures extends JPlacedFeature {

    public static final ResourceKey<PlacedFeature> DEPTHS_LAMP_ROOF = registerKey("depths_lamp_roof"),
            DEPTHS_LAMP_FLOOR = registerKey("depths_lamp_floor"),
            FLAIRIUM_ORE = registerKey("flairium_ore"),
            DES_ORE = registerKey("des_ore"),
            DEPTHS_VEG = registerKey("depths_veg"),
            DEPTHS_TREE = registerKey("depths_tree"),
            DEPTHS_CRYSTAL = registerKey("depths_crystal"),
            FLOOR_DEPTHS_CRYSTAL = registerKey("floor_depths_crystal"),
            DEPTHS_GEODE = registerKey("depths_geode");
    
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(
                context,
                FLAIRIUM_ORE,
                holdergetter.getOrThrow(JConfiguredFeatures.FLAIRIUM_ORE),
                commonOrePlacement(7, PlacementUtils.FULL_RANGE)
        );

        PlacementUtils.register(
                context,
                DES_ORE,
                holdergetter.getOrThrow(JConfiguredFeatures.DES_ORE),
                commonOrePlacement(7, PlacementUtils.FULL_RANGE)
        );

        PlacementUtils.register(
                context,
                DEPTHS_LAMP_FLOOR,
                holdergetter.getOrThrow(JConfiguredFeatures.DEPTHS_LAMP_FLOOR),
                commonOrePlacement(7, PlacementUtils.FULL_RANGE)
        );

        PlacementUtils.register(
                context,
                DEPTHS_LAMP_ROOF,
                holdergetter.getOrThrow(JConfiguredFeatures.DEPTHS_LAMP_ROOF),
                commonOrePlacement(7, PlacementUtils.FULL_RANGE)
        );

        PlacementUtils.register(
                context,
                DEPTHS_TREE,
                holdergetter.getOrThrow(JConfiguredFeatures.DEPTHS_TREE),
                treePlacement(CountPlacement.of(12), PlacementUtils.FULL_RANGE)
        );

        PlacementUtils.register(
                context,
                DEPTHS_VEG,
                holdergetter.getOrThrow(JConfiguredFeatures.DEPTHS_VEG),
                patch(6, PlacementUtils.FULL_RANGE)
        );

        PlacementUtils.register(
                context,
                DEPTHS_CRYSTAL,
                holdergetter.getOrThrow(JConfiguredFeatures.DEPTHS_CRYSTAL),
                patch(20, PlacementUtils.FULL_RANGE)
        );

        PlacementUtils.register(
                context,
                DEPTHS_GEODE,
                holdergetter.getOrThrow(JConfiguredFeatures.DEPTHS_GEODE),
                patch(1, 30, PlacementUtils.FULL_RANGE)
        );

        PlacementUtils.register(
                context,
                FLOOR_DEPTHS_CRYSTAL,
                holdergetter.getOrThrow(JConfiguredFeatures.FLOOR_DEPTHS_CRYSTAL),
                patch(1, PlacementUtils.FULL_RANGE)
        );
    }
}
