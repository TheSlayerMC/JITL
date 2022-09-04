package net.jitl.core.data;

import net.jitl.client.essence.PacketEssenceBar;
import net.jitl.client.knowledge.PacketKnowledge;
import net.jitl.core.init.JITL;
import net.jitl.core.init.network.SBossPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;

public class JNetworkRegistry {

    private static int packetId = 0;
    public static SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(JITL.MODID, "jitl_packet"), () -> "1.0", s -> true, s -> true);

    private static int nextID() {
        return packetId++;
    }

    public static void init() {
        INSTANCE.registerMessage(nextID(), PacketEssenceBar.class, PacketEssenceBar::toBytes, PacketEssenceBar::new, PacketEssenceBar::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(nextID(), SBossPacket.class, SBossPacket::toBytes, SBossPacket::new, SBossPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(nextID(), PacketKnowledge.class, PacketKnowledge::toBytes, PacketKnowledge::new, PacketKnowledge::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
    }
}
