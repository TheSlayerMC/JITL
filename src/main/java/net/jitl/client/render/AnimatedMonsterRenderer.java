package net.jitl.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AnimatedMonsterRenderer<T extends LivingEntity & GeoEntity> extends GeoEntityRenderer<T> {

    private float size = 1F;

    public AnimatedMonsterRenderer(EntityRendererProvider.Context renderManager, DefaultedEntityGeoModel<T> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.5F;
    }

    public AnimatedMonsterRenderer(EntityRendererProvider.Context renderManager, DefaultedEntityGeoModel<T> modelProvider, float shadow, float size) {
        super(renderManager, modelProvider);
        this.shadowRadius = shadow;
        this.size = size;
    }

    public AnimatedMonsterRenderer(EntityRendererProvider.Context renderManager, DefaultedEntityGeoModel<T> modelProvider, float shadow) {
        super(renderManager, modelProvider);
        this.shadowRadius = shadow;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull T instance) {
        return getGeoModel().getTextureResource(instance);
    }
//TODO
    /*@Override
    public RenderType getRenderType(T animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer
            , @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        stack.scale(this.size, this.size, this.size);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }

    @Override
    public RenderType getRenderType(T animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        //stack.scale(this.size, this.size, this.size);
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }*/
}