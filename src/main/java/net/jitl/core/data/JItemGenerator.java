package net.jitl.core.data;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JItems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JItemGenerator {

    protected BufferedWriter itemModelWriter;

    public void generate(){
        for(String name : JItems.itemName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            File itemModel = new File(itemModelDir);
            itemModel.mkdirs();
            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));
            } catch (IOException e) {
                e.printStackTrace();
            }

            getNormalItem(JITL.MODID, name, JItems.ItemType.ITEM);
            itemModelInit();
        }

        for(String name : JItems.toolName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            File itemModel = new File(itemModelDir);
            itemModel.mkdirs();
            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));
            } catch (IOException e) {
                e.printStackTrace();
            }

            getToolItem(JITL.MODID, name);
            itemModelInit();
        }

        for(String name : JItems.recordName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            File itemModel = new File(itemModelDir);
            itemModel.mkdirs();
            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));
            } catch (IOException e) {
                e.printStackTrace();
            }

            getNormalItem(JITL.MODID, name, JItems.ItemType.RECORD);
            itemModelInit();
        }

        for(String name : JItems.spawnName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            File itemModel = new File(itemModelDir);
            itemModel.mkdirs();
            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));
            } catch (IOException e) {
                e.printStackTrace();
            }

            getSpawnEggItem(JITL.MODID, name);
            itemModelInit();
        }

        for(String name : JItems.gunName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            File itemModel = new File(itemModelDir);
            itemModel.mkdirs();
            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));
            } catch (IOException e) {
                e.printStackTrace();
            }

            getGunItem(JITL.MODID, name);
            itemModelInit();
        }

        for(String name : JItems.hammerName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            File itemModel = new File(itemModelDir);
            itemModel.mkdirs();
            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));
            } catch (IOException e) {
                e.printStackTrace();
            }

            getHammerItem(JITL.MODID, name);
            itemModelInit();
        }
    }

    public void itemModelInit() {
        try {
            itemModelWriter.close();
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

    public void getGunItem(String modID, String name) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"jitl:item/gun\",");
        writeToItemModelFile("  \"textures\": {");
        writeToItemModelFile("    \"layer0\": \"" + modID + ":" + "item/" + name + "\"");
        writeToItemModelFile("  }");
        writeToItemModelFile("}");
    }

    public void getHammerItem(String modID, String name) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"jitl:item/hammer_base\",");
        writeToItemModelFile("  \"textures\": {");
        writeToItemModelFile("    \"1\": \"" + modID + ":" + "item/" + name + "\"");
        writeToItemModelFile("  }");
        writeToItemModelFile("}");
    }

    public void getToolItem(String modID, String name) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"minecraft:item/handheld\",");
        writeToItemModelFile("  \"textures\": {");
        writeToItemModelFile("    \"layer0\": \"" + modID + ":" + "item/" + name + "\"");
        writeToItemModelFile("  }");
        writeToItemModelFile("}");
    }

    public void getNormalItem(String modID, String name, JItems.ItemType type) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"minecraft:item/generated\",");
        String texName = type != JItems.ItemType.RECORD ? name : "record";
        writeToItemModelFile("  \"textures\": {");
        writeToItemModelFile("    \"layer0\": \"" + modID + ":" + "item/" + texName + "\"");
        writeToItemModelFile("  }");
        writeToItemModelFile("}");
    }

    public void getSpawnEggItem(String modID, String name) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"minecraft:item/generated\",");
        writeToItemModelFile("  \"textures\": {");
        writeToItemModelFile("    \"layer0\": \"" + modID + ":" + "item/spawn_eggs/" + name + "\"");
        writeToItemModelFile("  }");
        writeToItemModelFile("}");
    }
}