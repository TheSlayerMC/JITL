package net.jitl.common.items;

import net.jitl.common.items.base.JItem;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class FoilItem extends JItem {

    public FoilItem(Properties p) {
        super(p);
    }

    public FoilItem() {
        this(JItems.itemProps());
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }
}
