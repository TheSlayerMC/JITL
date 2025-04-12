package net.jitl.core.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArmorGenerator {

    protected BufferedWriter typeWriter;
    public static final ArrayList<String> ARMOR = new ArrayList<>();

    public void generate() {
        ARMOR.add("sapphire");
        ARMOR.add("bloodcrust");
        ARMOR.add("celestium");
        ARMOR.add("crystal_flake");
        ARMOR.add("flairium");
        ARMOR.add("flame");
        ARMOR.add("frostbitten");
        ARMOR.add("gorbite");
        ARMOR.add("hellmetal");
        ARMOR.add("hollow");
        ARMOR.add("lunium");
        ARMOR.add("orbadite");
        ARMOR.add("shadium");
        ARMOR.add("twilight");

        for(String name : ARMOR) {
            String dir = "../src/main/resources/assets/jitl/equipment/" + name + ".json";

            File armor = new File(dir);
            armor.mkdirs();
            try {
                if (armor.exists()) armor.delete();
                armor.createNewFile();
                typeWriter = new BufferedWriter(new FileWriter(armor));
            } catch (IOException e) {
                e.printStackTrace();
            }

            writeFile(typeWriter, name);

            writerInit();
        }
    }

    public void writeFile(BufferedWriter writer, String type) {
        writeToFile(writer, "{");
        writeToFile(writer, "  \"layers\": {");
        writeToFile(writer, "    \"horse_body\": [");
        writeToFile(writer, "      {");
        writeToFile(writer, "        \"texture\": \"jitl:" + type + "\"");
        writeToFile(writer, "      }");
        writeToFile(writer, "    ],");
        writeToFile(writer, "    \"humanoid\": [");
        writeToFile(writer, "      {");
        writeToFile(writer, "        \"texture\": \"jitl:" + type + "\"");
        writeToFile(writer, "      }");
        writeToFile(writer, "    ],");
        writeToFile(writer, "    \"humanoid_leggings\": [");
        writeToFile(writer, "      {");
        writeToFile(writer, "        \"texture\": \"jitl:" + type + "\"");
        writeToFile(writer, "      }");
        writeToFile(writer, "    ]");
        writeToFile(writer, "  }");
        writeToFile(writer, "}");
    }

    public void writerInit() {
        try {
            typeWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(BufferedWriter file, String text){
        try {
            file.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}