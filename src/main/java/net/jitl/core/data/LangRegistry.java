package net.jitl.core.data;

import net.jitl.core.init.internal.BlockRegistrys;
import net.jitl.core.init.internal.ItemRegistrys;
import net.jitl.core.init.internal.JItems;

import java.io.*;
import java.util.ArrayList;

public class LangRegistry {

    protected BufferedWriter langWriter;

    public void generate() {
        String langDir = "../src/main/resources/assets/jitl/lang/en_us.json";

        File en_us = new File(langDir);

        ArrayList<String> blockList = new ArrayList<>(BlockRegistrys.normalBlockName);
        ArrayList<String> logList = new ArrayList<>(BlockRegistrys.logBlockName);
        ArrayList<String> crossList = new ArrayList<>(BlockRegistrys.crossBlockName);
        ArrayList<String> doorList = new ArrayList<>(BlockRegistrys.doorBlockName);
        ArrayList<String> trapDoorList = new ArrayList<>(BlockRegistrys.trapDoorBlockName);
        ArrayList<String> stairList = new ArrayList<>(BlockRegistrys.stairsBlockName);
        ArrayList<String> slabList = new ArrayList<>(BlockRegistrys.slabBlockName);
        ArrayList<String> pressureList = new ArrayList<>(BlockRegistrys.pressurePlateBlockName);
        ArrayList<String> buttonList = new ArrayList<>(BlockRegistrys.buttonBlockName);
        ArrayList<String> gateList = new ArrayList<>(BlockRegistrys.gateBlockName);
        ArrayList<String> fenceList = new ArrayList<>(BlockRegistrys.fenceBlockName);

        ArrayList<String> toolItemList = new ArrayList<>(ItemRegistrys.toolName);
        ArrayList<String> itemList = new ArrayList<>(ItemRegistrys.itemName);

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
            writeToFile("\"block.jitl." + blockList.get(i) + "\": \"" + BlockRegistrys.normalLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < logList.size(); i++) {
            writeToFile("\"block.jitl." + logList.get(i) + "\": \"" + BlockRegistrys.logLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < crossList.size(); i++) {
            writeToFile("\"block.jitl." + crossList.get(i) + "\": \"" + BlockRegistrys.crossLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < doorList.size(); i++) {
            writeToFile("\"block.jitl." + doorList.get(i) + "\": \"" + BlockRegistrys.doorLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < trapDoorList.size(); i++) {
            writeToFile("\"block.jitl." + trapDoorList.get(i) + "\": \"" + BlockRegistrys.trapDoorLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < stairList.size(); i++) {
            writeToFile("\"block.jitl." + stairList.get(i) + "\": \"" + BlockRegistrys.stairsLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < slabList.size(); i++) {
            writeToFile("\"block.jitl." + slabList.get(i) + "\": \"" + BlockRegistrys.slabLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < buttonList.size(); i++) {
            writeToFile("\"block.jitl." + buttonList.get(i) + "\": \"" + BlockRegistrys.buttonLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < pressureList.size(); i++) {
            writeToFile("\"block.jitl." + pressureList.get(i) + "\": \"" + BlockRegistrys.pressurePlateLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < fenceList.size(); i++) {
            writeToFile("\"block.jitl." + fenceList.get(i) + "\": \"" + BlockRegistrys.fenceLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < gateList.size(); i++) {
            writeToFile("\"block.jitl." + gateList.get(i) + "\": \"" + BlockRegistrys.gateLangName.get(i) + "\"" + ",");
        }

        for(int i = 0; i < toolItemList.size(); i++) {
            writeToFile("\"item.jitl." + toolItemList.get(i) + "\": \"" + ItemRegistrys.toolLangName.get(i) + "\"" + ",");
        }

        int j = 0;
        for(int i = 0; i < itemList.size(); i++) {
            j++;
            String end = j == itemList.size() ? "" : ",";
            writeToFile("\"item.jitl." + itemList.get(i) + "\": \"" + ItemRegistrys.langName.get(i) + "\"" + end);
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