package net.jitl.common.entity.base;

import net.minecraft.world.entity.LivingEntity;

public interface IDontAttackWhenPeaceful {

    boolean wantsToAttack(LivingEntity target, LivingEntity living);

}