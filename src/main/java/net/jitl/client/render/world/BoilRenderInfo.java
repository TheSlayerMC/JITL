package net.jitl.client.render.world;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jitl.client.render.world.clouds.JCloudRenderer;
import net.jitl.core.init.JITL;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.state.LevelRenderState;
import net.minecraft.client.renderer.state.SkyRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.CustomCloudsRenderer;
import net.neoforged.neoforge.client.CustomSkyboxRenderer;
import org.joml.Matrix4f;

public class BoilRenderInfo extends JDimensionSpecialEffects implements CustomSkyboxRenderer, CustomCloudsRenderer {

    private static final Identifier SUN_LOCATION = JITL.rl("textures/environment/boil_sun.png");
    private static final Identifier BOIL_SKY_LOCATION = JITL.rl("textures/environment/boil_sky.png");
    private static final Identifier CORBA_MOON_LOCATION = JITL.rl("textures/environment/corba_moon.png");
    private static final Identifier EUCA_MOON_LOCATION = JITL.rl("textures/environment/euca_moon.png");

    private final GpuBuffer corbaMoonBuffer;
    private final GpuBuffer eucaMoonBuffer;
    private final GpuBuffer sunBuffer;

    public BoilRenderInfo() {
        super(Minecraft.getInstance().getTextureManager(), Minecraft.getInstance().getAtlasManager());
        this.corbaMoonBuffer = buildSunQuad(this.celestialsAtlas, 3F, CORBA_MOON_LOCATION);
        this.eucaMoonBuffer = buildSunQuad(this.celestialsAtlas, 6F, EUCA_MOON_LOCATION);
        this.sunBuffer = buildSunQuad(this.celestialsAtlas, 120F, SUN_LOCATION);
    }

    @Override
    public boolean renderClouds(LevelRenderState levelRenderState, Vec3 camPos, CloudStatus cloudStatus, int cloudColor, float cloudHeight, Matrix4f modelViewMatrix) {
        new JCloudRenderer(JITL.rl("textures/environment/boil_clouds.png")).render(cloudColor, cloudStatus, cloudHeight, camPos, levelRenderState.gameTime, 10F);
        return CustomCloudsRenderer.super.renderClouds(levelRenderState, camPos, cloudStatus, cloudColor, cloudHeight, modelViewMatrix);
    }

    @Override
    public boolean renderSky(LevelRenderState levelRenderState, SkyRenderState skyRenderState, Matrix4f modelViewMatrix, Runnable setupFog) {

        PoseStack posestack = new PoseStack();

        renderSky(BOIL_SKY_LOCATION, 3F);

        //START SUN
        posestack.pushPose();
        posestack.mulPose(Axis.XP.rotationDegrees(45F));
        renderSun(1F, posestack, this.sunBuffer);
        posestack.popPose();


        //START CORBA MOON
        posestack.pushPose();
        posestack.mulPose(Axis.YP.rotationDegrees(30F));
        posestack.mulPose(Axis.ZP.rotationDegrees(90));
        posestack.mulPose(Axis.XP.rotationDegrees(100));
        renderSun(1F, posestack, this.corbaMoonBuffer);
        posestack.popPose();

        //START EUCA MOON
        posestack.pushPose();
        posestack.mulPose(Axis.YP.rotationDegrees(120F));
        posestack.mulPose(Axis.ZP.rotationDegrees(-10));
        posestack.mulPose(Axis.XP.rotationDegrees(90));
        renderSun(1F, posestack, this.eucaMoonBuffer);
        posestack.popPose();
        return true;
    }

    @Override
    public void close() {
        this.corbaMoonBuffer.close();
        this.eucaMoonBuffer.close();
        this.sunBuffer.close();
    }

    //    @Override
//    public int getSunriseOrSunsetColor(float timeOfDay) {
//        return 0;
//    }
}