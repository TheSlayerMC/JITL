package net.jitl.common.block;

import net.jitl.common.items.base.MultitoolItem;
import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class JDirt extends Block {

    public JDirt() {
        super(JBlockProperties.DIRT);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        Item itemstack = pPlayer.getItemInHand(pHand).getItem();
        Block farmLand = JBlocks.GOLDITE_FARMLAND.get();
        if(this == JBlocks.GOLDITE_DIRT.get()) {
            farmLand = JBlocks.GOLDITE_FARMLAND.get();
        }
        pLevel.playSound(pPlayer, pPos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
        if(!pLevel.isClientSide) {
            pLevel.setBlock(pPos, farmLand.defaultBlockState(), 2);
            pPlayer.getItemInHand(pHand).hurt(1, pLevel.random, (ServerPlayer)pPlayer);
        }
        return itemstack instanceof HoeItem || itemstack instanceof MultitoolItem ? InteractionResult.SUCCESS : InteractionResult.PASS;
    }

}
