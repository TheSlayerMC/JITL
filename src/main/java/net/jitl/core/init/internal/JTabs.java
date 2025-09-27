package net.jitl.core.init.internal;

import net.jitl.common.block.base.JCropBlock;
import net.jitl.core.init.JITL;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class JTabs {
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, JITL.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOCKS = REGISTRY.register("blocks", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.jitl.blocks")).icon(() -> new ItemStack(JBlocks.FIRESTONE_BLOCK.get())).build());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ITEMS = REGISTRY.register("items", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.jitl.items")).icon(() -> new ItemStack(JItems.BLAZING_FIREBALL.get())).build());

    public static void registerTabs(BuildCreativeModeTabContentsEvent event){
        if(event.getTab() == BLOCKS.get()) {
            for(DeferredHolder<Block, ? extends Block> block : JBlocks.BLOCKS.getEntries()){
                if(!(block.get() instanceof JCropBlock || block.get() instanceof WallTorchBlock || block.get() instanceof TorchBlock))
                    event.accept(block.get());
            }
        };

        if(event.getTab() == ITEMS.get()) {
            for(DeferredHolder<Item, ? extends Item> item : JItems.ITEMS.getEntries()){
                event.accept(item.get());
            }
        };
    }
}
