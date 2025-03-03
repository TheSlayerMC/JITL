package net.jitl.common.world.gen.depths;

import com.mojang.serialization.Codec;
import java.util.Optional;
import java.util.OptionalInt;

import net.jitl.core.init.internal.JBlocks;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Column;
import net.minecraft.world.level.levelgen.feature.DripstoneUtils;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.DripstoneClusterConfiguration;

public class CrystalDripstoneClusterFeature extends Feature<DripstoneClusterConfiguration> {
    public CrystalDripstoneClusterFeature(Codec<DripstoneClusterConfiguration> p_159575_) {
        super(p_159575_);
    }

    @Override
    public boolean place(FeaturePlaceContext<DripstoneClusterConfiguration> p_159605_) {
        WorldGenLevel worldgenlevel = p_159605_.level();
        BlockPos blockpos = p_159605_.origin();
        DripstoneClusterConfiguration dripstoneclusterconfiguration = p_159605_.config();
        RandomSource randomsource = p_159605_.random();
        if (!CrystalDripstoneUtils.isEmptyOrWater(worldgenlevel, blockpos)) {
            return false;
        } else {
            int i = dripstoneclusterconfiguration.height.sample(randomsource);
            float f = dripstoneclusterconfiguration.wetness.sample(randomsource);
            float f1 = dripstoneclusterconfiguration.density.sample(randomsource);
            int j = dripstoneclusterconfiguration.radius.sample(randomsource);
            int k = dripstoneclusterconfiguration.radius.sample(randomsource);

            for (int l = -j; l <= j; l++) {
                for (int i1 = -k; i1 <= k; i1++) {
                    double d0 = this.getChanceOfStalagmiteOrStalactite(j, k, l, i1, dripstoneclusterconfiguration);
                    BlockPos blockpos1 = blockpos.offset(l, 0, i1);
                    this.placeColumn(worldgenlevel, randomsource, blockpos1, l, i1, f, d0, i, f1, dripstoneclusterconfiguration);
                }
            }

            return true;
        }
    }

    private void placeColumn(
        WorldGenLevel level,
        RandomSource random,
        BlockPos pos,
        int x,
        int z,
        float wetness,
        double chance,
        int height,
        float density,
        DripstoneClusterConfiguration config
    ) {
        Optional<Column> optional = Column.scan(
            level, pos, config.floorToCeilingSearchRange, DripstoneUtils::isEmptyOrWater, DripstoneUtils::isNeitherEmptyNorWater
        );
        if (!optional.isEmpty()) {
            OptionalInt optionalint = optional.get().getCeiling();
            OptionalInt optionalint1 = optional.get().getFloor();
            if (!optionalint.isEmpty() || !optionalint1.isEmpty()) {
                boolean flag = random.nextFloat() < wetness;
                Column column;
                if (flag && optionalint1.isPresent() && this.canPlacePool(level, pos.atY(optionalint1.getAsInt()))) {
                    int i = optionalint1.getAsInt();
                    column = optional.get().withFloor(OptionalInt.of(i - 1));
                    level.setBlock(pos.atY(i), Blocks.WATER.defaultBlockState(), 2);
                } else {
                    column = optional.get();
                }

                OptionalInt optionalint2 = column.getFloor();
                boolean flag1 = random.nextDouble() < chance;
                int j;
                if (optionalint.isPresent() && flag1 && !this.isLava(level, pos.atY(optionalint.getAsInt()))) {
                    int k = config.dripstoneBlockLayerThickness.sample(random);
                    this.replaceBlocksWithDripstoneBlocks(level, pos.atY(optionalint.getAsInt()), k, Direction.UP);
                    int l;
                    if (optionalint2.isPresent()) {
                        l = Math.min(height, optionalint.getAsInt() - optionalint2.getAsInt());
                    } else {
                        l = height;
                    }

                    j = this.getDripstoneHeight(random, x, z, density, l, config);
                } else {
                    j = 0;
                }

                boolean flag2 = random.nextDouble() < chance;
                int i3;
                if (optionalint2.isPresent() && flag2 && !this.isLava(level, pos.atY(optionalint2.getAsInt()))) {
                    int i1 = config.dripstoneBlockLayerThickness.sample(random);
                    this.replaceBlocksWithDripstoneBlocks(level, pos.atY(optionalint2.getAsInt()), i1, Direction.DOWN);
                    if (optionalint.isPresent()) {
                        i3 = Math.max(
                            0,
                            j
                                + Mth.randomBetweenInclusive(
                                    random, -config.maxStalagmiteStalactiteHeightDiff, config.maxStalagmiteStalactiteHeightDiff
                                )
                        );
                    } else {
                        i3 = this.getDripstoneHeight(random, x, z, density, height, config);
                    }
                } else {
                    i3 = 0;
                }

                int j1;
                int j3;
                if (optionalint.isPresent() && optionalint2.isPresent() && optionalint.getAsInt() - j <= optionalint2.getAsInt() + i3) {
                    int k1 = optionalint2.getAsInt();
                    int l1 = optionalint.getAsInt();
                    int i2 = Math.max(l1 - j, k1 + 1);
                    int j2 = Math.min(k1 + i3, l1 - 1);
                    int k2 = Mth.randomBetweenInclusive(random, i2, j2 + 1);
                    int l2 = k2 - 1;
                    j3 = l1 - k2;
                    j1 = l2 - k1;
                } else {
                    j3 = j;
                    j1 = i3;
                }

                boolean flag3 = random.nextBoolean() && j3 > 0 && j1 > 0 && column.getHeight().isPresent() && j3 + j1 == column.getHeight().getAsInt();
                if (optionalint.isPresent()) {
                    CrystalDripstoneUtils.growPointedDripstone(level, pos.atY(optionalint.getAsInt() - 1), Direction.DOWN, j3, flag3);
                }

                if (optionalint2.isPresent()) {
                    CrystalDripstoneUtils.growPointedDripstone(level, pos.atY(optionalint2.getAsInt() + 1), Direction.UP, j1, flag3);
                }
            }
        }
    }

