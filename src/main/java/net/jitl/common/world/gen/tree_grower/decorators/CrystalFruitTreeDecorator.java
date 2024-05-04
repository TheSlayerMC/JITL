package net.jitl.common.world.gen.tree_grower.decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.jitl.common.world.gen.JTreeDecorators;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class CrystalFruitTreeDecorator extends TreeDecorator {

    public static final MapCodec<CrystalFruitTreeDecorator> CODEC = Codec.intRange(0, 10).fieldOf("height").xmap(CrystalFruitTreeDecorator::new,
            (decorator) -> decorator.height);

    private final int height;

    public CrystalFruitTreeDecorator(int height) {
        this.height = height;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return JTreeDecorators.CRYSTAL_FRUIT_DECORATOR.get();
    }

    @Override
    public void place(TreeDecorator.Context context) {
        context.leaves().forEach((pos) -> {
            RandomSource random = context.random();
            if(random.nextInt(8) == 0) {
                BlockPos blockpos = pos.below();
                if (context.isAir(blockpos) && context.isAir(blockpos.below(height + 4))) {
                    BlockPos.MutableBlockPos mutable = blockpos.mutable();
                    addHangingVine(random, mutable, context);
                }
            }
        });
    }

    private void addHangingVine(RandomSource random, BlockPos.MutableBlockPos mutable, TreeDecorator.Context context) {
        int length = random.nextInt(4) + 2;
        for(int i = 0; i <= length; ++i) {
            if(i == length) {
                context.setBlock(mutable, JBlocks.CRYSTAL_FRUIT.get().defaultBlockState());
                break;
            }
            context.setBlock(mutable, JBlocks.ICY_IVY_PLANT.get().defaultBlockState());
            mutable.move(Direction.DOWN);
        }
    }
}