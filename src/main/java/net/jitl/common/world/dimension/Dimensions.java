package net.jitl.common.world.dimension;

import com.google.common.collect.ImmutableSet;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import software.bernie.example.registry.BlockRegistry;

public class Dimensions {

    public static final DeferredRegister<PoiType> REGISTRY = DeferredRegister.create(ForgeRegistries.POI_TYPES, JITL.MODID);

    public static final ResourceKey<Level> FROZEN_LANDS = ResourceKey.create(Registry.DIMENSION_REGISTRY, JITL.rl("frozen"));
    public static final ResourceKey<DimensionType> FROZEN_LANDS_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, JITL.rl("frozen"));

    public static final RegistryObject<PoiType> FROZEN_PORTAL = REGISTRY.register("frozen_portal",
            () -> new PoiType(ImmutableSet.copyOf(JBlocks.FROZEN_PORTAL.get().getStateDefinition().getPossibleStates()), 0, 1));
}