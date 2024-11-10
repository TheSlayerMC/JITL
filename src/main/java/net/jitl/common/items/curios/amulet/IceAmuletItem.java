package net.jitl.common.items.curios.amulet;

import net.jitl.common.items.curios.JCurioItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public class IceAmuletItem extends JCurioItem {
    protected static final UUID SPEED_MODIFIER = UUID.fromString("758787ea-2eda-4941-8f41-4e3efd1a95a7");
    protected static final UUID DAMAGE_MODIFIER = UUID.fromString("b0d292cf-74cd-4c6e-925f-eb81e78e3582");
    protected static final UUID ATTACK_MODIFIER = UUID.fromString("c0d86a67-553d-4c53-9f68-d8df3a891d38");

    protected static final AttributeModifier SPEED_MOD = new AttributeModifier(ResourceLocation.withDefaultNamespace("speed_modifier"), 0.1F, AttributeModifier.Operation.ADD_VALUE);
    protected static final AttributeModifier DAMAGE_MOD = new AttributeModifier(ResourceLocation.withDefaultNamespace("damage_modifier"), 1.0F, AttributeModifier.Operation.ADD_VALUE);
    protected static final AttributeModifier ATTACK_MOD = new AttributeModifier(ResourceLocation.withDefaultNamespace("attack_speed_modifier"), 0.5F, AttributeModifier.Operation.ADD_VALUE);

    public IceAmuletItem(Item.Properties properties) {
        super(properties);
        properties.durability(256);
    }

//TODO    @Override
//    public void curioTick(SlotContext slotContext, ItemStack stack) {
//        if(slotContext.entity() instanceof Player player) {
//            AttributeInstance attribMovementSpeed = player.getAttribute(Attributes.MOVEMENT_SPEED);
//            AttributeInstance attribAttackDamage = player.getAttribute(Attributes.ATTACK_DAMAGE);
//            AttributeInstance attribAttackSpeed = player.getAttribute(Attributes.ATTACK_SPEED);
//
//            if (player.level().getBiome(player.blockPosition()).value().getBaseTemperature() <= 0.2F) {
//                assert attribMovementSpeed != null;
//                if (!attribMovementSpeed.hasModifier(SPEED_MOD.id())) {
//                    attribMovementSpeed.addTransientModifier(SPEED_MOD);
//                }
//                if (!attribAttackDamage.hasModifier(DAMAGE_MOD.id())) {
//                    attribAttackDamage.addTransientModifier(DAMAGE_MOD);
//                }
//                if (!attribAttackSpeed.hasModifier(ATTACK_MOD.id())) {
//                    attribAttackSpeed.addTransientModifier(ATTACK_MOD);
//                }
//            } else {
//                if (attribMovementSpeed.hasModifier(SPEED_MOD.id())) {
//                    attribMovementSpeed.removeModifier(SPEED_MOD.id());
//                }
//                if (attribAttackDamage.hasModifier(DAMAGE_MOD.id())) {
//                    attribAttackDamage.removeModifier(DAMAGE_MOD.id());
//                }
//                if (attribAttackSpeed.hasModifier(ATTACK_MOD.id())) {
//                    attribAttackSpeed.removeModifier(ATTACK_MOD.id());
//                }
//            }
//        }
//    }
//
//    @Override
//    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
//        AttributeInstance attribMovementSpeed = slotContext.entity().getAttribute(Attributes.MOVEMENT_SPEED);
//        AttributeInstance attribAttackDamage = slotContext.entity().getAttribute(Attributes.ATTACK_DAMAGE);
//        AttributeInstance attribAttackSpeed = slotContext.entity().getAttribute(Attributes.ATTACK_SPEED);
//
//        if (attribMovementSpeed.hasModifier(SPEED_MOD.id())) {
//            attribMovementSpeed.removeModifier(SPEED_MOD.id());
//        }
//        if (attribAttackDamage.hasModifier(DAMAGE_MOD.id())) {
//            attribAttackDamage.removeModifier(DAMAGE_MOD.id());
//        }
//        if (attribAttackSpeed.hasModifier(ATTACK_MOD.id())) {
//            attribAttackSpeed.removeModifier(ATTACK_MOD.id());
//        }
//    }
}
