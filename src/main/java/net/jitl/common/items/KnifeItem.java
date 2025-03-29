package net.jitl.common.items;

import com.mojang.datafixers.util.Function3;
import net.jitl.common.entity.projectile.KnifeEntity;
import net.jitl.common.entity.projectile.PiercerEntity;
import net.jitl.common.items.base.JSwordItem;
import net.jitl.core.helper.JToolTiers;
import net.jitl.core.init.internal.JItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class KnifeItem extends JSwordItem {

    public final float damage;

    public KnifeItem(Properties properties, float damage) {
        super(properties, JToolTiers.THROWING_KNIFE, JItems.BASIC);
        this.damage = damage;
    }

    @Override
    public @NotNull InteractionResult use(Level worldIn, Player playerIn, @NotNull InteractionHand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (worldIn.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isClientSide()) {
            KnifeEntity entity = new KnifeEntity(playerIn, worldIn, stack, damage, stack);

            entity.setPos(playerIn.getX(), playerIn.getEyeY(), playerIn.getZ());
            entity.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 1.5F, 1.0F);

            if (playerIn.isCreative()) {
                entity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
            } else {
                stack.shrink(1);
            }
            worldIn.addFreshEntity(entity);
            playerIn.awardStat(Stats.ITEM_USED.get(this));
        }
        return InteractionResult.SUCCESS;
    }

//    @Override
//    public int getEnchantmentValue() {
//        return 1;
//    }
//
//    @Override
//    public boolean isEnchantable(@NotNull ItemStack stack) {
//        return true;
//    }
}
