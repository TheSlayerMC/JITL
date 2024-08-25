package net.jitl.core.data;

import net.jitl.core.init.internal.JItems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ItemTypeGenerator {

    protected BufferedWriter swordsWriter, pickaxeWriter, axeWriter, shovelWriter, hoeWriter, helmetWriter, chestWriter, legWriter, bootWriter;

    public void generate() {
        String baseDir = "../../src/main/resources/data/minecraft/tags/item/";

        File swords = new File(baseDir + "swords.json");
        File pickaxe = new File(baseDir + "pickaxes.json");
        File axe = new File(baseDir + "axes.json");
        File shovel = new File(baseDir + "shovels.json");
        File hoe = new File(baseDir + "hoes.json");

        File helmet = new File(baseDir + "head_armor.json");
        File chestplate = new File(baseDir + "chest_armor.json");
        File leggings = new File(baseDir + "leg_armor.json");
        File boots = new File(baseDir + "foot_armor.json");

        ArrayList<String> swordsList = new ArrayList<>(JItems.swordName);
        ArrayList<String> pickaxeList = new ArrayList<>(JItems.pickaxeName);
        ArrayList<String> axeList = new ArrayList<>(JItems.axeName);
        ArrayList<String> shovelList = new ArrayList<>(JItems.shovelName);
        ArrayList<String> hoeList = new ArrayList<>(JItems.hoeName);

        ArrayList<String> helmetList = new ArrayList<>(JItems.helmetName);
        ArrayList<String> chestList = new ArrayList<>(JItems.chestplateName);
        ArrayList<String> leggingList = new ArrayList<>(JItems.leggingsName);
        ArrayList<String> bootList = new ArrayList<>(JItems.bootsName);

        try {
            if(helmet.exists()) helmet.delete();
            helmet.createNewFile();
            helmetWriter = new BufferedWriter(new FileWriter(helmet));

            if(chestplate.exists()) chestplate.delete();
            chestplate.createNewFile();
            chestWriter = new BufferedWriter(new FileWriter(chestplate));

            if(leggings.exists()) leggings.delete();
            leggings.createNewFile();
            legWriter = new BufferedWriter(new FileWriter(leggings));

            if(boots.exists()) boots.delete();
            boots.createNewFile();
            bootWriter = new BufferedWriter(new FileWriter(boots));

            if(swords.exists()) swords.delete();
            swords.createNewFile();
            swordsWriter = new BufferedWriter(new FileWriter(swords));

            if(pickaxe.exists()) pickaxe.delete();
            pickaxe.createNewFile();
            pickaxeWriter = new BufferedWriter(new FileWriter(pickaxe));

            if(axe.exists()) axe.delete();
            axe.createNewFile();
            axeWriter = new BufferedWriter(new FileWriter(axe));

            if(shovel.exists()) shovel.delete();
            shovel.createNewFile();
            shovelWriter = new BufferedWriter(new FileWriter(shovel));

            if(hoe.exists()) hoe.delete();
            hoe.createNewFile();
            hoeWriter = new BufferedWriter(new FileWriter(hoe));
        } catch (IOException e) {
            e.printStackTrace();
        }

        writeFile(swordsWriter, swordsList);
        writeFile(pickaxeWriter, pickaxeList);
        writeFile(axeWriter, axeList);
        writeFile(shovelWriter, shovelList);
        writeFile(hoeWriter, hoeList);

        writeFile(helmetWriter, helmetList);
        writeFile(chestWriter, chestList);
        writeFile(legWriter, leggingList);
        writeFile(bootWriter, bootList);

        writerInit();
    }

    public void writeFile(BufferedWriter writer, ArrayList<String> type) {
        int j = 0;
        writeToFile(writer, "{");
        writeToFile(writer, "  \"replace\": false,");
        writeToFile(writer, "  \"values\": [");
        for(int i = 0; i < type.size(); i++) {
            j++;
            String end = j == type.size() ? "" : ",";
            writeToFile(writer, "    \"jitl:" + type.get(i) + "\"" + end);
        }
        writeToFile(writer, "  ]");
        writeToFile(writer, "}");
    }

    public void writerInit() {
        try {
            pickaxeWriter.close();
            axeWriter.close();
            shovelWriter.close();
            hoeWriter.close();
            swordsWriter.close();
            helmetWriter.close();
            chestWriter.close();
            legWriter.close();
            bootWriter.close();

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