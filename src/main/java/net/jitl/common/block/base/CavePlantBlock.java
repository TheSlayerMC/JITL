package net.jitl.common.block.base;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;

public class CavePlantBlock extends TallGrassBlock {

    public CavePlantBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState groundState = level.getBlockState(pos.below());
        return groundState.is(Tags.Blocks.STONE);
    }
}
