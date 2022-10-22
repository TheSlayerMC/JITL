package net.jitl.common.world.gen.depths;

import com.mojang.serialization.Codec;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class DepthsCrystalFeature extends Feature<NoneFeatureConfiguration> {

    public DepthsCrystalFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos pos1 = context.origin();
        WorldGenLevel reader = context.level();
        RandomSource rand = context.random();

        BlockPos pos2 = new BlockPos(pos1.getX(), pos1.getY() - 1, pos1.getZ());

        BlockPos pos = pos2.above(rand.nextInt(4));

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
                        BlockState blockstate = reader.getBlockState(pos.offset(i1, k, j1));
                        Block block = blockstate.getBlock();
                        if(blockstate.isAir()) {
                            this.setBlock(reader, pos.offset(i1, k, j1), JBlocks.DEPTHS_CRYSTAL.get().defaultBlockState());
                        }
                    }
                }
            }
        }
        return true;
    }
}