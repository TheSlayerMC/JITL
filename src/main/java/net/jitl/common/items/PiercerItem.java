package net.jitl.common.items;

import net.jitl.common.entity.projectile.PiercerEntity;
import net.jitl.common.items.base.JItem;
import net.jitl.core.helper.JEnchantmentHelper;
import net.jitl.core.init.internal.JEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class PiercerItem extends JItem {

    public final int maxBounces;
    public final float damage;

    public PiercerItem(Properties p, int maxBounces, float damage) {
        super(p.stacksTo(8));
        this.maxBounces = maxBounces;
        this.damage = damage;
    }

    @Override
    public InteractionResult use(Level worldIn, Player playerIn, @NotNull InteractionHand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (worldIn.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isClientSide()) {
            PiercerEntity entity = new PiercerEntity(playerIn, worldIn, stack, maxBounces, damage, stack);
            ServerLevel serverLevel = (ServerLevel)worldIn;

            int sharpnessLevel = JEnchantmentHelper.getEnchantmentAmount(playerIn, serverLevel, JEnchantments.RAZOR);
            if(sharpnessLevel > 0)
                entity.setBaseDamage(entity.baseDamage + (double) sharpnessLevel * 0.5D + 0.5D);

            int lightweightLevel = JEnchantmentHelper.getEnchantmentAmount(playerIn, serverLevel, JEnchantments.LIGHTWEIGHT);
            if(lightweightLevel > 0)
                entity.setVelocityMultiplier(lightweightLevel);

            int ambitLevel = JEnchantmentHelper.getEnchantmentAmount(playerIn, serverLevel, JEnchantments.AMBIT);
            if(ambitLevel > 0)
                entity.setRangeAddend(ambitLevel * 4);

            int scorchingLevel = JEnchantmentHelper.getEnchantmentAmount(playerIn, serverLevel, JEnchantments.SCORCHING);
            if(scorchingLevel > 0)
                entity.setFlameAddend(scorchingLevel);

            int faithfulLevel = JEnchantmentHelper.getEnchantmentAmount(playerIn, serverLevel, JEnchantments.FAITHFUL);
            if(faithfulLevel > 0)
                entity.setFaithfulLevel(faithfulLevel);

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
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return true;
    }

}
