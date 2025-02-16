package net.jitl.common.items;

import net.jitl.common.entity.projectile.MagicBombEntity;
import net.jitl.common.entity.projectile.MagicPotEntity;
import net.jitl.common.items.base.JItem;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class MagicBombItem extends JItem {

    protected BiFunction<Level, LivingEntity, Projectile> projectileFactory;
    @Nullable
    private Supplier<SoundEvent> sound;

    public MagicBombItem(Properties properties) {
        super(properties);
        this.projectileFactory = (world, owner) -> new MagicBombEntity(world, owner, 1F, new ItemStack(this));
    }

    public MagicBombItem setSound(Supplier<SoundEvent> sound) {
        this.sound = sound;
        return this;
    }

    public @NotNull InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, @NotNull InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (worldIn.getRandom().nextFloat() * 0.4F + 0.8F));
        if(sound != null) {
            worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), sound.get(), SoundSource.PLAYERS, 0.5F, 0.4F / (worldIn.getRandom().nextFloat() * 0.4F + 0.8F));
        }
        if(!worldIn.isClientSide) {
            Projectile projectile = projectileFactory.apply(worldIn, playerIn);
            projectile.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 1.5F, 1.0F);
            worldIn.addFreshEntity(projectile);

            if(!playerIn.isCreative()) {
                itemstack.shrink(1);
            }

            playerIn.awardStat(Stats.ITEM_USED.get(this));
        }

        return InteractionResultHolder.sidedSuccess(itemstack, worldIn.isClientSide());
    }
}
