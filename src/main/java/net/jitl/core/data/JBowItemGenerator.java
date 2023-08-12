package net.jitl.core.data;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JItems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBowItemGenerator {

    protected BufferedWriter itemModelWriter, pulling0Writer, pulling1Writer, pulling2Writer;

    public void generate(){
        for(String name : JItems.bowName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String itemModel0Dir = "../src/main/resources/assets/jitl/models/item/" + name + "_0.json";
            String itemModel1Dir = "../src/main/resources/assets/jitl/models/item/" + name + "_1.json";
            String itemModel2Dir = "../src/main/resources/assets/jitl/models/item/" + name + "_2.json";

            File itemModel = new File(itemModelDir);
            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));
            } catch (IOException e) {
                e.printStackTrace();
            }

            File itemModel0 = new File(itemModel0Dir);
            try {
                if(itemModel0.exists()) itemModel0.delete();
                itemModel0.createNewFile();
                pulling0Writer = new BufferedWriter(new FileWriter(itemModel0));
            } catch (IOException e) {
                e.printStackTrace();
            }

            File itemModel1 = new File(itemModel1Dir);
            try {
                if(itemModel1.exists()) itemModel1.delete();
                itemModel1.createNewFile();
                pulling1Writer = new BufferedWriter(new FileWriter(itemModel1));
            } catch (IOException e) {
                e.printStackTrace();
            }

            File itemModel2 = new File(itemModel2Dir);
            try {
                if(itemModel2.exists()) itemModel2.delete();
                itemModel2.createNewFile();
                pulling2Writer = new BufferedWriter(new FileWriter(itemModel2));
            } catch (IOException e) {
                e.printStackTrace();
            }

            getItem(JITL.MODID, name);
            itemModelInit();
        }
    }

    public void itemModelInit() {
        try {
            itemModelWriter.close();
            pulling0Writer.close();
            pulling1Writer.close();
            pulling2Writer.close();

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

    public void getItem(String modID, String name) {
        writeToItemModelFile(itemModelWriter, "{");
        writeToItemModelFile(itemModelWriter, "    \"parent\": \"item/generated\",");
        writeToItemModelFile(itemModelWriter, "    \"textures\": {");
        writeToItemModelFile(itemModelWriter, "        \"layer0\": \"" + modID + ":item/" + name + "\"");
        writeToItemModelFile(itemModelWriter, "    },");
        writeToItemModelFile(itemModelWriter, "    \"display\": {");
        writeToItemModelFile(itemModelWriter, "        \"thirdperson_righthand\": {");
        writeToItemModelFile(itemModelWriter, "            \"rotation\": [ -80, 260, -40 ],");
        writeToItemModelFile(itemModelWriter, "            \"translation\": [ -1, -2, 2.5 ],");
        writeToItemModelFile(itemModelWriter, "            \"scale\": [ 0.9, 0.9, 0.9 ]");
        writeToItemModelFile(itemModelWriter, "        },");
        writeToItemModelFile(itemModelWriter, "        \"thirdperson_lefthand\": {");
        writeToItemModelFile(itemModelWriter, "            \"rotation\": [ -80, -280, 40 ],");
        writeToItemModelFile(itemModelWriter, "            \"translation\": [ -1, -2, 2.5 ],");
        writeToItemModelFile(itemModelWriter, "            \"scale\": [ 0.9, 0.9, 0.9 ]");
        writeToItemModelFile(itemModelWriter, "        },");
        writeToItemModelFile(itemModelWriter, "        \"firstperson_righthand\": {");
        writeToItemModelFile(itemModelWriter, "            \"rotation\": [ 0, -90, 25 ],");
        writeToItemModelFile(itemModelWriter, "            \"translation\": [ 1.13, 3.2, 1.13 ],");
        writeToItemModelFile(itemModelWriter, "            \"scale\": [ 0.68, 0.68, 0.68 ]");
        writeToItemModelFile(itemModelWriter, "        },");
        writeToItemModelFile(itemModelWriter, "        \"firstperson_lefthand\": {");
        writeToItemModelFile(itemModelWriter, "            \"rotation\": [ 0, 90, -25 ],");
        writeToItemModelFile(itemModelWriter, "            \"translation\": [ 1.13, 3.2, 1.13 ],");
        writeToItemModelFile(itemModelWriter, "            \"scale\": [ 0.68, 0.68, 0.68 ]");
        writeToItemModelFile(itemModelWriter, "        }");
        writeToItemModelFile(itemModelWriter, "    },");
        writeToItemModelFile(itemModelWriter, "    \"overrides\": [");
        writeToItemModelFile(itemModelWriter, "        {");
        writeToItemModelFile(itemModelWriter, "            \"predicate\": {");
        writeToItemModelFile(itemModelWriter, "                \"pulling\": 1");
        writeToItemModelFile(itemModelWriter, "            },");
        writeToItemModelFile(itemModelWriter, "            \"model\": \"" + modID + ":item/" + name + "_0\"");
        writeToItemModelFile(itemModelWriter, "        },");
        writeToItemModelFile(itemModelWriter, "        {");
        writeToItemModelFile(itemModelWriter, "            \"predicate\": {");
        writeToItemModelFile(itemModelWriter, "                \"pulling\": 1,");
        writeToItemModelFile(itemModelWriter, "                \"pull\": 0.65");
        writeToItemModelFile(itemModelWriter, "            },");
        writeToItemModelFile(itemModelWriter, "            \"model\": \"" + modID + ":item/" + name + "_1\"");
        writeToItemModelFile(itemModelWriter, "        },");
        writeToItemModelFile(itemModelWriter, "        {");
        writeToItemModelFile(itemModelWriter, "            \"predicate\": {");
        writeToItemModelFile(itemModelWriter, "                \"pulling\": 1,");
        writeToItemModelFile(itemModelWriter, "                \"pull\": 0.9");
        writeToItemModelFile(itemModelWriter, "            },");
        writeToItemModelFile(itemModelWriter, "            \"model\": \"" + modID + ":item/" + name + "_2\"");
        writeToItemModelFile(itemModelWriter, "        }");
        writeToItemModelFile(itemModelWriter, "    ]");
        writeToItemModelFile(itemModelWriter, "}");

        writeToItemModelFile(pulling0Writer, "{");
        writeToItemModelFile(pulling0Writer, "    \"parent\": \"" + modID + ":item/" + name + "\",");
        writeToItemModelFile(pulling0Writer, "    \"textures\": {");
        writeToItemModelFile(pulling0Writer, "        \"layer0\": \"" + modID + ":item/" + name + "_0\"");
        writeToItemModelFile(pulling0Writer, "    }");
        writeToItemModelFile(pulling0Writer, "}");

        writeToItemModelFile(pulling1Writer, "{");
        writeToItemModelFile(pulling1Writer, "    \"parent\": \"" + modID + ":item/" + name + "\",");
        writeToItemModelFile(pulling1Writer, "    \"textures\": {");
        writeToItemModelFile(pulling1Writer, "        \"layer0\": \"" + modID + ":item/" + name + "_1\"");
        writeToItemModelFile(pulling1Writer, "    }");
        writeToItemModelFile(pulling1Writer, "}");

        writeToItemModelFile(pulling2Writer, "{");
        writeToItemModelFile(pulling2Writer, "    \"parent\": \"" + modID + ":item/" + name + "\",");
        writeToItemModelFile(pulling2Writer, "    \"textures\": {");
        writeToItemModelFile(pulling2Writer, "        \"layer0\": \"" + modID + ":item/" + name + "_2\"");
        writeToItemModelFile(pulling2Writer, "    }");
        writeToItemModelFile(pulling2Writer, "}");

    }
}