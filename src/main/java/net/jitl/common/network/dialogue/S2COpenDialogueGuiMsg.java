package net.jitl.common.network.dialogue;


import net.jitl.common.dialogue.DialogueNode;
import net.jitl.core.init.JITL;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record S2COpenDialogueGuiMsg(ResourceLocation npcKey, String textKey, List<DialogueNode.Option>options) implements CustomPacketPayload {

	public static final Type<S2COpenDialogueGuiMsg> TYPE = new Type<>(JITL.rl("open_gui"));

	//public static final Codec<List<DialogueNode.Option>> LIST_CODEC = Codec.list(DialogueNode.Option.toString()).<DialogueNode.Option>comapFlatMap.;

//	public static final StreamCodec<ByteBufCodecs, S2COpenDialogueGuiMsg> STREAM_CODEC = StreamCodec.composite(
//			ResourceLocation.CODEC, S2COpenDialogueGuiMsg::npcKey,
//			ByteBufCodecs.STRING_UTF8, S2COpenDialogueGuiMsg::textKey,
//			LIST_CODEC, S2COpenDialogueGuiMsg::options,
//			S2COpenDialogueGuiMsg::new);

	public void write(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(npcKey);
		buffer.writeUtf(textKey);
		//buffer.(options());
	}

	@Override
	public @NotNull Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
