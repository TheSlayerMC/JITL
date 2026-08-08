package net.jitl.common.world.gen.depths;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ClampedNormalFloat;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Column;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SpeleothemClusterConfiguration;

import java.util.Optional;
import java.util.OptionalInt;

public class CrystalDripstoneClusterFeature extends Feature<SpeleothemClusterConfiguration> {

    private final Block pointed, dripstone;

    public CrystalDripstoneClusterFeature(Block pointed, Block dripstone, Codec<SpeleothemClusterConfiguration> c) {
        super(c);
        this.pointed = pointed;
        this.dripstone = dripstone;
    }

    public boolean place(FeaturePlaceContext<SpeleothemClusterConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        SpeleothemClusterConfiguration config = (SpeleothemClusterConfiguration)context.config();
        RandomSource random = context.random();
        if (!net.jitl.common.world.gen.depths.JDripstoneUtils.isEmptyOrWater(level, origin)) {
            return false;
        } else {
            int height = config.height().sample(random);
            float wetness = config.wetness().sample(random);
            float density = config.density().sample(random);
            int xRadius = config.radius().sample(random);
            int zRadius = config.radius().sample(random);

            for(int dx = -xRadius; dx <= xRadius; ++dx) {
                for(int dz = -zRadius; dz <= zRadius; ++dz) {
                    double chanceOfStalagmiteOrStalactite = this.getChanceOfStalagmiteOrStalactite(xRadius, zRadius, dx, dz, config);
                    BlockPos pos = origin.offset(dx, 0, dz);
                    this.placeColumn(level, random, pos, dx, dz, wetness, chanceOfStalagmiteOrStalactite, height, density, config);
                }
            }

            return true;
        }
    }

    private void placeColumn(WorldGenLevel level, RandomSource random, BlockPos pos, int dx, int dz, float chanceOfWater, double chanceOfStalagmiteOrStalactite, int clusterHeight, float density, SpeleothemClusterConfiguration config) {
        Optional<Column> baseColumn = Column.scan(level, pos, config.floorToCeilingSearchRange(), net.jitl.common.world.gen.depths.JDripstoneUtils::isEmptyOrWater, net.jitl.common.world.gen.depths.JDripstoneUtils::isNeitherEmptyNorWater);
        if (!baseColumn.isEmpty()) {
            OptionalInt ceiling = ((Column)baseColumn.get()).getCeiling();
            OptionalInt baseFloor = ((Column)baseColumn.get()).getFloor();
            if (!ceiling.isEmpty() || !baseFloor.isEmpty()) {
                boolean wantPool = random.nextFloat() < chanceOfWater;
                Column column;
                if (wantPool && baseFloor.isPresent() && this.canPlacePool(level, pos.atY(baseFloor.getAsInt()), config)) {
                    int baseFloorY = baseFloor.getAsInt();
                    column = ((Column)baseColumn.get()).withFloor(OptionalInt.of(baseFloorY - 1));
                    level.setBlock(pos.atY(baseFloorY), Blocks.WATER.defaultBlockState(), 2);
                } else {
                    column = (Column)baseColumn.get();
                }

                OptionalInt floor = column.getFloor();
                boolean wantStalactite = random.nextDouble() < chanceOfStalagmiteOrStalactite;
                int stalactiteHeight;
                if (ceiling.isPresent() && wantStalactite && !this.isLava(level, pos.atY(ceiling.getAsInt()))) {
                    int ceilingThickness = config.speleothemBlockLayerThickness().sample(random);
                    this.replaceBlocksWithBaseBlocks(level, pos.atY(ceiling.getAsInt()), ceilingThickness, Direction.UP, config);
                    int maxHeightForThisColumn;
                    if (floor.isPresent()) {
                        maxHeightForThisColumn = Math.min(clusterHeight, ceiling.getAsInt() - floor.getAsInt());
                    } else {
                        maxHeightForThisColumn = clusterHeight;
                    }

                    stalactiteHeight = this.getSpeleothemHeight(random, dx, dz, density, maxHeightForThisColumn, config);
                } else {
                    stalactiteHeight = 0;
                }

                boolean wantStalagmite = random.nextDouble() < chanceOfStalagmiteOrStalactite;
                int stalagmiteHeight;
                if (floor.isPresent() && wantStalagmite && !this.isLava(level, pos.atY(floor.getAsInt()))) {
                    int floorThickness = config.speleothemBlockLayerThickness().sample(random);
                    this.replaceBlocksWithBaseBlocks(level, pos.atY(floor.getAsInt()), floorThickness, Direction.DOWN, config);
                    if (ceiling.isPresent()) {
                        stalagmiteHeight = Math.max(0, stalactiteHeight + Mth.randomBetweenInclusive(random, -config.maxStalagmiteStalactiteHeightDiff(), config.maxStalagmiteStalactiteHeightDiff()));
                    } else {
                        stalagmiteHeight = this.getSpeleothemHeight(random, dx, dz, density, clusterHeight, config);
                    }
                } else {
                    stalagmiteHeight = 0;
                }

                int actualStalactiteHeight;
                int actualStalagmiteHeight;
                if (ceiling.isPresent() && floor.isPresent() && ceiling.getAsInt() - stalactiteHeight <= floor.getAsInt() + stalagmiteHeight) {
                    int floorY = floor.getAsInt();
                    int ceilingY = ceiling.getAsInt();
                    int lowestStalactiteBottom = Math.max(ceilingY - stalactiteHeight, floorY + 1);
                    int highestStalagmiteTop = Math.min(floorY + stalagmiteHeight, ceilingY - 1);
                    int actualStalactiteBottom = Mth.randomBetweenInclusive(random, lowestStalactiteBottom, highestStalagmiteTop + 1);
                    int actualStalagmiteTop = actualStalactiteBottom - 1;
                    actualStalactiteHeight = ceilingY - actualStalactiteBottom;
                    actualStalagmiteHeight = actualStalagmiteTop - floorY;
                } else {
                    actualStalactiteHeight = stalactiteHeight;
                    actualStalagmiteHeight = stalagmiteHeight;
                }

                boolean mergeTips = random.nextBoolean() && actualStalactiteHeight > 0 && actualStalagmiteHeight > 0 && column.getHeight().isPresent() && actualStalactiteHeight + actualStalagmiteHeight == column.getHeight().getAsInt();
                if (ceiling.isPresent()) {
                    net.jitl.common.world.gen.depths.JDripstoneUtils.growSpeleothem(level, pos.atY(ceiling.getAsInt() - 1), Direction.DOWN, actualStalactiteHeight, mergeTips, config.baseBlock().getBlock(), config.pointedBlock().getBlock(), config.replaceableBlocks());
                }

                if (floor.isPresent()) {
                    net.jitl.common.world.gen.depths.JDripstoneUtils.growSpeleothem(level, pos.atY(floor.getAsInt() + 1), Direction.UP, actualStalagmiteHeight, mergeTips, config.baseBlock().getBlock(), config.pointedBlock().getBlock(), config.replaceableBlocks());
                }
            }
        }

    }

