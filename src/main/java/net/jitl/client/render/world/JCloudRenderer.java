package net.jitl.client.render.world;

import com.mojang.blaze3d.platform.GlStateManager;
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

    public void render(ClientLevel level, int ticks, PoseStack poseStack, Matrix4f projectionMatrix, Matrix4f frustrumMatrix, float partialTicks, double viewEntityX, double viewEntityY, double viewEntityZ) {
        Minecraft minecraft = Minecraft.getInstance();
        float f = level.effects().getCloudHeight();
        if (!Float.isNaN(f)) {
            RenderSystem.disableCull();
            RenderSystem.enableBlend();
            RenderSystem.enableDepthTest();
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.depthMask(true);
            double d1 = ((float) ticks + partialTicks) * 0.3F;
            double d2 = (viewEntityX + d1) / 12.0D;
            double d3 = f - (float) viewEntityY + 0.33F;
            double d4 = viewEntityZ / 12.0D + (double) 0.33F;
            d2 = d2 - (double) (Mth.floor(d2 / 2048.0D) * 2048);
            d4 = d4 - (double) (Mth.floor(d4 / 2048.0D) * 2048);
            float f3 = (float) (d2 - (double) Mth.floor(d2));
            float f4 = (float) (d3 / 4.0D - (double) Mth.floor(d3 / 4.0D)) * 4.0F;
            float f5 = (float) (d4 - (double) Mth.floor(d4));
            Vec3 vector3d = level.getCloudColor(partialTicks);
            int i = (int) Math.floor(d2);
            int j = (int) Math.floor(d3 / 4.0D);
            int k = (int) Math.floor(d4);
            if (i != this.prevCloudX || j != this.prevCloudY || k != this.prevCloudZ || minecraft.options.getCloudsType() != this.prevCloudsType || this.prevCloudColor.distanceToSqr(vector3d) > 2.0E-4D) {
                this.prevCloudX = i;
                this.prevCloudY = j;
                this.prevCloudZ = k;
                prevCloudColor = vector3d;
                prevCloudsType = minecraft.options.getCloudsType();
                this.generateClouds = true;
            }

            if (this.generateClouds) {
                this.generateClouds = false;
                if (this.cloudBuffer != null) {
                    this.cloudBuffer.close();
                }

                this.cloudBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
                this.cloudBuffer.bind();
                this.cloudBuffer.upload(this.buildClouds(Tesselator.getInstance(), d2, d3, d4, vector3d));
                VertexBuffer.unbind();
            }
            RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
            RenderSystem.setShaderTexture(0, CLOUDS_LOCATION);

            FogRenderer.levelFogColor();

            poseStack.pushPose();
            poseStack.mulPose(projectionMatrix);
            poseStack.scale(12.0F, 1.0F, 12.0F);
            poseStack.translate(-f3, f4, -f5);
            if (this.cloudBuffer != null) {
                this.cloudBuffer.bind();
                int i1 = prevCloudsType == CloudStatus.FANCY ? 0 : 1;

                for (int l = i1; l < 2; ++l) {
                    if (l == 0) {
                        RenderSystem.colorMask(false, false, false, false);
                    } else {
                        RenderSystem.colorMask(true, true, true, true);
                    }
                    RenderType rendertype = l == 0 ? RenderType.cloudsDepthOnly() : RenderType.clouds();
                    rendertype.setupRenderState();
                    ShaderInstance shaderinstance = RenderSystem.getShader();
                    assert shaderinstance != null;
                    this.cloudBuffer.drawWithShader(poseStack.last().pose(), frustrumMatrix, shaderinstance);
                    rendertype.clearRenderState();
                }
                VertexBuffer.unbind();
                DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL.clearBufferState();
            }
            poseStack.popPose();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
        }
    }

    private MeshData buildClouds(Tesselator t, double cloudsX, double cloudsY, double cloudsZ, Vec3 cloudsColor) {
        float f3 = (float) Mth.floor(cloudsX) * 0.00390625F;
        float f4 = (float) Mth.floor(cloudsZ) * 0.00390625F;
        float f5 = (float) cloudsColor.x;
        float f6 = (float) cloudsColor.y;
        float f7 = (float) cloudsColor.z;
        float f8 = f5 * 0.9F;
        float f9 = f6 * 0.9F;
        float f10 = f7 * 0.9F;
        float f11 = f5 * 0.7F;
        float f12 = f6 * 0.7F;
        float f13 = f7 * 0.7F;
        float f14 = f5 * 0.8F;
        float f15 = f6 * 0.8F;
        float f16 = f7 * 0.8F;
        BufferBuilder bufferIn = t.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL);
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        float f17 = (float) Math.floor(cloudsY / 4.0D) * 4.0F;
        if (prevCloudsType == CloudStatus.FANCY) {
            for (int k = -3; k <= 4; ++k) {
                for (int l = -3; l <= 4; ++l) {
                    float f18 = (float) (k * 8);
                    float f19 = (float) (l * 8);
                    if (f17 > -5.0F) {
                        bufferIn.addVertex((f18 + 0.0F), (f17 + 0.0F), (f19 + 8.0F)).setUv((f18 + 0.0F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4).setColor(f11, f12, f13, 0.8F).setNormal(0.0F, -1.0F, 0.0F);
                        bufferIn.addVertex((f18 + 8.0F), (f17 + 0.0F), (f19 + 8.0F)).setUv((f18 + 8.0F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4).setColor(f11, f12, f13, 0.8F).setNormal(0.0F, -1.0F, 0.0F);
                        bufferIn.addVertex((f18 + 8.0F), (f17 + 0.0F), (f19 + 0.0F)).setUv((f18 + 8.0F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4).setColor(f11, f12, f13, 0.8F).setNormal(0.0F, -1.0F, 0.0F);
                        bufferIn.addVertex((f18 + 0.0F), (f17 + 0.0F), (f19 + 0.0F)).setUv((f18 + 0.0F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4).setColor(f11, f12, f13, 0.8F).setNormal(0.0F, -1.0F, 0.0F);
                    }

                    if (f17 <= 5.0F) {
                        bufferIn.addVertex((f18 + 0.0F), (f17 + 4.0F - 9.765625E-4F), (f19 + 8.0F)).setUv((f18 + 0.0F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4).setColor(f5, f6, f7, 0.8F).setNormal(0.0F, 1.0F, 0.0F);
                        bufferIn.addVertex((f18 + 8.0F), (f17 + 4.0F - 9.765625E-4F), (f19 + 8.0F)).setUv((f18 + 8.0F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4).setColor(f5, f6, f7, 0.8F).setNormal(0.0F, 1.0F, 0.0F);
                        bufferIn.addVertex((f18 + 8.0F), (f17 + 4.0F - 9.765625E-4F), (f19 + 0.0F)).setUv((f18 + 8.0F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4).setColor(f5, f6, f7, 0.8F).setNormal(0.0F, 1.0F, 0.0F);
                        bufferIn.addVertex((f18 + 0.0F), (f17 + 4.0F - 9.765625E-4F), (f19 + 0.0F)).setUv((f18 + 0.0F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4).setColor(f5, f6, f7, 0.8F).setNormal(0.0F, 1.0F, 0.0F);
                    }

                    if (k > -1) {
                        for (int i1 = 0; i1 < 8; ++i1) {
                            bufferIn.addVertex((f18 + (float) i1 + 0.0F), (f17 + 0.0F), (f19 + 8.0F)).setUv((f18 + (float) i1 + 0.5F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4).setColor(f8, f9, f10, 0.8F).setNormal(-1.0F, 0.0F, 0.0F);
                            bufferIn.addVertex((f18 + (float) i1 + 0.0F), (f17 + 4.0F), (f19 + 8.0F)).setUv((f18 + (float) i1 + 0.5F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4).setColor(f8, f9, f10, 0.8F).setNormal(-1.0F, 0.0F, 0.0F);
                            bufferIn.addVertex((f18 + (float) i1 + 0.0F), (f17 + 4.0F), (f19 + 0.0F)).setUv((f18 + (float) i1 + 0.5F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4).setColor(f8, f9, f10, 0.8F).setNormal(-1.0F, 0.0F, 0.0F);
                            bufferIn.addVertex((f18 + (float) i1 + 0.0F), (f17 + 0.0F), (f19 + 0.0F)).setUv((f18 + (float) i1 + 0.5F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4).setColor(f8, f9, f10, 0.8F).setNormal(-1.0F, 0.0F, 0.0F);
                        }
                    }

                    if (k <= 1) {
                        for (int j2 = 0; j2 < 8; ++j2) {
                            bufferIn.addVertex((f18 + (float) j2 + 1.0F - 9.765625E-4F), (f17 + 0.0F), (f19 + 8.0F)).setUv((f18 + (float) j2 + 0.5F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4).setColor(f8, f9, f10, 0.8F).setNormal(1.0F, 0.0F, 0.0F);
                            bufferIn.addVertex((f18 + (float) j2 + 1.0F - 9.765625E-4F), (f17 + 4.0F), (f19 + 8.0F)).setUv((f18 + (float) j2 + 0.5F) * 0.00390625F + f3, (f19 + 8.0F) * 0.00390625F + f4).setColor(f8, f9, f10, 0.8F).setNormal(1.0F, 0.0F, 0.0F);
                            bufferIn.addVertex((f18 + (float) j2 + 1.0F - 9.765625E-4F), (f17 + 4.0F), (f19 + 0.0F)).setUv((f18 + (float) j2 + 0.5F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4).setColor(f8, f9, f10, 0.8F).setNormal(1.0F, 0.0F, 0.0F);
                            bufferIn.addVertex((f18 + (float) j2 + 1.0F - 9.765625E-4F), (f17 + 0.0F), (f19 + 0.0F)).setUv((f18 + (float) j2 + 0.5F) * 0.00390625F + f3, (f19 + 0.0F) * 0.00390625F + f4).setColor(f8, f9, f10, 0.8F).setNormal(1.0F, 0.0F, 0.0F);
                        }
                    }

                    if (l > -1) {
                        for (int k2 = 0; k2 < 8; ++k2) {
                            bufferIn.addVertex((f18 + 0.0F), (f17 + 4.0F), (f19 + (float) k2 + 0.0F)).setUv((f18 + 0.0F) * 0.00390625F + f3, (f19 + (float) k2 + 0.5F) * 0.00390625F + f4).setColor(f14, f15, f16, 0.8F).setNormal(0.0F, 0.0F, -1.0F);
                            bufferIn.addVertex((f18 + 8.0F), (f17 + 4.0F), (f19 + (float) k2 + 0.0F)).setUv((f18 + 8.0F) * 0.00390625F + f3, (f19 + (float) k2 + 0.5F) * 0.00390625F + f4).setColor(f14, f15, f16, 0.8F).setNormal(0.0F, 0.0F, -1.0F);
                            bufferIn.addVertex((f18 + 8.0F), (f17 + 0.0F), (f19 + (float) k2 + 0.0F)).setUv((f18 + 8.0F) * 0.00390625F + f3, (f19 + (float) k2 + 0.5F) * 0.00390625F + f4).setColor(f14, f15, f16, 0.8F).setNormal(0.0F, 0.0F, -1.0F);
                            bufferIn.addVertex((f18 + 0.0F), (f17 + 0.0F), (f19 + (float) k2 + 0.0F)).setUv((f18 + 0.0F) * 0.00390625F + f3, (f19 + (float) k2 + 0.5F) * 0.00390625F + f4).setColor(f14, f15, f16, 0.8F).setNormal(0.0F, 0.0F, -1.0F);
                        }
                    }

                    if (l <= 1) {
                        for (int l2 = 0; l2 < 8; ++l2) {
                            bufferIn.addVertex((f18 + 0.0F), (f17 + 4.0F), (f19 + (float) l2 + 1.0F - 9.765625E-4F)).setUv((f18 + 0.0F) * 0.00390625F + f3, (f19 + (float) l2 + 0.5F) * 0.00390625F + f4).setColor(f14, f15, f16, 0.8F).setNormal(0.0F, 0.0F, 1.0F);
                            bufferIn.addVertex((f18 + 8.0F), (f17 + 4.0F), (f19 + (float) l2 + 1.0F - 9.765625E-4F)).setUv((f18 + 8.0F) * 0.00390625F + f3, (f19 + (float) l2 + 0.5F) * 0.00390625F + f4).setColor(f14, f15, f16, 0.8F).setNormal(0.0F, 0.0F, 1.0F);
                            bufferIn.addVertex((f18 + 8.0F), (f17 + 0.0F), (f19 + (float) l2 + 1.0F - 9.765625E-4F)).setUv((f18 + 8.0F) * 0.00390625F + f3, (f19 + (float) l2 + 0.5F) * 0.00390625F + f4).setColor(f14, f15, f16, 0.8F).setNormal(0.0F, 0.0F, 1.0F);
                            bufferIn.addVertex((f18 + 0.0F), (f17 + 0.0F), (f19 + (float) l2 + 1.0F - 9.765625E-4F)).setUv((f18 + 0.0F) * 0.00390625F + f3, (f19 + (float) l2 + 0.5F) * 0.00390625F + f4).setColor(f14, f15, f16, 0.8F).setNormal(0.0F, 0.0F, 1.0F);
                        }
                    }
                }
            }
        } else {
            for (int l1 = -32; l1 < 32; l1 += 32) {
                for (int i2 = -32; i2 < 32; i2 += 32) {
                    bufferIn.addVertex((l1), f17, (i2 + 32)).setUv((float) (l1) * 0.00390625F + f3, (float) (i2 + 32) * 0.00390625F + f4).setColor(f5, f6, f7, 0.8F).setNormal(0.0F, -1.0F, 0.0F);
                    bufferIn.addVertex((l1 + 32), f17, (i2 + 32)).setUv((float) (l1 + 32) * 0.00390625F + f3, (float) (i2 + 32) * 0.00390625F + f4).setColor(f5, f6, f7, 0.8F).setNormal(0.0F, -1.0F, 0.0F);
                    bufferIn.addVertex((l1 + 32), f17, (i2)).setUv((float) (l1 + 32) * 0.00390625F + f3, (float) (i2) * 0.00390625F + f4).setColor(f5, f6, f7, 0.8F).setNormal(0.0F, -1.0F, 0.0F);
                    bufferIn.addVertex((l1), f17, (i2)).setUv((float) (l1) * 0.00390625F + f3, (float) (i2) * 0.00390625F + f4).setColor(f5, f6, f7, 0.8F).setNormal(0.0F, -1.0F, 0.0F);
                }
            }
        }
        return bufferIn.buildOrThrow();
    }
}
