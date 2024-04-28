package net.jitl.common.block;

import net.jitl.core.init.internal.JBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class TotemBlock extends Block {

    public static final BooleanProperty AWAKE = BooleanProperty.create("awake");

    public TotemBlock() {
        super(JBlockProperties.STONE);
        this.registerDefaultState(this.stateDefinition.any().setValue(AWAKE, Boolean.FALSE));
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return this.defaultBlockState().setValue(AWAKE, Boolean.FALSE);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AWAKE);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        setIsAwake(level, pos, state, !getIsAwake(state));
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    public boolean getIsAwake(BlockState state) {
        return state.getValue(AWAKE);
    }

    public void setIsAwake(Level level, BlockPos pos, BlockState state, boolean awake) {
        level.setBlock(pos, state.setValue(AWAKE, awake), 2);
    }
}
