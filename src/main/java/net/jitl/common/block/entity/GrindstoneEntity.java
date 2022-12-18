package net.jitl.common.block.entity;

import net.jitl.common.block.JGrindstoneBlock;
import net.jitl.core.init.internal.JBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class GrindstoneEntity extends BlockEntity implements GeoBlockEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public GrindstoneEntity(BlockPos pos, BlockState state) {
        super(JBlockEntities.GRINDSTONE.get(), pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, state -> {
            if(isPowered()) {
                return state.setAndContinue(RawAnimation.begin().thenPlay("animation.grindstone.working"));
            }
            else {
                return state.setAndContinue(RawAnimation.begin().thenPlay("animation.grindstone.nothing"));
            }
        }));
    }

    public boolean isPowered() {
        return getBlockState().getValue(JGrindstoneBlock.POWERED);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

}
