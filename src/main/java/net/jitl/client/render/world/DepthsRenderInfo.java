package net.jitl.client.render.world;

import net.minecraft.client.Minecraft;

public class DepthsRenderInfo extends JDimensionSpecialEffects {

    public DepthsRenderInfo() {
        super(Minecraft.getInstance().getTextureManager(), Minecraft.getInstance().getAtlasManager());
    }

//    @Override
//    public @NotNull Vec3 getBrightnessDependentFogColor(Vec3 vector3d, float float_) {
//        float color = 0.95F + 0.05F;
//        return vector3d.multiply((float_ * color), (float_ * color), (float_ * color));
//    }
//
//    @Override
//    public boolean isFoggyAt(int int_, int int1_) {
//        return false;
//    }
}
