package net.jitl.client.model.block;

import net.jitl.common.block.entity.ObeliskTile;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;

public class ObeliskModel extends DefaultedBlockGeoModel<ObeliskTile> {

    public ObeliskModel() {
        super(ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "geo/obelisk"));
    }

    @Override
    public ResourceLocation getModelResource(ObeliskTile object) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "geo/obelisk.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ObeliskTile object) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "textures/models/block/obelisk.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ObeliskTile animatable) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "animations/obelisk.animation.json");
    }
}
