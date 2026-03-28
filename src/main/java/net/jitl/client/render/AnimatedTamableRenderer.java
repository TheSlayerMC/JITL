package net.jitl.client.render;

import com.geckolib.animatable.GeoAnimatable;
import com.geckolib.model.GeoModel;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;
import com.geckolib.renderer.base.RenderPassInfo;
import net.jitl.common.entity.base.JTamableEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class AnimatedTamableRenderer<T extends LivingEntityRenderState & GeoAnimatable & GeoRenderState> extends GeoEntityRenderer<JTamableEntity, T> {

    private float size = 1F;

    public AnimatedTamableRenderer(EntityRendererProvider.Context renderManager, GeoModel<JTamableEntity> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.5F;
    }

    public AnimatedTamableRenderer(EntityRendererProvider.Context renderManager, GeoModel<JTamableEntity> modelProvider, float shadow, float size) {
        super(renderManager, modelProvider);
        this.shadowRadius = shadow;
        this.size = size;
    }

    public AnimatedTamableRenderer(EntityRendererProvider.Context renderManager, GeoModel<JTamableEntity> modelProvider, float shadow) {
        super(renderManager, modelProvider);
        this.shadowRadius = shadow;
    }

    @Override
    public @NotNull Identifier getTextureLocation(@NotNull T instance) {
        return getGeoModel().getTextureResource(instance);
    }

    @Override
    public void preRenderPass(RenderPassInfo<T> renderPassInfo, SubmitNodeCollector renderTasks) {
        if(renderPassInfo.renderState().isBaby) {
            this.size = 0.5F;
        }
        renderPassInfo.poseStack().scale(this.size, this.size, this.size);
        super.preRenderPass(renderPassInfo, renderTasks);
    }
}