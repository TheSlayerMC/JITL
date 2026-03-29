package net.jitl.client.render.entity.euca;

import com.geckolib.model.DefaultedEntityGeoModel;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;
import com.geckolib.renderer.base.RenderPassInfo;
import net.jitl.client.render.entity.euca.state.CrypianRenderState;
import net.jitl.common.entity.euca.npc.Crypian;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

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
    public @NotNull Identifier getTextureLocation(@NotNull R entity) {
        if(entity.isAlloyHouse) {
            return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "textures/entity/euca/crypian_alloy.png");
        }
        else if(entity.canTrade) {
            return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "textures/entity/euca/crypian_trade.png");
        } else {
            return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "textures/entity/euca/crypian.png");
        }

    }

    @Override
    public void preRenderPass(RenderPassInfo<R> renderPassInfo, SubmitNodeCollector renderTasks) {
        float size = 1.25F;
        renderPassInfo.poseStack().scale(size, size, size);
        super.preRenderPass(renderPassInfo, renderTasks);
    }

    @Override
    public void extractRenderState(Crypian entity, R entityRenderState, float partialTick) {
        super.extractRenderState(entity, entityRenderState, partialTick);
        entityRenderState.isAlloyHouse = entity.isAlloyHouse();
        entityRenderState.canTrade = entity.canTrade();
    }
}
