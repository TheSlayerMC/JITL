package net.jitl.core.data;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.entity.base.MobStats;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;

import java.io.*;
import java.util.ArrayList;

public class LangRegistry {

    protected BufferedWriter langWriter;

    public String FIREBALLS = "It shoots Fireballs";
    public String FLYING_FIREBALLS = "It fly's and shoots Fireballs";

    public void generate() {
        String langDir = "../src/main/resources/assets/jitl/lang/en_us.json";

        File en_us = new File(langDir);

        ArrayList<String> mobList = new ArrayList<>(JEntities.entityName);

        ArrayList<String> blockList = new ArrayList<>(JBlocks.normalBlockName);
        ArrayList<String> logList = new ArrayList<>(JBlocks.logBlockName);
        ArrayList<String> crossList = new ArrayList<>(JBlocks.crossBlockName);
        ArrayList<String> tintedCrossList = new ArrayList<>(JBlocks.tintedCrossBlockName);
        ArrayList<String> tintedLeavesList = new ArrayList<>(JBlocks.tintedLeavesBlockName);
        ArrayList<String> doublePlantList = new ArrayList<>(JBlocks.doublePlantBlockName);
        ArrayList<String> lilyList = new ArrayList<>(JBlocks.lilyPadBlockName);
        ArrayList<String> paneList = new ArrayList<>(JBlocks.paneBlockName);
        ArrayList<String> attachedCrossList = new ArrayList<>(JBlocks.attachedCrossBlockName);
        ArrayList<String> doorList = new ArrayList<>(JBlocks.doorBlockName);
        ArrayList<String> trapDoorList = new ArrayList<>(JBlocks.trapDoorBlockName);
        ArrayList<String> stairList = new ArrayList<>(JBlocks.stairsBlockName);
        ArrayList<String> slabList = new ArrayList<>(JBlocks.slabBlockName);
        ArrayList<String> pressureList = new ArrayList<>(JBlocks.pressurePlateBlockName);
        ArrayList<String> buttonList = new ArrayList<>(JBlocks.buttonBlockName);
        ArrayList<String> gateList = new ArrayList<>(JBlocks.gateBlockName);
        ArrayList<String> fenceList = new ArrayList<>(JBlocks.fenceBlockName);
        ArrayList<String> modelBlockList = new ArrayList<>(JBlocks.modelBlockName);
        ArrayList<String> rotatableBlockList = new ArrayList<>(JBlocks.rotatableBlockName);
        ArrayList<String> vineBlockList = new ArrayList<>(JBlocks.vineBlockName);
        ArrayList<String> furnaceBlockList = new ArrayList<>(JBlocks.furnaceBlockName);
        ArrayList<String> portalBlockList = new ArrayList<>(JBlocks.portalBlockName);
        ArrayList<String> campfireBlockList = new ArrayList<>(JBlocks.campfireBlockName);
        ArrayList<String> chestBlockList = new ArrayList<>(JBlocks.chestBlockName);
        ArrayList<String> ladderBlockList = new ArrayList<>(JBlocks.ladderBlockName);
        ArrayList<String> pathBlockList = new ArrayList<>(JBlocks.pathBlockName);
        ArrayList<String> grassBlockList = new ArrayList<>(JBlocks.grassBlockName);
        ArrayList<String> overlayGrassBlockList = new ArrayList<>(JBlocks.overlayGrassBlockName);
        ArrayList<String> terrainBlockList = new ArrayList<>(JBlocks.terrainBlockName);
        ArrayList<String> randomizedBlockList = new ArrayList<>(JBlocks.randomBlockName);
        ArrayList<String> bushBlockList = new ArrayList<>(JBlocks.bushBlockName);
        ArrayList<String> farmBlockList = new ArrayList<>(JBlocks.farmlandBlockName);
        ArrayList<String> cropBlockList = new ArrayList<>(JBlocks.cropBlockName);
        ArrayList<String> wallBlockList = new ArrayList<>(JBlocks.wallBlockName);
        ArrayList<String> slimeBlockList = new ArrayList<>(JBlocks.slimeBlockName);
        ArrayList<String> trophyBlockList = new ArrayList<>(JBlocks.trophyBlockName);
        ArrayList<String> mushroomBlockList = new ArrayList<>(JBlocks.mushroomBlockName);
        ArrayList<String> basePortalBlockList = new ArrayList<>(JBlocks.basePortalBlockName);
        ArrayList<String> basePortalFrameBlockList = new ArrayList<>(JBlocks.basePortalFrameBlockName);
        ArrayList<String> topBottomBlockList = new ArrayList<>(JBlocks.topBottomBlockName);
        ArrayList<String> totemBlockList = new ArrayList<>(JBlocks.totemBlockName);

        ArrayList<String> toolItemList = new ArrayList<>(JItems.toolName);
        ArrayList<String> itemList = new ArrayList<>(JItems.itemName);
        ArrayList<String> spawnEggList = new ArrayList<>(JItems.spawnName);
        ArrayList<String> bowItemList = new ArrayList<>(JItems.bowName);
        ArrayList<String> shieldItemList = new ArrayList<>(JItems.shieldName);
        ArrayList<String> recordList = new ArrayList<>(JItems.recordName);
        ArrayList<String> modelItemList = new ArrayList<>(JItems.modelName);
        ArrayList<String> recordDescList = new ArrayList<>(JItems.recordDescName);

        try {
            if(en_us.exists()) en_us.delete();
            en_us.createNewFile();
            langWriter = new BufferedWriter(new FileWriter(en_us));
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeToFile("{");
        /* --------------------- Add manual lines --------------------- */

        writeToFile("\"itemGroup.jitl.blocks\" : \"JITL Blocks\",");
        writeToFile("\"itemGroup.jitl.items\" : \"JITL Items\",");
        writeToFile("\"jitl.key.stats\" : \"Player Stats\",");
        writeToFile("\"jitl.key\" : \"JITL Keys\",");
        writeToFile("\"jitl.knowledge.level\" : \"Level Up!\",");
        writeToFile("\"jitl.knowledge.experience\" : \"XP Gain!\",");
        writeToFile("\"jitl.knowledge.overworld\" : \"Overworld Knowledge\",");
        writeToFile("\"jitl.knowledge.the_nether\" : \"Nether Knowledge\",");
        writeToFile("\"jitl.knowledge.the_end\" : \"End Knowledge\",");
        writeToFile("\"jitl.knowledge.euca\" : \"Euca Knowledge\",");
        writeToFile("\"jitl.knowledge.boil\" : \"Boiling Knowledge\",");
        writeToFile("\"jitl.knowledge.frozen\" : \"Frozen Knowledge\",");
        writeToFile("\"jitl.knowledge.the_depths\" : \"Depths Knowledge\",");
        writeToFile("\"jitl.knowledge.corba\" : \"Corba Knowledge\",");
        writeToFile("\"jitl.knowledge.terrania\" : \"Terranian Knowledge\",");
        writeToFile("\"jitl.knowledge.cloudia\" : \"Cloudia Knowledge\",");
        writeToFile("\"jitl.knowledge.senterian\" : \"Senterian Knowledge\",");
        writeToFile("\"death.attack.bradberryBush\" : \"%1$s was poked to death by a Bradberry bush\",");
        writeToFile("\"death.attack.redcurrant\" : \"%1$s was poked to death by a Redcurrant bush\",");
        writeToFile("\"jitl.stats\" : \"Player Stats\",");
        writeToFile("\"jitl.king.hero\" : \"Good Luck... Hero\",");
        writeToFile("\"jitl.sen.knowledge_0\" : \"Ill trade you Overworld knowledge to get in there\",");
        writeToFile("\"jitl.sen.knowledge_1\" : \"See me when you have at least 75 Overworld Knowledge\",");
        writeToFile("\"jitl.sen.unlocked\" : \"I locked it for a reason but goodluck...\",");

        writeToFile("\"scroll.jitl.fail\" : \"Can't retrieve entry from scroll, report in Discord (unless this item came from creative tab)\",");

        writeToFile("\"scroll.jitl.sentry_gospel\" : \"We never saw it, but we know it - the Divine land Corba was birthed by the o' powerful Rock and Eye. Never forget the day, the hour, the second he - The True One - the all powerful Rock and Eye - cometh unto the great world he created, to seek his revenge on the wrong-doers who dare disrespect his land.\",");
        writeToFile("\"scroll.jitl.chap1.my_last_words\" : \"I have to make this quick Clancy... I'm not sure how much time I have left... I'm not sure how much time our reality has left. These horrid machines... they're evil. They've pioneered an art form of killing. These Sentries... I'm certain they will destroy reality as we know it if they become too powerful. They - I think they've found me... I hope this reaches you. Please take all of the other contents of my Ender Chest I've left you. They're yours now. Goodbye, my friend.\",");
        writeToFile("\"scroll.jitl.chap1.netheric_status\" : \"  nether\",");
        writeToFile("\"scroll.jitl.chap1.the_end\" : \"  end\",");
        writeToFile("\"scroll.jitl.chap1.beyond_boiling\" : \" boiling \",");
        writeToFile("\"scroll.jitl.chap1.frozen_despair\" : \" frozen \",");
        writeToFile("\"scroll.jitl.chap1.the_royals\" : \" euca \",");
        writeToFile("\"scroll.jitl.chap1.darkness\" : \" depths \",");
        writeToFile("\"scroll.jitl.chap1.fungi\" : \" terrania \",");
        writeToFile("\"scroll.jitl.chap1.mist\" : \" cloudia \",");
        writeToFile("\"scroll.jitl.chap1.this_is_it\" : \" senterian \",");

        writeToFile("\"scroll.jitl.name.sentry_gospel\" : \"The Senterian Gospel\",");
        writeToFile("\"scroll.jitl.name.my_last_words\" : \"My Last Words\",");
        writeToFile("\"scroll.jitl.name.netheric_status\" : \"Netheric Status\",");
        writeToFile("\"scroll.jitl.name.the_end\" : \"Netheric Status\",");
        writeToFile("\"scroll.jitl.name.beyond_boiling\" : \"Beyond Boiling\",");
        writeToFile("\"scroll.jitl.name.frozen_despair\" : \"Frozen Despair\",");
        writeToFile("\"scroll.jitl.name.the_royals\" : \"The Royals\",");
        writeToFile("\"scroll.jitl.name.darkness\" : \"Darkness\",");
        writeToFile("\"scroll.jitl.name.fungi\" : \"Fungi Everywhere\",");
        writeToFile("\"scroll.jitl.name.mist\" : \"Mist In The Distance\",");
        writeToFile("\"scroll.jitl.name.this_is_it\" : \"This Is IT\",");

        writeToFile("\"scroll.jitl.chapter.one\" : \"Chapter 1\",");
        writeToFile("\"scroll.jitl.chapter.two\" : \"Chapter 2\",");
        writeToFile("\"scroll.jitl.chapter.three\" : \"Chapter 3\",");
        writeToFile("\"scroll.jitl.chapter.four\" : \"Chapter 4\",");
        writeToFile("\"scroll.jitl.chapter.five\" : \"Chapter 5\",");
        writeToFile("\"scroll.jitl.chapter.six\" : \"Chapter 6\",");
        writeToFile("\"scroll.jitl.chapter.seven\" : \"Chapter 7\",");
        writeToFile("\"scroll.jitl.chapter.eight\" : \"Chapter 8\",");
        writeToFile("\"scroll.jitl.chapter.nine\" : \"Chapter 9\",");
        writeToFile("\"scroll.jitl.chapter.ten\" : \"Chapter 10\",");
        writeToFile("\"scroll.jitl.chapter.eleven\" : \"Chapter 11\",");

        writeToFile("\"curios.identifier.heart_container\" : \"Heart\",");
        writeToFile("\"curios.identifier.catalyst\" : \"Catalyst\",");

        writeToFile("\"book.jitl.landing_text\" : " + "\"Welcome $(playername) to Journey Into The Light, any other info needed head over to the $(l:https://discord.com/invite/WhXvz5b)Discord\",");
        writeToFile("\"book.jitl.book_name\" : " + "\"Essentia Bible\",");

        writeToFile("\"book.jitl.category.getting_started\" : " + "\"Getting Started\",");
        writeToFile("\"book.jitl.category.getting_started.desc\" : " + "\"This should give you the tips and hints on getting started.\",");
        writeToFile("\"book.jitl.entries.getting_started.ores\" : " + "\"Ores\",");
        writeToFile("\"book.jitl.category.dimensions\" : " + "\"Dimensions\",");
        writeToFile("\"book.jitl.entries.pets\" : " + "\"Pets\",");

        writeToFile("\"book.jitl.entry.getting_started.iridium_ore.title\" : " + "\"Iridium Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.iridium_ore.1\" : " + "\"Ore that generates in the Overworld between Y -80 and 48, drops Iridium Nugget\",");

        writeToFile("\"book.jitl.entry.getting_started.sapphire_ore.title\" : " + "\"Sapphire Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.sapphire_ore.1\" : " + "\"Ore that generates in the Overworld between Y -80 and 48, drops Sapphire Gem\",");

        writeToFile("\"book.jitl.entry.getting_started.shadium_ore.title\" : " + "\"Shadium Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.shadium_ore.1\" : " + "\"Ore that generates in the Overworld between Y -80 and 48, drops Raw Shadium\",");

        writeToFile("\"book.jitl.entry.getting_started.lunium_ore.title\" : " + "\"Lunium Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.lunium_ore.1\" : " + "\"Ore that generates in the Overworld between Y -80 and 48, drops Lunium Powder\",");

        writeToFile("\"book.jitl.entry.getting_started.verdite_ore.title\" : " + "\"Verdite Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.verdite_ore.1\" : " + "\"Ore that generates in the Overworld between Y -80 and 48, drops itself\",");

        writeToFile("\"book.jitl.entry.getting_started.blood_rock.title\" : " + "\"Blood Rock\",");
        writeToFile("\"book.jitl.entry.getting_started.blood_rock.1\" : " + "\"Stone that generates in the Nether between Y -64 and 256. This is used for Summoning Blocks and the Summoning Shrine\",");

        writeToFile("\"book.jitl.entry.getting_started.bloodcrust_ore.title\" : " + "\"Bloodcrust Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.bloodcrust_ore.1\" : " + "\"Ore that generates in the Nether Y -64 and 256, drops Raw Bloodcrust\",");

        writeToFile("\"book.jitl.entry.getting_started.bleedstone_block.title\" : " + "\"Bleedstone\",");
        writeToFile("\"book.jitl.entry.getting_started.bleedstone_block.1\" : " + "\"Ore that generates in the Nether on the roof, drops Bleedstone Gem\",");

        writeToFile("\"book.jitl.entry.getting_started.smithstone_block.title\" : " + "\"Smithstone\",");
        writeToFile("\"book.jitl.entry.getting_started.smithstone_block.1\" : " + "\"Ore that generates in the Nether on the roof, drops Smithstone Gem\",");

        writeToFile("\"book.jitl.entry.getting_started.warped_quartz_ore.title\" : " + "\"Warped Quartz Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.warped_quartz_ore.1\" : " + "\"Ore that generates in the Nether Biome Warped Forest, drops Warped Quartz\",");

        writeToFile("\"book.jitl.entry.getting_started.crimson_quartz_ore.title\" : " + "\"Crimson Quartz Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.crimson_quartz_ore.1\" : " + "\"Ore that generates in the Nether Biome Crimson Forest, drops Crimson Quartz\",");

        writeToFile("\"book.jitl.entry.getting_started.firestone_ore.title\" : " + "\"Firestone Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.firestone_ore.1\" : " + "\"Ore that generates in the Nether in Basalt, drops Firestone\",");

        writeToFile("\"book.jitl.entry.getting_started.enderillium_ore.title\" : " + "\"Enderillium Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.enderillium_ore.1\" : " + "\"Ore that generates in the End Islands, drops Enderillium Shard\",");

        writeToFile("\"book.jitl.entry.getting_started.ashual_ore.title\" : " + "\"Ashual Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.ashual_ore.1\" : " + "\"Ore that generates in the Boiling Point, drops Ash\",");

        writeToFile("\"book.jitl.entry.getting_started.blazium_ore.title\" : " + "\"Blazium Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.blazium_ore.1\" : " + "\"Ore that generates in the Boiling Point, drops Raw Blazium\",");

        writeToFile("\"book.jitl.entry.getting_started.mekyum_ore.title\" : " + "\"Mekyum Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.mekyum_ore.1\" : " + "\"Ore that generates in Euca Biome Goldite Grains, drops Mekyum Gem\",");

        writeToFile("\"book.jitl.entry.getting_started.celestium_ore.title\" : " + "\"Celestium Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.celestium_ore.1\" : " + "\"Ore that generates in Euca Biome Euca Plains, drops Celestium Gem\",");

        writeToFile("\"book.jitl.entry.getting_started.storon_ore.title\" : " + "\"Storon Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.storon_ore.1\" : " + "\"Ore that generates in Euca Biome Goldite Grains, drops Storon Gem\",");

        writeToFile("\"book.jitl.entry.getting_started.korite_ore.title\" : " + "\"Korite Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.korite_ore.1\" : " + "\"Ore that generates in Euca Biome Euca Plains, drops Korite Gem\",");

        writeToFile("\"book.jitl.entry.getting_started.rimestone_ore.title\" : " + "\"Rimestone Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.rimestone_ore.1\" : " + "\"Ore that generates in Frozen Lands, drops Rimestone\",");

        writeToFile("\"book.jitl.entry.getting_started.peridot_ore.title\" : " + "\"Peridot Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.peridot_ore.1\" : " + "\"Ore that generates in Frozen Lands, drops Peridot Gem\",");

        writeToFile("\"book.jitl.entry.getting_started.des_ore.title\" : " + "\"Des Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.des_ore.1\" : " + "\"Ore that generates in The Depths, drops itself\",");

        writeToFile("\"book.jitl.entry.getting_started.flairium_ore.title\" : " + "\"Flairium Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.flairium_ore.1\" : " + "\"Ore that generates in The Depths, drops itself\",");

        writeToFile("\"book.jitl.entry.getting_started.orbadite_ore.title\" : " + "\"Orbadite Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.orbadite_ore.1\" : " + "\"Ore that generates in Corba, drops Raw Orbadite\",");

        writeToFile("\"book.jitl.entry.getting_started.gorbite_ore.title\" : " + "\"Gorbite Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.gorbite_ore.1\" : " + "\"Ore that generates in Corba, drops Gorbite Gem\",");

        writeToFile("\"book.jitl.entry.getting_started.lunite_ore.title\" : " + "\"Lunite Ore\",");
        writeToFile("\"book.jitl.entry.getting_started.lunite_ore.1\" : " + "\"Ore that generates in Cloudia, drops Lunite Chunk\",");

        writeToFile("\"book.jitl.entry.portals.boil\" : " + "\"You find the Portal Frame blocks inside the $(l:jitl:structures/boil_lock_structure)Boil Lock structure\",");
        writeToFile("\"book.jitl.entry.portals.frozen\" : " + "\"You can craft the Frozen Portal Frame blocks to gain access\",");
        writeToFile("\"book.jitl.entry.portals.euca\" : " + "\"You can craft the Euca Portal Frame blocks with Euca Portal Pieces dropped by Nether Bosses ($(l:jitl:nether/bosses#okoloo)Okoloo$(/l), $(l:jitl:nether/bosses#withering_beast)Withering Beast$(/l), $(l:jitl:nether/bosses#soul_watcher)Soul Watcher$(/l))\",");
        writeToFile("\"book.jitl.entry.portals.depths\" : " + "\"You can craft the Depths Portal Frame blocks with Depths Portal Pieces dropped by Euca Bosses ($(l:jitl:euca/bosses#eudor)Eudor$(/l), $(l:jitl:euca/bosses#corallator$)Corallator$(/l))\",");
        writeToFile("\"book.jitl.entry.portals.corba\" : " + "\"You can craft the Corba Portal Frame blocks with Corba Portal Pieces dropped by Depths Bosses ($(l:jitl:depths/bosses#scale)Scale$(/l), $(l:jitl:depths/bosses#thunder_bird)Thunder Bird$(/l))\",");
        writeToFile("\"book.jitl.entry.portals.terrania\" : " + "\"You can craft the Terranian Portal Frame blocks with Terrania Portal Pieces dropped by Corba Bosses ($(l:jitl:corba/bosses#logger)Logger$(/l), $(l:jitl:corba/bosses#sentry_king)Sentry King$(/l))\",");
        writeToFile("\"book.jitl.entry.portals.cloudia\" : " + "\"You can craft the Cloudia Portal Frame blocks with Cloudia Portal Pieces dropped by a Terrania Boss ($(l:jitl:terrania/bosses#terranian_protector)Terranian Protector$(/l))\",");
        writeToFile("\"book.jitl.entry.portals.senterian\" : " + "\"Found in the $(l:jitl:overworld/structures#ancient_structure)Ancient Structure\",");

        writeToFile("\"book.jitl.entries.structures\" : " + "\"Structures\",");
        writeToFile("\"book.jitl.entries.ores\" : " + "\"Ores\",");
        writeToFile("\"book.jitl.entries.mobs\" : " + "\"Mobs\",");
        writeToFile("\"book.jitl.entries.bosses\" : " + "\"Bosses\",");
        writeToFile("\"book.jitl.entries.traders\" : " + "\"NPC's\",");

        writeToFile("\"book.jitl.category.summoning\" : " + "\"Summoning\",");
        writeToFile("\"book.jitl.entries.summoning\" : " + "\"Summoning Shrine\",");
        writeToFile("\"book.jitl.category.summoning.desc\" : " + "\"Information needed on creating the Summoning Shrine\",");
        writeToFile("\"book.jitl.entry.summoning.book.desc\" : " + "\"Craft the book needed to find the recipes to summon all the JITL bosses\",");

        writeToFile("\"book.jitl.category.overworld\" : " + "\"Overworld\",");
        writeToFile("\"book.jitl.category.overworld.desc\" : " + "\"Information on added features to the Overworld\",");

        writeToFile("\"book.jitl.category.nether\" : " + "\"Nether\",");
        writeToFile("\"book.jitl.category.nether.desc\" : " + "\"Information on added features to the Nether\",");

        writeToFile("\"book.jitl.category.end\" : " + "\"The End\",");
        writeToFile("\"book.jitl.category.end.desc\" : " + "\"Information on added features to The End\",");

        writeToFile("\"book.jitl.category.boil\" : " + "\"Boiling Point\",");
        writeToFile("\"book.jitl.category.boil.desc\" : " + "\"Information on features in the Boiling Point\",");

        writeToFile("\"book.jitl.category.frozen\" : " + "\"Frozen Lands\",");
        writeToFile("\"book.jitl.category.frozen.desc\" : " + "\"Information on features in the Frozen Lands\",");

        writeToFile("\"book.jitl.category.euca\" : " + "\"Euca\",");
        writeToFile("\"book.jitl.category.euca.desc\" : " + "\"Information on features in Euca\",");

        writeToFile("\"book.jitl.category.depths\" : " + "\"The Depths\",");
        writeToFile("\"book.jitl.category.depths.desc\" : " + "\"Information on features in The Depths\",");

        writeToFile("\"book.jitl.category.corba\" : " + "\"Corba\",");
        writeToFile("\"book.jitl.category.corba.desc\" : " + "\"Information on features in Corba\",");

        writeToFile("\"book.jitl.category.terrania\" : " + "\"Terrania\",");
        writeToFile("\"book.jitl.category.terrania.desc\" : " + "\"Information on features in Terrania\",");

        writeToFile("\"book.jitl.category.cloudia\" : " + "\"Cloudia\",");
        writeToFile("\"book.jitl.category.cloudia.desc\" : " + "\"Information on features in Cloudia\",");

        writeToFile("\"book.jitl.category.senterian\" : " + "\"Senterian\",");
        writeToFile("\"book.jitl.category.senterian.desc\" : " + "\"Information on features in Senterian\",");

        writeToFile("\"structure.overworld.jitl.tower_guardian\" : " + "\"Guardian Tower\",");
        writeToFile("\"book.jitl.entry.overworld.tower_structure.desc\" : " + "\"Found randomly in any Overworld Biomes, climb your way to the top to find $(l:jitl:overworld/bosses#tower_guardian)Tower Guardian$(/l), Kill him if you can and open his Boss Crystal to receive keys to unlock the loot chests\",");
        writeToFile("\"book.jitl.entry.overworld.tower_guardian.desc\" : " + "\"Tower Guardian is one of the starting bosses, he has 275 Health and 5 Attack Damage, he can smash down on you which occasionally causes an explosion.\",");

        writeToFile("\"structure.overworld.jitl.rockite_dungeon\" : " + "\"Rockite Dungeon\",");
        writeToFile("\"book.jitl.entry.overworld.rockite_dungeon.desc\" : " + "\"Found randomly in any Overworld Biomes underground between -Y32 and -Y56, Break the stone Rockite Smasher with any Pickaxe to spawn in the $(l:jitl:overworld/bosses#rockite_smasher)Rockite Smasher$(/l) boss, Kill him if you can and open his Boss Crystal to receive keys to unlock the loot chests\",");
        writeToFile("\"book.jitl.entry.overworld.rockite_smasher.desc\" : " + "\"Rockite Smasher is one of the starting bosses, he can only be damaged with any Pickaxe. He has 175 Health and 10 Attack Damage, he can throw you up to damage you.\",");

        writeToFile("\"book.jitl.entry.overworld.ancient_structure.desc.1\" : " + "\"The Ancient Structure holds the Portal for Senterian, you can open this up by going to the top of the structure and \",");
        writeToFile("\"book.jitl.entry.overworld.ancient_structure.desc.2\" : " + "\"filling each corner with an Ancient piece (shard or chunk as well) and clicking the middle block with an Ancient Eye of Opening\",");
        writeToFile("\"book.jitl.entry.overworld.ancient_structure.desc.3\" : " + "\"Inside the bottom you will find $(l:jitl:overworld/traders#neutral_sentry_stalker)Neutral Sentry Stalker$(/l) wanting information on the overworld\",");
        writeToFile("\"book.jitl.entry.overworld.ancient_structure.desc.4\" : " + "\"Once you give him information he will drop the key he used to lock the room, open it up and fill the missing spots with Sentry Eyes\",");

        writeToFile("\"structure.overworld.jitl.mage_hut\" : " + "\"Mage Hut\",");
        writeToFile("\"book.jitl.entry.overworld.mage_hut.desc\" : " + "\"Found randomly in any Dark Forest Biome, Climb up the top to find and meet the $(l:jitl:overworld/traders#mage)Mage\",");

        writeToFile("\"structure.nether.jitl.boil_lock\" : " + "\"Boil Lock\",");
        writeToFile("\"book.jitl.entry.nether.boil_lock.desc\" : " + "\"Unbreakable structure which you need to unlock with a Boiling Lock Key which is found in the Nether Tower. This structure contains the Boiling Point Portal Frames\",");

        writeToFile("\"structure.nether.jitl.nether_tower\" : " + "\"Nether Tower\",");
        writeToFile("\"book.jitl.entry.nether.nether_tower.desc\" : " + "\"Loot Tower which has locked chests (unlocked with a crafted Nether Chest Key) which may hold a Boiling Lock Key which is used on the Boil Lock structure, also contains Mini Ghast spawners and extra loot\",");

        writeToFile("\"structure.nether.jitl.hellbot\" : " + "\"Hellbot Spawner\",");
        writeToFile("\"book.jitl.entry.nether.hellbot.desc\" : " + "\"Spawns Hellbots which drops items that is used to craft Nether Chest Keys\",");

        writeToFile("\"structure.nether.jitl.okoloo_crypt\" : " + "\"Okoloo Crypt\",");
        writeToFile("\"book.jitl.entry.nether.okoloo_crypt.desc\" : " + "\"A structure that you place the Broken Okoloo Club into the Pedestal to spawn $(l:jitl:nether/bosses#okoloo)Okoloo\",");

        writeToFile("\"book.jitl.entry.overworld.mage.desc\" : " + "\"Mage is a trading NPC that lives up the top of $(l:jitl:overworld/structures#mage_hut)Mage Hut\",");
        writeToFile("\"book.jitl.entry.overworld.neutral_sentry_stalker.desc\" : " + "\"This Sentry Stalker is a bit different to normal... He locked the Senterian Portal away trying to close it away while he finds enough information on the Overworld. While he resides inside of the $(l:jitl:overworld/structures#ancient_tower)Ancient Tower$(/l) he will give the key inside for an exchange\",");
        writeToFile("\"book.jitl.entry.overworld.rockite_golem.desc\" : " + "\"Rockite Golem is a trading NPC and spawns underground randomly\",");

        writeToFile("\"book.jitl.entry.nether.blazier.desc\" : " + "\"Blazier is spawned using the Blazier Orb made inside the Summoning Table, he has 850 Health, when he gets below 50% health he spawns help\",");
        writeToFile("\"book.jitl.entry.nether.calcia.desc\" : " + "\"Calcia is spawned using the Calcia Orb made inside the Summoning Table, he has 650 Health, he likes to choose when he is seen...\",");
        writeToFile("\"book.jitl.entry.nether.okoloo.desc\" : " + "\"Okoloo is spawned inside the $(l:jitl:nether/structures#okoloo_crypt)Okoloo Crypt$(/l), he has 600 Health, he likes to swing his club\",");
        writeToFile("\"book.jitl.entry.nether.soul_watcher.desc\" : " + "\"The Soul Watcher is spawned using the Soul Watcher Orb made inside the Summoning Table, he has 650 Health, make sure to contain him or he may fly away\",");
        writeToFile("\"book.jitl.entry.nether.withering_beast.desc\" : " + "\"Withering Beast is spawned using the Withering Beast Orb made inside the Summoning Table, he has 750 Health, deadly to the touch.\",");

        writeToFile("\"book.jitl.entry.terrania.terranian_protector.desc\" : " + "\"Terranian Protector is spawned using the Enchanted Terrastar made inside the Summoning Table, he has 2500 Health it shoots heavy Mud which does 7.5x Hearts of damage per impact\",");

        writeToFile("\"structure.boil.jitl.brison\" : " + "\"Brison\",");
        writeToFile("\"book.jitl.entry.boil.brison.desc\" : " + "\"The Brison is found in the Charred Fields Biome and keeps the $(l:jitl:boil/traders#escaped_convict)Escaped Convict$(/l) with a few needed spawners attached\",");
        writeToFile("\"book.jitl.entry.boil.mobs.escaped_convict.desc\" : " + "\"Escaped Convict is a NPC that lives in the $(l:jitl:boil/structures#brison)Brison\",");

        writeToFile("\"structure.boil.jitl.hellwing_tower\" : " + "\"Hellwing Tower\",");
        writeToFile("\"book.jitl.entry.boil.hellwing_tower.desc\" : " + "\"The Hellwing Tower is found inside the Boiling Sands Biome and spawns $(l:jitl:boil/mobs#hellwing)Hellwings$(/l)\",");

        writeToFile("\"structure.boil.jitl.observer_hut\" : " + "\"Observer Hut\",");
        writeToFile("\"book.jitl.entry.boil.observer_hut.desc\" : " + "\"The Observer Hut is found inside the Boiling Biome and spawns $(l:jitl:boil/mobs#observer)Observers$(/l)\",");

        writeToFile("\"structure.frozen.jitl.eskimo\" : " + "\"Eskimo Camp\",");
        writeToFile("\"book.jitl.entry.frozen.mobs.eskimo.desc\" : " + "\"The Eskimo is a Trading NPC that lives in the $(l:jitl:frozen/structures#eskimo_camp)Eskimo Camp$(/l), He is the only way to get Frostborn Souls\",");

        writeToFile("\"structure.frozen.jitl.eskimo_camp\" : " + "\"Eskimo Camp\",");
        writeToFile("\"book.jitl.entry.frozen.eskimo_camp.desc\" : " + "\"The Eskimo Camp is found inside the Dying Forest Biome and spawns $(l:jitl:frozen/traders#eskimo)Eskimos$(/l)\",");

        writeToFile("\"structure.frozen.jitl.frozen_dungeon\" : " + "\"Frozen Dungeon\",");
        writeToFile("\"book.jitl.entry.frozen.frozen_dungeon.desc\" : " + "\"The Frozen Dungeon is found in any Frozen Lands Biome and spawns $(l:jitl:frozen/mobs#frozen_frostbiter)Frozen Frostbiter$(/l)\",");

        writeToFile("\"structure.frozen.jitl.guardian_ruins\" : " + "\"Guardian Ruins\",");
        writeToFile("\"book.jitl.entry.frozen.guardian_ruins.desc\" : " + "\"The Guardian Ruins is found inside the Dying Forest Biome and inside has 8 Pedestals and the $(l:jitl:frozen/traders#frozen_guardian)Frozen Guardian$(/l), place 8 x Frostborn souls (collected by trading with an $(l:jitl:frozen/traders#eskimo)Eskimos$(/l)) on the pedestals and interact with the Guardian, this will remove the storm from the dimension and complete the Frozen Knowledge system\",");
        writeToFile("\"book.jitl.entry.frozen.mobs.frozen_guardian.desc\" : " + "\"The Frozen Guardian is a idle NPC that lives in the $(l:jitl:frozen/structures#guardian_ruins)Guardian Ruins$(/l), He just wants warmth and peace but his anger is the only thing standing between him leaving the Frozen Lands Dimension, without him the storm will be gone\",");

        writeToFile("\"structure.frozen.jitl.spike_dungeon\" : " + "\"Spike Dungeon\",");
        writeToFile("\"book.jitl.entry.frozen.spike_dungeon.desc\" : " + "\"The Spike Dungeon is found the Frozen Wastes Biome and spawns $(l:jitl:frozen/bosses#frost_golem)Frost Golem$(/l)\",");
        writeToFile("\"book.jitl.entry.frozen.frost_golem.desc\" : " + "\"Frost Golem is a Boss that lives in the $(l:jitl:frozen/structures#spike_dungeon)Spike Dungeon$(/l), he will hit you up into the air. He has 350 Health and 10 Damage\",");

        writeToFile("\"structure.boil.jitl.trader_hut\" : " + "\"Trader Hut\",");
        writeToFile("\"book.jitl.entry.boil.trader_hut.desc\" : " + "\"The Trader Hut is found in the Scorched Wastelands Biome and keeps the $(l:jitl:boil/traders#boil_trader)Boil Trader$(/l)\",");
        writeToFile("\"book.jitl.entry.boil.mobs.boil_trader.desc\" : " + "\"Boil Trader is a NPC that lives in the $(l:jitl:boil/structures#trader_hut)Trader Hut\",");

        writeToFile("\"book.jitl.entry.euca.corallator.desc\" : " + "\"The Corallator is spawned using the Corallator Orb made inside the Summoning Table, he has 950 Health, make sure to trap him or he might fly away\",");
        writeToFile("\"book.jitl.entry.euca.eudor.desc\" : " + "\"Eudor is the Royal of the lands and is spawned using Eudor's Crown which the $(l:jitl:euca/traders#royal_king)Royal King$(/l) will give you, he has 950 Health, he sure is strong and is only seen when he wants to be\",");

        writeToFile("\"structure.euca.jitl.alloy_mender_hut\" : " + "\"Alloy Mender's Hut\",");
        writeToFile("\"book.jitl.entry.euca.alloy_mender_hut.desc\" : " + "\"The Alloy Mender's Hut is found in the Goldite Grains Biome and resides the $(l:jitl:euca/traders#alloy_mender)Alloy Mender$(/l)\",");
        writeToFile("\"book.jitl.entry.euca.mobs.alloy_mender.desc\" : " + "\"Alloy Mender is a trader NPC that lives in his $(l:jitl:euca/structures#alloy_mender_hut)Hut$(/l), He moved away from the village to find his own riches\",");

        writeToFile("\"structure.euca.jitl.euca_palace\" : " + "\"Euca Palace\",");
        writeToFile("\"book.jitl.entry.euca.euca_palace.desc\" : " + "\"Euca Palace is found in the Goldite Grains Biome and resides the $(l:jitl:euca/traders#royal_king)Royal King$(/l)\",");
        writeToFile("\"book.jitl.entry.euca.mobs.royal_king.desc\" : " + "\"The Royal King is the keeper of Eudor, he is a idle NPC that lives in the $(l:jitl:euca/structures#euca_palace)Euca Palace$(/l), Once you put on the Royal Pedestals around him what the wants he will give you his Crown\",");

        writeToFile("\"structure.euca.jitl.euca_village\" : " + "\"Euca Village\",");
        writeToFile("\"book.jitl.entry.euca.euca_village.desc\" : " + "\"Euca Village is found in the Goldite Plains Biome and resides some $(l:jitl:euca/traders#crypian)Crypians$(/l)\",");
        writeToFile("\"book.jitl.entry.euca.mobs.crypian.desc\" : " + "\"Crypian is a possible trader NPC that lives in the $(l:jitl:euca/structures#euca_village)Euca Village$(/l), He is a Goldbot that found his Sprocket to go back to normal\",");

        writeToFile("\"structure.euca.jitl.euca_sphere\" : " + "\"Euca Sphere\",");
        writeToFile("\"book.jitl.entry.euca.euca_sphere.desc\" : " + "\"Euca Sphere is found in any Euca Biomes and spawns $(l:jitl:euca/mobs#goldbot)Goldbots$(/l), it also contains loot witch is unlockable with Euca Keys found in chests inside Euca\",");

        writeToFile("\"structure.euca.jitl.gold_bot_spawner\" : " + "\"Goldbot Spawner\",");
        writeToFile("\"book.jitl.entry.euca.gold_bot_spawner.desc\" : " + "\"Goldbot Spawner is found in any Euca Biomes and spawns $(l:jitl:euca/mobs#goldbot)Goldbots$(/l)\",");

        writeToFile("\"structure.depths.jitl.dark_sorcerers_dungeon\" : " + "\"Sorcerers Dungeon\",");
        writeToFile("\"book.jitl.entry.depths.dark_sorcerers_dungeon.desc\" : " + "\"Dark Sorcerers Dungeon spawns $(l:jitl:depths/mobs#dark_sorcerer)Dark Sorcerers$(/l) when inside, it is unlocked with Depths Lock Key obtained from $(l:jitl:depths/traders#staring_guardian)Staring Guardian$(/l)\",");

        writeToFile("\"structure.depths.jitl.depths_watchtower\" : " + "\"Depths Watchtower\",");
        writeToFile("\"book.jitl.entry.depths.depths_watchtower.desc\" : " + "\"Depths Watchtower resides $(l:jitl:depths/traders#staring_guardian)Staring Guardian$(/l) up the top\",");
        writeToFile("\"book.jitl.entry.depths.mobs.staring_guardian.desc\" : " + "\"Staring Guardian is a trading NPC that lives on top of the $(l:jitl:depths/structures#depths_watchtower)Depths Watchtower$(/l)\",");

        writeToFile("\"structure.depths.jitl.depths_village\" : " + "\"Depths Village\",");
        writeToFile("\"book.jitl.entry.depths.depths_village.desc\" : " + "\"Depths Village resides $(l:jitl:depths/traders#auron)Auron$(/l) and also new crops\",");
        writeToFile("\"book.jitl.entry.depths.mobs.auron.desc\" : " + "\"Auron is a trading NPC that lives in the $(l:jitl:depths/structures#depths_village)Depths Village$(/l)\",");

        writeToFile("\"book.jitl.entry.depths.scale.desc\" : " + "\"Scale is spawned using the Scale Orb made inside the Summoning Table, he has 1550 Health, make sure to trap him or he might fly away\",");
        writeToFile("\"book.jitl.entry.depths.thunder_bird.desc\" : " + "\"Thunder Bird is spawned using the Thunder Bird Orb made inside the Summoning Table, he has 1550 Health, he has a hard hit with 10 Damage\",");

        writeToFile("\"structure.corba.jitl.corba_village\" : " + "\"Corba Village\",");
        writeToFile("\"book.jitl.entry.corba.corba_village.desc\" : " + "\"Corba Village resides Trading NPC's and also new crops\",");

        writeToFile("\"structure.corba.jitl.seer_tree\" : " + "\"Seer Tree\",");
        writeToFile("\"book.jitl.entry.corba.seer_tree.desc\" : " + "\"Seer Tree spawns $(l:jitl:corba/mobs#overseer)Overseers$(/l), once you have obtained an Elder Key from $(l:jitl:corba/traders#hooded)The Hooded$(/l) climb your way to the very top to spawn $(l:jitl:corba/mobs#overseer_elder)Overseer Elder$(/l)\",");
        writeToFile("\"book.jitl.entry.corba.mobs.green_tordo.desc\" : " + "\"Green Tordo is a trading NPC that lives in the $(l:jitl:corba/structures#corba_village)Corba Village$(/l)\",");
        writeToFile("\"book.jitl.entry.corba.mobs.hooded.desc\" : " + "\"The Hooded is a trading NPC that lives in the $(l:jitl:corba/structures#corba_village)Corba Village$(/l)\",");
        writeToFile("\"book.jitl.entry.corba.mobs.red_tordo.desc\" : " + "\"Red Tordo is a trading NPC that lives in the $(l:jitl:corba/structures#corba_village)Corba Village$(/l)\",");
        writeToFile("\"book.jitl.entry.corba.mobs.overgrown_merchant.desc\" : " + "\"Overgrown Merchant is a trading NPC that lives in the $(l:jitl:corba/structures#corba_village)Corba Village$(/l)\",");
        writeToFile("\"book.jitl.entry.corba.logger.desc\" : " + "\"Logger is spawned using the Logger Orb made inside the Summoning Table, he has 2350 Health, he will throw you while he has a hard hit with 10 Damage\",");
        writeToFile("\"book.jitl.entry.corba.sentry_king.desc\" : " + "\"Sentry King is spawned using the Sentry King Orb made inside the Summoning Table, he has 2500 Health, he will throw mud at you that hits hard with 15 Damage, his loot contains Ancient Eye of Opening which is used on the $(l:jitl:overworld/structures#ancient)Ancient Tower$(/l)\",");

        writeToFile("\"structure.terrania.jitl.mega_mushroom\" : " + "\"Mega Mushroom\",");
        writeToFile("\"book.jitl.entry.terrania.mega_mushroom.desc\" : " + "\"Mega Mushroom is found in the Mushroom Biome and has 2 variants, each variant being Purple or Blue topped containing a different Trading NPC\",");

        writeToFile("\"structure.terrania.jitl.mega_tree\" : " + "\"Mega Tree\",");
        writeToFile("\"book.jitl.entry.terrania.mega_tree.desc\" : " + "\"Mega Tree is found in the Forest Biome and contains the Earthen Crystals which is needed to progress for making the Enchanted Terrastar in the Summoning Shrine\",");
        writeToFile("\"book.jitl.entry.terrania.mobs.terranian_enchanter.desc\" : " + "\"Terranian Enchanter is a trading NPC that lives in one variant of the $(l:jitl:terrania/structures#mega_mushroom)Mega Mushroom$(/l), he also trade the shards needed to get into the $(l:jitl:overworld/structures#ancient_structure)Ancient Structure$(/l)\",");
        writeToFile("\"book.jitl.entry.terrania.mobs.terranian_trader.desc\" : " + "\"Terranian Trader is a trading NPC that lives in one variant of the $(l:jitl:terrania/structures#mega_mushroom)Mega Mushroom$(/l), he also trade the shards needed to get into the $(l:jitl:overworld/structures#ancient_structure)Ancient Structure$(/l)\",");

        writeToFile("\"structure.cloudia.jitl.cloudia_village_1\" : " + "\"Cloudia Village\",");
        writeToFile("\"book.jitl.entry.cloudia.cloudia_village_1.desc\" : " + "\"Cloudia Village resides trading NPC's\",");

        writeToFile("\"structure.cloudia.jitl.cloudia_village_2\" : " + "\"Cloudia Village\",");
        writeToFile("\"book.jitl.entry.cloudia.cloudia_village_2.desc\" : " + "\"Cloudia Village resides trading NPC's\",");

        writeToFile("\"book.jitl.entry.cloudia.sky_stalker.desc\" : " + "\"Sky Stalker is spawned using the Mysterious Disk which is traded to get from $(l:jitl:cloudia/traders#starlight_villager)Starlight Villager$(/l), be sure to trap him as he has 3500 Health and may fly away\",");

        writeToFile("\"book.jitl.entry.cloudia.mobs.starlight_villager.desc\" : " + "\"Starlight Villager is a trading NPC that lives in one variant of the $(l:jitl:terrania/structures#cloudia_village_1)Cloudia Villages$(/l), he trades what you need for the Mysterious Disk to spawn $(l:jitl:cloudia/bosses#sky_stalker)Sky Stalker$(/l)\",");
        writeToFile("\"book.jitl.entry.cloudia.mobs.starlight_blacksmith.desc\" : " + "\"Starlight Blacksmith is a trading NPC that lives in one variant of the $(l:jitl:terrania/structures#cloudia_village_1)Cloudia Villages$(/l)\",");

        writeToFile("\"structure.senterian.jitl.senterian_altar\" : " + "\"Senterian Altar\",");
        writeToFile("\"book.jitl.entry.senterian.senterian_altar.desc\" : " + "\"Senterian Altar Room is the biggest room in the dimension, in this room in the middle you will find the Senterian Altars, you can place a Sentry Observer item into them and they will spawn Mini Senterian Mobs, this is the only way to recieve Sentacoins.\",");

        addMob(EnumKnowledge.OVERWORLD, "brown_hongo", "Forest like", true, MobStats.BIG_HONGO_HEALTH, MobStats.BIG_HONGO_DAMAGE, "Hongoshrooms");
        addMob(EnumKnowledge.OVERWORLD, "big_hongo", "Forest like", true, MobStats.BIG_HONGO_HEALTH, MobStats.BIG_HONGO_DAMAGE, "Hongoshrooms");
        addMob(EnumKnowledge.OVERWORLD, "medium_hongo", "Forest like", true, MobStats.MEDIUM_HONGO_HEALTH, MobStats.MEDIUM_HONGO_DAMAGE, "Hongoshrooms");
        addMob(EnumKnowledge.OVERWORLD, "small_hongo", "Forest like", true, MobStats.SMALL_HONGO_HEALTH, MobStats.SMALL_HONGO_DAMAGE, "Hongoshrooms");
        addMob(EnumKnowledge.OVERWORLD, "blizzard", "Snow like", FIREBALLS,false, MobStats.BLIZZARD_HEALTH, 0,"Snowballs");
        addMob(EnumKnowledge.OVERWORLD, "boom_boom", "Overworld", "Will create a medium explosion", false, MobStats.BOOM_HEALTH, 0, "Gunpowder and TNT blocks");
        addMob(EnumKnowledge.OVERWORLD, "caveling", "Overworld Underground", false, MobStats.CAVELING_HEALTH, MobStats.CAVELING_DAMAGE, "Stone Clump, Cave Dust and Cave Crystal");
        addMob(EnumKnowledge.OVERWORLD, "cavurn", "Overworld Underground", false, MobStats.CAVURN_HEALTH, MobStats.CAVELING_DAMAGE, "Cave Dust and Cave Crystal");
        addMob(EnumKnowledge.OVERWORLD, "stonewalker", "Overworld Underground", false, MobStats.STONEWALKER_HEALTH, MobStats.STONEWALKER_DAMAGE, "Cave Dust, Stone Clump, Shadium Ingot, Lunium Ingot, Sapphire and Cave Crystal");
        addMob(EnumKnowledge.OVERWORLD, "floro", "Forest like", "Once not hiding away it will shoot projectiles", false, MobStats.FLORO_HEALTH, 0, "Floro Pedal and Floro Seeds");
        addStructureMob(EnumKnowledge.OVERWORLD, "illager_mech", "Illager Bunker", true, 100, 8, "");
        addMob(EnumKnowledge.OVERWORLD, "jungle_turtle", "Jungle like", true, MobStats.JUNGLE_TURTLE_HEALTH, MobStats.JUNGLE_TURTLE_DAMAGE, "");
        addMob(EnumKnowledge.OVERWORLD, "sand_crawler", "Desert like", false, MobStats.SAND_CRAWLER_HEALTH, MobStats.SAND_CRAWLER_DAMAGE, "Sand Blocks");
        addMob(EnumKnowledge.OVERWORLD, "spyclopse", "Desert like", false, MobStats.SPYCLOPS_HEALTH, MobStats.SPYCLOPS_DAMAGE, "Spyclopse Eye");
        addMob(EnumKnowledge.OVERWORLD, "rockite_golem", "Overworld Underground", "NPC Mob, Will trade with you", MobStats.NPC_HEALTH, "");
        addStructureMob(EnumKnowledge.OVERWORLD, "neutral_sentry_stalker", "$(l:jitl:overworld/structures#ancient_structure)Ancient Structure$(/l)", "NPC Mob, Will trade with you once you reach 100 Overworld Knowledge", true, MobStats.NPC_HEALTH, 0, "");
        addStructureMob(EnumKnowledge.OVERWORLD, "mage", "$(l:jitl:overworld/structures#mage_hut)Mage Hut$(/l)", "NPC Mob, Will trade with you", true, MobStats.NPC_HEALTH, 0, "");
        addMob(EnumKnowledge.OVERWORLD, "robot", "Any Overworld Biome", "Will attack on site", MobStats.ROBOT_HEALTH, "Iron Ingot and Redstone Dust");
        addPet(EnumKnowledge.OVERWORLD, "pet_robot", true, "Obtained from the $(l:jitl:overworld/traders#mage)Mage$(/l)", "Already tamed when spawned, can be healed with Pet Food", MobStats.PET_ROBOT_HEALTH, MobStats.PET_ROBOT_DAMAGE,"");
        addPet(EnumKnowledge.OVERWORLD, "ferret", false, "Forest like", "Pet Food", MobStats.FERRET_HEALTH, MobStats.FERRET_DAMAGE,"");

        addStructureMob(EnumKnowledge.NETHER, "hellbot", "$(l:jitl:nether/structures#hellbot)Hellbot Structure$(/l)", false, MobStats.HELLBOT_HEALTH, MobStats.HELLBOT_DAMAGE, "Flaming Sprocket, Hell Shards and Flaming Spring");
        addMob(EnumKnowledge.NETHER, "hell_cow", "Nether", "Breedable with HellShards, Can be used to get Lava with a Bucket", MobStats.HELL_COW_HEALTH, "Blazing Fireball and Boil Powder");
        addMob(EnumKnowledge.NETHER, "hell_serpent", "Nether", false, MobStats.LAVASNAKE_HEALTH, MobStats.LAVASNAKE_DAMAGE, "Snake Skin, Hell Shards, Snake Flesh and Blood");
        addMob(EnumKnowledge.NETHER, "hell_turtle", "Nether", true, MobStats.HELL_TURTLE_HEALTH, MobStats.HELL_TURTLE_DAMAGE, "Hell Turtle Shell, Hell Shards and Blood");
        addMob(EnumKnowledge.NETHER, "inferno_blaze", "Nether", FIREBALLS,false, MobStats.INFERNO_BLAZE_HEALTH, 0,"Blaze Powder, Hell Shards, Blaze Rod");
        addStructureMob(EnumKnowledge.NETHER, "mini_ghast", "$(l:jitl:nether/structures#nether_tower)Nether Tower$(/l)", FIREBALLS, false, MobStats.MINI_GHAST_HEALTH, 0, "Flaming Ghast Tentacle, Hell Shards and Balmy Teardrop");
        addMob(EnumKnowledge.NETHER, "reaper", "Nether", false, MobStats.REAPER_HEALTH, MobStats.REAPER_DAMAGE, "Withic Dust, Hell Shards and Lost Soul");
        addMob(EnumKnowledge.NETHER, "witherspine", "Nether", false, MobStats.WITHERSPINE_HEALTH, MobStats.WITHERSPINE_DAMAGE, "Withic Dust and Withicspine");

        addMob(EnumKnowledge.BOIL, "burning_light", "Boiling Point", false, MobStats.BURNING_LIGHT_HEALTH, MobStats.BURNING_LIGHT_DAMAGE, "Blazing Fireball and Boil Powder");
        addMob(EnumKnowledge.BOIL, "flame_lotus", "Boiling Point", "For aesthetics", MobStats.FLAME_LOTUS_HEALTH, "");
        addMob(EnumKnowledge.BOIL, "frightener", "Boiling Point", false, MobStats.FRIGHTENER_HEALTH, MobStats.FRIGHTENER_DAMAGE, "Sand blocks");
        addStructureMob(EnumKnowledge.BOIL, "hellwing", "$(l:jitl:boil/structures#hellwing)Hellwing Tower$(/l)", "Shoots Fireballs", false, MobStats.HELLWING_HEALTH, 0, "Boiling Skull and a Iron Sword");
        addMob(EnumKnowledge.BOIL, "magma_blaze", "Boiling Point", FIREBALLS, false, MobStats.MAGMA_BLAZE_HEALTH, 0, "Blaze Powder and Boil Powder");
        addStructureMob(EnumKnowledge.BOIL, "observer", "$(l:jitl:boil/structures#brison)Brison Network$(/l) and $(l:jitl:boil/structures#observer_hut)Observer Hut$(/l)", "Shoots Fireballs", false, MobStats.OBSERVER_HEALTH, 0, "Sizzling Eye and Boil Powder");
        addStructureMob(EnumKnowledge.BOIL, "screamer", "$(l:jitl:boil/structures#brison)Brison Network$(/l)", "Shoots Fireballs", false, MobStats.SCREAMER_HEALTH, 0, "Sizzling Eye and Boil Powder");

        addMob(EnumKnowledge.FROZEN, "capybara", "Frozen", true, MobStats.CAPYBARA_HEALTH, MobStats.CAPYBARA_DAMAGE, "");
        addMob(EnumKnowledge.FROZEN, "crystal_cluster", "Frozen", "Fly's around", MobStats.CRYSTAL_CLUSTER_HEALTH, "");
        addStructureMob(EnumKnowledge.FROZEN, "frozen_frostbiter", "$(l:jitl:frozen/structures#frozen_dungeon)Frozen Dungeon$(/l)", FIREBALLS, false, MobStats.FROZEN_FROSTBITER_HEALTH, 0, "Frost Flakes");
        addMob(EnumKnowledge.FROZEN, "frozen_troll", "Frozen", "It has a hard straight hit", true, MobStats.FROZEN_TROLL_HEALTH, MobStats.FROZEN_TROLL_DAMAGE, "");
        addStructureMob(EnumKnowledge.FROZEN, "ice_golem", "$(l:jitl:frozen/structures#eskimo_camp)Eskimo Camp$(/l)", true, MobStats.ICE_GOLEM_HEALTH, MobStats.ICE_GOLEM_DAMAGE, "");
        addMob(EnumKnowledge.FROZEN, "permafraust", "Frozen", false, MobStats.PERMAFRAUST_HEALTH, MobStats.PERMAFRAUST_DAMAGE, "Crystal Flake");
        addMob(EnumKnowledge.FROZEN, "shatterer", "Frozen", true, MobStats.SHATTERER_HEALTH, 0, "");
        addMob(EnumKnowledge.FROZEN, "shivering_bushwalker", "Frozen", true, MobStats.SHIVERING_BUSHWALKER_HEALTH, MobStats.SHIVERING_BUSHWALKER_DAMAGE, "Crystal Flakes");
        addMob(EnumKnowledge.FROZEN, "shivering_shrieker", "Frozen", true, MobStats.SHIVERING_SHRIEKER_HEALTH, MobStats.SHIVERING_SHRIEKER_DAMAGE, "Crystal Flakes");
        addPet(EnumKnowledge.FROZEN, "shiverwolf", "Dying Forest and Bitterwood Forest", "Frozen Ice Ball which is obtained from an $(l:jitl:frozen/traders#eskimo)Eskimo$(/l))", MobStats.TAMED_SHIVERWOLF_HEALTH, MobStats.SHIVERWOLF_HEALTH, MobStats.SHIVERWOLF_DAMAGE, "");

        addMob(EnumKnowledge.EUCA, "dynaster", "Euca", true, MobStats.DYNASTER_HEALTH, MobStats.DYNASTER_DAMAGE, "Euca Meat, Royal Disk and Shimmerer Dust");
        addMob(EnumKnowledge.EUCA, "euca_charger", "Euca", "Very quick", false , MobStats.EUCA_CHARGER_HEALTH, MobStats.EUCA_CHARGER_DAMAGE, "Euca Meat, Gate Keys and Shimmerer Dust");
        addStructureMob(EnumKnowledge.EUCA, "goldbot", "$(l:jitl:euca/structures#euca_sphere)Euca Sphere$(/l)", false, MobStats.GOLDBOT_HEALTH, MobStats.GOLDBOT_DAMAGE, "Gate Keys and Metal Disk");
        addMob(EnumKnowledge.EUCA, "golder", "Euca", false, MobStats.GOLDER_HEALTH, MobStats.GOLDER_DAMAGE, "Euca Meat and Golder Dust");
        addMob(EnumKnowledge.EUCA, "shimmerer", "Euca", "Fly's around", MobStats.SHIMMERER_HEALTH, "Gate Keys, Royal Disc and Shimmerer Dust");
        addPet(EnumKnowledge.EUCA, "euca_hopper", "Goldite Grains", "Euca Meat", MobStats.TAMED_EUCA_HOPPER_HEALTH, MobStats.EUCA_HOPPER_HEALTH, (float)MobStats.EUCA_HOPPER_DAMAGE, "");

        addMob(EnumKnowledge.DEPTHS, "darkener", "Depths", "Fly's around", MobStats.DARKENER_HEALTH, 0, "Dark Crystal");
        addPet(EnumKnowledge.DEPTHS, "darkness_crawler", "Depths", "Beastly Stomach", MobStats.TAME_DARKNESS_CRAWLER_HEALTH, MobStats.DARKNESS_CRAWLER_HEALTH, (float)MobStats.DARKNESS_CRAWLER_DAMAGE, "Scale");
        addStructureMob(EnumKnowledge.DEPTHS, "dark_sorcerer", "$(l:jitl:depths/structures#dark_sorcerers_dungeon)Dark Sorcerers Dungeon$(/l)", false, MobStats.DARK_SORCERER_HEALTH, MobStats.DARK_SORCERER_DAMAGE, "Dark Orb");
        addMob(EnumKnowledge.DEPTHS, "depths_beast", "Depths", false, MobStats.DEPTHS_BEAST_HEALTH, MobStats.DEPTHS_BEAST_DAMAGE, "Beastly Stomach");
        addMob(EnumKnowledge.DEPTHS, "depths_hunter", "Depths", false, MobStats.DEPTHS_HUNTER_HEALTH, MobStats.DEPTHS_HUNTER_DAMAGE, "Depths Meat and Dark Crystal");
        addPet(EnumKnowledge.DEPTHS, "roc", "Depths", "Depths Meat", MobStats.TAMED_ROC_HEALTH, MobStats.ROC_HEALTH, MobStats.ROC_DAMAGE, "Roc Feather");
        addMob(EnumKnowledge.DEPTHS, "spiked_beast", "Depths", false, MobStats.SPIKED_BEAST_HEALTH, MobStats.SPIKED_BEAST_DAMAGE, "Beastly Stomach");

        addMob(EnumKnowledge.CORBA, "corbanian_mollusk", "Corba", "A Slow slug that leaves a trail of Slime behind", MobStats.CORBANIAN_MOLLUSK_HEALTH, "Slimy Flesh and Slug Slime");
        addMob(EnumKnowledge.CORBA, "leaf_blower", "Corba", false, MobStats.LEAF_BLOWER_HEALTH, MobStats.LEAF_BLOWER_DAMAGE, "Stick, Enchanted Leaf and Nature Tablet");
        addMob(EnumKnowledge.CORBA, "nature_mage", "Tainted Swamp", FIREBALLS, false, MobStats.NATURE_MAGE_HEALTH, "Enchanted Leaf");
        addStructureMob(EnumKnowledge.CORBA, "overseer", "$(l:jitl:corba/structures#seer_tree)Overseer Tree$(/l)", FLYING_FIREBALLS, false, MobStats.OVERSEER_HEALTH, 0, "Collector Rock, Sentry Stone and Over Seeing Eye");
        addStructureMob(EnumKnowledge.CORBA, "overseer_elder", "$(l:jitl:corba/structures#seer_tree)Overseer Tree$(/l) up the very top", FLYING_FIREBALLS, false, MobStats.OVERSEER_ELDER_HEALTH, 0, "Collector Rock, Over Seeing Tablet, Sentry Stone and Over Seeing Eye");
        addMob(EnumKnowledge.CORBA, "smelly", "Corba", false, MobStats.SMELLY_HEALTH, MobStats.SMELLY_DAMAGE, "");
        addMob(EnumKnowledge.CORBA, "stinky", "Tainted Swamp", false, MobStats.STINKY_HEALTH, MobStats.STINKY_DAMAGE, "");
        addMob(EnumKnowledge.CORBA, "surface_seer", "Tainted Swamp", FLYING_FIREBALLS, false, MobStats.SURFACE_SEER_HEALTH, 0, "Nature Tablet, Sentry Stone and Over Seeing Eye");
        addMob(EnumKnowledge.CORBA, "tree_golem", "Tainted Forest", "When it's angry it has a hard hit", true, MobStats.TREE_GOLEM_HEALTH, MobStats.TREE_GOLEM_ATTACK, "Sticks, Enchanted Leaf, Overgrown Nature Ball and Nature Tablet");
        addMob(EnumKnowledge.CORBA, "wood_creature", "Corba", true, MobStats.WOOD_CREATURE_HEALTH, MobStats.WOOD_CREATURE_DAMAGE, "Sticks and Enchanted Leaf");
        addMob(EnumKnowledge.CORBA, "swamp_fly", "Tainted Swamp", "Can be caught in a Glass Bottle", MobStats.SWAMP_FLY_HEALTH, "");

        addMob(EnumKnowledge.TERRANIA, "arana_king", "Terrania", false, MobStats.ARANA_KING_HEALTH, MobStats.ARANA_KING_DAMAGE, "Terrashroom");
        addMob(EnumKnowledge.TERRANIA, "flungas", "Mushroom Forest", "Turns a Glass Bottle into a Bile Vile", MobStats.FLUNGUS_HEALTH, "Breathing Fungus");
        addMob(EnumKnowledge.TERRANIA, "purplian", "Terrania", FIREBALLS, false, MobStats.PURPLIAN_HEALTH, 0, "Purple Powder and Terrastar");
        addMob(EnumKnowledge.TERRANIA, "terragrow", "Terrania", true, MobStats.TERRAGROW_HEALTH, MobStats.TERRAGROW_DAMAGE, "Light Terranian Soil and Earthen Crystal");
        addMob(EnumKnowledge.TERRANIA, "terrascatterer", "Terrania", false, MobStats.TERRA_SCATTERRER_HEALTH, MobStats.TERRA_SCATTERRER_DAMAGE, "Dark Terranian Soil and Earthen Crystal");
        addMob(EnumKnowledge.TERRANIA, "terrashroom", "Mushroom Forest", false, MobStats.TERRASHROOM_HEALTH, MobStats.TERRASHROOM_DAMAGE, "Terrashroom");
        addMob(EnumKnowledge.TERRANIA, "terraslug", "Terrania", false, MobStats.TERRASLUG_HEALTH, MobStats.TERRASLUG_DAMAGE, "Slug Slime");

        addMob(EnumKnowledge.CLOUDIA, "aero_lotus", "Cloudia", MobStats.AERO_LOTUS_HEALTH, "Fluffy Feather");
        addMob(EnumKnowledge.CLOUDIA, "cloud_ghost", "Cloudia", false, MobStats.CLOUD_GHOST_HEALTH, MobStats.CLOUD_GHOST_DAMAGE, "Fluffy Feather");
        addMob(EnumKnowledge.CLOUDIA, "sky_eel", "Cloudia", "Fly's around", false, MobStats.SKY_EEL_HEALTH, 0, "Fluffy Feather");
        addMob(EnumKnowledge.CLOUDIA, "starlight_golem", "Cloudia", true, MobStats.STARLIGHT_GOLEM_HEALTH, MobStats.STARLIGHT_GOLEM_DAMAGE, "Golem Chunk");
        addMob(EnumKnowledge.CLOUDIA, "starlight_transporter", "Cloudia", false, MobStats.STARLIGHT_TRANSPORTER_HEALTH, MobStats.STARLIGHT_TRANSPORTER_DAMAGE, "Golem Chunk");
        addMob(EnumKnowledge.CLOUDIA, "starlight_walker", "Cloudia", false, MobStats.STARLIGHT_WALKER_HEALTH, MobStats.STARLIGHT_WALKER_DAMAGE, "Golem Chunk");

        addMob(EnumKnowledge.SENTERIAN, "sentry_lord", "Senterian labyrinth", false, MobStats.SENTRY_LORD_HEALTH, MobStats.SENTRY_LORD_DAMAGE, "Sentry Observer which is used in $(l:jitl:senterian/structures#senterian_altar)Senterian Altar$(/l)");
        addMob(EnumKnowledge.SENTERIAN, "sentry_stalker", "Senterian labyrinth", false, MobStats.SENTRY_STALKER_HEALTH, MobStats.SENTRY_STALKER_DAMAGE, "Sentry Observer which is used in $(l:jitl:senterian/structures#senterian_altar)Senterian Altar$(/l)");
        addMob(EnumKnowledge.SENTERIAN, "sentry_walker", "Senterian labyrinth", false, MobStats.SENTRY_WALKER_HEALTH, MobStats.SENTRY_WALKER_DAMAGE, "Sentry Observer which is used in $(l:jitl:senterian/structures#senterian_altar)Senterian Altar$(/l)");

        addStructureMob(EnumKnowledge.SENTERIAN, "mini_sentry_walker", "$(l:jitl:senterian/structures#senterian_altar)Senterian Altar$(/l)", false, MobStats.MINI_SENTRY_WALKER_HEALTH, MobStats.MINI_SENTRY_WALKER_DAMAGE, "Sentacoins");
        addStructureMob(EnumKnowledge.SENTERIAN, "mini_sentry_stalker", "$(l:jitl:senterian/structures#senterian_altar)Senterian Altar$(/l)", false, MobStats.MINI_SENTRY_STALKER_HEALTH, MobStats.MINI_SENTRY_STALKER_DAMAGE, "Sentacoins");
        addStructureMob(EnumKnowledge.SENTERIAN, "mini_sentry_lord", "$(l:jitl:senterian/structures#senterian_altar)Senterian Altar$(/l)", false, MobStats.MINI_SENTRY_LORD_HEALTH, MobStats.MINI_SENTRY_LORD_DAMAGE, "Sentacoins");

        writeToFile("\"book.jitl.entry.portals.flame_coin\" : " + "\"The Flame Coin is what is used to light all the JITL Portals\",");
        writeToFile("\"book.jitl.entries.dimensions\" : " + "\"Dimensions\",");
        writeToFile("\"book.jitl.entry.portals.start\" : " + "\"What to do and how to get into Dimensions\",");
        writeToFile("\"book.jitl.category.dimensions.desc\" : " + "\"A rundown of how to get access to all Portals\",");

        writeToFile("\"jitl.trader.boil_trader1\" : \"Boil Trader: It's not often that newcomers arrive here. Can you stand the heat?\",");
        writeToFile("\"jitl.trader.boil_trader2\" : \"Boil Trader: I'm an unusual collector, but I have the best deals of any realm!\",");
        writeToFile("\"jitl.trader.boil_trader3\" : \"Boil Trader: I can see that you're having a tough time getting around. An armor upgrade, perhaps?\",");

        writeToFile("\"jitl.trader.escaped_convict1\" : \"Escaped Convict: I'm an innocent man! I have no reason to be in this realm!\",");
        writeToFile("\"jitl.trader.escaped_convict2\" : \"Escaped Convict: Don't be threatened by me, strange thing!\",");
        writeToFile("\"jitl.trader.escaped_convict3\" : \"Escaped Convict: I managed to find some great loot on my way here. If you can help me out of here, I'll give back in return.\",");

        writeToFile("\"jitl.trader.staring_guardian1\" : \"Guardian: It sure is dark down here... I've always speculated about what's on the surface.\",");
        writeToFile("\"jitl.trader.staring_guardian2\" : \"Guardian: You don't look like any normal creature I've seen here before. We don't get a lot of travelers.\",");
        writeToFile("\"jitl.trader.staring_guardian3\" : \"Guardian: It's always too dark here to see. Hopefully, there'll be a journey to the light as some wish.\",");

        writeToFile("\"jitl.trader.alloy_mender1\" : \"Alloy Mender: It takes over a thousand degreese to melt this gold!\",");
        writeToFile("\"jitl.trader.alloy_mender2\" : \"Alloy Mender: My weapons have been melted and shaped into deadly perfection!\",");
        writeToFile("\"jitl.trader.alloy_mender3\" : \"Alloy Mender: It's a real struggle living here, when everything wants to kill you...\",");
        writeToFile("\"jitl.trader.alloy_mender4\" : \"Alloy Mender: Did they steal my other things at the village?\",");

        writeToFile("\"jitl.trader.crypian1\" : \"Crypian: Yeah I took some of it\",");
        writeToFile("\"jitl.trader.crypian2\" : \"Crypian: IT'S MINE.... Unless you want it?\",");
        writeToFile("\"jitl.trader.crypian3\" : \"Crypian: I might go see where he has moved to....\",");

        writeToFile("\"jitl.npc.crypian_alloy1\" : \"Crypian: Alloy Mender used to live here, but he moved into the royal lands\",");
        writeToFile("\"jitl.npc.crypian_alloy2\" : \"Crypian: Everyone has raided his house\",");
        writeToFile("\"jitl.npc.crypian_alloy3\" : \"Crypian: I'm sure you could possibly trade with some of them\",");

        writeToFile("\"jitl.npc.crypian1\" : \"Crypian: I missed out on the Alloy Menders gear\",");
        writeToFile("\"jitl.npc.crypian2\" : \"Crypian: A few others have some of the Alloy Menders things\",");

        writeToFile("\"jitl.trader.eskimo1\" : \"Eskimo: Did you know its not as cold as it has been?\",");
        writeToFile("\"jitl.trader.eskimo2\" : \"Eskimo: Storms have been raging since the shrine showed up...\",");
        writeToFile("\"jitl.trader.eskimo3\" : \"Eskimo: Please sort out the shrine... It's Freezing!\",");

        writeToFile("\"jitl.npc.frozen_guardian1\" : \"Frozen Guardian: Warmth...\",");
        writeToFile("\"jitl.npc.frozen_guardian2\" : \"Frozen Guardian: ...\",");

        writeToFile("\"jitl.trader.mage1\" : \"Mage: I have some great deals!\",");
        writeToFile("\"jitl.trader.mage2\" : \"Mage: I've got some nice valuables!\",");
        writeToFile("\"jitl.trader.mage3\" : \"Mage: Greetings, weary traveller!\",");

        writeToFile("\"jitl.trader.rockite_golem\" : \"Rockite Golem: ...\",");

        writeToFile("\"jitl.tile.summon_table\" : \"Summoning Table\",");

        writeToFile("\"jitl.tooltip.freeze\" : \"On hit: Freezes the target for 6 seconds\",");
        writeToFile("\"jitl.tooltip.poison\" : \"On hit: Poisions for 6 seconds\",");
        writeToFile("\"jitl.tooltip.fire\" : \"On hit: Burns for 10 seconds\",");
        writeToFile("\"jitl.tooltip.fire_health1\" : \"On hit: Sets enemies ablaze and heals player heart(s)\",");//"On hit: Sets enemies ablaze and heals player \" + health / 2 + \" heart(s)\",");
        writeToFile("\"jitl.tooltip.fire_health2\" : \"Drawback: slows the user on hit\",");
        writeToFile("\"jitl.tooltip.fire_health3\" : \"Random chance to steal 5 full hearts from the user on hit\",");
        writeToFile("\"jitl.tooltip.fire_wither\" : \"On hit: Withers and sets enemies ablaze\",");
        writeToFile("\"jitl.tooltip.health1\" : \"On hit: Sets enemies ablaze and heals player heart(s)\",");//\"On hit: Heals player \" + health / 2 + \" heart(s)\",");
        writeToFile("\"jitl.tooltip.health2\" : \"Drawback: Random chance to slow and blind the user on hit\",");
        writeToFile("\"jitl.tooltip.health3\" : \"Random chance to steal 2 full hearts from the user on hit\",");
        writeToFile("\"jitl.tooltip.night_health\" : \"On hit: Poisons and Withers enemies\",");
        writeToFile("\"jitl.tooltip.poison_health1\" : \"On hit: Sets enemies ablaze and heals player heart(s)\",");//"On hit: Poisons enemies and heals player \" + health / 2 + \" heart(s)\",");
        writeToFile("\"jitl.tooltip.poison_health2\" : \"Drawback: Random chance to poison the user on hit\",");
        writeToFile("\"jitl.tooltip.regen\" : \"On hit: Grants player regeneration\",");
        writeToFile("\"jitl.tooltip.stun\" : \"On hit: Harms and stuns enemies\",");
        writeToFile("\"jitl.tooltip.stun_wither\" : \"On hit: Withers and stuns enemies\",");
        writeToFile("\"jitl.tooltip.wither\" : \"On hit: Withers enemies\",");
        writeToFile("\"jitl.tooltip.night\" : \"On hit: Grants the player Night vision\",");
        writeToFile("\"jitl.tooltip.bubble\" : \"On hit: Poisons enemies and grants player damage boost\",");
        writeToFile("\"jitl.tooltip.logger\" : \"On hit: Stuns enemies and grants player damage boost\",");
        writeToFile("\"jitl.tooltip.lunium_gear.0\" : \"Auto-repair\",");
        writeToFile("\"jitl.tooltip.lunium_gear.1\" : \"Repair speed relies on light level\",");
        writeToFile("\"jitl.tooltip.lunium_gear.2\" : \"%1$s%% charge\",");
        writeToFile("\"jitl.tooltip.shadium_sword.0\" : \"Deals extra damage while in darkness\",");
        writeToFile("\"jitl.tooltip.shadium_sword.1\" : \"Maximum +4 damage\",");
        writeToFile("\"jitl.tooltip.shadium_sword.2\" : \"+%s damage\",");
        writeToFile("\"jitl.tooltip.shadium_tool.0\" : \"Mines faster while in darkness\",");
        writeToFile("\"jitl.tooltip.shadium_tool.1\" : \"Maximum +50% mining speed\",");
        writeToFile("\"jitl.tooltip.shadium_tool.2\" : \"+%s mining speed\",");
        writeToFile("\"jitl.tooltip.shadium_armor.0\" : \"Provides Armor Toughness in darkness\",");
        writeToFile("\"jitl.tooltip.shadium_armor.1\" : \"Maximum +3.75 Armor Toughness per piece\",");
        writeToFile("\"jitl.tooltip.shadium_armor.2\" : \"+%s Armor Toughness\",");
        writeToFile("\"jitl.tooltip.bloodcrust_sword.0\" : \"Absorbs target's flames to empower your next strike\",");
        writeToFile("\"jitl.tooltip.bloodcrust_sword.1\" : \"Damage gained is equal to what the target would have taken from being on fire\",");
        writeToFile("\"jitl.tooltip.bloodcrust_sword.2\" : \"+%s damage\",");
        writeToFile("\"jitl.tooltip.bloodcrust_tool.0\" : \"Absorb fire to gain triple mining speed\",");
        writeToFile("\"jitl.tooltip.bloodcrust_tool.1\" : \"Every block broken removes 1/16th of the boost\",");
        writeToFile("\"jitl.tooltip.bloodcrust_tool.2\" : \"+%1$s%% mining speed\",");
        writeToFile("\"jitl.tooltip.bloodcrust_armor.0\" : \"Full set lights attackers on fire\",");
        writeToFile("\"jitl.tooltip.bloodcrust_armor.1\" : \"Fire duration stacks with existing flames to a maximum of 30 seconds\",");
        writeToFile("\"jitl.tooltip.bloodcrust_armor.2\" : \"Every life lost adds an extra second\",");
        writeToFile("\"jitl.tooltip.mekyum_sword.0\" : \"Deals magic damage\",");
        writeToFile("\"jitl.tooltip.korite_sword.0\" : \"Right-click to consume Essence and charge your next attack\",");
        writeToFile("\"jitl.tooltip.korite_sword.1\" : \"Maximum +5 damage\",");
        writeToFile("\"jitl.tooltip.korite_sword.2\" : \"+%s damage\",");
        writeToFile("\"jitl.tooltip.celestium_armor.0\" : \"Jumping in the air allows you to dash\",");
        writeToFile("\"jitl.tooltip.celestium_armor.1\" : \"Dashing cancels your vertical movement and increases your horizontal movement\",");
        writeToFile("\"jitl.tooltip.celestium_armor.2\" : \"To recharge this ability, touch the ground at least 2 seconds after previous dash\",");

        writeToFile("\"jitl.tooltip.uses_remaining\" : \"Uses Remaining\",");
        writeToFile("\"jitl.tooltip.okoloo\" : \"Placed in Okoloo Pedestal\",");
        writeToFile("\"jitl.boss.spawn\" : \"Used to spawn the boss %s\",");
        writeToFile("\"jitl.pet.spawn\" : \"Used to spawn a pet %s\",");

        writeToFile("\"enchantment.jitl.lightweight\" : \"Lightweight\",");
        writeToFile("\"enchantment.jitl.razor\" : \"Razor Sharp\",");
        writeToFile("\"enchantment.jitl.ambit\" : \"Ambit\",");
        writeToFile("\"enchantment.jitl.scorching\" : \"Scorching\",");
        writeToFile("\"enchantment.jitl.faithful\" : \"Faithful\",");

        writeToFile("\"jitl.item.desc.flame_coin\" : \"Used to Light JITL Portals\",");
        writeToFile("\"jitl.item.desc.padlock\" : \"Used to lock JITL chests\",");
        writeToFile("\"jitl.item.desc.master_key\" : \"Opens all locked JITL chests\",");
        writeToFile("\"jitl.item.desc.journey_key\" : \"Opens Locked Journey Chest\",");
        writeToFile("\"jitl.item.desc.nether_key\" : \"Opens Locked Nether Chest\",");
        writeToFile("\"jitl.item.desc.frozen_key\" : \"Opens Locked Frozen Chest\",");
        writeToFile("\"jitl.item.desc.euca_key\" : \"Opens Locked Euca Chest\",");
        writeToFile("\"jitl.item.desc.boiling_key\" : \"Opens Locked Boil Chest\",");
        writeToFile("\"jitl.item.desc.depths_key\" : \"Opens Locked Depths Chest\",");
        writeToFile("\"jitl.item.desc.corba_key\" : \"Opens Locked Corba Chest\",");
        writeToFile("\"jitl.item.desc.terranian_key\" : \"Opens Locked Terranian Chest\",");
        writeToFile("\"jitl.item.desc.cloudia_key\" : \"Opens Locked Cloudia Chest\",");
        writeToFile("\"jitl.item.desc.senterian_key\" : \"Opens Locked Senterian Chest\",");
        writeToFile("\"jitl.tooltip.eye_of_the_blizzard.0\" : \"Used to see in the Frozen Storm\",");

        writeToFile("\"jitl.item.desc.sentry_eye\" : \"Inserted into the Senterian Portal Frame\",");
        writeToFile("\"jitl.item.desc.ancient_socket\" : \"Inserted into a Ancient Socket\",");
        writeToFile("\"jitl.item.desc.ancient_catalyst\" : \"Used to open the Ancient Catalyst\",");
        writeToFile("\"jitl.item.desc.night\" : \"When eaten: Grants Night Vision\",");
        writeToFile("\"jitl.item.desc.sat\" : \"When eaten: Grants Saturation\",");
        writeToFile("\"jitl.item.desc.water\" : \"When eaten: Grants Water Breathing\",");
        writeToFile("\"jitl.item.desc.dam\" : \"When eaten: Grants Damage Boost\",");
        writeToFile("\"jitl.item.desc.abs\" : \"When eaten: Grants Absorption\",");
        writeToFile("\"jitl.item.desc.fire\" : \"When eaten: Grants Fire Resistance\",");
        writeToFile("\"jitl.item.desc.mine\" : \"When eaten: Increased Mining Speed\",");
        writeToFile("\"jitl.item.desc.regen\" : \"When eaten: Grants Regeneration\",");
        writeToFile("\"jitl.item.desc.jump\" : \"When eaten: Grants Jump Boost\",");
        writeToFile("\"jitl.item.desc.speed\" : \"When eaten: Grants Speed Boost\",");
        writeToFile("\"jitl.item.desc.iridium\" : \"Radioactive\",");
        writeToFile("\"jitl.item.desc.demonic_eye\" : \"Inserted into the Corba Portal Frame\",");
        writeToFile("\"jitl.item.desc.dark_gem\" : \"Inserted into the Depths Portal Frame\",");
        writeToFile("\"jitl.item.desc.sentry_observer\" : \"Inserted into the Senterian Altar\",");
        writeToFile("\"jitl.item.desc.bile\" : \"A very Bile Vile!\",");

        writeToFile("\"jitl.message.no_internet\" : \"Update checker failed, Please check your internet connection.\",");
        writeToFile("\"jitl.message.thank_you\" : \"Thank you %s, for downloading and playing!\",");
        writeToFile("\"jitl.message.update_available\" : \"Update available! Latest version: %s\",");
        writeToFile("\"jitl.message.current_version\" : \"Current Version: %s\",");
        writeToFile("\"jitl.message.up_to_date\" : \"Your version is up to date. Enjoy!\",");

        writeToFile("\"jitl.message.item.miners_pearl\" : \"The pearl doesn't feel safe enough to teleport.\",");
        writeToFile("\"jitl.message.item.eternal_night\" : \"%s isn't scared of the dark...\",");
        writeToFile("\"jitl.item.desc.full_health\" : \"Restores player to full health\",");
        writeToFile("\"jitl.item.desc.health\" : \"Restores player to full health\",");

        writeToFile("\"jitl.boss_spawn.fail\" : \"%s cannot be called upon unless in the %s dimension.\",");
        writeToFile("\"jitl.boss_spawn.okoloo\" : \"Time to get Clubbing\",");
        writeToFile("\"jitl.boss_spawn.withering_beast\" : \"You can feel the Withering around you\",");
        writeToFile("\"jitl.boss_spawn.calcia\" : \"Hide and seek?\",");
        writeToFile("\"jitl.boss_spawn.soul_watcher\" : \"Your soul shouldn't be watched!\",");
        writeToFile("\"jitl.boss_spawn.blazier\" : \"You're playing with the inferno. It's not too late to turn back...\",");
        writeToFile("\"jitl.boss_spawn.eudor\" : \"Bow down The King of this land\",");
        writeToFile("\"jitl.boss_spawn.corallator\" : \"You will regret this mistake for the rest of your life - if you'll still have one, that is...\",");
        writeToFile("\"jitl.boss_spawn.thunderbird\" : \"The thunderbird is not pleased with its awakening...\",");
        writeToFile("\"jitl.boss_spawn.scale\" : \"The blue blubby fish monster has been summoned!\",");
        writeToFile("\"jitl.boss_spawn.logger\" : \"You'll get chopped to pieces with this one...\",");
        writeToFile("\"jitl.boss_spawn.sentry_king\" : \"It sucked all the energy out of this world, don't let it suck the energy out of you...\",");
        writeToFile("\"jitl.boss_spawn.terranian_protector\" : \"It's sole purpose was to protect this land. Why would you try to destroy it?\",");
        writeToFile("\"jitl.boss_spawn.sky_stalker\" : \"Watch the Sky's...\",");


        /* --------------------- Finish manual lines --------------------- */

        for(int i = 0; i < mobList.size(); i++)
            writeToFile("\"entity.jitl." + mobList.get(i) + "\": \"" + JEntities.entityLangName.get(i) + "\"" + ",");

        for(int i = 0; i < recordDescList.size(); i++)
            writeToFile("\"" + recordDescList.get(i) + "\": \"" + JItems.recordDescLangName.get(i) + "\",");

        for(int i = 0; i < blockList.size(); i++)
            writeToFile("\"block.jitl." + blockList.get(i) + "\": \"" + JBlocks.normalLangName.get(i) + "\"" + ",");

        for(int i = 0; i < doublePlantList.size(); i++)
            writeToFile("\"block.jitl." + doublePlantList.get(i) + "\": \"" + JBlocks.doublePlantLangName.get(i) + "\"" + ",");

        for(int i = 0; i < logList.size(); i++)
            writeToFile("\"block.jitl." + logList.get(i) + "\": \"" + JBlocks.logLangName.get(i) + "\"" + ",");

        for(int i = 0; i < crossList.size(); i++)
            writeToFile("\"block.jitl." + crossList.get(i) + "\": \"" + JBlocks.crossLangName.get(i) + "\"" + ",");

        for(int i = 0; i < lilyList.size(); i++)
            writeToFile("\"block.jitl." + lilyList.get(i) + "\": \"" + JBlocks.lilyPadLangName.get(i) + "\"" + ",");

        for(int i = 0; i < tintedCrossList.size(); i++)
            writeToFile("\"block.jitl." + tintedCrossList.get(i) + "\": \"" + JBlocks.tintedCrossLangName.get(i) + "\"" + ",");

        for(int i = 0; i < tintedLeavesList.size(); i++)
            writeToFile("\"block.jitl." + tintedLeavesList.get(i) + "\": \"" + JBlocks.tintedLeavesLangName.get(i) + "\"" + ",");

        for(int i = 0; i < grassBlockList.size(); i++)
            writeToFile("\"block.jitl." + grassBlockList.get(i) + "\": \"" + JBlocks.grassLangName.get(i) + "\"" + ",");

        for(int i = 0; i < chestBlockList.size(); i++)
            writeToFile("\"block.jitl." + chestBlockList.get(i) + "\": \"" + JBlocks.chestLangName.get(i) + "\"" + ",");

        for(int i = 0; i < wallBlockList.size(); i++)
            writeToFile("\"block.jitl." + wallBlockList.get(i) + "\": \"" + JBlocks.wallLangName.get(i) + "\"" + ",");

        for(int i = 0; i < vineBlockList.size(); i++)
            writeToFile("\"block.jitl." + vineBlockList.get(i) + "\": \"" + JBlocks.vineLangName.get(i) + "\"" + ",");

        for(int i = 0; i < ladderBlockList.size(); i++)
            writeToFile("\"block.jitl." + ladderBlockList.get(i) + "\": \"" + JBlocks.ladderLangName.get(i) + "\"" + ",");

        for(int i = 0; i < overlayGrassBlockList.size(); i++)
            writeToFile("\"block.jitl." + overlayGrassBlockList.get(i) + "\": \"" + JBlocks.overlayGrassLangName.get(i) + "\"" + ",");

        for(int i = 0; i < paneList.size(); i++)
            writeToFile("\"block.jitl." + paneList.get(i) + "\": \"" + JBlocks.paneLangName.get(i) + "\"" + ",");

        for(int i = 0; i < totemBlockList.size(); i++)
            writeToFile("\"block.jitl." + totemBlockList.get(i) + "\": \"" + JBlocks.totemLangName.get(i) + "\"" + ",");

        for(int i = 0; i < terrainBlockList.size(); i++)
            writeToFile("\"block.jitl." + terrainBlockList.get(i) + "\": \"" + JBlocks.terrainLangName.get(i) + "\"" + ",");

        for(int i = 0; i < attachedCrossList.size(); i++)
            writeToFile("\"block.jitl." + attachedCrossList.get(i) + "\": \"" + JBlocks.attachedCrossLangName.get(i) + "\"" + ",");

        for(int i = 0; i < doorList.size(); i++)
            writeToFile("\"block.jitl." + doorList.get(i) + "\": \"" + JBlocks.doorLangName.get(i) + "\"" + ",");

        for(int i = 0; i < trapDoorList.size(); i++)
            writeToFile("\"block.jitl." + trapDoorList.get(i) + "\": \"" + JBlocks.trapDoorLangName.get(i) + "\"" + ",");

        for(int i = 0; i < stairList.size(); i++)
            writeToFile("\"block.jitl." + stairList.get(i) + "\": \"" + JBlocks.stairsLangName.get(i) + "\"" + ",");

        for(int i = 0; i < slabList.size(); i++)
            writeToFile("\"block.jitl." + slabList.get(i) + "\": \"" + JBlocks.slabLangName.get(i) + "\"" + ",");

        for(int i = 0; i < buttonList.size(); i++)
            writeToFile("\"block.jitl." + buttonList.get(i) + "\": \"" + JBlocks.buttonLangName.get(i) + "\"" + ",");

        for(int i = 0; i < pressureList.size(); i++)
            writeToFile("\"block.jitl." + pressureList.get(i) + "\": \"" + JBlocks.pressurePlateLangName.get(i) + "\"" + ",");

        for(int i = 0; i < fenceList.size(); i++)
            writeToFile("\"block.jitl." + fenceList.get(i) + "\": \"" + JBlocks.fenceLangName.get(i) + "\"" + ",");

        for(int i = 0; i < gateList.size(); i++)
            writeToFile("\"block.jitl." + gateList.get(i) + "\": \"" + JBlocks.gateLangName.get(i) + "\"" + ",");

        for(int i = 0; i < modelBlockList.size(); i++)
            writeToFile("\"block.jitl." + modelBlockList.get(i) + "\": \"" + JBlocks.modelLangName.get(i) + "\"" + ",");

        for(int i = 0; i < rotatableBlockList.size(); i++)
            writeToFile("\"block.jitl." + rotatableBlockList.get(i) + "\": \"" + JBlocks.rotatableLangName.get(i) + "\"" + ",");

        for(int i = 0; i < randomizedBlockList.size(); i++)
            writeToFile("\"block.jitl." + randomizedBlockList.get(i) + "\": \"" + JBlocks.randomLangName.get(i) + "\"" + ",");

        for(int i = 0; i < mushroomBlockList.size(); i++)
            writeToFile("\"block.jitl." + mushroomBlockList.get(i) + "\": \"" + JBlocks.mushroomLangName.get(i) + "\"" + ",");

        for(int i = 0; i < basePortalBlockList.size(); i++)
            writeToFile("\"block.jitl." + basePortalBlockList.get(i) + "\": \"" + JBlocks.basePortalLangName.get(i) + "\"" + ",");

        for(int i = 0; i < basePortalFrameBlockList.size(); i++)
            writeToFile("\"block.jitl." + basePortalFrameBlockList.get(i) + "\": \"" + JBlocks.basePortalFrameLangName.get(i) + "\"" + ",");

        for(int i = 0; i < topBottomBlockList.size(); i++)
            writeToFile("\"block.jitl." + topBottomBlockList.get(i) + "\": \"" + JBlocks.topBottomLangName.get(i) + "\"" + ",");

        for(int i = 0; i < portalBlockList.size(); i++)
            writeToFile("\"block.jitl." + portalBlockList.get(i) + "\": \"" + JBlocks.portalLangName.get(i) + "\"" + ",");

        for(int i = 0; i < campfireBlockList.size(); i++)
            writeToFile("\"block.jitl." + campfireBlockList.get(i) + "\": \"" + JBlocks.campfireLangName.get(i) + "\"" + ",");

        for(int i = 0; i < pathBlockList.size(); i++)
            writeToFile("\"block.jitl." + pathBlockList.get(i) + "\": \"" + JBlocks.pathLangName.get(i) + "\"" + ",");

        for(int i = 0; i < farmBlockList.size(); i++)
            writeToFile("\"block.jitl." + farmBlockList.get(i) + "\": \"" + JBlocks.farmlandLangName.get(i) + "\"" + ",");

        for(int i = 0; i < cropBlockList.size(); i++)
            writeToFile("\"block.jitl." + cropBlockList.get(i) + "\": \"" + JBlocks.cropLangName.get(i) + "\"" + ",");

        for(int i = 0; i < bushBlockList.size(); i++)
            writeToFile("\"block.jitl." + bushBlockList.get(i) + "\": \"" + JBlocks.bushLangName.get(i) + "\"" + ",");

        for(int i = 0; i < furnaceBlockList.size(); i++)
            writeToFile("\"block.jitl." + furnaceBlockList.get(i) + "\": \"" + JBlocks.furnaceLangName.get(i) + "\"" + ",");

        for(int i = 0; i < slimeBlockList.size(); i++)
            writeToFile("\"block.jitl." + slimeBlockList.get(i) + "\": \"" + JBlocks.slimeLangName.get(i) + "\"" + ",");

        for(int i = 0; i < trophyBlockList.size(); i++)
            writeToFile("\"block.jitl." + trophyBlockList.get(i) + "\": \"" + JBlocks.trophyLangName.get(i) + "\"" + ",");

        for(int i = 0; i < toolItemList.size(); i++)
            writeToFile("\"item.jitl." + toolItemList.get(i) + "\": \"" + JItems.toolLangName.get(i) + "\"" + ",");

        for(int i = 0; i < bowItemList.size(); i++)
            writeToFile("\"item.jitl." + bowItemList.get(i) + "\": \"" + JItems.bowLangName.get(i) + "\"" + ",");

        for(int i = 0; i < shieldItemList.size(); i++)
            writeToFile("\"item.jitl." + shieldItemList.get(i) + "\": \"" + JItems.shieldLangName.get(i) + "\"" + ",");

        for(int i = 0; i < recordList.size(); i++)
            writeToFile("\"item.jitl." + recordList.get(i) + "\": \"" + JItems.recordLangName.get(i) + "\"" + ",");

        for(int i = 0; i < spawnEggList.size(); i++)
            writeToFile("\"item.jitl." + spawnEggList.get(i) + "\": \"" + JItems.spawnLangName.get(i) + "\"" + ",");

        for(int i = 0; i < modelItemList.size(); i++)
            writeToFile("\"item.jitl." + modelItemList.get(i) + "\": \"" + JItems.modelLangName.get(i) + "\"" + ",");

        int j = 0;
        for(int i = 0; i < itemList.size(); i++) {
            j++;
            String end = j == itemList.size() ? "" : ",";
            writeToFile("\"item.jitl." + itemList.get(i) + "\": \"" + JItems.langName.get(i) + "\"" + end);
        }

        writeToFile("}");

        writerInit();
    }

    public void addMob(EnumKnowledge dim, String name, String biome, double health, String drops) {
        writeToFile("\"book.jitl.entry." + dim.getName().toLowerCase() + ".mobs." + name + ".desc\" : " + "\"Spawns in " + biome + " Biomes, Harmless, " + getHealth(health, 0) + getDrops(drops) + "\",");
    }

    public void addMob(EnumKnowledge dim, String name, String biome, String extra, boolean neutral, double health, double damage, String drops) {
        writeToFile("\"book.jitl.entry." + dim.getName().toLowerCase() + ".mobs." + name + ".desc\" : " + "\"Spawns in " + biome + " Biomes, " + getNeutral(neutral) + ", " + extra + ", " + getHealth(health, damage) + getDrops(drops) + "\",");
    }

    public void addPet(EnumKnowledge dim, String name, String biome, String tameItem, double tamedHealth, double health, double damage, String drops) {
        writeToFile("\"book.jitl.entry." + dim.getName().toLowerCase() + ".mobs." + name + ".desc\" : " + "\"Spawns in " + biome + " Biomes, " + getNeutral(true) + ", " + "Tamable with " + tameItem + ", when tamed it goes to " + (int)tamedHealth + "HP" + ", " + getHealth(health, damage) + getDrops(drops) + "\",");
    }

    public void addPet(EnumKnowledge dim, String name, boolean structure, String obtained, String tameItem, double health, double damage, String drops) {
        String found = "";
        if(structure) {
            found = "\"" + obtained;
        } else {
            found = "\"Spawns in " + obtained + " Biomes";
        }
        writeToFile("\"book.jitl.entry." + dim.getName().toLowerCase() + ".mobs." + name + ".desc\" : " + found + ", " + getNeutral(true) + ", " + "Tamable with " + tameItem + ", " + getHealth(health, damage) + getDrops(drops) + "\",");
    }

    public void addMob(EnumKnowledge dim, String name, String biome, String extra, boolean harmless, double health, String drops) {
        writeToFile("\"book.jitl.entry." + dim.getName().toLowerCase() + ".mobs." + name + ".desc\" : " + "\"Spawns in " + biome + " Biomes" + getHarmless(harmless) + ", " + extra + ", " + getHealth(health, 0) + getDrops(drops) + "\",");
    }

    public void addMob(EnumKnowledge dim, String name, String biome, String extra, double health, String drops) {
        addMob(dim, name, biome, extra, true, health, drops);
    }

    public void addMob(EnumKnowledge dim, String name, String biome, String extra, double health, float damage, String drops) {
        addMob(dim, name, biome, extra, true, health, damage, drops);
    }

    public void addMob(EnumKnowledge dim, String name, String biome, boolean neutral, double health, double damage, String drops) {
        writeToFile("\"book.jitl.entry." + dim.getName().toLowerCase() + ".mobs." + name + ".desc\" : " + "\"Spawns in " + biome + " Biomes, " + getNeutral(neutral) + ", " + getHealth(health, damage) + getDrops(drops) + "\",");
    }

    public void addStructureMob(EnumKnowledge dim, String name, String structure, String extra, boolean neutral, double health, double damage, String drops) {
        writeToFile("\"book.jitl.entry." + dim.getName().toLowerCase() + ".mobs." + name + ".desc\" : " + "\"Spawns in " + structure + ", " + getNeutral(neutral) + ", " + extra + ", " + getHealth(health, damage) + getDrops(drops) + "\",");
    }

    public void addStructureMob(EnumKnowledge dim, String name, String structure, boolean neutral, double health, double damage, String drops) {
        writeToFile("\"book.jitl.entry." + dim.getName().toLowerCase() + ".mobs." + name + ".desc\" : " + "\"Spawns in " + structure + ", " + getNeutral(neutral) + ", " + getHealth(health, damage) + getDrops(drops) + "\",");
    }

    public String getHarmless(boolean harmless) {
        return harmless ? ", Harmless" : "";
    }

    public String getDrops(String drops) {
        return !drops.isEmpty() ? ", Chance to drop " + drops : "";
    }

    public String getNeutral(boolean neutral) {
        return neutral ? "Peaceful unless attacked" : "Will attack on sight";
    }

    public String getHealth(double health, double damage) {
        return damage != 0 ? "It has " + (int)health + "HP and does " + damage / 2 + "x Hearts of Damage" : "It has " + (int)health + "HP";
    }

    public void writerInit() {
        try {
            langWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String text){
        try {
            langWriter.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}