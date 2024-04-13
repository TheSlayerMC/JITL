package net.jitl.common.items.gear;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

public abstract class FullArmorAbility {
    protected CompoundTag tag;

    public FullArmorAbility(CompoundTag nbt) {
        tag = nbt;
    }

    public void tick(LivingEntity entity) {}

    public void hit(LivingDamageEvent event) {}

    public void keyPressed(LivingEntity entity) {}
}