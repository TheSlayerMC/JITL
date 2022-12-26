package net.jitl.client.model.misc;

import net.jitl.common.entity.boss.BossCrystal;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class BossCrystalModel extends DefaultedEntityGeoModel<BossCrystal> {

    public BossCrystalModel() {
        super(new ResourceLocation(GeckoLib.MOD_ID, "bosscrystal"));
    }

    @Override
    public ResourceLocation getModelResource(BossCrystal object) {
        return new ResourceLocation(JITL.MODID, "geo/bosscrystal.json");
    }

    @Override
    public ResourceLocation getTextureResource(BossCrystal object) {
        return object.getTexture();
    }

    @Override
    public ResourceLocation getAnimationResource(BossCrystal animatable) {
        return new ResourceLocation(JITL.MODID, "animations/bosscrystal.animation.json");
    }
}
