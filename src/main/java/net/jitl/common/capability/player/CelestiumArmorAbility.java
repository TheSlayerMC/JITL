package net.jitl.common.capability.player;

import net.jitl.core.data.JNetworkRegistry;
import net.jitl.core.init.network.PacketCelestiumArmor;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

public class CelestiumArmorAbility implements INBTSerializable<CompoundTag> {

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

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putBoolean("jumpReady", this.jumpReady);
        nbt.putInt("cooldown", this.cooldown);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        jumpReady = nbt.getBooleanOr("jumpReady", false);
        cooldown = nbt.getIntOr("cooldown", 0);
    }

    public void sendPacket(Player player) {
        if(player != null && player instanceof ServerPlayer) {
            JNetworkRegistry.sendToPlayer((ServerPlayer)player, new PacketCelestiumArmor(getJumpReady(), getCooldown()));
        }
    }
}