package net.jitl.common.items.base;

import net.jitl.core.helper.JToolTiers;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.item.HoeItem;

public class JHoeItem extends HoeItem {

    public JHoeItem(JToolTiers tier) {
        super(tier.getTier(), JItems.itemProps().attributes(createAttributes(tier.getTier(), tier.getDamage(), tier.getSpeedModifier())));
    }
}
