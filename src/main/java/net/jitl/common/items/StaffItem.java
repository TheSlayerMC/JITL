package net.jitl.common.items;

import net.jitl.common.capability.essence.PlayerEssenceProvider;
import net.jitl.core.helper.IEssenceItem;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public class StaffItem extends Item implements IEssenceItem {

    protected BiFunction<Level, LivingEntity, ThrowableProjectile> projectileFactory;
    private final int essenceUsage;

    public StaffItem(int essence, BiFunction<Level, LivingEntity, ThrowableProjectile> projectileFactory) {
        super(JItems.rangedProps().stacksTo(1));
        this.projectileFactory = projectileFactory;
        this.essenceUsage = essence;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if(!level.isClientSide()) {
            player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(essence -> {
                if(essence.consumeEssence(player, this.essenceUsage)) {
                    ThrowableProjectile projectile = projectileFactory.apply(level, player);
                    projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                    level.addFreshEntity(projectile);
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), JSounds.STAFF_0.get(), SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
                }
            });
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}