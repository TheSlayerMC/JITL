package net.jitl.client.knowledge;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketKnowledge {

    private float knowledgeXP;
    private int level;
    private EnumKnowledge knowledge;

    public PacketKnowledge(FriendlyByteBuf buf) {
        knowledgeXP = buf.readFloat();
        level = buf.readInt();
        knowledge = buf.readEnum(EnumKnowledge.class);
    }

    public PacketKnowledge(EnumKnowledge knowledge, KnowledgeStorage storage) {
        if(storage == null || knowledge == null)
            return;
        this.knowledgeXP = storage.getAmountOnCurrentLevel();
        this.level = storage.getLevelCount();
        this.knowledge = knowledge;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeFloat(knowledgeXP);
        buf.writeInt(level);
        buf.writeEnum(knowledge);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if(Minecraft.getInstance().player != null) {
            ClientKnowledge.setClientKnowledgeXP(knowledge, this.knowledgeXP);
            ClientKnowledge.setClientKnowledgeLevel(knowledge, this.level);
            ctx.get().setPacketHandled(true);
        }
    }
}