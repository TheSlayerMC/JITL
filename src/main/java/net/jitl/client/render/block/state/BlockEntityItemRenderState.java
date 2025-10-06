package net.jitl.client.render.block.state;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;

public class BlockEntityItemRenderState extends BlockEntityRenderState {

    public ItemStackRenderState item;

    public BlockEntityItemRenderState() {
        this.item = new ItemStackRenderState();
    }
}
