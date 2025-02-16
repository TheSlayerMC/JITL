package net.jitl.client.render.projectile;

import net.jitl.common.entity.projectile.EssenceArrowEntity;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class EssenceArrowRenderer extends ArrowRenderer<EssenceArrowEntity> {

    public EssenceArrowRenderer(EntityRendererProvider.Context c) {
        super(c);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(EssenceArrowEntity entity) {
        return JITL.rl("textures/entity/projectile/essence_arrow.png");
    }
}
