package net.jitl.core.data.block_generation;

import net.jitl.core.data.BasicFileGenerator;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockTrophyGenerator extends BasicFileGenerator {

    protected BufferedWriter blockModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : JBlocks.trophyBlockName) {
            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
            String blockModelDir = "../src/main/resources/assets/jitl/models/block/" + name + ".json";
            String blockstateDir = "../src/main/resources/assets/jitl/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockModel = new File(blockModelDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if (itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if (blockModel.exists()) blockModel.delete();
                blockModel.createNewFile();
                blockModelWriter = new BufferedWriter(new FileWriter(blockModel));

                if (blockstateModel.exists()) blockstateModel.delete();
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
        writeToItemModelFile("      \"layer0\": \"" + modID + ":item/" + name + "\"");
        writeToItemModelFile("  }");
        writeToItemModelFile("}");
    }

    public void getBlockModel(String modID, String name) {
        writeToBlockModelFile("{");
        writeToBlockModelFile("    \"textures\": {");
        writeToBlockModelFile("        \"particle\": \"" + modID + ":block/" + name + "\",");
        writeToBlockModelFile("        \"0\": \"" + modID + ":block/" + name + "\",");
        writeToBlockModelFile("        \"1\": \"" + modID + ":block/" + name + "_front\",");
        writeToBlockModelFile("        \"2\": \"" + modID + ":block/" + name + "_side\"");
        writeToBlockModelFile("    },");
        writeToBlockModelFile("    \"elements\": [");
        writeToBlockModelFile("        {");
        writeToBlockModelFile("            \"name\": \"base\",");
        writeToBlockModelFile("            \"from\": [ 2.0, 0.0, 2.0 ],");
        writeToBlockModelFile("            \"to\": [ 14.0, 1.0, 14.0 ],");
        writeToBlockModelFile("            \"faces\": {");
        writeToBlockModelFile("                \"north\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 12.0, 1.0 ] },");
        writeToBlockModelFile("                \"east\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 12.0, 1.0 ] },");
        writeToBlockModelFile("                \"south\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 12.0, 1.0 ] },");
        writeToBlockModelFile("                \"west\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 12.0, 1.0 ] },");
        writeToBlockModelFile("                \"up\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 12.0, 12.0 ] },");
        writeToBlockModelFile("                \"down\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 12.0, 12.0 ] }");
        writeToBlockModelFile("            }");
        writeToBlockModelFile("        },");
        writeToBlockModelFile("        {");
        writeToBlockModelFile("            \"name\": \"base1\",");
        writeToBlockModelFile("            \"from\": [ 4.0, 1.0, 4.0 ],");
        writeToBlockModelFile("            \"to\": [ 12.0, 3.0, 12.0 ],");
        writeToBlockModelFile("            \"faces\": {");
        writeToBlockModelFile("                \"north\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 8.0, 2.0 ] },");
        writeToBlockModelFile("                \"east\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 8.0, 2.0 ] },");
        writeToBlockModelFile("                \"south\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 8.0, 2.0 ] },");
        writeToBlockModelFile("                \"west\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 8.0, 2.0 ] },");
        writeToBlockModelFile("                \"up\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 8.0, 2.0 ] },");
        writeToBlockModelFile("                \"down\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 8.0, 2.0 ] }");
        writeToBlockModelFile("            }");
        writeToBlockModelFile("        },");
        writeToBlockModelFile("        {");
        writeToBlockModelFile("            \"name\": \"shaft\",");
        writeToBlockModelFile("            \"from\": [ 7.0, 3.0, 7.0 ],");
        writeToBlockModelFile("            \"to\": [ 9.0, 9.0, 9.0 ],");
        writeToBlockModelFile("            \"faces\": {");
        writeToBlockModelFile("                \"north\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 2.0, 6.0 ] },");
        writeToBlockModelFile("                \"east\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 2.0, 6.0 ] },");
        writeToBlockModelFile("                \"south\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 2.0, 6.0 ] },");
        writeToBlockModelFile("                \"west\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 2.0, 6.0 ] },");
        writeToBlockModelFile("                \"up\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 2.0, 6.0 ] },");
        writeToBlockModelFile("                \"down\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 2.0, 6.0 ] }");
        writeToBlockModelFile("            }");
        writeToBlockModelFile("        },");
        writeToBlockModelFile("        {");
        writeToBlockModelFile("            \"name\": \"trophy1\",");
        writeToBlockModelFile("            \"from\": [ 4.0, 9.0, 4.0 ],");
        writeToBlockModelFile("            \"to\": [ 12.0, 11.0, 12.0 ],");
        writeToBlockModelFile("            \"faces\": {");
        writeToBlockModelFile("                \"north\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 8.0, 2.0 ] },");
        writeToBlockModelFile("                \"east\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 8.0, 2.0 ] },");
        writeToBlockModelFile("                \"south\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 8.0, 2.0 ] },");
        writeToBlockModelFile("                \"west\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 8.0, 2.0 ] },");
        writeToBlockModelFile("                \"up\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 8.0, 2.0 ] },");
        writeToBlockModelFile("                \"down\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 8.0, 2.0 ] }");
        writeToBlockModelFile("            }");
        writeToBlockModelFile("        },");
        writeToBlockModelFile("        {");
        writeToBlockModelFile("            \"name\": \"trophyBottom\",");
        writeToBlockModelFile("            \"from\": [ 2.0, 11.0, 2.0 ],");
        writeToBlockModelFile("            \"to\": [ 14.0, 12.0, 14.0 ],");
        writeToBlockModelFile("            \"faces\": {");
        writeToBlockModelFile("                \"north\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 12.0, 1.0 ] },");
        writeToBlockModelFile("                \"east\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 12.0, 1.0 ] },");
        writeToBlockModelFile("                \"south\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 12.0, 1.0 ] },");
        writeToBlockModelFile("                \"west\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 12.0, 1.0 ] },");
        writeToBlockModelFile("                \"up\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 12.0, 1.0 ] },");
        writeToBlockModelFile("                \"down\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 12.0, 1.0 ] }");
        writeToBlockModelFile("            }");
        writeToBlockModelFile("        },");
        writeToBlockModelFile("        {");
        writeToBlockModelFile("            \"name\": \"sidefront\",");
        writeToBlockModelFile("            \"from\": [ 2.0, 12.0, 1.0 ],");
        writeToBlockModelFile("            \"to\": [ 14.0, 22.0, 2.0 ],");
        writeToBlockModelFile("            \"faces\": {");
        writeToBlockModelFile("                \"north\": { \"texture\": \"#1\", \"uv\": [ 2.0, 3.0, 14.0, 13.0 ] },");
        writeToBlockModelFile("                \"east\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 1.0, 10.0 ] },");
        writeToBlockModelFile("                \"south\": { \"texture\": \"#2\", \"uv\": [ 2.0, 3.0, 14.0, 13.0 ] },");
        writeToBlockModelFile("                \"west\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 1.0, 10.0 ] },");
        writeToBlockModelFile("                \"up\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 12.0, 1.0 ] },");
        writeToBlockModelFile("                \"down\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 12.0, 1.0 ] }");
        writeToBlockModelFile("            }");
        writeToBlockModelFile("        },");
        writeToBlockModelFile("        {");
        writeToBlockModelFile("            \"name\": \"side1\",");
        writeToBlockModelFile("            \"from\": [ 1.0, 12.0, 2.0 ],");
        writeToBlockModelFile("            \"to\": [ 2.0, 22.0, 14.0 ],");
        writeToBlockModelFile("            \"faces\": {");
        writeToBlockModelFile("                \"north\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 4.0, 7.0 ] },");
        writeToBlockModelFile("                \"east\": { \"texture\": \"#2\", \"uv\": [ 2.0, 3.0, 14.0, 13.0 ] },");
        writeToBlockModelFile("                \"south\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 1.0, 10.0 ] },");
        writeToBlockModelFile("                \"west\": { \"texture\": \"#2\", \"uv\": [ 2.0, 3.0, 14.0, 13.0 ] },");
        writeToBlockModelFile("                \"up\": { \"texture\": \"#0\", \"uv\": [ 2.0, 0.0, 3.0, 12.0] },");
        writeToBlockModelFile("                \"down\": { \"texture\": \"#0\", \"uv\": [ 2.0, 0.0, 3.0, 12.0 ] }");
        writeToBlockModelFile("            }");
        writeToBlockModelFile("        },");
        writeToBlockModelFile("        {");
        writeToBlockModelFile("            \"name\": \"side2\",");
        writeToBlockModelFile("            \"from\": [ 2.0, 12.0, 14.0 ],");
        writeToBlockModelFile("            \"to\": [ 14.0, 22.0, 15.0 ],");
        writeToBlockModelFile("            \"faces\": {");
        writeToBlockModelFile("                \"north\": { \"texture\": \"#2\", \"uv\": [ 2.0, 3.0, 14.0, 13.0 ] },");
        writeToBlockModelFile("                \"east\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 1.0, 10.0 ] },");
        writeToBlockModelFile("                \"south\": { \"texture\": \"#2\", \"uv\": [ 2.0, 3.0, 14.0, 13.0 ] },");
        writeToBlockModelFile("                \"west\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 1.0, 10.0 ] },");
        writeToBlockModelFile("                \"up\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 12.0, 1.0 ] },");
        writeToBlockModelFile("                \"down\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 12.0, 1.0 ] }");
        writeToBlockModelFile("            }");
        writeToBlockModelFile("        },");
        writeToBlockModelFile("        {");
        writeToBlockModelFile("            \"name\": \"side3\",");
        writeToBlockModelFile("            \"from\": [ 14.0, 12.0, 2.0 ],");
        writeToBlockModelFile("            \"to\": [ 15.0, 22.0, 14.0 ], ");
        writeToBlockModelFile("            \"faces\": {");
        writeToBlockModelFile("                \"north\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 1.0, 10.0 ] },");
        writeToBlockModelFile("                \"east\": { \"texture\": \"#2\", \"uv\": [ 2.0, 3.0, 14.0, 13.0 ] },");
        writeToBlockModelFile("                \"south\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 1.0, 10.0 ] },");
        writeToBlockModelFile("                \"west\": { \"texture\": \"#2\", \"uv\": [ 2.0, 3.0, 14.0, 13.0 ] },");
        writeToBlockModelFile("                \"up\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 1.0, 12.0 ] },");
        writeToBlockModelFile("                \"down\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 1.0, 12.0 ] }");
        writeToBlockModelFile("            }");
        writeToBlockModelFile("        },");
        writeToBlockModelFile("        {");
        writeToBlockModelFile("            \"name\": \"handle1\",");
        writeToBlockModelFile("            \"from\": [ -1.0, 14.0, 7.0 ], ");
        writeToBlockModelFile("            \"to\": [ 1.0, 23.0, 9.0 ], ");
        writeToBlockModelFile("            \"faces\": {");
        writeToBlockModelFile("                \"north\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 2.0, 9.0 ] },");
        writeToBlockModelFile("                \"east\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 2.0, 9.0 ] },");
        writeToBlockModelFile("                \"south\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 2.0, 9.0 ] },");
        writeToBlockModelFile("                \"west\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 2.0, 9.0 ] },");
        writeToBlockModelFile("                \"up\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 2.0, 2.0 ] },");
        writeToBlockModelFile("                \"down\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 2.0, 2.0 ] }");
        writeToBlockModelFile("            }");
        writeToBlockModelFile("        },");
        writeToBlockModelFile("        {");
        writeToBlockModelFile("            \"name\": \"handle2\",");
        writeToBlockModelFile("            \"from\": [ 15.0, 14.0, 7.0 ],");
        writeToBlockModelFile("            \"to\": [ 17.0, 23.0, 9.0 ],");
        writeToBlockModelFile("            \"faces\": {");
        writeToBlockModelFile("                \"north\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 2.0, 9.0 ] },");
        writeToBlockModelFile("                \"east\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 2.0, 9.0 ] },");
        writeToBlockModelFile("                \"south\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 2.0, 9.0 ] },");
        writeToBlockModelFile("                \"west\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 2.0, 9.0 ] },");
        writeToBlockModelFile("                \"up\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 2.0, 2.0 ] },");
        writeToBlockModelFile("                \"down\": { \"texture\": \"#0\", \"uv\": [ 0.0, 0.0, 2.0, 2.0 ] }");
        writeToBlockModelFile("            }");
        writeToBlockModelFile("        }");
        writeToBlockModelFile("    ]");
        writeToBlockModelFile("}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        writeToBlockstateFile("    \"facing=east\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("    \"facing=north\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("    \"facing=south\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("     },");
        writeToBlockstateFile("    \"facing=west\": {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("     }");
        writeToBlockstateFile("  }");
        writeToBlockstateFile("}");
    }

    public void writerInit() {
        try {
            itemModelWriter.close();
            blockModelWriter.close();
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

    public void writeToBlockModelFile(String text){
        try {
            blockModelWriter.write(text + "\n");
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