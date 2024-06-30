package net.jitl.core.data;

import net.jitl.client.stats.PacketPlayerStats;
import net.jitl.common.JManagers;
import net.jitl.common.dialogue.DialogueNetHandler;
import net.jitl.common.network.dialogue.C2SChosenOptionMsg;
import net.jitl.common.network.dialogue.S2CCloseDialogueGuiMsg;
import net.jitl.common.network.dialogue.S2COpenDialogueGuiMsg;
import net.jitl.core.init.JITL;
import net.jitl.core.init.network.CKeyPressedPacket;
import net.jitl.core.init.network.PacketCelestiumArmor;
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
        DialogueNetHandler dialogueNetHandler = getDialogueNetHandler();


        registry.playBidirectional(PacketPlayerStats.TYPE, PacketPlayerStats.STREAM_CODEC, PacketPlayerStats::handle);
        registry.playBidirectional(PacketEssenceBar.TYPE, PacketEssenceBar.STREAM_CODEC, PacketEssenceBar::handle);
        registry.playBidirectional(PacketCelestiumArmor.TYPE, PacketCelestiumArmor.STREAM_CODEC, PacketCelestiumArmor::handle);
        registry.playBidirectional(CKeyPressedPacket.TYPE, CKeyPressedPacket.STREAM_CODEC, CKeyPressedPacket::handle);

//        registry.playToClient(S2COpenDialogueGuiMsg.TYPE, S2COpenDialogueGuiMsg.STREAM_CODEC, dialogueNetHandler::handleDialogueOpenPacket);
//        registry.playToClient(S2CCloseDialogueGuiMsg.TYPE, S2CCloseDialogueGuiMsg.STREAM_CODEC, dialogueNetHandler::handleDialogueClosePacket);
//        registry.playToServer(C2SChosenOptionMsg.TYPE, C2SChosenOptionMsg.STREAM_CODEC, dialogueNetHandler::handlePressOptionPacket);
    }

    public static void sendToPlayer(ServerPlayer player, CustomPacketPayload packet) {
        PacketDistributor.sendToPlayer(player, packet);
    }

    public static DialogueNetHandler getDialogueNetHandler() {
        return JManagers.DIALOGUE_MANAGER.getNetHandler();
    }
}
