package net.jitl.client.render.nether;

import net.jitl.client.model.nether.WitherspineModel;
import net.jitl.common.entity.nether.Witherspine;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class WitherspineRenderer extends GeoEntityRenderer<Witherspine> {

    public WitherspineRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WitherspineModel());
        this.shadowRadius = 0.5F;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Witherspine instance) {
        return getGeoModelProvider().getTextureResource(instance);
    }
}