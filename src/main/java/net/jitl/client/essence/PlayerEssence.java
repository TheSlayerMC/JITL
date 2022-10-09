package net.jitl.client.essence;

import net.jitl.core.data.JNetworkRegistry;
import net.jitl.core.init.internal.JAttributes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.PacketDistributor;

import java.util.Objects;

public class PlayerEssence {

    private float currentEssence;
    private float burnoutTime;
    private int timeout;

    public void copyFrom(PlayerEssence source) {
        this.currentEssence = source.currentEssence;
    }

    public float getCurrentEssence() {
        return currentEssence;
    }

    public float getMaxEssence(Player player) {
        return (float) Objects.requireNonNull(player.getAttribute(JAttributes.MAX_ESSENCE.get())).getValue();
    }

    public void setBurnout(float value) {
        burnoutTime = (Math.max(value, 0.0F));
    }

    public void setTimeout(int value) {
        timeout = value;
    }

    public float getBurnout() {
        return burnoutTime;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setEssence(float value) {
        if (getCurrentEssence() != value) {
            currentEssence = value;
        }
    }

    public boolean isRegenReady() {
        if (getTimeout() <= 0) return getBurnout() <= 0;
        setTimeout(getTimeout() - 1);
        return false;
    }

    public void addEssence(Player player, float add) {
        setEssence(Math.min(getCurrentEssence() + add, getMaxEssence(player)));
    }

    public boolean consumeEssence(Player player, float price) {
        if (!player.isCreative()) {
            if (hasEssence(price)) {
                setEssence(getCurrentEssence() - price);
                setTimeout(20);
                return true;
            }
            float attributeValue = (float) player.getAttribute(JAttributes.ESSENCE_BURNOUT.get()).getValue();
            setBurnout(Math.min(getBurnout() + attributeValue, attributeValue * 5));
            return false;
        }
        return true;
    }

    public boolean hasEssence(float price) {
        return getCurrentEssence() >= price;
    }

    public boolean checkEssenceEitherSide(boolean client, Player player, float price) {
        if (client) {
            return player.isCreative() || hasEssence(price);
        }
        return consumeEssence(player, price);
    }

    public void saveNBT(CompoundTag nbt) {
        nbt.putFloat("essence", this.currentEssence);
        nbt.putFloat("burnoutTime", this.burnoutTime);
        nbt.putInt("timeout", this.timeout);
    }

    public void readNBT(CompoundTag nbt) {
        currentEssence = nbt.getFloat("essence");
        burnoutTime = nbt.getFloat("burnoutTime");
        timeout = nbt.getInt("timeout");
    }

    public void sendPacket(Player player) {
        if(!(player instanceof FakePlayer) && player instanceof ServerPlayer) {
            JNetworkRegistry.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)player), new PacketEssenceBar(this));
        }
    }
}