package net.jitl.common.items.base;

import net.jitl.core.helper.JToolTiers;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.item.AxeItem;

public class JAxeItem extends AxeItem {
    public JAxeItem(JToolTiers tier) {
        super(tier.getTier(), tier.getDamage(), tier.getSpeedModifier(), JItems.toolProps());
    }
}
