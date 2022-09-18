package net.jitl.common.items.base;

import net.jitl.core.helper.EnumJTier;
import net.jitl.core.init.internal.ItemRegistrys;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;

public class JHoeItem extends HoeItem {

    public JHoeItem(EnumJTier tier) {
        super(tier.getTier(), tier.getDamage(), tier.getSpeedModifier(), ItemRegistrys.toolProps());
    }
}
