package net.jitl.common.block;

import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class JDirtPathBlock extends Block {

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    public JDirtPathBlock(BlockBehaviour.Properties p) {
        super(p);
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState p_153159_) {
        return true;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext b) {

        return !this.defaultBlockState().canSurvive(b.getLevel(), b.getClickedPos()) ? Block.pushEntitiesUp(this.defaultBlockState(), getDirt().defaultBlockState(), b.getLevel(), b.getClickedPos()) : super.getStateForPlacement(b);
    }

    private Block getDirt() {
        Block dirt = JBlocks.PERMAFROST_ROAD.get();
        if(this == JBlocks.PERMAFROST_ROAD.getHolder().get()) {
            dirt = JBlocks.CRUMBLED_PERMAFROST.get();
        }
        if(this == JBlocks.CORBA_PATH.getHolder().get()) {
            dirt = JBlocks.CORBA_GRASS.get();
        }
        return dirt;
    }

    @Override
    public BlockState updateShape(BlockState b, Direction d, BlockState s, LevelAccessor l, BlockPos p1, BlockPos p2) {
        if(d == Direction.UP && !b.canSurvive(l, p1)) {
            l.scheduleTick(p1, this, 1);
        }
        return super.updateShape(b, d, s, l, p1, p2);
    }

    @Override
    public void tick(BlockState s, ServerLevel l, BlockPos p, RandomSource r) {
        l.setBlockAndUpdate(p, pushEntitiesUp(s, getDirt().defaultBlockState(), l, p));
    }

    @Override
    public boolean canSurvive(BlockState s, LevelReader l, BlockPos p) {
        BlockState blockstate = l.getBlockState(p.above());
        return !blockstate.getMaterial().isSolid() || blockstate.getBlock() instanceof FenceGateBlock;
    }

    @Override
    public VoxelShape getShape(BlockState s, BlockGetter b, BlockPos p, CollisionContext c) {
        return SHAPE;
    }

    @Override
    public boolean isPathfindable(BlockState s, BlockGetter b, BlockPos p, PathComputationType c) {
        return false;
    }
}