package net.jitl.common.block;

import net.jitl.core.init.internal.JBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class JSlimeBlock extends Block {

    protected static final VoxelShape SLIME_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2D, 16.0D);

    public JSlimeBlock() {
        super(JBlockProperties.SLIME);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        return SLIME_AABB;
    }

    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SLIME_AABB;
    }

    public VoxelShape getBlockSupportShape(BlockState pState, BlockGetter pReader, BlockPos pPos) {
        return SLIME_AABB;
    }

    @Override
    public void randomTick(BlockState s, ServerLevel l, BlockPos p, RandomSource r) {
        if(!l.isClientSide) {
            int age = 5;
            age--;
            if(age == 0)
                l.removeBlock(p, false);
        }
    }

    public VoxelShape getVisualShape(BlockState pState, BlockGetter pReader, BlockPos pPos, CollisionContext pContext) {
        return SLIME_AABB;
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockState blockstate = pLevel.getBlockState(pPos.below());
        return Block.isFaceFull(blockstate.getCollisionShape(pLevel, pPos.below()), Direction.UP);
    }
}