package net.jitl.core.data.block_generation;

import net.jitl.core.data.BasicFileGenerator;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockWallGenerator extends BasicFileGenerator {

    protected BufferedWriter blockModelWriter, blockPostModelWriter, blockSideModelWriter, blockSideTallModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.wallBlockName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_inventory.json";
            String blockPostModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_post.json";
            String blockSideModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_side.json";
            String blockSideTallModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_side_tall.json";
            String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockModel = new File(blockModelDir);
            File blockPostModel = new File(blockPostModelDir);
            File blockSideModel = new File(blockSideModelDir);
            File blockSideTallModel = new File(blockSideTallModelDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if(blockModel.exists()) blockModel.delete();
                blockModel.createNewFile();
                blockModelWriter = new BufferedWriter(new FileWriter(blockModel));

                if(blockPostModel.exists()) blockPostModel.delete();
                blockPostModel.createNewFile();
                blockPostModelWriter = new BufferedWriter(new FileWriter(blockPostModel));

                if(blockSideModel.exists()) blockSideModel.delete();
                blockSideModel.createNewFile();
                blockSideModelWriter = new BufferedWriter(new FileWriter(blockSideModel));

                if(blockSideTallModel.exists()) blockSideTallModel.delete();
                blockSideTallModel.createNewFile();
                blockSideTallModelWriter = new BufferedWriter(new FileWriter(blockSideTallModel));

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
        writeToBlockModelFile(blockModelWriter, "  \"parent\": \"minecraft:block/wall_inventory\",");
        writeToBlockModelFile(blockModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockModelWriter, "    \"wall\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\"");
        writeToBlockModelFile(blockModelWriter, "  }");
        writeToBlockModelFile(blockModelWriter, "}");

        writeToBlockModelFile(blockPostModelWriter, "{");
        writeToBlockModelFile(blockPostModelWriter, "  \"parent\": \"minecraft:block/template_wall_post\",");
        writeToBlockModelFile(blockPostModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockPostModelWriter, "    \"wall\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\"");
        writeToBlockModelFile(blockPostModelWriter, "  }");
        writeToBlockModelFile(blockPostModelWriter, "}");

        writeToBlockModelFile(blockSideModelWriter, "{");
        writeToBlockModelFile(blockSideModelWriter, "  \"parent\": \"minecraft:block/template_wall_side\",");
        writeToBlockModelFile(blockSideModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockSideModelWriter, "    \"wall\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\"");
        writeToBlockModelFile(blockSideModelWriter, "  }");
        writeToBlockModelFile(blockSideModelWriter, "}");

        writeToBlockModelFile(blockSideTallModelWriter, "{");
        writeToBlockModelFile(blockSideTallModelWriter, "  \"parent\": \"minecraft:block/template_wall_side_tall\",");
        writeToBlockModelFile(blockSideTallModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockSideTallModelWriter, "    \"wall\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\"");
        writeToBlockModelFile(blockSideTallModelWriter, "  }");
        writeToBlockModelFile(blockSideTallModelWriter, "}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"multipart\": [");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_post\"");
        writeToBlockstateFile("        },");
        writeToBlockstateFile("        \"when\": {");
        writeToBlockstateFile("            \"up\": \"true\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_side\",");
        writeToBlockstateFile("            \"uvlock\": true");
        writeToBlockstateFile("        },");
        writeToBlockstateFile("        \"when\": {");
        writeToBlockstateFile("            \"north\": \"low\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_side\",");
        writeToBlockstateFile("            \"uvlock\": true,");
        writeToBlockstateFile("            \"y\": 90");
        writeToBlockstateFile("        },");
        writeToBlockstateFile("        \"when\": {");
        writeToBlockstateFile("            \"east\": \"low\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_side\",");
        writeToBlockstateFile("            \"uvlock\": true,");
        writeToBlockstateFile("            \"y\": 180");
        writeToBlockstateFile("        },");
        writeToBlockstateFile("        \"when\": {");
        writeToBlockstateFile("            \"south\": \"low\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_side\",");
        writeToBlockstateFile("            \"uvlock\": true,");
        writeToBlockstateFile("            \"y\": 270");
        writeToBlockstateFile("        },");
        writeToBlockstateFile("        \"when\": {");
        writeToBlockstateFile("            \"west\": \"low\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_side_tall\",");
        writeToBlockstateFile("            \"uvlock\": true");
        writeToBlockstateFile("        },");
        writeToBlockstateFile("        \"when\": {");
        writeToBlockstateFile("            \"north\": \"tall\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_side_tall\",");
        writeToBlockstateFile("            \"uvlock\": true,");
        writeToBlockstateFile("            \"y\": 90");
        writeToBlockstateFile("        },");
        writeToBlockstateFile("        \"when\": {");
        writeToBlockstateFile("            \"east\": \"tall\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_side_tall\",");
        writeToBlockstateFile("            \"uvlock\": true,");
        writeToBlockstateFile("            \"y\": 180");
        writeToBlockstateFile("        },");
        writeToBlockstateFile("        \"when\": {");
        writeToBlockstateFile("            \"south\": \"tall\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_side_tall\",");
        writeToBlockstateFile("            \"uvlock\": true,");
        writeToBlockstateFile("            \"y\": 270");
        writeToBlockstateFile("        },");
        writeToBlockstateFile("        \"when\": {");
        writeToBlockstateFile("            \"west\": \"tall\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     }");
        writeToBlockstateFile("  ]");
        writeToBlockstateFile("}");
    }

    public void writerInit() {
        try {
            itemModelWriter.close();
            blockModelWriter.close();
            blockPostModelWriter.close();
            blockSideModelWriter.close();
            blockSideTallModelWriter.close();
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
