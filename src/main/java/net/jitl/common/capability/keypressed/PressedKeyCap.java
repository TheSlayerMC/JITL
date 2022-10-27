package net.jitl.common.capability.keypressed;

import net.jitl.client.gui.KeyBindEvents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

public class PressedKeyCap {
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
        if (KeyBindEvents.keyAmulet != null) return KeyBindEvents.keyAmulet.isDown();
        @NotNull LazyOptional<PressedKeyCap> capability = player.getCapability(PressedKeyCapProvider.PRESSED_KEY_CAP);
        AtomicBoolean isPressed = new AtomicBoolean(false);
        capability.ifPresent(pressedKeyCap -> isPressed.set(pressedKeyCap.isAmuletPressed()));
        return (capability.isPresent() && isPressed.get());
    }


    public void saveNBT(CompoundTag nbt) {
        nbt.putBoolean("armor pressed", armor);
        nbt.putBoolean("amulet pressed", amulet);
    }

    public void readNBT(CompoundTag nbt) {
        this.armor = nbt.getBoolean("armor pressed");
        this.amulet = nbt.getBoolean("amulet pressed");
    }
}
