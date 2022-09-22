package net.jitl.common.block.entity;

import net.jitl.common.block.JGrindstoneBlock;
import net.jitl.core.init.internal.JBlockEntities;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Objects;


public class GrindstoneEntity extends BlockEntity implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    public GrindstoneEntity(BlockPos pos, BlockState state) {
        super(JBlockEntities.GRINDSTONE.get(), pos, state);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends IAnimatable> PlayState predicate(AnimationEvent<T> event) {
        if(isPowered()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.grindstone.working", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.grindstone.working", false));
        }
        return PlayState.CONTINUE;
    }

    public boolean isPowered() {
        return getBlockState().getValue(JGrindstoneBlock.POWERED);
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
