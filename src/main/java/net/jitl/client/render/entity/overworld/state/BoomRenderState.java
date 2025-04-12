package net.jitl.client.render.entity.overworld.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoomRenderState extends LivingEntityRenderState {

    public float swelling;
    public boolean isPowered;

}
