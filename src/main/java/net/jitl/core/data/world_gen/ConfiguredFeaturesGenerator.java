package net.jitl.core.data.world_gen;


import net.jitl.core.init.JITL;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ConfiguredFeaturesGenerator extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder().add(Registries.CONFIGURED_FEATURE, JConfiguredFeatures::bootstrap);


    public ConfiguredFeaturesGenerator(PackOutput output) {
        super(output, ConfiguredFeaturesGenerator.createLookup(), Set.of(JITL.MODID));
    }

    public static CompletableFuture<HolderLookup.Provider> createLookup() {
        return CompletableFuture.completedFuture(BUILDER.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), VanillaRegistries.createLookup()));
    }
}
