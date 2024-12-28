package net.jitl.core.data.block_generation;

import net.jitl.core.data.BasicFileGenerator;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockMushroomGenerator extends BasicFileGenerator {

    protected BufferedWriter blockModelWriter, blockInsideModelWriter, blockInventoryModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.mushroomBlockName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockModelDir = "../src/main/resources/assets/jitl/models/block/" + name + ".json";
            String blockInsideModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_inside.json";
            String blockInventoryModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_inventory.json";
            String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockModel = new File(blockModelDir);
            File blockInsideModel = new File(blockInsideModelDir);
            File blockInventoryModel = new File(blockInventoryModelDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if (itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if (blockModel.exists()) blockModel.delete();
                blockModel.createNewFile();
                blockModelWriter = new BufferedWriter(new FileWriter(blockModel));

                if (blockInsideModel.exists()) blockInsideModel.delete();
                blockInsideModel.createNewFile();
                blockInsideModelWriter = new BufferedWriter(new FileWriter(blockInsideModel));

                if (blockInventoryModel.exists()) blockInventoryModel.delete();
                blockInventoryModel.createNewFile();
                blockInventoryModelWriter = new BufferedWriter(new FileWriter(blockInventoryModel));

                if (blockstateModel.exists()) blockstateModel.delete();
                blockstateModel.createNewFile();
                blockstateWriter = new BufferedWriter(new FileWriter(blockstateModel));
            } catch (IOException e) {
                e.printStackTrace();
            }
            getBlockItem(JITL.MODID, name);
            getBlockModel(JITL.MODID, name);
            getBlockInventoryModel(JITL.MODID, name);
            getBlockInsideModel(JITL.MODID, name);
            getBlockstate(JITL.MODID, name);
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
        writeToBlockModelFile("{");
        writeToBlockModelFile("  \"parent\": \"minecraft:block/template_single_face\",");
        writeToBlockModelFile("  \"textures\": {");
        writeToBlockModelFile("    \"texture\": \"" + modID + ":" + "block/" + name + "_skin\"");
        writeToBlockModelFile("  }");
        writeToBlockModelFile("}");
    }

    public void getBlockInventoryModel(String modID, String name) {
        writeToBlockInventoryModelFile("{");
        writeToBlockInventoryModelFile("  \"parent\": \"minecraft:block/cube_all\",");
        writeToBlockInventoryModelFile("  \"textures\": {");
        writeToBlockInventoryModelFile("    \"all\": \"" + modID + ":" + "block/" + name + "_skin\"");
        writeToBlockInventoryModelFile("  }");
        writeToBlockInventoryModelFile("}");
    }

    public void getBlockInsideModel(String modID, String name) {
        writeToBlockInsideModelFile("{");
        writeToBlockInsideModelFile("  \"parent\": \"minecraft:block/template_single_face\",");
        writeToBlockInsideModelFile("  \"textures\": {");
        writeToBlockInsideModelFile("    \"texture\": \"" + modID + ":" + "block/" + name + "_inside\"");
        writeToBlockInsideModelFile("  }");
        writeToBlockInsideModelFile("}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"multipart\": [");
        writeToBlockstateFile("    {");
        writeToBlockstateFile("      \"apply\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":block/" + name + "\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("      \"when\": {");
        writeToBlockstateFile("        \"north\": \"true\"");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    {");
        writeToBlockstateFile("      \"apply\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":block/" + name + "\",");
        writeToBlockstateFile("        \"uvlock\": true,");
        writeToBlockstateFile("        \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("      \"when\": {");
        writeToBlockstateFile("        \"east\": \"true\"");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    {");
        writeToBlockstateFile("      \"apply\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":block/" + name + "\",");
        writeToBlockstateFile("        \"uvlock\": true,");
        writeToBlockstateFile("        \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("      \"when\": {");
        writeToBlockstateFile("        \"south\": \"true\"");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    {");
        writeToBlockstateFile("      \"apply\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":block/" + name + "\",");
        writeToBlockstateFile("        \"uvlock\": true,");
        writeToBlockstateFile("        \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("      \"when\": {");
        writeToBlockstateFile("        \"west\": \"true\"");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    {");
        writeToBlockstateFile("      \"apply\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":block/" + name + "\",");
        writeToBlockstateFile("        \"uvlock\": true,");
        writeToBlockstateFile("        \"x\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("      \"when\": {");
        writeToBlockstateFile("        \"up\": \"true\"");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    {");
        writeToBlockstateFile("      \"apply\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":block/" + name + "\",");
        writeToBlockstateFile("        \"uvlock\": true,");
        writeToBlockstateFile("        \"x\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("      \"when\": {");
        writeToBlockstateFile("        \"down\": \"true\"");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    {");
        writeToBlockstateFile("      \"apply\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":block/" + name + "_inside\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("      \"when\": {");
        writeToBlockstateFile("        \"north\": \"false\"");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    {");
        writeToBlockstateFile("      \"apply\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":block/" + name + "_inside\",");
        writeToBlockstateFile("        \"uvlock\": true,");
        writeToBlockstateFile("        \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("      \"when\": {");
        writeToBlockstateFile("        \"east\": \"false\"");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    {");
        writeToBlockstateFile("      \"apply\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":block/" + name + "_inside\",");
        writeToBlockstateFile("        \"uvlock\": true,");
        writeToBlockstateFile("        \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("      \"when\": {");
        writeToBlockstateFile("        \"south\": \"false\"");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    {");
        writeToBlockstateFile("      \"apply\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":block/" + name + "_inside\",");
        writeToBlockstateFile("        \"uvlock\": true,");
        writeToBlockstateFile("        \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("      \"when\": {");
        writeToBlockstateFile("        \"west\": \"false\"");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    {");
        writeToBlockstateFile("      \"apply\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":block/" + name + "_inside\",");
        writeToBlockstateFile("        \"uvlock\": true,");
        writeToBlockstateFile("        \"x\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("      \"when\": {");
        writeToBlockstateFile("        \"up\": \"false\"");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    {");
        writeToBlockstateFile("      \"apply\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":block/" + name + "_inside\",");
        writeToBlockstateFile("        \"uvlock\": true,");
        writeToBlockstateFile("        \"x\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("      \"when\": {");
        writeToBlockstateFile("        \"down\": \"false\"");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("    }");
        writeToBlockstateFile("  ]");
        writeToBlockstateFile("}");
    }

    public void writerInit() {
        try {
            itemModelWriter.close();
            blockModelWriter.close();
            blockInsideModelWriter.close();
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

    public void writeToBlockInsideModelFile(String text){
        try {
            blockInsideModelWriter.write(text + "\n");
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

    public void writeToBlockInventoryModelFile(String text){
        try {
            blockInventoryModelWriter.write(text + "\n");
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