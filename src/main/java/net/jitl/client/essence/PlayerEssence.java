package net.jitl.client.essence;

import net.jitl.core.data.JNetworkRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.PacketDistributor;

public class PlayerEssence {

    private int essence;

    public void copyFrom(PlayerEssence source) {
        this.essence = source.essence;
    }

    public int getEssence() {
        return essence;
    }

    public int getMaxEssence() {
        return 8;
    }

    public void setEssence(int value) {
        essence = value;
    }

    public void addEssence(Player player, int add) {
        setEssence(getEssence() + add);
        if(getEssence() > getMaxEssence()) setEssence(getMaxEssence());
        sendPacket(player);
    }

    public boolean consumeEssence(Player player, int price) {
        if(!player.isCreative()) {
            if(getEssence() < price)
                return false;
            setEssence(getEssence() - price);
            sendPacket(player);
            return true;
        }
        return true;
    }

    public void update(Player player) {
        if(getEssence() >= getMaxEssence()) setEssence(getMaxEssence());
        sendPacket(player);
    }

    public void regen() {
        setEssence(getEssence() + 1);
    }

    public void saveNBT(CompoundTag nbt) {
        nbt.putInt("essence", this.essence);
    }

    public void readNBT(CompoundTag nbt) {
        essence = nbt.getInt("essence");
    }

    public void sendPacket(Player player) {
        if(!(player instanceof FakePlayer) && player instanceof ServerPlayer) {
            JNetworkRegistry.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)player), new PacketEssenceBar(this));
        }
    }
}