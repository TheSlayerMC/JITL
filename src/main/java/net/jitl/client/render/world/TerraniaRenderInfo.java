package net.jitl.client.render.world;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.jitl.client.render.world.clouds.JCloudRenderer;
import net.jitl.core.init.JITL;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import javax.annotation.Nullable;

public class TerraniaRenderInfo extends DimensionSpecialEffects {

    private static final ResourceLocation MOON_LOCATION = JITL.rl("textures/environment/terrania_moon1.png");
    private static final ResourceLocation CLOUDIA_SKY_LOCATION = JITL.rl("textures/environment/cloudia_sky.png");

    public TerraniaRenderInfo() {
        super(200F, true, SkyType.NONE, false, false);
    }

        @Override
    public boolean renderClouds(ClientLevel level, int ticks, float partialTick, double camX, double camY, double camZ, Matrix4f modelViewMatrix, Matrix4f projectionMatrix) {
        new JCloudRenderer(JITL.rl("textures/environment/cloudia_clouds.png")).render(level.getCloudColor(partialTick), Minecraft.getInstance().options.cloudStatus().get(), getCloudHeight(), new Vec3(camX, camY, camZ), partialTick);
        return true;
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

  /*   @Override
    public boolean renderSky(@NotNull ClientLevel level, int ticks, float partialTick, @NotNull Matrix4f frustumMatrix, @NotNull Camera camera, @NotNull Matrix4f projectionMatrix, @NotNull Runnable setupFog) {
        Tesselator tesselator = Tesselator.getInstance();
        Minecraft mc = Minecraft.getInstance();
        setupFog.run();
        FogType fogtype = camera.getFluidInCamera();
        if(fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA && !doesMobEffectBlockSky(camera)) {
            PoseStack poseStack = new PoseStack();
            poseStack.mulPose(frustumMatrix);
            this.renderSkyTexture(poseStack);
            Vec3 vec3 = Vec3.fromRGB24(level.getSkyColor(mc.gameRenderer.getMainCamera().getPosition(), partialTick));
            float f = (float)vec3.x;
            float f1 = (float)vec3.y;
            float f2 = (float)vec3.z;

            RenderSystem.depthMask(false);
            RenderSystem.setShaderColor(f, f1, f2, 1.0F);
            VertexBuffer.unbind();
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            poseStack.pushPose();
            poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
            float f3 = Mth.sin(level.getSunAngle(partialTick)) < 0.0F ? 180.0F : 0.0F;
            poseStack.mulPose(Axis.ZP.rotationDegrees(f3));
            poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
            poseStack.popPose();
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            poseStack.pushPose();

            //START CLOUDIA MOON
            poseStack.mulPose(Axis.YP.rotationDegrees(-180.0F));
            poseStack.mulPose(Axis.XP.rotationDegrees(level.getTimeOfDay(partialTick) + 2200F * 360.0F));
            Matrix4f matrix4f1 = poseStack.last().pose();
            float f12 = 80F;
            RenderSystem.setShaderTexture(0, MOON_LOCATION);
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

    @Nullable
    public float[] getSunriseColor(float ff, float ff1) {
        return null;
    }

    private void renderSkyTexture(PoseStack poseStack) {
        RenderSystem.enableBlend();
        RenderSystem.depthMask(false);
        RenderSystem.setShaderTexture(0, CLOUDIA_SKY_LOCATION);
        Tesselator tesselator = Tesselator.getInstance();

        for(int i = 0; i < 6; ++i) {
            poseStack.pushPose();
            switch(i) {
                default -> { }
                case 1 -> poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                case 2 -> poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
                case 3 -> poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
                case 4 -> poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
                case 5 -> poseStack.mulPose(Axis.ZP.rotationDegrees(-90.0F));
            }

            Matrix4f matrix4f = poseStack.last().pose();
            BufferBuilder bufferbuilder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
            bufferbuilder.addVertex(matrix4f, -100.0F, -100.0F, -100.0F).setUv(0.0F, 0.0F).setColor(40, 40, 40, 255);
            bufferbuilder.addVertex(matrix4f, -100.0F, -100.0F, 100.0F).setUv(0.0F, 16.0F).setColor(40, 40, 40, 255);
            bufferbuilder.addVertex(matrix4f, 100.0F, -100.0F, 100.0F).setUv(16.0F, 16.0F).setColor(40, 40, 40, 255);
            bufferbuilder.addVertex(matrix4f, 100.0F, -100.0F, -100.0F).setUv(16.0F, 0.0F).setColor(40, 40, 40, 255);
            BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());
            poseStack.popPose();
        }

        RenderSystem.depthMask(true);
        RenderSystem.disableBlend();
    }

    public static boolean doesMobEffectBlockSky(Camera camera) {
        Entity entity = camera.getEntity();
        if(!(entity instanceof LivingEntity livingentity)) return false;
        return livingentity.hasEffect(MobEffects.BLINDNESS) || livingentity.hasEffect(MobEffects.DARKNESS);
    }*/
}