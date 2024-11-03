package net.jitl.client.model.block;

import net.jitl.common.block.entity.ObeliskTile;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class ObeliskModel extends DefaultedBlockGeoModel<ObeliskTile> {

    public ObeliskModel() {
        super(ResourceLocation.fromNamespaceAndPath(JITL.MODID, "geo/obelisk"));
    }

    @Override
    public ResourceLocation getModelResource(ObeliskTile object, GeoRenderer<ObeliskTile> renderer) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MODID, "geo/obelisk.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ObeliskTile object, GeoRenderer<ObeliskTile> renderer) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MODID, "textures/models/block/obelisk.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ObeliskTile animatable) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MODID, "animations/obelisk.animation.json");
    }
}
