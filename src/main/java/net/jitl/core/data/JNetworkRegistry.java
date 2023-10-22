package net.jitl.core.data;

import net.jitl.client.knowledge.PacketKnowledge;
import net.jitl.client.stats.PacketPlayerStats;
import net.jitl.core.init.JITL;
import net.jitl.core.init.network.CKeyPressedPacket;
import net.jitl.core.init.network.SBossPacket;
import net.jitl.core.network.PacketEssenceBar;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.SimpleChannel;

public class JNetworkRegistry {

    public static SimpleChannel INSTANCE = ChannelBuilder
            .named(new ResourceLocation(JITL.MODID, "main"))
            .networkProtocolVersion(1)
            .simpleChannel();

    public static void init() {
        INSTANCE.messageBuilder(PacketEssenceBar.class, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(PacketEssenceBar::encode)
                .decoder(PacketEssenceBar::decode)
                .consumerNetworkThread(PacketEssenceBar::handle)
                .add();

        INSTANCE.messageBuilder(CKeyPressedPacket.class, NetworkDirection.PLAY_TO_SERVER)
                .encoder(CKeyPressedPacket::encode)
                .decoder(CKeyPressedPacket::new)
                .consumerNetworkThread(CKeyPressedPacket::handle)
                .add();

        INSTANCE.messageBuilder(SBossPacket.class, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(SBossPacket::encode)
                .decoder(SBossPacket::new)
                .consumerNetworkThread(SBossPacket::handle)
                .add();

        INSTANCE.messageBuilder(PacketKnowledge.class, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(PacketKnowledge::encode)
                .decoder(PacketKnowledge::new)
                .consumerNetworkThread(PacketKnowledge::handle)
                .add();


        INSTANCE.messageBuilder(PacketPlayerStats.class, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(PacketPlayerStats::encode)
                .decoder(PacketPlayerStats::new)
                .consumerNetworkThread(PacketPlayerStats::handle)
                .add();
    }
}
