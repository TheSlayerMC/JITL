package net.jitl.core.data;

import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BlockBreakingGenerator {

    protected BufferedWriter pickaxeWriter, axeWriter, shovelWriter, hoeWriter;

    public void generate() {
        String baseDir = "../../src/main/resources/data/minecraft/tags/block/mineable/";

        File pickaxe = new File(baseDir + "pickaxe.json");
        File axe = new File(baseDir + "axe.json");
        File shovel = new File(baseDir + "shovel.json");
        File hoe = new File(baseDir + "hoe.json");

        ArrayList<String> pickaxeList = new ArrayList<>(JBlocks.PICKAXE_BLOCKS);
        ArrayList<String> axeList = new ArrayList<>(JBlocks.AXE_BLOCKS);
        ArrayList<String> shovelList = new ArrayList<>(JBlocks.SHOVEL_BLOCKS);
        ArrayList<String> hoeList = new ArrayList<>(JBlocks.HOE_BLOCKS);


        try {
            if(pickaxe.exists()) pickaxe.delete();
            pickaxe.createNewFile();
            pickaxeWriter = new BufferedWriter(new FileWriter(pickaxe));

            if(axe.exists()) axe.delete();
            axe.createNewFile();
            axeWriter = new BufferedWriter(new FileWriter(axe));

            if(shovel.exists()) shovel.delete();
            shovel.createNewFile();
            shovelWriter = new BufferedWriter(new FileWriter(shovel));

            if(hoe.exists()) hoe.delete();
            hoe.createNewFile();
            hoeWriter = new BufferedWriter(new FileWriter(hoe));
        } catch (IOException e) {
            e.printStackTrace();
        }

        writeToFile(pickaxeWriter, "{");
        writeToFile(pickaxeWriter, "  \"replace\": false,");
        writeToFile(pickaxeWriter, "  \"values\": [");
        int j = 0;
        for(int i = 0; i < pickaxeList.size(); i++) {
            j++;
            String end = j == pickaxeList.size() ? "" : ",";
            writeToFile(pickaxeWriter, "    \"jitl:" + pickaxeList.get(i) + "\"" + end);
        }
        writeToFile(pickaxeWriter, "  ]");
        writeToFile(pickaxeWriter, "}");

        j = 0;
        writeToFile(axeWriter, "{");
        writeToFile(axeWriter, "  \"replace\": false,");
        writeToFile(axeWriter, "  \"values\": [");
        for(int i = 0; i < axeList.size(); i++) {
            j++;
            String end = j == axeList.size() ? "" : ",";
            writeToFile(axeWriter, "    \"jitl:" + axeList.get(i) + "\"" + end);
        }
        writeToFile(axeWriter, "  ]");
        writeToFile(axeWriter, "}");

        j = 0;
        writeToFile(shovelWriter, "{");
        writeToFile(shovelWriter, "  \"replace\": false,");
        writeToFile(shovelWriter, "  \"values\": [");
        for(int i = 0; i < shovelList.size(); i++) {
            j++;
            String end = j == shovelList.size() ? "" : ",";
            writeToFile(shovelWriter, "    \"jitl:" + shovelList.get(i) + "\"" + end);
        }
        writeToFile(shovelWriter, "  ]");
        writeToFile(shovelWriter, "}");

        j = 0;
        writeToFile(hoeWriter, "{");
        writeToFile(hoeWriter, "  \"replace\": false,");
        writeToFile(hoeWriter, "  \"values\": [");
        for(int i = 0; i < hoeList.size(); i++) {
            j++;
            String end = j == hoeList.size() ? "" : ",";
            writeToFile(hoeWriter, "    \"jitl:" + hoeList.get(i) + "\"" + end);
        }
        writeToFile(hoeWriter, "  ]");
        writeToFile(hoeWriter, "}");

        writerInit();
    }

    public void writerInit() {
        try {
            pickaxeWriter.close();
            axeWriter.close();
            shovelWriter.close();
            hoeWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(BufferedWriter file, String text){
        try {
            file.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}