    private boolean isLava(LevelReader level, BlockPos pos) {
        return level.getBlockState(pos).is(Blocks.LAVA);
    }

    private int getDripstoneHeight(
        RandomSource random, int x, int z, float chance, int height, DripstoneClusterConfiguration config
    ) {
        if (random.nextFloat() > chance) {
            return 0;
        } else {
            int i = Math.abs(x) + Math.abs(z);
            float f = (float)Mth.clampedMap((double)i, 0.0, (double)config.maxDistanceFromCenterAffectingHeightBias, (double)height / 2.0, 0.0);
            return (int)randomBetweenBiased(random, 0.0F, (float)height, f, (float)config.heightDeviation);
        }
    }

    private boolean canPlacePool(WorldGenLevel level, BlockPos pos) {
        BlockState blockstate = level.getBlockState(pos);
        if (!blockstate.is(Blocks.WATER) && !blockstate.is(JBlocks.CRYSTALLIZED_DRIPSTONE) && !blockstate.is(JBlocks.POINTED_CRYSTALLIZED_DRIPSTONE)) {
            if (level.getBlockState(pos.above()).getFluidState().is(FluidTags.WATER)) {
                return false;
            } else {
                for (Direction direction : Direction.Plane.HORIZONTAL) {
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
        BlockState blockstate = level.getBlockState(pos);
        return blockstate.is(JBlocks.DEPTHS_GRASS) || blockstate.is(JBlocks.DEPTHS_STONE) || blockstate.is(JBlocks.DEPTHS_DIRT) || blockstate.getFluidState().is(FluidTags.WATER);
    }

    private void replaceBlocksWithDripstoneBlocks(WorldGenLevel level, BlockPos pos, int thickness, Direction direction) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();

        for (int i = 0; i < thickness; i++) {
            if (!CrystalDripstoneUtils.placeDripstoneBlockIfPossible(level, blockpos$mutableblockpos)) {
                return;
            }

            blockpos$mutableblockpos.move(direction);
        }
    }

    private double getChanceOfStalagmiteOrStalactite(int xRadius, int zRadius, int x, int z, DripstoneClusterConfiguration config) {
        int i = xRadius - Math.abs(x);
        int j = zRadius - Math.abs(z);
        int k = Math.min(i, j);
        return (double)Mth.clampedMap(
            (float)k,
            0.0F,
            (float)config.maxDistanceFromEdgeAffectingChanceOfDripstoneColumn,
            config.chanceOfDripstoneColumnAtMaxDistanceFromCenter,
            1.0F
        );
    }

    private static float randomBetweenBiased(RandomSource random, float min, float max, float mean, float deviation) {
        return ClampedNormalFloat.sample(random, mean, deviation, min, max);
    }
}
