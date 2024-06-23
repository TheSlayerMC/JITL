package net.jitl.client.render.world;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

import javax.annotation.Nullable;

public class JCloudRenderer {

    private CloudStatus prevCloudsType;
    private Vec3 prevCloudColor = Vec3.ZERO;
    private VertexBuffer cloudBuffer;
    private int prevCloudX = Integer.MIN_VALUE;
    private int prevCloudY = Integer.MIN_VALUE;
    private int prevCloudZ = Integer.MIN_VALUE;
    private boolean generateClouds = true;
    private final ResourceLocation CLOUDS_LOCATION;

    public JCloudRenderer(ResourceLocation texture) {
        this.CLOUDS_LOCATION = texture;
    }

    private static RenderType.CompositeRenderType createClouds(boolean pColor) {
        return RenderType.create(
                "clouds",
                DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL,
                VertexFormat.Mode.QUADS,
                786432,
                false,
                false,
                RenderType.CompositeState.builder()
                        .setShaderState(RENDERTYPE_CLOUDS_SHADER)
                        .setTextureState(new RenderStateShard.TextureStateShard(CLOUDS_LOCATION, false, false))
                        .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                        .setCullState(NO_CULL)
                        .setWriteMaskState(pColor ? DEPTH_WRITE : COLOR_DEPTH_WRITE)
                        .setOutputState(CLOUDS_TARGET)
                        .createCompositeState(true)
        );
    }

    public void render(ClientLevel level, int ticks, PoseStack pPoseStack, Matrix4f pProjectionMatrix, Matrix4f pFrustrumMatrix, float pPartialTick, double pCamX, double pCamY, double pCamZ) {
        Minecraft minecraft = Minecraft.getInstance();
        float f = level.effects().getCloudHeight();
        if (!Float.isNaN(f)) {
            float f1 = 12.0F;
            float f2 = 4.0F;
            double d0 = 2.0E-4;
            double d1 = (double)(((float)ticks + pPartialTick) * 0.03F);
            double d2 = (pCamX + d1) / 12.0;
            double d3 = (double)(f - (float)pCamY + 0.33F);
            double d4 = pCamZ / 12.0 + 0.33F;
            d2 -= (double)(Mth.floor(d2 / 2048.0) * 2048);
            d4 -= (double)(Mth.floor(d4 / 2048.0) * 2048);
            float f3 = (float)(d2 - (double)Mth.floor(d2));
            float f4 = (float)(d3 / 4.0 - (double)Mth.floor(d3 / 4.0)) * 4.0F;
            float f5 = (float)(d4 - (double)Mth.floor(d4));
            Vec3 vec3 = level.getCloudColor(pPartialTick);
            int i = (int)Math.floor(d2);
            int j = (int)Math.floor(d3 / 4.0);
            int k = (int)Math.floor(d4);
            if (i != this.prevCloudX
                    || j != this.prevCloudY
                    || k != this.prevCloudZ
                    || minecraft.options.getCloudsType() != this.prevCloudsType
                    || this.prevCloudColor.distanceToSqr(vec3) > 2.0E-4) {
                this.prevCloudX = i;
                this.prevCloudY = j;
                this.prevCloudZ = k;
                this.prevCloudColor = vec3;
                this.prevCloudsType = minecraft.options.getCloudsType();
                this.generateClouds = true;
            }

            if (this.generateClouds) {
                this.generateClouds = false;
                if (this.cloudBuffer != null) {
                    this.cloudBuffer.close();
                }

                this.cloudBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
                this.cloudBuffer.bind();
                this.cloudBuffer.upload(this.buildClouds(Tesselator.getInstance(), d2, d3, d4, vec3));
                VertexBuffer.unbind();
            }

            FogRenderer.levelFogColor();
            pPoseStack.pushPose();
            pPoseStack.mulPose(pProjectionMatrix);
            pPoseStack.scale(12.0F, 1.0F, 12.0F);
            pPoseStack.translate(-f3, f4, -f5);
            if (this.cloudBuffer != null) {
                this.cloudBuffer.bind();
                int l = this.prevCloudsType == CloudStatus.FANCY ? 0 : 1;

                for (int i1 = l; i1 < 2; i1++) {
                    RenderType rendertype = i1 == 0 ? RenderType.cloudsDepthOnly() : RenderType.clouds();
                    rendertype.setupRenderState();
                    ShaderInstance shaderinstance = RenderSystem.getShader();
                    this.cloudBuffer.drawWithShader(pPoseStack.last().pose(), pFrustrumMatrix, shaderinstance);
                    rendertype.clearRenderState();
                }

                VertexBuffer.unbind();
            }

            pPoseStack.popPose();
        }
    }

