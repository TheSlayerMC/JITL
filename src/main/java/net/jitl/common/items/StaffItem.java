package net.jitl.common.items;

import net.jitl.common.entity.projectile.JThrowableProjectile;
import net.jitl.common.items.base.JItem;
import net.jitl.core.helper.IEssenceItem;
import net.jitl.core.helper.LangHelper;
import net.jitl.core.helper.TriFunction;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiFunction;

public class StaffItem extends JItem implements IEssenceItem {

    protected TriFunction<Integer, Level, LivingEntity, JThrowableProjectile> projectileFactory;
    private final int essenceUsage, damage;

    public StaffItem(int essence, int damage, int maxUses, TriFunction<Integer, Level, LivingEntity, JThrowableProjectile> projectileFactory) {
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

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext pContext, List<Component> tooltip, TooltipFlag pTooltipFlag) {
        tooltip.add(Component.translatable("jitl.tooltip.staff", (damage / 2)));
        addItemDesc(JItems.STAFF_OF_CONJURING.asItem(), tooltip, "jitl.tooltip.staff_of_conjuring");
        addItemDesc(JItems.STAFF_OF_ESSENCIA.asItem(), tooltip, "jitl.tooltip.staff_of_essencia");
        addItemDesc(JItems.STAFF_OF_HELLSTONE.asItem(), tooltip, "jitl.tooltip.staff_of_hellstone");
        addItemDesc(JItems.DOOMSBRINGER.asItem(), tooltip, "jitl.tooltip.doomsbringer");
        addItemDesc(JItems.OVERGROWN_STAFF.asItem(), tooltip, "jitl.tooltip.overgrown_staff");
        addItemDesc(JItems.STAFF_OF_DIVINITY.asItem(), tooltip, "jitl.tooltip.staff_of_divinity");
        addItemDesc(JItems.STAFF_OF_ENLIGHTENMENT.asItem(), tooltip, "jitl.tooltip.staff_of_enlightenment");
        addItemDesc(JItems.CRYSTAL_STAFF.asItem(), tooltip, "jitl.tooltip.crystal_staff");
        addItemDesc(JItems.STAFF_OF_GREENPACE.asItem(), tooltip, "jitl.tooltip.staff_of_greenpace");
        tooltip.add(Component.translatable("jitl.tooltip.essence_usage", essenceUsage));
        tooltip.add(Component.translatable("jitl.uses_remaining", (stack.getMaxDamage() - stack.getDamageValue())));
    }
}