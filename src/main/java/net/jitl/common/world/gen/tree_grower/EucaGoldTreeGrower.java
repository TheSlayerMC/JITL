package net.jitl.common.world.gen.tree_grower;

import net.jitl.common.world.gen.JConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EucaGoldTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource random, boolean largeHive) {
        return JConfiguredFeatures.EUCA_GOLD_TREE.getHolder().get();
    }
}
