package net.jitl.common.world.dimension;

import net.jitl.core.init.JITL;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Dimensions {

    public static final DeferredRegister<PoiType> REGISTRY = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, JITL.MOD_ID);

    public static final ResourceKey<Level> OVERWORLD = Level.OVERWORLD;
    public static final ResourceKey<Level> NETHER = Level.NETHER;
    public static final ResourceKey<Level> THE_END = Level.END;

    public static final ResourceKey<Level> FROZEN_LANDS = ResourceKey.create(Registries.DIMENSION, JITL.rl("frozen"));
    public static final ResourceKey<DimensionType> FROZEN_LANDS_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, JITL.rl("frozen"));
    public static final ResourceLocation FROZEN_EFFECTS = JITL.rl("frozen");

    public static final ResourceKey<Level> EUCA = ResourceKey.create(Registries.DIMENSION, JITL.rl("euca"));
    public static final ResourceKey<DimensionType> EUCA_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, JITL.rl("euca"));
    public static final ResourceLocation EUCA_EFFECTS = JITL.rl("euca");

    public static final ResourceKey<Level> BOIL = ResourceKey.create(Registries.DIMENSION, JITL.rl("boil"));
    public static final ResourceKey<DimensionType> BOIL_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, JITL.rl("boil"));
    public static final ResourceLocation BOIL_EFFECTS = JITL.rl("boil");

    public static final ResourceKey<Level> DEPTHS = ResourceKey.create(Registries.DIMENSION, JITL.rl("depths"));
    public static final ResourceKey<DimensionType> DEPTHS_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, JITL.rl("depths"));
    public static final ResourceLocation DEPTHS_EFFECTS = JITL.rl("depths");

    public static final ResourceKey<Level> CORBA = ResourceKey.create(Registries.DIMENSION, JITL.rl("corba"));
    public static final ResourceKey<DimensionType> CORBA_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, JITL.rl("corba"));
    public static final ResourceLocation CORBA_EFFECTS = JITL.rl("corba");

    public static final ResourceKey<Level> TERRANIA = ResourceKey.create(Registries.DIMENSION, JITL.rl("terrania"));
    public static final ResourceKey<DimensionType> TERRANIA_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, JITL.rl("terrania"));
    public static final ResourceLocation TERRANIA_EFFECTS = JITL.rl("terrania");

    public static final ResourceKey<Level> CLOUDIA = ResourceKey.create(Registries.DIMENSION, JITL.rl("cloudia"));
    public static final ResourceKey<DimensionType> CLOUDIA_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, JITL.rl("cloudia"));
    public static final ResourceLocation CLOUDIA_EFFECTS = JITL.rl("cloudia");

    public static final ResourceKey<Level> SENTERIAN = ResourceKey.create(Registries.DIMENSION, JITL.rl("senterian"));
    public static final ResourceKey<DimensionType> SENTERIAN_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, JITL.rl("senterian"));

    public static class JBiomes {

        public static final ResourceKey<Biome> EUCA_GOLDITE_GRAINS = ResourceKey.create(Registries.BIOME, JITL.rl("euca/euca_goldite_grains"));
        public static final ResourceKey<Biome> EUCA_PLAINS = ResourceKey.create(Registries.BIOME, JITL.rl("euca/euca_plains"));

        public static final ResourceKey<Biome> BOIL = ResourceKey.create(Registries.BIOME, JITL.rl("boil/boil"));
        public static final ResourceKey<Biome> BOILING_SANDS = ResourceKey.create(Registries.BIOME, JITL.rl("boil/boiling_sands"));
        public static final ResourceKey<Biome> CHARRED_FIELDS = ResourceKey.create(Registries.BIOME, JITL.rl("boil/charred_fields"));
        public static final ResourceKey<Biome> SCORCHED_WASTELANDS = ResourceKey.create(Registries.BIOME, JITL.rl("boil/scorched_wastelands"));

        public static final ResourceKey<Biome> BITTERWOOD_FOREST = ResourceKey.create(Registries.BIOME, JITL.rl("frozen/bitterwood_forest"));
        public static final ResourceKey<Biome> DYING_FOREST = ResourceKey.create(Registries.BIOME, JITL.rl("frozen/dying_forest"));
        public static final ResourceKey<Biome> FROZEN_WASTES = ResourceKey.create(Registries.BIOME, JITL.rl("frozen/frozen_wastes"));

        public static final ResourceKey<Biome> DEPTHS = ResourceKey.create(Registries.BIOME, JITL.rl("depths/depths"));
        public static final ResourceKey<Biome> DARK_LANDS = ResourceKey.create(Registries.BIOME, JITL.rl("depths/dark_lands"));
        public static final ResourceKey<Biome> SHROOMLAND = ResourceKey.create(Registries.BIOME, JITL.rl("depths/shroomland"));

        public static final ResourceKey<Biome> BOGWEED_FIELDS = ResourceKey.create(Registries.BIOME, JITL.rl("corba/bogweed_fields"));
        public static final ResourceKey<Biome> CORBA_PLAINS = ResourceKey.create(Registries.BIOME, JITL.rl("corba/corba_plains"));
        public static final ResourceKey<Biome> TAINTED_FOREST = ResourceKey.create(Registries.BIOME, JITL.rl("corba/tainted_forest"));
        public static final ResourceKey<Biome> TAINTED_SWAMP = ResourceKey.create(Registries.BIOME, JITL.rl("corba/tainted_swamp"));

        public static final ResourceKey<Biome> MUSHROOM_FOREST = ResourceKey.create(Registries.BIOME, JITL.rl("terrania/mushroom_forest"));
        public static final ResourceKey<Biome> TERRANIA = ResourceKey.create(Registries.BIOME, JITL.rl("terrania/terrania"));

        public static final ResourceKey<Biome> CLOUDIA = ResourceKey.create(Registries.BIOME, JITL.rl("cloudia/cloudia"));

        public static final ResourceKey<Biome> SENTERIAN = ResourceKey.create(Registries.BIOME, JITL.rl("senterian/senterian"));

    }
}