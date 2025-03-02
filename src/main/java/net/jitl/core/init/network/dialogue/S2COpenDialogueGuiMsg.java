package net.jitl.core.init.network.dialogue;

import net.jitl.common.dialogue.DialogueNode;
import net.jitl.core.init.JITL;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public record S2COpenDialogueGuiMsg(ResourceLocation npcKey, String textKey, List<DialogueNode.Option> options) implements CustomPacketPayload {

	public static final Type<S2COpenDialogueGuiMsg> TYPE = new Type<>(JITL.rl("open_gui"));

	public static final StreamCodec<FriendlyByteBuf, List<DialogueNode.Option>> OPTION_CODEC = new StreamCodec<>() {

		@Override
		public @NotNull List<DialogueNode.Option> decode(FriendlyByteBuf buf) {
			List<DialogueNode.Option> opt = new ArrayList<>();
			opt.add(DialogueNode.Option.of(buf.readUtf()));
			return opt;
		}

		@Override
		public void encode(FriendlyByteBuf buf, List<DialogueNode.Option> options) {
			buf.writeUtf(options.toString());
		}
	};

	public static final StreamCodec<FriendlyByteBuf, S2COpenDialogueGuiMsg> STREAM_CODEC = StreamCodec.composite(
			ResourceLocation.STREAM_CODEC, S2COpenDialogueGuiMsg::npcKey,
			ByteBufCodecs.STRING_UTF8, S2COpenDialogueGuiMsg::textKey,
			OPTION_CODEC, S2COpenDialogueGuiMsg::options,
			S2COpenDialogueGuiMsg::new
	);

	public void write(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(npcKey);
		buffer.writeUtf(textKey);
		for(DialogueNode.Option opt : options)
			buffer.writeUtf(opt.getText());
	}

	@Override
	public @NotNull Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
