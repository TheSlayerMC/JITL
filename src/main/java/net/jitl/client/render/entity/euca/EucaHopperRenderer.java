package net.jitl.client.render.entity.euca;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.entity.state.TamableRenderState;
import net.jitl.common.entity.euca.EucaHopper;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class EucaHopperRenderer<T extends TamableRenderState & GeoRenderState> extends GeoEntityRenderer<EucaHopper, T> {

    public EucaHopperRenderer(EntityRendererProvider.Context renderManager, DefaultedEntityGeoModel<EucaHopper> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.5F;
    }

    @Override
    protected T createBaseRenderState(EucaHopper entity) {
        return (T)new TamableRenderState();
    }

    @Override
    public void render(T renderState, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(0.75F, 0.75F, 0.75F);
        super.render(renderState, poseStack, bufferSource, packedLight);
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