    private boolean isLava(LevelReader level, BlockPos pos) {
        return level.getBlockState(pos).is(Blocks.LAVA);
    }

    private int getSpeleothemHeight(RandomSource random, int dx, int dz, float density, int maxHeight, SpeleothemClusterConfiguration config) {
        if (random.nextFloat() > density) {
            return 0;
        } else {
            int distanceFromCenter = Math.abs(dx) + Math.abs(dz);
            float heightMean = (float)Mth.clampedMap((double)distanceFromCenter, (double)0.0F, (double)config.maxDistanceFromCenterAffectingHeightBias(), (double)maxHeight / (double)2.0F, (double)0.0F);
            return (int)randomBetweenBiased(random, 0.0F, (float)maxHeight, heightMean, (float)config.heightDeviation());
        }
    }

    private boolean canPlacePool(WorldGenLevel level, BlockPos pos, SpeleothemClusterConfiguration config) {
        BlockState state = level.getBlockState(pos);
        if (!state.is(Blocks.WATER) && !state.is(config.baseBlock().getBlock()) && !state.is(config.pointedBlock().getBlock())) {
            if (level.getBlockState(pos.above()).getFluidState().is(FluidTags.WATER)) {
                return false;
            } else {
                for(Direction direction : Direction.Plane.HORIZONTAL) {
                    if (!this.canBeAdjacentToWater(level, pos.relative(direction))) {
                        return false;
                    }
                }

                return this.canBeAdjacentToWater(level, pos.below());
            }
        } else {
            return false;
        }
    }

    private boolean canBeAdjacentToWater(LevelAccessor level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        return state.is(BlockTags.BASE_STONE_OVERWORLD) || state.getFluidState().is(FluidTags.WATER);
    }

    private void replaceBlocksWithBaseBlocks(WorldGenLevel level, BlockPos firstPos, int maxCount, Direction direction, SpeleothemClusterConfiguration config) {
        BlockPos.MutableBlockPos pos = firstPos.mutable();

        for(int i = 0; i < maxCount; ++i) {
            if (!JDripstoneUtils.placeBaseBlockIfPossible(level, pos, config.baseBlock().getBlock(), config.replaceableBlocks())) {
                return;
            }

            pos.move(direction);
        }

    }

    private double getChanceOfStalagmiteOrStalactite(int xRadius, int zRadius, int dx, int dz, SpeleothemClusterConfiguration config) {
        int xDistanceFromEdge = xRadius - Math.abs(dx);
        int zDistanceFromEdge = zRadius - Math.abs(dz);
        int distanceFromEdge = Math.min(xDistanceFromEdge, zDistanceFromEdge);
        return (double)Mth.clampedMap((float)distanceFromEdge, 0.0F, (float)config.maxDistanceFromEdgeAffectingChanceOfSpeleothem(), config.chanceOfSpeleothemAtMaxDistanceFromCenter(), 1.0F);
    }

    private static float randomBetweenBiased(RandomSource random, float min, float maxExclusive, float mean, float deviation) {
        return ClampedNormalFloat.sample(random, mean, deviation, min, maxExclusive);
    }
}
