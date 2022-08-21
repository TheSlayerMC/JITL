package net.jitl.core.data;

import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JItems;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class LangRegistry {

    protected BufferedWriter langWriter;

    public void generate() {
        String langDir = "../src/main/resources/assets/jitl/lang/en_us.json";

        File en_us = new File(langDir);

        ArrayList<String> blockList = new ArrayList<>(JBlocks.blockName);

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

        /* --------------------- Finish manual lines --------------------- */

        for(int i = 0; i < blockList.size(); i++) {
            writeToFile("\"block.jitl." + blockList.get(i) + "\": \"" + JBlocks.langName.get(i) + "\"" + ",");
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