package net.jitl.common.world.gen;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockStateMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class JConfiguredFeatures {

    public static final RuleTest GRASS = new BlockStateMatchTest(Blocks.GRASS_BLOCK.defaultBlockState());
    public static final RuleTest SAND = new BlockStateMatchTest(Blocks.SAND.defaultBlockState());
    public static final RuleTest EUCA_GRASS = new TagMatchTest(JTags.EUCA_GRASS);

    //FROZEN
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_FROZEN_TREE = registerKey("small_frozen_tree"),
            MEDIUM_FROZEN_TREE = registerKey("medium_frozen_tree"),
            LARGE_FROZEN_TREE = registerKey("large_frozen_tree"),
            LARGE_FROZEN_BITTERWOOD_TREE = registerKey("large_frozen_bitterwood_tree"),
            MEDIUM_FROZEN_BITTERWOOD_TREE = registerKey("medium_frozen_bitterwood_tree"),
            SMALL_FROZEN_BITTERWOOD_TREE = registerKey("small_frozen_bitterwood_tree");
    public static void register(BootstapContext<ConfiguredFeature<?, ?>> context) {
        //FROZEN
         register(context, LARGE_FROZEN_BITTERWOOD_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.FROZEN_LOG.get().defaultBlockState()), new GiantTrunkPlacer(15, 7, 7), BlockStateProvider.simple(JBlocks.FROZEN_LEAVES.get().defaultBlockState()), new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(2)),new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.CRUMBLED_PERMAFROST.get())).build());
        register(context, MEDIUM_FROZEN_BITTERWOOD_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.FROZEN_LOG.get().defaultBlockState()), new GiantTrunkPlacer(10, 7, 7), BlockStateProvider.simple(JBlocks.FROZEN_LEAVES.get().defaultBlockState()), new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(2)), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.CRUMBLED_PERMAFROST.get())).build());
        register(context, SMALL_FROZEN_BITTERWOOD_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.FROZEN_LOG.get().defaultBlockState()), new StraightTrunkPlacer(4, 2, 3), BlockStateProvider.simple(JBlocks.FROZEN_LEAVES.get().defaultBlockState()), new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(2)), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.GRASSY_PERMAFROST.get())).build());
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