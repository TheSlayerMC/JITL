package net.jitl.client.model.block;

import com.geckolib.model.DefaultedBlockGeoModel;
import com.geckolib.renderer.base.GeoRenderState;
import net.jitl.common.block.SenterianAltar;
import net.jitl.common.block.entity.SenterianAltarTile;
import net.jitl.core.init.JITL;
import net.minecraft.resources.Identifier;

public class SenterianAltarModel extends DefaultedBlockGeoModel<SenterianAltarTile> {

    public SenterianAltarModel() {
        super(Identifier.fromNamespaceAndPath(JITL.MOD_ID, "geo/senterian_altar"));
    }

    @Override
    public void addAdditionalStateData(SenterianAltarTile animatable, Object extra, GeoRenderState renderState) {
        renderState.addGeckolibData(SenterianAltar.IS_ACTIVE_TICKET, animatable.getBlockState().getValue(SenterianAltar.IS_ACTIVE));
    }

    @Override
    public Identifier getModelResource(GeoRenderState object) {
        return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "senterian_altar");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState object) {
        return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "textures/models/block/senterian_altar.png");
    }

    @Override
    public Identifier getAnimationResource(SenterianAltarTile animatable) {
        return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "senterian_altar");
    }
}
