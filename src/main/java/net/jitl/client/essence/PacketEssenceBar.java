package net.jitl.client.essence;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketEssenceBar {

    private float essence;
    private float burnout;

    public PacketEssenceBar(ByteBuf buf) {
        essence = buf.readFloat();
        burnout = buf.readFloat();
    }

    public PacketEssenceBar(PlayerEssence essence) {
        if(essence == null)
            return;
        this.essence = essence.getCurrentEssence();
        this.burnout = essence.getBurnout();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeFloat(essence);
        buf.writeFloat(burnout);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ctx.get().enqueueWork(() -> {
                ClientEssence.setClientEssence(this.essence);
                ClientEssence.setClientBurnout(this.burnout);
            });
        });
        ctx.get().setPacketHandled(true);
    }
}
