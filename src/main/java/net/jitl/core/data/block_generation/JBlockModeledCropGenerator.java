package net.jitl.core.data.block_generation;

import net.jitl.core.data.BasicFileGenerator;
import net.jitl.core.init.JITL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockModeledCropGenerator extends BasicFileGenerator {

    protected BufferedWriter blockstateWriter, itemModelWriter;

    public void generate(String name, int maxStages) {
        String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
        String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

        File itemModel = new File(itemModelDir);
        File blockstateModel = new File(blockstateDir);

        try {
            if(itemModel.exists()) itemModel.delete();
            itemModel.createNewFile();
            itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

            if(blockstateModel.exists()) blockstateModel.delete();
            blockstateModel.createNewFile();
            blockstateWriter = new BufferedWriter(new FileWriter(blockstateModel));
        } catch (IOException e) {
            e.printStackTrace();
        }

            getBlockItem(JITL.MOD_ID, name, maxStages);
            getBlockstate(JITL.MOD_ID, name, maxStages);
        generateBasicFile(name + "_" + (maxStages - 1));

            writerInit();

    }

    public void getBlockItem(String modID, String name, int maxStages) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"minecraft:item/generated\",");
        writeToItemModelFile("  \"textures\": {");
        writeToItemModelFile("    \"layer0\": \"" + modID + ":" + "block/" + name + "_" + (maxStages - 1) + "\"");
        writeToItemModelFile("  }");
        writeToItemModelFile("}");
    }

    public void getBlockstate(String modID, String name, int maxStages) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        int j = 0;
        for(int i = 0; i < maxStages; i++) {
            j++;
            String end = j == maxStages ? "}" : "},";
            writeToBlockstateFile("   \"age=" + i + "\": {");
            writeToBlockstateFile("       \"model\": \"" + modID + ":" + "block/" + name + "_" + i + "\"");
            writeToBlockstateFile("    " + end);
        }
        writeToBlockstateFile("  }");
        writeToBlockstateFile("}");
    }

    public void writerInit() {
        try {
            itemModelWriter.close();
            blockstateWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToItemModelFile(String text){
        try {
            itemModelWriter.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToBlockstateFile(String text){
        try {
            blockstateWriter.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}