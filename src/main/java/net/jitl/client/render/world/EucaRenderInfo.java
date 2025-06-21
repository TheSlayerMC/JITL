package net.jitl.client.render.world;

import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.jitl.client.render.world.clouds.JCloudRenderer;
import net.jitl.core.init.JITL;
import net.minecraft.client.Camera;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import java.util.Optional;

public class EucaRenderInfo extends JDimensionSpecialEffects {

    private static final ResourceLocation CORBA_MOON_LOCATION = JITL.rl("textures/environment/corba_moon.png");

    public EucaRenderInfo() {
        super(SkyType.OVERWORLD, false, false);
    }

    @Override
    public @NotNull Vec3 getBrightnessDependentFogColor(Vec3 vector3d, float float_) {
        float color = 0.95F + 0.05F;
        return vector3d.multiply((float_ * color), (float_ * color), (float_ * color));
    }

    @Override
    public JCloudRenderer getCloudRenderer() {
        return new JCloudRenderer(JITL.rl("textures/environment/euca_clouds.png"));
    }

    @Override
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, Matrix4f modelViewMatrix, Camera camera, Runnable setupFog) {
        setupFog.run();
        FogType fogtype = camera.getFluidInCamera();
        if(fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA && !doesMobEffectBlockSky(camera)) {
            PoseStack poseStack = new PoseStack();
            poseStack.pushPose();

            //START CORBA MOON
            poseStack.mulPose(Axis.YP.rotationDegrees(-180F));
            poseStack.mulPose(Axis.XP.rotationDegrees(-24000F));
            renderSun(20F, 1F, Minecraft.getInstance().renderBuffers().bufferSource(), poseStack, CORBA_MOON_LOCATION);
            poseStack.popPose();
        }
        return true;
    }

    @Override
    public boolean isFoggyAt(int int_, int int1_) {
        return false;
    }
}
