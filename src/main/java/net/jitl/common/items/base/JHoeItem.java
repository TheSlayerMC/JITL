package net.jitl.common.items.base;

import net.jitl.core.helper.JToolTiers;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.item.HoeItem;

public class JHoeItem extends HoeItem {

    public JHoeItem(JToolTiers tier, int dam) {
        super(tier.getTier(), JItems.itemProps().attributes(createAttributes(tier.getTier(), dam, tier.getSpeedModifier())));
    }
}
