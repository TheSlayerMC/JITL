package net.jitl.client.render.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jitl.core.init.internal.JItems;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.state.ThrownItemRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class ThrownItemRenderer<T extends Entity & ItemSupplier> extends EntityRenderer<T, ThrownItemRenderState> {

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

   public void extractRenderState(T p_364505_, ThrownItemRenderState p_363251_, float p_362608_) {
      super.extractRenderState(p_364505_, p_363251_, p_362608_);
      this.itemModelResolver.updateForNonLiving(p_363251_.item, p_364505_.getItem(), ItemDisplayContext.GROUND, p_364505_);
   }

   @Override
   public void render(ThrownItemRenderState entity, @NotNull PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
      matrixStack.pushPose();
      matrixStack.scale(this.scale, this.scale, this.scale);
      matrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
      entity.item.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
      matrixStack.popPose();
      super.render(entity, matrixStack, buffer, packedLight);
   }

   @Override
   public ThrownItemRenderState createRenderState() {
      return new ThrownItemRenderState();
   }
}