package net.jitl.core.init.internal;

import net.jitl.common.block.entity.*;
import net.jitl.core.init.JITL;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, JITL.MODID);

    public static final RegistryObject<BlockEntityType<GrindstoneEntity>> GRINDSTONE = REGISTRY.register("grindstone",
            () -> BlockEntityType.Builder.of(GrindstoneEntity::new, JBlocks.GRINDSTONE.get()).build(null));

    public static final RegistryObject<BlockEntityType<RockiteSpawnerEntity>> ROCKITE = REGISTRY.register("rockite",
            () -> BlockEntityType.Builder.of(RockiteSpawnerEntity::new, JBlocks.ROCKITE_SPAWNER.get()).build(null));

    public static final RegistryObject<BlockEntityType<JChestBlockEntity>> JCHEST = REGISTRY.register("jchest",
            () -> BlockEntityType.Builder.of(JChestBlockEntity::new,
                    JBlocks.JOURNEY_CHEST.get(), JBlocks.EUCA_CHEST.get(), JBlocks.BOIL_CHEST.get(), JBlocks.FROZEN_CHEST.get(),
                    JBlocks.NETHER_CHEST.get(), JBlocks.DEPTHS_CHEST.get(), JBlocks.CORBA_CHEST.get()).build(null));

    public static final RegistryObject<BlockEntityType<CampfireBlockEntity>> CAMPFIRE = REGISTRY.register("campfire",
            () -> BlockEntityType.Builder.of(CampfireBlockEntity::new, JBlocks.BITTERWOOD_CAMPFIRE.get()).build(null));

    public static final RegistryObject<BlockEntityType<JFurnaceTile>> JFURNACE = REGISTRY.register("furnace",
            () -> BlockEntityType.Builder.of(JFurnaceTile::new, JBlocks.GOLDITE_FURNACE.get(), JBlocks.PERMAFROST_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<GoldBotSpawnerEntity>> GOLD_BOT_SPAWNER = REGISTRY.register("gb_spawner",
            () -> BlockEntityType.Builder.of(GoldBotSpawnerEntity::new, JBlocks.GOLD_BOT_SPAWNER.get()).build(null));

    public static final RegistryObject<BlockEntityType<MinIGhastSpawnerEntity>> MINI_GHAST_SPAWNER = REGISTRY.register("mg_spawner",
            () -> BlockEntityType.Builder.of(MinIGhastSpawnerEntity::new, JBlocks.MINI_GHAST_SPAWNER.get()).build(null));

    public static final RegistryObject<BlockEntityType<PedestalTile>> PEDESTAL = REGISTRY.register("pedestal",
            () -> BlockEntityType.Builder.of(PedestalTile::new, JBlocks.FROZEN_PEDESTAL.get(), JBlocks.ROYAL_PEDESTAL.get()).build(null));
}
