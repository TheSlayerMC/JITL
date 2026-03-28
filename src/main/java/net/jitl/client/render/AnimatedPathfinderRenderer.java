package net.jitl.client.render;

import com.geckolib.animatable.GeoAnimatable;
import com.geckolib.model.GeoModel;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;
import com.geckolib.renderer.base.RenderPassInfo;
import net.jitl.common.entity.base.JPathfinderMob;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class AnimatedPathfinderRenderer<T extends LivingEntityRenderState & GeoAnimatable & GeoRenderState> extends GeoEntityRenderer<JPathfinderMob, T> {

    private float size = 1F;

    public AnimatedPathfinderRenderer(EntityRendererProvider.Context renderManager, GeoModel<JPathfinderMob> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.5F;
    }

    public AnimatedPathfinderRenderer(EntityRendererProvider.Context renderManager, GeoModel<JPathfinderMob> modelProvider, float shadow, float size) {
        super(renderManager, modelProvider);
        this.shadowRadius = shadow;
        this.size = size;
    }

    public AnimatedPathfinderRenderer(EntityRendererProvider.Context renderManager, GeoModel<JPathfinderMob> modelProvider, float shadow) {
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