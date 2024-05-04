package net.jitl.client.render.world;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.core.init.JITL;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class EucaRenderInfo extends DimensionSpecialEffects {

    public EucaRenderInfo() {
        super(0F, true, SkyType.NONE, false, false);
    }

    @Override
    public @NotNull Vec3 getBrightnessDependentFogColor(Vec3 vector3d, float float_) {
        float color = 0.95F + 0.05F;
        return vector3d.multiply((float_ * color), (float_ * color), (float_ * color));
    }

    @Override
    public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f modelViewMatrix, Matrix4f projectionMatrix) {
        new JCloudRenderer(JITL.rl("textures/environment/euca_clouds.png")).render(level, ticks, poseStack, projectionMatrix, modelViewMatrix, partialTick, camX, camY, camZ);
        return true;
    }

    @Override
    public boolean isFoggyAt(int int_, int int1_) {
        return false;
    }
}
