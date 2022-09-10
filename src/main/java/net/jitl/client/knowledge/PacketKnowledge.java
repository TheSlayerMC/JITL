package net.jitl.client.knowledge;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketKnowledge {

    private float overworldXP = 0F, endXP = 0F, netherXP = 0F;
    private int overworldLevel = 0, endLevel = 0, netherLevel = 0;
    private EnumKnowledge knowledge;

    public PacketKnowledge(FriendlyByteBuf buf) {
        knowledge = buf.readEnum(EnumKnowledge.class);

        overworldXP = buf.readFloat();
        overworldLevel = buf.readInt();

        endXP = buf.readFloat();
        endLevel = buf.readInt();

        netherXP = buf.readFloat();
        netherLevel = buf.readInt();
    }

    public PacketKnowledge(EnumKnowledge knowledge, PlayerKnowledge storage) {
        if(storage == null || knowledge == null)
            return;
        this.knowledge = knowledge;

        this.overworldXP = storage.getXP(knowledge);
        this.overworldLevel = storage.getLevel(knowledge);

        this.endXP = storage.getXP(knowledge);
        this.endLevel = storage.getLevel(knowledge);

        this.netherXP = storage.getXP(knowledge);
        this.netherLevel = storage.getLevel(knowledge);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeEnum(knowledge);
        buf.writeInt(overworldLevel);
        buf.writeFloat(overworldXP);
        buf.writeInt(endLevel);
        buf.writeFloat(endXP);
        buf.writeInt(netherLevel);
        buf.writeFloat(netherXP);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            switch(knowledge) {
                case OVERWORLD -> {
                    ctx.get().enqueueWork(() -> ClientKnowledge.setClientKnowledgeXP(knowledge, this.overworldXP));
                    ctx.get().enqueueWork(() -> ClientKnowledge.setClientKnowledgeLevel(knowledge, this.overworldLevel));
                }
                case NETHER -> {
                    ctx.get().enqueueWork(() -> ClientKnowledge.setClientKnowledgeXP(knowledge, this.netherXP));
                    ctx.get().enqueueWork(() -> ClientKnowledge.setClientKnowledgeLevel(knowledge, this.netherLevel));
                }
                case END -> {
                    ctx.get().enqueueWork(() -> ClientKnowledge.setClientKnowledgeXP(knowledge, this.endXP));
                    ctx.get().enqueueWork(() -> ClientKnowledge.setClientKnowledgeLevel(knowledge, this.endLevel));
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}