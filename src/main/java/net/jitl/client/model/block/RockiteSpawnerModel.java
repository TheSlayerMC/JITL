package net.jitl.client.model.block;

import net.jitl.common.block.entity.RockiteSpawnerEntity;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RockiteSpawnerModel extends AnimatedGeoModel<RockiteSpawnerEntity> {

    @Override
    public ResourceLocation getModelResource(RockiteSpawnerEntity object) {
        return new ResourceLocation(JITL.MODID, "geo/rockite_smasher.json");
    }

    @Override
    public ResourceLocation getTextureResource(RockiteSpawnerEntity object) {
        return new ResourceLocation(JITL.MODID, "textures/models/block/rockite.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RockiteSpawnerEntity animatable) {
        return new ResourceLocation(JITL.MODID, "");
    }
}
