package net.jitl.core.data.block_generation;

import net.jitl.core.data.BasicFileGenerator;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockPaneGenerator extends BasicFileGenerator {

    protected BufferedWriter blockPostModelWriter, blockPostEndsWriter, blockCapModelWriter, blockSideModelWriter, blockCapAltModelWriter, blockSideAltModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.paneBlockName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            String blockPostModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_post.json";
            String blockPostEndsModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_post_ends.json";
            String blockPostCapModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_cap.json";
            String blockSideModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_side.json";
            String blockPostAltCapModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_cap_alt.json";
            String blockSideAltModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_side_alt.json";

            File itemModel = new File(itemModelDir);
            File blockPostModel = new File(blockPostModelDir);
            File blockPostEndsModel = new File(blockPostEndsModelDir);
            File blockPostCapModel = new File(blockPostCapModelDir);
            File blockSideModel = new File(blockSideModelDir);
            File blockPostAltCapModel = new File(blockPostAltCapModelDir);
            File blockSideAltModel = new File(blockSideAltModelDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if(blockPostModel.exists()) blockPostModel.delete();
                blockPostModel.createNewFile();
                blockPostModelWriter = new BufferedWriter(new FileWriter(blockPostModel));

                if(blockPostEndsModel.exists()) blockPostEndsModel.delete();
                blockPostEndsModel.createNewFile();
                blockPostEndsWriter = new BufferedWriter(new FileWriter(blockPostEndsModel));

                if(blockPostCapModel.exists()) blockPostCapModel.delete();
                blockPostCapModel.createNewFile();
                blockCapModelWriter = new BufferedWriter(new FileWriter(blockPostCapModel));

                if(blockSideModel.exists()) blockSideModel.delete();
                blockSideModel.createNewFile();
                blockSideModelWriter = new BufferedWriter(new FileWriter(blockSideModel));

                if(blockPostAltCapModel.exists()) blockPostAltCapModel.delete();
                blockPostAltCapModel.createNewFile();
                blockCapAltModelWriter = new BufferedWriter(new FileWriter(blockPostAltCapModel));

                if(blockSideAltModel.exists()) blockSideAltModel.delete();
                blockSideAltModel.createNewFile();
                blockSideAltModelWriter = new BufferedWriter(new FileWriter(blockSideAltModel));

                if(blockstateModel.exists()) blockstateModel.delete();
                blockstateModel.createNewFile();
                blockstateWriter = new BufferedWriter(new FileWriter(blockstateModel));

            } catch (IOException e) {
                e.printStackTrace();
            }
            getBlockItem(JITL.MOD_ID, name);
            getBlockModel(JITL.MOD_ID, name);
            getBlockstate(JITL.MOD_ID, name);
            generateBasicFile(true, name);

            writerInit();
        }
    }

    public void getBlockItem(String modID, String name) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"minecraft:item/generated\",");
        writeToItemModelFile("  \"textures\": {");
        writeToItemModelFile("    \"layer0\": \"" + modID + ":" + "block/" + name + "\"");
        writeToItemModelFile("  }");
        writeToItemModelFile("}");
    }

    public void getBlockModel(String modID, String name) {
        writeToBlockModelFile(blockPostModelWriter, "{");
        writeToBlockModelFile(blockPostModelWriter, "  \"parent\": \"minecraft:block/iron_bars_post\",");
        writeToBlockModelFile(blockPostModelWriter, "  \"ambientocclusion\": false,");
        writeToBlockModelFile(blockPostModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockPostModelWriter, "    \"particle\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockModelFile(blockPostModelWriter, "    \"bars\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockModelFile(blockPostModelWriter, "  }");
        writeToBlockModelFile(blockPostModelWriter, "}");

        writeToBlockModelFile(blockPostEndsWriter, "{");
        writeToBlockModelFile(blockPostEndsWriter, "  \"parent\": \"minecraft:block/iron_bars_post_ends\",");
        writeToBlockModelFile(blockPostEndsWriter, "  \"ambientocclusion\": false,");
        writeToBlockModelFile(blockPostEndsWriter, "  \"textures\": {");
        writeToBlockModelFile(blockPostEndsWriter, "    \"particle\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockModelFile(blockPostEndsWriter, "    \"edge\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockModelFile(blockPostEndsWriter, "  }");
        writeToBlockModelFile(blockPostEndsWriter, "}");

        writeToBlockModelFile(blockCapModelWriter, "{");
        writeToBlockModelFile(blockCapModelWriter, "  \"parent\": \"minecraft:block/iron_bars_cap\",");
        writeToBlockModelFile(blockCapModelWriter, "  \"ambientocclusion\": false,");
        writeToBlockModelFile(blockCapModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockCapModelWriter, "    \"particle\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockModelFile(blockCapModelWriter, "    \"bars\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockModelFile(blockCapModelWriter, "    \"edge\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockModelFile(blockCapModelWriter, "  }");
        writeToBlockModelFile(blockCapModelWriter, "}");

        writeToBlockModelFile(blockCapAltModelWriter, "{");
        writeToBlockModelFile(blockCapAltModelWriter, "  \"parent\": \"minecraft:block/iron_bars_cap_alt\",");
        writeToBlockModelFile(blockCapAltModelWriter, "  \"ambientocclusion\": false,");
        writeToBlockModelFile(blockCapAltModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockCapAltModelWriter, "    \"particle\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockModelFile(blockCapAltModelWriter, "    \"bars\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockModelFile(blockCapAltModelWriter, "    \"edge\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockModelFile(blockCapAltModelWriter, "  }");
        writeToBlockModelFile(blockCapAltModelWriter, "}");

        writeToBlockModelFile(blockSideModelWriter, "{");
        writeToBlockModelFile(blockSideModelWriter, "  \"parent\": \"minecraft:block/iron_bars_side\",");
        writeToBlockModelFile(blockSideModelWriter, "  \"ambientocclusion\": false,");
        writeToBlockModelFile(blockSideModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockSideModelWriter, "    \"particle\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockModelFile(blockSideModelWriter, "    \"bars\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockModelFile(blockSideModelWriter, "    \"edge\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockModelFile(blockSideModelWriter, "  }");
        writeToBlockModelFile(blockSideModelWriter, "}");

        writeToBlockModelFile(blockSideAltModelWriter, "{");
        writeToBlockModelFile(blockSideAltModelWriter, "  \"parent\": \"minecraft:block/iron_bars_side_alt\",");
        writeToBlockModelFile(blockSideAltModelWriter, "  \"ambientocclusion\": false,");
        writeToBlockModelFile(blockSideAltModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockSideAltModelWriter, "    \"particle\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockModelFile(blockSideAltModelWriter, "    \"bars\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockModelFile(blockSideAltModelWriter, "    \"edge\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockModelFile(blockSideAltModelWriter, "  }");
        writeToBlockModelFile(blockSideAltModelWriter, "}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"multipart\": [");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_post_ends\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_post\"");
        writeToBlockstateFile("        },");
        writeToBlockstateFile("        \"when\": {");
        writeToBlockstateFile("            \"east\": \"false\",");
        writeToBlockstateFile("            \"north\": \"false\",");
        writeToBlockstateFile("            \"south\": \"false\",");
        writeToBlockstateFile("            \"west\": \"false\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_cap\"");
        writeToBlockstateFile("        },");
        writeToBlockstateFile("        \"when\": {");
        writeToBlockstateFile("            \"east\": \"false\",");
        writeToBlockstateFile("            \"north\": \"true\",");
        writeToBlockstateFile("            \"south\": \"false\",");
        writeToBlockstateFile("            \"west\": \"false\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_cap\",");
        writeToBlockstateFile("            \"y\": 90");
        writeToBlockstateFile("        },");
        writeToBlockstateFile("        \"when\": {");
        writeToBlockstateFile("            \"east\": \"true\",");
        writeToBlockstateFile("            \"north\": \"false\",");
        writeToBlockstateFile("            \"south\": \"false\",");
        writeToBlockstateFile("            \"west\": \"false\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_cap_alt\"");
        writeToBlockstateFile("        },");
        writeToBlockstateFile("        \"when\": {");
        writeToBlockstateFile("            \"east\": \"false\",");
        writeToBlockstateFile("            \"north\": \"false\",");
        writeToBlockstateFile("            \"south\": \"true\",");
        writeToBlockstateFile("            \"west\": \"false\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_cap_alt\",");
        writeToBlockstateFile("            \"y\": 90");
        writeToBlockstateFile("        },");
        writeToBlockstateFile("        \"when\": {");
        writeToBlockstateFile("            \"east\": \"false\",");
        writeToBlockstateFile("            \"north\": \"false\",");
        writeToBlockstateFile("            \"south\": \"false\",");
        writeToBlockstateFile("            \"west\": \"true\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_side\"");
        writeToBlockstateFile("        },");
        writeToBlockstateFile("        \"when\": {");
        writeToBlockstateFile("            \"north\": \"true\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_side\",");
        writeToBlockstateFile("            \"y\": 90");
        writeToBlockstateFile("        },");
        writeToBlockstateFile("        \"when\": {");
        writeToBlockstateFile("            \"east\": \"true\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_side_alt\"");
        writeToBlockstateFile("        },");
        writeToBlockstateFile("        \"when\": {");
        writeToBlockstateFile("            \"south\": \"true\"");
        writeToBlockstateFile("        }");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("     {");
        writeToBlockstateFile("        \"apply\": {");
        writeToBlockstateFile("            \"model\": \"" + modID + ":" + "block/" + name + "_side_alt\",");
        writeToBlockstateFile("            \"y\": 90");
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
            blockPostModelWriter.close();
            blockPostEndsWriter.close();
            blockCapModelWriter.close();
            blockSideModelWriter.close();
            blockCapAltModelWriter.close();
            blockSideAltModelWriter.close();
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
