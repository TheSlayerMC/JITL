package net.jitl.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jitl.common.block.entity.base.JSpawnerEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.SpawnerRenderState;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class JSpawnerRenderer implements BlockEntityRenderer<JSpawnerEntity, SpawnerRenderState> {

   private final EntityRenderDispatcher entityRenderer;

   public JSpawnerRenderer(BlockEntityRendererProvider.Context pContext) {
      this.entityRenderer = pContext.entityRenderer();
   }

   @Override
    public SpawnerRenderState createRenderState() {
        return new SpawnerRenderState();
    }

    @Override
    public void extractRenderState(JSpawnerEntity p_446072_, SpawnerRenderState p_447179_, float p_446518_, Vec3 p_446190_, @Nullable ModelFeatureRenderer.CrumblingOverlay p_445699_) {
        BlockEntityRenderer.super.extractRenderState(p_446072_, p_447179_, p_446518_, p_446190_, p_445699_);
        if (p_446072_.getLevel() != null) {
            BaseSpawner basespawner = p_446072_.getSpawner();
            Entity entity = basespawner.getOrCreateDisplayEntity(p_446072_.getLevel(), p_446072_.getBlockPos());
            extractSpawnerData(p_447179_, p_446518_, entity, this.entityRenderer, basespawner.getOSpin(), basespawner.getSpin());
        }

    }

    private static void extractSpawnerData(SpawnerRenderState p_447339_, float p_445795_, @Nullable Entity p_447101_, EntityRenderDispatcher p_445573_, double p_445737_, double p_445854_) {
        if (p_447101_ != null) {
            p_447339_.displayEntity = p_445573_.extractEntity(p_447101_, p_445795_);
            p_447339_.displayEntity.lightCoords = p_447339_.lightCoords;
            p_447339_.spin = (float)Mth.lerp((double)p_445795_, p_445737_, p_445854_) * 10.0F;
            p_447339_.scale = 0.53125F;
            float f = Math.max(p_447101_.getBbWidth(), p_447101_.getBbHeight());
            if ((double)f > (double)1.0F) {
                p_447339_.scale /= f;
            }
        }
    }

    @Override
    public void submit(SpawnerRenderState p_446520_, PoseStack p_440479_, SubmitNodeCollector p_439725_, CameraRenderState p_451046_) {
        if (p_446520_.displayEntity != null) {
            submitEntityInSpawner(p_440479_, p_439725_, p_446520_.displayEntity, this.entityRenderer, p_446520_.spin, p_446520_.scale, p_451046_);
        }

    }

    public static void submitEntityInSpawner(PoseStack p_439357_, SubmitNodeCollector p_439460_, EntityRenderState p_446169_, EntityRenderDispatcher p_438990_, float p_439644_, float p_447153_, CameraRenderState p_451126_) {
        p_439357_.pushPose();
        p_439357_.translate(0.5F, 0.4F, 0.5F);
        p_439357_.mulPose(Axis.YP.rotationDegrees(p_439644_));
        p_439357_.translate(0.0F, -0.2F, 0.0F);
        p_439357_.mulPose(Axis.XP.rotationDegrees(-30.0F));
        p_439357_.scale(p_447153_, p_447153_, p_447153_);
        p_438990_.submit(p_446169_, p_451126_, (double)0.0F, (double)0.0F, (double)0.0F, p_439357_, p_439460_);
        p_439357_.popPose();
    }

    @Override
    public @NotNull AABB getRenderBoundingBox(JSpawnerEntity blockEntity) {
        BlockPos pos = blockEntity.getBlockPos();
        return new AABB((double)pos.getX() - (double)1.0F, (double)pos.getY() - (double)1.0F, (double)pos.getZ() - (double)1.0F, (double)pos.getX() + (double)2.0F, (double)pos.getY() + (double)2.0F, (double)pos.getZ() + (double)2.0F);
    }
}