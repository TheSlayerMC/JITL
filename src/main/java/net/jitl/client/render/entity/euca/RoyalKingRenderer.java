package net.jitl.client.render.entity.euca;

import net.jitl.client.model.AnimatedMonsterModel;
import net.jitl.client.render.entity.state.ActiveRenderState;
import net.jitl.common.entity.euca.npc.RoyalKing;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class RoyalKingRenderer<R extends ActiveRenderState & GeoRenderState> extends GeoEntityRenderer<RoyalKing, R> {

    public RoyalKingRenderer(EntityRendererProvider.Context renderManager, AnimatedMonsterModel<RoyalKing> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.5F;
    }

    @Override
    public R createRenderState(RoyalKing entity, Void relatedObject) {
        return (R)new ActiveRenderState();
    }

    @Override
    public @NotNull Identifier getTextureLocation(@NotNull R entity) {
        boolean isActivated = entity.isActive;
        if (isActivated) {
            return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "textures/entity/euca/royal_king_no_crown.png");
        } else {
            return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "textures/entity/euca/royal_king.png");
        }
    }

    @Override
    public void extractRenderState(RoyalKing entity, R entityRenderState, float partialTick) {
        super.extractRenderState(entity, entityRenderState, partialTick);
        entityRenderState.isActive = entity.isActivated();
    }
}
