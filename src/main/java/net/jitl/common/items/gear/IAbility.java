package net.jitl.common.items.gear;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.List;
import java.util.function.Consumer;

public interface IAbility {

    default void tick(LivingEntity entity, Level world, ItemStack stack) {

    }

    default void onSweep(ItemStack stack, Entity target, Player player) {

    }

    default void playerInteract(PlayerInteractEvent event) {

    }

    default void attackTarget(LivingEntity attacker, ItemStack stack, LivingDamageEvent.Pre event) {

    }

    default void damageTarget(LivingEntity holder, ItemStack stack, LivingDamageEvent event) {

    }

    default void rightClick(Player player, InteractionHand hand, Level world) {

    }

    default void breakBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, Player entity) {

    }

    default float blockBreakSpeed(ItemStack stack, BlockState state, float original) {
        return original;
    }

    default boolean isCorrectTool(ItemStack stack, BlockState state) {
        Item item = stack.getItem();
        return item.isCorrectToolForDrops(stack, state);
    }

    default void equip(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {

    }

    default void unEquip(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {

    }

    default void fillTooltips(ItemStack stack, Consumer<Component> tooltip) {

    }

    default boolean animate(boolean original, ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return original;
    }

    default FullArmorAbility getFullAbility(CompoundTag nbt) {
        return null;
    }

    default boolean resetBreak(boolean original, ItemStack oldStack, ItemStack newStack) {
        return original;
    }

    interface INBTUpdateAbility extends IAbility {
        @Override
        default boolean animate(boolean original, ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
            if (oldStack.equals(newStack)) return false;
            if (oldStack.is(newStack.getItem())) {
                int durability = oldStack.getDamageValue() - newStack.getDamageValue();
                return durability != 0 && durability != 1;
            }
            return true;
        }

        @Override
        default boolean resetBreak(boolean original, ItemStack oldStack, ItemStack newStack) {
            if (oldStack.equals(newStack)) return false;
            if (oldStack.is(newStack.getItem())) {
                int durability = oldStack.getDamageValue() - newStack.getDamageValue();
                return durability != 0 && durability != 1;
            }
            return true;
        }

        @Override
        default void equip(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {

        }
    }
}