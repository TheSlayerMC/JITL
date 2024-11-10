package net.jitl.common.items;

import net.jitl.common.block.portal.SenterianPortalFrameBlock;
import net.jitl.common.items.base.JItem;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import org.jetbrains.annotations.NotNull;

public class SentryEyeItem extends JItem {

    public SentryEyeItem(Properties p) {
        super(p);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        if (blockstate.is(JBlocks.SENTERIAN_PORTAL_FRAME.get()) && !blockstate.getValue(SenterianPortalFrameBlock.HAS_EYE)) {
            if (!level.isClientSide) {
                return InteractionResult.SUCCESS_SERVER;
            } else {
                BlockState blockstate1 = blockstate.setValue(SenterianPortalFrameBlock.HAS_EYE, Boolean.TRUE);
                Block.pushEntitiesUp(blockstate, blockstate1, level, blockpos);
                level.setBlock(blockpos, blockstate1, 2);
                level.updateNeighbourForOutputSignal(blockpos, JBlocks.SENTERIAN_PORTAL_FRAME.get());
                pContext.getItemInHand().shrink(1);
                level.levelEvent(1503, blockpos, 0);
                BlockPattern.BlockPatternMatch blockpattern$blockpatternmatch = SenterianPortalFrameBlock.getOrCreatePortalShape().find(level, blockpos);
                if(blockpattern$blockpatternmatch != null) {
                    BlockPos blockpos1 = blockpattern$blockpatternmatch.getFrontTopLeft().offset(-3, 0, -3);
                    for(int i = 0; i < 3; ++i) {
                        for(int j = 0; j < 3; ++j) {
                            level.setBlock(blockpos1.offset(i, 0, j), JBlocks.SENTERIAN_PORTAL.get().defaultBlockState(), 2);
                        }
                    }
                    level.globalLevelEvent(1038, blockpos1.offset(1, 0, 1), 0);
                }
                return InteractionResult.CONSUME;
            }
        } else {
            return InteractionResult.PASS;
        }
    }
}
