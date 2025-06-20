package net.jitl.common.capability.player;

import net.jitl.core.data.JNetworkRegistry;
import net.jitl.core.init.network.PacketItemCooldown;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.util.ValueIOSerializable;

public class ItemCooldown implements ValueIOSerializable {

    private int cooldown;

    public void copyFrom(ItemCooldown source) {
        this.cooldown = source.cooldown;
    }
    
    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void sendPacket(Player player) {
        if(player instanceof ServerPlayer) {
            JNetworkRegistry.sendToPlayer((ServerPlayer)player, new PacketItemCooldown(getCooldown()));
        }
    }

    @Override
    public void serialize(ValueOutput valueOutput) {
        valueOutput.putInt("cooldown", this.cooldown);
    }

    @Override
    public void deserialize(ValueInput valueInput) {
        cooldown = valueInput.getIntOr("cooldown", 0);
    }
}