package net.jitl.common.entity.goal;

import java.util.EnumSet;
import javax.annotation.Nullable;

import net.jitl.common.entity.frozen.Shiverwolf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class ShiverwolfBegGoal extends Goal {
    private final Shiverwolf wolf;
    @Nullable
    private Player player;
    private final Level level;
    private final float lookDistance;
    private int lookTime;

    public ShiverwolfBegGoal(Shiverwolf wolf, float lookDistance) {
        this.wolf = wolf;
        this.level = wolf.level();
        this.lookDistance = lookDistance;
        this.setFlags(EnumSet.of(Flag.LOOK));
    }

    public boolean canUse() {
        this.player = this.level.getNearestPlayer(this.wolf, this.lookDistance);
        return this.player != null && this.playerHoldingInteresting(this.player);
    }

    public boolean canContinueToUse() {
        assert this.player != null;
        if (!this.player.isAlive()) {
            return false;
        } else {
            return !(this.wolf.distanceToSqr(this.player) > (double) (this.lookDistance * this.lookDistance)) && this.lookTime > 0 && this.playerHoldingInteresting(this.player);
        }
    }

    public void start() {
        this.wolf.setIsInterested(true);
        this.lookTime = this.adjustedTickDelay(40 + this.wolf.getRandom().nextInt(40));
    }

    public void stop() {
        this.wolf.setIsInterested(false);
        this.player = null;
    }

    public void tick() {
        assert this.player != null;
        this.wolf.getLookControl().setLookAt(this.player.getX(), this.player.getEyeY(), this.player.getZ(), 10.0F, (float)this.wolf.getMaxHeadXRot());
        --this.lookTime;
    }

    private boolean playerHoldingInteresting(Player player) {
        InteractionHand[] var2 = InteractionHand.values();
        for(InteractionHand interactionhand : var2) {
            ItemStack itemstack = player.getItemInHand(interactionhand);
            if (itemstack.is(Items.BONE) || this.wolf.isFood(itemstack)) {
                return true;
            }
        }

        return false;
    }
}
