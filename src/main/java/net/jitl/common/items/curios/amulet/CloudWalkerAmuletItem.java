package net.jitl.common.items.curios.amulet;

import net.jitl.common.capability.essence.PlayerEssence;
import net.jitl.common.capability.keypressed.PressedKeyCap;
import net.jitl.common.items.curios.JCurioItem;
import net.jitl.core.data.JNetworkRegistry;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.network.PacketUpdateClientPlayerMovement;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class CloudWalkerAmuletItem extends JCurioItem {

    public CloudWalkerAmuletItem(Item.Properties properties) {
        super(properties);
        properties.durability(256);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if(slotContext.entity() instanceof Player player && !player.level().isClientSide()) {
            PressedKeyCap key = player.getData(JDataAttachments.KEY_PRESSED);
            PlayerEssence essence = player.getData(JDataAttachments.ESSENCE);

            if(key.isAmuletPressed()) {
                if(essence.consumeEssence(player, 0.15F)) {
                    player.fallDistance = 0.0F;
                    JNetworkRegistry.sendToPlayer((ServerPlayer) player, new PacketUpdateClientPlayerMovement(PacketUpdateClientPlayerMovement.Operation.ADD, 0.1));

                    if(!player.level().isClientSide()) {
                        double halfSize = player.getBbWidth() / 2;
                        ((ServerLevel) player.level()).sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 5, halfSize, 0, halfSize, 0);
                    }
                }
            }
        }
    }
}
