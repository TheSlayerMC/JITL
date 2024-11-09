package net.jitl.core.helper;

import net.jitl.core.init.internal.JItems;
import net.minecraft.world.item.Item;

import java.util.Arrays;

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

    CORALLATOR(new Item[]{
            JItems.GATE_KEYS.get(),
            JItems.GOLD_CLUMP.get(),
            JItems.GATE_KEYS.get(),

            JItems.METAL_DISK.get(),

            JItems.GATE_KEYS.get(),
            JItems.GOLD_CLUMP.get(),
            JItems.GATE_KEYS.get(),

            JItems.CORALLATOR_ORB.get()}),

    BLAZIER(new Item[]{
            JItems.BOIL_POWDER.get(),
            JItems.ASH.get(),
            JItems.BOIL_POWDER.get(),

            JItems.BLAZING_FIREBALL.get(),

            JItems.BOIL_POWDER.get(),
            JItems.ASH.get(),
            JItems.BOIL_POWDER.get(),

            JItems.BLAZIER_ORB.get()}),

    THUNDERBIRD(new Item[]{
            JItems.ROC_FEATHER.get(),
            JItems.DARK_CRYSTAL.get(),
            JItems.ROC_FEATHER.get(),

            JItems.DARK_ORB.get(),

            JItems.ROC_FEATHER.get(),
            JItems.DARK_CRYSTAL.get(),
            JItems.ROC_FEATHER.get(),

            JItems.THUNDER_BIRD_ORB.get()}),

    SCALE(new Item[]{
            JItems.SCALE.get(),
            JItems.BEASTLY_STOMACH.get(),
            JItems.SCALE.get(),

            JItems.DARK_ORB.get(),

            JItems.SCALE.get(),
            JItems.BEASTLY_STOMACH.get(),
            JItems.SCALE.get(),

            JItems.SCALE_ORB.get()}),

    LOGGER(new Item[]{
            JItems.NATURE_TABLET.get(),
            JItems.ENCHANTED_LEAF.get(),
            JItems.NATURE_TABLET.get(),

            JItems.OVERGROWN_NATURE_BALL.get(),

            JItems.NATURE_TABLET.get(),
            JItems.ENCHANTED_LEAF.get(),
            JItems.NATURE_TABLET.get(),

            JItems.LOGGER_ORB.get()}),

    SENTRY_KING(new Item[]{
            JItems.OVER_SEEING_EYE.get(),
            JItems.COLLECTOR_ROCK.get(),
            JItems.OVER_SEEING_EYE.get(),

            JItems.OVER_SEEING_TABLET.get(),

            JItems.OVER_SEEING_EYE.get(),
            JItems.COLLECTOR_ROCK.get(),
            JItems.OVER_SEEING_EYE.get(),

            JItems.SENTRY_KING_ORB.get()}),

    TERRASTAR(new Item[]{
            JItems.EARTHEN_CRYSTAL.get(),
            JItems.PURPLE_POWDER.get(),
            JItems.EARTHEN_CRYSTAL.get(),

            JItems.TERRASTAR.get(),

            JItems.EARTHEN_CRYSTAL.get(),
            JItems.PURPLE_POWDER.get(),
            JItems.EARTHEN_CRYSTAL.get(),

            JItems.ENCHANTED_TERRASTAR.get()}),
    ;

    public final Item[] items;

    EnumSummoningRecipes(Item[] items) {
        this.items = items;
    }

    public Item getItem(int index) {
        return items[index];
    }

    public Item[] getInputs() {
        return Arrays.copyOfRange(items, 0, items.length - 1);
    }

    public Item getOutput() {
        return items[items.length - 1];
    }

    public String getName() {
        return getOutput().asItem().toString();
    }
}