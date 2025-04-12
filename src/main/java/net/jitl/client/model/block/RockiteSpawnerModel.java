package net.jitl.client.model.block;

import net.jitl.common.block.entity.RockiteSpawnerEntity;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class RockiteSpawnerModel extends DefaultedEntityGeoModel<RockiteSpawnerEntity> {

    public RockiteSpawnerModel() {
        super(ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "rockite_smasher"));
    }

    @Override
    public ResourceLocation getModelResource(GeoRenderState object) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "rockite_spawner");
    }

    @Override
    public ResourceLocation getTextureResource(GeoRenderState object) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "textures/models/block/rockite.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RockiteSpawnerEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "");
    }
}
