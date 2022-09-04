package net.jitl.client.knowledge;

import io.netty.buffer.ByteBuf;
import net.jitl.client.essence.ClientEssence;
import net.jitl.client.gui.overlay.Knowledge;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketKnowledge {

    private float knowledgeXP;
    private int level;
    private EnumKnowledge knowledge;

    public PacketKnowledge(ByteBuf buf) {
        knowledgeXP = buf.readFloat();
        level = buf.readInt();
    }

    public PacketKnowledge(EnumKnowledge knowledge, KnowledgeStorage knowledge2) {
        if(knowledge2 == null)
            return;
        this.knowledge = knowledge;
        this.knowledgeXP = knowledge2.getAmountOnCurrentLevel();
        this.level = knowledge2.getLevelCount();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(level);
        buf.writeFloat(knowledgeXP);
        
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ctx.get().enqueueWork(() -> ClientKnowledge.addClientKnowledgeXP(knowledge, this.knowledgeXP));
            ctx.get().enqueueWork(() -> ClientKnowledge.addClientKnowledgeLevel(knowledge, this.level));
        });
        ctx.get().setPacketHandled(true);
    }
}