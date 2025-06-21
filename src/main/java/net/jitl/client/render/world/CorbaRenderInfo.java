package net.jitl.client.render.world;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jitl.client.render.world.clouds.JCloudRenderer;
import net.jitl.core.init.JITL;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class CorbaRenderInfo extends JDimensionSpecialEffects {

    private static final ResourceLocation SKY_LOCATION = JITL.rl("textures/environment/corba_sky.png");
    private static final ResourceLocation BOIL_MOON_LOCATION = JITL.rl("textures/environment/boil_moon1.png");
    private static final ResourceLocation EUCA_MOON_LOCATION = JITL.rl("textures/environment/euca_moon1.png");

    public CorbaRenderInfo() {
        super(SkyType.END, true, false);
    }

    @Override
    public @NotNull Vec3 getBrightnessDependentFogColor(Vec3 vector3d, float float_) {
        float color = 0.95F + 0.05F;
        return vector3d.multiply((float_ * color), (float_ * color), (float_ * color));
    }

    @Override
    public JCloudRenderer getCloudRenderer() {
        return new JCloudRenderer(JITL.rl("textures/environment/corba_clouds.png"));
    }

    @Override
    public boolean isFoggyAt(int i, int i2) {
        return false;
    }

    @Override
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, Matrix4f modelViewMatrix, Camera camera, Runnable setupFog) {
        setupFog.run();
        FogType fogtype = camera.getFluidInCamera();
        if(fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA && !doesMobEffectBlockSky(camera)) {
            PoseStack posestack = new PoseStack();

            renderSky(SKY_LOCATION);

            //START BOIL MOON
            posestack.pushPose();
            posestack.mulPose(Axis.YP.rotationDegrees(20F));
            posestack.mulPose(Axis.ZP.rotationDegrees(-20));
            renderSun(10F, 1F, Minecraft.getInstance().renderBuffers().bufferSource(), posestack, BOIL_MOON_LOCATION);
            posestack.popPose();

            //START EUCA MOON
            posestack.pushPose();
            posestack.mulPose(Axis.YP.rotationDegrees(-90.0F));
            posestack.mulPose(Axis.XP.rotationDegrees(360F));
            posestack.mulPose(Axis.ZP.rotationDegrees(-10));

            renderSun(25F, 1F, Minecraft.getInstance().renderBuffers().bufferSource(), posestack, EUCA_MOON_LOCATION);
            posestack.popPose();
        }
        return true;
    }
}