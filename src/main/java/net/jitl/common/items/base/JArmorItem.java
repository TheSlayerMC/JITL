package net.jitl.common.items.base;

import net.jitl.common.items.gear.IAbility;
import net.jitl.common.items.gear.JGear;
import net.jitl.core.helper.JArmorMaterial;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JArmorItem extends ArmorItem implements JGear {

    private final IAbility ability;

    public JArmorItem(JArmorMaterial pMaterial, EquipmentSlot pSlot, IAbility ability) {
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
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if(ability != null)
            ability.fillTooltips(stack, tooltip);
    }
}
