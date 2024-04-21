package net.jitl.common.capability.stats;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.client.stats.PacketPlayerStats;
import net.jitl.core.data.JNetworkRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

public class PlayerStats implements INBTSerializable<CompoundTag> {

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

    public void useSentacoins(int amount) {
        sentacoins -= amount;
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
    public @UnknownNullability CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("hasBlizzard", this.hasBlizzard);
        tag.putInt("sentacoins", this.sentacoins);

        tag.putInt("overworld_level", overworldLevel);
        tag.putInt("nether_level", netherLevel);
        tag.putInt("end_level", endLevel);
        tag.putInt("euca_level", eucaLevel);
        tag.putInt("boil_level", boilLevel);
        tag.putInt("frozen_level", frozenLevel);
        tag.putInt("depths_level", depthsLevel);
        tag.putInt("corba_level", corbaLevel);
        tag.putInt("cloudia_level", cloudiaLevel);
        tag.putInt("terrania_level", terraniaLevel);
        tag.putInt("senterian_level", senterianLevel);

        tag.putFloat("overworld_xp", overworldXP);
        tag.putFloat("nether_xp", netherXP);
        tag.putFloat("end_xp", endXP);
        tag.putFloat("euca_xp", eucaXP);
        tag.putFloat("boil_xp", boilXP);
        tag.putFloat("frozen_xp", frozenXP);
        tag.putFloat("depths_xp", depthsXP);
        tag.putFloat("corba_xp", corbaXP);
        tag.putFloat("cloudia_xp", cloudiaXP);
        tag.putFloat("terrania_xp", terraniaXP);
        tag.putFloat("senterian_xp", senterianXP);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        hasBlizzard = tag.getBoolean("hasBlizzard");
        sentacoins = tag.getInt("sentacoins");

        overworldLevel = tag.getInt("overworld_level");
        netherLevel = tag.getInt("nether_level");
        endLevel = tag.getInt("end_level");
        eucaLevel = tag.getInt("euca_level");
        boilLevel = tag.getInt("boil_level");
        frozenLevel = tag.getInt("frozen_level");
        depthsLevel = tag.getInt("depths_level");
        corbaLevel = tag.getInt("corba_level");
        cloudiaLevel = tag.getInt("cloudia_level");
        terraniaLevel = tag.getInt("terrania_level");
        senterianLevel = tag.getInt("senterian_level");

        overworldXP = tag.getFloat("overworld_xp");
        netherXP = tag.getFloat("nether_xp");
        endXP = tag.getFloat("end_xp");
        eucaXP = tag.getFloat("euca_xp");
        boilXP = tag.getFloat("boil_xp");
        frozenXP = tag.getFloat("frozen_xp");
        depthsXP = tag.getFloat("depths_xp");
        corbaXP = tag.getFloat("corba_xp");
        cloudiaXP = tag.getFloat("cloudia_xp");
        terraniaXP = tag.getFloat("terrania_xp");
        senterianXP = tag.getFloat("senterian_xp");
    }
}