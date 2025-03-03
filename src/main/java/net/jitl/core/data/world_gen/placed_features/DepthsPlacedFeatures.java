package net.jitl.core.data.world_gen.placed_features;

import net.jitl.core.data.world_gen.JConfiguredFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

public class DepthsPlacedFeatures extends JPlacedFeature {

    public static final ResourceKey<PlacedFeature> DEPTHS_LAMP_ROOF = registerKey("depths_lamp_roof"),
            DEPTHS_LAMP_FLOOR = registerKey("depths_lamp_floor"),
            FLAIRIUM_ORE = registerKey("flairium_ore"),
            DES_ORE = registerKey("des_ore"),
            DEPTHS_VEG = registerKey("depths_veg"),
            DEPTHS_TREE = registerKey("depths_tree"),
            DEPTHS_CRYSTAL = registerKey("depths_crystal"),
            FLOOR_DEPTHS_CRYSTAL = registerKey("floor_depths_crystal"),
            DEPTHS_WATER = registerKey("depths_water"),
            DEPTHS_GEODE = registerKey("depths_geode"),
            DEPTHS_SPIKE = registerKey("depths_spike"),
            CRYSTAL_DRIPSTONE = registerKey("crystal_dripstone");

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
                DEPTHS_SPIKE,
                holdergetter.getOrThrow(JConfiguredFeatures.DEPTHS_SPIKE),
                patch(1, 30, PlacementUtils.FULL_RANGE)
        );

        PlacementUtils.register(
                context,
                CRYSTAL_DRIPSTONE,
                holdergetter.getOrThrow(JConfiguredFeatures.CRYSTAL_DRIPSTONE),
                patch(3, PlacementUtils.FULL_RANGE)
        );

        PlacementUtils.register(
                context,
                DEPTHS_WATER,
                holdergetter.getOrThrow(JConfiguredFeatures.DEPTHS_WATER),
                patch(100, 8, PlacementUtils.FULL_RANGE)
        );

        PlacementUtils.register(
                context,
                FLOOR_DEPTHS_CRYSTAL,
                holdergetter.getOrThrow(JConfiguredFeatures.FLOOR_DEPTHS_CRYSTAL),
                patch(1, PlacementUtils.FULL_RANGE)
        );
    }
}
