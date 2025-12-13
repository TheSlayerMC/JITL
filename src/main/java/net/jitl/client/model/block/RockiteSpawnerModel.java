package net.jitl.client.model.block;

import net.jitl.common.block.entity.RockiteSpawnerEntity;
import net.jitl.core.init.JITL;
import net.minecraft.resources.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class RockiteSpawnerModel extends DefaultedEntityGeoModel<RockiteSpawnerEntity> {

    public RockiteSpawnerModel() {
        super(Identifier.fromNamespaceAndPath(JITL.MOD_ID, "rockite_smasher"));
    }

    @Override
    public Identifier getModelResource(GeoRenderState object) {
        return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "rockite_spawner");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState object) {
        return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "textures/models/block/rockite.png");
    }

    @Override
    public Identifier getAnimationResource(RockiteSpawnerEntity animatable) {
        return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "");
    }
}
