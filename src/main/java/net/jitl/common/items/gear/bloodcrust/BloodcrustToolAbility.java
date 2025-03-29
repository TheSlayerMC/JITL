package net.jitl.common.items.gear.bloodcrust;

import net.jitl.common.capability.player.BloodcrustAbility;
import net.jitl.common.items.gear.IAbility;
import net.jitl.core.helper.TooltipFiller;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.List;
import java.util.function.Consumer;

public class BloodcrustToolAbility implements IAbility {

    @Override
    public void breakBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, Player entity) {
        BloodcrustAbility bloodcrust = stack.get(JDataComponents.BLOODCRUST.get());
        if(stack.has(JDataComponents.BLOODCRUST)) {
            if (bloodcrust.fire_boost() >= 1)
                stack.set(JDataComponents.BLOODCRUST, new BloodcrustAbility(bloodcrust.fire_boost() - 1));
            System.out.println(bloodcrust.fire_boost());
        }
    }

    @Override
    public void playerInteract(PlayerInteractEvent event) {
        if(event.getLevel().getBlockState(event.getPos()).is(Blocks.FIRE)) {
            event.getItemStack().set(JDataComponents.BLOODCRUST, new BloodcrustAbility(16));
            System.out.println("boost");
        }
    }

   @Override
    public float blockBreakSpeed(ItemStack stack, BlockState state, float original) {
       if(stack.has(JDataComponents.BLOODCRUST)) {
           if (isCorrectTool(stack, state)) {
               original += (float) stack.get(JDataComponents.BLOODCRUST).fire_boost();
           }
       }
        return original;
    }

    @Override
    public void fillTooltips(ItemStack stack, Consumer<Component> tooltip) {
        TooltipFiller filler = new TooltipFiller(tooltip, "bloodcrust_tool");
        filler.addOverview();
        filler.addDrawback();
        filler.addBreak();
        if(stack.has(JDataComponents.BLOODCRUST.get()))
            filler.addValue(stack.get(JDataComponents.BLOODCRUST).fire_boost());
    }
}
