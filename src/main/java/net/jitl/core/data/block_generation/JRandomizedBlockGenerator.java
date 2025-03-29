package net.jitl.core.data.block_generation;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JRandomizedBlockGenerator {

    protected BufferedWriter blockModelWriter, blockAltModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.randomBlockName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockModelDir = "../src/main/resources/assets/jitl/models/block/" + name + ".json";
            String blockModelAltDir = "../src/main/resources/assets/jitl/models/block/" + name + "_alt.json";
            String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockModel = new File(blockModelDir);
            File blockAltModel = new File(blockModelAltDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if (itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if (blockModel.exists()) blockModel.delete();
                blockModel.createNewFile();
                blockModelWriter = new BufferedWriter(new FileWriter(blockModel));

                if (blockAltModel.exists()) blockAltModel.delete();
                blockAltModel.createNewFile();
                blockAltModelWriter = new BufferedWriter(new FileWriter(blockAltModel));

                if (blockstateModel.exists()) blockstateModel.delete();
                blockstateModel.createNewFile();
                blockstateWriter = new BufferedWriter(new FileWriter(blockstateModel));
            } catch (IOException e) {
                e.printStackTrace();
            }

            getBlockItem(JITL.MOD_ID, name);
            getBlockModel(JITL.MOD_ID, name);
            getBlockAltModel(JITL.MOD_ID, name);
            getBlockstate(JITL.MOD_ID, name);

            writerInit();
        }
    }

    public void getBlockItem(String modID, String name) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"" + modID + ":block/" + name + "\"");
        writeToItemModelFile("}");
    }

    public void getBlockModel(String modID, String name) {
        writeToBlockModelFile("{");
        writeToBlockModelFile("  \"parent\": \"minecraft:block/cube_all\",");
        writeToBlockModelFile("  \"textures\": {");
        writeToBlockModelFile("    \"all\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockModelFile("  }");
        writeToBlockModelFile("}");
    }

    public void getBlockAltModel(String modID, String name) {
        writeToBlockAltModelFile("{");
        writeToBlockAltModelFile("  \"parent\": \"minecraft:block/cube_all\",");
        writeToBlockAltModelFile("  \"textures\": {");
        writeToBlockAltModelFile("    \"all\": \"" + modID + ":" + "block/" + name + "_alt\"");
        writeToBlockAltModelFile("  }");
        writeToBlockAltModelFile("}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        writeToBlockstateFile("    \"\": [");
        writeToBlockstateFile("      {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("      {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_alt\"");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("    ]");
        writeToBlockstateFile("  }");
        writeToBlockstateFile("}");
    }

    public void writerInit() {
        try {
            itemModelWriter.close();
            blockModelWriter.close();
            blockAltModelWriter.close();
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

    public void writeToBlockModelFile(String text){
        try {
            blockModelWriter.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToBlockAltModelFile(String text){
        try {
            blockAltModelWriter.write(text + "\n");
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