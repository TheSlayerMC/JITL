package net.jitl.common.items;

import net.jitl.common.block.portal.logic.JPortalShape;
import net.jitl.common.items.base.JItem;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class FlameCoinItem extends JItem {

    public FlameCoinItem() {
        super(JItems.itemProps().durability(100));
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        Player player = context.getPlayer();
        if(!context.getLevel().isClientSide) {
            if (player != null) {
                for (Direction direction : Direction.Plane.VERTICAL) {
                    BlockPos blockpos = context.getClickedPos();
                    BlockState blockstate = world.getBlockState(blockpos);

                    if (blockstate.getBlock() == JBlocks.EUCA_PORTAL_FRAME.get() && context.getClickedFace() == Direction.UP) {
                        JPortalShape.PortalDirection facing = JPortalShape.testFrameForActivation(world, blockpos, direction, JBlocks.EUCA_PORTAL.get(), JBlocks.EUCA_PORTAL_FRAME.get());
                        if(world instanceof ServerLevel level)
                            JPortalShape.lightPortalFrame(level, blockpos, facing, JBlocks.EUCA_PORTAL.get());
                        return InteractionResult.CONSUME;
                    }
                    if (blockstate.getBlock() == JBlocks.FROZEN_PORTAL_FRAME.get() && context.getClickedFace() == Direction.UP) {
                        JPortalShape.PortalDirection facing = JPortalShape.testFrameForActivation(world, blockpos, direction, JBlocks.FROZEN_PORTAL.get(), JBlocks.FROZEN_PORTAL_FRAME.get());
                        if(world instanceof ServerLevel level)
                            JPortalShape.lightPortalFrame(level, blockpos, facing, JBlocks.FROZEN_PORTAL.get());
                        return InteractionResult.CONSUME;
                    }
                    if (blockstate.getBlock() == JBlocks.BOIL_PORTAL_FRAME.get() && context.getClickedFace() == Direction.UP) {
                        JPortalShape.PortalDirection facing = JPortalShape.testFrameForActivation(world, blockpos, direction, JBlocks.BOIL_PORTAL.get(), JBlocks.BOIL_PORTAL_FRAME.get());
                        if(world instanceof ServerLevel level)
                            JPortalShape.lightPortalFrame(level, blockpos, facing, JBlocks.BOIL_PORTAL.get());
                        return InteractionResult.CONSUME;
                    }
                    if (blockstate.getBlock() == JBlocks.TERRANIAN_PORTAL_FRAME.get() && context.getClickedFace() == Direction.UP) {
                        JPortalShape.PortalDirection facing = JPortalShape.testFrameForActivation(world, blockpos, direction, JBlocks.TERRANIAN_PORTAL.get(), JBlocks.TERRANIAN_PORTAL_FRAME.get());
                        if(world instanceof ServerLevel level)
                            JPortalShape.lightPortalFrame(level, blockpos, facing, JBlocks.TERRANIAN_PORTAL.get());
                        return InteractionResult.CONSUME;
                    }
                    if (blockstate.getBlock() == JBlocks.CLOUDIA_PORTAL_FRAME.get() && context.getClickedFace() == Direction.UP) {
                        JPortalShape.PortalDirection facing = JPortalShape.testFrameForActivation(world, blockpos, direction, JBlocks.CLOUDIA_PORTAL.get(), JBlocks.CLOUDIA_PORTAL_FRAME.get());
                        if(world instanceof ServerLevel level)
                            JPortalShape.lightPortalFrame(level, blockpos, facing, JBlocks.CLOUDIA_PORTAL.get());
                        return InteractionResult.CONSUME;
                    }
                }
                if (!player.isCreative()) context.getItemInHand().shrink(1);
            }
        }
        return InteractionResult.sidedSuccess(context.getLevel().isClientSide);
    }
}
