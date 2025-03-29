package net.jitl.common.block;

import net.jitl.common.block.base.JFarmlandBlock;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class CorbaFarmland extends JFarmlandBlock {

    public CorbaFarmland(BlockBehaviour.Properties props) {
        super(props);
    }

    @Override
    public Block setDirt() {
        return JBlocks.CORBA_DIRT.get();
    }
}