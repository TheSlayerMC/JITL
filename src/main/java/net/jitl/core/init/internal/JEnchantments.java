package net.jitl.core.init.internal;

import net.jitl.core.init.JITL;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;

public class JEnchantments {

    public static final ResourceKey<Enchantment> FAITHFUL = registerKey("faithful");

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<DamageType> damageGetter = context.lookup(Registries.DAMAGE_TYPE);
        HolderGetter<Enchantment> enchantmentGetter = context.lookup(Registries.ENCHANTMENT);
        HolderGetter<Item> itemGetter = context.lookup(Registries.ITEM);
        HolderGetter<Block> blockGetter = context.lookup(Registries.BLOCK);

        register(
                context,
                FAITHFUL,
                Enchantment.enchantment(
                                Enchantment.definition(
                                        itemGetter.getOrThrow(JTags.PIERCER_ITEM),
                                        2,
                                        1,
                                        Enchantment.dynamicCost(12, 20),
                                        Enchantment.constantCost(50),
                                        4,
                                        EquipmentSlotGroup.MAINHAND
                                )
                        )
        );
    }

    private static void register(BootstrapContext<Enchantment> pContext, ResourceKey<Enchantment> pKey, Enchantment.Builder pBuilder) {
        pContext.register(pKey, pBuilder.build(pKey.location()));
    }

    public static ResourceKey<Enchantment> registerKey(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(JITL.MODID, name));
    }

//    public static final DeferredHolder<Enchantment, Enchantment> LIGHTWEIGHT =
//            REGISTRY.register("lightweight", () -> new Enchantment(Component.translatable("enchantment.jitl.lightweight"), Enchantment.definition(JTags.PIERCER_ITEM, 2, 1,
//                    Enchantment.dynamicCost(12, 20), Enchantment.constantCost(50), 4, EquipmentSlot.MAINHAND)));
//
//    public static final DeferredHolder<Enchantment, Enchantment> AMBIT =
//            REGISTRY.register("ambit", () -> new Enchantment(Enchantment.definition(JTags.PIERCER_ITEM, 2, 1,
//                    Enchantment.dynamicCost(12, 20), Enchantment.constantCost(50), 4, EquipmentSlot.MAINHAND)));
//
//    public static final DeferredHolder<Enchantment, Enchantment> SCORCHING =
//            REGISTRY.register("scorching", () -> new Enchantment(Enchantment.definition(JTags.PIERCER_ITEM, 2, 1,
//                    Enchantment.dynamicCost(12, 20), Enchantment.constantCost(50), 4, EquipmentSlot.MAINHAND)));
//


    //public static final RegistryObject<Enchantment> HOT_TOUCH =
     //       REGISTRY.register("hot_touch", () -> new HotTouchEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.DIGGER, EquipmentSlot.MAINHAND));
}
