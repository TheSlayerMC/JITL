package net.jitl.common.world.gen.tree_grower.decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.jitl.common.block.base.AttachedBlock;
import net.jitl.common.world.gen.JTreeDecorators;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.jetbrains.annotations.NotNull;

public class FrozenTreeDecorator extends TreeDecorator {

    public static final MapCodec<FrozenTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(FrozenTreeDecorator::new,
            (decorator) -> decorator.probability);
    private final float probability;

    public FrozenTreeDecorator(float float_) {
        this.probability = float_;
    }

    @Override
    protected @NotNull TreeDecoratorType<?> type() {
        return JTreeDecorators.FROZEN_DECORATOR.get();
    }

    @Override
    public void place(TreeDecorator.Context context) {
        RandomSource random = context.random();
        if (!(random.nextFloat() >= this.probability)) {
            int i = context.leaves().get(0).getY();
            context.leaves().stream().filter((blockPos_) -> blockPos_.getY() - i <= 16).forEach((blockPos1_) -> {
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    if (random.nextFloat() <= 0.25F) {
                        Direction direction1 = direction.getOpposite();
                        BlockPos blockpos = blockPos1_.offset(direction1.getStepX(), 0, direction1.getStepZ());
                        if (context.isAir(blockpos)) {
                            BlockState blockstate = JBlocks.FROST_CRYSTAL_LARGE.get().defaultBlockState().setValue(AttachedBlock.FACING, direction.getOpposite());
                            context.setBlock(blockpos, blockstate);
                        }
                    }
                }
            });
        }
    }
}