package net.jitl.common.items.gear;

public class LuniumAbility implements IAbility.INBTUpdateAbility {
   /* @Override
    public void tick(LivingEntity entity, Level world, ItemStack stack) {
        if (!world.isClientSide()) {
            CompoundTag tag = stack.getTag();
            float value = tag.getFloat("cooldown");
            if (stack.getDamageValue() > 0 || value < 100) {
                value -= entity.level().getBrightness(LightLayer.BLOCK, entity.blockPosition());
                if (value <= 0) {
                    value = 100;
                    stack.setDamageValue(stack.getDamageValue() - 1);
                }
                tag.putFloat("cooldown", value);
            }
        }
    }

    @Override
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