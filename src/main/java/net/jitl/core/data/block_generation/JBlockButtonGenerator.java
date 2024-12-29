package net.jitl.core.data.block_generation;

import net.jitl.core.data.BasicFileGenerator;
import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockButtonGenerator extends BasicFileGenerator {

    protected BufferedWriter blockModelWriter, blockPressedModelWriter, blockInventoryModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.buttonBlockName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockModelDir = "../src/main/resources/assets/jitl/models/block/" + name + ".json";
            String blockPressedModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_pressed.json";
            String blockInventoryModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_inventory.json";
            String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockModel = new File(blockModelDir);
            File blockPressedModel = new File(blockPressedModelDir);
            File blockInventoryModel = new File(blockInventoryModelDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if(blockModel.exists()) blockModel.delete();
                blockModel.createNewFile();
                blockModelWriter = new BufferedWriter(new FileWriter(blockModel));

                if(blockPressedModel.exists()) blockPressedModel.delete();
                blockPressedModel.createNewFile();
                blockPressedModelWriter = new BufferedWriter(new FileWriter(blockPressedModel));

                if(blockInventoryModel.exists()) blockInventoryModel.delete();
                blockInventoryModel.createNewFile();
                blockInventoryModelWriter = new BufferedWriter(new FileWriter(blockInventoryModel));

                if(blockstateModel.exists()) blockstateModel.delete();
                blockstateModel.createNewFile();
                blockstateWriter = new BufferedWriter(new FileWriter(blockstateModel));

            } catch (IOException e) {
                e.printStackTrace();
            }
            getBlockItem(JITL.MOD_ID, name);
            getBlockModel(JITL.MOD_ID, name);
            getBlockstate(JITL.MOD_ID, name);
            generateBasicFile(name, "_inventory");

            writerInit();
        }
    }

    public void getBlockItem(String modID, String name) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"" + modID + ":block/" + name + "_inventory\"");
        writeToItemModelFile("}");
    }

    public void getBlockModel(String modID, String name) {
        writeToBlockModelFile(blockModelWriter, "{");
        writeToBlockModelFile(blockModelWriter, "  \"parent\": \"minecraft:block/button\",");
        writeToBlockModelFile(blockModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockModelWriter, "    \"texture\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\"");
        writeToBlockModelFile(blockModelWriter, "  }");
        writeToBlockModelFile(blockModelWriter, "}");

        writeToBlockModelFile(blockPressedModelWriter, "{");
        writeToBlockModelFile(blockPressedModelWriter, "  \"parent\": \"minecraft:block/button_pressed\",");
        writeToBlockModelFile(blockPressedModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockPressedModelWriter, "    \"texture\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\"");
        writeToBlockModelFile(blockPressedModelWriter, "  }");
        writeToBlockModelFile(blockPressedModelWriter, "}");

        writeToBlockModelFile(blockInventoryModelWriter, "{");
        writeToBlockModelFile(blockInventoryModelWriter, "  \"parent\": \"minecraft:block/button_inventory\",");
        writeToBlockModelFile(blockInventoryModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockInventoryModelWriter, "    \"texture\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\"");
        writeToBlockModelFile(blockInventoryModelWriter, "  }");
        writeToBlockModelFile(blockInventoryModelWriter, "}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        writeToBlockstateFile("    \"face=ceiling,facing=east,powered=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"face=ceiling,facing=east,powered=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_pressed\",");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"face=ceiling,facing=north,powered=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"face=ceiling,facing=north,powered=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_pressed\",");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"face=ceiling,facing=south,powered=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"x\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"face=ceiling,facing=south,powered=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_pressed\",");
        writeToBlockstateFile("         \"x\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"face=ceiling,facing=west,powered=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");

        writeToBlockstateFile("    \"face=ceiling,facing=west,powered=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_pressed\",");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"face=floor,facing=east,powered=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"face=floor,facing=east,powered=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_pressed\",");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"face=floor,facing=north,powered=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"face=floor,facing=north,powered=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_pressed\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"face=floor,facing=south,powered=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"face=floor,facing=south,powered=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_pressed\",");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"face=floor,facing=west,powered=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"face=floor,facing=west,powered=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_pressed\",");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");

        writeToBlockstateFile("    \"face=wall,facing=east,powered=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 90,");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"face=wall,facing=east,powered=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_pressed\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 90,");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"face=wall,facing=north,powered=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"face=wall,facing=north,powered=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_pressed\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"face=wall,facing=south,powered=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 90,");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"face=wall,facing=south,powered=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_pressed\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 90,");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"face=wall,facing=west,powered=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 90,");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"face=wall,facing=west,powered=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_pressed\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 90,");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("  }");
        writeToBlockstateFile("}");
    }

    public void writerInit() {
        try {
            itemModelWriter.close();
            blockModelWriter.close();
            blockPressedModelWriter.close();
            blockInventoryModelWriter.close();
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