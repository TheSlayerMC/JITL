package net.jitl.client.render.block.state;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.level.block.state.properties.ChestType;

public class JChestRenderState extends BlockEntityRenderState {
    public ChestType type;
    public float open;
    public float angle;
    public JChestMaterialType material;
    public ItemStackRenderState item;
    public float facingYAngle;
    public boolean isLocked;

    public JChestRenderState() {
        this.type = ChestType.SINGLE;
        this.material = JChestRenderState.JChestMaterialType.JOURNEY;
        this.item = new ItemStackRenderState();
        this.facingYAngle = 0F;
    }

    public enum JChestMaterialType {
        BOILING,
        CLOUDIA,
        CORBA,
        DEPTHS,
        EUCA,
        FROZEN,
        JOURNEY,
        NETHER,
        SENTERIAN,
        TERRANIAN
    }
}
