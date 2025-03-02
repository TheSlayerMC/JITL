package net.jitl.core.helper;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import org.apache.commons.lang3.tuple.Triple;

import java.util.OptionalDouble;
import java.util.function.BiFunction;

public final class StreamCodecUtil {
    public static final StreamCodec<FriendlyByteBuf, OptionalDouble> OPTIONAL_DOUBLE = StreamCodec.of((buf, value) -> {
        buf.writeBoolean(value.isPresent());

        if (value.isPresent())
            buf.writeDouble(value.getAsDouble());
    }, buf -> buf.readBoolean() ? OptionalDouble.of(buf.readDouble()) : OptionalDouble.empty());

    public static <T, B extends FriendlyByteBuf> StreamCodec<B, NonNullList<T>> nonNullList(StreamCodec<B, T> elementCodec, T defaultElement) {
        return StreamCodec.of((buf, value) -> {
            buf.writeVarInt(value.size());

            for (T element : value) {
                elementCodec.encode(buf, element);
            }
        }, buf -> {
            NonNullList<T> list = NonNullList.withSize(buf.readVarInt(), defaultElement);

            list.replaceAll(element -> elementCodec.decode(buf));

            return list;
        });
    }

    public static <L, R, B extends ByteBuf> StreamCodec<B, Pair<L, R>> pair(StreamCodec<? super B, L> leftCodec, StreamCodec<? super B, R> rightCodec) {
        return pair(Pair::of, leftCodec, rightCodec);
    }

    public static <L, R, P extends Pair<L, R>, B extends ByteBuf> StreamCodec<B, P> pair(BiFunction<L, R, P> factory, StreamCodec<? super B, L> leftCodec, StreamCodec<? super B, R> rightCodec) {
        return StreamCodec.of((buf, value) -> {
            leftCodec.encode(buf, value.left());
            rightCodec.encode(buf, value.right());
        }, buf -> factory.apply(leftCodec.decode(buf), rightCodec.decode(buf)));
    }

    public static <A, B, C, T extends Triple<A, B, C>, BUF extends ByteBuf> StreamCodec<BUF, T> triple(TriFunction<A, B, C, T> factory, StreamCodec<? super BUF, A> leftCodec, StreamCodec<? super BUF, B> middleCodec, StreamCodec<? super BUF, C> rightCodec) {
        return StreamCodec.of((buf, value) -> {
            leftCodec.encode(buf, value.getLeft());
            middleCodec.encode(buf, value.getMiddle());
            rightCodec.encode(buf, value.getRight());
        }, buf -> factory.apply(leftCodec.decode(buf), middleCodec.decode(buf), rightCodec.decode(buf)));
    }
}
