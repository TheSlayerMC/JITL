package net.jitl.client.stats;

import net.jitl.core.data.JNetworkRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.PacketDistributor;

public class PlayerStats {

    private boolean hasBlizzard;

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

    public void sendPacket(Player player) {
        if(!(player instanceof FakePlayer) && player instanceof ServerPlayer) {
            JNetworkRegistry.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)player), new PacketPlayerStats(this));
        }
    }

    public void update(Player player) {
        sendPacket(player);
    }

    public void saveNBT(CompoundTag nbt) {
        nbt.putBoolean("hasBlizzard", this.hasBlizzard);
    }

    public void readNBT(CompoundTag nbt) {
        hasBlizzard = nbt.getBoolean("hasBlizzard");
    }
}