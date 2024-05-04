package net.jitl.core.init.network;

import net.jitl.client.essence.ClientEssence;
import net.jitl.core.init.JITL;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PacketEssenceBar(float essence, float burnout) implements CustomPacketPayload {

    public static final Type<PacketEssenceBar> TYPE = new Type<>(JITL.rl("essence"));

    public static PacketEssenceBar decode(FriendlyByteBuf buffer) {
        return new PacketEssenceBar(buffer.readFloat(), buffer.readFloat());
    }
    public static final StreamCodec<RegistryFriendlyByteBuf, PacketEssenceBar> STREAM_CODEC = CustomPacketPayload.codec(PacketEssenceBar::write, PacketEssenceBar::decode);

    public void write(FriendlyByteBuf buf) {
        buf.writeFloat(essence);
        buf.writeFloat(burnout);
    }

    public static void handle(PacketEssenceBar payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            ClientEssence.setClientEssence(payload.essence);
            ClientEssence.setClientBurnout(payload.burnout);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
