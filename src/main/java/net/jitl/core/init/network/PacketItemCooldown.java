package net.jitl.core.init.network;

import net.jitl.client.ability.ClientItemCooldown;
import net.jitl.core.init.JITL;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PacketItemCooldown(int cooldown) implements CustomPacketPayload {

    public static final Type<PacketItemCooldown> TYPE = new Type<>(JITL.rl("curio_cooldown"));

    public static PacketItemCooldown decode(FriendlyByteBuf buffer) {
        return new PacketItemCooldown(buffer.readInt());
    }
    public static final StreamCodec<RegistryFriendlyByteBuf, PacketItemCooldown> STREAM_CODEC = CustomPacketPayload.codec(PacketItemCooldown::write, PacketItemCooldown::decode);

    public void write(FriendlyByteBuf buf) {
        buf.writeInt(cooldown);
    }

    public static void handle(PacketItemCooldown payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            ClientItemCooldown.setCooldown(payload.cooldown);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
