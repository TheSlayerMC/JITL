package net.jitl.common.capability.stats;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.client.stats.PacketPlayerStats;
import net.jitl.core.data.JNetworkRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.util.ValueIOSerializable;

public class PlayerStats implements ValueIOSerializable {

    private boolean hasBlizzard;
    private int sentacoins;

    public void copyFrom(PlayerStats stats) {
        this.hasBlizzard = stats.hasBlizzard;

        for(EnumKnowledge k : EnumKnowledge.values()) {
            setLevel(k, stats.getLevel(k));
            setXP(k, stats.getXP(k));
        }
    }

    public boolean hasBlizzard() {
        return hasBlizzard;
    }

    public void setBlizzard(boolean blizzard) {
        this.hasBlizzard = blizzard;
    }

    public int getSentacoins(){
        return sentacoins;
    }

    public void setSentacoins(int value){
        sentacoins = value;
    }

    public boolean useSentacoins(int amount) {
        if(sentacoins >= amount) {
            sentacoins -= amount;
            return true;
        } else {
            return false;
        }
    }

    public void addSentacoins(int amount) {
       sentacoins += amount;
    }

    private int overworldLevel, netherLevel, endLevel, eucaLevel, boilLevel, frozenLevel, depthsLevel, corbaLevel, cloudiaLevel, terraniaLevel, senterianLevel;
    private float overworldXP, netherXP, endXP, eucaXP, boilXP, frozenXP, depthsXP, corbaXP, cloudiaXP, terraniaXP, senterianXP;

    public int getLevel(EnumKnowledge knowledge) {
        int level = 0;
        switch(knowledge) {
            case OVERWORLD -> level = overworldLevel;
            case NETHER -> level = netherLevel;
            case END -> level = endLevel;
            case EUCA -> level = eucaLevel;
            case BOIL -> level = boilLevel;
            case FROZEN -> level = frozenLevel;
            case DEPTHS -> level = depthsLevel;
            case CORBA -> level = corbaLevel;
            case CLOUDIA -> level = cloudiaLevel;
            case TERRANIA -> level = terraniaLevel;
            case SENTERIAN -> level = senterianLevel;
        }
        return level;
    }

    public void addLevel(EnumKnowledge knowledge, int level) {
        switch(knowledge) {
            case OVERWORLD -> overworldLevel = overworldLevel + level;
            case NETHER -> netherLevel = netherLevel + level;
            case END -> endLevel = endLevel + level;
            case EUCA -> eucaLevel = eucaLevel + level;
            case BOIL -> boilLevel = boilLevel + level;
            case FROZEN -> frozenLevel = frozenLevel + level;
            case DEPTHS -> depthsLevel = depthsLevel + level;
            case CORBA -> corbaLevel = corbaLevel + level;
            case CLOUDIA -> cloudiaLevel = cloudiaLevel + level;
            case TERRANIA -> terraniaLevel = terraniaLevel + level;
            case SENTERIAN -> senterianLevel = senterianLevel + level;
        }
    }

    public void setLevel(EnumKnowledge knowledge, int level) {
        switch(knowledge) {
            case OVERWORLD -> overworldLevel = level;
            case NETHER -> netherLevel = level;
            case END -> endLevel = level;
            case EUCA -> eucaLevel = level;
            case BOIL -> boilLevel = level;
            case FROZEN -> frozenLevel = level;
            case DEPTHS -> depthsLevel = level;
            case CORBA -> corbaLevel = level;
            case CLOUDIA -> cloudiaLevel = level;
            case TERRANIA -> terraniaLevel = level;
            case SENTERIAN -> senterianLevel = level;
        }
    }

    public void setXP(EnumKnowledge knowledge, float amount) {
        switch(knowledge) {
            case OVERWORLD -> overworldXP = amount;
            case NETHER -> netherXP = amount;
            case END -> endXP = amount;
            case EUCA -> eucaXP = amount;
            case BOIL -> boilXP = amount;
            case FROZEN -> frozenXP = amount;
            case DEPTHS -> depthsXP = amount;
            case CORBA -> corbaXP = amount;
            case CLOUDIA -> cloudiaXP = amount;
            case TERRANIA -> terraniaXP = amount;
            case SENTERIAN -> senterianXP = amount;
        }
    }

    public float getXP(EnumKnowledge knowledge) {
        float XP = 0.0F;
        switch(knowledge) {
            case OVERWORLD -> XP = overworldXP;
            case NETHER -> XP = netherXP;
            case END -> XP = endXP;
            case EUCA -> XP = eucaXP;
            case BOIL -> XP = boilXP;
            case FROZEN -> XP = frozenXP;
            case DEPTHS -> XP = depthsXP;
            case CORBA -> XP = corbaXP;
            case CLOUDIA -> XP = cloudiaXP;
            case TERRANIA -> XP = terraniaXP;
            case SENTERIAN -> XP = senterianXP;
        }
        return XP;
    }

    public void addXP(EnumKnowledge knowledge, float amount, Player player) {
        if(getXP(knowledge) + amount >= getLevelCapacity(getLevel(knowledge))) {
            setXP(knowledge, getXP(knowledge) + amount - getLevelCapacity(getLevel(knowledge)));
            addLevel(knowledge, 1);
        } else {
            setXP(knowledge, getXP(knowledge) + amount);
        }
        sendPacket(knowledge, player);
    }

