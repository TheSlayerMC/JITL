package net.jitl.core.data;

import net.jitl.client.stats.PacketPlayerStats;
import net.jitl.core.init.JITL;
import net.jitl.core.init.network.CKeyPressedPacket;
import net.jitl.core.network.PacketEssenceBar;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

public class JNetworkRegistry {

    public static void init(IEventBus eventBus) {
        eventBus.addListener(JNetworkRegistry::registerPackets);
    }

    private static void registerPackets(final RegisterPayloadHandlerEvent ev) {
        final IPayloadRegistrar registry = ev.registrar(JITL.MODID);

        registry.play(PacketPlayerStats.ID, PacketPlayerStats::decode, PacketPlayerStats::handle);
        registry.play(PacketEssenceBar.ID, PacketEssenceBar::decode, PacketEssenceBar::handle);
        registry.play(CKeyPressedPacket.ID, CKeyPressedPacket::decode, CKeyPressedPacket::handle);
    }

    public static void sendToPlayer(ServerPlayer player, CustomPacketPayload packet) {
        if(player.connection != null)
            PacketDistributor.PLAYER.with(player).send(packet);
    }
}
