package net.jitl.common.block;

import net.jitl.common.block.base.FaceableBlock;
import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class LockBlock extends FaceableBlock {

    public LockBlock() {
        super(JBlockProperties.DUNGEON_BLOCK);
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(ItemStack pStack, BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        ItemStack heldItem = player.getMainHandItem();
        Random r = new Random();
        boolean canOpen = this == JBlocks.BOIL_LOCK.get() && heldItem.getItem() == JItems.BOIL_LOCK_KEY.get() ||
                this == JBlocks.DARKLY_LOCK.get() && heldItem.getItem() == JItems.DARK_KEY.get() ||
                this == JBlocks.DEPTHS_LOCK.get() && heldItem.getItem() == JItems.DEPTHS_KEY.get() ||
                this == JBlocks.SENTRY_LOCK.get() && heldItem.getItem() == JItems.SENTRY_KEY.get();

        if(heldItem != null && canOpen) {
            worldIn.playSound(player, pos, SoundEvents.IRON_DOOR_OPEN, SoundSource.BLOCKS, 1.0F, r.nextFloat());
            if(worldIn.isClientSide) {
                return ItemInteractionResult.SUCCESS;
            } else {
                if(state.getValue(FACING) == Direction.WEST || state.getValue(FACING) == Direction.EAST) {
                    for(int y = -1; y < 2; y++) {
                        for(int z = -1; z < 2; z++) {
                            worldIn.removeBlock(pos.offset(0, y, z), false);
                        }
                    }
                }
                if(state.getValue(FACING) == Direction.NORTH || state.getValue(FACING) == Direction.SOUTH) {
                    for (int x = -1; x < 2; x++) {
                        for (int y = -1; y < 2; y++) {
                            worldIn.removeBlock(pos.offset(x, y, 0), false);
                        }
                    }
                }
                if(!player.isCreative())
                    heldItem.shrink(1);
            }
        }
        return ItemInteractionResult.CONSUME;
    }
}
