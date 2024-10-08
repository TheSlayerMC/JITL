package net.jitl.common.items.gear.korite;

import net.jitl.common.items.gear.IAbility;

import java.util.UUID;

public class KoriteSwordAbility implements IAbility {
    private static final UUID ID = UUID.fromString("cb129eb4-c625-4a9a-b778-00176b83610d");
/*
    @Override
    public void attackTarget(LivingEntity attacker, ItemStack stack, LivingHurtEvent event) {
        if (stack.hasTag()) {
            Objects.requireNonNull(attacker.getAttribute(Attributes.ATTACK_DAMAGE)).removeModifier(ID);
            if (stack.getTag() != null)
            stack.getTag().putFloat("bonus", 0);
        }
    }

    @Override
    public void rightClick(Player player, InteractionHand hand, Level world) {
        if (!world.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            ItemStack stack = player.getMainHandItem();
            if (!stack.hasTag()) stack.setTag(new CompoundTag());
            CompoundTag nbt = stack.getTag();
            PlayerEssence essence = player.getData(JDataAttachments.ESSENCE);
                float bonus = Math.min(essence.getCurrentEssence(), 5.0F);
                if (nbt != null)
                if (nbt.getFloat("bonus") < bonus && essence.consumeEssence(player, bonus)) {
                    nbt.putFloat("bonus", bonus);
                    addModifier(player, bonus);
                }

        }

    @Override
    public void equip(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.MAINHAND && stack.hasTag()) {
            if (stack.getTag() != null) {
                float bonus = stack.getTag().getFloat("bonus");
                if (bonus > 0) {
                    addModifier(entity, bonus);
                }
            }
        }
    }

    @Override
    public void unEquip(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.MAINHAND) {
            Objects.requireNonNull(entity.getAttribute(Attributes.ATTACK_DAMAGE)).removeModifier(ID);
        }
    }

    @Override
    public void fillTooltips(ItemStack stack, List<Component> tooltip) {
        TooltipFiller filler = new TooltipFiller(tooltip, "korite_sword");
        filler.addOverview();
        filler.addDrawback();
        filler.addBreak();
        assert stack.getTag() != null;
        filler.addValue(Math.floor(stack.getTag().getFloat("bonus") * 100) / 100);
    }

    private void addModifier(LivingEntity entity, float value) {
        AttributeInstance attribute = entity.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attribute != null) {
            attribute.removeModifier(ID);
            attribute.addTransientModifier(new AttributeModifier(ID,
                    "Korite sword",
                    value,
                    AttributeModifier.Operation.ADDITION));
        }
    }*/
}
