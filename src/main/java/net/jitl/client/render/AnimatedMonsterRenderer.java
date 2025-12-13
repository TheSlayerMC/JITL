package net.jitl.client.render;

import net.jitl.common.entity.base.JMonsterEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.internal.RenderPassInfo;

public class AnimatedMonsterRenderer<T extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<JMonsterEntity, T> {

    private float size = 1F;

    public AnimatedMonsterRenderer(EntityRendererProvider.Context renderManager, GeoModel<JMonsterEntity> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.5F;
    }

    public AnimatedMonsterRenderer(EntityRendererProvider.Context renderManager, GeoModel<JMonsterEntity> modelProvider, float shadow, float size) {
        super(renderManager, modelProvider);
        this.shadowRadius = shadow;
        this.size = size;
    }

    public AnimatedMonsterRenderer(EntityRendererProvider.Context renderManager, GeoModel<JMonsterEntity> modelProvider, float shadow) {
        super(renderManager, modelProvider);
        this.shadowRadius = shadow;
    }

    @Override
    public @NotNull Identifier getTextureLocation(@NotNull T instance) {
        return getGeoModel().getTextureResource(instance);
    }

    @Override
    public void preRenderPass(RenderPassInfo<T> renderPassInfo, SubmitNodeCollector renderTasks) {
        renderPassInfo.poseStack().scale(this.size, this.size, this.size);
        super.preRenderPass(renderPassInfo, renderTasks);
    }
}