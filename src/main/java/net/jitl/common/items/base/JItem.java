package net.jitl.common.items.base;

import net.jitl.core.init.internal.JItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.List;
import java.util.function.Consumer;

public class JItem extends Item {

    public JItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext pContext, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag pTooltipFlag) {
        addItemDesc(JItems.FLAME_COIN.get(), tooltip, "jitl.item.desc.flame_coin");

        addItemDesc(JItems.PADLOCK.get(), tooltip, "jitl.item.desc.padlock");
        addItemDesc(JItems.CHEST_KEY.get(), tooltip, "jitl.item.desc.master_key");
        addItemDesc(JItems.JOURNEY_KEY.get(), tooltip, "jitl.item.desc.journey_key");
        addItemDesc(JItems.NETHER_KEY.get(), tooltip, "jitl.item.desc.nether_key");
        addItemDesc(JItems.FROZEN_KEY.get(), tooltip, "jitl.item.desc.frozen_key");
        addItemDesc(JItems.EUCA_KEY.get(), tooltip, "jitl.item.desc.euca_key");
        addItemDesc(JItems.BOILING_KEY.get(), tooltip, "jitl.item.desc.boiling_key");
        addItemDesc(JItems.DEPTHS_CHEST_KEY.get(), tooltip, "jitl.item.desc.depths_key");
        addItemDesc(JItems.CORBA_KEY.get(), tooltip, "jitl.item.desc.corba_key");
        addItemDesc(JItems.TERRANIAN_KEY.get(), tooltip, "jitl.item.desc.terranian_key");
        addItemDesc(JItems.CLOUDIA_KEY.get(), tooltip, "jitl.item.desc.cloudia_key");
        addItemDesc(JItems.SENTERIAN_KEY.get(), tooltip, "jitl.item.desc.senterian_key");

        addItemDesc(JItems.IRIDIUM_NUGGET.get(), tooltip, "jitl.item.desc.iridium");
        addItemDesc(JItems.DEMONIC_EYE.get(), tooltip, "jitl.item.desc.demonic_eye");
        addItemDesc(JItems.DARK_GEM.get(), tooltip, "jitl.item.desc.dark_gem");
        addItemDesc(JItems.SENTRY_EYE.get(), tooltip, "jitl.item.desc.sentry_eye");
        addItemDesc(JItems.SENTRY_OBSERVER.get(), tooltip, "jitl.item.desc.sentry_observer");
        addItemDesc(JItems.BILE_VIAL.get(), tooltip, "jitl.item.desc.bile");

        addItemDesc(JItems.ANCIENT_CHUNK.get(), tooltip, "jitl.item.desc.ancient_socket");
        addItemDesc(JItems.ANCIENT_FRAGMENT.get(), tooltip, "jitl.item.desc.ancient_socket");
        addItemDesc(JItems.ANCIENT_PIECE.get(), tooltip, "jitl.item.desc.ancient_socket");
        addItemDesc(JItems.ANCIENT_SHARD.get(), tooltip, "jitl.item.desc.ancient_socket");
        addItemDesc(JItems.ANCIENT_EYE_OF_OPENING.get(), tooltip, "jitl.item.desc.ancient_catalyst");

        addItemDesc(JItems.HONGLOWSHROOM.get(), tooltip, "jitl.item.desc.night");
        addItemDesc(JItems.TERRASHROOM.get(), tooltip, "jitl.item.desc.night");
        addItemDesc(JItems.CORVEGGIES.get(), tooltip, "jitl.item.desc.sat");
        addItemDesc(JItems.GLOWA.get(), tooltip, "jitl.item.desc.night");
        addItemDesc(JItems.CRAKEBULB.get(), tooltip, "jitl.item.desc.water");
        addItemDesc(JItems.CRACKENCANE.get(), tooltip, "jitl.item.desc.dam");
        addItemDesc(JItems.SPINEBERRIES.get(), tooltip, "jitl.item.desc.abs");
        addItemDesc(JItems.ZATPEDAL.get(), tooltip, "jitl.item.desc.fire");
        addItemDesc(JItems.MINT_CANDY_CANE.get(), tooltip, "jitl.item.desc.mine");
        addItemDesc(JItems.FRUITY_CANDY_CANE.get(), tooltip, "jitl.item.desc.dam");
        addItemDesc(JItems.CHERRY_CANDY_CANE.get(), tooltip, "jitl.item.desc.regen");

        addItemDesc(JItems.MOON_OF_ETERNAL_NIGHT.get(), tooltip, "jitl.item.desc.eternal_night");
        addItemDesc(JItems.MINERS_PEARL.get(), tooltip, "jitl.item.desc.miners_pearl");
        addItemDesc(JItems.LUCKY_CHARM.get(), tooltip, "jitl.item.desc.lucky_charm");
        addItemDesc(JItems.DEATH_CAP.get(), tooltip, "jitl.item.desc.death_cap");
        addItemDesc(JItems.SKULL_OF_DECAY.get(), tooltip, "jitl.item.desc.skull_decay");
        addItemDesc(JItems.CLOUDWALKER_AMULET.get(), tooltip, "jitl.item.desc.cloud_walker");
        addItemDesc(JItems.ICE_AMULET.get(), tooltip, "jitl.item.desc.ice_amulet");

    }

    public void addItemDesc(Item item, Consumer<Component> tooltip, String descLoc) {
        if(this == item) {
            tooltip.accept(Component.translatable(descLoc));
        }
    }
}
