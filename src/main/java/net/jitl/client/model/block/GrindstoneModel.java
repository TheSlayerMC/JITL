package net.jitl.client.model.block;

import net.jitl.common.block.entity.GrindstoneEntity;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrindstoneModel extends AnimatedGeoModel<GrindstoneEntity> {

    @Override
    public ResourceLocation getModelResource(GrindstoneEntity object) {
        return new ResourceLocation(JITL.MODID, "geo/grindstone.json");
    }

    @Override
    public ResourceLocation getTextureResource(GrindstoneEntity object) {
        return new ResourceLocation(JITL.MODID, "textures/models/block/grindstone.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GrindstoneEntity animatable) {
        return new ResourceLocation(JITL.MODID, "animations/grindstone.animation.json");
    }
}
