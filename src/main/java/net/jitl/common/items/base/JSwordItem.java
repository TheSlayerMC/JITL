package net.jitl.common.items.base;

import net.jitl.core.helper.EnumJTier;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.item.SwordItem;

public class JSwordItem extends SwordItem {

    public JSwordItem(EnumJTier tier) {
        super(tier.getTier(), tier.getDamage(), tier.getSpeedModifier(), JItems.weaponProps());
    }

}
