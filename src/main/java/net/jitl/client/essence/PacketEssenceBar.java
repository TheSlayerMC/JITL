package net.jitl.client.essence;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketEssenceBar {

    private int essence;

    public PacketEssenceBar(ByteBuf buf) {
        essence = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(essence);
    }

    public PacketEssenceBar(PlayerEssence essence) {
        if(essence == null)
            return;
        this.essence = essence.getEssence();
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ctx.get().enqueueWork(() -> ClientEssence.setClientEssence(this.essence));
        });
        ctx.get().setPacketHandled(true);
    }
}
