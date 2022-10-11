package net.jitl.core.data;

import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JItems;

import java.io.*;
import java.util.ArrayList;

public class LangRegistry {

    protected BufferedWriter langWriter;

    public void generate() {
        String langDir = "../src/main/resources/assets/jitl/lang/en_us.json";

        File en_us = new File(langDir);

        ArrayList<String> blockList = new ArrayList<>(JBlocks.normalBlockName);
        ArrayList<String> logList = new ArrayList<>(JBlocks.logBlockName);
        ArrayList<String> crossList = new ArrayList<>(JBlocks.crossBlockName);
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
        ArrayList<String> furnaceBlockList = new ArrayList<>(JBlocks.furnaceBlockName);

        ArrayList<String> toolItemList = new ArrayList<>(JItems.toolName);
        ArrayList<String> itemList = new ArrayList<>(JItems.itemName);
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
        writeToFile("\"itemGroup.jitl.materials\" : \"JITL Materials\",");
        writeToFile("\"itemGroup.jitl.tools\" : \"JITL Tools\",");
        writeToFile("\"itemGroup.jitl.weapons\" : \"JITL Weapons\",");
        writeToFile("\"itemGroup.jitl.armor\" : \"JITL Armor\",");
        writeToFile("\"itemGroup.jitl.ranged\" : \"JITL Ranged\",");
        writeToFile("\"itemGroup.jitl.misc\" : \"JITL MISC\",");
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

        /* --------------------- Finish manual lines --------------------- */

        for(int i = 0; i < recordDescList.size(); i++) {
            writeToFile("\"" + recordDescList.get(i) + "\": \"" + JItems.recordDescLangName.get(i) + "\",");
        }

        for(int i = 0; i < blockList.size(); i++) {
            writeToFile("\"block.jitl." + blockList.get(i) + "\": \"" + JBlocks.normalLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < logList.size(); i++) {
            writeToFile("\"block.jitl." + logList.get(i) + "\": \"" + JBlocks.logLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < crossList.size(); i++) {
            writeToFile("\"block.jitl." + crossList.get(i) + "\": \"" + JBlocks.crossLangName.get(i) + "\"" + ",");
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

        for(int i = 0; i < furnaceBlockList.size(); i++) {
            writeToFile("\"block.jitl." + furnaceBlockList.get(i) + "\": \"" + JBlocks.furnaceLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < toolItemList.size(); i++) {
            writeToFile("\"item.jitl." + toolItemList.get(i) + "\": \"" + JItems.toolLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < recordList.size(); i++) {
            writeToFile("\"item.jitl." + recordList.get(i) + "\": \"" + JItems.recordLangName.get(i) + "\"" + ",");
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