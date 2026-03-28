package net.jitl.common.items;

import net.jitl.core.helper.JToolTiers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class BattleAxeItem extends AxeItem {

    public BattleAxeItem(Properties p, JToolTiers tier, float damage) {
        super(tier.getTier(), tier.getDamage() + damage, tier.getSpeedModifier(), p);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext pContext, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag pTooltipFlag) {
        super.appendHoverText(stack, pContext, display, tooltip, pTooltipFlag);
        tooltip.accept(Component.translatable("jitl.uses_remaining", (stack.getMaxDamage() - stack.getDamageValue())));
    }
}