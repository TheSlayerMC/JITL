package net.jitl.common.items.curios.amulet;

import net.jitl.common.capability.essence.PlayerEssenceProvider;
import net.jitl.common.capability.keypressed.PressedKeyCap;
import net.jitl.common.items.curios.JCurioItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class CloudwalkingAmuletItem extends JCurioItem {
    public CloudwalkingAmuletItem(Item.Properties properties) {
        super(properties);
        properties.durability(256);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (!player.onGround() && !player.isInLava() && !player.isInWaterOrBubble() && PressedKeyCap.isAmuletPressedEitherSide(player)) {
                player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(essence -> {
                    if (essence.checkEssenceEitherSide(player.level().isClientSide(), player, 0.15F)) {
                        player.fallDistance = 0.0F;
                        player.setDeltaMovement(slotContext.entity().getDeltaMovement().add(0, 0.1F, 0));
                        if (!player.level().isClientSide()) {
                            double halfSize = player.getBbWidth() / 2;
                            ((ServerLevel) player.level()).sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 5, halfSize, 0, halfSize, 0);
                        }
                    }
                });
            }
        }
    }
}
