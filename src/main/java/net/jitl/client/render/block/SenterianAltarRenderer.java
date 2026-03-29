package net.jitl.client.render.block;

import com.geckolib.renderer.GeoBlockRenderer;
import com.geckolib.renderer.base.GeoRenderState;
import net.jitl.client.model.block.SenterianAltarModel;
import net.jitl.common.block.entity.SenterianAltarTile;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;

public class SenterianAltarRenderer<R extends BlockEntityRenderState & GeoRenderState> extends GeoBlockRenderer<SenterianAltarTile, R> {

    public SenterianAltarRenderer(BlockEntityRendererProvider.Context context) {
        super(context, new SenterianAltarModel());
    }
}