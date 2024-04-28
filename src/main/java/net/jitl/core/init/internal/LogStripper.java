package net.jitl.core.init.internal;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;

public class LogStripper {

    public static void init() {
        AxeItem.STRIPPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPPABLES)
                .put(JBlocks.EUCA_BROWN_LOG.get(), JBlocks.STRIPPED_EUCA_BROWN_LOG.get())
                .put(JBlocks.EUCA_GOLD_LOG.get(), JBlocks.STRIPPED_EUCA_GOLD_LOG.get())
                .put(JBlocks.FROZEN_LOG.get(), JBlocks.STRIPPED_FROZEN_LOG.get())
                .put(JBlocks.BURNED_BARK.get(), JBlocks.STRIPPED_BURNED_BARK.get())
                .put(JBlocks.DEPTHS_LOG.get(), JBlocks.STRIPPED_DEPTHS_LOG.get())
                .put(JBlocks.BOGWOOD_LOG.get(), JBlocks.STRIPPED_BOGWOOD_LOG.get())
                .put(JBlocks.CORBA_LOG.get(), JBlocks.STRIPPED_CORBA_LOG.get())
                .put(JBlocks.TERRANIAN_LOG.get(), JBlocks.STRIPPED_TERRANIAN_LOG.get())
                .put(JBlocks.CLOUDIA_LOG.get(), JBlocks.STRIPPED_CLOUDIA_LOG.get())
                .build();
    }
}
