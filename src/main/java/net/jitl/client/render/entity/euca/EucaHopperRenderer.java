package net.jitl.client.render.entity.euca;

import net.jitl.client.render.AnimatedMonsterRenderer;
import net.jitl.common.entity.euca.EucaHopper;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class EucaHopperRenderer<T extends LivingEntity & GeoEntity> extends AnimatedMonsterRenderer<T> {

    public EucaHopperRenderer(EntityRendererProvider.Context renderManager, DefaultedEntityGeoModel<T> modelProvider) {
        super(renderManager, modelProvider, 0.5F, 0.75F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull T entity) {
        if(((EucaHopper)entity).isTame()) {
            return new ResourceLocation(JITL.MODID, "textures/entity/euca/euca_hopper_tame.png");
        } else {
            return new ResourceLocation(JITL.MODID, "textures/entity/euca/euca_hopper.png");
        }
    }
}
