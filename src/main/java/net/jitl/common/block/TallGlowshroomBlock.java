package net.jitl.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.Tags;

public class TallGlowshroomBlock extends JDoublePlantBlock {

    private static final VoxelShape HITBOX = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 14.0D, 13.0D);

    public TallGlowshroomBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return HITBOX;
    }

    @Override
    public boolean canPlace(BlockState ground) {
        return ground.is(Tags.Blocks.STONE);
    }
}