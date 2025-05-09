package net.jitl.client.model.misc;

import net.jitl.common.entity.corba.SpiritCrystal;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class SpiritCrystalModel extends DefaultedEntityGeoModel<SpiritCrystal> {

    public SpiritCrystalModel() {
        super(ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "spirit_crystal"));
    }

    @Override
    public ResourceLocation getModelResource(GeoRenderState object) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "spirit_crystal");
    }

    @Override
    public ResourceLocation getTextureResource(GeoRenderState object) {
        return JITL.rl("textures/entity/crystal/spirit_crystal.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpiritCrystal animatable) {
        return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "spirit_crystal");
    }
}
