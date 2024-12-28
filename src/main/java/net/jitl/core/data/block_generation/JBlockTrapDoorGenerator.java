package net.jitl.core.data.block_generation;

import net.jitl.core.data.BasicFileGenerator;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockTrapDoorGenerator extends BasicFileGenerator {

    protected BufferedWriter blockBottomModelWriter, blockTopModelWriter, blockOpenModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.trapDoorBlockName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockBottomModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_bottom.json";
            String blockTopModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_top.json";
            String blockOpenModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_open.json";
            String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockBottomModel = new File(blockBottomModelDir);
            File blockTopModel = new File(blockTopModelDir);
            File blockOpenModel = new File(blockOpenModelDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if(blockBottomModel.exists()) blockBottomModel.delete();
                blockBottomModel.createNewFile();
                blockBottomModelWriter = new BufferedWriter(new FileWriter(blockBottomModel));

                if(blockTopModel.exists()) blockTopModel.delete();
                blockTopModel.createNewFile();
                blockTopModelWriter = new BufferedWriter(new FileWriter(blockTopModel));

                if(blockOpenModel.exists()) blockOpenModel.delete();
                blockOpenModel.createNewFile();
                blockOpenModelWriter = new BufferedWriter(new FileWriter(blockOpenModel));

                if(blockstateModel.exists()) blockstateModel.delete();
                blockstateModel.createNewFile();
                blockstateWriter = new BufferedWriter(new FileWriter(blockstateModel));

            } catch (IOException e) {
                e.printStackTrace();
            }
            getBlockItem(JITL.MODID, name);
            getBlockModel(JITL.MODID, name);
            getBlockstate(JITL.MODID, name);
            generateBasicFile(name, "_bottom");

            writerInit();
        }
    }

    public void getBlockItem(String modID, String name) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"" + modID + ":block/" + name + "_bottom\"");
        writeToItemModelFile("}");
    }

    public void getBlockModel(String modID, String name) {
        writeToBlockModelFile(blockBottomModelWriter, "{");
        writeToBlockModelFile(blockBottomModelWriter, "  \"parent\": \"minecraft:block/template_orientable_trapdoor_bottom\",");
        writeToBlockModelFile(blockBottomModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockBottomModelWriter, "    \"texture\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockModelFile(blockBottomModelWriter, "  }");
        writeToBlockModelFile(blockBottomModelWriter, "}");

        writeToBlockModelFile(blockTopModelWriter, "{");
        writeToBlockModelFile(blockTopModelWriter, "  \"parent\": \"minecraft:block/template_orientable_trapdoor_top\",");
        writeToBlockModelFile(blockTopModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockTopModelWriter, "    \"texture\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockModelFile(blockTopModelWriter, "  }");
        writeToBlockModelFile(blockTopModelWriter, "}");

        writeToBlockModelFile(blockOpenModelWriter, "{");
        writeToBlockModelFile(blockOpenModelWriter, "  \"parent\": \"minecraft:block/template_orientable_trapdoor_open\",");
        writeToBlockModelFile(blockOpenModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockOpenModelWriter, "    \"texture\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockModelFile(blockOpenModelWriter, "  }");
        writeToBlockModelFile(blockOpenModelWriter, "}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        writeToBlockstateFile("    \"facing=east,half=bottom,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_bottom\",");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,half=bottom,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_open\",");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,half=top,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_top\",");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,half=top,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_open\",");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,half=bottom,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_bottom\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,half=bottom,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_open\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,half=top,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_top\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,half=top,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_open\",");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=bottom,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_bottom\",");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=bottom,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_open\",");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=top,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_top\",");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=top,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_open\",");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 0");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,half=bottom,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_bottom\",");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,half=bottom,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_open\",");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,half=top,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_top\",");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,half=top,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_open\",");
        writeToBlockstateFile("         \"x\": 180,");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("  }");
        writeToBlockstateFile("}");
    }

    public void writerInit() {
        try {
            itemModelWriter.close();
            blockBottomModelWriter.close();
            blockTopModelWriter.close();
            blockOpenModelWriter.close();
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