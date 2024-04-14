package net.jitl.core.data.block_generation;

import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockFenceGenerator {

    protected BufferedWriter blockModelWriter, blockPostModelWriter, blockSideModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.fenceBlockName) {
            String itemModelDir = "../../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockModelDir = "../../src/main/resources/assets/jitl/models/block/" + name + "_inventory.json";
            String blockPostModelDir = "../../src/main/resources/assets/jitl/models/block/" + name + "_post.json";
            String blockSideModelDir = "../../src/main/resources/assets/jitl/models/block/" + name + "_side.json";
            String blockstateDir = "../../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockModel = new File(blockModelDir);
            File blockPostModel = new File(blockPostModelDir);
            File blockSideModel = new File(blockSideModelDir);
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
        writeToItemModelFile("  \"parent\": \"" + modID + ":block/" + name + "_inventory\"");
        writeToItemModelFile("}");
    }

    public void getBlockModel(String modID, String name) {
        writeToBlockModelFile(blockModelWriter, "{");
        writeToBlockModelFile(blockModelWriter, "  \"parent\": \"minecraft:block/fence_inventory\",");
        writeToBlockModelFile(blockModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockModelWriter, "    \"texture\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\"");
        writeToBlockModelFile(blockModelWriter, "  }");
        writeToBlockModelFile(blockModelWriter, "}");

        writeToBlockModelFile(blockPostModelWriter, "{");
        writeToBlockModelFile(blockPostModelWriter, "  \"parent\": \"minecraft:block/fence_post\",");
        writeToBlockModelFile(blockPostModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockPostModelWriter, "    \"texture\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\"");
        writeToBlockModelFile(blockPostModelWriter, "  }");
        writeToBlockModelFile(blockPostModelWriter, "}");

        writeToBlockModelFile(blockSideModelWriter, "{");
        writeToBlockModelFile(blockSideModelWriter, "  \"parent\": \"minecraft:block/fence_side\",");
        writeToBlockModelFile(blockSideModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockSideModelWriter, "    \"texture\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\"");
        writeToBlockModelFile(blockSideModelWriter, "  }");
        writeToBlockModelFile(blockSideModelWriter, "}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"multipart\": [");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_post\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_side\",");
        writeToBlockstateFile("            \"uvlock\": true");
        writeToBlockstateFile("        },");
        writeToBlockstateFile("        \"when\": {");
        writeToBlockstateFile("            \"north\": \"true\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_side\",");
        writeToBlockstateFile("            \"uvlock\": true,");
        writeToBlockstateFile("            \"y\": 90");
        writeToBlockstateFile("        },");
        writeToBlockstateFile("        \"when\": {");
        writeToBlockstateFile("            \"east\": \"true\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_side\",");
        writeToBlockstateFile("            \"uvlock\": true,");
        writeToBlockstateFile("            \"y\": 180");
        writeToBlockstateFile("        },");
        writeToBlockstateFile("        \"when\": {");
        writeToBlockstateFile("            \"south\": \"true\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_side\",");
        writeToBlockstateFile("            \"uvlock\": true,");
        writeToBlockstateFile("            \"y\": 270");
        writeToBlockstateFile("        },");
        writeToBlockstateFile("        \"when\": {");
        writeToBlockstateFile("            \"west\": \"true\"");
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
