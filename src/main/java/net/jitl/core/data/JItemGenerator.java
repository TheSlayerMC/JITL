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

            getNormalItem(JITL.MODID, name, JItems.ItemType.TOOL);
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

            getSpawnEggItem();
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

    public void getNormalItem(String modID, String name, JItems.ItemType type) {
        writeToItemModelFile("{");
        if(type == JItems.ItemType.TOOL) {
            writeToItemModelFile("  \"parent\": \"minecraft:item/handheld\",");
        } else {
            writeToItemModelFile("  \"parent\": \"minecraft:item/generated\",");
        }
        String texName = type != JItems.ItemType.RECORD ? name : "record";
        writeToItemModelFile("  \"textures\": {");
        writeToItemModelFile("    \"layer0\": \"" + modID + ":" + "item/" + texName + "\"");
        writeToItemModelFile("  }");
        writeToItemModelFile("}");
    }

    public void getSpawnEggItem() {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"minecraft:item/template_spawn_egg\"");
        writeToItemModelFile("}");
    }
}