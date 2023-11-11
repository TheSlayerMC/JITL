package net.jitl.client.model.block;

import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class GrindstoneModel extends DefaultedEntityGeoModel<GrindstoneEntity> {

    public GrindstoneModel() {
        super(new ResourceLocation(GeckoLib.MOD_ID, "grindstone"));
    }

    @Override
    public ResourceLocation getModelResource(GrindstoneEntity object) {
        return new ResourceLocation(JITL.MODID, "geo/grindstone.json");
    }

    @Override
    public ResourceLocation getTextureResource(GrindstoneEntity object) {
        return new ResourceLocation(JITL.MODID, "textures/models/block/grindstone.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GrindstoneEntity animatable) {
        return new ResourceLocation(JITL.MODID, "animations/grindstone.animation.json");
    }
}
