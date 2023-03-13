package net.jitl.common.world.gen;

import net.jitl.common.world.gen.tree_grower.foliageplacers.SphericalFoliagePlacer;
import net.jitl.common.world.gen.tree_grower.foliageplacers.SwampFoliagePlacer;
import net.jitl.core.init.JITL;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JFoliagePlacers {

    public static final DeferredRegister<FoliagePlacerType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, JITL.MODID);

    public static final RegistryObject<FoliagePlacerType<SphericalFoliagePlacer>> SPHERICAL_FOLIAGE_PLACER = REGISTRY.register("spherical_foliage_placer", () -> new FoliagePlacerType<>(SphericalFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<SwampFoliagePlacer>> SWAMP_FOLIAGE_PLACER = REGISTRY.register("swamp_foliage_placer", () -> new FoliagePlacerType<>(SwampFoliagePlacer.CODEC));
}
