package net.jitl.core.data.recipe;

import net.jitl.core.data.JRecipeProvider;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class JRecipeRegistry extends JRecipeProvider {

    public JRecipeRegistry(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> recipeConsumer) {
        buildBlockRecipes(recipeConsumer);
        buildItemRecipes(recipeConsumer);
        buildToolRecipes(recipeConsumer);
        buildWoodTypes(recipeConsumer);
        buildBlastingRecipes();
        buildSmeltingRecipes();
        buildCookingRecipes();
        buildSmithingRecipes(recipeConsumer);
    }

    public void buildBlockRecipes(Consumer<FinishedRecipe> recipeConsumer) {
        add3x3Recipe(recipeConsumer, JItems.GREEN_GEM.get(), JBlocks.GREEN_GEM_BLOCK.get());
        add3x3Recipe(recipeConsumer, JItems.PURPLE_GEM.get(), JBlocks.PURPLE_GEM_BLOCK.get());
        add3x3Recipe(recipeConsumer, JItems.BLUE_GEM.get(), JBlocks.BLUE_GEM_BLOCK.get());
        add3x3Recipe(recipeConsumer, JItems.YELLOW_GEM.get(), JBlocks.YELLOW_GEM_BLOCK.get());

        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, JBlocks.GREEN_GEM_BLOCK.get(), JItems.GREEN_GEM.get(), 9);
        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, JBlocks.PURPLE_GEM_BLOCK.get(), JItems.PURPLE_GEM.get(), 9);
        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, JBlocks.BLUE_GEM_BLOCK.get(), JItems.BLUE_GEM.get(), 9);
        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, JBlocks.YELLOW_GEM_BLOCK.get(), JItems.YELLOW_GEM.get(), 9);

        addOreBlockRecipe(recipeConsumer, JItems.SAPPHIRE.get(), JBlocks.SAPPHIRE_BLOCK.get());
        addOreBlockRecipe(recipeConsumer, JItems.LUNIUM_INGOT.get(), JBlocks.LUNIUM_BLOCK.get());
        addOreBlockRecipe(recipeConsumer, JItems.SHADIUM_INGOT.get(), JBlocks.SHADIUM_BLOCK.get());
        addOreBlockRecipe(recipeConsumer, JItems.IRIDIUM_NUGGET.get(), JBlocks.IRIDIUM_BLOCK.get());
        addOreBlockRecipe(recipeConsumer, JItems.BLOODCRUST_INGOT.get(), JBlocks.BLOODCRUST_BLOCK.get());
        addOreBlockRecipe(recipeConsumer, JItems.ENDERILLIUM_SHARD.get(), JBlocks.ENDERILLIUM_BLOCK.get());
        addOreBlockRecipe(recipeConsumer, JItems.ASH.get(), JBlocks.ASHUAL_BLOCK.get());
        addOreBlockRecipe(recipeConsumer, JItems.CELESTIUM_GEMSTONE.get(), JBlocks.CELESTIUM_BLOCK.get());
        addOreBlockRecipe(recipeConsumer, JItems.MEKYUM_GEMSTONE.get(), JBlocks.MEKYUM_BLOCK.get());
        addOreBlockRecipe(recipeConsumer, JItems.STORON_GEMSTONE.get(), JBlocks.STORON_BLOCK.get());
        addOreBlockRecipe(recipeConsumer, JItems.PERIDOT_GEMSTONE.get(), JBlocks.PERIDOT_BLOCK.get());
        addOreBlockRecipe(recipeConsumer, JItems.RIMESTONE.get(), JBlocks.RIMESTONE_BLOCK.get());

        add3x3Recipe(recipeConsumer, JItems.EUCA_PORTAL_GEM.get(), JBlocks.EUCA_PORTAL_FRAME.get(), 10);
        add3x3Recipe(recipeConsumer, JBlocks.DEPTHS_PORTAL_FRAME.get(), JItems.DEPTHS_PORTAL_GEM.get(), 12);
        add3x3Recipe(recipeConsumer, JBlocks.CORBA_PORTAL_FRAME.get(), JItems.CORBA_PORTAL_GEM.get(), 12);
        addShapedRecipe(recipeConsumer, RecipeCategory.BUILDING_BLOCKS, "iii", "idi", "iii", 'i', Items.SNOWBALL, 'd', Items.DIAMOND, JBlocks.FROZEN_PORTAL_FRAME.get(), 10);

        addShapedRecipe(recipeConsumer, "sss", "sss", "sss", 's', Items.STRING, Blocks.COBWEB, 1);

    }

    public void buildItemRecipes(Consumer<FinishedRecipe> recipeConsumer) {
        addShapedRecipe(recipeConsumer, RecipeCategory.MISC, "iii", "idi", "iii", 'i', Items.GOLD_INGOT, 'd', Items.DIAMOND, JItems.FLAME_COIN.get(), 1);
        addShapedRecipe(recipeConsumer, RecipeCategory.MISC, "ddd", "did", "ddd", 'd', JItems.STONE_CLUMP.get(), 'i', Blocks.STONE, JItems.REINFORCED_STONE_INGOT.get(), 1);
        addShapedRecipe(recipeConsumer, RecipeCategory.MISC, "ddd", "did", "ddd", 'd', JItems.STONE_CLUMP.get(), 'i', JItems.CAVE_CRYSTAL.get(), JItems.REINFORCED_CRYSTAL_INGOT.get(), 1);
        addShapedRecipe(recipeConsumer, RecipeCategory.MISC, "iei", "iii",  'i', JItems.MAGIC_DUST.get(), 'e', Items.ENDER_PEARL, JItems.CRYSTAL_BALL.get(), 1);
        addShapedRecipe(recipeConsumer, RecipeCategory.COMBAT, "ddd", "did", "ddd", 'd', JItems.DEMONIC_DUST.get(), 'i', JItems.CRYSTAL_BALL.get(), JItems.DEMONIC_BOMB.get(), 16);
        addShapedRecipe(recipeConsumer, RecipeCategory.COMBAT, "ddd", "did", "ddd",  'd', JItems.MAGIC_DUST.get(), 'i', JItems.CRYSTAL_BALL.get(), JItems.MAGIC_BOMB.get(), 1);
        //addShapedRecipe(recipeConsumer, RecipeCategory.MISC,"ddd", "ddd", "ddd", 'd', JItems.CAVE_DUST.get(), JItems.STONE_CLUMP.get(), 4);
        addShapedRecipe(recipeConsumer, RecipeCategory.MISC, "ddd", "ddd", "ddd", 'd', Blocks.STONE, JItems.STONE_CLUMP.get(), 16);
        addShapedRecipe(recipeConsumer, RecipeCategory.MISC, "ddd", "did", "ddd", 'd', JItems.WITHIC_DUST.get(), 'i', JItems.LOST_SOUL.get(), JItems.WITHIC_SOUL.get(), 1);
        addShapedRecipe(recipeConsumer, RecipeCategory.MISC, "ddd", "did", "ddd", 'd', JItems.BLOOD.get(), 'i', JItems.LOST_SOUL.get(), JItems.CONCENTRATED_BLOOD.get(), 1);
        addShapedRecipe(recipeConsumer, RecipeCategory.MISC, "dgd", "did", "dgd", 'd', JItems.FLAMING_SPRING.get(), 'i', JItems.FLAMING_SPROCKET.get(), 'g', Items.GOLD_INGOT, JItems.BOILING_KEY.get(), 1);
        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, JItems.DEMONIC_BONE.get(), JItems.DEMONIC_DUST.get(), 5);
        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, JItems.SMITHSTONE.get(), JItems.SMITHSTONE_DUST.get(), 4);
        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, JItems.BLEEDSTONE.get(), JItems.BLEEDSTONE_DUST.get(), 4);
        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, JItems.BLEEDSTONE_DUST.get(), JItems.SMITHSTONE_DUST.get(), JItems.SOULSTONE.get(), 1);

    }

    public void buildToolRecipes(Consumer<FinishedRecipe> recipeConsumer) {

    }

    public void buildWoodTypes(Consumer<FinishedRecipe> consumer) {
        addWoodType(consumer, JBlocks.EUCA_GOLD_LOG, JBlocks.EUCA_GOLD_PLANKS, JBlocks.EUCA_GOLD_STAIRS, JBlocks.EUCA_GOLD_SLAB, JBlocks.EUCA_GOLD_FENCE, JBlocks.EUCA_GOLD_FENCE_GATE, JBlocks.EUCA_GOLD_TRAP_DOOR, JBlocks.EUCA_GOLD_PRESSURE_PLATE, JBlocks.EUCA_GOLD_DOOR, JBlocks.EUCA_GOLD_BUTTON, JItems.GOLDEN_EUCA_BOAT);
        addWoodType(consumer, JBlocks.EUCA_BROWN_LOG, JBlocks.EUCA_BROWN_PLANKS, JBlocks.EUCA_BROWN_STAIRS, JBlocks.EUCA_BROWN_SLAB, JBlocks.EUCA_BROWN_FENCE, JBlocks.EUCA_BROWN_FENCE_GATE, JBlocks.EUCA_BROWN_TRAP_DOOR, JBlocks.EUCA_BROWN_PRESSURE_PLATE, JBlocks.EUCA_BROWN_DOOR, JBlocks.EUCA_BROWN_BUTTON, JItems.BROWN_EUCA_BOAT);
        addWoodType(consumer, JBlocks.FROZEN_LOG, JBlocks.FROZEN_PLANKS, JBlocks.FROZEN_STAIRS, JBlocks.FROZEN_SLAB, JBlocks.FROZEN_FENCE, JBlocks.FROZEN_FENCE_GATE, JBlocks.FROZEN_TRAP_DOOR, JBlocks.FROZEN_PRESSURE_PLATE, JBlocks.FROZEN_DOOR, JBlocks.FROZEN_BUTTON, JItems.FROZEN_BOAT);
        //addWoodType(consumer, JBlocks.BURNED_BARK, JBlocks.BURNED_BARK_PLANK, JBlocks.BURNED_BARK_STAIRS, JBlocks.BURNED_BARK_PLANK_SLAB, JBlocks.BURNED_BARK_FENCE, JBlocks.BURNED_BARK_GATE, JBlocks.BURNED_BARK_TRAP_DOOR, JBlocks.BURNED_BARK_PRESSURE_PLATE, JBlocks.BURNED_BARK_DOOR, JBlocks.BURNED_BARK_BUTTON, JItems.BURNED_BOAT);
    }

    public void buildBlastingRecipes() {
        addBlastingRecipe(JBlocks.FIRESTONE_ORE.get(), JItems.FIRESTONE_CLUMP.get(), 1.0F, 100);
        addBlastingRecipe(JBlocks.LUNIUM_ORE.get(), JItems.LUNIUM_INGOT.get(), 1.0F, 100);
        addBlastingRecipe(JItems.LUNIUM_POWDER.get(), JItems.LUNIUM_INGOT.get(), 1.0F, 100);
    }

    public void buildSmeltingRecipes() {
        addSmeltingRecipe(JBlocks.FIRESTONE_ORE.get(), JItems.FIRESTONE_CLUMP.get(), 1.0F, 200);
        addSmeltingRecipe(JItems.LUNIUM_POWDER.get(), JItems.LUNIUM_INGOT.get(), 1.0F, 200);
    }

    public void buildCookingRecipes() {
//		addCookingRecipe();
    }

    public void buildSmithingRecipes(Consumer<FinishedRecipe> recipeConsumer) {
        addSmithingRecipe(recipeConsumer, Items.DIAMOND_HELMET, JItems.LUNIUM_INGOT.get(), JItems.LUNIUM_HELMET.get());
        addSmithingRecipe(recipeConsumer, Items.DIAMOND_CHESTPLATE, JItems.LUNIUM_INGOT.get(), JItems.LUNIUM_CHEST.get());
        addSmithingRecipe(recipeConsumer, Items.DIAMOND_LEGGINGS, JItems.LUNIUM_INGOT.get(), JItems.LUNIUM_LEGS.get());
        addSmithingRecipe(recipeConsumer, Items.DIAMOND_BOOTS, JItems.LUNIUM_INGOT.get(), JItems.LUNIUM_BOOTS.get());

        addSmithingRecipe(recipeConsumer, Items.DIAMOND_SWORD, JItems.LUNIUM_INGOT.get(), JItems.LUNIUM_SWORD.get());

        addSmithingRecipe(recipeConsumer, Items.DIAMOND_AXE, JItems.LUNIUM_INGOT.get(), JItems.LUNIUM_AXE.get());
        addSmithingRecipe(recipeConsumer, Items.DIAMOND_PICKAXE, JItems.LUNIUM_INGOT.get(), JItems.LUNIUM_PICKAXE.get());
        addSmithingRecipe(recipeConsumer, Items.DIAMOND_SHOVEL, JItems.LUNIUM_INGOT.get(), JItems.LUNIUM_SHOVEL.get());
        addSmithingRecipe(recipeConsumer, Items.DIAMOND_HOE, JItems.LUNIUM_INGOT.get(), JItems.LUNIUM_HOE.get());
    }
}