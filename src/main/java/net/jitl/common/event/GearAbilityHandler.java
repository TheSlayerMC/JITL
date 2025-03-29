package net.jitl.common.event;

import net.jitl.common.capability.gear.PlayerArmor;
import net.jitl.common.items.base.JArmorItem;
import net.jitl.common.items.base.JSwordItem;
import net.jitl.common.items.gear.FullArmorAbility;
import net.jitl.common.items.gear.JGear;
import net.jitl.core.helper.TooltipFiller;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.ArrayList;
import java.util.Objects;

@EventBusSubscriber(modid = JITL.MOD_ID)
public class GearAbilityHandler {

    @SubscribeEvent
    public static void handleTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        player.getInventory();
        ItemStack hand = player.getMainHandItem();
        Item item = hand.getItem();
        if (item instanceof JGear && !(item instanceof JArmorItem)) {
            ((JGear) hand.getItem()).getAbility().tick(player, player.level(), hand);
        }
        hand = player.getOffhandItem();
        item = hand.getItem();
        if (item instanceof JGear && !(item instanceof JArmorItem)) {
            ((JGear) hand.getItem()).getAbility().tick(player, player.level(), hand);
        }
        PlayerArmor armor = event.getEntity().getData(JDataAttachments.PLAYER_ARMOR);
        ArrayList<ItemStack> stacks = armor.getArmor();
        if (stacks != null) {
            for (ItemStack stack : stacks) {
                if(stack.getItem() instanceof JArmorItem)
                    Objects.requireNonNull(((JArmorItem) stack.getItem()).getAbility()).tick(player, player.level(), stack);
            }
        }
        FullArmorAbility fullSet = armor.getFullArmor();
        if(fullSet != null) {
            fullSet.tick(player);
        }
    }

    @SubscribeEvent
    public static void handleIncomingAttack(LivingDamageEvent.Pre event) {
        Entity entity = event.getSource().getDirectEntity();
        if (entity != null) {
            if (entity instanceof LivingEntity living) {
                ItemStack stack = living.getMainHandItem();
                Item item = stack.getItem();
                if (item instanceof JGear) {
                    ((JGear) item).getAbility().attackTarget(living, stack, event);
                }
            } else if (entity.getType() == EntityType.ARROW) {
                if (((Arrow) entity).getOwner() instanceof LivingEntity owner) {
                    for (ItemStack itemStack : owner.getArmorSlots()) {
                        Item current = itemStack.getItem();
                        if (!(current instanceof ArmorItem && ((ArmorItem) current).getMaterial() == ArmorMaterials.LEATHER))
                            return;
                    }
                    event.setNewDamage(event.getOriginalDamage() + 5F);
                }
            }
        }
    }

    @SubscribeEvent
    public static void handleDamageDealt(LivingDamageEvent.Pre event) {
        LivingEntity entity = event.getEntity();
        ItemStack stack = entity.getMainHandItem();
        Item item = stack.getItem();
        if (item instanceof JGear) {
            ((JGear) item).getAbility().damageTarget(entity, stack, event);
        }
        PlayerArmor armor = event.getEntity().getData(JDataAttachments.PLAYER_ARMOR);
        if(armor.getFullArmor() != null) {
            armor.getFullArmor().hit(event);
        }
    }

    @SubscribeEvent
    public static void handleInteraction(PlayerInteractEvent.LeftClickBlock event) {
        if(event.getItemStack().getItem() instanceof JGear item) {
            item.getAbility().playerInteract(event);
        }
    }

    @SubscribeEvent
    public static void breakBlock(BlockEvent.BreakEvent event) {
        if(event.getPlayer().getMainHandItem().getItem() instanceof JGear item) {
            item.getAbility().breakBlock(event.getPlayer().getMainHandItem(), event.getPlayer().level(), event.getState(), event.getPos(), event.getPlayer());
        }
    }

    @SubscribeEvent
    public static void breakSpeed(PlayerEvent.BreakSpeed event) {
        if(event.getEntity().getMainHandItem().getItem() instanceof JGear item) {
            event.setNewSpeed(item.getAbility().blockBreakSpeed(event.getEntity().getMainHandItem(), event.getState(), event.getOriginalSpeed()));
        }
    }

    @SubscribeEvent
    public static void addVanillaTooptips(ItemTooltipEvent event) {
        Item item = event.getItemStack().getItem();
        if (item instanceof ArmorItem) {
            ArmorMaterial material = ((ArmorItem) item).getMaterial().value();
            if (material.equals(ArmorMaterials.LEATHER)) {
                TooltipFiller filler = new TooltipFiller(event.getToolTip(), "leather_gear", 1);
                filler.addOverview();
            } else if (material.equals(ArmorMaterials.CHAIN)) {
                TooltipFiller filler = new TooltipFiller(event.getToolTip(), "chain_gear");
                filler.addOverview();
            }
        }
    }

    @SubscribeEvent
    public static void handlePlayerSwing(AttackEntityEvent event) {
        if(event.getEntity() instanceof Player player) {
            ItemStack stack = player.getMainHandItem();
            if(stack.getItem() instanceof JSwordItem sword) {
                if(player.getAttackStrengthScale(0.5F) > 0.9F && !player.isSprinting()) { //combines flag and flag1, since there's no reason not to
                    if(player.onGround() && player.walkDist - player.walkDistO < player.getSpeed()) { //flag3. flag2 is ignored as the isOnGround() call in flag3 automatically means flag2 will be false
                       sword.getAbility().onSweep(stack, event.getTarget(), player);
                    }
                }
            }
        }
    }

    public static void onKeyPressed(Player player) {
        if(player != null) {
            PlayerArmor playerArmor = player.getData(JDataAttachments.PLAYER_ARMOR);
            FullArmorAbility armor = playerArmor.getFullArmor();
            if (armor != null) armor.keyPressed(player);
        }
    }

    @SubscribeEvent
    public static void equipmentChange(LivingEquipmentChangeEvent event) {
        Item item = event.getFrom().getItem();
        LivingEntity entity = event.getEntity();
        EquipmentSlot slot = event.getSlot();
        if (item instanceof JGear) {
            ((JGear) item).getAbility().unEquip(entity, slot, event.getFrom());
        }
        item = event.getTo().getItem();
        if (item instanceof JGear) {
            ((JGear) item).getAbility().equip(entity, slot, event.getTo());
        }
        if(slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR) {
            PlayerArmor armor = event.getEntity().getData(JDataAttachments.PLAYER_ARMOR);
            armor.setArmor(entity.getArmorSlots().iterator());
        }
    }
}