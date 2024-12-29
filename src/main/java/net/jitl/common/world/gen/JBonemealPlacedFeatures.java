package net.jitl.common.world.gen;

import net.jitl.core.data.world_gen.JConfiguredFeatures;
import net.jitl.core.init.JITL;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class JBonemealPlacedFeatures {

    public static final ResourceKey<PlacedFeature>
            GOLDITE_GRASS_BONEMEAL = registerKey("goldite_grass_bonemeal"),
            EUCA_GOLD_GRASS_BONEMEAL = registerKey("euca_gold_grass_bonemeal");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holder = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> goldite_stalks = holder.getOrThrow(JConfiguredFeatures.SINGLE_GOLDITE_STALKS);
        Holder<ConfiguredFeature<?, ?>> gold_grass = holder.getOrThrow(JConfiguredFeatures.SINGLE_GOLDITE_STALKS);

        PlacementUtils.register(context, GOLDITE_GRASS_BONEMEAL, goldite_stalks, PlacementUtils.isEmpty());
        PlacementUtils.register(context, EUCA_GOLD_GRASS_BONEMEAL, gold_grass, PlacementUtils.isEmpty());
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, name));
    }
}