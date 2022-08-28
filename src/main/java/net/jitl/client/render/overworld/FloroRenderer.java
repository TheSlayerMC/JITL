package net.jitl.client.render.overworld;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jitl.client.render.model.FloroModel;
import net.jitl.common.entity.overworld.Floro;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FloroRenderer extends GeoEntityRenderer<Floro> {

    public FloroRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FloroModel());
        this.shadowRadius = 0.5F;
    }

    @Override
    public ResourceLocation getTextureLocation(Floro instance) {
        return getGeoModelProvider().getTextureResource(instance);
    }

    @Override
    public RenderType getRenderType(Floro animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer
            , @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        stack.scale(1.2F, 1.2F, 1.2F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}