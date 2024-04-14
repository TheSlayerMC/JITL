package net.jitl.core.data;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JItems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JShieldItemGenerator {

    protected BufferedWriter itemModelWriter, blockingWriter;

    public void generate(){
        for(String name : JItems.shieldName) {
            String itemModelDir = "../../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String itemModelBlockingDir = "../../src/main/resources/assets/jitl/models/item/" + name + "_blocking.json";

            File itemModel = new File(itemModelDir);
            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));
            } catch (IOException e) {
                e.printStackTrace();
            }

            File itemModelBlocking = new File(itemModelBlockingDir);
            try {
                if(itemModelBlocking.exists()) itemModelBlocking.delete();
                itemModelBlocking.createNewFile();
                blockingWriter = new BufferedWriter(new FileWriter(itemModelBlocking));
            } catch (IOException e) {
                e.printStackTrace();
            }

            getItem(JITL.MODID, name);
            itemModelInit();
        }
    }


    public void getItem(String modID, String name) {
        writeToItemModelFile(itemModelWriter, "{");
        writeToItemModelFile(itemModelWriter, "  \"parent\": \"item/shield\",");
        writeToItemModelFile(itemModelWriter, "  \"textures\": {");
        writeToItemModelFile(itemModelWriter, "    \"particle\": \"" + modID + ":shield/" + name + "\"");
        writeToItemModelFile(itemModelWriter, "  },");
        writeToItemModelFile(itemModelWriter, "  \"overrides\": [");
        writeToItemModelFile(itemModelWriter, "    {");
        writeToItemModelFile(itemModelWriter, "      \"predicate\": {");
        writeToItemModelFile(itemModelWriter, "        \"" + modID + ":blocking\": 1");
        writeToItemModelFile(itemModelWriter, "      },");
        writeToItemModelFile(itemModelWriter, "      \"model\": \"" + modID + ":item/" + name + "_blocking\"");
        writeToItemModelFile(itemModelWriter, "    }");
        writeToItemModelFile(itemModelWriter, "  ]");
        writeToItemModelFile(itemModelWriter, "}");

        writeToItemModelFile(blockingWriter, "{");
        writeToItemModelFile(blockingWriter, "  \"parent\": \"item/shield_blocking\",");
        writeToItemModelFile(blockingWriter, "  \"textures\": {");
        writeToItemModelFile(blockingWriter, "    \"particle\": \"" + modID + ":shield/" + name + "\"");
        writeToItemModelFile(blockingWriter, "  }");
        writeToItemModelFile(blockingWriter, "}");
    }

    public void itemModelInit() {
        try {
            itemModelWriter.close();
            blockingWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToItemModelFile(BufferedWriter writer, String text){
        try {
            writer.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}