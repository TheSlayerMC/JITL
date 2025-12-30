package net.jitl.client.render.entity.euca;

import net.jitl.client.render.entity.state.TamableRenderState;
import net.jitl.common.entity.euca.EucaHopper;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.base.RenderPassInfo;

public class EucaHopperRenderer<T extends TamableRenderState & GeoRenderState> extends GeoEntityRenderer<EucaHopper, T> {

    public EucaHopperRenderer(EntityRendererProvider.Context renderManager, DefaultedEntityGeoModel<EucaHopper> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.5F;
    }

    @Override
    public T createRenderState(EucaHopper animatable, Void relatedObject) {
        return (T)new TamableRenderState();
    }

    @Override
    public void preRenderPass(RenderPassInfo<T> renderPassInfo, SubmitNodeCollector renderTasks) {
        float size = 0.75F;
        renderPassInfo.poseStack().scale(size, size, size);
        super.preRenderPass(renderPassInfo, renderTasks);
    }

    @Override
    public @NotNull Identifier getTextureLocation(@NotNull T entity) {
        if(entity.isTame) {
            return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "textures/entity/euca/euca_hopper_tame.png");
        } else {
            return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "textures/entity/euca/euca_hopper.png");
        }
    }

    @Override
    public void extractRenderState(EucaHopper entity, T entityRenderState, float partialTick) {
        super.extractRenderState(entity, entityRenderState, partialTick);
        entityRenderState.isTame = entity.isTame();
    }
}
