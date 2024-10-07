package net.jitl.core.data.world_gen;

import net.jitl.common.world.dimension.Dimensions;
import net.jitl.core.data.world_gen.biome.*;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class JBiomeData {

    public static void bootstrap(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placed = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carver = context.lookup(Registries.CONFIGURED_CARVER);

        context.register(Dimensions.JBiomes.EUCA_GOLDITE_GRAINS, EucaBiomes.golditeGrains(placed, carver));
        context.register(Dimensions.JBiomes.EUCA_PLAINS, EucaBiomes.eucaPlains(placed, carver));

        context.register(Dimensions.JBiomes.BOIL, BoilBiomes.boil(placed, carver));
        context.register(Dimensions.JBiomes.BOILING_SANDS, BoilBiomes.boilingSands(placed, carver));
        context.register(Dimensions.JBiomes.CHARRED_FIELDS, BoilBiomes.charredFields(placed, carver));
        context.register(Dimensions.JBiomes.SCORCHED_WASTELANDS, BoilBiomes.scorchedWastelands(placed, carver));

        context.register(Dimensions.JBiomes.BITTERWOOD_FOREST, FrozenBiomes.bitterwoodForest(placed, carver));
        context.register(Dimensions.JBiomes.DYING_FOREST, FrozenBiomes.dyingForest(placed, carver));
        context.register(Dimensions.JBiomes.FROZEN_WASTES, FrozenBiomes.frozenWastes(placed, carver));

        context.register(Dimensions.JBiomes.DEPTHS, DepthsBiomes.depths(placed, carver));

        context.register(Dimensions.JBiomes.BOGWEED_FIELDS, CorbaBiomes.bogweedFields(placed, carver));
        context.register(Dimensions.JBiomes.CORBA_PLAINS, CorbaBiomes.corbaPlains(placed, carver));
        context.register(Dimensions.JBiomes.TAINTED_FOREST, CorbaBiomes.taintedForest(placed, carver));
        context.register(Dimensions.JBiomes.TAINTED_SWAMP, CorbaBiomes.taintedSwamp(placed, carver));

        context.register(Dimensions.JBiomes.TERRANIA, TerranianBiomes.terrania(placed, carver));
        context.register(Dimensions.JBiomes.MUSHROOM_FOREST, TerranianBiomes.mushroomForest(placed, carver));

        context.register(Dimensions.JBiomes.CLOUDIA, CloudiaBiomes.cloudia(placed, carver));

        context.register(Dimensions.JBiomes.SENTERIAN, SenterianBiomes.senterian(placed, carver));
    }
}
