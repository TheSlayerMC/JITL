package net.jitl.core.network;

import io.netty.buffer.ByteBuf;
import net.jitl.client.essence.ClientEssence;
import net.jitl.core.init.JITL;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import org.jetbrains.annotations.NotNull;

public record PacketEssenceBar(float essence, float burnout) implements CustomPacketPayload {

    public static final ResourceLocation ID = JITL.rl("essence");

    public static PacketEssenceBar decode(FriendlyByteBuf buffer) {
        return new PacketEssenceBar(buffer.readFloat(), buffer.readFloat());
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeFloat(essence);
        buf.writeFloat(burnout);
    }

    public void handle(PlayPayloadContext ctx) {
        ctx.workHandler().submitAsync(() -> {
            ClientEssence.setClientEssence(this.essence);
            ClientEssence.setClientBurnout(this.burnout);
        });
    }

    @Override
    public @NotNull ResourceLocation id() {
        return ID;
    }
}
