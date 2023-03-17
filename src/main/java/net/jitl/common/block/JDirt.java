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

    public Block getFarmlandFromGrassDirt(Block checkedBlock) {
        Block farmLand = null;
        if(checkedBlock == JBlocks.GOLDITE_GRASS.get() || checkedBlock == JBlocks.EUCA_GOLD_GRASS.get() || this == JBlocks.GOLDITE_DIRT.get())
            farmLand = JBlocks.GOLDITE_FARMLAND.get();

        if(checkedBlock == JBlocks.GRASSY_PERMAFROST.get() || checkedBlock == JBlocks.CRUMBLED_PERMAFROST.get())
            farmLand = JBlocks.PERMAFROST_FARMLAND.get();

        if(checkedBlock == JBlocks.DEPTHS_GRASS.get() || checkedBlock == JBlocks.DEPTHS_DIRT.get())
            farmLand = JBlocks.DEPTHS_FARMLAND.get();

        if(checkedBlock == JBlocks.CORBA_GRASS.get() || checkedBlock == JBlocks.CORBA_DIRT.get())
            farmLand = JBlocks.CORBA_FARMLAND.get();

        if(checkedBlock == JBlocks.TERRANIAN_GRASS.get() || checkedBlock == JBlocks.TERRANIAN_DIRT.get())
            farmLand = JBlocks.TERRANIAN_DIRT.get();

        if(checkedBlock == JBlocks.CLOUDIA_GRASS.get() || checkedBlock == JBlocks.CLOUDIA_DIRT.get())
            farmLand = JBlocks.CLOUDIA_DIRT.get();

        return farmLand;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        Item itemstack = pPlayer.getItemInHand(pHand).getItem();
        Block farmLand = getFarmlandFromGrassDirt(pLevel.getBlockState(pPos).getBlock());
        if(itemstack instanceof HoeItem || itemstack instanceof MultitoolItem) {
            if(farmLand != null) {
                pLevel.playSound(pPlayer, pPos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                if(!pLevel.isClientSide) {
                    pLevel.setBlock(pPos, farmLand.defaultBlockState(), 2);
                    if(!pPlayer.isCreative())
                        pPlayer.getItemInHand(pHand).hurt(1, pLevel.random, (ServerPlayer) pPlayer);
                }
                return InteractionResult.SUCCESS;
            }
        }
        return  InteractionResult.PASS;
    }
}
