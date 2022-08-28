package net.jitl.core.init.internal;

import net.jitl.common.entity.overworld.Floro;
import net.jitl.common.entity.overworld.npc.Mage;
import net.jitl.common.entity.projectile.ConjuringProjectileEntity;
import net.jitl.common.entity.projectile.EssenciaBoltEntity;
import net.jitl.common.entity.projectile.EssenciaProjectileEntity;
import net.jitl.core.init.JITL;
import net.minecraft.SharedConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.checkerframework.checker.units.qual.h;

import java.util.ArrayList;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = JITL.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
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

    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, JITL.MODID);
    public static final DeferredRegister<Item> EGG_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, JITL.MODID);
    public static final ArrayList<String> eggName = new ArrayList<>();
    public static final ArrayList<String> eggLangName = new ArrayList<>();
    public static final ArrayList<String> entityName = new ArrayList<>();
    public static final ArrayList<String> entityLangName = new ArrayList<>();

    public static final RegistryObject<EntityType<ConjuringProjectileEntity>> CONJURING_PROJECTILE_TYPE = REGISTRY.register("conjuring_projectile", () ->
            EntityType.Builder.<ConjuringProjectileEntity>of(ConjuringProjectileEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).build("conjuring_projectile"));

    public static final RegistryObject<EntityType<EssenciaProjectileEntity>> ESSENCIA_PROJECTILE_TYPE = REGISTRY.register("essencia_projectile", () ->
            EntityType.Builder.<EssenciaProjectileEntity>of(EssenciaProjectileEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).build("essencia_projectile"));

    public static final RegistryObject<EntityType<EssenciaBoltEntity>> ESSENCIA_BOLT_TYPE = REGISTRY.register("essencia_bolt", () ->
            EntityType.Builder.of(EssenciaBoltEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).build("essencia_bolt"));

    public static final RegistryObject<EntityType<Mage>> MAGE_TYPE = REGISTRY.register("mage", () ->
            EntityType.Builder.of(Mage::new, MobCategory.MONSTER)
                    .sized(1F, 1.75F).build("mage"));

    public static final RegistryObject<EntityType<Floro>> FLORO_TYPE = REGISTRY.register("floro", () ->
            EntityType.Builder.of(Floro::new, MobCategory.MONSTER)
                    .setTrackingRange(15)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(1F, 1.75F).build("floro"));

    @SubscribeEvent
    public static void registerAttributes(final EntityAttributeCreationEvent event) {
        event.put(MAGE_TYPE.get(), Mage.createAttributes());
        event.put(FLORO_TYPE.get(), Floro.createAttributes());
    }
}