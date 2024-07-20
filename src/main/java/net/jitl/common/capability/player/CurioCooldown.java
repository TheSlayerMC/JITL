package net.jitl.common.capability.player;

import net.jitl.core.data.JNetworkRegistry;
import net.jitl.core.init.network.PacketCelestiumArmor;
import net.jitl.core.init.network.PacketCuriosCooldown;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

public class CurioCooldown implements INBTSerializable<CompoundTag> {

    private int cooldown;

    public void copyFrom(CurioCooldown source) {
        this.cooldown = source.cooldown;
    }
    
    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getCooldown() {
        return cooldown;
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("cooldown", this.cooldown);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        cooldown = nbt.getInt("cooldown");
    }

    public void sendPacket(Player player) {
        if(player != null && player instanceof ServerPlayer) {
            JNetworkRegistry.sendToPlayer((ServerPlayer)player, new PacketCuriosCooldown(getCooldown()));
        }
    }
}