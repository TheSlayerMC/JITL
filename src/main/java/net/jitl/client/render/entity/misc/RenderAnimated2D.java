package net.jitl.client.render.entity.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.jitl.core.helper.RandHelper;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class RenderAnimated2D<T extends Entity> extends EntityRenderer<T> {

    public String[] textures;
    public int animationSpeed;
    private final boolean fullBright = true;

    public RenderAnimated2D(EntityRendererProvider.Context context, int animationSpeed, String... textures) {
        super(context);
        this.textures = textures;
        this.animationSpeed = animationSpeed;
    }

    @Override
    public void render(@NotNull T entityIn, float entityYaw, float partialTicks, @NotNull PoseStack matrixStackIn, @NotNull MultiBufferSource bufferIn, int packedLightIn) {
        if (entityIn.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(entityIn) < 12.25D)) {
            render(entityIn, matrixStackIn, bufferIn, packedLightIn);
        }
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    protected int getBlockLightLevel(@NotNull T entity, @NotNull BlockPos pos) {
        return this.fullBright ? 15 : super.getBlockLightLevel(entity, pos);
    }

    private void render(@NotNull T entityIn, @NotNull PoseStack matrixStackIn, @NotNull MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        float scale = 0.5F;
        matrixStackIn.scale(scale, scale, scale);
        matrixStackIn.mulPose(this.entityRenderDispatcher.cameraOrientation());
        matrixStackIn.mulPose(Axis.YP.rotationDegrees(180.0F));
        matrixStackIn.translate(0, 0.5D, 0);
        float scale1 = 1.0F;
        matrixStackIn.scale(scale1, scale1, scale1);

        PoseStack.Pose lastMatrix = matrixStackIn.last();
        Matrix4f pose = lastMatrix.pose();
        Matrix3f normal = lastMatrix.normal();
        VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderType.entityTranslucent(getTextureLocation(entityIn)));
        vertex(ivertexbuilder, pose, normal, packedLightIn, 0, 0, 0, 1);
        vertex(ivertexbuilder, pose, normal, packedLightIn, 1, 0, 1, 1);
        vertex(ivertexbuilder, pose, normal, packedLightIn, 1, 1, 1, 0);
        vertex(ivertexbuilder, pose, normal, packedLightIn, 0, 1, 0, 0);

        matrixStackIn.popPose();
    }

    private static void vertex(VertexConsumer builder, Matrix4f pose, Matrix3f normal, int lightmapUV, float x, float y, int u, int v) {
        builder.addVertex(pose, x - 0.5F, y - 0.5F, 0.0F).setColor(255, 255, 255, 255).setUv((float) u, (float) v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(lightmapUV).setNormal(0.0F, 1.0F, 0.0F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(T t) {
        RandomSource r = RandomSource.create();
        if(t.tickCount % animationSpeed == 0) {
            String name = RandHelper.chooseEqual(r, textures);
            return JITL.rl("textures/entity/" + name + ".png");
        }
        if(t.tickCount % (animationSpeed / 2) == 0) {
            String name = RandHelper.chooseEqual(r, textures);
            return JITL.rl("textures/entity/" + name + ".png");
        } else {
            return JITL.rl("textures/entity/" + textures[0] + ".png");
        }
    }
}
