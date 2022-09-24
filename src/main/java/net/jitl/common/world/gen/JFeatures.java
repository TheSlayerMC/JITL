package net.jitl.common.world.gen;

import net.jitl.common.world.gen.euca.BoulderFeature;
import net.jitl.core.init.JITL;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JFeatures {

    public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, JITL.MODID);

    public static final RegistryObject<Feature<BlockStateConfiguration>> BOULDER = REGISTRY.register("boulder", () -> new BoulderFeature(BlockStateConfiguration.CODEC));

}
