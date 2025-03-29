package net.jitl.core.data.block_generation;

import net.jitl.core.data.BasicFileGenerator;
import net.jitl.core.init.JITL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockCropGenerator extends BasicFileGenerator {

    protected BufferedWriter blockModelWriter0, blockModelWriter1, blockModelWriter2, blockModelWriter3, blockModelWriter4, blockModelWriter5, blockModelWriter6, blockModelWriter7,
            blockstateWriter, itemModelWriter;

    public void generate(String name, int maxStages) {
        String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
        String blockModelDir0 = "../src/main/resources/assets/jitl/models/block/" + name + "_0.json";
        String blockModelDir1 = "../src/main/resources/assets/jitl/models/block/" + name + "_1.json";
        String blockModelDir2 = "../src/main/resources/assets/jitl/models/block/" + name + "_2.json";
        String blockModelDir3 = "../src/main/resources/assets/jitl/models/block/" + name + "_3.json";
        String blockModelDir4 = "../src/main/resources/assets/jitl/models/block/" + name + "_4.json";
        String blockModelDir5 = "../src/main/resources/assets/jitl/models/block/" + name + "_5.json";
        String blockModelDir6 = "../src/main/resources/assets/jitl/models/block/" + name + "_6.json";
        String blockModelDir7 = "../src/main/resources/assets/jitl/models/block/" + name + "_7.json";
        String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

        File itemModel = new File(itemModelDir);
        File blockModel0 = new File(blockModelDir0);
        File blockModel1 = new File(blockModelDir1);
        File blockModel2 = new File(blockModelDir2);
        File blockModel3 = new File(blockModelDir3);
        File blockModel4 = new File(blockModelDir4);
        File blockModel5 = new File(blockModelDir5);
        File blockModel6 = new File(blockModelDir6);
        File blockModel7 = new File(blockModelDir7);
        File blockstateModel = new File(blockstateDir);

        try {
            if(itemModel.exists()) itemModel.delete();
            itemModel.createNewFile();
            itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

            if(blockModel0.exists()) blockModel0.delete();
            blockModel0.createNewFile();
            blockModelWriter0 = new BufferedWriter(new FileWriter(blockModel0));

            if(maxStages > 1) {
                if (blockModel1.exists()) blockModel1.delete();
                blockModel1.createNewFile();
                blockModelWriter1 = new BufferedWriter(new FileWriter(blockModel1));
            }
            if(maxStages > 2) {
                if (blockModel2.exists()) blockModel2.delete();
                blockModel2.createNewFile();
                blockModelWriter2 = new BufferedWriter(new FileWriter(blockModel2));
            }
            if(maxStages > 3) {
                if(blockModel3.exists()) blockModel3.delete();
                blockModel3.createNewFile();
                blockModelWriter3 = new BufferedWriter(new FileWriter(blockModel3));
            }
            if(maxStages > 4) {
                if(blockModel4.exists()) blockModel4.delete();
                blockModel4.createNewFile();
                blockModelWriter4 = new BufferedWriter(new FileWriter(blockModel4));
            }
            if(maxStages > 5) {
                if(blockModel5.exists()) blockModel5.delete();
                blockModel5.createNewFile();
                blockModelWriter5 = new BufferedWriter(new FileWriter(blockModel5));

            }
            if(maxStages > 6) {
                if(blockModel6.exists()) blockModel6.delete();
                blockModel6.createNewFile();
                blockModelWriter6 = new BufferedWriter(new FileWriter(blockModel6));
            }

            if(maxStages > 7) {
                if(blockModel7.exists()) blockModel6.delete();
                blockModel7.createNewFile();
                blockModelWriter7 = new BufferedWriter(new FileWriter(blockModel7));
            }

            if(blockstateModel.exists()) blockstateModel.delete();
            blockstateModel.createNewFile();
            blockstateWriter = new BufferedWriter(new FileWriter(blockstateModel));
        } catch (IOException e) {
            e.printStackTrace();
        }

        generateBasicFile(name + "_" +(maxStages - 1));

        if(itemModelWriter != null) {
            getBlockItem(JITL.MOD_ID, name, maxStages);
            getBlockModel(JITL.MOD_ID, name, maxStages);
            getBlockstate(JITL.MOD_ID, name, maxStages);
            writerInit(maxStages);
        }
    }

    public void getBlockItem(String modID, String name, int maxStages) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"minecraft:item/generated\",");
        writeToItemModelFile("  \"textures\": {");
        writeToItemModelFile("    \"layer0\": \"" + modID + ":" + "block/" + name + "_" + (maxStages - 1) + "\"");
        writeToItemModelFile("  }");
        writeToItemModelFile("}");
    }

    public void getBlockModel(String modID, String name, int maxStages) {
        for(int i = 0; i < maxStages; i++) {
            writeToBlockModelFile(i, "{");
            writeToBlockModelFile(i,"  \"parent\": \"minecraft:block/crop\",");
            writeToBlockModelFile(i,"  \"textures\": {");
            writeToBlockModelFile(i,"    \"crop\": \"" + modID + ":" + "block/" + name + "_" + i + "\"");
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

    public void writerInit(int maxStages) {
        try {
            itemModelWriter.close();
            blockModelWriter0.close();
            if(maxStages > 1)
                blockModelWriter1.close();
            if(maxStages > 2)
                blockModelWriter2.close();
            if(maxStages > 3)
                blockModelWriter3.close();
            if(maxStages > 4)
                blockModelWriter4.close();
            if(maxStages > 5)
                blockModelWriter5.close();
            if(maxStages > 6)
                blockModelWriter6.close();
            if(maxStages > 7)
                blockModelWriter7.close();
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
            if(writerNumber == 4)
                writer = blockModelWriter4;
            if(writerNumber == 5)
                writer = blockModelWriter5;
            if(writerNumber == 6)
                writer = blockModelWriter6;
            if(writerNumber == 7)
                writer = blockModelWriter7;
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