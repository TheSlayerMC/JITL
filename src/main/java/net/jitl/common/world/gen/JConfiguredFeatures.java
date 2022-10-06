package net.jitl.common.world.gen;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import net.jitl.common.world.gen.ruins.RuinsFeatureConfig;
import net.jitl.common.world.gen.tree_grower.SphericalFoliagePlacer;
import net.jitl.common.world.gen.treedecorator.CharredBrushTreeDecorator;
import net.jitl.common.world.gen.treedecorator.FrozenTreeDecorator;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.PineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockStateMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class JConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, JITL.MODID);

    public static final RuleTest EUCA_ORE_REPLACEABLES = new TagMatchTest(JTags.EUCA_STONE_ORE_REPLACEABLES);
    public static final RuleTest BOIL_ORE_REPLACEABLES = new TagMatchTest(JTags.BOIL_STONE_ORE_REPLACEABLES);
    public static final RuleTest FROZEN_ORE_REPLACEABLES = new TagMatchTest(JTags.FROZEN_STONE_ORE_REPLACEABLES);
    public static final RuleTest GRASS = new BlockStateMatchTest(Blocks.GRASS_BLOCK.defaultBlockState());
    public static final RuleTest SAND = new BlockStateMatchTest(Blocks.SAND.defaultBlockState());
    public static final RuleTest EUCA_GRASS = new TagMatchTest(JTags.EUCA_GRASS);

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

    private static final Supplier<List<OreConfiguration.TargetBlockState>> BLAZIUM_TARGET = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(BOIL_ORE_REPLACEABLES, JBlocks.BLAZIUM_ORE.get().defaultBlockState()))
    );

    private static final Supplier<List<OreConfiguration.TargetBlockState>> ASHUAL_TARGET = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(BOIL_ORE_REPLACEABLES, JBlocks.ASHUAL_ORE.get().defaultBlockState()))
    );

    private static final Supplier<List<OreConfiguration.TargetBlockState>> RIMESTONE_TARGET = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(FROZEN_ORE_REPLACEABLES, JBlocks.RIMESTONE_ORE.get().defaultBlockState()))
    );

    private static final Supplier<List<OreConfiguration.TargetBlockState>> PERIDOT_TARGET = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(FROZEN_ORE_REPLACEABLES, JBlocks.PERIDOT_ORE.get().defaultBlockState()))
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

    public static final RegistryObject<ConfiguredFeature<?, ?>> BLAZIUM_ORE = CONFIGURED_FEATURES.register("blazium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(BLAZIUM_TARGET.get(), 7)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ASHUAL_ORE = CONFIGURED_FEATURES.register("ashual_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ASHUAL_TARGET.get(), 7)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> RIMESTONE_ORE = CONFIGURED_FEATURES.register("rimestone_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(RIMESTONE_TARGET.get(), 7)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> PERIDOT_ORE = CONFIGURED_FEATURES.register("peridot_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(PERIDOT_TARGET.get(), 7)));

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

    public static final RegistryObject<ConfiguredFeature<?, ?>> EUCA_BOULDER = CONFIGURED_FEATURES.register("euca_boulder",
                    () -> new ConfiguredFeature<>(JFeatures.BOULDER.get(),
                            new BlockStateConfiguration(JBlocks.GOLDITE_COBBLESTONE.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> EUCA_WATER = CONFIGURED_FEATURES.register("euca_water",
                    () -> new ConfiguredFeature<>(JFeatures.EUCA_WATER_GEN.get(),
                            new SpringConfiguration(Fluids.WATER.defaultFluidState(), false, 4, 1,
                                    HolderSet.direct(JBlocks.GOLDITE_STONE.getHolder().get(), JBlocks.GOLDITE_DIRT.getHolder().get()))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> VOLCANIC_ROCK = CONFIGURED_FEATURES.register("volcanic_rock",
           () -> new ConfiguredFeature<>(JFeatures.VOLCANIC_ROCK.get(), new NoneFeatureConfiguration()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> BOIL_STALAGMITE = CONFIGURED_FEATURES.register("scorched_stalagmite",
            () -> new ConfiguredFeature<>(JFeatures.SCORCHED_STALAGMITE.get(), new NoneFeatureConfiguration()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> SULPHUR_DEPOSIT = CONFIGURED_FEATURES.register("sulphur_deposit",
            () -> new ConfiguredFeature<>(JFeatures.SULPHUR_DEPOSIT.get(), new BlockStateConfiguration(JBlocks.SULPHUR_ROCK.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> SULPHUR_CRYSTAL = CONFIGURED_FEATURES.register("sulphur_crystal",
            () -> new ConfiguredFeature<>(JFeatures.SULPHUR_CRYSTAL.get(), new NoneFeatureConfiguration()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> GOLD_VEG = CONFIGURED_FEATURES.register("gold_veg",
            () -> new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(96, 6, 2,
                    PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L,
                            new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F,
                                List.of(JBlocks.EUCA_SILVER_FLOWER.get().defaultBlockState(),
                                JBlocks.EUCA_TALL_FLOWERS.get().defaultBlockState(),
                                JBlocks.EUCA_TALL_GRASS.get().defaultBlockState())))))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> GOLDITE_VEG = CONFIGURED_FEATURES.register("goldite_veg",
            () -> new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(40, 6, 2,
                    PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L,
                            new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F,
                            List.of(JBlocks.GOLDITE_STALKS.get().defaultBlockState(),
                                    JBlocks.GOLDITE_FLOWER.get().defaultBlockState(),
                                    JBlocks.GOLDITE_BULB.get().defaultBlockState())))))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> PATCH_FIRE = CONFIGURED_FEATURES.register("boil_fire",
            () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(50, 6, 2,
                    PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L,
                            new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(Blocks.FIRE.defaultBlockState())))))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> BOIL_SANDS_VEG = CONFIGURED_FEATURES.register("boil_sands_veg",
            () -> new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(60, 6, 2,
                    PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L,
                            new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F,
                            List.of(JBlocks.LAVA_BLOOM.get().defaultBlockState(),
                                    JBlocks.CRUMBLING_PINE.get().defaultBlockState(),
                                    JBlocks.INFERNO_BUSH.get().defaultBlockState())))))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> CHARRED_FIELDS_VEG = CONFIGURED_FEATURES.register("charred_veg",
            () -> new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(96, 6, 2,
                    PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L,
                            new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F,
                            List.of(JBlocks.CHARRED_BRUSH.get().defaultBlockState(),
                                    JBlocks.CHARRED_GRASS.get().defaultBlockState(),
                                    JBlocks.CHARRED_WEEDS.get().defaultBlockState(),
                                    JBlocks.CHARRED_SHORT_GRASS.get().defaultBlockState())))))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> BOIL_PLAINS_VEG = CONFIGURED_FEATURES.register("boil_plains",
            () -> new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(60, 6, 2,
                    PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L,
                            new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F,
                            List.of(JBlocks.INFERNO_BUSH.get().defaultBlockState(),
                                    JBlocks.FLAME_POD.get().defaultBlockState(),
                                    JBlocks.CRISP_GRASS.get().defaultBlockState(),
                                    JBlocks.LAVA_BLOOM.get().defaultBlockState())))))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> SCORCHED_CACTUS = CONFIGURED_FEATURES.register("scorched_cactus",
            () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(10,
                    PlacementUtils.inlinePlaced(Feature.BLOCK_COLUMN,
                            BlockColumnConfiguration.simple(BiasedToBottomInt.of(1, 5),
                                    BlockStateProvider.simple(JBlocks.SCORCHED_CACTUS.get())),
                            BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                    BlockPredicate.wouldSurvive(JBlocks.SCORCHED_CACTUS.get().defaultBlockState(), BlockPos.ZERO)))))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> LARGE_CHARRED_TREE = CONFIGURED_FEATURES.register("large_charred_tree",
            () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(JBlocks.BURNED_BARK.get()),
                    new ForkingTrunkPlacer(5, 5, 5),
                    BlockStateProvider.simple(JBlocks.CHARRED_LEAVES.get()),
                    new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines()
                    .forceDirt()
                    .dirt(BlockStateProvider.simple(JBlocks.CHARRED_GRASS.get()))
                    .decorators(List.of(CharredBrushTreeDecorator.INSTANCE))
                    .build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> DYING_BURNED_TREE = CONFIGURED_FEATURES.register("dying_burned_tree",
            () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(JBlocks.BURNED_BARK.get()),
                    new ForkingTrunkPlacer(2, 1, 1),
                    BlockStateProvider.simple(JBlocks.CHARRED_LEAVES.get()),
                    new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
                    new TwoLayersFeatureSize(1, 1, 2))
                    .forceDirt()
                    .dirt(BlockStateProvider.simple(JBlocks.VOLCANIC_SAND.get()))
                    .build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> MEDIUM_BURNED_TREE = CONFIGURED_FEATURES.register("medium_burned_tree",
            () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(JBlocks.BURNED_BARK.get()),
                    new ForkingTrunkPlacer(4, 4, 4),
                    BlockStateProvider.simple(JBlocks.CHARRED_LEAVES.get()),
                    new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 2),
                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines()
                    .decorators(ImmutableList.of(CharredBrushTreeDecorator.INSTANCE))
                    .forceDirt()
                    .dirt(BlockStateProvider.simple(JBlocks.CHARRED_GRASS.get()))
                    .build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> SMALL_BURNED_TREE = CONFIGURED_FEATURES.register("small_burned_tree",
            () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(JBlocks.BURNED_BARK.get()),
                    new ForkingTrunkPlacer(3, 3, 3),
                    BlockStateProvider.simple(JBlocks.CHARRED_LEAVES.get()),
                    new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines()
                    .decorators(ImmutableList.of(CharredBrushTreeDecorator.INSTANCE))
                    .forceDirt()
                    .dirt(BlockStateProvider.simple(JBlocks.CHARRED_GRASS.get()))
                    .build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> DEFAULT_OVERWORLD_RUINS = CONFIGURED_FEATURES.register("default_overworld_ruins",
                    () -> new ConfiguredFeature<>(JFeatures.RUINS.get()
                            , new RuinsFeatureConfig(GRASS,
                            BlockStateProvider.simple(JBlocks.JOURNEY_CHEST.get()),
                                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                                            .add(Blocks.STONE_BRICKS.defaultBlockState(), 6)
                                            .add(Blocks.CRACKED_STONE_BRICKS.defaultBlockState(), 5)
                                            .add(Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 4)
                                            .add(Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 3)
                                            .add(Blocks.COBBLESTONE.defaultBlockState(), 4)
                                            .add(Blocks.CHISELED_STONE_BRICKS.defaultBlockState(), 2)
                                            .add(Blocks.INFESTED_COBBLESTONE.defaultBlockState(), 1)
                                            .add(Blocks.INFESTED_STONE_BRICKS.defaultBlockState(), 1)
                                            .add(Blocks.INFESTED_MOSSY_STONE_BRICKS.defaultBlockState(), 1)
                                            .add(Blocks.INFESTED_CRACKED_STONE_BRICKS.defaultBlockState(), 1)),
                                    5,
                                    5,
                                    8,
                                    BuiltInLootTables.ABANDONED_MINESHAFT)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> DESERT_OVERWORLD_RUINS =
            CONFIGURED_FEATURES.register("desert_overworld_ruins",
                    () -> new ConfiguredFeature<>(JFeatures.RUINS.get() ,new RuinsFeatureConfig(SAND,
                            BlockStateProvider.simple(JBlocks.JOURNEY_CHEST.get()),
                            new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                                            .add(Blocks.SANDSTONE.defaultBlockState(), 3)
                                            .add(Blocks.CHISELED_SANDSTONE.defaultBlockState(), 1)
                                            .add(Blocks.CUT_SANDSTONE.defaultBlockState(), 2)),
                                    5,
                                    5,
                                    8,
                                    BuiltInLootTables.DESERT_PYRAMID)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> EUCA_RUINS =
            CONFIGURED_FEATURES.register("euca_ruins",
                    () -> new ConfiguredFeature<>(JFeatures.RUINS.get() ,new RuinsFeatureConfig(EUCA_GRASS,
                            BlockStateProvider.simple(JBlocks.EUCA_CHEST.get()),
                            new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                                    .add(JBlocks.EUCA_SQUARE_BRICKS.get().defaultBlockState(), 3)
                                    .add(JBlocks.EUCA_SQUARE_RUNIC_BRICKS.get().defaultBlockState(), 1)
                                    .add(JBlocks.EUCA_RUNIC_BRICKS.get().defaultBlockState(), 2)),
                            7,
                            6,
                            9,
                            BuiltInLootTables.VILLAGE_WEAPONSMITH)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> EUCA_GOLDITE_RUINS =
            CONFIGURED_FEATURES.register("euca_goldite_ruins",
                    () -> new ConfiguredFeature<>(JFeatures.RUINS.get() ,new RuinsFeatureConfig(EUCA_GRASS,
                            BlockStateProvider.simple(JBlocks.EUCA_CHEST.get()),
                            new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                                    .add(JBlocks.EUCA_SQUARE_BRICKS.get().defaultBlockState(), 3)
                                    .add(JBlocks.EUCA_SQUARE_RUNIC_BRICKS.get().defaultBlockState(), 1)
                                    .add(JBlocks.EUCA_RUNIC_BRICKS.get().defaultBlockState(), 2)),
                            8,
                            6,
                            9,
                            BuiltInLootTables.VILLAGE_WEAPONSMITH)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> SMALL_FROZEN_TREE = CONFIGURED_FEATURES.register("small_frozen_tree",
            () -> new ConfiguredFeature<>(Feature.TREE,
                            new TreeConfiguration.TreeConfigurationBuilder(
                                    BlockStateProvider.simple(JBlocks.FROZEN_LOG.get().defaultBlockState()),
                                    new ForkingTrunkPlacer(2, 1, 3),
                                    BlockStateProvider.simple(JBlocks.FROZEN_LEAVES.get().defaultBlockState()),
                                    new PineFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(2)),
                                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines()
                                    //.decorators(ImmutableList.of(IcyBrushTreeDecorator.INSTANCE, new IceShroomTreeDecorator(0.2F)))
                                    .forceDirt()
                                    .dirt(BlockStateProvider.simple(JBlocks.GRASSY_PERMAFROST.get()))
                                    .build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> MEDIUM_FROZEN_TREE = CONFIGURED_FEATURES.register("medium_frozen_tree",
            () -> new ConfiguredFeature<>(Feature.TREE,
                            new TreeConfiguration.TreeConfigurationBuilder(
                                    BlockStateProvider.simple(JBlocks.FROZEN_LOG.get().defaultBlockState()),
                                    new FancyTrunkPlacer(10, 5, 5),
                                    BlockStateProvider.simple(JBlocks.FROZEN_LEAVES.get().defaultBlockState()),
                                    new PineFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(2)),
                                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines()
                                    //.decorators(ImmutableList.of(IcyBrushTreeDecorator.INSTANCE,new IceShroomTreeDecorator(0.2F), new CrystalFruitTreeDecorator(4)))
                                    .forceDirt()
                                    .dirt(BlockStateProvider.simple(JBlocks.GRASSY_PERMAFROST.get()))
                                    .build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> LARGE_FROZEN_TREE = CONFIGURED_FEATURES.register("large_frozen_tree",
                    () -> new ConfiguredFeature<>(Feature.TREE,
                            new TreeConfiguration.TreeConfigurationBuilder(
                                    BlockStateProvider.simple(JBlocks.FROZEN_LOG.get().defaultBlockState()),
                                    new FancyTrunkPlacer(15, 7, 7),
                                    BlockStateProvider.simple(JBlocks.FROZEN_LEAVES.get().defaultBlockState()),
                                    new PineFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(2)),
                                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines()
                                    //.decorators(ImmutableList.of(IcyBrushTreeDecorator.INSTANCE, new IceShroomTreeDecorator(0.2F), new CrystalFruitTreeDecorator(4)))
                                    .forceDirt()
                                    .dirt(BlockStateProvider.simple(JBlocks.CRUMBLED_PERMAFROST.get()))
                                    .build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> LARGE_FROZEN_BITTERWOOD_TREE = CONFIGURED_FEATURES.register("large_frozen_bitterwood_tree",
            () -> new ConfiguredFeature<>(Feature.TREE,
                            new TreeConfiguration.TreeConfigurationBuilder(
                                    BlockStateProvider.simple(JBlocks.FROZEN_LOG.get().defaultBlockState()),
                                    new GiantTrunkPlacer(15, 7, 7),
                                    BlockStateProvider.simple(JBlocks.FROZEN_LEAVES.get().defaultBlockState()),
                                    new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(2)),
                                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines()
                                    .decorators(ImmutableList.of(new FrozenTreeDecorator(0.01F)))
                                    .forceDirt()
                                    .dirt(BlockStateProvider.simple(JBlocks.CRUMBLED_PERMAFROST.get()))
                                    .build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> MEDIUM_FROZEN_BITTERWOOD_TREE = CONFIGURED_FEATURES.register("medium_frozen_bitterwood_tree",
                    () -> new ConfiguredFeature<>(Feature.TREE,
                            new TreeConfiguration.TreeConfigurationBuilder(
                                    BlockStateProvider.simple(JBlocks.FROZEN_LOG.get().defaultBlockState()),
                                    new GiantTrunkPlacer(10, 7, 7),
                                    BlockStateProvider.simple(JBlocks.FROZEN_LEAVES.get().defaultBlockState()),
                                    new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(2)),
                                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines()
                                    .decorators(ImmutableList.of(new FrozenTreeDecorator(0.01F)))
                                    .forceDirt()
                                    .dirt(BlockStateProvider.simple(JBlocks.CRUMBLED_PERMAFROST.get()))
                                    .build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> SMALL_FROZEN_BITTERWOOD_TREE = CONFIGURED_FEATURES.register("small_frozen_bitterwood_tree",
                    () -> new ConfiguredFeature<>(Feature.TREE,
                            new TreeConfiguration.TreeConfigurationBuilder(
                                    BlockStateProvider.simple(JBlocks.FROZEN_LOG.get().defaultBlockState()),
                                    new StraightTrunkPlacer(4, 2, 3),
                                    BlockStateProvider.simple(JBlocks.FROZEN_LEAVES.get().defaultBlockState()),
                                    new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(2)),
                                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines()
                                    .decorators(ImmutableList.of(new FrozenTreeDecorator(0.01F)))
                                    .forceDirt()
                                    .dirt(BlockStateProvider.simple(JBlocks.GRASSY_PERMAFROST.get()))
                                    .build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> FROZEN_VEG = CONFIGURED_FEATURES.register("frozen_veg",
            () -> new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(60, 6, 2,
                    PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L,
                            new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F,
                            List.of(JBlocks.ICE_BUSH.get().defaultBlockState(),
                                    JBlocks.FROSTBERRY_THORN.get().defaultBlockState())))))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> FROZEN_FLOWERS = CONFIGURED_FEATURES.register("frozen_flowers",
            () -> new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(60, 6, 2,
                    PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L,
                            new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F,
                            List.of(JBlocks.FROZEN_BLOOM.get().defaultBlockState(),
                                    JBlocks.ICE_BUD.get().defaultBlockState())))))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ICE_SPIKE = CONFIGURED_FEATURES.register("frozen_ice_spike",
            () -> new ConfiguredFeature<>(JFeatures.FROZEN_ICE_SPIKE.get(), new NoneFeatureConfiguration()));

}