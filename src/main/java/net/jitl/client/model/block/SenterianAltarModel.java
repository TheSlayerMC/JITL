package net.jitl.client.model.block;

import net.jitl.common.block.entity.SenterianAltarTile;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class SenterianAltarModel extends DefaultedBlockGeoModel<SenterianAltarTile> {

    public SenterianAltarModel() {
        super(ResourceLocation.fromNamespaceAndPath(JITL.MODID, "geo/senterian_altar"));
    }

    @Override
    public ResourceLocation getModelResource(SenterianAltarTile object, GeoRenderer<SenterianAltarTile> renderer) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MODID, "geo/senterian_altar.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SenterianAltarTile object, GeoRenderer<SenterianAltarTile> renderer) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MODID, "textures/models/block/senterian_altar.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SenterianAltarTile animatable) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MODID, "animations/senterian_altar.animation.json");
    }
}
