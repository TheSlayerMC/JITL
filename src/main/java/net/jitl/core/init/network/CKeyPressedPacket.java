package net.jitl.core.init.network;

import net.jitl.common.capability.keypressed.PressedKeyCapProvider;
import net.jitl.common.event.GearAbilityHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CKeyPressedPacket {

    private final boolean isAmulet;
    private final boolean isDown;

    public CKeyPressedPacket(FriendlyByteBuf buf) {
        this.isAmulet = buf.readBoolean();
        this.isDown = buf.readBoolean();
    }

    public CKeyPressedPacket(boolean key, boolean isDown) {
        isAmulet = key;
        this.isDown = isDown;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBoolean(isAmulet);
        buffer.writeBoolean(isDown);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
            ServerPlayer player = ctx.get().getSender();
            assert player != null;
            player.getCapability(PressedKeyCapProvider.PRESSED_KEY_CAP).ifPresent(keys -> {
                if (isAmulet) {
                    keys.setAmuletPressed(isDown);
                    //CurioEventHandler.onKeyPressed(player);
                } else {
                    keys.setArmorPressed(isDown);
                    GearAbilityHandler.onKeyPressed(player);
                }
            });
            System.out.println(player.getScoreboardName() + " " + (isDown ? "pressed" : "released") + " " + (isAmulet ? "amulet" : "armor") + " ability key.");
        ctx.get().setPacketHandled(true);
    }
}