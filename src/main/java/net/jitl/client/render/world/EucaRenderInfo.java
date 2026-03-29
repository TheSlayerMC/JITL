package net.jitl.client.render.world;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jitl.client.render.world.clouds.JCloudRenderer;
import net.jitl.core.init.JITL;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.client.renderer.state.level.SkyRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.CustomCloudsRenderer;
import net.neoforged.neoforge.client.CustomSkyboxRenderer;
import org.joml.Matrix4fc;

public class EucaRenderInfo extends JDimensionSpecialEffects implements CustomSkyboxRenderer, CustomCloudsRenderer {

    private static final Identifier CORBA_MOON_LOCATION = JITL.rl("textures/environment/corba_moon.png");
    private final GpuBuffer sunBuffer;

    public EucaRenderInfo() {
        super(Minecraft.getInstance().getTextureManager(), Minecraft.getInstance().getAtlasManager());
        this.sunBuffer = buildSunQuad(this.celestialsAtlas, 40F, CORBA_MOON_LOCATION);
    }

    @Override
    public boolean renderClouds(LevelRenderState levelRenderState, Vec3 camPos, CloudStatus cloudStatus, int cloudColor, float cloudHeight, int cloudRange, Matrix4fc modelViewMatrix) {
        new JCloudRenderer(JITL.rl("textures/environment/euca_clouds.png")).render(cloudColor, cloudStatus, cloudHeight, cloudRange, camPos, levelRenderState.gameTime, 10F);
        return CustomCloudsRenderer.super.renderClouds(levelRenderState, camPos, cloudStatus, cloudColor, cloudHeight, cloudRange, modelViewMatrix);
    }

    @Override
    public boolean renderSky(LevelRenderState levelRenderState, SkyRenderState skyRenderState, Matrix4fc modelViewMatrix, Runnable setupFog) {
        setupFog.run();
            PoseStack poseStack = new PoseStack();
            poseStack.pushPose();

            //START CORBA MOON
            poseStack.mulPose(Axis.XP.rotationDegrees(90F));
            renderSun(1F, poseStack, this.sunBuffer);
            poseStack.popPose();

        return true;
    }

    @Override
    public void close() {
        this.sunBuffer.close();
    }

    /*
    @Override
    public boolean isFoggyAt(int int_, int int1_) {
        return false;
    }*/
}
