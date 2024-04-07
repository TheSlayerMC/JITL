package net.jitl.common.block;

import net.jitl.common.block.entity.SenterianAltarTile;
import net.jitl.core.init.internal.JBlockEntities;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
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
    public static final BooleanProperty IS_SPAWNING = BooleanProperty.create("is_spawning");

    public SenterianAltar(Properties p) {
        super(p);
        this.registerDefaultState(this.stateDefinition.any().setValue(IS_ACTIVE, Boolean.FALSE));
        this.registerDefaultState(this.stateDefinition.any().setValue(IS_SPAWNING, Boolean.FALSE));
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return this.defaultBlockState().setValue(IS_ACTIVE, Boolean.FALSE).setValue(IS_SPAWNING, Boolean.FALSE);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(IS_ACTIVE);
        builder.add(IS_SPAWNING);
    }

    @javax.annotation.Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> entity) {
        return level.isClientSide ? null : createTickerHelper(entity, JBlockEntities.SENTERIAN_ALTAR.get(), SenterianAltarTile::serverTick);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource r) {
        if(getIsSpawning(state)) {
            for (int i = 0; i < 20; i++)
                level.addParticle(ParticleTypes.CLOUD,
                        pos.getX() + 0.5F - Mth.nextDouble(r, -0.45D, 0.75D),
                        pos.getY() + Mth.nextDouble(r, 0.5D, 2.0D),
                        pos.getZ() + 0.5F  - Mth.nextDouble(r, -0.45D, 0.75D),
                        r.nextGaussian() * 0.05D, 0.15D, r.nextGaussian() * 0.05D);
        }
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

    public boolean getIsSpawning(BlockState state) {
        return state.getValue(IS_SPAWNING);
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