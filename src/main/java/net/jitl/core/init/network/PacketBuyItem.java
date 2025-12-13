package net.jitl.core.init.network;

import net.jitl.common.capability.stats.PlayerStats;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record PacketBuyItem(String item, int amount, int cost) implements CustomPacketPayload {

    public static final Type<PacketBuyItem> TYPE = new Type<>(JITL.rl("buy_item"));
    public static final StreamCodec<RegistryFriendlyByteBuf, PacketBuyItem> STREAM_CODEC = CustomPacketPayload.codec(PacketBuyItem::write, PacketBuyItem::decode);

    public static PacketBuyItem decode(FriendlyByteBuf buffer) {
        return new PacketBuyItem(buffer.readUtf(), buffer.readInt(), buffer.readInt());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeUtf(item);
        buf.writeInt(amount);
        buf.writeInt(cost);
    }

    public static void handle(PacketBuyItem payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Player player = ctx.player();
            PlayerStats stats = player.getData(JDataAttachments.PLAYER_STATS);
            Item item = BuiltInRegistries.ITEM.getValue(Identifier.parse(payload.item));

            if(stats.useSentacoins(payload.cost())) {
                player.addItem(new ItemStack(item, payload.amount));
            } else {
                player.displayClientMessage(Component.translatable("jitl.trade.no"), false);
            }
        });
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

}