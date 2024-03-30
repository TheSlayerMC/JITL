package net.jitl.core.data;

import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;

import java.io.*;
import java.util.ArrayList;

public class LangRegistry {

    protected BufferedWriter langWriter;

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