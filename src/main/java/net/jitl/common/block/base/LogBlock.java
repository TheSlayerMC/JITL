package net.jitl.common.block.base;

import net.jitl.common.items.base.MultitoolItem;
import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LogBlock extends RotatedPillarBlock {

    public LogBlock() {
        super(JBlockProperties.WOOD);
    }

    @Override
    public @Nullable BlockState getToolModifiedState(@NotNull BlockState state, @NotNull UseOnContext context, @NotNull ItemAbility itemAbility, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem || context.getItemInHand().getItem() instanceof MultitoolItem
                || context.getItemInHand().getItem().getName(context.getItemInHand()).contains(Component.literal("shickaxe"))) {
            if(state.is(JBlocks.EUCA_BROWN_LOG.get()))
                return JBlocks.STRIPPED_EUCA_BROWN_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            if(state.is(JBlocks.EUCA_GOLD_LOG.get()))
                return JBlocks.STRIPPED_EUCA_GOLD_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            if(state.is(JBlocks.FROZEN_LOG.get()))
                return JBlocks.STRIPPED_FROZEN_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            if(state.is(JBlocks.BURNED_BARK.get()))
                return JBlocks.STRIPPED_BURNED_BARK.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            if(state.is(JBlocks.DEPTHS_LOG.get()))
                return JBlocks.STRIPPED_DEPTHS_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            if(state.is(JBlocks.BOGWOOD_LOG.get()))
                return JBlocks.STRIPPED_BOGWOOD_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            if(state.is(JBlocks.CORBA_LOG.get()))
                return JBlocks.STRIPPED_CORBA_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            if(state.is(JBlocks.TERRANIAN_LOG.get()))
                return JBlocks.STRIPPED_TERRANIAN_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            if(state.is(JBlocks.CLOUDIA_LOG.get()))
                return JBlocks.STRIPPED_CLOUDIA_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
        }
        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }
}
