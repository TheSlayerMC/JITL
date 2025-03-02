package net.jitl.core.helper;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import java.util.OptionalDouble;

public final class StreamCodecUtil {

    public static final StreamCodec<FriendlyByteBuf, OptionalDouble> DOUBLE = StreamCodec.of((buf, value) -> {
        buf.writeBoolean(value.isPresent());

        if (value.isPresent())
            buf.writeDouble(value.getAsDouble());
    }, buf -> buf.readBoolean() ? OptionalDouble.of(buf.readDouble()) : OptionalDouble.empty());
}
