package net.jitl.client.stats;

import io.netty.buffer.ByteBuf;
import net.jitl.common.capability.stats.PlayerStats;
import net.minecraft.client.Minecraft;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketPlayerStats {

    private boolean hasBlizzard;
    private int sentacoins;

    public PacketPlayerStats(ByteBuf buf) {
        hasBlizzard = buf.readBoolean();
        sentacoins = buf.readInt();
    }

    public PacketPlayerStats(PlayerStats stats) {
        if(stats == null)
            return;
        this.hasBlizzard = stats.hasBlizzard();
        this.sentacoins = stats.getSentacoins();
    }

    public void encode(ByteBuf buf) {
        buf.writeBoolean(hasBlizzard);
        buf.writeInt(sentacoins);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if(Minecraft.getInstance().player != null) {
            ClientPlayerStats.setHasBlizzard(this.hasBlizzard);
            ClientPlayerStats.setSentacoins(this.sentacoins);
            ctx.get().setPacketHandled(true);
        }
    }
}
