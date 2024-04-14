package net.jitl.core.data.block_generation;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockDoublePlantGenerator {

    protected BufferedWriter blockModelWriter, blockTopModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.doublePlantBlockName) {
            String itemModelDir = "../../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockModelDir = "../../src/main/resources/assets/jitl/models/block/" + name + "_bottom.json";
            String blockTopModelDir = "../../src/main/resources/assets/jitl/models/block/" + name + "_top.json";
            String blockstateDir = "../../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockModel = new File(blockModelDir);
            File blockTopModel = new File(blockTopModelDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if (itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if (blockModel.exists()) blockModel.delete();
                blockModel.createNewFile();
                blockModelWriter = new BufferedWriter(new FileWriter(blockModel));

                if (blockTopModel.exists()) blockModel.delete();
                blockTopModel.createNewFile();
                blockTopModelWriter = new BufferedWriter(new FileWriter(blockTopModel));

                if (blockstateModel.exists()) blockstateModel.delete();
                blockstateModel.createNewFile();
                blockstateWriter = new BufferedWriter(new FileWriter(blockstateModel));
            } catch (IOException e) {
                e.printStackTrace();
            }

            getBlockItem(JITL.MODID, name);
            getBlockModel(JITL.MODID, name);
            getBlockstate(JITL.MODID, name);

            writerInit();
        }
    }

    public void getBlockItem(String modID, String name) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"minecraft:item/generated\",");
        writeToItemModelFile("  \"textures\": {");
        writeToItemModelFile("    \"layer0\": \"" + modID + ":" + "block/" + name + "_top\"");
        writeToItemModelFile("  }");
        writeToItemModelFile("}");
    }

    public void getBlockModel(String modID, String name) {
        writeToBlockModelFile("{");
        writeToBlockModelFile("  \"parent\": \"minecraft:block/cross\",");
        writeToBlockModelFile("  \"textures\": {");
        writeToBlockModelFile("    \"cross\": \"" + modID + ":" + "block/" + name + "_bottom\"");
        writeToBlockModelFile("  }");
        writeToBlockModelFile("}");

        writeToBlockTopModelFile("{");
        writeToBlockTopModelFile("  \"parent\": \"minecraft:block/cross\",");
        writeToBlockTopModelFile("  \"textures\": {");
        writeToBlockTopModelFile("    \"cross\": \"" + modID + ":" + "block/" + name + "_top\"");
        writeToBlockTopModelFile("  }");
        writeToBlockTopModelFile("}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        writeToBlockstateFile("  \"half=lower\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":" + "block/" + name + "_bottom\"");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("  \"half=upper\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":" + "block/" + name + "_top\"");
        writeToBlockstateFile("    }");
        writeToBlockstateFile("  }");
        writeToBlockstateFile("}");
    }

    public void writerInit() {
        try {
            itemModelWriter.close();
            blockModelWriter.close();
            blockTopModelWriter.close();
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

    public void writeToBlockTopModelFile(String text){
        try {
            blockTopModelWriter.write(text + "\n");
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