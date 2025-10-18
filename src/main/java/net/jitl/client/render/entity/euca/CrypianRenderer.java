package net.jitl.client.render.entity.euca;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.entity.euca.state.CrypianRenderState;
import net.jitl.common.entity.euca.npc.Crypian;
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

public class CrypianRenderer<R extends CrypianRenderState & GeoRenderState> extends GeoEntityRenderer<Crypian, R> {

    public CrypianRenderer(EntityRendererProvider.Context renderManager, DefaultedEntityGeoModel<Crypian> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.5F;
    }

    @Override
    public R createRenderState(Crypian animatable, Void relatedObject) {
        return (R)new CrypianRenderState();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull R entity) {
        if(entity.isAlloyHouse) {
            return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "textures/entity/euca/crypian_alloy.png");
        }
        else if(entity.canTrade) {
            return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "textures/entity/euca/crypian_trade.png");
        } else {
            return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "textures/entity/euca/crypian.png");
        }

    }

    @Override
    public void preRender(R renderState, PoseStack poseStack, BakedGeoModel model, SubmitNodeCollector renderTasks, CameraRenderState cameraState, int packedLight, int packedOverlay, int renderColor) {
        poseStack.scale(1.25F, 1.25F, 1.25F);
        super.preRender(renderState, poseStack, model, renderTasks, cameraState, packedLight, packedOverlay, renderColor);
    }

    @Override
    public void extractRenderState(Crypian entity, R entityRenderState, float partialTick) {
        super.extractRenderState(entity, entityRenderState, partialTick);
        entityRenderState.isAlloyHouse = entity.isAlloyHouse();
        entityRenderState.canTrade = entity.canTrade();
    }
}
