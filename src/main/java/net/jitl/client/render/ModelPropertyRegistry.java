package net.jitl.client.render;

import net.jitl.common.items.base.JBowItem;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelPropertyRegistry {

    public static void init() {
//        registerBow(JItems.BLAZING_BOW.get());
//        registerBow(JItems.FLAME_BOW.get());
//        registerBow(JItems.POISON_BOW.get());
//        registerBow(JItems.FROZEN_BOW.get());
//        registerBow(JItems.DARKNESS_BOW.get());
//        registerBow(JItems.FROSTBITTEN_BOW.get());
//        registerBow(JItems.FROSTY_BOW.get());
//        registerBow(JItems.STARING_BOW.get());
//        registerBow(JItems.CHARRED_BOW.get());
//        registerBow(JItems.FLAMING_BOW.get());
//        registerBow(JItems.MANTLE_BOW.get());
//        registerBow(JItems.CORE_EXPANDER.get());
//        registerBow(JItems.ROYAL_BOW.get());
//        registerBow(JItems.DARK_ENFORCER.get());
//        registerBow(JItems.DEPTHS_BOW.get());
//        registerBow(JItems.ROCS_WING.get());
//        registerBow(JItems.SCALE_BOW.get());
//        registerBow(JItems.LOGGERS_BOW.get());
//        registerBow(JItems.OVERGROWN_BOW.get());
//        registerBow(JItems.OVERSEER_BOW.get());
//        registerBow(JItems.WOODLAND_BOW.get());
//        registerBow(JItems.DARK_TERRA_BOW.get());
//        registerBow(JItems.LAVENDER_BOW.get());
//        registerBow(JItems.TERRALIGHT_BOW.get());
//        registerBow(JItems.TERRIAN_BOW.get());
//        registerBow(JItems.STARLIGHT_BOW.get());
//        registerBow(JItems.FLUFFY_BOW.get());
//        registerBow(JItems.GOLEM_BOW.get());
//        registerBow(JItems.DEATH_PIERCER_BOW.get());
//        registerBow(JItems.FUSION_BOW.get());
//        registerBow(JItems.SPRING_BOW.get());
//        registerBow(JItems.WASTEFUL_BOW.get());
//
//        registerShield(JItems.SAPPHIRE_SHIELD.get());
//        registerShield(JItems.LUNIUM_SHIELD.get());
//        registerShield(JItems.SHADIUM_SHIELD.get());
//        registerShield(JItems.BLOODCRUST_SHIELD.get());
//        registerShield(JItems.CELESTIUM_SHIELD.get());
//        registerShield(JItems.MEKYUM_SHIELD.get());
//        registerShield(JItems.STORON_SHIELD.get());
//        registerShield(JItems.KORITE_SHIELD.get());
//        registerShield(JItems.FLAIRIUM_SHIELD.get());
//        registerShield(JItems.DES_SHIELD.get());
//        registerShield(JItems.GORBITE_SHIELD.get());
//        registerShield(JItems.ORBADITE_SHIELD.get());
//        registerShield(JItems.SOULSTONE_SHIELD.get());
    }

// TODO   private static void registerShield(Item shield) {
//            ItemProperties.register(shield.asItem(), JITL.rl("blocking"),
//                    (stack, world, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
//    }
//
//    public static void registerBow(Item item) {
//        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("pull"), (stack, level, entity, i) -> {
//            if (entity == null) {
//                return 0.0F;
//            } else {
//                return entity.getUseItem() != stack ? 0.0F : ((stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 20.0F)
//                        * ((float)JBowItem.DEFAULT_DURATION / stack.getUseDuration(entity));
//            }
//        });
//        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("pulling"), (stack, level, entity, i) ->
//                entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
//    }
}
