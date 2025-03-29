package net.jitl.client.render.entity.frozen;

import net.jitl.client.JModelLayers;
import net.jitl.client.model.ShiverwolfModel;
import net.jitl.client.render.entity.frozen.layer.ShiverwolfArmorLayer;
import net.jitl.client.render.entity.frozen.layer.ShiverwolfCollarLayer;
import net.jitl.common.entity.frozen.Shiverwolf;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.WolfRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ShiverwolfRenderer extends AgeableMobRenderer<Shiverwolf, WolfRenderState, ShiverwolfModel> {

    public ShiverwolfRenderer(EntityRendererProvider.Context p_174452_) {
        super(p_174452_, new ShiverwolfModel(p_174452_.bakeLayer(JModelLayers.SHIVERWOLF_MODEL_LAYER)), new ShiverwolfModel(p_174452_.bakeLayer(JModelLayers.SHIVERWOLF_BABY_MODEL_LAYER)), 0.5F);
        this.addLayer(new ShiverwolfArmorLayer(this, p_174452_.getModelSet(), p_174452_.getEquipmentRenderer()));
        this.addLayer(new ShiverwolfCollarLayer(this));
    }

    protected int getModelTint(WolfRenderState p_365181_) {
        float f = p_365181_.wetShade;
        return f == 1.0F ? -1 : ARGB.colorFromFloat(1.0F, f, f, f);
    }

    public void extractRenderState(Shiverwolf p_363274_, WolfRenderState p_363549_, float p_362105_) {
        super.extractRenderState(p_363274_, p_363549_, p_362105_);
        p_363549_.isAngry = p_363274_.isAngry();
        p_363549_.isSitting = p_363274_.isInSittingPose();
        p_363549_.tailAngle = p_363274_.getTailAngle();
        p_363549_.headRollAngle = p_363274_.getHeadRollAngle(p_362105_);
        p_363549_.shakeAnim = p_363274_.getShakeAnim(p_362105_);
        p_363549_.texture = p_363274_.getTexture();
        p_363549_.wetShade = p_363274_.getWetShade(p_362105_);
        p_363549_.collarColor = p_363274_.isTame() ? p_363274_.getCollarColor() : null;
        p_363549_.bodyArmorItem = p_363274_.getBodyArmorItem().copy();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(WolfRenderState entity) {
        return entity.texture;
    }

    public WolfRenderState createRenderState() {
        return new WolfRenderState();
    }
}
