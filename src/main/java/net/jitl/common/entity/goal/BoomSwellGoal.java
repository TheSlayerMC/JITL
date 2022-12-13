package net.jitl.common.entity.goal;

import net.jitl.common.entity.overworld.BoomBoom;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class BoomSwellGoal extends Goal {

   private final BoomBoom BoomBoom;
   @Nullable
   private LivingEntity target;

   public BoomSwellGoal(BoomBoom pBoomBoom) {
      this.BoomBoom = pBoomBoom;
      this.setFlags(EnumSet.of(Goal.Flag.MOVE));
   }

   @Override
   public boolean canUse() {
      LivingEntity livingentity = this.BoomBoom.getTarget();
      return this.BoomBoom.getSwellDir() > 0 || livingentity != null && this.BoomBoom.distanceToSqr(livingentity) < 9.0D;
   }

   @Override
   public void start() {
      this.BoomBoom.getNavigation().stop();
      this.target = this.BoomBoom.getTarget();
   }

   @Override
   public void stop() {
      this.target = null;
   }

   @Override
   public boolean requiresUpdateEveryTick() {
      return true;
   }

   @Override
   public void tick() {
      if (this.target == null) {
         this.BoomBoom.setSwellDir(-1);
      } else if (this.BoomBoom.distanceToSqr(this.target) > 49.0D) {
         this.BoomBoom.setSwellDir(-1);
      } else if (!this.BoomBoom.getSensing().hasLineOfSight(this.target)) {
         this.BoomBoom.setSwellDir(-1);
      } else {
         this.BoomBoom.setSwellDir(1);
      }
   }
}