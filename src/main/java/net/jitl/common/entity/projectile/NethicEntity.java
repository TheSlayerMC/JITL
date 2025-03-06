package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class NethicEntity extends AbstractHomingEntity {

    public NethicEntity(EntityType<NethicEntity> type, Level world) {
        super(type, world);
    }

    public NethicEntity(int damage, Level world, LivingEntity thrower) {
        super(JEntities.NETHIC_TYPE.get(), damage, world, thrower);
        setPotionEffect(MobEffects.HARM, 60);
        setFire(60);
    }

    @Override
    public int getHomingRadius() {
        return 4;
    }
}