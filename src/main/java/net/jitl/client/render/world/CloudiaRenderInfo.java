package net.jitl.client.render.world;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.jitl.client.render.world.clouds.CloudiaCloudRenderer;
import net.jitl.client.render.world.clouds.JCloudRenderer;
import net.jitl.core.init.JITL;
import net.minecraft.client.Camera;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.util.TriState;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import javax.annotation.Nullable;

public class CloudiaRenderInfo extends DimensionSpecialEffects {

    private static final ResourceLocation SUN_LOCATION = JITL.rl("textures/environment/cloudia_sun.png");
    private static final ResourceLocation CLOUDIA_SKY_LOCATION = JITL.rl("textures/environment/cloudia_sky.png");
    private final VertexBuffer SKY_BUFFER;

    public CloudiaRenderInfo() {
        super(63F, true, SkyType.NONE, false, false);
        this.SKY_BUFFER = VertexBuffer.uploadStatic(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR, this::renderSkyTexture);
    }

    public void renderSky() {
        this.SKY_BUFFER.drawWithRenderType(RenderType.create("cloudia_sky", DefaultVertexFormat.POSITION_TEX_COLOR, VertexFormat.Mode.QUADS, 1536, false, false
                , RenderType.CompositeState.builder().setShaderState(RenderType.POSITION_TEXTURE_COLOR_SHADER).setTextureState(new RenderStateShard.TextureStateShard(CLOUDIA_SKY_LOCATION, TriState.FALSE, false))
                        .setTransparencyState(RenderType.TRANSLUCENT_TRANSPARENCY).setWriteMaskState(RenderType.COLOR_WRITE).createCompositeState(false)));
    }

    @Override
    public boolean renderClouds(ClientLevel level, int ticks, float partialTick, double camX, double camY, double camZ, Matrix4f modelViewMatrix, Matrix4f projectionMatrix) {
        //new JCloudRenderer(JITL.rl("textures/environment/cloudia_clouds.png")).render(1, CloudStatus.FANCY, getCloudHeight(), projectionMatrix, modelViewMatrix, new Vec3(camX, camY, camZ), partialTick);
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

    @Override
    public boolean renderSky(@NotNull ClientLevel level, int ticks, float partialTick, @NotNull Matrix4f frustumMatrix, @NotNull Camera camera, @NotNull Matrix4f projectionMatrix, @NotNull Runnable setupFog) {
        FogType fogtype = camera.getFluidInCamera();
        if (fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA && !this.doesMobEffectBlockSky(camera)) {
            PoseStack posestack = new PoseStack();

            renderSky();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

            posestack.mulPose(Axis.YP.rotationDegrees(90F));
            posestack.mulPose(Axis.XP.rotationDegrees(level.getTimeOfDay(partialTick) * 360.0F));
            renderSun(14F, 1F, Minecraft.getInstance().renderBuffers().bufferSource(), posestack, SUN_LOCATION);

        }
        return true;
    }

    private void renderSun(float size, float alpha, MultiBufferSource bufferSource, PoseStack poseStack, ResourceLocation tex) {
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.celestial(tex));
        int i = ARGB.white(alpha);
        Matrix4f matrix4f = poseStack.last().pose();
        poseStack.pushPose();
        poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
        float f = Mth.sin(0) < 0.0F ? 180.0F : 0.0F;
        poseStack.mulPose(Axis.ZP.rotationDegrees(f));
        poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        vertexconsumer.addVertex(matrix4f, -size, 100.0F, -size).setUv(0.0F, 0.0F).setColor(i);
        vertexconsumer.addVertex(matrix4f, size, 100.0F, -size).setUv(1.0F, 0.0F).setColor(i);
        vertexconsumer.addVertex(matrix4f, size, 100.0F, size).setUv(1.0F, 1.0F).setColor(i);
        vertexconsumer.addVertex(matrix4f, -size, 100.0F, size).setUv(0.0F, 1.0F).setColor(i);
        poseStack.popPose();
    }

    private void renderSkyTexture(VertexConsumer buffer) {
        RenderSystem.enableBlend();
        RenderSystem.depthMask(false);
        RenderSystem.setShaderTexture(0, CLOUDIA_SKY_LOCATION);

        for(int i = 0; i < 6; ++i) {
            Matrix4f matrix4f = new Matrix4f();
            switch (i) {
                case 1 -> matrix4f.rotationX(1.5707964F);
                case 2 -> matrix4f.rotationX(-1.5707964F);
                case 3 -> matrix4f.rotationX(3.1415927F);
                case 4 -> matrix4f.rotationZ(1.5707964F);
                case 5 -> matrix4f.rotationZ(-1.5707964F);
            }

            buffer.addVertex(matrix4f, -100.0F, -100.0F, -100.0F).setUv(0.0F, 0.0F).setColor(-14145496);
            buffer.addVertex(matrix4f, -100.0F, -100.0F, 100.0F).setUv(0.0F, 16.0F).setColor(-14145496);
            buffer.addVertex(matrix4f, 100.0F, -100.0F, 100.0F).setUv(16.0F, 16.0F).setColor(-14145496);
            buffer.addVertex(matrix4f, 100.0F, -100.0F, -100.0F).setUv(16.0F, 0.0F).setColor(-14145496);
        }
    }

    public boolean doesMobEffectBlockSky(Camera camera) {
        Entity entity = camera.getEntity();
        if(!(entity instanceof LivingEntity livingentity)) return false;
        return livingentity.hasEffect(MobEffects.BLINDNESS) || livingentity.hasEffect(MobEffects.DARKNESS);
    }
}