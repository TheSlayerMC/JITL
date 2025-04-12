package net.jitl.client.render.entity.euca.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrypianRenderState extends LivingEntityRenderState {

    public boolean canTrade, isAlloyHouse;

}