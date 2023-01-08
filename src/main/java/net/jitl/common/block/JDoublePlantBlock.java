package net.jitl.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class JDoublePlantBlock extends DoublePlantBlock {

    protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public JDoublePlantBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public boolean canSurvive(BlockState state, @NotNull LevelReader worldIn, @NotNull BlockPos pos) {
        if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
            return mayPlaceOn(state, worldIn, pos);
        } else {
            BlockState blockstate = worldIn.getBlockState(pos.below());
            if (state.getBlock() != this) return mayPlaceOn(state, worldIn, pos);
            return blockstate.is(this) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER;
        }
    }

    public boolean canPlace(BlockState ground) {
        return ground.is(BlockTags.DIRT);
    }

    @Override
    protected boolean mayPlaceOn(@NotNull BlockState state, BlockGetter worldIn, BlockPos pos) {
        BlockPos groundPos = pos.below();
        BlockState groundState = worldIn.getBlockState(groundPos);
        return canPlace(groundState);
    }
}
