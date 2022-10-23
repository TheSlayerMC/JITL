package net.jitl.common.items.gear;

import net.jitl.core.init.JITL;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

import java.util.List;

public class LuniumAbility implements IAbility.INBTUpdateAbility {
    @Override
    public void tick(LivingEntity entity, Level world, ItemStack stack) {
        if (!world.isClientSide()) {
            CompoundTag tag = stack.getTag();
            float value = tag.getFloat("cooldown");
            if (stack.getDamageValue() > 0 || value < 100) {
                value -= entity.level.getBrightness(LightLayer.BLOCK, entity.blockPosition());
                if (value <= 0) {
                    value = 100;
                    stack.setDamageValue(stack.getDamageValue() - 1);
                }
                tag.putFloat("cooldown", value);
                System.out.println(tag.getFloat("cooldown"));
                JITL.LOGGER.info(value);
            }
        }
    }

    /*@Override
    public void fillTooltips(ItemStack stack, List<Component> tooltip) {
        TooltipFiller filler = new TooltipFiller(tooltip, "lunium_gear");
        filler.addOverview();
        filler.addDrawback();
        if (stack.getDamageValue() > 0) {
            filler.addBreak();
            filler.addValue((int) (100 - stack.getTag().getFloat("cooldown")));
        }
    }*/
}
