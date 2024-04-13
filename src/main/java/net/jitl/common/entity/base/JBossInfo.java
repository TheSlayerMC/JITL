package net.jitl.common.entity.base;

import net.jitl.core.data.JNetworkRegistry;
import net.jitl.core.init.network.SBossPacket;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.UUID;

public class JBossInfo {
    public static HashMap<UUID, Integer> map = new HashMap<>();

    public static void addInfo(ServerPlayer player, ServerBossEvent info, int boss) {
        info.addPlayer(player);
        JNetworkRegistry.sendToPlayer(player, new SBossPacket(SBossPacket.Operation.ADD, info.getId(), boss));
    }

    public static void removeInfo(ServerPlayer player, ServerBossEvent info, int boss) {
        info.removePlayer(player);
        JNetworkRegistry.sendToPlayer(player, new SBossPacket(SBossPacket.Operation.REMOVE, info.getId(), boss));
    }
}