package net.jitl.common.world.gen.terrania;

import com.mojang.serialization.Codec;
import net.jitl.common.world.gen.JTreeDecorators;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.jetbrains.annotations.NotNull;

public class TerranianLeaveVineDecorator extends TreeDecorator {

   public static final Codec<TerranianLeaveVineDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(TerranianLeaveVineDecorator::new, (dec) -> dec.probability).codec();
   private final float probability;

   @Override
   protected @NotNull TreeDecoratorType<?> type() {
      return JTreeDecorators.TERRANIAN_DECORATOR.get();
   }

   public TerranianLeaveVineDecorator(float p) {
      this.probability = p;
   }

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

   private static void addHangingVine(BlockPos pPos, BooleanProperty pSideProperty, TreeDecorator.Context pContext) {
      pContext.setBlock(pPos, JBlocks.TERRANIAN_VINE.get().defaultBlockState().setValue(pSideProperty, Boolean.TRUE));
      int i = 4;

      for(BlockPos blockpos = pPos.below(); pContext.isAir(blockpos) && i > 0; --i) {
         pContext.setBlock(pPos, JBlocks.TERRANIAN_VINE.get().defaultBlockState().setValue(pSideProperty, Boolean.TRUE));
         blockpos = blockpos.below();
      }

   }
}