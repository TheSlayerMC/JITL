package net.jitl.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.common.entity.base.JAnimalEntity;
import net.jitl.common.entity.base.JAnimalEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class AnimatedAnimalRenderer<T extends LivingEntityRenderState & GeoAnimatable & GeoRenderState> extends GeoEntityRenderer<JAnimalEntity, T> {

    private float size = 1F;

    public AnimatedAnimalRenderer(EntityRendererProvider.Context renderManager, GeoModel<JAnimalEntity> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.5F;
    }

    public AnimatedAnimalRenderer(EntityRendererProvider.Context renderManager, GeoModel<JAnimalEntity> modelProvider, float shadow, float size) {
        super(renderManager, modelProvider);
        this.shadowRadius = shadow;
        this.size = size;
    }

    public AnimatedAnimalRenderer(EntityRendererProvider.Context renderManager, GeoModel<JAnimalEntity> modelProvider, float shadow) {
        super(renderManager, modelProvider);
        this.shadowRadius = shadow;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull T instance) {
        return getGeoModel().getTextureResource(instance);
    }

    @Override
    public void render(T renderState, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(this.size, this.size, this.size);
        super.render(renderState, poseStack, bufferSource, packedLight);
    }
}