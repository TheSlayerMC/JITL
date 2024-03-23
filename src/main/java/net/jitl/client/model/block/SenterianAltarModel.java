package net.jitl.client.model.block;

import net.jitl.common.block.entity.SenterianAltarTile;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;

public class SenterianAltarModel extends DefaultedBlockGeoModel<SenterianAltarTile> {

    public SenterianAltarModel() {
        super(new ResourceLocation(JITL.MODID, "geo/senterian_altar"));
    }

    @Override
    public ResourceLocation getModelResource(SenterianAltarTile object) {
        return new ResourceLocation(JITL.MODID, "geo/senterian_altar.json");
    }

    @Override
    public ResourceLocation getTextureResource(SenterianAltarTile object) {
        return new ResourceLocation(JITL.MODID, "textures/models/block/senterian_altar.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SenterianAltarTile animatable) {
        return new ResourceLocation(JITL.MODID, "animations/senterian_altar.json");
    }
}
