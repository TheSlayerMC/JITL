package net.jitl.common.capability.keypressed;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

public class PressedKeyCap implements INBTSerializable<CompoundTag> {
    private boolean armor;
    private boolean amulet;

    public void copyFrom(PressedKeyCap pressedKeyCap) {
        this.armor = pressedKeyCap.armor;
        this.amulet = pressedKeyCap.amulet;
    }

    public void setArmorPressed(boolean bool) {
        armor = bool;
    }

    public boolean isArmorPressed() {
        return armor;
    }

    public void setAmuletPressed(boolean bool) {
        amulet = bool;
    }

    public boolean isAmuletPressed() {
        return amulet;
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putBoolean("armor", armor);
        nbt.putBoolean("amulet", amulet);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.armor = nbt.getBooleanOr("armor", false);
        this.amulet = nbt.getBooleanOr("amulet", false);
    }
}
