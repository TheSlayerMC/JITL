package net.jitl.common.items;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.block.JChestBlock;
import net.jitl.common.items.base.JItem;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ChestInteractionItem extends JItem {

    public ChestInteractionItem(Properties p) {
        super(p.stacksTo(16));
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        assert player != null;
        ItemStack heldItem = player.getMainHandItem();
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState blockstate = world.getBlockState(pos);

        if(blockstate.getBlock() instanceof JChestBlock) {
            //Sets the chest key to be the universal key
            unlockChest(player, EnumKnowledge.OVERWORLD, JItems.CHEST_KEY.get(), world, pos, JBlocks.BOIL_CHEST.get(), JBlocks.EUCA_CHEST.get(), JBlocks.FROZEN_CHEST.get(), JBlocks.NETHER_CHEST.get(), JBlocks.JOURNEY_CHEST.get(),
                    JBlocks.DEPTHS_CHEST.get(), JBlocks.CORBA_CHEST.get(), JBlocks.TERRANIAN_CHEST.get(), JBlocks.CLOUDIA_CHEST.get(), JBlocks.SENTERIAN_CHEST.get());

            //Sets specific keys to only open their respective chests
            unlockChest(player, EnumKnowledge.BOIL, JItems.BOILING_KEY.get(), world, pos, JBlocks.BOIL_CHEST.get());
            unlockChest(player, EnumKnowledge.EUCA, JItems.EUCA_KEY.get(), world, pos, JBlocks.EUCA_CHEST.get());
            unlockChest(player, EnumKnowledge.FROZEN, JItems.FROZEN_KEY.get(), world, pos, JBlocks.FROZEN_CHEST.get());
            unlockChest(player, EnumKnowledge.NETHER, JItems.NETHER_KEY.get(), world, pos, JBlocks.NETHER_CHEST.get());
            unlockChest(player, EnumKnowledge.OVERWORLD, JItems.JOURNEY_KEY.get(), world, pos, JBlocks.JOURNEY_CHEST.get());
            unlockChest(player, EnumKnowledge.DEPTHS, JItems.DEPTHS_CHEST_KEY.get(), world, pos, JBlocks.DEPTHS_CHEST.get());
            unlockChest(player, EnumKnowledge.CORBA, JItems.CORBA_KEY.get(), world, pos, JBlocks.CORBA_CHEST.get());
            unlockChest(player, EnumKnowledge.TERRANIA, JItems.TERRANIAN_KEY.get(), world, pos, JBlocks.TERRANIAN_CHEST.get());
            unlockChest(player, EnumKnowledge.CLOUDIA, JItems.CLOUDIA_KEY.get(), world, pos, JBlocks.CLOUDIA_CHEST.get());
            unlockChest(player, EnumKnowledge.SENTERIAN, JItems.SENTERIAN_KEY.get(), world, pos, JBlocks.SENTERIAN_CHEST.get());

            if(heldItem.getItem() == JItems.PADLOCK.get()) {
                lockChest(player, world, pos);
            }
        }
        return InteractionResult.SUCCESS;
    }

    public void lockChest(Player player, Level world, BlockPos pos) {
        BlockState clickedChest = world.getBlockState(pos);
        Block chest = clickedChest.getBlock();
        if(world.getBlockState(pos).getBlock() == chest) {
            BlockState n = world.getBlockState(pos.north());
            BlockState s = world.getBlockState(pos.south());
            BlockState e = world.getBlockState(pos.east());
            BlockState w = world.getBlockState(pos.west());
            boolean isNorth = n.getBlock() == chest;
            boolean isSouth = s.getBlock() == chest;
            boolean isEast = e.getBlock() == chest;
            boolean isWest = w.getBlock() == chest;
            if(!clickedChest.getValue(JChestBlock.IS_LOCKED)) {
                world.setBlock(pos, clickedChest.setValue(JChestBlock.IS_LOCKED, Boolean.TRUE), 2); //Locks the clicked chest

                //Checks if double chest is adjacent
                if(isNorth) {
                    world.setBlock(pos.north(), n.setValue(JChestBlock.IS_LOCKED, Boolean.TRUE), 2);
                }
                if(isSouth) {
                    world.setBlock(pos.south(), s.setValue(JChestBlock.IS_LOCKED, Boolean.TRUE), 2);
                }
                if(isEast) {
                    world.setBlock(pos.east(), e.setValue(JChestBlock.IS_LOCKED, Boolean.TRUE), 2);
                }
                if(isWest) {
                    world.setBlock(pos.west(), w.setValue(JChestBlock.IS_LOCKED, Boolean.TRUE), 2);
                }
                player.getMainHandItem().shrink(1);
                world.playSound(null, pos, SoundEvents.IRON_DOOR_CLOSE, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
        }
    }

    public void unlockChest(Player player, EnumKnowledge knowledge, Item key, Level world, BlockPos pos, Block...c) {
        for(Block chest : c) {
            if (player.getMainHandItem().getItem() == key && world.getBlockState(pos).getBlock() == chest) {
                BlockState n = world.getBlockState(pos.north());
                BlockState s = world.getBlockState(pos.south());
                BlockState e = world.getBlockState(pos.east());
                BlockState w = world.getBlockState(pos.west());
                boolean isNorth = n.getBlock() == chest;
                boolean isSouth = s.getBlock() == chest;
                boolean isEast = e.getBlock() == chest;
                boolean isWest = w.getBlock() == chest;
                BlockState clickedChest = world.getBlockState(pos);
                if(clickedChest.getValue(JChestBlock.IS_LOCKED)) {
                    world.setBlock(pos, clickedChest.setValue(JChestBlock.IS_LOCKED, Boolean.FALSE), 2); //Locks the clicked chest

                    //Checks if double chest is adjacent
                    if(isNorth) {
                        world.setBlock(pos.north(), n.setValue(JChestBlock.IS_LOCKED, Boolean.FALSE), 2);
                    }
                    if(isSouth) {
                        world.setBlock(pos.south(), s.setValue(JChestBlock.IS_LOCKED, Boolean.FALSE), 2);
                    }
                    if(isEast) {
                        world.setBlock(pos.east(), e.setValue(JChestBlock.IS_LOCKED, Boolean.FALSE), 2);
                    }
                    if(isWest) {
                        world.setBlock(pos.west(), w.setValue(JChestBlock.IS_LOCKED, Boolean.FALSE), 2);
                    }
                    player.getMainHandItem().shrink(1);
                    world.playSound(null, pos, SoundEvents.IRON_DOOR_OPEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                    player.getData(JDataAttachments.PLAYER_STATS).addXP(knowledge, 10F, player);
                }
            }
        }
    }

    public void checkSpecificKeyToChest(Player player, Item key, Block chest, BlockState blockstate, Level world, BlockPos pos) {
        if(player.getMainHandItem().getItem() == key && blockstate.getBlock() == chest) {
            if(blockstate.getValue(JChestBlock.IS_LOCKED)) {
                BlockState s = blockstate.setValue(JChestBlock.IS_LOCKED, Boolean.FALSE);
                world.setBlock(pos, s, 2);
                player.getMainHandItem().shrink(1);
                world.playSound(null, pos, SoundEvents.IRON_DOOR_OPEN, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
        }
    }

}