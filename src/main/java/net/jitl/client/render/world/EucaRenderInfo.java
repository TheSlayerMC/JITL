package net.jitl.client.render.world;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.jitl.client.render.world.clouds.EucaCloudRenderer;
import net.jitl.core.init.JITL;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class EucaRenderInfo extends DimensionSpecialEffects {

    private static final ResourceLocation CORBA_MOON_LOCATION = JITL.rl("textures/environment/corba_moon.png");

    public EucaRenderInfo() {
        super(0F, true, SkyType.NONE, false, false);
    }

    @Override
    public @NotNull Vec3 getBrightnessDependentFogColor(Vec3 vector3d, float float_) {
        float color = 0.95F + 0.05F;
        return vector3d.multiply((float_ * color), (float_ * color), (float_ * color));
    }

    @Override
    public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f modelViewMatrix, Matrix4f projectionMatrix) {
        new EucaCloudRenderer().render(level, ticks, poseStack, projectionMatrix, modelViewMatrix, partialTick, camX, camY, camZ);
        return true;
    }

    @Override
    public boolean renderSky(@NotNull ClientLevel level, int ticks, float partialTick, @NotNull Matrix4f frustumMatrix, @NotNull Camera camera, @NotNull Matrix4f projectionMatrix, boolean isFoggy, @NotNull Runnable setupFog) {
        Tesselator tesselator = Tesselator.getInstance();
        Minecraft mc = Minecraft.getInstance();
        setupFog.run();
        FogType fogtype = camera.getFluidInCamera();
        if(fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA && !doesMobEffectBlockSky(camera)) {
            PoseStack poseStack = new PoseStack();
            poseStack.mulPose(frustumMatrix);
            Vec3 vec3 = level.getSkyColor(mc.gameRenderer.getMainCamera().getPosition(), partialTick);
            float f = (float)vec3.x;
            float f1 = (float)vec3.y;
            float f2 = (float)vec3.z;

            FogRenderer.levelFogColor();
            RenderSystem.depthMask(false);
            RenderSystem.setShaderColor(f, f1, f2, 1.0F);
            VertexBuffer.unbind();
            RenderSystem.enableBlend();
            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            poseStack.pushPose();
            poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
            float f3 = Mth.sin(level.getSunAngle(partialTick)) < 0.0F ? 180.0F : 0.0F;
            poseStack.mulPose(Axis.ZP.rotationDegrees(f3));
            poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
            poseStack.popPose();
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            poseStack.pushPose();

            //START CORBA MOON
            poseStack.mulPose(Axis.YP.rotationDegrees(-180.0F));
            poseStack.mulPose(Axis.XP.rotationDegrees(-24000));
            Matrix4f matrix4f1 = poseStack.last().pose();
            float f12 = 20F;
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, CORBA_MOON_LOCATION);
            BufferBuilder bufferbuilder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            bufferbuilder.addVertex(matrix4f1, -f12, 100.0F, -f12).setUv(0.0F, 0.0F);
            bufferbuilder.addVertex(matrix4f1, f12, 100.0F, -f12).setUv(1.0F, 0.0F);
            bufferbuilder.addVertex(matrix4f1, f12, 100.0F, f12).setUv(1.0F, 1.0F);
            bufferbuilder.addVertex(matrix4f1, -f12, 100.0F, f12).setUv(0.0F, 1.0F);
            BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());

            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.disableBlend();
            RenderSystem.defaultBlendFunc();
            poseStack.popPose();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.depthMask(true);
        }
        return false;
    }

    public static boolean doesMobEffectBlockSky(Camera camera) {
        Entity entity = camera.getEntity();
        if(!(entity instanceof LivingEntity livingentity)) return false;
        return livingentity.hasEffect(MobEffects.BLINDNESS) || livingentity.hasEffect(MobEffects.DARKNESS);
    }

    @Override
    public boolean isFoggyAt(int int_, int int1_) {
        return false;
    }
}
