package net.jitl.core.data.block_generation;

import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockGateGenerator {

    protected BufferedWriter blockModelWriter, blockGateOpenModelWriter, blockGateWallModelWriter, blockGateWallOpenModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.gateBlockName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockModelDir = "../src/main/resources/assets/jitl/models/block/" + name + ".json";
            String blockOpenModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_open.json";
            String blockWallModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_wall.json";
            String blockWallOpenModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_wall_open.json";
            String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockModel = new File(blockModelDir);
            File blockOpenModel = new File(blockOpenModelDir);
            File blockWallModel = new File(blockWallModelDir);
            File blockWallOpenModel = new File(blockWallOpenModelDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if(blockModel.exists()) blockModel.delete();
                blockModel.createNewFile();
                blockModelWriter = new BufferedWriter(new FileWriter(blockModel));

                if(blockOpenModel.exists()) blockOpenModel.delete();
                blockOpenModel.createNewFile();
                blockGateOpenModelWriter = new BufferedWriter(new FileWriter(blockOpenModel));

                if(blockWallModel.exists()) blockWallModel.delete();
                blockWallModel.createNewFile();
                blockGateWallModelWriter = new BufferedWriter(new FileWriter(blockWallModel));

                if(blockWallOpenModel.exists()) blockWallOpenModel.delete();
                blockWallOpenModel.createNewFile();
                blockGateWallOpenModelWriter = new BufferedWriter(new FileWriter(blockWallOpenModel));

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
        writeToBlockModelFile(blockModelWriter, "  \"parent\": \"minecraft:block/template_fence_gate\",");
        writeToBlockModelFile(blockModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockModelWriter, "    \"texture\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\"");
        writeToBlockModelFile(blockModelWriter, "  }");
        writeToBlockModelFile(blockModelWriter, "}");

        writeToBlockModelFile(blockGateOpenModelWriter, "{");
        writeToBlockModelFile(blockGateOpenModelWriter, "  \"parent\": \"minecraft:block/template_fence_gate_open\",");
        writeToBlockModelFile(blockGateOpenModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockGateOpenModelWriter, "    \"texture\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\"");
        writeToBlockModelFile(blockGateOpenModelWriter, "  }");
        writeToBlockModelFile(blockGateOpenModelWriter, "}");

        writeToBlockModelFile(blockGateWallModelWriter, "{");
        writeToBlockModelFile(blockGateWallModelWriter, "  \"parent\": \"minecraft:block/template_fence_gate_wall\",");
        writeToBlockModelFile(blockGateWallModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockGateWallModelWriter, "    \"texture\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\"");
        writeToBlockModelFile(blockGateWallModelWriter, "  }");
        writeToBlockModelFile(blockGateWallModelWriter, "}");

        writeToBlockModelFile(blockGateWallOpenModelWriter, "{");
        writeToBlockModelFile(blockGateWallOpenModelWriter, "  \"parent\": \"minecraft:block/template_fence_gate_wall_open\",");
        writeToBlockModelFile(blockGateWallOpenModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockGateWallOpenModelWriter, "    \"texture\": \"" + modID + ":" + "block/" + JBlockProperties.getTextureFromName(name) + "\"");
        writeToBlockModelFile(blockGateWallOpenModelWriter, "  }");
        writeToBlockModelFile(blockGateWallOpenModelWriter, "}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        writeToBlockstateFile("    \"facing=east,in_wall=false,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,in_wall=false,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_open\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,in_wall=true,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_wall\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,in_wall=true,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_wall_open\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,in_wall=false,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,in_wall=false,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_open\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,in_wall=true,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_wall\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,in_wall=true,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_wall_open\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,in_wall=false,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"uvlock\": true");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,in_wall=false,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_open\",");
        writeToBlockstateFile("         \"uvlock\": true");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,in_wall=true,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_wall\",");
        writeToBlockstateFile("         \"uvlock\": true");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,in_wall=true,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_wall_open\",");
        writeToBlockstateFile("         \"uvlock\": true");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,in_wall=false,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,in_wall=false,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_open\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,in_wall=true,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_wall\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,in_wall=true,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_wall_open\",");
        writeToBlockstateFile("         \"uvlock\": true,");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("  }");
        writeToBlockstateFile("}");
    }

    public void writerInit() {
        try {
            itemModelWriter.close();
            blockModelWriter.close();
            blockGateOpenModelWriter.close();
            blockGateWallModelWriter.close();
            blockGateWallOpenModelWriter.close();
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