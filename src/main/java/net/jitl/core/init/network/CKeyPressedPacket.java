package net.jitl.core.init.network;

import net.jitl.common.event.GearAbilityHandler;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record CKeyPressedPacket(boolean isAmulet, boolean isDown) implements CustomPacketPayload {

    public static final Type<CKeyPressedPacket> TYPE = new Type<>(JITL.rl("key_pressed"));

    public static CKeyPressedPacket decode(FriendlyByteBuf buffer) {
        return new CKeyPressedPacket(buffer.readBoolean(), buffer.readBoolean());
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, CKeyPressedPacket> STREAM_CODEC = CustomPacketPayload.codec(CKeyPressedPacket::write, CKeyPressedPacket::decode);

    public void write(FriendlyByteBuf buffer) {
        buffer.writeBoolean(isAmulet);
        buffer.writeBoolean(isDown);
    }

    public static void handle(CKeyPressedPacket payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
                if (payload.isAmulet) {
                    player.getData(JDataAttachments.KEY_PRESSED).setAmuletPressed(payload.isDown);
                    //CurioEventHandler.onKeyPressed(player);
                } else {
                    player.getData(JDataAttachments.KEY_PRESSED).setArmorPressed(payload.isDown);
                    GearAbilityHandler.onKeyPressed(player);
                }

            System.out.println(player.getScoreboardName() + " " + (payload.isDown ? "pressed" : "released") + " " + (payload.isAmulet ? "amulet" : "armor") + " ability key.");
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}