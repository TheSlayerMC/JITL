package net.jitl.client.stats;

import io.netty.buffer.ByteBuf;
import net.jitl.common.capability.stats.PlayerStats;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketPlayerStats {

    private boolean hasBlizzard;

    public PacketPlayerStats(ByteBuf buf) {
        hasBlizzard = buf.readBoolean();
    }

    public PacketPlayerStats(PlayerStats stats) {
        if(stats == null)
            return;
        this.hasBlizzard = stats.hasBlizzard();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(hasBlizzard);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ctx.get().enqueueWork(() -> ClientPlayerStats.setHasBlizzard(this.hasBlizzard));
        });
        ctx.get().setPacketHandled(true);
    }
}
