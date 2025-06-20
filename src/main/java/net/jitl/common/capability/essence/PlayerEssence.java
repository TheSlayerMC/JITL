package net.jitl.common.capability.essence;

import net.jitl.core.data.JNetworkRegistry;
import net.jitl.core.init.internal.JAttributes;
import net.jitl.core.init.network.PacketEssenceBar;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.util.ValueIOSerializable;

import java.util.Objects;

public class PlayerEssence implements ValueIOSerializable {
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

    public void sendPacket(Player player) {
        if(player != null && player instanceof ServerPlayer) {
            JNetworkRegistry.sendToPlayer((ServerPlayer)player, new PacketEssenceBar(getCurrentEssence(), getBurnout()));
        }
    }

    @Override
    public void serialize(ValueOutput valueOutput) {
        valueOutput.putFloat("essence", this.currentEssence);
        valueOutput.putFloat("burnoutTime", this.burnoutTime);
        valueOutput.putInt("timeout", this.timeout);
    }

    @Override
    public void deserialize(ValueInput valueInput) {
        currentEssence = valueInput.getFloatOr("essence", 0F);
        burnoutTime = valueInput.getFloatOr("burnoutTime", 0F);
        timeout = valueInput.getIntOr("timeout", 0);
    }
}