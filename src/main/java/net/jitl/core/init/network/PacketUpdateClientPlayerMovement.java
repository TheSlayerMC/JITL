package net.jitl.core.init.network;

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
            DOUBLE_STREAM_CODEC, PacketUpdateClientPlayerMovement::x,
            DOUBLE_STREAM_CODEC, PacketUpdateClientPlayerMovement::y,
            DOUBLE_STREAM_CODEC, PacketUpdateClientPlayerMovement::z,
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
            adjustPlayerMovement(payload.x, payload.y, payload.z, payload.operation);
        });
    }

    public static void adjustPlayerMovement(OptionalDouble x, OptionalDouble y, OptionalDouble z, PacketUpdateClientPlayerMovement.Operation operation) {
        Player player = Minecraft.getInstance().player;
        assert player != null;
        Vec3 velocity = player.getDeltaMovement();
        switch (operation) {
            case SET -> player.setDeltaMovement(x.orElseGet(velocity::x), y.orElseGet(velocity::y), z.orElseGet(velocity::z));
            case ADD -> player.setDeltaMovement(velocity.add(x.orElse(0), y.orElse(0), z.orElse(0)));
            case MULTIPLY -> player.setDeltaMovement(velocity.multiply(x.orElse(1), y.orElse(1), z.orElse(1)));
            case MAX -> player.setDeltaMovement(Math.min(x.orElseGet(velocity::x), velocity.x), Math.min(y.orElseGet(velocity::y), velocity.y), Math.min(z.orElseGet(velocity::z), velocity.z));
            case MIN -> player.setDeltaMovement(Math.max(x.orElseGet(velocity::x), velocity.x), Math.max(y.orElseGet(velocity::y), velocity.y), Math.max(z.orElseGet(velocity::z), velocity.z));
        }
    }

    public enum Operation {
        SET,
        ADD,
        MULTIPLY,
        MAX,
        MIN;
    }
}