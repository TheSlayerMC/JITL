package net.jitl.core.init.network.dialogue;

import net.jitl.core.init.JITL;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
public record S2CCloseDialogueGuiMsg() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<S2CCloseDialogueGuiMsg> TYPE = new CustomPacketPayload.Type<>(JITL.rl("close_dialogue"));

    public static final StreamCodec<FriendlyByteBuf, S2CCloseDialogueGuiMsg> STREAM_CODEC = CustomPacketPayload.codec(S2CCloseDialogueGuiMsg::write, S2CCloseDialogueGuiMsg::decode);


    public static S2CCloseDialogueGuiMsg decode(FriendlyByteBuf buffer) {
        return new S2CCloseDialogueGuiMsg();
    }

    public void write(FriendlyByteBuf buffer) {

    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
