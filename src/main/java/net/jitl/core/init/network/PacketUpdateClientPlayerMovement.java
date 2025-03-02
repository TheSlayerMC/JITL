package net.jitl.core.init.network;

import net.jitl.client.ability.ClientPlayerMovement;
import net.jitl.core.helper.StreamCodecUtil;
import net.jitl.core.init.JITL;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.OptionalDouble;

public record PacketUpdateClientPlayerMovement(Operation operation, OptionalDouble x, OptionalDouble y, OptionalDouble z) implements CustomPacketPayload {

    public static final Type<PacketUpdateClientPlayerMovement> TYPE = new Type<>(JITL.rl("update_client_movement"));
    public static final StreamCodec<FriendlyByteBuf, PacketUpdateClientPlayerMovement> CODEC = StreamCodec.composite(
            NeoForgeStreamCodecs.enumCodec(Operation.class), PacketUpdateClientPlayerMovement::operation,
            StreamCodecUtil.DOUBLE, PacketUpdateClientPlayerMovement::x,
            StreamCodecUtil.DOUBLE, PacketUpdateClientPlayerMovement::y,
            StreamCodecUtil.DOUBLE, PacketUpdateClientPlayerMovement::z,
            PacketUpdateClientPlayerMovement::new);

    public PacketUpdateClientPlayerMovement(Operation operation, double x, double y, double z) {
        this(operation, OptionalDouble.of(x), OptionalDouble.of(y), OptionalDouble.of(z));
    }

    public PacketUpdateClientPlayerMovement(Operation operation, double x, double z) {
        this(operation, OptionalDouble.of(x), OptionalDouble.empty(), OptionalDouble.of(z));
    }

    public PacketUpdateClientPlayerMovement(Operation operation, double y) {
        this(operation, OptionalDouble.empty(), OptionalDouble.of(y), OptionalDouble.empty());
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(PacketUpdateClientPlayerMovement payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientPlayerMovement.adjustPlayerMovement(payload.x, payload.y, payload.z, payload.operation);
        });
    }

    public enum Operation {
        SET,
        ADD,
        MULTIPLY,
        MAX,
        MIN;
    }
}