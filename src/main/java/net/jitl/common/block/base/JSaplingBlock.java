package net.jitl.common.block.base;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class JSaplingBlock extends BushBlock implements BonemealableBlock {

    public static final IntegerProperty STAGE = BlockStateProperties.STAGE;
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
    private final AbstractTreeGrower treeGrower;

    public JSaplingBlock(AbstractTreeGrower treeIn, BlockBehaviour.Properties p) {
        super(p);
        this.treeGrower = treeIn;
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, 0));
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void randomTick(@NotNull BlockState state, ServerLevel worldIn, BlockPos pos, @NotNull RandomSource random) {
        if (!worldIn.isAreaLoaded(pos, 1))
            return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (worldIn.getMaxLocalRawBrightness(pos.above()) >= 9 && random.nextInt(7) == 0) {
            this.advanceTree(worldIn, pos, state, random);
        }
    }

    public void advanceTree(ServerLevel worldIn, BlockPos pos, BlockState state, RandomSource rand) {
        if (state.getValue(STAGE) == 0) {
            worldIn.setBlock(pos, state.cycle(STAGE), 4);
        } else {
            this.treeGrower.growTree(worldIn, worldIn.getChunkSource().getGenerator(), pos, state, rand);
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean b) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, @NotNull RandomSource pRandom, @NotNull BlockPos pPos, @NotNull BlockState pState) {
        return (double) level.random.nextFloat() < 0.45D;
    }

    @Override
    public void performBonemeal(@NotNull ServerLevel level, @NotNull RandomSource rand, @NotNull BlockPos pos, @NotNull BlockState state) {
        this.advanceTree(level, pos, state, rand);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STAGE);
    }
}
