package net.jitl.common.dialogue;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DialogueNode {
	public static final DialogueNode END = new DialogueNode("");
	public static final Action EMPTY_ACTION = (world, player) -> {
	};

	private static final List<Option> STANDBY_END_OPTION_LIST = Collections.singletonList(new Option("dialogue.jitl.standby_end_option"));

	private final String text;
	private List<Option> options;

	public static final Codec<DialogueNode> CODEC = RecordCodecBuilder.create(instance ->
			instance.group(
					Codec.STRING.fieldOf("text").forGetter(DialogueNode::getTextKey)
			).apply(instance, DialogueNode::new)
	);

	public DialogueNode(String text) {
		this.text = text;
	}

	public void addOption(Option option) {
		if (options == null) options = new ArrayList<>();
		options.add(option);
	}

	public List<Option> getOptions() {
		return options != null ? options : STANDBY_END_OPTION_LIST;
	}

	public String getTextKey() {
		return text;
	}

	@Override
	public String toString() {
		return "DialogueNode{" +
				"text='" + text + '\'' +
				", options=" + options +
				'}';
	}

	public static class Option {
		private final String text;
		private DialogueNode nextNode = END;
		private Action onClickAction = EMPTY_ACTION;

		public static Option of(String option) {
			return new Option(option);
		}

		public static final Codec<Option> CODEC = RecordCodecBuilder.create(instance ->
				instance.group(
						Codec.STRING.fieldOf("text").forGetter(Option::getText)
				).apply(instance, Option::new)
		);

		public Option(String text) {
			this.text = text;
		}

		public void setOnClickAction(Action onClickAction) {
			this.onClickAction = onClickAction;
		}

		public void setNextNode(DialogueNode nextNode) {
			this.nextNode = nextNode;
		}

		public String getText() {
			return text;
		}

		public DialogueNode getNextNode() {
			return nextNode;
		}

		public void onClickAction(Level world, ServerPlayer player) {
			onClickAction.onClick(world, player);
		}

		@Override
		public String toString() {
			return "Option{" +
					"text='" + text + '\'' +
					", hasNextNode=" + (nextNode != END) +
					'}';
		}
	}

	public interface Action {
		void onClick(Level world, ServerPlayer player);
	}
}
