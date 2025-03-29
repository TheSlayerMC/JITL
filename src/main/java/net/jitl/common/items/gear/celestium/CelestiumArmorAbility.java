package net.jitl.common.items.gear.celestium;

import net.jitl.common.items.gear.FullArmorAbility;
import net.jitl.common.items.gear.IAbility;
import net.jitl.core.helper.TooltipFiller;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.Consumer;

public class CelestiumArmorAbility implements IAbility {

    @Override
    public FullArmorAbility getFullAbility(CompoundTag nbt) {
        return new CelestiumFullAbility(nbt);
    }

    @Override
    public void fillTooltips(ItemStack stack, Consumer<Component> tooltip) {
        TooltipFiller filler = new TooltipFiller(tooltip, "celestium_armor");
        filler.addOverview();
        filler.addDetail();
        filler.addDrawback();
    }
}
