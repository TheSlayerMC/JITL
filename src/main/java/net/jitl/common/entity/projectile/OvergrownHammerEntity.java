package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class OvergrownHammerEntity extends AbstractHomingEntity {

    public OvergrownHammerEntity(EntityType<OvergrownHammerEntity> type, Level world) {
        super(type, world);
    }

    public OvergrownHammerEntity(int damage, Level world, LivingEntity thrower) {
        super(JEntities.OVERGROWN_HAMMER_TYPE.get(), damage, world, thrower);
        setPotionEffect(MobEffects.BLINDNESS, 100);
    }

    @Override
    public int getHomingRadius() {
        return 4;
    }
}