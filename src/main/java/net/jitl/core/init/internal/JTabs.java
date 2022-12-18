package net.jitl.core.init.internal;

import net.jitl.core.init.JITL;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JTabs {

    public static final ResourceLocation BLOCKS = new ResourceLocation(JITL.MODID, ".blocks");
    public static final ResourceLocation ITEMS = new ResourceLocation(JITL.MODID, ".items");

    private static ItemStack makeBlockIcon() {
        return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(JITL.MODID, "sapphire_ore")));
    }

    private static ItemStack makeItemIcon() {
        return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(JITL.MODID, "stone_clump")));
    }

    public static void registerTabs(CreativeModeTabEvent.Register event){
        event.registerCreativeModeTab(BLOCKS, builder -> builder.title(Component.translatable("itemGroup.jitl.blocks")).icon(JTabs::makeBlockIcon).withSearchBar().displayItems((flags, output, isOp) -> {
            for(RegistryObject<Block> item : JBlocks.BLOCKS.getEntries()){
                output.accept(item.get());
            }
        }));

        event.registerCreativeModeTab(ITEMS, builder -> builder.title(Component.translatable("itemGroup.jitl.items")).icon(JTabs::makeItemIcon).withSearchBar().displayItems((flags, output, isOp) -> {
            for(RegistryObject<Item> item : JItems.ITEMS.getEntries()){
                output.accept(item.get());
            }
        }));
    }
}
