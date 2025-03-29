package net.jitl.core.data;

import net.jitl.core.init.JITL;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class JDamageSources {

    public static final ResourceKey<DamageType> BRADBERRY_BUSH = register("bradberry_bush");
    public static final ResourceKey<DamageType> REDCURRANT_BUSH = register("redcurrant_bush");
    public static final ResourceKey<DamageType> DEMONIC_BOMB = register("demonic_bomb");
    public static final ResourceKey<DamageType> FIRE_BOMB = register("fire_bomb");

    public static void register(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(true, (DataProvider.Factory<DatapackBuiltinEntriesProvider>) packOutput
                -> new DatapackBuiltinEntriesProvider(packOutput, lookupProvider, new RegistrySetBuilder()
                .add(Registries.DAMAGE_TYPE, bootstrap -> bootstrap.register(BRADBERRY_BUSH, addDamageType(BRADBERRY_BUSH)))
//                .add(Registries.DAMAGE_TYPE, bootstrap -> bootstrap.register(REDCURRANT_BUSH, addDamageType(REDCURRANT_BUSH)))
//                .add(Registries.DAMAGE_TYPE, bootstrap -> bootstrap.register(DEMONIC_BOMB, addDamageType(DEMONIC_BOMB)))
//                .add(Registries.DAMAGE_TYPE, bootstrap -> bootstrap.register(FIRE_BOMB, addDamageType(FIRE_BOMB)))
                , Set.of(JITL.MOD_ID))
        );
    }

    private static DamageType addDamageType(ResourceKey<DamageType> damage) {
        return new DamageType(damage.location() + ".damage", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0.1F, DamageEffects.HURT, DeathMessageType.DEFAULT);
    }

    private static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, JITL.rl(name));
    }

    public static DamageSource hurt(Entity causer, ResourceKey<DamageType> damage) {
        return new DamageSource(causer.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(damage), causer);
    }
}
