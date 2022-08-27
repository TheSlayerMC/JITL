package net.jitl.client.essence;

import io.netty.buffer.ByteBuf;
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
            PlayerEssence essence = ctx.get().getSender().getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).orElseThrow(null);
            essence.setEssence(ctx.get().getSender(), this.essence);
        });
    }
}
