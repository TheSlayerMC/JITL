package net.jitl.core.data;

import net.jitl.client.essence.PacketEssenceBar;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class JNetworkRegistry {

    private static int packetId = 0;
    public static SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(JITL.MODID, "jitl_packet"), () -> "1.0", s -> true, s -> true);

    private static int nextID() {
        return packetId++;
    }

    public static void init() {
        INSTANCE.registerMessage(nextID(), PacketEssenceBar.class, PacketEssenceBar::toBytes, PacketEssenceBar::new, PacketEssenceBar::handle);
    }
}
