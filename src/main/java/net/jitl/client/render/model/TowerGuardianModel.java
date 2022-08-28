package net.jitl.client.render.model;

import net.jitl.common.entity.boss.TowerGuardian;
import net.jitl.common.entity.overworld.Floro;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TowerGuardianModel extends AnimatedGeoModel<TowerGuardian> {

    @Override
    public ResourceLocation getModelResource(TowerGuardian object) {
        return JITL.rl("geo/tower_guardian.json");
    }

    @Override
    public ResourceLocation getTextureResource(TowerGuardian object) {
        return JITL.rl("textures/entity/overworld/tower_guardian.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TowerGuardian animatable) {
        return JITL.rl("animations/tower_guardian.animation.json");
    }
}
