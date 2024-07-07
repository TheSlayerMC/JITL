package net.jitl.common.items.gear.bloodcrust;

import net.jitl.common.items.gear.FullArmorAbility;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

public class BloodcrustFullAbility extends FullArmorAbility {

    BloodcrustFullAbility(CompoundTag nbt) {
        super(nbt);
    }

    @Override
    public void hit(LivingDamageEvent.Pre event) {
        LivingEntity entity = event.getEntity();
        int duration = entity.getRemainingFireTicks();
        System.out.println(duration);
        System.out.println(event.getOriginalDamage());
        if (duration < 600) entity.setRemainingFireTicks(Math.min(600, duration + (int) event.getOriginalDamage() * 20));
        System.out.println(entity.getRemainingFireTicks());
    }
}
