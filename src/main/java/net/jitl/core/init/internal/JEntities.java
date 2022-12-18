package net.jitl.core.init.internal;

import net.jitl.common.entity.base.JBoat;
import net.jitl.common.entity.boss.RockiteSmasher;
import net.jitl.common.entity.boss.TowerGuardian;
import net.jitl.common.entity.euca.*;
import net.jitl.common.entity.frozen.Eskimo;
import net.jitl.common.entity.frozen.FrozenGuardian;
import net.jitl.common.entity.nether.Witherspine;
import net.jitl.common.entity.overworld.BoomBoom;
import net.jitl.common.entity.overworld.BrownHongo;
import net.jitl.common.entity.overworld.Floro;
import net.jitl.common.entity.overworld.IllagerMech;
import net.jitl.common.entity.overworld.npc.Mage;
import net.jitl.common.entity.projectile.*;
import net.jitl.core.init.JITL;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = JITL.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JEntities {

    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, JITL.MODID);
    public static final DeferredRegister<Item> EGG_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, JITL.MODID);
    public static final ArrayList<String> eggName = new ArrayList<>();
    public static final ArrayList<String> eggLangName = new ArrayList<>();
    public static final ArrayList<String> entityName = new ArrayList<>();
    public static final ArrayList<String> entityLangName = new ArrayList<>();

    public static final RegistryObject<EntityType<ConjuringProjectileEntity>> CONJURING_PROJECTILE_TYPE = REGISTRY.register("conjuring_projectile", () ->
            EntityType.Builder.<ConjuringProjectileEntity>of(ConjuringProjectileEntity::new, MobCategory.MISC)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(0.25F, 0.25F).build("conjuring_projectile"));

    public static final RegistryObject<EntityType<EssenciaProjectileEntity>> ESSENCIA_PROJECTILE_TYPE = REGISTRY.register("essencia_projectile", () ->
            EntityType.Builder.<EssenciaProjectileEntity>of(EssenciaProjectileEntity::new, MobCategory.MISC)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(0.25F, 0.25F).build("essencia_projectile"));

    public static final RegistryObject<EntityType<FloroMudEntity>> FLORO_MUD_TYPE = REGISTRY.register("floro_mud", () ->
            EntityType.Builder.<FloroMudEntity>of(FloroMudEntity::new, MobCategory.MISC)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(0.5F, 0.5F).build("floro_mud"));

    public static final RegistryObject<EntityType<PiercerEntity>> PIERCER_TYPE = REGISTRY.register("piercer", () ->
                    EntityType.Builder.<PiercerEntity>of(PiercerEntity::new, MobCategory.MISC)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(0.5F, 0.5F).build("piercer"));

    public static final RegistryObject<EntityType<KnifeEntity>> KNIFE_TYPE = REGISTRY.register("knife", () ->
            EntityType.Builder.<KnifeEntity>of(KnifeEntity::new, MobCategory.MISC)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(0.5F, 0.5F).build("knife"));

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

    public static final RegistryObject<EntityType<BoomBoom>> BOOM_TYPE = REGISTRY.register("boomboom", () ->
            EntityType.Builder.of(BoomBoom::new, MobCategory.MONSTER)
                    .setTrackingRange(15)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(1F, 1.75F).build("boomboom"));

    public static final RegistryObject<EntityType<TowerGuardian>> TOWER_GUARDIAN_TYPE = REGISTRY.register("tower_guardian", () ->
            EntityType.Builder.of(TowerGuardian::new, MobCategory.MONSTER)
                    .setTrackingRange(15)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(2.0F, 3.5F).build("tower_guardian"));

    public static final RegistryObject<EntityType<RockiteSmasher>> ROCKITE_SMASHER_TYPE = REGISTRY.register("rockite_smasher", () ->
            EntityType.Builder.of(RockiteSmasher::new, MobCategory.MONSTER)
                    .setTrackingRange(15)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(2.0F, 3.25F).build("rockite_smasher"));

    public static final RegistryObject<EntityType<IllagerMech>> ILLAGER_MECH_TYPE = REGISTRY.register("illager_mech", () ->
            EntityType.Builder.of(IllagerMech::new, MobCategory.MONSTER)
                    .setTrackingRange(15)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(2.0F, 3.25F).build("illager_mech"));

    public static final RegistryObject<EntityType<Witherspine>> WITHERSPINE_TYPE = REGISTRY.register("witherspine", () ->
            EntityType.Builder.of(Witherspine::new, MobCategory.MONSTER)
                    .setTrackingRange(15)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(1F, 2.0F).build("witherspine"));

    public static final RegistryObject<EntityType<BrownHongo>> BROWN_HONGO_TYPE = REGISTRY.register("brown_hongo", () ->
            EntityType.Builder.of(BrownHongo::new, MobCategory.MONSTER)
                    .setTrackingRange(15)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(1F, 2.0F).build("brown_hongo"));

    public static final RegistryObject<EntityType<JBoat>> JBOAT_TYPE = REGISTRY.register("jboat", () ->
                    EntityType.Builder.<JBoat>of(JBoat::new, MobCategory.MISC)
                            .setTrackingRange(10)
                            .sized(1.375F, 0.5625F).build("jboat"));

    public static final RegistryObject<EntityType<EucaCharger>> EUCA_CHARGER_TYPE = REGISTRY.register("euca_charger", () ->
            EntityType.Builder.of(EucaCharger::new, MobCategory.MONSTER)
                    .setTrackingRange(15)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(0.5F, 0.75F).build("euca_charger"));

    public static final RegistryObject<EntityType<Dynaster>> DYNASTER_TYPE = REGISTRY.register("dynaster", () ->
            EntityType.Builder.of(Dynaster::new, MobCategory.MONSTER)
                    .setTrackingRange(15)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(1F, 1F).build("dynaster"));

    public static final RegistryObject<EntityType<Goldbot>> GOLDBOT_TYPE = REGISTRY.register("goldbot", () ->
            EntityType.Builder.of(Goldbot::new, MobCategory.MONSTER)
                    .setTrackingRange(15)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(0.5F, 0.75F).build("goldbot"));

    public static final RegistryObject<EntityType<Crypian>> CRYPIAN_TYPE = REGISTRY.register("crypian", () ->
            EntityType.Builder.of(Crypian::new, MobCategory.CREATURE)
                    .setTrackingRange(15)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(0.75F, 1.8F).build("crypian"));

    public static final RegistryObject<EntityType<Shimmerer>> SHIMMERER_TYPE = REGISTRY.register("shimmerer", () ->
            EntityType.Builder.of(Shimmerer::new, MobCategory.MONSTER)
                    .setTrackingRange(15)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(0.5F, 0.75F).build("shimmerer"));

    public static final RegistryObject<EntityType<Golder>> GOLDER_TYPE = REGISTRY.register("golder", () ->
            EntityType.Builder.of(Golder::new, MobCategory.MONSTER)
                    .setTrackingRange(15)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(1F, 2F).build("golder"));

    public static final RegistryObject<EntityType<RoyalKing>> ROYAL_KING_TYPE = REGISTRY.register("royal_king", () ->
            EntityType.Builder.of(RoyalKing::new, MobCategory.CREATURE)
                    .setTrackingRange(15)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(0.75F, 2F).build("royal_king"));

    public static final RegistryObject<EntityType<Eskimo>> ESKIMO_TYPE = REGISTRY.register("eskimo", () ->
            EntityType.Builder.of(Eskimo::new, MobCategory.CREATURE)
                    .setTrackingRange(15)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(0.75F, 2F).build("eskimo"));

    public static final RegistryObject<EntityType<FrozenGuardian>> FROZEN_GUARDIAN_TYPE = REGISTRY.register("frozen_guardian", () ->
            EntityType.Builder.of(FrozenGuardian::new, MobCategory.CREATURE)
                    .setTrackingRange(15)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(0.75F, 2F).build("frozen_guardian"));

    //GET TO WORK
    private static RegistryObject<EntityType<Mob>> registerEntity(EntityType.EntityFactory<Mob> factory, String entityName, String langName, int bg, int fg, float width, float height, MobCategory classification) {
        EntityType<Mob> entity = EntityType.Builder.of(factory, classification).sized(width, height).setTrackingRange(15).setShouldReceiveVelocityUpdates(true).build(entityName);
        JItems.register(entityName + "_spawn_egg", langName + " Spawn Egg", () -> new ForgeSpawnEggItem(() -> entity, bg, fg, JItems.eggProps()), JItems.ItemType.SPAWN_EGG);
        return REGISTRY.register(entityName, () -> entity);
    }

    @SubscribeEvent
    public static void registerAttributes(final EntityAttributeCreationEvent event) {
        event.put(MAGE_TYPE.get(), Mage.createAttributes());
        event.put(FLORO_TYPE.get(), Floro.createAttributes());
        event.put(BOOM_TYPE.get(), BoomBoom.createAttributes());
        event.put(TOWER_GUARDIAN_TYPE.get(), TowerGuardian.createAttributes());
        event.put(ROCKITE_SMASHER_TYPE.get(), RockiteSmasher.createAttributes());
        event.put(WITHERSPINE_TYPE.get(), Witherspine.createAttributes());
        event.put(BROWN_HONGO_TYPE.get(), BrownHongo.createAttributes());
        event.put(ILLAGER_MECH_TYPE.get(), IllagerMech.createAttributes());

        event.put(EUCA_CHARGER_TYPE.get(), EucaCharger.createAttributes());
        event.put(DYNASTER_TYPE.get(), Dynaster.createAttributes());
        event.put(GOLDBOT_TYPE.get(), Goldbot.createAttributes());
        event.put(SHIMMERER_TYPE.get(), Shimmerer.createAttributes());
        event.put(GOLDER_TYPE.get(), Golder.createAttributes());
        event.put(ROYAL_KING_TYPE.get(), RoyalKing.createAttributes());
        event.put(CRYPIAN_TYPE.get(), Crypian.createAttributes());

        event.put(ESKIMO_TYPE.get(), Eskimo.createAttributes());
        event.put(FROZEN_GUARDIAN_TYPE.get(), FrozenGuardian.createAttributes());

    }


    @SubscribeEvent
    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event) {
        event.register(SHIMMERER_TYPE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Shimmerer::checkSpawn, SpawnPlacementRegisterEvent.Operation.AND);
    }
}