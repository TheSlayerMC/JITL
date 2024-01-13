package net.jitl.core.data.world_gen;


import net.minecraft.Util;
import net.minecraft.core.Cloner;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.RegistriesDatapackGenerator;
import net.minecraft.data.registries.VanillaRegistries;

import java.util.concurrent.CompletableFuture;

public class ConfiguredFeaturesGenerator extends RegistriesDatapackGenerator {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder().add(Registries.CONFIGURED_FEATURE, JConfiguredFeatures::bootstrap);


    public ConfiguredFeaturesGenerator(PackOutput output) {
        super(output, ConfiguredFeaturesGenerator.createLookup(CompletableFuture.supplyAsync(VanillaRegistries::createLookup, Util.backgroundExecutor())));
    }

    public static CompletableFuture<HolderLookup.Provider> createLookup(CompletableFuture<HolderLookup.Provider> pLookup) {
        return pLookup.thenApply((reg) -> {
            CompletableFuture.completedFuture(BUILDER.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), reg, new Cloner.Factory()));
            return reg;
        });
    }
}
