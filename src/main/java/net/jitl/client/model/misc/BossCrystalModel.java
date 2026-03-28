package net.jitl.client.model.misc;

import com.geckolib.model.DefaultedEntityGeoModel;
import com.geckolib.renderer.base.GeoRenderState;
import net.jitl.common.entity.boss.BossCrystal;
import net.jitl.core.init.JITL;
import net.minecraft.resources.Identifier;

public class BossCrystalModel extends DefaultedEntityGeoModel<BossCrystal> {

    public BossCrystalModel() {
        super(Identifier.fromNamespaceAndPath(JITL.MOD_ID, "bosscrystal"));
    }

    @Override
    public Identifier getModelResource(GeoRenderState object) {
        return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "bosscrystal");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState object) {
        return JITL.rl("textures/entity/crystal/" + object.getGeckolibData(BossCrystal.TYPE_TICKET) + ".png");
    }

    @Override
    public void addAdditionalStateData(BossCrystal animatable, Object related, GeoRenderState renderState) {
        renderState.addGeckolibData(BossCrystal.TYPE_TICKET, animatable.getCrystalType());
    }

    @Override
    public Identifier getAnimationResource(BossCrystal animatable) {
        return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "bosscrystal");
    }
}
