package net.jitl.common.world.dimension;

import com.google.common.collect.ImmutableSet;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Dimensions {

    public static final DeferredRegister<PoiType> REGISTRY = DeferredRegister.create(ForgeRegistries.POI_TYPES, JITL.MODID);

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

    public static final ResourceKey<Level> CORBA = ResourceKey.create(Registries.DIMENSION, JITL.rl("corba"));
    public static final ResourceKey<DimensionType> CORBA_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, JITL.rl("corba"));

    public static final ResourceKey<Level> TERRANIA = ResourceKey.create(Registries.DIMENSION, JITL.rl("terrania"));
    public static final ResourceKey<DimensionType> TERRANIA_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, JITL.rl("terrania"));
    public static final ResourceLocation TERRANIA_EFFECTS = JITL.rl("terrania");

    public static final ResourceKey<Level> CLOUDIA = ResourceKey.create(Registries.DIMENSION, JITL.rl("cloudia"));
    public static final ResourceKey<DimensionType> CLOUDIA_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, JITL.rl("cloudia"));
    public static final ResourceLocation CLOUDIA_EFFECTS = JITL.rl("cloudia");

    public static final RegistryObject<PoiType> FROZEN_PORTAL = REGISTRY.register("frozen_portal",
            () -> new PoiType(ImmutableSet.copyOf(JBlocks.FROZEN_PORTAL.get().getStateDefinition().getPossibleStates()), 0, 1));

    public static final RegistryObject<PoiType> DEPTHS_PORTAL = REGISTRY.register("depths_portal",
            () -> new PoiType(ImmutableSet.copyOf(JBlocks.DEPTHS_PORTAL.get().getStateDefinition().getPossibleStates()), 0, 1));

    public static final RegistryObject<PoiType> EUCA_PORTAL = REGISTRY.register("euca_portal",
            () -> new PoiType(ImmutableSet.copyOf(JBlocks.EUCA_PORTAL.get().getStateDefinition().getPossibleStates()), 0, 1));

    public static final RegistryObject<PoiType> BOIL_PORTAL = REGISTRY.register("boil_portal",
            () -> new PoiType(ImmutableSet.copyOf(JBlocks.BOIL_PORTAL.get().getStateDefinition().getPossibleStates()), 0, 1));

    public static final RegistryObject<PoiType> CORBA_PORTAL = REGISTRY.register("corba_portal",
            () -> new PoiType(ImmutableSet.copyOf(JBlocks.CORBA_PORTAL.get().getStateDefinition().getPossibleStates()), 0, 1));

    public static final RegistryObject<PoiType> TERRANIAN_PORTAL = REGISTRY.register("terranian_portal",
            () -> new PoiType(ImmutableSet.copyOf(JBlocks.TERRANIAN_PORTAL.get().getStateDefinition().getPossibleStates()), 0, 1));

    public static final RegistryObject<PoiType> CLOUDIA_PORTAL = REGISTRY.register("cloudia_portal",
            () -> new PoiType(ImmutableSet.copyOf(JBlocks.CLOUDIA_PORTAL.get().getStateDefinition().getPossibleStates()), 0, 1));
}