package net.jitl.client.model.block;

import net.jitl.common.block.entity.ObeliskTile;
import net.jitl.core.init.JITL;
import net.minecraft.resources.Identifier;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class ObeliskModel extends DefaultedBlockGeoModel<ObeliskTile> {

    public ObeliskModel() {
        super(Identifier.fromNamespaceAndPath(JITL.MOD_ID, "obelisk"));
    }

    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "obelisk");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState object) {
        return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "textures/models/block/obelisk.png");
    }

    @Override
    public Identifier getAnimationResource(ObeliskTile animatable) {
        return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "obelisk");
    }
}
