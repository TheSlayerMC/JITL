package net.jitl.core.init.internal;

import net.jitl.core.init.JITL;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class JEnchantments {


    public static DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(Registries.ENCHANTMENT, JITL.MODID);

    public static final DeferredHolder<Enchantment, Enchantment> LIGHTWEIGHT =
            REGISTRY.register("lightweight", () -> new Enchantment(Enchantment.definition(JTags.PIERCER_ITEM, 2, 1,
                    Enchantment.dynamicCost(12, 20), Enchantment.constantCost(50), 4, EquipmentSlot.MAINHAND)));

    public static final DeferredHolder<Enchantment, Enchantment> AMBIT =
            REGISTRY.register("ambit", () -> new Enchantment(Enchantment.definition(JTags.PIERCER_ITEM, 2, 1,
                    Enchantment.dynamicCost(12, 20), Enchantment.constantCost(50), 4, EquipmentSlot.MAINHAND)));

    public static final DeferredHolder<Enchantment, Enchantment> SCORCHING =
            REGISTRY.register("scorching", () -> new Enchantment(Enchantment.definition(JTags.PIERCER_ITEM, 2, 1,
                    Enchantment.dynamicCost(12, 20), Enchantment.constantCost(50), 4, EquipmentSlot.MAINHAND)));

    public static final DeferredHolder<Enchantment, Enchantment> FAITHFUL =
            REGISTRY.register("faithful", () -> new Enchantment(Enchantment.definition(JTags.PIERCER_ITEM, 2, 1,
                    Enchantment.dynamicCost(12, 20), Enchantment.constantCost(50), 4, EquipmentSlot.MAINHAND)));

    //public static final RegistryObject<Enchantment> HOT_TOUCH =
     //       REGISTRY.register("hot_touch", () -> new HotTouchEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.DIGGER, EquipmentSlot.MAINHAND));
}
