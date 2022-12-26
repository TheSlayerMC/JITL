package net.jitl.client.render.entity.misc;

import net.jitl.client.model.misc.BossCrystalModel;
import net.jitl.common.entity.boss.BossCrystal;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BossCrystalRenderer extends GeoEntityRenderer<BossCrystal> {

    public BossCrystalRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BossCrystalModel());
        this.shadowRadius = 0.5F;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull BossCrystal entity) {
        return entity.getTexture();
    }
}
