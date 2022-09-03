package net.jitl.client.model.nether;

import net.jitl.common.entity.nether.Witherspine;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WitherspineModel extends AnimatedGeoModel<Witherspine> {

    @Override
    public ResourceLocation getModelResource(Witherspine object) {
        return JITL.rl("geo/witherspine.json");
    }

    @Override
    public ResourceLocation getTextureResource(Witherspine object) {
        return JITL.rl("textures/entity/nether/witherspine.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Witherspine animatable) {
        return JITL.rl("animations/witherspine.animation.json");
    }
}