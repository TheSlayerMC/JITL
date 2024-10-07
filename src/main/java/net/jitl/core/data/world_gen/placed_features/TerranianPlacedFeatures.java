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

public class TerranianPlacedFeatures extends JPlacedFeature {

    public static final ResourceKey<PlacedFeature>
            TERRANIA_VEG = registerKey("terrania_veg"),
            TERRANIA_TALL_GRASS = registerKey("terrania_tall_grass"),
            ENCHANTED_SHROOMS = registerKey("enchanted_shrooms"),
            ENCHANTED_SHROOMS_TALL = registerKey("enchanted_shrooms_tall"),
            TALL_TERRAMUSHROOM = registerKey("tall_terramushroom"),
            SMALL_TERRAMUSHROOM = registerKey("small_terramushroom"),
            HUGE_PINK_TERRASHROOM = registerKey("huge_pink_terrashroom"),
            HUGE_PURPLE_TERRASHROOM = registerKey("huge_purple_terrashroom"),
            MEGA_TERRANIAN_TREE = registerKey("mega_terranian_tree");
    
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(
                context,
                MEGA_TERRANIAN_TREE,
                holdergetter.getOrThrow(JConfiguredFeatures.MEGA_TERRANIAN_TREE),
                treePlacement(CountPlacement.of(120))
        );

        PlacementUtils.register(
                context,
                TERRANIA_VEG,
                holdergetter.getOrThrow(JConfiguredFeatures.TERRANIA_VEG),
                patch(10, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                ENCHANTED_SHROOMS,
                holdergetter.getOrThrow(JConfiguredFeatures.ENCHANTED_SHROOMS),
                patch(3, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                ENCHANTED_SHROOMS_TALL,
                holdergetter.getOrThrow(JConfiguredFeatures.ENCHANTED_SHROOMS_TALL),
                patch(1, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                TALL_TERRAMUSHROOM,
                holdergetter.getOrThrow(JConfiguredFeatures.TALL_TERRAMUSHROOM),
                patch(3, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                SMALL_TERRAMUSHROOM,
                holdergetter.getOrThrow(JConfiguredFeatures.SMALL_TERRAMUSHROOM),
                patch(5, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                TERRANIA_TALL_GRASS,
                holdergetter.getOrThrow(JConfiguredFeatures.TERRANIA_TALL_GRASS),
                patch(10, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                HUGE_PINK_TERRASHROOM,
                holdergetter.getOrThrow(JConfiguredFeatures.HUGE_PINK_TERRASHROOM),
                patch(1, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );

        PlacementUtils.register(
                context,
                HUGE_PURPLE_TERRASHROOM,
                holdergetter.getOrThrow(JConfiguredFeatures.HUGE_PURPLE_TERRASHROOM),
                patch(1, PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );
    }
}
