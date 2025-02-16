package net.jitl.common.block;

import net.jitl.common.block.base.JFarmlandBlock;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.world.level.block.Block;

public class GolditeFarmland extends JFarmlandBlock {

    @Override
    public Block setDirt() {
        return JBlocks.GOLDITE_DIRT.get();
    }
}