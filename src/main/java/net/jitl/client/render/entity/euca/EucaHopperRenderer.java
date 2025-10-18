package net.jitl.client.render.entity.euca;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.entity.state.TamableRenderState;
import net.jitl.common.entity.euca.EucaHopper;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

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
    public void preRender(T renderState, PoseStack poseStack, BakedGeoModel model, SubmitNodeCollector renderTasks, CameraRenderState cameraState, int packedLight, int packedOverlay, int renderColor) {
        poseStack.scale(0.75F, 0.75F, 0.75F);
        super.preRender(renderState, poseStack, model, renderTasks, cameraState, packedLight, packedOverlay, renderColor);
    }



    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull T entity) {
        if(entity.isTame) {
            return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "textures/entity/euca/euca_hopper_tame.png");
        } else {
            return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "textures/entity/euca/euca_hopper.png");
        }
    }

    @Override
    public void extractRenderState(EucaHopper entity, T entityRenderState, float partialTick) {
        super.extractRenderState(entity, entityRenderState, partialTick);
        entityRenderState.isTame = entity.isTame();
    }
}
