package net.jitl.client.model.misc;

import net.jitl.common.entity.boss.BossCrystal;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class BossCrystalModel extends DefaultedEntityGeoModel<BossCrystal> {

    public BossCrystalModel() {
        super(ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "bosscrystal"));
    }

    @Override
    public ResourceLocation getModelResource(BossCrystal object, GeoRenderer<BossCrystal> renderer) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "geo/bosscrystal.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BossCrystal object, GeoRenderer<BossCrystal> renderer) {
        return object.getTexture();
    }

    @Override
    public ResourceLocation getAnimationResource(BossCrystal animatable) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "animations/bosscrystal.animation.json");
    }
}
