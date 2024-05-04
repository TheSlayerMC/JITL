package net.jitl.common.items;

import com.mojang.datafixers.util.Function3;
import net.jitl.common.entity.projectile.PiercerEntity;
import net.jitl.common.items.base.JItem;
import net.jitl.core.init.internal.JEnchantments;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class PiercerItem extends JItem {
    protected Function3<Level, LivingEntity, ItemStack, PiercerEntity> projectileFactory;

    public PiercerItem(Properties properties, Function3<Level, LivingEntity, ItemStack, PiercerEntity> projectileFactory) {
        super(properties);
        this.projectileFactory = projectileFactory;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (worldIn.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isClientSide()) {
            PiercerEntity entity = projectileFactory.apply(worldIn, playerIn, stack);

            int sharpnessLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, playerIn);
            if (sharpnessLevel > 0) {
                entity.setBaseDamage(entity.getBaseDamage() + (double) sharpnessLevel * 0.5D + 0.5D);
            }

            int lightweightLevel = EnchantmentHelper.getEnchantmentLevel(JEnchantments.LIGHTWEIGHT.get(), playerIn);
            if (lightweightLevel > 0) {
                entity.setVelocityMultiplier(lightweightLevel);
            }

            double ambitLevel = EnchantmentHelper.getEnchantmentLevel(JEnchantments.AMBIT.get(), playerIn);
            if (ambitLevel > 0) {
                entity.setRangeAddend(ambitLevel * 4);
            }

            int scorchingLevel = EnchantmentHelper.getEnchantmentLevel(JEnchantments.SCORCHING.get(), playerIn);
            if (scorchingLevel > 0) {
                entity.setFlameAddend(scorchingLevel);
            }

            int faithfulLevel = EnchantmentHelper.getEnchantmentLevel(JEnchantments.FAITHFUL.get(), playerIn);
            if (faithfulLevel > 0) {
                entity.setFaithfulLevel(faithfulLevel);
            }

            entity.setPos(playerIn.getX(), playerIn.getEyeY(), playerIn.getZ());
            entity.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 1.5F, 1.0F);
            if(playerIn.isCreative()) {
                entity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
            } else {
                playerIn.getInventory().removeItem(stack);
            }
            worldIn.addFreshEntity(entity);
            playerIn.awardStat(Stats.ITEM_USED.get(this));
        }
        return InteractionResultHolder.sidedSuccess(stack, worldIn.isClientSide());
    }

    @Override
    public int getEnchantmentValue() {
        return 1;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment == Enchantments.MENDING ||
                enchantment == Enchantments.UNBREAKING ||
                enchantment == Enchantments.SHARPNESS ||
                enchantment == JEnchantments.LIGHTWEIGHT.get() ||
                enchantment == JEnchantments.AMBIT.get() ||
                enchantment == JEnchantments.FAITHFUL.get() ||
                enchantment == JEnchantments.SCORCHING.get();
    }
}
