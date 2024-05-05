package net.jitl.client.model.block;

import net.jitl.common.block.entity.RockiteSpawnerEntity;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class RockiteSpawnerModel extends DefaultedEntityGeoModel<RockiteSpawnerEntity> {

    public RockiteSpawnerModel() {
        super(new ResourceLocation(JITL.MODID, "geo/rockite_smasher"));
    }

    @Override
    public ResourceLocation getModelResource(RockiteSpawnerEntity object) {
        return new ResourceLocation(JITL.MODID, "geo/rockite_spawner.geo.json");
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
