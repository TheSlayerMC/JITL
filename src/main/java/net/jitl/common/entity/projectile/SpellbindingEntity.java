package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JParticleManager;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class SpellbindingEntity extends AbstractHomingEntity {

    public SpellbindingEntity(EntityType<SpellbindingEntity> type, Level world) {
        super(type, world);
    }

    public SpellbindingEntity(int damage, Level world, LivingEntity thrower) {
        super(JEntities.SPELLBINDING_TYPE.get(), damage, world, thrower);
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        super.onHitEntity(result);
        EssenciaBoltEntity essenciaBoltEntity = new EssenciaBoltEntity(JEntities.ESSENCIA_BOLT_TYPE.get(), level());
        essenciaBoltEntity.setPos(result.getLocation().x(), result.getLocation().y(), result.getLocation().z());
        essenciaBoltEntity.setARGB(0xff00ff);
        this.level().addFreshEntity(essenciaBoltEntity);
    }

    @Override
    public int getHomingRadius() {
        return 4;
    }
}