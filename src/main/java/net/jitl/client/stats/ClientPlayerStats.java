package net.jitl.client.stats;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.client.Minecraft;

public class ClientPlayerStats {

    public static void setHasBlizzard(boolean value) {
        Minecraft.getInstance().player.getData(JDataAttachments.PLAYER_STATS.get()).setBlizzard(value);
    }

    public static boolean getHasBlizzard() {
        return Minecraft.getInstance().player.getData(JDataAttachments.PLAYER_STATS.get()).hasBlizzard();
    }

    public static void addSentacoins(int value) {
        Minecraft.getInstance().player.getData(JDataAttachments.PLAYER_STATS.get()).addSentacoins(value);
    }

    public static void useSentacoins(int value) {
        Minecraft.getInstance().player.getData(JDataAttachments.PLAYER_STATS.get()).useSentacoins(value);
    }

    public static int getSentacoins() {
        return Minecraft.getInstance().player.getData(JDataAttachments.PLAYER_STATS.get()).getSentacoins();
    }

    public static void setSentacoins(int value) {
        Minecraft.getInstance().player.getData(JDataAttachments.PLAYER_STATS.get()).setSentacoins(value);
    }

    public static void setClientKnowledgeXP(EnumKnowledge knowledge, float value) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(JDataAttachments.PLAYER_STATS.get()).setXP(knowledge, value);
    }

    public static float getClientKnowledgeXP(EnumKnowledge knowledge) {
        assert Minecraft.getInstance().player != null;
        return Minecraft.getInstance().player.getData(JDataAttachments.PLAYER_STATS.get()).getXP(knowledge);
    }

    public static void setClientKnowledgeLevel(EnumKnowledge knowledge, int amount) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(JDataAttachments.PLAYER_STATS.get()).setLevel(knowledge, amount);
    }

    public static int getClientKnowledgeLevel(EnumKnowledge knowledge) {
        assert Minecraft.getInstance().player != null;
        return Minecraft.getInstance().player.getData(JDataAttachments.PLAYER_STATS.get()).getLevel(knowledge);
    }

}
