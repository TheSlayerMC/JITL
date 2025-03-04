package net.jitl.common.items;

import net.jitl.common.entity.projectile.JThrowableProjectile;
import net.jitl.common.items.base.JItem;
import net.jitl.core.helper.IEssenceItem;
import net.jitl.core.helper.TriFunction;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public class GunItem extends JItem implements IEssenceItem {

    protected TriFunction<Integer, Level, LivingEntity, JThrowableProjectile> projectileFactory;
    private final int essenceUsage, damage;

    public GunItem(int essence, int damage, int maxUses, TriFunction<Integer, Level, LivingEntity, JThrowableProjectile> projectileFactory) {
        super(JItems.itemProps().stacksTo(1).durability(maxUses));
        this.projectileFactory = projectileFactory;
        this.essenceUsage = essence;
        this.damage = damage;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if(!level.isClientSide()) {
            if(player.getData(JDataAttachments.ESSENCE).consumeEssence(player, this.essenceUsage)) {
                JThrowableProjectile projectile = projectileFactory.apply(this.damage, level, player);
                projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                level.addFreshEntity(projectile);
                player.getItemInHand(usedHand).hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                level.playSound(null, player.getX(), player.getY(), player.getZ(), JSounds.STAFF_0.get(), SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            }
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}