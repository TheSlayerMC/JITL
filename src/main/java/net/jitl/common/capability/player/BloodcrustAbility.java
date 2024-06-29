package net.jitl.common.capability.player;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.Objects;

public record BloodcrustAbility(double fire_boost) {

    public static final Codec<BloodcrustAbility> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.DOUBLE.fieldOf("fire_boost").forGetter(BloodcrustAbility::fire_boost)
            ).apply(instance, BloodcrustAbility::new)
    );

    public static final StreamCodec<ByteBuf, BloodcrustAbility> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.DOUBLE, BloodcrustAbility::fire_boost,
            BloodcrustAbility::new
    );

    @Override
    public int hashCode() {
        return Objects.hash(this.fire_boost);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        } else {
            return obj instanceof BloodcrustAbility s &&
                    this.fire_boost == s.fire_boost;
        }
    }
}