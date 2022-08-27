package net.jitl.client.essence;

import net.jitl.core.data.JNetworkRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.NetworkDirection;

public class PlayerEssence {

    private int essence, maxEssence = 10;

    public void copyFrom(PlayerEssence source) {
        this.essence = source.essence;
        this.maxEssence = source.maxEssence;
    }

    public int getEssence() {
        return essence;
    }

    public int getMaxEssence() {
        return maxEssence;
    }

    public void setEssence(Player player, int value) {
        if(getEssence() != value) {
            essence = value;
            sendPacket(player);
        }
    }

    public void addEssence(Player player, int add) {
        setEssence(player, Math.min(getEssence() + add, getMaxEssence()));
    }

    public boolean consumeEssence(Player player, int price) {
        if(!player.isCreative()) {
            if(hasEssence(price)) {
                setEssence(player, getEssence() - price);
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

    public void sendPacket(Player player) {
        if(!(player instanceof FakePlayer) && player instanceof ServerPlayer && player != null) {
            JNetworkRegistry.INSTANCE.sendTo(new PacketEssenceBar(this),((ServerPlayer) player).connection.connection, NetworkDirection.PLAY_TO_CLIENT);
        }
    }
}