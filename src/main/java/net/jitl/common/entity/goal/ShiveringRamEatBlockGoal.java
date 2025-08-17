package net.jitl.common.entity.goal;

import java.util.EnumSet;
import java.util.function.Predicate;

import net.jitl.common.entity.frozen.ShiveringRam;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.EventHooks;

public class ShiveringRamEatBlockGoal extends Goal {
    
    private static final Predicate<BlockState> IS_EDIBLE = (p_401757_) -> p_401757_.is(JBlocks.GRASSY_PERMAFROST);
    private final ShiveringRam mob;
    private final Level level;
    private int eatAnimationTick;

    public ShiveringRamEatBlockGoal(ShiveringRam mob) {
        this.mob = mob;
        this.level = mob.level();
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
    }

    public boolean canUse() {
        if (this.mob.getRandom().nextInt(this.mob.isBaby() ? 50 : 1000) != 0) {
            return false;
        } else {
            BlockPos blockpos = this.mob.blockPosition();
            return IS_EDIBLE.test(this.level.getBlockState(blockpos)) ? true : this.level.getBlockState(blockpos.below()).is(JBlocks.GRASSY_PERMAFROST);
        }
    }

    public void start() {
        this.eatAnimationTick = this.adjustedTickDelay(40);
        this.level.broadcastEntityEvent(this.mob, (byte)10);
        this.mob.getNavigation().stop();
        this.mob.setEating(true);
    }

    public void stop() {
        this.mob.setEating(false);
        this.eatAnimationTick = 0;
    }

    public boolean canContinueToUse() {
        return this.eatAnimationTick > 0;
    }

    public void tick() {
        this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
        if (this.eatAnimationTick == this.adjustedTickDelay(4)) {
            BlockPos blockpos = this.mob.blockPosition();
            if(IS_EDIBLE.test(this.level.getBlockState(blockpos))) {
                if(EventHooks.canEntityGrief(getServerLevel(this.level), this.mob)) {
                    this.level.destroyBlock(blockpos, false);
                }
                this.mob.ate();
            } else {
                BlockPos blockpos1 = blockpos.below();
                if (this.level.getBlockState(blockpos1).is(JBlocks.GRASSY_PERMAFROST)) {
                    if (EventHooks.canEntityGrief(getServerLevel(this.level), this.mob)) {
                        this.level.levelEvent(2001, blockpos1, Block.getId(JBlocks.GRASSY_PERMAFROST.get().defaultBlockState()));
                        this.level.setBlock(blockpos1, JBlocks.CRUMBLED_PERMAFROST.get().defaultBlockState(), 2);
                    }
                    this.mob.ate();
                }
            }
        }

    }
}
