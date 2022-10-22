package net.jitl.common.capability.gear;

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

public class PlayerArmorProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerArmor> PLAYER_ARMOR = CapabilityManager.get(new CapabilityToken<>() { });

    private PlayerArmor playerArmor = null;
    private final LazyOptional<PlayerArmor> optional = LazyOptional.of(this::createPlayerArmor);

    private @NotNull PlayerArmor createPlayerArmor() {
        if(this.playerArmor == null) {
            this.playerArmor = new PlayerArmor();
        }
        return this.playerArmor;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_ARMOR) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        createPlayerArmor().saveNBT(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerArmor().readNBT(nbt);
    }
}