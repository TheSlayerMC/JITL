package net.jitl.core.data;

import net.jitl.client.stats.PacketPlayerStats;
import net.jitl.core.init.JITL;
import net.jitl.core.init.network.CKeyPressedPacket;
import net.jitl.core.init.network.PacketEssenceBar;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class JNetworkRegistry {

    public static void init(IEventBus eventBus) {
        eventBus.addListener(JNetworkRegistry::registerPackets);
    }

    private static void registerPackets(final RegisterPayloadHandlersEvent ev) {
        PayloadRegistrar registry = ev.registrar(JITL.MODID);
        registry.playBidirectional(PacketPlayerStats.TYPE, PacketPlayerStats.STREAM_CODEC, PacketPlayerStats::handle);
        registry.playBidirectional(PacketEssenceBar.TYPE, PacketEssenceBar.STREAM_CODEC, PacketEssenceBar::handle);
        registry.playBidirectional(CKeyPressedPacket.TYPE, CKeyPressedPacket.STREAM_CODEC, CKeyPressedPacket::handle);
    }

    public static void sendToPlayer(ServerPlayer player, CustomPacketPayload packet) {
        PacketDistributor.sendToPlayer(player, packet);
    }
}
