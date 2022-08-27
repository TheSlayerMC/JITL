package net.jitl.client.essence;

import net.jitl.core.data.JNetworkRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.PacketDistributor;

public class PlayerEssence {

    private int essence;
    private final int maxEssence = 10;

    public void copyFrom(PlayerEssence source) {
        this.essence = source.essence;
    }

    public int getEssence() {
        return essence;
    }

    public int getMaxEssence() {
        return maxEssence;
    }

    public void setEssence(Player player, int value) {
        essence = value;
        //sendPacket(player);
    }

    public void addEssence(Player player, int add) {
        setEssence(player, getEssence() + add);
        if(getEssence() > getMaxEssence()) setEssence(player, getMaxEssence());
        //sendPacket(player);
    }

    public boolean consumeEssence(Player player, int price) {
        if(!player.isCreative()) {
            if(getEssence() < price)
                return false;
            setEssence(player, getEssence() - price);
            //sendPacket(player);
            return true;
        }
        return true;
    }

    public void update(Player player) {
        if(getEssence() > getMaxEssence()) setEssence(player, getMaxEssence());
       // sendPacket(player);
    }

    public void regen(Player player) {
        setEssence(player, getEssence() + 1);
    }

    public void saveNBT(CompoundTag nbt) {
        nbt.putInt("essence", this.essence);
    }

    public void readNBT(CompoundTag nbt) {
        essence = nbt.getInt("essence");
    }
}