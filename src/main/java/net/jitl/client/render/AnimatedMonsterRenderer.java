package net.jitl.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
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
        return getGeoModel().getTextureResource(instance, this);
    }

    @Override
    public void render(@NotNull EntityRenderState entity, PoseStack stack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        stack.scale(this.size, this.size, this.size);
        super.render(entity, stack, bufferSource, packedLight);
    }
}