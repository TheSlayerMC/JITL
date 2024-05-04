package net.jitl.client.render.world;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

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

    public void render(ClientLevel level, int ticks, PoseStack pPoseStack, Matrix4f projectionMatrix, Matrix4f frustrumMatrix, float partialTicks, double pCamX, double pCamY, double pCamZ) {
        float f = level.effects().getCloudHeight();
        Minecraft minecraft = Minecraft.getInstance();
        if (!Float.isNaN(f)) {
            float f1 = 12.0F;
            float f2 = 4.0F;
            double d0 = 2.0E-4;
            double d1 = (double)(((float)ticks + partialTicks) * 0.03F);
            double d2 = (pCamX + d1) / 12.0;
            double d3 = (double)(f - (float)pCamY + 0.33F);
            double d4 = pCamZ / 12.0 + 0.33F;
            d2 -= (double)(Mth.floor(d2 / 2048.0) * 2048);
            d4 -= (double)(Mth.floor(d4 / 2048.0) * 2048);
            float f3 = (float)(d2 - (double)Mth.floor(d2));
            float f4 = (float)(d3 / 4.0 - (double)Mth.floor(d3 / 4.0)) * 4.0F;
            float f5 = (float)(d4 - (double)Mth.floor(d4));
            Vec3 vec3 = level.getCloudColor(partialTicks);
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
                BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
                if (this.cloudBuffer != null) {
                    this.cloudBuffer.close();
                }

                this.cloudBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
                BufferBuilder.RenderedBuffer bufferbuilder$renderedbuffer = this.buildClouds(bufferbuilder, d2, d3, d4, vec3);
                this.cloudBuffer.bind();
                this.cloudBuffer.upload(bufferbuilder$renderedbuffer);
                VertexBuffer.unbind();
            }
            RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
            RenderSystem.setShaderTexture(0, CLOUDS_LOCATION);
            FogRenderer.levelFogColor();
            pPoseStack.pushPose();
            pPoseStack.mulPose(projectionMatrix);
            pPoseStack.scale(12.0F, 1.0F, 12.0F);
            pPoseStack.translate(-f3, f4, -f5);
            if (this.cloudBuffer != null) {
                this.cloudBuffer.bind();
                int l = this.prevCloudsType == CloudStatus.FANCY ? 0 : 1;

                for (int i1 = l; i1 < 2; i1++) {
                    RenderType rendertype = i1 == 0 ? RenderType.cloudsDepthOnly() : RenderType.clouds();
                    rendertype.setupRenderState();
                    ShaderInstance shaderinstance = RenderSystem.getShader();
                    this.cloudBuffer.drawWithShader(pPoseStack.last().pose(), frustrumMatrix, shaderinstance);
                    rendertype.clearRenderState();
                }

                VertexBuffer.unbind();
            }

            pPoseStack.popPose();
        }
    }

    private BufferBuilder.RenderedBuffer buildClouds(BufferBuilder pBuilder, double pX, double pY, double pZ, Vec3 pCloudColor) {
        float f = 4.0F;
        float f1 = 0.00390625F;
        int i = 8;
        int j = 4;
        float f2 = 9.765625E-4F;
        float f3 = (float)Mth.floor(pX) * 0.00390625F;
        float f4 = (float)Mth.floor(pZ) * 0.00390625F;
        float f5 = (float)pCloudColor.x;
        float f6 = (float)pCloudColor.y;
        float f7 = (float)pCloudColor.z;
        float f8 = f5 * 0.9F;
        float f9 = f6 * 0.9F;
        float f10 = f7 * 0.9F;
        float f11 = f5 * 0.7F;
        float f12 = f6 * 0.7F;
        float f13 = f7 * 0.7F;
        float f14 = f5 * 0.8F;
        float f15 = f6 * 0.8F;
        float f16 = f7 * 0.8F;
        pBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL);
        float f17 = (float)Math.floor(pY / 4.0) * 4.0F;
        if (this.prevCloudsType == CloudStatus.FANCY) {
            for (int k = -3; k <= 4; k++) {
                for (int l = -3; l <= 4; l++) {
                    float f18 = (float)(k * 8);
                    float f19 = (float)(l * 8);
                    if (f17 > -5.0F) {
                        pBuilder.vertex((double)(f18 + 0.0F), (double)(f17 + 0.0F), (double)(f19 + 8.0F))
                                .uv((f18 + 0.0F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4)
                                .color(f11, f12, f13, 0.8F)
                                .normal(0.0F, -1.0F, 0.0F)
                                .endVertex();
                        pBuilder.vertex((double)(f18 + 8.0F), (double)(f17 + 0.0F), (double)(f19 + 8.0F))
                                .uv((f18 + 8.0F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4)
                                .color(f11, f12, f13, 0.8F)
                                .normal(0.0F, -1.0F, 0.0F)
                                .endVertex();
                        pBuilder.vertex((double)(f18 + 8.0F), (double)(f17 + 0.0F), (double)(f19 + 0.0F))
                                .uv((f18 + 8.0F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4)
                                .color(f11, f12, f13, 0.8F)
                                .normal(0.0F, -1.0F, 0.0F)
                                .endVertex();
                        pBuilder.vertex((double)(f18 + 0.0F), (double)(f17 + 0.0F), (double)(f19 + 0.0F))
                                .uv((f18 + 0.0F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4)
                                .color(f11, f12, f13, 0.8F)
                                .normal(0.0F, -1.0F, 0.0F)
                                .endVertex();
                    }

                    if (f17 <= 5.0F) {
                        pBuilder.vertex((double)(f18 + 0.0F), (double)(f17 + 4.0F - 9.765625E-4F), (double)(f19 + 8.0F))
                                .uv((f18 + 0.0F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4)
                                .color(f5, f6, f7, 0.8F)
                                .normal(0.0F, 1.0F, 0.0F)
                                .endVertex();
                        pBuilder.vertex((double)(f18 + 8.0F), (double)(f17 + 4.0F - 9.765625E-4F), (double)(f19 + 8.0F))
                                .uv((f18 + 8.0F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4)
                                .color(f5, f6, f7, 0.8F)
                                .normal(0.0F, 1.0F, 0.0F)
                                .endVertex();
                        pBuilder.vertex((double)(f18 + 8.0F), (double)(f17 + 4.0F - 9.765625E-4F), (double)(f19 + 0.0F))
                                .uv((f18 + 8.0F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4)
                                .color(f5, f6, f7, 0.8F)
                                .normal(0.0F, 1.0F, 0.0F)
                                .endVertex();
                        pBuilder.vertex((double)(f18 + 0.0F), (double)(f17 + 4.0F - 9.765625E-4F), (double)(f19 + 0.0F))
                                .uv((f18 + 0.0F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4)
                                .color(f5, f6, f7, 0.8F)
                                .normal(0.0F, 1.0F, 0.0F)
                                .endVertex();
                    }

                    if (k > -1) {
                        for (int i1 = 0; i1 < 8; i1++) {
                            pBuilder.vertex((double)(f18 + (float)i1 + 0.0F), (double)(f17 + 0.0F), (double)(f19 + 8.0F))
                                    .uv((f18 + (float)i1 + 0.5F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4)
                                    .color(f8, f9, f10, 0.8F)
                                    .normal(-1.0F, 0.0F, 0.0F)
                                    .endVertex();
                            pBuilder.vertex((double)(f18 + (float)i1 + 0.0F), (double)(f17 + 4.0F), (double)(f19 + 8.0F))
                                    .uv((f18 + (float)i1 + 0.5F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4)
                                    .color(f8, f9, f10, 0.8F)
                                    .normal(-1.0F, 0.0F, 0.0F)
                                    .endVertex();
                            pBuilder.vertex((double)(f18 + (float)i1 + 0.0F), (double)(f17 + 4.0F), (double)(f19 + 0.0F))
                                    .uv((f18 + (float)i1 + 0.5F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4)
                                    .color(f8, f9, f10, 0.8F)
                                    .normal(-1.0F, 0.0F, 0.0F)
                                    .endVertex();
                            pBuilder.vertex((double)(f18 + (float)i1 + 0.0F), (double)(f17 + 0.0F), (double)(f19 + 0.0F))
                                    .uv((f18 + (float)i1 + 0.5F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4)
                                    .color(f8, f9, f10, 0.8F)
                                    .normal(-1.0F, 0.0F, 0.0F)
                                    .endVertex();
                        }
                    }

                    if (k <= 1) {
                        for (int j2 = 0; j2 < 8; j2++) {
                            pBuilder.vertex((double)(f18 + (float)j2 + 1.0F - 9.765625E-4F), (double)(f17 + 0.0F), (double)(f19 + 8.0F))
                                    .uv((f18 + (float)j2 + 0.5F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4)
                                    .color(f8, f9, f10, 0.8F)
                                    .normal(1.0F, 0.0F, 0.0F)
                                    .endVertex();
                            pBuilder.vertex((double)(f18 + (float)j2 + 1.0F - 9.765625E-4F), (double)(f17 + 4.0F), (double)(f19 + 8.0F))
                                    .uv((f18 + (float)j2 + 0.5F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4)
                                    .color(f8, f9, f10, 0.8F)
                                    .normal(1.0F, 0.0F, 0.0F)
                                    .endVertex();
                            pBuilder.vertex((double)(f18 + (float)j2 + 1.0F - 9.765625E-4F), (double)(f17 + 4.0F), (double)(f19 + 0.0F))
                                    .uv((f18 + (float)j2 + 0.5F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4)
                                    .color(f8, f9, f10, 0.8F)
                                    .normal(1.0F, 0.0F, 0.0F)
                                    .endVertex();
                            pBuilder.vertex((double)(f18 + (float)j2 + 1.0F - 9.765625E-4F), (double)(f17 + 0.0F), (double)(f19 + 0.0F))
                                    .uv((f18 + (float)j2 + 0.5F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4)
                                    .color(f8, f9, f10, 0.8F)
                                    .normal(1.0F, 0.0F, 0.0F)
                                    .endVertex();
                        }
                    }

                    if (l > -1) {
                        for (int k2 = 0; k2 < 8; k2++) {
                            pBuilder.vertex((double)(f18 + 0.0F), (double)(f17 + 4.0F), (double)(f19 + (float)k2 + 0.0F))
                                    .uv((f18 + 0.0F) * 0.00390625F + f3, (f19 + (float)k2 + 0.5F) * 0.00390625F + f4)
                                    .color(f14, f15, f16, 0.8F)
                                    .normal(0.0F, 0.0F, -1.0F)
                                    .endVertex();
                            pBuilder.vertex((double)(f18 + 8.0F), (double)(f17 + 4.0F), (double)(f19 + (float)k2 + 0.0F))
                                    .uv((f18 + 8.0F) * 0.00390625F + f3, (f19 + (float)k2 + 0.5F) * 0.00390625F + f4)
                                    .color(f14, f15, f16, 0.8F)
                                    .normal(0.0F, 0.0F, -1.0F)
                                    .endVertex();
                            pBuilder.vertex((double)(f18 + 8.0F), (double)(f17 + 0.0F), (double)(f19 + (float)k2 + 0.0F))
                                    .uv((f18 + 8.0F) * 0.00390625F + f3, (f19 + (float)k2 + 0.5F) * 0.00390625F + f4)
                                    .color(f14, f15, f16, 0.8F)
                                    .normal(0.0F, 0.0F, -1.0F)
                                    .endVertex();
                            pBuilder.vertex((double)(f18 + 0.0F), (double)(f17 + 0.0F), (double)(f19 + (float)k2 + 0.0F))
                                    .uv((f18 + 0.0F) * 0.00390625F + f3, (f19 + (float)k2 + 0.5F) * 0.00390625F + f4)
                                    .color(f14, f15, f16, 0.8F)
                                    .normal(0.0F, 0.0F, -1.0F)
                                    .endVertex();
                        }
                    }

                    if (l <= 1) {
                        for (int l2 = 0; l2 < 8; l2++) {
                            pBuilder.vertex((double)(f18 + 0.0F), (double)(f17 + 4.0F), (double)(f19 + (float)l2 + 1.0F - 9.765625E-4F))
                                    .uv((f18 + 0.0F) * 0.00390625F + f3, (f19 + (float)l2 + 0.5F) * 0.00390625F + f4)
                                    .color(f14, f15, f16, 0.8F)
                                    .normal(0.0F, 0.0F, 1.0F)
                                    .endVertex();
                            pBuilder.vertex((double)(f18 + 8.0F), (double)(f17 + 4.0F), (double)(f19 + (float)l2 + 1.0F - 9.765625E-4F))
                                    .uv((f18 + 8.0F) * 0.00390625F + f3, (f19 + (float)l2 + 0.5F) * 0.00390625F + f4)
                                    .color(f14, f15, f16, 0.8F)
                                    .normal(0.0F, 0.0F, 1.0F)
                                    .endVertex();
                            pBuilder.vertex((double)(f18 + 8.0F), (double)(f17 + 0.0F), (double)(f19 + (float)l2 + 1.0F - 9.765625E-4F))
                                    .uv((f18 + 8.0F) * 0.00390625F + f3, (f19 + (float)l2 + 0.5F) * 0.00390625F + f4)
                                    .color(f14, f15, f16, 0.8F)
                                    .normal(0.0F, 0.0F, 1.0F)
                                    .endVertex();
                            pBuilder.vertex((double)(f18 + 0.0F), (double)(f17 + 0.0F), (double)(f19 + (float)l2 + 1.0F - 9.765625E-4F))
                                    .uv((f18 + 0.0F) * 0.00390625F + f3, (f19 + (float)l2 + 0.5F) * 0.00390625F + f4)
                                    .color(f14, f15, f16, 0.8F)
                                    .normal(0.0F, 0.0F, 1.0F)
                                    .endVertex();
                        }
                    }
                }
            }
        } else {
            int j1 = 1;
            int k1 = 32;

            for (int l1 = -32; l1 < 32; l1 += 32) {
                for (int i2 = -32; i2 < 32; i2 += 32) {
                    pBuilder.vertex((double)(l1 + 0), (double)f17, (double)(i2 + 32))
                            .uv((float)(l1 + 0) * 0.00390625F + f3, (float)(i2 + 32) * 0.00390625F + f4)
                            .color(f5, f6, f7, 0.8F)
                            .normal(0.0F, -1.0F, 0.0F)
                            .endVertex();
                    pBuilder.vertex((double)(l1 + 32), (double)f17, (double)(i2 + 32))
                            .uv((float)(l1 + 32) * 0.00390625F + f3, (float)(i2 + 32) * 0.00390625F + f4)
                            .color(f5, f6, f7, 0.8F)
                            .normal(0.0F, -1.0F, 0.0F)
                            .endVertex();
                    pBuilder.vertex((double)(l1 + 32), (double)f17, (double)(i2 + 0))
                            .uv((float)(l1 + 32) * 0.00390625F + f3, (float)(i2 + 0) * 0.00390625F + f4)
                            .color(f5, f6, f7, 0.8F)
                            .normal(0.0F, -1.0F, 0.0F)
                            .endVertex();
                    pBuilder.vertex((double)(l1 + 0), (double)f17, (double)(i2 + 0))
                            .uv((float)(l1 + 0) * 0.00390625F + f3, (float)(i2 + 0) * 0.00390625F + f4)
                            .color(f5, f6, f7, 0.8F)
                            .normal(0.0F, -1.0F, 0.0F)
                            .endVertex();
                }
            }
        }

        return pBuilder.end();
    }
}
