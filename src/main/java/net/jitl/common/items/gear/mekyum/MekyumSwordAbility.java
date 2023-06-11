package net.jitl.common.items.gear.mekyum;

import net.jitl.common.items.gear.IAbility;
import net.jitl.core.init.JITL;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class MekyumSwordAbility implements IAbility {

    @Override
    public void attackTarget(LivingEntity attacker, ItemStack stack, LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        int time = entity.invulnerableTime;
        event.setCanceled(true);
        entity.invulnerableTime = 0;
        entity.hurt(entity.level().damageSources().magic(), event.getAmount());
        entity.invulnerableTime = time;
        JITL.LOGGER.info(time);
    }
}