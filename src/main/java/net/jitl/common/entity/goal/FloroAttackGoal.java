package net.jitl.common.entity.goal;

import net.jitl.common.entity.overworld.Floro;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class FloroAttackGoal extends Goal {

    private final Mob mob;
    private final Floro rangedAttackMob;
    @Nullable
    private LivingEntity target;
    private int attackTime = -1;
    private final double speedModifier;
    private int seeTime;
    private final int attackIntervalMin;
    private final int attackIntervalMax;
    private final float attackRadius;
    private final float attackRadiusSqr;

    public FloroAttackGoal(Floro pRangedAttackMob, double pSpeedModifier, int pAttackInterval, float pAttackRadius) {
        this(pRangedAttackMob, pSpeedModifier, pAttackInterval, pAttackInterval, pAttackRadius);
    }

    public FloroAttackGoal(Floro pRangedAttackMob, double pSpeedModifier, int pAttackIntervalMin, int pAttackIntervalMax, float pAttackRadius) {
        if (pRangedAttackMob == null) {
            throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
        } else {
            this.rangedAttackMob = pRangedAttackMob;
            this.mob = pRangedAttackMob;
            this.speedModifier = pSpeedModifier;
            this.attackIntervalMin = pAttackIntervalMin;
            this.attackIntervalMax = pAttackIntervalMax;
            this.attackRadius = pAttackRadius;
            this.attackRadiusSqr = pAttackRadius * pAttackRadius;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }
    }

    public boolean canUse() {
        LivingEntity livingentity = this.mob.getTarget();
        if (livingentity != null && livingentity.isAlive() && !rangedAttackMob.isHidden()) {
            this.target = livingentity;
            return true;
        } else {
            return false;
        }
    }

    public boolean canContinueToUse() {
        return this.canUse() || this.target.isAlive() && !this.mob.getNavigation().isDone();
    }

    public void stop() {
        this.target = null;
        this.seeTime = 0;
        this.attackTime = -1;
        rangedAttackMob.setHidden(true);
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        double d0 = this.mob.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
        boolean flag = this.mob.getSensing().hasLineOfSight(this.target);
        if (flag) {
            ++this.seeTime;
        } else {
            this.seeTime = 0;
        }

        if (!(d0 > (double)this.attackRadiusSqr) && this.seeTime >= 5) {
            this.mob.getNavigation().stop();
        } else {
            this.mob.getNavigation().moveTo(this.target, this.speedModifier);
        }

        this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
        if (--this.attackTime == 0) {
            if (!flag) {
                return;
            }

            float f = (float)Math.sqrt(d0) / this.attackRadius;
            float f1 = Mth.clamp(f, 0.1F, 1.0F);
            this.rangedAttackMob.performRangedAttack(this.target, f1);
            rangedAttackMob.setShooting(true);
            this.attackTime = Mth.floor(f * (float)(this.attackIntervalMax - this.attackIntervalMin) + (float)this.attackIntervalMin);
        } else if (this.attackTime < 0) {
            this.attackTime = Mth.floor(Mth.lerp(Math.sqrt(d0) / (double)this.attackRadius, (double)this.attackIntervalMin, (double)this.attackIntervalMax));
            rangedAttackMob.setShooting(false);
        }
    }
}
