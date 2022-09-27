package net.jitl.core.init.internal;

import net.jitl.common.enchantment.*;
import net.jitl.common.items.PiercerItem;
import net.jitl.core.init.JITL;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JEnchantments {

    public static EnchantmentCategory PIERCER = EnchantmentCategory.create("piercer", (item) ->
       item instanceof PiercerItem
    );

    public static DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, JITL.MODID);

    public static final RegistryObject<Enchantment> LIGHTWEIGHT =
            REGISTRY.register("lightweight", () -> new LightweightEnchantment(Enchantment.Rarity.COMMON, PIERCER, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> AMBIT =
            REGISTRY.register("ambit", () -> new AmbitEnchantment(Enchantment.Rarity.COMMON, PIERCER, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> SCORCHING =
            REGISTRY.register("scorching", () -> new ScorchingEnchantment(Enchantment.Rarity.COMMON, PIERCER, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> FAITHFUL =
            REGISTRY.register("faithful", () -> new FaithfulEnchantment(Enchantment.Rarity.COMMON, PIERCER, EquipmentSlot.MAINHAND));

    //public static final RegistryObject<Enchantment> HOT_TOUCH =
     //       REGISTRY.register("hot_touch", () -> new HotTouchEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.DIGGER, EquipmentSlot.MAINHAND));
}
