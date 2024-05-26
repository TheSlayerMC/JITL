package net.jitl.core.init.internal;


import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.capability.stats.PlayerStats;
import net.jitl.common.dialogue.Dialogue;
import net.jitl.common.dialogue.DialogueBuilder;
import net.jitl.common.dialogue.JRegistries;
import net.jitl.common.items.LoreScrollItem;
import net.jitl.core.init.JITL;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class JDialogues {
	public static String STRING_HOODED = "The Hooded: ";
	public static final Dialogue TEST = JRegistries.DIALOGUE_REGISTRY.register(
			DialogueBuilder.dialogue(JITL.rl("test"),
					DialogueBuilder.node("dialogue.jitl.test.hello.text")
							.addOption(DialogueBuilder.option("dialogue.jitl.test.hello.answer.to_nonsense")
									.leadsTo(DialogueBuilder.node("dialogue.jitl.test.nonsense.text")
											.addOption(DialogueBuilder.option("dialogue.jitl.test.nonsense.answer.wtf")
													.withAction((world, player) -> {
														if (!world.isClientSide) {
															sendMessage(player, "Woop-woop!");
														}
													})
											)
									)
							)
							.addOption(DialogueBuilder.option("dialogue.jitl.test.hello.answer.bye"))
							.addOption(DialogueBuilder.option("dialogue.jitl.test.hello.answer.bye2"))
							.addOption(DialogueBuilder.option("dialogue.jitl.test.hello.answer.bye3"))
			)
	);
	public static final Dialogue THE_HOODED = JRegistries.DIALOGUE_REGISTRY.register(
			DialogueBuilder.dialogue(JITL.rl("the_hooded"),
					DialogueBuilder.node("What do you want? We don't have time for unworthy travelers.")
							.addOption(DialogueBuilder.option("What do you mean by 'unworthy' travelers?")
									.leadsTo(DialogueBuilder.node("All who are not loyal to the Divine Rock and Eye are not worthy of our time.")
											.addOption(DialogueBuilder.option("The Divine Rock and Eye?")
													.leadsTo(DialogueBuilder.node("He gives us life. He is the one true God. If you do not praise him, do not speak to me.")
															.addOption(DialogueBuilder.option("Where can I learn more about him?")
																	.leadsTo(DialogueBuilder.node("If you truly wish to become a divine follower, take this scroll. Otherwise, he will take care of you eventually..."))
																	.withAction((world, player) -> {
																		if (!world.isClientSide) {
																			PlayerStats journeyPlayer = player.getData(JDataAttachments.PLAYER_STATS);
																			sendMessage(player, "You've gained 100 knowledge");

																			ItemStack scrollStack = new ItemStack(JItems.LORE_SCROLL.get());
																			LoreScrollItem.bindScrollEntry(scrollStack, ScrollEntries.SENTERIAN_GOSPEL, EnumKnowledge.CORBA, 100);

																			player.addItem(scrollStack);
																		}
																	})
															)
															.addOption(DialogueBuilder.option("I don't belong to any Gods.")
																	.leadsTo(DialogueBuilder.node("Then I have no reason to speak to you. I have things to do here, now go away.")
																	)
															)
															.addOption(DialogueBuilder.option("I am loyal to him.")
																	.leadsTo(DialogueBuilder.node("You can't fool me. I know a true follower when I see one, and you look lost.")
																	)
															)
													)
											)
									)
							)
							.addOption(DialogueBuilder.option("I was hoping you could tell me more about this place.")
									.leadsTo(DialogueBuilder.node("This is the Divine Land Corba. Of all the realms, it is the most beautiful.")
											.addOption(DialogueBuilder.option("Yes it's... uh, beautiful.")
													.leadsTo(DialogueBuilder.node("...")
													)
											)
											.addOption(DialogueBuilder.option("The air here is putrid, I can hardly breathe.")
													.leadsTo(DialogueBuilder.node("...")
													)
											)
									)
							)
							.addOption(DialogueBuilder.option("Sorry for disturbing you... goodbye."))
			)
	);

	public static void sendMessage(Player player, String key) {
		MutableComponent msg = Component.literal(key);
		player.sendSystemMessage(msg);
	}
}
