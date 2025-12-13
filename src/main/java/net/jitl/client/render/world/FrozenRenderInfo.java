package net.jitl.client.render.world;

import net.jitl.client.render.world.clouds.JCloudRenderer;
import net.jitl.core.init.JITL;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.state.LevelRenderState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.CustomCloudsRenderer;
import org.joml.Matrix4f;

public class FrozenRenderInfo extends JDimensionSpecialEffects implements CustomCloudsRenderer{

    public FrozenRenderInfo() {
        super(Minecraft.getInstance().getTextureManager(), Minecraft.getInstance().getAtlasManager());
    }

    @Override
    public boolean renderClouds(LevelRenderState levelRenderState, Vec3 camPos, CloudStatus cloudStatus, int cloudColor, float cloudHeight, Matrix4f modelViewMatrix) {
        new JCloudRenderer(JITL.rl("textures/environment/frozen_clouds.png")).render(cloudColor, cloudStatus, cloudHeight, camPos, levelRenderState.gameTime, 10F);
        return CustomCloudsRenderer.super.renderClouds(levelRenderState, camPos, cloudStatus, cloudColor, cloudHeight, modelViewMatrix);
    }
}
