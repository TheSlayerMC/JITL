package net.jitl.core.data.block_generation;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockBushGenerator {

    protected BufferedWriter blockModelWriter0, blockModelWriter1, blockModelWriter2, blockModelWriter3, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.bushBlockName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockModelDir0 = "../src/main/resources/assets/jitl/models/block/" + name + "_0.json";
            String blockModelDir1 = "../src/main/resources/assets/jitl/models/block/" + name + "_1.json";
            String blockModelDir2 = "../src/main/resources/assets/jitl/models/block/" + name + "_2.json";
            String blockModelDir3 = "../src/main/resources/assets/jitl/models/block/" + name + "_3.json";
            String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockModel0 = new File(blockModelDir0);
            File blockModel1 = new File(blockModelDir1);
            File blockModel2 = new File(blockModelDir2);
            File blockModel3 = new File(blockModelDir3);
            File blockstateModel = new File(blockstateDir);

            try {
                if (itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if (blockModel0.exists()) blockModel0.delete();
                blockModel0.createNewFile();
                blockModelWriter0 = new BufferedWriter(new FileWriter(blockModel0));

                if (blockModel1.exists()) blockModel1.delete();
                blockModel1.createNewFile();
                blockModelWriter1 = new BufferedWriter(new FileWriter(blockModel1));

                if (blockModel2.exists()) blockModel2.delete();
                blockModel2.createNewFile();
                blockModelWriter2 = new BufferedWriter(new FileWriter(blockModel2));

                if (blockModel3.exists()) blockModel3.delete();
                blockModel3.createNewFile();
                blockModelWriter3 = new BufferedWriter(new FileWriter(blockModel3));

                if (blockstateModel.exists()) blockstateModel.delete();
                blockstateModel.createNewFile();
                blockstateWriter = new BufferedWriter(new FileWriter(blockstateModel));
            } catch (IOException e) {
                e.printStackTrace();
            }

            getBlockItem(JITL.MOD_ID, name);
            getBlockModel(JITL.MOD_ID, name, 4);
            getBlockstate(JITL.MOD_ID, name, 4);
            writerInit();
        }
    }

    public void getBlockItem(String modID, String name) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"minecraft:item/generated\",");
        writeToItemModelFile("  \"textures\": {");
        writeToItemModelFile("    \"layer0\": \"" + modID + ":" + "block/" + name + "_3\"");
        writeToItemModelFile("  }");
        writeToItemModelFile("}");
    }

    public void getBlockModel(String modID, String name, int maxStages) {
        for(int i = 0; i < maxStages; i++) {
            writeToBlockModelFile(i, "{");
            writeToBlockModelFile(i,"  \"parent\": \"minecraft:block/cross\",");
            writeToBlockModelFile(i,"  \"textures\": {");
            writeToBlockModelFile(i,"    \"cross\": \"" + modID + ":" + "block/" + name + "_" + i + "\"");
            writeToBlockModelFile(i,"  }");
            writeToBlockModelFile(i,"}");
        }
    }

    public void getBlockstate(String modID, String name, int maxStages) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        int j = 0;
        for(int i = 0; i < maxStages; i++) {
            j++;
            String end = j == maxStages ? "}" : "},";
            writeToBlockstateFile("   \"age=" + i + "\": {");
            writeToBlockstateFile("       \"model\": \"" + modID + ":" + "block/" + name + "_" + i + "\"");
            writeToBlockstateFile("    " + end);
        }
        writeToBlockstateFile("  }");
        writeToBlockstateFile("}");
    }

    public void writerInit() {
        try {
            itemModelWriter.close();
            blockModelWriter0.close();
            blockModelWriter1.close();
            blockModelWriter2.close();
            blockModelWriter3.close();
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

    public void writeToBlockModelFile(int writerNumber, String text){
        BufferedWriter writer = blockModelWriter0;
        try {
            if(writerNumber == 0)
                writer = blockModelWriter0;
            if(writerNumber == 1)
                writer = blockModelWriter1;
            if(writerNumber == 2)
                writer = blockModelWriter2;
            if(writerNumber == 3)
                writer = blockModelWriter3;
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