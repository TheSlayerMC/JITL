package net.jitl.common.entity.jmerchent;

import net.jitl.common.world.menu.SentacoinMerchantMenu;
import net.jitl.core.init.JITL;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import org.jetbrains.annotations.NotNull;

public record ClientBoundSentacoinMerchantOffersPacket(SentacoinMerchantOffers offers) implements CustomPacketPayload {

    public static final ResourceLocation ID = JITL.rl("sentacoin_merchant_offers");

    public static ClientBoundSentacoinMerchantOffersPacket decode(FriendlyByteBuf buf) {
        return new ClientBoundSentacoinMerchantOffersPacket(SentacoinMerchantOffers.createFromStream(buf));
    }

    public void write(FriendlyByteBuf pBuffer) {
        this.offers.writeToStream(pBuffer);
    }

    @Override
    public @NotNull ResourceLocation id() {
        return ID;
    }

    public void handle(PlayPayloadContext context) {
        context.workHandler().submitAsync(() -> {
            assert Minecraft.getInstance().player != null;
            AbstractContainerMenu abstractcontainermenu = Minecraft.getInstance().player.containerMenu;
            if(abstractcontainermenu instanceof SentacoinMerchantMenu m) {
                m.setOffers(getOffers());
            }
        });
    }

    public SentacoinMerchantOffers getOffers() {
        return this.offers;
    }
}
