package net.jitl.common.entity.base;

import net.jitl.common.entity.IJourneyBoss;
import net.jitl.core.data.JNetworkRegistry;
import net.jitl.core.init.network.SBossPacket;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.PacketDistributor;

import java.util.HashMap;
import java.util.UUID;

public class JBossInfo {
    public static HashMap<UUID, IJourneyBoss> map = new HashMap<>();

    public static void addInfo(ServerPlayer player, ServerBossEvent info, IJourneyBoss boss) {
        info.addPlayer(player);
        JNetworkRegistry.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SBossPacket(SBossPacket.Operation.ADD, info.getId(), (LivingEntity) boss));
    }

    public static void removeInfo(ServerPlayer player, ServerBossEvent info, IJourneyBoss boss) {
        info.removePlayer(player);
        JNetworkRegistry.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SBossPacket(SBossPacket.Operation.REMOVE, info.getId(), (LivingEntity) boss));
    }
}