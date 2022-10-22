package net.jitl.common.world.gen.depths;

import com.mojang.serialization.Codec;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class DepthsLampFeature extends Feature<NoneFeatureConfiguration> {

    public DepthsLampFeature(Codec<NoneFeatureConfiguration> c) {
        super(c);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos blockpos = context.origin();
        RandomSource randomsource = context.random();
        if (!level.isEmptyBlock(blockpos)) {
            return false;
        } else {
            BlockState blockstate = level.getBlockState(blockpos.above());
            if (!blockstate.is(JBlocks.DEPTHS_STONE.get())) {
                return false;
            } else {
                level.setBlock(blockpos, JBlocks.DEPTHS_LAMP.get().defaultBlockState(), 2);
                for(int i = 0; i < 1500; ++i) {
                    BlockPos blockpos1 = blockpos.offset(randomsource.nextInt(8) - randomsource.nextInt(8), -randomsource.nextInt(12), randomsource.nextInt(8) - randomsource.nextInt(8));
                    if (level.getBlockState(blockpos1).isAir()) {
                        int j = 0;
                        for(Direction direction : Direction.values()) {
                            if(level.getBlockState(blockpos1.relative(direction)).is(JBlocks.DEPTHS_LAMP.get()))
                                j++;
                            
                            if(j > 1)
                                break;
                        }
                        if(j == 1)
                            level.setBlock(blockpos1, JBlocks.DEPTHS_LAMP.get().defaultBlockState(), 2);
                    }
                }
                return true;
            }
        }
    }
}