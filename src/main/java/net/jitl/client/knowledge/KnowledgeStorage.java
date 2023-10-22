package net.jitl.client.knowledge;

import net.jitl.client.gui.overlay.KnowledgeToast;
import net.jitl.core.data.JNetworkRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.PacketDistributor;

public class KnowledgeStorage {

    private float amountOnLevel = 0F;
    private int levels = 0;

    public void add(float amount, Player p, EnumKnowledge type) {
        if(amountOnLevel + amount >= getLevelCapacity(levels)) {
            amountOnLevel = (amountOnLevel + amount - getLevelCapacity(levels));
            addLevel(1, p, type);
        } else {
            amountOnLevel = amountOnLevel + amount;
        }
        sendPacket(type, p);
    }

    public void addLevel(int amount, Player p, EnumKnowledge type) {
        levels = levels + amount;
        if(!isCompleted())
            Minecraft.getInstance().getToasts().addToast(new KnowledgeToast(type, true));
        sendPacket(type, p);
    }

    public float remove(float amount) {
        float total = getTotal();

        if(amount > total) {
            float left = amount - total;

            levels = 0;
            amountOnLevel = 0F;

            return left;
        }

        if(amountOnLevel - amount < 0) {
            amount -= amountOnLevel;

            while(amount > 0) {
                float levelCapacity = getLevelCapacity(levels);
                if (levelCapacity > amount) {
                    levels = levels - 1;
                    amountOnLevel = levelCapacity - amount;
                    return 0;
                } else {
                    amount -= levelCapacity;
                    levels = levels - 1;
                }
            }

            throw new IllegalStateException("This shouldn't be achieved, because if all levels' at capacity is smaller than removed amount, it should be cut in the start of the method");
        } else {
            amountOnLevel = amountOnLevel - amount;
            return 0;
        }
    }

    public float getLevelCapacity(int level) {
        return level >= 5 ? 50 : level >= 10 ? 70 : level >= 15 ? 90 : level >= 20 ? 110 : level >= 30 ? 130 : level >= 40 ? 150 : 30; //May need balancing
    }

    public float getAmountOnCurrentLevel() {
        return amountOnLevel;
    }

    public int getLevelCount() {
        return levels;
    }

    public void setLevel(int level) {
        levels = level;
    }

    public void setXP(float xp) {
        amountOnLevel = xp;
    }

    public void saveNBT(CompoundTag tag) {
        tag.putFloat("amount_on_level", amountOnLevel);
        tag.putInt("levels", levels);
    }

    public boolean isCompleted() {
        return getLevelCount() >= 100;
    }

    public void readNBT(CompoundTag nbt) {
        amountOnLevel = nbt.getFloat("amount_on_level");
        levels = nbt.getInt("levels");
    }

    public float getTotal() {
        float amount = 0;

        for(int i = 0; i < getLevelCount(); i++) {
            amount += getLevelCapacity(getLevelCount());
        }
        return amount + getAmountOnCurrentLevel();
    }

    public void sendPacket(EnumKnowledge k, Player player) {
        if(player != null && player instanceof ServerPlayer) {
            JNetworkRegistry.INSTANCE.send(new PacketKnowledge(k,this), PacketDistributor.PLAYER.with(((ServerPlayer)player)));
        }
    }

    public void update() {
        if(getLevelCount() >= 100) {
            setLevel(100);
            setXP(0);
        }
    }

    public void copyFrom(KnowledgeStorage k) {
        amountOnLevel = k.amountOnLevel;
        levels = k.levels;
    }
}