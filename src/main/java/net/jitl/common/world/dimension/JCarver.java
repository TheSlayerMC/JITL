package net.jitl.common.world.dimension;

import net.jitl.common.world.carver.BoilCaveCarver;
import net.jitl.common.world.carver.FrozenCaveCarver;
import net.jitl.core.init.JITL;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class JCarver {

    public static final DeferredRegister<WorldCarver<?>> REGISTRY = DeferredRegister.create(Registries.CARVER, JITL.MOD_ID);

    public static final DeferredHolder<WorldCarver<?>, WorldCarver<CaveCarverConfiguration>> FROZEN_CARVER = REGISTRY.register("frozen_cave", () -> new FrozenCaveCarver(CaveCarverConfiguration.CODEC));
    public static final DeferredHolder<WorldCarver<?>, WorldCarver<CaveCarverConfiguration>> BOIL_CARVER = REGISTRY.register("boil_cave", () -> new BoilCaveCarver(CaveCarverConfiguration.CODEC));
}
