package net.jitl.client.model.misc;

import net.jitl.common.entity.corba.SpiritCrystal;
import net.jitl.core.init.JITL;
import net.minecraft.resources.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class SpiritCrystalModel extends DefaultedEntityGeoModel<SpiritCrystal> {

    public SpiritCrystalModel() {
        super(Identifier.fromNamespaceAndPath(JITL.MOD_ID, "spirit_crystal"));
    }

    @Override
    public Identifier getModelResource(GeoRenderState object) {
        return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "spirit_crystal");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState object) {
        return JITL.rl("textures/entity/crystal/spirit_crystal.png");
    }

    @Override
    public Identifier getAnimationResource(SpiritCrystal animatable) {
        return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "spirit_crystal");
    }
}
