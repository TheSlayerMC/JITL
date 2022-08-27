package net.jitl.client.essence;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketEssenceBar {

    private int essence;

    public PacketEssenceBar(FriendlyByteBuf buf) {
        essence = buf.readInt();
    }

    public PacketEssenceBar(int essence) {
        this.essence = essence;
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(essence);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ClientEssence.setClientEssence(this.essence);
        });
        return true;
    }
}
