package net.jitl.common.dialogue;

import net.jitl.common.JManagers;
import net.jitl.common.network.dialogue.S2CCloseDialogueGuiMsg;
import net.jitl.common.network.dialogue.S2COpenDialogueGuiMsg;
import net.jitl.core.data.JNetworkRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.UUID;

public class DialogueTracker {
	private final UUID playerId;
	private final ResourceLocation npcClass;
	private DialogueNode currentNode;

	public DialogueTracker(UUID playerId, ResourceLocation npcClass, DialogueNode currentNode) {
		this.playerId = playerId;
		this.npcClass = npcClass;
		this.currentNode = currentNode;
	}

	public UUID getPlayerId() {
		return playerId;
	}

	public DialogueNode getCurrentNode() {
		return currentNode;
	}

	public void start(ServerPlayer player) {
		openGuiWithCurrentNode(player);
	}

	public void pressOption(ServerPlayer player, int optionIndex) throws DialogueSystemException {
		if (optionIndex >= currentNode.getOptions().size()) {
			throw new DialogueSystemException("Tracker received the index " + optionIndex + "that doesn't fit options list size (" + currentNode.getOptions().size() + "). Problem node: " + currentNode);
		}

		DialogueNode.Option option = currentNode.getOptions().get(optionIndex);
		option.onClickAction(player.level(), player);

		currentNode = option.getNextNode();

		if (currentNode == DialogueNode.END) {
			JManagers.DIALOGUE_MANAGER.removeTracker(this);
			closeGui(player);
		} else {
			openGuiWithCurrentNode(player);
		}
	}

	private void openGuiWithCurrentNode(ServerPlayer player) {
		JNetworkRegistry.sendToPlayer(player, new S2COpenDialogueGuiMsg(npcClass, currentNode.getTextKey(), currentNode.getOptions()));
	}

	private void closeGui(ServerPlayer player) {
		JNetworkRegistry.sendToPlayer(player, new S2CCloseDialogueGuiMsg());
	}
}
