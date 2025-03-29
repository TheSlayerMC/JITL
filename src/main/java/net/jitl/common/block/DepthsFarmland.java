package net.jitl.common.block;

import net.jitl.common.block.base.JFarmlandBlock;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class DepthsFarmland extends JFarmlandBlock {

    public DepthsFarmland(BlockBehaviour.Properties props) {
        super(props);
    }

    @Override
    public Block setDirt() {
        return JBlocks.DEPTHS_DIRT.get();
    }
}