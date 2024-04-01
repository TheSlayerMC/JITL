package net.jitl.common.capability.stats;

import net.jitl.client.stats.PacketPlayerStats;
import net.jitl.core.data.JNetworkRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkDirection;

public class PlayerStats {

    private boolean hasBlizzard;
    private int sentacoins;

    public void copyFrom(PlayerStats stats) {
        this.hasBlizzard = stats.hasBlizzard;
    }

    public boolean hasBlizzard() {
        return hasBlizzard;
    }

    public void setBlizzard(boolean blizzard) {
        this.hasBlizzard = blizzard;
    }

    public void removeBlizzard() {
        this.hasBlizzard = false;
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

    public void sendPacket(Player player) {
        if(player != null && player instanceof ServerPlayer) {
            JNetworkRegistry.INSTANCE.sendTo(new PacketPlayerStats(this), ((ServerPlayer) player).connection.connection, NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    public void update(Player player) {
        sendPacket(player);
    }

    public void saveNBT(CompoundTag nbt) {
        nbt.putBoolean("hasBlizzard", this.hasBlizzard);
        nbt.putInt("sentacoins", this.sentacoins);
    }

    public void readNBT(CompoundTag nbt) {
        hasBlizzard = nbt.getBoolean("hasBlizzard");
        sentacoins = nbt.getInt("sentacoins");
    }
}