    private MeshData buildClouds(Tesselator p_350398_, double p_234263_, double p_234264_, double p_234265_, Vec3 p_234266_) {
        float f3 = (float)Mth.floor(p_234263_) * 0.00390625F;
        float f4 = (float)Mth.floor(p_234265_) * 0.00390625F;
        float f5 = (float)p_234266_.x;
        float f6 = (float)p_234266_.y;
        float f7 = (float)p_234266_.z;
        float f8 = f5 * 0.9F;
        float f9 = f6 * 0.9F;
        float f10 = f7 * 0.9F;
        float f11 = f5 * 0.7F;
        float f12 = f6 * 0.7F;
        float f13 = f7 * 0.7F;
        float f14 = f5 * 0.8F;
        float f15 = f6 * 0.8F;
        float f16 = f7 * 0.8F;
        BufferBuilder bufferbuilder = p_350398_.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL);
        float f17 = (float)Math.floor(p_234264_ / 4.0) * 4.0F;
        if (this.prevCloudsType == CloudStatus.FANCY) {
            for (int k = -3; k <= 4; k++) {
                for (int l = -3; l <= 4; l++) {
                    float f18 = (float)(k * 8);
                    float f19 = (float)(l * 8);
                    if (f17 > -5.0F) {
                        bufferbuilder.addVertex(f18 + 0.0F, f17 + 0.0F, f19 + 8.0F)
                                .setUv((f18 + 0.0F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4)
                                .setColor(f11, f12, f13, 0.8F)
                                .setNormal(0.0F, -1.0F, 0.0F);
                        bufferbuilder.addVertex(f18 + 8.0F, f17 + 0.0F, f19 + 8.0F)
                                .setUv((f18 + 8.0F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4)
                                .setColor(f11, f12, f13, 0.8F)
                                .setNormal(0.0F, -1.0F, 0.0F);
                        bufferbuilder.addVertex(f18 + 8.0F, f17 + 0.0F, f19 + 0.0F)
                                .setUv((f18 + 8.0F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4)
                                .setColor(f11, f12, f13, 0.8F)
                                .setNormal(0.0F, -1.0F, 0.0F);
                        bufferbuilder.addVertex(f18 + 0.0F, f17 + 0.0F, f19 + 0.0F)
                                .setUv((f18 + 0.0F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4)
                                .setColor(f11, f12, f13, 0.8F)
                                .setNormal(0.0F, -1.0F, 0.0F);
                    }

                    if (f17 <= 5.0F) {
                        bufferbuilder.addVertex(f18 + 0.0F, f17 + 4.0F - 9.765625E-4F, f19 + 8.0F)
                                .setUv((f18 + 0.0F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4)
                                .setColor(f5, f6, f7, 0.8F)
                                .setNormal(0.0F, 1.0F, 0.0F);
                        bufferbuilder.addVertex(f18 + 8.0F, f17 + 4.0F - 9.765625E-4F, f19 + 8.0F)
                                .setUv((f18 + 8.0F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4)
                                .setColor(f5, f6, f7, 0.8F)
                                .setNormal(0.0F, 1.0F, 0.0F);
                        bufferbuilder.addVertex(f18 + 8.0F, f17 + 4.0F - 9.765625E-4F, f19 + 0.0F)
                                .setUv((f18 + 8.0F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4)
                                .setColor(f5, f6, f7, 0.8F)
                                .setNormal(0.0F, 1.0F, 0.0F);
                        bufferbuilder.addVertex(f18 + 0.0F, f17 + 4.0F - 9.765625E-4F, f19 + 0.0F)
                                .setUv((f18 + 0.0F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4)
                                .setColor(f5, f6, f7, 0.8F)
                                .setNormal(0.0F, 1.0F, 0.0F);
                    }

                    if (k > -1) {
                        for (int i1 = 0; i1 < 8; i1++) {
                            bufferbuilder.addVertex(f18 + (float)i1 + 0.0F, f17 + 0.0F, f19 + 8.0F)
                                    .setUv((f18 + (float)i1 + 0.5F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4)
                                    .setColor(f8, f9, f10, 0.8F)
                                    .setNormal(-1.0F, 0.0F, 0.0F);
                            bufferbuilder.addVertex(f18 + (float)i1 + 0.0F, f17 + 4.0F, f19 + 8.0F)
                                    .setUv((f18 + (float)i1 + 0.5F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4)
                                    .setColor(f8, f9, f10, 0.8F)
                                    .setNormal(-1.0F, 0.0F, 0.0F);
                            bufferbuilder.addVertex(f18 + (float)i1 + 0.0F, f17 + 4.0F, f19 + 0.0F)
                                    .setUv((f18 + (float)i1 + 0.5F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4)
                                    .setColor(f8, f9, f10, 0.8F)
                                    .setNormal(-1.0F, 0.0F, 0.0F);
                            bufferbuilder.addVertex(f18 + (float)i1 + 0.0F, f17 + 0.0F, f19 + 0.0F)
                                    .setUv((f18 + (float)i1 + 0.5F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4)
                                    .setColor(f8, f9, f10, 0.8F)
                                    .setNormal(-1.0F, 0.0F, 0.0F);
                        }
                    }

                    if (k <= 1) {
                        for (int j2 = 0; j2 < 8; j2++) {
                            bufferbuilder.addVertex(f18 + (float)j2 + 1.0F - 9.765625E-4F, f17 + 0.0F, f19 + 8.0F)
                                    .setUv((f18 + (float)j2 + 0.5F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4)
                                    .setColor(f8, f9, f10, 0.8F)
                                    .setNormal(1.0F, 0.0F, 0.0F);
                            bufferbuilder.addVertex(f18 + (float)j2 + 1.0F - 9.765625E-4F, f17 + 4.0F, f19 + 8.0F)
                                    .setUv((f18 + (float)j2 + 0.5F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4)
                                    .setColor(f8, f9, f10, 0.8F)
                                    .setNormal(1.0F, 0.0F, 0.0F);
                            bufferbuilder.addVertex(f18 + (float)j2 + 1.0F - 9.765625E-4F, f17 + 4.0F, f19 + 0.0F)
                                    .setUv((f18 + (float)j2 + 0.5F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4)
                                    .setColor(f8, f9, f10, 0.8F)
                                    .setNormal(1.0F, 0.0F, 0.0F);
                            bufferbuilder.addVertex(f18 + (float)j2 + 1.0F - 9.765625E-4F, f17 + 0.0F, f19 + 0.0F)
                                    .setUv((f18 + (float)j2 + 0.5F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4)
                                    .setColor(f8, f9, f10, 0.8F)
                                    .setNormal(1.0F, 0.0F, 0.0F);
                        }
                    }

                    if (l > -1) {
                        for (int k2 = 0; k2 < 8; k2++) {
                            bufferbuilder.addVertex(f18 + 0.0F, f17 + 4.0F, f19 + (float)k2 + 0.0F)
                                    .setUv((f18 + 0.0F) * 0.00390625F + f3, (f19 + (float)k2 + 0.5F) * 0.00390625F + f4)
                                    .setColor(f14, f15, f16, 0.8F)
                                    .setNormal(0.0F, 0.0F, -1.0F);
                            bufferbuilder.addVertex(f18 + 8.0F, f17 + 4.0F, f19 + (float)k2 + 0.0F)
                                    .setUv((f18 + 8.0F) * 0.00390625F + f3, (f19 + (float)k2 + 0.5F) * 0.00390625F + f4)
                                    .setColor(f14, f15, f16, 0.8F)
                                    .setNormal(0.0F, 0.0F, -1.0F);
                            bufferbuilder.addVertex(f18 + 8.0F, f17 + 0.0F, f19 + (float)k2 + 0.0F)
                                    .setUv((f18 + 8.0F) * 0.00390625F + f3, (f19 + (float)k2 + 0.5F) * 0.00390625F + f4)
                                    .setColor(f14, f15, f16, 0.8F)
                                    .setNormal(0.0F, 0.0F, -1.0F);
                            bufferbuilder.addVertex(f18 + 0.0F, f17 + 0.0F, f19 + (float)k2 + 0.0F)
                                    .setUv((f18 + 0.0F) * 0.00390625F + f3, (f19 + (float)k2 + 0.5F) * 0.00390625F + f4)
                                    .setColor(f14, f15, f16, 0.8F)
                                    .setNormal(0.0F, 0.0F, -1.0F);
                        }
                    }

                    if (l <= 1) {
                        for (int l2 = 0; l2 < 8; l2++) {
                            bufferbuilder.addVertex(f18 + 0.0F, f17 + 4.0F, f19 + (float)l2 + 1.0F - 9.765625E-4F)
                                    .setUv((f18 + 0.0F) * 0.00390625F + f3, (f19 + (float)l2 + 0.5F) * 0.00390625F + f4)
                                    .setColor(f14, f15, f16, 0.8F)
                                    .setNormal(0.0F, 0.0F, 1.0F);
                            bufferbuilder.addVertex(f18 + 8.0F, f17 + 4.0F, f19 + (float)l2 + 1.0F - 9.765625E-4F)
                                    .setUv((f18 + 8.0F) * 0.00390625F + f3, (f19 + (float)l2 + 0.5F) * 0.00390625F + f4)
                                    .setColor(f14, f15, f16, 0.8F)
                                    .setNormal(0.0F, 0.0F, 1.0F);
                            bufferbuilder.addVertex(f18 + 8.0F, f17 + 0.0F, f19 + (float)l2 + 1.0F - 9.765625E-4F)
                                    .setUv((f18 + 8.0F) * 0.00390625F + f3, (f19 + (float)l2 + 0.5F) * 0.00390625F + f4)
                                    .setColor(f14, f15, f16, 0.8F)
                                    .setNormal(0.0F, 0.0F, 1.0F);
                            bufferbuilder.addVertex(f18 + 0.0F, f17 + 0.0F, f19 + (float)l2 + 1.0F - 9.765625E-4F)
                                    .setUv((f18 + 0.0F) * 0.00390625F + f3, (f19 + (float)l2 + 0.5F) * 0.00390625F + f4)
                                    .setColor(f14, f15, f16, 0.8F)
                                    .setNormal(0.0F, 0.0F, 1.0F);
                        }
                    }
                }
            }
        } else {
            int j1 = 1;
            int k1 = 32;

            for (int l1 = -32; l1 < 32; l1 += 32) {
                for (int i2 = -32; i2 < 32; i2 += 32) {
                    bufferbuilder.addVertex((float)(l1 + 0), f17, (float)(i2 + 32))
                            .setUv((float)(l1 + 0) * 0.00390625F + f3, (float)(i2 + 32) * 0.00390625F + f4)
                            .setColor(f5, f6, f7, 0.8F)
                            .setNormal(0.0F, -1.0F, 0.0F);
                    bufferbuilder.addVertex((float)(l1 + 32), f17, (float)(i2 + 32))
                            .setUv((float)(l1 + 32) * 0.00390625F + f3, (float)(i2 + 32) * 0.00390625F + f4)
                            .setColor(f5, f6, f7, 0.8F)
                            .setNormal(0.0F, -1.0F, 0.0F);
                    bufferbuilder.addVertex((float)(l1 + 32), f17, (float)(i2 + 0))
                            .setUv((float)(l1 + 32) * 0.00390625F + f3, (float)(i2 + 0) * 0.00390625F + f4)
                            .setColor(f5, f6, f7, 0.8F)
                            .setNormal(0.0F, -1.0F, 0.0F);
                    bufferbuilder.addVertex((float)(l1 + 0), f17, (float)(i2 + 0))
                            .setUv((float)(l1 + 0) * 0.00390625F + f3, (float)(i2 + 0) * 0.00390625F + f4)
                            .setColor(f5, f6, f7, 0.8F)
                            .setNormal(0.0F, -1.0F, 0.0F);
                }
            }
        }

        return bufferbuilder.buildOrThrow();
    }
}
