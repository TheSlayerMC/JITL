package net.jitl.client.render.entity.misc;

import net.jitl.client.model.misc.BossCrystalModel;
import net.jitl.client.render.entity.state.BossCrystalRenderState;
import net.jitl.common.entity.boss.BossCrystal;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class BossCrystalRenderer<T extends BossCrystalRenderState & GeoRenderState> extends GeoEntityRenderer<BossCrystal, T> {

    public BossCrystalRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BossCrystalModel());
        this.shadowRadius = 0.5F;
    }

    @Override
    public T createRenderState(BossCrystal animatable, Void relatedObject) {
        return (T)new BossCrystalRenderState();
    }

    @Override
    public @NotNull Identifier getTextureLocation(@NotNull T entity) {
        return entity.texture;
    }

    @Override
    public void extractRenderState(BossCrystal entity, T entityRenderState, float partialTick) {
        super.extractRenderState(entity, entityRenderState, partialTick);
        entityRenderState.texture = entity.getTexture();
    }
}
