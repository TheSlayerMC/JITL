package net.jitl.common.capability.keypressed;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PressedKeyCapProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PressedKeyCap> PRESSED_KEY_CAP = CapabilityManager.get(new CapabilityToken<>() { });

    private PressedKeyCap keyCap = null;
    private final LazyOptional<PressedKeyCap> optional = LazyOptional.of(this::createPressedKeyCap);

    private @NotNull PressedKeyCap createPressedKeyCap() {
        if(this.keyCap == null) {
            this.keyCap = new PressedKeyCap();
        }
        return this.keyCap;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PRESSED_KEY_CAP) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        createPressedKeyCap().saveNBT(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPressedKeyCap().readNBT(nbt);
    }
}