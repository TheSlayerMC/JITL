package net.jitl.common.items;

import net.jitl.core.helper.JToolTiers;
import net.jitl.core.init.internal.JItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;

import java.util.List;

public class BattleAxeItem extends AxeItem {

    public BattleAxeItem(JToolTiers tier, float damage) {
        super(tier.getTier(), JItems.itemProps().attributes(createAttributes(tier.getTier(), tier.getDamage() + damage, tier.getSpeedModifier())).stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext pContext, List<Component> tooltip, TooltipFlag pTooltipFlag) {
        tooltip.add(Component.translatable("jitl.uses_remaining", (stack.getMaxDamage() - stack.getDamageValue())));
    }
}