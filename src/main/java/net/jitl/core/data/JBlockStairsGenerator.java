package net.jitl.core.data;

import net.jitl.core.init.JBlockProperties;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockStairsGenerator {

    protected BufferedWriter blockModelWriter, blockInnerModelWriter, blockOuterModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.stairsBlockName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockModelDir = "../src/main/resources/assets/jitl/models/block/" + name + ".json";
            String blockInnerModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_inner.json";
            String blockOuterModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_outer.json";
            String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockModel = new File(blockModelDir);
            File blockInnerModel = new File(blockInnerModelDir);
            File blockOuterModel = new File(blockOuterModelDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if(blockModel.exists()) blockModel.delete();
                blockModel.createNewFile();
                blockModelWriter = new BufferedWriter(new FileWriter(blockModel));

                if(blockInnerModel.exists()) blockInnerModel.delete();
                blockInnerModel.createNewFile();
                blockInnerModelWriter = new BufferedWriter(new FileWriter(blockInnerModel));

                if(blockOuterModel.exists()) blockOuterModel.delete();
                blockOuterModel.createNewFile();
                blockOuterModelWriter = new BufferedWriter(new FileWriter(blockOuterModel));

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
        writeToBlockModelFile(blockModelWriter, "  \"parent\": \"minecraft:block/stairs\",");
        writeToBlockModelFile(blockModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockModelWriter, "    \"bottom\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\",");
        writeToBlockModelFile(blockModelWriter, "    \"side\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\",");
        writeToBlockModelFile(blockModelWriter, "    \"top\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\"");
        writeToBlockModelFile(blockModelWriter, "  }");
        writeToBlockModelFile(blockModelWriter, "}");

        writeToBlockModelFile(blockInnerModelWriter, "{");
        writeToBlockModelFile(blockInnerModelWriter, "  \"parent\": \"minecraft:block/inner_stairs\",");
        writeToBlockModelFile(blockInnerModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockInnerModelWriter, "    \"bottom\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\",");
        writeToBlockModelFile(blockInnerModelWriter, "    \"side\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\",");
        writeToBlockModelFile(blockInnerModelWriter, "    \"top\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\"");
        writeToBlockModelFile(blockInnerModelWriter, "  }");
        writeToBlockModelFile(blockInnerModelWriter, "}");

        writeToBlockModelFile(blockOuterModelWriter, "{");
        writeToBlockModelFile(blockOuterModelWriter, "  \"parent\": \"minecraft:block/outer_stairs\",");
        writeToBlockModelFile(blockOuterModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockOuterModelWriter, "    \"bottom\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\",");
        writeToBlockModelFile(blockOuterModelWriter, "    \"side\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\",");
        writeToBlockModelFile(blockOuterModelWriter, "    \"top\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\"");
        writeToBlockModelFile(blockOuterModelWriter, "  }");
        writeToBlockModelFile(blockOuterModelWriter, "}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        writeToBlockstateFile("    \"facing=east,half=bottom,shape=inner_left\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_inner\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,half=bottom,shape=inner_right\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_inner\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,half=bottom,shape=outer_left\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_outer\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,half=bottom,shape=outer_right\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_outer\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,half=bottom,shape=straight\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,half=top,shape=inner_left\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_inner\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,half=top,shape=inner_right\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_inner\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,half=top,shape=outer_left\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_outer\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,half=top,shape=outer_right\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_outer\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,half=top,shape=straight\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,half=bottom,shape=inner_left\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_inner\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,half=bottom,shape=inner_right\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_inner\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,half=bottom,shape=outer_left\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_outer\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,half=bottom,shape=outer_right\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_outer\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,half=bottom,shape=straight\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,half=top,shape=inner_left\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_inner\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");

        writeToBlockstateFile("    \"facing=north,half=top,shape=inner_right\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_inner\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,half=top,shape=outer_left\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_outer\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,half=top,shape=outer_right\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_outer\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,half=top,shape=straight\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=bottom,shape=inner_left\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_inner\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=bottom,shape=inner_right\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_inner\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=bottom,shape=outer_left\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_outer\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=bottom,shape=outer_right\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_outer\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=bottom,shape=straight\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=top,shape=inner_left\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_inner\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=top,shape=inner_right\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_inner\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=top,shape=outer_left\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_outer\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=top,shape=outer_right\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_outer\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=top,shape=straight\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,half=bottom,shape=inner_left\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_inner\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,half=bottom,shape=inner_right\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_inner\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,half=bottom,shape=outer_left\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_outer\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");

        writeToBlockstateFile("    \"facing=west,half=bottom,shape=outer_right\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_outer\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,half=bottom,shape=straight\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,half=top,shape=inner_left\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_inner\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,half=top,shape=inner_right\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_inner\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,half=top,shape=outer_left\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_outer\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,half=top,shape=outer_right\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_outer\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,half=top,shape=straight\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("  }");
        writeToBlockstateFile("}");
    }

    public void writerInit() {
        try {
            itemModelWriter.close();
            blockModelWriter.close();
            blockInnerModelWriter.close();
            blockOuterModelWriter.close();
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