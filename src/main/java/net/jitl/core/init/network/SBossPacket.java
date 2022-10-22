package net.jitl.core.init.network;

import net.jitl.common.entity.IJourneyBoss;

import net.jitl.common.entity.base.JBossInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class SBossPacket {

    private final Operation addOrRemove;
    private final UUID barUUID;
    private final int bossNum;

    public SBossPacket(FriendlyByteBuf buf) {
        this.addOrRemove = buf.readEnum(Operation.class);
        this.barUUID = buf.readUUID();
        this.bossNum = buf.readInt();
    }

    public SBossPacket(Operation aor, UUID uuid, Entity boss) {
        this.addOrRemove = aor;
        this.barUUID = uuid;
        this.bossNum = boss.getId();
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeEnum(addOrRemove);
        buffer.writeUUID(barUUID);
        buffer.writeInt(bossNum);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
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
