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

public class CorbaRenderInfo extends JDimensionSpecialEffects implements CustomSkyboxRenderer, CustomCloudsRenderer {

    private static final Identifier SKY_LOCATION = JITL.rl("textures/environment/corba_sky.png");
    private static final Identifier BOIL_MOON_LOCATION = JITL.rl("textures/environment/boil_moon1.png");
    private static final Identifier EUCA_MOON_LOCATION = JITL.rl("textures/environment/euca_moon1.png");

    private final GpuBuffer boilMoonBuffer;
    private final GpuBuffer sunBuffer;

    public CorbaRenderInfo() {
        super(Minecraft.getInstance().getTextureManager(), Minecraft.getInstance().getAtlasManager());
        this.boilMoonBuffer = buildSunQuad(this.celestialsAtlas, 15F, BOIL_MOON_LOCATION);
        this.sunBuffer = buildSunQuad(this.celestialsAtlas, 50F, EUCA_MOON_LOCATION);
    }

    @Override
    public boolean renderClouds(LevelRenderState levelRenderState, Vec3 camPos, CloudStatus cloudStatus, int cloudColor, float cloudHeight, Matrix4f modelViewMatrix) {
        new JCloudRenderer(JITL.rl("textures/environment/corba_clouds.png")).render(cloudColor, cloudStatus, cloudHeight, camPos, levelRenderState.gameTime, 10F);
        return CustomCloudsRenderer.super.renderClouds(levelRenderState, camPos, cloudStatus, cloudColor, cloudHeight, modelViewMatrix);
    }

    @Override
    public boolean renderSky(LevelRenderState levelRenderState, SkyRenderState skyRenderState, Matrix4f modelViewMatrix, Runnable setupFog) {
        setupFog.run();
            PoseStack posestack = new PoseStack();

            renderSky(SKY_LOCATION, 3F);

            //START BOIL MOON
            posestack.pushPose();
            posestack.mulPose(Axis.YP.rotationDegrees(20F));
            posestack.mulPose(Axis.ZP.rotationDegrees(-30));
            renderSun(1F, posestack, this.boilMoonBuffer);
            posestack.popPose();

            //START EUCA MOON
            posestack.pushPose();
            posestack.mulPose(Axis.YP.rotationDegrees(-80.0F));
            posestack.mulPose(Axis.ZP.rotationDegrees(-10));

            renderSun(1F, posestack, this.sunBuffer);
            posestack.popPose();

        return true;
    }

    @Override
    public void close() {
        this.boilMoonBuffer.close();
        this.sunBuffer.close();
    }
}