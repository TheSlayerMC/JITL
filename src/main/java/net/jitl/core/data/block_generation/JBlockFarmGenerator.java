package net.jitl.core.data.block_generation;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockFarmGenerator {

    protected BufferedWriter blockModelWriter, blockMoistModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.farmlandBlockName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockModelDir = "../src/main/resources/assets/jitl/models/block/" + name + ".json";
            String blockMoistModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_moist.json";
            String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockModel = new File(blockModelDir);
            File blockMoistModel = new File(blockMoistModelDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if (itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if (blockModel.exists()) blockModel.delete();
                blockModel.createNewFile();
                blockModelWriter = new BufferedWriter(new FileWriter(blockModel));

                if (blockMoistModel.exists()) blockMoistModel.delete();
                blockMoistModel.createNewFile();
                blockMoistModelWriter = new BufferedWriter(new FileWriter(blockMoistModel));

                if (blockstateModel.exists()) blockstateModel.delete();
                blockstateModel.createNewFile();
                blockstateWriter = new BufferedWriter(new FileWriter(blockstateModel));
            } catch (IOException e) {
                e.printStackTrace();
            }
            getBlockItem(JITL.MOD_ID, name);
            getBlockModel(JITL.MOD_ID, name);
            getBlockHorModel(JITL.MOD_ID, name);
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
        writeToBlockModelFile("  \"parent\": \"minecraft:block/template_farmland\",");
        writeToBlockModelFile("  \"textures\": {");
        writeToBlockModelFile("    \"dirt\": \"" + modID + ":" + "block/" + name + "_dirt\",");
        writeToBlockModelFile("    \"top\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockModelFile("  }");
        writeToBlockModelFile("}");
    }

    public void getBlockHorModel(String modID, String name) {
        writeToBlockMoistModelFile("{");
        writeToBlockMoistModelFile("  \"parent\": \"minecraft:block/template_farmland\",");
        writeToBlockMoistModelFile("  \"textures\": {");
        writeToBlockMoistModelFile("    \"dirt\": \"" + modID + ":" + "block/" + name + "_dirt\",");
        writeToBlockMoistModelFile("    \"top\": \"" + modID + ":" + "block/" + name + "_moist\"");
        writeToBlockMoistModelFile("  }");
        writeToBlockMoistModelFile("}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        writeToBlockstateFile("    \"moisture=0\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"moisture=1\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"moisture=2\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"moisture=3\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"moisture=4\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"moisture=5\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"moisture=6\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"moisture=7\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_moist\"");
        writeToBlockstateFile("    }");
        writeToBlockstateFile("  }");
        writeToBlockstateFile("}");
    }

    public void writerInit() {
        try {
            itemModelWriter.close();
            blockModelWriter.close();
            blockMoistModelWriter.close();
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

    public void writeToBlockMoistModelFile(String text){
        try {
            blockMoistModelWriter.write(text + "\n");
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