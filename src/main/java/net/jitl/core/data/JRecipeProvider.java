package net.jitl.core.data;

import net.jitl.core.init.JITL;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class JRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public JRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        
    }

    protected void addSmithingRecipe(RecipeOutput recipeConsumer, ItemLike input, ItemLike modifier, Item result) {
        //SmithingTransformRecipeBuilder.smithing(Ingredient.of(input), Ingredient.of(modifier), RecipeCategory.MISC, result).unlocks("has_" + modifier.toString().toLowerCase(), has(modifier)).save(recipeConsumer, "jitl:" + result.getDescriptionId() + "_smithing");
        //TODO FIX
    }

    protected void add3x3Recipe(RecipeOutput recipeConsumer, ItemLike input, ItemLike output) {
        add3x3Recipe(recipeConsumer, RecipeCategory.BUILDING_BLOCKS, input, output);
    }

    protected void add3x3Recipe(RecipeOutput recipeConsumer, ItemLike input, ItemLike output, int count) {
        add3x3Recipe(recipeConsumer, RecipeCategory.BUILDING_BLOCKS, input, output, count);
    }

    protected void add3x3Recipe(RecipeOutput recipeConsumer, RecipeCategory cat, ItemLike input, ItemLike output) {
        add3x3Recipe(recipeConsumer, cat, input, output, 1);
    }

    protected void add3x3Recipe(RecipeOutput recipeConsumer, RecipeCategory cat, ItemLike input, ItemLike output, int count) {
        ShapedRecipeBuilder.shaped(cat, output, count).define('#', input)
                .pattern("###")
                .pattern("###")
                .pattern("###").unlockedBy(inputToKey(input), has(input)).save(recipeConsumer);
    }

    protected void addShapedRecipe(RecipeOutput recipeConsumer, RecipeCategory cat, String t, String m, String b, char s, ItemLike input, ItemLike output, int count, String name) {
        ShapedRecipeBuilder.shaped(cat, output, count).define(s, input)
                .pattern(t)
                .pattern(m)
                .pattern(b).unlockedBy(inputToKey(input), has(input)).save(recipeConsumer, "jitl:" + getItemFromRegistryName(input.toString()) + name);
    }

    protected void addShapedRecipe(RecipeOutput recipeConsumer, RecipeCategory cat, String m, String b, char s, ItemLike input, char s2, ItemLike input2, char s3, ItemLike input3, ItemLike output, int count) {
        ShapedRecipeBuilder.shaped(cat, output, count).define(s, input).define(s2, input2).define(s3, input3)
                .pattern(m)
                .pattern(b).unlockedBy(inputToKey(input), has(input)).save(recipeConsumer);
    }

    protected void addShapedRecipe(RecipeOutput recipeConsumer, RecipeCategory cat, String t, String m, String b, char s, ItemLike input, ItemLike output, int count) {
        ShapedRecipeBuilder.shaped(cat, output, count).define(s, input)
                .pattern(t)
                .pattern(m)
                .pattern(b).unlockedBy(inputToKey(input), has(input)).save(recipeConsumer);
    }

    protected void addShapedRecipe(RecipeOutput recipeConsumer, RecipeCategory cat, String t, char s, ItemLike input, ItemLike output, int count) {
        ShapedRecipeBuilder.shaped(cat, output, count).define(s, input)
                .pattern(t).unlockedBy(inputToKey(input), has(input)).save(recipeConsumer);
    }

    protected void addShapedRecipe(RecipeOutput recipeConsumer, RecipeCategory cat, String t, String m, char s, ItemLike input, ItemLike output, int count) {
        ShapedRecipeBuilder.shaped(cat, output, count).define(s, input)
                .pattern(t)
                .pattern(m).unlockedBy(inputToKey(input), has(input)).save(recipeConsumer);
    }

    protected void addShapedRecipe(RecipeOutput recipeConsumer, String t, String m, String b, char s, ItemLike input, ItemLike output, int count) {
        addShapedRecipe(recipeConsumer, RecipeCategory.BUILDING_BLOCKS, t, m, b, s, input, output, count);
    }

    protected void addShapedRecipe(RecipeOutput recipeConsumer, RecipeCategory cat, String t, String m, String b, char s, ItemLike input, char s2, ItemLike input2, ItemLike output, int count) {
        ShapedRecipeBuilder.shaped(cat, output, count).define(s, input).define(s2, input2)
                .pattern(t)
                .pattern(m)
                .pattern(b).unlockedBy(inputToKey(input), has(input)).save(recipeConsumer);
    }

    protected void addShapedRecipe(RecipeOutput recipeConsumer, RecipeCategory cat, String t, String m, String b, char s, ItemLike input, char s2, ItemLike input2, char s3, ItemLike input3, ItemLike output, int count) {
        ShapedRecipeBuilder.shaped(cat, output, count).define(s, input).define(s2, input2).define(s3, input3)
                .pattern(t)
                .pattern(m)
                .pattern(b).unlockedBy(inputToKey(input), has(input)).save(recipeConsumer);
    }

    protected void addShapedRecipe(RecipeOutput recipeConsumer, RecipeCategory cat, String t, String m, char s, ItemLike input, char s2, ItemLike input2, ItemLike output, int count) {
        ShapedRecipeBuilder.shaped(cat, output, count).define(s, input).define(s2, input2)
                .pattern(t)
                .pattern(m).unlockedBy(inputToKey(input), has(input)).save(recipeConsumer);
    }

    protected void addShapedRecipe(RecipeOutput recipeConsumer, String t, String m, String b, char s, ItemLike input, char s2, ItemLike input2, ItemLike output, int count) {
        addShapedRecipe(recipeConsumer, RecipeCategory.BUILDING_BLOCKS, t, m, b, s, input, s2, input2, output, count);
    }

    protected void addShapelessRecipe(RecipeOutput recipeConsumer, RecipeCategory cat, ItemLike input, ItemLike output, int count) {
        ShapelessRecipeBuilder.shapeless(cat, output, count).requires(input).unlockedBy(inputToKey(input), has(input)).save(recipeConsumer);
    }

    protected void addShapelessRecipe(RecipeOutput recipeConsumer, RecipeCategory cat, ItemLike input, ItemLike input2, ItemLike output, int count, String name) {
        ShapelessRecipeBuilder.shapeless(cat, output, count).requires(input).requires(input2).unlockedBy(inputToKey(input), has(input)).unlockedBy(inputToKey(input2), has(input2)).save(recipeConsumer, "jitl:" + getItemFromRegistryName(output.toString()) + name);
    }

    protected void addShapelessRecipe(RecipeOutput recipeConsumer, RecipeCategory cat, ItemLike input, ItemLike input2, ItemLike output, int count) {
        addShapelessRecipe(recipeConsumer, cat, input, input2, output, count, getItemFromRegistryName(output.toString()).toString());
    }

    protected void addShapelessRecipe(RecipeOutput recipeConsumer, RecipeCategory cat, ItemLike input, ItemLike input2, ItemLike input3, ItemLike output, int count) {
        ShapelessRecipeBuilder.shapeless(cat, output, count).requires(input).requires(input2).requires(input3).unlockedBy(inputToKey(input), has(input)).unlockedBy(inputToKey(input2), has(input2)).save(recipeConsumer);
    }

    protected void addOreBlockRecipe(RecipeOutput recipeConsumer, ItemLike input, ItemLike output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 1).define('#', input)
                .pattern("###")
                .pattern("###")
                .pattern("###").unlockedBy(inputToKey(input), has(input)).save(recipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, input, 9).requires(output)
                .unlockedBy(inputToKey(output), has(output)).group(input.asItem().toString()).save(recipeConsumer, "jitl:" + getItemFromRegistryName(input.toString()) + "_from_block");

        JITL.LOGGER.info(input.asItem().getDescriptionId());
    }

    protected void add2x2Recipe(RecipeOutput recipeConsumer, ItemLike input, ItemLike output, int count, boolean addReverse) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output, count).define('#', input)
                .pattern("##")
                .pattern("##").unlockedBy(inputToKey(input), has(input)).save(recipeConsumer);
        if(addReverse) {
            addShapelessRecipe(recipeConsumer, RecipeCategory.BUILDING_BLOCKS, output, input, 4);
        }
    }

    protected void add2x2Recipe(RecipeOutput recipeConsumer, ItemLike input, ItemLike output, boolean addReverse) {
        add2x2Recipe(recipeConsumer, input, output, 1, addReverse);
    }

    public void addOreDefaultItems(RecipeOutput recipeConsumer, RecipePrefix name, ItemLike block, ItemLike oreBlock, ItemLike deepslateOre, ItemLike raw, ItemLike ingot, ItemLike stickItem) {
        addToolsetAndArmorRecipes(recipeConsumer, stickItem, ingot, name);
        addShieldRecipe(recipeConsumer, ingot,  getItemFromRegistryName(name.getString() + "shield"));
        addOreBlockRecipe(recipeConsumer, ingot, block);
        addBlastingRecipe(recipeConsumer, oreBlock, ingot, 1.0F, 100, "_ore_blasting");
        addSmeltingRecipe(recipeConsumer, oreBlock, ingot, 1.0F, 200,  "_ore_smelting");
        if(deepslateOre != null) {
            addBlastingRecipe(recipeConsumer, deepslateOre, ingot, 1.0F, 100, "_deep_blasting");
            addSmeltingRecipe(recipeConsumer, deepslateOre, ingot, 1.0F, 200,  "_deep_smelting");
        }
        if(raw != null) {
            addBlastingRecipe(recipeConsumer, raw, ingot, 1.0F, 100, "_raw_blasting");
            addSmeltingRecipe(recipeConsumer, raw, ingot, 1.0F, 200, "_raw_smelting");
        }
    }

    public void addOreNoArmorItems(RecipeOutput recipeConsumer, RecipePrefix name, ItemLike block, ItemLike oreBlock, ItemLike deepslateOre, ItemLike raw, ItemLike ingot, ItemLike stickItem) {
        addToolsetRecipes(recipeConsumer, stickItem, ingot, name);
        addShieldRecipe(recipeConsumer, ingot,  getItemFromRegistryName(name.getString() + "shield"));
        addOreBlockRecipe(recipeConsumer, ingot, block);
        if(oreBlock != null) {
            addBlastingRecipe(recipeConsumer, oreBlock, ingot, 1.0F, 100, "_ore_blasting");
            addSmeltingRecipe(recipeConsumer, oreBlock, ingot, 1.0F, 200, "_ore_smelting");
        }
        if(deepslateOre != null) {
            addBlastingRecipe(recipeConsumer, deepslateOre, ingot, 1.0F, 100, "_deep_blasting");
            addSmeltingRecipe(recipeConsumer, deepslateOre, ingot, 1.0F, 200,  "_deep_smelting");
        }
        if(raw != null) {
            addBlastingRecipe(recipeConsumer, raw, ingot, 1.0F, 100, "_raw_blasting");
            addSmeltingRecipe(recipeConsumer, raw, ingot, 1.0F, 200, "_raw_smelting");
        }
    }

    protected void addToolsetAndArmorRecipes(RecipeOutput recipeConsumer, ItemLike stickItem, ItemLike materialItem, RecipePrefix recipePrefix) {
        addToolsetRecipes(recipeConsumer, stickItem, materialItem, recipePrefix);
        addArmorRecipes(recipeConsumer, materialItem, recipePrefix);
    }

    protected void addToolsetRecipes(RecipeOutput recipeConsumer, ItemLike stickItem, ItemLike materialItem, RecipePrefix recipePrefix) {
        addAxeRecipe(recipeConsumer, stickItem, materialItem, getItemFromRegistryName((recipePrefix.getString() + "axe")));
        addPickaxeRecipe(recipeConsumer, stickItem, materialItem, getItemFromRegistryName(recipePrefix.getString() + "pickaxe"));
        addShovelRecipe(recipeConsumer, stickItem, materialItem, getItemFromRegistryName(recipePrefix.getString() + "shovel"));
        addSwordRecipe(recipeConsumer, stickItem, materialItem, getItemFromRegistryName(recipePrefix.getString() + "sword"));
        addHoeRecipe(recipeConsumer, stickItem, materialItem, getItemFromRegistryName(recipePrefix.getString() + "hoe"));
        addMultitoolRecipe(recipeConsumer,
                getItemFromRegistryName((recipePrefix.getString() + "axe")), getItemFromRegistryName(recipePrefix.getString() + "pickaxe")
                , getItemFromRegistryName(recipePrefix.getString() + "shovel"), getItemFromRegistryName(recipePrefix.getString() + "hoe")
                , getItemFromRegistryName(recipePrefix.getString() + "multitool"));

    }

    public void addArmorRecipes(RecipeOutput recipeConsumer, ItemLike materialItem, RecipePrefix recipePrefix) {
        addHelmetRecipe(recipeConsumer, materialItem, getItemFromRegistryName((recipePrefix.getString() + "helmet")));
        addChestplateRecipe(recipeConsumer, materialItem, getItemFromRegistryName((recipePrefix.getString() + "chestplate")));
        addLeggingsRecipe(recipeConsumer, materialItem, getItemFromRegistryName((recipePrefix.getString() + "leggings")));
        addBootsRecipe(recipeConsumer, materialItem, getItemFromRegistryName((recipePrefix.getString() + "boots")));
    }

    public void addWoodType(RecipeOutput recipeConsumer, RegistryObject<? extends Block> log, RegistryObject<? extends Block> plank, RegistryObject<? extends Block> stairs, RegistryObject<? extends Block> slab, RegistryObject<? extends Block> fence, RegistryObject<? extends Block> gate, RegistryObject<? extends Block> trapdoor, RegistryObject<? extends Block> pressureplate, RegistryObject<? extends Block> door, RegistryObject<? extends Block> button, RegistryObject<Item> boat) {
        addAxeRecipe(recipeConsumer, Items.STICK, plank.get(), Items.WOODEN_AXE, plank.get().getDescriptionId());
        addPickaxeRecipe(recipeConsumer, Items.STICK, plank.get(), Items.WOODEN_PICKAXE, plank.get().getDescriptionId());
        addShovelRecipe(recipeConsumer, Items.STICK, plank.get(), Items.WOODEN_SHOVEL, plank.get().getDescriptionId());
        addSwordRecipe(recipeConsumer, Items.STICK, plank.get(), Items.WOODEN_SWORD, plank.get().getDescriptionId());
        addHoeRecipe(recipeConsumer, Items.STICK, plank.get(), Items.WOODEN_HOE, plank.get().getDescriptionId());
        addBoatRecipe(recipeConsumer, plank.get(), boat.get());
        addStairRecipe(recipeConsumer, plank.get(), stairs.get());
        addSlabRecipe(recipeConsumer, plank.get(), slab.get());
        addFenceRecipe(recipeConsumer, plank.get(), Items.STICK, fence.get());
        addFenceGateRecipe(recipeConsumer, Items.STICK, plank.get(), gate.get());
        addTrapdoorRecipe(recipeConsumer, plank.get(), trapdoor.get());
        addPressureplateRecipe(recipeConsumer, plank.get(), pressureplate.get());
        addDoorRecipe(recipeConsumer, plank.get(), door.get());
        addStick(recipeConsumer, plank.get());
        planksFromLogs(recipeConsumer, plank.get(), log.get());
        buttonBuilder(recipeConsumer, button.get(), plank.get());
    }

    public void addQuartzType(RecipeOutput recipeConsumer, RegistryObject<? extends Item> quartz, RegistryObject<? extends Block> quartzOre, RegistryObject<? extends Block> quartzBlock, RegistryObject<? extends Block> smoothQuartzBlock, RegistryObject<? extends Block> quartzStairs, RegistryObject<? extends Block> smoothQuartzStairs, RegistryObject<? extends Block> quartzSlab, RegistryObject<? extends Block> smoothQuartzSlab
            , RegistryObject<? extends Block> quartzBricks, RegistryObject<? extends Block> chiseledQuartzBlock, RegistryObject<? extends Block> quartzPillar) {

        addSmeltingAndBlastingRecipe(recipeConsumer, quartzBlock.get(), smoothQuartzBlock.get());
        addSmeltingAndBlastingRecipe(recipeConsumer, quartzOre.get(), quartz.get());
        add2x2Recipe(recipeConsumer, quartz.get(), quartzBlock.get(), false);
        addStairRecipe(recipeConsumer, quartzBlock.get(), quartzStairs.get());
        addSlabRecipe(recipeConsumer, quartzBlock.get(), quartzSlab.get());
        addStairRecipe(recipeConsumer, smoothQuartzBlock.get(), smoothQuartzStairs.get());
        addSlabRecipe(recipeConsumer, smoothQuartzBlock.get(), smoothQuartzSlab.get());
        addShapedRecipe(recipeConsumer, RecipeCategory.BUILDING_BLOCKS, "s", "s", 's', quartzSlab.get(), chiseledQuartzBlock.get(), 1);
        addShapedRecipe(recipeConsumer, RecipeCategory.BUILDING_BLOCKS, "s", "s", 's', quartzBlock.get(), quartzPillar.get(), 2);
        add2x2Recipe(recipeConsumer, quartzBlock.get(), quartzBricks.get(), 4,false);

    }

    protected void buttonBuilder(RecipeOutput recipeConsumer, ItemLike button, ItemLike material) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, button).requires(material).unlockedBy(inputToKey(material), has(material)).save(recipeConsumer);
    }

    protected void planksFromLogs(RecipeOutput finishedRecipeConsumer, ItemLike planks, ItemLike logs) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planks, 4).requires(logs).unlockedBy(inputToKey(logs), has(logs)).save(finishedRecipeConsumer);
    }

    protected void addStick(RecipeOutput recipeConsumer, ItemLike materialItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.STICK, 4).define('#', materialItem)
                .pattern("#")
                .pattern("#").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer, "jitl:" + materialItem.asItem().getDescriptionId() + "_to_stick");
    }

    protected void addDoorRecipe(RecipeOutput recipeConsumer, ItemLike materialItem, ItemLike output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 1).define('#', materialItem)
                .pattern("##")
                .pattern("##")
                .pattern("##").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
    }

    protected void addPressureplateRecipe(RecipeOutput recipeConsumer, ItemLike materialItem, ItemLike output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 1).define('#', materialItem)
                .pattern("##").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
    }

    protected void addTrapdoorRecipe(RecipeOutput recipeConsumer, ItemLike materialItem, ItemLike output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 1).define('#', materialItem)
                .pattern("###")
                .pattern("###").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
    }

    protected void addFenceGateRecipe(RecipeOutput recipeConsumer, ItemLike stickItem, ItemLike materialItem, ItemLike output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 1).define('#', materialItem).define('I', stickItem)
                .pattern("#I#")
                .pattern("#I#").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
    }

    protected void addFenceRecipe(RecipeOutput recipeConsumer, ItemLike stickItem, ItemLike materialItem, ItemLike output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 1).define('#', materialItem).define('I', stickItem)
                .pattern("#I#")
                .pattern("#I#").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
    }

    protected void addSlabRecipe(RecipeOutput recipeConsumer, ItemLike materialItem, ItemLike output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 6).define('#', materialItem)
                .pattern("###").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
    }

    protected void addStairRecipe(RecipeOutput recipeConsumer, ItemLike materialItem, ItemLike output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 1).define('#', materialItem)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
    }

    protected void addBoatRecipe(RecipeOutput recipeConsumer, ItemLike materialItem, ItemLike output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, output, 1).define('#', materialItem)
                .pattern("# #")
                .pattern("###").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
    }

    protected void addPickaxeRecipe(RecipeOutput recipeConsumer, ItemLike stickItem, ItemLike materialItem, ItemLike output, String name) {
        if(!Objects.equals(name, "")) {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1).define('#', materialItem).define('I', stickItem)
                    .pattern("###")
                    .pattern(" I ")
                    .pattern(" I ").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer, "jitl:" + materialItem.asItem().getDescriptionId() + "_to_pickaxe");
        } else {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1).define('#', materialItem).define('I', stickItem)
                    .pattern("###")
                    .pattern(" I ")
                    .pattern(" I ").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
        }
    }

    protected void addShovelRecipe(RecipeOutput recipeConsumer, ItemLike stickItem, ItemLike materialItem, ItemLike output, String name) {
        if(!Objects.equals(name, "")) {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1).define('#', materialItem).define('I', stickItem)
                    .pattern("#")
                    .pattern("I")
                    .pattern("I").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer, "jitl:" + materialItem.asItem().getDescriptionId() + "_to_shovel");
        } else {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1).define('#', materialItem).define('I', stickItem)
                    .pattern("#")
                    .pattern("I")
                    .pattern("I").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
        }
    }

    protected void addAxeRecipe(RecipeOutput recipeConsumer, ItemLike stickItem, ItemLike materialItem, ItemLike output, String name) {
        if(!Objects.equals(name, "")) {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1).define('#', materialItem).define('I', stickItem)
                    .pattern("##")
                    .pattern("#I")
                    .pattern(" I").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer, "jitl:" + materialItem.asItem().getDescriptionId() + "_to_axe");
        } else {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1).define('#', materialItem).define('I', stickItem)
                    .pattern("##")
                    .pattern("#I")
                    .pattern(" I").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
        }
    }

    protected void addHoeRecipe(RecipeOutput recipeConsumer, ItemLike stickItem, ItemLike materialItem, ItemLike output, String name) {
       if(!Objects.equals(name, "")) {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1).define('#', materialItem).define('I', stickItem)
                    .pattern("##")
                    .pattern(" I")
                    .pattern(" I").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer, "jitl:" + materialItem.asItem().getDescriptionId() + "_to_hoe");
        } else {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1).define('#', materialItem).define('I', stickItem)
                    .pattern("##")
                    .pattern(" I")
                    .pattern(" I").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
        }
    }

    protected void addMultitoolRecipe(RecipeOutput recipeConsumer, ItemLike axe, ItemLike pick, ItemLike shovel, ItemLike hoe, ItemLike output) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, output).requires(axe).requires(pick).requires(shovel).requires(hoe).unlockedBy(inputToKey(axe), has(axe)).save(recipeConsumer);
    }

    protected void addSwordRecipe(RecipeOutput recipeConsumer, ItemLike stickItem, ItemLike materialItem, ItemLike output, String name) {
        if(!Objects.equals(name, "")) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1).define('#', materialItem).define('I', stickItem)
                    .pattern("#")
                    .pattern("#")
                    .pattern("I").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer, "jitl:" + materialItem.asItem().getDescriptionId() + "_to_sword");
        } else {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1).define('#', materialItem).define('I', stickItem)
                    .pattern("#")
                    .pattern("#")
                    .pattern("I").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
        }
    }

    protected void addPickaxeRecipe(RecipeOutput recipeConsumer, ItemLike stickItem, ItemLike materialItem, ItemLike output) {
        addPickaxeRecipe(recipeConsumer, stickItem, materialItem, output, "");
    }

    protected void addShovelRecipe(RecipeOutput recipeConsumer, ItemLike stickItem, ItemLike materialItem, ItemLike output) {
        addShovelRecipe(recipeConsumer, stickItem, materialItem, output, "");

    }

    protected void addAxeRecipe(RecipeOutput recipeConsumer, ItemLike stickItem, ItemLike materialItem, ItemLike output) {
        addAxeRecipe(recipeConsumer, stickItem, materialItem, output, "");

    }

    protected void addHoeRecipe(RecipeOutput recipeConsumer, ItemLike stickItem, ItemLike materialItem, ItemLike output) {
        addHoeRecipe(recipeConsumer, stickItem, materialItem, output, "");
    }

    protected void addSwordRecipe(RecipeOutput recipeConsumer, ItemLike stickItem, ItemLike materialItem, ItemLike output) {
        addSwordRecipe(recipeConsumer, stickItem, materialItem, output, "");
    }

    protected static void threeByThreePacker(@NotNull RecipeOutput pRecipeOutput, @NotNull RecipeCategory pCategory, ItemLike pPacked, @NotNull ItemLike pUnpacked, @NotNull String pCriterionName) {
        ShapelessRecipeBuilder.shapeless(pCategory, pPacked).requires(pUnpacked, 9).unlockedBy(pCriterionName, has(pUnpacked)).save(pRecipeOutput);
    }

    protected void addShieldRecipe(RecipeOutput recipeConsumer, ItemLike materialItem, ItemLike output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1).define('#', ItemTags.PLANKS).define('M', materialItem)
                .pattern("#M#")
                .pattern("###")
                .pattern(" # ").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
    }

    protected void addHelmetRecipe(RecipeOutput recipeConsumer, ItemLike materialItem, ItemLike output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1).define('#', materialItem)
                .pattern("###")
                .pattern("# #").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
    }

    protected void addChestplateRecipe(RecipeOutput recipeConsumer, ItemLike materialItem, ItemLike output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1).define('#', materialItem)
                .pattern("# #")
                .pattern("###")
                .pattern("###").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
    }

    protected void addLeggingsRecipe(RecipeOutput recipeConsumer, ItemLike materialItem, ItemLike output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1).define('#', materialItem)
                .pattern("###")
                .pattern("# #")
                .pattern("# #").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
    }

    protected void addBootsRecipe(RecipeOutput recipeConsumer, ItemLike materialItem, ItemLike output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1).define('#', materialItem)
                .pattern("# #")
                .pattern("# #").unlockedBy(inputToKey(materialItem), has(materialItem)).save(recipeConsumer);
    }

    protected void addSmeltingRecipe(RecipeOutput consumer, ItemLike input, ItemLike output, float xpGiven, int time, String name) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.MISC, output, xpGiven, time).unlockedBy(inputToKey(input), has(input)).save(consumer, "jitl:" + output.asItem().getDescriptionId() + name);
    }

    protected void addBlastingRecipe(RecipeOutput consumer, ItemLike input, ItemLike output, float xpGiven, int time, String name) {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(input), RecipeCategory.MISC, output, xpGiven, time).unlockedBy(inputToKey(input), has(input)).save(consumer, "jitl:" + output.asItem().getDescriptionId() + name);
    }

    protected void addSmeltingRecipe(RecipeOutput consumer, ItemLike input, ItemLike output, float xpGiven, int time) {
        addSmeltingRecipe(consumer, input, output, xpGiven, time, "");
    }

    protected void addBlastingRecipe(RecipeOutput consumer, ItemLike input, ItemLike output, float xpGiven, int time) {
        addBlastingRecipe(consumer, input, output, xpGiven, time, "");
    }

    protected void addSmeltingAndBlastingRecipe(RecipeOutput consumer, ItemLike input, ItemLike output) {
        addSmeltingRecipe(consumer, input, output, 1F, 200, "_smelting");
        addBlastingRecipe(consumer, input, output, 1F, 100, "_blasting");
    }

    protected void addCookingRecipe(ItemLike input, ItemLike output, float xpGiven) {
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(input), RecipeCategory.MISC, output, xpGiven, 100);
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(input), RecipeCategory.MISC, output, xpGiven, 600);
    }

    protected String inputToKey(ItemLike input) {
        String key = "has_" + Objects.requireNonNull(input.asItem().getDescriptionId());
        JITL.LOGGER.info(key);
        return key;
    }

    public ItemLike getItemFromRegistryName(String registryName) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(JITL.MODID, registryName));
    }

    public enum RecipePrefix {

        SAPPHIRE("sapphire_"),
        LUNIUM("lunium_"),
        SHADIUM("shadium_"),
        BLOODCRUST("bloodcrust_"),
        CELESTIUM("celestium_"),
        STORON("storon_"),
        KORITE("korite_"),
        MEKYUM("mekyum_"),
        FLAIRIUM("flairium_"),
        DES("des_"),
        ORBADITE("orbadite_"),
        GORBITE("gorbite_"),
        SOULSTONE("soulstone_"),

        WOODEN("wooden_")
        ;

        String prefix;

        RecipePrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getString() {
            return prefix;
        }
    }
}
