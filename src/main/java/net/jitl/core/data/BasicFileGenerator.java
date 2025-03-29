package net.jitl.core.data;

import net.jitl.core.init.JITL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BasicFileGenerator {

    protected BufferedWriter itemFileWriter;

    public void generateBasicFile(String name, String extension) {
        String itemFileDir = "../src/main/resources/assets/jitl/items/" + name + ".json";
        File itemFile = new File(itemFileDir);
        itemFile.mkdirs();
        try {
            if(itemFile.exists()) itemFile.delete();
            itemFile.createNewFile();
            itemFileWriter = new BufferedWriter(new FileWriter(itemFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        getItemFile(JITL.MOD_ID, name, extension);
        itemModelInit();
    }

    public void generateSpawnEggFile(String name) {
        String itemFileDir = "../src/main/resources/assets/jitl/items/" + name + ".json";
        File itemFile = new File(itemFileDir);
        itemFile.mkdirs();
        try {
            if(itemFile.exists()) itemFile.delete();
            itemFile.createNewFile();
            itemFileWriter = new BufferedWriter(new FileWriter(itemFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        getSpawnEggItem(JITL.MOD_ID, name);
        itemModelInit();
    }

    public void generateShieldFile(String name) {
        String itemFileDir = "../src/main/resources/assets/jitl/items/" + name + ".json";
        File itemFile = new File(itemFileDir);
        itemFile.mkdirs();
        try {
            if(itemFile.exists()) itemFile.delete();
            itemFile.createNewFile();
            itemFileWriter = new BufferedWriter(new FileWriter(itemFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        getShieldItem(name);
        itemModelInit();
    }

    public void generateBowFile(String name) {
        String itemFileDir = "../src/main/resources/assets/jitl/items/" + name + ".json";
        File itemFile = new File(itemFileDir);
        itemFile.mkdirs();
        try {
            if(itemFile.exists()) itemFile.delete();
            itemFile.createNewFile();
            itemFileWriter = new BufferedWriter(new FileWriter(itemFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        getBowItem(name);
        itemModelInit();
    }

    public void generateBasicFile(boolean item, String name) {
        String itemFileDir = "../src/main/resources/assets/jitl/items/" + name + ".json";
        File itemFile = new File(itemFileDir);
        itemFile.mkdirs();
        try {
            if(itemFile.exists()) itemFile.delete();
            itemFile.createNewFile();
            itemFileWriter = new BufferedWriter(new FileWriter(itemFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        getItemFile(item, JITL.MOD_ID, name);
        itemModelInit();
    }

    public void generateBasicFile(String name) {
        generateBasicFile(false, name);
    }

    private void itemModelInit() {
        try {
            itemFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToItemFile(String text){
        try {
            itemFileWriter.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getItemFile(boolean item, String modID, String name) {
        writeToItemFile("{");
        writeToItemFile("  \"model\": {");
        writeToItemFile("    \"type\": \"minecraft:model\",");
        if(!item) {
            writeToItemFile("    \"model\": \"" + modID + ":" + "block/" + name + "\"");
        } else {
            writeToItemFile("    \"model\": \"" + modID + ":" + "item/" + name + "\"");
        }
        writeToItemFile("  }");
        writeToItemFile("}");
    }

    private void getItemFile(String modID, String name, String ext) {
        writeToItemFile("{");
        writeToItemFile("  \"model\": {");
        writeToItemFile("    \"type\": \"minecraft:model\",");
        writeToItemFile("    \"model\": \"" + modID + ":" + "block/" + name + ext + "\"");
        writeToItemFile("  }");
        writeToItemFile("}");
    }

    public void getSpawnEggItem(String modID, String name) {
        writeToItemFile("{");
        writeToItemFile("  \"parent\": \"minecraft:item/generated\",");
        writeToItemFile("  \"textures\": {");
        writeToItemFile("    \"layer0\": \"" + modID + ":" + "item/spawn_eggs/" + name + "\"");
        writeToItemFile("  }");
        writeToItemFile("}");
    }

    public void getShieldItem(String name) {
        writeToItemFile("{");
        writeToItemFile("  \"model\": {");
        writeToItemFile("    \"type\": \"minecraft:condition\",");
        writeToItemFile("    \"on_false\": {");
        writeToItemFile("      \"type\": \"minecraft:special\",");
        writeToItemFile("      \"base\": \"jitl:item/" + name + "\",");
        writeToItemFile("      \"model\": {");
        writeToItemFile("        \"type\": \"jitl:" + name + "\"");
        writeToItemFile("      }");
        writeToItemFile("    },");
        writeToItemFile("    \"on_true\": {");
        writeToItemFile("      \"type\": \"minecraft:special\",");
        writeToItemFile("      \"base\": \"jitl:item/" + name + "_blocking\",");
        writeToItemFile("      \"model\": {");
        writeToItemFile("        \"type\": \"jitl:" + name + "\"");
        writeToItemFile("      }");
        writeToItemFile("    },");
        writeToItemFile("    \"property\": \"minecraft:using_item\"");
        writeToItemFile("  }");
        writeToItemFile("}");
    }

    public void getBowItem(String name) {
        writeToItemFile("{");
        writeToItemFile("  \"model\": {");
        writeToItemFile("    \"type\": \"minecraft:condition\",");
        writeToItemFile("    \"on_false\": {");
        writeToItemFile("      \"type\": \"minecraft:model\",");
        writeToItemFile("      \"model\": \"jitl:item/" + name + "\"");
        writeToItemFile("    },");
        writeToItemFile("    \"on_true\": {");
        writeToItemFile("      \"type\": \"minecraft:range_dispatch\",");
        writeToItemFile("      \"entries\": [");
        writeToItemFile("        {");
        writeToItemFile("          \"model\": {");
        writeToItemFile("            \"type\": \"minecraft:model\",");
        writeToItemFile("      \"model\": \"jitl:item/" + name + "_1\"");
        writeToItemFile("          },");
        writeToItemFile("          \"threshold\": 0.65");
        writeToItemFile("        },");
        writeToItemFile("        {");
        writeToItemFile("          \"model\": {");
        writeToItemFile("            \"type\": \"minecraft:model\",");
        writeToItemFile("      \"model\": \"jitl:item/" + name + "_2\"");
        writeToItemFile("          },");
        writeToItemFile("          \"threshold\": 0.9");
        writeToItemFile("        }");
        writeToItemFile("      ],");
        writeToItemFile("      \"fallback\": {");
        writeToItemFile("        \"type\": \"minecraft:model\",");
        writeToItemFile("      \"model\": \"jitl:item/" + name + "_0\"");
        writeToItemFile("      },");
        writeToItemFile("      \"property\": \"minecraft:use_duration\",");
        writeToItemFile("      \"scale\": 0.05");
        writeToItemFile("    },");
        writeToItemFile("    \"property\": \"minecraft:using_item\"");
        writeToItemFile("  }");
        writeToItemFile("}");
    }
}
