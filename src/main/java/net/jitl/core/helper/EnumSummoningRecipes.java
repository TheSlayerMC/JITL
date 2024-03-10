package net.jitl.core.helper;

import net.jitl.core.init.internal.JItems;
import net.minecraft.world.item.Item;

public enum EnumSummoningRecipes {
    OKOLOO(new Item[]{JItems.BLOODCRUST_INGOT.get(),
            JItems.HELL_SHARDS.get(),
            JItems.BLOODCRUST_INGOT.get(),
            JItems.SPAWNER_BAR.get(),
            JItems.BLOODCRUST_INGOT.get(),
            JItems.HELL_SHARDS.get(),
            JItems.BLOODCRUST_INGOT.get(), JItems.BROKEN_OKOLOO_CLUB.get()}),

    w(new Item[]{JItems.KORITE_INGOT.get(),
            JItems.CELESTIUM_INGOT.get(),
            JItems.FLAIRIUM_INGOT.get(),
            JItems.MEKYUM_INGOT.get(),
            JItems.BLOODCRUST_INGOT.get(),
            JItems.ORBADITE_INGOT.get(),
            JItems.LUNIUM_POWDER.get(), JItems.CELESTIUM_INGOT.get()}),

    a(new Item[]{JItems.BLOODWIELD_SWORD.get(),
            JItems.SWORD_THUNDERBIRD.get(),
            JItems.BLOODWIELD_SWORD.get(),
            JItems.CHAMPIONS_SWORD.get(),
            JItems.FROSTBITTEN_SWORD.get(),
            JItems.DEVELOPER_SWORD.get(),
            JItems.GOLEM_SWORD.get(), JItems.POISON_SWORD.get()}),

    s(new Item[]{JItems.BLOODCRUST_SHOVEL.get(),
            JItems.CELESTIUM_SHOVEL.get(),
            JItems.BLOODCRUST_SHOVEL.get(),
            JItems.CELESTIUM_SHOVEL.get(),
            JItems.GORBITE_SHOVEL.get(),
            JItems.ORBADITE_SHOVEL.get(),
            JItems.LUNIUM_SHOVEL.get(), JItems.SNOW_SHOVELER.get()}),

    d(new Item[]{JItems.BLOODCRUST_AXE.get(),
            JItems.CELESTIUM_AXE.get(),
            JItems.CELESTIUM_AXE.get(),
            JItems.GORBITE_AXE.get(),
            JItems.MEKYUM_AXE.get(),
            JItems.ORBADITE_AXE.get(),
            JItems.BLOODCRUST_AXE.get(), JItems.SAPPHIRE_AXE.get()}),

    f(new Item[]{JItems.BLOODCRUST_PICKAXE.get(),
            JItems.DES_PICKAXE.get(),
            JItems.FLAIRIUM_PICKAXE.get(),
            JItems.KORITE_PICKAXE.get(),
            JItems.DES_PICKAXE.get(),
            JItems.GORBITE_PICKAXE.get(),
            JItems.SAPPHIRE_PICKAXE.get(), JItems.FLAIRIUM_PICKAXE.get()}),
    ;

    public Item[] items;

    EnumSummoningRecipes(Item[] items) {
        this.items = items;
    }

    public Item getItems(int index) {
        return items[index];
    }

    public String getName() {
        return getItems(7).asItem().toString();
    }
}
