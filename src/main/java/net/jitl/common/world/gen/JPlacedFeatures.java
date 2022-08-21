package net.jitl.common.world.gen;

import net.jitl.core.init.JITL;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class JPlacedFeatures {

    public  static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, JITL.MODID);

    public static final RegistryObject<PlacedFeature> IRIDIUM_ORE = PLACED_FEATURES.register("iridum_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.IRIDIUM_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(48)))));

    public static final RegistryObject<PlacedFeature> SAPPHIRE_ORE = PLACED_FEATURES.register("sapphire_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.SAPPHIRE_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(48)))));

    public static final RegistryObject<PlacedFeature> LUNIUM_ORE = PLACED_FEATURES.register("lunium_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.LUNIUM_ORE.getHolder().get(),
                    commonOrePlacement(8,
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(48)))));

    public static final RegistryObject<PlacedFeature> SHADIUM_ORE = PLACED_FEATURES.register("shadium_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.SHADIUM_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(48)))));

    public static final RegistryObject<PlacedFeature> WARPED_QUARTZ_ORE = PLACED_FEATURES.register("warped_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.WARPED_QUARTZ_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()))));

    public static final RegistryObject<PlacedFeature> BLOODCRUST_ORE = PLACED_FEATURES.register("blood_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.BLOODCRUST_ORE.getHolder().get(),
                    commonOrePlacement(4,
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()))));

    public static final RegistryObject<PlacedFeature> ENDERILLIUM_ORE = PLACED_FEATURES.register("enderillium_ore_placed", () ->
            new PlacedFeature(JConfiguredFeatures.ENDERILLIUM_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(100)))));

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier height) {
        return orePlacement(CountPlacement.of(count), height);
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier count, PlacementModifier height) {
        return List.of(count, InSquarePlacement.spread(), height, BiomeFilter.biome());
    }

}
