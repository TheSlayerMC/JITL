package net.jitl.core.init.internal;

import net.jitl.core.init.JITL;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class JTabs {

    public static final CreativeModeTab BLOCKS = new CreativeModeTab(JITL.MODID + ".blocks") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(JBlocks.SAPPHIRE_ORE.get());
        }
    };

    public static final CreativeModeTab MATERIALS = new CreativeModeTab(JITL.MODID + ".materials") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(JItems.LUNIUM_POWDER.get());
        }
    };

    public static final CreativeModeTab ARMOR = new CreativeModeTab(JITL.MODID + ".armor") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(JItems.BLOODCRUST_HELMET.get());
        }
    };

    public static final CreativeModeTab TOOLS = new CreativeModeTab(JITL.MODID + ".tools") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(JItems.LUNIUM_SHOVEL.get());
        }
    };

    public static final CreativeModeTab WEAPONS = new CreativeModeTab(JITL.MODID + ".weapons") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(JItems.SHADIUM_INGOT.get());
        }
    };

    public static final CreativeModeTab RANGED_WEAPONS = new CreativeModeTab(JITL.MODID + ".ranged") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(JItems.STAFF_OF_CONJURING.get());
        }
    };

    public static final CreativeModeTab MISC = new CreativeModeTab(JITL.MODID + ".misc") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(JItems.STRONG_BOTTLE_OF_ESSENCIA.get());
        }
    };
}
