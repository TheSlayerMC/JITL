package net.jitl.common.block;

import net.jitl.common.block.entity.SenterianAltarTile;
import net.jitl.core.init.internal.JBlockEntities;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SenterianAltar extends BaseEntityBlock {

    public static final BooleanProperty IS_ACTIVE = BooleanProperty.create("is_active");

    public SenterianAltar(Properties p) {
        super(p);
        this.registerDefaultState(this.stateDefinition.any().setValue(IS_ACTIVE, Boolean.FALSE));
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return this.defaultBlockState().setValue(IS_ACTIVE, Boolean.FALSE);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(IS_ACTIVE);
    }

    @javax.annotation.Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> entity) {
        return level.isClientSide ? null : createTickerHelper(entity, JBlockEntities.SENTERIAN_ALTAR.get(), SenterianAltarTile::serverTick);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if(level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            if(player.getMainHandItem().is(JItems.SENTRY_OBSERVER.get())) {
                if(!getIsActive(state)) {
                    level.setBlock(pos, state.setValue(IS_ACTIVE, true), 2);
                    if(!player.isCreative())
                        player.getMainHandItem().shrink(1);
                }
            }
        }
        return InteractionResult.PASS;
    }

    public boolean getIsActive(BlockState state) {
        return state.getValue(IS_ACTIVE);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return JBlockEntities.SENTERIAN_ALTAR.get().create(pos, state);
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}