package net.jitl.common.items.base;

import net.jitl.common.items.gear.IAbility;
import net.jitl.common.items.gear.JGear;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class JArmorItem extends JItem implements JGear {

    private final IAbility ability;
    private final ArmorMaterial material;

    public JArmorItem(Item.Properties p, ArmorMaterial pMaterial, ArmorType pSlot, IAbility ability) {
        super(p.stacksTo(1).humanoidArmor(pMaterial, pSlot));
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
    public void appendHoverText(ItemStack stack, TooltipContext pContext, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag pTooltipFlag) {
        super.appendHoverText(stack, pContext, display, tooltip, pTooltipFlag);
        if(ability != null)
        ability.fillTooltips(stack, tooltip);
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return true;
    }

}
