package net.jitl.core.init.network;

import net.jitl.client.ability.ClientCuriosCooldown;
import net.jitl.core.init.JITL;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PacketCuriosCooldown(int cooldown) implements CustomPacketPayload {

    public static final Type<PacketCuriosCooldown> TYPE = new Type<>(JITL.rl("curio_cooldown"));

    public static PacketCuriosCooldown decode(FriendlyByteBuf buffer) {
        return new PacketCuriosCooldown(buffer.readInt());
    }
    public static final StreamCodec<RegistryFriendlyByteBuf, PacketCuriosCooldown> STREAM_CODEC = CustomPacketPayload.codec(PacketCuriosCooldown::write, PacketCuriosCooldown::decode);

    public void write(FriendlyByteBuf buf) {
        buf.writeInt(cooldown);
    }

    public static void handle(PacketCuriosCooldown payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            ClientCuriosCooldown.setCooldown(payload.cooldown);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
