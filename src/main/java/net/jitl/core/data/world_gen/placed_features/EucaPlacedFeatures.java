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

public class EucaPlacedFeatures extends JPlacedFeature {

    //EUCA
    public static final ResourceKey<PlacedFeature> EUCA_GOLD_TREE = registerKey("euca_gold_tree"),
            EUCA_GREEN_TREE = registerKey("euca_green_tree"),
            EUCA_BOULDER = registerKey("euca_boulder"),
            EUCA_GOLDITE_RUINS = registerKey("euca_goldite_ruins"),
            EUCA_RUINS = registerKey("euca_ruins"),
            MEKYUM_ORE = registerKey("mekyum_ore"),
            CELESTIUM_ORE = registerKey("celestium_ore"),
            STORON_ORE = registerKey("storon_ore"),
            KORITE_ORE = registerKey("korite_ore"),
            GOLDITE_VEG = registerKey("goldite_veg"),
            GOLD_VEG = registerKey("gold_veg"),
            SINGLE_EUCA_GRASS = registerKey("gold_grass"),
            SINGLE_GOLDITE_STALKS = registerKey("goldite_stalks"),
            EUCA_WATER = registerKey("euca_water"),
            GOLD_BOT_SPAWNER = registerKey("gold_bot_spawner"),
            EUCA_PUMPKIN = registerKey("euca_pumpkin");
    
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(
                context,
                STORON_ORE,
                holdergetter.getOrThrow(JConfiguredFeatures.STORON_ORE),
                commonOrePlacement(7, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(128)))
        );

        PlacementUtils.register(
                context,
                MEKYUM_ORE,
                holdergetter.getOrThrow(JConfiguredFeatures.MEKYUM_ORE),
                commonOrePlacement(7, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(128)))
        );

        PlacementUtils.register(
                context,
                CELESTIUM_ORE,
                holdergetter.getOrThrow(JConfiguredFeatures.CELESTIUM_ORE),
                commonOrePlacement(7, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(128)))
        );

        PlacementUtils.register(
                context,
                KORITE_ORE,
                holdergetter.getOrThrow(JConfiguredFeatures.KORITE_ORE),
                commonOrePlacement(7, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(128)))
        );

        PlacementUtils.register(
                context,
                EUCA_GREEN_TREE,
                holdergetter.getOrThrow(JConfiguredFeatures.EUCA_GREEN_TREE),
                treePlacement(PlacementUtils.countExtra(3, 0.1F, 1))
        );

        PlacementUtils.register(
                context,
                EUCA_GOLD_TREE,
                holdergetter.getOrThrow(JConfiguredFeatures.EUCA_GOLD_TREE),
                treePlacement(PlacementUtils.countExtra(4, 0.1F, 1))
        );

        PlacementUtils.register(
                context,
                EUCA_WATER,
                holdergetter.getOrThrow(JConfiguredFeatures.EUCA_WATER),
                patch(100, 8, PlacementUtils.FULL_RANGE)
        );

        PlacementUtils.register(
                context,
                GOLDITE_VEG,
                holdergetter.getOrThrow(JConfiguredFeatures.GOLDITE_VEG),
                patch(6, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                GOLD_VEG,
                holdergetter.getOrThrow(JConfiguredFeatures.GOLD_VEG),
                patch(6, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                SINGLE_EUCA_GRASS,
                holdergetter.getOrThrow(JConfiguredFeatures.SINGLE_EUCA_GRASS),
                patch(2, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                SINGLE_GOLDITE_STALKS,
                holdergetter.getOrThrow(JConfiguredFeatures.SINGLE_GOLDITE_STALKS),
                patch(2, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                EUCA_RUINS,
                holdergetter.getOrThrow(JConfiguredFeatures.EUCA_RUINS),
                patch(1, 24, PlacementUtils.FULL_RANGE)
        );

        PlacementUtils.register(
                context,
                EUCA_PUMPKIN,
                holdergetter.getOrThrow(JConfiguredFeatures.EUCA_PUMPKIN),
                patch(1, 30, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );


        PlacementUtils.register(
                context,
                EUCA_GOLDITE_RUINS,
                holdergetter.getOrThrow(JConfiguredFeatures.EUCA_GOLDITE_RUINS),
                patch(1, 24, PlacementUtils.FULL_RANGE)
        );

        PlacementUtils.register(
                context,
                GOLD_BOT_SPAWNER,
                holdergetter.getOrThrow(JConfiguredFeatures.GOLD_BOT_SPAWNER),
                patch(1, 20, PlacementUtils.FULL_RANGE)
        );

        PlacementUtils.register(
                context,
                EUCA_BOULDER,
                holdergetter.getOrThrow(JConfiguredFeatures.EUCA_BOULDER),
                patch(2, 5, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );
    }
}
