package net.jitl.core.data;

import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BlockTagGenerator {

    protected BufferedWriter planksWriter, woodWriter;

    public void generate() {
        String baseDir = "../src/main/resources/data/minecraft/tags/item/";

        File planks = new File(baseDir + "planks.json");
        File woodTools = new File(baseDir + "wooden_tool_materials.json");

        ArrayList<String> planksList = new ArrayList<>(JBlocks.PLANKS);

        try {
            if(planks.exists()) planks.delete();
            planks.createNewFile();
            planksWriter = new BufferedWriter(new FileWriter(planks));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if(woodTools.exists()) woodTools.delete();
            woodTools.createNewFile();
            woodWriter = new BufferedWriter(new FileWriter(woodTools));
        } catch (IOException e) {
            e.printStackTrace();
        }

        writeToFile(planksWriter, "{");
        writeToFile(planksWriter, "  \"replace\": false,");
        writeToFile(planksWriter, "  \"values\": [");
        int j = 0;
        for(int i = 0; i < planksList.size(); i++) {
            j++;
            String end = j == planksList.size() ? "" : ",";
            writeToFile(planksWriter, "    \"jitl:" + planksList.get(i) + "\"" + end);
        }
        writeToFile(planksWriter, "  ]");
        writeToFile(planksWriter, "}");

        j = 0;
        writeToFile(woodWriter, "{");
        writeToFile(woodWriter, "  \"replace\": false,");
        writeToFile(woodWriter, "  \"values\": [");
        for(int i = 0; i < planksList.size(); i++) {
            j++;
            String end = j == planksList.size() ? "" : ",";
            writeToFile(woodWriter, "    \"jitl:" + planksList.get(i) + "\"" + end);
        }
        writeToFile(woodWriter, "  ]");
        writeToFile(woodWriter, "}");

        writerInit();
    }

    public void writerInit() {
        try {
            planksWriter.close();
            woodWriter.close();
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