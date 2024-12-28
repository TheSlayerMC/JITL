package net.jitl.core.data.block_generation;

import net.jitl.core.data.BasicFileGenerator;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BlockTotemGenerator extends BasicFileGenerator {

    protected BufferedWriter blockModelWriter, blockAwakeModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.totemBlockName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockModelDir = "../src/main/resources/assets/jitl/models/block/" + name + ".json";
            String blockAwakeModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_awake.json";
            String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockModel = new File(blockModelDir);
            File blockAwakeModel = new File(blockAwakeModelDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if (itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if (blockModel.exists()) blockModel.delete();
                blockModel.createNewFile();
                blockModelWriter = new BufferedWriter(new FileWriter(blockModel));

                if (blockAwakeModel.exists()) blockAwakeModel.delete();
                blockAwakeModel.createNewFile();
                blockAwakeModelWriter = new BufferedWriter(new FileWriter(blockAwakeModel));

                if (blockstateModel.exists()) blockstateModel.delete();
                blockstateModel.createNewFile();
                blockstateWriter = new BufferedWriter(new FileWriter(blockstateModel));
            } catch (IOException e) {
                e.printStackTrace();
            }
            getBlockItem(JITL.MODID, name);
            getBlockModel(JITL.MODID, name);
            getBlockAwakeModel(JITL.MODID, name);
            getBlockstate(JITL.MODID, name);

            generateBasicFile(name);

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
        writeToBlockModelFile("  \"parent\": \"minecraft:block/cube_column\",");
        writeToBlockModelFile("  \"textures\": {");
        writeToBlockModelFile("    \"end\": \"" + modID + ":" + "block/totem_base_top\",");
        writeToBlockModelFile("    \"side\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockModelFile("  }");
        writeToBlockModelFile("}");
    }

    public void getBlockAwakeModel(String modID, String name) {
        writeToBlockAwakeModelFile("{");
        writeToBlockAwakeModelFile("  \"parent\": \"minecraft:block/cube_column\",");
        writeToBlockAwakeModelFile("  \"textures\": {");
        writeToBlockAwakeModelFile("    \"end\": \"" + modID + ":" + "block/totem_base_top\",");
        writeToBlockAwakeModelFile("    \"side\": \"" + modID + ":" + "block/" + name + "_awake\"");
        writeToBlockAwakeModelFile("  }");
        writeToBlockAwakeModelFile("}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        writeToBlockstateFile("    \"awake=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_awake\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"awake=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("  }");
        writeToBlockstateFile("}");
    }

    public void writerInit() {
        try {
            itemModelWriter.close();
            blockModelWriter.close();
            blockAwakeModelWriter.close();
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

    public void writeToBlockAwakeModelFile(String text){
        try {
            blockAwakeModelWriter.write(text + "\n");
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

    public void writeToBlockstateFile(String text){
        try {
            blockstateWriter.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}