package net.jitl.core.data.world_gen;


import net.jitl.common.world.gen.JBonemealPlacedFeatures;
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

public class PlacedBonmealFeaturesGenerator extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder().add(Registries.PLACED_FEATURE, JBonemealPlacedFeatures::bootstrap);


    public PlacedBonmealFeaturesGenerator(PackOutput output) {
        super(output, PlacedBonmealFeaturesGenerator.createLookup(), Set.of(JITL.MODID));
    }

    public static CompletableFuture<HolderLookup.Provider> createLookup() {
        return CompletableFuture.completedFuture(BUILDER.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), VanillaRegistries.createLookup()));
    }
}
