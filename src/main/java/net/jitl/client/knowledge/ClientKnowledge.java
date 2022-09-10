package net.jitl.client.knowledge;

import net.minecraft.client.Minecraft;

public class ClientKnowledge {

    public static void setClientKnowledgeXP(EnumKnowledge knowledge, float value) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).orElseThrow(null).setXP(knowledge, value);
    }

    public static float getClientKnowledgeXP(EnumKnowledge knowledge) {
        assert Minecraft.getInstance().player != null;
        return Minecraft.getInstance().player.getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).orElseThrow(null).getXP(knowledge);
    }

    public static void setClientKnowledgeLevel(EnumKnowledge knowledge, int amount) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).orElseThrow(null).setLevel(knowledge, amount);
    }

    public static int getClientKnowledgeLevel(EnumKnowledge knowledge) {
        assert Minecraft.getInstance().player != null;
        return Minecraft.getInstance().player.getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).orElseThrow(null).getLevel(knowledge);
    }
}