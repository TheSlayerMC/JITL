package net.jitl.client.essence;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketEssenceBar {

    private int essence, maxEssence, regenDelay;

    public PacketEssenceBar(ByteBuf buf) {
        this.essence = buf.readInt();
        this.maxEssence = buf.readInt();
        this.regenDelay = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(essence);
        buf.writeInt(maxEssence);
        buf.writeInt(regenDelay);
    }

    public PacketEssenceBar(PlayerEssence essence) {
        if(essence == null)
            return;
        this.essence = essence.getEssence();
        this.maxEssence = essence.getMaxEssence();
        this.regenDelay = essence.getRegenDelay();
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            PlayerEssence essence = ctx.get().getSender().getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).orElseThrow(null);
            essence.setEssence(ctx.get().getSender(), this.essence);
            essence.setMaxEssence(this.maxEssence);
            essence.setRegenDelay(this.regenDelay);
        });
        ctx.get().setPacketHandled(true);
    }
}
