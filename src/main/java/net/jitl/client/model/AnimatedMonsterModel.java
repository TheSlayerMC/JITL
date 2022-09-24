package net.jitl.client.model;

import net.jitl.core.init.internal.JDimension;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AnimatedMonsterModel<T extends IAnimatable> extends AnimatedGeoModel<T> {

    private final String name;
    private final JDimension dim;

    public AnimatedMonsterModel(String name, JDimension dim) {
        this.dim = dim;
        this.name = name;
    }

    @Override
    public ResourceLocation getModelResource(T object) {
        return JITL.rl("geo/" + name +".json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        return JITL.rl("textures/entity/" + dim.getDim() + "/" + name +".png");
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return JITL.rl("animations/" + name + ".animation.json");
    }
}