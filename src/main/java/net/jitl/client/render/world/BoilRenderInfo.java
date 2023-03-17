package net.jitl.client.render.world;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.jitl.core.init.JITL;
import net.minecraft.client.Camera;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.*;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import javax.annotation.Nullable;

public class BoilRenderInfo extends DimensionSpecialEffects {

    private static final ResourceLocation SUN_LOCATION = JITL.rl("textures/environment/boil_sun.png");
    private static final ResourceLocation BOIL_SKY_LOCATION = JITL.rl("textures/environment/boil_sky.png");

    @Nullable private VertexBuffer skyBuffer;
    @Nullable private final VertexBuffer starBuffer;

    public BoilRenderInfo() {
        super(256F, true, SkyType.NORMAL, false, false);
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        RenderSystem.setShader(GameRenderer::getPositionShader);
        starBuffer = new VertexBuffer();
        BufferBuilder.RenderedBuffer bufferbuilder$renderedbuffer = drawStars(bufferbuilder);
        starBuffer.bind();
        starBuffer.upload(bufferbuilder$renderedbuffer);
        VertexBuffer.unbind();
        tesselator = Tesselator.getInstance();
        bufferbuilder = tesselator.getBuilder();
        if(skyBuffer != null) skyBuffer.close();
        skyBuffer = new VertexBuffer();
        bufferbuilder$renderedbuffer = buildSkyDisc(bufferbuilder, 16F);
        skyBuffer.bind();
        skyBuffer.upload(bufferbuilder$renderedbuffer);
        VertexBuffer.unbind();
    }

    @Override
    public @NotNull Vec3 getBrightnessDependentFogColor(Vec3 vector3d, float float_) {
        float color = 0.95F + 0.05F;
        return vector3d.multiply((float_ * color), (float_ * color), (float_ * color));
    }

    @Override
    public boolean isFoggyAt(int i, int i2) {
        return false;
    }

