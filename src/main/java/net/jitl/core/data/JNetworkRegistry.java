package net.jitl.core.data;

import net.jitl.client.essence.PacketEssenceBar;
import net.jitl.core.init.JITL;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;

public class JNetworkRegistry {

    private static int packetId = 0;
    public static SimpleChannel INSTANCE;

    private static int nextID() {
        return packetId++;
    }

    public static void init() {
        SimpleChannel channel = NetworkRegistry.newSimpleChannel(new ResourceLocation(JITL.MODID, "jitl_packet"), () -> "1.0", s -> true, s -> true);

        INSTANCE = channel;

        channel.messageBuilder(PacketEssenceBar.class, nextID(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PacketEssenceBar::new).encoder(PacketEssenceBar::toBytes).consumerMainThread(PacketEssenceBar::handle).add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
