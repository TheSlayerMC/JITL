package net.jitl.client.model.block;

import net.jitl.common.block.entity.ObeliskTile;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;

public class ObeliskModel extends DefaultedBlockGeoModel<ObeliskTile> {

    public ObeliskModel() {
        super(new ResourceLocation(JITL.MODID, "geo/obelisk"));
    }

    @Override
    public ResourceLocation getModelResource(ObeliskTile object) {
        return new ResourceLocation(JITL.MODID, "geo/obelisk.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ObeliskTile object) {
        return new ResourceLocation(JITL.MODID, "textures/models/block/obelisk.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ObeliskTile animatable) {
        return new ResourceLocation(JITL.MODID, "animations/obelisk.animation.json");
    }
}
