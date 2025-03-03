package net.jitl.common.world.gen.depths;

import java.util.function.Consumer;

import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;

public class CrystalDripstoneUtils {

    protected static double getDripstoneHeight(double radius, double maxRadius, double scale, double minRadius) {
        if (radius < minRadius) {
            radius = minRadius;
        }

        double d0 = 0.384;
        double d1 = radius / maxRadius * 0.384;
        double d2 = 0.75 * Math.pow(d1, 1.3333333333333333);
        double d3 = Math.pow(d1, 0.6666666666666666);
        double d4 = 0.3333333333333333 * Math.log(d1);
        double d5 = scale * (d2 - d3 - d4);
        d5 = Math.max(d5, 0.0);
        return d5 / 0.384 * maxRadius;
    }

    protected static boolean isCircleMostlyEmbeddedInStone(WorldGenLevel level, BlockPos pos, int radius) {
        if (isEmptyOrWaterOrLava(level, pos)) {
            return false;
        } else {
            float f = 6.0F;
            float f1 = 6.0F / (float)radius;

            for (float f2 = 0.0F; f2 < (float) (Math.PI * 2); f2 += f1) {
                int i = (int)(Mth.cos(f2) * (float)radius);
                int j = (int)(Mth.sin(f2) * (float)radius);
                if (isEmptyOrWaterOrLava(level, pos.offset(i, 0, j))) {
                    return false;
                }
            }

            return true;
        }
    }

    protected static boolean isEmptyOrWater(LevelAccessor level, BlockPos pos) {
        return level.isStateAtPosition(pos, CrystalDripstoneUtils::isEmptyOrWater);
    }

    protected static boolean isEmptyOrWaterOrLava(LevelAccessor level, BlockPos pos) {
        return level.isStateAtPosition(pos, CrystalDripstoneUtils::isEmptyOrWaterOrLava);
    }

    protected static void buildBaseToTipColumn(Direction direction, int height, boolean mergeTip, Consumer<BlockState> blockSetter) {
        if (height >= 3) {
            blockSetter.accept(createPointedDripstone(direction, DripstoneThickness.BASE));

            for (int i = 0; i < height - 3; i++) {
                blockSetter.accept(createPointedDripstone(direction, DripstoneThickness.MIDDLE));
            }
        }

        if (height >= 2) {
            blockSetter.accept(createPointedDripstone(direction, DripstoneThickness.FRUSTUM));
        }

        if (height >= 1) {
            blockSetter.accept(createPointedDripstone(direction, mergeTip ? DripstoneThickness.TIP_MERGE : DripstoneThickness.TIP));
        }
    }

    protected static void growPointedDripstone(LevelAccessor level, BlockPos pos, Direction direction, int height, boolean mergeTip) {
        if (isDripstoneBase(level.getBlockState(pos.relative(direction.getOpposite())))) {
            BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();
            buildBaseToTipColumn(direction, height, mergeTip, p_313662_ -> {
                if (p_313662_.is(JBlocks.POINTED_CRYSTALLIZED_DRIPSTONE)) {
                    p_313662_ = p_313662_.setValue(PointedDripstoneBlock.WATERLOGGED, Boolean.valueOf(level.isWaterAt(blockpos$mutableblockpos)));
                }

                level.setBlock(blockpos$mutableblockpos, p_313662_, 2);
                blockpos$mutableblockpos.move(direction);
            });
        }
    }

    protected static boolean placeDripstoneBlockIfPossible(LevelAccessor level, BlockPos pos) {
        BlockState blockstate = level.getBlockState(pos);
        if(blockstate.is(JBlocks.DEPTHS_GRASS) || blockstate.is(JBlocks.DEPTHS_STONE) || blockstate.is(JBlocks.DEPTHS_DIRT)) {
            level.setBlock(pos, JBlocks.CRYSTALLIZED_DRIPSTONE.get().defaultBlockState(), 2);
            return true;
        } else {
            return false;
        }
    }

    private static BlockState createPointedDripstone(Direction direction, DripstoneThickness dripstoneThickness) {
        return JBlocks.POINTED_CRYSTALLIZED_DRIPSTONE.get()
            .defaultBlockState()
            .setValue(PointedDripstoneBlock.TIP_DIRECTION, direction)
            .setValue(PointedDripstoneBlock.THICKNESS, dripstoneThickness);
    }

    public static boolean isDripstoneBaseOrLava(BlockState state) {
        return isDripstoneBase(state) || state.is(Blocks.LAVA);
    }

    public static boolean isDripstoneBase(BlockState state) {
        return state.is(JBlocks.CRYSTALLIZED_DRIPSTONE) || state.is(JBlocks.DEPTHS_GRASS) || state.is(JBlocks.DEPTHS_STONE) || state.is(JBlocks.DEPTHS_DIRT);
    }

    public static boolean isEmptyOrWater(BlockState state) {
        return state.isAir() || state.is(Blocks.WATER);
    }

    public static boolean isNeitherEmptyNorWater(BlockState state) {
        return !state.isAir() && !state.is(Blocks.WATER);
    }

    public static boolean isEmptyOrWaterOrLava(BlockState state) {
        return state.isAir() || state.is(Blocks.WATER) || state.is(Blocks.LAVA);
    }
}
