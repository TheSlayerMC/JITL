package net.jitl.core.init.internal;

import net.jitl.core.helper.internal.EmptyContainer;
import net.jitl.core.init.JITL;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JContainers {
    public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, JITL.MODID);

    public static RegistryObject<MenuType<EmptyContainer>> EMPTY_CONTAINER = REGISTRY.register("", () -> IForgeMenuType.create(EmptyContainer::createContainerClientSide));

}