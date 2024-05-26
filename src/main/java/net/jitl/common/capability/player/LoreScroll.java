package net.jitl.common.capability.player;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.Objects;

public record LoreScroll(String entry, String knowledge, int level, boolean openedBefore) {

    public static final Codec<LoreScroll> LORE_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("entry").forGetter(LoreScroll::entry),
                    Codec.STRING.fieldOf("knowledge").forGetter(LoreScroll::knowledge),
                    Codec.INT.fieldOf("xp").forGetter(LoreScroll::level),
                    Codec.BOOL.fieldOf("openedBefore").forGetter(LoreScroll::openedBefore)
            ).apply(instance, LoreScroll::new)
    );

    public static final StreamCodec<ByteBuf, LoreScroll> LORE_STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, LoreScroll::entry,
            ByteBufCodecs.STRING_UTF8, LoreScroll::knowledge,
            ByteBufCodecs.INT, LoreScroll::level,
            ByteBufCodecs.BOOL, LoreScroll::openedBefore,
            LoreScroll::new
    );

    public LoreScroll(String entry, String knowledge, int level, boolean openedBefore) {
        this.entry = entry;
        this.knowledge = knowledge;
        this.level = level;
        this.openedBefore = openedBefore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.entry, this.entry, this.level, this.openedBefore);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        } else {
            return obj instanceof LoreScroll s &&
                    this.entry == s.entry &&
                    this.knowledge == s.knowledge &&
                    this.level == s.level &&
                    this.openedBefore == s.openedBefore;
        }
    }
}