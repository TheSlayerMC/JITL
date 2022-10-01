package net.jitl.common.world.gen;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import net.jitl.common.world.gen.tree_grower.SphericalFoliagePlacer;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JTags;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class JConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, JITL.MODID);

    public static final RuleTest EUCA_ORE_REPLACEABLES = new TagMatchTest(JTags.EUCA_STONE_ORE_REPLACEABLES);


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

    private static final Supplier<List<OreConfiguration.TargetBlockState>> CRIMSON_TARGET = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, JBlocks.CRIMSON_QUARTZ_ORE.get().defaultBlockState()))
    );

    private static final Supplier<List<OreConfiguration.TargetBlockState>> BLOODCRUST_TARGET = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, JBlocks.BLOODCRUST_ORE.get().defaultBlockState()))
    );

    private static final Supplier<List<OreConfiguration.TargetBlockState>> ENDERILLIUM_TARGET = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), JBlocks.ENDERILLIUM_ORE.get().defaultBlockState()))
    );

    private static final Supplier<List<OreConfiguration.TargetBlockState>> MEKYUM_TARGET = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(EUCA_ORE_REPLACEABLES, JBlocks.MEKYUM_ORE.get().defaultBlockState()))
    );

    private static final Supplier<List<OreConfiguration.TargetBlockState>> KORITE_TARGET = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(EUCA_ORE_REPLACEABLES, JBlocks.KORITE_ORE.get().defaultBlockState()))
    );

    private static final Supplier<List<OreConfiguration.TargetBlockState>> STORON_TARGET = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(EUCA_ORE_REPLACEABLES, JBlocks.STORON_ORE.get().defaultBlockState()))
    );

    private static final Supplier<List<OreConfiguration.TargetBlockState>> CELESTIUM_TARGET = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(EUCA_ORE_REPLACEABLES, JBlocks.CELESTIUM_ORE.get().defaultBlockState()))
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
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(WARPED_TARGET.get(), 12)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> CRIMSON_QUARTZ_ORE = CONFIGURED_FEATURES.register("crimson_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(CRIMSON_TARGET.get(), 12)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> BLOODCRUST_ORE = CONFIGURED_FEATURES.register("bloodcrust_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(BLOODCRUST_TARGET.get(), 7)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ENDERILLIUM_ORE = CONFIGURED_FEATURES.register("enderillium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ENDERILLIUM_TARGET.get(), 7)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> MEKYUM_ORE = CONFIGURED_FEATURES.register("mekyum_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(MEKYUM_TARGET.get(), 7)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> KORITE_ORE = CONFIGURED_FEATURES.register("korite_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(KORITE_TARGET.get(), 7)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> STORON_ORE = CONFIGURED_FEATURES.register("storon_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(STORON_TARGET.get(), 7)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> CELESTIUM_ORE = CONFIGURED_FEATURES.register("celestium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(CELESTIUM_TARGET.get(), 7)));


    public static final RegistryObject<ConfiguredFeature<?, ?>> EUCA_GOLD_TREE = CONFIGURED_FEATURES.register("euca_gold_tree",
            () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                                    BlockStateProvider.simple(JBlocks.EUCA_GOLD_LOG.get()),
                                    new ForkingTrunkPlacer(4, 1, 6),
                                    BlockStateProvider.simple(JBlocks.EUCA_GOLD_LEAVES.get()),
                                    new SphericalFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 1),
                                    new TwoLayersFeatureSize(1, 1, 2))
                                    .ignoreVines()
                                    .dirt(BlockStateProvider.simple(JBlocks.GOLDITE_DIRT.get())).build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> EUCA_GREEN_TREE = CONFIGURED_FEATURES.register("euca_green_tree",
            () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                                    BlockStateProvider.simple(JBlocks.EUCA_BROWN_LOG.get()),
                                    new ForkingTrunkPlacer(4, 1, 6),
                                    BlockStateProvider.simple(JBlocks.EUCA_GREEN_LEAVES.get()),
                                    new SphericalFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 1),
                                    new TwoLayersFeatureSize(1, 1, 2))
                                    //.decorators(ImmutableList.of(new GlimmerRootTreeDecorator(4)))
                                    .ignoreVines()
                                    .dirt(BlockStateProvider.simple(JBlocks.GOLDITE_DIRT.get()))
                                    .build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> EUCA_BOULDER =
            CONFIGURED_FEATURES.register("euca_boulder",
                    () -> new ConfiguredFeature<>(JFeatures.BOULDER.get(),
                            new BlockStateConfiguration(
                                    JBlocks.GOLDITE_STONE.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> GOLD_VEG = CONFIGURED_FEATURES.register("gold_veg",
            () -> new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(96, 6, 2,
                    PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L,
                            new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F,
                                List.of(JBlocks.EUCA_SILVER_FLOWER.get().defaultBlockState(),
                                JBlocks.EUCA_TALL_FLOWERS.get().defaultBlockState(),
                                JBlocks.EUCA_TALL_GRASS.get().defaultBlockState())))))));

}
