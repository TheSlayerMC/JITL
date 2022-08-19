package net.jitl.core.data;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JItems;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LangRegistry {

    protected BufferedWriter langWriter;

    public void generate() {
        String langDir = "../src/main/resources/assets/jitl/lang/en_us.json";
        ArrayList<String> itemList = new ArrayList<>();
        ArrayList<String> blockList = new ArrayList<>();
        File en_us = new File(langDir);

        for(String name : JBlocks.blockName) {
            blockList.add(name);
        }

        for(String name : JItems.toolName) {
            itemList.add(name);
        }

        for(String name : JItems.itemName) {
            itemList.add(name);
        }



        try {
            if(en_us.exists()) en_us.delete();
            en_us.createNewFile();
            langWriter = new BufferedWriter(new FileWriter(en_us));
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeToFile("{");
        writeToFile("\"itemgroup.jitl.blocks\" : \"JITL Blocks\",");
        writeToFile("\"itemgroup.jitl.materials\" : \"JITL Materials\",");
        writeToFile("\"itemgroup.jitl.tools\" : \"JITL Tools\",");
        writeToFile("\"itemgroup.jitl.weapons\" : \"JITL Weapons\",");

        for(int i = 0; i < blockList.size(); i++) {
            writeToFile("\"block.jitl." + blockList.get(i) + "\": \"" + JBlocks.langName.get(i) + "\"" + ",");
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