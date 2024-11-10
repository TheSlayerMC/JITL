package net.jitl.client.render.projectile.render_state;

import net.minecraft.client.renderer.entity.state.ThrownItemRenderState;
import net.minecraft.world.level.Level;

public class KnifeRenderState extends ThrownItemRenderState {

    public float tick, xRot, yRot;
    public boolean inGround;
    public Level level;
    public int id;
}