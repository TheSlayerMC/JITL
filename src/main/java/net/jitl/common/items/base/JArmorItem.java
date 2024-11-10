package net.jitl.common.items.base;

import net.jitl.common.items.gear.IAbility;
import net.jitl.common.items.gear.JGear;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JArmorItem extends ArmorItem implements JGear {

    private final IAbility ability;
    private final ArmorMaterial material;

    public JArmorItem(Properties p, ArmorMaterial pMaterial, ArmorType pSlot, IAbility ability) {
        super(pMaterial, pSlot, p.stacksTo(1));
        this.ability = ability;
        this.material = pMaterial;
    }

    public ArmorMaterial getMaterial() {
        return material;
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

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return true;
    }

}
