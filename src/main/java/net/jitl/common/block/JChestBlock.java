package net.jitl.common.block;

import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.jitl.common.block.entity.JChestBlockEntity;
import net.jitl.core.init.internal.JBlockEntities;
import net.jitl.core.init.internal.JBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;

public class JChestBlock extends AbstractChestBlock<JChestBlockEntity> implements SimpleWaterloggedBlock {

    public static final MapCodec<JChestBlock> CODEC = simpleCodec(JChestBlock::new);

    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape NORTHAABB = Block.box(1.0D, 0.0D, 0.0D, 15.0D, 14.0D, 15.0D);
    protected static final VoxelShape SOUTHAABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 16.0D);
    protected static final VoxelShape WESTAABB = Block.box(0.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
    protected static final VoxelShape EASTAABB = Block.box(1.0D, 0.0D, 1.0D, 16.0D, 14.0D, 15.0D);
    protected static final VoxelShape AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
    public static final BooleanProperty IS_LOCKED = BlockStateProperties.LOCKED;

    private static final DoubleBlockCombiner.Combiner<JChestBlockEntity, Optional<Container>> CHESTCOMBINER = new DoubleBlockCombiner.Combiner<>() {
        @Override
        public @NotNull Optional<Container> acceptDouble(JChestBlockEntity j, JChestBlockEntity j1) {
            return Optional.of(new CompoundContainer(j, j1));
        }

        @Override
        public @NotNull Optional<Container> acceptSingle(JChestBlockEntity j) {
            return Optional.of(j);
        }

        @Override
        public @NotNull Optional<Container> acceptNone() {
            return Optional.empty();
        }
    };

    private static final DoubleBlockCombiner.Combiner<JChestBlockEntity, Optional<MenuProvider>> MENU_PROVIDER_COMBINER = new DoubleBlockCombiner.Combiner<>() {
        @Override
        public @NotNull Optional<MenuProvider> acceptDouble(final @NotNull JChestBlockEntity JChestBlockEntity3, final @NotNull JChestBlockEntity JChestBlockEntity4) {
            final Container container = new CompoundContainer(JChestBlockEntity3, JChestBlockEntity4);
            return Optional.of(new MenuProvider() {
                @Override
                @Nullable
                public AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player player1) {
                    if (JChestBlockEntity3.canOpen(player1) && JChestBlockEntity4.canOpen(player1)) {
                        JChestBlockEntity3.unpackLootTable(inventory.player);
                        JChestBlockEntity4.unpackLootTable(inventory.player);
                        return ChestMenu.sixRows(id, inventory, container);
                    } else {
                        return null;
                    }
                }

                @Override
                public @NotNull Component getDisplayName() {
                    if (JChestBlockEntity3.hasCustomName()) {
                        return JChestBlockEntity3.getDisplayName();
                    } else {
                        return JChestBlockEntity4.hasCustomName() ? JChestBlockEntity4.getDisplayName() : Component.translatable("container.chestDouble");
                    }
                }
            });
        }

        @Override
        public @NotNull Optional<MenuProvider> acceptSingle(JChestBlockEntity j) {
            return Optional.of(j);
        }

        @Override
        public @NotNull Optional<MenuProvider> acceptNone() {
            return Optional.empty();
        }
    };

    public JChestBlock(BlockBehaviour.Properties props) {
        super(props, JBlockEntities.JCHEST::get);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH)
                .setValue(TYPE, ChestType.SINGLE).setValue(IS_LOCKED, Boolean.FALSE).setValue(WATERLOGGED, Boolean.FALSE));
    }

    public static DoubleBlockCombiner.BlockType getBlockType(BlockState state1) {
        ChestType chesttype = state1.getValue(TYPE);
        if (chesttype == ChestType.SINGLE) {
            return DoubleBlockCombiner.BlockType.SINGLE;
        } else {
            return chesttype == ChestType.RIGHT ? DoubleBlockCombiner.BlockType.FIRST : DoubleBlockCombiner.BlockType.SECOND;
        }
    }

    @Override
    protected BlockState updateShape(BlockState p_51555_, LevelReader p_374487_, ScheduledTickAccess p_374060_, BlockPos p_51559_, Direction p_51556_, BlockPos p_51560_, BlockState p_51557_, RandomSource p_374212_) {
        if ((Boolean)p_51555_.getValue(WATERLOGGED)) {
            p_374060_.scheduleTick(p_51559_, Fluids.WATER, Fluids.WATER.getTickDelay(p_374487_));
        }

        if (p_51557_.is(this) && p_51556_.getAxis().isHorizontal()) {
            ChestType chesttype = (ChestType)p_51557_.getValue(TYPE);
            if (p_51555_.getValue(TYPE) == ChestType.SINGLE && chesttype != ChestType.SINGLE && p_51555_.getValue(FACING) == p_51557_.getValue(FACING) && getConnectedDirection(p_51557_) == p_51556_.getOpposite()) {
                return (BlockState)p_51555_.setValue(TYPE, chesttype.getOpposite());
            }
        } else if (getConnectedDirection(p_51555_) == p_51556_) {
            return (BlockState)p_51555_.setValue(TYPE, ChestType.SINGLE);
        }

        return super.updateShape(p_51555_, p_374487_, p_374060_, p_51559_, p_51556_, p_51560_, p_51557_, p_374212_);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        if (state.getValue(TYPE) == ChestType.SINGLE) {
            return AABB;
        } else {
            return switch (getConnectedDirection(state)) {
                case NORTH -> NORTHAABB;
                case SOUTH -> SOUTHAABB;
                case WEST -> WESTAABB;
                case EAST -> EASTAABB;
                default -> throw new IllegalStateException("Unexpected value: " + getConnectedDirection(state));
            };
        }
    }

    public static Direction getConnectedDirection(BlockState state2) {
        Direction direction = state2.getValue(FACING);
        return state2.getValue(TYPE) == ChestType.LEFT ? direction.getClockWise() : direction.getCounterClockWise();
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        ChestType chesttype = ChestType.SINGLE;
        Direction direction = context.getHorizontalDirection().getOpposite();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        boolean flag = context.isSecondaryUseActive();
        Direction direction1 = context.getClickedFace();
        if (direction1.getAxis().isHorizontal() && flag) {
            Direction direction2 = this.candidatePartnerFacing(context, direction1.getOpposite());
            if (direction2 != null && direction2.getAxis() != direction1.getAxis()) {
                direction = direction2;
                chesttype = direction2.getCounterClockWise() == direction1.getOpposite() ? ChestType.RIGHT : ChestType.LEFT;
            }
        }

        if (chesttype == ChestType.SINGLE && !flag) {
            if (direction == this.candidatePartnerFacing(context, direction.getClockWise())) {
                chesttype = ChestType.LEFT;
            } else if (direction == this.candidatePartnerFacing(context, direction.getCounterClockWise())) {
                chesttype = ChestType.RIGHT;
            }
        }

        return this.defaultBlockState().setValue(FACING, direction).setValue(TYPE, chesttype).setValue(IS_LOCKED, Boolean.FALSE).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nullable
    private Direction candidatePartnerFacing(BlockPlaceContext context, Direction direction) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(direction));
        return blockstate.is(this) && blockstate.getValue(TYPE) == ChestType.SINGLE ? blockstate.getValue(FACING) : null;
    }

