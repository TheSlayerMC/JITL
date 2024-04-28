package net.jitl.client.render.entity.misc;

import net.jitl.client.model.misc.SpiritCrystalModel;
import net.jitl.common.entity.corba.SpiritCrystal;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SpiritCrystalRenderer extends GeoEntityRenderer<SpiritCrystal> {

    public SpiritCrystalRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SpiritCrystalModel());
        this.shadowRadius = 0.5F;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull SpiritCrystal entity) {
        return JITL.rl("textures/entity/crystal/spirit_crystal.png");
    }
}
