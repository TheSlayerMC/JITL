package net.jitl.client.render.entity.euca;

import net.jitl.common.entity.euca.RoyalKing;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RoyalKingRenderer<T extends LivingEntity & IAnimatable> extends GeoEntityRenderer<T> {

    public RoyalKingRenderer(EntityRendererProvider.Context renderManager, AnimatedGeoModel<T> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.5F;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull T entity) {
        RoyalKing e = (RoyalKing)entity;
        boolean isActivated = e.isActivated();
        if (isActivated) {
            return new ResourceLocation(JITL.MODID, "textures/entity/euca/royal_king_no_crown.png");
        } else {
            return new ResourceLocation(JITL.MODID, "textures/entity/euca/royal_king.png");
        }
    }
}
