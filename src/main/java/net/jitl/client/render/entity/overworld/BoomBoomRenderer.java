package net.jitl.client.render.entity.overworld;

import com.geckolib.model.DefaultedEntityGeoModel;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;
import com.geckolib.renderer.base.RenderPassInfo;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.entity.overworld.state.BoomRenderState;
import net.jitl.common.entity.overworld.BoomBoom;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;

public class BoomBoomRenderer<T extends BoomRenderState & GeoRenderState> extends GeoEntityRenderer<BoomBoom, T> {

    public BoomBoomRenderer(EntityRendererProvider.Context renderManager, DefaultedEntityGeoModel<BoomBoom> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.5F;
    }

    public void scale(T pLivingEntity, PoseStack pMatrixStack) {
        float f = pLivingEntity.swelling;
        float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
        f = Mth.clamp(f, 0.0F, 1.0F);
        f *= f;
        f *= f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        pMatrixStack.scale(f2, f3, f2);
    }

    @Override
    public T createRenderState(BoomBoom animatable, Void relatedObject) {
        return (T)new BoomRenderState();
    }

    @Override
    public void preRenderPass(RenderPassInfo<T> renderPassInfo, SubmitNodeCollector renderTasks) {
        scale(renderPassInfo.renderState(), renderPassInfo.poseStack());
        super.preRenderPass(renderPassInfo, renderTasks);
    }

    @Override
    public void extractRenderState(BoomBoom e, T state, float ticks) {
        super.extractRenderState(e, state, ticks);
        state.swelling = e.getSwelling(ticks);
        state.isPowered = e.isPowered();
    }
}
