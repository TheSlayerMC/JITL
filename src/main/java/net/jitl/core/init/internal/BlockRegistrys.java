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

public class BlockRegistrys {
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
    public static final ArrayList<String> crossBlockName = new ArrayList<>();
    public static final ArrayList<String> crossLangName = new ArrayList<>();

    public static RegistryObject<Block> register(String name, String translatedName, BlockBehaviour.Properties props, CreativeModeTab tab) {
        return register(name, translatedName, () -> new Block(props), tab);
    }

    public static RegistryObject<Block> register(String name, String translatedName, BlockBehaviour.Properties props) {
        normalBlockName.add(name);
        return register(name, translatedName, () -> new Block(props), JTabs.BLOCKS);
    }

    public static RegistryObject<Block> register(String name, String translatedName, Supplier<Block> block) {
        normalLangName.add(translatedName);
        RegistryObject<Block> block1 = BLOCKS.register(name, block);
        ItemRegistrys.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<Block> register(String name, String translatedName, Supplier<Block> block, CreativeModeTab tab) {
        normalLangName.add(translatedName);
        RegistryObject<Block> block1 = BLOCKS.register(name, block);
        ItemRegistrys.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(tab)));
        return block1;
    }

    public static RegistryObject<RotatedPillarBlock> registerLog(String name, String translatedName) {
        logBlockName.add(name);
        logLangName.add(translatedName);
        RegistryObject<RotatedPillarBlock> block1 = BLOCKS.register(name, () -> new RotatedPillarBlock(JBlockProperties.WOOD));
        ItemRegistrys.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<DoorBlock> registerDoor(String name, String translatedName) {
        doorBlockName.add(name);
        doorLangName.add(translatedName);
        RegistryObject<DoorBlock> block1 = BLOCKS.register(name, () -> new DoorBlock(JBlockProperties.DOOR));
        ItemRegistrys.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<TrapDoorBlock> registerTrapDoor(String name, String translatedName) {
        trapDoorBlockName.add(name);
        trapDoorLangName.add(translatedName);
        RegistryObject<TrapDoorBlock> block1 = BLOCKS.register(name, () -> new TrapDoorBlock(JBlockProperties.DOOR));
        ItemRegistrys.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<StairBlock> registerStairs(String name, String translatedName, RegistryObject<Block> plank) {
        stairsBlockName.add(name);
        stairsLangName.add(translatedName);
        RegistryObject<StairBlock> block1 = BLOCKS.register(name, () -> new StairBlock(() -> plank.get().defaultBlockState(), JBlockProperties.WOOD));
        ItemRegistrys.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<SlabBlock> registerSlab(String name, String translatedName) {
        slabBlockName.add(name);
        slabLangName.add(translatedName);
        RegistryObject<SlabBlock> block1 = BLOCKS.register(name, () -> new SlabBlock(JBlockProperties.WOOD));
        ItemRegistrys.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
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
        ItemRegistrys.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<PressurePlateBlock> registerPressurePlate(String name, String translatedName, PressurePlateBlock.Sensitivity s) {
        pressurePlateBlockName.add(name);
        pressurePlateLangName.add(translatedName);
        RegistryObject<PressurePlateBlock> block1 = BLOCKS.register(name, () -> new PressurePlateBlock(s, JBlockProperties.WOOD));
        ItemRegistrys.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<FenceGateBlock> registerFenceGate(String name, String translatedName) {
        gateBlockName.add(name);
        gateLangName.add(translatedName);
        RegistryObject<FenceGateBlock> block1 = BLOCKS.register(name, () -> new FenceGateBlock(JBlockProperties.WOOD));
        ItemRegistrys.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<JFenceBlock> registerFence(String name, String translatedName) {
        fenceBlockName.add(name);
        fenceLangName.add(translatedName);
        RegistryObject<JFenceBlock> block1 = BLOCKS.register(name, () -> new JFenceBlock(JBlockProperties.WOOD));
        ItemRegistrys.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static RegistryObject<Block> registerCrossBlock(String name, String translatedName, Supplier<Block> block) {
        crossBlockName.add(name);
        crossLangName.add(translatedName);
        RegistryObject<Block> block1 = BLOCKS.register(name, block);
        ItemRegistrys.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)));
        return block1;
    }

    public static <T extends Block>RegistryObject<T> registerFuelBlock(String name, String translatedName, Supplier<T> block, int burnTime) {
        normalLangName.add(translatedName);
        normalBlockName.add(name);
        RegistryObject<T> block1 = BLOCKS.register(name, block);
        ItemRegistrys.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(JTabs.BLOCKS)) {
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return burnTime;
            }
        });
        return block1;
    }
}
