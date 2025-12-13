package net.jitl.client.model;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JDimension;
import net.minecraft.resources.Identifier;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class AnimatedMonsterModel<T extends GeoEntity> extends DefaultedEntityGeoModel<T> {

    private final String name;
    private final JDimension dim;

    public AnimatedMonsterModel(String name, JDimension dim) {
        super(Identifier.fromNamespaceAndPath(JITL.MOD_ID, name));
        this.dim = dim;
        this.name = name;
    }

    @Override
    public Identifier getModelResource(GeoRenderState object) {
        return JITL.rl(this.name);
    }

    @Override
    public Identifier getTextureResource(GeoRenderState object) {
        return JITL.rl("textures/entity/" + dim.getDim() + "/" + this.name +".png");
    }

    @Override
    public Identifier getAnimationResource(T animatable) {
        return JITL.rl(this.name);
    }
}