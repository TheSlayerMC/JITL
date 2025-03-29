package net.jitl.core.data.block_generation;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockAttachedCrossGenerator {

    protected BufferedWriter blockModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.attachedCrossBlockName) {
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

            getBlockItem(JITL.MOD_ID, name);
            getBlockModel(JITL.MOD_ID, name);
            getBlockstate(JITL.MOD_ID, name);

            writerInit();
        }
    }

    public void getBlockItem(String modID, String name) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"minecraft:item/generated\",");
        writeToItemModelFile("  \"textures\": {");
        writeToItemModelFile("    \"layer0\": \"" + modID + ":" + "block/" + name + "\"");
        writeToItemModelFile("  }");
        writeToItemModelFile("}");
    }

    public void getBlockModel(String modID, String name) {
        writeToBlockModelFile("{");
        writeToBlockModelFile("  \"parent\": \"minecraft:block/cross\",");
        writeToBlockModelFile("  \"textures\": {");
        writeToBlockModelFile("    \"cross\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockModelFile("  }");
        writeToBlockModelFile("}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        writeToBlockstateFile("   \"facing=north\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("       \"x\": 90");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("   \"facing=south\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("       \"x\": -90");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("   \"facing=east\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("       \"x\": 90,");
        writeToBlockstateFile("       \"y\": 90");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("   \"facing=west\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("       \"x\": 270,");
        writeToBlockstateFile("       \"y\": 90");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("   \"facing=up\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("   \"facing=down\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("       \"x\": 180");
        writeToBlockstateFile("    }");
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