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

    public void addLevel(Player player, EnumKnowledge knowledge, int level) {
        knowledgeMap.get(knowledge).addLevel(level, knowledge);
        //update(player);
    }

    public void setLevel(EnumKnowledge knowledge, int level) {
        knowledgeMap.get(knowledge).setLevel(level);
        //update(player);
    }

    public void setXP(EnumKnowledge knowledge, float xp) {
        knowledgeMap.get(knowledge).setXP(xp);
        //update(player);
    }

    public float getLevelCapacity(int level) {
        return level >= 5 ? 50 : level >= 10 ? 70 : level >= 15 ? 90 : level >= 20 ? 110 : level >= 30 ? 130 : level >= 40 ? 150 : 30;
    }

    public float getXP(EnumKnowledge type) {
        return knowledgeMap.get(type).getAmountOnCurrentLevel();
    }

    public void addXP(EnumKnowledge type, float xp, boolean showXPToast) {
        knowledgeMap.get(type).add(xp, type);
        if(showXPToast)
            Minecraft.getInstance().getToasts().addToast(new KnowledgeToast(type, true));
    }

    public void addXP(Player p, EnumKnowledge type, float xp) {
        this.addXP(type, xp, true);
    }

    public void copyFrom(PlayerKnowledge oldStore) {
        this.knowledgeMap = oldStore.knowledgeMap;
    }

    public void update(Player p) {
        for(EnumKnowledge k : EnumKnowledge.values())
            knowledgeMap.get(k).sendPacket(k, p);
    }

    public void saveNBT(CompoundTag nbt) {
        for(EnumKnowledge k : EnumKnowledge.values()) {
            knowledgeMap.get(k).saveNBT(nbt);
        }
    }

    public void readNBT(CompoundTag nbt) {
        for(EnumKnowledge k : EnumKnowledge.values()) {
            knowledgeMap.get(k).readNBT(nbt);
        }
    }
}