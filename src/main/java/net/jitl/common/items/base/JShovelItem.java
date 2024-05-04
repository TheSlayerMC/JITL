package net.jitl.common.items.base;

import net.jitl.core.helper.JToolTiers;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.item.ShovelItem;

public class JShovelItem extends ShovelItem {

    public JShovelItem(JToolTiers tier) {
        super(tier.getTier(), JItems.itemProps());
    }
}
