package net.jitl.client.essence;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketEssenceBar {

    private float essence;

    public PacketEssenceBar(ByteBuf buf) {
        essence = buf.readFloat();
    }

    public PacketEssenceBar(PlayerEssence essence) {
        if(essence == null)
            return;
        this.essence = essence.getCurrentEssence();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeFloat(essence);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ctx.get().enqueueWork(() -> ClientEssence.setClientEssence(this.essence));
        });
        ctx.get().setPacketHandled(true);
    }
}
