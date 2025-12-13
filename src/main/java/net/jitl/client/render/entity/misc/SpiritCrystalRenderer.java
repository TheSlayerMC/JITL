package net.jitl.client.render.entity.misc;

import net.jitl.client.model.misc.SpiritCrystalModel;
import net.jitl.common.entity.corba.SpiritCrystal;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class SpiritCrystalRenderer<T extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<SpiritCrystal, T> {

    public SpiritCrystalRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SpiritCrystalModel());
        this.shadowRadius = 0.5F;
    }

    @Override
    public @NotNull Identifier getTextureLocation(@NotNull T entity) {
        return JITL.rl("textures/entity/crystal/spirit_crystal.png");
    }
}
