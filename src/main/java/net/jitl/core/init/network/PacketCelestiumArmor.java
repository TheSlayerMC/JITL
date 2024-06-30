package net.jitl.core.init.network;

import net.jitl.client.ability.ClientCelestiumArmor;
import net.jitl.client.essence.ClientEssence;
import net.jitl.core.init.JITL;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PacketCelestiumArmor(boolean jumpReady, int cooldown) implements CustomPacketPayload {

    public static final Type<PacketCelestiumArmor> TYPE = new Type<>(JITL.rl("celestium_armor"));

    public static PacketCelestiumArmor decode(FriendlyByteBuf buffer) {
        return new PacketCelestiumArmor(buffer.readBoolean(), buffer.readInt());
    }
    public static final StreamCodec<RegistryFriendlyByteBuf, PacketCelestiumArmor> STREAM_CODEC = CustomPacketPayload.codec(PacketCelestiumArmor::write, PacketCelestiumArmor::decode);

    public void write(FriendlyByteBuf buf) {
        buf.writeBoolean(jumpReady);
        buf.writeInt(cooldown);
    }

    public static void handle(PacketCelestiumArmor payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            ClientCelestiumArmor.setJumpReady(payload.jumpReady);
            ClientCelestiumArmor.setCooldown(payload.cooldown);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
