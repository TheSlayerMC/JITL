package net.jitl.core.data.block_generation;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockDripstoneGenerator {

    protected BufferedWriter blockDownBase, blockDownFrustum, blockDownMiddle, blockDownTip, blockDownTipMerge, blockUpBase, blockUpFrustum, blockUpMiddle, blockUpTip, blockUpTipMerge, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.dripstoneBlockName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_";
            String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);

            File blockDownBaseModel = new File(blockModelDir + "down_base.json");
            File blockDownFrustumModel = new File(blockModelDir + "down_frustum.json");
            File blockDownMiddleModel = new File(blockModelDir + "down_middle.json");
            File blockDownTipModel = new File(blockModelDir + "down_tip.json");
            File blockDownTipMergeModel = new File(blockModelDir + "down_tip_merge.json");

            File blockUpBaseModel = new File(blockModelDir + "up_base.json");
            File blockUpFrustumModel = new File(blockModelDir + "up_frustum.json");
            File blockUpMiddleModel = new File(blockModelDir + "up_middle.json");
            File blockUpTipModel = new File(blockModelDir + "up_tip.json");
            File blockUpTipMergeModel = new File(blockModelDir + "up_tip_merge.json");
            
            File blockstateModel = new File(blockstateDir);
            
            try {
                if (itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if (blockDownBaseModel.exists()) blockDownBaseModel.delete();
                blockDownBaseModel.createNewFile();
                blockDownBase = new BufferedWriter(new FileWriter(blockDownBaseModel));

                if (blockDownFrustumModel.exists()) blockDownFrustumModel.delete();
                blockDownFrustumModel.createNewFile();
                blockDownFrustum = new BufferedWriter(new FileWriter(blockDownFrustumModel));

                if (blockDownMiddleModel.exists()) blockDownMiddleModel.delete();
                blockDownMiddleModel.createNewFile();
                blockDownMiddle = new BufferedWriter(new FileWriter(blockDownMiddleModel));

                if (blockDownTipModel.exists()) blockDownTipModel.delete();
                blockDownTipModel.createNewFile();
                blockDownTip = new BufferedWriter(new FileWriter(blockDownTipModel));

                if (blockDownTipMergeModel.exists()) blockDownTipMergeModel.delete();
                blockDownTipMergeModel.createNewFile();
                blockDownTipMerge = new BufferedWriter(new FileWriter(blockDownTipMergeModel));

                if (blockUpBaseModel.exists()) blockUpBaseModel.delete();
                blockUpBaseModel.createNewFile();
                blockUpBase = new BufferedWriter(new FileWriter(blockUpBaseModel));

                if (blockUpFrustumModel.exists()) blockUpFrustumModel.delete();
                blockUpFrustumModel.createNewFile();
                blockUpFrustum = new BufferedWriter(new FileWriter(blockUpFrustumModel));

                if (blockUpMiddleModel.exists()) blockUpMiddleModel.delete();
                blockUpMiddleModel.createNewFile();
                blockUpMiddle = new BufferedWriter(new FileWriter(blockUpMiddleModel));

                if (blockUpTipModel.exists()) blockUpTipModel.delete();
                blockUpTipModel.createNewFile();
                blockUpTip = new BufferedWriter(new FileWriter(blockUpTipModel));

                if (blockUpTipMergeModel.exists()) blockUpTipMergeModel.delete();
                blockUpTipMergeModel.createNewFile();
                blockUpTipMerge = new BufferedWriter(new FileWriter(blockUpTipMergeModel));
                
                if (blockstateModel.exists()) blockstateModel.delete();
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
        writeToItemModelFile("  \"parent\": \"minecraft:item/generated\",");
        writeToItemModelFile("  \"textures\": {");
        writeToItemModelFile("    \"layer0\": \"" + modID + ":" + "item/" + name + "\"");
        writeToItemModelFile("  },");
        writeToItemModelFile("  \"display\": {");
        writeToItemModelFile("    \"thirdperson_righthand\": {");
        writeToItemModelFile("      \"rotation\": [ 0, 100, 0 ],");
        writeToItemModelFile("      \"translation\": [ -1, -1, 0],");
        writeToItemModelFile("      \"scale\": [ 0.9, 0.9, 0.9 ]");
        writeToItemModelFile("    },");
        writeToItemModelFile("    \"firstperson_righthand\": {");
        writeToItemModelFile("      \"rotation\": [ 0, 100, 0 ],");
        writeToItemModelFile("      \"translation\": [ 0, -2, 0],");
        writeToItemModelFile("      \"scale\": [ 0.9, 0.9, 0.9 ]");
        writeToItemModelFile("    }");
        writeToItemModelFile("  }");
        writeToItemModelFile("}");
    }

    public void getBlockModel(String modID, String name) {
        writeToBlockModelFile(blockDownBase, "{");
        writeToBlockModelFile(blockDownBase, "  \"parent\": \"minecraft:block/pointed_dripstone\",");
        writeToBlockModelFile(blockDownBase, "  \"textures\": {");
        writeToBlockModelFile(blockDownBase, "    \"cross\": \"" + modID + ":block/" + name + "_down_base\"");
        writeToBlockModelFile(blockDownBase, "  }");
        writeToBlockModelFile(blockDownBase, "}");

        writeToBlockModelFile(blockDownFrustum, "{");
        writeToBlockModelFile(blockDownFrustum, "  \"parent\": \"minecraft:block/pointed_dripstone\",");
        writeToBlockModelFile(blockDownFrustum, "  \"textures\": {");
        writeToBlockModelFile(blockDownFrustum, "    \"cross\": \"" + modID + ":block/" + name + "_down_frustum\"");
        writeToBlockModelFile(blockDownFrustum, "  }");
        writeToBlockModelFile(blockDownFrustum, "}");

        writeToBlockModelFile(blockDownMiddle, "{");
        writeToBlockModelFile(blockDownMiddle, "  \"parent\": \"minecraft:block/pointed_dripstone\",");
        writeToBlockModelFile(blockDownMiddle, "  \"textures\": {");
        writeToBlockModelFile(blockDownMiddle, "    \"cross\": \"" + modID + ":block/" + name + "_down_middle\"");
        writeToBlockModelFile(blockDownMiddle, "  }");
        writeToBlockModelFile(blockDownMiddle, "}");

        writeToBlockModelFile(blockDownTip, "{");
        writeToBlockModelFile(blockDownTip, "  \"parent\": \"minecraft:block/pointed_dripstone\",");
        writeToBlockModelFile(blockDownTip, "  \"textures\": {");
        writeToBlockModelFile(blockDownTip, "    \"cross\": \"" + modID + ":block/" + name + "_down_tip\"");
        writeToBlockModelFile(blockDownTip, "  }");
        writeToBlockModelFile(blockDownTip, "}");

        writeToBlockModelFile(blockDownTipMerge, "{");
        writeToBlockModelFile(blockDownTipMerge, "  \"parent\": \"minecraft:block/pointed_dripstone\",");
        writeToBlockModelFile(blockDownTipMerge, "  \"textures\": {");
        writeToBlockModelFile(blockDownTipMerge, "    \"cross\": \"" + modID + ":block/" + name + "_down_tip_merge\"");
        writeToBlockModelFile(blockDownTipMerge, "  }");
        writeToBlockModelFile(blockDownTipMerge, "}");

        writeToBlockModelFile(blockUpBase, "{");
        writeToBlockModelFile(blockUpBase, "  \"parent\": \"minecraft:block/pointed_dripstone\",");
        writeToBlockModelFile(blockUpBase, "  \"textures\": {");
        writeToBlockModelFile(blockUpBase, "    \"cross\": \"" + modID + ":block/" + name + "_up_base\"");
        writeToBlockModelFile(blockUpBase, "  }");
        writeToBlockModelFile(blockUpBase, "}");

        writeToBlockModelFile(blockUpFrustum, "{");
        writeToBlockModelFile(blockUpFrustum, "  \"parent\": \"minecraft:block/pointed_dripstone\",");
        writeToBlockModelFile(blockUpFrustum, "  \"textures\": {");
        writeToBlockModelFile(blockUpFrustum, "    \"cross\": \"" + modID + ":block/" + name + "_up_frustum\"");
        writeToBlockModelFile(blockUpFrustum, "  }");
        writeToBlockModelFile(blockUpFrustum, "}");

        writeToBlockModelFile(blockUpMiddle, "{");
        writeToBlockModelFile(blockUpMiddle, "  \"parent\": \"minecraft:block/pointed_dripstone\",");
        writeToBlockModelFile(blockUpMiddle, "  \"textures\": {");
        writeToBlockModelFile(blockUpMiddle, "    \"cross\": \"" + modID + ":block/" + name + "_up_middle\"");
        writeToBlockModelFile(blockUpMiddle, "  }");
        writeToBlockModelFile(blockUpMiddle, "}");

        writeToBlockModelFile(blockUpTip, "{");
        writeToBlockModelFile(blockUpTip, "  \"parent\": \"minecraft:block/pointed_dripstone\",");
        writeToBlockModelFile(blockUpTip, "  \"textures\": {");
        writeToBlockModelFile(blockUpTip, "    \"cross\": \"" + modID + ":block/" + name + "_up_tip\"");
        writeToBlockModelFile(blockUpTip, "  }");
        writeToBlockModelFile(blockUpTip, "}");

        writeToBlockModelFile(blockUpTipMerge, "{");
        writeToBlockModelFile(blockUpTipMerge, "  \"parent\": \"minecraft:block/pointed_dripstone\",");
        writeToBlockModelFile(blockUpTipMerge, "  \"textures\": {");
        writeToBlockModelFile(blockUpTipMerge, "    \"cross\": \"" + modID + ":block/" + name + "_up_tip_merge\"");
        writeToBlockModelFile(blockUpTipMerge, "  }");
        writeToBlockModelFile(blockUpTipMerge, "}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        writeToBlockstateFile("    \"thickness=base,vertical_direction=down\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":block/" + name + "_down_base\"");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"thickness=base,vertical_direction=up\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":block/" + name + "_up_base\"");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"thickness=frustum,vertical_direction=down\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":block/" + name + "_down_frustum\"");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"thickness=frustum,vertical_direction=up\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":block/" + name + "_up_frustum\"");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"thickness=middle,vertical_direction=down\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":block/" + name + "_down_middle\"");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"thickness=middle,vertical_direction=up\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":block/" + name + "_up_middle\"");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"thickness=tip,vertical_direction=down\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":block/" + name + "_down_tip\"");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"thickness=tip,vertical_direction=up\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":block/" + name + "_up_tip\"");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"thickness=tip_merge,vertical_direction=down\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":block/" + name + "_down_tip_merge\"");
        writeToBlockstateFile("    },");
        writeToBlockstateFile("    \"thickness=tip_merge,vertical_direction=up\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":block/" + name + "_up_tip_merge\"");
        writeToBlockstateFile("    }");
        writeToBlockstateFile("  }");
        writeToBlockstateFile("}");
    }

    public void writerInit() {
        try {
            itemModelWriter.close();
            blockstateWriter.close();
            blockDownBase.close();
            blockDownFrustum.close();
            blockDownMiddle.close();
            blockDownTip.close();
            blockDownTipMerge.close();
            blockUpBase.close();
            blockUpFrustum.close();
            blockUpMiddle.close();
            blockUpTip.close();
            blockUpTipMerge.close();
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