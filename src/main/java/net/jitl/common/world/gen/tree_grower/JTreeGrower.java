package net.jitl.common.world.gen.tree_grower;

import net.jitl.core.data.world_gen.JConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class JTreeGrower {

    public static final AbstractTreeGrower EUCA_GOLD_TREE_GROWER = new AbstractTreeGrower() {
        @Nullable
        @Override
        protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource r, boolean b) {
            return JConfiguredFeatures.EUCA_GOLD_TREE;
        }
    };
    public static final AbstractTreeGrower EUCA_GREEN_TREE_GROWER = new AbstractTreeGrower() {
        @Nullable
        @Override
        protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource r, boolean b) {
            return JConfiguredFeatures.EUCA_GREEN_TREE;
        }
    };

    public static final AbstractTreeGrower DEPTHS_TREE_GROWER = new AbstractTreeGrower() {
        @Nullable
        @Override
        protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource r, boolean b) {
            return JConfiguredFeatures.DEPTHS_TREE;
        }
    };

    public static final AbstractTreeGrower BOGWOOD_TREE_GROWER = new AbstractTreeGrower() {
        @Nullable
        @Override
        protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource r, boolean b) {
            return JConfiguredFeatures.CORBA_SWAMP_TREE;
        }
    };

    public static final AbstractTreeGrower CORBA_TREE_GROWER = new AbstractTreeGrower() {
        @Nullable
        @Override
        protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
            return JConfiguredFeatures.CORBA_TREE_LARGE;
        }
    };

    public static final AbstractTreeGrower TERRRANIAN_TREE_GROWER = new AbstractTreeGrower() {
        @Nullable
        @Override
        protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
            return JConfiguredFeatures.MEGA_TERRANIAN_TREE;
        }
    };

}
