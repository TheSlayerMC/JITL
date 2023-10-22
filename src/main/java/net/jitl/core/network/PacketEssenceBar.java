package net.jitl.core.network;

import io.netty.buffer.ByteBuf;
import net.jitl.client.essence.ClientEssence;
import net.jitl.common.capability.essence.PlayerEssence;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;

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

    public void encode(ByteBuf buf) {
        buf.writeFloat(essence);
        buf.writeFloat(burnout);
    }

    public void handle(CustomPayloadEvent.Context context) {
        if(Minecraft.getInstance().player != null) {
            ClientEssence.setClientEssence(this.essence);
            ClientEssence.setClientBurnout(this.burnout);
            context.setPacketHandled(true);
        }
    }
}
