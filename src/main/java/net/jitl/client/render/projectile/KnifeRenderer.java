package net.jitl.client.render.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jitl.client.render.projectile.state.KnifeRenderState;
import net.jitl.common.entity.projectile.KnifeEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class KnifeRenderer<T extends KnifeEntity> extends EntityRenderer<T, KnifeRenderState> {
    private final Random random = new Random();
    private final ItemModelResolver renderEntity;

    public KnifeRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.15F;
        this.shadowStrength = 0.75F;
        this.renderEntity = context.getItemModelResolver();
    }

    @Override
    public void submit(KnifeRenderState entityIn, PoseStack matrixStackIn, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
        matrixStackIn.pushPose();
        ItemStack itemstack = entityIn.knife;
        int i = itemstack.isEmpty() ? 187 : Item.getId(itemstack.getItem()) + itemstack.getDamageValue();
        this.random.setSeed(i);

        float f1 = (entityIn.tick + entityIn.partialTick) / 10 * (float) (Math.PI * 2.0D);

        matrixStackIn.mulPose(Axis.YP.rotationDegrees(Mth.lerp(entityIn.partialTick, entityIn.yRot, entityIn.yRot) - 90.0F));
        matrixStackIn.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(entityIn.partialTick, entityIn.xRot, entityIn.xRot) + 90.0F));

        if (!entityIn.inGround) {
            matrixStackIn.mulPose(Axis.ZP.rotation(f1));
        }
        ItemStackRenderState item = entityIn.item;
        item.submit(matrixStackIn, nodeCollector, entityIn.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        matrixStackIn.translate(0.0, 0.0, 0.09375F);
        matrixStackIn.popPose();
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
        s.knife = e.getItem();
        ItemStackRenderState itemstackrenderstate = new ItemStackRenderState();
        this.renderEntity.updateForNonLiving(itemstackrenderstate, e.getItem(), ItemDisplayContext.GROUND, e);
    }

    @Override
    public @NotNull KnifeRenderState createRenderState() {
        return new KnifeRenderState();
    }
}
