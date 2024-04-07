package net.jitl.client.stats;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.capability.stats.PlayerStats;
import net.minecraft.client.Minecraft;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;

public class PacketPlayerStats {

    private boolean hasBlizzard;
    private int sentacoins;

    private float knowledgeXP;
    private int level;
    private EnumKnowledge knowledge;

    public PacketPlayerStats(FriendlyByteBuf buf) {
        hasBlizzard = buf.readBoolean();
        sentacoins = buf.readInt();

        knowledgeXP = buf.readFloat();
        level = buf.readInt();
        knowledge = buf.readEnum(EnumKnowledge.class);
    }

    public PacketPlayerStats(EnumKnowledge knowledge, PlayerStats stats) {
        if(stats == null || knowledge == null)
            return;
        this.hasBlizzard = stats.hasBlizzard();
        this.sentacoins = stats.getSentacoins();

        this.knowledgeXP = stats.getXP(knowledge);
        this.level = stats.getLevel(knowledge);
        this.knowledge = knowledge;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeBoolean(hasBlizzard);
        buf.writeInt(sentacoins);

        buf.writeFloat(knowledgeXP);
        buf.writeInt(level);
        buf.writeEnum(knowledge);
    }


    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (Minecraft.getInstance().player != null) {
            ClientPlayerStats.setHasBlizzard(this.hasBlizzard);
            ClientPlayerStats.setSentacoins(this.sentacoins);
            ClientPlayerStats.setClientKnowledgeLevel(knowledge, this.level);
            ClientPlayerStats.setClientKnowledgeXP(knowledge, this.knowledgeXP);
            ctx.get().setPacketHandled(true);
        }
    }
}
