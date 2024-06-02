package net.jitl.common.dialogue;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.UUID;
//TODO add DialogueTracker serializing
public class DialogueManager {
	private final DialogueNetHandler netHandler = new DialogueNetHandler();
	private final HashMap<UUID, DialogueTracker> trackers = new HashMap<>();//FIXME move to map of lists of trackers

	public void startDialogue(ServerPlayer player, ResourceLocation npc, Dialogue dialogue) {
		DialogueRegistry dialogueRegistry = JRegistries.DIALOGUE_REGISTRY;
		dialogueRegistry.verifyRegistration(dialogue);

		startDialogue(player, npc, dialogue.getRootNode());
	}

	public DialogueNetHandler getNetHandler() {
		return netHandler;
	}

	private void startDialogue(ServerPlayer player, ResourceLocation npcClass, DialogueNode node) {
		DialogueTracker tracker = new DialogueTracker(player.getUUID(), npcClass, node);
		trackers.put(player.getUUID(), tracker);

		tracker.start(player);
	}

	public void handleDialogueChosenOption(ServerPlayer player, int optionIndex) throws DialogueSystemException {
		DialogueTracker dialogueTracker = trackers.get(player.getUUID());
		if (dialogueTracker == null) { // this can be achieved when someone try to use cheaty exploits
			throw new DialogueSystemException("There are no opened dialogues on server! What are you trying to do???");
		}

		dialogueTracker.pressOption(player, optionIndex);
	}

	void removeTracker(DialogueTracker tracker) {
		trackers.remove(tracker.getPlayerId());
	}
}
