package net.jitl.common.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import net.jitl.common.world.gen.JTreeDecorators;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.jetbrains.annotations.NotNull;

public class CharredBrushTreeDecorator extends TreeDecorator {
    public static final Codec<CharredBrushTreeDecorator> CODEC;
    public static final CharredBrushTreeDecorator INSTANCE = new CharredBrushTreeDecorator();

    @Override
    protected @NotNull TreeDecoratorType<?> type() {
        return JTreeDecorators.CHARRED_BRUSH_TREE_DECORATOR.get();
    }

    @Override
    public void place(TreeDecorator.Context context) {
        RandomSource random = context.random();
        context.logs().forEach((blockPos) -> {
            int chance = 5;
            if(random.nextInt(chance) == 0) {
                BlockPos blockpos = blockPos.west();
                if(context.isAir(blockpos)) {
                    this.addHangingVine(random, blockpos, VineBlock.EAST, context);
                }
            }

            if (random.nextInt(chance) == 0) {
                BlockPos blockpos1 = blockPos.east();
                if(context.isAir(blockpos1)) {
                    this.addHangingVine(random, blockpos1, VineBlock.WEST, context);
                }
            }

            if(random.nextInt(chance) == 0) {
                BlockPos blockpos2 = blockPos.north();
                if(context.isAir(blockpos2)) {
                    this.addHangingVine(random, blockpos2, VineBlock.SOUTH, context);
                }
            }

            if(random.nextInt(chance) == 0) {
                BlockPos blockpos3 = blockPos.south();
                if(context.isAir(blockpos3)) {
                    this.addHangingVine(random, blockpos3, VineBlock.NORTH, context);
                }
            }
        });
    }

    private void addHangingVine(RandomSource random, BlockPos blockPos, BooleanProperty booleanProperty, TreeDecorator.Context context) {
        placeBrush(blockPos, booleanProperty, context);
        int i = random.nextInt(3) + 2;
        for(BlockPos blockpos = blockPos.below(); context.isAir(blockpos) && i > 0; --i) {
            placeBrush(blockpos, booleanProperty, context);
            blockpos = blockpos.below();
        }
    }

    protected void placeBrush(BlockPos pos, BooleanProperty side, TreeDecorator.Context context) {
        context.setBlock(pos, JBlocks.CHARRED_BRUSH.get().defaultBlockState().setValue(side, true));
    }

    static {
        CODEC = Codec.unit(() -> INSTANCE);
    }
}