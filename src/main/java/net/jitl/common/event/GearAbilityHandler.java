package net.jitl.common.event;

import net.jitl.common.capability.gear.PlayerArmorProvider;
import net.jitl.common.items.base.JArmorItem;
import net.jitl.common.items.gear.FullArmorAbility;
import net.jitl.common.items.gear.JGear;
import net.jitl.core.init.JITL;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class GearAbilityHandler {

    @SubscribeEvent
    public static void handleTick(LivingEvent event) {
        event.setPhase(EventPriority.LOW);
        if(event.getEntity() instanceof Player player) {
            if(player.getInventory() != null) {
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
            }
            event.getEntity().getCapability(PlayerArmorProvider.PLAYER_ARMOR).ifPresent(armor -> {
                ArrayList<ItemStack> stacks = armor.getArmor();
                if (stacks != null) {
                    for (ItemStack stack : stacks) {
                        if(stack.getItem() instanceof JArmorItem)
                            Objects.requireNonNull(((JArmorItem) stack.getItem()).getAbility()).tick(player, player.level(), stack);
                    }
                }
                FullArmorAbility fullSet = armor.getFullArmor();
                if (fullSet != null) {
                    fullSet.tick(player);
                }
            });
        }
    }

    @SubscribeEvent()
    public static void handleIncomingAttack(LivingHurtEvent event) {
        Entity entity = event.getSource().getDirectEntity();
        if (entity != null) {
            if (entity instanceof LivingEntity living) {
                ItemStack stack = living.getMainHandItem();
                Item item = stack.getItem();
                if (item instanceof JGear) {
                    ((JGear) item).getAbility().attackTarget(living, stack, event);
                }
            } else if (entity.getType() == EntityType.ARROW) {
                if (((Arrow) entity).getOwner() instanceof LivingEntity) {
                    for (ItemStack itemStack : ((Arrow) entity).getOwner().getArmorSlots()) {
                        Item current = itemStack.getItem();
                        if (!(current instanceof ArmorItem && ((ArmorItem) current).getMaterial() == ArmorMaterials.LEATHER))
                            return;
                    }
                    event.setAmount(event.getAmount() + 5F);
                }
            }
        }
    }

    @SubscribeEvent()
    public static void handleDamageDealt(LivingDamageEvent event) {
        Entity entity = event.getSource().getDirectEntity();
        if (entity instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) entity;
            ItemStack stack = living.getMainHandItem();
            Item item = stack.getItem();
            if (item instanceof JGear) {
                ((JGear) item).getAbility().damageTarget(living, stack, event);
            }
        }
        event.getEntity().getCapability(PlayerArmorProvider.PLAYER_ARMOR).ifPresent(armor -> {
            if (armor.getFullArmor() != null) {
                armor.getFullArmor().hit(event);
            }
        });
    }

    public static void onKeyPressed(Player player) {
        if(player != null) {
            player.getCapability(PlayerArmorProvider.PLAYER_ARMOR).ifPresent(playerArmor -> {
                FullArmorAbility armor = playerArmor.getFullArmor();
                if (armor != null) armor.keyPressed(player);
            });
        }
    }

    //TODO: reimplement vanilla tooltips

    /*@SubscribeEvent()
    public static void addVanillaTooptips(ItemTooltipEvent event) {
        Item item = event.getItemStack().getItem();
        if (item instanceof ArmorItem) {
            ArmorMaterial material = ((ArmorItem) item).getMaterial();
            if (material == ArmorMaterials.LEATHER) {
                TooltipFiller filler = new TooltipFiller(event.getToolTip(), "leather_gear", 1);
                filler.addOverview();
            } else if (material == ArmorMaterials.CHAIN) {
                TooltipFiller filler = new TooltipFiller(event.getToolTip(), "chain_gear");
                filler.addOverview();
            }
        }
    }

    /*@SubscribeEvent()
    public static void handlePlayerSwing(AttackEntityEvent event) {
        PlayerEntity player = event.getPlayer();
        //Attempts to imitate the vanilla sweep check
        ItemStack stack = player.getMainHandItem();
        if (stack.getItem() instanceof JSwordItem) {
            if (player.getAttackStrengthScale(0.5F) > 0.9F && !player.isSprinting()) { //combines flag and flag1, since there's no reason not to
                System.out.println("Pre-swing");
                if (player.isOnGround() && player.walkDist - player.walkDistO < player.getSpeed()) { //flag3. flag2 is ignored as the isOnGround() call in flag3 automatically means flag2 will be false
                    System.out.println("Swing");
                    ((JToolTiers) ((JSwordItem) stack.getItem()).getTier()).getAbilities().onSweep(stack, event.getTarget(), player);
                }
            }
        }
    }

    @SubscribeEvent()
    public static void handlePreHurt(LivingHurtEvent event) {
        System.out.println("Pre damage: " + event.getAmount());
        Entity attacker = event.getSource().getDirectEntity();
        float damageModifier = 0;
        if (attacker instanceof LivingEntity) {
            Item heldItem = ((LivingEntity) attacker).getItemInHand(Hand.MAIN_HAND).getItem();
            if (heldItem instanceof JSwordItem) {
                BaseToolAbilities ability = ((JToolTiers) ((JSwordItem) heldItem).getTier()).getAbilities();
                if (ability != null) {
                    damageModifier += ability.getSwordDamageModifier(event);
                }
            }
        }
        event.setAmount(event.getAmount() + damageModifier);
    }

    @SubscribeEvent
    public static void handlePostHurt(LivingDamageEvent event) {
        System.out.println("Post damage: " + event.getAmount());
        float damageModifier = 0;
        Optional<IArmorSetCapability> optional = event.getEntityLiving().getCapability(JCapabilityProvider.ARMOR).resolve();
        if (optional.isPresent()) {
            PieceArmorAbilities gear = optional.get().getArmor();
            if (gear != null) {
                damageModifier += gear.getArmorReduction(event);
            }
        }
        event.setAmount(event.getAmount() + damageModifier);
        System.out.println("Post effect: " + event.getAmount());
    }*/

    @SubscribeEvent()
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
        if (slot.getType() == EquipmentSlot.Type.ARMOR) {
            entity.getCapability(PlayerArmorProvider.PLAYER_ARMOR).ifPresent(playerArmor -> playerArmor.setArmor(entity.getArmorSlots().iterator()));
        }
    }
}