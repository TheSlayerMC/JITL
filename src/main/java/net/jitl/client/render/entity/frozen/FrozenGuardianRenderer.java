package net.jitl.client.render.entity.frozen;

import net.jitl.common.entity.frozen.FrozenGuardian;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FrozenGuardianRenderer<T extends LivingEntity & IAnimatable> extends GeoEntityRenderer<T> {

    public FrozenGuardianRenderer(EntityRendererProvider.Context renderManager, AnimatedGeoModel<T> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.5F;
        //this.addLayer(new ItemInHandLayer<>(this));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull T entity) {
        FrozenGuardian e = (FrozenGuardian)entity;
        boolean isActivated = e.isActivated();
        if (isActivated) {
            return new ResourceLocation(JITL.MODID, "textures/entity/frozen/frozen_guardian_awake.png");
        } else {
            return new ResourceLocation(JITL.MODID, "textures/entity/frozen/frozen_guardian_asleep.png");
        }
    }
}
