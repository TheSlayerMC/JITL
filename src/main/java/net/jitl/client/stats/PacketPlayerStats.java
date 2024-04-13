package net.jitl.client.stats;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.capability.stats.PlayerStats;
import net.jitl.core.init.JITL;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import org.jetbrains.annotations.NotNull;

public record PacketPlayerStats(boolean hasBlizzard, int sentacoins, float knowledgeXP, int level, EnumKnowledge knowledge) implements CustomPacketPayload {

    public static final ResourceLocation ID = JITL.rl("player_stats");

    public static PacketPlayerStats decode(FriendlyByteBuf buffer) {
        return new PacketPlayerStats(buffer.readBoolean(), buffer.readInt(), buffer.readFloat(), buffer.readInt(), buffer.readEnum(EnumKnowledge.class));
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeBoolean(hasBlizzard);
        buf.writeInt(sentacoins);

        buf.writeFloat(knowledgeXP);
        buf.writeInt(level);
        buf.writeEnum(knowledge);
    }

    public void handle(PlayPayloadContext ctx) {
        ctx.workHandler().submitAsync(() -> {
            ClientPlayerStats.setHasBlizzard(this.hasBlizzard);
            ClientPlayerStats.setSentacoins(this.sentacoins);

            ClientPlayerStats.setClientKnowledgeLevel(knowledge, this.level);
            ClientPlayerStats.setClientKnowledgeXP(knowledge, this.knowledgeXP);
        });
    }

    @Override
    public @NotNull ResourceLocation id() {
        return ID;
    }
}
