package net.jitl.core.data.world_gen.carver;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.RegistriesDatapackGenerator;

import java.util.concurrent.CompletableFuture;

public class CarverGenerator extends RegistriesDatapackGenerator {
    public CarverGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

//    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder().add(Registries.CONFIGURED_CARVER, CarverFeatureKeys::bootstrap);
//
//
//    public CarverGenerator(PackOutput output) {
//        super(output, CarverGenerator.createLookup(), Set.of(JITL.MODID));
//    }
//
//    public static CompletableFuture<HolderLookup.Provider> createLookup() {
//        return CompletableFuture.completedFuture(BUILDER.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), VanillaRegistries.createLookup()));
//    }
}
