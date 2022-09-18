package net.jitl.common.items.base;

import net.jitl.core.helper.EnumJTier;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.item.ShovelItem;

public class JShovelItem extends ShovelItem {

    public JShovelItem(EnumJTier tier) {
        super(tier.getTier(), tier.getDamage(), tier.getSpeedModifier(), JItems.toolProps());
    }
}
