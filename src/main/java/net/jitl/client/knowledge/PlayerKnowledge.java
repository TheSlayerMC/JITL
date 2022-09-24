package net.jitl.client.knowledge;

import net.jitl.client.gui.overlay.KnowledgeToast;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

import java.util.EnumMap;

public class PlayerKnowledge {

    private EnumMap<EnumKnowledge, KnowledgeStorage> knowledgeMap;

    public PlayerKnowledge() {
        this.knowledgeMap = new EnumMap<>(EnumKnowledge.class);

        for(EnumKnowledge type : EnumKnowledge.values()) {
            this.knowledgeMap.put(type, new KnowledgeStorage());
        }
    }

    public int getLevel(EnumKnowledge knowledge) {
        return knowledgeMap.get(knowledge).getLevelCount();
    }

    public void addLevel(Player p, EnumKnowledge knowledge, int level) {
        knowledgeMap.get(knowledge).addLevel(level, p, knowledge);
    }

    public boolean isCompleted(EnumKnowledge k) {
        return knowledgeMap.get(k).isCompleted();
    }

    public void setLevel(EnumKnowledge knowledge, int level) {
        knowledgeMap.get(knowledge).setLevel(level);
    }

    public void setXP(EnumKnowledge knowledge, float xp) {
        knowledgeMap.get(knowledge).setXP(xp);
    }

    public float getLevelCapacity(int level) {
        return level >= 5 ? 50 : level >= 10 ? 70 : level >= 15 ? 90 : level >= 20 ? 110 : level >= 30 ? 130 : level >= 40 ? 150 : 30;
    }

    public float getXP(EnumKnowledge type) {
        return knowledgeMap.get(type).getAmountOnCurrentLevel();
    }

    public void addXP(EnumKnowledge type, Player p, float xp, boolean showXPToast) {
        knowledgeMap.get(type).add(xp, p, type);
        if(showXPToast)
            Minecraft.getInstance().getToasts().addToast(new KnowledgeToast(type, false));
    }

    public void addXP(Player p, EnumKnowledge type, float xp) {
        this.addXP(type, p, xp, true);
    }

    public void copyFrom(PlayerKnowledge oldStore) {
        this.knowledgeMap = oldStore.knowledgeMap;
        for(EnumKnowledge k : EnumKnowledge.values())
            this.knowledgeMap.get(k).copyFrom(oldStore.knowledgeMap.get(k));
    }

    public void update(Player p) {
        for(EnumKnowledge k : EnumKnowledge.values()) {
            knowledgeMap.get(k).sendPacket(k, p);
            knowledgeMap.get(k).update();
        }
    }

    public void saveNBT(CompoundTag tag) {
        for(EnumKnowledge k : EnumKnowledge.values()) {
            knowledgeMap.get(k).saveNBT(tag);
        }
    }

    public void readNBT(CompoundTag nbt) {
        for(EnumKnowledge k : EnumKnowledge.values()) {
            knowledgeMap.get(k).readNBT(nbt);
        }
    }
}