package net.jitl.client.render.world;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jitl.client.render.world.clouds.JCloudRenderer;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.state.LevelRenderState;
import net.minecraft.client.renderer.state.SkyRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class BoilRenderInfo extends JDimensionSpecialEffects {

    private static final ResourceLocation SUN_LOCATION = JITL.rl("textures/environment/boil_sun.png");
    private static final ResourceLocation BOIL_SKY_LOCATION = JITL.rl("textures/environment/boil_sky.png");
    private static final ResourceLocation CORBA_MOON_LOCATION = JITL.rl("textures/environment/corba_moon.png");
    private static final ResourceLocation EUCA_MOON_LOCATION = JITL.rl("textures/environment/euca_moon.png");

    public BoilRenderInfo() {
        super(SkyType.END, false, false);
    }

    @Override
    public JCloudRenderer getCloudRenderer() {
        return new JCloudRenderer(JITL.rl("textures/environment/boil_clouds.png"));
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
    public boolean renderSky(LevelRenderState levelRenderState, SkyRenderState skyRenderState, Matrix4f modelViewMatrix, Runnable setupFog) {

        PoseStack posestack = new PoseStack();

        renderSky(BOIL_SKY_LOCATION, 3F);

        //START SUN
        posestack.pushPose();
        posestack.mulPose(Axis.XP.rotationDegrees(45F));
        renderSun(120F, 1F, posestack, SUN_LOCATION);
        posestack.popPose();


        //START CORBA MOON
        posestack.pushPose();
        posestack.mulPose(Axis.YP.rotationDegrees(30F));
        posestack.mulPose(Axis.ZP.rotationDegrees(90));
        posestack.mulPose(Axis.XP.rotationDegrees(100));
        renderSun(3F, 1F, posestack, CORBA_MOON_LOCATION);
        posestack.popPose();

        //START EUCA MOON
        posestack.pushPose();
        posestack.mulPose(Axis.YP.rotationDegrees(120F));
        posestack.mulPose(Axis.ZP.rotationDegrees(-10));
        posestack.mulPose(Axis.XP.rotationDegrees(90));
        renderSun(6F, 1F, posestack, EUCA_MOON_LOCATION);
        posestack.popPose();
        return true;
    }

    @Override
    public int getSunriseOrSunsetColor(float timeOfDay) {
        return 0;
    }
}