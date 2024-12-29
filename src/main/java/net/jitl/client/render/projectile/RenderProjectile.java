package net.jitl.client.render.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.jitl.client.render.projectile.render_state.TwoDRenderState;
import net.jitl.core.init.internal.JItems;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ThrownItemRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class RenderProjectile<T extends Entity> extends EntityRenderer<T, TwoDRenderState> {

    private final ItemModelResolver itemModelResolver;
    private final ResourceLocation texture;

    public RenderProjectile(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context);
        this.texture = texture;
        this.itemModelResolver = context.getItemModelResolver();
    }

    @Override
    public void render(@NotNull TwoDRenderState entityIn, @NotNull PoseStack matrixStackIn, @NotNull MultiBufferSource bufferIn, int packedLightIn) {
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
        VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderType.entityCutoutNoCull(entityIn.texture));
        vertex(ivertexbuilder, pose, normal, packedLightIn, 0, 0, 0, 1);
        vertex(ivertexbuilder, pose, normal, packedLightIn, 1, 0, 1, 1);
        vertex(ivertexbuilder, pose, normal, packedLightIn, 1, 1, 1, 0);
        vertex(ivertexbuilder, pose, normal, packedLightIn, 0, 1, 0, 0);

        matrixStackIn.popPose();
        super.render(entityIn, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public void extractRenderState(T entity, TwoDRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        this.itemModelResolver.updateForNonLiving(state.item, JItems.AIRMELON.toStack(), ItemDisplayContext.GROUND, entity);
        state.texture = this.texture;
    }

    @Override
    public @NotNull TwoDRenderState createRenderState() {
        return new TwoDRenderState();
    }

    @Override
    protected int getBlockLightLevel(@NotNull T entity, @NotNull BlockPos pos) {
        return 15;
    }

    private static void vertex(VertexConsumer builder, Matrix4f pose, Matrix3f normal, int lightmapUV, float x, float y, int u, int v) {
        builder.addVertex(pose, x - 0.5F, y - 0.5F, 0.0F).setColor(255, 255, 255, 255).setUv((float) u, (float) v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(lightmapUV).setNormal(0.0F, 1.0F, 0.0F);
    }
}
