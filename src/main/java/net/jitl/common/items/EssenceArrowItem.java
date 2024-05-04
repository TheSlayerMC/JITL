package net.jitl.common.items;

import net.jitl.common.entity.projectile.EssenceArrowEntity;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class EssenceArrowItem extends ArrowItem {

    public EssenceArrowItem() {
        super(JItems.itemProps());
    }

    @Override
    public @NotNull AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        return new EssenceArrowEntity(JEntities.ESSENCE_ARROW_TYPE.get(), pLevel);
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, LivingEntity livingEntity) {
        int enchant = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY, bow);
        return enchant > 0 && this.getClass() == EssenceArrowItem.class;
    }
}
