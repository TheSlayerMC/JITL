package net.jitl.common.world.gen.boil;

import com.mojang.serialization.Codec;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class VolcaniocRockFeature extends Feature<NoneFeatureConfiguration> {

    public VolcaniocRockFeature(Codec<NoneFeatureConfiguration> codec_) {
        super(codec_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos pos = context.origin();
        WorldGenLevel reader = context.level();
        int xPos = pos.getX();
        int zPos = pos.getZ();
        int yPos = reader.getHeight(Heightmap.Types.WORLD_SURFACE_WG, xPos, zPos);
        BlockPos placePos = new BlockPos(xPos, yPos, zPos);
        BlockState belowBlock = reader.getBlockState(placePos.below());
        boolean canGen = belowBlock == JBlocks.VOLCANIC_SAND.get().defaultBlockState() ||
                belowBlock == JBlocks.VOLCANIC_SOIL.get().defaultBlockState() ||
                belowBlock == JBlocks.RUBBLE.get().defaultBlockState() ||
                belowBlock == JBlocks.HOT_GROUND.get().defaultBlockState() ||
                belowBlock == JBlocks.SCORCHED_RUBBLE.get().defaultBlockState();
        if(canGen)
            setBlock(reader, placePos, JBlocks.VOLCANIC_ROCK.get().defaultBlockState());
        return true;
    }
}
