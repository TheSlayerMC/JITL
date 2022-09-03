package net.jitl.client.model.boss;

import net.jitl.common.entity.boss.RockiteSmasher;
import net.jitl.common.entity.boss.TowerGuardian;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RockiteSmasherModel extends AnimatedGeoModel<RockiteSmasher> {

    @Override
    public ResourceLocation getModelResource(RockiteSmasher object) {
        return JITL.rl("geo/rockite_smasher.json");
    }

    @Override
    public ResourceLocation getTextureResource(RockiteSmasher object) {
        return JITL.rl("textures/entity/overworld/rockite_smasher.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RockiteSmasher animatable) {
        return JITL.rl("animations/rockite_smasher.animation.json");
    }
}
