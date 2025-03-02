package net.jitl.core.init.network;

import net.jitl.client.ability.ClientPlayerMovement;
import net.jitl.core.init.JITL;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.OptionalDouble;

public record PacketUpdateClientPlayerMovement(Operation operation, OptionalDouble x, OptionalDouble y, OptionalDouble z) implements CustomPacketPayload {

    public static final Type<PacketUpdateClientPlayerMovement> TYPE = new Type<>(JITL.rl("update_client_movement"));

    public static final StreamCodec<FriendlyByteBuf, OptionalDouble> DOUBLE_STREAM_CODEC = StreamCodec.of((buf, value) -> {
        buf.writeBoolean(value.isPresent());

        if (value.isPresent())
            buf.writeDouble(value.getAsDouble());
    }, buf -> buf.readBoolean() ? OptionalDouble.of(buf.readDouble()) : OptionalDouble.empty());

    public static final StreamCodec<FriendlyByteBuf, PacketUpdateClientPlayerMovement> CODEC = StreamCodec.composite(
            NeoForgeStreamCodecs.enumCodec(Operation.class), PacketUpdateClientPlayerMovement::operation,
<<<<<<< HEAD
            DOUBLE_STREAM_CODEC, PacketUpdateClientPlayerMovement::x,
            DOUBLE_STREAM_CODEC, PacketUpdateClientPlayerMovement::y,
            DOUBLE_STREAM_CODEC, PacketUpdateClientPlayerMovement::z,
=======
            StreamCodecUtil.OPTIONAL_DOUBLE, PacketUpdateClientPlayerMovement::x,
            StreamCodecUtil.OPTIONAL_DOUBLE, PacketUpdateClientPlayerMovement::y,
            StreamCodecUtil.OPTIONAL_DOUBLE, PacketUpdateClientPlayerMovement::z,
>>>>>>> parent of 363d0355 (dialogue work)
            PacketUpdateClientPlayerMovement::new);

    public PacketUpdateClientPlayerMovement(Operation operation, double x, double y, double z) {
        this(operation, OptionalDouble.of(x), OptionalDouble.of(y), OptionalDouble.of(z));
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