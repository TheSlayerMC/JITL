package net.jitl.client.render.block.state;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;

import java.util.Collections;
import java.util.List;

public class SummoningTableRenderState extends BlockEntityRenderState {

    public List<ItemStackRenderState> items = Collections.emptyList();

}
