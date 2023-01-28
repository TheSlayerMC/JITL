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
        ArrayList<String> doublePlantList = new ArrayList<>(JBlocks.doublePlantBlockName);
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

        ArrayList<String> toolItemList = new ArrayList<>(JItems.toolName);
        ArrayList<String> itemList = new ArrayList<>(JItems.itemName);
        ArrayList<String> spawnEggList = new ArrayList<>(JItems.spawnName);
        ArrayList<String> recordList = new ArrayList<>(JItems.recordName);
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

        writeToFile("\"jitl.tooltip.uses_remaining\" : \"Uses Remaining\",");

        /* --------------------- Finish manual lines --------------------- */

        for(int i = 0; i < mobList.size(); i++) {
            writeToFile("\"entity.jitl." + mobList.get(i) + "\": \"" + JEntities.entityLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < recordDescList.size(); i++) {
            writeToFile("\"" + recordDescList.get(i) + "\": \"" + JItems.recordDescLangName.get(i) + "\",");
        }

        for(int i = 0; i < blockList.size(); i++) {
            writeToFile("\"block.jitl." + blockList.get(i) + "\": \"" + JBlocks.normalLangName.get(i) + "\"" + ",");
        }


        for(int i = 0; i < doublePlantList.size(); i++) {
            writeToFile("\"block.jitl." + doublePlantList.get(i) + "\": \"" + JBlocks.doublePlantLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < logList.size(); i++) {
            writeToFile("\"block.jitl." + logList.get(i) + "\": \"" + JBlocks.logLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < crossList.size(); i++) {
            writeToFile("\"block.jitl." + crossList.get(i) + "\": \"" + JBlocks.crossLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < grassBlockList.size(); i++) {
            writeToFile("\"block.jitl." + grassBlockList.get(i) + "\": \"" + JBlocks.grassLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < chestBlockList.size(); i++) {
            writeToFile("\"block.jitl." + chestBlockList.get(i) + "\": \"" + JBlocks.chestLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < vineBlockList.size(); i++) {
            writeToFile("\"block.jitl." + vineBlockList.get(i) + "\": \"" + JBlocks.vineLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < ladderBlockList.size(); i++) {
            writeToFile("\"block.jitl." + ladderBlockList.get(i) + "\": \"" + JBlocks.ladderLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < overlayGrassBlockList.size(); i++) {
            writeToFile("\"block.jitl." + overlayGrassBlockList.get(i) + "\": \"" + JBlocks.overlayGrassLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < paneList.size(); i++) {
            writeToFile("\"block.jitl." + paneList.get(i) + "\": \"" + JBlocks.paneLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < terrainBlockList.size(); i++) {
            writeToFile("\"block.jitl." + terrainBlockList.get(i) + "\": \"" + JBlocks.terrainLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < attachedCrossList.size(); i++) {
            writeToFile("\"block.jitl." + attachedCrossList.get(i) + "\": \"" + JBlocks.attachedCrossLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < doorList.size(); i++) {
            writeToFile("\"block.jitl." + doorList.get(i) + "\": \"" + JBlocks.doorLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < trapDoorList.size(); i++) {
            writeToFile("\"block.jitl." + trapDoorList.get(i) + "\": \"" + JBlocks.trapDoorLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < stairList.size(); i++) {
            writeToFile("\"block.jitl." + stairList.get(i) + "\": \"" + JBlocks.stairsLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < slabList.size(); i++) {
            writeToFile("\"block.jitl." + slabList.get(i) + "\": \"" + JBlocks.slabLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < buttonList.size(); i++) {
            writeToFile("\"block.jitl." + buttonList.get(i) + "\": \"" + JBlocks.buttonLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < pressureList.size(); i++) {
            writeToFile("\"block.jitl." + pressureList.get(i) + "\": \"" + JBlocks.pressurePlateLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < fenceList.size(); i++) {
            writeToFile("\"block.jitl." + fenceList.get(i) + "\": \"" + JBlocks.fenceLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < gateList.size(); i++) {
            writeToFile("\"block.jitl." + gateList.get(i) + "\": \"" + JBlocks.gateLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < modelBlockList.size(); i++) {
            writeToFile("\"block.jitl." + modelBlockList.get(i) + "\": \"" + JBlocks.modelLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < rotatableBlockList.size(); i++) {
            writeToFile("\"block.jitl." + rotatableBlockList.get(i) + "\": \"" + JBlocks.rotatableLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < randomizedBlockList.size(); i++) {
            writeToFile("\"block.jitl." + randomizedBlockList.get(i) + "\": \"" + JBlocks.randomLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < portalBlockList.size(); i++) {
            writeToFile("\"block.jitl." + portalBlockList.get(i) + "\": \"" + JBlocks.portalLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < campfireBlockList.size(); i++) {
            writeToFile("\"block.jitl." + campfireBlockList.get(i) + "\": \"" + JBlocks.campfireLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < pathBlockList.size(); i++) {
            writeToFile("\"block.jitl." + pathBlockList.get(i) + "\": \"" + JBlocks.pathLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < farmBlockList.size(); i++) {
            writeToFile("\"block.jitl." + farmBlockList.get(i) + "\": \"" + JBlocks.farmlandLangName.get(i) + "\"" + ",");
        }


        for(int i = 0; i < cropBlockList.size(); i++) {
            writeToFile("\"block.jitl." + cropBlockList.get(i) + "\": \"" + JBlocks.cropLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < bushBlockList.size(); i++) {
            writeToFile("\"block.jitl." + pathBlockList.get(i) + "\": \"" + JBlocks.bushLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < furnaceBlockList.size(); i++) {
            writeToFile("\"block.jitl." + furnaceBlockList.get(i) + "\": \"" + JBlocks.furnaceLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < toolItemList.size(); i++) {
            writeToFile("\"item.jitl." + toolItemList.get(i) + "\": \"" + JItems.toolLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < recordList.size(); i++) {
            writeToFile("\"item.jitl." + recordList.get(i) + "\": \"" + JItems.recordLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < spawnEggList.size(); i++) {
            writeToFile("\"item.jitl." + spawnEggList.get(i) + "\": \"" + JItems.spawnLangName.get(i) + "\"" + ",");
        }

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