    public float getTotal(EnumKnowledge knowledge) {
        float amount = 0;
        for(int i = 0; i < getLevel(knowledge); i++)
            amount += getLevelCapacity(getLevel(knowledge));

        return amount + getXP(knowledge);
    }

    public float remove(EnumKnowledge knowledge, float amount) {
        float total = getTotal(knowledge);

        if(amount > total) {
            float left = amount - total;
            setLevel(knowledge, 0);
            setXP(knowledge, 0F);
            return left;
        }

        if(getXP(knowledge) - amount < 0) {
            amount -= getXP(knowledge);

            while(amount > 0) {
                float levelCapacity = getLevelCapacity(getLevel(knowledge));
                if (levelCapacity > amount) {
                    setLevel(knowledge, getLevel(knowledge) - 1);
                    setXP(knowledge, levelCapacity - amount);
                    return 0;
                } else {
                    amount -= levelCapacity;
                    setLevel(knowledge, getLevel(knowledge) - 1);
                }
            }

            throw new IllegalStateException("This shouldn't be achieved, because if all levels' at capacity is smaller than removed amount, it should be cut in the start of the method");
        } else {
            setXP(knowledge, getXP(knowledge) - amount);
            return 0;
        }
    }

    public void sendPacket(EnumKnowledge k, Player player) {
        if(player instanceof ServerPlayer) {
            JNetworkRegistry.sendToPlayer((ServerPlayer) player, new PacketPlayerStats(hasBlizzard(), getSentacoins(), getXP(k), getLevel(k), k));
        }
    }

    public void update(Player player) {
        for(EnumKnowledge k : EnumKnowledge.values()) {
            if(getLevel(k) >= 100) {
                setLevel(k, 100);
                setXP(k, 0);
            }
            sendPacket(k, player);
        }
    }

    public boolean isCompleted(EnumKnowledge k) {
        return getLevel(k) >= 100;
    }

    public float getLevelCapacity(int level) {
        return level >= 5 ? 50 : level >= 10 ? 60 : level >= 15 ? 70 : level >= 20 ? 80 : level >= 30 ? 90 : level >= 40 ? 100 : 30;
    }

    @Override
    public void serialize(ValueOutput valueOutput) {
        valueOutput.putBoolean("hasBlizzard", this.hasBlizzard);
        valueOutput.putInt("sentacoins", this.sentacoins);

        valueOutput.putInt("overworld_level", overworldLevel);
        valueOutput.putInt("nether_level", netherLevel);
        valueOutput.putInt("end_level", endLevel);
        valueOutput.putInt("euca_level", eucaLevel);
        valueOutput.putInt("boil_level", boilLevel);
        valueOutput.putInt("frozen_level", frozenLevel);
        valueOutput.putInt("depths_level", depthsLevel);
        valueOutput.putInt("corba_level", corbaLevel);
        valueOutput.putInt("cloudia_level", cloudiaLevel);
        valueOutput.putInt("terrania_level", terraniaLevel);
        valueOutput.putInt("senterian_level", senterianLevel);

        valueOutput.putFloat("overworld_xp", overworldXP);
        valueOutput.putFloat("nether_xp", netherXP);
        valueOutput.putFloat("end_xp", endXP);
        valueOutput.putFloat("euca_xp", eucaXP);
        valueOutput.putFloat("boil_xp", boilXP);
        valueOutput.putFloat("frozen_xp", frozenXP);
        valueOutput.putFloat("depths_xp", depthsXP);
        valueOutput.putFloat("corba_xp", corbaXP);
        valueOutput.putFloat("cloudia_xp", cloudiaXP);
        valueOutput.putFloat("terrania_xp", terraniaXP);
        valueOutput.putFloat("senterian_xp", senterianXP);
    }

    @Override
    public void deserialize(ValueInput valueInput) {
        hasBlizzard = valueInput.getBooleanOr("hasBlizzard", false);
        sentacoins = valueInput.getIntOr("sentacoins", 0);

        overworldLevel = valueInput.getIntOr("overworld_level", 0);
        netherLevel = valueInput.getIntOr("nether_level", 0);
        endLevel = valueInput.getIntOr("end_level", 0);
        eucaLevel = valueInput.getIntOr("euca_level", 0);
        boilLevel = valueInput.getIntOr("boil_level", 0);
        frozenLevel = valueInput.getIntOr("frozen_level", 0);
        depthsLevel = valueInput.getIntOr("depths_level", 0);
        corbaLevel = valueInput.getIntOr("corba_level", 0);
        cloudiaLevel = valueInput.getIntOr("cloudia_level", 0);
        terraniaLevel = valueInput.getIntOr("terrania_level", 0);
        senterianLevel = valueInput.getIntOr("senterian_level", 0);

        overworldXP = valueInput.getFloatOr("overworld_xp", 0F);
        netherXP = valueInput.getFloatOr("nether_xp", 0F);
        endXP = valueInput.getFloatOr("end_xp", 0F);
        eucaXP = valueInput.getFloatOr("euca_xp", 0F);
        boilXP = valueInput.getFloatOr("boil_xp", 0F);
        frozenXP = valueInput.getFloatOr("frozen_xp", 0F);
        depthsXP = valueInput.getFloatOr("depths_xp", 0F);
        corbaXP = valueInput.getFloatOr("corba_xp", 0F);
        cloudiaXP = valueInput.getFloatOr("cloudia_xp", 0F);
        terraniaXP = valueInput.getFloatOr("terrania_xp", 0F);
        senterianXP = valueInput.getFloatOr("senterian_xp", 0F);
    }
}