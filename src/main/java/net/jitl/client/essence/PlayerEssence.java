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
    }

    public int getEssence() {
        return essence;
    }

    public int getMaxEssence() {
        return maxEssence;
    }

    public void setEssence(Player player, int value) {
        essence = value;
        sendPacket(player);
    }

    public void addEssence(Player player, int add) {
        essence += add;
        if(essence > maxEssence) essence = maxEssence;
        sendPacket(player);
    }

    public boolean consumeEssence(Player player, int price) {
        if(!player.isCreative()) {
            if(getEssence() < price)
                return false;
            essence -= price;
            sendPacket(player);
            return true;
        }
        return true;
    }

    public void update(Player player) {
        if(essence > maxEssence) essence = maxEssence;
        sendPacket(player);
    }

    public void regen() {
        System.out.println("REGEN");
        essence += 1;
    }

    public void saveNBT(CompoundTag nbt) {
        nbt.putInt("essence", this.essence);
    }

    public void readNBT(CompoundTag nbt) {
        essence = nbt.getInt("essence");
    }

    public void sendPacket(Player player) {
        if(!(player instanceof FakePlayer) && player instanceof ServerPlayer && player != null) {
            JNetworkRegistry.INSTANCE.sendTo(new PacketEssenceBar(this),((ServerPlayer) player).connection.connection, NetworkDirection.PLAY_TO_CLIENT);
        }
    }
}