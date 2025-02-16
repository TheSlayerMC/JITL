package net.jitl.client.render.entity.frozen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.JModelLayers;
import net.jitl.client.model.ShiverwolfModel;
import net.jitl.client.render.entity.frozen.layer.ShiverwolfArmorLayer;
import net.jitl.client.render.entity.frozen.layer.ShiverwolfCollarLayer;
import net.jitl.common.entity.frozen.Shiverwolf;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ShiverwolfRenderer extends MobRenderer<Shiverwolf, ShiverwolfModel<Shiverwolf>> {

    public ShiverwolfRenderer(EntityRendererProvider.Context p_174452_) {
        super(p_174452_, new ShiverwolfModel<>(p_174452_.bakeLayer(JModelLayers.SHIVERWOLF_MODEL_LAYER)), 0.5F);
        this.addLayer(new ShiverwolfArmorLayer(this, p_174452_.getModelSet()));
        this.addLayer(new ShiverwolfCollarLayer(this));
    }

    @Override
    protected float getBob(Shiverwolf livingBase, float partialTicks) {
        return livingBase.getTailAngle();
    }

    @Override
    public void render(Shiverwolf entity, float entityYaw, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        if(entity.isWet()) {
            float f = entity.getWetShade(partialTicks);
            this.model.setColor(FastColor.ARGB32.colorFromFloat(1.0F, f, f, f));
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
        if(entity.isWet())
            this.model.setColor(-1);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Shiverwolf entity) {
        return entity.getTexture();
    }
}
