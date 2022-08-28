package net.jitl.client.render.overworld;

import net.jitl.client.render.model.TowerGuardianModel;
import net.jitl.common.entity.boss.TowerGuardian;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class TowerGuardianRenderer extends GeoEntityRenderer<TowerGuardian> {

    public TowerGuardianRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TowerGuardianModel());
        this.shadowRadius = 0.5F;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(TowerGuardian instance) {
        return getGeoModelProvider().getTextureResource(instance);
    }
}