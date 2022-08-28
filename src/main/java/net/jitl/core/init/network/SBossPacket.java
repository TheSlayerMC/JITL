package net.jitl.core.init.network;

import net.jitl.common.entity.IJourneyBoss;

import net.jitl.common.entity.base.JBossInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.io.IOException;
import java.util.UUID;

public class SBossPacket {

    private final Operation addOrRemove;
    private final UUID barUUID;
    private final int bossNum;

    private SBossPacket(Operation operation, UUID barUUID, int bossNum) {
        this.addOrRemove = operation;
        this.barUUID = barUUID;
        this.bossNum = bossNum;
    }

    public SBossPacket(Operation operation, UUID barUUID, Entity boss) {
        this(operation, barUUID, boss.getId());
    }

    public void toBytes(FriendlyByteBuf buffer) throws IOException {
        buffer.writeEnum(addOrRemove);
        buffer.writeUUID(barUUID);
        buffer.writeInt(bossNum);
    }

    public boolean handle(NetworkEvent.Context ctx) {
        ctx.enqueueWork(() -> {
            switch(this.addOrRemove) {
                case ADD -> {
                    Entity boss = Minecraft.getInstance().level.getEntity(this.bossNum);
                    if(boss instanceof IJourneyBoss) {
                        JBossInfo.map.put(this.barUUID, (IJourneyBoss)boss);
                    } else {
                        throw new IllegalStateException("Attempted to add boss info to " + boss.getClass().getName());
                    }
                }
                case REMOVE -> JBossInfo.map.remove(barUUID);
                default -> throw new IllegalStateException();
            }
        });
            return true;
    }

    public enum Operation {
        ADD,
        REMOVE
    }
}
