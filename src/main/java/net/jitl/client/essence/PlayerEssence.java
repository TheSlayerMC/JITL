package net.jitl.client.essence;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class PlayerEssence {

    private int essence, maxEssence = 10;

    public void copyFrom(PlayerEssence source) {
        this.essence = source.essence;
        this.maxEssence = source.maxEssence;
    }

    public int getEssence() {
        return essence;
    }

<<<<<<< HEAD
    public int getMaxEssence() {
        return maxEssence;
    }

    public void setEssence(Player player, int value) {
=======
    public void setEssence(int value) {
>>>>>>> parent of f289f8f (hmmmm)
        if(getEssence() != value) {
            essence = value;
        }
    }

<<<<<<< HEAD
    public void addEssence(Player player, int add) {
        setEssence(player, Math.min(getEssence() + add, getMaxEssence()));
=======
    public void addEssence(int add) {
        setEssence(Math.min(getEssence() + add, 11));
>>>>>>> parent of f289f8f (hmmmm)
    }

    public boolean consumeEssence(Player player, int price) {
        if(!player.isCreative()) {
            if(hasEssence(price)) {
<<<<<<< HEAD
                setEssence(player, getEssence() - price);
                sendPacket(player);
=======
                setEssence(getEssence() - price);
>>>>>>> parent of f289f8f (hmmmm)
                return true;
            }
            return false;
        }
        return true;
    }

    public boolean hasEssence(float price) {
        return getEssence() >= price;
    }

    public void saveNBT(CompoundTag nbt) {
        nbt.putInt("essence", this.essence);
        nbt.putInt("maxEssence", this.maxEssence);
    }

    public void readNBT(CompoundTag nbt) {
        maxEssence = nbt.getInt("maxEssence");
        essence = nbt.getInt("essence");
    }
}