package net.jitl.core.init.internal;

import net.jitl.common.enchantment.AmbitEnchantment;
import net.jitl.common.enchantment.FaithfulEnchantment;
import net.jitl.common.enchantment.LightweightEnchantment;
import net.jitl.common.enchantment.ScorchingEnchantment;
import net.jitl.common.items.PiercerItem;
import net.jitl.core.init.JITL;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class JEnchantments {

    public static EnchantmentCategory PIERCER = EnchantmentCategory.create("piercer", (item) ->
       item instanceof PiercerItem
    );

    public static DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(Registries.ENCHANTMENT, JITL.MODID);

    public static final DeferredHolder<Enchantment, LightweightEnchantment> LIGHTWEIGHT =
            REGISTRY.register("lightweight", () -> new LightweightEnchantment(Enchantment.Rarity.COMMON, PIERCER, EquipmentSlot.MAINHAND));

    public static final DeferredHolder<Enchantment, AmbitEnchantment> AMBIT =
            REGISTRY.register("ambit", () -> new AmbitEnchantment(Enchantment.Rarity.COMMON, PIERCER, EquipmentSlot.MAINHAND));

    public static final DeferredHolder<Enchantment, ScorchingEnchantment> SCORCHING =
            REGISTRY.register("scorching", () -> new ScorchingEnchantment(Enchantment.Rarity.COMMON, PIERCER, EquipmentSlot.MAINHAND));

    public static final DeferredHolder<Enchantment, FaithfulEnchantment> FAITHFUL =
            REGISTRY.register("faithful", () -> new FaithfulEnchantment(Enchantment.Rarity.COMMON, PIERCER, EquipmentSlot.MAINHAND));

    //public static final RegistryObject<Enchantment> HOT_TOUCH =
     //       REGISTRY.register("hot_touch", () -> new HotTouchEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.DIGGER, EquipmentSlot.MAINHAND));
}
