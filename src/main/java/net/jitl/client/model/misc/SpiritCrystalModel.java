package net.jitl.client.model.misc;

import net.jitl.common.entity.corba.SpiritCrystal;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class SpiritCrystalModel extends DefaultedEntityGeoModel<SpiritCrystal> {

    public SpiritCrystalModel() {
        super(ResourceLocation.fromNamespaceAndPath(JITL.MODID, "spirit_crystal"));
    }

    @Override
    public ResourceLocation getModelResource(SpiritCrystal object, GeoRenderer<SpiritCrystal> renderer) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MODID, "geo/spirit_crystal.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpiritCrystal object, GeoRenderer<SpiritCrystal> renderer) {
        return JITL.rl("textures/entity/crystal/spirit_crystal.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpiritCrystal animatable) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MODID, "animations/spirit_crystal.animation.json");
    }
}
