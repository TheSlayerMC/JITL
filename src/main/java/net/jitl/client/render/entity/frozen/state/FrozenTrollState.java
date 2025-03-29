package net.jitl.client.render.entity.frozen.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FrozenTrollState extends LivingEntityRenderState {

    public boolean isAngry;

}