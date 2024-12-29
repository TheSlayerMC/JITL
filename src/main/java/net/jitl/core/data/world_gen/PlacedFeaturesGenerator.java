package net.jitl.core.data.world_gen;


import net.jitl.core.data.world_gen.placed_features.*;
import net.jitl.core.init.JITL;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class PlacedFeaturesGenerator extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder().add(Registries.PLACED_FEATURE, PlacedFeaturesGenerator::addFeatures);

    public PlacedFeaturesGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(JITL.MOD_ID));
    }

    public static void addFeatures(BootstrapContext<PlacedFeature> context) {
        EucaPlacedFeatures.bootstrap(context);
        BoilPlacedFeatures.bootstrap(context);
        FrozenPlacedFeatures.bootstrap(context);
        CorbaPlacedFeatures.bootstrap(context);
        DepthsPlacedFeatures.bootstrap(context);
        TerranianPlacedFeatures.bootstrap(context);
        CloudiaPlacedFeatures.bootstrap(context);
        SenterianPlacedFeatures.bootstrap(context);
    }
}
