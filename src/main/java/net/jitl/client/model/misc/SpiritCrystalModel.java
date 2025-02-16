package net.jitl.client.model.misc;

import net.jitl.common.entity.corba.SpiritCrystal;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class SpiritCrystalModel extends DefaultedEntityGeoModel<SpiritCrystal> {

    public SpiritCrystalModel() {
        super(ResourceLocation.fromNamespaceAndPath(JITL.MODID, "spirit_crystal"));
    }

    @Override
    public ResourceLocation getModelResource(SpiritCrystal object) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MODID, "geo/spirit_crystal.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpiritCrystal object) {
        return JITL.rl("textures/entity/crystal/spirit_crystal.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpiritCrystal animatable) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MODID, "animations/spirit_crystal.animation.json");
    }
}
