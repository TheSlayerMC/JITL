package net.jitl.common.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import net.jitl.common.block.AttachedBlock;
import net.jitl.common.world.gen.JTreeDecorators;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class FrozenTreeDecorator extends TreeDecorator {

    public static final Codec<FrozenTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(FrozenTreeDecorator::new,
            (decorator) -> decorator.probability).codec();
    private final float probability;

    public FrozenTreeDecorator(float float_) {
        this.probability = float_;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return JTreeDecorators.FROZEN_DECORATOR.get();
    }

    @Override
    public void place(Context context) {
        RandomSource random = context.random();
        if (!(random.nextFloat() >= this.probability)) {
            int i = context.logs().get(0).getY();
            context.logs().stream().filter((blockPos_) -> blockPos_.getY() - i <= 16).forEach((blockPos1_) -> {
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    if (random.nextFloat() <= 0.25F) {
                        Direction direction1 = direction.getOpposite();
                        BlockPos blockpos = blockPos1_.offset(direction1.getStepX(), 0, direction1.getStepZ());
                        if (context.isAir( blockpos)) {
                            BlockState blockstate = JBlocks.FROST_CRYSTAL_LARGE.get().defaultBlockState().setValue(AttachedBlock.FACING, direction.getOpposite());
                            context.setBlock(blockpos, blockstate);
                        }
                    }
                }
            });
        }
    }
}