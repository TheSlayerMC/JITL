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

public class CloudiaRenderInfo extends JDimensionSpecialEffects implements CustomSkyboxRenderer, CustomCloudsRenderer {

    private static final Identifier SUN_LOCATION = JITL.rl("textures/environment/cloudia_sun.png");
    private static final Identifier CLOUDIA_SKY_LOCATION = JITL.rl("textures/environment/cloudia_sky.png");
    private final GpuBuffer sunBuffer;

    public CloudiaRenderInfo() {
        super(Minecraft.getInstance().getTextureManager(), Minecraft.getInstance().getAtlasManager());
        this.sunBuffer = buildSunQuad(this.celestialsAtlas, 60, SUN_LOCATION);
    }

    @Override
    public boolean renderClouds(LevelRenderState levelRenderState, Vec3 camPos, CloudStatus cloudStatus, int cloudColor, float cloudHeight, Matrix4f modelViewMatrix) {
        new JCloudRenderer(JITL.rl("textures/environment/cloudia_clouds.png")).render(cloudColor, cloudStatus, cloudHeight, camPos, levelRenderState.gameTime, 10F);
        return CustomCloudsRenderer.super.renderClouds(levelRenderState, camPos, cloudStatus, cloudColor, cloudHeight, modelViewMatrix);
    }

    @Override
    public boolean renderSky(LevelRenderState levelRenderState, SkyRenderState skyRenderState, Matrix4f modelViewMatrix, Runnable setupFog) {
        PoseStack poseStack = new PoseStack();

        renderSky(CLOUDIA_SKY_LOCATION, 8F);

        poseStack.pushPose();
        poseStack.mulPose(Axis.ZP.rotationDegrees(-270));
        renderSun(1F, poseStack, this.sunBuffer);
        poseStack.popPose();
        return true;
    }

    @Override
    public void close() {
        this.sunBuffer.close();
    }
}