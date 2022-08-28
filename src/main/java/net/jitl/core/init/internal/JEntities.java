package net.jitl.core.init.internal;

import net.jitl.common.entity.ConjuringProjectileEntity;
import net.jitl.common.entity.EssenciaBoltEntity;
import net.jitl.common.entity.EssenciaProjectileEntity;
import net.jitl.core.init.JITL;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JEntities {

    private static final int OVERWORLD_COLOR = 0x32f53f;
    private static final int NETHER_COLOR = 0x881a2b;
    private static final int END_COLOR = 0x000000/*0x931aa3*/;
    private static final int FROZEN_COLOR = 0x3ea4ff;
    private static final int BOILING_COLOR = 0xeb8026;
    private static final int EUCA_COLOR = 0xffff0b;
    private static final int DEPTHS_COLOR = 0x0705a7;
    private static final int CORBA_COLOR = 0x106903;
    private static final int TERRANIA_COLOR = 0x91046d;
    private static final int CLOUDIA_COLOR = 0xfa45cd;
    private static final int SENTERIAN_COLOR = 0x2e2d2c;

    //mob type colors
    private static final int PASSIVE_COLOR = 0x00ff00;
    private static final int NEUTRAL_COLOR = 0x555555;
    private static final int HOSTILE_COLOR = 0xff0000;
    private static final int TRADER_COLOR = 0x7d007d;
    private static final int BOSS_COLOR = 0xffff7d;

    private static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, JITL.MODID);

    public static final RegistryObject<EntityType<ConjuringProjectileEntity>> CONJURING_PROJECTILE_TYPE = REGISTRY.register("conjuring_projectile", () ->
            EntityType.Builder.<ConjuringProjectileEntity>of(ConjuringProjectileEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).build("conjuring_projectile"));

    public static final RegistryObject<EntityType<EssenciaProjectileEntity>> ESSENCIA_PROJECTILE_TYPE = REGISTRY.register("essencia_projectile", () ->
            EntityType.Builder.<EssenciaProjectileEntity>of(EssenciaProjectileEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).build("essencia_projectile"));

    public static final RegistryObject<EntityType<EssenciaBoltEntity>> ESSENCIA_BOLT_TYPE = REGISTRY.register("essencia_bolt", () ->
            EntityType.Builder.<EssenciaBoltEntity>of(EssenciaBoltEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).build("essencia_bolt"));


    public static void register(IEventBus bus) {
        REGISTRY.register(bus);
    }
}