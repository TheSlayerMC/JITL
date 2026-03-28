package net.jitl.client.model.block;

import com.geckolib.model.DefaultedEntityGeoModel;
import com.geckolib.renderer.base.GeoRenderState;
import net.jitl.common.block.entity.RockiteSpawnerEntity;
import net.jitl.core.init.JITL;
import net.minecraft.resources.Identifier;

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
