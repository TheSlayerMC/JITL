package net.jitl.core.init.internal;

import net.jitl.core.init.JBlockProperties;
import net.jitl.core.init.JITL;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.function.Supplier;

public class JBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, JITL.MODID);

    public static final ArrayList<String> blockName = new ArrayList<>();
    public static final ArrayList<String> langName = new ArrayList<>();

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

    public static RegistryObject<Block> register(String name, String translatedName, BlockBehaviour.Properties props, CreativeModeTab tab) {
        return register(name, translatedName, () -> new Block(props), tab);
    }

    public static RegistryObject<Block> register(String name, String translatedName, BlockBehaviour.Properties props) {
        blockName.add(name);
        return register(name, translatedName, () -> new Block(props), JTabs.BLOCKS);
    }

    public static <T extends Block>RegistryObject<T> register(String name, String translatedName, Supplier<T> block, CreativeModeTab tab) {
        langName.add(translatedName);
        RegistryObject<T> block1 = BLOCKS.register(name, block);
        JItems.register(name, () -> new BlockItem(block1.get(), new Item.Properties().tab(tab)));
        return block1;
    }

    public static <T extends Block>RegistryObject<T> registerFuelBlock(String name, String translatedName, Supplier<T> block, int burnTime) {
        langName.add(translatedName);
        blockName.add(name);
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