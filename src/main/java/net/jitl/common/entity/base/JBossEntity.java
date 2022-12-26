package net.jitl.common.entity.base;

import net.jitl.common.entity.IJourneyBoss;
import net.jitl.common.entity.boss.BossCrystal;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public abstract class JBossEntity extends AnimatableMonster implements IJourneyBoss, IDontAttackWhenPeaceful{

    protected JBossEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public boolean wantsToAttack(LivingEntity target, LivingEntity living) {
        return level.getDifficulty() != Difficulty.PEACEFUL;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JSounds.BOSS_DEATH.get();
    }

    @Override
    public float getSoundVolume() {
        return 10.0F;
    }

    @Nullable
    protected abstract BossCrystal.Type getDeathCrystalType();

    @Override
    public void tickDeath() {
        super.tickDeath();
        if(!level.isClientSide()) {
            BossCrystal.Type crystalType = getDeathCrystalType();
            if(crystalType != null) {
                ResourceLocation lootTable = getLootTable();
                BossCrystal crystal;
                crystal = BossCrystal.create((ServerLevel)level, blockPosition(), getDeathCrystalType(), null, lootTable, 0L);
                level.addFreshEntity(crystal);
            }
        }
    }
}
