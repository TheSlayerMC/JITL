package net.jitl.common.world.gen.depths;

import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SpeleothemThickness;
import net.minecraft.world.level.levelgen.feature.SpeleothemUtils;

import java.util.function.Consumer;

public class JDripstoneUtils {

    protected static double getSpeleothemHeight(double xzDistanceFromCenter, double speleothemRadius, double scale, double bluntness) {
        if (xzDistanceFromCenter < bluntness) {
            xzDistanceFromCenter = bluntness;
        }

        double cutoff = 0.384;
        double r = xzDistanceFromCenter / speleothemRadius * 0.384;
        double part1 = (double)0.75F * Math.pow(r, 1.3333333333333333);
        double part2 = Math.pow(r, 0.6666666666666666);
        double part3 = 0.3333333333333333 * Math.log(r);
        double heightRelativeToMaxRadius = scale * (part1 - part2 - part3);
        heightRelativeToMaxRadius = Math.max(heightRelativeToMaxRadius, (double)0.0F);
        return heightRelativeToMaxRadius / 0.384 * speleothemRadius;
    }

    protected static boolean isCircleMostlyEmbeddedInStone(WorldGenLevel level, BlockPos center, int xzRadius) {
        if (isEmptyOrWaterOrLava(level, center)) {
            return false;
        } else {
            float arcLength = 6.0F;
            float angleIncrement = 6.0F / (float)xzRadius;

            for(float angle = 0.0F; angle < ((float)Math.PI * 2F); angle += angleIncrement) {
                int dx = (int)(Mth.cos((double)angle) * (float)xzRadius);
                int dz = (int)(Mth.sin((double)angle) * (float)xzRadius);
                if (isEmptyOrWaterOrLava(level, center.offset(dx, 0, dz))) {
                    return false;
                }
            }

            return true;
        }
    }

    protected static boolean isEmptyOrWater(LevelAccessor level, BlockPos pos) {
        return level.isStateAtPosition(pos, SpeleothemUtils::isEmptyOrWater);
    }

    protected static boolean isEmptyOrWaterOrLava(LevelAccessor level, BlockPos pos) {
        return level.isStateAtPosition(pos, SpeleothemUtils::isEmptyOrWaterOrLava);
    }

    protected static void buildBaseToTipColumn(Direction direction, int totalLength, boolean mergedTip, Consumer<BlockState> consumer, Block pointedBlock) {
        if (totalLength >= 3) {
            consumer.accept(createPointedBlock(direction, SpeleothemThickness.BASE, pointedBlock));

            for(int i = 0; i < totalLength - 3; ++i) {
                consumer.accept(createPointedBlock(direction, SpeleothemThickness.MIDDLE, pointedBlock));
            }
        }

        if (totalLength >= 2) {
            consumer.accept(createPointedBlock(direction, SpeleothemThickness.FRUSTUM, pointedBlock));
        }

        if (totalLength >= 1) {
            consumer.accept(createPointedBlock(direction, mergedTip ? SpeleothemThickness.TIP_MERGE : SpeleothemThickness.TIP, pointedBlock));
        }

    }

    protected static void growSpeleothem(LevelAccessor level, BlockPos startPos, Direction tipDirection, int height, boolean mergedTip, Block baseBlock, Block pointedBlock, HolderSet<Block> replaceableBlocks) {
        if (isBase(level.getBlockState(startPos.relative(tipDirection.getOpposite())))) {
            BlockPos.MutableBlockPos pos = startPos.mutable();
            buildBaseToTipColumn(tipDirection, height, mergedTip, (state) -> {
                if (state.is(pointedBlock)) {
                    state = (BlockState)state.setValue(PointedDripstoneBlock.WATERLOGGED, level.isWaterAt(pos));
                }

                level.setBlock(pos, state, 2);
                pos.move(tipDirection);
            }, pointedBlock);
        }

    }

    protected static boolean placeBaseBlockIfPossible(LevelAccessor level, BlockPos pos, Block baseBlock, HolderSet<Block> replaceableBlocks) {
        BlockState state = level.getBlockState(pos);
        if (state.is(replaceableBlocks)) {
            level.setBlock(pos, baseBlock.defaultBlockState(), 2);
            return true;
        } else {
            return false;
        }
    }

    private static BlockState createPointedBlock(Direction direction, SpeleothemThickness thickness, Block pointedBlock) {
        return (BlockState)((BlockState)pointedBlock.defaultBlockState().setValue(PointedDripstoneBlock.TIP_DIRECTION, direction)).setValue(PointedDripstoneBlock.THICKNESS, thickness);
    }

    public static boolean isBaseOrLava(BlockState state) {
        return isBase(state) || state.is(Blocks.LAVA);
    }

    public static boolean isBase(BlockState state) {
        return state.is(JBlocks.CRYSTALLIZED_DRIPSTONE) || state.is(JBlocks.DEPTHS_DRIPSTONE) ||state.is(JBlocks.DEPTHS_GRASS) || state.is(JBlocks.DEPTHS_STONE) || state.is(JBlocks.DEPTHS_DIRT);
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
