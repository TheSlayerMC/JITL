package net.jitl.core.init.internal;

import net.jitl.common.block.entity.GrindstoneEntity;
import net.jitl.common.block.entity.JChestBlockEntity;
import net.jitl.common.block.entity.JFurnaceTile;
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

    public static final RegistryObject<BlockEntityType<JChestBlockEntity>> JCHEST = REGISTRY.register("jchest",
            () -> BlockEntityType.Builder.of(JChestBlockEntity::new,
                    JBlocks.JOURNEY_CHEST.get(), JBlocks.EUCA_CHEST.get(), JBlocks.BOIL_CHEST.get(), JBlocks.FROZEN_CHEST.get(), JBlocks.NETHER_CHEST.get()).build(null));

    public static final RegistryObject<BlockEntityType<CampfireBlockEntity>> CAMPFIRE = REGISTRY.register("campfire",
            () -> BlockEntityType.Builder.of(CampfireBlockEntity::new, JBlocks.BITTERWOOD_CAMPFIRE.get()).build(null));

    public static final RegistryObject<BlockEntityType<JFurnaceTile>> JFURNACE = REGISTRY.register("furnace",
            () -> BlockEntityType.Builder.of(JFurnaceTile::new, JBlocks.GOLDITE_FURNACE.get(), JBlocks.PERMAFROST_FURNACE.get()).build(null));
}
