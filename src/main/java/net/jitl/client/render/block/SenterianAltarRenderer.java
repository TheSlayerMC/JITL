package net.jitl.client.render.block;

import net.jitl.client.model.block.SenterianAltarModel;
import net.jitl.common.block.entity.SenterianAltarTile;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class SenterianAltarRenderer<R extends BlockEntityRenderState & GeoRenderState> extends GeoBlockRenderer<SenterianAltarTile, R> {

    public SenterianAltarRenderer() {
        super(new SenterianAltarModel());
    }
}