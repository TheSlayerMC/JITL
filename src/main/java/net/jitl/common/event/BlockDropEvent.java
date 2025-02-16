package net.jitl.common.event;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockDropsEvent;

import java.util.List;
import java.util.Objects;

@EventBusSubscriber(modid = JITL.MODID)
public class BlockDropEvent {

    @SubscribeEvent
    public static void onBlockHarvested(BlockDropsEvent event) {
//        ItemStack heldItem = event.getTool();
//        List<ItemEntity> itemEntity = event.getDrops();
//        BlockPos pos = event.getBlockEntity().getBlockPos();
//
//        if(event.getBreaker() instanceof Player player) {
//            RecipeHolder<SmeltingRecipe> holder = RecipeManager.createCheck(RecipeType.SMELTING).getRecipeFor(new SingleRecipeInput(itemEntity.getFirst().getItem()), player.level()).get();
//            ItemStack smelted = holder.value().getResultItem(null);
//
//            if(smelted != null) {
//                ItemEntity entity = new ItemEntity(player.level(), pos.getX(), pos.getY(), pos.getZ(), smelted);
//                if (heldItem.is(JItems.BLOODCRUST_MULTITOOL)) {
//                    itemEntity.removeFirst();
//                    itemEntity.add(entity);
//                }
//            }
//        }
    }
}