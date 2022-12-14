package net.jitl.common.world.gen.boil;

import com.mojang.serialization.Codec;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class ScorchedStalagmiteFeature extends Feature<NoneFeatureConfiguration> {
    public ScorchedStalagmiteFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos pos = context.origin();
        WorldGenLevel reader = context.level();
        RandomSource rand = context.random();
        
        pos = reader.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, pos);
        if (reader.getBlockState(pos.below()) != JBlocks.SCORCHED_RUBBLE.get().defaultBlockState()) {
            return false;
        } else {
            BlockPos.MutableBlockPos placePos = pos.mutable();

            int xPos = pos.getX();
            int zPos = pos.getZ();
            int yPos = reader.getHeight(Heightmap.Types.WORLD_SURFACE_WG, xPos, zPos);

            placePos.set(xPos, yPos - 1, zPos);

            BlockPos blockPos = reader.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, pos);
            int bottomHeight = 1 + rand.nextInt(4);
            int largeHeight = 1 + rand.nextInt(5);
            int medHeight = 1 + rand.nextInt(6);
            int smallHeight = 1 + rand.nextInt(7);
            int tinyHeight = 1 + rand.nextInt(8);
            placePos.setWithOffset(blockPos, rand.nextInt(10) + 5, 0, rand.nextInt(10) + 5);

            if (reader.getBlockState(placePos.above()) == Blocks.AIR.defaultBlockState() && reader.getBlockState(placePos) == JBlocks.SCORCHED_RUBBLE.get().defaultBlockState()) {
                for (int i = 0; i < bottomHeight; i++) {
                    setBlock(reader, placePos.move(Direction.UP), JBlocks.SCORCHED_RUBBLE.get().defaultBlockState());
                }
                for (int i = 0; i < largeHeight; i++) {
                    setBlock(reader, placePos.move(Direction.UP), JBlocks.SCORCHED_STALAGMITE_LARGE.get().defaultBlockState());
                }
                for (int i = 0; i < medHeight; i++) {
                    setBlock(reader, placePos.move(Direction.UP), JBlocks.SCORCHED_STALAGMITE_MED.get().defaultBlockState());
                }
                for (int i = 0; i < smallHeight; i++) {
                    setBlock(reader, placePos.move(Direction.UP), JBlocks.SCORCHED_STALAGMITE_SMALL.get().defaultBlockState());
                }
                for (int i = 0; i < tinyHeight; i++) {
                    setBlock(reader, placePos.move(Direction.UP), JBlocks.SCORCHED_STALAGMITE_TINY.get().defaultBlockState());
                }
            }
            return true;
        }
    }
}
