package net.jitl.client.render.block;

import net.jitl.client.model.block.SenterianAltarModel;
import net.jitl.common.block.entity.SenterianAltarTile;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SenterianAltarRenderer extends GeoBlockRenderer<SenterianAltarTile> {

    public SenterianAltarRenderer() {
        super(new SenterianAltarModel());
    }
}