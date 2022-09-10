package net.jitl.client.knowledge;

import net.jitl.client.gui.overlay.KnowledgeToast;
import net.jitl.core.data.JNetworkRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.PacketDistributor;

public class PlayerKnowledge {

    private float overworldXP = 0F, endXP = 0F, netherXP = 0F;
    private int overworldLevel = 0, endLevel = 0, netherLevel = 0;

    public void addXP(EnumKnowledge type, float amount) {
        switch(type) {
            case OVERWORLD -> {
                if (overworldXP + amount >= getLevelCapacity(overworldLevel)) {
                    overworldXP = (overworldXP + amount - getLevelCapacity(overworldLevel));
                    addLevel(1, type);
                } else {
                    overworldXP = overworldXP + amount;
                }
            }
            case END -> {
                if (endXP + amount >= getLevelCapacity(endLevel)) {
                    endXP = (endXP + amount - getLevelCapacity(endLevel));
                    addLevel(1, type);
                } else {
                    endXP = endXP + amount;
                }
            }

            case NETHER -> {
                if (netherXP + amount >= getLevelCapacity(netherLevel)) {
                    netherXP = (netherXP + amount - getLevelCapacity(netherLevel));
                    addLevel(1, type);
                } else {
                    netherXP = netherXP + amount;
                }
            }
        }
    }

    public void addLevel(int amount, EnumKnowledge type) {
        switch(type) {
            case OVERWORLD -> {
                overworldLevel = overworldLevel + amount;
            }

            case END -> {
                endLevel = endLevel + amount;
            }

            case NETHER -> {
                netherLevel = netherLevel + amount;
            }
        }
        Minecraft.getInstance().getToasts().addToast(new KnowledgeToast(type, true));
    }

    public float getLevelCapacity(int level) {
        return level >= 5 ? 50 : level >= 10 ? 70 : level >= 15 ? 90 : level >= 20 ? 110 : level >= 30 ? 130 : level >= 40 ? 150 : 30; //May need balancing
    }

    public float getXP(EnumKnowledge knowledge) {
        switch(knowledge) {
            case OVERWORLD -> {
                return overworldXP;
            }
            case NETHER -> {
                return netherXP;
            }
            case END -> {
                return endXP;
            }
            case EUCA -> {
                return 0;
            }
            case BOIL -> {
                return 0;
            }
            case FROZEN -> {
                return 0;
            }
            case DEPTHS -> {
                return 0;
            }
            case CORBA -> {
                return 0;
            }
            case CLOUDIA -> {
                return 0;
            }
            case TERRANIA -> {
                return 0;
            }
            case SENTERIAN -> {
                return 0;
            }
        }
        return 0F;
    }

    public int getLevel(EnumKnowledge knowledge) {
        switch(knowledge) {
            case OVERWORLD -> {
                return overworldLevel;
            }
            case NETHER -> {
                return netherLevel;
            }
            case END -> {
                return endLevel;
            }
            case EUCA -> {
                return 0;
            }
            case BOIL -> {
                return 0;
            }
            case FROZEN -> {
                return 0;
            }
            case DEPTHS -> {
                return 0;
            }
            case CORBA -> {
                return 0;
            }
            case CLOUDIA -> {
                return 0;
            }
            case TERRANIA -> {
                return 0;
            }
            case SENTERIAN -> {
                return 0;
            }
        }
        return 0;
    }

    public void setLevel(EnumKnowledge knowledge, int level) {
        switch(knowledge) {
            case OVERWORLD -> {
                overworldLevel = level;
            }
            case NETHER -> {
                netherLevel = level;
            }
            case END -> {
                endLevel = level;
            }
            case EUCA -> {

            }
            case BOIL -> {

            }
            case FROZEN -> {

            }
            case DEPTHS -> {

            }
            case CORBA -> {

            }
            case CLOUDIA -> {

            }
            case TERRANIA -> {

            }
            case SENTERIAN -> {

            }
        }
    }

    public void setXP(EnumKnowledge knowledge, float xp) {
        switch(knowledge) {
            case OVERWORLD -> {
                overworldXP = xp;
            }
            case NETHER -> {
                netherXP = xp;
            }
            case END -> {
                endXP = xp;
            }
            case EUCA -> {

            }
            case BOIL -> {

            }
            case FROZEN -> {

            }
            case DEPTHS -> {

            }
            case CORBA -> {

            }
            case CLOUDIA -> {

            }
            case TERRANIA -> {

            }
            case SENTERIAN -> {

            }
        }
    }

    public void saveNBT(CompoundTag nbt) {
        nbt.putFloat("overworldXP", overworldXP);
        nbt.putInt("overworldLevel", overworldLevel);

        nbt.putFloat("endXP", endXP);
        nbt.putInt("endLevel", endLevel);

        nbt.putFloat("netherXP", netherXP);
        nbt.putInt("netherLevel", netherLevel);
    }

    public void readNBT(CompoundTag nbt) {
        overworldXP = nbt.getFloat("overworldXP");
        overworldLevel = nbt.getInt("overworldLevel");

        endXP = nbt.getFloat("endXP");
        endLevel = nbt.getInt("endLevel");

        netherXP = nbt.getFloat("netherXP");
        netherLevel = nbt.getInt("netherLevel");
    }

    public void update(Player p) {
        for(EnumKnowledge k : EnumKnowledge.values()) {
            sendPacket(k, p);
        }
    }

    public void sendPacket(EnumKnowledge k, Player player) {
        if(!(player instanceof FakePlayer) && player instanceof ServerPlayer) {
            JNetworkRegistry.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)player), new PacketKnowledge(k,this));
        }
    }

    public void copyFrom(PlayerKnowledge oldStore) {
        overworldXP = oldStore.overworldXP;
        overworldLevel = oldStore.overworldLevel;

        endXP = oldStore.endXP;
        endLevel = oldStore.endLevel;

        netherXP = oldStore.netherXP;
        netherLevel = oldStore.netherLevel;
    }
}