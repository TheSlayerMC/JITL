package net.jitl.client.render.entity.euca;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.entity.euca.state.CrypianRenderState;
import net.jitl.common.entity.euca.npc.Crypian;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class CrypianRenderer<R extends CrypianRenderState & GeoRenderState> extends GeoEntityRenderer<Crypian, R> {

    public CrypianRenderer(EntityRendererProvider.Context renderManager, DefaultedEntityGeoModel<Crypian> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.5F;
    }

    @Override
    protected R createBaseRenderState(Crypian entity) {
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
    public void render(R renderState, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(1.25F, 1.25F, 1.25F);
        super.render(renderState, poseStack, bufferSource, packedLight);
    }

    @Override
    public void extractRenderState(Crypian entity, R entityRenderState, float partialTick) {
        super.extractRenderState(entity, entityRenderState, partialTick);
        entityRenderState.isAlloyHouse = entity.isAlloyHouse();
        entityRenderState.canTrade = entity.canTrade();
    }
}
