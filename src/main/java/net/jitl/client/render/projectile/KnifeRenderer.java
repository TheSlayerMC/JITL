package net.jitl.client.render.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jitl.client.render.projectile.render_state.KnifeRenderState;
import net.jitl.common.entity.projectile.KnifeEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.state.ThrownItemRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class KnifeRenderer<T extends KnifeEntity> extends EntityRenderer<T, KnifeRenderState> {
    private final ItemRenderer itemRenderer;
    private final Random random = new Random();

    public KnifeRenderer(EntityRendererProvider.Context context, ItemRenderer itemRendererIn) {
        super(context);
        this.itemRenderer = itemRendererIn;
        this.shadowRadius = 0.15F;
        this.shadowStrength = 0.75F;
    }

    @Override
    public void render(KnifeRenderState entityIn, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        ItemStack itemstack = entityIn.item;
        int i = itemstack.isEmpty() ? 187 : Item.getId(itemstack.getItem()) + itemstack.getDamageValue();
        this.random.setSeed(i);
        BakedModel ibakedmodel = this.itemRenderer.getModel(itemstack, entityIn.level, null, entityIn.id);

        float f1 = ((float) entityIn.tick + entityIn.partialTick) / 10 * (float) (Math.PI * 2.0D);

        matrixStackIn.mulPose(Axis.YP.rotationDegrees(Mth.lerp(entityIn.partialTick, entityIn.yRot, entityIn.yRot) - 90.0F));
        matrixStackIn.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(entityIn.partialTick, entityIn.xRot, entityIn.xRot) + 90.0F));

        if (!entityIn.inGround) {
            matrixStackIn.mulPose(Axis.ZP.rotation(f1));
        }
        matrixStackIn.mulPose(Axis.ZP.rotationDegrees(120));
        matrixStackIn.translate(0, -0.075, 0);

        matrixStackIn.pushPose();

        this.itemRenderer.render(itemstack, ItemDisplayContext.GROUND, false, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY, ibakedmodel);
        matrixStackIn.popPose();
        matrixStackIn.translate(0.0, 0.0, 0.09375F);

        matrixStackIn.popPose();
        super.render(entityIn, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public void extractRenderState(T e, KnifeRenderState s, float f) {
        super.extractRenderState(e, s, f);
        s.tick = e.tickCount;
        s.xRot = e.xRotO;
        s.yRot = e.yRotO;
        s.level = e.level();
        s.inGround = e.isInGround();
        s.id = e.getId();
    }

    @Override
    public @NotNull KnifeRenderState createRenderState() {
        return new KnifeRenderState();
    }
}
