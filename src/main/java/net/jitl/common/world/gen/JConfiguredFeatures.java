package net.jitl.common.world.gen;

import net.jitl.common.world.gen.ruins.RuinsFeatureConfig;
import net.jitl.common.world.gen.tree_grower.SphericalFoliagePlacer;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JTags;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.PineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockStateMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JConfiguredFeatures {

    private JConfiguredFeatures() {

    }

    public static final RuleTest GRASS = new BlockStateMatchTest(Blocks.GRASS_BLOCK.defaultBlockState());
    public static final RuleTest SAND = new BlockStateMatchTest(Blocks.SAND.defaultBlockState());
    public static final RuleTest EUCA_GRASS = new TagMatchTest(JTags.EUCA_GRASS);

    //OVERWORLD
    public static final ResourceKey<ConfiguredFeature<?, ?>> DESERT_OVERWORLD_RUINS = registerKey("desert_overworld_ruins"),
            DEFAULT_OVERWORLD_RUINS = registerKey("default_overworld_ruins");

    //EUCA
    public static final ResourceKey<ConfiguredFeature<?, ?>> EUCA_GOLD_TREE = registerKey("euca_gold_tree"),
            EUCA_GREEN_TREE = registerKey("euca_green_tree"),
            EUCA_BOULDER = registerKey("euca_boulder"),
            EUCA_GOLDITE_RUINS = registerKey("euca_goldite_ruins"),
            EUCA_RUINS = registerKey("euca_ruins"),
            EUCA_WATER = registerKey("euca_water");

    //DEPTHS
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEPTHS_LAMP_ROOF = registerKey("depths_lamp_roof"),
            DEPTHS_TREE = registerKey("depths_tree"),
            DEPTHS_CRYSTAL = registerKey("depths_crystal");

    //BOIL
    public static final ResourceKey<ConfiguredFeature<?, ?>> VOLCANIC_ROCK = registerKey("volcanic_rock"),
            BOIL_STALAGMITE = registerKey("scorched_stalagmite"),
            SULPHUR_DEPOSIT = registerKey("sulphur_deposit"),
            SULPHUR_CRYSTAL = registerKey("sulphur_crystal"),
            LARGE_CHARRED_TREE = registerKey("large_charred_tree"),
            DYING_BURNED_TREE = registerKey("dying_burned_tree"),
            MEDIUM_BURNED_TREE = registerKey("medium_burned_tree"),
            SMALL_BURNED_TREE = registerKey("small_burned_tree");

    //FROZEN
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_FROZEN_TREE = registerKey("small_frozen_tree"),
            MEDIUM_FROZEN_TREE = registerKey("medium_frozen_tree"),
            LARGE_FROZEN_TREE = registerKey("large_frozen_tree"),
            LARGE_FROZEN_BITTERWOOD_TREE = registerKey("large_frozen_bitterwood_tree"),
            MEDIUM_FROZEN_BITTERWOOD_TREE = registerKey("medium_frozen_bitterwood_tree"),
            SMALL_FROZEN_BITTERWOOD_TREE = registerKey("small_frozen_bitterwood_tree"),
            ICE_SPIKE = registerKey("frozen_ice_spike");

    public static void register(BootstapContext<ConfiguredFeature<?, ?>> context) {
        //OVERWORLD
        register(context, DESERT_OVERWORLD_RUINS, JFeatures.RUINS.get(), new RuinsFeatureConfig(SAND, BlockStateProvider.simple(JBlocks.JOURNEY_CHEST.get()), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.SANDSTONE.defaultBlockState(), 3).add(Blocks.CHISELED_SANDSTONE.defaultBlockState(), 1).add(Blocks.CUT_SANDSTONE.defaultBlockState(), 2)), 5, 5, 8, BuiltInLootTables.DESERT_PYRAMID));
        register(context, DEFAULT_OVERWORLD_RUINS, JFeatures.RUINS.get(), new RuinsFeatureConfig(GRASS, BlockStateProvider.simple(JBlocks.JOURNEY_CHEST.get()), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.STONE_BRICKS.defaultBlockState(), 6).add(Blocks.CRACKED_STONE_BRICKS.defaultBlockState(), 5).add(Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 4).add(Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 3).add(Blocks.COBBLESTONE.defaultBlockState(), 4).add(Blocks.CHISELED_STONE_BRICKS.defaultBlockState(), 2).add(Blocks.INFESTED_COBBLESTONE.defaultBlockState(), 1).add(Blocks.INFESTED_STONE_BRICKS.defaultBlockState(), 1).add(Blocks.INFESTED_MOSSY_STONE_BRICKS.defaultBlockState(), 1).add(Blocks.INFESTED_CRACKED_STONE_BRICKS.defaultBlockState(), 1)),5, 5, 8, BuiltInLootTables.ABANDONED_MINESHAFT));

        //EUCA
        register(context, EUCA_GOLD_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.EUCA_GOLD_LOG.get()), new ForkingTrunkPlacer(4, 1, 6), BlockStateProvider.simple(JBlocks.EUCA_GOLD_LEAVES.get()), new SphericalFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 1), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().dirt(BlockStateProvider.simple(JBlocks.GOLDITE_DIRT.get())).build());
        register(context, EUCA_GREEN_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.EUCA_BROWN_LOG.get()), new ForkingTrunkPlacer(4, 1, 6), BlockStateProvider.simple(JBlocks.EUCA_GREEN_LEAVES.get()), new SphericalFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 1), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().dirt(BlockStateProvider.simple(JBlocks.GOLDITE_DIRT.get())).build());
        register(context, EUCA_BOULDER, JFeatures.BOULDER.get(), new BlockStateConfiguration(JBlocks.GOLDITE_COBBLESTONE.get().defaultBlockState()));
        register(context, EUCA_WATER, JFeatures.EUCA_WATER_GEN.get(), new SpringConfiguration(Fluids.WATER.defaultFluidState(), false, 4, 1, HolderSet.direct(JBlocks.GOLDITE_STONE.getHolder().get(), JBlocks.GOLDITE_DIRT.getHolder().get())));
        register(context, EUCA_GOLDITE_RUINS, JFeatures.RUINS.get(), new RuinsFeatureConfig(EUCA_GRASS, BlockStateProvider.simple(JBlocks.EUCA_CHEST.get()), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(JBlocks.EUCA_SQUARE_BRICKS.get().defaultBlockState(), 3).add(JBlocks.EUCA_SQUARE_RUNIC_BRICKS.get().defaultBlockState(), 1).add(JBlocks.EUCA_RUNIC_BRICKS.get().defaultBlockState(), 2)), 8, 6, 9, BuiltInLootTables.VILLAGE_WEAPONSMITH));
        register(context, EUCA_RUINS, JFeatures.RUINS.get(), new RuinsFeatureConfig(EUCA_GRASS, BlockStateProvider.simple(JBlocks.EUCA_CHEST.get()), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(JBlocks.EUCA_SQUARE_BRICKS.get().defaultBlockState(), 3).add(JBlocks.EUCA_SQUARE_RUNIC_BRICKS.get().defaultBlockState(), 1).add(JBlocks.EUCA_RUNIC_BRICKS.get().defaultBlockState(), 2)), 7, 6, 9, BuiltInLootTables.VILLAGE_WEAPONSMITH));

        //DEPTHS
        register(context, DEPTHS_LAMP_ROOF, JFeatures.ROOF_DEPTHS_LAMP.get(), new NoneFeatureConfiguration());
        register(context, DEPTHS_CRYSTAL, JFeatures.DEPTHS_CRYSTAL.get(), new NoneFeatureConfiguration());
        register(context, DEPTHS_TREE, Feature.TREE, createStraightBlobTree(JBlocks.DEPTHS_LOG.get(), JBlocks.DEPTHS_LEAVES.get(), 7, 3, 0, 2).ignoreVines().build());

        //BOIL
        register(context, VOLCANIC_ROCK, JFeatures.VOLCANIC_ROCK.get(), new NoneFeatureConfiguration());
        register(context, BOIL_STALAGMITE, JFeatures.SCORCHED_STALAGMITE.get(), new NoneFeatureConfiguration());
        register(context, SULPHUR_DEPOSIT, JFeatures.SULPHUR_DEPOSIT.get(), new BlockStateConfiguration(JBlocks.SULPHUR_ROCK.get().defaultBlockState()));
        register(context, SULPHUR_CRYSTAL, JFeatures.SULPHUR_CRYSTAL.get(), new NoneFeatureConfiguration());
        register(context, LARGE_CHARRED_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.BURNED_BARK.get()), new ForkingTrunkPlacer(5, 5, 5), BlockStateProvider.simple(JBlocks.CHARRED_LEAVES.get()), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.CHARRED_GRASS.get())).build());
        register(context, DYING_BURNED_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.BURNED_BARK.get()), new ForkingTrunkPlacer(2, 1, 1), BlockStateProvider.simple(JBlocks.CHARRED_LEAVES.get()), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(1, 1, 2)).forceDirt().dirt(BlockStateProvider.simple(JBlocks.VOLCANIC_SAND.get())).build());
        register(context, MEDIUM_BURNED_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.BURNED_BARK.get()), new ForkingTrunkPlacer(4, 4, 4), BlockStateProvider.simple(JBlocks.CHARRED_LEAVES.get()), new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 2), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.CHARRED_GRASS.get())).build());
        register(context, SMALL_BURNED_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.BURNED_BARK.get()), new ForkingTrunkPlacer(3, 3, 3), BlockStateProvider.simple(JBlocks.CHARRED_LEAVES.get()), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.CHARRED_GRASS.get())).build());

        //FROZEN
        register(context, SMALL_FROZEN_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.FROZEN_LOG.get().defaultBlockState()), new ForkingTrunkPlacer(2, 1, 3), BlockStateProvider.simple(JBlocks.FROZEN_LEAVES.get().defaultBlockState()), new PineFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(2)), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.GRASSY_PERMAFROST.get())).build());
        register(context, DEPTHS_TREE, Feature.TREE, createStraightBlobTree(JBlocks.DEPTHS_LOG.get(), JBlocks.DEPTHS_LEAVES.get(), 7, 3, 0, 2).ignoreVines().build());
        register(context, MEDIUM_FROZEN_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.FROZEN_LOG.get().defaultBlockState()), new FancyTrunkPlacer(10, 5, 5), BlockStateProvider.simple(JBlocks.FROZEN_LEAVES.get().defaultBlockState()), new PineFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(2)), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.GRASSY_PERMAFROST.get())).build());
        register(context, LARGE_FROZEN_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.FROZEN_LOG.get().defaultBlockState()), new FancyTrunkPlacer(15, 7, 7), BlockStateProvider.simple(JBlocks.FROZEN_LEAVES.get().defaultBlockState()), new PineFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(2)), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.CRUMBLED_PERMAFROST.get())).build());
        register(context, LARGE_FROZEN_BITTERWOOD_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.FROZEN_LOG.get().defaultBlockState()), new GiantTrunkPlacer(15, 7, 7), BlockStateProvider.simple(JBlocks.FROZEN_LEAVES.get().defaultBlockState()), new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(2)),new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.CRUMBLED_PERMAFROST.get())).build());
        register(context, MEDIUM_FROZEN_BITTERWOOD_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.FROZEN_LOG.get().defaultBlockState()), new GiantTrunkPlacer(10, 7, 7), BlockStateProvider.simple(JBlocks.FROZEN_LEAVES.get().defaultBlockState()), new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(2)), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.CRUMBLED_PERMAFROST.get())).build());
        register(context, SMALL_FROZEN_BITTERWOOD_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.FROZEN_LOG.get().defaultBlockState()), new StraightTrunkPlacer(4, 2, 3), BlockStateProvider.simple(JBlocks.FROZEN_LEAVES.get().defaultBlockState()), new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(2)), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.GRASSY_PERMAFROST.get())).build());
        register(context, ICE_SPIKE, JFeatures.FROZEN_ICE_SPIKE.get(), new NoneFeatureConfiguration());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(JITL.MODID, name));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block log, Block leaves, int baseHeight, int heightRandA, int heightRandB, int width) {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log), new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB), BlockStateProvider.simple(leaves), new BlobFoliagePlacer(ConstantInt.of(width), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1));
    }
}