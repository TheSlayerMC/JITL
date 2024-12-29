package net.jitl.core.data;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JItemGenerator extends BasicFileGenerator {

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

            getNormalItem(JITL.MOD_ID, name, JItems.ItemType.ITEM);
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

            getNormalItem(JITL.MOD_ID, name, JItems.ItemType.TOOL);
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

            getNormalItem(JITL.MOD_ID, name, JItems.ItemType.RECORD);
            itemModelInit();
        }

        for(int i = 0; i < JEntities.entityName.size(); i++) {
            String name = JEntities.entityName.get(i) + "_spawn_egg";
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
            generateSpawnEggFile(name, JEntities.COLOUR1.get(i), JEntities.COLOUR2.get(i));
            itemModelInit();
        }

        generateBasicFile(true, "broken_okoloo_club");
        generateBasicFile(true, "sentacoin_bag");

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
        generateBasicFile(true, name);
    }

    public void getSpawnEggItem() {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"minecraft:item/template_spawn_egg\"");
        writeToItemModelFile("}");
    }
}