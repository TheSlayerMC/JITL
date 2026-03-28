package net.jitl.client.model.block;

import com.geckolib.model.DefaultedBlockGeoModel;
import com.geckolib.renderer.base.GeoRenderState;
import net.jitl.common.block.entity.ObeliskTile;
import net.jitl.core.init.JITL;
import net.minecraft.resources.Identifier;

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
