package net.jitl.client.render.world;

import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.jitl.client.render.world.clouds.JCloudRenderer;
import net.jitl.core.init.JITL;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import java.util.Optional;

public class EucaRenderInfo extends DimensionSpecialEffects {

    private static final ResourceLocation CORBA_MOON_LOCATION = JITL.rl("textures/environment/corba_moon.png");

    public EucaRenderInfo() {
        super(SkyType.NONE, false, false);
    }

    @Override
    public @NotNull Vec3 getBrightnessDependentFogColor(Vec3 vector3d, float float_) {
        float color = 0.95F + 0.05F;
        return vector3d.multiply((float_ * color), (float_ * color), (float_ * color));
    }

    @Override
    public boolean renderClouds(ClientLevel level, int ticks, float partialTick, double camX, double camY, double camZ, Matrix4f modelViewMatrix) {
        Optional<Integer> optional = level.dimensionType().cloudHeight();
        optional.ifPresent(height -> new JCloudRenderer(JITL.rl("textures/environment/euca_clouds.png")).render(1, Minecraft.getInstance().options.cloudStatus().get(), height, new Vec3(camX, camY, camZ), partialTick + ticks));
        return true;
    }

    @Override
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, Matrix4f modelViewMatrix, Camera camera, Runnable setupFog) {
        setupFog.run();
        FogType fogtype = camera.getFluidInCamera();
        if(fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA && !doesMobEffectBlockSky(camera)) {
            PoseStack poseStack = new PoseStack();
            poseStack.mulPose(modelViewMatrix);

            //START CORBA MOON
            poseStack.mulPose(Axis.YP.rotationDegrees(-180F));
            poseStack.mulPose(Axis.XP.rotationDegrees(-24000F));
            renderSun(20F, 1F, Minecraft.getInstance().renderBuffers().bufferSource(), poseStack, CORBA_MOON_LOCATION);
        }
        return false;
    }

    private void renderSun(float size, float alpha, MultiBufferSource bufferSource, PoseStack poseStack, ResourceLocation tex) {
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.celestial(tex));
        int i = ARGB.white(alpha);
        Matrix4f matrix4f = poseStack.last().pose();
        poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
        float f = Mth.sin(0) < 0.0F ? 180.0F : 0.0F;
        poseStack.mulPose(Axis.ZP.rotationDegrees(f));
        poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        vertexconsumer.addVertex(matrix4f, -size, 100.0F, -size).setUv(0.0F, 0.0F).setColor(i);
        vertexconsumer.addVertex(matrix4f, size, 100.0F, -size).setUv(1.0F, 0.0F).setColor(i);
        vertexconsumer.addVertex(matrix4f, size, 100.0F, size).setUv(1.0F, 1.0F).setColor(i);
        vertexconsumer.addVertex(matrix4f, -size, 100.0F, size).setUv(0.0F, 1.0F).setColor(i);
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
