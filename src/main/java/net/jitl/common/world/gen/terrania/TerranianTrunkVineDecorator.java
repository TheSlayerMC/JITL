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

public class TerranianTrunkVineDecorator extends TreeDecorator {

   public static final Codec<TerranianTrunkVineDecorator> CODEC = Codec.unit(() -> TerranianTrunkVineDecorator.INSTANCE);
   public static final TerranianTrunkVineDecorator INSTANCE = new TerranianTrunkVineDecorator();

   @Override
   protected @NotNull TreeDecoratorType<?> type() {
      return JTreeDecorators.TERRANIAN_TRUNK_DECORATOR.get();
   }

   @Override
   public void place(TreeDecorator.Context context) {
      RandomSource randomsource = context.random();
      context.logs().forEach((d) -> {
         if(randomsource.nextInt(3) > 0) {
            BlockPos blockpos = d.west();
            if(context.isAir(blockpos))
               placeVine(context, blockpos, VineBlock.EAST);
         }

         if(randomsource.nextInt(3) > 0) {
            BlockPos blockpos1 = d.east();
            if (context.isAir(blockpos1))
               placeVine(context, blockpos1, VineBlock.WEST);
         }

         if(randomsource.nextInt(3) > 0) {
            BlockPos blockpos2 = d.north();
            if(context.isAir(blockpos2))
               placeVine(context, blockpos2, VineBlock.SOUTH);
         }

         if(randomsource.nextInt(3) > 0) {
            BlockPos blockpos3 = d.south();
            if(context.isAir(blockpos3))
               placeVine(context, blockpos3, VineBlock.NORTH);
         }
      });
   }

   public void placeVine(TreeDecorator.Context c, BlockPos p, BooleanProperty s) {
      c.setBlock(p, JBlocks.TERRANIAN_VINE.get().defaultBlockState().setValue(s, Boolean.TRUE));
   }
}