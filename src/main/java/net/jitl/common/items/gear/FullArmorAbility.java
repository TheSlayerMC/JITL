package net.jitl.common.items.gear;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

public abstract class FullArmorAbility {
    protected CompoundTag tag;

    public FullArmorAbility(CompoundTag nbt) {
        tag = nbt;
    }

    public void tick(Player player) { }

    public void hit(LivingDamageEvent.Pre event) { }

    public void keyPressed(Player player) { }
}