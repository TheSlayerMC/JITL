package net.jitl.common.entity.frozen.tasks;

import net.jitl.common.entity.frozen.FrozenTrollEntity;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;

public class FrozenTrollStopHoldingItemIfNoLongerAdmiring {

   public static BehaviorControl<FrozenTrollEntity> create() {
      return BehaviorBuilder.create((p_259197_) -> {
         return p_259197_.group(p_259197_.absent(MemoryModuleType.ADMIRING_ITEM)).apply(p_259197_, (p_259512_) -> {
            return (p_309208_, p_309209_, p_309210_) -> {
               if (!p_309209_.getOffhandItem().isEmpty() && !p_309209_.getOffhandItem().canPerformAction(net.minecraftforge.common.ToolActions.SHIELD_BLOCK)) {
                  FrozenTrollTasks.stopHoldingOffHandItem(p_309209_, true);
                  return true;
               } else {
                  return false;
               }
            };
         });
      });
   }
}
