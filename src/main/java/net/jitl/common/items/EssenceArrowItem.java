package net.jitl.common.items;

import net.jitl.common.entity.projectile.EssenceArrowEntity;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EssenceArrowItem extends ArrowItem {

    public EssenceArrowItem() {
        super(JItems.itemProps());
    }

    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter, @Nullable ItemStack weapon) {
        return new EssenceArrowEntity(pLevel, pShooter, weapon);
    }

    @Override
    public Projectile asProjectile(Level pLevel, Position pPos, ItemStack pStack, Direction pDirection) {
        EssenceArrowEntity arrow = new EssenceArrowEntity(pLevel, pPos.x(), pPos.y(), pPos.z(), null);
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return arrow;
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, LivingEntity livingEntity) {
        return false;
    }
}
