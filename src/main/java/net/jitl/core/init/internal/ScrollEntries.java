package net.jitl.core.init.internal;

import net.jitl.common.scroll.ScrollAPI;
import net.jitl.common.scroll.ScrollCategory;
import net.jitl.common.scroll.ScrollEntry;
import net.jitl.common.scroll.ScrollEntryBuilder;
import net.jitl.core.init.JITL;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public class ScrollEntries {
    private static final Identifier BG = JITL.rl("textures/gui/scroll_background.png");

    public static final ScrollEntry MY_LAST_WORDS = new ScrollEntryBuilder("my_last_words", "My Last Words", "They're Coming...", new ItemStack(JBlocks.GOLDITE_STONE), 10, 10)
            .addTextComponent("scroll.jitl.chap1.my_last_words")
            .build();

    public static final ScrollEntry NETHERIC_STATUS = new ScrollEntryBuilder("netheric_status", "Netheric Status", "1", new ItemStack(JBlocks.GOLDITE_STONE), 10, 10)
            .addTextComponent("scroll.jitl.chap1.netheric_status")
            .build();

    public static final ScrollEntry THE_END = new ScrollEntryBuilder("the_end", "The End", "1", new ItemStack(JBlocks.GOLDITE_STONE), 10, 10)
            .addTextComponent("scroll.jitl.chap1.the_end")
            .build();

    public static final ScrollEntry BEYOND_BOILING = new ScrollEntryBuilder("beyond_boiling", "Beyond Boiling", "1", new ItemStack(JBlocks.GOLDITE_STONE), 10, 10)
            .addTextComponent("scroll.jitl.chap1.beyond_boiling")
            .build();

    public static final ScrollEntry THE_ROYALS = new ScrollEntryBuilder("the_royals", "The Royals", "1", new ItemStack(JBlocks.GOLDITE_STONE), 10, 10)
            .addTextComponent("scroll.jitl.chap1.the_royals")
            .build();

    public static final ScrollEntry DARKNESS = new ScrollEntryBuilder("darkness", "Darkness", "1", new ItemStack(JBlocks.GOLDITE_STONE), 10, 10)
            .addTextComponent("scroll.jitl.chap1.darkness")
            .build();

    public static final ScrollEntry FROZEN_DESPAIR = new ScrollEntryBuilder("frozen_despair", "Frozen Despair", "1", new ItemStack(JBlocks.GOLDITE_STONE), 10, 10)
            .addTextComponent("scroll.jitl.chap1.frozen_despair")
            .build();

    public static final ScrollEntry SENTERIAN_GOSPEL = new ScrollEntryBuilder("senterian_gospel", "Senterian Gospel", "Book of Divinity", new ItemStack(JItems.GOLD_LOOT_POUCH.get()), 10, 10)
            .addTextComponent("scroll.jitl.sentry_gospel")
            .build();

    public static final ScrollEntry FUNGI = new ScrollEntryBuilder("fungi", "Fungi Everywhere", "1", new ItemStack(JBlocks.GOLDITE_STONE), 10, 10)
            .addTextComponent("scroll.jitl.chap1.fungi")
            .build();

    public static final ScrollEntry MIST = new ScrollEntryBuilder("mist", "Mist In The Distance", "1", new ItemStack(JBlocks.GOLDITE_STONE), 10, 10)
            .addTextComponent("scroll.jitl.chap1.mist")
            .build();

    public static final ScrollEntry THIS_IS_IT = new ScrollEntryBuilder("this_is_it", "This Is It", "1", new ItemStack(JBlocks.GOLDITE_STONE), 10, 10)
            .addTextComponent("scroll.jitl.chap1.this_is_it")
            .build();

    public static final ScrollEntry TEST = new ScrollEntryBuilder("test", "test", "Testing. test. this is a test.", new ItemStack(JBlocks.GOLDITE_STONE), 10, 10)
            .addTextComponent("heyyyy. this is a really long text message. this is to test the scroll bar and image components. " +
                    "anyway, how are you? what are you up to? whats up? im prpetty good. things are going good. this needs to be way longer " +
                    "AHHHHHHHHHHHH AHA hahahahahah HEHEYEYEYEYYEH HAHAHAHAH heheyeyeh HAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAH HEYEYEYEYEYYEEY HEYEYY" +
                    "AHHHHHHHHHHHH AHA hahahahahah HEHEYEYEYEYYEH HAHAHAHAH heheyeyeh HAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAH HEYEYEYEYEYYEEY HEYEYY" +
                    "AHHHHHHHHHHHH AHA hahahahahah HEHEYEYEYEYYEH HAHAHAHAH heheyeyeh HAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAH HEYEYEYEYEYYEEY HEYEYY" +
                    "AHHHHHHHHHHHH AHA hahahahahah HEHEYEYEYEYYEH HAHAHAHAH heheyeyeh HAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAH HEYEYEYEYEYYEEY HEYEYY" +
                    "AHHHHHHHHHHHH AHA hahahahahah HEHEYEYEYEYYEH HAHAHAHAH heheyeyeh HAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAH HEYEYEYEYEYYEEY HEYEYY" +
                    "AHHHHHHHHHHHH AHA hahahahahah HEHEYEYEYEYYEH HAHAHAHAH heheyeyeh HAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAH HEYEYEYEYEYYEEY HEYEYY" +
                    "AHHHHHHHHHHHH AHA hahahahahah HEHEYEYEYEYYEH HAHAHAHAH heheyeyeh HAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAH HEYEYEYEYEYYEEY HEYEYY" +
                    "AHHHHHHHHHHHH AHA hahahahahah HEHEYEYEYEYYEH HAHAHAHAH heheyeyeh HAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAH HEYEYEYEYEYYEEY HEYEYY" +
                    "AHHHHHHHHHHHH AHA hahahahahah HEHEYEYEYEYYEH HAHAHAHAH heheyeyeh HAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAH HEYEYEYEYEYYEEY HEYEYY" +
                    "AHHHHHHHHHHHH AHA hahahahahah HEHEYEYEYEYYEH HAHAHAHAH heheyeyeh HAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAH HEYEYEYEYEYYEEY HEYEYY" +
                    "HWHAHHTHATHTH WHWEAT WHAT WHAT WHAT WHAT")
            .build();

    public static void register() {
        ScrollAPI.registerCategory(new ScrollCategory("MY_LAST_WORDS", BG));
        ScrollAPI.registerEntry("MY_LAST_WORDS", MY_LAST_WORDS);

        ScrollAPI.registerCategory(new ScrollCategory("NETHERIC_STATUS", BG));
        ScrollAPI.registerEntry("NETHERIC_STATUS", NETHERIC_STATUS);

        ScrollAPI.registerCategory(new ScrollCategory("THE_END", BG));
        ScrollAPI.registerEntry("THE_END", THE_END);

        ScrollAPI.registerCategory(new ScrollCategory("BEYOND_BOILING", BG));
        ScrollAPI.registerEntry("BEYOND_BOILING", BEYOND_BOILING);

        ScrollAPI.registerCategory(new ScrollCategory("FROZEN_DESPAIR", BG));
        ScrollAPI.registerEntry("FROZEN_DESPAIR", FROZEN_DESPAIR);

        ScrollAPI.registerCategory(new ScrollCategory("THE_ROYALS", BG));
        ScrollAPI.registerEntry("THE_ROYALS", THE_ROYALS);

        ScrollAPI.registerCategory(new ScrollCategory("DARKNESS", BG));
        ScrollAPI.registerEntry("DARKNESS", DARKNESS);

        ScrollAPI.registerCategory(new ScrollCategory("SENTRY_GOSPEL", BG));
        ScrollAPI.registerEntry("SENTRY_GOSPEL", SENTERIAN_GOSPEL);

        ScrollAPI.registerCategory(new ScrollCategory("FUNGI", BG));
        ScrollAPI.registerEntry("FUNGI", FUNGI);

        ScrollAPI.registerCategory(new ScrollCategory("MIST", BG));
        ScrollAPI.registerEntry("MIST", MIST);

        ScrollAPI.registerCategory(new ScrollCategory("THIS_IS_IT", BG));
        ScrollAPI.registerEntry("THIS_IS_IT", THIS_IS_IT);

        ScrollAPI.registerCategory(new ScrollCategory("TEST", BG));
        ScrollAPI.registerEntry("TEST", TEST);
    }
}
