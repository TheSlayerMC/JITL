package net.jitl.core.helper;

import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class JourneySoundType extends DeferredSoundType {
    private final Supplier<SoundEvent> breakSound;
    private final Supplier<SoundEvent> stepSound;
    private final Supplier<SoundEvent> placeSound;
    private final Supplier<SoundEvent> hitSound;
    private final Supplier<SoundEvent> fallSound;

    public JourneySoundType(float volumeIn, float pitchIn, Supplier<SoundEvent> breakSound, Supplier<SoundEvent> stepSound, Supplier<SoundEvent> placeSound, Supplier<SoundEvent> hitSound, Supplier<SoundEvent> fallSound) {
        super(volumeIn, pitchIn, STONE::getBreakSound, STONE::getStepSound, STONE::getPlaceSound, STONE::getHitSound, STONE::getFallSound);
        this.breakSound = breakSound;
        this.stepSound = stepSound;
        this.placeSound = placeSound;
        this.hitSound = hitSound;
        this.fallSound = fallSound;
    }

    @Override
    public @NotNull SoundEvent getBreakSound() {
        return this.breakSound.get();
    }

    @Override
    public @NotNull SoundEvent getStepSound() {
        return this.stepSound.get();
    }

    @Override
    public @NotNull SoundEvent getPlaceSound() {
        return this.placeSound.get();
    }

    @Override
    public @NotNull SoundEvent getHitSound() {
        return this.hitSound.get();
    }

    @Override
    public @NotNull SoundEvent getFallSound() {
        return this.fallSound.get();
    }
}
