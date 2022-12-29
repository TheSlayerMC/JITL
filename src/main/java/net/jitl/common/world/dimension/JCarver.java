package net.jitl.common.world.dimension;

import net.jitl.common.world.carver.BoilCaveCarver;
import net.jitl.common.world.carver.FrozenCaveCarver;
import net.jitl.core.init.JITL;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JCarver {

    public static final DeferredRegister<WorldCarver<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, JITL.MODID);

    public static final RegistryObject<WorldCarver<CaveCarverConfiguration>> FROZEN_CARVER = REGISTRY.register("frozen_cave", () -> new FrozenCaveCarver(CaveCarverConfiguration.CODEC));
    public static final RegistryObject<WorldCarver<CaveCarverConfiguration>> BOIL_CARVER = REGISTRY.register("boil_cave", () -> new BoilCaveCarver(CaveCarverConfiguration.CODEC));
}
