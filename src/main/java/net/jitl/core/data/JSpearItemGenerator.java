package net.jitl.core.data;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JItems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JSpearItemGenerator extends BasicFileGenerator {

    protected BufferedWriter itemModelWriter, inHandModelWriter;

    public void generate(){
        for(String name : JItems.spearName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String inHandModelDir = "../src/main/resources/assets/jitl/models/item/" + name + "_in_hand.json";

            File itemModel = new File(itemModelDir);
            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));
            } catch (IOException e) {
                e.printStackTrace();
            }

            File itemModelBlocking = new File(inHandModelDir);
            try {
                if(itemModelBlocking.exists()) itemModelBlocking.delete();
                itemModelBlocking.createNewFile();
                inHandModelWriter = new BufferedWriter(new FileWriter(itemModelBlocking));
            } catch (IOException e) {
                e.printStackTrace();
            }

            getItem(JITL.MOD_ID, name);
            generateSpearFile(name);
            itemModelInit();
        }
    }

    public void getItem(String modID, String name) {
        writeToItemModelFile(inHandModelWriter, "{");
        writeToItemModelFile(inHandModelWriter, "  \"parent\": \"minecraft:item/spear_in_hand\",");
        writeToItemModelFile(inHandModelWriter, "  \"textures\": {");
        writeToItemModelFile(inHandModelWriter, "    \"layer0\": \"" + modID + ":item/" + name + "_in_hand\"");
        writeToItemModelFile(inHandModelWriter, "  }");
        writeToItemModelFile(inHandModelWriter, "}");

        writeToItemModelFile(itemModelWriter, "{");
        writeToItemModelFile(itemModelWriter, "  \"parent\": \"minecraft:item/generated\",");
        writeToItemModelFile(itemModelWriter, "  \"textures\": {");
        writeToItemModelFile(itemModelWriter, "    \"layer0\": \"" + modID + ":item/" + name + "\"");
        writeToItemModelFile(itemModelWriter, "  }");
        writeToItemModelFile(itemModelWriter, "}");
    }

    public void itemModelInit() {
        try {
            itemModelWriter.close();
            inHandModelWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToItemModelFile(BufferedWriter writer, String text) {
        try {
            writer.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}