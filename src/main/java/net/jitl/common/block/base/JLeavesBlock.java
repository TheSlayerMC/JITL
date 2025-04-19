package net.jitl.common.block.base;

import net.jitl.core.init.internal.JBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.IShearable;
import org.jetbrains.annotations.NotNull;

public class JLeavesBlock extends Block implements SimpleWaterloggedBlock, IShearable {

    public static final int MAX_DISTANCE = 12;
    public static final IntegerProperty DISTANCE = IntegerProperty.create("jitl_leaf_distance", 1, 12);
    public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected final float leafParticleChance = 0.01F;
    public final int particleColour;

    public JLeavesBlock(BlockBehaviour.Properties props, int particleColour) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE, MAX_DISTANCE).setValue(PERSISTENT, Boolean.FALSE).setValue(WATERLOGGED, Boolean.FALSE));
        this.particleColour = particleColour;
    }

    @Override
    public @NotNull VoxelShape getBlockSupportShape(@NotNull BlockState state, @NotNull BlockGetter reader, @NotNull BlockPos pos) {
        return Shapes.empty();
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return pState.getValue(DISTANCE) == MAX_DISTANCE && !pState.getValue(PERSISTENT);
    }

    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if(this.decaying(state)) {
            dropResources(state, level, pos);
            level.removeBlock(pos, false);
        }
    }

    protected boolean decaying(BlockState s) {
        return !s.getValue(PERSISTENT) && s.getValue(DISTANCE) == MAX_DISTANCE;
    }

    @Override
    public void tick(@NotNull BlockState state, ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        level.setBlock(pos, updateDistance(state, level, pos), 3);
    }

    @Override
    protected int getLightBlock(BlockState state) {
        return 1;
    }

    @Override
    public BlockState updateShape(BlockState stateIn, LevelReader reader, ScheduledTickAccess tick, BlockPos currentPos, Direction dir, BlockPos facingPos, BlockState state, RandomSource source) {
        if (!stateIn.canSurvive(reader, currentPos)) {
            tick.scheduleTick(currentPos, this, 1);
        }
        if (stateIn.getValue(WATERLOGGED))
            tick.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(reader));

        int i = getDistanceAt(state) + 1;

        if (i != 1 || stateIn.getValue(DISTANCE) != i)
            tick.scheduleTick(currentPos, this, 1);
        return super.updateShape(stateIn, reader, tick, currentPos, dir, facingPos, state, source);
    }

    private static BlockState updateDistance(BlockState state, LevelAccessor level, BlockPos pos) {
        int i = MAX_DISTANCE;
        BlockPos.MutableBlockPos b = new BlockPos.MutableBlockPos();
        for(Direction direction : Direction.values()) {
            b.setWithOffset(pos, direction);
            i = Math.min(i, getDistanceAt(level.getBlockState(b)) + 1);
            if(i == 1)
                break;
        }
        return state.setValue(DISTANCE, i);
    }

    private static int getDistanceAt(BlockState neighbor) {
        if(neighbor.is(BlockTags.LOGS)) {
            return 0;
        } else {
            return neighbor.getBlock() instanceof JLeavesBlock || neighbor.getBlock() instanceof LeavesBlock ? neighbor.getValue(DISTANCE) : MAX_DISTANCE;
        }
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        BlockPos blockpos = pos.below();
        BlockState blockstate = level.getBlockState(blockpos);
        makeDrippingWaterParticles(level, pos, random, blockstate, blockpos);
        this.makeFallingLeavesParticles(level, pos, random, blockstate, blockpos);
    }

    private static void makeDrippingWaterParticles(Level p_394137_, BlockPos p_394032_, RandomSource p_393792_, BlockState p_393464_, BlockPos p_393946_) {
        if (p_394137_.isRainingAt(p_394032_.above()) && p_393792_.nextInt(15) == 1 && (!p_393464_.canOcclude() || !p_393464_.isFaceSturdy(p_394137_, p_393946_, Direction.UP))) {
            ParticleUtils.spawnParticleBelow(p_394137_, p_394032_, p_393792_, ParticleTypes.DRIPPING_WATER);
        }

    }

    private void makeFallingLeavesParticles(Level level, BlockPos pos, RandomSource random, BlockState state, BlockPos pos2) {
        if (!(random.nextFloat() >= this.leafParticleChance) && !isFaceFull(state.getCollisionShape(level, pos2), Direction.UP)) {
            ParticleUtils.spawnParticleBelow(level, pos, random, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, this.particleColour));
        }

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DISTANCE, PERSISTENT, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        BlockState blockstate = this.defaultBlockState().setValue(PERSISTENT, Boolean.TRUE).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
        return updateDistance(blockstate, pContext.getLevel(), pContext.getClickedPos());
    }
}
