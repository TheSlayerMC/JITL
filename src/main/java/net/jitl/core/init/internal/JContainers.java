package net.jitl.core.init.internal;

import net.jitl.client.gui.screen.SummoningTableScreen;
import net.jitl.common.block.entity.container.JFurnaceMenu;
import net.jitl.client.gui.screen.JFurnaceScreen;
import net.jitl.common.block.entity.container.SummoningTableContainer;
import net.jitl.core.helper.internal.EmptyContainer;
import net.jitl.core.init.JITL;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JContainers {
    public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, JITL.MODID);

    public static RegistryObject<MenuType<EmptyContainer>> EMPTY_CONTAINER = REGISTRY.register("empty", () -> IForgeMenuType.create(EmptyContainer::createContainerClientSide));

    public static final RegistryObject<MenuType<JFurnaceMenu>> JFURNACE = REGISTRY.register("jfurnace", () -> IForgeMenuType.create((id, inv, data) -> new JFurnaceMenu(id, inv)));

    public static final RegistryObject<MenuType<SummoningTableContainer>> SUMMONING_TABLE = REGISTRY.register("summoning_table", () -> IForgeMenuType.create(SummoningTableContainer::new));

    public static void register() {
        MenuScreens.register(JFURNACE.get(), JFurnaceScreen::new);
        MenuScreens.register(SUMMONING_TABLE.get(), SummoningTableScreen::new);
    }
}