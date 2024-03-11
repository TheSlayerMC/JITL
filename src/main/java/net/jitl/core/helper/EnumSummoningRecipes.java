package net.jitl.core.helper;

import net.jitl.core.init.internal.JItems;
import net.minecraft.world.item.Item;

public enum EnumSummoningRecipes {
    OKOLOO(new Item[]{
            JItems.BLOODCRUST_INGOT.get(),
            JItems.HELL_SHARDS.get(),
            JItems.BLOODCRUST_INGOT.get(),

            JItems.SPAWNER_BAR.get(),

            JItems.BLOODCRUST_INGOT.get(),
            JItems.HELL_SHARDS.get(),
            JItems.BLOODCRUST_INGOT.get(),

            JItems.BROKEN_OKOLOO_CLUB.get()}),

    WITHERING_BEAST(new Item[]{
            JItems.WITHICSPINE.get(),
            JItems.LOST_SOUL.get(),
            JItems.WITHICSPINE.get(),

            JItems.WITHIC_SOUL.get(),

            JItems.WITHICSPINE.get(),
            JItems.LOST_SOUL.get(),
            JItems.WITHICSPINE.get(),

            JItems.WITHERING_BEAST_ORB.get()}),

    CALCIA(new Item[]{
            JItems.SPAWNER_BAR.get(),
            JItems.ASH.get(),
            JItems.SPAWNER_BAR.get(),

            JItems.HELL_SHARDS.get(),

            JItems.SPAWNER_BAR.get(),
            JItems.ASH.get(),
            JItems.SPAWNER_BAR.get(),

            JItems.CALCIA_ORB.get()}),

    SOUL_WATCHER(new Item[]{
            JItems.SNAKE_SKIN.get(),
            JItems.CONCENTRATED_BLOOD.get(),
            JItems.SNAKE_SKIN.get(),

            JItems.SIZZLING_EYE.get(),

            JItems.SNAKE_SKIN.get(),
            JItems.CONCENTRATED_BLOOD.get(),
            JItems.SNAKE_SKIN.get(),

            JItems.SOUL_WATCHER_ORB.get()}),

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
