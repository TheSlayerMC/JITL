package net.jitl.common.items.gear.bloodcrust;

import net.jitl.common.items.gear.FullArmorAbility;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

public class BloodcrustFullAbility extends FullArmorAbility {
    BloodcrustFullAbility(CompoundTag nbt) {
        super(nbt);
    }

    @Override
    public void hit(LivingDamageEvent event) {
        System.out.println("Bloodcrust armor hit");
        Entity entity = event.getSource().getDirectEntity();
        if (entity != null) {
            int duration = entity.getRemainingFireTicks();
            System.out.println(duration);
            System.out.println(event.getAmount());
            if (duration < 600) entity.setRemainingFireTicks(Math.min(600, duration + (int) event.getAmount() * 20));
            System.out.println(entity.getRemainingFireTicks());
        }
    }
}
