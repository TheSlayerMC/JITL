package net.jitl.common.items.curios.amulet;

import net.jitl.common.capability.essence.PlayerEssenceProvider;
import net.jitl.common.capability.keypressed.PressedKeyCap;
import net.jitl.common.items.curios.JCurioItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import top.theillusivec4.curios.api.SlotContext;

public class DynasterAmuletItem extends JCurioItem {
    public DynasterAmuletItem(Item.Properties properties) {
        super(properties);
        properties.durability(256);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if(slotContext.entity() instanceof Player player) {
            if (!player.onGround() && !player.isInLava() && !player.isInWaterOrBubble() && PressedKeyCap.isAmuletPressedEitherSide(player)) {
                player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(essence -> {
                    if (essence.checkEssenceEitherSide(player.level().isClientSide(), player, 0.5F)) {
                        boolean bool = isFloatReady(player);
                        if (bool) {
                            player.fallDistance = 0;
                            player.setDeltaMovement(player.getDeltaMovement().multiply(1, 0.75F, 1));
                            if (!player.level().isClientSide()) {
                                ((ServerLevel) player.level()).sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 1, 0, 0, 0, 0.2);
                            }
                        }
                        if (!player.level().isClientSide()) {
                            ((ServerLevel) player.level()).sendParticles(bool ? ParticleTypes.CLOUD : ParticleTypes.SMOKE, player.getX(), player.getY(), player.getZ(), 1, 0, 0, 0, 0.2);
                        }
                    }
                });
            }
        }
    }

    private boolean isFloatReady(Player player) {
        return player.level().clip(new ClipContext(player.position(),
                player.position().add(0, -5, 0),
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.ANY,
                player)).getType() == HitResult.Type.BLOCK;
    }
}
