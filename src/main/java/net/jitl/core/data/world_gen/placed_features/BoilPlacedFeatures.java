package net.jitl.core.data.world_gen.placed_features;

import net.jitl.core.data.world_gen.JConfiguredFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

public class BoilPlacedFeatures extends JPlacedFeature {

    public static final ResourceKey<PlacedFeature> VOLCANIC_ROCK = registerKey("volcanic_rock"),
            BOIL_STALAGMITE = registerKey("scorched_stalagmite"),
            SULPHUR_DEPOSIT = registerKey("sulphur_deposit"),
            SULPHUR_CRYSTAL = registerKey("sulphur_crystal"),
            LARGE_CHARRED_TREE = registerKey("large_charred_tree"),
            DYING_BURNED_TREE = registerKey("dying_burned_tree"),
            MEDIUM_BURNED_TREE = registerKey("medium_burned_tree"),
            SMALL_BURNED_TREE = registerKey("small_burned_tree"),
            SCORCHED_CACTUS = registerKey("scorched_cactus"),
            FIRE = registerKey("boil_fire"),
            BOIL_PLAINS_VEG = registerKey("boil_veg"),
            BOIL_SANDS_VEG = registerKey("boil_sands_veg"),
            BOIL_UNDERGROWTH = registerKey("boil_undergrowth"),
            CHARRED_FIELDS_VEG = registerKey("charred_veg"),
            ASHUAL_ORE = registerKey("ashual_ore"),
            BLAZIUM_ORE = registerKey("blazium_ore");
    
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(
                context,
                BLAZIUM_ORE,
                holdergetter.getOrThrow(JConfiguredFeatures.BLAZIUM_ORE),
                commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(256)))
        );

        PlacementUtils.register(
                context,
                ASHUAL_ORE,
                holdergetter.getOrThrow(JConfiguredFeatures.ASHUAL_ORE),
                commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(256)))
        );

        PlacementUtils.register(
                context,
                LARGE_CHARRED_TREE,
                holdergetter.getOrThrow(JConfiguredFeatures.LARGE_CHARRED_TREE),
                treePlacement(PlacementUtils.countExtra(4, 0.1F, 1))
        );

        PlacementUtils.register(
                context,
                DYING_BURNED_TREE,
                holdergetter.getOrThrow(JConfiguredFeatures.DYING_BURNED_TREE),
                treePlacement(PlacementUtils.countExtra(5, 0.1F, 1))
        );

        PlacementUtils.register(
                context,
                MEDIUM_BURNED_TREE,
                holdergetter.getOrThrow(JConfiguredFeatures.MEDIUM_BURNED_TREE),
                treePlacement(PlacementUtils.countExtra(24, 0.1F, 1))
        );

        PlacementUtils.register(
                context,
                SMALL_BURNED_TREE,
                holdergetter.getOrThrow(JConfiguredFeatures.SMALL_BURNED_TREE),
                treePlacement(PlacementUtils.countExtra(20, 0.1F, 1))
        );

        PlacementUtils.register(
                context,
                BOIL_UNDERGROWTH,
                holdergetter.getOrThrow(JConfiguredFeatures.BOIL_UNDERGROWTH),
                patch(1, 40, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                CHARRED_FIELDS_VEG,
                holdergetter.getOrThrow(JConfiguredFeatures.CHARRED_FIELDS_VEG),
                patch(7, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                BOIL_PLAINS_VEG,
                holdergetter.getOrThrow(JConfiguredFeatures.BOIL_PLAINS_VEG),
                patch(2, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                BOIL_SANDS_VEG,
                holdergetter.getOrThrow(JConfiguredFeatures.BOIL_SANDS_VEG),
                patch(6, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                FIRE,
                holdergetter.getOrThrow(JConfiguredFeatures.FIRE),
                patch(1, 6, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                SCORCHED_CACTUS,
                holdergetter.getOrThrow(JConfiguredFeatures.SCORCHED_CACTUS),
                patch(5, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                BOIL_STALAGMITE,
                holdergetter.getOrThrow(JConfiguredFeatures.BOIL_STALAGMITE),
                patch(95, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                SULPHUR_DEPOSIT,
                holdergetter.getOrThrow(JConfiguredFeatures.SULPHUR_DEPOSIT),
                patch(2, 5, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                VOLCANIC_ROCK,
                holdergetter.getOrThrow(JConfiguredFeatures.VOLCANIC_ROCK),
                patch(1, 2, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                SULPHUR_CRYSTAL,
                holdergetter.getOrThrow(JConfiguredFeatures.SULPHUR_CRYSTAL),
                patch(1, 8, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );
    }
}
