package net.jitl.common.capability.essence;

import net.jitl.core.data.JNetworkRegistry;
import net.jitl.core.init.internal.JAttributes;
import net.jitl.core.init.network.PacketEssenceBar;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.Objects;

public class PlayerEssence implements INBTSerializable<CompoundTag> {
    private float currentEssence;
    private float burnoutTime;
    private int timeout;

    public void copyFrom(PlayerEssence source) {
        this.currentEssence = source.currentEssence;
    }

    public float getCurrentEssence() {
        return currentEssence;
    }

    public static float getMaxEssence(Player player) {
        return (float) Objects.requireNonNull(player.getAttribute(JAttributes.MAX_ESSENCE)).getValue();
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
                setTimeout(15);
                return true;
            }
            float attributeValue = (float)Objects.requireNonNull(player.getAttribute(JAttributes.ESSENCE_BURNOUT)).getValue();
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

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putFloat("essence", this.currentEssence);
        nbt.putFloat("burnoutTime", this.burnoutTime);
        nbt.putInt("timeout", this.timeout);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        currentEssence = nbt.getFloatOr("essence", 0F);
        burnoutTime = nbt.getFloatOr("burnoutTime", 0F);
        timeout = nbt.getIntOr("timeout", 0);
    }

    public void sendPacket(Player player) {
        if(player != null && player instanceof ServerPlayer) {
            JNetworkRegistry.sendToPlayer((ServerPlayer)player, new PacketEssenceBar(getCurrentEssence(), getBurnout()));
        }
    }
}