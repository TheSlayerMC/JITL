package net.jitl.client.render.block;

import com.geckolib.renderer.GeoBlockRenderer;
import com.geckolib.renderer.base.GeoRenderState;
import net.jitl.client.model.block.ObeliskModel;
import net.jitl.common.block.entity.ObeliskTile;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;

public class ObeliskRenderer<R extends BlockEntityRenderState & GeoRenderState> extends GeoBlockRenderer<ObeliskTile, R> {

    public ObeliskRenderer() {
        super(new ObeliskModel());
    }
}