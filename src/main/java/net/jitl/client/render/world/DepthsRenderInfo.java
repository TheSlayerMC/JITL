package net.jitl.client.render.world;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class DepthsRenderInfo extends DimensionSpecialEffects {

    public DepthsRenderInfo() {
        super(SkyType.NONE, false, false);
    }

    @Override
    public @NotNull Vec3 getBrightnessDependentFogColor(Vec3 vector3d, float float_) {
        float color = 0.95F + 0.05F;
        return vector3d.multiply((float_ * color), (float_ * color), (float_ * color));
    }

    @Override
    public boolean isFoggyAt(int int_, int int1_) {
        return false;
    }
}
