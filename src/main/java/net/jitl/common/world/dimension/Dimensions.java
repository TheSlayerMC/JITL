package net.jitl.common.world.dimension;

import net.jitl.core.init.JITL;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Dimensions {

    public static final DeferredRegister<PoiType> REGISTRY = DeferredRegister.create(ForgeRegistries.POI_TYPES, JITL.MODID);

    public static final ResourceKey<Level> FROZEN_LANDS = ResourceKey.create(Registry.DIMENSION_REGISTRY, JITL.rl("frozen"));
    public static final ResourceKey<DimensionType> FROZEN_LANDS_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, JITL.rl("frozen"));
    public static final ResourceKey<PoiType> FROZEN_PORTAL = ResourceKey.create(Registry.POINT_OF_INTEREST_TYPE_REGISTRY, JITL.rl("frozen"));

}