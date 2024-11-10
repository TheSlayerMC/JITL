package net.jitl.common.block;

import net.jitl.common.block.base.FaceableBlock;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class TrophyBlock extends FaceableBlock {

    protected static final VoxelShape BOTTOM = Block.box(2D, 0D, 2D, 14D, 1D, 14D);
    protected static final VoxelShape BOTTOM1 = Block.box(4D, 1D, 4D, 12D, 3D, 12D);
    protected static final VoxelShape STEM = Block.box(7D, 3D, 7D, 9D, 9D, 9D);
    protected static final VoxelShape BASE = Block.box(4D, 9D, 4D, 12D, 11D, 12D);
    protected static final VoxelShape BASE1 = Block.box(2D, 11D, 2D, 14D, 12D, 14D);
    protected static final VoxelShape SIDE1 = Block.box(2D, 12D, 1D, 14D, 22D, 2D);
    protected static final VoxelShape SIDE2 = Block.box(14D, 12D, 2D, 15D, 22D, 14D);
    protected static final VoxelShape SIDE3 = Block.box(1D, 12D, 2D, 2D, 22D, 14D);
    protected static final VoxelShape SIDE4 = Block.box(2D, 12D, 14D, 14D, 22D, 15D);
    protected static final VoxelShape BASE_SHAPE = Shapes.or(BOTTOM, BOTTOM1, STEM, BASE, BASE1, SIDE1, SIDE2, SIDE3, SIDE4);

    protected static final VoxelShape SIDE_NS1 = Block.box(15D, 14D, 7D, 17D, 23D, 9D);
    protected static final VoxelShape SIDE_NS2 = Block.box(-1D, 14D, 7D, 1D, 23D, 9D);

    protected static final VoxelShape SIDE_EW1 = Block.box(7D, 14D, 15D, 9D, 23D, 17D);
    protected static final VoxelShape SIDE_EW2 = Block.box(7D, 14D, -1D, 9D, 23D, 1D);

    protected static final VoxelShape N_S_SHAPE = Shapes.or(BASE_SHAPE, SIDE_NS1, SIDE_NS2);
    protected static final VoxelShape E_W_SHAPE = Shapes.or(BASE_SHAPE, SIDE_EW1, SIDE_EW2);

    public TrophyBlock(BlockBehaviour.Properties props) {
        super(props);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return state.getValue(FACING) == Direction.EAST || state.getValue(FACING) == Direction.WEST ? E_W_SHAPE : N_S_SHAPE ;
    }
}
