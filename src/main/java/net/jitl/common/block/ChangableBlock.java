package net.jitl.common.block;

import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class ChangableBlock extends Block {

    public ChangableBlock(BlockBehaviour.Properties props) {
        super(props);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack pStack, @NotNull BlockState state, Level worldIn, @NotNull BlockPos pos, Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        ItemStack heldItem = player.getMainHandItem();
        RandomSource r = RandomSource.create();

        if(worldIn.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else {
            if(this == JBlocks.ELDER_BLOCK.get()) {
                if (heldItem.getItem() == JItems.ELDER_KEY.get()) {
                    worldIn.setBlock(pos, JBlocks.OVERSEER_ELDER_SPAWNER.get().defaultBlockState(), 2);
                    worldIn.playSound(player, pos, SoundEvents.IRON_DOOR_OPEN, SoundSource.BLOCKS, 1.0F, r.nextFloat());

                    if (!player.isCreative())
                        heldItem.shrink(1);
                }
            }
        }
        return InteractionResult.CONSUME;
    }
}
