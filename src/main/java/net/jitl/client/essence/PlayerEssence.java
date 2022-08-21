package net.jitl.client.essence;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class PlayerEssence {

    private int essence;

    public void copyFrom(PlayerEssence source) {
        this.essence = source.essence;
    }

    public int getEssence() {
        return essence;
    }

    public void setEssence(int value) {
        if(getEssence() != value) {
            essence = value;
        }
    }

    public void addEssence(int add) {
        setEssence(Math.min(getEssence() + add, 10));
    }

    public boolean consumeEssence(Player player, int price) {
        if(!player.isCreative()) {
            if(hasEssence(price)) {
                setEssence(getEssence() - price);
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
    }

    public void readNBT(CompoundTag nbt) {
        essence = nbt.getInt("essence");
    }
}