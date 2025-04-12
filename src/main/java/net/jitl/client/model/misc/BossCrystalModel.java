package net.jitl.client.model.misc;

import net.jitl.common.entity.boss.BossCrystal;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class BossCrystalModel extends DefaultedEntityGeoModel<BossCrystal> {

    public BossCrystalModel() {
        super(ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "bosscrystal"));
    }

    @Override
    public ResourceLocation getModelResource(GeoRenderState object) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "bosscrystal");
    }

    @Override
    public ResourceLocation getTextureResource(GeoRenderState object) {
        return JITL.rl("textures/entity/crystal/" + object.getGeckolibData(BossCrystal.TYPE_TICKET) + ".png");
    }

    @Override
    public void addAdditionalStateData(BossCrystal animatable, GeoRenderState renderState) {
        renderState.addGeckolibData(BossCrystal.TYPE_TICKET, animatable.getCrystalType());
    }

    @Override
    public ResourceLocation getAnimationResource(BossCrystal animatable) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "bosscrystal");
    }
}
