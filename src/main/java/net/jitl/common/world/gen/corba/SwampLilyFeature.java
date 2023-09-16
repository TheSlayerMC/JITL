package net.jitl.common.world.gen.corba;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;

public class SwampLilyFeature extends Feature<RandomPatchConfiguration> {

   public SwampLilyFeature(Codec<RandomPatchConfiguration> r) {
      super(r);
   }

   @Override
   public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context) {
      RandomPatchConfiguration randompatchconfiguration = context.config();
      RandomSource randomsource = context.random();
      BlockPos blockpos = context.origin();
      WorldGenLevel worldgenlevel = context.level();
      int i = 0;
      BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
      int j = randompatchconfiguration.xzSpread() + 1;
      int k = randompatchconfiguration.ySpread() + 1;
      for(int l = 0; l < randompatchconfiguration.tries(); l++) {
         int offsetX = randomsource.nextInt(j) - randomsource.nextInt(j);
         int offsetY = randomsource.nextInt(k) - randomsource.nextInt(k);
         int offsetZ = randomsource.nextInt(j) - randomsource.nextInt(j);
         if(worldgenlevel.getBlockState(blockpos.below()) == Blocks.WATER.defaultBlockState()) {
            pos.setWithOffset(blockpos, offsetX, offsetY, offsetZ);
            if(randompatchconfiguration.feature().value().place(worldgenlevel, context.chunkGenerator(), randomsource, pos)) {
               i++;
            }
         }
      }
      return i > 0;
   }
}