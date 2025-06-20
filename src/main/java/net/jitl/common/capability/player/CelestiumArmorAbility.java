package net.jitl.common.capability.player;

import net.jitl.core.data.JNetworkRegistry;
import net.jitl.core.init.network.PacketCelestiumArmor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.util.ValueIOSerializable;

public class CelestiumArmorAbility implements ValueIOSerializable {

    private boolean jumpReady;
    private int cooldown;

    public void copyFrom(CelestiumArmorAbility source) {
        this.jumpReady = source.jumpReady;
        this.cooldown = source.cooldown;
    }

    public void setJumpReady(boolean jumpReady) {
        this.jumpReady = jumpReady;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public boolean getJumpReady() {
        return jumpReady;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void sendPacket(Player player) {
        if(player != null && player instanceof ServerPlayer) {
            JNetworkRegistry.sendToPlayer((ServerPlayer)player, new PacketCelestiumArmor(getJumpReady(), getCooldown()));
        }
    }

    @Override
    public void serialize(ValueOutput valueOutput) {
        valueOutput.putBoolean("jumpReady", this.jumpReady);
        valueOutput.putInt("cooldown", this.cooldown);
    }

    @Override
    public void deserialize(ValueInput valueInput) {
        jumpReady = valueInput.getBooleanOr("jumpReady", false);
        cooldown = valueInput.getIntOr("cooldown", 0);
    }
}