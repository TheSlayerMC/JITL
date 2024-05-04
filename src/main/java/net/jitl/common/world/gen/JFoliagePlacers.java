package net.jitl.common.world.gen;

import net.jitl.common.world.gen.tree_grower.foliageplacers.SphericalFoliagePlacer;
import net.jitl.common.world.gen.tree_grower.foliageplacers.SwampFoliagePlacer;
import net.jitl.core.init.JITL;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.lang.reflect.Constructor;

public class JFoliagePlacers {

    public static final DeferredRegister<FoliagePlacerType<?>> REGISTRY = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, JITL.MODID);

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<SphericalFoliagePlacer>> SPHERICAL_FOLIAGE_PLACER = REGISTRY.register("spherical_foliage_placer", () -> new FoliagePlacerType<>(SphericalFoliagePlacer.CODEC));
    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<SwampFoliagePlacer>> SWAMP_FOLIAGE_PLACER = REGISTRY.register("swamp_foliage_placer", () -> new FoliagePlacerType<>(SwampFoliagePlacer.CODEC));
}
