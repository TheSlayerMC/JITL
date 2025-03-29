package net.jitl.core.data.block_generation;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockCampfireGenerator {

    protected BufferedWriter blockModelWriter, blockOffModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.campfireBlockName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockModelDir = "../src/main/resources/assets/jitl/models/block/" + name + ".json";
            String blockOffModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_off.json";
            String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockModel = new File(blockModelDir);
            File blockOffModel = new File(blockOffModelDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if(blockModel.exists()) blockModel.delete();
                blockModel.createNewFile();
                blockModelWriter = new BufferedWriter(new FileWriter(blockModel));

                if(blockOffModel.exists()) blockOffModel.delete();
                blockOffModel.createNewFile();
                blockOffModelWriter = new BufferedWriter(new FileWriter(blockOffModel));

                if(blockstateModel.exists()) blockstateModel.delete();
                blockstateModel.createNewFile();
                blockstateWriter = new BufferedWriter(new FileWriter(blockstateModel));
            } catch (IOException e) {
                e.printStackTrace();
            }
            getBlockItem(JITL.MOD_ID, name);
            getBlockModel(JITL.MOD_ID, name);
            getBlockOffModel(JITL.MOD_ID, name);
            getBlockstate(JITL.MOD_ID, name);
            writerInit();
        }
    }

    public void getBlockItem(String modID, String name) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"minecraft:item/generated\",");
        writeToItemModelFile("  \"textures\": {");
        writeToItemModelFile("      \"layer0\": \"" + modID + ":item/" + name + "\"");
        writeToItemModelFile("  }");
        writeToItemModelFile("}");
    }

    public void getBlockModel(String modID, String name) {
        writeToBlockModelFile(blockModelWriter, "{");
        writeToBlockModelFile(blockModelWriter, "  \"parent\": \"minecraft:block/campfire\",");
        writeToBlockModelFile(blockModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockModelWriter, "    \"particle\": \"" + modID + ":" + "block/" + name + "_log\",");
        writeToBlockModelFile(blockModelWriter, "    \"fire\": \"" + modID + ":" + "block/" + name + "_fire\",");
        writeToBlockModelFile(blockModelWriter, "    \"log\": \"" + modID + ":" + "block/" + name + "_log\",");
        writeToBlockModelFile(blockModelWriter, "    \"lit_log\": \"" + modID + ":" + "block/" + name + "_log_lit\"");
        writeToBlockModelFile(blockModelWriter, "  }");
        writeToBlockModelFile(blockModelWriter, "}");
    }

    public void getBlockOffModel(String modID, String name) {
        writeToBlockModelFile(blockOffModelWriter, "{");
        writeToBlockModelFile(blockOffModelWriter, "  \"parent\": \"minecraft:block/campfire_off\",");
        writeToBlockModelFile(blockOffModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockOffModelWriter, "    \"particle\": \"" + modID + ":" + "block/" + name + "_log\",");
        writeToBlockModelFile(blockOffModelWriter, "    \"log\": \"" + modID + ":" + "block/" + name + "_log\"");
        writeToBlockModelFile(blockOffModelWriter, "  }");
        writeToBlockModelFile(blockOffModelWriter, "}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        writeToBlockstateFile("    \"facing=east,lit=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_off\",");
        writeToBlockstateFile("        \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,lit=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("        \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,lit=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_off\",");
        writeToBlockstateFile("        \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,lit=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("        \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,lit=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_off\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,lit=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,lit=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_off\",");
        writeToBlockstateFile("        \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,lit=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("        \"y\": 90");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("  }");
        writeToBlockstateFile("}");
    }

    public void writerInit() {
        try {
            itemModelWriter.close();
            blockModelWriter.close();
            blockOffModelWriter.close();
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