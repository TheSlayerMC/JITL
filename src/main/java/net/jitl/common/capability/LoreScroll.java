package net.jitl.common.capability;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.Objects;

public record LoreScroll(String entry, String knowledge, float xp, boolean openedBefore) {

    public static final Codec<LoreScroll> LORE_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("entry").forGetter(LoreScroll::entry),
                    Codec.STRING.fieldOf("knowledge").forGetter(LoreScroll::knowledge),
                    Codec.FLOAT.fieldOf("xp").forGetter(LoreScroll::xp),
                    Codec.BOOL.fieldOf("openedBefore").forGetter(LoreScroll::openedBefore)
            ).apply(instance, LoreScroll::new)
    );

    public static final StreamCodec<ByteBuf, LoreScroll> LORE_STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, LoreScroll::entry,
            ByteBufCodecs.STRING_UTF8, LoreScroll::knowledge,
            ByteBufCodecs.FLOAT, LoreScroll::xp,
            ByteBufCodecs.BOOL, LoreScroll::openedBefore,
            LoreScroll::new
    );

    public LoreScroll(String entry, String knowledge, float xp, boolean openedBefore) {
        this.entry = entry;
        this.knowledge = knowledge;
        this.xp = xp;
        this.openedBefore = openedBefore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.entry, this.entry, this.xp, this.openedBefore);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        } else {
            return obj instanceof LoreScroll s &&
                    this.entry == s.entry &&
                    this.knowledge == s.knowledge &&
                    this.xp == s.xp &&
                    this.openedBefore == s.openedBefore;
        }
    }
}