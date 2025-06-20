package net.jitl.common.capability.keypressed;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.util.ValueIOSerializable;
import org.jetbrains.annotations.UnknownNullability;

public class PressedKeyCap implements ValueIOSerializable {
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
    public void serialize(ValueOutput valueOutput) {
        valueOutput.putBoolean("armor", armor);
        valueOutput.putBoolean("amulet", amulet);
    }

    @Override
    public void deserialize(ValueInput valueInput) {
        this.armor = valueInput.getBooleanOr("armor", false);
        this.amulet = valueInput.getBooleanOr("amulet", false);
    }
}
