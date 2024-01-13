package net.jitl.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jitl.common.block.entity.base.JSpawnerEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BaseSpawner;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class JSpawnerRenderer implements BlockEntityRenderer<JSpawnerEntity> {

   private final EntityRenderDispatcher entityRenderer;

   public JSpawnerRenderer(BlockEntityRendererProvider.Context pContext) {
      this.entityRenderer = pContext.getEntityRenderer();
   }

   @Override
   public void render(JSpawnerEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
      pPoseStack.pushPose();
      pPoseStack.translate(0.5F, 0.0F, 0.5F);
      BaseSpawner basespawner = pBlockEntity.getSpawner();
      Entity entity = basespawner.getOrCreateDisplayEntity(Objects.requireNonNull(pBlockEntity.getLevel()), pBlockEntity.getBlockPos());
      if (entity != null) {
         float f = 0.53125F;
         float f1 = Math.max(entity.getBbWidth(), entity.getBbHeight());
         if ((double)f1 > 1.0D) {
            f /= f1;
         }

         pPoseStack.translate(0.0F, 0.4F, 0.0F);
         pPoseStack.mulPose(Axis.YP.rotationDegrees((float)Mth.lerp((double)pPartialTick, basespawner.getoSpin(), basespawner.getSpin()) * 10.0F));
         pPoseStack.translate(0.0F, -0.2F, 0.0F);
         pPoseStack.mulPose(Axis.XP.rotationDegrees(-30.0F));
         pPoseStack.scale(f, f, f);
         this.entityRenderer.render(entity, 0.0D, 0.0D, 0.0D, 0.0F, pPartialTick, pPoseStack, pBufferSource, pPackedLight);
      }
      pPoseStack.popPose();
   }
}