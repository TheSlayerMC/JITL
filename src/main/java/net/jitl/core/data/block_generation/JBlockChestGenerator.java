package net.jitl.core.data.block_generation;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockChestGenerator {

    protected BufferedWriter blockModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.chestBlockName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockModelDir = "../src/main/resources/assets/jitl/models/block/" + name + ".json";
            String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockModel = new File(blockModelDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if (itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if (blockModel.exists()) blockModel.delete();
                blockModel.createNewFile();
                blockModelWriter = new BufferedWriter(new FileWriter(blockModel));

                if (blockstateModel.exists()) blockstateModel.delete();
                blockstateModel.createNewFile();
                blockstateWriter = new BufferedWriter(new FileWriter(blockstateModel));
            } catch (IOException e) {
                e.printStackTrace();
            }

            getBlockItem();
            getBlockModel(JITL.MODID, name);
            getBlockstate();

            writerInit();
        }
    }

    public void getBlockItem() {
        writeToItemModelFile("{");
        writeToItemModelFile("    \"parent\": \"builtin/entity\",");
        writeToItemModelFile("    \"textures\": {");
        writeToItemModelFile("        \"particle\": \"block/cobblestone\"");
        writeToItemModelFile("    },");
        writeToItemModelFile("    \"display\": {");
        writeToItemModelFile("        \"gui\": {");
        writeToItemModelFile("            \"rotation\": [ 30, 45, 0 ],");
        writeToItemModelFile("            \"translation\": [ 0, 0, 0],");
        writeToItemModelFile("            \"scale\":[ 0.625, 0.625, 0.625 ]");
        writeToItemModelFile("        },");
        writeToItemModelFile("        \"ground\": {");
        writeToItemModelFile("            \"rotation\": [ 0, 0, 0 ],");
        writeToItemModelFile("            \"translation\": [ 0, 3, 0],");
        writeToItemModelFile("            \"scale\":[ 0.25, 0.25, 0.25 ]");
        writeToItemModelFile("        },");
        writeToItemModelFile("        \"head\": {");
        writeToItemModelFile("            \"rotation\": [ 0, 180, 0 ],");
        writeToItemModelFile("            \"translation\": [ 0, 0, 0],");
        writeToItemModelFile("            \"scale\":[ 1, 1, 1]");
        writeToItemModelFile("        },");
        writeToItemModelFile("        \"fixed\": {");
        writeToItemModelFile("            \"rotation\": [ 0, 180, 0 ],");
        writeToItemModelFile("            \"translation\": [ 0, 0, 0],");
        writeToItemModelFile("            \"scale\":[ 0.5, 0.5, 0.5 ]");
        writeToItemModelFile("        },");
        writeToItemModelFile("        \"thirdperson_righthand\": {");
        writeToItemModelFile("            \"rotation\": [ 75, 315, 0 ],");
        writeToItemModelFile("            \"translation\": [ 0, 2.5, 0],");
        writeToItemModelFile("            \"scale\": [ 0.375, 0.375, 0.375 ]");
        writeToItemModelFile("        },");
        writeToItemModelFile("        \"firstperson_righthand\": {");
        writeToItemModelFile("            \"rotation\": [ 0, 315, 0 ],");
        writeToItemModelFile("            \"translation\": [ 0, 0, 0],");
        writeToItemModelFile("            \"scale\": [ 0.4, 0.4, 0.4 ]");
        writeToItemModelFile("        }");
        writeToItemModelFile("    }");
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

    public void getBlockstate() {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        writeToBlockstateFile("  \"\": {");
        writeToBlockstateFile("      \"model\": \"minecraft:" + "block/cobblestone\"");
        writeToBlockstateFile("     }");
        writeToBlockstateFile("  }");
        writeToBlockstateFile("}");
    }

    public void writerInit() {
        try {
            itemModelWriter.close();
            blockModelWriter.close();
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

    public void writeToBlockstateFile(String text){
        try {
            blockstateWriter.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}