package net.jitl.common.world.gen.tree_grower.decorators;

import com.mojang.serialization.Codec;
import net.jitl.common.world.gen.JTreeDecorators;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class IcyBrushTreeDecorator extends TreeDecorator {

    public static final Codec<IcyBrushTreeDecorator> CODEC;
    public static final IcyBrushTreeDecorator INSTANCE = new IcyBrushTreeDecorator(0.20F);
    private final float probability;

    public IcyBrushTreeDecorator(float p) {
        this.probability = p;
    }

    protected TreeDecoratorType<?> type() {
        return JTreeDecorators.ICY_BRUSH_TREE.get();
    }

    @Override
    public void place(TreeDecorator.Context pContext) {
        RandomSource randomsource = pContext.random();
        pContext.leaves().forEach((d) -> {
            if(randomsource.nextFloat() < this.probability) {
                BlockPos blockpos = d.west();
                if(pContext.isAir(blockpos))
                    addHangingVine(blockpos, VineBlock.EAST, pContext);
            }

            if(randomsource.nextFloat() < this.probability) {
                BlockPos blockpos1 = d.east();
                if (pContext.isAir(blockpos1))
                    addHangingVine(blockpos1, VineBlock.WEST, pContext);
            }

            if(randomsource.nextFloat() < this.probability) {
                BlockPos blockpos2 = d.north();
                if(pContext.isAir(blockpos2))
                    addHangingVine(blockpos2, VineBlock.SOUTH, pContext);
            }

            if(randomsource.nextFloat() < this.probability) {
                BlockPos blockpos3 = d.south();
                if(pContext.isAir(blockpos3))
                    addHangingVine(blockpos3, VineBlock.NORTH, pContext);
            }
        });
    }

    private void addHangingVine(BlockPos pPos, BooleanProperty pSideProperty, TreeDecorator.Context pContext) {
        placeBrush(pContext, pPos, pSideProperty);
        int i = pContext.random().nextInt(4) + 3;

        for(BlockPos blockpos = pPos.below(); pContext.isAir(blockpos) && i > 0; --i) {
            placeBrush(pContext, pPos, pSideProperty);
            blockpos = blockpos.below();
        }
    }

    private void placeBrush(TreeDecorator.Context context, BlockPos pos, BooleanProperty sideProperty) {
        context.setBlock(pos, JBlocks.ICY_BRUSH.get().defaultBlockState().setValue(sideProperty, Boolean.TRUE));
    }

    static {
        CODEC = Codec.unit(() -> INSTANCE);
    }
}