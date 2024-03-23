package net.jitl.client.render.block;

import net.jitl.client.model.block.ObeliskModel;
import net.jitl.common.block.entity.ObeliskTile;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ObeliskRenderer extends GeoBlockRenderer<ObeliskTile> {

    public ObeliskRenderer() {
        super(new ObeliskModel());
    }
}