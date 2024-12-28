package net.jitl.common.items.base;

import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;

public class JShieldItem extends ShieldItem {

    public final ResourceLocation texture;

    public JShieldItem(Properties p, String name, int uses, Item repairItem) {
        super(p.durability(uses).repairable(repairItem));
        this.texture = JITL.rl("textures/shield/" + name + "_shield.png");
    }
}
