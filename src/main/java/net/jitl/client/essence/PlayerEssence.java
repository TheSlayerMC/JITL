package net.jitl.client.essence;

import net.jitl.core.data.JNetworkRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.NetworkDirection;

public class PlayerEssence {

    private int essence, maxEssence = 10, regenDelay = 150;

    public void copyFrom(PlayerEssence source) {
        this.essence = source.essence;
        this.maxEssence = source.maxEssence;
        this.regenDelay = source.regenDelay;
    }

    public int getEssence() {
        return essence;
    }

    public void setEssence(Player player, int value) {
        essence = value;
        sendPacket(player);
    }

    public void addEssence(Player player, int add) {
        setEssence(player, Math.min(getEssence() + add, getMaxEssence()));
        sendPacket(player);
    }

    public boolean consumeEssence(Player player, int price) {
        if(!player.isCreative()) {
            if(hasEssence(price)) {
                setEssence(player, getEssence() - price);
                setRegenDelay(150);
                sendPacket(player);
                return true;
            }
            return false;
        }
        return true;
    }

    public int getMaxEssence() {
        return maxEssence;
    }

    public void setMaxEssence(int max) {
        this.maxEssence = max;
    }

    public int getRegenDelay() {
        return regenDelay;
    }

    public void setRegenDelay(int delay) {
        regenDelay = delay;
    }

    public void regen(Player player) {
        if(getRegenDelay() == 0) {
            addEssence(player, 1);
            sendPacket(player);
        }
    }

    public boolean hasEssence(float price) {
        return getEssence() >= price;
    }

    public void saveNBT(CompoundTag nbt) {
        nbt.putInt("essence", this.essence);
        nbt.putInt("maxEssence", this.maxEssence);
        nbt.putInt("regenDelay", this.regenDelay);
    }

    public void readNBT(CompoundTag nbt) {
        essence = nbt.getInt("essence");
        maxEssence = nbt.getInt("maxEssence");
        regenDelay = nbt.getInt("regenDelay");
    }

    public void sendPacket(Player player) {
        if(!(player instanceof FakePlayer) && player instanceof ServerPlayer && player != null) {
            JNetworkRegistry.INSTANCE.sendTo(new PacketEssenceBar(this),((ServerPlayer) player).connection.connection, NetworkDirection.PLAY_TO_CLIENT);
        }
    }
}