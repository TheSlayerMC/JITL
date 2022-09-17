package net.jitl.core.init.internal;

import net.jitl.common.block.JFenceBlock;
import net.jitl.core.init.JBlockProperties;
import net.jitl.core.init.JITL;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.function.Supplier;

public class JBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, JITL.MODID);

    public static final ArrayList<String> normalBlockName = new ArrayList<>();
    public static final ArrayList<String> normalLangName = new ArrayList<>();

    public static final ArrayList<String> logBlockName = new ArrayList<>();
    public static final ArrayList<String> logLangName = new ArrayList<>();

    public static final ArrayList<String> doorBlockName = new ArrayList<>();
    public static final ArrayList<String> doorLangName = new ArrayList<>();

    public static final ArrayList<String> trapDoorBlockName = new ArrayList<>();
    public static final ArrayList<String> trapDoorLangName = new ArrayList<>();

    public static final ArrayList<String> stairsBlockName = new ArrayList<>();
    public static final ArrayList<String> stairsLangName = new ArrayList<>();

    public static final ArrayList<String> slabBlockName = new ArrayList<>();
    public static final ArrayList<String> slabLangName = new ArrayList<>();

    public static final ArrayList<String> buttonBlockName = new ArrayList<>();
    public static final ArrayList<String> buttonLangName = new ArrayList<>();

    public static final ArrayList<String> pressurePlateBlockName = new ArrayList<>();
    public static final ArrayList<String> pressurePlateLangName = new ArrayList<>();

    public static final ArrayList<String> gateBlockName = new ArrayList<>();
    public static final ArrayList<String> gateLangName = new ArrayList<>();

    public static final ArrayList<String> fenceBlockName = new ArrayList<>();
    public static final ArrayList<String> fenceLangName = new ArrayList<>();

    public static final RegistryObject<Block> IRIDIUM_ORE = register("iridium_ore", "Iridium Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> IRIDIUM_BLOCK = registerFuelBlock("iridium_block", "Iridium Block", () -> new Block(JBlockProperties.STONE), 16000);
    public static final RegistryObject<Block> DEEPSLATE_IRIDIUM_ORE = register("deepslate_iridium_ore", "Deepslate Iridium Ore", JBlockProperties.STONE);

    public static final RegistryObject<Block> SAPPHIRE_ORE = register("sapphire_ore", "Sapphire Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> SAPPHIRE_BLOCK = register("sapphire_block", "Sapphire Block", JBlockProperties.STONE);
    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = register("deepslate_sapphire_ore", "Deepslate Sapphire Ore", JBlockProperties.STONE);

    public static final RegistryObject<Block> SHADIUM_ORE = register("shadium_ore", "Shadium Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> SHADIUM_BLOCK = register("shadium_block", "Shadium Block", JBlockProperties.STONE);
    public static final RegistryObject<Block> DEEPSLATE_SHADIUM_ORE = register("deepslate_shadium_ore", "Deepslate Shadium Ore", JBlockProperties.STONE);

    public static final RegistryObject<Block> LUNIUM_ORE = register("lunium_ore", "Lunium Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> LUNIUM_BLOCK = register("lunium_block", "Lunium Block", JBlockProperties.STONE);
    public static final RegistryObject<Block> DEEPSLATE_LUNIUM_ORE = register("deepslate_lunium_ore", "Deepslate Lunium Ore", JBlockProperties.STONE);

    public static final RegistryObject<Block> WARPED_QUARTZ_ORE = register("warped_quartz_ore", "Warped Quartz Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> WARPED_QUARTZ_BLOCK = register("warped_quartz_block", "Warped Quartz Block", JBlockProperties.STONE);

    public static final RegistryObject<Block> BLOODCRUST_ORE = register("bloodcrust_ore", "Bloodcrust Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> BLOODCRUST_BLOCK = register("bloodcrust_block", "Bloodcrust Block", JBlockProperties.STONE);

    public static final RegistryObject<Block> ENDERILLIUM_ORE = register("enderillium_ore", "Enderillium Ore", JBlockProperties.STONE);
    public static final RegistryObject<Block> ENDERILLIUM_BLOCK = register("enderillium_block", "Enderillium Block", JBlockProperties.STONE);

    public static final RegistryObject<RotatedPillarBlock> EUCA_GOLD_LOG = registerLog("euca_gold_log", "Euca Gold Log");
    public static final RegistryObject<Block> EUCA_GOLD_PLANKS = register("euca_gold_planks", "Euca Gold Planks", JBlockProperties.WOOD);
    public static final RegistryObject<DoorBlock> EUCA_GOLD_DOOR = registerDoor("euca_gold_door", "Euca Gold Door");
    public static final RegistryObject<TrapDoorBlock> EUCA_GOLD_TRAP_DOOR = registerTrapDoor("euca_gold_trap_door", "Euca Gold Trap Door");
    public static final RegistryObject<StairBlock> EUCA_GOLD_STAIRS = registerStairs("euca_gold_stairs", "Euca Gold Plank Stairs", EUCA_GOLD_PLANKS);
    public static final RegistryObject<SlabBlock> EUCA_GOLD_SLAB = registerSlab("euca_gold_slab", "Euca Gold Plank Slab");
    public static final RegistryObject<ButtonBlock> EUCA_GOLD_BUTTON = registerButton("euca_gold_button", "Euca Gold Button", false);
    public static final RegistryObject<PressurePlateBlock> EUCA_GOLD_PRESSURE_PLATE = registerPressurePlate("euca_gold_pressure_plate", "Euca Gold Pressure Plate", PressurePlateBlock.Sensitivity.EVERYTHING);
    public static final RegistryObject<FenceGateBlock> EUCA_GOLD_FENCE_GATE = registerFenceGate("euca_gold_fence_gate", "Euca Gold Fence Gate");
    public static final RegistryObject<JFenceBlock> EUCA_GOLD_FENCE = registerFence("euca_gold_fence", "Euca Gold Fence");

    public static final RegistryObject<RotatedPillarBlock> EUCA_BROWN_LOG = registerLog("euca_brown_log", "Euca Brown Log");
    public static final RegistryObject<Block> EUCA_BROWN_PLANKS = register("euca_brown_planks", "Euca Brown Planks", JBlockProperties.WOOD);
    public static final RegistryObject<DoorBlock> EUCA_BROWN_DOOR = registerDoor("euca_brown_door", "Euca Brown Door");
    public static final RegistryObject<TrapDoorBlock> EUCA_BROWN_TRAP_DOOR = registerTrapDoor("euca_brown_trap_door", "Euca Brown Trap Door");
    public static final RegistryObject<StairBlock> EUCA_BROWN_STAIRS = registerStairs("euca_brown_stairs", "Euca Brown Plank Stairs", EUCA_BROWN_PLANKS);
    public static final RegistryObject<SlabBlock> EUCA_BROWN_SLAB = registerSlab("euca_brown_slab", "Euca Brown Plank Slab");
    public static final RegistryObject<ButtonBlock> EUCA_BROWN_BUTTON = registerButton("euca_brown_button", "Euca Brown Button", true);
    public static final RegistryObject<PressurePlateBlock> EUCA_BROWN_PRESSURE_PLATE = registerPressurePlate("euca_brown_pressure_plate", "Euca Brown Pressure Plate", PressurePlateBlock.Sensitivity.EVERYTHING);
    public static final RegistryObject<FenceGateBlock> EUCA_BROWN_FENCE_GATE = registerFenceGate("euca_brown_fence_gate", "Euca Brown Fence Gate");
    public static final RegistryObject<JFenceBlock> EUCA_BROWN_FENCE = registerFence("euca_brown_fence", "Euca Brown Fence");


    public static RegistryObject<Block> register(String name, String translatedName, BlockBehaviour.Properties props, CreativeModeTab tab) {
        return register(name, translatedName, () -> new Block(props), tab);
    }

    public static RegistryObject<Block> register(String name, String translatedName, BlockBehaviour.Properties props) {
        normalBlockName.add(name);
        return register(name, translatedName, () -> new Block(props), JTabs.BLOCKS);
    }

    public static RegistryObject<Block> register(String name, String translatedName, Supplier<Block> block, CreativeModeTab tab) {
        normalLangName.add(translatedName);
        RegistryObject<Block> block1 = BLOCKS.register(name, block);
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(tab)));
        return block1;
    }

    public static RegistryObject<RotatedPillarBlock> registerLog(String name, String translatedName) {
        logBlockName.add(name);
        logLangName.add(translatedName);
        RegistryObject<RotatedPillarBlock> block1 = BLOCKS.register(name, () -> new RotatedPillarBlock(JBlockProperties.WOOD));
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<DoorBlock> registerDoor(String name, String translatedName) {
        doorBlockName.add(name);
        doorLangName.add(translatedName);
        RegistryObject<DoorBlock> block1 = BLOCKS.register(name, () -> new DoorBlock(JBlockProperties.DOOR));
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<TrapDoorBlock> registerTrapDoor(String name, String translatedName) {
        trapDoorBlockName.add(name);
        trapDoorLangName.add(translatedName);
        RegistryObject<TrapDoorBlock> block1 = BLOCKS.register(name, () -> new TrapDoorBlock(JBlockProperties.DOOR));
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<StairBlock> registerStairs(String name, String translatedName, RegistryObject<Block> plank) {
        stairsBlockName.add(name);
        stairsLangName.add(translatedName);
        RegistryObject<StairBlock> block1 = BLOCKS.register(name, () -> new StairBlock(() -> plank.get().defaultBlockState(), JBlockProperties.WOOD));
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<SlabBlock> registerSlab(String name, String translatedName) {
        slabBlockName.add(name);
        slabLangName.add(translatedName);
        RegistryObject<SlabBlock> block1 = BLOCKS.register(name, () -> new SlabBlock(JBlockProperties.WOOD));
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<ButtonBlock> registerButton(String name, String translatedName, boolean sensitive) {
        buttonBlockName.add(name);
        buttonLangName.add(translatedName);
        RegistryObject<ButtonBlock> block1 = BLOCKS.register(name, () -> new ButtonBlock(sensitive, JBlockProperties.BUTTON) {
            @Override
            protected @NotNull SoundEvent getSound(boolean pIsOn) {
                return SoundEvents.WOODEN_BUTTON_CLICK_ON;
            }
        });
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<PressurePlateBlock> registerPressurePlate(String name, String translatedName, PressurePlateBlock.Sensitivity s) {
        pressurePlateBlockName.add(name);
        pressurePlateLangName.add(translatedName);
        RegistryObject<PressurePlateBlock> block1 = BLOCKS.register(name, () -> new PressurePlateBlock(s, JBlockProperties.WOOD));
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<FenceGateBlock> registerFenceGate(String name, String translatedName) {
        gateBlockName.add(name);
        gateLangName.add(translatedName);
        RegistryObject<FenceGateBlock> block1 = BLOCKS.register(name, () -> new FenceGateBlock(JBlockProperties.WOOD));
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<JFenceBlock> registerFence(String name, String translatedName) {
        fenceBlockName.add(name);
        fenceLangName.add(translatedName);
        RegistryObject<JFenceBlock> block1 = BLOCKS.register(name, () -> new JFenceBlock(JBlockProperties.WOOD));
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static <T extends Block>RegistryObject<T> registerFuelBlock(String name, String translatedName, Supplier<T> block, int burnTime) {
        normalLangName.add(translatedName);
        normalBlockName.add(name);
        RegistryObject<T> block1 = BLOCKS.register(name, block);
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)) {
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return burnTime;
            }
        });
        return block1;
    }

}