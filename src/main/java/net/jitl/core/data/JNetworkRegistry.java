package net.jitl.core.data;

import net.jitl.client.stats.PacketPlayerStats;
import net.jitl.core.init.JITL;
import net.jitl.core.init.network.*;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class JNetworkRegistry {

    public static void init(IEventBus eventBus) {
        eventBus.addListener(JNetworkRegistry::registerPackets);
    }

    private static void registerPackets(final RegisterPayloadHandlersEvent ev) {
        PayloadRegistrar registry = ev.registrar(JITL.MOD_ID);

        registry.playBidirectional(PacketPlayerStats.TYPE, PacketPlayerStats.STREAM_CODEC, PacketPlayerStats::handle, PacketPlayerStats::handle);
        registry.playBidirectional(PacketEssenceBar.TYPE, PacketEssenceBar.STREAM_CODEC, PacketEssenceBar::handle, PacketEssenceBar::handle);
        registry.playBidirectional(PacketCelestiumArmor.TYPE, PacketCelestiumArmor.STREAM_CODEC, PacketCelestiumArmor::handle, PacketCelestiumArmor::handle);
        registry.playBidirectional(PacketItemCooldown.TYPE, PacketItemCooldown.STREAM_CODEC, PacketItemCooldown::handle, PacketItemCooldown::handle);
        registry.playBidirectional(PacketKeyPressed.TYPE, PacketKeyPressed.STREAM_CODEC, PacketKeyPressed::handle, PacketKeyPressed::handle);
        registry.playBidirectional(PacketBuyItem.TYPE, PacketBuyItem.STREAM_CODEC, PacketBuyItem::handle, PacketBuyItem::handle);

        registry.playToClient(PacketUpdateClientPlayerMovement.TYPE, PacketUpdateClientPlayerMovement.CODEC, PacketUpdateClientPlayerMovement::handle);

//        registry.playToClient(S2COpenDialogueGuiMsg.TYPE, S2COpenDialogueGuiMsg.STREAM_CODEC, dialogueNetHandler::handleDialogueOpenPacket);
//        registry.playToClient(S2CCloseDialogueGuiMsg.TYPE, S2CCloseDialogueGuiMsg.STREAM_CODEC, dialogueNetHandler::handleDialogueClosePacket);
//        registry.playToServer(C2SChosenOptionMsg.TYPE, C2SChosenOptionMsg.STREAM_CODEC, dialogueNetHandler::handlePressOptionPacket);
    }

    public static void sendToServer(CustomPacketPayload packet) {
        ClientPacketDistributor.sendToServer(packet);
    }

    public static void sendToPlayer(ServerPlayer player, CustomPacketPayload packet) {
        PacketDistributor.sendToPlayer(player, packet);
    }
}
