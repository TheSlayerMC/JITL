package net.jitl.client.render.projectile;

import net.jitl.common.entity.projectile.EssenceArrowEntity;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.ResourceLocation;

public class EssenceArrowRenderer extends ArrowRenderer<EssenceArrowEntity, ArrowRenderState> {

    public EssenceArrowRenderer(EntityRendererProvider.Context c) {
        super(c);
    }

    @Override
    public ArrowRenderState createRenderState() {
        return new ArrowRenderState();
    }

    @Override
    protected ResourceLocation getTextureLocation(ArrowRenderState arrowRenderState) {
        return JITL.rl("textures/entity/projectile/essence_arrow.png");
    }
}