    @Override
    public boolean renderSky(@NotNull ClientLevel level, int ticks, float partialTick, @NotNull PoseStack poseStack, @NotNull Camera camera, @NotNull Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
        Minecraft mc = Minecraft.getInstance();
        LevelRenderer levelRenderer = mc.levelRenderer;
        setupFog.run();
        if (!isFoggy) {
            FogType fogtype = camera.getFluidInCamera();
            if(fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA && !doesMobEffectBlockSky(camera)) {
                Vec3 vec3 = level.getSkyColor(mc.gameRenderer.getMainCamera().getPosition(), partialTick);
                float f = (float)vec3.x;
                float f1 = (float)vec3.y;
                float f2 = (float)vec3.z;
                FogRenderer.levelFogColor();
                BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
                RenderSystem.depthMask(false);
                RenderSystem.setShaderColor(f, f1, f2, 1.0F);
                ShaderInstance shaderinstance = RenderSystem.getShader();
                this.skyBuffer.bind();
                this.skyBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderinstance);
                VertexBuffer.unbind();
                RenderSystem.enableBlend();
                float[] afloat = level.effects().getSunriseColor(level.getTimeOfDay(partialTick), partialTick);
                if (afloat != null) {
                    RenderSystem.setShader(GameRenderer::getPositionColorShader);
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                    poseStack.pushPose();
                    poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                    float f3 = Mth.sin(level.getSunAngle(partialTick)) < 0.0F ? 180.0F : 0.0F;
                    poseStack.mulPose(Axis.ZP.rotationDegrees(f3));
                    poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
                    float f4 = afloat[0];
                    float f5 = afloat[1];
                    float f6 = afloat[2];
                    Matrix4f matrix4f = poseStack.last().pose();
                    bufferbuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
                    bufferbuilder.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(f4, f5, f6, afloat[3]).endVertex();
                    for(int j = 0; j <= 16; ++j) {
                        float f7 = (float)j * ((float)Math.PI * 2F) / 16.0F;
                        float f8 = Mth.sin(f7);
                        float f9 = Mth.cos(f7);
                        bufferbuilder.vertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
                    }
                    BufferUploader.drawWithShader(bufferbuilder.end());
                    poseStack.popPose();
                }
                RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                poseStack.pushPose();
                float f11 = 1.0F - level.getRainLevel(partialTick);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, f11);
                poseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
                poseStack.mulPose(Axis.XP.rotationDegrees(level.getTimeOfDay(partialTick) * 360.0F));
                Matrix4f matrix4f1 = poseStack.last().pose();
                float f12 = 80F;
                this.renderSkyTexture(poseStack);
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderTexture(0, SUN_LOCATION);
                bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                bufferbuilder.vertex(matrix4f1, -f12, 100.0F, -f12).uv(0.0F, 0.0F).endVertex();
                bufferbuilder.vertex(matrix4f1, f12, 100.0F, -f12).uv(1.0F, 0.0F).endVertex();
                bufferbuilder.vertex(matrix4f1, f12, 100.0F, f12).uv(1.0F, 1.0F).endVertex();
                bufferbuilder.vertex(matrix4f1, -f12, 100.0F, f12).uv(0.0F, 1.0F).endVertex();
                BufferUploader.drawWithShader(bufferbuilder.end());
                f12 = 20.0F;
                //RenderSystem.setShaderTexture(0, MOON_LOCATION);
                int k = level.getMoonPhase();
                int l = k % 4;
                int i1 = k / 4 % 2;
                float f13 = (float)(l) / 4.0F;
                float f14 = (float)(i1) / 2.0F;
                float f15 = (float)(l + 1) / 4.0F;
                float f16 = (float)(i1 + 1) / 2.0F;
                bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                bufferbuilder.vertex(matrix4f1, -f12, -100.0F, f12).uv(f15, f16).endVertex();
                bufferbuilder.vertex(matrix4f1, f12, -100.0F, f12).uv(f13, f16).endVertex();
                bufferbuilder.vertex(matrix4f1, f12, -100.0F, -f12).uv(f13, f14).endVertex();
                bufferbuilder.vertex(matrix4f1, -f12, -100.0F, -f12).uv(f15, f14).endVertex();
                BufferUploader.drawWithShader(bufferbuilder.end());
                float f10 = level.getStarBrightness(partialTick) * f11;
                if (f10 > 0.0F) {
                    RenderSystem.setShaderColor(f10, f10, f10, f10);
                    FogRenderer.setupNoFog();
                    this.starBuffer.bind();
                    this.starBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, GameRenderer.getPositionShader());
                    VertexBuffer.unbind();
                    setupFog.run();
                }

                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.disableBlend();
                RenderSystem.defaultBlendFunc();
                poseStack.popPose();
                RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
                double d0 = mc.player.getEyePosition(partialTick).y - level.getLevelData().getHorizonHeight(level);
                if (d0 < 0.0D) {
                    poseStack.pushPose();
                    poseStack.translate(0.0F, 12.0F, 0.0F);
                    levelRenderer.darkBuffer.bind();
                    levelRenderer.darkBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderinstance);
                    VertexBuffer.unbind();
                    poseStack.popPose();
                }

                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.depthMask(true);
            }
        }
        return true;
    }

    private void renderSkyTexture(PoseStack pPoseStack) {
        RenderSystem.enableBlend();
        RenderSystem.depthMask(false);
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, BOIL_SKY_LOCATION);
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();

        for(int i = 0; i < 6; ++i) {
            pPoseStack.pushPose();
            if (i == 1) {
                pPoseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
            }

            if (i == 2) {
                pPoseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
            }

            if (i == 3) {
                pPoseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
            }

            if (i == 4) {
                pPoseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
            }

            if (i == 5) {
                pPoseStack.mulPose(Axis.ZP.rotationDegrees(-90.0F));
            }

            Matrix4f matrix4f = pPoseStack.last().pose();
            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
            bufferbuilder.vertex(matrix4f, -100.0F, -100.0F, -100.0F).uv(0.0F, 0.0F).color(40, 40, 40, 255).endVertex();
            bufferbuilder.vertex(matrix4f, -100.0F, -100.0F, 100.0F).uv(0.0F, 16.0F).color(40, 40, 40, 255).endVertex();
            bufferbuilder.vertex(matrix4f, 100.0F, -100.0F, 100.0F).uv(16.0F, 16.0F).color(40, 40, 40, 255).endVertex();
            bufferbuilder.vertex(matrix4f, 100.0F, -100.0F, -100.0F).uv(16.0F, 0.0F).color(40, 40, 40, 255).endVertex();
            tesselator.end();
            pPoseStack.popPose();
        }

        RenderSystem.depthMask(true);
        RenderSystem.disableBlend();
    }

    public static boolean doesMobEffectBlockSky(Camera camera) {
        Entity entity = camera.getEntity();
        if(!(entity instanceof LivingEntity livingentity)) return false;
        return livingentity.hasEffect(MobEffects.BLINDNESS) || livingentity.hasEffect(MobEffects.DARKNESS);
    }

    public static BufferBuilder.RenderedBuffer buildSkyDisc(BufferBuilder builder, float g) {
        float f = Math.signum(g) * 512.0F;
        RenderSystem.setShader(GameRenderer::getPositionShader);
        builder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION);
        builder.vertex(0D, g, 0D).endVertex();
        for(int i = -180; i <= 180; i += 45) builder.vertex(f * Math.cos(i * (Math.PI / 180D)), g, 512.0F * Math.sin(i * (Math.PI / 180D))).endVertex();
        return builder.end();
    }

    public static BufferBuilder.RenderedBuffer drawStars(BufferBuilder builder) {
        RandomSource randomsource = RandomSource.create(10842L);
        builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);
        for(int i = 0; i < 1500; ++i) {
            double d0 = randomsource.nextFloat() * 2F - 1F, d1 = randomsource.nextFloat() * 2F - 1F, d2 = randomsource.nextFloat() * 2F - 1F,
                    d3 = .15F + randomsource.nextFloat() * .1F, d4 = d0 * d0 + d1 * d1 + d2 * d2;
            if(d4 < 1D && d4 > .01D) {
                d4 = 1D / Math.sqrt(d4);
                d0 *= d4;
                d1 *= d4;
                d2 *= d4;
                double d5 = d0 * 100D, d6 = d1 * 100D, d7 = d2 * 100D, d8 = Math.atan2(d0, d2), d9 = Math.sin(d8), d10 = Math.cos(d8), d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1),
                        d12 = Math.sin(d11), d13 = Math.cos(d11), d14 = randomsource.nextDouble() * Math.PI * 2D, d15 = Math.sin(d14), d16 = Math.cos(d14);
                for(int j = 0; j < 4; ++j) {
                    double d18 = ((j & 2) - 1) * d3, d19 = ((j + 1 & 2) - 1) * d3, d21 = d18 * d16 - d19 * d15, d22 = d19 * d16 + d18 * d15,
                            d23 = d21 * d12 + 0D * d13, d24 = 0D * d12 - d21 * d13, d25 = d24 * d9 - d22 * d10, d26 = d22 * d9 + d24 * d10;
                    builder.vertex(d5 + d25, d6 + d23, d7 + d26).endVertex();
                }
            }
        }
        return builder.end();
    }
}