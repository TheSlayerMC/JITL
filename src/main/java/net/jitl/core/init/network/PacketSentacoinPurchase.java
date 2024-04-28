package net.jitl.core.init.network;

import net.jitl.common.capability.stats.PlayerStats;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import org.jetbrains.annotations.NotNull;

public record PacketSentacoinPurchase(ItemStack item, int ret, int cost) implements CustomPacketPayload {

    public static final ResourceLocation ID = JITL.rl("sentacoin_purchase");

    public static PacketSentacoinPurchase decode(final FriendlyByteBuf buf) {
        return new PacketSentacoinPurchase(buf.readItem(), buf.readInt(), buf.readInt());
    }

    @Override
    public void write(@NotNull FriendlyByteBuf buf) {
        buf.writeItem(item);
        buf.writeInt(ret);
        buf.writeInt(cost);
    }

    public void handle(PlayPayloadContext ctx) {
        ctx.workHandler().submitAsync(() -> {
            Player player = ctx.player().get();
            PlayerStats stats = player.getData(JDataAttachments.PLAYER_STATS);
            if(stats.useSentacoins(cost)) {
                player.getInventory().add(item);
            }
        });
    }

    @Override
    public ResourceLocation id() {
        return null;
    }
}