package net.jitl.client.stats;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.core.init.JITL;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PacketPlayerStats(boolean hasBlizzard, int sentacoins, float knowledgeXP, int level, EnumKnowledge knowledge) implements CustomPacketPayload {

    public static final Type<PacketPlayerStats> TYPE = new Type<>(JITL.rl("player_stats"));

    public static PacketPlayerStats decode(FriendlyByteBuf buffer) {
        return new PacketPlayerStats(buffer.readBoolean(), buffer.readInt(), buffer.readFloat(), buffer.readInt(), buffer.readEnum(EnumKnowledge.class));
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, PacketPlayerStats> STREAM_CODEC = CustomPacketPayload.codec(PacketPlayerStats::write, PacketPlayerStats::decode);

    private void write(FriendlyByteBuf buf) {
        buf.writeBoolean(hasBlizzard);
        buf.writeInt(sentacoins);
        buf.writeFloat(knowledgeXP);
        buf.writeInt(level);
        buf.writeEnum(knowledge);
    }

    public static void handle(PacketPlayerStats payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientPlayerStats.setHasBlizzard(payload.hasBlizzard);
            ClientPlayerStats.setSentacoins(payload.sentacoins);
            ClientPlayerStats.setClientKnowledgeLevel(payload.knowledge, payload.level);
            ClientPlayerStats.setClientKnowledgeXP(payload.knowledge, payload.knowledgeXP);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
