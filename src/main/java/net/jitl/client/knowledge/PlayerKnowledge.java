package net.jitl.client.knowledge;

import java.util.EnumMap;

public class PlayerKnowledge {

    private final EnumMap<EnumKnowledge, KnowledgeStorage> knowledgeMap;

    public PlayerKnowledge() {
        this.knowledgeMap = new EnumMap<>(EnumKnowledge.class);

        for(EnumKnowledge type : EnumKnowledge.values()) {
            KnowledgeStorage container = knowledgeMap.put(type, new KnowledgeStorage());
            this.knowledgeMap.put(type, container);
        }
    }

    public int getLevel(EnumKnowledge knowledge) {
        return knowledgeMap.get(knowledge).getLevelCount();
    }

    public void addLevel(EnumKnowledge knowledge, int level) {
        knowledgeMap.get(knowledge).addLevel(level, knowledge);
    }

    public KnowledgeStorage getKnowledge(EnumKnowledge knowledgeType) {
        return knowledgeMap.get(knowledgeType);
    }

    public float getLevelCapacity(int level) {
        return level >= 5 ? 50 : level >= 10 ? 70 : level >= 15 ? 90 : level >= 20 ? 110 : level >= 30 ? 130 : level >= 40 ? 150 : 30;
    }

    public float getXP(EnumKnowledge type) {
        return knowledgeMap.get(type).getAmountOnCurrentLevel();
    }

    public void addXP(EnumKnowledge type, float xp, boolean showXPToast) {
        knowledgeMap.get(type).add(xp, type);
        //if(showXPToast)
           // Minecraft.getInstance().getToasts().addToast(new KnowledgeToast(type, true));
    }

    public void addXP(EnumKnowledge type, float xp) {
        this.addXP(type, xp, false);
    }
}