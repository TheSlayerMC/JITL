package net.jitl.common.world.dimension;

import net.jitl.core.init.JITL;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;


public class JBiomeRegistry {

    public static ResourceKey<Biome> EUCA_PLAINS = ResourceKey.create(Registries.BIOME, JITL.rl("euca/euca_plains"));
    public static ResourceKey<Biome> EUCA_GOLDITE_GRAINS = ResourceKey.create(Registries.BIOME, JITL.rl("euca/euca_goldite_grains"));

    public static ResourceKey<Biome> FROZEN_WASTES = ResourceKey.create(Registries.BIOME, JITL.rl("frozen/frozen_wastes"));
    public static ResourceKey<Biome> FROZEN_DYING_FOREST = ResourceKey.create(Registries.BIOME, JITL.rl("frozen/dying_forest"));
    public static ResourceKey<Biome> FROZEN_BITTERWOOD_FOREST = ResourceKey.create(Registries.BIOME, JITL.rl("frozen/bitterwood_forest"));

    public static ResourceKey<Biome> BOILING_PLAINS = ResourceKey.create(Registries.BIOME, JITL.rl("boil/boiling_plains"));
    public static ResourceKey<Biome> BOILING_SANDS = ResourceKey.create(Registries.BIOME, JITL.rl("boil/boiling_sands"));
    public static ResourceKey<Biome> CHARRED_FIELDS = ResourceKey.create(Registries.BIOME, JITL.rl("boil/charred_fields"));
    public static ResourceKey<Biome> SCORCHED_WASTELANDS = ResourceKey.create(Registries.BIOME, JITL.rl("boil/scorched_wastelands"));
}
