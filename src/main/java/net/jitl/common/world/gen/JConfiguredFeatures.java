package net.jitl.common.world.gen;

import com.google.common.base.Suppliers;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class JConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, JITL.MODID);

    private static final Supplier<List<OreConfiguration.TargetBlockState>> IRIDIUM_TARGET = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, JBlocks.IRIDIUM_ORE.get().defaultBlockState()),
                    OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, JBlocks.DEEPSLATE_IRIDIUM_ORE.get().defaultBlockState()))
    );

    private static final Supplier<List<OreConfiguration.TargetBlockState>> SAPPHIRE_TARGET = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, JBlocks.SAPPHIRE_ORE.get().defaultBlockState()),
                    OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, JBlocks.DEEPSLATE_SAPPHIRE_ORE.get().defaultBlockState()))
    );

    private static final Supplier<List<OreConfiguration.TargetBlockState>> SHADIUM_TARGET = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, JBlocks.SHADIUM_ORE.get().defaultBlockState()),
                    OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, JBlocks.DEEPSLATE_SHADIUM_ORE.get().defaultBlockState()))
    );

    private static final Supplier<List<OreConfiguration.TargetBlockState>> LUNIUM_TARGET = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, JBlocks.LUNIUM_ORE.get().defaultBlockState()),
                    OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, JBlocks.DEEPSLATE_LUNIUM_ORE.get().defaultBlockState()))
    );

    private static final Supplier<List<OreConfiguration.TargetBlockState>> WARPED_TARGET = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, JBlocks.WARPED_QUARTZ_ORE.get().defaultBlockState()))
    );

    private static final Supplier<List<OreConfiguration.TargetBlockState>> BLOODCRUST_TARGET = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, JBlocks.BLOODCRUST_ORE.get().defaultBlockState()))
    );

    private static final Supplier<List<OreConfiguration.TargetBlockState>> ENDERILLIUM_TARGET = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), JBlocks.ENDERILLIUM_ORE.get().defaultBlockState()))
    );


    public static final RegistryObject<ConfiguredFeature<?, ?>> IRIDIUM_ORE = CONFIGURED_FEATURES.register("iridium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(IRIDIUM_TARGET.get(), 7)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> SAPPHIRE_ORE = CONFIGURED_FEATURES.register("sapphire_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(SAPPHIRE_TARGET.get(), 7)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> SHADIUM_ORE = CONFIGURED_FEATURES.register("shadium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(SHADIUM_TARGET.get(), 7)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> LUNIUM_ORE = CONFIGURED_FEATURES.register("lunium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(LUNIUM_TARGET.get(), 7)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> WARPED_QUARTZ_ORE = CONFIGURED_FEATURES.register("warped_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(WARPED_TARGET.get(), 7)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> BLOODCRUST_ORE = CONFIGURED_FEATURES.register("bloodcrust_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(BLOODCRUST_TARGET.get(), 7)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ENDERILLIUM_ORE = CONFIGURED_FEATURES.register("enderillium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ENDERILLIUM_TARGET.get(), 7)));

}
