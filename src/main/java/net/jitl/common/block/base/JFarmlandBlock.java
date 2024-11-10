package net.jitl.common.block.base;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.FarmlandWaterManager;
import org.jetbrains.annotations.NotNull;

public abstract class JFarmlandBlock extends Block {

    public static final IntegerProperty MOISTURE = BlockStateProperties.MOISTURE;
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    public JFarmlandBlock(BlockBehaviour.Properties props) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any().setValue(MOISTURE, 0));
    }

    public abstract Block setDirt();

    @Override
    public BlockState updateShape(BlockState stateIn, LevelReader reader, ScheduledTickAccess tick, BlockPos currentPos, Direction dir, BlockPos facingPos, BlockState state, RandomSource source) {
        if(dir == Direction.UP && !stateIn.canSurvive(reader, currentPos)) {
            tick.scheduleTick(currentPos, this, 1);
        }
        return super.updateShape(stateIn, reader, tick,currentPos, dir, facingPos, state, source);
    }

    @Override
    public boolean canSurvive(@NotNull BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockState blockstate = pLevel.getBlockState(pPos.above());
        return !blockstate.isSolid() || blockstate.getBlock() instanceof FenceGateBlock || blockstate.getBlock() instanceof MovingPistonBlock;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return !this.defaultBlockState().canSurvive(pContext.getLevel(), pContext.getClickedPos()) ? setDirt().defaultBlockState() : super.getStateForPlacement(pContext);
    }

    @Override
    public boolean useShapeForLightOcclusion(@NotNull BlockState pState) {
        return true;
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public void tick(BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom) {
        if(!pState.canSurvive(pLevel, pPos))
            turnToDirt(pState, pLevel, pPos);
    }

    @Override
    public void randomTick(BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom) {
        int i = pState.getValue(MOISTURE);
        if(!isNearWater(pLevel, pPos) && !pLevel.isRainingAt(pPos.above())) {
            if(i > 0) {
                pLevel.setBlock(pPos, pState.setValue(MOISTURE, i - 1), 2);
            } else if (!isUnderCrops(pLevel, pPos)) {
                turnToDirt(pState, pLevel, pPos);
            }
        } else if(i < 7) {
            pLevel.setBlock(pPos, pState.setValue(MOISTURE, 7), 2);
        }

    }

    @Override
    public void fallOn(Level pLevel, @NotNull BlockState pState, @NotNull BlockPos pPos, @NotNull Entity pEntity, float pFallDistance) {
        if(pLevel instanceof ServerLevel level) {
            if (!pLevel.isClientSide && CommonHooks.onFarmlandTrample(level, pPos, setDirt().defaultBlockState(), pFallDistance, pEntity))
                turnToDirt(pState, pLevel, pPos);
        }

        super.fallOn(pLevel, pState, pPos, pEntity, pFallDistance);
    }

    public void turnToDirt(BlockState pState, Level pLevel, BlockPos pPos) {
        pLevel.setBlockAndUpdate(pPos, pushEntitiesUp(pState, setDirt().defaultBlockState(), pLevel, pPos));
    }

    private static boolean isUnderCrops(BlockGetter pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos.above()).is(BlockTags.MAINTAINS_FARMLAND);
    }

    private static boolean isNearWater(LevelReader pLevel, BlockPos pPos) {
        BlockState state = pLevel.getBlockState(pPos);
        for(BlockPos blockpos : BlockPos.betweenClosed(pPos.offset(-4, 0, -4), pPos.offset(4, 1, 4))) {
            if(state.canBeHydrated(pLevel, pPos, pLevel.getFluidState(blockpos), blockpos))
                return true;
        }

        return FarmlandWaterManager.hasBlockWaterTicket(pLevel, pPos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(MOISTURE);
    }



    @Override
    protected boolean isPathfindable(BlockState pState, PathComputationType pPathComputationType) {
        return false;
    }
}