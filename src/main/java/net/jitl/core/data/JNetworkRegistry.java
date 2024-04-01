package net.jitl.core.data;

import net.jitl.client.knowledge.PacketKnowledge;
import net.jitl.client.stats.PacketPlayerStats;
import net.jitl.core.init.JITL;
import net.jitl.core.init.network.CKeyPressedPacket;
import net.jitl.core.init.network.SBossPacket;
import net.jitl.core.network.PacketEssenceBar;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class JNetworkRegistry {

    private static final String PROTOCOL_VERSION = "1";

    public static SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(JITL.MODID, "main"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void init() {
        int index = 0;
        INSTANCE.registerMessage(index++, PacketEssenceBar.class, PacketEssenceBar::encode, PacketEssenceBar::decode, PacketEssenceBar::handle);
        INSTANCE.registerMessage(index++, CKeyPressedPacket.class, CKeyPressedPacket::encode, CKeyPressedPacket::new, CKeyPressedPacket::handle);
        INSTANCE.registerMessage(index++, SBossPacket.class, SBossPacket::encode, SBossPacket::new, SBossPacket::handle);
        INSTANCE.registerMessage(index++, PacketKnowledge.class, PacketKnowledge::encode, PacketKnowledge::new, PacketKnowledge::handle);
        INSTANCE.registerMessage(index++, PacketPlayerStats.class, PacketPlayerStats::encode, PacketPlayerStats::new, PacketPlayerStats::handle);
    }
}
