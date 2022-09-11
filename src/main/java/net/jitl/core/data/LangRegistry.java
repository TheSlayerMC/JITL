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
        ArrayList<String> doorList = new ArrayList<>(JBlocks.doorBlockName);
        ArrayList<String> trapDoorList = new ArrayList<>(JBlocks.trapDoorBlockName);
        ArrayList<String> stairList = new ArrayList<>(JBlocks.stairsBlockName);
        ArrayList<String> slabList = new ArrayList<>(JBlocks.slabBlockName);
        ArrayList<String> pressureList = new ArrayList<>(JBlocks.pressurePlateBlockName);
        ArrayList<String> buttonList = new ArrayList<>(JBlocks.buttonBlockName);

        ArrayList<String> toolItemList = new ArrayList<>(JItems.toolName);
        ArrayList<String> itemList = new ArrayList<>(JItems.itemName);

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

        for(int i = 0; i < blockList.size(); i++) {
            writeToFile("\"block.jitl." + blockList.get(i) + "\": \"" + JBlocks.normalLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < logList.size(); i++) {
            writeToFile("\"block.jitl." + logList.get(i) + "\": \"" + JBlocks.logLangName.get(i) + "\"" + ",");
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

        for(int i = 0; i < toolItemList.size(); i++) {
            writeToFile("\"item.jitl." + toolItemList.get(i) + "\": \"" + JItems.toolLangName.get(i) + "\"" + ",");
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