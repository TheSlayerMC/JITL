package net.jitl.client.knowledge;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerKnowledgeProvider implements ICapabilitySerializable<CompoundTag> {

    public static Capability<PlayerKnowledge> PLAYER_KNOWLEDGE = CapabilityManager.get(new CapabilityToken<>() { });

    private PlayerKnowledge knowledge = null;
    private final LazyOptional<PlayerKnowledge> optional = LazyOptional.of(this::createPlayerKnowledge);

    private @NotNull PlayerKnowledge createPlayerKnowledge() {
        if(this.knowledge == null) {
            this.knowledge = new PlayerKnowledge();
        }
        return this.knowledge;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_KNOWLEDGE) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        createPlayerKnowledge().saveNBT(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerKnowledge().readNBT(nbt);
    }
}