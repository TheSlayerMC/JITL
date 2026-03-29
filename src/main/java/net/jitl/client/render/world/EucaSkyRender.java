package net.jitl.client.render.world;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jitl.core.init.JITL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.client.renderer.state.level.SkyRenderState;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.CustomSkyboxRenderer;
import org.joml.Matrix4fc;

public class EucaSkyRender implements CustomSkyboxRenderer {

    private static final Identifier CORBA_MOON_LOCATION = JITL.rl("textures/environment/corba_moon.png");

    public EucaSkyRender() { }

    @Override
    public boolean renderSky(LevelRenderState levelRenderState, SkyRenderState skyRenderState, Matrix4fc modelViewMatrix, Runnable setupFog) {
        JDimensionSpecialEffects sky = new JDimensionSpecialEffects(Minecraft.getInstance().getTextureManager(), Minecraft.getInstance().getAtlasManager());
        setupFog.run();
            PoseStack poseStack = new PoseStack();
            poseStack.pushPose();

            //START CORBA MOON
            poseStack.mulPose(Axis.XP.rotationDegrees(90F));
            sky.renderSun(1F, poseStack, sky.buildSunQuad(sky.celestialsAtlas, 40F, CORBA_MOON_LOCATION));
            poseStack.popPose();

        return true;
    }

    /*
    @Override
    public boolean isFoggyAt(int int_, int int1_) {
        return false;
    }*/
}
