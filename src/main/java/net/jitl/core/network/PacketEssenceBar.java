package net.jitl.core.network;

import net.jitl.client.essence.ClientEssence;
import net.jitl.common.capability.essence.PlayerEssence;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketEssenceBar {

    private float essence;
    private float burnout;

    public PacketEssenceBar(float essence, float burnout) {
        this.essence = essence;
        this.burnout = burnout;
    }

    public PacketEssenceBar(PlayerEssence essence) {
        if(essence == null)
            return;
        this.essence = essence.getCurrentEssence();
        this.burnout = essence.getBurnout();
    }

    public static PacketEssenceBar decode(FriendlyByteBuf buffer) {
        return new PacketEssenceBar(buffer.readFloat(), buffer.readFloat());
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeFloat(essence);
        buf.writeFloat(burnout);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if(Minecraft.getInstance().player != null) {
            ClientEssence.setClientEssence(this.essence);
            ClientEssence.setClientBurnout(this.burnout);
            ctx.get().setPacketHandled(true);
        }
    }
}
