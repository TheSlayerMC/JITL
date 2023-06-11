package net.jitl.client.render.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ThrownItemRenderer<T extends Entity & ItemSupplier> extends EntityRenderer<T> {

   private final ItemRenderer itemRenderer;
   private final float scale;
   private final boolean fullBright;

   public ThrownItemRenderer(EntityRendererProvider.Context ctx, float scale, boolean lit) {
      super(ctx);
      this.itemRenderer = ctx.getItemRenderer();
      this.scale = scale;
      this.fullBright = lit;
   }

   public ThrownItemRenderer(EntityRendererProvider.Context context) {
      this(context, 1.0F, false);
   }

   @Override
   protected int getBlockLightLevel(@NotNull T entity, @NotNull BlockPos pos) {
      return this.fullBright ? 15 : super.getBlockLightLevel(entity, pos);
   }

   @Override
   public void render(T entity, float entityYaw_, float partialTicks, @NotNull PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
      if (entity.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(entity) < 12.25D)) {
         matrixStack.pushPose();
         matrixStack.scale(this.scale, this.scale, this.scale);
         matrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
         matrixStack.mulPose(Axis.YP.rotationDegrees(180.0F));
         this.itemRenderer.renderStatic(entity.getItem(), ItemDisplayContext.GROUND, packedLight, OverlayTexture.NO_OVERLAY, matrixStack, buffer, entity.level(), entity.getId());
         matrixStack.popPose();
         super.render(entity, entityYaw_, partialTicks, matrixStack, buffer, packedLight);
      }
   }

   @Override
   public @NotNull ResourceLocation getTextureLocation(@NotNull Entity entity) {
      return InventoryMenu.BLOCK_ATLAS;
   }
}