//    @Override
//    public void onRemove(BlockState state, @NotNull Level level, @NotNull BlockPos pos, BlockState newState, boolean isMoving) {
//        if (!state.is(newState.getBlock())) {
//            BlockEntity blockentity = level.getBlockEntity(pos);
//            if (blockentity instanceof Container) {
//                if(!state.getValue(IS_LOCKED))
//                    Containers.dropContents(level, pos, (Container)blockentity);
//                level.updateNeighbourForOutputSignal(pos, this);
//            }
//            super.onRemove(state, level, pos, newState, isMoving);
//        }
//    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level instanceof ServerLevel serverlevel) {
            MenuProvider menuprovider = this.getMenuProvider(state, serverlevel, pos);
            if(menuprovider != null) {
                if(!state.getValue(IS_LOCKED) || player.isCreative())
                    player.openMenu(menuprovider);
            }
        }
        return InteractionResult.SUCCESS_SERVER;
    }

    @Nullable
    public static Container getContainer(JChestBlock chest, BlockState state, Level level, BlockPos pos, boolean override) {
        return chest.combine(state, level, pos, override).apply(CHESTCOMBINER).orElse(null);
    }

    @Override
    protected MapCodec<? extends AbstractChestBlock<JChestBlockEntity>> codec() {
        return CODEC;
    }

    @NotNull
    @Override
    public DoubleBlockCombiner.NeighborCombineResult<? extends JChestBlockEntity> combine(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, boolean override) {
        BiPredicate<LevelAccessor, BlockPos> bipredicate;
        if (override) {
            bipredicate = (world, pos2) -> false;
        } else {
            bipredicate = JChestBlock::isJChestBlockedAt;
        }
        return DoubleBlockCombiner.combineWithNeigbour(this.blockEntityType.get(), JChestBlock::getBlockType, JChestBlock::getConnectedDirection, FACING, state, level, pos, bipredicate);
    }

    @Override
    @Nullable
    public MenuProvider getMenuProvider(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
        return this.combine(state, level, pos, false).<Optional<MenuProvider>>apply(MENU_PROVIDER_COMBINER).orElse(null);
    }

    public static DoubleBlockCombiner.Combiner<JChestBlockEntity, Float2FloatFunction> opennessCombiner(final LidBlockEntity lid) {
        return new DoubleBlockCombiner.Combiner<>() {
            @Override
            public @NotNull Float2FloatFunction acceptDouble(@NotNull JChestBlockEntity JChestBlockEntity8, @NotNull JChestBlockEntity JChestBlockEntity9) {
                return (tickDelta) -> Math.max(JChestBlockEntity8.getOpenNess(tickDelta), JChestBlockEntity9.getOpenNess(tickDelta));
            }
            @Override
            public @NotNull Float2FloatFunction acceptSingle(@NotNull JChestBlockEntity JChestBlockEntity10) {
                return JChestBlockEntity10::getOpenNess;
            }
            @Override
            public @NotNull Float2FloatFunction acceptNone() {
                return lid::getOpenNess;
            }
        };
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new JChestBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? createTickerHelper(blockEntityType, this.blockEntityType.get(), JChestBlockEntity::lidAnimateTick) : null;
    }

    public static boolean isJChestBlockedAt(LevelAccessor level1, BlockPos pos1) {
        return isBlockedChestByBlock(level1, pos1) || isCatSittingOnChest(level1, pos1);
    }

    private static boolean isBlockedChestByBlock(BlockGetter level, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return level.getBlockState(blockpos).isRedstoneConductor(level, blockpos);
    }

    private static boolean isCatSittingOnChest(LevelAccessor level, BlockPos pos) {
        List<Cat> list = level.getEntitiesOfClass(Cat.class, new AABB((double)pos.getX(), (double)(pos.getY() + 1), (double)pos.getZ(), (double)(pos.getX() + 1), (double)(pos.getY() + 2), (double)(pos.getZ() + 1)));
        if (!list.isEmpty()) {
            for(Cat cat : list) {
                if (cat.isInSittingPose()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasAnalogOutputSignal(@NotNull BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromContainer(getContainer(this, blockState, level, pos, false));
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public @NotNull BlockState mirror(BlockState state, Mirror mirror) {
        BlockState rotated = state.rotate(mirror.getRotation(state.getValue(FACING)));
        return mirror == Mirror.NONE ? rotated : rotated.setValue(TYPE, rotated.getValue(TYPE).getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, WATERLOGGED, IS_LOCKED);
    }

    @Override
    protected boolean isPathfindable(BlockState pState, PathComputationType pPathComputationType) {
        return false;
    }

    @Override
    public void tick(@NotNull BlockState state, ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if(blockentity instanceof JChestBlockEntity) {
            ((JChestBlockEntity)blockentity).recheckOpen();
        }
    }

    @Override
    public float getExplosionResistance(BlockState state, BlockGetter world, BlockPos pos, Explosion explosion) {
        return state.getValue(IS_LOCKED) ? 1000000F : super.getExplosionResistance(state, world, pos, explosion);
    }

    @Override
    public float getDestroyProgress(BlockState state, @NotNull Player player, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        return state.getValue(IS_LOCKED) ? 0F : super.getDestroyProgress(state, player, level, pos);
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState pState) {
        return RenderShape.INVISIBLE;
    }
}