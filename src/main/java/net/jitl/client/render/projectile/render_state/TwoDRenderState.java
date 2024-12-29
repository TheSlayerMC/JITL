package net.jitl.client.render.projectile.render_state;

import net.minecraft.client.renderer.entity.state.ThrownItemRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TwoDRenderState extends ThrownItemRenderState {

    public ResourceLocation texture;
    public Entity entity;
}
