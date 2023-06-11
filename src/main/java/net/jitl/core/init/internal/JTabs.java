package net.jitl.core.init.internal;

import net.jitl.core.init.JITL;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = JITL.MODID)
public class JTabs {
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, JITL.MODID);

    public static final RegistryObject<CreativeModeTab> BLOCKS = REGISTRY.register("blocks", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.jitl.blocks")).icon(() -> new ItemStack(JBlocks.FIRESTONE_BLOCK.get())).build());
    public static final RegistryObject<CreativeModeTab> ITEMS = REGISTRY.register("items", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.jitl.items")).icon(() -> new ItemStack(JItems.BLAZING_FIREBALL.get())).build());

    public static void registerTabs(BuildCreativeModeTabContentsEvent event){
        if (event.getTab() == BLOCKS.get()) {
            for(RegistryObject<Block> item : JBlocks.BLOCKS.getEntries()){
                event.accept(item.get());
            }
        };

        if (event.getTab() == ITEMS.get()) {
            for(RegistryObject<Item> item : JItems.ITEMS.getEntries()){
                event.accept(item.get());
            }
        };
    }
}
