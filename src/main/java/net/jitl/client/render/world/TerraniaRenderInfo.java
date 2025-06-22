package net.jitl.client.render.world;

import com.mojang.blaze3d.vertex.*;
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

public class TerraniaRenderInfo extends JDimensionSpecialEffects {

    private static final ResourceLocation MOON_LOCATION = JITL.rl("textures/environment/terrania_moon1.png");
    private static final ResourceLocation CLOUDIA_SKY_LOCATION = JITL.rl("textures/environment/cloudia_sky.png");

    public TerraniaRenderInfo() {
        super(SkyType.OVERWORLD, false, false);
    }

    @Override
    public JCloudRenderer getCloudRenderer() {
        return new JCloudRenderer(JITL.rl("textures/environment/cloudia_clouds.png"));
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
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, Matrix4f modelViewMatrix, Camera camera, Runnable setupFog) {
        setupFog.run();
        FogType fogtype = camera.getFluidInCamera();
        if(fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA && !this.doesMobEffectBlockSky(camera)) {
            PoseStack poseStack = new PoseStack();
            renderSky(CLOUDIA_SKY_LOCATION, 2F);

            //START CLOUDIA MOON
            poseStack.pushPose();
            poseStack.mulPose(Axis.YP.rotationDegrees(20F));
            poseStack.mulPose(Axis.ZP.rotationDegrees(-90));
            renderSun(40F, 1F, Minecraft.getInstance().renderBuffers().bufferSource(), poseStack, MOON_LOCATION);
            poseStack.popPose();
        }
        return true;
    }
}