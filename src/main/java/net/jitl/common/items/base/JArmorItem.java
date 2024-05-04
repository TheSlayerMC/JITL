package net.jitl.common.items.base;

import net.jitl.common.items.gear.IAbility;
import net.jitl.common.items.gear.JGear;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JArmorItem extends ArmorItem implements JGear {

    private final IAbility ability;

    public JArmorItem(Holder<ArmorMaterial> pMaterial, ArmorItem.Type pSlot, IAbility ability) {
        super(pMaterial, pSlot, new Item.Properties());
        this.ability = ability;
    }

    @Nullable
    public IAbility getAbility() {
        if(ability == null) {
            return new IAbility() {};
        } else {
            return ability;
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext pContext, List<Component> tooltip, TooltipFlag pTooltipFlag) {
        super.appendHoverText(stack, pContext, tooltip, pTooltipFlag);
        if(ability != null)
            ability.fillTooltips(stack, tooltip);
    }
}
