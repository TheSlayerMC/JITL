package net.jitl.core.init.network.dialogue;

import net.jitl.core.init.JITL;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record C2SChosenOptionMsg(int optionIndex) implements CustomPacketPayload {

	public static final Type<C2SChosenOptionMsg> TYPE = new Type<>(JITL.rl("chosen_option"));

	public static final StreamCodec<RegistryFriendlyByteBuf, C2SChosenOptionMsg> STREAM_CODEC = CustomPacketPayload.codec(C2SChosenOptionMsg::write, C2SChosenOptionMsg::decode);


	public static C2SChosenOptionMsg decode(FriendlyByteBuf buffer) {
		return new C2SChosenOptionMsg(buffer.readInt());
	}

	public void write(FriendlyByteBuf buffer) {
		buffer.writeInt(optionIndex);
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
