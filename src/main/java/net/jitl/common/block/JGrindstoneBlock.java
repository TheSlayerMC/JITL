package net.jitl.common.block;

import net.jitl.common.block.entity.GrindstoneEntity;
import net.jitl.core.init.JBlockProperties;
import net.jitl.core.init.internal.JBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JGrindstoneBlock extends BaseEntityBlock {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public JGrindstoneBlock() {
        super(JBlockProperties.GRINDSTONE);
        this.registerDefaultState(this.defaultBlockState().setValue(POWERED, false));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return JBlockEntities.GRINDSTONE.get().create(pos, state);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if(!level.isClientSide()) {
            boolean powered = state.getValue(POWERED);
            if(powered != level.hasNeighborSignal(pos)) {
                if(powered) {
                    level.scheduleTick(pos, this, 4);
                } else {
                    level.setBlock(pos, state.cycle(POWERED), 2);
                }
            }
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(state.getValue(POWERED) && !level.hasNeighborSignal(pos)) {
            level.setBlock(pos, state.cycle(POWERED), 2);
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(POWERED, false);
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction) {
        return true;
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }
}
