package net.jitl.client.model.overworld;

import net.jitl.common.entity.overworld.npc.Mage;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MageModel extends AnimatedGeoModel<Mage> {

    @Override
    public ResourceLocation getModelResource(Mage object) {
        return JITL.rl("geo/mage.json");
    }

    @Override
    public ResourceLocation getTextureResource(Mage object) {
        return JITL.rl("textures/entity/overworld/mage.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Mage animatable) {
        return JITL.rl("animations/mage.animation.json");
    }
}
