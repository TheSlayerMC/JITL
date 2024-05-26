package net.jitl.common.capability.player;

import net.jitl.client.util.ClientTools;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

public class Portal implements INBTSerializable<CompoundTag> {

    private float portalOverlayTime = 0F;
    private float oldPortalOverlayTime = 0F;
    private Block portalBlockToRender = Blocks.AIR;
    private int portalTimer = 0;
    private boolean inPortal = false;

    public void copyFrom(Portal source) {
        this.portalOverlayTime = source.portalOverlayTime;
        this.oldPortalOverlayTime = source.oldPortalOverlayTime;
        this.portalBlockToRender = source.portalBlockToRender;
        this.portalTimer = source.portalTimer;
        this.inPortal = source.inPortal;
    }

    public void setInPortal(Block portal, boolean inPortal) {
        this.portalBlockToRender = portal;
        this.inPortal = inPortal;
    }

    public void serverTick() {
        this.oldPortalOverlayTime = this.portalOverlayTime;
        float alphaTime = 0.01F;
        if(this.inPortal) {
            this.portalTimer++;
            this.portalOverlayTime += alphaTime;
            if(this.portalOverlayTime > 1.0F) this.portalOverlayTime = 1.0F;
            this.inPortal = false;
        } else {
            if(this.portalOverlayTime > 0) this.portalOverlayTime -= 0.05F;

            if(this.portalOverlayTime < 0) this.portalOverlayTime = 0;

            if(this.portalTimer > 0) this.portalTimer -= 4;
        }
    }

    public void clientTick() {
        if(this.portalOverlayTime == 0.01F) {
            if(getPortalBlockToRender() == JBlocks.TERRANIAN_PORTAL.get())
                ClientTools.playLocalSound(SoundEvents.PORTAL_TRIGGER, 1.0F, 0.65F);

            if(getPortalBlockToRender() == JBlocks.EUCA_PORTAL.get())
                ClientTools.playLocalSound(JSounds.EUCA_PORTAL, 1.0F, 0.65F);

            if(getPortalBlockToRender() == JBlocks.FROZEN_PORTAL.get())
                ClientTools.playLocalSound(JSounds.FROZEN_PORTAL, 1.0F, 0.65F);

            if(getPortalBlockToRender() == JBlocks.BOIL_PORTAL.get())
                ClientTools.playLocalSound(JSounds.BOIL_PORTAL, 1.0F, 0.65F);

            if(getPortalBlockToRender() == JBlocks.DEPTHS_PORTAL.get())
                ClientTools.playLocalSound(JSounds.DEPTHS_PORTAL, 1.0F, 0.65F);

            if(getPortalBlockToRender() == JBlocks.CORBA_PORTAL.get())
                ClientTools.playLocalSound(JSounds.CORBA_PORTAL, 1.0F, 0.65F);

            if(getPortalBlockToRender() == JBlocks.CLOUDIA_PORTAL.get())
                ClientTools.playLocalSound(JSounds.CLOUDIA_PORTAL, 1.0F, 0.65F);

            if(getPortalBlockToRender() == JBlocks.SENTERIAN_PORTAL.get())
                ClientTools.playLocalSound(JSounds.SENTERIAN_PORTAL, 1.0F, 0.65F);
        }
    }

    public Block getPortalBlockToRender() {
        return this.portalBlockToRender;
    }

    public void setPortalTimer(int timer) {
        this.portalTimer = timer;
    }

    public int getPortalTimer() {
        return this.portalTimer;
    }

    public float getPortalOverlayTime() {
        return this.portalOverlayTime;
    }

    public float getOldPortalOverlayTime() {
        return this.oldPortalOverlayTime;
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putFloat("portalOverlayTime", this.portalOverlayTime);
        nbt.putFloat("oldPortalOverlayTime", this.oldPortalOverlayTime);
        nbt.putInt("portalTimer", this.portalTimer);
        nbt.putBoolean("inPortal", this.inPortal);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.portalOverlayTime = nbt.getFloat("portalOverlayTime");
        this.oldPortalOverlayTime = nbt.getFloat("oldPortalOverlayTime");
        this.portalTimer = nbt.getInt("portalTimer");
        this.inPortal = nbt.getBoolean("inPortal");
    }
}