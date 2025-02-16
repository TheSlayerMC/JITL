package net.jitl.core.init.internal;

import net.jitl.core.init.JITL;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;

public class JDamageSources {

    private final Registry<DamageType> damageTypes;
    public final DamageSource BRADBERRY_BUSH;
    public final DamageSource REDCURRANT_BUSH;
    public final DamageSource DEMONIC_BOMB;
    public final DamageSource FIRE_BOMB;

    private JDamageSources(RegistryAccess r) {
        this.damageTypes = r.registryOrThrow(Registries.DAMAGE_TYPE);
        this.BRADBERRY_BUSH = source(makeSource("bradberry_bush"));
        this.REDCURRANT_BUSH = source(makeSource("redcurrant_bush"));
        this.DEMONIC_BOMB = source(makeSource("demonic_bomb"));
        this.FIRE_BOMB = source(makeSource("fire_bomb"));
    }

    private ResourceKey<DamageType> makeSource(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.withDefaultNamespace(name));
    }

    private DamageSource source(ResourceKey<DamageType> r) {
        return new DamageSource(this.damageTypes.getHolderOrThrow(r));
    }
}