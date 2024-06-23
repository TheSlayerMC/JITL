package net.jitl.common.network.dialogue;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.jitl.common.capability.player.LoreScroll;
import net.jitl.common.dialogue.DialogueNode;
import net.jitl.core.init.JITL;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record S2COpenDialogueGuiMsg(ResourceLocation npcKey, String textKey, List<DialogueNode.Option>options) implements CustomPacketPayload {

	public static final Type<S2COpenDialogueGuiMsg> TYPE = new Type<>(JITL.rl("open_gui"));

//	public static final Codec<List<DialogueNode.Option>> LIST_CODEC = RecordCodecBuilder.create(rec -> rec.group(ExtraCodecs.nonEmptyList(
//			Codec.list(DialogueNode.CODEC).listOf()).fieldOf("option").forGetter(o -> o.)));
//
//	public static final Codec<S2COpenDialogueGuiMsg> CODEC = RecordCodecBuilder.create(objectInstance ->
//			objectInstance.group(
//			ResourceLocation.CODEC.fieldOf("npcKey").forGetter(S2COpenDialogueGuiMsg::npcKey),
//			Codec.STRING.fieldOf("textKey").forGetter(S2COpenDialogueGuiMsg::textKey),
//			LIST_CODEC.fieldOf("options").forGetter(S2COpenDialogueGuiMsg::options))
//					.apply(objectInstance, S2COpenDialogueGuiMsg::new));
//
//	public static final StreamCodec<ByteBuf, S2COpenDialogueGuiMsg> STREAM_CODEC = StreamCodec.composite(
//			ByteBufCodecs.STRING_UTF8, S2COpenDialogueGuiMsg::npcKey,
//			ByteBufCodecs.STRING_UTF8, S2COpenDialogueGuiMsg::textKey,
//			LIST_CODEC, S2COpenDialogueGuiMsg::options,
//			S2COpenDialogueGuiMsg::new
//	);

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
