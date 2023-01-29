package net.jitl.common.world.gen.depths;

import com.mojang.serialization.Codec;
import net.jitl.common.block.base.AttachedBlock;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class DepthsCrystalFeature extends Feature<NoneFeatureConfiguration> {

    public DepthsCrystalFeature(Codec<NoneFeatureConfiguration> codec_) {
        super(codec_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos pos1 = context.origin();
        WorldGenLevel reader = context.level();
        RandomSource rand = context.random();
        if (reader.getBlockState(pos1.below()) != JBlocks.DEPTHS_STONE.get().defaultBlockState()) {
            return false;
        } else {

            int i = rand.nextInt(20) + 5;
            int j = i / 4 + rand.nextInt(2);

            for (int k = 0; k < i; ++k) {
                float f = (1.0F - (float) k / (float) i) * (float) j;
                int l = Mth.ceil(f);

                for (int i1 = -l; i1 <= l; ++i1) {
                    float f1 = (float) Mth.abs(i1) - 0.5F;
                    for (int j1 = -l; j1 <= l; ++j1) {
                        float f2 = (float) Mth.abs(j1) - 0.5F;
                        if ((i1 == 0 && j1 == 0 || !(f1 * f1 + f2 * f2 > f * f)) && (i1 != -l && i1 != l && j1 != -l && j1 != l || !(rand.nextFloat() > 0.75F))) {
                            BlockState blockstate = reader.getBlockState(pos1.offset(i1, k, j1));
                            BlockState above = reader.getBlockState(pos1.offset(i1, k + 1, j1));
                            if(blockstate.isAir() && above == JBlocks.DEPTHS_STONE.get().defaultBlockState()) {
                                if(rand.nextInt(4) == 0) {
                                    this.setBlock(reader, pos1.offset(i1, k, j1), JBlocks.DEPTHS_CRYSTAL.get().defaultBlockState().setValue(AttachedBlock.FACING, Direction.DOWN));
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }
    }
}