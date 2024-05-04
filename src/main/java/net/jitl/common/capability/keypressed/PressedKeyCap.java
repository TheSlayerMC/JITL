package net.jitl.common.capability.keypressed;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
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

    public static boolean isAmuletPressedEitherSide(Player player) {
//        if (KeyBindEvents.keyAmulet != null) return KeyBindEvents.keyAmulet.isDown();
//        @NotNull LazyOptional<PressedKeyCap> capability = player.getCapability(PressedKeyCapProvider.PRESSED_KEY_CAP);
//        AtomicBoolean isPressed = new AtomicBoolean(false);
//        capability.ifPresent(pressedKeyCap -> isPressed.set(pressedKeyCap.isAmuletPressed()));
//        return (capability.isPresent() && isPressed.get());
        return false;
    }


    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putBoolean("armor pressed", armor);
        nbt.putBoolean("amulet pressed", amulet);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.armor = nbt.getBoolean("armor pressed");
        this.amulet = nbt.getBoolean("amulet pressed");
    }
}
