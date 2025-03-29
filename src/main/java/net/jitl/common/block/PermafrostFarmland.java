package net.jitl.common.block;

import net.jitl.common.block.base.JFarmlandBlock;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class PermafrostFarmland extends JFarmlandBlock {

    public PermafrostFarmland(BlockBehaviour.Properties props) {
        super(props);
    }

    @Override
    public Block setDirt() {
        return JBlocks.CRUMBLED_PERMAFROST.get();
    }
}