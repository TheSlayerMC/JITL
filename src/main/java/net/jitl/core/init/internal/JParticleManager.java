package net.jitl.core.init.internal;

import net.jitl.client.render.particle.*;
import net.jitl.core.init.JITL;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.SnowflakeParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class JParticleManager {

    public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(Registries.PARTICLE_TYPE, JITL.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> RED_FLAME = REGISTRY.register("red_flame", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MUD = REGISTRY.register("mud", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CONJURING = REGISTRY.register("conjuring", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ESSENCIA_LIGHTNING = REGISTRY.register("essencia_lightning", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MINERS_PEARL = REGISTRY.register("miners_pearl", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SNOWFLAKE = REGISTRY.register("snowflake", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SULPHUR = REGISTRY.register("sulphur", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CRYSTAL_FRUIT = REGISTRY.register("crystal_fruit", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GOLDITE_FLOWER = REGISTRY.register("goldite_flower", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FLAME_POLLEN = REGISTRY.register("flame_pollen", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CAVE_VINE = REGISTRY.register("cave_vine", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> HELLSTONE = REGISTRY.register("hellstone", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> TERRANIA_PORTAL = REGISTRY.register("terrania", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CLOUDIA_PORTAL = REGISTRY.register("cloudia", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SWAMP_FLY = REGISTRY.register("swamp_fly", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> IRIDIUM_TORCH = REGISTRY.register("iridium_torch", () -> new SimpleParticleType(false));

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(RED_FLAME.get(), RedFlameParticle.Factory::new);
        event.registerSpriteSet(MUD.get(), MudParticle.Factory::new);
        event.registerSpriteSet(CONJURING.get(), ConjuringParticle.Factory::new);
        event.registerSpriteSet(ESSENCIA_LIGHTNING.get(), EssenciaLightningParticle.Factory::new);
        event.registerSpriteSet(MINERS_PEARL.get(), MinersPearlParticle.Factory::new);
        event.registerSpriteSet(SNOWFLAKE.get(), SnowflakeParticle.Provider::new);
        event.registerSpriteSet(SULPHUR.get(), SulphurParticle.Factory::new);
        event.registerSpriteSet(CRYSTAL_FRUIT.get(), CrystalFruitParticle.Factory::new);
        event.registerSpriteSet(GOLDITE_FLOWER.get(), GolditeFlowerParticle.Factory::new);
        event.registerSpriteSet(FLAME_POLLEN.get(), FlamePollenParticle.Factory::new);
        event.registerSpriteSet(HELLSTONE.get(), HellstoneParticle.Factory::new);
        event.registerSpriteSet(TERRANIA_PORTAL.get(), TerraniaPortalParticle.Factory::new);
        event.registerSpriteSet(CLOUDIA_PORTAL.get(), CloudiaPortalParticle.Factory::new);
        event.registerSpriteSet(SWAMP_FLY.get(), SwampFlyParticle.Factory::new);
        event.registerSpriteSet(IRIDIUM_TORCH.get(), FlameParticle.Provider::new);
    }
}
