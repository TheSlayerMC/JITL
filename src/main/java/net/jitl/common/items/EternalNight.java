package net.jitl.common.items;

import net.jitl.client.util.ChatUtils;
import net.jitl.common.capability.essence.PlayerEssence;
import net.jitl.common.items.base.JItem;
import net.jitl.core.config.JCommonConfig;
import net.jitl.core.helper.IEssenceItem;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class EternalNight extends JItem implements IEssenceItem {

    public EternalNight(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResult.CONSUME;
    }

    @Override
    public boolean releaseUsing(@NotNull ItemStack stack, @NotNull Level world, @NotNull LivingEntity entity, int timeLeft) {
        if(entity instanceof Player player) {
            PlayerEssence essence = player.getData(JDataAttachments.ESSENCE);
            if(!world.isClientSide()) {
                if(JCommonConfig.ENABLE_ETERNAL_LIGHT.get()) {
                    ServerLevel serverLevel = (ServerLevel) world;
                    player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 2));
                    if (essence.consumeEssence(player, 10)) {
                        serverLevel.setDayTime(18000);
                        stack.hurtAndBreak(1, serverLevel, player, item -> {
                        });
                        world.playSound(player, player.getX(), player.getY(), player.getZ(), JSounds.STAFF_0.get(), SoundSource.BLOCKS);
                        ChatUtils.sendColouredTranslatedMessage(player, ChatFormatting.DARK_PURPLE, "jitl.message.item.eternal_night", player.getDisplayName());
                    }
                }
            }
        }
        return true;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.BOW;
    }
}
