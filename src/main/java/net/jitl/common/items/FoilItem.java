package net.jitl.common.items;

import net.jitl.core.init.internal.JItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class FoilItem extends Item {

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
