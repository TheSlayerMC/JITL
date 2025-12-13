package net.jitl.core.init.internal;

import net.jitl.core.init.JITL;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;

public class JEnchantments {

    public static final ResourceKey<Enchantment> FAITHFUL = registerKey("faithful");
    public static final ResourceKey<Enchantment> LIGHTWEIGHT = registerKey("lightweight");
    public static final ResourceKey<Enchantment> AMBIT = registerKey("ambit");
    public static final ResourceKey<Enchantment> SCORCHING = registerKey("scorching");
    public static final ResourceKey<Enchantment> RAZOR = registerKey("razor");

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<DamageType> damageGetter = context.lookup(Registries.DAMAGE_TYPE);
        HolderGetter<Enchantment> enchantmentGetter = context.lookup(Registries.ENCHANTMENT);
        HolderGetter<Item> itemGetter = context.lookup(Registries.ITEM);
        HolderGetter<Block> blockGetter = context.lookup(Registries.BLOCK);

        register(context, FAITHFUL, Enchantment.enchantment(Enchantment.definition(itemGetter.getOrThrow(JTags.PIERCER_ITEM),
                                        2,
                                        1,
                                        Enchantment.dynamicCost(12, 20),
                                        Enchantment.constantCost(50),
                                        4,
                                        EquipmentSlotGroup.MAINHAND)));

        register(context, LIGHTWEIGHT, Enchantment.enchantment(Enchantment.definition(itemGetter.getOrThrow(JTags.PIERCER_ITEM),
                2,
                1,
                Enchantment.dynamicCost(12, 20),
                Enchantment.constantCost(50),
                4,
                EquipmentSlotGroup.MAINHAND)));

        register(context, AMBIT, Enchantment.enchantment(Enchantment.definition(itemGetter.getOrThrow(JTags.PIERCER_ITEM),
                2,
                1,
                Enchantment.dynamicCost(12, 20),
                Enchantment.constantCost(50),
                4,
                EquipmentSlotGroup.MAINHAND)));

        register(context, SCORCHING, Enchantment.enchantment(Enchantment.definition(itemGetter.getOrThrow(JTags.PIERCER_ITEM),
                2,
                1,
                Enchantment.dynamicCost(12, 20),
                Enchantment.constantCost(50),
                4,
                EquipmentSlotGroup.MAINHAND)));

        register(context, RAZOR, Enchantment.enchantment(Enchantment.definition(itemGetter.getOrThrow(JTags.PIERCER_ITEM),
                2,
                1,
                Enchantment.dynamicCost(12, 20),
                Enchantment.constantCost(50),
                4,
                EquipmentSlotGroup.MAINHAND)));
    }

    private static void register(BootstrapContext<Enchantment> pContext, ResourceKey<Enchantment> pKey, Enchantment.Builder pBuilder) {
        pContext.register(pKey, pBuilder.build(pKey.identifier()));
    }

    public static ResourceKey<Enchantment> registerKey(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, Identifier.fromNamespaceAndPath(JITL.MOD_ID, name));
    }

    //public static final RegistryObject<Enchantment> HOT_TOUCH =
     //       REGISTRY.register("hot_touch", () -> new HotTouchEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.DIGGER, EquipmentSlot.MAINHAND));
}
