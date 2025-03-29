package net.jitl.core.data.block_generation;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockPortalGenerator {

    protected BufferedWriter blockNSModelWriter, blockEWModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.portalBlockName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockNSModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_ns.json";
            String blockEWModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_ew.json";
            String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockNSModel = new File(blockNSModelDir);
            File blockEWModel = new File(blockEWModelDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if(blockNSModel.exists()) blockNSModel.delete();
                blockNSModel.createNewFile();
                blockNSModelWriter = new BufferedWriter(new FileWriter(blockNSModel));

                if(blockEWModel.exists()) blockEWModel.delete();
                blockEWModel.createNewFile();
                blockEWModelWriter = new BufferedWriter(new FileWriter(blockEWModelDir));

                if(blockstateModel.exists()) blockstateModel.delete();
                blockstateModel.createNewFile();
                blockstateWriter = new BufferedWriter(new FileWriter(blockstateModel));
            } catch (IOException e) {
                e.printStackTrace();
            }
            getBlockItem(JITL.MOD_ID, name);
            getBlockNSModel(JITL.MOD_ID, name);
            getBlockEWModel(JITL.MOD_ID, name);
            getBlockstate(JITL.MOD_ID, name);
            writerInit();
        }
    }

    public void getBlockItem(String modID, String name) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"" + modID + ":block/" + name + "_ns\"");
        writeToItemModelFile("}");
    }

    public void getBlockNSModel(String modID, String name) {
        writeToBlockModelFile(blockNSModelWriter, "{");
        writeToBlockModelFile(blockNSModelWriter, "  \"parent\": \"minecraft:block/nether_portal_ns\",");
        writeToBlockModelFile(blockNSModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockNSModelWriter, "    \"particle\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockModelFile(blockNSModelWriter, "    \"portal\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockModelFile(blockNSModelWriter, "  }");
        writeToBlockModelFile(blockNSModelWriter, "}");
    }

    public void getBlockEWModel(String modID, String name) {
        writeToBlockModelFile(blockEWModelWriter, "{");
        writeToBlockModelFile(blockEWModelWriter, "  \"parent\": \"minecraft:block/nether_portal_ew\",");
        writeToBlockModelFile(blockEWModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockEWModelWriter, "    \"particle\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockModelFile(blockEWModelWriter, "    \"portal\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockModelFile(blockEWModelWriter, "  }");
        writeToBlockModelFile(blockEWModelWriter, "}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        writeToBlockstateFile("    \"axis=x\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_ns\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"axis=z\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_ew\"");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("  }");
        writeToBlockstateFile("}");
    }

    public void writerInit() {
        try {
            itemModelWriter.close();
            blockEWModelWriter.close();
            blockNSModelWriter.close();
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