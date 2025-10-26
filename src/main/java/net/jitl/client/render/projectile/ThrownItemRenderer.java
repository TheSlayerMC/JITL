package net.jitl.client.render.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.projectile.state.JThrownItemRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.NotNull;

public class ThrownItemRenderer<T extends Entity & ItemSupplier> extends EntityRenderer<T, JThrownItemRenderState> {

   private final float scale;
   private final boolean fullBright;
   private final ItemModelResolver itemModelResolver;

   public ThrownItemRenderer(EntityRendererProvider.Context ctx, float scale, boolean lit) {
      super(ctx);
      this.scale = scale;
      this.fullBright = lit;
      this.itemModelResolver = ctx.getItemModelResolver();
   }

   public ThrownItemRenderer(EntityRendererProvider.Context context) {
      this(context, 1.0F, false);
   }

   @Override
   protected int getBlockLightLevel(@NotNull T entity, @NotNull BlockPos pos) {
      return this.fullBright ? 15 : super.getBlockLightLevel(entity, pos);
   }

    @Override
    public void submit(JThrownItemRenderState entityIn, PoseStack matrixStackIn, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
        matrixStackIn.pushPose();

        matrixStackIn.mulPose(cameraRenderState.orientation);

        ItemStackRenderState item = entityIn.renderState;
        matrixStackIn.scale(scale, scale, scale);
        item.submit(matrixStackIn, nodeCollector, entityIn.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        matrixStackIn.popPose();
    }

    @Override
    public void extractRenderState(T e, JThrownItemRenderState s, float f) {
        super.extractRenderState(e, s, f);
        s.tick = e.tickCount;
        s.xRot = e.xRotO;
        s.yRot = e.yRotO;
        s.level = e.level();
        s.id = e.getId();
        s.item = e.getItem();
        ItemStackRenderState itemstackrenderstate = new ItemStackRenderState();
        this.itemModelResolver.updateForNonLiving(itemstackrenderstate, e.getItem(), ItemDisplayContext.GROUND, e);
        s.renderState = itemstackrenderstate;
    }

   @Override
   public JThrownItemRenderState createRenderState() {
      return new JThrownItemRenderState();
   }
}