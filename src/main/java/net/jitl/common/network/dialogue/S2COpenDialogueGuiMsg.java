package net.jitl.common.network.dialogue;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.jitl.common.capability.player.LoreScroll;
import net.jitl.common.dialogue.DialogueNode;
import net.jitl.core.init.JITL;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.Utf8String;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record S2COpenDialogueGuiMsg(ResourceLocation npcKey, String textKey, List<DialogueNode.Option>options) implements CustomPacketPayload {

	public static final Type<S2COpenDialogueGuiMsg> TYPE = new Type<>(JITL.rl("open_gui"));

//	public static final StreamCodec<ByteBuf, List<DialogueNode.Option>> LIST_CODEC = DialogueNode.Option.CODEC.listOf();

	static StreamCodec<ByteBuf, List<DialogueNode.Option>> opt() {

		return new StreamCodec<>() {
            public @NotNull List<DialogueNode.Option> decode(@NotNull ByteBuf byteBuf) {
                List<DialogueNode.Option> opt = List.of();
                return opt;
            }

            public void encode(ByteBuf byteBuf, List<DialogueNode.Option> option) {

            }
        };
	}

	public static final StreamCodec<FriendlyByteBuf, S2COpenDialogueGuiMsg> STREAM_CODEC = StreamCodec.composite(
			ResourceLocation.STREAM_CODEC, S2COpenDialogueGuiMsg::npcKey,
			ByteBufCodecs.STRING_UTF8, S2COpenDialogueGuiMsg::textKey,
			opt(), S2COpenDialogueGuiMsg::options,
			S2COpenDialogueGuiMsg::new
	);

	public List<DialogueNode.Option> getOptions() {
		return options;
	}

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
