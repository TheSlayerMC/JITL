package net.jitl.core.init.internal;

import net.jitl.common.block.entity.*;
import net.jitl.common.block.entity.spawner.*;
import net.jitl.core.init.JITL;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class JBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, JITL.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SummoningTableTile>> SUMMONING_TABLE = REGISTRY.register("summon_table",
            () -> new BlockEntityType<>(SummoningTableTile::new, JBlocks.SUMMONING_TABLE.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RockiteSpawnerEntity>> ROCKITE = REGISTRY.register("rockite",
            () -> new BlockEntityType<>(RockiteSpawnerEntity::new, JBlocks.ROCKITE_SPAWNER.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ObeliskTile>> OBELISK = REGISTRY.register("obelisk",
            () -> new BlockEntityType<>(ObeliskTile::new, JBlocks.ANCIENT_OBELISK.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SenterianAltarTile>> SENTERIAN_ALTAR = REGISTRY.register("senterian_altar",
            () -> new BlockEntityType<>(SenterianAltarTile::new, JBlocks.SENTERIAN_ALTAR.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<JChestBlockEntity>> JCHEST = REGISTRY.register("jchest",
            () -> new BlockEntityType<>(JChestBlockEntity::new,
                    JBlocks.JOURNEY_CHEST.get(), JBlocks.EUCA_CHEST.get(), JBlocks.BOIL_CHEST.get(), JBlocks.FROZEN_CHEST.get(),
                    JBlocks.NETHER_CHEST.get(), JBlocks.DEPTHS_CHEST.get(), JBlocks.CORBA_CHEST.get(), JBlocks.TERRANIAN_CHEST.get(),
                    JBlocks.CLOUDIA_CHEST.get(), JBlocks.SENTERIAN_CHEST.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BitterwoodCampfireBlockEntity>> BITTERWOOD_CAMPFIRE = REGISTRY.register("bitterwood_campfire",
            () -> new BlockEntityType<>(BitterwoodCampfireBlockEntity::new, JBlocks.BITTERWOOD_CAMPFIRE.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<IridiumCampfireBlockEntity>> IRIDIUM_CAMPFIRE = REGISTRY.register("iridium_campfire",
            () -> new BlockEntityType<>(IridiumCampfireBlockEntity::new, JBlocks.IRIDIUM_CAMPFIRE.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<JFurnaceTile>> JFURNACE = REGISTRY.register("furnace",
            () -> new BlockEntityType<>(JFurnaceTile::new, JBlocks.GOLDITE_FURNACE.get(), JBlocks.PERMAFROST_FURNACE.get(), JBlocks.DEPTHS_FURNACE.get(), JBlocks.CORBA_FURNACE.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GoldBotSpawnerEntity>> GOLD_BOT_SPAWNER = REGISTRY.register("gb_spawner",
            () -> new BlockEntityType<>(GoldBotSpawnerEntity::new, JBlocks.GOLD_BOT_SPAWNER.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DarkSorcererSpawnerEntity>> DARK_SORCERER_SPAWNER = REGISTRY.register("ds_spawner",
            () -> new BlockEntityType<>(DarkSorcererSpawnerEntity::new, JBlocks.DARK_SORCERER_SPAWNER.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FrostbiterSpawnerEntity>> FROSTBITER_SPAWNER = REGISTRY.register("fb_spawner",
            () -> new BlockEntityType<>(FrostbiterSpawnerEntity::new, JBlocks.FROSTBITER_SPAWNER.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MiniGhastSpawnerEntity>> MINI_GHAST_SPAWNER = REGISTRY.register("mg_spawner",
            () -> new BlockEntityType<>(MiniGhastSpawnerEntity::new, JBlocks.MINI_GHAST_SPAWNER.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HellwingSpawnerEntity>> HELLWING_SPAWNER = REGISTRY.register("hw_spawner",
            () -> new BlockEntityType<>(HellwingSpawnerEntity::new, JBlocks.HELLWING_SPAWNER.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ObserverSpawnerEntity>> OBSERVER_SPAWNER = REGISTRY.register("observer_spawner",
            () -> new BlockEntityType<>(ObserverSpawnerEntity::new, JBlocks.OBSERVER_SPAWNER.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ScreamerSpawnerEntity>> SCREAMER_SPAWNER = REGISTRY.register("screamer_spawner",
            () -> new BlockEntityType<>(ScreamerSpawnerEntity::new, JBlocks.SCREAMER_SPAWNER.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HellbotSpawnerEntity>> HELLBOT_SPAWNER = REGISTRY.register("hb_spawner",
            () -> new BlockEntityType<>(HellbotSpawnerEntity::new, JBlocks.HELLBOT_SPAWNER.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<OverseerElderSpawnerEntity>> OVERSEER_ELDER_SPAWNER = REGISTRY.register("oe_spawner",
            () -> new BlockEntityType<>(OverseerElderSpawnerEntity::new, JBlocks.OVERSEER_ELDER_SPAWNER.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<OverseerSpawnerEntity>> OVERSEER_SPAWNER = REGISTRY.register("ov_spawner",
            () -> new BlockEntityType<>(OverseerSpawnerEntity::new, JBlocks.OVERSEER_SPAWNER.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PedestalTile>> PEDESTAL = REGISTRY.register("pedestal",
            () -> new BlockEntityType<>(PedestalTile::new, JBlocks.FROZEN_PEDESTAL.get(), JBlocks.ROYAL_PEDESTAL.get()));
}
