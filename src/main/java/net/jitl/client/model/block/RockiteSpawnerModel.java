package net.jitl.client.model.block;

import net.jitl.common.block.entity.RockiteSpawnerEntity;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class RockiteSpawnerModel extends DefaultedEntityGeoModel<RockiteSpawnerEntity> {

    public RockiteSpawnerModel() {
        super(ResourceLocation.fromNamespaceAndPath(JITL.MODID, "geo/rockite_smasher"));
    }

    @Override
    public ResourceLocation getModelResource(RockiteSpawnerEntity object, GeoRenderer<RockiteSpawnerEntity> renderer) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MODID, "geo/rockite_spawner.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RockiteSpawnerEntity object, GeoRenderer<RockiteSpawnerEntity> renderer) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MODID, "textures/models/block/rockite.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RockiteSpawnerEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MODID, "");
    }
}
