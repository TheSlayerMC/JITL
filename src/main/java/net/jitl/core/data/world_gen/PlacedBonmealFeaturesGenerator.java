package net.jitl.core.data.world_gen;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.RegistriesDatapackGenerator;

import java.util.concurrent.CompletableFuture;

public class PlacedBonmealFeaturesGenerator extends RegistriesDatapackGenerator {
    public PlacedBonmealFeaturesGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

//    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder().add(Registries.PLACED_FEATURE, JBonemealPlacedFeatures::bootstrap);
//
//
//    public PlacedBonmealFeaturesGenerator(PackOutput output) {
//        super(output, PlacedBonmealFeaturesGenerator.createLookup(), Set.of(JITL.MODID));
//    }
//
//    public static CompletableFuture<HolderLookup.Provider> createLookup() {
//        return CompletableFuture.completedFuture(BUILDER.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), VanillaRegistries.createLookup()));
//    }
}
