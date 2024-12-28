package net.jitl.core.data.block_generation;

import net.jitl.core.data.BasicFileGenerator;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockDoorGenerator extends BasicFileGenerator {

    protected BufferedWriter blockBLModelWriter, blockBRModelWriter, blockTLModelWriter, blockTRModelWriter,
            blockBLOModelWriter, blockBROModelWriter, blockTLOModelWriter, blockTROModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.doorBlockName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockBLModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_bottom_left.json";
            String blockBRModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_bottom_right.json";
            String blockTLModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_top_left.json";
            String blockTRModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_top_right.json";
            String blockBLOModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_bottom_left_open.json";
            String blockBROModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_bottom_right_open.json";
            String blockTLOModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_top_left_open.json";
            String blockTROModelDir = "../src/main/resources/assets/jitl/models/block/" + name + "_top_right_open.json";
            String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockBLModel = new File(blockBLModelDir);
            File blockBRModel = new File(blockBRModelDir);
            File blockTLModel = new File(blockTLModelDir);
            File blockTRModel = new File(blockTRModelDir);
            File blockBLOModel = new File(blockBLOModelDir);
            File blockBROModel = new File(blockBROModelDir);
            File blockTLOModel = new File(blockTLOModelDir);
            File blockTROModel = new File(blockTROModelDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if(blockBLModel.exists()) blockBLModel.delete();
                blockBLModel.createNewFile();
                blockBLModelWriter = new BufferedWriter(new FileWriter(blockBLModel));

                if(blockBRModel.exists()) blockBRModel.delete();
                blockBRModel.createNewFile();
                blockBRModelWriter = new BufferedWriter(new FileWriter(blockBRModel));

                if(blockTRModel.exists()) blockTRModel.delete();
                blockTRModel.createNewFile();
                blockTRModelWriter = new BufferedWriter(new FileWriter(blockTRModel));

                if(blockTLModel.exists()) blockTLModel.delete();
                blockTLModel.createNewFile();
                blockTLModelWriter = new BufferedWriter(new FileWriter(blockTLModel));

                if(blockBLOModel.exists()) blockBLOModel.delete();
                blockBLOModel.createNewFile();
                blockBLOModelWriter = new BufferedWriter(new FileWriter(blockBLOModel));

                if(blockBROModel.exists()) blockBROModel.delete();
                blockBROModel.createNewFile();
                blockBROModelWriter = new BufferedWriter(new FileWriter(blockBROModel));

                if(blockTROModel.exists()) blockTROModel.delete();
                blockTROModel.createNewFile();
                blockTROModelWriter = new BufferedWriter(new FileWriter(blockTROModel));

                if(blockTLOModel.exists()) blockTLOModel.delete();
                blockTLOModel.createNewFile();
                blockTLOModelWriter = new BufferedWriter(new FileWriter(blockTLOModel));

                if(blockstateModel.exists()) blockstateModel.delete();
                blockstateModel.createNewFile();
                blockstateWriter = new BufferedWriter(new FileWriter(blockstateModel));

            } catch (IOException e) {
                e.printStackTrace();
            }
            getBlockItem(JITL.MODID, name);
            getBlockModel(JITL.MODID, name);
            getBlockstate(JITL.MODID, name);
            generateBasicFile(true, name);

            writerInit();
        }
    }

    public void getBlockItem(String modID, String name) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"minecraft:item/generated\",");
        writeToItemModelFile("  \"textures\": {");
        writeToItemModelFile("    \"layer0\": \"" + modID + ":" + "item/" + name + "\"");
        writeToItemModelFile("  }");
        writeToItemModelFile("}");
    }

    public void getBlockModel(String modID, String name) {
        writeToBlockModelFile(blockBLModelWriter, "{");
        writeToBlockModelFile(blockBLModelWriter, "  \"parent\": \"minecraft:block/door_bottom_left\",");
        writeToBlockModelFile(blockBLModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockBLModelWriter, "    \"bottom\": \"" + modID + ":" + "block/" + name + "_bottom\",");
        writeToBlockModelFile(blockBLModelWriter, "    \"top\": \"" + modID + ":" + "block/" + name + "_top\"");
        writeToBlockModelFile(blockBLModelWriter, "  }");
        writeToBlockModelFile(blockBLModelWriter, "}");

        writeToBlockModelFile(blockTLModelWriter, "{");
        writeToBlockModelFile(blockTLModelWriter, "  \"parent\": \"minecraft:block/door_top_left\",");
        writeToBlockModelFile(blockTLModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockTLModelWriter, "    \"bottom\": \"" + modID + ":" + "block/" + name + "_bottom\",");
        writeToBlockModelFile(blockTLModelWriter, "    \"top\": \"" + modID + ":" + "block/" + name + "_top\"");
        writeToBlockModelFile(blockTLModelWriter, "  }");
        writeToBlockModelFile(blockTLModelWriter, "}");

        writeToBlockModelFile(blockBRModelWriter, "{");
        writeToBlockModelFile(blockBRModelWriter, "  \"parent\": \"minecraft:block/door_bottom_right\",");
        writeToBlockModelFile(blockBRModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockBRModelWriter, "    \"bottom\": \"" + modID + ":" + "block/" + name + "_bottom\",");
        writeToBlockModelFile(blockBRModelWriter, "    \"top\": \"" + modID + ":" + "block/" + name + "_top\"");
        writeToBlockModelFile(blockBRModelWriter, "  }");
        writeToBlockModelFile(blockBRModelWriter, "}");

        writeToBlockModelFile(blockTRModelWriter, "{");
        writeToBlockModelFile(blockTRModelWriter, "  \"parent\": \"minecraft:block/door_top_right\",");
        writeToBlockModelFile(blockTRModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockTRModelWriter, "    \"bottom\": \"" + modID + ":" + "block/" + name + "_bottom\",");
        writeToBlockModelFile(blockTRModelWriter, "    \"top\": \"" + modID + ":" + "block/" + name + "_top\"");
        writeToBlockModelFile(blockTRModelWriter, "  }");
        writeToBlockModelFile(blockTRModelWriter, "}");

        writeToBlockModelFile(blockTROModelWriter, "{");
        writeToBlockModelFile(blockTROModelWriter, "  \"parent\": \"minecraft:block/door_top_right_open\",");
        writeToBlockModelFile(blockTROModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockTROModelWriter, "    \"bottom\": \"" + modID + ":" + "block/" + name + "_bottom\",");
        writeToBlockModelFile(blockTROModelWriter, "    \"top\": \"" + modID + ":" + "block/" + name + "_top\"");
        writeToBlockModelFile(blockTROModelWriter, "  }");
        writeToBlockModelFile(blockTROModelWriter, "}");

        writeToBlockModelFile(blockTLOModelWriter, "{");
        writeToBlockModelFile(blockTLOModelWriter, "  \"parent\": \"minecraft:block/door_top_left_open\",");
        writeToBlockModelFile(blockTLOModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockTLOModelWriter, "    \"bottom\": \"" + modID + ":" + "block/" + name + "_bottom\",");
        writeToBlockModelFile(blockTLOModelWriter, "    \"top\": \"" + modID + ":" + "block/" + name + "_top\"");
        writeToBlockModelFile(blockTLOModelWriter, "  }");
        writeToBlockModelFile(blockTLOModelWriter, "}");

        writeToBlockModelFile(blockBROModelWriter, "{");
        writeToBlockModelFile(blockBROModelWriter, "  \"parent\": \"minecraft:block/door_bottom_right_open\",");
        writeToBlockModelFile(blockBROModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockBROModelWriter, "    \"bottom\": \"" + modID + ":" + "block/" + name + "_bottom\",");
        writeToBlockModelFile(blockBROModelWriter, "    \"top\": \"" + modID + ":" + "block/" + name + "_top\"");
        writeToBlockModelFile(blockBROModelWriter, "  }");
        writeToBlockModelFile(blockBROModelWriter, "}");

        writeToBlockModelFile(blockBLOModelWriter, "{");
        writeToBlockModelFile(blockBLOModelWriter, "  \"parent\": \"minecraft:block/door_bottom_left_open\",");
        writeToBlockModelFile(blockBLOModelWriter, "  \"textures\": {");
        writeToBlockModelFile(blockBLOModelWriter, "    \"bottom\": \"" + modID + ":" + "block/" + name + "_bottom\",");
        writeToBlockModelFile(blockBLOModelWriter, "    \"top\": \"" + modID + ":" + "block/" + name + "_top\"");
        writeToBlockModelFile(blockBLOModelWriter, "  }");
        writeToBlockModelFile(blockBLOModelWriter, "}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        writeToBlockstateFile("    \"facing=east,half=lower,hinge=left,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_bottom_left\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,half=lower,hinge=left,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_bottom_left\",");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,half=lower,hinge=right,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_bottom_right\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,half=lower,hinge=right,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_bottom_right_open\",");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,half=upper,hinge=left,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_top_left\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,half=upper,hinge=left,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_top_left_open\",");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,half=upper,hinge=right,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_top_right\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=east,half=upper,hinge=right,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_top_right_open\",");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,half=lower,hinge=left,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_bottom_left\",");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,half=lower,hinge=left,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_bottom_left_open\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,half=lower,hinge=right,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_bottom_right\",");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,half=lower,hinge=right,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_bottom_right_open\",");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,half=upper,hinge=left,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_top_left\",");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,half=upper,hinge=left,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_top_left_open\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,half=upper,hinge=right,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_top_right\",");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=north,half=upper,hinge=right,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_top_right_open\",");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=lower,hinge=left,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_bottom_left\",");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=lower,hinge=left,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_bottom_left_open\",");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=lower,hinge=right,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_bottom_right\",");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=lower,hinge=right,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_bottom_right_open\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=upper,hinge=left,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_top_right\",");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=upper,hinge=left,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_top_left_open\",");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=upper,hinge=right,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_top_right\",");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=south,half=upper,hinge=right,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_top_right_open\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,half=lower,hinge=left,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_bottom_left\",");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,half=lower,hinge=left,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_bottom_left_open\",");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,half=lower,hinge=right,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_bottom_right\",");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,half=lower,hinge=right,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_bottom_right_open\",");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,half=upper,hinge=left,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_top_left\",");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,half=upper,hinge=left,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_top_left_open\",");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,half=upper,hinge=right,open=false\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_top_right\",");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("    \"facing=west,half=upper,hinge=right,open=true\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "_top_right_open\",");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("  }");
        writeToBlockstateFile("}");
    }

    public void writerInit() {
        try {
            itemModelWriter.close();
            blockBLModelWriter.close();
            blockBRModelWriter.close();
            blockTLModelWriter.close();
            blockTRModelWriter.close();
            blockBLOModelWriter.close();
            blockBROModelWriter.close();
            blockTLOModelWriter.close();
            blockTROModelWriter.close();
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