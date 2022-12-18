package net.jitl.common.items.base;

import net.jitl.core.helper.JToolTiers;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.item.PickaxeItem;

public class JPickaxeItem extends PickaxeItem {

    public JPickaxeItem(JToolTiers tier) {
        super(tier.getTier(), tier.getDamage(), tier.getSpeedModifier(), JItems.itemProps());
    }
}
