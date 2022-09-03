package net.jitl.client.model.overworld;

import net.jitl.common.entity.overworld.Floro;

import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FloroModel extends AnimatedGeoModel<Floro> {

    @Override
    public ResourceLocation getModelResource(Floro object) {
        return JITL.rl("geo/floro.json");
    }

    @Override
    public ResourceLocation getTextureResource(Floro object) {
        return JITL.rl("textures/entity/overworld/floro.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Floro animatable) {
        return JITL.rl("animations/floro.animation.json");
    }
}
