package net.jitl.common.block;

import net.jitl.common.block.base.FaceableBlock;
import net.jitl.core.init.internal.JBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class TrophyBlock extends FaceableBlock {

    public TrophyBlock() {
        super(JBlockProperties.TROPHY);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState s, BlockGetter b, BlockPos p, CollisionContext c) {
        return Block.box(0D, 0D, 0D, 16D, 24D, 16.0D);
    }
}
