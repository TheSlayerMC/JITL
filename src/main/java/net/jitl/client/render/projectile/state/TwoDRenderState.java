package net.jitl.client.render.projectile.state;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;

public class TwoDRenderState extends EntityRenderState {

    public Identifier texture;
    public Entity entity;
    public ItemStackRenderState renderState;
}
