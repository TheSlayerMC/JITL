package net.jitl.client.model.block;

import net.jitl.common.block.SenterianAltar;
import net.jitl.common.block.entity.SenterianAltarTile;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class SenterianAltarModel extends DefaultedBlockGeoModel<SenterianAltarTile> {

    public SenterianAltarModel() {
        super(ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "geo/senterian_altar"));
    }

    @Override
    public void addAdditionalStateData(SenterianAltarTile animatable, GeoRenderState renderState) {
        renderState.addGeckolibData(SenterianAltar.IS_ACTIVE_TICKET, animatable.getBlockState().getValue(SenterianAltar.IS_ACTIVE));
    }

    @Override
    public ResourceLocation getModelResource(GeoRenderState object) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "senterian_altar");
    }

    @Override
    public ResourceLocation getTextureResource(GeoRenderState object) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "textures/models/block/senterian_altar.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SenterianAltarTile animatable) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "senterian_altar");
    }
}
