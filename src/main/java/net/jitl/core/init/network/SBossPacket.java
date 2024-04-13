package net.jitl.core.init.network;

import net.jitl.common.entity.IJourneyBoss;
import net.jitl.common.entity.base.JBossInfo;
import net.jitl.core.init.JITL;
import net.jitl.core.network.PacketEssenceBar;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record SBossPacket(Operation addOrRemove, UUID barUUID, int bossNum) implements CustomPacketPayload {

    public static final ResourceLocation ID = JITL.rl("boss_bar");

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeEnum(addOrRemove);
        buffer.writeUUID(barUUID);
        buffer.writeInt(bossNum);
    }


    public static SBossPacket decode(FriendlyByteBuf buffer) {
        return new SBossPacket(buffer.readEnum(Operation.class), buffer.readUUID(), buffer.readInt());
    }

    public void handle(PlayPayloadContext ctx) {
        ctx.workHandler().submitAsync(() -> {
            switch(this.addOrRemove) {
                case ADD -> {
                    assert Minecraft.getInstance().level != null;
                    Entity boss = Minecraft.getInstance().level.getEntity(this.bossNum);
                    if (boss instanceof IJourneyBoss) {
                        JBossInfo.map.put(this.barUUID, boss.getId());
                    } else {
                        assert boss != null;
                        throw new IllegalStateException("Attempted to add boss info to " + boss.getClass().getName());
                    }
                }
                case REMOVE -> JBossInfo.map.remove(barUUID);
                default -> throw new IllegalStateException();
            }
        });
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public enum Operation {
        ADD,
        REMOVE
    }
}
