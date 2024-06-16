package net.jitl.client.model;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JDimension;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class AnimatedMonsterModel<T extends GeoEntity> extends DefaultedEntityGeoModel<T> {

    private final String name;
    private final JDimension dim;

    public AnimatedMonsterModel(String name, JDimension dim) {
        super(ResourceLocation.fromNamespaceAndPath(JITL.MODID, name));
        this.dim = dim;
        this.name = name;
    }

    @Override
    public ResourceLocation getModelResource(T object) {
        return JITL.rl("geo/" + this.name +".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        return JITL.rl("textures/entity/" + dim.getDim() + "/" + this.name +".png");
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return JITL.rl("animations/" + this.name + ".animation.json");
    }
}