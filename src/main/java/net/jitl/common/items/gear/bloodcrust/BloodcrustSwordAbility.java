package net.jitl.common.items.gear.bloodcrust;

import net.jitl.common.capability.player.BloodcrustAbility;
import net.jitl.common.items.gear.IAbility;
import net.jitl.core.helper.TooltipFiller;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class BloodcrustSwordAbility implements IAbility {

    private static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "bloodcrust_ability");

    @Override
    public void damageTarget(LivingEntity holder, ItemStack stack, LivingDamageEvent event) {
        Entity entity = event.getEntity();
        stack.set(JDataComponents.BLOODCRUST.get(), new BloodcrustAbility(entity.getRemainingFireTicks() / 20));
        entity.clearFire();
    }

    @Override
    public void equip(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {
        if(stack.has(JDataComponents.BLOODCRUST.get())) {
            if (slot == EquipmentSlot.MAINHAND) {
                AttributeInstance attribute = entity.getAttribute(Attributes.ATTACK_DAMAGE);
                attribute.removeModifier(ID);
                double amount = stack.get(JDataComponents.BLOODCRUST).fire_boost();
                if (amount > 0) {
                    Objects.requireNonNull(entity.getAttribute(Attributes.ATTACK_DAMAGE)).addTransientModifier(new AttributeModifier(
                            ID,
                            amount,
                            AttributeModifier.Operation.ADD_VALUE));
                }
            }
        }
    }

    @Override
    public void unEquip(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.MAINHAND) entity.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ID);
    }

    @Override
    public void fillTooltips(ItemStack stack, Consumer<Component> tooltip) {
        TooltipFiller filler = new TooltipFiller(tooltip, "bloodcrust_sword");
        filler.addOverview();
        filler.addDetail();
        filler.addBreak();
        if(stack.has(JDataComponents.BLOODCRUST.get()))
            filler.addValue(stack.get(JDataComponents.BLOODCRUST).fire_boost());
    }
}
