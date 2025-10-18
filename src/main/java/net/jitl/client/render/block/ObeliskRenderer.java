package net.jitl.client.render.block;

import net.jitl.client.model.block.ObeliskModel;
import net.jitl.common.block.entity.ObeliskTile;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class ObeliskRenderer<R extends BlockEntityRenderState & GeoRenderState> extends GeoBlockRenderer<ObeliskTile, R> {

    public ObeliskRenderer() {
        super(new ObeliskModel());
    }
}