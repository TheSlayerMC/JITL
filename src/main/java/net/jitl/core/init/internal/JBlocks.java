package net.jitl.core.init.internal;

import net.jitl.core.init.JBlockProperties;
import net.jitl.core.init.JITL;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.function.Supplier;

public class JBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, JITL.MODID);

    public static final ArrayList<String> blockName = new ArrayList<String>();
    public static final ArrayList<String> langName = new ArrayList<String>();

    public static final RegistryObject<Block> SAPPHIRE_ORE = register("sapphire_ore", "Sapphire Ore", JBlockProperties.STONE);

    public static RegistryObject<Block> register(String name, String translatedName, BlockBehaviour.Properties props, CreativeModeTab tab) {
        return register(name, translatedName, () -> new Block(props), tab);
    }

    public static RegistryObject<Block> register(String name, String translatedName, BlockBehaviour.Properties props) {
        return register(name, translatedName, () -> new Block(props), JTabs.BLOCKS);
    }

    public static <T extends Block>RegistryObject<T> register(String name, String translatedName, Supplier<T> block, CreativeModeTab tab) {
        blockName.add(name);
        langName.add(translatedName);
        RegistryObject<T> block1 = BLOCKS.register(name, block);
        JItems.register(name, translatedName, () -> new BlockItem(block1.get(), new Item.Properties().tab(tab)));
        return block1;
    }

}