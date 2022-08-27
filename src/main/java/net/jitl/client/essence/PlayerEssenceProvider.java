package net.jitl.client.essence;

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

public class PlayerEssenceProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerEssence> PLAYER_ESSENCE = CapabilityManager.get(new CapabilityToken<>() { });

    private PlayerEssence essence = null;
    private final LazyOptional<PlayerEssence> optional = LazyOptional.of(this::createPlayerEssence);

    private PlayerEssence createPlayerEssence() {
        if(this.essence == null) {
            this.essence = new PlayerEssence();
        }
        return this.essence;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_ESSENCE) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        createPlayerEssence().saveNBT(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerEssence().readNBT(nbt);
    }
}