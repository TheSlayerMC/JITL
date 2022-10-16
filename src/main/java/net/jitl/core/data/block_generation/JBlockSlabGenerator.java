package net.jitl.core.data.block_generation;

import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockSlabGenerator {

    protected BufferedWriter blockModelWriter, blockTopModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.slabBlockName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockModelDir = "../src/main/resources/assets/jitl/models/block/" + name + ".json";
            String blockTopModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_top.json";
            String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockModel = new File(blockModelDir);
            File blockTopModel = new File(blockTopModelDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if(blockModel.exists()) blockModel.delete();
                blockModel.createNewFile();
                blockModelWriter = new BufferedWriter(new FileWriter(blockModel));

                if(blockTopModel.exists()) blockTopModel.delete();
                blockTopModel.createNewFile();
                blockTopModelWriter = new BufferedWriter(new FileWriter(blockTopModel));

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
        writeToBlockModelFile(blockModelWriter, "  \"parent\": \"minecraft:block/slab\",");
        writeToBlockModelFile(blockModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockModelWriter, "    \"bottom\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\",");
        writeToBlockModelFile(blockModelWriter, "    \"side\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\",");
        writeToBlockModelFile(blockModelWriter, "    \"top\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\"");
        writeToBlockModelFile(blockModelWriter, "  }");
        writeToBlockModelFile(blockModelWriter, "}");

        writeToBlockModelFile(blockTopModelWriter, "{");
        writeToBlockModelFile(blockTopModelWriter, "  \"parent\": \"minecraft:block/slab_top\",");
        writeToBlockModelFile(blockTopModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockTopModelWriter, "    \"bottom\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\",");
        writeToBlockModelFile(blockTopModelWriter, "    \"side\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\",");
        writeToBlockModelFile(blockTopModelWriter, "    \"top\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\"");
        writeToBlockModelFile(blockTopModelWriter, "  }");
        writeToBlockModelFile(blockTopModelWriter, "}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        writeToBlockstateFile("    \"type=bottom\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"type=double\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"type=top\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_top\"");
        writeToBlockstateFile("      }");
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