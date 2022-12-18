package net.jitl.client.render.entity.euca;

import net.jitl.client.render.AnimatedMonsterRenderer;
import net.jitl.common.entity.euca.Crypian;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class CrypianRenderer<T extends LivingEntity & GeoEntity> extends AnimatedMonsterRenderer<T> {

    public CrypianRenderer(EntityRendererProvider.Context renderManager, DefaultedEntityGeoModel<T> modelProvider) {
        super(renderManager, modelProvider, 0.5F, 1.25F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull T entity) {
        if(((Crypian)entity).isAlloyHouse()) {
            return new ResourceLocation(JITL.MODID, "textures/entity/euca/crypian_alloy.png");
        }
        else if(((Crypian)entity).canTrade()) {
            return new ResourceLocation(JITL.MODID, "textures/entity/euca/crypian_trade.png");
        } else {
            return new ResourceLocation(JITL.MODID, "textures/entity/euca/crypian.png");
        }

    }
}
