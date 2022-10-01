package net.jitl.core.data;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockFurnaceGenerator {

    protected BufferedWriter blockModelWriter, blockOnModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.furnaceBlockName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockModelDir = "../src/main/resources/assets/jitl/models/block/" + name + ".json";
            String blockOnModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_on.json";
            String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockModel = new File(blockModelDir);
            File blockOnModel = new File(blockOnModelDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if (itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if (blockModel.exists()) blockModel.delete();
                blockModel.createNewFile();
                blockModelWriter = new BufferedWriter(new FileWriter(blockModel));

                if (blockOnModel.exists()) blockOnModel.delete();
                blockOnModel.createNewFile();
                blockOnModelWriter = new BufferedWriter(new FileWriter(blockOnModel));

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
        writeToItemModelFile("  \"parent\": \"" + modID + ":block/" + name + "\"");
        writeToItemModelFile("}");
    }

    public void getBlockModel(String modID, String name) {
        writeToBlockModelFile(blockModelWriter,"{");
        writeToBlockModelFile(blockModelWriter,"  \"parent\": \"minecraft:block/orientable\",");
        writeToBlockModelFile(blockModelWriter,"  \"textures\": {");
        writeToBlockModelFile(blockModelWriter,"    \"front\": \"" + modID + ":" + "block/" + name + "_front\",");
        writeToBlockModelFile(blockModelWriter,"    \"side\": \"" + modID + ":" + "block/" + name + "_side\",");
        writeToBlockModelFile(blockModelWriter,"    \"top\": \"" + modID + ":" + "block/" + name + "_top\"");
        writeToBlockModelFile(blockModelWriter,"  }");
        writeToBlockModelFile(blockModelWriter,"}");

        writeToBlockModelFile(blockOnModelWriter, "{");
        writeToBlockModelFile(blockOnModelWriter,"  \"parent\": \"minecraft:block/orientable\",");
        writeToBlockModelFile(blockOnModelWriter,"  \"textures\": {");
        writeToBlockModelFile(blockOnModelWriter,"    \"front\": \"" + modID + ":" + "block/" + name + "_front_on\",");
        writeToBlockModelFile(blockOnModelWriter,"    \"side\": \"" + modID + ":" + "block/" + name + "_side\",");
        writeToBlockModelFile(blockOnModelWriter,"    \"top\": \"" + modID + ":" + "block/" + name + "_top\"");
        writeToBlockModelFile(blockOnModelWriter,"  }");
        writeToBlockModelFile(blockOnModelWriter,"}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        writeToBlockstateFile("    \"facing=east,lit=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("    \"facing=north,lit=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("    \"facing=south,lit=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("    \"facing=west,lit=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("    \"facing=east,lit=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_on\",");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("    \"facing=north,lit=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_on\"");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("    \"facing=south,lit=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_on\",");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("    \"facing=west,lit=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_on\",");
        writeToBlockstateFile("         \"y\": 270");
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

    public void writeToBlockModelFile(BufferedWriter w, String text){
        try {
            w.write(text + "\n");
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