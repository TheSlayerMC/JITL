package net.jitl.common.items.curios.amulet;

import net.jitl.common.items.curios.JCurioItem;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;

import java.util.UUID;

public class IceAmuletItem extends JCurioItem {
    protected static final UUID SPEED_MODIFIER = UUID.fromString("758787ea-2eda-4941-8f41-4e3efd1a95a7");
    protected static final UUID DAMAGE_MODIFIER = UUID.fromString("b0d292cf-74cd-4c6e-925f-eb81e78e3582");
    protected static final UUID ATTACK_MODIFIER = UUID.fromString("c0d86a67-553d-4c53-9f68-d8df3a891d38");

    protected static final AttributeModifier SPEED_MOD = new AttributeModifier(SPEED_MODIFIER, "speed_modifier", 0.1F, AttributeModifier.Operation.ADDITION);
    protected static final AttributeModifier DAMAGE_MOD = new AttributeModifier(DAMAGE_MODIFIER, "damage_modifier", 1.0F, AttributeModifier.Operation.ADDITION);
    protected static final AttributeModifier ATTACK_MOD = new AttributeModifier(ATTACK_MODIFIER, "attack_speed_modifier", 0.5F, AttributeModifier.Operation.ADDITION);

    public IceAmuletItem(Item.Properties properties) {
        super(properties);
        properties.durability(256);
    }

   /* @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        Player player = (Player) livingEntity;
        AttributeInstance attribMovementSpeed = player.getAttribute(Attributes.MOVEMENT_SPEED);
        AttributeInstance attribAttackDamage = player.getAttribute(Attributes.ATTACK_DAMAGE);
        AttributeInstance attribAttackSpeed = player.getAttribute(Attributes.ATTACK_SPEED);

        if (player.level().getBiome(player.blockPosition()).value().getBaseTemperature() <= 0.2F) {
            assert attribMovementSpeed != null;
            if (!attribMovementSpeed.hasModifier(SPEED_MOD)) {
                attribMovementSpeed.addTransientModifier(SPEED_MOD);
            }
            if (!attribAttackDamage.hasModifier(DAMAGE_MOD)) {
                attribAttackDamage.addTransientModifier(DAMAGE_MOD);
            }
            if (!attribAttackSpeed.hasModifier(ATTACK_MOD)) {
                attribAttackSpeed.addTransientModifier(ATTACK_MOD);
            }
        } else {
            if (attribMovementSpeed.hasModifier(SPEED_MOD)) {
                attribMovementSpeed.removeModifier(SPEED_MOD.getId());
            }
            if (attribAttackDamage.hasModifier(DAMAGE_MOD)) {
                attribAttackDamage.removeModifier(DAMAGE_MOD.getId());
            }
            if (attribAttackSpeed.hasModifier(ATTACK_MOD)) {
                attribAttackSpeed.removeModifier(ATTACK_MOD.getId());
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        AttributeInstance attribMovementSpeed = slotContext.getWearer().getAttribute(Attributes.MOVEMENT_SPEED);
        AttributeInstance attribAttackDamage = slotContext.getWearer().getAttribute(Attributes.ATTACK_DAMAGE);
        AttributeInstance attribAttackSpeed = slotContext.getWearer().getAttribute(Attributes.ATTACK_SPEED);

        if (attribMovementSpeed.hasModifier(SPEED_MOD)) {
            attribMovementSpeed.removeModifier(SPEED_MOD.getId());
        }
        if (attribAttackDamage.hasModifier(DAMAGE_MOD)) {
            attribAttackDamage.removeModifier(DAMAGE_MOD.getId());
        }
        if (attribAttackSpeed.hasModifier(ATTACK_MOD)) {
            attribAttackSpeed.removeModifier(ATTACK_MOD.getId());
        }
    }*/
}
