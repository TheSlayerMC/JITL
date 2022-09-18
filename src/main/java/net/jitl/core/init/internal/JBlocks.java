package net.jitl.core.init.internal;

import net.jitl.common.block.JFenceBlock;
import net.jitl.common.block.JLeavesBlock;
import net.jitl.common.block.JSaplingBlock;
import net.jitl.common.world.gen.tree_grower.EucaGoldTreeGrower;
import net.jitl.common.world.gen.tree_grower.EucaGreenTreeGrower;
import net.jitl.core.init.JBlockProperties;
import net.minecraft.world.level.block.*;
import net.minecraftforge.registries.RegistryObject;

public class JBlocks {

    public static final RegistryObject<Block> IRIDIUM_ORE = BlockRegistrys.register("iridium_ore", "Iridium Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> IRIDIUM_BLOCK = BlockRegistrys.registerFuelBlock("iridium_block", "Iridium Block", () -> new Block(JBlockProperties.STONE), 16000);
    public static final RegistryObject<Block> DEEPSLATE_IRIDIUM_ORE = BlockRegistrys.register("deepslate_iridium_ore", "Deepslate Iridium Ore", JBlockProperties.STONE);

    public static final RegistryObject<Block> SAPPHIRE_ORE = BlockRegistrys.register("sapphire_ore", "Sapphire Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> SAPPHIRE_BLOCK = BlockRegistrys.register("sapphire_block", "Sapphire Block", JBlockProperties.STONE);
    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = BlockRegistrys.register("deepslate_sapphire_ore", "Deepslate Sapphire Ore", JBlockProperties.STONE);

    public static final RegistryObject<Block> SHADIUM_ORE = BlockRegistrys.register("shadium_ore", "Shadium Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> SHADIUM_BLOCK = BlockRegistrys.register("shadium_block", "Shadium Block", JBlockProperties.STONE);
    public static final RegistryObject<Block> DEEPSLATE_SHADIUM_ORE = BlockRegistrys.register("deepslate_shadium_ore", "Deepslate Shadium Ore", JBlockProperties.STONE);

    public static final RegistryObject<Block> LUNIUM_ORE = BlockRegistrys.register("lunium_ore", "Lunium Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> LUNIUM_BLOCK = BlockRegistrys.register("lunium_block", "Lunium Block", JBlockProperties.STONE);
    public static final RegistryObject<Block> DEEPSLATE_LUNIUM_ORE = BlockRegistrys.register("deepslate_lunium_ore", "Deepslate Lunium Ore", JBlockProperties.STONE);

    public static final RegistryObject<Block> WARPED_QUARTZ_ORE = BlockRegistrys.register("warped_quartz_ore", "Warped Quartz Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> WARPED_QUARTZ_BLOCK = BlockRegistrys.register("warped_quartz_block", "Warped Quartz Block", JBlockProperties.STONE);

    public static final RegistryObject<Block> BLOODCRUST_ORE = BlockRegistrys.register("bloodcrust_ore", "Bloodcrust Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> BLOODCRUST_BLOCK = BlockRegistrys.register("bloodcrust_block", "Bloodcrust Block", JBlockProperties.STONE);

    public static final RegistryObject<Block> ENDERILLIUM_ORE = BlockRegistrys.register("enderillium_ore", "Enderillium Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> ENDERILLIUM_BLOCK = BlockRegistrys.register("enderillium_block", "Enderillium Block", JBlockProperties.STONE);

    public static final RegistryObject<Block> GOLDITE_DIRT = BlockRegistrys.register("goldite_dirt", "Goldite Soil", JBlockProperties.DIRT);
    public static final RegistryObject<Block> GOLDITE_STONE = BlockRegistrys.register("goldite_stone", "Goldite Stone", JBlockProperties.STONE);

    public static final RegistryObject<RotatedPillarBlock> EUCA_GOLD_LOG = BlockRegistrys.registerLog("euca_gold_log", "Euca Gold Log");
    public static final RegistryObject<Block> EUCA_GOLD_LEAVES = BlockRegistrys.register("euca_gold_leaves", "Euca Gold Leaves", JBlockProperties.LEAVES);//JLeavesBlock::new);
    public static final RegistryObject<Block> EUCA_GOLD_SAPLING = BlockRegistrys.registerCrossBlock("euca_gold_sapling", "Euca Gold Sapling", () -> new JSaplingBlock(new EucaGoldTreeGrower()));
    public static final RegistryObject<Block> EUCA_GOLD_PLANKS = BlockRegistrys.register("euca_gold_planks", "Euca Gold Planks", JBlockProperties.WOOD);
    public static final RegistryObject<DoorBlock> EUCA_GOLD_DOOR = BlockRegistrys.registerDoor("euca_gold_door", "Euca Gold Door");
    public static final RegistryObject<TrapDoorBlock> EUCA_GOLD_TRAP_DOOR = BlockRegistrys.registerTrapDoor("euca_gold_trap_door", "Euca Gold Trap Door");
    public static final RegistryObject<StairBlock> EUCA_GOLD_STAIRS = BlockRegistrys.registerStairs("euca_gold_stairs", "Euca Gold Plank Stairs", EUCA_GOLD_PLANKS);
    public static final RegistryObject<SlabBlock> EUCA_GOLD_SLAB = BlockRegistrys.registerSlab("euca_gold_slab", "Euca Gold Plank Slab");
    public static final RegistryObject<ButtonBlock> EUCA_GOLD_BUTTON = BlockRegistrys.registerButton("euca_gold_button", "Euca Gold Button", false);
    public static final RegistryObject<PressurePlateBlock> EUCA_GOLD_PRESSURE_PLATE = BlockRegistrys.registerPressurePlate("euca_gold_pressure_plate", "Euca Gold Pressure Plate", PressurePlateBlock.Sensitivity.EVERYTHING);
    public static final RegistryObject<FenceGateBlock> EUCA_GOLD_FENCE_GATE = BlockRegistrys.registerFenceGate("euca_gold_fence_gate", "Euca Gold Fence Gate");
    public static final RegistryObject<JFenceBlock> EUCA_GOLD_FENCE = BlockRegistrys.registerFence("euca_gold_fence", "Euca Gold Fence");

    public static final RegistryObject<RotatedPillarBlock> EUCA_BROWN_LOG = BlockRegistrys.registerLog("euca_brown_log", "Euca Brown Log");
    public static final RegistryObject<Block> EUCA_GREEN_LEAVES = BlockRegistrys.register("euca_green_leaves", "Euca Green Leaves", JBlockProperties.LEAVES);// JLeavesBlock::new);
    public static final RegistryObject<Block> EUCA_GREEN_SAPLING = BlockRegistrys.registerCrossBlock("euca_green_sapling", "Euca Green Sapling", () -> new JSaplingBlock(new EucaGreenTreeGrower()));
    public static final RegistryObject<Block> EUCA_BROWN_PLANKS = BlockRegistrys.register("euca_brown_planks", "Euca Brown Planks", JBlockProperties.WOOD);
    public static final RegistryObject<DoorBlock> EUCA_BROWN_DOOR = BlockRegistrys.registerDoor("euca_brown_door", "Euca Brown Door");
    public static final RegistryObject<TrapDoorBlock> EUCA_BROWN_TRAP_DOOR = BlockRegistrys.registerTrapDoor("euca_brown_trap_door", "Euca Brown Trap Door");
    public static final RegistryObject<StairBlock> EUCA_BROWN_STAIRS = BlockRegistrys.registerStairs("euca_brown_stairs", "Euca Brown Plank Stairs", EUCA_BROWN_PLANKS);
    public static final RegistryObject<SlabBlock> EUCA_BROWN_SLAB = BlockRegistrys.registerSlab("euca_brown_slab", "Euca Brown Plank Slab");
    public static final RegistryObject<ButtonBlock> EUCA_BROWN_BUTTON = BlockRegistrys.registerButton("euca_brown_button", "Euca Brown Button", true);
    public static final RegistryObject<PressurePlateBlock> EUCA_BROWN_PRESSURE_PLATE = BlockRegistrys.registerPressurePlate("euca_brown_pressure_plate", "Euca Brown Pressure Plate", PressurePlateBlock.Sensitivity.EVERYTHING);
    public static final RegistryObject<FenceGateBlock> EUCA_BROWN_FENCE_GATE = BlockRegistrys.registerFenceGate("euca_brown_fence_gate", "Euca Brown Fence Gate");
    public static final RegistryObject<JFenceBlock> EUCA_BROWN_FENCE = BlockRegistrys.registerFence("euca_brown_fence", "Euca Brown Fence");


}