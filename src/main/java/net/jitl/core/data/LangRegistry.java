package net.jitl.core.data;

import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;

import java.io.*;
import java.util.ArrayList;

public class LangRegistry {

    protected BufferedWriter langWriter;

    public void generate() {
        String langDir = "../../src/main/resources/assets/jitl/lang/en_us.json";

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
        writeToFile("\"jitl.knowledge.nether\" : \"Nether Knowledge\",");
        writeToFile("\"jitl.knowledge.end\" : \"End Knowledge\",");
        writeToFile("\"jitl.knowledge.euca\" : \"Euca Knowledge\",");
        writeToFile("\"jitl.knowledge.boil\" : \"Boiling Knowledge\",");
        writeToFile("\"jitl.knowledge.frozen\" : \"Frozen Knowledge\",");
        writeToFile("\"death.attack.bradberryBush\" : \"%1$s was poked to death by a Bradberry bush\",");
        writeToFile("\"death.attack.redcurrant\" : \"%1$s was poked to death by a Redcurrant bush\",");
        writeToFile("\"jitl.stats\" : \"Player Stats\",");
        writeToFile("\"jitl.king.hero\" : \"Good Luck... Hero\",");
        writeToFile("\"jitl.sen.knowledge_0\" : \"Ill trade you Overworld knowledge to get in there\",");
        writeToFile("\"jitl.sen.knowledge_1\" : \"See me when you have at least 75 Overworld Knowledge\",");
        writeToFile("\"jitl.sen.unlocked\" : \"I locked it for a reason but goodluck...\",");

        writeToFile("\"curios.identifier.heart_container\" : \"Heart\",");
        writeToFile("\"curios.identifier.catalyst\" : \"Catalyst\",");

        writeToFile("\"book.jitl.landing_text\" : " + "\"Welcome $(playername) to Journey Into The Light, This book is a WIP, any other info needed head over to the $(l:https://discord.com/invite/WhXvz5b)Discord\",");
        writeToFile("\"book.jitl.book_name\" : " + "\"Essentia Bible\",");

        writeToFile("\"book.jitl.category.getting_started\" : " + "\"Getting Started\",");
        writeToFile("\"book.jitl.category.getting_started.desc\" : " + "\"This should give you the tips and hints on getting started.\",");
        writeToFile("\"book.jitl.entries.getting_started.ores\" : " + "\"Ores\",");
        writeToFile("\"book.jitl.category.dimensions\" : " + "\"Dimensions\",");

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
        writeToFile("\"book.jitl.entry.depths.dark_sorcerers_dungeon.desc\" : " + "\"Dark Sorcerers Dungeon spawns $(l:jitl:depths/mobs#dark_sorcerer)Dark Sorcerers$(/l) when inside, it is unlocked with Depths Lock Key obtained from $(l:jitl:depths/traders#staring_guardian)Staring Guardian(/l)\",");

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
        writeToFile("\"book.jitl.entry.corba.sentry_king.desc\" : " + "\"Sentry King is spawned using the Sentry King Orb made inside the Summoning Table, he has 2500 Health, he will throw mud at you that hits hard with 15 Damage, his loot contains Ancient Eye of Opening which is used on the $(l:jitl:overworld/structures#ancient_tower)Ancient Tower$(/l)\",");

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

        writeToFile("\"book.jitl.entry.senterian.mobs.sentry_lord.desc\" : " + "\"Sentry Lord is a mob that spawns inside the labyrinth, may drop a Sentry Observer which is used in $(l:jitl:senterian/structures#senterian_altar)Senterian Altar$(/l)\",");
        writeToFile("\"book.jitl.entry.senterian.mobs.sentry_stalker.desc\" : " + "\"Sentry Stalker is a mob that spawns inside the labyrinth, may drop a Sentry Observer which is used in $(l:jitl:senterian/structures#senterian_altar)Senterian Altar$(/l)\",");
        writeToFile("\"book.jitl.entry.senterian.mobs.sentry_walker.desc\" : " + "\"Sentry Walker is a mob that spawns inside the labyrinth, may drop a Sentry Observer which is used in $(l:jitl:senterian/structures#senterian_altar)Senterian Altar$(/l)\",");

        writeToFile("\"book.jitl.entry.senterian.mobs.mini_sentry_walker.desc\" : " + "\"Mini Sentry Walker is a mob that spawns from inside the Senterian Altar when activated, they will drop Sentacoins\",");
        writeToFile("\"book.jitl.entry.senterian.mobs.mini_sentry_stalker.desc\" : " + "\"Mini Sentry Stalker is a mob that spawns from inside the Senterian Altar when activated, they will drop Sentacoins\",");
        writeToFile("\"book.jitl.entry.senterian.mobs.mini_sentry_lord.desc\" : " + "\"Mini Sentry Lord is a mob that spawns from inside the Senterian Altar when activated, they will drop Sentacoins\",");

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
        writeToFile("\"jitl.tooltip.spawn\" : \"Used to spawn a Boss\",");

        writeToFile("\"enchantment.jitl.lightweight\" : \"Lightweight\",");
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
        writeToFile("\"jitl.item.desc.bile\" : \"Very Vile!\",");

        writeToFile("\"jitl.message.no_internet\" : \"[JITL] Update checker failed, Please check your internet connection.\",");

        writeToFile("\"jitl.message.update_available1\" : \"[JITL] Thank you %s, for downloading and playing!\",");
        writeToFile("\"jitl.message.update_available2\" : \"[JITL] [Version: %s]\",");
        writeToFile("\"jitl.message.update_available3\" : \"[JITL] A update is available.\",");
        writeToFile("\"jitl.message.update_available4\" : \"[JITL] [New Version: %s]\",");

        writeToFile("\"jitl.message.no_update1\" : \"[JITL] Thank you %s, for downloading and playing!\",");
        writeToFile("\"jitl.message.no_update2\" : \"[JITL] [Version: %s]\",");
        writeToFile("\"jitl.message.no_update3\" : \"[JITL] JITL is up to date. Enjoy!\",");

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