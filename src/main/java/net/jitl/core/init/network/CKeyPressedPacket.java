package net.jitl.core.init.network;

import net.jitl.common.event.GearAbilityHandler;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.network.PacketEssenceBar;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import org.jetbrains.annotations.NotNull;

public record CKeyPressedPacket(boolean isAmulet, boolean isDown) implements CustomPacketPayload {

    public static final ResourceLocation ID = JITL.rl("key_pressed");

    public static CKeyPressedPacket decode(FriendlyByteBuf buffer) {
        return new CKeyPressedPacket(buffer.readBoolean(), buffer.readBoolean());
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeBoolean(isAmulet);
        buffer.writeBoolean(isDown);
    }

    public void handle(PlayPayloadContext context) {
        context.workHandler().submitAsync(() -> {

            Player player = context.player().get();
                if (isAmulet) {
                    player.getData(JDataAttachments.KEY_PRESSED).setAmuletPressed(isDown);
                    //CurioEventHandler.onKeyPressed(player);
                } else {
                    player.getData(JDataAttachments.KEY_PRESSED).setArmorPressed(isDown);
                    GearAbilityHandler.onKeyPressed(player);
                }

            System.out.println(player.getScoreboardName() + " " + (isDown ? "pressed" : "released") + " " + (isAmulet ? "amulet" : "armor") + " ability key.");
        });
    }

    @Override
    public @NotNull ResourceLocation id() {
        return ID;
    }
}