package net.jitl.common.world.gen.depths;

import com.mojang.serialization.Codec;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class DepthsPillarFeature extends Feature<NoneFeatureConfiguration> {

    public DepthsPillarFeature(Codec<NoneFeatureConfiguration> c) {
        super(c);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos blockpos = context.origin();
        RandomSource randomsource = context.random();
        if(!level.isEmptyBlock(blockpos)) {
            return false;
        } else {
            BlockState blockstate = level.getBlockState(blockpos.below());
            if(!blockstate.is(JBlocks.DEPTHS_GRASS.get()) && level.getBlockState(blockpos).is(Blocks.AIR)) {
                return false;
            } else {
                int x = blockpos.getX(), y = blockpos.getY(), z = blockpos.getZ();
                int width = randomsource.nextInt(4) + 10;
                int height = 100;
                if(y < 24) {
                    for(int j = 0; j < height; j++)
                        placeCircle(level, x, j + y, z, Math.abs(((height / 2) - j)) / 5 + width);
                }
                return true;
            }
        }
    }

    public void placeCircle(WorldGenLevel w, int x, int y, int z, int r) {
        if (r >= 17) r = 10;
        for (float i = 0; i < r; i += 0.5) {
            for (float j = 0; j < 2 * Math.PI * i; j += 0.5) {
                w.setBlock(new BlockPos((int) Math.floor(x + Math.sin(j) * i), y, (int) Math.floor(z + Math.cos(j) * i)), JBlocks.DEPTHS_STONE.get().defaultBlockState(), 2);
            }
        }
    }
}