package net.jitl.common.world.gen.depths;

import com.mojang.serialization.Codec;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class JSpikeFeature extends Feature<NoneFeatureConfiguration> {

    private final Block generate;

    public JSpikeFeature(Block generate, Codec<NoneFeatureConfiguration> codec) {
        super(codec);
        this.generate = generate;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos pos1 = context.origin();
        WorldGenLevel reader = context.level();
        RandomSource rand = context.random();

        BlockPos pos = pos1.above(rand.nextInt(4));
        BlockState s = reader.getBlockState(pos1.below());
        if(!s.is(JBlocks.DEPTHS_GRASS.get()) && reader.getBlockState(pos1).is(Blocks.AIR)) {
            return false;
        } else {
            if(pos1.getY() < 24) {
                int i = rand.nextInt(20) + 10;
                int j = i / 4 + rand.nextInt(2);

                if (j > 1 && rand.nextInt(60) == 0) {
                    pos = pos.above(10 + rand.nextInt(30));
                }

                for (int k = 0; k < i; ++k) {
                    float f = (1.0F - (float) k / (float) i) * (float) j;
                    int l = Mth.ceil(f);

                    for (int i1 = -l; i1 <= l; ++i1) {
                        float f1 = (float) Mth.abs(i1) - 0.25F;
                        for (int j1 = -l; j1 <= l; ++j1) {
                            float f2 = (float) Mth.abs(j1) - 0.25F;
                            if ((i1 == 0 && j1 == 0 || !(f1 * f1 + f2 * f2 > f * f)) && (i1 != -l && i1 != l && j1 != -l && j1 != l || !(rand.nextFloat() > 0.75F))) {
                                BlockState blockstate = reader.getBlockState(pos.offset(i1, k, j1));
                                Block block = blockstate.getBlock();
                                if (blockstate.isAir() || block == this.generate) {
                                    this.setBlock(reader, pos.offset(i1, k, j1), this.generate.defaultBlockState());
                                }

                                if (k != 0 && l > 1) {
                                    blockstate = reader.getBlockState(pos.offset(i1, -k, j1));
                                    block = blockstate.getBlock();
                                    if (blockstate.isAir() || block == this.generate) {
                                        this.setBlock(reader, pos.offset(i1, -k, j1), this.generate.defaultBlockState());
                                    }
                                }
                            }
                        }
                    }
                }
                int k1 = j - 1;
                if (k1 < 0) {
                    k1 = 0;
                } else if (k1 > 1) {
                    k1 = 1;
                }
                for (int l1 = -k1; l1 <= k1; ++l1) {
                    for (int i2 = -k1; i2 <= k1; ++i2) {
                        BlockPos blockpos = pos.offset(l1, -1, i2);
                        int j2 = 50;
                        if (Math.abs(l1) == 1 && Math.abs(i2) == 1) {
                            j2 = rand.nextInt(5);
                        }
                        while (blockpos.getY() > 50) {
                            BlockState blockstate1 = reader.getBlockState(blockpos);
                            Block block1 = blockstate1.getBlock();
                            if (!blockstate1.isAir() && block1 != this.generate) {
                                break;
                            }
                            this.setBlock(reader, blockpos, this.generate.defaultBlockState());
                            blockpos = blockpos.below();
                            --j2;
                            if (j2 <= 0) {
                                blockpos = blockpos.below(rand.nextInt(5) + 1);
                                j2 = rand.nextInt(5);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
