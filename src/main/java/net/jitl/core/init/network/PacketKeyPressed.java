package net.jitl.core.init.network;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PacketKeyPressed(boolean isAmuletPressed, boolean isGearPressed) implements CustomPacketPayload {

    public static final Type<PacketKeyPressed> TYPE = new Type<>(JITL.rl("key_pressed"));

    public static PacketKeyPressed decode(FriendlyByteBuf buffer) {
        return new PacketKeyPressed(buffer.readBoolean(), buffer.readBoolean());
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, PacketKeyPressed> STREAM_CODEC = CustomPacketPayload.codec(PacketKeyPressed::write, PacketKeyPressed::decode);

    public void write(FriendlyByteBuf buffer) {
        buffer.writeBoolean(isAmuletPressed);
        buffer.writeBoolean(isGearPressed);
    }

    public static void handle(PacketKeyPressed payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            context.player().getData(JDataAttachments.KEY_PRESSED).setAmuletPressed(payload.isAmuletPressed);
            context.player().getData(JDataAttachments.KEY_PRESSED).setArmorPressed(payload.isGearPressed);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}