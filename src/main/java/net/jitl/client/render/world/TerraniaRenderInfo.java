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

public class TerraniaRenderInfo extends JDimensionSpecialEffects implements CustomSkyboxRenderer, CustomCloudsRenderer {

    private static final Identifier MOON_LOCATION = JITL.rl("textures/environment/terrania_moon1.png");
    private static final Identifier CLOUDIA_SKY_LOCATION = JITL.rl("textures/environment/cloudia_sky.png");
    private final GpuBuffer moonBuffer;

    public TerraniaRenderInfo() {
        super(Minecraft.getInstance().getTextureManager(), Minecraft.getInstance().getAtlasManager());
        this.moonBuffer = buildSunQuad(this.celestialsAtlas, 80, MOON_LOCATION);
    }

    @Override
    public boolean renderClouds(LevelRenderState levelRenderState, Vec3 camPos, CloudStatus cloudStatus, int cloudColor, float cloudHeight, Matrix4f modelViewMatrix) {
        new JCloudRenderer(JITL.rl("textures/environment/cloudia_clouds.png")).render(cloudColor, cloudStatus, cloudHeight, camPos, levelRenderState.gameTime, 10F);
        return CustomCloudsRenderer.super.renderClouds(levelRenderState, camPos, cloudStatus, cloudColor, cloudHeight, modelViewMatrix);
    }

    @Override
    public boolean renderSky(LevelRenderState levelRenderState, SkyRenderState skyRenderState, Matrix4f modelViewMatrix, Runnable setupFog) {
        setupFog.run();
        PoseStack poseStack = new PoseStack();
        renderSky(CLOUDIA_SKY_LOCATION, 2F);

        //START CLOUDIA MOON
        poseStack.pushPose();
        poseStack.mulPose(Axis.XP.rotationDegrees(180F));
        renderSun(1F, poseStack, this.moonBuffer);
        poseStack.popPose();

        return true;
    }

    @Override
    public void close() {
        this.moonBuffer.close();
    }
}