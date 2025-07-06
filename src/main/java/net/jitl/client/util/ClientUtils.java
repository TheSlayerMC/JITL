package net.jitl.client.util;

import net.jitl.client.gui.overlay.JRecipeBookGUI;
import net.jitl.client.gui.overlay.KnowledgeToast;
import net.jitl.client.gui.screen.LoreScrollEntryScreen;
import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.capability.player.LoreScroll;
import net.jitl.common.scroll.ScrollEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class ClientUtils {

    public static void displayToast(LoreScroll scroll) {
        Minecraft.getInstance().getToastManager().addToast(new KnowledgeToast(EnumKnowledge.byName(scroll.knowledge()), true));
    }

    public static void displayScrollGui(ScrollEntry entry) {
        Minecraft.getInstance().setScreen(new LoreScrollEntryScreen(entry));
    }

    public static void displayBookGUI(Player player) {
        Minecraft.getInstance().setScreen(new JRecipeBookGUI(player.getInventory()));
    }
}
