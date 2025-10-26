package net.jitl.client.render.projectile.state;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class JThrownItemRenderState extends EntityRenderState {

    public float tick, xRot, yRot;
    public boolean inGround;
    public Level level;
    public int id;
    public ItemStack item;
    public ItemStackRenderState renderState;

    public JThrownItemRenderState() {
        this.renderState = new ItemStackRenderState();
    }
}