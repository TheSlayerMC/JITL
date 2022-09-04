package net.jitl.client.knowledge;

import net.jitl.client.gui.overlay.Knowledge;
import net.minecraft.client.Minecraft;

public class ClientKnowledge {

    public static void addClientKnowledgeXP(EnumKnowledge knowledge, float value) {
        Minecraft.getInstance().player.getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).orElseThrow(null).addXP(knowledge, value);
    }

    public static float getClientKnowledgeXP(EnumKnowledge knowledge) {
        return Minecraft.getInstance().player.getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).orElseThrow(null).getXP(knowledge);
    }

    public static void addClientKnowledgeLevel(EnumKnowledge knowledge, int amount) {
        Minecraft.getInstance().player.getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).orElseThrow(null).addLevel(knowledge, amount);
    }

    public static int getClientKnowledgeLevel(EnumKnowledge knowledge) {
        return Minecraft.getInstance().player.getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).orElseThrow(null).getLevel(knowledge);
    }
}