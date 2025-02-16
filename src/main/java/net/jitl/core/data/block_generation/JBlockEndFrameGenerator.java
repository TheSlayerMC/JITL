package net.jitl.core.data.block_generation;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockEndFrameGenerator {

    protected BufferedWriter blockModelWriter, blockFilledModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.basePortalFrameBlockName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockModelDir = "../src/main/resources/assets/jitl/models/block/" + name + ".json";
            String blockFilledModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_filled.json";
            String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockModel = new File(blockModelDir);
            File blockFilledModel = new File(blockFilledModelDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if(blockModel.exists()) blockModel.delete();
                blockModel.createNewFile();
                blockModelWriter = new BufferedWriter(new FileWriter(blockModel));

                if(blockFilledModel.exists()) blockFilledModel.delete();
                blockFilledModel.createNewFile();
                blockFilledModelWriter = new BufferedWriter(new FileWriter(blockFilledModel));

                if(blockstateModel.exists()) blockstateModel.delete();
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
        writeToBlockModelFile(blockModelWriter, "{");
        writeToBlockModelFile(blockModelWriter, "  \"parent\": \"minecraft:block/end_portal_frame\",");
        writeToBlockModelFile(blockModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockModelWriter, "    \"particle\": \"" + modID + ":" + "block/" + name + "_side\",");
        writeToBlockModelFile(blockModelWriter, "    \"bottom\": \"" + modID + ":" + "block/" + name + "_bottom\",");
        writeToBlockModelFile(blockModelWriter, "    \"top\": \"" + modID + ":" + "block/" + name + "_top\",");
        writeToBlockModelFile(blockModelWriter, "    \"side\": \"" + modID + ":" + "block/" + name + "_side\"");
        writeToBlockModelFile(blockModelWriter, "  }");
        writeToBlockModelFile(blockModelWriter, "}");

        writeToBlockModelFile(blockFilledModelWriter, "{");
        writeToBlockModelFile(blockFilledModelWriter, "  \"parent\": \"minecraft:block/end_portal_frame_filled\",");
        writeToBlockModelFile(blockFilledModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockFilledModelWriter, "    \"particle\": \"" + modID + ":" + "block/" + name + "_side\",");
        writeToBlockModelFile(blockFilledModelWriter, "    \"bottom\": \"" + modID + ":" + "block/" + name + "_bottom\",");
        writeToBlockModelFile(blockFilledModelWriter, "    \"top\": \"" + modID + ":" + "block/" + name + "_top\",");
        writeToBlockModelFile(blockFilledModelWriter, "    \"side\": \"" + modID + ":" + "block/" + name + "_side\",");
        writeToBlockModelFile(blockFilledModelWriter, "    \"eye\": \"" + modID + ":" + "block/" + name + "_eye\"");
        writeToBlockModelFile(blockFilledModelWriter, "  }");
        writeToBlockModelFile(blockFilledModelWriter, "}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        writeToBlockstateFile("    \"eye=false,facing=east\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":block/" + name + "\",");
        writeToBlockstateFile("      \"y\": 270");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"eye=false,facing=north\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":block/" + name + "\",");
        writeToBlockstateFile("      \"y\": 180");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"eye=false,facing=south\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":block/" + name + "\"");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"eye=false,facing=west\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":block/" + name + "\",");
        writeToBlockstateFile("      \"y\": 90");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"eye=true,facing=east\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":block/" + name + "_filled\",");
        writeToBlockstateFile("      \"y\": 270");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"eye=true,facing=north\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":block/" + name + "_filled\",");
        writeToBlockstateFile("      \"y\": 180");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"eye=true,facing=south\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":block/" + name + "_filled\"");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"eye=true,facing=west\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":block/" + name + "_filled\",");
        writeToBlockstateFile("      \"y\": 90");
        writeToBlockstateFile("    }");
        writeToBlockstateFile("  }");
        writeToBlockstateFile("}");
    }

    public void writerInit() {
        try {
            itemModelWriter.close();
            blockModelWriter.close();
            blockFilledModelWriter.close();
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

    public void writeToBlockModelFile(BufferedWriter writer, String text){
        try {
            writer.write(text + "\n");
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