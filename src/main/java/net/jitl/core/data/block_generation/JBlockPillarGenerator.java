package net.jitl.core.data.block_generation;

import net.jitl.core.data.BasicFileGenerator;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockPillarGenerator extends BasicFileGenerator {

    protected BufferedWriter blockModelWriter, blockHorModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.logBlockName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockModelDir = "../src/main/resources/assets/jitl/models/block/" + name + ".json";
            String blockHorModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_horizontal.json";
            String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockModel = new File(blockModelDir);
            File blockHorModel = new File(blockHorModelDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if (itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if (blockModel.exists()) blockModel.delete();
                blockModel.createNewFile();
                blockModelWriter = new BufferedWriter(new FileWriter(blockModel));

                if (blockHorModel.exists()) blockHorModel.delete();
                blockHorModel.createNewFile();
                blockHorModelWriter = new BufferedWriter(new FileWriter(blockHorModel));

                if (blockstateModel.exists()) blockstateModel.delete();
                blockstateModel.createNewFile();
                blockstateWriter = new BufferedWriter(new FileWriter(blockstateModel));
            } catch (IOException e) {
                e.printStackTrace();
            }
            getBlockItem(JITL.MODID, name);
            getBlockModel(JITL.MODID, name);
            getBlockHorModel(JITL.MODID, name);
            getBlockstate(JITL.MODID, name);
            generateBasicFile(name);

            writerInit();
        }
    }

    public void getBlockItem(String modID, String name) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"" + modID + ":block/" + name + "\"");
        writeToItemModelFile("}");
    }

    public void getBlockModel(String modID, String name) {
        writeToBlockModelFile("{");
        writeToBlockModelFile("  \"parent\": \"minecraft:block/cube_column\",");
        writeToBlockModelFile("  \"textures\": {");
        writeToBlockModelFile("    \"end\": \"" + modID + ":" + "block/" + name + "_top\",");
        writeToBlockModelFile("    \"side\": \"" + modID + ":" + "block/" + name + "_side\"");
        writeToBlockModelFile("  }");
        writeToBlockModelFile("}");
    }

    public void getBlockHorModel(String modID, String name) {
        writeToBlockHorModelFile("{");
        writeToBlockHorModelFile("  \"parent\": \"minecraft:block/cube_column_horizontal\",");
        writeToBlockHorModelFile("  \"textures\": {");
        writeToBlockHorModelFile("    \"end\": \"" + modID + ":" + "block/" + name + "_top\",");
        writeToBlockHorModelFile("    \"side\": \"" + modID + ":" + "block/" + name + "_side\"");
        writeToBlockHorModelFile("  }");
        writeToBlockHorModelFile("}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        writeToBlockstateFile("    \"axis=x\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_horizontal\",");
        writeToBlockstateFile("         \"x\": 90,");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"axis=y\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"axis=z\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_horizontal\",");
        writeToBlockstateFile("         \"x\": 90");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("  }");
        writeToBlockstateFile("}");
    }

    public void writerInit() {
        try {
            itemModelWriter.close();
            blockModelWriter.close();
            blockHorModelWriter.close();
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

    public void writeToBlockHorModelFile(String text){
        try {
            blockHorModelWriter.write(text + "\n");
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