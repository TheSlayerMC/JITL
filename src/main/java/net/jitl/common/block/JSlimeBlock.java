package net.jitl.common.block;

import net.jitl.core.init.internal.JBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class JSlimeBlock extends Block {

    protected static final VoxelShape SLIME_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2D, 16.0D);
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 3);

    public JSlimeBlock() {
        super(JBlockProperties.SLIME);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 3));
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
    public BlockState updateShape(BlockState stateIn, LevelReader reader, ScheduledTickAccess tick, BlockPos currentPos, Direction dir, BlockPos facingPos, BlockState state, RandomSource source) {
        if(dir == Direction.UP && !stateIn.canSurvive(reader, currentPos)) {
            tick.scheduleTick(currentPos, this, 1);
        }
        return super.updateShape(stateIn, reader, tick,currentPos, dir, facingPos, state, source);
    }

    @Override
    public void randomTick(BlockState s, ServerLevel l, BlockPos p, RandomSource r) {
        int i = s.getValue(AGE);

        if(i > 0)
            l.setBlock(p, s.setValue(AGE, i - 1), 2);

        if(s.getValue(AGE) == 0)
            l.removeBlock(p, false);

    }

    public VoxelShape getVisualShape(BlockState pState, BlockGetter pReader, BlockPos pPos, CollisionContext pContext) {
        return SLIME_AABB;
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockState blockstate = pLevel.getBlockState(pPos.below());
        return Block.isFaceFull(blockstate.getCollisionShape(pLevel, pPos.below()), Direction.UP);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }
}