package net.jitl.common.world.gen.tree_grower.foliageplacers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.jitl.common.world.gen.JFoliagePlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public class SwampFoliagePlacer extends BlobFoliagePlacer {

    public static final Codec<SwampFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> blobParts(instance).apply(instance, SwampFoliagePlacer::new));
    
    public SwampFoliagePlacer(IntProvider featureSpread, IntProvider featureSpread1, int i) {
        super(featureSpread, featureSpread1, i);
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return JFoliagePlacers.SPHERICAL_FOLIAGE_PLACER.get();
    }

    
    @Override
    protected void createFoliage(@NotNull LevelSimulatedReader reader, @NotNull FoliagePlacer.FoliageSetter blockSetter, @NotNull RandomSource rand, @NotNull TreeConfiguration baseTreeFeatureConfig, int maxFreeTreeHeight, FoliageAttachment foliage, int foliageHeight, int foliageRadius, int offset) {
        int size = foliageRadius + foliage.radiusOffset();
        BlockPos pos = foliage.pos().above(offset);
        pos = pos.offset(Direction.UP.getNormal());
        for (byte x = 0; x <= size; x++) {
            for (byte y = 0; y <= size; y++) {
                for (byte z = 0; z <= size; z++) {
                    int distance;
                    if (x >= y & x >= z) distance = x + (Math.max(y, z) >> 1) + (Math.min(y, z) >> 1);
                    else if (y >= x & y >= z) distance = y + (Math.max(x, z) >> 1) + (Math.min(x, z) >> 1);
                    else distance = z + (Math.max(x, y) >> 1) + (Math.min(x, y) >> 1);
                    if (distance <= size) {
                        placeLeavesRow(reader, blockSetter, rand, baseTreeFeatureConfig, pos.offset(-x, +y, +z), foliageHeight, offset, false);
                        placeLeavesRow(reader, blockSetter, rand, baseTreeFeatureConfig, pos.offset(-x, +y, -z), foliageHeight, offset, false);
                        placeLeavesRow(reader, blockSetter, rand, baseTreeFeatureConfig, pos.offset(+x, +y, +z), foliageHeight, offset, false);
                        placeLeavesRow(reader, blockSetter, rand, baseTreeFeatureConfig, pos.offset(+x, +y, -z), foliageHeight, offset, false);
                    }
                }
            }
        }
    }

    @Override
    public int foliageHeight(@NotNull RandomSource random, int height, @NotNull TreeConfiguration baseTreeFeatureConfig) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(@NotNull RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        if (localY == 0) {
            return (localX > 1 || localZ > 1) && localX != 0 && localZ != 0;
        } else {
            return localX == range && localZ == range && range > 0;
        }
    }
}
