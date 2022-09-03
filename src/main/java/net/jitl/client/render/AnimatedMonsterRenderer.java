package net.jitl.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AnimatedMonsterRenderer<T extends LivingEntity & IAnimatable> extends GeoEntityRenderer<T> {

    private float size = 1F;

    public AnimatedMonsterRenderer(EntityRendererProvider.Context renderManager, AnimatedGeoModel<T> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.5F;
    }

    public AnimatedMonsterRenderer(EntityRendererProvider.Context renderManager, AnimatedGeoModel<T> modelProvider, float shadow, float size) {
        super(renderManager, modelProvider);
        this.shadowRadius = shadow;
        this.size = size;
    }

    public AnimatedMonsterRenderer(EntityRendererProvider.Context renderManager, AnimatedGeoModel<T> modelProvider, float shadow) {
        super(renderManager, modelProvider);
        this.shadowRadius = shadow;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull T instance) {
        return getGeoModelProvider().getTextureResource(instance);
    }

    @Override
    public RenderType getRenderType(T animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer
            , @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        stack.scale(this.size, this.size, this.size);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}